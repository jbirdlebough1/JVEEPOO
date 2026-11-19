package com.veepoo.protocol.util;

import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.vp.cso.hrvreport.JNIChange;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_m.class */
public class vp_m {
    Timer vp_a = new Timer();
    CopyOnWriteArrayList vp_b = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_c = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_d = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_e = new CopyOnWriteArrayList();
    int vp_f = 0;
    JNIChange vp_g;
    IECGDetectListener vp_h;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_m$vp_a.class */
    class vp_a extends TimerTask {
        vp_a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int[] iArrVp_b = vp_m.this.vp_b();
            if (iArrVp_b == null || iArrVp_b.length == 0) {
                return;
            }
            vp_m.this.vp_h.onEcgADCChange(iArrVp_b, null);
        }
    }

    public vp_m(JNIChange jNIChange, IECGDetectListener iECGDetectListener) {
        this.vp_g = jNIChange;
        this.vp_h = iECGDetectListener;
    }

    private void vp_c(CopyOnWriteArrayList copyOnWriteArrayList, CopyOnWriteArrayList copyOnWriteArrayList2) {
        if (copyOnWriteArrayList.size() <= 500) {
            vp_b(copyOnWriteArrayList, copyOnWriteArrayList2);
        } else {
            vp_a(copyOnWriteArrayList, copyOnWriteArrayList2);
        }
    }

    private void vp_a(CopyOnWriteArrayList copyOnWriteArrayList, CopyOnWriteArrayList copyOnWriteArrayList2) {
        int size = copyOnWriteArrayList.size();
        int[] iArr = new int[750];
        int[] iArr2 = new int[750];
        int[] iArr3 = new int[750];
        int i = 0;
        for (int i2 = size - 750; i2 < size; i2++) {
            iArr[i] = ((Integer) copyOnWriteArrayList.get(i2)).intValue();
            iArr2[i] = ((Integer) copyOnWriteArrayList2.get(i2)).intValue();
            i++;
        }
        this.vp_g.ecgFilter(iArr, iArr2, iArr3, 250, 100, 30, 124, 1.0f, 750, 7, 4, 3);
        int size2 = this.vp_e.size();
        int i3 = 0;
        for (int i4 = 250; i4 < 750; i4++) {
            if (i3 < 250) {
                this.vp_e.set((size2 - 250) + i3, Integer.valueOf(iArr3[i4]));
                i3++;
            } else {
                this.vp_e.add(Integer.valueOf(iArr3[i4]));
            }
        }
    }

    private void vp_b(CopyOnWriteArrayList copyOnWriteArrayList, CopyOnWriteArrayList copyOnWriteArrayList2) {
        int[] iArr = new int[500];
        int[] iArr2 = new int[500];
        int[] iArr3 = new int[500];
        for (int i = 0; i < 500; i++) {
            iArr[i] = ((Integer) copyOnWriteArrayList.get(i)).intValue();
            iArr2[i] = ((Integer) copyOnWriteArrayList2.get(i)).intValue();
        }
        this.vp_g.ecgFilter(iArr, iArr2, iArr3, 250, 100, 30, 124, 1.0f, 500, 7, 4, 3);
        for (int i2 = 0; i2 < 500; i2++) {
            this.vp_e.add(Integer.valueOf(iArr3[i2]));
        }
    }

    public int[] vp_d() {
        if (this.vp_e.size() <= 0) {
            return null;
        }
        int[] iArr = new int[this.vp_e.size()];
        for (int i = 0; i < this.vp_e.size(); i++) {
            iArr[i] = ((Integer) this.vp_e.get(i)).intValue();
        }
        return iArr;
    }

    public void vp_f() {
        Timer timer = this.vp_a;
        if (timer != null) {
            timer.cancel();
            this.vp_a = null;
        }
        this.vp_a = new Timer();
    }

    public void vp_e() {
        if (this.vp_h == null) {
            return;
        }
        this.vp_a.schedule(new vp_a(), 2000L, 100L);
    }

    private int[] vp_b() {
        int size = this.vp_e.size();
        int i = this.vp_f;
        int i2 = i;
        if (size < i + 25) {
            return null;
        }
        int[] iArr = new int[25];
        int i3 = i2 + 25;
        while (i2 < i3) {
            iArr[i2 - this.vp_f] = ((Integer) this.vp_e.get(i2)).intValue();
            i2++;
        }
        this.vp_f = i3;
        return iArr;
    }

    public int[] vp_c() {
        if (this.vp_c.size() <= 0) {
            return null;
        }
        int[] iArr = new int[this.vp_c.size()];
        for (int i = 0; i < this.vp_c.size(); i++) {
            iArr[i] = ((Integer) this.vp_c.get(i)).intValue();
        }
        return iArr;
    }

    public void vp_a() {
        this.vp_f = 0;
        if (!this.vp_b.isEmpty()) {
            this.vp_b.clear();
        }
        if (!this.vp_c.isEmpty()) {
            this.vp_c.clear();
        }
        if (!this.vp_d.isEmpty()) {
            this.vp_d.clear();
        }
        if (!this.vp_e.isEmpty()) {
            this.vp_e.clear();
        }
        vp_f();
    }

    public void vp_a(byte[] bArr) {
        BluetoothLog.e("ecg数据, 频率：250赫兹");
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        for (int i : vp_l.vp_a.vp_a(bArr, 1)) {
            this.vp_b.add(Integer.valueOf(i));
            this.vp_d.add(Integer.valueOf(i));
            this.vp_c.add(Integer.valueOf(iArrByte2HexToIntArr[17]));
        }
        if (this.vp_b.size() < 250) {
            return;
        }
        this.vp_b.clear();
        if (this.vp_d.size() < 500) {
            return;
        }
        vp_c(this.vp_d, this.vp_c);
    }
}
