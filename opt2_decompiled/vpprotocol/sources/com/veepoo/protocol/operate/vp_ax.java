package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ax.class */
public class vp_ax extends com.veepoo.protocol.vp_a {
    @Override // com.veepoo.protocol.vp_a
    public void vp_bf(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bf(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bp, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bx(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bx(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bq, bluetoothClient, str, bleWriteResponse);
    }
}
