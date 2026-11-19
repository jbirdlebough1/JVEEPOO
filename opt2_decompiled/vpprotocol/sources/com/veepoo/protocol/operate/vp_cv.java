package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISportDataListener;
import com.veepoo.protocol.model.datas.SportData;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.SportUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cv.class */
public class vp_cv extends com.veepoo.protocol.vp_a {
    private ISportDataListener vp_c;

    /* JADX WARN: Type inference failed for: r0v6, types: [boolean, com.veepoo.protocol.model.datas.SportData] */
    private SportData vp_b(byte[] bArr) {
        SportData sportData = new SportData();
        int iVp_c = 0;
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        if (strArrByte2HexToStrArr.length > 6 && strArrByte2HexToStrArr[5].equals("00")) {
            iVp_c = vp_c(bArr);
        }
        int personHeight = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getPersonHeight();
        ?? IsSupportCalcStepNew = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportCalcStepNew();
        int i = iVp_c;
        double d = personHeight;
        double kcal = SportUtil.getKcal(i, d, 3, IsSupportCalcStepNew);
        double distance3 = SportUtil.getDistance3(i, d);
        sportData.setStep(i);
        sportData.setKcal(kcal);
        IsSupportCalcStepNew.setDis(distance3);
        if (IsSupportCalcStepNew != 0) {
            sportData.setCalcType(1);
        } else {
            sportData.setCalcType(0);
        }
        short s = (short) (bArr[6] | (bArr[11] << 8));
        short s2 = (short) (bArr[7] | (bArr[12] << 8));
        short s3 = (short) (bArr[8] | (bArr[13] << 8));
        sportData.setTriaxialX(s);
        sportData.setTriaxialY(s2);
        sportData.setTriaxialZ(s3);
        return sportData;
    }

    private int vp_c(byte[] bArr) {
        if (bArr.length <= 4) {
            return 0;
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        String str = strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[3] + strArrByte2HexToStrArr[4];
        String str2 = str;
        if (str.equals("FFFFFFFF")) {
            str2 = "0";
        }
        return Integer.valueOf(str2, 16).intValue();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_aq(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_aq(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_am, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ISportDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onSportDataChange(vp_b(bArr));
    }
}
