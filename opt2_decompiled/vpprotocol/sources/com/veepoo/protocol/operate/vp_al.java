package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IECGSwitchListener;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_al.class */
public class vp_al extends com.veepoo.protocol.vp_a {
    BluetoothClient vp_c;
    String vp_d;
    BleWriteResponse vp_e;
    private IECGSwitchListener vp_f;

    private byte[] vp_c() {
        return vp_a((byte) 3);
    }

    private byte[] vp_b() {
        return vp_a((byte) 1);
    }

    private byte[] vp_a() {
        return vp_a((byte) 2);
    }

    public void vp_cf(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        vp_c(bluetoothClient, str, bleWriteResponse, vp_c());
    }

    public void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        vp_c(bluetoothClient, str, bleWriteResponse, vp_b());
    }

    public void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        vp_c(bluetoothClient, str, bleWriteResponse, vp_a());
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length >= 20 && bArr[3] == 85 && bArr[4] == 1 && bArr[5] == 115 && bArr[6] == 64 && this.vp_f != null) {
            int i = VpBleByteUtil.byte2HexToIntArr(bArr)[2];
            if (i == 0) {
                this.vp_f.onECGSwitchStatusChanged(EFunctionStatus.UNSUPPORT);
            } else if (i == 1) {
                this.vp_f.onECGSwitchStatusChanged(EFunctionStatus.SUPPORT_OPEN);
            } else if (i == 2) {
                this.vp_f.onECGSwitchStatusChanged(EFunctionStatus.SUPPORT_CLOSE);
            }
        }
    }

    private void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -108;
        bArr[1] = 5;
        bArr[2] = b;
        bArr[3] = 85;
        bArr[4] = 1;
        bArr[5] = 115;
        bArr[6] = 64;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_f = (IECGSwitchListener) iListener;
        handler(bArr);
    }
}
