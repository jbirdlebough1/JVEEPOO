package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IHeartDataListener;
import com.veepoo.protocol.model.datas.HeartData;
import com.veepoo.protocol.model.enums.EHeartStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bd.class */
public class vp_bd extends com.veepoo.protocol.vp_a {
    IHeartDataListener vp_c;

    private HeartData vp_b(byte[] bArr) {
        HeartData heartData = new HeartData();
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        heartData.setHeartStatus(EHeartStatus.STATE_INIT);
        int i = 0;
        int i2 = 0;
        if (iArrByte2HexToIntArr.length >= 2) {
            i2 = iArrByte2HexToIntArr[1];
        }
        heartData.setData(i2);
        if (iArrByte2HexToIntArr.length >= 6) {
            i = iArrByte2HexToIntArr[5];
        }
        if (i != 0 && i != 2 && i != 3) {
            heartData.setHeartStatus(EHeartStatus.STATE_HEART_BUSY);
        } else if (i2 == 1 || i2 == 2) {
            heartData.setHeartStatus(EHeartStatus.STATE_HEART_WEAR_ERROR);
        } else if (30 <= i2 && i2 <= 210) {
            heartData.setHeartStatus(EHeartStatus.STATE_HEART_NORMAL);
        }
        return heartData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bc(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bc(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -48;
        bArr[1] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bp(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bp(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_al, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IHeartDataListener) iListener;
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isDetectingHeart()) {
            handler(bArr);
        }
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onDataChange(vp_b(bArr));
    }
}
