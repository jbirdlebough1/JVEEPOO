package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IMetDetectListener;
import com.veepoo.protocol.model.enums.MetDetectState;
import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bl.class */
public final class vp_bl extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IMetDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bl$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IMetDetectListener iMetDetectListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iMetDetectListener, "detectListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, iMetDetectListener);
        this.vp_c = iMetDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 8;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bq(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_bq(bluetoothClient, str, bleWriteResponse);
        this.vp_c = this.vp_c;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 8;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IMetDetectListener iMetDetectListener;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-Met测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 8) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iMetDetectListener = this.vp_c) == null) {
                    return;
                }
                iMetDetectListener.onDetectStop();
            }
            switch (bArr[3] & 255) {
                case 0:
                    int i = bArr[4] & 255;
                    float fFloatValue = new BigDecimal(String.valueOf(((double) (bArr[5] & 255)) / 10.0d)).setScale(1, RoundingMode.HALF_UP).floatValue();
                    IMetDetectListener iMetDetectListener2 = this.vp_c;
                    if (iMetDetectListener2 != null) {
                        iMetDetectListener2.onMetDetect(i, fFloatValue);
                    }
                    break;
                case 1:
                case 3:
                    IMetDetectListener iMetDetectListener3 = this.vp_c;
                    if (iMetDetectListener3 != null) {
                        iMetDetectListener3.onDetectFailed(MetDetectState.BUSY);
                    }
                    break;
                case 2:
                    IMetDetectListener iMetDetectListener4 = this.vp_c;
                    if (iMetDetectListener4 != null) {
                        iMetDetectListener4.onDetectFailed(MetDetectState.LOW_POWER);
                    }
                    break;
                case 4:
                    IMetDetectListener iMetDetectListener5 = this.vp_c;
                    if (iMetDetectListener5 != null) {
                        iMetDetectListener5.onDetectFailed(MetDetectState.WEAR_OFF);
                    }
                    break;
            }
        }
    }
}
