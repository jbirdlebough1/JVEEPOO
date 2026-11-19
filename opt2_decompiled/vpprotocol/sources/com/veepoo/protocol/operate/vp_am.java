package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IEmotionDetectListener;
import com.veepoo.protocol.model.enums.EmotionDetectState;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_am.class */
public final class vp_am extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IEmotionDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_am$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IEmotionDetectListener iEmotionDetectListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iEmotionDetectListener, "detectListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, iEmotionDetectListener);
        this.vp_c = iEmotionDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 9;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bn(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_bn(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 9;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IEmotionDetectListener iEmotionDetectListener;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-情绪测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 9) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iEmotionDetectListener = this.vp_c) == null) {
                    return;
                }
                iEmotionDetectListener.onDetectStop();
            }
            switch (bArr[3] & 255) {
                case 0:
                    int i = bArr[4] & 255;
                    int i2 = bArr[5] & 255;
                    IEmotionDetectListener iEmotionDetectListener2 = this.vp_c;
                    if (iEmotionDetectListener2 != null) {
                        iEmotionDetectListener2.onEmotionDetect(i, i2);
                    }
                    break;
                case 1:
                case 3:
                    IEmotionDetectListener iEmotionDetectListener3 = this.vp_c;
                    if (iEmotionDetectListener3 != null) {
                        iEmotionDetectListener3.onDetectFailed(EmotionDetectState.BUSY);
                    }
                    break;
                case 2:
                    IEmotionDetectListener iEmotionDetectListener4 = this.vp_c;
                    if (iEmotionDetectListener4 != null) {
                        iEmotionDetectListener4.onDetectFailed(EmotionDetectState.LOW_POWER);
                    }
                    break;
                case 4:
                    IEmotionDetectListener iEmotionDetectListener5 = this.vp_c;
                    if (iEmotionDetectListener5 != null) {
                        iEmotionDetectListener5.onDetectFailed(EmotionDetectState.WEAR_OFF);
                    }
                    break;
            }
        }
    }
}
