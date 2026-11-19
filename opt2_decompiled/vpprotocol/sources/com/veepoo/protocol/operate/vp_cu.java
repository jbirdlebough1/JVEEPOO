package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.model.datas.SportData;
import com.veepoo.protocol.util.SportUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cu.class */
public class vp_cu extends com.veepoo.protocol.vp_a {
    private ISportDataListener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_ar(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ar(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ao, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onSportDataChange(vp_b(bArr));
    }

    public int vp_c(String[] strArr) {
        return vp_a(strArr[5] + strArr[4] + strArr[3] + strArr[2]);
    }

    public double vp_a(String[] strArr) {
        return SportUtil.getPositionDouble(((double) vp_a(strArr[9] + strArr[8] + strArr[7] + strArr[6])) / 1000.0d, 3);
    }

    public double vp_b(String[] strArr) {
        return SportUtil.getPositionDouble(((double) vp_a(strArr[13] + strArr[12] + strArr[11] + strArr[10])) / 10.0d, 1);
    }

    private SportData vp_b(byte[] bArr) {
        SportData sportData = new SportData();
        if (bArr.length < 20) {
            return sportData;
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iVp_c = vp_c(strArrByte2HexToStrArr);
        double dVp_a = vp_a(strArrByte2HexToStrArr);
        double dVp_b = vp_b(strArrByte2HexToStrArr);
        sportData.setStep(iVp_c);
        sportData.setDis(dVp_a);
        sportData.setKcal(dVp_b);
        sportData.setCalcType(2);
        return sportData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ISportDataListener) iListener;
        handler(bArr);
    }

    private int vp_a(String str) {
        if (str.equals("FFFFFFFF")) {
            str = "0";
        }
        return Integer.valueOf(str, 16).intValue();
    }
}
