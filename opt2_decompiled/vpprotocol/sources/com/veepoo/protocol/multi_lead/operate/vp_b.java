package com.veepoo.protocol.multi_lead.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.multi_lead.data.ECGPatchAutoDetectData;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchAutoDetectAck;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchLeadMode;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchMeasureStatus;
import com.veepoo.protocol.multi_lead.enums.EMultiEcgConnectMode;
import com.veepoo.protocol.multi_lead.enums.EMultiEcgTestMode;
import com.veepoo.protocol.multi_lead.listener.IECGPatchAutoDetectListener;
import com.veepoo.protocol.util.vp_i;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_b.class */
public final class vp_b extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IECGPatchAutoDetectListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_b$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 1);
        String str2 = "心电贴进入自动识别测试模式：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 2);
        String str2 = "心电贴退出自动识别测试模式：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 11;
        bArr[2] = b;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 10) {
            String str = "心电贴自动识别模式回复数据残帧：" + vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] == -109 && bArr[1] == 11) {
            EEcgPatchAutoDetectAck eEcgPatchAutoDetectAckFromValue = EEcgPatchAutoDetectAck.INSTANCE.fromValue(bArr[3] & 255);
            EEcgPatchAutoDetectAck eEcgPatchAutoDetectAck = eEcgPatchAutoDetectAckFromValue;
            if (eEcgPatchAutoDetectAckFromValue == null) {
                eEcgPatchAutoDetectAck = EEcgPatchAutoDetectAck.UNSUPPORT;
            }
            EEcgPatchMeasureStatus eEcgPatchMeasureStatusFromValue = EEcgPatchMeasureStatus.INSTANCE.fromValue(bArr[4] & 255);
            EEcgPatchMeasureStatus eEcgPatchMeasureStatus = eEcgPatchMeasureStatusFromValue;
            if (eEcgPatchMeasureStatusFromValue == null) {
                eEcgPatchMeasureStatus = EEcgPatchMeasureStatus.NOT_DETECTED;
            }
            EEcgPatchLeadMode eEcgPatchLeadModeFromValue = EEcgPatchLeadMode.INSTANCE.fromValue(bArr[5] & 255);
            EMultiEcgTestMode eMultiEcgTestModeFromValue = EMultiEcgTestMode.INSTANCE.fromValue(bArr[6] & 255);
            EMultiEcgConnectMode eMultiEcgConnectModeFromValue = EMultiEcgConnectMode.INSTANCE.fromValue(bArr[7] & 255);
            int i = (bArr[8] & 255) | ((bArr[9] & 255) << 8);
            String str2 = "心电贴自动识别模式上报：ack=" + eEcgPatchAutoDetectAck.getDes() + ", leadDetectStatus=" + eEcgPatchMeasureStatus.getDes() + ", leadMode=" + (eEcgPatchLeadModeFromValue != null ? eEcgPatchLeadModeFromValue.getDes() : null) + ", testMode=" + (eMultiEcgTestModeFromValue != null ? eMultiEcgTestModeFromValue.getDes() : null) + ", connectMode=" + (eMultiEcgConnectModeFromValue != null ? eMultiEcgConnectModeFromValue.getDes() : null) + ", duration=" + i + " s";
            ECGPatchAutoDetectData eCGPatchAutoDetectData = new ECGPatchAutoDetectData(eEcgPatchAutoDetectAck, eEcgPatchMeasureStatus, eEcgPatchLeadModeFromValue, eMultiEcgTestModeFromValue, eMultiEcgConnectModeFromValue, i);
            IECGPatchAutoDetectListener iECGPatchAutoDetectListener = this.vp_c;
            if (iECGPatchAutoDetectListener != null) {
                iECGPatchAutoDetectListener.onECGPatchAutoDetectResult(eCGPatchAutoDetectData);
            }
        }
    }

    public final void vp_a(IECGPatchAutoDetectListener iECGPatchAutoDetectListener) {
        this.vp_c = iECGPatchAutoDetectListener;
    }
}
