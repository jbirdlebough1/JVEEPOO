package com.inuker.bluetooth.library.connect.listener;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/IBluetoothGattResponse.class */
public interface IBluetoothGattResponse {
    void onConnectionStateChange(int i, int i2);

    void onServicesDiscovered(int i);

    void onCharacteristicRead(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);

    void onCharacteristicWrite(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, byte[] bArr);

    void onCharacteristicChanged(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    void onDescriptorRead(BluetoothGattDescriptor bluetoothGattDescriptor, int i, byte[] bArr);

    void onDescriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i);

    void onReadRemoteRssi(int i, int i2);

    void onMtuChanged(int i, int i2);
}
