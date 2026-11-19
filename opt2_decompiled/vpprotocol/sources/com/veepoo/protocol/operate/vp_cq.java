package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISpo2hOriginDataListener;
import com.veepoo.protocol.model.datas.Spo2hOriginData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cq.class */
public class vp_cq extends com.veepoo.protocol.vp_a {
    private static int vp_m = 3;
    BluetoothClient vp_d;
    String vp_e;
    BleWriteResponse vp_f;
    ISpo2hOriginDataListener vp_g;
    Timer vp_k;
    private boolean vp_c = false;
    boolean vp_h = false;
    private final byte vp_i = 0;
    private float vp_j = 0.0f;
    final int vp_l = 35000;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cq$vp_a.class */
    class vp_a extends TimerTask {
        int vp_a;

        public vp_a(int i) {
            this.vp_a = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            vp_cq.this.vp_d(this.vp_a);
        }
    }

    private void vp_b(int i) {
        vp_c(i);
    }

    private void vp_c(int i) {
        Timer timer = this.vp_k;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.vp_k = timer2;
        timer2.schedule(new vp_a(i), 35000L);
    }

    private void vp_a(int i) {
        Timer timer = this.vp_k;
        if (timer != null) {
            timer.cancel();
        }
    }

    private void vp_d(int i) {
        vp_a(i);
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
        vp_a(this.vp_d, this.vp_e, this.vp_f, new ReadOriginSetting(i, 1, this.vp_c, vp_m), this.vp_h);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length < 20) {
            return;
        }
        int i = VpBleByteUtil.byte2HexToIntArr(bArr)[5];
        Spo2hOriginData spo2hOriginDataVp_c = this.vp_h ? vp_c(bArr) : vp_b(bArr);
        Spo2hOriginData spo2hOriginData = spo2hOriginDataVp_c;
        int allPackNumner = spo2hOriginData.getAllPackNumner();
        int currentPackNumber = spo2hOriginData.getCurrentPackNumber();
        this.vp_j = vp_a(i, currentPackNumber, allPackNumner);
        this.vp_g.onReadOriginProgressDetail(i, spo2hOriginDataVp_c.getDate(), allPackNumner, currentPackNumber);
        float f = this.vp_j;
        if (f != 1.0f) {
            this.vp_g.onReadOriginProgress(f);
        }
        if (spo2hOriginDataVp_c.getmTime() != null) {
            this.vp_g.onSpo2hOriginListener(spo2hOriginDataVp_c);
        }
        vp_a(bArr, i);
    }

    private void vp_a() {
        ISpo2hOriginDataListener iSpo2hOriginDataListener = this.vp_g;
        if (iSpo2hOriginDataListener != null) {
            iSpo2hOriginDataListener.onReadOriginProgress(1.0f);
            this.vp_g.onReadOriginComplete();
        }
    }

    private Spo2hOriginData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int i = iArrByte2HexToIntArr[5];
        int i2 = iArrByte2HexToIntArr[6];
        int i3 = iArrByte2HexToIntArr[7];
        int i4 = iArrByte2HexToIntArr[8];
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(com.veepoo.protocol.util.vp_j.vp_a(-i));
        timeDataVp_a.setHour(i2);
        timeDataVp_a.setMinute(i3);
        return (i2 >= 24 || i3 >= 60 || iIntValue2 == 0) ? new Spo2hOriginData() : new Spo2hOriginData(timeDataVp_a.toDatabaseDateString(), timeDataVp_a, i4, iIntValue2, iIntValue);
    }

    private Spo2hOriginData vp_c(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[2] + strArrByte2HexToStrArr[1], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int i = iArrByte2HexToIntArr[5];
        int i2 = iArrByte2HexToIntArr[6];
        int i3 = iArrByte2HexToIntArr[7];
        int i4 = iArrByte2HexToIntArr[8];
        int i5 = iArrByte2HexToIntArr[9];
        int i6 = iArrByte2HexToIntArr[10];
        int i7 = iArrByte2HexToIntArr[11];
        int i8 = iArrByte2HexToIntArr[12];
        int i9 = iArrByte2HexToIntArr[13];
        int i10 = iArrByte2HexToIntArr[14];
        int i11 = iArrByte2HexToIntArr[15];
        int i12 = iArrByte2HexToIntArr[16];
        int i13 = iArrByte2HexToIntArr[17];
        int i14 = iArrByte2HexToIntArr[18];
        int i15 = iArrByte2HexToIntArr[19];
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(com.veepoo.protocol.util.vp_j.vp_a(-i));
        timeDataVp_a.setHour(i2);
        timeDataVp_a.setMinute(i3);
        return (i2 >= 24 || i3 >= 60 || iIntValue2 == 0) ? new Spo2hOriginData() : new Spo2hOriginData(timeDataVp_a.toDatabaseDateString(), timeDataVp_a, i12, i11, i4, i5, i6, i7, i8, i9, i10, i13, i14, i15, iIntValue2, iIntValue);
    }

    public byte[] vp_a(ReadOriginSetting readOriginSetting) {
        byte[] bArrIntToBytes = VpBleByteUtil.intToBytes(readOriginSetting.getPosition());
        return new byte[]{-46, bArrIntToBytes[3], bArrIntToBytes[2], VpBleByteUtil.loUint16((short) readOriginSetting.getDay())};
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
        this.vp_g = (ISpo2hOriginDataListener) iListener;
        handler(bArr);
    }

    private void vp_a(byte[] bArr, int i) {
        byte b = bArr[1];
        byte b2 = bArr[3];
        if ((b == b2 && bArr[2] == bArr[4]) || ((b2 == bArr[4] && b2 == 0) || (b == bArr[2] && b == 0))) {
            vp_d(i);
        }
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadOriginSetting readOriginSetting, boolean z) {
        this.vp_d = bluetoothClient;
        this.vp_e = str;
        this.vp_j = 0.0f;
        this.vp_f = bleWriteResponse;
        int day = readOriginSetting.getDay();
        this.vp_c = readOriginSetting.isReadOneDay();
        this.vp_h = z;
        vp_m = readOriginSetting.getWatchday();
        vp_b(day);
        super.send(vp_a(readOriginSetting), bluetoothClient, str, bleWriteResponse);
    }
}
