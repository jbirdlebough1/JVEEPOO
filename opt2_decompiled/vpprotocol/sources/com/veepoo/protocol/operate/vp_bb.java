package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IHealthLightListener;
import com.veepoo.protocol.model.enums.EHealthLightStatus;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bb.class */
public final class vp_bb extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IHealthLightListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bb$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_bb vp_a() {
            return vp_b.vp_a.vp_a();
        }

        public final boolean vp_a(byte[] bArr) {
            byte b;
            Intrinsics.checkNotNullParameter(bArr, "data");
            return ((bArr.length == 0) ^ true) && bArr.length >= 3 && bArr[0] == -23 && ((b = bArr[1]) == 4 || b == 5 || b == 6);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bb$vp_b.class */
    public static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_bb vp_b = new vp_bb();

        private vp_b() {
        }

        public final vp_bb vp_a() {
            return vp_b;
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_y(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_y(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a();
        com.veepoo.protocol.util.vp_w.vp_a("-JM33A健康灯-", "读取健康灯状态>> : " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a() {
        return new byte[]{-23, 5, 0};
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IHealthLightListener iHealthLightListener;
        super.handler(bArr);
        if (bArr != null) {
            boolean z = bArr[2] == 1;
            EHealthLightStatus statusWithCMD = EHealthLightStatus.INSTANCE.getStatusWithCMD(bArr[3]);
            byte b = bArr[1];
            if (b == 5) {
                IHealthLightListener iHealthLightListener2 = this.vp_c;
                if (iHealthLightListener2 != null) {
                    iHealthLightListener2.onHealthLightStatusRead(z, statusWithCMD);
                    return;
                }
                return;
            }
            if (b == 4) {
                IHealthLightListener iHealthLightListener3 = this.vp_c;
                if (iHealthLightListener3 != null) {
                    iHealthLightListener3.onHealthLightStatusSetting(z, statusWithCMD);
                    return;
                }
                return;
            }
            if (b != 6 || (iHealthLightListener = this.vp_c) == null) {
                return;
            }
            iHealthLightListener.onHealthLightStatusReport(statusWithCMD);
        }
    }

    public final byte[] vp_a(EHealthLightStatus eHealthLightStatus) {
        Intrinsics.checkNotNullParameter(eHealthLightStatus, "status");
        return new byte[]{-23, 4, eHealthLightStatus.getTag()};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EHealthLightStatus eHealthLightStatus) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(eHealthLightStatus, "status");
        super.vp_a(bluetoothClient, str, bleWriteResponse, eHealthLightStatus);
        byte[] bArrVp_a = vp_a(eHealthLightStatus);
        com.veepoo.protocol.util.vp_w.vp_a("-JM33A健康灯-", "设置健康灯状态>> " + eHealthLightStatus + " : " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IHealthLightListener iHealthLightListener) {
        this.vp_c = iHealthLightListener;
    }
}
