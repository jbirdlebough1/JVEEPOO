package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGPatchSpo2SwitchListener;
import com.veepoo.protocol.model.enums.EEcgPatchSpo2Switch;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_aj.class */
public final class vp_aj extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IECGPatchSpo2SwitchListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_aj$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a();
        String str2 = "心电贴读取ECGBlood Oxygen (SpO2)测量开关：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 8;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 4) {
            String str = "心电贴ECGBlood Oxygen (SpO2)测量开关回复数据残帧：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] != -23) {
            return;
        }
        byte b = bArr[1];
        if (b == 7) {
            vp_a(bArr, true);
        } else if (b == 8) {
            vp_a(bArr, false);
        }
    }

    public final byte[] vp_a(EEcgPatchSpo2Switch eEcgPatchSpo2Switch) {
        Intrinsics.checkNotNullParameter(eEcgPatchSpo2Switch, "spo2Switch");
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 7;
        bArr[2] = (byte) eEcgPatchSpo2Switch.getValue();
        return bArr;
    }

    private final void vp_a(byte[] bArr, boolean z) {
        byte b = bArr[2];
        EEcgPatchSpo2Switch eEcgPatchSpo2SwitchFromValue = EEcgPatchSpo2Switch.INSTANCE.fromValue(bArr[3] & 255);
        if (b != 1 || eEcgPatchSpo2SwitchFromValue == null) {
            String str = "心电贴ECGBlood Oxygen (SpO2)测量开关" + (z ? "设置" : "读取") + "失败，ACK=" + (bArr.length > 2 ? Integer.toHexString(bArr[2]) : "null");
            IECGPatchSpo2SwitchListener iECGPatchSpo2SwitchListener = this.vp_c;
            if (iECGPatchSpo2SwitchListener != null) {
                iECGPatchSpo2SwitchListener.onECGPatchSpo2SwitchResult(false, EEcgPatchSpo2Switch.NONE);
                return;
            }
            return;
        }
        String str2 = "心电贴ECGBlood Oxygen (SpO2)测量开关" + (z ? "设置" : "读取") + "成功，开关=" + eEcgPatchSpo2SwitchFromValue.getDes();
        IECGPatchSpo2SwitchListener iECGPatchSpo2SwitchListener2 = this.vp_c;
        if (iECGPatchSpo2SwitchListener2 != null) {
            iECGPatchSpo2SwitchListener2.onECGPatchSpo2SwitchResult(true, eEcgPatchSpo2SwitchFromValue);
        }
    }

    public final void vp_a(EEcgPatchSpo2Switch eEcgPatchSpo2Switch, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eEcgPatchSpo2Switch, "spo2Switch");
        byte[] bArrVp_a = vp_a(eEcgPatchSpo2Switch);
        String str2 = "心电贴设置ECGBlood Oxygen (SpO2)测量开关：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IECGPatchSpo2SwitchListener iECGPatchSpo2SwitchListener) {
        this.vp_c = iECGPatchSpo2SwitchListener;
    }
}
