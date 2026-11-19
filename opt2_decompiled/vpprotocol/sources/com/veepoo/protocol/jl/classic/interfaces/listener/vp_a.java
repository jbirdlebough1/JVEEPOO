package com.veepoo.protocol.jl.classic.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.ParcelUuid;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/interfaces/listener/vp_a.class */
public interface vp_a {
    void vp_a(boolean z, int i, BluetoothProfile bluetoothProfile);

    void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr);

    void onA2dpStatus(BluetoothDevice bluetoothDevice, int i);

    void onHfpStatus(BluetoothDevice bluetoothDevice, int i);

    void vp_a(BluetoothDevice bluetoothDevice, int i);
}
