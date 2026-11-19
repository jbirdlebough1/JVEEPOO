package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGattDescriptor;
import com.inuker.bluetooth.library.connect.listener.WriteDescriptorListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleWriteDescriptorRequest.class */
public class BleWriteDescriptorRequest extends BleRequest implements WriteDescriptorListener {
    private UUID mServiceUUID;
    private UUID mCharacterUUID;
    private UUID mDescriptorUUID;
    private byte[] mBytes;

    public BleWriteDescriptorRequest(UUID service, UUID character, UUID descriptor, byte[] bytes, BleGeneralResponse response) {
        super(response);
        this.mServiceUUID = service;
        this.mCharacterUUID = character;
        this.mDescriptorUUID = descriptor;
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
        if (!writeDescriptor(this.mServiceUUID, this.mCharacterUUID, this.mDescriptorUUID, this.mBytes)) {
            onRequestCompleted(-1);
        } else {
            startRequestTiming();
        }
    }

    @Override // com.inuker.bluetooth.library.connect.listener.WriteDescriptorListener
    public void onDescriptorWrite(BluetoothGattDescriptor descriptor, int status) {
        stopRequestTiming();
        if (status == 0) {
            onRequestCompleted(0);
        } else {
            onRequestCompleted(-1);
        }
    }
}
