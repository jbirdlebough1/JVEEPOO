package com.inuker.bluetooth.library.bluetrum.manager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.inuker.bluetooth.library.bluetrum.log.Logger;
import com.inuker.bluetooth.library.bluetrum.utils.HexUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/manager/OtaManager.class */
public abstract class OtaManager {
    private static final String TAG = OtaManager.class.getSimpleName();
    private static final int DEFAULT_TIMEOUT = 10000;
    private static final int UNDEFINED_FIRMWARE_VERSION = -1;
    private final EventListener eventListener;
    protected OtaDataProvider dataProvider;
    protected boolean allowedUpdate;
    protected static final int DELAY_AFTER_SEND_IDENTIFICATION = 200;
    protected int otaFirmwareVersion = -1;
    protected int deviceFirmwareVersion = -1;
    private int mBlockSize = OtaConstants.DEFAULT_BLOCK_SIZE;
    private int mPacketSize = OtaConstants.DEFAULT_PACKET_SIZE;
    protected boolean isUpdating = false;
    protected boolean isUpdatePause = false;
    protected Boolean isTwsDevice = null;
    protected Boolean isTwsConnected = null;
    public boolean disconnectedDueToDeviceError = false;
    private boolean _needIdentification = true;
    protected boolean sentIdentification = false;
    private final Handler notifyHandler = new Handler(Looper.getMainLooper());
    private final Handler timeoutHandler = new Handler(Looper.getMainLooper());
    protected OtaCommandGenerator commandGenerator = new OtaCommandGenerator();

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/manager/OtaManager$EventListener.class */
    public interface EventListener {
        void onOtaReady();

        void onOtaStart();

        void onOtaProgress(int i);

        void onOtaStop();

        void onOtaFinish();

        void onOtaPause();

        void onOtaContinue();

        void onOtaError(OtaError otaError);

        void onTWSDisconnected();

        void onReceiveVersion(int i);

        void onReceiveIsTWS(boolean z);

        void onReceiveTWSConnected(boolean z);

        void onReceiveChannel(boolean z);
    }

    protected abstract void btSendData(byte[] bArr);

    public OtaManager(@NonNull EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void init() {
    }

    public void release() {
        this.otaFirmwareVersion = -1;
        this.deviceFirmwareVersion = -1;
        this.allowedUpdate = false;
        this.isUpdating = false;
        this.isUpdatePause = false;
        this.sentIdentification = false;
        cancelTimeout();
        this.commandGenerator.reset();
    }

    public boolean isCompressedData() {
        return this.dataProvider.isCompressedData();
    }

    protected int getPacketSize() {
        return this.mPacketSize;
    }

    public void setPacketSize(int packetSize) {
        this.mPacketSize = packetSize;
    }

    protected int getBlockSize() {
        return this.mBlockSize;
    }

    public void prepareToUpdate() {
        if (needIdentification()) {
            sendOtaIdentification();
        } else {
            getAllInfo();
        }
    }

    public void startOTA() {
        this.isUpdating = true;
        notifyOnStart();
        getOtaInfoVersion();
    }

    public boolean isDeviceReady() {
        Logger.i(TAG, "isDeviceReady: T:" + this.isTwsDevice + ", C:" + this.isTwsConnected);
        if (this.isTwsDevice == null) {
            return false;
        }
        if (this.isTwsDevice.booleanValue()) {
            return isTwsConnected();
        }
        return true;
    }

    public boolean isReadyToUpdate() {
        Logger.i(TAG, "isReadyToUpdate: R:" + isDeviceReady() + ", D:" + (this.dataProvider != null));
        return isDeviceReady() && this.dataProvider != null;
    }

    protected boolean canSendNow() {
        return this.allowedUpdate && !this.dataProvider.isBlockSendFinish();
    }

    public void setOtaData(@NonNull byte[] otaData) {
        this.dataProvider = new OtaDataProvider(otaData);
        this.dataProvider.setBlockSize(getBlockSize());
        this.dataProvider.setPacketSize(getPacketSize());
        if (this.commandGenerator != null) {
            this.commandGenerator.setDataProvider(this.dataProvider);
        } else {
            this.commandGenerator = new OtaCommandGenerator(this.dataProvider);
        }
        checkIfReadyToUpdate();
    }

    public boolean isUpdating() {
        return this.isUpdating;
    }

    public boolean isTwsDevice() {
        return this.isTwsDevice != null && this.isTwsDevice.booleanValue();
    }

    public boolean isTwsConnected() {
        return this.isTwsConnected != null && this.isTwsConnected.booleanValue();
    }

    protected void checkIfReadyToUpdate() {
        boolean isReadyToUpdate = isReadyToUpdate();
        Logger.i(TAG, "checkIfReadyToUpdate: " + isReadyToUpdate);
        if (isReadyToUpdate) {
            notifyOnReady();
        }
    }

    protected void notifyOnReady() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onOtaReady);
    }

    protected void notifyOnStart() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onOtaStart);
    }

    protected void notifyOnProgress(int progress) {
        this.notifyHandler.post(() -> {
            this.eventListener.onOtaProgress(progress);
        });
    }

    protected void notifyOnStop() {
        if (!this.disconnectedDueToDeviceError) {
            Handler handler = this.notifyHandler;
            EventListener eventListener = this.eventListener;
            Objects.requireNonNull(eventListener);
            handler.post(eventListener::onOtaStop);
        }
        this.disconnectedDueToDeviceError = false;
    }

    protected void notifyOnFinish() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onOtaFinish);
    }

    protected void notifyOnPause() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onOtaPause);
    }

    protected void notifyOnContinue() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onOtaContinue);
    }

    protected void notifyOnError(OtaError error) {
        this.disconnectedDueToDeviceError = true;
        this.notifyHandler.post(() -> {
            this.eventListener.onOtaError(error);
        });
        release();
    }

    protected void notifyTWSDisconnected() {
        Handler handler = this.notifyHandler;
        EventListener eventListener = this.eventListener;
        Objects.requireNonNull(eventListener);
        handler.post(eventListener::onTWSDisconnected);
    }

    protected void notifyOnReceiveVersion(int version) {
        this.notifyHandler.post(() -> {
            this.eventListener.onReceiveVersion(version);
        });
    }

    protected void notifyOnReceiveIsTWS(boolean isTwsDevice) {
        this.notifyHandler.post(() -> {
            this.eventListener.onReceiveIsTWS(isTwsDevice);
        });
    }

    protected void notifyOnReceiveTWSConnected(boolean connected) {
        this.notifyHandler.post(() -> {
            this.eventListener.onReceiveTWSConnected(connected);
        });
    }

    protected void notifyOnReceiveChannel(boolean isLeftChannel) {
        this.notifyHandler.post(() -> {
            this.eventListener.onReceiveChannel(isLeftChannel);
        });
    }

    protected void getOtaInfoVersion() {
        byte[] cmd = this.commandGenerator.cmdGetInfoVersion();
        btSendData(cmd);
    }

    protected void getOtaInfoUpdate(int version) {
        if (this.dataProvider != null) {
            byte[] hashData = this.dataProvider.getHash();
            byte[] cmd = this.commandGenerator.cmdGetInfoUpdate(version, hashData);
            btSendData(cmd);
        }
    }

    protected void sendOtaIdentification() {
        byte[] cmd = this.commandGenerator.cmdOtaIdentification();
        btSendData(cmd);
    }

    protected void getAllInfo() {
        byte[] cmd = this.commandGenerator.cmdGetAllInfo();
        btSendData(cmd);
    }

    protected void sendOtaStart() {
        byte[] cmd = this.commandGenerator.cmdStartSend();
        btSendData(cmd);
        int progress = this.dataProvider.getProgress();
        notifyOnProgress(progress);
    }

    protected void sendOtaData() {
        byte[] cmd = this.commandGenerator.cmdSendData();
        btSendData(cmd);
        int progress = this.dataProvider.getProgress();
        notifyOnProgress(progress);
    }

    protected void sendOtaDataOnce() {
        sendOtaData();
    }

    protected void runDataSend() {
        Logger.e(TAG + "中科", "-runDataSend-needIdentification = " + needIdentification() + ",sentIdentification = " + this.sentIdentification);
        if (!needIdentification() || this.sentIdentification) {
            if (canSendNow()) {
                Logger.e(TAG + "中科", "-runDataSend- 1 ");
                sendOtaDataOnce();
                return;
            } else {
                if (this.dataProvider != null && this.dataProvider.isBlockSendFinish()) {
                    Logger.e(TAG + "中科", "-runDataSend- 2 ");
                    this.timeoutHandler.postDelayed(this::handleTimeout, 10000L);
                    return;
                }
                return;
            }
        }
        Logger.e(TAG + "中科", "-runDataSend- 3 ");
        HandlerThread thread = new HandlerThread("Get All Info");
        thread.start();
        new Handler(thread.getLooper()).postDelayed(() -> {
            this.sentIdentification = true;
            Logger.e(TAG + "中科", "-runDataSend- 4 ");
            getAllInfo();
        }, 200L);
    }

    private void handleTimeout() {
        if (this.isUpdatePause) {
            return;
        }
        Logger.e(TAG, "Timed out while waiting for response");
        notifyOnError(OtaError.TIMEOUT_RECEIVE_RESPONSE);
    }

    private void cancelTimeout() {
        this.timeoutHandler.removeCallbacksAndMessages(null);
    }

    public boolean processData(byte[] data) {
        if (data.length < 3) {
            Logger.w(TAG, "The length of received data less than 3");
            return false;
        }
        ByteBuffer bb = ByteBuffer.wrap(data);
        byte cmdType = bb.get();
        bb.get();
        switch (cmdType) {
            case OtaCommandGenerator.CMD_NOTIFY_STATUS /* -112 */:
                byte status = bb.get();
                processNotifyStatus(status);
                return true;
            case OtaCommandGenerator.CMD_GET_INFO /* -111 */:
                byte cmdSubType = bb.get();
                byte[] extraData = new byte[bb.remaining()];
                bb.get(extraData, 0, bb.remaining());
                processGetInfo(cmdSubType, extraData);
                return true;
            case OtaCommandGenerator.CMD_GET_INFO_TLV /* -110 */:
                byte[] infoData = new byte[bb.remaining()];
                bb.get(infoData, 0, bb.remaining());
                processGetInfoTLV(infoData);
                return true;
            default:
                return false;
        }
    }

    protected void processNotifyStatus(byte status) {
        switch (status) {
            case OtaCommandGenerator.STATE_TWS_DISCONNECTED /* -128 */:
                cancelTimeout();
                this.isUpdating = false;
                this.isTwsConnected = false;
                notifyTWSDisconnected();
                notifyOnReceiveTWSConnected(this.isTwsConnected.booleanValue());
                break;
            case -3:
                cancelTimeout();
                this.allowedUpdate = false;
                this.isUpdating = false;
                this.isUpdatePause = true;
                notifyOnPause();
                break;
            case -2:
                notifyOnContinue();
                this.isUpdating = true;
                this.isUpdatePause = false;
                getOtaInfoVersion();
                break;
            case -1:
                cancelTimeout();
                notifyOnFinish();
                release();
                break;
            case 0:
                cancelTimeout();
                if (!this.isUpdatePause && !this.dataProvider.isAllDataSent()) {
                    sendOtaStart();
                    break;
                }
                break;
            default:
                OtaError.REPORT_FROM_DEVICE.setDeviceErrorCode(status);
                cancelTimeout();
                notifyOnError(OtaError.REPORT_FROM_DEVICE);
                break;
        }
    }

    protected void processGetInfo(byte cmdSubType, byte[] extraData) {
        processInfo(cmdSubType, extraData);
        switch (cmdSubType) {
            case 1:
                getOtaInfoUpdate(this.otaFirmwareVersion);
                break;
            case 2:
                if (this.allowedUpdate) {
                    sendOtaStart();
                } else {
                    cancelTimeout();
                    notifyOnError(OtaError.REFUSED_BY_DEVICE);
                }
                break;
        }
    }

    private void processGetInfoTLV(byte[] extraData) {
        while (extraData.length > 2) {
            ByteBuffer bb = ByteBuffer.wrap(extraData);
            byte infoType = bb.get();
            byte[] infoData = new byte[bb.get()];
            bb.get(infoData);
            processInfo(infoType, infoData);
            if (!bb.hasRemaining()) {
                break;
            }
            byte[] remainingData = new byte[bb.remaining()];
            bb.get(remainingData);
            extraData = remainingData;
        }
        checkIfReadyToUpdate();
    }

    private void processInfo(byte infoType, byte[] infoData) {
        Logger.d(TAG, "processInfo: " + ((int) infoType) + " -> " + HexUtils.bytesToHex(infoData));
        switch (infoType) {
            case 1:
                if (infoData.length == 2) {
                    ByteBuffer bb = ByteBuffer.wrap(infoData).order(ByteOrder.LITTLE_ENDIAN);
                    int version = bb.getShort();
                    notifyOnReceiveVersion(version);
                    this.deviceFirmwareVersion = version;
                }
                break;
            case 2:
                if (infoData.length == 11) {
                    ByteBuffer bb2 = ByteBuffer.wrap(infoData).order(ByteOrder.LITTLE_ENDIAN);
                    int startAddress = bb2.getInt();
                    this.dataProvider.setStartAddress(startAddress);
                    Logger.d(TAG, "startAddress = " + startAddress);
                    int blockSize = bb2.getInt();
                    this.mBlockSize = blockSize;
                    this.dataProvider.setBlockSize(blockSize);
                    Logger.d(TAG, "blockSize = " + blockSize);
                    int packetSize = bb2.getShort();
                    this.mPacketSize = packetSize;
                    this.dataProvider.setPacketSize(packetSize);
                    Logger.d(TAG, "packetSize = " + packetSize);
                    this.allowedUpdate = bb2.get() == 1;
                    Logger.d(TAG, "allowedUpdate = " + this.allowedUpdate);
                }
                break;
            case 3:
                if (infoData.length == 2) {
                    ByteBuffer bb3 = ByteBuffer.wrap(infoData).order(ByteOrder.LITTLE_ENDIAN);
                    short dev_info_capabilities = bb3.getShort();
                    this.isTwsDevice = Boolean.valueOf((dev_info_capabilities & 1) != 0);
                    Logger.d(TAG, "isTWS: " + this.isTwsDevice);
                    notifyOnReceiveIsTWS(this.isTwsDevice.booleanValue());
                }
                break;
            case 4:
                if (infoData.length == 2) {
                    ByteBuffer bb4 = ByteBuffer.wrap(infoData).order(ByteOrder.LITTLE_ENDIAN);
                    short device_status = bb4.getShort();
                    this.isTwsConnected = Boolean.valueOf((device_status & 1) != 0);
                    Logger.d(TAG, "isTwsConnected: " + this.isTwsConnected);
                    notifyOnReceiveTWSConnected(this.isTwsConnected.booleanValue());
                }
                break;
            case 6:
                if (infoData.length == 1) {
                    byte channel = infoData[0];
                    if (channel == 1) {
                        Logger.d(TAG, "Channel: Left");
                        notifyOnReceiveChannel(true);
                    } else if (channel == 0) {
                        Logger.d(TAG, "Channel: Right");
                        notifyOnReceiveChannel(false);
                    }
                }
                break;
        }
    }

    public boolean needIdentification() {
        return this._needIdentification;
    }

    public void setNeedIdentification(boolean needIdentification) {
        this._needIdentification = needIdentification;
    }

    public void setOtaFirmwareVersion(int otaFirmwareVersion) {
        this.otaFirmwareVersion = otaFirmwareVersion;
    }
}
