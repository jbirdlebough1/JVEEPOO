package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGattCharacteristic;
import com.inuker.bluetooth.library.connect.listener.ReadCharacterListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleReadRequest.class */
public class BleReadRequest extends BleRequest implements ReadCharacterListener {
    private UUID mServiceUUID;
    private UUID mCharacterUUID;

    public BleReadRequest(UUID service, UUID character, BleGeneralResponse response) {
        super(response);
        this.mServiceUUID = service;
        this.mCharacterUUID = character;
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        switch (getCurrentStatus()) {
            case 0:
                onRequestCompleted(-1);
                break;
            case 2:
                startRead();
                break;
            case 19:
                startRead();
                break;
            default:
                onRequestCompleted(-1);
                break;
        }
    }

    private void startRead() {
        if (!readCharacteristic(this.mServiceUUID, this.mCharacterUUID)) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.ReadCharacterListener
    public void onCharacteristicRead(BluetoothGattCharacteristic characteristic, int status, byte[] value) {
        stopRequestTiming();
        if (status == 0) {
            putByteArray("extra.byte.value", value);
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
