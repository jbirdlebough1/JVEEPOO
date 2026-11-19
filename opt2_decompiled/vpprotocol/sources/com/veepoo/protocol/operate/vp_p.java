package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ICameraDataListener;
import com.veepoo.protocol.model.enums.ECameraStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_p.class */
public class vp_p extends com.veepoo.protocol.vp_a {
    private ICameraDataListener vp_c;

    private ECameraStatus vp_b(byte[] bArr) {
        if (bArr.length < 3) {
            return ECameraStatus.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        boolean z = bArr[3] == 1;
        if (b2 == 1 && b == 0) {
            return ECameraStatus.OPEN_FALI;
        }
        if (b2 == 1 && b == 1) {
            return z ? ECameraStatus.DEVICE_OPEN_SUCCESS : ECameraStatus.OPEN_SUCCESS;
        }
        if (b2 == 2 && b == 0) {
            return ECameraStatus.TAKEPHOTO_CAN_NOT;
        }
        if (b2 == 2 && b == 1) {
            return ECameraStatus.TAKEPHOTO_CAN;
        }
        if (b2 == 0 && b == 0) {
            return ECameraStatus.CLOSE_FAIL;
        }
        return (b2 == 0 && b == 1) ? ECameraStatus.CLOSE_SUCCESS : ECameraStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ay(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ay(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bb, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bi(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bi(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bc, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ICameraDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.OnCameraDataChange(vp_b(bArr));
    }
}
