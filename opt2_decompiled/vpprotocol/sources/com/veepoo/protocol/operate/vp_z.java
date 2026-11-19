package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IF2DebugListener;
import com.veepoo.protocol.model.enums.DebugCmd;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_z.class */
public class vp_z extends com.veepoo.protocol.vp_a {
    BluetoothClient vp_c;
    String vp_d;
    BleWriteResponse vp_e;
    private IF2DebugListener vp_f;

    private void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(DebugCmd debugCmd) {
        byte[] bArr = new byte[20];
        bArr[0] = -14;
        bArr[1] = (byte) debugCmd.cmd;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length < 20) {
            return;
        }
        boolean z = bArr[1] == 1;
        byte b = bArr[2];
        DebugCmd debugCmd = null;
        for (DebugCmd debugCmd2 : DebugCmd.values()) {
            if (debugCmd2.cmd == b) {
                debugCmd = debugCmd2;
                break;
            }
        }
        IF2DebugListener iF2DebugListener = this.vp_f;
        if (iF2DebugListener != null) {
            iF2DebugListener.onIF2DebugStatusChanged(z, debugCmd);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_f = (IF2DebugListener) iListener;
        handler(bArr);
    }

    public void vp_a(DebugCmd debugCmd, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        new StringBuilder().append("*** 发送设备调试质量：").append(debugCmd.toString());
        vp_c(bluetoothClient, str, bleWriteResponse, vp_a(debugCmd));
    }
}
