package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.inuker.bluetooth.library.connect.listener.WriteCharacterListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleWriteNoRspRequest.class */
public class BleWriteNoRspRequest extends BleRequest implements WriteCharacterListener {
    private UUID mServiceUUID;
    private UUID mCharacterUUID;
    private byte[] mBytes;

    public BleWriteNoRspRequest(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        super(response);
        this.mServiceUUID = service;
        this.mCharacterUUID = character;
        this.mBytes = bytes;
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        switch (getCurrentStatus()) {
            case 0:
                onRequestCompleted(-1);
                break;
            case 2:
                startWrite();
                break;
            case 19:
                startWrite();
                break;
            default:
                onRequestCompleted(-1);
                break;
        }
    }

    private void startWrite() {
        if (!writeCharacteristicWithNoRsp(this.mServiceUUID, this.mCharacterUUID, this.mBytes)) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.WriteCharacterListener
    public void onCharacteristicWrite(BluetoothGattCharacteristic characteristic, int status, byte[] value) {
        stopRequestTiming();
        if (status == 0) {
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
