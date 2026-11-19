package com.veepoo.protocol.jl.classic.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/interfaces/listener/vp_d.class */
public interface vp_d {
    void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, int i);

    void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr);

    void vp_a(BluetoothDevice bluetoothDevice);
}
