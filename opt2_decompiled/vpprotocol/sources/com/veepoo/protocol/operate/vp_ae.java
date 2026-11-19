package com.veepoo.protocol.operate;

import com.veepoo.protocol.listener.data.IECGAutoReportListener;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ae.class */
public class vp_ae extends com.veepoo.protocol.vp_a {
    private IECGAutoReportListener vp_c;

    public void vp_a(IECGAutoReportListener iECGAutoReportListener) {
        this.vp_c = iECGAutoReportListener;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length >= 20 && this.vp_c != null) {
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            this.vp_c.onECGAutoReport(iArrByte2HexToIntArr[2], new TimeData(iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8]));
        }
    }

    public void vp_c(byte[] bArr) {
        IECGAutoReportListener iECGAutoReportListener;
        new StringBuilder().append("handlerADC!!!!!!!!!!!-> = ").append(com.veepoo.protocol.util.vp_g.vp_b(bArr));
        if (bArr[0] != -120 || (iECGAutoReportListener = this.vp_c) == null) {
            return;
        }
        iECGAutoReportListener.onECGDataReport(vp_b(bArr));
    }

    int[] vp_b(byte[] bArr) {
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            String str = strArrByte2HexToStrArr[i2 + 1] + strArrByte2HexToStrArr[i2 + 2] + strArrByte2HexToStrArr[i2 + 3];
            if (str.equals("020202")) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                int iIntValue = Integer.valueOf(str, 16).intValue();
                if (iIntValue == 16777215) {
                    iArr[i] = Integer.MAX_VALUE;
                } else {
                    iArr[i] = com.veepoo.protocol.util.vp_l.vp_a.vp_f(iIntValue);
                }
            }
        }
        return iArr;
    }
}
