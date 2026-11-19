package com.veepoo.protocol.jl.classic.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import com.veepoo.protocol.jl.classic.vp_f;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/interfaces/listener/vp_b.class */
public interface vp_b {
    void onAdapterStatus(boolean z, boolean z2);

    void vp_a(BluetoothDevice bluetoothDevice, int i);

    void vp_a(BluetoothDevice bluetoothDevice, vp_f vp_fVar);
}
