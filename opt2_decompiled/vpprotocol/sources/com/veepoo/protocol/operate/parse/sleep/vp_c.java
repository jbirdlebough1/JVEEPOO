package com.veepoo.protocol.operate.parse.sleep;

import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.model.datas.InsomniaTimeData;
import com.veepoo.protocol.model.datas.SleepCrcData;
import com.veepoo.protocol.model.datas.SleepPrecisionData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_g;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/parse/sleep/vp_c.class */
public class vp_c implements com.veepoo.protocol.operate.parse.sleep.vp_a {
    ISleepDataListener vp_a;
    final byte[] vp_b = {-95, 24, 0, -94, 8, 1, 2, 3, 4, 5, 6, 7, 8, -93, 1, 11, -92, 2, 12, -52, -91, 3, 13, -35, -35, -90, 2, 1, -1, -89, 2, 1, -1};

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/parse/sleep/vp_c$vp_a.class */
    public static class vp_a {
        private byte[] vp_a = null;
        private byte[] vp_b = null;
        private byte[] vp_c = null;
        private byte[] vp_d = null;
        private byte[] vp_e = null;
        private int vp_f = 0;
        private int vp_g = 1;
        private boolean vp_h = false;

        public boolean vp_h() {
            return this.vp_h;
        }

        public byte[] vp_b() {
            return this.vp_a;
        }

        public byte[] vp_a() {
            return this.vp_b;
        }

        public byte[] vp_c() {
            return this.vp_c;
        }

        public byte[] vp_g() {
            return this.vp_e;
        }

        public int vp_e() {
            return this.vp_f;
        }

        public int vp_f() {
            return this.vp_g;
        }

        public byte[] vp_d() {
            return this.vp_d;
        }

        public void vp_b(byte[] bArr) {
            this.vp_a = bArr;
        }

        public void vp_a(byte[] bArr) {
            this.vp_b = bArr;
        }

        public void vp_c(byte[] bArr) {
            this.vp_c = bArr;
        }

        public void vp_e(byte[] bArr) {
            this.vp_e = bArr;
        }

        public void vp_d(byte[] bArr) {
            this.vp_d = bArr;
        }

        public void vp_a(int i) {
            this.vp_f = i;
        }

        public void vp_b(int i) {
            this.vp_g = i;
        }

        public void vp_a(boolean z) {
            this.vp_h = z;
        }
    }

    public vp_c(ISleepDataListener iSleepDataListener) {
        this.vp_a = iSleepDataListener;
    }

    private SleepCrcData vp_c(byte[] bArr) {
        SleepCrcData sleepCrcData = new SleepCrcData();
        if (bArr == null || bArr.length < 8) {
            return sleepCrcData;
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        sleepCrcData.setBaseSleepCrc(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[0]));
        sleepCrcData.setInsomniaCrc(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[3] + strArrByte2HexToStrArr[2]));
        sleepCrcData.setSleepCurveCrc(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4]));
        sleepCrcData.setAllSleepCrc(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[7] + strArrByte2HexToStrArr[6]));
        return sleepCrcData;
    }

    public static byte[] vp_d(byte[] bArr) {
        new StringBuilder().append("中科原始Sleep曲线------>> ").append(vp_g.vp_d(bArr));
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length * 8];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            int iVp_a = vp_g.vp_a(strArrVp_f[i]);
            byte[] bArrVp_d = vp_g.vp_d(((iVp_a & 192) >> 6) << 13);
            byte[] bArrVp_d2 = vp_g.vp_d(((iVp_a & 48) >> 4) << 13);
            byte[] bArrVp_d3 = vp_g.vp_d(((iVp_a & 12) >> 2) << 13);
            byte[] bArrVp_d4 = vp_g.vp_d((iVp_a & 3) << 13);
            int i2 = i * 8;
            bArr2[i2] = bArrVp_d[0];
            bArr2[i2 + 1] = bArrVp_d[1];
            bArr2[i2 + 2] = bArrVp_d2[0];
            bArr2[i2 + 3] = bArrVp_d2[1];
            bArr2[i2 + 4] = bArrVp_d3[0];
            bArr2[i2 + 5] = bArrVp_d3[1];
            bArr2[i2 + 6] = bArrVp_d4[0];
            bArr2[i2 + 7] = bArrVp_d4[1];
        }
        new StringBuilder().append("转换原始Sleep曲线------>> ").append(vp_g.vp_d(bArr2));
        return bArr2;
    }

    private vp_a vp_b(byte[] bArr) {
        byte b;
        vp_a vp_aVar = new vp_a();
        if (bArr == null || bArr.length == 0) {
            return vp_aVar;
        }
        int length = bArr.length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= length || (b = bArr[i2]) == 0) {
                break;
            }
            if (!vp_a(b)) {
                new StringBuilder().append("parseItemSleepV1Data 出现未知子块，停止本条解析 type = 0x").append(Integer.toHexString(b & 255)).append(",position = ").append(i2).append(",data = ").append(vp_g.vp_c(bArr));
                break;
            }
            int i3 = i2 + 1;
            int i4 = b == -91 ? 2 : 1;
            int i5 = i3 + i4;
            if (i5 > length) {
                new StringBuilder().append("parseItemSleepV1Data 子块长度字段被截断 type = 0x").append(Integer.toHexString(b & 255)).append(",position = ").append(i3);
                break;
            }
            int i6 = i4;
            int i7 = bArr[i3] & 255;
            if (i6 == 2) {
                i7 |= (bArr[i2 + 2] & 255) << 8;
            }
            int i8 = length - i5;
            int iVp_a = vp_a(b, i7, i8, vp_aVar);
            int i9 = iVp_a;
            boolean z = iVp_a < 0;
            if (z) {
                i9 = i8;
            }
            if (i9 <= 0) {
                new StringBuilder().append("parseItemSleepV1Data 子块长度非法 type = 0x").append(Integer.toHexString(b & 255)).append(",declaredLength = ").append(i7).append(",remain = ").append(i8);
                break;
            }
            boolean z2 = z;
            byte[] bArr2 = new byte[i9];
            System.arraycopy(bArr, i5, bArr2, 0, i9);
            int i10 = i5 + i9;
            vp_a(vp_aVar, b, bArr2);
            if (z2) {
                new StringBuilder().append("parseItemSleepV1Data 子块被截断，停止后续解析 type = 0x").append(Integer.toHexString(b & 255)).append(",declaredLength = ").append(i7).append(",contentLength = ").append(i9).append(",data = ").append(vp_g.vp_c(bArr));
                break;
            }
            i = i10;
        }
        return vp_aVar;
    }

    private void vp_a(vp_a vp_aVar, byte b, byte[] bArr) {
        switch (b) {
            case EUIFromType.UICode.G15ImgAPPDownloadAppQRCode /* -94 */:
                vp_aVar.vp_b(bArr);
                break;
            case EUIFromType.UICode.G15ImgProfile /* -93 */:
                vp_aVar.vp_a(bArr);
                int i = bArr.length >= 36 ? bArr[35] & 255 : -1;
                int i2 = bArr.length > 0 ? bArr[bArr.length - 1] & 255 : -1;
                if (i == 2 || i2 == 2) {
                    vp_aVar.vp_a(true);
                }
                break;
            case EUIFromType.UICode.G15ImgTheme1 /* -92 */:
                vp_aVar.vp_c(bArr);
                break;
            case EUIFromType.UICode.G15ImgTheme2 /* -91 */:
                vp_aVar.vp_e(bArr);
                break;
            case -90:
                int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
                if (iArrByte2HexToIntArr != null && iArrByte2HexToIntArr.length >= 1) {
                    vp_aVar.vp_a(iArrByte2HexToIntArr[0]);
                }
                if (iArrByte2HexToIntArr != null && iArrByte2HexToIntArr.length >= 2) {
                    vp_aVar.vp_b(iArrByte2HexToIntArr[1]);
                    break;
                }
                break;
            case -89:
                vp_aVar.vp_d(bArr);
                break;
        }
    }

    private byte[] vp_a(List list) {
        if (list.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = new byte[list.size() * 16];
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr2 = (byte[]) list.get(i);
            if (bArr2 != null && bArr2.length > 4) {
                System.arraycopy(bArr2, 4, bArr, i * 16, Math.min(bArr2.length - 4, 16));
            }
        }
        return bArr;
    }

    private void vp_c(SleepPrecisionData sleepPrecisionData, byte[] bArr) {
        int accurateType = sleepPrecisionData.getAccurateType();
        if (bArr == null || bArr.length == 0) {
            new StringBuilder().append("pasrseSleepCurveBlock 0xA5曲线块为空 allSleepTime = ").append(sleepPrecisionData.getAllSleepTime());
            sleepPrecisionData.setSleepSourceStr("");
            sleepPrecisionData.setSleepLine("");
            return;
        }
        int length = bArr.length;
        if (accurateType != 1 || length % 2 == 0) {
            String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
            int i = 0;
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i2 = 0; i2 < length / 2; i2++) {
                int i3 = i2 * 2;
                int iHexStrToInt = (VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[i3] + strArrByte2HexToStrArr[i3 + 1]) & 57344) >> 13;
                if (sleepPrecisionData.getAccurateType() == 2 && iHexStrToInt == 3) {
                    iHexStrToInt = 4;
                }
                if (iHexStrToInt > 4) {
                    iHexStrToInt = 4;
                }
                if (iHexStrToInt == 3) {
                    i++;
                }
                sb.append(iHexStrToInt);
            }
            for (String str : strArrByte2HexToStrArr) {
                sb2.append(str);
            }
            sleepPrecisionData.setSleepSourceStr(sb2.toString());
            int allSleepTime = sleepPrecisionData.getAllSleepTime();
            int length2 = allSleepTime;
            if (allSleepTime < 0) {
                length2 = 0;
            }
            if (length2 > sb.length()) {
                new StringBuilder().append("pasrseSleepCurveBlock 曲线点数不足 allSleepTime = ").append(length2).append(",curvePoints = ").append(sb.length());
                length2 = sb.length();
            }
            sleepPrecisionData.setSleepLine(sb.substring(0, length2));
            sleepPrecisionData.setInsomniaDuration((i * sleepPrecisionData.getOnePointDuration()) / 60);
        }
    }

    public void vp_b(SleepPrecisionData sleepPrecisionData, byte[] bArr) {
        if (bArr.length < 45) {
            return;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        sleepPrecisionData.setInsomniaTag(iArrByte2HexToIntArr[0]);
        sleepPrecisionData.setInsomniaScore(iArrByte2HexToIntArr[1]);
        sleepPrecisionData.setInsomniaTimes(iArrByte2HexToIntArr[2]);
        sleepPrecisionData.setInsomniaLength(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3]));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int i2 = i * 2;
            int iHexStrToInt = VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[i2 + 5] + strArrByte2HexToStrArr[i2 + 5]);
            int iHexStrToInt2 = VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[i2 + 25] + strArrByte2HexToStrArr[i2 + 25]);
            arrayList.add(new InsomniaTimeData(new TimeData(iHexStrToInt / 60, iHexStrToInt % 60), new TimeData(iHexStrToInt2 / 60, iHexStrToInt2 % 60)));
        }
        sleepPrecisionData.setInsomniaBeanList(arrayList);
    }

    private boolean vp_a(byte b) {
        return b == -94 || b == -93 || b == -92 || b == -91 || b == -90 || b == -89;
    }

    private void vp_a(SleepPrecisionData sleepPrecisionData) {
        sleepPrecisionData.getInsomniaBeanList();
        sleepPrecisionData.setSleepLine(sleepPrecisionData.getSleepLine());
    }

    private List vp_a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr == null || bArr.length == 0) {
            return arrayList;
        }
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            if (bArr[i] != -95) {
                i++;
            } else {
                int i2 = i + 3;
                if (i2 > length) {
                    new StringBuilder().append("parseA1SleepV1Data 0xA1头部被截断 position = ").append(i).append(",total = ").append(length);
                    break;
                }
                int i3 = ((bArr[i + 2] & 255) << 8) | (bArr[i + 1] & 255);
                int i4 = i3 - 3;
                int i5 = i4;
                new StringBuilder().append("parseA1SleepV1Data 0xA1 startPosition = ").append(i2).append(",contentLength = ").append(i5).append(",lengthA1 = ").append(i3);
                if (i4 <= 0 || i2 + i5 > length) {
                    new StringBuilder().append("parseA1SleepV1Data 0xA1长度异常 lengthA1 = ").append(i3).append(",startPosition = ").append(i2).append(",total = ").append(length);
                    int i6 = length - i2;
                    i5 = i6;
                    if (i6 <= 0) {
                        break;
                    }
                }
                byte[] bArr2 = new byte[i5];
                System.arraycopy(bArr, i2, bArr2, 0, i5);
                arrayList.add(bArr2);
                i = i2 + i5;
            }
        }
        return arrayList;
    }

    @Override // com.veepoo.protocol.operate.parse.sleep.vp_a
    public void vp_a(int i, List list) {
        if (list == null || list.isEmpty()) {
            new StringBuilder().append("解析Sleep数据 入参为空 day = ").append(i);
            return;
        }
        byte[] bArrVp_a = vp_a(list);
        if (bArrVp_a.length == 0) {
            new StringBuilder().append("解析Sleep数据 拼接后为空 day = ").append(i);
            return;
        }
        new StringBuilder().append("解析Sleep数据 day = ").append(i);
        new StringBuilder().append("解析Sleep数据 data = ").append(vp_g.vp_c(bArrVp_a));
        List listVp_a = vp_a(bArrVp_a);
        for (int i2 = 0; i2 < listVp_a.size(); i2++) {
            byte[] bArr = (byte[]) listVp_a.get(i2);
            try {
                vp_a vp_aVarVp_b = vp_b(bArr);
                if (vp_aVarVp_b == null || vp_aVarVp_b.vp_a() == null) {
                    new StringBuilder().append("Sleep明细缺少0xA3基本信息块，丢弃 data = ").append(vp_g.vp_c(bArr));
                } else {
                    byte[] bArrVp_a2 = vp_aVarVp_b.vp_a();
                    byte[] bArrVp_c = vp_aVarVp_b.vp_c();
                    byte[] bArrVp_d = vp_aVarVp_b.vp_d();
                    byte[] bArrVp_g = vp_aVarVp_b.vp_g();
                    vp_c(vp_aVarVp_b.vp_b());
                    SleepPrecisionData sleepPrecisionDataVp_a = vp_a(i, bArrVp_a2, bArrVp_c, bArrVp_d, bArrVp_g, vp_aVarVp_b.vp_h());
                    if (sleepPrecisionDataVp_a != null) {
                        sleepPrecisionDataVp_a.setLaster(vp_aVarVp_b.vp_e());
                        sleepPrecisionDataVp_a.setNext(vp_aVarVp_b.vp_f());
                        ISleepDataListener iSleepDataListener = this.vp_a;
                        if (iSleepDataListener != null) {
                            iSleepDataListener.onSleepDataChange(String.valueOf(i), sleepPrecisionDataVp_a);
                        }
                    }
                }
            } catch (Exception e) {
                new StringBuilder().append("解析Sleep数据异常 data = ").append(vp_g.vp_c(bArr)).append(" , ").append(e);
                e.printStackTrace();
            }
        }
    }

    private void vp_a(int i, SleepPrecisionData sleepPrecisionData, byte[] bArr) {
        if (bArr == null || bArr.length < 35) {
            new StringBuilder().append("pasrseBaseSleepBlock 0xA3长度不足，放弃解析 length = ").append(bArr == null ? -1 : bArr.length);
            return;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -i);
        int i2 = calendar.get(1);
        int i3 = calendar.get(2) + 1;
        int i4 = (i3 != 1 || iArrByte2HexToIntArr[0] <= i3) ? i2 : i2 - 1;
        if (i3 == 1 && iArrByte2HexToIntArr[4] > i3) {
            i2--;
        }
        sleepPrecisionData.setPresicionDate(new TimeData(i4, iArrByte2HexToIntArr[0], iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3]), new TimeData(i2, iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7]));
        sleepPrecisionData.setSleepTag(iArrByte2HexToIntArr[8]);
        sleepPrecisionData.setGetUpScore(iArrByte2HexToIntArr[9]);
        sleepPrecisionData.setDeepScore(iArrByte2HexToIntArr[10]);
        sleepPrecisionData.setSleepEfficiencyScore(iArrByte2HexToIntArr[11]);
        sleepPrecisionData.setFallAsleepScore(iArrByte2HexToIntArr[12]);
        sleepPrecisionData.setSleepTimeScore(iArrByte2HexToIntArr[13]);
        sleepPrecisionData.setExitSleepMode(iArrByte2HexToIntArr[14]);
        sleepPrecisionData.setSleepQulity(iArrByte2HexToIntArr[15]);
        sleepPrecisionData.setWakeCount(iArrByte2HexToIntArr[16]);
        sleepPrecisionData.setDeepAndLightMode(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[18] + strArrByte2HexToStrArr[17]));
        sleepPrecisionData.setDeepSleepTime(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[20] + strArrByte2HexToStrArr[19]));
        sleepPrecisionData.setLowSleepTime(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[22] + strArrByte2HexToStrArr[21]));
        sleepPrecisionData.setOtherDuration(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[24] + strArrByte2HexToStrArr[23]));
        sleepPrecisionData.setAllSleepTime(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[26] + strArrByte2HexToStrArr[25]));
        sleepPrecisionData.setFirstDeepDuration(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[28] + strArrByte2HexToStrArr[27]));
        sleepPrecisionData.setGetUpDuration(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[30] + strArrByte2HexToStrArr[29]));
        sleepPrecisionData.setGetUpToDeepAve(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[32] + strArrByte2HexToStrArr[31]));
        sleepPrecisionData.setOnePointDuration(VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[34] + strArrByte2HexToStrArr[33]));
        if (bArr.length >= 36) {
            sleepPrecisionData.setAccurateType(iArrByte2HexToIntArr[35]);
        }
    }

    private void vp_a(SleepPrecisionData sleepPrecisionData, byte[] bArr) {
        if (bArr.length < 44) {
            return;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        sleepPrecisionData.setInsomniaTag(iArrByte2HexToIntArr[0]);
        sleepPrecisionData.setInsomniaScore(iArrByte2HexToIntArr[1]);
        sleepPrecisionData.setInsomniaTimes(iArrByte2HexToIntArr[2]);
        sleepPrecisionData.setInsomniaLength(iArrByte2HexToIntArr[3]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            int i2 = i * 2;
            int iHexStrToInt = VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[i2 + 4] + strArrByte2HexToStrArr[i2 + 4]);
            int iHexStrToInt2 = VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[i2 + 24] + strArrByte2HexToStrArr[i2 + 24]);
            arrayList.add(new InsomniaTimeData(new TimeData(iHexStrToInt / 60, iHexStrToInt % 60), new TimeData(iHexStrToInt2 / 60, iHexStrToInt2 % 60)));
        }
        sleepPrecisionData.setInsomniaBeanList(arrayList);
    }

    private SleepPrecisionData vp_a(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, boolean z) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        SleepPrecisionData sleepPrecisionData = new SleepPrecisionData();
        vp_a(i, sleepPrecisionData, bArr);
        if (bArr2 != null) {
            vp_a(sleepPrecisionData, bArr2);
        } else if (bArr3 != null) {
            vp_b(sleepPrecisionData, bArr3);
        }
        if (z || sleepPrecisionData.getAccurateType() == 2) {
            bArr4 = vp_d(bArr4);
        }
        vp_c(sleepPrecisionData, bArr4);
        vp_a(sleepPrecisionData);
        return sleepPrecisionData;
    }

    private int vp_a(byte b, int i, int i2, vp_a vp_aVar) {
        if (i <= 0) {
            return 0;
        }
        if (b != -91) {
            if (i <= i2) {
                return i;
            }
            new StringBuilder().append("resolveBlockContentLength 长度越界 type = 0x").append(Integer.toHexString(b & 255)).append(",declaredLength = ").append(i).append(",remain = ").append(i2);
            return -1;
        }
        if (vp_aVar.vp_h()) {
            int i3 = (i + 3) / 4;
            if (i3 <= i2) {
                return i3;
            }
            new StringBuilder().append("resolveBlockContentLength 中科曲线越界 declaredLength = ").append(i).append(",bytes = ").append(i3).append(",remain = ").append(i2);
            return -1;
        }
        if (i <= i2) {
            return i;
        }
        int i4 = (i + 3) / 4;
        if (i4 <= 0 || i4 > i2) {
            new StringBuilder().append("resolveBlockContentLength 0xA5长度越界且无法换算 declaredLength = ").append(i).append(",remain = ").append(i2);
            return -1;
        }
        new StringBuilder().append("resolveBlockContentLength 0xA5按中科点数口径兼容 declaredLength = ").append(i).append(" -> bytes = ").append(i4).append(",remain = ").append(i2);
        vp_aVar.vp_a(true);
        return i4;
    }
}
