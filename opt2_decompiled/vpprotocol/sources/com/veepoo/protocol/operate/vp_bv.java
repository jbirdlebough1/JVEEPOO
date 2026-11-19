package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IOriginDataListener;
import com.veepoo.protocol.listener.data.IOriginProgressListener;
import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.SportUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bv.class */
public class vp_bv extends com.veepoo.protocol.vp_a {
    private static int vp_n = 3;
    BluetoothClient vp_f;
    String vp_g;
    BleWriteResponse vp_h;
    IOriginDataListener vp_i;
    Timer vp_l;
    List vp_c = new ArrayList();
    private boolean vp_d = false;
    private boolean vp_e = false;
    private final byte vp_j = 0;
    private float vp_k = 0.0f;
    final int vp_m = 35000;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bv$vp_a.class */
    class vp_a extends TimerTask {
        int vp_a;

        public vp_a(int i) {
            this.vp_a = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            vp_bv.this.vp_d(this.vp_a);
        }
    }

    private void vp_b(int i) {
        vp_c(i);
        if (this.vp_c.isEmpty()) {
            return;
        }
        this.vp_c.clear();
    }

    private void vp_c(int i) {
        Timer timer = this.vp_l;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.vp_l = timer2;
        timer2.schedule(new vp_a(i), 35000L);
    }

    private int vp_e(int i) {
        return i / 32;
    }

    private int vp_f(int i) {
        return i % 32;
    }

    private void vp_a(int i) {
        Timer timer = this.vp_l;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void vp_d(int i) {
        vp_a(i);
        vp_a(this.vp_c, i);
        vp_g(i + 1);
    }

    private void vp_g(int i) {
        if (i > vp_n - 1 || this.vp_e) {
            vp_a();
        } else {
            vp_h(i);
        }
    }

    private void vp_h(int i) {
        vp_a(this.vp_f, this.vp_g, this.vp_h, new ReadOriginSetting(i, 1, this.vp_e, vp_n));
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length >= 20 && this.vp_i != null) {
            int iVp_e = VpBleByteUtil.byte2HexToIntArr(bArr)[5];
            if (this.vp_d) {
                iVp_e = vp_e(iVp_e);
            }
            OriginData originDataVp_c = this.vp_d ? vp_c(bArr) : vp_b(bArr);
            OriginData originData = originDataVp_c;
            int allPackage = originData.getAllPackage();
            int packageNumber = originData.getPackageNumber();
            this.vp_k = vp_a(iVp_e, packageNumber, allPackage);
            this.vp_i.onReadOriginProgressDetail(iVp_e, originDataVp_c.getDate(), allPackage, packageNumber);
            float f = this.vp_k;
            if (f != 1.0f) {
                this.vp_i.onReadOriginProgress(f);
            }
            if (originDataVp_c.getmTime() != null) {
                this.vp_c.add(originDataVp_c);
                this.vp_i.onOringinFiveMinuteDataChange(originDataVp_c);
            }
            vp_a(bArr, iVp_e);
        }
    }

    private void vp_a() {
        Timer timer = this.vp_l;
        if (timer != null) {
            timer.cancel();
        }
        IOriginDataListener iOriginDataListener = this.vp_i;
        if (iOriginDataListener == null) {
            return;
        }
        iOriginDataListener.onReadOriginProgress(1.0f);
        this.vp_i.onReadOriginComplete();
    }

    /* JADX WARN: Type inference failed for: r5v7, types: [double, int] */
    private OriginData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int i = iArrByte2HexToIntArr[5];
        int i2 = iArrByte2HexToIntArr[7];
        int i3 = iArrByte2HexToIntArr[8];
        int i4 = iArrByte2HexToIntArr[9];
        int i5 = iArrByte2HexToIntArr[14];
        int i6 = iArrByte2HexToIntArr[15];
        int i7 = iArrByte2HexToIntArr[16];
        int iIntValue3 = Integer.valueOf(strArrByte2HexToStrArr[10] + strArrByte2HexToStrArr[11], 16).intValue();
        int iIntValue4 = Integer.valueOf(strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[13], 16).intValue();
        String strVp_c = com.veepoo.protocol.util.vp_j.vp_c();
        if (i == 0) {
            strVp_c = com.veepoo.protocol.util.vp_j.vp_c();
        } else if (i == 1) {
            strVp_c = com.veepoo.protocol.util.vp_j.vp_d();
        } else if (i == 2) {
            strVp_c = com.veepoo.protocol.util.vp_j.vp_a();
        }
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(strVp_c);
        if (i2 != timeDataVp_a.getDay()) {
            OriginData originData = new OriginData();
            originData.setAllPackage(iIntValue2);
            originData.setPackageNumber(iIntValue);
            return originData;
        }
        OriginData originData2 = new OriginData(strVp_c, iIntValue2, iIntValue, new TimeData(timeDataVp_a.getYear(), timeDataVp_a.getMonth(), i2, i3, i4), i5, iIntValue4, iIntValue3, i6, i7);
        int personHeight = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getPersonHeight();
        boolean zIsSupportCalcStepNew = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportCalcStepNew();
        ?? r5 = personHeight;
        originData2.setCalValue(SportUtil.getKcal1(r5, r5, zIsSupportCalcStepNew));
        originData2.setDisValue(SportUtil.getDistance3(iIntValue3, r5));
        if (zIsSupportCalcStepNew) {
            originData2.setCalcType(1);
        } else {
            originData2.setCalcType(0);
        }
        return originData2;
    }

    private OriginData vp_c(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int i = iArrByte2HexToIntArr[5];
        int iVp_e = vp_e(i);
        int iVp_f = vp_f(i);
        int i2 = 0;
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[7], 16).intValue();
        double positionDouble = SportUtil.getPositionDouble(((double) Integer.valueOf(strArrByte2HexToStrArr[8] + strArrByte2HexToStrArr[9], 16).intValue()) / 1000.0d, 3);
        double positionDouble2 = SportUtil.getPositionDouble(((double) iIntValue) / 10.0d, 1);
        int i3 = iArrByte2HexToIntArr[14];
        int i4 = iArrByte2HexToIntArr[15];
        int i5 = iArrByte2HexToIntArr[16];
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue();
        int iIntValue3 = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int iIntValue4 = Integer.valueOf(strArrByte2HexToStrArr[10] + strArrByte2HexToStrArr[11], 16).intValue();
        int iIntValue5 = Integer.valueOf(strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[13], 16).intValue();
        int i6 = -1;
        int i7 = -1;
        if (bArr.length >= 20) {
            i6 = iArrByte2HexToIntArr[17];
            i7 = iArrByte2HexToIntArr[18];
            i2 = iArrByte2HexToIntArr[19];
        }
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(com.veepoo.protocol.util.vp_j.vp_a(-iVp_e));
        timeDataVp_a.setHour(iVp_f);
        timeDataVp_a.setMinute(i2);
        OriginData originData = new OriginData(timeDataVp_a.toDatabaseDateString(), iIntValue3, iIntValue2, timeDataVp_a, i3, iIntValue5, iIntValue4, i4, i5, i6, i7, -1, positionDouble, positionDouble2);
        originData.setCalcType(2);
        return originData;
    }

    public byte[] vp_a(ReadOriginSetting readOriginSetting) {
        byte[] bArrIntToBytes = VpBleByteUtil.intToBytes(readOriginSetting.getPosition());
        return new byte[]{-47, bArrIntToBytes[3], bArrIntToBytes[2], VpBleByteUtil.loUint16((short) readOriginSetting.getDay())};
    }

    private float vp_a(int i, float f, int i2) {
        float f2;
        if (this.vp_e) {
            f2 = 1.0f;
            if (i2 != 0 && f != 0.0f) {
                f2 = f / i2;
            }
        } else {
            float f3 = 1.0f / vp_n;
            f2 = (f3 * i) + ((i2 == 0 || f == 0.0f) ? f3 : (f / i2) * f3);
        }
        return f2;
    }

    public void vp_a(byte[] bArr, IOriginProgressListener iOriginProgressListener) {
        if (iOriginProgressListener instanceof IOriginDataListener) {
            this.vp_i = (IOriginDataListener) iOriginProgressListener;
            handler(bArr);
        }
    }

    private void vp_a(List list, int i) {
        if (list == null || list.isEmpty()) {
            return;
        }
        com.veepoo.protocol.util.vp_t vp_tVar = new com.veepoo.protocol.util.vp_t();
        this.vp_i.onOringinHalfHourDataChange(new OriginHalfHourData(vp_tVar.vp_a(list, i), vp_tVar.vp_c(list, i), vp_tVar.vp_b(list), vp_tVar.vp_a(list)));
    }

    private void vp_a(byte[] bArr, int i) {
        byte b = bArr[1];
        byte b2 = bArr[3];
        if ((b == b2 && bArr[2] == bArr[4]) || ((b2 == bArr[4] && b2 == 0) || (b == bArr[2] && b == 0))) {
            vp_d(i);
        }
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadOriginSetting readOriginSetting) {
        this.vp_f = bluetoothClient;
        this.vp_g = str;
        this.vp_k = 0.0f;
        this.vp_h = bleWriteResponse;
        int day = readOriginSetting.getDay();
        this.vp_e = readOriginSetting.isReadOneDay();
        vp_n = readOriginSetting.getWatchday();
        this.vp_d = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isParseSportModel();
        vp_b(day);
        super.send(vp_a(readOriginSetting), bluetoothClient, str, bleWriteResponse);
    }
}
