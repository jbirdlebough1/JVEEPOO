package com.veepoo.protocol.jl.classic.interfaces.listener;

import android.bluetooth.BluetoothDevice;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import com.veepoo.protocol.jl.classic.vp_f;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/interfaces/listener/vp_c.class */
public interface vp_c {
    void vp_a(boolean z, boolean z2);

    void vp_a(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage);

    void vp_a(vp_f vp_fVar);
}
