package com.inuker.bluetooth.library.connect.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.RuntimeChecker;
import com.inuker.bluetooth.library.connect.IBleConnectDispatcher;
import com.inuker.bluetooth.library.connect.IBleConnectWorker;
import com.inuker.bluetooth.library.connect.listener.GattResponseListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleRequest.class */
public abstract class BleRequest implements IBleConnectWorker, IBleRequest, Handler.Callback, GattResponseListener, RuntimeChecker {
    protected static final int MSG_REQUEST_TIMEOUT = 32;
    protected BleGeneralResponse mResponse;
    protected String mAddress;
    protected IBleConnectDispatcher mDispatcher;
    protected IBleConnectWorker mWorker;
    private RuntimeChecker mRuntimeChecker;
    private boolean mFinished;
    protected boolean mRequestTimeout;
    protected Bundle mExtra = new Bundle();
    protected Handler mHandler = new Handler(Looper.myLooper(), this);
    protected Handler mResponseHandler = new Handler(Looper.getMainLooper());

    public abstract void processRequest();

    public BleRequest(BleGeneralResponse response) {
        this.mResponse = response;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public void setWorker(IBleConnectWorker worker) {
        this.mWorker = worker;
    }

    public void onResponse(final int code) {
        if (this.mFinished) {
            return;
        }
        this.mFinished = true;
        this.mResponseHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.connect.request.BleRequest.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (BleRequest.this.mResponse != null) {
                        BleRequest.this.mResponse.onResponse(code, BleRequest.this.mExtra);
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void putIntExtra(String key, int value) {
        this.mExtra.putInt(key, value);
    }

    public int getIntExtra(String key, int defaultValue) {
        return this.mExtra.getInt(key, defaultValue);
    }

    public void putByteArray(String key, byte[] bytes) {
        this.mExtra.putByteArray(key, bytes);
    }

    public void putParcelable(String key, Parcelable object) {
        this.mExtra.putParcelable(key, object);
    }

    public Bundle getExtra() {
        return this.mExtra;
    }

    protected String getStatusText() {
        return Constants.getStatusText(getCurrentStatus());
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readDescriptor(UUID service, UUID characteristic, UUID descriptor) {
        return this.mWorker.readDescriptor(service, characteristic, descriptor);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeDescriptor(UUID service, UUID characteristic, UUID descriptor, byte[] value) {
        return this.mWorker.writeDescriptor(service, characteristic, descriptor, value);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean openGatt() {
        return this.mWorker.openGatt();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean discoverService() {
        return this.mWorker.discoverService();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public int getCurrentStatus() {
        return this.mWorker.getCurrentStatus();
    }

    @Override // com.inuker.bluetooth.library.connect.request.IBleRequest
    public final void process(IBleConnectDispatcher dispatcher) {
        checkRuntime();
        this.mDispatcher = dispatcher;
        BluetoothLog.w(String.format("Process %s, status = %s", getClass().getSimpleName(), getStatusText()));
        if (!BluetoothUtils.isBleSupported()) {
            onRequestCompleted(-4);
            return;
        }
        if (!BluetoothUtils.isBluetoothEnabled()) {
            onRequestCompleted(-5);
            return;
        }
        try {
            registerGattResponseListener(this);
            processRequest();
        } catch (Throwable e) {
            BluetoothLog.e(e);
            onRequestCompleted(-10);
        }
    }

    protected void onRequestCompleted(int code) {
        checkRuntime();
        log(String.format(Locale.CHINA, "request complete: code = %d", Integer.valueOf(code)));
        this.mHandler.removeCallbacksAndMessages(null);
        clearGattResponseListener(this);
        onResponse(code);
        this.mDispatcher.onRequestCompleted(this);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void closeGatt() {
        log("close gatt");
        BluetoothLog.e("closeGatt ====> BleRequest:-closeGatt-");
        this.mWorker.closeGatt();
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0004. Please report as an issue. */
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
        }
        return true;
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void registerGattResponseListener(GattResponseListener listener) {
        this.mWorker.registerGattResponseListener(listener);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public void clearGattResponseListener(GattResponseListener listener) {
        this.mWorker.clearGattResponseListener(listener);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean refreshDeviceCache() {
        return this.mWorker.refreshDeviceCache();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readCharacteristic(UUID service, UUID characteristic) {
        return this.mWorker.readCharacteristic(service, characteristic);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeCharacteristic(UUID service, UUID character, byte[] value) {
        return this.mWorker.writeCharacteristic(service, character, value);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean writeCharacteristicWithNoRsp(UUID service, UUID character, byte[] value) {
        return this.mWorker.writeCharacteristicWithNoRsp(service, character, value);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean setCharacteristicNotification(UUID service, UUID character, boolean enable) {
        return this.mWorker.setCharacteristicNotification(service, character, enable);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean setCharacteristicIndication(UUID service, UUID character, boolean enable) {
        return this.mWorker.setCharacteristicIndication(service, character, enable);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean readRemoteRssi() {
        return this.mWorker.readRemoteRssi();
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public boolean requestMtu(int mtu) {
        return this.mWorker.requestMtu(mtu);
    }

    protected void log(String msg) {
        BluetoothLog.v(String.format("%s %s >>> %s", getClass().getSimpleName(), getAddress(), msg));
    }

    public void setRuntimeChecker(RuntimeChecker checker) {
        this.mRuntimeChecker = checker;
    }

    @Override // com.inuker.bluetooth.library.RuntimeChecker
    public void checkRuntime() {
    }

    public void cancel() {
        checkRuntime();
        log("request canceled");
        this.mHandler.removeCallbacksAndMessages(null);
        clearGattResponseListener(this);
        onResponse(-2);
    }

    protected long getTimeoutInMillis() {
        return 30000L;
    }

    public void onConnectStatusChanged(boolean connectedOrDisconnected) {
        if (!connectedOrDisconnected) {
            onRequestCompleted(this.mRequestTimeout ? -7 : -1);
        }
    }

    protected void startRequestTiming() {
        this.mHandler.sendEmptyMessageDelayed(32, getTimeoutInMillis());
    }

    protected void stopRequestTiming() {
        this.mHandler.removeMessages(32);
    }

    @Override // com.inuker.bluetooth.library.connect.IBleConnectWorker
    public BleGattProfile getGattProfile() {
        return this.mWorker.getGattProfile();
    }
}
