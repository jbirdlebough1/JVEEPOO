package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IGsrDetectListener;
import com.veepoo.protocol.model.datas.GsrDetectResult;
import com.veepoo.protocol.model.enums.DepressionRisk;
import com.veepoo.protocol.model.enums.GsrDetectAck;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ay.class */
public final class vp_ay extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IGsrDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ay$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IGsrDetectListener iGsrDetectListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iGsrDetectListener);
        this.vp_c = iGsrDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 10;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_by(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_by(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 10;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IGsrDetectListener iGsrDetectListener;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-皮电功能-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 10) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iGsrDetectListener = this.vp_c) == null) {
                    return;
                }
                iGsrDetectListener.onGsrDetectStop();
                return;
            }
            int i = bArr[3] & 255;
            if (i != 0) {
                IGsrDetectListener iGsrDetectListener2 = this.vp_c;
                if (iGsrDetectListener2 != null) {
                    iGsrDetectListener2.onGsrDetectFailed(GsrDetectAck.INSTANCE.fromValue(i));
                    return;
                }
                return;
            }
            int i2 = bArr[4] & 255;
            IGsrDetectListener iGsrDetectListener3 = this.vp_c;
            if (iGsrDetectListener3 != null) {
                iGsrDetectListener3.onGsrDetectProgress(i2);
            }
            if (i2 != 100 || bArr.length <= 11) {
                return;
            }
            GsrDetectResult gsrDetectResult = new GsrDetectResult(0, 0, null, 0, 0, 31, null);
            gsrDetectResult.setEmotionLevel(bArr[5] & 255);
            gsrDetectResult.setSkinMoisture(bArr[6] & 255);
            gsrDetectResult.setDepressionRisk(DepressionRisk.INSTANCE.fromValue(bArr[7] & 255));
            gsrDetectResult.setSnsActivation(bArr[8] & 255);
            gsrDetectResult.setCortisolValue(((bArr[10] & 255) << 8) | (bArr[9] & 255));
            IGsrDetectListener iGsrDetectListener4 = this.vp_c;
            if (iGsrDetectListener4 != null) {
                iGsrDetectListener4.onGsrDetectSuccess(gsrDetectResult);
            }
        }
    }
}
