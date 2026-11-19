package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IHrvDetectListener;
import com.veepoo.protocol.model.enums.HrvDetectState;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_be.class */
public final class vp_be extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IHrvDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_be$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iHrvDetectListener, "detectListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, iHrvDetectListener);
        this.vp_c = iHrvDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 7;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHrvDetectListener iHrvDetectListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iHrvDetectListener, "detectListener");
        super.vp_b(bluetoothClient, str, bleWriteResponse, iHrvDetectListener);
        this.vp_c = iHrvDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 7;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IHrvDetectListener iHrvDetectListener;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-Hrv测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 7) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iHrvDetectListener = this.vp_c) == null) {
                    return;
                }
                iHrvDetectListener.onDetectStop();
            }
            switch (bArr[3] & 255) {
                case 0:
                    int i = bArr[4] & 255;
                    IHrvDetectListener iHrvDetectListener2 = this.vp_c;
                    if (iHrvDetectListener2 != null) {
                        iHrvDetectListener2.onHrvDetect(i);
                    }
                    break;
                case 1:
                case 3:
                    IHrvDetectListener iHrvDetectListener3 = this.vp_c;
                    if (iHrvDetectListener3 != null) {
                        iHrvDetectListener3.onDetectFailed(HrvDetectState.BUSY);
                    }
                    break;
                case 2:
                    IHrvDetectListener iHrvDetectListener4 = this.vp_c;
                    if (iHrvDetectListener4 != null) {
                        iHrvDetectListener4.onDetectFailed(HrvDetectState.LOW_POWER);
                    }
                    break;
                case 4:
                    IHrvDetectListener iHrvDetectListener5 = this.vp_c;
                    if (iHrvDetectListener5 != null) {
                        iHrvDetectListener5.onDetectFailed(HrvDetectState.WEAR_OFF);
                    }
                    break;
            }
        }
    }
}
