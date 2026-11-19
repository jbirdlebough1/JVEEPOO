package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.log.VPLocalLogger;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.TemptureData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.ecg.FiveMinuteData;
import com.veepoo.protocol.model.datas.ecg.HRVFiveMinuteData;
import com.veepoo.protocol.model.datas.ecg.Spo2hMinuteData;
import com.veepoo.protocol.model.datas.ecg.SportFiveMinuteData;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ar.class */
public class vp_ar {
    public static boolean vp_a = false;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ar$vp_a.class */
    static class vp_a {
        vp_b vp_a;
        byte[] vp_b;

        vp_a() {
        }

        public vp_b vp_b() {
            return this.vp_a;
        }

        public byte[] vp_a() {
            return this.vp_b;
        }

        public void vp_a(byte[] bArr) {
            this.vp_b = bArr;
        }

        public void vp_a(vp_b vp_bVar) {
            this.vp_a = vp_bVar;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ar$vp_b.class */
    static class vp_b {
        int vp_a;
        int vp_b;
        int vp_c;
        int vp_d;
        int vp_e;
        int vp_f;
        int vp_g;
        int vp_h;
        int vp_i;
        int vp_j;

        public vp_b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.vp_a = i;
            this.vp_b = i2;
            this.vp_c = i3;
            this.vp_d = i4;
            this.vp_e = i5;
            this.vp_f = i6;
            this.vp_g = i7;
            this.vp_i = i9;
            this.vp_h = i8;
            this.vp_j = i10;
        }

        public int vp_a() {
            return this.vp_h;
        }

        public int vp_b() {
            return this.vp_i;
        }

        public String toString() {
            return "DFHead{当前块=" + this.vp_a + ", 总块=" + this.vp_b + ", 当前包=" + this.vp_c + ", 总包=" + this.vp_d + ", 读取日期=" + this.vp_e + ", 读取类型=" + this.vp_f + ", 数据类型=" + this.vp_g + ", 有效字节=" + this.vp_h + ", 检验参数=" + this.vp_i + ", 单包长度=" + this.vp_j + '}';
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ar$vp_c.class */
    static class vp_c {
        public byte[] vp_a = null;
        public byte[] vp_b = null;
        public byte[] vp_c = null;
        public byte[] vp_d = null;
        public byte[] vp_e = null;
        public byte[] vp_f = null;
        public byte[] vp_g = null;
        public byte[] vp_h = null;
        public byte[] vp_i = null;
        public byte[] vp_j = null;
        public byte[] vp_k = null;
        public byte[] vp_l = null;
        public byte[] vp_m = null;
        public byte[] vp_n = null;
        public byte[] vp_o = null;
        public byte[] vp_p = null;
        public byte[] vp_q = null;
        public byte[] vp_r = null;
        public byte[] vp_s = null;

        vp_c() {
        }

        public byte[] vp_d() {
            return this.vp_h;
        }

        public byte[] vp_n() {
            return this.vp_j;
        }

        public byte[] vp_p() {
            return this.vp_i;
        }

        public byte[] vp_f() {
            return this.vp_a;
        }

        public byte[] vp_r() {
            return this.vp_b;
        }

        public byte[] vp_m() {
            return this.vp_c;
        }

        public byte[] vp_k() {
            return this.vp_d;
        }

        public byte[] vp_h() {
            return this.vp_e;
        }

        public byte[] vp_e() {
            return this.vp_f;
        }

        public byte[] vp_g() {
            return this.vp_g;
        }

        public void vp_s(byte[] bArr) {
            this.vp_b = bArr;
        }

        public byte[] vp_o() {
            return this.vp_k;
        }

        public byte[] vp_l() {
            return this.vp_l;
        }

        public byte[] vp_b() {
            return this.vp_n;
        }

        public byte[] vp_i() {
            return this.vp_o;
        }

        public byte[] vp_j() {
            return this.vp_p;
        }

        public byte[] vp_a() {
            return this.vp_q;
        }

        public byte[] vp_c() {
            return this.vp_r;
        }

        public byte[] vp_q() {
            return this.vp_s;
        }

        public void vp_d(byte[] bArr) {
            this.vp_h = bArr;
        }

        public void vp_q(byte[] bArr) {
            this.vp_i = bArr;
        }

        public void vp_o(byte[] bArr) {
            this.vp_j = bArr;
        }

        public void vp_f(byte[] bArr) {
            this.vp_a = bArr;
        }

        public void vp_n(byte[] bArr) {
            this.vp_c = bArr;
        }

        public void vp_l(byte[] bArr) {
            this.vp_d = bArr;
        }

        public void vp_h(byte[] bArr) {
            this.vp_e = bArr;
        }

        public void vp_e(byte[] bArr) {
            this.vp_f = bArr;
        }

        public void vp_i(byte[] bArr) {
            this.vp_g = bArr;
        }

        public void vp_p(byte[] bArr) {
            this.vp_k = bArr;
        }

        public void vp_m(byte[] bArr) {
            this.vp_l = bArr;
        }

        public void vp_g(byte[] bArr) {
            this.vp_m = bArr;
        }

        public void vp_b(byte[] bArr) {
            this.vp_n = bArr;
        }

        public void vp_j(byte[] bArr) {
            this.vp_o = bArr;
        }

        public void vp_k(byte[] bArr) {
            this.vp_p = bArr;
        }

        public void vp_a(byte[] bArr) {
            this.vp_q = bArr;
        }

        public void vp_c(byte[] bArr) {
            this.vp_r = bArr;
        }

        public void vp_r(byte[] bArr) {
            this.vp_s = bArr;
        }
    }

    public static byte[] vp_a(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return new byte[0];
        }
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private FiveMinuteData vp_b(byte[] bArr, int i) {
        new StringBuilder().append("getFiveMinuteBean bytes: ").append(com.veepoo.protocol.util.vp_g.vp_b(bArr));
        new FiveMinuteData();
        vp_c vp_cVarVp_e = vp_e(bArr);
        TimeData timeDataVp_c = vp_c(vp_cVarVp_e.vp_f(), i);
        if (timeDataVp_c == null || timeDataVp_c.isLeadTime()) {
            if (timeDataVp_c != null) {
                VPLocalLogger.bleChangeLog("超前时间数据，不解析：" + timeDataVp_c.getDateAndClockAndSecondForDb());
                return null;
            }
            VPLocalLogger.bleChangeLog("时间为空,异常数据");
            return null;
        }
        SportFiveMinuteData sportFiveMinuteDataVp_h = vp_h(vp_cVarVp_e.vp_r());
        byte[] bArrVp_a = vp_cVarVp_e.vp_a();
        byte[] bArrVp_j = vp_cVarVp_e.vp_j();
        byte[] bArrVp_i = vp_cVarVp_e.vp_i();
        byte[] bArrVp_c = vp_cVarVp_e.vp_c();
        BloodComponent bloodComponentVp_c = null;
        TemptureData temptureDataVp_d = null;
        if (bArrVp_a != null && vp_a(bArrVp_a)) {
            bloodComponentVp_c = vp_c(vp_cVarVp_e.vp_a());
        }
        int i2 = bArrVp_j != null ? bArrVp_j[0] & 255 : 0;
        float f = bArrVp_i != null ? (bArrVp_i[0] & 255) / 10.0f : 0.0f;
        if (bArrVp_c != null && vp_a(bArrVp_c)) {
            temptureDataVp_d = vp_d(bArrVp_c);
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(vp_cVarVp_e.vp_m());
        int[] iArrByte2HexToIntArr2 = VpBleByteUtil.byte2HexToIntArr(vp_cVarVp_e.vp_k());
        int[] iArrByte2HexToIntArr3 = VpBleByteUtil.byte2HexToIntArr(vp_cVarVp_e.vp_h());
        int[] iArrByte2HexToIntArr4 = VpBleByteUtil.byte2HexToIntArr(vp_cVarVp_e.vp_e());
        int[] iArrByte2HexToIntArr5 = VpBleByteUtil.byte2HexToIntArr(vp_cVarVp_e.vp_d());
        byte[] bArrVp_n = vp_cVarVp_e.vp_n();
        int[] iArrByte2HexToIntArr6 = bArrVp_n != null ? VpBleByteUtil.byte2HexToIntArr(bArrVp_n) : null;
        Spo2hMinuteData spo2hMinuteDataVp_g = vp_g(vp_cVarVp_e.vp_p());
        HRVFiveMinuteData hRVFiveMinuteDataVp_f = vp_f(vp_cVarVp_e.vp_g());
        boolean z = vp_a ? false : VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getBloodGlucoseTag() == 5;
        byte[] bArrVp_b = vp_cVarVp_e.vp_b();
        int iVp_a = -1;
        int iVp_a2 = 0;
        if (bArrVp_b != null) {
            boolean z2 = z;
            iVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArrVp_b[1], bArrVp_b[0]);
            if (z2) {
                iVp_a2 = (iVp_a & 57344) >> 13;
                iVp_a &= 8191;
            } else {
                iVp_a2 = vp_a(iVp_a);
            }
        }
        int[] iArr = null;
        byte[] bArrVp_o = vp_cVarVp_e.vp_o();
        int[] iArrByte2HexToIntArr7 = bArrVp_o != null ? VpBleByteUtil.byte2HexToIntArr(bArrVp_o) : null;
        byte[] bArrVp_l = vp_cVarVp_e.vp_l();
        if (bArrVp_l != null) {
            iArr = new int[bArrVp_l.length];
            for (int i3 = 0; i3 < bArrVp_l.length; i3++) {
                iArr[i3] = bArrVp_l[i3] & 255;
            }
        }
        int[] iArrByte2HexToIntArr8 = new int[6];
        byte[] bArrVp_q = vp_cVarVp_e.vp_q();
        if (bArrVp_q != null) {
            iArrByte2HexToIntArr8 = VpBleByteUtil.byte2HexToIntArr(bArrVp_q);
        }
        int[] iArr2 = iArrByte2HexToIntArr6;
        FiveMinuteData fiveMinuteData = new FiveMinuteData(timeDataVp_c, sportFiveMinuteDataVp_h, iArrByte2HexToIntArr, iArrByte2HexToIntArr2, iArrByte2HexToIntArr3, iArrByte2HexToIntArr4, iArrByte2HexToIntArr5, iVp_a, hRVFiveMinuteDataVp_f, spo2hMinuteDataVp_g);
        fiveMinuteData.setBloodGlucoseRiskLevel(iVp_a2);
        fiveMinuteData.setResetTagContent(iArr);
        fiveMinuteData.setPressure(i2);
        fiveMinuteData.setMet(f);
        fiveMinuteData.initSportStatus(iArrByte2HexToIntArr8);
        if (iArr2 != null) {
            fiveMinuteData.setSleepSport(iArrByte2HexToIntArr6);
        }
        if (iArrByte2HexToIntArr7 != null) {
            fiveMinuteData.setSleepStatus(iArrByte2HexToIntArr7);
        }
        if (bloodComponentVp_c != null) {
            fiveMinuteData.setBloodComposition(bloodComponentVp_c);
        }
        if (temptureDataVp_d != null) {
            fiveMinuteData.setBaseTemperature(temptureDataVp_d.getBaseTempture());
            fiveMinuteData.setTemperature(temptureDataVp_d.getTempture());
        }
        return fiveMinuteData;
    }

    private Spo2hMinuteData vp_g(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int length = iArrByte2HexToIntArr.length;
        int[] iArr = new int[5];
        if (length >= 5) {
            System.arraycopy(iArrByte2HexToIntArr, 0, iArr, 0, 5);
        }
        int[] iArr2 = new int[5];
        if (length >= 10) {
            System.arraycopy(iArrByte2HexToIntArr, 5, iArr2, 0, 5);
        }
        int[] iArr3 = new int[5];
        if (length >= 15) {
            System.arraycopy(iArrByte2HexToIntArr, 10, iArr3, 0, 5);
        }
        int[] iArr4 = new int[5];
        if (length >= 20) {
            System.arraycopy(iArrByte2HexToIntArr, 15, iArr4, 0, 5);
        }
        int[] iArr5 = new int[5];
        if (length >= 25) {
            System.arraycopy(iArrByte2HexToIntArr, 20, iArr5, 0, 5);
        }
        int[] iArr6 = new int[5];
        if (length >= 30) {
            System.arraycopy(iArrByte2HexToIntArr, 25, iArr6, 0, 5);
        }
        return new Spo2hMinuteData(iArr, iArr2, iArr3, iArr4, iArr5, iArr6);
    }

    private HRVFiveMinuteData vp_f(byte[] bArr) {
        new HRVFiveMinuteData();
        if (bArr == null || bArr.length < 51) {
            vp_a("解析5分钟的HRV", bArr, 51);
            return null;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[0];
        int[] iArr = new int[50];
        System.arraycopy(iArrByte2HexToIntArr, 1, iArr, 0, 50);
        int[] iArr2 = new int[5];
        for (int i2 = 0; i2 < 5; i2++) {
            iArr2[i2] = (i >> (7 - i2)) & 1;
        }
        int[] iArr3 = new int[5];
        for (int i3 = 0; i3 < 5; i3++) {
            iArr3[i3] = vp_a(i3, iArr);
        }
        return new HRVFiveMinuteData(i, iArr2, iArr, iArr3);
    }

    private SportFiveMinuteData vp_h(byte[] bArr) {
        SportFiveMinuteData sportFiveMinuteData = new SportFiveMinuteData();
        if (bArr == null || bArr.length < 10) {
            vp_a("解析5分钟数据-运动", bArr, 4);
            return sportFiveMinuteData;
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        return new SportFiveMinuteData(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[0] + strArrByte2HexToStrArr[1]), VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[3]), VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[5]), VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[7]), iArrByte2HexToIntArr[8], iArrByte2HexToIntArr[9]);
    }

    private BloodComponent vp_c(byte[] bArr) {
        BloodComponent bloodComponent = new BloodComponent();
        if (bArr == null || bArr.length < 10) {
            vp_a("解析5分钟数据-血液", bArr, 4);
            return bloodComponent;
        }
        bloodComponent.setTCHO(com.veepoo.protocol.util.vp_g.vp_a(bArr[1], bArr[0]) / 100.0f);
        bloodComponent.setTAG(com.veepoo.protocol.util.vp_g.vp_a(bArr[3], bArr[2]) / 100.0f);
        bloodComponent.setHDL(com.veepoo.protocol.util.vp_g.vp_a(bArr[5], bArr[4]) / 100.0f);
        bloodComponent.setLDL(com.veepoo.protocol.util.vp_g.vp_a(bArr[7], bArr[6]) / 100.0f);
        bloodComponent.setUricAcid(com.veepoo.protocol.util.vp_g.vp_a(bArr[9], bArr[8]) / 10.0f);
        return bloodComponent;
    }

    private TemptureData vp_d(byte[] bArr) {
        TemptureData temptureData = new TemptureData();
        if (bArr == null || bArr.length < 4) {
            vp_a("解析5分钟数据-Body Temperature", bArr, 4);
            return temptureData;
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[0], 16).intValue();
        int i = iIntValue;
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[3] + strArrByte2HexToStrArr[2], 16).intValue();
        if (iIntValue < 120 || i > 480 || iIntValue2 < 120 || iIntValue2 > 480) {
            i = 0;
            iIntValue2 = 0;
        }
        float fVp_b = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iIntValue2 * 1.0f) / 10.0f), 1);
        temptureData.setBaseTempture(com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((i * 1.0f) / 10.0f), 1));
        temptureData.setTempture(fVp_b);
        return temptureData;
    }

    private vp_c vp_e(byte[] bArr) {
        vp_c vp_cVar = new vp_c();
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        boolean z = true;
        while (byteBufferOrder.position() < length && z) {
            try {
                z = true;
                switch (byteBufferOrder.get()) {
                    case -79:
                        byte[] bArrVp_a = vp_a(byteBufferOrder, "0xB1", bArr);
                        if (bArrVp_a == null || bArrVp_a.length != 4 || (bArrVp_a[0] == 0 && bArrVp_a[1] == 0)) {
                            return vp_cVar;
                        }
                        vp_cVar.vp_f(bArrVp_a);
                        break;
                    case -78:
                        vp_cVar.vp_s(vp_a(byteBufferOrder, "0xB2", bArr));
                        break;
                    case -77:
                        vp_cVar.vp_n(vp_a(byteBufferOrder, "0xB3", bArr));
                        break;
                    case -76:
                        vp_cVar.vp_l(vp_a(byteBufferOrder, "0xB4", bArr));
                        break;
                    case -75:
                        vp_cVar.vp_h(vp_a(byteBufferOrder, "0xB5", bArr));
                        break;
                    case -74:
                        vp_cVar.vp_e(vp_a(byteBufferOrder, "0xB6", bArr));
                        break;
                    case -73:
                        vp_cVar.vp_i(vp_a(byteBufferOrder, "0xB7", bArr));
                        break;
                    case -72:
                        vp_cVar.vp_d(vp_a(byteBufferOrder, "0xB8", bArr));
                        break;
                    case -71:
                        vp_cVar.vp_q(vp_a(byteBufferOrder, "0xB9", bArr));
                        break;
                    case -70:
                        vp_cVar.vp_o(vp_a(byteBufferOrder, "0xBA", bArr));
                        break;
                    case -69:
                        vp_cVar.vp_p(vp_a(byteBufferOrder, "0xBB", bArr));
                        break;
                    case -68:
                        vp_cVar.vp_m(vp_a(byteBufferOrder, "0xBC", bArr));
                        break;
                    case -67:
                        vp_cVar.vp_g(vp_a(byteBufferOrder, "0xBD", bArr));
                        break;
                    case -66:
                        vp_cVar.vp_b(vp_a(byteBufferOrder, "0xBE", bArr));
                        break;
                    case -65:
                        vp_cVar.vp_j(vp_a(byteBufferOrder, "0xBF", bArr));
                        break;
                    case -64:
                    default:
                        z = false;
                        break;
                    case -63:
                        vp_cVar.vp_k(vp_a(byteBufferOrder, "0xC1", bArr));
                        break;
                    case -62:
                        byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xC2", bArr);
                        if (!vp_a(bArrVp_a2)) {
                            bArrVp_a2 = null;
                        }
                        vp_cVar.vp_a(bArrVp_a2);
                        break;
                    case -61:
                        byte[] bArrVp_a3 = vp_a(byteBufferOrder, "0xC3", bArr);
                        if (!vp_a(bArrVp_a3)) {
                            bArrVp_a3 = null;
                        }
                        vp_cVar.vp_c(bArrVp_a3);
                        break;
                    case -60:
                        vp_a(byteBufferOrder, "0xC4", bArr);
                        break;
                    case -59:
                        vp_a(byteBufferOrder, "0xC5", bArr);
                        break;
                    case -58:
                        vp_cVar.vp_r(vp_a(byteBufferOrder, "0xC6", bArr));
                        break;
                }
            } catch (Exception unused) {
                vp_cVar.vp_a = null;
            }
        }
        return vp_cVar;
    }

    private boolean vp_b(byte[] bArr) {
        return bArr != null && bArr.length >= 20 && bArr[3] == 0;
    }

    private TimeData vp_c(byte[] bArr, int i) {
        if (bArr == null || bArr.length < 4) {
            vp_a("解析5分钟数据时间", bArr, 4);
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        int i4 = calendar.get(5);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        TimeData timeData = timeData;
        TimeData timeData2 = new TimeData(i2, i3, i4, iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3]);
        if (!com.veepoo.protocol.util.vp_j.vp_b(timeData2)) {
            timeData = null;
        }
        return timeData;
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) throws Exception {
        int i = byteBuffer.get() & 255;
        int iPosition = byteBuffer.position();
        int i2 = iPosition + i;
        if (i2 > bArr.length) {
            throw new Exception();
        }
        byteBuffer.position(i2);
        return vp_a(bArr, i, iPosition);
    }

    private List vp_a(List list) {
        byte b = 0;
        byte b2 = 0;
        byte[] bArrVp_a = null;
        vp_b vp_bVar = null;
        int i = -1;
        int i2 = -1;
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            byte[] bArr = (byte[]) list.get(i3);
            if (bArr != null && bArr.length >= 5) {
                byte b3 = bArr[1];
                boolean z = b3 == -1 && bArr[2] == -1;
                if (z || b3 != b2 || bArr[2] != b) {
                    vp_b vp_bVar2 = null;
                    if (!z) {
                        if (vp_b(bArr)) {
                            vp_b vp_bVarVp_a = vp_a(bArr, i);
                            vp_bVar2 = vp_bVarVp_a;
                            i = vp_bVarVp_a.vp_j;
                        } else {
                            new StringBuilder().append("DF 丢弃非法包(非首包非结束包)：").append(com.veepoo.protocol.util.vp_g.vp_b(bArr));
                        }
                    }
                    if (bArrVp_a != null) {
                        vp_a vp_aVar = new vp_a();
                        vp_aVar.vp_a(vp_bVar);
                        vp_aVar.vp_a(bArrVp_a);
                        if (vp_a(vp_aVar)) {
                            arrayList.add(vp_aVar);
                        }
                    }
                    b2 = bArr[1];
                    i2 = -1;
                    vp_bVar = vp_bVar2;
                    bArrVp_a = null;
                    b = bArr[2];
                } else if (vp_bVar == null) {
                    new StringBuilder().append("DF 丢弃孤儿子包(首包丢失或乱序)：").append(com.veepoo.protocol.util.vp_g.vp_b(bArr));
                } else {
                    int i4 = vp_bVar.vp_j - 4;
                    if (i4 <= 0 || i4 > bArr.length - 4) {
                        new StringBuilder().append("拼接五分钟数据长度出错：data[13]:").append(vp_bVar.vp_j).append(" 计算长度:").append(i4).append(" 实际长度 (data.length-4):").append(bArr.length - 4);
                        i2 = -1;
                        vp_bVar = null;
                        bArrVp_a = null;
                    } else if (i2 == -1 || (bArr[3] & 255) != i2) {
                        int i5 = bArr[3] & 255;
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, 4, bArr2, 0, i4);
                        i2 = i5;
                        bArrVp_a = vp_a(bArrVp_a, bArr2);
                    } else {
                        new StringBuilder().append("DF 忽略同一子包的重传：").append(com.veepoo.protocol.util.vp_g.vp_b(bArr));
                    }
                }
            }
        }
        return arrayList;
    }

    public int vp_a(int i) {
        int i2 = 0;
        if (i >= 300 && i <= 700) {
            i2 = 1;
        }
        if (i > 700 && i <= 1100) {
            i2 = 2;
        }
        if (i > 1100 && i <= 1500) {
            i2 = 3;
        }
        return i2;
    }

    private vp_b vp_a(byte[] bArr, int i) {
        int i2;
        int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
        int iVp_c = com.veepoo.protocol.util.vp_g.vp_c(bArr[2], bArr[1]);
        int iVp_c2 = com.veepoo.protocol.util.vp_g.vp_c(bArr[5], bArr[4]);
        int iVp_c3 = com.veepoo.protocol.util.vp_g.vp_c(bArr[12], bArr[11]);
        int i3 = iArrVp_e[3];
        int i4 = iArrVp_e[6];
        int i5 = iArrVp_e[7];
        int i6 = iArrVp_e[8];
        int i7 = iArrVp_e[9];
        int i8 = iArrVp_e[10] & 255;
        if (vp_a || VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getOriginProtocolVersion() == 5) {
            int i9 = iArrVp_e[13];
            i2 = i9;
            if (i9 < 4) {
                new StringBuilder().append("DF 头包包长非法 data[13]=").append(i2).append("，回退为 ").append(i);
                i2 = i >= 4 ? i : 20;
            }
        } else {
            i2 = 20;
        }
        if (i2 < 4) {
            i2 = 20;
        }
        return new vp_b(iVp_c, iVp_c2, i3, i4, i5, i6, i7, i8, iVp_c3, i2);
    }

    private int vp_a(int i, int[] iArr) {
        int iAbs = 0;
        int i2 = 0;
        int i3 = -1;
        for (int i4 = i * 10; i4 < (i + 1) * 10; i4++) {
            int i5 = iArr[i4];
            if (i5 >= 30 && i5 <= 210) {
                if (i3 != -1) {
                    iAbs += Math.abs(i3 - i5);
                    i2++;
                }
                i3 = i5;
            }
        }
        int i6 = iAbs * 10;
        if (i2 > 0) {
            return Math.round(i6 / i2) % 211;
        }
        return 0;
    }

    public List vp_a(List list, int i) {
        List listVp_a = vp_a(list);
        ArrayList arrayList = new ArrayList();
        if (!listVp_a.isEmpty()) {
            for (int i2 = 0; i2 < listVp_a.size(); i2++) {
                FiveMinuteData fiveMinuteDataVp_b = vp_b(((vp_a) listVp_a.get(i2)).vp_a(), i);
                if (fiveMinuteDataVp_b != null && fiveMinuteDataVp_b.getTimeBean() != null && !fiveMinuteDataVp_b.getTimeBean().isLeadTime()) {
                    arrayList.add(fiveMinuteDataVp_b);
                }
            }
        }
        return arrayList;
    }

    public boolean vp_a(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (b != 0) {
                return true;
            }
        }
        return false;
    }

    private boolean vp_a(vp_a vp_aVar) {
        byte[] bArrVp_a = vp_aVar.vp_a();
        if (vp_aVar.vp_b() == null) {
            return false;
        }
        int iVp_b = vp_aVar.vp_b().vp_b();
        int iVp_a = vp_aVar.vp_b().vp_a();
        if (bArrVp_a.length < iVp_a) {
            return false;
        }
        byte[] bArr = new byte[iVp_a];
        System.arraycopy(bArrVp_a, 0, bArr, 0, iVp_a);
        return iVp_b == com.veepoo.protocol.util.vp_a.vp_a(bArr);
    }

    public static String vp_a(String str, byte[] bArr, int i) {
        if (bArr == null) {
            return "-error- 【" + str + "】 数据块为空";
        }
        return bArr.length < i ? "-error- 【" + str + "】 数据块长度异常 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr) : str + " ---> " + com.veepoo.protocol.util.vp_g.vp_d(bArr);
    }
}
