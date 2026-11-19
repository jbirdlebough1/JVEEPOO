package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IHRVOriginDataListener;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.util.HrvScoreUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_az.class */
public class vp_az extends com.veepoo.protocol.vp_a {
    private static int vp_m = 3;
    BluetoothClient vp_d;
    String vp_e;
    BleWriteResponse vp_f;
    IHRVOriginDataListener vp_g;
    Timer vp_j;
    private boolean vp_c = false;
    private final byte vp_h = 0;
    private float vp_i = 0.0f;
    final int vp_k = 35000;
    List vp_l = new ArrayList();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_az$vp_a.class */
    class vp_a extends TimerTask {
        int vp_a;

        public vp_a(int i) {
            this.vp_a = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            vp_az.this.vp_d(this.vp_a);
        }
    }

    private void vp_b(int i) {
        vp_c(i);
    }

    private void vp_c(int i) {
        Timer timer = this.vp_j;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.vp_j = timer2;
        timer2.schedule(new vp_a(i), 35000L);
    }

    private void vp_a(int i) {
        Timer timer = this.vp_j;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void vp_d(int i) {
        vp_a(i);
        this.vp_l.clear();
        vp_e(i + 1);
    }

    private void vp_e(int i) {
        if (i > vp_m - 1 || this.vp_c) {
            vp_a();
        } else {
            vp_f(i);
        }
    }

    private void vp_f(int i) {
        vp_a(this.vp_d, this.vp_e, this.vp_f, new ReadOriginSetting(i, 1, this.vp_c, vp_m));
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length < 20) {
            return;
        }
        int i = VpBleByteUtil.byte2HexToIntArr(bArr)[5];
        HRVOriginData hRVOriginDataVp_b = vp_b(bArr);
        int allCurrentPackNumber = hRVOriginDataVp_b.getAllCurrentPackNumber();
        int currentPackNumber = hRVOriginDataVp_b.getCurrentPackNumber();
        this.vp_i = vp_a(i, currentPackNumber, allCurrentPackNumber);
        this.vp_g.onReadOriginProgressDetail(i, hRVOriginDataVp_b.getDate(), allCurrentPackNumber, currentPackNumber);
        float f = this.vp_i;
        if (f != 1.0f) {
            this.vp_g.onReadOriginProgress(f);
        }
        if (hRVOriginDataVp_b.getmTime() != null) {
            this.vp_g.onHRVOriginListener(hRVOriginDataVp_b);
            this.vp_l.add(hRVOriginDataVp_b);
        }
        vp_a(bArr, i, hRVOriginDataVp_b);
    }

    private void vp_a() {
        IHRVOriginDataListener iHRVOriginDataListener = this.vp_g;
        if (iHRVOriginDataListener != null) {
            iHRVOriginDataListener.onReadOriginProgress(1.0f);
            this.vp_g.onReadOriginComplete();
        }
    }

    private HRVOriginData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int i = iArrByte2HexToIntArr[5];
        int i2 = iArrByte2HexToIntArr[6];
        int i3 = iArrByte2HexToIntArr[7];
        int i4 = iArrByte2HexToIntArr[8];
        int[] iArr = new int[10];
        for (int i5 = 0; i5 < 10; i5++) {
            iArr[i5] = iArrByte2HexToIntArr[i5 + 9];
        }
        String strIntArrtoString = VpBleByteUtil.intArrtoString(iArr);
        int i6 = iArrByte2HexToIntArr[19];
        String strVp_a = com.veepoo.protocol.util.vp_j.vp_a(-i);
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(strVp_a);
        timeDataVp_a.setHour(i2);
        timeDataVp_a.setMinute(i3);
        return new HRVOriginData(strVp_a, timeDataVp_a, iIntValue2, iIntValue, strIntArrtoString, i4, i6);
    }

    private void vp_a(int i, HRVOriginData hRVOriginData) {
        new HrvScoreUtil();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.vp_l);
        this.vp_g.onDayHrvScore(i, hRVOriginData.getDate(), HrvScoreUtil.getSocre(arrayList));
    }

    public byte[] vp_a(ReadOriginSetting readOriginSetting) {
        return new byte[]{-39, VpBleByteUtil.loUint16((short) readOriginSetting.getPosition()), VpBleByteUtil.hiUint16((short) readOriginSetting.getPosition()), VpBleByteUtil.loUint16((short) readOriginSetting.getDay())};
    }

    private float vp_a(int i, float f, int i2) {
        float f2;
        if (this.vp_c) {
            f2 = 1.0f;
            if (i2 != 0 && f != 0.0f) {
                f2 = f / i2;
            }
        } else {
            float f3 = 1.0f / vp_m;
            f2 = (f3 * i) + ((i2 == 0 || f == 0.0f) ? f3 : (f / i2) * f3);
        }
        return f2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_g = (IHRVOriginDataListener) iListener;
        handler(bArr);
    }

    private void vp_a(byte[] bArr, int i, HRVOriginData hRVOriginData) {
        byte b = bArr[1];
        byte b2 = bArr[3];
        if ((b == b2 && bArr[2] == bArr[4]) || ((b2 == bArr[4] && b2 == 0) || (b == bArr[2] && b == 0))) {
            vp_a(i, hRVOriginData);
            vp_d(i);
        }
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadOriginSetting readOriginSetting) {
        this.vp_d = bluetoothClient;
        this.vp_e = str;
        this.vp_i = 0.0f;
        this.vp_f = bleWriteResponse;
        int day = readOriginSetting.getDay();
        this.vp_c = readOriginSetting.isReadOneDay();
        vp_m = readOriginSetting.getWatchday();
        vp_b(day);
        super.send(vp_a(readOriginSetting), bluetoothClient, str, bleWriteResponse);
    }
}
