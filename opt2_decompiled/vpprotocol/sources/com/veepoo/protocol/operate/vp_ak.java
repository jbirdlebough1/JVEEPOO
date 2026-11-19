package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGSingleLeadTestDurationListener;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ak.class */
public final class vp_ak extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IECGSingleLeadTestDurationListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ak$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void vp_d(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a(i);
        String str2 = "JH151设置ECG单导测量时长：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a();
        String str2 = "JH151读取ECG单导测量时长：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 10;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 5) {
            String str = "JH151 ECG单导测量时长回复数据残帧：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] != -23) {
            return;
        }
        byte b = bArr[1];
        if (b == 9) {
            vp_a(bArr, true);
        } else if (b == 10) {
            vp_a(bArr, false);
        }
    }

    public final byte[] vp_a(int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 9;
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        return bArr;
    }

    private final void vp_a(byte[] bArr, boolean z) {
        byte b = bArr[2];
        int i = (bArr[3] & 255) | ((bArr[4] & 255) << 8);
        if (b == 1) {
            String str = "JH151 ECG单导测量时长" + (z ? "设置" : "读取") + "成功，时长=" + i + " s";
            IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener = this.vp_c;
            if (iECGSingleLeadTestDurationListener != null) {
                iECGSingleLeadTestDurationListener.onECGSingleLeadTestDurationResult(true, i);
                return;
            }
            return;
        }
        String str2 = "JH151 ECG单导测量时长" + (z ? "设置" : "读取") + "失败，设备返回失败";
        IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener2 = this.vp_c;
        if (iECGSingleLeadTestDurationListener2 != null) {
            iECGSingleLeadTestDurationListener2.onECGSingleLeadTestDurationResult(false, i);
        }
    }

    public final void vp_a(IECGSingleLeadTestDurationListener iECGSingleLeadTestDurationListener) {
        this.vp_c = iECGSingleLeadTestDurationListener;
    }
}
