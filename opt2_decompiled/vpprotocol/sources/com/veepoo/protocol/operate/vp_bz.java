package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IPressureDetectListener;
import com.veepoo.protocol.model.enums.PressureDetectState;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bz.class */
public final class vp_bz extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IPressureDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bz$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IPressureDetectListener iPressureDetectListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iPressureDetectListener);
        this.vp_c = iPressureDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 6;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bs(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bs(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 6;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IPressureDetectListener iPressureDetectListener;
        IPressureDetectListener iPressureDetectListener2;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-压力测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 6) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iPressureDetectListener = this.vp_c) == null) {
                    return;
                }
                iPressureDetectListener.onDetectStop();
            }
            switch (bArr[3] & 255) {
                case 0:
                    int i = bArr[4] & 255;
                    int i2 = bArr[5] & 255;
                    IPressureDetectListener iPressureDetectListener3 = this.vp_c;
                    if (iPressureDetectListener3 != null) {
                        iPressureDetectListener3.onDetecting(i);
                    }
                    if (i == 100 && i2 > 0 && (iPressureDetectListener2 = this.vp_c) != null) {
                        iPressureDetectListener2.onDetectSuccess(i2);
                        break;
                    }
                    break;
                case 1:
                case 3:
                    IPressureDetectListener iPressureDetectListener4 = this.vp_c;
                    if (iPressureDetectListener4 != null) {
                        iPressureDetectListener4.onDetectFailed(PressureDetectState.BUSY);
                    }
                    break;
                case 2:
                    IPressureDetectListener iPressureDetectListener5 = this.vp_c;
                    if (iPressureDetectListener5 != null) {
                        iPressureDetectListener5.onDetectFailed(PressureDetectState.LOW_POWER);
                    }
                    break;
                case 4:
                    IPressureDetectListener iPressureDetectListener6 = this.vp_c;
                    if (iPressureDetectListener6 != null) {
                        iPressureDetectListener6.onDetectFailed(PressureDetectState.WEAR_OFF);
                    }
                    break;
            }
        }
    }
}
