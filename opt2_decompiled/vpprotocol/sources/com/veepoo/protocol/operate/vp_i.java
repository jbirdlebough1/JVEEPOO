package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBloodComponentDetectListener;
import com.veepoo.protocol.listener.data.IBloodComponentOptListener;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.enums.EBloodComponentDetectState;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_i.class */
public final class vp_i extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private IBloodComponentDetectListener vp_c;
    private IBloodComponentOptListener vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_i$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_i$vp_b.class */
    public /* synthetic */ class vp_b {
        public static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EBloodComponentDetectState.values().length];
            try {
                iArr[EBloodComponentDetectState.ENABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EBloodComponentDetectState.DETECTING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EBloodComponentDetectState.LOW_POWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EBloodComponentDetectState.BUSY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[EBloodComponentDetectState.WEAR_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            vp_a = iArr;
        }
    }

    private final BloodComponent vp_a(byte[] bArr, int i) {
        BloodComponent bloodComponent = new BloodComponent(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 31, null);
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[i + 1], bArr[i]);
        int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArr[i + 3], bArr[i + 2]);
        int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArr[i + 5], bArr[i + 4]);
        int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArr[i + 7], bArr[i + 6]);
        int iVp_b5 = com.veepoo.protocol.util.vp_g.vp_b(bArr[i + 9], bArr[i + 8]);
        bloodComponent.setUricAcid(iVp_b / 10.0f);
        bloodComponent.setTCHO(iVp_b2 / 100.0f);
        bloodComponent.setTAG(iVp_b3 / 100.0f);
        bloodComponent.setHDL(iVp_b4 / 100.0f);
        bloodComponent.setLDL(iVp_b5 / 100.0f);
        return bloodComponent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void vp_g(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, Boolean.valueOf(z));
        byte[] bArr = new byte[20];
        bArr[0] = -118;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = z;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bj(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bj(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -118;
        bArr[1] = 1;
        bArr[2] = 2;
        bArr[3] = 0;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_p(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_p(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -118;
        bArr[1] = 2;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    static /* synthetic */ BloodComponent vp_a(vp_i vp_iVar, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 5;
        }
        return vp_iVar.vp_a(bArr, i);
    }

    private final EBloodComponentDetectState vp_a(byte b) {
        EBloodComponentDetectState eBloodComponentDetectState;
        EBloodComponentDetectState[] eBloodComponentDetectStateArrValues = EBloodComponentDetectState.values();
        int length = eBloodComponentDetectStateArrValues.length - 1;
        int i = length;
        if (length >= 0) {
            do {
                int i2 = i;
                i = i2 - 1;
                EBloodComponentDetectState eBloodComponentDetectState2 = eBloodComponentDetectStateArrValues[i2];
                eBloodComponentDetectState = eBloodComponentDetectState2;
                if (eBloodComponentDetectState2.getCode() == b) {
                }
            } while (i >= 0);
            eBloodComponentDetectState = null;
        } else {
            eBloodComponentDetectState = null;
        }
        return eBloodComponentDetectState;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        IBloodComponentDetectListener iBloodComponentDetectListener;
        super.vp_a(bArr, iListener);
        if (bArr == null) {
            return;
        }
        byte b = bArr[1];
        if (b == 1) {
            Intrinsics.checkNotNull(iListener, "null cannot be cast to non-null type com.veepoo.protocol.listener.data.IBloodComponentDetectListener");
            IBloodComponentDetectListener iBloodComponentDetectListener2 = (IBloodComponentDetectListener) iListener;
            this.vp_c = iBloodComponentDetectListener2;
            if (bArr[2] == 2) {
                if (iBloodComponentDetectListener2 != null) {
                    iBloodComponentDetectListener2.onDetectStop();
                    return;
                }
                return;
            }
            EBloodComponentDetectState eBloodComponentDetectStateVp_a = vp_a(bArr[3]);
            switch (eBloodComponentDetectStateVp_a == null ? -1 : vp_b.vp_a[eBloodComponentDetectStateVp_a.ordinal()]) {
                case 1:
                    byte b2 = bArr[4];
                    BloodComponent bloodComponentVp_a = vp_a(this, bArr, 0, 2, (Object) null);
                    IBloodComponentDetectListener iBloodComponentDetectListener3 = this.vp_c;
                    if (iBloodComponentDetectListener3 != null) {
                        iBloodComponentDetectListener3.onDetecting(b2, bloodComponentVp_a);
                    }
                    if (b2 == 100 && (iBloodComponentDetectListener = this.vp_c) != null) {
                        iBloodComponentDetectListener.onDetectComplete(bloodComponentVp_a);
                        break;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    IBloodComponentDetectListener iBloodComponentDetectListener4 = this.vp_c;
                    if (iBloodComponentDetectListener4 != null) {
                        iBloodComponentDetectListener4.onDetectFailed(eBloodComponentDetectStateVp_a);
                    }
                    break;
                default:
                    IBloodComponentDetectListener iBloodComponentDetectListener5 = this.vp_c;
                    if (iBloodComponentDetectListener5 != null) {
                        iBloodComponentDetectListener5.onDetectFailed(EBloodComponentDetectState.UNKNOWN);
                    }
                    break;
            }
            return;
        }
        if (b == 2) {
            Intrinsics.checkNotNull(iListener, "null cannot be cast to non-null type com.veepoo.protocol.listener.data.IBloodComponentOptListener");
            IBloodComponentOptListener iBloodComponentOptListener = (IBloodComponentOptListener) iListener;
            this.vp_d = iBloodComponentOptListener;
            byte b3 = bArr[2];
            byte b4 = bArr[3];
            if (b3 == 2) {
                if (b4 != 1) {
                    if (b4 != 0 || iBloodComponentOptListener == null) {
                        return;
                    }
                    iBloodComponentOptListener.onBloodCompositionReadFailed();
                    return;
                }
                boolean z = bArr[4] == 1;
                BloodComponent bloodComponentVp_a2 = vp_a(bArr, 5);
                IBloodComponentOptListener iBloodComponentOptListener2 = this.vp_d;
                if (iBloodComponentOptListener2 != null) {
                    iBloodComponentOptListener2.onBloodCompositionReadSuccess(z, bloodComponentVp_a2);
                    return;
                }
                return;
            }
            if (b3 == 1) {
                if (b4 != 1) {
                    if (b4 != 0 || iBloodComponentOptListener == null) {
                        return;
                    }
                    iBloodComponentOptListener.onBloodCompositionSettingFailed();
                    return;
                }
                boolean z2 = bArr[4] == 1;
                BloodComponent bloodComponentVp_a3 = vp_a(bArr, 5);
                IBloodComponentOptListener iBloodComponentOptListener3 = this.vp_d;
                if (iBloodComponentOptListener3 != null) {
                    iBloodComponentOptListener3.onBloodCompositionSettingSuccess(z2, bloodComponentVp_a3);
                }
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public /* bridge */ /* synthetic */ void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Boolean bool, BloodComponent bloodComponent) {
        vp_a(bluetoothClient, str, bleWriteResponse, bool.booleanValue(), bloodComponent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, BloodComponent bloodComponent) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, Boolean.valueOf(z), bloodComponent);
        byte[] bArr = new byte[20];
        bArr[0] = -118;
        bArr[1] = 2;
        bArr[2] = 1;
        bArr[3] = z;
        if (bloodComponent != null) {
            int uricAcid = (int) (bloodComponent.getUricAcid() * 10);
            float f = 100;
            int tcho = (int) (bloodComponent.getTCHO() * f);
            int tag = (int) (bloodComponent.getTAG() * f);
            int hdl = (int) (bloodComponent.getHDL() * f);
            int ldl = (int) (bloodComponent.getLDL() * f);
            byte[] bArrVp_c = com.veepoo.protocol.util.vp_g.vp_c(uricAcid);
            Intrinsics.checkNotNullExpressionValue(bArrVp_c, "intToByteArray(...)");
            bArr[4] = bArrVp_c[0];
            bArr[5] = bArrVp_c[1];
            byte[] bArrVp_c2 = com.veepoo.protocol.util.vp_g.vp_c(tcho);
            Intrinsics.checkNotNullExpressionValue(bArrVp_c2, "intToByteArray(...)");
            bArr[6] = bArrVp_c2[0];
            bArr[7] = bArrVp_c2[1];
            byte[] bArrVp_c3 = com.veepoo.protocol.util.vp_g.vp_c(tag);
            Intrinsics.checkNotNullExpressionValue(bArrVp_c3, "intToByteArray(...)");
            bArr[8] = bArrVp_c3[0];
            bArr[9] = bArrVp_c3[1];
            byte[] bArrVp_c4 = com.veepoo.protocol.util.vp_g.vp_c(hdl);
            Intrinsics.checkNotNullExpressionValue(bArrVp_c4, "intToByteArray(...)");
            bArr[10] = bArrVp_c4[0];
            bArr[11] = bArrVp_c4[1];
            byte[] bArrVp_c5 = com.veepoo.protocol.util.vp_g.vp_c(ldl);
            Intrinsics.checkNotNullExpressionValue(bArrVp_c5, "intToByteArray(...)");
            bArr[12] = bArrVp_c5[0];
            bArr[13] = bArrVp_c5[1];
        }
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public /* bridge */ /* synthetic */ void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Boolean bool) {
        vp_g(bluetoothClient, str, bleWriteResponse, bool.booleanValue());
    }
}
