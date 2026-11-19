package com.veepoo.protocol.util;

import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_p.class */
public class vp_p {
    static int vp_l = 512;
    static int vp_m = 25;
    int vp_a;
    int vp_b;
    Timer vp_c;
    CopyOnWriteArrayList vp_d;
    CopyOnWriteArrayList vp_e;
    CopyOnWriteArrayList vp_f;
    int vp_g;
    IECGDetectListener vp_h;
    private int vp_i;
    private final TimerTask vp_j;
    private volatile boolean vp_k;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_p$vp_a.class */
    class vp_a extends TimerTask {
        vp_a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            vp_p vp_pVar = vp_p.this;
            if (vp_pVar.vp_h == null) {
                BluetoothLog.e("-【ECG测量-HZ512】-Error:监听为null");
                return;
            }
            vp_b vp_bVarVp_b = vp_pVar.vp_b();
            if (vp_bVarVp_b != null) {
                int[] iArrVp_a = vp_bVarVp_b.vp_a();
                int[] iArrVp_b = vp_bVarVp_b.vp_b();
                BluetoothLog.e("-【ECG测量-HZ512】-ecgType = " + vp_p.this.vp_i + "-----adc数据截取:" + (iArrVp_a == null ? "null" : iArrVp_a.length + ""));
                if (iArrVp_a == null || iArrVp_a.length == 0) {
                    return;
                }
                vp_p.this.vp_h.onEcgADCChange(iArrVp_a, iArrVp_b);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_p$vp_b.class */
    private class vp_b {
        private int[] vp_a;
        private int[] vp_b;

        public vp_b(int[] iArr, int[] iArr2) {
            this.vp_a = iArr;
            this.vp_b = iArr2;
        }

        public int[] vp_a() {
            return this.vp_a;
        }

        public int[] vp_b() {
            return this.vp_b;
        }
    }

    public vp_p(IECGDetectListener iECGDetectListener) {
        int i = vp_l;
        int i2 = vp_m;
        this.vp_a = i / i2;
        this.vp_b = 1000 / i2;
        this.vp_c = null;
        this.vp_d = new CopyOnWriteArrayList();
        this.vp_e = new CopyOnWriteArrayList();
        this.vp_f = new CopyOnWriteArrayList();
        this.vp_g = 0;
        this.vp_i = 0;
        this.vp_j = new vp_a();
        this.vp_k = false;
        this.vp_h = iECGDetectListener;
        this.vp_i = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getECGType();
    }

    private vp_b vp_b() {
        int size = this.vp_f.size();
        int i = this.vp_g;
        int i2 = this.vp_a;
        int i3 = i + i2;
        if (size < i3 || size <= 0) {
            return null;
        }
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i4 = i; i4 < i3; i4++) {
            iArr[i4 - this.vp_g] = ((Integer) this.vp_f.get(i4)).intValue();
            iArr2[i4 - this.vp_g] = ((Integer) this.vp_e.get(i4)).intValue();
        }
        vp_b vp_bVar = new vp_b(iArr, iArr2);
        this.vp_g = i3;
        return vp_bVar;
    }

    public int[] vp_d() {
        if (this.vp_f.size() <= 0) {
            return null;
        }
        int[] iArr = new int[this.vp_f.size()];
        for (int i = 0; i < this.vp_f.size(); i++) {
            iArr[i] = ((Integer) this.vp_f.get(i)).intValue();
        }
        return iArr;
    }

    public int[] vp_c() {
        if (this.vp_e.size() <= 0) {
            return null;
        }
        int[] iArr = new int[this.vp_e.size()];
        for (int i = 0; i < this.vp_e.size(); i++) {
            iArr[i] = ((Integer) this.vp_e.get(i)).intValue();
        }
        return iArr;
    }

    public void vp_a() {
        this.vp_g = 0;
        if (!this.vp_d.isEmpty()) {
            this.vp_d.clear();
        }
        if (!this.vp_e.isEmpty()) {
            this.vp_e.clear();
        }
        if (!this.vp_f.isEmpty()) {
            this.vp_f.clear();
        }
        vp_f();
    }

    public synchronized void vp_f() {
        if (this.vp_c != null) {
            this.vp_k = false;
            this.vp_c.cancel();
            BluetoothLog.e("-【ECG测量-HZ512】-暂停ADC监听 ====》》》》》》.... ");
            this.vp_c.purge();
            this.vp_c = null;
        }
        this.vp_c = new Timer();
    }

    public synchronized void vp_e() {
        if (this.vp_k) {
            BluetoothLog.e("-【ECG测量-HZ512】--startDrawCurve-ADC监听任务已开启，无需再次开启 ====》》》》》》 ");
            return;
        }
        this.vp_k = true;
        if (this.vp_h == null) {
            BluetoothLog.e("-【ECG测量-HZ512】--startDrawCurve-Error:监听为null");
            return;
        }
        BluetoothLog.e("-【ECG测量-HZ512】--startDrawCurve-开始ADC处理任务 ====》》》》》》");
        BluetoothLog.e("-【ECG测量-HZ512】--startDrawCurve-开始ADC处理任务 ====》》》》》》 start...");
        if (this.vp_c == null) {
            this.vp_c = new Timer();
        }
        this.vp_c.schedule(this.vp_j, 2000L, this.vp_b);
    }

    public void vp_b(byte[] bArr) {
        if (this.vp_i == 0) {
            this.vp_i = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getECGType();
        }
        int iVp_a = vp_a(bArr);
        for (int i : vp_l.vp_a.vp_a(bArr, this.vp_i)) {
            this.vp_d.add(Integer.valueOf(i));
            this.vp_e.add(Integer.valueOf(iVp_a));
        }
        BluetoothLog.e("-【ECG测量-HZ512】-ecg类型 = " + this.vp_i + ", adc缓存池大小:" + this.vp_d.size());
        if (this.vp_d.size() < 512) {
            return;
        }
        this.vp_f.addAll(this.vp_d);
        this.vp_d.clear();
    }

    int vp_a(byte[] bArr) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return Integer.valueOf(strArrByte2HexToStrArr[strArrByte2HexToStrArr.length - 4] + strArrByte2HexToStrArr[strArrByte2HexToStrArr.length - 3], 16).intValue();
    }
}
