package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IYM23ProTestStatusListener;
import com.veepoo.protocol.listener.data.IYM23ProWearReportListener;
import com.veepoo.protocol.model.datas.YM23ProWearReportData;
import com.veepoo.protocol.model.enums.EYM23ProOptStatus;
import com.veepoo.protocol.model.enums.EYM23ProReportType;
import com.veepoo.protocol.model.enums.EYM23ProTestStatus;
import com.veepoo.protocol.model.enums.EYM23ProWearState;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dh.class */
public final class vp_dh extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private IYM23ProWearReportListener vp_c;
    private IYM23ProTestStatusListener vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dh$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void vp_c(byte[] bArr) {
        if (bArr.length < 5) {
            String str = "YM23PRO佩戴状态上报回复数据异常：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        byte b = bArr[3];
        if (b == -96) {
            vp_d(bArr);
            return;
        }
        EYM23ProOptStatus eYM23ProOptStatusFromTag = EYM23ProOptStatus.fromTag(bArr[4]);
        if (b == 1) {
            String str2 = "YM23PRO开启佩戴状态上报应答：" + eYM23ProOptStatusFromTag;
            IYM23ProWearReportListener iYM23ProWearReportListener = this.vp_c;
            if (iYM23ProWearReportListener != null) {
                Intrinsics.checkNotNull(eYM23ProOptStatusFromTag);
                iYM23ProWearReportListener.onYM23ProWearReportOpenResult(eYM23ProOptStatusFromTag);
                return;
            }
            return;
        }
        String str3 = "YM23PRO关闭佩戴状态上报应答：" + eYM23ProOptStatusFromTag;
        IYM23ProWearReportListener iYM23ProWearReportListener2 = this.vp_c;
        if (iYM23ProWearReportListener2 != null) {
            Intrinsics.checkNotNull(eYM23ProOptStatusFromTag);
            iYM23ProWearReportListener2.onYM23ProWearReportCloseResult(eYM23ProOptStatusFromTag);
        }
    }

    private final void vp_d(byte[] bArr) {
        if (bArr.length < 6) {
            String str = "YM23PRO佩戴状态主动上报数据异常：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        EYM23ProReportType eYM23ProReportTypeFromTag = EYM23ProReportType.fromTag(bArr[4]);
        Intrinsics.checkNotNull(eYM23ProReportTypeFromTag);
        YM23ProWearReportData yM23ProWearReportDataVp_a = vp_a(eYM23ProReportTypeFromTag, bArr);
        String str2 = "YM23PRO佩戴状态主动上报：" + yM23ProWearReportDataVp_a;
        IYM23ProWearReportListener iYM23ProWearReportListener = this.vp_c;
        if (iYM23ProWearReportListener != null) {
            iYM23ProWearReportListener.onYM23ProWearReport(yM23ProWearReportDataVp_a);
        }
    }

    public final void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_b = vp_b();
        String str2 = "YM23PRO开启佩戴状态上报：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_b);
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a();
        String str2 = "YM23PRO关闭佩戴状态上报：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_b() {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 6;
        bArr[2] = 1;
        bArr[3] = 1;
        return bArr;
    }

    public final byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 6;
        bArr[2] = 1;
        bArr[3] = 0;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 3) {
            String str = "YM23PRO回复数据残帧：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] == 2 && bArr[1] == 6) {
            byte b = bArr[2];
            if (b == 1) {
                vp_c(bArr);
            } else if (b == 2) {
                vp_b(bArr);
            }
        }
    }

    private final void vp_b(byte[] bArr) {
        if (bArr.length < 6) {
            String str = "YM23PRO测试状态回复数据异常：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        EYM23ProOptStatus eYM23ProOptStatusFromTag = EYM23ProOptStatus.fromTag(bArr[4]);
        EYM23ProTestStatus eYM23ProTestStatusFromTag = EYM23ProTestStatus.fromTag(bArr[5]);
        String str2 = "YM23PRO测试状态应答：ack=" + eYM23ProOptStatusFromTag + " status=" + eYM23ProTestStatusFromTag;
        IYM23ProTestStatusListener iYM23ProTestStatusListener = this.vp_d;
        if (iYM23ProTestStatusListener != null) {
            Intrinsics.checkNotNull(eYM23ProOptStatusFromTag);
            Intrinsics.checkNotNull(eYM23ProTestStatusFromTag);
            iYM23ProTestStatusListener.onYM23ProTestStatusResult(eYM23ProOptStatusFromTag, eYM23ProTestStatusFromTag);
        }
    }

    public final byte[] vp_a(EYM23ProTestStatus eYM23ProTestStatus) {
        Intrinsics.checkNotNullParameter(eYM23ProTestStatus, "testStatus");
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 6;
        bArr[2] = 2;
        bArr[3] = 1;
        bArr[4] = (byte) eYM23ProTestStatus.getTag();
        return bArr;
    }

    private final YM23ProWearReportData vp_a(EYM23ProReportType eYM23ProReportType, byte[] bArr) {
        YM23ProWearReportData yM23ProWearReportData = new YM23ProWearReportData();
        yM23ProWearReportData.setReportType(eYM23ProReportType);
        if (bArr.length > 5) {
            yM23ProWearReportData.setWearState(EYM23ProWearState.fromTag(bArr[5]));
        }
        if (bArr.length > 6) {
            yM23ProWearReportData.setHeartRate(bArr[6] & 255);
        }
        return yM23ProWearReportData;
    }

    public final void vp_a(EYM23ProTestStatus eYM23ProTestStatus, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eYM23ProTestStatus, "testStatus");
        byte[] bArrVp_a = vp_a(eYM23ProTestStatus);
        String str2 = "YM23PRO下发测试状态：" + eYM23ProTestStatus.getDes() + " " + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IYM23ProTestStatusListener iYM23ProTestStatusListener) {
        this.vp_d = iYM23ProTestStatusListener;
    }

    public final void vp_a(IYM23ProWearReportListener iYM23ProWearReportListener) {
        this.vp_c = iYM23ProWearReportListener;
    }
}
