package com.inuker.bluetooth.library.connect.response;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.inuker.bluetooth.library.connect.listener.IBluetoothGattResponse;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/response/BluetoothGattResponse.class */
public class BluetoothGattResponse extends BluetoothGattCallback {
    private IBluetoothGattResponse response;

    public BluetoothGattResponse(IBluetoothGattResponse response) {
        this.response = response;
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        this.response.onConnectionStateChange(status, newState);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        this.response.onServicesDiscovered(status);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        this.response.onCharacteristicRead(characteristic, status, characteristic.getValue());
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        this.response.onCharacteristicWrite(characteristic, status, characteristic.getValue());
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        this.response.onCharacteristicChanged(characteristic, characteristic.getValue());
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        this.response.onDescriptorWrite(descriptor, status);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        this.response.onDescriptorRead(descriptor, status, descriptor.getValue());
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
        this.response.onReadRemoteRssi(rssi, status);
    }

    @Override // android.bluetooth.BluetoothGattCallback
    public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
        this.response.onMtuChanged(mtu, status);
    }
}
