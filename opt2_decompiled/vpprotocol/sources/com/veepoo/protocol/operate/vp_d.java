package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBPFunctionListener;
import com.veepoo.protocol.model.datas.BpFunctionData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_d.class */
public class vp_d extends com.veepoo.protocol.vp_a {
    IBPFunctionListener vp_c;
    byte vp_d = 85;
    byte vp_e = 1;
    byte vp_f = 115;
    byte vp_g = 64;

    private boolean vp_b(byte[] bArr) {
        return bArr[2] == this.vp_d && bArr[3] == this.vp_e && bArr[4] == this.vp_f && bArr[5] == this.vp_g;
    }

    private BpFunctionData vp_c(byte[] bArr) {
        BpFunctionData bpFunctionData = new BpFunctionData();
        if (bArr == null || bArr.length < 20) {
            return bpFunctionData;
        }
        if (vp_b(bArr)) {
            byte b = bArr[6];
            boolean z = b != 0;
            boolean z2 = false;
            if (z) {
                z2 = b == 1;
            }
            bpFunctionData.setSupport(z);
            bpFunctionData.setOpen(z2);
        }
        return bpFunctionData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_r(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_r(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(true, false), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        this.vp_c = (IBPFunctionListener) iListener;
        this.vp_c.onDataChange(vp_c(bArr));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, z);
        super.send(vp_a(false, z), bluetoothClient, str, bleWriteResponse);
    }

    public byte[] vp_a(boolean z, boolean z2) {
        byte[] bArr = new byte[20];
        bArr[0] = -110;
        if (z) {
            bArr[1] = 3;
        } else {
            bArr[1] = z2 ? (byte) 1 : (byte) 2;
        }
        bArr[2] = this.vp_d;
        bArr[3] = this.vp_e;
        bArr[4] = this.vp_f;
        bArr[5] = this.vp_g;
        return bArr;
    }
}
