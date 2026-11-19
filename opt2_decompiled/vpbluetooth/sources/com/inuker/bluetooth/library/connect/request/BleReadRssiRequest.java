package com.inuker.bluetooth.library.connect.request;

import com.inuker.bluetooth.library.connect.listener.ReadRssiListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleReadRssiRequest.class */
public class BleReadRssiRequest extends BleRequest implements ReadRssiListener {
    public BleReadRssiRequest(BleGeneralResponse response) {
        super(response);
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        switch (getCurrentStatus()) {
            case 0:
                onRequestCompleted(-1);
                break;
            case 2:
                startReadRssi();
                break;
            case 19:
                startReadRssi();
                break;
            default:
                onRequestCompleted(-1);
                break;
        }
    }

    private void startReadRssi() {
        if (!readRemoteRssi()) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.ReadRssiListener
    public void onReadRemoteRssi(int rssi, int status) {
        stopRequestTiming();
        if (status == 0) {
            putIntExtra("extra.rssi", rssi);
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
