package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ITemptureDataListener;
import com.veepoo.protocol.model.datas.TemptureData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.settings.ReadOriginSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cx.class */
public class vp_cx extends com.veepoo.protocol.vp_a {
    private static int vp_k = 3;
    BluetoothClient vp_e;
    String vp_f;
    BleWriteResponse vp_g;
    Timer vp_h;
    ITemptureDataListener vp_j;
    List vp_c = Collections.synchronizedList(new ArrayList());
    private boolean vp_d = false;
    final int vp_i = 60000;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cx$vp_a.class */
    class vp_a extends TimerTask {
        int vp_a;

        public vp_a(int i) {
            this.vp_a = i;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            vp_cx.this.vp_d(this.vp_a);
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
        Timer timer = this.vp_h;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.vp_h = timer2;
        timer2.schedule(new vp_a(i), 60000L);
        new StringBuilder().append("温度数据读取处理，添加").append(i).append("天的超时判断");
    }

    private void vp_d(int i) {
        new StringBuilder().append("[tempture data]，[tempture data],").append(i).append("天，读取结束");
        long jCurrentTimeMillis = System.currentTimeMillis();
        List listVp_b = vp_b(this.vp_c);
        new StringBuilder().append("[tempture data]，").append(listVp_b.size()).append(",拷贝时长：").append(System.currentTimeMillis() - jCurrentTimeMillis);
        vp_a(i);
        vp_a(listVp_b);
        new StringBuilder().append("[tempture data],").append(i).append("天，读取结束");
        int i2 = i + 1;
        if (i2 > vp_k - 1 || this.vp_d) {
            vp_a();
            return;
        }
        List list = this.vp_c;
        if (list != null && !list.isEmpty()) {
            this.vp_c.clear();
        }
        vp_e(i2);
    }

    private void vp_a(List list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            TemptureData temptureDataVp_b = vp_b((byte[]) list.get(i));
            if (temptureDataVp_b != null) {
                arrayList.add(temptureDataVp_b);
            }
        }
        this.vp_j.onTemptureDataListDataChange(arrayList);
    }

    private void vp_e(int i) {
        vp_a(this.vp_e, this.vp_f, this.vp_g, new ReadOriginSetting(i, 1, this.vp_d, vp_k));
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        if (bArr.length < 20) {
            return;
        }
        this.vp_c.add(bArr);
        int iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[3]);
        int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt(bArr[6], bArr[5]);
        float fVp_a = vp_a(bArr[2], iTwoByteToUnsignedInt2, iTwoByteToUnsignedInt);
        ITemptureDataListener iTemptureDataListener = this.vp_j;
        if (iTemptureDataListener != null) {
            iTemptureDataListener.onReadOriginProgressDetail(bArr[2], "", iTwoByteToUnsignedInt, iTwoByteToUnsignedInt2);
            this.vp_j.onReadOriginProgress(fVp_a);
        }
        byte b = bArr[3];
        byte b2 = bArr[5];
        if (b == b2 && bArr[4] == bArr[6]) {
            vp_d(VpBleByteUtil.byte2HexToIntArr(bArr)[2]);
            return;
        }
        if ((b == bArr[4] && b == 0) || (b2 == bArr[6] && b2 == 0)) {
            int i = VpBleByteUtil.byte2HexToIntArr(bArr)[2];
            vp_d(i);
            new StringBuilder().append("5分钟温度[Body Temperature]数据,").append(i).append("天，为空读取结束||只有一包读取结束");
        }
    }

    private void vp_a(int i) {
        new StringBuilder().append("[tempture data]，取消").append(i).append("天的超时判断");
        Timer timer = this.vp_h;
        if (timer != null) {
            timer.cancel();
        }
    }

    public List vp_b(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((byte[]) ((byte[]) it.next()).clone());
        }
        return arrayList;
    }

    private TemptureData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int i = iArrByte2HexToIntArr[2];
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[3], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[5], 16).intValue();
        int i2 = iArrByte2HexToIntArr[7];
        int i3 = iArrByte2HexToIntArr[8];
        int i4 = iArrByte2HexToIntArr[9];
        int i5 = iArrByte2HexToIntArr[10];
        Calendar calendar = Calendar.getInstance();
        TimeData timeData = new TimeData();
        timeData.setYear(calendar.get(1));
        timeData.setMonth(i2);
        timeData.setDay(i3);
        timeData.setHour(i4);
        timeData.setMinute(i5);
        if (timeData.isLeadTime()) {
            return null;
        }
        int iIntValue3 = Integer.valueOf(strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[11], 16).intValue();
        int iIntValue4 = Integer.valueOf(strArrByte2HexToStrArr[14] + strArrByte2HexToStrArr[13], 16).intValue();
        float fVp_b = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iIntValue3 * 1.0f) / 10.0f), 1);
        float fVp_b2 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iIntValue4 * 1.0f) / 10.0f), 1);
        if (!vp_a(fVp_b) || i4 >= 24 || i5 >= 60 || iIntValue == 0) {
            return null;
        }
        TemptureData temptureData = new TemptureData(iIntValue, iIntValue2, timeData, false, fVp_b, fVp_b2);
        new StringBuilder().append("[tempture data]数据,细节").append(i).append("天：").append(temptureData.toString());
        return temptureData;
    }

    private void vp_a() {
        Timer timer = this.vp_h;
        if (timer != null) {
            timer.cancel();
        }
        ITemptureDataListener iTemptureDataListener = this.vp_j;
        if (iTemptureDataListener != null) {
            iTemptureDataListener.onReadOriginProgress(1.0f);
            this.vp_j.onReadOriginComplete();
        }
    }

    private float vp_a(int i, float f, int i2) {
        float f2;
        if (this.vp_d) {
            f2 = 1.0f;
            if (i2 != 0 && f != 0.0f) {
                f2 = f / i2;
            }
        } else {
            float f3 = 1.0f / vp_k;
            f2 = (f3 * i) + ((i2 == 0 || f == 0.0f) ? f3 : (f / i2) * f3);
        }
        return f2;
    }

    private byte[] vp_a(ReadOriginSetting readOriginSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = -120;
        bArr[1] = 1;
        bArr[2] = VpBleByteUtil.loUint16((short) readOriginSetting.getDay());
        bArr[3] = VpBleByteUtil.loUint16((short) readOriginSetting.getPosition());
        bArr[4] = VpBleByteUtil.hiUint16((short) readOriginSetting.getPosition());
        return bArr;
    }

    public void vp_a(byte[] bArr, ITemptureDataListener iTemptureDataListener) {
        this.vp_j = iTemptureDataListener;
        handler(bArr);
    }

    public boolean vp_a(float f) {
        return f >= 12.0f && f <= 48.0f;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadOriginSetting readOriginSetting) {
        this.vp_e = bluetoothClient;
        this.vp_f = str;
        this.vp_g = bleWriteResponse;
        int day = readOriginSetting.getDay();
        this.vp_d = readOriginSetting.isReadOneDay();
        this.vp_d = readOriginSetting.isReadOneDay();
        vp_k = readOriginSetting.getWatchday();
        new StringBuilder().append("温度数据读取处理，ORIGIN_DATA_DAY:").append(vp_k);
        vp_b(day);
        byte[] bArrVp_a = vp_a(readOriginSetting);
        new StringBuilder().append("温度数据读取处理：").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
