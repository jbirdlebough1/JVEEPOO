package com.inuker.bluetooth.library.channel;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.SparseArray;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.channel.packet.ACKPacket;
import com.inuker.bluetooth.library.channel.packet.CTRPacket;
import com.inuker.bluetooth.library.channel.packet.DataPacket;
import com.inuker.bluetooth.library.channel.packet.Packet;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.ByteUtils;
import com.inuker.bluetooth.library.utils.proxy.ProxyBulk;
import com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor;
import com.inuker.bluetooth.library.utils.proxy.ProxyUtils;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/Channel.class */
public abstract class Channel implements IChannel, ProxyInterceptor {
    private static final long TIMEOUT = 5000;
    private static final int MSG_WRITE_CALLBACK = 1;
    private static final String TIMER_EXCEPTION = "exception";
    private byte[] mBytesToWrite;
    private int mCurrentSync;
    private int mTotalBytes;
    private int mFrameCount;
    private ChannelCallback mChannelCallback;
    private Handler mWorkerHandler;
    private int mLastSync;
    private ChannelState mCurrentState = ChannelState.IDLE;
    private final IChannelStateHandler mSyncPacketHandler = new IChannelStateHandler() { // from class: com.inuker.bluetooth.library.channel.Channel.1
        @Override // com.inuker.bluetooth.library.channel.IChannelStateHandler
        public void handleState(Object... args) {
            Channel.this.assertRuntime(false);
            DataPacket dataPacket = (DataPacket) args[0];
            if (dataPacket.getSeq() != Channel.this.mCurrentSync) {
                BluetoothLog.w(String.format("sync packet not matched!!", new Object[0]));
                return;
            }
            if (!Channel.this.onDataPacketRecvd(dataPacket)) {
                BluetoothLog.w(String.format("sync packet repeated!!", new Object[0]));
                return;
            }
            Channel.this.mLastSync = Channel.this.mCurrentSync;
            Channel.this.mCurrentSync = 0;
            Channel.this.startSyncPacket();
        }
    };
    private final IChannelStateHandler mRecvDataHandler = new IChannelStateHandler() { // from class: com.inuker.bluetooth.library.channel.Channel.2
        @Override // com.inuker.bluetooth.library.channel.IChannelStateHandler
        public void handleState(Object... args) {
            Channel.this.assertRuntime(false);
            DataPacket dataPacket = (DataPacket) args[0];
            if (!Channel.this.onDataPacketRecvd(dataPacket)) {
                BluetoothLog.w(String.format("dataPacket repeated!!", new Object[0]));
            } else if (dataPacket.getSeq() == Channel.this.mFrameCount) {
                Channel.this.startSyncPacket();
            } else {
                Channel.this.startTimer(Channel.TIMEOUT, new Timer.TimerCallback("WaitData") { // from class: com.inuker.bluetooth.library.channel.Channel.2.1
                    @Override // com.inuker.bluetooth.library.channel.Timer.TimerCallback
                    public void onTimerCallback() {
                        Channel.this.startSyncPacket();
                    }
                });
            }
        }
    };
    private final IChannelStateHandler mRecvCTRHandler = new IChannelStateHandler() { // from class: com.inuker.bluetooth.library.channel.Channel.3
        @Override // com.inuker.bluetooth.library.channel.IChannelStateHandler
        public void handleState(Object... args) {
            Channel.this.assertRuntime(false);
            CTRPacket ctrPacket = (CTRPacket) args[0];
            Channel.this.mFrameCount = ctrPacket.getFrameCount();
            ACKPacket ackPacket = new ACKPacket(1);
            Channel.this.setCurrentState(ChannelState.READY);
            Channel.this.performWrite(ackPacket, new ChannelCallback() { // from class: com.inuker.bluetooth.library.channel.Channel.3.1
                @Override // com.inuker.bluetooth.library.channel.ChannelCallback
                public void onCallback(int code) {
                    Channel.this.assertRuntime(false);
                    if (code == 0) {
                        Channel.this.setCurrentState(ChannelState.READING);
                        Channel.this.startTimer();
                    } else {
                        Channel.this.resetChannelStatus();
                    }
                }
            });
        }
    };
    private final IChannelStateHandler mWaitStartACKHandler = new IChannelStateHandler() { // from class: com.inuker.bluetooth.library.channel.Channel.4
        @Override // com.inuker.bluetooth.library.channel.IChannelStateHandler
        public void handleState(Object... args) {
            Channel.this.assertRuntime(false);
            Channel.this.setCurrentState(ChannelState.WAIT_START_ACK);
            Channel.this.startTimer();
        }
    };
    private final Timer.TimerCallback mTimeoutHandler = new Timer.TimerCallback(getClass().getSimpleName()) { // from class: com.inuker.bluetooth.library.channel.Channel.5
        @Override // com.inuker.bluetooth.library.channel.Timer.TimerCallback
        public void onTimerCallback() {
            Channel.this.assertRuntime(false);
            Channel.this.onSendCallback(-2);
            Channel.this.resetChannelStatus();
        }
    };
    private final IChannelStateHandler mRecvACKHandler = new IChannelStateHandler() { // from class: com.inuker.bluetooth.library.channel.Channel.6
        @Override // com.inuker.bluetooth.library.channel.IChannelStateHandler
        public void handleState(Object... args) {
            Channel.this.assertRuntime(false);
            ACKPacket ackPacket = (ACKPacket) args[0];
            switch (ackPacket.getStatus()) {
                case 0:
                    Channel.this.onSendCallback(0);
                    Channel.this.resetChannelStatus();
                    break;
                case 1:
                    Channel.this.stopTimer();
                    Channel.this.setCurrentState(ChannelState.WRITING);
                    Channel.this.sendDataPacket(0, true);
                    break;
                case 5:
                    int index = ackPacket.getSeq();
                    if (index >= 1 && index <= Channel.this.mFrameCount) {
                        Channel.this.sendDataPacket(index - 1, false);
                        Channel.this.startTimer();
                        break;
                    }
                    break;
                default:
                    Channel.this.onSendCallback(-1);
                    Channel.this.resetChannelStatus();
                    break;
            }
        }
    };
    private final ChannelStateBlock[] STATE_MACHINE = {new ChannelStateBlock(ChannelState.READY, ChannelEvent.SEND_CTR, this.mWaitStartACKHandler), new ChannelStateBlock(ChannelState.WAIT_START_ACK, ChannelEvent.RECV_ACK, this.mRecvACKHandler), new ChannelStateBlock(ChannelState.SYNC, ChannelEvent.RECV_ACK, this.mRecvACKHandler), new ChannelStateBlock(ChannelState.IDLE, ChannelEvent.RECV_CTR, this.mRecvCTRHandler), new ChannelStateBlock(ChannelState.READING, ChannelEvent.RECV_DATA, this.mRecvDataHandler), new ChannelStateBlock(ChannelState.SYNC_ACK, ChannelEvent.RECV_DATA, this.mSyncPacketHandler)};
    private final IChannel mChannelImpl = new IChannel() { // from class: com.inuker.bluetooth.library.channel.Channel.12
        @Override // com.inuker.bluetooth.library.channel.IChannel
        public void write(byte[] bytes, ChannelCallback callback) {
            throw new UnsupportedOperationException();
        }

        @Override // com.inuker.bluetooth.library.channel.IChannel
        public void onRead(byte[] bytes) {
            Channel.this.performOnRead(bytes);
        }

        @Override // com.inuker.bluetooth.library.channel.IChannel
        public void onRecv(byte[] bytes) {
            throw new UnsupportedOperationException();
        }

        @Override // com.inuker.bluetooth.library.channel.IChannel
        public void send(byte[] value, ChannelCallback callback) {
            Channel.this.performSend(value, callback);
        }
    };
    private final Handler.Callback mCallback = new Handler.Callback() { // from class: com.inuker.bluetooth.library.channel.Channel.13
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    ChannelCallback callback = (ChannelCallback) msg.obj;
                    callback.onCallback(msg.arg1);
                    break;
                default:
                    ProxyBulk.safeInvoke(msg.obj);
                    break;
            }
            return false;
        }
    };
    private SparseArray<Packet> mPacketRecv = new SparseArray<>();
    private IChannel mChannel = (IChannel) ProxyUtils.getProxy(this.mChannelImpl, this);

    public Channel() {
        HandlerThread thread = new HandlerThread(getClass().getSimpleName());
        thread.start();
        this.mWorkerHandler = new Handler(thread.getLooper(), this.mCallback);
    }

    @Override // com.inuker.bluetooth.library.channel.IChannel
    public final void onRead(byte[] bytes) {
        this.mChannel.onRead(bytes);
    }

    @Override // com.inuker.bluetooth.library.channel.IChannel
    public final void send(byte[] value, ChannelCallback callback) {
        BluetoothLog.e(String.format(">>> send %s", new String(value)));
        this.mChannel.send(value, callback);
    }

    private void performWrite(Packet packet, final ChannelCallback callback) {
        assertRuntime(false);
        if (callback == null) {
            throw new NullPointerException("callback can't be null");
        }
        if (!isTimerOn()) {
            startExceptionTimer();
        }
        final byte[] bytes = packet.toBytes();
        BluetoothLog.w(String.format("%s: %s", getLogTag(), packet));
        BluetoothContext.post(new Runnable() { // from class: com.inuker.bluetooth.library.channel.Channel.7
            @Override // java.lang.Runnable
            public void run() {
                Channel.this.write(bytes, Channel.this.new WriteCallback(callback));
            }
        });
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/Channel$WriteCallback.class */
    private class WriteCallback implements ChannelCallback {
        ChannelCallback callback;

        WriteCallback(ChannelCallback callback) {
            this.callback = callback;
        }

        @Override // com.inuker.bluetooth.library.channel.ChannelCallback
        public void onCallback(int code) {
            if (Channel.this.isExceptionTimerOn()) {
                Channel.this.stopTimer();
            }
            Channel.this.mWorkerHandler.obtainMessage(1, code, 0, this.callback).sendToTarget();
        }
    }

    private void sendStartFlowPacket() {
        assertRuntime(false);
        CTRPacket flowPacket = new CTRPacket(this.mFrameCount);
        performWrite(flowPacket, new ChannelCallback() { // from class: com.inuker.bluetooth.library.channel.Channel.8
            @Override // com.inuker.bluetooth.library.channel.ChannelCallback
            public void onCallback(int code) {
                Channel.this.assertRuntime(false);
                if (code == 0) {
                    Channel.this.onPostState(ChannelEvent.SEND_CTR, new Object[0]);
                } else {
                    Channel.this.onSendCallback(-1);
                    Channel.this.resetChannelStatus();
                }
            }
        });
    }

    private void onSendCallback(int code) {
        assertRuntime(false);
        BluetoothLog.v(String.format("%s: code = %d", getLogTag(), Integer.valueOf(code)));
        if (this.mChannelCallback != null) {
            this.mChannelCallback.onCallback(code);
        }
    }

    private boolean onDataPacketRecvd(DataPacket packet) {
        assertRuntime(false);
        if (this.mPacketRecv.get(packet.getSeq()) != null) {
            return false;
        }
        if (packet.getSeq() == this.mFrameCount) {
            packet.setLastFrame();
        }
        this.mPacketRecv.put(packet.getSeq(), packet);
        this.mTotalBytes += packet.getDataLength();
        stopTimer();
        return true;
    }

    private void startSyncPacket() {
        assertRuntime(false);
        BluetoothLog.v(getLogTag());
        startTimer();
        setCurrentState(ChannelState.SYNC);
        if (!syncLostPacket()) {
            final byte[] bytes = getTotalRecvdBytes();
            if (!ByteUtils.isEmpty(bytes)) {
                ACKPacket ackPacket = new ACKPacket(0);
                performWrite(ackPacket, new ChannelCallback() { // from class: com.inuker.bluetooth.library.channel.Channel.9
                    @Override // com.inuker.bluetooth.library.channel.ChannelCallback
                    public void onCallback(int code) {
                        Channel.this.assertRuntime(false);
                        Channel.this.resetChannelStatus();
                        if (code == 0) {
                            Channel.this.dispatchOnReceive(bytes);
                        }
                    }
                });
            } else {
                resetChannelStatus();
            }
        }
    }

    private void dispatchOnReceive(byte[] bytes) {
        BluetoothLog.e(String.format(">>> receive: %s", new String(bytes)));
        BluetoothContext.post(new RecvCallback(bytes));
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/Channel$RecvCallback.class */
    private class RecvCallback implements Runnable {
        private byte[] bytes;

        RecvCallback(byte[] bytes) {
            this.bytes = bytes;
        }

        @Override // java.lang.Runnable
        public void run() {
            Channel.this.onRecv(this.bytes);
        }
    }

    private byte[] getTotalRecvdBytes() {
        assertRuntime(false);
        if (this.mPacketRecv.size() != this.mFrameCount) {
            throw new IllegalStateException();
        }
        BluetoothLog.v(String.format("%s: totalBytes = %d", getLogTag(), Integer.valueOf(this.mTotalBytes)));
        ByteBuffer buffer = ByteBuffer.allocate(this.mTotalBytes);
        for (int i = 1; i <= this.mFrameCount; i++) {
            DataPacket packet = (DataPacket) this.mPacketRecv.get(i);
            packet.fillByteBuffer(buffer);
            if (i == this.mFrameCount && !checkCRC(buffer.array(), packet.getCrc())) {
                BluetoothLog.e(String.format("check crc failed!!", new Object[0]));
                return ByteUtils.EMPTY_BYTES;
            }
        }
        return buffer.array();
    }

    private boolean syncLostPacket() {
        assertRuntime(false);
        BluetoothLog.v(getLogTag());
        int i = this.mLastSync + 1;
        while (i <= this.mFrameCount && this.mPacketRecv.get(i) != null) {
            i++;
        }
        if (i <= this.mFrameCount) {
            this.mCurrentSync = i;
            ACKPacket ackPacket = new ACKPacket(5, i);
            performWrite(ackPacket, new ChannelCallback() { // from class: com.inuker.bluetooth.library.channel.Channel.10
                @Override // com.inuker.bluetooth.library.channel.ChannelCallback
                public void onCallback(int code) {
                    Channel.this.assertRuntime(false);
                    if (code == 0) {
                        Channel.this.setCurrentState(ChannelState.SYNC_ACK);
                        Channel.this.startTimer();
                    } else {
                        Channel.this.resetChannelStatus();
                    }
                }
            });
            return true;
        }
        return false;
    }

    private void resetChannelStatus() {
        assertRuntime(false);
        BluetoothLog.v(getLogTag());
        stopTimer();
        setCurrentState(ChannelState.IDLE);
        this.mBytesToWrite = null;
        this.mFrameCount = 0;
        this.mChannelCallback = null;
        this.mPacketRecv.clear();
        this.mCurrentSync = 0;
        this.mLastSync = 0;
        this.mTotalBytes = 0;
    }

    private void sendDataPacket(final int index, final boolean looped) {
        assertRuntime(false);
        if (index >= this.mFrameCount) {
            BluetoothLog.v(String.format("%s: all packets sended!!", getLogTag()));
            setCurrentState(ChannelState.SYNC);
            startTimer(15000L);
        } else {
            BluetoothLog.v(String.format("%s: index = %d, looped = %b", getLogTag(), Integer.valueOf(index + 1), Boolean.valueOf(looped)));
            int start = index * 18;
            int end = Math.min(this.mBytesToWrite.length, (index + 1) * 18);
            DataPacket dataPacket = new DataPacket(index + 1, this.mBytesToWrite, start, end);
            performWrite(dataPacket, new ChannelCallback() { // from class: com.inuker.bluetooth.library.channel.Channel.11
                @Override // com.inuker.bluetooth.library.channel.ChannelCallback
                public void onCallback(int code) {
                    Channel.this.assertRuntime(false);
                    if (code != 0) {
                        BluetoothLog.w(String.format(">>> packet %d write failed", Integer.valueOf(index)));
                    }
                    if (looped) {
                        Channel.this.sendDataPacket(index + 1, looped);
                    }
                }
            });
        }
    }

    private void setCurrentState(ChannelState state) {
        assertRuntime(false);
        BluetoothLog.v(String.format("%s: state = %s", getLogTag(), state));
        this.mCurrentState = state;
    }

    private void onPostState(ChannelEvent event, Object... args) {
        assertRuntime(false);
        BluetoothLog.v(String.format("%s: state = %s, event = %s", getLogTag(), this.mCurrentState, event));
        for (ChannelStateBlock block : this.STATE_MACHINE) {
            if (block.state == this.mCurrentState && block.event == event) {
                block.handler.handleState(args);
                return;
            }
        }
    }

    private void assertRuntime(boolean sync) {
        Looper target = sync ? Looper.getMainLooper() : this.mWorkerHandler.getLooper();
        if (Looper.myLooper() != target) {
            throw new RuntimeException();
        }
    }

    private void performOnRead(byte[] bytes) {
        assertRuntime(false);
        Packet packet = Packet.getPacket(bytes);
        BluetoothLog.w(String.format("%s: %s", getLogTag(), packet));
        switch (packet.getName()) {
            case "ack":
                onPostState(ChannelEvent.RECV_ACK, packet);
                break;
            case "data":
                onPostState(ChannelEvent.RECV_DATA, packet);
                break;
            case "ctr":
                onPostState(ChannelEvent.RECV_CTR, packet);
                break;
        }
    }

    private void performSend(byte[] value, ChannelCallback callback) {
        assertRuntime(false);
        if (this.mCurrentState != ChannelState.IDLE) {
            callback.onCallback(-3);
            return;
        }
        this.mCurrentState = ChannelState.READY;
        this.mChannelCallback = (ChannelCallback) ProxyUtils.getUIProxy(callback);
        this.mTotalBytes = value.length;
        this.mFrameCount = getFrameCount(this.mTotalBytes);
        BluetoothLog.v(String.format("%s: totalBytes = %d, frameCount = %d", getLogTag(), Integer.valueOf(this.mTotalBytes), Integer.valueOf(this.mFrameCount)));
        this.mBytesToWrite = Arrays.copyOf(value, value.length + 2);
        byte[] crc = CRC16.get(value);
        System.arraycopy(crc, 0, this.mBytesToWrite, value.length, 2);
        sendStartFlowPacket();
    }

    @Override // com.inuker.bluetooth.library.utils.proxy.ProxyInterceptor
    public boolean onIntercept(Object object, Method method, Object[] args) {
        this.mWorkerHandler.obtainMessage(0, new ProxyBulk(object, method, args)).sendToTarget();
        return true;
    }

    private String getLogTag() {
        return String.format("%s.%s", getClass().getSimpleName(), BluetoothContext.getCurrentMethodName());
    }

    private int getFrameCount(int totalBytes) {
        int total = totalBytes + 2;
        return 1 + ((total - 1) / 18);
    }

    private void startTimer() {
        startTimer(TIMEOUT);
    }

    private void startExceptionTimer() {
        startTimer(TIMEOUT, new Timer.TimerCallback(TIMER_EXCEPTION) { // from class: com.inuker.bluetooth.library.channel.Channel.14
            @Override // com.inuker.bluetooth.library.channel.Timer.TimerCallback
            public void onTimerCallback() throws TimeoutException {
                throw new TimeoutException();
            }
        });
    }

    private void startTimer(long duration) {
        startTimer(duration, this.mTimeoutHandler);
    }

    private void startTimer(long duration, Timer.TimerCallback callback) {
        BluetoothLog.v(String.format("%s: duration = %d", getLogTag(), Long.valueOf(duration)));
        Timer.start(callback, duration);
    }

    private void stopTimer() {
        BluetoothLog.v(getLogTag());
        Timer.stop();
    }

    private boolean isTimerOn() {
        return Timer.isRunning();
    }

    private boolean isExceptionTimerOn() {
        return TIMER_EXCEPTION.equals(Timer.getName());
    }

    private boolean checkCRC(byte[] bytes, byte[] crc0) {
        return ByteUtils.equals(crc0, CRC16.get(bytes));
    }
}
