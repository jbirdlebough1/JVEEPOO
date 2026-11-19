package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGattDescriptor;
import com.inuker.bluetooth.library.connect.listener.ReadDescriptorListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleReadDescriptorRequest.class */
public class BleReadDescriptorRequest extends BleRequest implements ReadDescriptorListener {
    private UUID mServiceUUID;
    private UUID mCharacterUUID;
    private UUID mDescriptorUUID;

    public BleReadDescriptorRequest(UUID service, UUID character, UUID descriptor, BleGeneralResponse response) {
        super(response);
        this.mServiceUUID = service;
        this.mCharacterUUID = character;
        this.mDescriptorUUID = descriptor;
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
        if (!readDescriptor(this.mServiceUUID, this.mCharacterUUID, this.mDescriptorUUID)) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.ReadDescriptorListener
    public void onDescriptorRead(BluetoothGattDescriptor descriptor, int status, byte[] value) {
        stopRequestTiming();
        if (status == 0) {
            putByteArray("extra.byte.value", value);
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
