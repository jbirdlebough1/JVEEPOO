package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IJH76SNCodeOptListener;
import com.veepoo.protocol.listener.data.IJH76SNCodeReadListener;
import com.veepoo.protocol.model.datas.JH76SNCodeData;
import com.veepoo.protocol.model.enums.EJH76SNCodeError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bf.class */
public final class vp_bf extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_g = new vp_a(null);
    private IJH76SNCodeReadListener vp_c;
    private IJH76SNCodeOptListener vp_d;
    private byte[] vp_e;
    private int vp_f;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bf$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void vp_c(byte[] bArr) {
        if (bArr.length <= 2 || bArr[2] != 1) {
            IJH76SNCodeOptListener iJH76SNCodeOptListener = this.vp_d;
            if (iJH76SNCodeOptListener != null) {
                iJH76SNCodeOptListener.onJH76SNCodeOptResult(EJH76SNCodeError.DEVICE_FAILED);
                return;
            }
            return;
        }
        IJH76SNCodeOptListener iJH76SNCodeOptListener2 = this.vp_d;
        if (iJH76SNCodeOptListener2 != null) {
            iJH76SNCodeOptListener2.onJH76SNCodeOptResult(EJH76SNCodeError.SUCCESS);
        }
    }

    public final void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_b = vp_b();
        String str2 = "JH76读取SN码：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_b);
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a();
        String str2 = "JH76删除SN码：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_b() {
        return new byte[]{2, 4, 1, 1, 3};
    }

    public final byte[] vp_a() {
        byte[] bArr = new byte[12];
        bArr[0] = -124;
        bArr[1] = 3;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 2) {
            String str = "JH76回复数据残帧：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        byte b = bArr[0];
        if (b == -124 && bArr[1] == 3) {
            vp_c(bArr);
        } else if (b == 2 && bArr[1] == 4 && bArr.length > 4 && bArr[4] == 3) {
            vp_b(bArr);
        }
    }

    private final void vp_b(byte[] bArr) {
        vp_a(bArr, bArr[2] & 255, bArr[3] & 255, com.veepoo.protocol.util.vp_d.vp_a.vp_b(bArr, 5));
    }

    public final void vp_c(String str, BluetoothClient bluetoothClient, String str2, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(str, "snCode");
        byte[] bArrVp_a = vp_a(str);
        String str3 = "JH76设置SN码：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str2, bleWriteResponse);
    }

    public final byte[] vp_a(String str) {
        Intrinsics.checkNotNullParameter(str, "snCode");
        byte[] bArr = new byte[12];
        bArr[0] = -124;
        bArr[1] = 3;
        byte[] bytes = str.getBytes(Charsets.US_ASCII);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        System.arraycopy(bytes, 0, bArr, 2, 10);
        return bArr;
    }

    private final void vp_a(byte[] bArr, int i, int i2, int i3) {
        if (i3 <= 0 && i == 1) {
            JH76SNCodeData jH76SNCodeData = new JH76SNCodeData();
            jH76SNCodeData.setSet(false);
            jH76SNCodeData.setSnCode("");
            IJH76SNCodeReadListener iJH76SNCodeReadListener = this.vp_c;
            if (iJH76SNCodeReadListener != null) {
                iJH76SNCodeReadListener.onJH76SNCodeRead(jH76SNCodeData);
                return;
            }
            return;
        }
        if (i == 1) {
            this.vp_f = 0;
            this.vp_e = new byte[i3];
        }
        byte[] bArr2 = this.vp_e;
        if (bArr2 != null) {
            int i4 = i == 1 ? 7 : 5;
            int length = bArr.length;
            for (int i5 = i4; i5 < length; i5++) {
                int i6 = (this.vp_f + i5) - i4;
                if (i6 < bArr2.length) {
                    bArr2[i6] = bArr[i5];
                }
            }
            this.vp_f += length - i4;
            if (i == i2) {
                String str = "JH76 读取SN码：长度=" + i3 + " -> 数据上报：" + com.veepoo.protocol.util.vp_i.vp_a(bArr2);
                JH76SNCodeData jH76SNCodeData2 = new JH76SNCodeData();
                jH76SNCodeData2.setSet(true);
                jH76SNCodeData2.setSnCode(new String(bArr2, Charsets.US_ASCII));
                IJH76SNCodeReadListener iJH76SNCodeReadListener2 = this.vp_c;
                if (iJH76SNCodeReadListener2 != null) {
                    iJH76SNCodeReadListener2.onJH76SNCodeRead(jH76SNCodeData2);
                }
                this.vp_e = null;
            }
        }
    }

    public final void vp_a(IJH76SNCodeOptListener iJH76SNCodeOptListener) {
        this.vp_d = iJH76SNCodeOptListener;
    }

    public final void vp_a(IJH76SNCodeReadListener iJH76SNCodeReadListener) {
        this.vp_c = iJH76SNCodeReadListener;
    }
}
