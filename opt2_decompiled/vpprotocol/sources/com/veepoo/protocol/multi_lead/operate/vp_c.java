package com.veepoo.protocol.multi_lead.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGPatchMultiLeadSettingListener;
import com.veepoo.protocol.multi_lead.data.ECGPatchMultiLeadSettingData;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchSettingType;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchSwitch;
import com.veepoo.protocol.util.vp_i;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_c.class */
public final class vp_c extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IECGPatchMultiLeadSettingListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_c$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final EEcgPatchSettingType vp_a(byte b) {
            if (b == 11) {
                return EEcgPatchSettingType.SINGLE_MEASURE_DURATION;
            }
            if (b == 12) {
                return EEcgPatchSettingType.DO_NOT_DISTURB;
            }
            if (b == 13) {
                return EEcgPatchSettingType.HEART_ABNORMAL_REMIND;
            }
            if (b == 14) {
                return EEcgPatchSettingType.DISEASE_REMIND;
            }
            if (b == 15) {
                return EEcgPatchSettingType.LEAD_OFF_REMIND;
            }
            return null;
        }
    }

    private static final Unit vp_c(vp_c vp_cVar, byte[] bArr, ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData, int i) {
        Intrinsics.checkNotNullParameter(eCGPatchMultiLeadSettingData, "data");
        eCGPatchMultiLeadSettingData.setEcgSwitch(vp_cVar.vp_a(bArr, i));
        return Unit.INSTANCE;
    }

    private static final Unit vp_d(vp_c vp_cVar, byte[] bArr, ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData, int i) {
        Intrinsics.checkNotNullParameter(eCGPatchMultiLeadSettingData, "data");
        eCGPatchMultiLeadSettingData.setEcgSwitch(vp_cVar.vp_a(bArr, i));
        return Unit.INSTANCE;
    }

    public final void vp_ch(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 2, 0);
        String str2 = "心电贴读取多导联单次测量时长：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_ce(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 2, EEcgPatchSwitch.NONE, 0, 0, 0, 0);
        String str2 = "心电贴读取多导联免打扰：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_cf(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 2, EEcgPatchSwitch.NONE, 0, 0);
        String str2 = "心电贴读取多导联Heart Rate异常提醒：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 2, EEcgPatchSwitch.NONE);
        String str2 = "心电贴读取多导联疾病提醒开关：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(byte b, EEcgPatchSwitch eEcgPatchSwitch) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 14;
        bArr[2] = b;
        bArr[3] = (byte) eEcgPatchSwitch.getValue();
        return bArr;
    }

    public final void vp_cg(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_b = vp_b((byte) 2, EEcgPatchSwitch.NONE);
        String str2 = "心电贴读取多导联导联脱落提醒开关：" + vp_i.vp_a(bArrVp_b);
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_b(byte b, EEcgPatchSwitch eEcgPatchSwitch) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 15;
        bArr[2] = b;
        bArr[3] = (byte) eEcgPatchSwitch.getValue();
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        byte b;
        EEcgPatchSettingType eEcgPatchSettingTypeVp_a;
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        if (bArr.length < 8) {
            String str = "心电贴多导联设置项回复数据残帧：" + vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] == -23 && (eEcgPatchSettingTypeVp_a = vp_d.vp_a((b = bArr[1]))) != null) {
            if (b == 11) {
                vp_a(bArr, eEcgPatchSettingTypeVp_a, 4, (v1, v2) -> {
                    return vp_a(r1, v1, v2);
                });
                return;
            }
            if (b == 12) {
                vp_a(bArr, eEcgPatchSettingTypeVp_a, 4, (v2, v3) -> {
                    return vp_a(r1, r2, v2, v3);
                });
                return;
            }
            if (b == 13) {
                vp_a(bArr, eEcgPatchSettingTypeVp_a, 4, (v2, v3) -> {
                    return vp_b(r1, r2, v2, v3);
                });
            } else if (b == 14) {
                vp_a(bArr, eEcgPatchSettingTypeVp_a, 4, (v2, v3) -> {
                    return vp_c(r1, r2, v2, v3);
                });
            } else if (b == 15) {
                vp_a(bArr, eEcgPatchSettingTypeVp_a, 4, (v2, v3) -> {
                    return vp_d(r1, r2, v2, v3);
                });
            }
        }
    }

    private static final Unit vp_b(vp_c vp_cVar, byte[] bArr, ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData, int i) {
        Intrinsics.checkNotNullParameter(eCGPatchMultiLeadSettingData, "data");
        eCGPatchMultiLeadSettingData.setEcgSwitch(vp_cVar.vp_a(bArr, i));
        eCGPatchMultiLeadSettingData.setMinHeart(bArr[i + 1] & 255);
        eCGPatchMultiLeadSettingData.setMaxHeart(bArr[i + 2] & 255);
        return Unit.INSTANCE;
    }

    public final void vp_d(int i, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a((byte) 1, i);
        String str2 = "心电贴设置多导联单次测量时长：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(byte b, EEcgPatchSwitch eEcgPatchSwitch, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 12;
        bArr[2] = b;
        bArr[3] = (byte) eEcgPatchSwitch.getValue();
        boolean z = eEcgPatchSwitch == EEcgPatchSwitch.OPEN;
        if (!z) {
            i = 0;
        }
        boolean z2 = z;
        bArr[4] = (byte) i;
        if (!z2) {
            i2 = 0;
        }
        boolean z3 = z;
        bArr[5] = (byte) i2;
        if (!z3) {
            i3 = 0;
        }
        boolean z4 = z;
        bArr[6] = (byte) i3;
        if (!z4) {
            i4 = 0;
        }
        bArr[7] = (byte) i4;
        return bArr;
    }

    public final byte[] vp_a(byte b, EEcgPatchSwitch eEcgPatchSwitch, int i, int i2) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 13;
        bArr[2] = b;
        bArr[3] = (byte) eEcgPatchSwitch.getValue();
        bArr[4] = (byte) i;
        bArr[5] = (byte) i2;
        return bArr;
    }

    public final void vp_b(EEcgPatchSwitch eEcgPatchSwitch, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArrVp_b = vp_b((byte) 1, eEcgPatchSwitch);
        String str2 = "心电贴设置多导联导联脱落提醒开关：" + vp_i.vp_a(bArrVp_b);
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(byte b, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -23;
        bArr[1] = 11;
        bArr[2] = b;
        bArr[3] = (byte) (i & 255);
        bArr[4] = (byte) ((i >> 8) & 255);
        return bArr;
    }

    private static final Unit vp_a(byte[] bArr, ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData, int i) {
        Intrinsics.checkNotNullParameter(eCGPatchMultiLeadSettingData, "data");
        eCGPatchMultiLeadSettingData.setDuration((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8));
        return Unit.INSTANCE;
    }

    private static final Unit vp_a(vp_c vp_cVar, byte[] bArr, ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData, int i) {
        Intrinsics.checkNotNullParameter(eCGPatchMultiLeadSettingData, "data");
        eCGPatchMultiLeadSettingData.setEcgSwitch(vp_cVar.vp_a(bArr, i));
        eCGPatchMultiLeadSettingData.setStartHour(bArr[i + 1] & 255);
        eCGPatchMultiLeadSettingData.setStartMinute(bArr[i + 2] & 255);
        eCGPatchMultiLeadSettingData.setEndHour(bArr[i + 3] & 255);
        eCGPatchMultiLeadSettingData.setEndMinute(bArr[i + 4] & 255);
        return Unit.INSTANCE;
    }

    private final void vp_a(byte[] bArr, EEcgPatchSettingType eEcgPatchSettingType, int i, Function2 function2) {
        boolean z = bArr[3] == 1;
        boolean z2 = z;
        ECGPatchMultiLeadSettingData eCGPatchMultiLeadSettingData = new ECGPatchMultiLeadSettingData(null, 0, 0, 0, 0, 0, 0, 0, 255, null);
        if (z2) {
            try {
                function2.invoke(eCGPatchMultiLeadSettingData, Integer.valueOf(i));
            } catch (Exception e) {
                String str = "心电贴多导联设置项(" + eEcgPatchSettingType.getDes() + ")回显数据解析异常：" + e.getMessage();
            }
            String str2 = "心电贴多导联设置项(" + eEcgPatchSettingType.getDes() + ")操作成功";
        } else {
            String str3 = "心电贴多导联设置项(" + eEcgPatchSettingType.getDes() + ")操作失败，设备返回失败";
        }
        IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener = this.vp_c;
        if (iECGPatchMultiLeadSettingListener != null) {
            iECGPatchMultiLeadSettingListener.onECGPatchMultiLeadSettingResult(eEcgPatchSettingType, z, eCGPatchMultiLeadSettingData);
        }
    }

    private final EEcgPatchSwitch vp_a(byte[] bArr, int i) {
        EEcgPatchSwitch eEcgPatchSwitchFromValue = EEcgPatchSwitch.INSTANCE.fromValue(bArr[i] & 255);
        EEcgPatchSwitch eEcgPatchSwitch = eEcgPatchSwitchFromValue;
        if (eEcgPatchSwitchFromValue == null) {
            eEcgPatchSwitch = EEcgPatchSwitch.NONE;
        }
        return eEcgPatchSwitch;
    }

    public final void vp_a(EEcgPatchSwitch eEcgPatchSwitch, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArrVp_a = vp_a((byte) 1, eEcgPatchSwitch);
        String str2 = "心电贴设置多导联疾病提醒开关：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(EEcgPatchSwitch eEcgPatchSwitch, int i, int i2, int i3, int i4, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArrVp_a = vp_a((byte) 1, eEcgPatchSwitch, i, i2, i3, i4);
        String str2 = "心电贴设置多导联免打扰：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(EEcgPatchSwitch eEcgPatchSwitch, int i, int i2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "spo2Switch");
        byte[] bArrVp_a = vp_a((byte) 1, eEcgPatchSwitch, i, i2);
        String str2 = "心电贴设置多导联Heart Rate异常提醒：" + vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IECGPatchMultiLeadSettingListener iECGPatchMultiLeadSettingListener) {
        this.vp_c = iECGPatchMultiLeadSettingListener;
    }
}
