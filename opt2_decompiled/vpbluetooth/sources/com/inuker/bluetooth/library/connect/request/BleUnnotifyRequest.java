package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothGattDescriptor;
import com.inuker.bluetooth.library.connect.listener.WriteDescriptorListener;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleUnnotifyRequest.class */
public class BleUnnotifyRequest extends BleRequest implements WriteDescriptorListener {
    private UUID mServiceUUID;
    private UUID mCharacterUUID;

    public BleUnnotifyRequest(UUID service, UUID character, BleGeneralResponse response) {
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
                closeNotify();
                break;
            case 19:
                closeNotify();
                break;
            default:
                onRequestCompleted(-1);
                break;
        }
    }

    private void closeNotify() {
        if (!setCharacteristicNotification(this.mServiceUUID, this.mCharacterUUID, false)) {
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
