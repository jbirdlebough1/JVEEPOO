package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IDeviceRenameListener;
import com.veepoo.protocol.model.enums.ERenameError;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import java.nio.charset.Charset;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cg.class */
public final class vp_cg extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private IDeviceRenameListener vp_c;
    private String vp_d = "";

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cg$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(String str, BluetoothClient bluetoothClient, String str2, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(str, "deviceName");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str2, "mac");
        super.vp_a(str, bluetoothClient, str2, bleWriteResponse);
        byte[] bArrVp_a = vp_a(1, str);
        if (bArrVp_a != null) {
            this.vp_d = str;
            send(vp_a((byte) 1, bArrVp_a), bluetoothClient, str2, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(String str, BluetoothClient bluetoothClient, String str2, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(str, "deviceName");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str2, "mac");
        super.vp_b(str, bluetoothClient, str2, bleWriteResponse);
        byte[] bArrVp_a = vp_a(2, str);
        if (bArrVp_a != null) {
            this.vp_d = str;
            send(vp_a((byte) 2, bArrVp_a), bluetoothClient, str2, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
    }

    private final byte[] vp_a(int i, String str) {
        Charset charsetDefaultCharset = Charset.defaultCharset();
        Intrinsics.checkNotNullExpressionValue(charsetDefaultCharset, "defaultCharset(...)");
        byte[] bytes = str.getBytes(charsetDefaultCharset);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length;
        if (1 <= length && length < 19) {
            return bytes;
        }
        IDeviceRenameListener iDeviceRenameListener = this.vp_c;
        if (iDeviceRenameListener == null) {
            return null;
        }
        iDeviceRenameListener.onDeviceRenameFail(ERenameError.LENGTH_1_18_ERROR, str);
        return null;
    }

    private final byte[] vp_a(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[20];
        bArr2[0] = -124;
        bArr2[1] = b;
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i;
            i++;
            bArr2[i2 + 2] = b2;
        }
        return bArr2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        IDeviceRenameListener iDeviceRenameListener;
        super.vp_a(bArr, iListener);
        if (iListener != null) {
            this.vp_c = (IDeviceRenameListener) iListener;
        }
        if (bArr != null) {
            byte b = bArr[1];
            if (bArr[2] == 1) {
                if (b == 1) {
                    VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceName(this.vp_d);
                }
                IDeviceRenameListener iDeviceRenameListener2 = this.vp_c;
                if (iDeviceRenameListener2 != null) {
                    iDeviceRenameListener2.onDeviceRenameSuccess(this.vp_d);
                }
            }
            if (bArr[2] != 0 || (iDeviceRenameListener = this.vp_c) == null) {
                return;
            }
            iDeviceRenameListener.onDeviceRenameFail(ERenameError.DEVICE_MODIFY_ERROR, this.vp_d);
        }
    }
}
