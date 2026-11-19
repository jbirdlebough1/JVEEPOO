package com.inuker.bluetooth.library.connect.request;

import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.connect.listener.RequestMtuListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleMtuRequest.class */
public class BleMtuRequest extends BleRequest implements RequestMtuListener {
    private int mMtu;

    public BleMtuRequest(int mtu, BleGeneralResponse response) {
        super(response);
        this.mMtu = mtu;
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        switch (getCurrentStatus()) {
            case 0:
                onRequestCompleted(-1);
                break;
            case 2:
                requestMtu();
                break;
            case 19:
                requestMtu();
                break;
            default:
                onRequestCompleted(-1);
                break;
        }
    }

    private void requestMtu() {
        if (!requestMtu(this.mMtu)) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.RequestMtuListener
    public void onMtuChanged(int mtu, int status) {
        stopRequestTiming();
        if (status == 0) {
            putIntExtra(Constants.EXTRA_MTU, mtu);
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
