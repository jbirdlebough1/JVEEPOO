package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IMagneticTherapyListener;
import com.veepoo.protocol.model.datas.MagneticTherapy;
import com.veepoo.protocol.model.enums.MagneticTherapyAckState;
import com.veepoo.protocol.model.enums.MagneticTherapyType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bk.class */
public final class vp_bk extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IMagneticTherapyListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bk$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final MagneticTherapy vp_b(byte[] bArr) {
        byte b = bArr[5];
        MagneticTherapyType magneticTherapyType = MagneticTherapyType.PULSE_MAGNETIC_THERAPY;
        MagneticTherapyType magneticTherapyType2 = magneticTherapyType;
        if (b != magneticTherapyType.getFlag()) {
            magneticTherapyType2 = MagneticTherapyType.CONVENTIONAL_MAGNETIC_THERAPY;
        }
        return new MagneticTherapy(magneticTherapyType2, bArr[2] != 2, bArr[6], MagneticTherapyAckState.INSTANCE.getAckTypeWithCMD(bArr[4]));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MagneticTherapyType magneticTherapyType, IMagneticTherapyListener iMagneticTherapyListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(magneticTherapyType, "type");
        Intrinsics.checkNotNullParameter(iMagneticTherapyListener, "listener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, magneticTherapyType, iMagneticTherapyListener);
        this.vp_c = iMagneticTherapyListener;
        byte[] bArrVp_a = vp_a(magneticTherapyType);
        com.veepoo.protocol.util.vp_w.vp_a("-磁疗-", "关闭磁疗: " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr != null) {
            if (bArr[1] != 6) {
                com.veepoo.protocol.util.vp_w.vp_d("-磁疗-", "他么的 什么鬼");
                return;
            }
            com.veepoo.protocol.util.vp_w.vp_b("-磁疗-", "收到当前的磁疗：" + com.veepoo.protocol.util.vp_i.vp_a(bArr));
            byte b = bArr[2];
            if (b == 0) {
                com.veepoo.protocol.util.vp_w.vp_b("-磁疗-", "当前设备暂不支持该功能，请确保设备支持磁疗功能");
                IMagneticTherapyListener iMagneticTherapyListener = this.vp_c;
                if (iMagneticTherapyListener != null) {
                    iMagneticTherapyListener.functionNotSupport();
                    return;
                }
                return;
            }
            if (b == 1) {
                MagneticTherapy magneticTherapyVp_b = vp_b(bArr);
                byte b2 = bArr[3];
                if (b2 == 0) {
                    com.veepoo.protocol.util.vp_w.vp_d("-磁疗-", "磁疗打开上报：" + magneticTherapyVp_b);
                    IMagneticTherapyListener iMagneticTherapyListener2 = this.vp_c;
                    if (iMagneticTherapyListener2 != null) {
                        iMagneticTherapyListener2.onMagneticTherapyOpen(vp_b(bArr));
                        return;
                    }
                    return;
                }
                if (b2 == 1) {
                    com.veepoo.protocol.util.vp_w.vp_d("-磁疗-", "磁疗档位变化上报：" + magneticTherapyVp_b);
                    IMagneticTherapyListener iMagneticTherapyListener3 = this.vp_c;
                    if (iMagneticTherapyListener3 != null) {
                        iMagneticTherapyListener3.onMagneticTherapyChange(vp_b(bArr));
                        return;
                    }
                    return;
                }
                return;
            }
            if (b == 2) {
                MagneticTherapy magneticTherapyVp_b2 = vp_b(bArr);
                byte b3 = bArr[3];
                if (b3 == 0) {
                    com.veepoo.protocol.util.vp_w.vp_d("-磁疗-", "磁疗关闭上报：" + magneticTherapyVp_b2);
                    IMagneticTherapyListener iMagneticTherapyListener4 = this.vp_c;
                    if (iMagneticTherapyListener4 != null) {
                        iMagneticTherapyListener4.onMagneticTherapyClose(vp_b(bArr));
                        return;
                    }
                    return;
                }
                if (b3 == 1) {
                    com.veepoo.protocol.util.vp_w.vp_d("-磁疗-", "设备端关闭上报：" + magneticTherapyVp_b2);
                    IMagneticTherapyListener iMagneticTherapyListener5 = this.vp_c;
                    if (iMagneticTherapyListener5 != null) {
                        iMagneticTherapyListener5.onMagneticTherapyChange(vp_b(bArr));
                    }
                }
            }
        }
    }

    private final byte[] vp_a(MagneticTherapyType magneticTherapyType) {
        byte[] bArr = new byte[20];
        bArr[0] = -113;
        bArr[1] = 6;
        bArr[2] = 2;
        bArr[3] = magneticTherapyType.getFlag();
        return bArr;
    }

    private final byte[] vp_a(MagneticTherapy magneticTherapy, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -113;
        bArr[1] = 6;
        bArr[2] = 1;
        bArr[3] = magneticTherapy.getType().getFlag();
        bArr[4] = (byte) i;
        bArr[5] = (byte) magneticTherapy.getLevel();
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        Intrinsics.checkNotNullParameter(iListener, "listener");
        this.vp_c = (IMagneticTherapyListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, MagneticTherapy magneticTherapy, int i, IMagneticTherapyListener iMagneticTherapyListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(magneticTherapy, "magneticTherapy");
        Intrinsics.checkNotNullParameter(iMagneticTherapyListener, "listener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, magneticTherapy, i, iMagneticTherapyListener);
        this.vp_c = iMagneticTherapyListener;
        byte[] bArrVp_a = vp_a(magneticTherapy, i);
        com.veepoo.protocol.util.vp_w.vp_a("-磁疗-", "打开 " + i + "分钟磁疗: " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
