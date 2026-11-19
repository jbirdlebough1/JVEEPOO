package com.veepoo.protocol.util;

import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IECGDetectListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_q.class */
public class vp_q {
    IECGDetectListener vp_e;
    private int vp_f;
    CopyOnWriteArrayList vp_a = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_b = new CopyOnWriteArrayList();
    CopyOnWriteArrayList vp_c = new CopyOnWriteArrayList();
    int vp_d = 0;
    private volatile boolean vp_g = false;

    public vp_q(IECGDetectListener iECGDetectListener) {
        this.vp_f = 0;
        this.vp_e = iECGDetectListener;
        this.vp_f = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getECGType();
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

    public int[] vp_b() {
        if (this.vp_b.size() <= 0) {
            return null;
        }
        int[] iArr = new int[this.vp_b.size()];
        for (int i = 0; i < this.vp_b.size(); i++) {
            iArr[i] = ((Integer) this.vp_b.get(i)).intValue();
        }
        return iArr;
    }

    public void vp_a() {
        this.vp_d = 0;
        if (!this.vp_a.isEmpty()) {
            this.vp_a.clear();
        }
        if (!this.vp_b.isEmpty()) {
            this.vp_b.clear();
        }
        if (this.vp_c.isEmpty()) {
            return;
        }
        this.vp_c.clear();
    }

    public synchronized void vp_d() {
        if (this.vp_g) {
            BluetoothLog.e("-【ECG测量】--startDrawCurve-ADC监听任务已开启，无需再次开启 ====》》》》》》 ");
            return;
        }
        this.vp_g = true;
        if (this.vp_e == null) {
            BluetoothLog.e("-【ECG测量】--startDrawCurve-Error:监听为null");
        } else {
            BluetoothLog.e("-【ECG测量】--startDrawCurve-开始ADC处理任务 ====》》》》》》");
        }
    }

    public void vp_b(byte[] bArr) {
        if (this.vp_f == 0) {
            this.vp_f = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getECGType();
        }
        int iVp_a = vp_a(bArr);
        int[] iArrVp_a = vp_l.vp_a.vp_a(bArr, this.vp_f);
        int[] iArr = new int[iArrVp_a.length];
        for (int i = 0; i < iArrVp_a.length; i++) {
            this.vp_a.add(Integer.valueOf(iArrVp_a[i]));
            this.vp_b.add(Integer.valueOf(iVp_a));
            iArr[i] = iVp_a;
        }
        if (this.vp_a.size() < 1) {
            return;
        }
        this.vp_c.addAll(this.vp_a);
        this.vp_a.clear();
        IECGDetectListener iECGDetectListener = this.vp_e;
        if (iECGDetectListener == null || iArrVp_a.length == 0) {
            return;
        }
        iECGDetectListener.onEcgADCChange(iArrVp_a, iArr);
    }

    int vp_a(byte[] bArr) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return Integer.valueOf(strArrByte2HexToStrArr[strArrByte2HexToStrArr.length - 4] + strArrByte2HexToStrArr[strArrByte2HexToStrArr.length - 3], 16).intValue();
    }
}
