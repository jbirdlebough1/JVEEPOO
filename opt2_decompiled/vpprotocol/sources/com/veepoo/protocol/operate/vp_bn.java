package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IMotorOperateListener;
import com.veepoo.protocol.model.datas.MotorLevelData;
import com.veepoo.protocol.model.enums.EMotorLevel;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bn.class */
public final class vp_bn extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IMotorOperateListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bn$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_bn vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bn$vp_b.class */
    public static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_bn vp_b = new vp_bn();

        private vp_b() {
        }

        public final vp_bn vp_a() {
            return vp_b;
        }
    }

    private final byte[] vp_a(int i, int i2) {
        return new byte[]{-96, 4, (byte) i, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), 0};
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        byte[] bArrVp_a = vp_a((byte) 0, 1, vp_a(1, 0));
        com.veepoo.protocol.util.vp_w.vp_a("-马达-", "读取振动强度: " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr != null && bArr.length >= 5 && bArr[0] == -50 && bArr[1] == 3) {
            com.veepoo.protocol.util.vp_w.vp_b("-马达-", "收到振动强度数据: " + com.veepoo.protocol.util.vp_i.vp_a(bArr));
            byte b = bArr[2];
            boolean z = bArr[3] == 1;
            EMotorLevel eMotorLevelVp_a = vp_a(bArr, 5);
            if (eMotorLevelVp_a == null) {
                com.veepoo.protocol.util.vp_w.vp_b("-马达-", "振动强度内容解析失败");
                return;
            }
            if (b == 0) {
                com.veepoo.protocol.util.vp_w.vp_a("-马达-", "读取振动强度回调: level=" + eMotorLevelVp_a + " ack=" + z);
                IMotorOperateListener iMotorOperateListener = this.vp_c;
                if (iMotorOperateListener != null) {
                    iMotorOperateListener.onMotorLevelRead(new MotorLevelData(eMotorLevelVp_a, z, false));
                    return;
                }
                return;
            }
            if (b == 1) {
                com.veepoo.protocol.util.vp_w.vp_a("-马达-", "设置振动强度回调: level=" + eMotorLevelVp_a + " ack=" + z);
                IMotorOperateListener iMotorOperateListener2 = this.vp_c;
                if (iMotorOperateListener2 != null) {
                    iMotorOperateListener2.onMotorLevelSetting(new MotorLevelData(eMotorLevelVp_a, z, false));
                    return;
                }
                return;
            }
            if (b != 2) {
                com.veepoo.protocol.util.vp_w.vp_b("-马达-", "未知CMD: " + b);
                return;
            }
            com.veepoo.protocol.util.vp_w.vp_a("-马达-", "设备主动上报振动强度: level=" + eMotorLevelVp_a);
            IMotorOperateListener iMotorOperateListener3 = this.vp_c;
            if (iMotorOperateListener3 != null) {
                iMotorOperateListener3.onMotorLevelReport(new MotorLevelData(eMotorLevelVp_a, true, true));
            }
        }
    }

    private final byte[] vp_a(byte b, int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 4];
        bArr2[0] = -50;
        bArr2[1] = 3;
        bArr2[2] = b;
        bArr2[3] = (byte) i;
        ArraysKt.copyInto$default(bArr, bArr2, 4, 0, 0, 12, (Object) null);
        return bArr2;
    }

    private final EMotorLevel vp_a(byte[] bArr, int i) {
        while (i < bArr.length) {
            byte b = bArr[i];
            if (b == -96) {
                int i2 = i + 1;
                if (i2 >= bArr.length) {
                    return null;
                }
                i += (bArr[i2] & 255) + 2;
            } else {
                if (b == -95) {
                    int i3 = i + 2;
                    if (i3 >= bArr.length) {
                        return null;
                    }
                    return EMotorLevel.INSTANCE.fromValue(bArr[i3] & 255);
                }
                i++;
            }
        }
        return null;
    }

    public final void vp_a(IMotorOperateListener iMotorOperateListener) {
        this.vp_c = iMotorOperateListener;
    }

    public final void vp_a(EMotorLevel eMotorLevel, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eMotorLevel, "level");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        byte[] bArrVp_a = vp_a((byte) 1, 1, ArraysKt.plus(vp_a(1, 3), new byte[]{-95, 1, (byte) eMotorLevel.getValue()}));
        com.veepoo.protocol.util.vp_w.vp_a("-马达-", "设置振动强度: level=" + eMotorLevel + " cmd=" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
