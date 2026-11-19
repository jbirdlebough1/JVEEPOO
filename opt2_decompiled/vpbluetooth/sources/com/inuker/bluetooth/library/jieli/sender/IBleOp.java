package com.inuker.bluetooth.library.jieli.sender;

import android.bluetooth.BluetoothGatt;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/sender/IBleOp.class */
public interface IBleOp {
    int getBleMtu();

    boolean writeDataByBle(BluetoothGatt bluetoothGatt, UUID uuid, UUID uuid2, byte[] bArr);
}
