package com.inuker.bluetooth.library.jieli.sender;

import android.bluetooth.BluetoothDevice;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/OnWriteDataCallback.class */
public interface OnWriteDataCallback {
    void onBleResult(BluetoothDevice bluetoothDevice, UUID uuid, UUID uuid2, boolean z, byte[] bArr);
}
