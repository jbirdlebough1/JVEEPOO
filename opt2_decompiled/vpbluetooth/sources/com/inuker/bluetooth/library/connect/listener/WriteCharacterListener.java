package com.inuker.bluetooth.library.connect.listener;

import android.bluetooth.BluetoothGattCharacteristic;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/WriteCharacterListener.class */
public interface WriteCharacterListener extends GattResponseListener {
    void onCharacteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);
}
