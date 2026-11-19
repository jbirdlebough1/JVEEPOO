package com.inuker.bluetooth.library.connect.listener;

import android.bluetooth.BluetoothGattDescriptor;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/WriteDescriptorListener.class */
public interface WriteDescriptorListener extends GattResponseListener {
    void onDescriptorWrite(BluetoothGattDescriptor bluetoothGattDescriptor, int i);
}
