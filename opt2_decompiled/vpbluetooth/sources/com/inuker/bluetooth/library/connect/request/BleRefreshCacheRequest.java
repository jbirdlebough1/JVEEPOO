package com.inuker.bluetooth.library.connect.request;

import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleRefreshCacheRequest.class */
public class BleRefreshCacheRequest extends BleRequest {
    public BleRefreshCacheRequest(BleGeneralResponse response) {
        super(response);
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        refreshDeviceCache();
        this.mHandler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.connect.request.BleRefreshCacheRequest.1
            @Override // java.lang.Runnable
            public void run() {
                BleRefreshCacheRequest.this.onRequestCompleted(0);
            }
        }, 300L);
    }
}
