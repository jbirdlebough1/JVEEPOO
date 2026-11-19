package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISportModelOriginListener;
import com.veepoo.protocol.model.datas.SportModelGPSWatchOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginHeadData;
import com.veepoo.protocol.model.datas.SportModelOriginItemData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.shareprence.SpUtil;
import com.veepoo.protocol.shareprence.SputilVari;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.SportUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cs.class */
public class vp_cs extends com.veepoo.protocol.vp_a {
    static final DateFormat vp_o = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    BluetoothClient vp_c;
    String vp_d;
    BleWriteResponse vp_e;
    ISportModelOriginListener vp_i;
    private final List vp_f = new ArrayList();
    int vp_g = -1;
    int vp_h = -1;
    private float vp_j = 0.0f;
    private int vp_k = 0;
    private boolean vp_l = false;
    DecimalFormat vp_m = new DecimalFormat("#0.000000", DecimalFormatSymbols.getInstance(Locale.US));
    List vp_n = new ArrayList();

    private void vp_e(byte[] bArr) {
        if (bArr[0] == -95) {
            int i = bArr[1] + 2;
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            SportModelGPSWatchOriginHeadData sportModelGPSWatchOriginHeadDataVp_c = vp_c(bArr2);
            if (!com.veepoo.protocol.util.vp_j.vp_d(sportModelGPSWatchOriginHeadDataVp_c.getStartTime().toFullDateTimeString())) {
                vp_d();
                return;
            }
            if (!com.veepoo.protocol.util.vp_j.vp_d(sportModelGPSWatchOriginHeadDataVp_c.getStopTime().toFullDateTimeString())) {
                vp_d();
                return;
            }
            int length = bArr.length - i;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, i, bArr3, 0, length);
            vp_d(bArr3);
            int rate = 0;
            int i2 = 0;
            int i3 = 0;
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            while (i4 < this.vp_n.size()) {
                byte[] bArr4 = (byte[]) this.vp_n.get(i4);
                i4++;
                SportModelOriginItemData sportModelOriginItemDataVp_a = vp_a(sportModelGPSWatchOriginHeadDataVp_c, bArr4, i4, this.vp_n.size(), this.vp_k);
                if (sportModelOriginItemDataVp_a.getRate() >= 30 && sportModelOriginItemDataVp_a.getRate() <= 200) {
                    i2++;
                    rate += sportModelOriginItemDataVp_a.getRate();
                }
                if (sportModelOriginItemDataVp_a.getRate() <= 120) {
                    i3++;
                }
                arrayList.add(sportModelOriginItemDataVp_a);
            }
            if (i2 != 0) {
                sportModelGPSWatchOriginHeadDataVp_c.setAverRate(rate / i2);
            }
            int i5 = i3 * 60;
            if (i5 >= sportModelGPSWatchOriginHeadDataVp_c.getSportDuration()) {
                sportModelGPSWatchOriginHeadDataVp_c.setOxSportDuration(sportModelGPSWatchOriginHeadDataVp_c.getSportDuration());
            } else {
                sportModelGPSWatchOriginHeadDataVp_c.setOxSportDuration(i5);
            }
            this.vp_i.onGPSWatchSportModeHeadChange(sportModelGPSWatchOriginHeadDataVp_c);
            this.vp_i.onItemChangeListListener(arrayList);
            vp_d();
        }
    }

    private static String vp_c(int i) {
        switch (i) {
            case 0:
                return "结束";
            case 1:
                return "起始";
            case 2:
                return "过程中";
            case 3:
                return "暂停";
            default:
                return "unknown";
        }
    }

    private static String vp_b(int i) {
        if (i == 0) {
            return "无";
        }
        if (i != 1) {
            return i != 2 ? "unknown" : "强";
        }
        return "弱";
    }

    private void vp_d(byte[] bArr) {
        this.vp_n.clear();
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        int length = bArr.length;
        boolean z = true;
        while (byteBufferOrder.position() < length && z) {
            z = byteBufferOrder.get() == -94;
            if (z) {
                int iVp_a = com.veepoo.protocol.util.vp_g.vp_a(byteBufferOrder.get(), byteBufferOrder.get());
                int iPosition = byteBufferOrder.position();
                int i = iPosition + iVp_a;
                if (i >= bArr.length - 1) {
                    return;
                }
                byteBufferOrder.position(i);
                this.vp_n.add(vp_a(bArr, iVp_a, iPosition));
            }
        }
    }

    private void vp_a() {
        if (this.vp_f.isEmpty()) {
            return;
        }
        this.vp_f.clear();
    }

    public void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        this.vp_h = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getSportModelDay();
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_g = -1;
        this.vp_e = bleWriteResponse;
        this.vp_j = 0.0f;
        this.vp_l = false;
        vp_d();
    }

    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_g = i;
        this.vp_e = bleWriteResponse;
        this.vp_j = 0.0f;
        this.vp_l = true;
        byte[] bArrVp_a = vp_a(i);
        this.vp_f.clear();
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        float fVp_a = vp_a(Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue(), Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue());
        this.vp_j = fVp_a;
        if (fVp_a != 1.0f) {
            this.vp_i.onReadOriginProgress(fVp_a);
        }
        if (b == b3 && b2 == b4) {
            int i = SpUtil.getInt(this.vp_a, SputilVari.MULTSPORTMODEL_TYPE, 0);
            this.vp_k = i;
            if (i == 4 || i == 5 || i == 6) {
                vp_c();
            } else {
                vp_b();
            }
        }
    }

    private void vp_c() {
        int size = this.vp_f.size();
        int length = 0;
        Iterator it = this.vp_f.iterator();
        while (it.hasNext()) {
            length += ((byte[]) it.next()).length;
        }
        byte[] bArr = new byte[length - (size * 5)];
        int length2 = 0;
        for (int i = 0; i < this.vp_f.size(); i++) {
            byte[] bArr2 = (byte[]) this.vp_f.get(i);
            if (bArr2.length - 5 >= 0) {
                System.arraycopy(bArr2, 5, bArr, length2, bArr2.length - 5);
            }
            length2 += bArr2.length - 5;
        }
        vp_e(bArr);
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private void vp_b() {
        if (this.vp_f.size() < 3) {
            vp_d();
            return;
        }
        byte[] bArr = new byte[42];
        for (int i = 0; i < 3; i++) {
            System.arraycopy((byte[]) this.vp_f.get(i), 6, bArr, i * 14, 14);
        }
        SportModelOriginHeadData sportModelOriginHeadDataVp_b = vp_b(bArr);
        if (sportModelOriginHeadDataVp_b == null) {
            vp_d();
            return;
        }
        new StringBuilder().append("origin sport model head:").append(sportModelOriginHeadDataVp_b.toString());
        if (!com.veepoo.protocol.util.vp_j.vp_d(sportModelOriginHeadDataVp_b.getStartTime().toFullDateTimeString())) {
            vp_d();
            return;
        }
        if (!com.veepoo.protocol.util.vp_j.vp_d(sportModelOriginHeadDataVp_b.getStopTime().toFullDateTimeString())) {
            vp_d();
            return;
        }
        ArrayList arrayList = new ArrayList();
        int rate = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 3; i4 < this.vp_f.size(); i4++) {
            SportModelOriginItemData sportModelOriginItemDataVp_a = vp_a((byte[]) this.vp_f.get(i4), sportModelOriginHeadDataVp_b.getStartTime(), sportModelOriginHeadDataVp_b.getCrc(), 3);
            if (sportModelOriginItemDataVp_a.getRate() >= 30 && sportModelOriginItemDataVp_a.getRate() <= 200) {
                i2++;
                rate += sportModelOriginItemDataVp_a.getRate();
            }
            if (sportModelOriginItemDataVp_a.getRate() <= 120) {
                i3++;
            }
            arrayList.add(sportModelOriginItemDataVp_a);
            new StringBuilder().append("origin sport model item:").append(sportModelOriginItemDataVp_a.toString());
        }
        if (i2 != 0) {
            sportModelOriginHeadDataVp_b.setAverRate(rate / i2);
        }
        int i5 = i3 * 60;
        if (i5 >= sportModelOriginHeadDataVp_b.getSportDuration()) {
            sportModelOriginHeadDataVp_b.setOxsporttimes(sportModelOriginHeadDataVp_b.getSportDuration());
        } else {
            sportModelOriginHeadDataVp_b.setOxsporttimes(i5);
        }
        this.vp_i.onHeadChangeListListener(sportModelOriginHeadDataVp_b);
        this.vp_i.onItemChangeListListener(arrayList);
        vp_d();
    }

    private void vp_d() {
        new StringBuilder().append("origin sport model read day=").append(this.vp_g);
        int i = this.vp_g + 1;
        this.vp_g = i;
        if (i < this.vp_h && !this.vp_l) {
            vp_a();
            super.send(vp_a(this.vp_g), this.vp_c, this.vp_d, this.vp_e);
        } else {
            ISportModelOriginListener iSportModelOriginListener = this.vp_i;
            if (iSportModelOriginListener != null) {
                iSportModelOriginListener.onReadOriginComplete();
            }
        }
    }

    public static SportModelGPSWatchOriginHeadData vp_c(byte[] bArr) {
        vp_a(bArr[0]);
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        byte[] bArr4 = new byte[4];
        System.arraycopy(bArr, 4, bArr2, 0, 4);
        System.arraycopy(bArr, 8, bArr3, 0, 4);
        System.arraycopy(bArr, 12, bArr4, 0, 4);
        String[] strArrVp_f2 = com.veepoo.protocol.util.vp_g.vp_f(bArr2);
        int iIntValue = Integer.valueOf(strArrVp_f2[3] + strArrVp_f2[2] + strArrVp_f2[1] + strArrVp_f2[0], 16).intValue();
        String[] strArrVp_f3 = com.veepoo.protocol.util.vp_g.vp_f(bArr3);
        int iIntValue2 = Integer.valueOf(strArrVp_f3[3] + strArrVp_f3[2] + strArrVp_f3[1] + strArrVp_f3[0], 16).intValue();
        String[] strArrVp_f4 = com.veepoo.protocol.util.vp_g.vp_f(bArr4);
        int iIntValue3 = Integer.valueOf(strArrVp_f4[3] + strArrVp_f4[2] + strArrVp_f4[1] + strArrVp_f4[0], 16).intValue();
        TimeData timeBeanByTimestampSecond = TimeData.getTimeBeanByTimestampSecond(iIntValue);
        TimeData timeBeanByTimestampSecond2 = TimeData.getTimeBeanByTimestampSecond(iIntValue2);
        vp_a(bArr[2]);
        new StringBuilder().append("开始时间：").append(timeBeanByTimestampSecond.toString());
        new StringBuilder().append("结束时间：").append(timeBeanByTimestampSecond2.toString());
        byte b = bArr[17];
        int i = b + 2;
        String str = strArrVp_f[21] + strArrVp_f[20] + strArrVp_f[19] + strArrVp_f[18];
        String str2 = strArrVp_f[25] + strArrVp_f[24] + strArrVp_f[23] + strArrVp_f[22];
        String str3 = strArrVp_f[29] + strArrVp_f[28] + strArrVp_f[27] + strArrVp_f[26];
        int unsignedInt = Integer.parseUnsignedInt(strArrVp_f[33] + strArrVp_f[32] + strArrVp_f[31] + strArrVp_f[30], 16);
        int unsignedInt2 = Integer.parseUnsignedInt(str, 16);
        int unsignedInt3 = Integer.parseUnsignedInt(str3, 16);
        int unsignedInt4 = Integer.parseUnsignedInt(str2, 16);
        int i2 = Integer.parseInt(strArrVp_f[34], 16);
        int i3 = Integer.parseInt(strArrVp_f[35], 16);
        int i4 = Integer.parseInt(strArrVp_f[36], 16);
        String str4 = strArrVp_f[38] + strArrVp_f[37];
        String str5 = strArrVp_f[40] + strArrVp_f[39];
        String str6 = strArrVp_f[42] + strArrVp_f[41];
        int iIntValue4 = Integer.valueOf(str4, 16).intValue();
        int iIntValue5 = Integer.valueOf(str5, 16).intValue();
        int iIntValue6 = Integer.valueOf(str6, 16).intValue();
        String str7 = strArrVp_f[44] + strArrVp_f[43];
        String str8 = strArrVp_f[46] + strArrVp_f[45];
        String str9 = strArrVp_f[48] + strArrVp_f[47];
        int iIntValue7 = Integer.valueOf(str7, 16).intValue();
        int iIntValue8 = Integer.valueOf(str8, 16).intValue();
        int iIntValue9 = Integer.valueOf(str9, 16).intValue();
        String str10 = strArrVp_f[50] + strArrVp_f[49];
        String str11 = strArrVp_f[52] + strArrVp_f[51];
        String str12 = strArrVp_f[54] + strArrVp_f[53];
        int iIntValue10 = Integer.valueOf(str10, 16).intValue();
        int iIntValue11 = Integer.valueOf(str11, 16).intValue();
        int iIntValue12 = Integer.valueOf(str12, 16).intValue();
        long jLongValue = 0;
        long jLongValue2 = 0;
        long jLongValue3 = 0;
        long jIntValue = 0;
        long jIntValue2 = 0;
        if (i > 39) {
            String str13 = strArrVp_f[58] + strArrVp_f[57] + strArrVp_f[56] + strArrVp_f[55];
            String str14 = strArrVp_f[62] + strArrVp_f[61] + strArrVp_f[60] + strArrVp_f[59];
            String str15 = strArrVp_f[66] + strArrVp_f[65] + strArrVp_f[64] + strArrVp_f[63];
            String str16 = strArrVp_f[70] + strArrVp_f[69] + strArrVp_f[68] + strArrVp_f[67];
            String str17 = strArrVp_f[74] + strArrVp_f[73] + strArrVp_f[72] + strArrVp_f[71];
            jLongValue = Long.valueOf(str13, 16).longValue();
            jLongValue2 = Long.valueOf(str14, 16).longValue();
            jLongValue3 = Long.valueOf(str15, 16).longValue();
            jIntValue = Integer.valueOf(str16, 16).intValue();
            jIntValue2 = Integer.valueOf(str17, 16).intValue();
        }
        vp_a(bArr[16]);
        new StringBuilder().append("总计步：").append(unsignedInt2);
        new StringBuilder().append("总距离：").append(unsignedInt4);
        new StringBuilder().append("总卡路里：").append(unsignedInt3);
        new StringBuilder().append("总运动量：").append(unsignedInt);
        String str18 = strArrVp_f[b + 21] + strArrVp_f[b + 20];
        String str19 = strArrVp_f[b + 24] + strArrVp_f[b + 23];
        int iIntValue13 = Integer.valueOf(str18, 16).intValue();
        int iIntValue14 = Integer.valueOf(str19, 16).intValue();
        int i5 = iArrVp_e[b + 22];
        vp_a(bArr[b + 18]);
        new StringBuilder().append("总记录条数：").append(iIntValue13);
        new StringBuilder().append("暂停次数：").append(iIntValue14);
        new StringBuilder().append("总暂停时间：").append(i5);
        int iIntValue15 = Integer.valueOf(strArrVp_f[b + 28] + strArrVp_f[b + 27], 16).intValue();
        vp_a(bArr[b + 25]);
        new StringBuilder().append("CRC：").append(iIntValue15);
        int iIntValue16 = Integer.valueOf(strArrVp_f[b + 32] + strArrVp_f[b + 31], 16).intValue();
        int i6 = iArrVp_e[b + 33];
        vp_a(bArr[b + 29]);
        new StringBuilder().append("运动类型：").append(iIntValue16);
        new StringBuilder().append("数据展示类型：").append(i6);
        SportModelGPSWatchOriginHeadData sportModelGPSWatchOriginHeadData = new SportModelGPSWatchOriginHeadData(timeBeanByTimestampSecond, timeBeanByTimestampSecond2, unsignedInt2, unsignedInt4, unsignedInt3, unsignedInt, iIntValue13, i5, iIntValue14, iIntValue15, iIntValue16, i6 == 1 || i6 == 2 || i6 == 5 || i6 == 6);
        sportModelGPSWatchOriginHeadData.setMaxHeartRate(i2);
        sportModelGPSWatchOriginHeadData.setMinHeartRate(i4);
        sportModelGPSWatchOriginHeadData.setAvgHeartRate(i3);
        sportModelGPSWatchOriginHeadData.setMaxPace(iIntValue4);
        sportModelGPSWatchOriginHeadData.setMinPace(iIntValue6);
        sportModelGPSWatchOriginHeadData.setAvgPace(iIntValue5);
        sportModelGPSWatchOriginHeadData.setMaxSpeed(iIntValue7);
        sportModelGPSWatchOriginHeadData.setMinSpeed(iIntValue9);
        sportModelGPSWatchOriginHeadData.setAvgSpeed(iIntValue8);
        sportModelGPSWatchOriginHeadData.setMaxCadence(iIntValue10);
        sportModelGPSWatchOriginHeadData.setMinCadence(iIntValue12);
        sportModelGPSWatchOriginHeadData.setAvgCadence(iIntValue11);
        sportModelGPSWatchOriginHeadData.setMaxAltitude((int) jLongValue);
        sportModelGPSWatchOriginHeadData.setAvgAltitude((int) jLongValue2);
        sportModelGPSWatchOriginHeadData.setMinAltitude((int) jLongValue3);
        sportModelGPSWatchOriginHeadData.setCumulativeClimb((int) jIntValue);
        sportModelGPSWatchOriginHeadData.setCumulativeDecline((int) jIntValue2);
        sportModelGPSWatchOriginHeadData.setDataType(i6);
        sportModelGPSWatchOriginHeadData.setA7ProtectSportType(SpUtil.getInt(VPOperateManager.mContext, SputilVari.MULTSPORTMODEL_TYPE, 0));
        sportModelGPSWatchOriginHeadData.setSportDuration(iIntValue3);
        return sportModelGPSWatchOriginHeadData;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.veepoo.protocol.model.datas.SportModelOriginHeadData, java.lang.Throwable] */
    private SportModelOriginHeadData vp_b(byte[] bArr) {
        ?? sportModelOriginHeadData;
        try {
            new StringBuilder().append("SportModelHead Bytes:").append(VpBleByteUtil.byte2HexForShow(bArr));
            String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            String str = strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1];
            String str2 = strArrByte2HexToStrArr[9] + strArrByte2HexToStrArr[8];
            String str3 = strArrByte2HexToStrArr[18] + strArrByte2HexToStrArr[17] + strArrByte2HexToStrArr[16] + strArrByte2HexToStrArr[15];
            String str4 = strArrByte2HexToStrArr[22] + strArrByte2HexToStrArr[21] + strArrByte2HexToStrArr[20] + strArrByte2HexToStrArr[19];
            String str5 = strArrByte2HexToStrArr[26] + strArrByte2HexToStrArr[25] + strArrByte2HexToStrArr[24] + strArrByte2HexToStrArr[23];
            String str6 = strArrByte2HexToStrArr[30] + strArrByte2HexToStrArr[29] + strArrByte2HexToStrArr[28] + strArrByte2HexToStrArr[27];
            String str7 = strArrByte2HexToStrArr[32] + strArrByte2HexToStrArr[31];
            String str8 = strArrByte2HexToStrArr[35] + strArrByte2HexToStrArr[34];
            String str9 = strArrByte2HexToStrArr[37] + strArrByte2HexToStrArr[36];
            int i = iArrByte2HexToIntArr[0];
            int iIntValue = Integer.valueOf(str, 16).intValue();
            int i2 = iArrByte2HexToIntArr[3];
            int i3 = iArrByte2HexToIntArr[4];
            int i4 = iArrByte2HexToIntArr[5];
            int i5 = iArrByte2HexToIntArr[6];
            int i6 = iArrByte2HexToIntArr[7];
            int iIntValue2 = Integer.valueOf(str2, 16).intValue();
            int i7 = iArrByte2HexToIntArr[10];
            int i8 = iArrByte2HexToIntArr[11];
            int i9 = iArrByte2HexToIntArr[12];
            int i10 = iArrByte2HexToIntArr[13];
            int i11 = iArrByte2HexToIntArr[14];
            int i12 = iArrByte2HexToIntArr[38];
            int iIntValue3 = Integer.valueOf(str3, 16).intValue();
            int iIntValue4 = Integer.valueOf(str4, 16).intValue();
            int iIntValue5 = Integer.valueOf(str5, 16).intValue();
            int iIntValue6 = Integer.valueOf(str6, 16).intValue();
            int iIntValue7 = Integer.valueOf(str7, 16).intValue();
            int iIntValue8 = Integer.valueOf(str8, 16).intValue();
            int iIntValue9 = Integer.valueOf(str9, 16).intValue();
            double positionDouble = SportUtil.getPositionDouble(((double) iIntValue4) / 1000.0d, 4);
            double positionDouble2 = SportUtil.getPositionDouble(((double) iIntValue5) / 1000.0d, 1);
            TimeData timeData = new TimeData(iIntValue, i2, i3, i4, i5, i6);
            TimeData timeData2 = new TimeData(iIntValue2, i7, i8, i9, i10, i11);
            int iVp_a = vp_a(timeData2, timeData);
            sportModelOriginHeadData = new SportModelOriginHeadData(timeData.toDatabaseDateString(), timeData, timeData2, iIntValue3, iVp_a, iIntValue6, positionDouble, positionDouble2, iIntValue7, iArrByte2HexToIntArr[33], iIntValue8, iIntValue9, vp_a(iVp_a, iIntValue8, positionDouble), 0, 0, i12);
            return sportModelOriginHeadData;
        } catch (Exception unused) {
            sportModelOriginHeadData.printStackTrace();
            return null;
        }
    }

    private static Date vp_a(TimeData timeData) {
        return vp_o.parse(timeData.toFullDateTimeString());
    }

    private int vp_a(int i, int i2, double d) {
        int i3 = 0;
        if (d != 0.0d) {
            i3 = (int) (((double) (i - i2)) / d);
        }
        return i3;
    }

    private float vp_a(float f, int i) {
        float f2 = 1.0f / this.vp_h;
        float f3 = (f2 * this.vp_g) + ((i == 0 || f == 0.0f) ? f2 : (f / i) * f2);
        new StringBuilder().append("originPackageNumber=").append(f).append(",allPackage=").append(i).append(",progress=").append(f3);
        return f3;
    }

    public byte[] vp_a(int i) {
        new StringBuilder().append("getReadCmd=").append(i);
        byte bLoUint16 = VpBleByteUtil.loUint16((short) i);
        byte[] bArr = new byte[20];
        bArr[0] = -44;
        bArr[1] = bLoUint16;
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.model.datas.TimeData] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [long] */
    /* JADX WARN: Type inference failed for: r0v9, types: [long] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    private static int vp_a(TimeData timeData, TimeData timeData2) {
        ?? time = timeData2;
        ?? r5 = 0;
        try {
            time = (vp_a(timeData).getTime() - vp_a((TimeData) time).getTime()) / 1000;
            r5 = time;
        } catch (ParseException unused) {
            time.printStackTrace();
        }
        return (int) Math.abs((long) r5);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        ISportModelOriginListener iSportModelOriginListener = (ISportModelOriginListener) iListener;
        this.vp_i = iSportModelOriginListener;
        if (iSportModelOriginListener != null) {
            this.vp_f.add(bArr);
            handler(bArr);
        }
    }

    private SportModelOriginItemData vp_a(byte[] bArr, TimeData timeData, int i, int i2) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i3 = iArrByte2HexToIntArr[6];
        int i4 = iArrByte2HexToIntArr[15];
        String str = strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1];
        String str2 = strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3];
        String str3 = strArrByte2HexToStrArr[8] + strArrByte2HexToStrArr[7];
        String str4 = strArrByte2HexToStrArr[10] + strArrByte2HexToStrArr[9];
        String str5 = strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[11];
        String str6 = strArrByte2HexToStrArr[14] + strArrByte2HexToStrArr[13];
        int iIntValue = Integer.valueOf(str, 16).intValue() - i2;
        int iIntValue2 = Integer.valueOf(str2, 16).intValue() - i2;
        int iIntValue3 = Integer.valueOf(str3, 16).intValue();
        return new SportModelOriginItemData(timeData.toDatabaseDateString(), timeData, iIntValue, iIntValue2, i3, Integer.valueOf(str4, 16).intValue(), iIntValue3, Integer.valueOf(str6, 16).intValue(), Integer.valueOf(str5, 16).intValue(), i4, i);
    }

    private SportModelOriginItemData vp_a(SportModelGPSWatchOriginHeadData sportModelGPSWatchOriginHeadData, byte[] bArr, int i, int i2, int i3) {
        boolean zIsHaveAltitude = sportModelGPSWatchOriginHeadData.isHaveAltitude();
        int length = (bArr.length - 16) / 11;
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
        int i4 = iArrVp_e[2];
        new StringBuilder().append("Heart Rate：").append(i4);
        int iVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr[6], bArr[5]);
        int iVp_a2 = com.veepoo.protocol.util.vp_g.vp_a(bArr[8], bArr[7]);
        int iVp_a3 = com.veepoo.protocol.util.vp_g.vp_a(bArr[10], bArr[9]);
        int iVp_a4 = com.veepoo.protocol.util.vp_g.vp_a(bArr[12], bArr[11]);
        int i5 = 0;
        if (zIsHaveAltitude) {
            String str = strArrVp_f[16] + strArrVp_f[15] + strArrVp_f[14] + strArrVp_f[13];
            int iIntValue = Long.valueOf(str, 16).intValue();
            new StringBuilder().append("-paresA2Content-: 海拔| index = ").append(i).append(" ").append(str).append(" ---> ").append(iIntValue);
            i5 = iIntValue;
        }
        new StringBuilder().append("计步：").append(iVp_a);
        new StringBuilder().append("距离：").append(iVp_a2);
        new StringBuilder().append("卡路里：").append(iVp_a3);
        new StringBuilder().append("运动量：").append(iVp_a4);
        new StringBuilder().append("海拔：").append(i5);
        int i6 = iArrVp_e[15];
        new StringBuilder().append("暂停标识：").append(i6);
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        int i7 = zIsHaveAltitude ? 22 : 18;
        for (int i8 = 0; i8 < length; i8++) {
            int i9 = (i8 * 11) + i7;
            byte b = bArr[i9];
            int i10 = b & 3;
            int i11 = b >> 2;
            iArr[i8] = i10;
            iArr2[i8] = i11;
            new StringBuilder().append("===================>解析GPS -> isHaveAltitude = ").append(zIsHaveAltitude).append(" , ").append(i9);
            String str2 = strArrVp_f[i9 + 4] + strArrVp_f[i9 + 3] + strArrVp_f[i9 + 2] + strArrVp_f[i9 + 1];
            String str3 = strArrVp_f[i9 + 8] + strArrVp_f[i9 + 7] + strArrVp_f[i9 + 6] + strArrVp_f[i9 + 5];
            int iIntValue2 = Long.valueOf(str2, 16).intValue();
            int iIntValue3 = Long.valueOf(str3, 16).intValue();
            dArr[i8] = Double.parseDouble(this.vp_m.format(iIntValue2 / 1000000.0f));
            dArr2[i8] = Double.parseDouble(this.vp_m.format(iIntValue3 / 1000000.0f));
            new StringBuilder().append("信号强度:").append(vp_b(i10)).append(",状态:").append(vp_c(i11)).append(",坐标 [").append(dArr[i8]).append(",").append(dArr2[i8]).append(" ]");
        }
        SportModelOriginItemData sportModelOriginItemData = new SportModelOriginItemData(sportModelGPSWatchOriginHeadData.getStartTime().toDatabaseDateString(), sportModelGPSWatchOriginHeadData.getStartTime(), i, i2, i4, iVp_a, iVp_a4, iVp_a2, iVp_a3, i6, sportModelGPSWatchOriginHeadData.getCrc());
        sportModelOriginItemData.setLatArray(dArr2);
        sportModelOriginItemData.setLngArray(dArr);
        sportModelOriginItemData.setSignalArray(iArr);
        sportModelOriginItemData.setSportStateArray(iArr2);
        sportModelOriginItemData.setHasGpsInfo(true);
        sportModelOriginItemData.setA7SportModel(i3);
        sportModelOriginItemData.setAltitude(i5);
        return sportModelOriginItemData;
    }

    private static void vp_a(byte b) {
        new StringBuilder().append("===================").append(com.veepoo.protocol.util.vp_g.vp_d(new byte[]{b})).append("===================");
    }
}
