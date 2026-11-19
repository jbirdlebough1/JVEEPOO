package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ITemptureDetectDataListener;
import com.veepoo.protocol.model.datas.TemptureDetectData;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cy.class */
public class vp_cy extends com.veepoo.protocol.vp_a {
    public final String vp_c = vp_cy.class.getSimpleName();
    ITemptureDetectDataListener vp_d;

    private byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -121;
        bArr[1] = 1;
        bArr[2] = b;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_be(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_be(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((byte) 1), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bv(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bv(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((byte) 2), bluetoothClient, str, bleWriteResponse);
    }

    public TemptureDetectData vp_b(byte[] bArr) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        return new TemptureDetectData(iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((Integer.valueOf(strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[5], 16).intValue() * 1.0f) / 10.0f), 1), com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((Integer.valueOf(strArrByte2HexToStrArr[8] + strArrByte2HexToStrArr[7], 16).intValue() * 1.0f) / 10.0f), 1));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_d = (ITemptureDetectDataListener) iListener;
        TemptureDetectData temptureDetectDataVp_b = vp_b(bArr);
        ITemptureDetectDataListener iTemptureDetectDataListener = this.vp_d;
        if (iTemptureDetectDataListener != null) {
            iTemptureDetectDataListener.onDataChange(temptureDetectDataVp_b);
        }
    }
}
