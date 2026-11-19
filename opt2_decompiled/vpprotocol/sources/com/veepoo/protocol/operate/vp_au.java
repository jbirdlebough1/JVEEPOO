package com.veepoo.protocol.operate;

import android.os.Handler;
import android.os.Looper;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IG15QRCodeSendListener;
import com.veepoo.protocol.model.G15QRCode;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_au.class */
public final class vp_au extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_f = new vp_a(null);
    private Handler vp_c = new Handler(Looper.getMainLooper());
    public G15QRCode vp_d;
    public IG15QRCodeSendListener vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_au$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final byte[] vp_c() {
        byte[] bArr = new byte[vp_b().getQRCodeCmdByteLength()];
        bArr[0] = -96;
        bArr[1] = 1;
        bArr[2] = (byte) vp_b().getQrType();
        bArr[3] = -95;
        bArr[4] = (byte) vp_b().getQrContentByteLength();
        int length = vp_b().getQrContentBytes().length;
        for (int i = 0; i < length; i++) {
            bArr[i + 5] = vp_b().getQrContentBytes()[i];
        }
        int qrContentByteLength = vp_b().getQrContentByteLength();
        bArr[qrContentByteLength + 5] = -94;
        bArr[qrContentByteLength + 6] = (byte) vp_b().getQrNameByteLength();
        int i2 = qrContentByteLength + 7;
        int length2 = vp_b().getQrNameBytes().length;
        for (int i3 = 0; i3 < length2; i3++) {
            bArr[i2 + i3] = vp_b().getQrNameBytes()[i3];
        }
        int qrNameByteLength = i2 + vp_b().getQrNameByteLength();
        bArr[qrNameByteLength] = -93;
        bArr[qrNameByteLength + 1] = (byte) vp_b().getQrGroupByteLength();
        int i4 = qrNameByteLength + 2;
        int length3 = vp_b().getQrGroupBytes().length;
        for (int i5 = 0; i5 < length3; i5++) {
            bArr[i4 + i5] = vp_b().getQrGroupBytes()[i5];
        }
        int qrGroupByteLength = i4 + vp_b().getQrGroupByteLength();
        bArr[qrGroupByteLength] = -92;
        bArr[qrGroupByteLength + 1] = (byte) vp_b().getQrNumberByteLength();
        int i6 = qrGroupByteLength + 2;
        int length4 = vp_b().getQrNumberBytes().length;
        for (int i7 = 0; i7 < length4; i7++) {
            bArr[i6 + i7] = vp_b().getQrNumberBytes()[i7];
        }
        return bArr;
    }

    private final List vp_d() {
        byte[] bArrVp_c = vp_c();
        int iCoerceAtLeast = RangesKt.coerceAtLeast(20, VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getMtuValue());
        int i = iCoerceAtLeast - 2;
        int qRCodeCmdByteLength = vp_b().getQRCodeCmdByteLength() / i;
        if (vp_b().getQRCodeCmdByteLength() % i != 0) {
            qRCodeCmdByteLength++;
        }
        ArrayList arrayList = new ArrayList(qRCodeCmdByteLength);
        for (int i2 = 0; i2 < qRCodeCmdByteLength; i2++) {
            byte[] bArr = new byte[iCoerceAtLeast];
            bArr[0] = -69;
            bArr[1] = 1;
            int i3 = i2 * i;
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = i3 + i4;
                if (i5 < vp_b().getQRCodeCmdByteLength()) {
                    bArr[i4 + 2] = bArrVp_c[i5];
                } else {
                    bArr[i4 + 2] = 0;
                }
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public final G15QRCode vp_b() {
        G15QRCode g15QRCode = this.vp_d;
        if (g15QRCode != null) {
            return g15QRCode;
        }
        Intrinsics.throwUninitializedPropertyAccessException("qrCode");
        return null;
    }

    public final IG15QRCodeSendListener vp_a() {
        IG15QRCodeSendListener iG15QRCodeSendListener = this.vp_e;
        if (iG15QRCodeSendListener != null) {
            return iG15QRCodeSendListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mG15QRCodeSendListener");
        return null;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        if (iListener != null) {
            vp_a((IG15QRCodeSendListener) iListener);
            if (bArr != null) {
                byte b = bArr[2];
                if (b == 0) {
                    vp_a().onG15QRCodeSendFailed();
                } else if (b == 1) {
                    vp_a().onG15QRCodeSendSuccess();
                }
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, G15QRCode g15QRCode) {
        Intrinsics.checkNotNullParameter(g15QRCode, "g15QRCode");
        super.vp_a(bluetoothClient, str, bleWriteResponse, g15QRCode);
        vp_a(g15QRCode);
        Iterator it = vp_d().iterator();
        while (it.hasNext()) {
            send((byte[]) it.next(), bluetoothClient, str, bleWriteResponse);
        }
    }

    public final void vp_a(IG15QRCodeSendListener iG15QRCodeSendListener) {
        Intrinsics.checkNotNullParameter(iG15QRCodeSendListener, "<set-?>");
        this.vp_e = iG15QRCodeSendListener;
    }

    public final void vp_a(G15QRCode g15QRCode) {
        Intrinsics.checkNotNullParameter(g15QRCode, "<set-?>");
        this.vp_d = g15QRCode;
    }
}
