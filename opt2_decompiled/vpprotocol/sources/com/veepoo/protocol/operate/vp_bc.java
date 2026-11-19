package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.IHealthRemindListener;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.HealthRemind;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.HealthRemindType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bc.class */
public final class vp_bc extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IHealthRemindListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bc$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final byte[] vp_a(HealthRemindType healthRemindType) {
        byte[] bArr = new byte[20];
        bArr[0] = -25;
        bArr[1] = 2;
        bArr[2] = healthRemindType.getFlag();
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(HealthRemind healthRemind) {
        byte[] bArr = new byte[20];
        bArr[0] = -25;
        bArr[1] = 1;
        bArr[2] = healthRemind.getRemindType().getFlag();
        bArr[3] = (byte) healthRemind.getStartTime().hour;
        bArr[4] = (byte) healthRemind.getStartTime().minute;
        bArr[5] = (byte) healthRemind.getEndTime().hour;
        bArr[6] = (byte) healthRemind.getEndTime().minute;
        bArr[7] = (byte) healthRemind.getInterval();
        bArr[8] = healthRemind.getStatus();
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        Intrinsics.checkNotNull(iListener, "null cannot be cast to non-null type com.veepoo.protocol.listener.IHealthRemindListener");
        IHealthRemindListener iHealthRemindListener = (IHealthRemindListener) iListener;
        this.vp_c = iHealthRemindListener;
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 0) {
                if (iHealthRemindListener != null) {
                    iHealthRemindListener.functionNotSupport();
                    return;
                }
                return;
            }
            if (b == 2) {
                byte b2 = bArr[2];
                if (b2 == 0) {
                    com.veepoo.protocol.util.vp_w.vp_b("-健康提醒-", "读取健康提醒失败");
                    IHealthRemindListener iHealthRemindListener2 = this.vp_c;
                    if (iHealthRemindListener2 != null) {
                        iHealthRemindListener2.onHealthRemindReadFailed();
                        return;
                    }
                    return;
                }
                if (b2 == 1) {
                    HealthRemindType healthRemindTypeByFlag = HealthRemindType.INSTANCE.getHealthRemindTypeByFlag(bArr[3]);
                    TimeData timeData = new TimeData(bArr[4], bArr[5]);
                    TimeData timeData2 = new TimeData(bArr[6], bArr[7]);
                    int i = bArr[8] & 255;
                    boolean z = bArr[9] == 1;
                    boolean z2 = bArr[10] == bArr[11];
                    HealthRemind healthRemind = new HealthRemind(healthRemindTypeByFlag, timeData, timeData2, i, z);
                    com.veepoo.protocol.util.vp_w.vp_a("-健康提醒-", "收到健康提醒读取：" + healthRemind + " 是否读取完成：" + z2);
                    IHealthRemindListener iHealthRemindListener3 = this.vp_c;
                    if (iHealthRemindListener3 != null) {
                        boolean z3 = z2;
                        iHealthRemindListener3.onHealthRemindRead(healthRemind);
                        if (z3) {
                            iHealthRemindListener3.onHealthRemindReadingComplete();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (b == 1) {
                byte b3 = bArr[2];
                if (b3 == 0) {
                    HealthRemindType healthRemindTypeByFlag2 = HealthRemindType.INSTANCE.getHealthRemindTypeByFlag(bArr[3]);
                    com.veepoo.protocol.util.vp_w.vp_b("-健康提醒-", "设置健康提醒失败:" + healthRemindTypeByFlag2);
                    IHealthRemindListener iHealthRemindListener4 = this.vp_c;
                    if (iHealthRemindListener4 != null) {
                        iHealthRemindListener4.onHealthRemindSettingFailed(healthRemindTypeByFlag2);
                        return;
                    }
                    return;
                }
                if (b3 == 1) {
                    HealthRemind healthRemind2 = new HealthRemind(HealthRemindType.INSTANCE.getHealthRemindTypeByFlag(bArr[3]), new TimeData(bArr[4], bArr[5]), new TimeData(bArr[6], bArr[7]), bArr[8] & 255, bArr[9] == 1);
                    com.veepoo.protocol.util.vp_w.vp_a("-健康提醒-", "设置健康提醒成功：" + healthRemind2);
                    IHealthRemindListener iHealthRemindListener5 = this.vp_c;
                    if (iHealthRemindListener5 != null) {
                        iHealthRemindListener5.onHealthRemindSettingSuccess(healthRemind2);
                        return;
                    }
                    return;
                }
                return;
            }
            if (b == 3) {
                byte b4 = bArr[2];
                if (b4 == 0) {
                    com.veepoo.protocol.util.vp_w.vp_b("-健康提醒-", "上报健康提醒失败");
                    IHealthRemindListener iHealthRemindListener6 = this.vp_c;
                    if (iHealthRemindListener6 != null) {
                        iHealthRemindListener6.onHealthRemindReportFailed();
                        return;
                    }
                    return;
                }
                if (b4 == 1) {
                    HealthRemind healthRemind3 = new HealthRemind(HealthRemindType.INSTANCE.getHealthRemindTypeByFlag(bArr[3]), new TimeData(bArr[4], bArr[5]), new TimeData(bArr[6], bArr[7]), bArr[8] & 255, bArr[9] == 1);
                    com.veepoo.protocol.util.vp_w.vp_a("-健康提醒-", "收到健康提醒上报：" + healthRemind3);
                    IHealthRemindListener iHealthRemindListener7 = this.vp_c;
                    if (iHealthRemindListener7 != null) {
                        iHealthRemindListener7.onHealthRemindReport(healthRemind3);
                    }
                }
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(HealthRemindType healthRemindType, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(healthRemindType, "type");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(healthRemindType, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(healthRemindType);
        com.veepoo.protocol.util.vp_w.vp_a("-健康提醒-", "发送读取健康提醒命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(HealthRemind healthRemind, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(healthRemind, "healthRemind");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_a(healthRemind, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(healthRemind);
        com.veepoo.protocol.util.vp_w.vp_a("-健康提醒-", "发送设置健康提醒命令：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
