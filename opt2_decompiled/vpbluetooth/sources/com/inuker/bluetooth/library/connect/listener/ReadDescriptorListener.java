package com.inuker.bluetooth.library.connect.listener;

import android.bluetooth.BluetoothGattDescriptor;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/listener/ReadDescriptorListener.class */
public interface ReadDescriptorListener extends GattResponseListener {
    void onDescriptorRead(BluetoothGattDescriptor bluetoothGattDescriptor, int i, byte[] bArr);
}
