package com.veepoo.protocol.operate;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ICustomDataOptListener;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_v.class */
public class vp_v extends com.veepoo.protocol.vp_a {
    private BluetoothClient vp_g;
    private String vp_h;
    private BleWriteResponse vp_i;
    private ICustomDataOptListener vp_j;
    private int vp_k;
    private int vp_l;
    private int vp_m;
    private final Handler vp_c = new Handler(Looper.getMainLooper());
    private final Queue vp_d = new ConcurrentLinkedQueue();
    private boolean vp_e = false;
    private int vp_f = 20;
    private final Runnable vp_n = new vp_a();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_v$vp_a.class */
    class vp_a implements Runnable {
        vp_a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            vp_v.this.vp_b();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_v$vp_b.class */
    class vp_b implements BleWriteResponse {
        vp_b() {
        }

        public void onResponse(int i) {
            BleWriteResponse bleWriteResponse = vp_v.this.vp_i;
            if (bleWriteResponse != null) {
                bleWriteResponse.onResponse(i);
            }
            if (i != 0) {
                vp_v.this.vp_a(6, vp_v.this.vp_a(6) + ",code=" + i);
            }
            vp_v.this.vp_b(i);
        }
    }

    private void vp_c() {
        if (this.vp_e || this.vp_d.isEmpty()) {
            return;
        }
        this.vp_e = true;
        this.vp_c.post(this.vp_n);
    }

    private int vp_b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return 3;
        }
        if (bArr[0] != 4) {
            return 4;
        }
        return bArr.length > this.vp_f ? 5 : 0;
    }

    private String vp_a(int i) {
        switch (i) {
            case 1:
                return "设备未连接";
            case 2:
                return "指令列表为空";
            case 3:
                return "单条指令为空";
            case 4:
                return "指令协议头不是0x04";
            case 5:
                return "指令长度超过单条上限" + this.vp_f + "字节";
            case 6:
                return "指令写入失败";
            default:
                return "未知错误";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    private void vp_b(int i) {
        ?? r0 = i;
        boolean z = false;
        boolean z2 = false;
        synchronized (this) {
            try {
                int i2 = this.vp_l + 1;
                this.vp_l = i2;
                if (r0 != 0) {
                    this.vp_m++;
                }
                int i3 = this.vp_k;
                if (i3 > 0 && i2 >= i3) {
                    z = true;
                    z2 = this.vp_m == 0;
                    this.vp_k = 0;
                    this.vp_l = 0;
                    this.vp_m = 0;
                }
                r0 = z;
            } catch (Throwable unused) {
                throw r0;
            }
        }
        if (r0 == 0 || !z2) {
            return;
        }
        vp_a();
    }

    private void vp_a(int i, String str) {
        new StringBuilder().append("-CustomPassthroughOperate- errorCode=").append(i).append(",").append(str);
        ICustomDataOptListener iCustomDataOptListener = this.vp_j;
        if (iCustomDataOptListener != null) {
            iCustomDataOptListener.onError(i, str);
        }
    }

    private void vp_b() {
        byte[] bArr = (byte[]) this.vp_d.poll();
        if (bArr != null) {
            new StringBuilder().append("-CustomPassthroughOperate- send,value=").append(VpBleByteUtil.byte2HexForShow(bArr));
            super.send(bArr, this.vp_g, this.vp_h, new vp_b());
            this.vp_c.postDelayed(this.vp_n, 30L);
        } else {
            this.vp_e = false;
            if (this.vp_d.isEmpty()) {
                return;
            }
            vp_c();
        }
    }

    private void vp_a() {
        ICustomDataOptListener iCustomDataOptListener = this.vp_j;
        if (iCustomDataOptListener != null) {
            iCustomDataOptListener.onSendComplete();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v46 */
    /* JADX WARN: Type inference failed for: r0v47 */
    /* JADX WARN: Type inference failed for: r0v48 */
    public void vp_a(BluetoothClient bluetoothClient, String str, List list, int i, BleWriteResponse bleWriteResponse, ICustomDataOptListener iCustomDataOptListener) {
        ?? r0;
        this.vp_g = bluetoothClient;
        this.vp_h = str;
        this.vp_f = i;
        this.vp_i = bleWriteResponse;
        this.vp_j = iCustomDataOptListener;
        if (TextUtils.isEmpty(str) || bluetoothClient == null || bluetoothClient.getConnectStatus(str) != 2) {
            vp_a(1, "设备未连接");
            return;
        }
        if (list == null || list.isEmpty()) {
            vp_a(2, "指令列表为空");
            return;
        }
        if (!this.vp_e && this.vp_d.isEmpty()) {
            ?? r1 = this;
            synchronized (r1) {
                try {
                    this.vp_k = 0;
                    this.vp_l = 0;
                    this.vp_m = 0;
                    r1 = this;
                } catch (Throwable th) {
                    throw r1;
                }
            }
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            byte[] bArr = (byte[]) list.get(i2);
            int iVp_b = vp_b(bArr);
            if (iVp_b != 0) {
                String str2 = vp_a(iVp_b) + ",index=" + i2 + ",data=" + VpBleByteUtil.byte2HexForShow(bArr);
                vp_v vp_vVar = this;
                vp_vVar.vp_a(iVp_b, str2);
                r0 = vp_vVar;
            } else {
                this.vp_d.offer(bArr);
                synchronized (this) {
                    this.vp_k++;
                    vp_v vp_vVar2 = this;
                    r0 = vp_vVar2;
                }
            }
            try {
            } catch (Throwable th2) {
                throw r0;
            }
        }
        vp_c();
    }
}
