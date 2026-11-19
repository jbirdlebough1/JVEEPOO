package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ISleepDataListener;
import com.veepoo.protocol.model.settings.ReadSleepSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cl.class */
public class vp_cl extends com.veepoo.protocol.vp_a {
    private static int vp_m = 3;
    public static boolean vp_n = false;
    BluetoothClient vp_e;
    String vp_f;
    BleWriteResponse vp_g;
    ISleepDataListener vp_h;
    private int vp_j;
    private float vp_k;
    private byte vp_c = 0;
    List vp_d = new ArrayList();
    boolean vp_i = false;
    boolean vp_l = false;

    private void vp_d(int i) {
        com.veepoo.protocol.operate.parse.sleep.vp_a vp_aVar;
        if (this.vp_l) {
            vp_aVar = vp_cVar;
            com.veepoo.protocol.operate.parse.sleep.vp_c vp_cVar = new com.veepoo.protocol.operate.parse.sleep.vp_c(this.vp_h);
        } else {
            vp_aVar = vp_bVar;
            com.veepoo.protocol.operate.parse.sleep.vp_b vp_bVar = new com.veepoo.protocol.operate.parse.sleep.vp_b(this.vp_h);
        }
        vp_aVar.vp_a(i, this.vp_d);
        vp_b(i + 1);
    }

    private void vp_c(int i) {
        vp_a();
        vp_a(this.vp_e, this.vp_f, this.vp_g, new ReadSleepSetting(i, this.vp_i, vp_m));
    }

    private void vp_b() {
        this.vp_h.onSleepProgress(1.0f);
        this.vp_h.onReadSleepComplete();
    }

    public void vp_a() {
        this.vp_j = 0;
        if (this.vp_d.isEmpty()) {
            return;
        }
        this.vp_d.clear();
    }

    private float vp_a(int i, float f, int i2) {
        float f2;
        if (this.vp_i) {
            f2 = 1.0f;
            if (i2 != 0) {
                f2 = 1.0f - (f / i2);
            }
        } else {
            float f3 = 1.0f / vp_m;
            f2 = (f3 * i) + (i2 != 0 ? (1.0f - (f / i2)) * f3 : f3);
        }
        return f2;
    }

    public void vp_b(byte[] bArr) {
        if (bArr.length < 20) {
            return;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[3];
        if (this.vp_j == 0) {
            this.vp_j = i;
        }
        this.vp_k = vp_a(i2, i, this.vp_j);
        this.vp_h.onSleepProgressDetail(i2 + "", i);
        float f = this.vp_k;
        if (f != 1.0f) {
            this.vp_h.onSleepProgress(f);
        }
        this.vp_d.add(bArr);
        if (i == this.vp_c) {
            vp_d(i2);
        }
    }

    private byte[] vp_a(int i) {
        return new byte[]{-32, VpBleByteUtil.loUint16((short) i)};
    }

    private void vp_b(int i) {
        if (i > vp_m - 1 || this.vp_i) {
            vp_b();
        } else {
            if (vp_n) {
                return;
            }
            vp_c(i);
        }
    }

    public void vp_a(byte[] bArr, ISleepDataListener iSleepDataListener) {
        this.vp_h = iSleepDataListener;
        vp_b(bArr);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ReadSleepSetting readSleepSetting) {
        vp_a();
        this.vp_e = bluetoothClient;
        this.vp_f = str;
        this.vp_k = 0.0f;
        this.vp_g = bleWriteResponse;
        this.vp_i = readSleepSetting.isReadOneDay();
        vp_m = readSleepSetting.getWatchDataDay();
        super.send(vp_a(readSleepSetting.getDayInt()), bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(boolean z) {
        this.vp_l = z;
    }
}
