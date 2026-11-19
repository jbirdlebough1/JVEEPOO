package com.inuker.bluetooth.library.connect.request;

import android.os.Message;
import com.inuker.bluetooth.library.connect.listener.ServiceDiscoverListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.utils.BluetoothLog;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleConnectRequest.class */
public class BleConnectRequest extends BleRequest implements ServiceDiscoverListener {
    private static final int MSG_CONNECT = 1;
    private static final int MSG_DISCOVER_SERVICE = 2;
    private static final int MSG_CONNECT_TIMEOUT = 3;
    private static final int MSG_DISCOVER_SERVICE_TIMEOUT = 4;
    private static final int MSG_RETRY_DISCOVER_SERVICE = 5;
    private BleConnectOptions mConnectOptions;
    private int mConnectCount;
    private int mServiceDiscoverCount;

    public BleConnectRequest(BleConnectOptions options, BleGeneralResponse response) {
        super(response);
        this.mConnectOptions = options != null ? options : new BleConnectOptions.Builder().build();
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        processConnect();
    }

    private void processConnect() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mServiceDiscoverCount = 0;
        switch (getCurrentStatus()) {
            case 0:
                if (!doOpenNewGatt()) {
                    BluetoothLog.e("closeGatt ====> BleConnectRequest: -processConnect- STATUS_DEVICE_DISCONNECTED");
                    closeGatt();
                } else {
                    this.mHandler.sendEmptyMessageDelayed(3, this.mConnectOptions.getConnectTimeout());
                }
                break;
            case 2:
                processDiscoverService();
                break;
            case 19:
                onConnectSuccess();
                break;
        }
    }

    private boolean doOpenNewGatt() {
        this.mConnectCount++;
        return openGatt();
    }

    private boolean doDiscoverService() {
        this.mServiceDiscoverCount++;
        return discoverService();
    }

    private void retryConnectIfNeeded() {
        if (this.mConnectCount < this.mConnectOptions.getConnectRetry() + 1) {
            retryConnectLater();
        } else {
            onRequestCompleted(-1);
        }
    }

    private void retryDiscoverServiceIfNeeded() {
        if (this.mServiceDiscoverCount < this.mConnectOptions.getServiceDiscoverRetry() + 1) {
            retryDiscoverServiceLater();
        } else {
            BluetoothLog.e("closeGatt ====> BleConnectRequest:-retryDiscoverServiceIfNeeded-");
            closeGatt();
        }
    }

    private void onServiceDiscoverFailed() {
        BluetoothLog.v(String.format("onServiceDiscoverFailed", new Object[0]));
        refreshDeviceCache();
        this.mHandler.sendEmptyMessage(5);
    }

    private void processDiscoverService() {
        BluetoothLog.v(String.format("processDiscoverService, status = %s", getStatusText()));
        switch (getCurrentStatus()) {
            case 0:
                retryConnectIfNeeded();
                break;
            case 2:
                if (!doDiscoverService()) {
                    onServiceDiscoverFailed();
                } else {
                    this.mHandler.sendEmptyMessageDelayed(4, this.mConnectOptions.getServiceDiscoverTimeout());
                }
                break;
            case 19:
                onConnectSuccess();
                break;
        }
    }

    private void retryConnectLater() {
        log(String.format("retry connect later", new Object[0]));
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.sendEmptyMessageDelayed(1, 1000L);
    }

    private void retryDiscoverServiceLater() {
        log(String.format("retry discover service later", new Object[0]));
        this.mHandler.removeCallbacksAndMessages(null);
        this.mHandler.sendEmptyMessageDelayed(2, 1000L);
    }

    private void processConnectTimeout() {
        log(String.format("connect timeout", new Object[0]));
        this.mHandler.removeCallbacksAndMessages(null);
        BluetoothLog.e("closeGatt ====> BleConnectRequest:-processConnectTimeout-");
        closeGatt();
    }

    private void processDiscoverServiceTimeout() {
        log(String.format("service discover timeout", new Object[0]));
        this.mHandler.removeCallbacksAndMessages(null);
        BluetoothLog.e("closeGatt ====> BleConnectRequest:-processDiscoverServiceTimeout-");
        closeGatt();
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest, android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                processConnect();
                break;
            case 2:
                processDiscoverService();
                break;
            case 3:
                processConnectTimeout();
                break;
            case 4:
                processDiscoverServiceTimeout();
                break;
            case 5:
                retryDiscoverServiceIfNeeded();
                break;
        }
        return super.handleMessage(msg);
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public String toString() {
        return "BleConnectRequest{options=" + this.mConnectOptions + '}';
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest, com.inuker.bluetooth.library.connect.listener.GattResponseListener
    public void onConnectStatusChanged(boolean connectedOrDisconnected) {
        checkRuntime();
        this.mHandler.removeMessages(3);
        if (connectedOrDisconnected) {
            this.mHandler.sendEmptyMessageDelayed(2, 300L);
        } else {
            this.mHandler.removeCallbacksAndMessages(null);
            retryConnectIfNeeded();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.ServiceDiscoverListener
    public void onServicesDiscovered(int status, BleGattProfile profile) {
        checkRuntime();
        this.mHandler.removeMessages(4);
        if (status == 0) {
            onConnectSuccess();
        } else {
            onServiceDiscoverFailed();
        }
    }

    private void onConnectSuccess() {
        BleGattProfile profile = getGattProfile();
        if (profile != null) {
            putParcelable("extra.gatt.profile", profile);
        }
        onRequestCompleted(0);
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest, com.inuker.bluetooth.library.connect.request.IBleRequest
    public void cancel() {
        super.cancel();
    }
}
