package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ITextImageTransferListener;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_da.class */
public final class vp_da extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_g = new vp_a(null);
    public ITextImageTransferListener vp_c;
    public BluetoothClient vp_d;
    public String vp_e;
    private boolean vp_f;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_da$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_da vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_da$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_da vp_b = new vp_da(null);

        private vp_b() {
        }

        public final vp_da vp_a() {
            return vp_b;
        }
    }

    private vp_da() {
    }

    private final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArr = new byte[20];
        bArr[0] = -125;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -95;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = 0;
        com.veepoo.protocol.util.vp_w.vp_a("-文本图片传输-", "终止图文传输-回复：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public /* synthetic */ vp_da(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final ITextImageTransferListener vp_c() {
        ITextImageTransferListener iTextImageTransferListener = this.vp_c;
        if (iTextImageTransferListener != null) {
            return iTextImageTransferListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("textImageTransferListener");
        return null;
    }

    public final BluetoothClient vp_a() {
        BluetoothClient bluetoothClient = this.vp_d;
        if (bluetoothClient != null) {
            return bluetoothClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException("client");
        return null;
    }

    public final String vp_b() {
        String str = this.vp_e;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mac");
        return null;
    }

    public final boolean vp_d() {
        return this.vp_f;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-文本图片传输-", "收到图文传输->：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr != null) {
            byte b = bArr[3];
            if (b != -96) {
                if (b == -95) {
                    if (bArr[6] == 1) {
                        vp_cd(vp_a(), vp_b(), (v1) -> {
                            vp_a(r1, v1);
                        });
                        return;
                    } else {
                        this.vp_f = false;
                        vp_c().onTextImageTransferStopSuccess();
                        return;
                    }
                }
                return;
            }
            boolean z = bArr[7] == 0;
            byte b2 = bArr[6];
            if (b2 == 0) {
                this.vp_f = true;
                vp_c().onTextImageTransferStartSuccess(z);
            } else if (b2 == 1) {
                this.vp_f = false;
                vp_c().onTextImageTransferComplete(z);
            } else {
                this.vp_f = false;
                vp_c().onTextImageTransferStartFailed(z);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(boolean z) {
        byte[] bArr = new byte[20];
        bArr[0] = -125;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -96;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = !z;
        return bArr;
    }

    public final void vp_b(boolean z) {
        this.vp_f = z;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
        Intrinsics.checkNotNullParameter(iTextImageTransferListener, "listener");
        super.vp_c(bluetoothClient, str, bleWriteResponse, iTextImageTransferListener);
        vp_a(iTextImageTransferListener);
        byte[] bArrVp_a = vp_a(true);
        com.veepoo.protocol.util.vp_w.vp_a("-文本图片传输-", "开始文本传输：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    private static final void vp_a(vp_da vp_daVar, int i) {
        if (i == 0) {
            vp_daVar.vp_f = false;
            vp_daVar.vp_c().onTextImageTransferStopSuccess();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
        Intrinsics.checkNotNullParameter(iTextImageTransferListener, "listener");
        super.vp_b(bluetoothClient, str, bleWriteResponse, iTextImageTransferListener);
        vp_a(iTextImageTransferListener);
        byte[] bArrVp_a = vp_a(false);
        com.veepoo.protocol.util.vp_w.vp_a("-文本图片传输-", "开始图片传输：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(BluetoothClient bluetoothClient, String str) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        vp_a(bluetoothClient);
        vp_a(str);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ITextImageTransferListener iTextImageTransferListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iTextImageTransferListener, "listener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, iTextImageTransferListener);
        vp_a(iTextImageTransferListener);
        byte[] bArr = new byte[20];
        bArr[0] = -125;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -95;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = 1;
        com.veepoo.protocol.util.vp_w.vp_a("-文本图片传输-", "终止图文传输-请求：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(BluetoothClient bluetoothClient) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "<set-?>");
        this.vp_d = bluetoothClient;
    }

    public final void vp_a(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vp_e = str;
    }

    public final void vp_a(ITextImageTransferListener iTextImageTransferListener) {
        Intrinsics.checkNotNullParameter(iTextImageTransferListener, "<set-?>");
        this.vp_c = iTextImageTransferListener;
    }
}
