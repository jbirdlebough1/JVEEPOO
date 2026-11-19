package com.inuker.bluetooth.library.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.RuntimeChecker;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.request.BleConnectRequest;
import com.inuker.bluetooth.library.connect.request.BleIndicateRequest;
import com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest;
import com.inuker.bluetooth.library.connect.request.BleMtuRequest;
import com.inuker.bluetooth.library.connect.request.BleNotifyRequest;
import com.inuker.bluetooth.library.connect.request.BleReadDescriptorRequest;
import com.inuker.bluetooth.library.connect.request.BleReadRequest;
import com.inuker.bluetooth.library.connect.request.BleReadRssiRequest;
import com.inuker.bluetooth.library.connect.request.BleRefreshCacheRequest;
import com.inuker.bluetooth.library.connect.request.BleRequest;
import com.inuker.bluetooth.library.connect.request.BleUnnotifyRequest;
import com.inuker.bluetooth.library.connect.request.BleWriteDescriptorRequest;
import com.inuker.bluetooth.library.connect.request.BleWriteNoRspRequest;
import com.inuker.bluetooth.library.connect.request.BleWriteRequest;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.ota.JLOTAHolder;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.ListUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/BleConnectDispatcher.class */
public class BleConnectDispatcher implements IBleConnectDispatcher, RuntimeChecker, Handler.Callback {
    private static final int MAX_REQUEST_COUNT = 100;
    private static final int MSG_SCHEDULE_NEXT = 18;
    private BleRequest mCurrentRequest;
    private final IBleConnectWorker mWorker;
    private final String mAddress;
    private final List<BleRequest> mBleWorkList = new LinkedList();
    private final Handler mHandler = new Handler(Looper.myLooper(), this);

    public static BleConnectDispatcher newInstance(String mac) {
        return new BleConnectDispatcher(mac);
    }

    private BleConnectDispatcher(String mac) {
        this.mAddress = mac;
        this.mWorker = new BleConnectWorker(mac, this);
    }

    public void connect(BleConnectOptions options, BleGeneralResponse response) {
        addNewRequest(new BleConnectRequest(options, response));
    }

    public void disconnect() {
        checkRuntime();
        BluetoothLog.w("Process disconnect");
        if (this.mCurrentRequest != null) {
            this.mCurrentRequest.cancel();
            this.mCurrentRequest = null;
        }
        for (BleRequest request : this.mBleWorkList) {
            request.cancel();
        }
        this.mBleWorkList.clear();
        BluetoothLog.e("closeGatt ====> BleConnectDispatcher:-disconnect-");
        this.mWorker.closeGatt();
        releaseJLSDK();
    }

    private void releaseJLSDK() {
        WatchManager.getInstance().release();
        JLOTAManager.getInstance().setJLOTAInitSuccess(false);
        RcspAuthManager.getInstance().setAuthPass(false);
        JLBleDataManager.getInstance().stopSendDataThread();
        JLOTAHolder.getInstance().release();
        JLOTAHolder.getInstance().removeOTAListener();
    }

    public void refreshCache() {
        addNewRequest(new BleRefreshCacheRequest(null));
    }

    public void clearRequest(int clearType) {
        checkRuntime();
        BluetoothLog.w(String.format(Locale.CHINA, "clearRequest %d", Integer.valueOf(clearType)));
        List<BleRequest> requestClear = new LinkedList<>();
        if (clearType == 0) {
            requestClear.addAll(this.mBleWorkList);
        } else {
            for (BleRequest request : this.mBleWorkList) {
                if (isRequestMatch(request, clearType)) {
                    requestClear.add(request);
                }
            }
        }
        Iterator<BleRequest> it = requestClear.iterator();
        while (it.hasNext()) {
            it.next().cancel();
        }
        this.mBleWorkList.removeAll(requestClear);
    }

    private boolean isRequestMatch(BleRequest request, int requestType) {
        if ((requestType & 1) != 0) {
            return request instanceof BleReadRequest;
        }
        if ((requestType & 2) != 0) {
            return (request instanceof BleWriteRequest) || (request instanceof BleWriteNoRspRequest);
        }
        if ((requestType & 4) != 0) {
            return (request instanceof BleNotifyRequest) || (request instanceof BleUnnotifyRequest) || (request instanceof BleIndicateRequest);
        }
        if ((requestType & 8) != 0) {
            return request instanceof BleReadRssiRequest;
        }
        return false;
    }

    public void read(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleReadRequest(service, character, response));
    }

    public void write(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteRequest(service, character, bytes, response));
    }

    public void writeNoRsp(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteNoRspRequest(service, character, bytes, response));
    }

    public void readDescriptor(UUID service, UUID character, UUID descriptor, BleGeneralResponse response) {
        addNewRequest(new BleReadDescriptorRequest(service, character, descriptor, response));
    }

    public void writeDescriptor(UUID service, UUID character, UUID descriptor, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteDescriptorRequest(service, character, descriptor, bytes, response));
    }

    public void notify(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleNotifyRequest(service, character, response));
    }

    public void unnotify(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleUnnotifyRequest(service, character, response));
    }

    public void indicate(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleIndicateRequest(service, character, response));
    }

    public void unindicate(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleUnnotifyRequest(service, character, response));
    }

    public void readRemoteRssi(BleGeneralResponse response) {
        addNewRequest(new BleReadRssiRequest(response));
    }

    public void requestMtu(int mtu, BleGeneralResponse response) {
        addNewRequest(new BleMtuRequest(mtu, response));
    }

    public void startJLDeviceAuth(BleGeneralResponse response) {
        addNewRequest(new BleJLDeviceAuthRequest(response));
    }

    private void addNewRequest(BleRequest request) {
        checkRuntime();
        if (this.mBleWorkList.size() < MAX_REQUEST_COUNT) {
            request.setRuntimeChecker(this);
            request.setAddress(this.mAddress);
            request.setWorker(this.mWorker);
            this.mBleWorkList.add(request);
        } else {
            request.onResponse(-8);
        }
        scheduleNextRequest(4L);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectDispatcher
    public void onRequestCompleted(BleRequest request) {
        checkRuntime();
        if (request != this.mCurrentRequest) {
            BluetoothLog.e("request not match");
        }
        this.mCurrentRequest = null;
        scheduleNextRequest(10L);
    }

    private void scheduleNextRequest(long delayInMillis) {
        this.mHandler.sendEmptyMessageDelayed(MSG_SCHEDULE_NEXT, delayInMillis);
    }

    private void scheduleNextRequest() {
        if (this.mCurrentRequest == null && !ListUtils.isEmpty(this.mBleWorkList)) {
            this.mCurrentRequest = this.mBleWorkList.remove(0);
            this.mCurrentRequest.process(this);
        }
    }

    @Override // com.inuker.bluetooth.library.RuntimeChecker
    public void checkRuntime() {
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SCHEDULE_NEXT /* 18 */:
                scheduleNextRequest();
                break;
        }
        return true;
    }
}
