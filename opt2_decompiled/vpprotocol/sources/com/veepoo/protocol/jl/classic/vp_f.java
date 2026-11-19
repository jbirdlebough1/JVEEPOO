package com.veepoo.protocol.jl.classic;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_f.class */
public class vp_f {
    private int vp_a;
    private String vp_b;
    private BluetoothDevice vp_c;

    public vp_f(int i, String str) {
        vp_a(i);
        vp_a(str);
    }

    public void vp_a(int i) {
        this.vp_a = i;
    }

    public String toString() {
        return "ErrorInfo{code=" + this.vp_a + ", message='" + this.vp_b + "', device=" + this.vp_c + '}';
    }

    public void vp_a(BluetoothDevice bluetoothDevice) {
        this.vp_c = bluetoothDevice;
    }

    public void vp_a(String str) {
        this.vp_b = str;
    }
}
