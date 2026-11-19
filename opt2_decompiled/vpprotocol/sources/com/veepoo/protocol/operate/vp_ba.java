package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IDeviceFunctionStatusChangeListener;
import com.veepoo.protocol.listener.data.IHealthAlarmIntervalListener;
import com.veepoo.protocol.listener.data.IHeartWaringDataListener;
import com.veepoo.protocol.model.datas.HealthAlarmInterval;
import com.veepoo.protocol.model.datas.HeartWaringData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EHealthAlarmType;
import com.veepoo.protocol.model.enums.EHeartWaringStatus;
import com.veepoo.protocol.model.settings.HeartWaringSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ba.class */
public final class vp_ba extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private IHealthAlarmIntervalListener vp_c;
    private IHeartWaringDataListener vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ba$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ba$vp_b.class */
    public /* synthetic */ class vp_b {
        public static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EHealthAlarmType.values().length];
            try {
                iArr[EHealthAlarmType.HEART_RATE_ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EHealthAlarmType.BLOOD_PRESSURE_ALARM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EHealthAlarmType.HIGH_TEMPERATURE_ALARM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EHealthAlarmType.LOW_BLOOD_OXYGEN_ALARM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            vp_a = iArr;
        }
    }

    private final HeartWaringData vp_c(byte[] bArr) {
        HeartWaringData heartWaringData = new HeartWaringData();
        if (bArr.length < 6) {
            heartWaringData.setStatus(EHeartWaringStatus.UNKONW);
            return heartWaringData;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        heartWaringData.setStatus(vp_d(bArr));
        heartWaringData.setOpen(vp_a(bArr[5]));
        heartWaringData.setHeartHigh(i);
        heartWaringData.setHeartLow(i2);
        return heartWaringData;
    }

    private final IDeviceFunctionStatusChangeListener.DeviceFunction vp_a(HealthAlarmInterval healthAlarmInterval) {
        switch (vp_b.vp_a[healthAlarmInterval.getHealthType().ordinal()]) {
            case 1:
                return IDeviceFunctionStatusChangeListener.DeviceFunction.HeartRateAlarm;
            case 2:
                return IDeviceFunctionStatusChangeListener.DeviceFunction.BloodPressureAlarm;
            case 3:
                return IDeviceFunctionStatusChangeListener.DeviceFunction.HighTemperatureAlarm;
            case 4:
                return IDeviceFunctionStatusChangeListener.DeviceFunction.LowBloodOxygenAlarm;
            default:
                return IDeviceFunctionStatusChangeListener.DeviceFunction.UNKNOWN;
        }
    }

    private final HealthAlarmInterval vp_b(byte[] bArr) {
        EHealthAlarmType eHealthAlarmTypeWithCMD = EHealthAlarmType.INSTANCE.getEHealthAlarmTypeWithCMD(bArr[6]);
        float fVp_b = 0.0f;
        float fVp_a = 0.0f;
        int i = vp_b.vp_a[eHealthAlarmTypeWithCMD.ordinal()];
        if (i == 3) {
            fVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[2], bArr[1]) / 10.0f;
        } else if (i != 4) {
            fVp_b = com.veepoo.protocol.util.vp_g.vp_a(bArr[1]);
            fVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr[2]);
        } else {
            fVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr[2]);
        }
        byte b = bArr[4];
        return new HealthAlarmInterval(eHealthAlarmTypeWithCMD, fVp_b, fVp_a, bArr[5] != 0);
    }

    private final EHeartWaringStatus vp_d(byte[] bArr) {
        byte b = bArr[3];
        byte b2 = bArr[4];
        if (b == 16) {
            return EHeartWaringStatus.UNSUPPORT;
        }
        if (b2 == 1 && b == 1) {
            return EHeartWaringStatus.OPEN_SUCCESS;
        }
        if (b2 == 1 && b == 0) {
            return EHeartWaringStatus.OPEN_FAIL;
        }
        if (b2 == 0 && b == 1) {
            return EHeartWaringStatus.CLOSE_SUCCESS;
        }
        if (b2 == 0 && b == 0) {
            return EHeartWaringStatus.CLOSE_FAIL;
        }
        if (b2 == 2 && b == 1) {
            return EHeartWaringStatus.READ_SUCCESS;
        }
        return (b2 == 2 && b == 0) ? EHeartWaringStatus.READ_FAIL : EHeartWaringStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IHealthAlarmIntervalListener iHealthAlarmIntervalListener;
        IHealthAlarmIntervalListener iHealthAlarmIntervalListener2;
        super.handler(bArr);
        if (bArr != null) {
            byte b = bArr[3];
            byte b2 = bArr[4];
            byte b3 = bArr[5];
            HealthAlarmInterval healthAlarmIntervalVp_b = vp_b(bArr);
            if (b == 16) {
                IHealthAlarmIntervalListener iHealthAlarmIntervalListener3 = this.vp_c;
                if (iHealthAlarmIntervalListener3 != null) {
                    iHealthAlarmIntervalListener3.functionNotSupport();
                    return;
                }
                return;
            }
            if (b == 0) {
                if (b2 == 0 || b2 == 1) {
                    IHealthAlarmIntervalListener iHealthAlarmIntervalListener4 = this.vp_c;
                    if (iHealthAlarmIntervalListener4 != null) {
                        iHealthAlarmIntervalListener4.onHealthAlarmIntervalSetting(healthAlarmIntervalVp_b, false);
                        return;
                    }
                    return;
                }
                if (b2 != 2 || (iHealthAlarmIntervalListener2 = this.vp_c) == null) {
                    return;
                }
                iHealthAlarmIntervalListener2.onHealthAlarmIntervalReadSuccess(healthAlarmIntervalVp_b, false);
                return;
            }
            if (b == 1) {
                if (b2 == 0 || b2 == 1) {
                    IHealthAlarmIntervalListener iHealthAlarmIntervalListener5 = this.vp_c;
                    if (iHealthAlarmIntervalListener5 != null) {
                        iHealthAlarmIntervalListener5.onHealthAlarmIntervalSetting(healthAlarmIntervalVp_b, true);
                    }
                } else if (b2 == 2 && (iHealthAlarmIntervalListener = this.vp_c) != null) {
                    iHealthAlarmIntervalListener.onHealthAlarmIntervalReadSuccess(healthAlarmIntervalVp_b, true);
                }
                VPOperateManager.getInstance().postFunctionStatusChanged(vp_a(healthAlarmIntervalVp_b), healthAlarmIntervalVp_b.isOpen() ? EFunctionStatus.SUPPORT_OPEN : EFunctionStatus.SUPPORT_CLOSE);
                if (healthAlarmIntervalVp_b.getHealthType() == EHealthAlarmType.HEART_RATE_ALARM) {
                    HeartWaringData heartWaringDataVp_c = vp_c(bArr);
                    IHeartWaringDataListener iHeartWaringDataListener = this.vp_d;
                    if (iHeartWaringDataListener != null) {
                        iHeartWaringDataListener.onHeartWaringDataChange(heartWaringDataVp_c);
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_b(HealthAlarmInterval healthAlarmInterval) {
        byte[] bArr = new byte[20];
        bArr[0] = -84;
        if (healthAlarmInterval.getHealthType() == EHealthAlarmType.HIGH_TEMPERATURE_ALARM) {
            byte[] bArrVp_d = com.veepoo.protocol.util.vp_g.vp_d((int) (healthAlarmInterval.getCeilingValue() * 10.0f));
            bArr[1] = bArrVp_d[1];
            bArr[2] = bArrVp_d[0];
        } else {
            bArr[1] = (byte) healthAlarmInterval.getCeilingValue();
            bArr[2] = (byte) healthAlarmInterval.getFloorValue();
        }
        bArr[3] = healthAlarmInterval.isOpen();
        bArr[4] = healthAlarmInterval.getHealthType().getTag();
        return bArr;
    }

    private final byte[] vp_a(HeartWaringSetting heartWaringSetting) {
        byte[] bArr = new byte[4];
        bArr[0] = -84;
        bArr[1] = VpBleByteUtil.intToBytes(heartWaringSetting.getHeartHigh())[3];
        bArr[2] = VpBleByteUtil.intToBytes(heartWaringSetting.getHeartLow())[3];
        if (heartWaringSetting.isOpen()) {
            bArr[3] = 1;
        } else {
            bArr[3] = 0;
        }
        return bArr;
    }

    private final boolean vp_a(byte b) {
        return b != 0 && b == 1;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(EHealthAlarmType eHealthAlarmType, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        Intrinsics.checkNotNullParameter(eHealthAlarmType, "healthAlarmType");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(iHealthAlarmIntervalListener, "listener");
        super.vp_a(eHealthAlarmType, bluetoothClient, str, bleWriteResponse, iHealthAlarmIntervalListener);
        this.vp_c = iHealthAlarmIntervalListener;
        byte[] bArr = new byte[20];
        bArr[0] = -84;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 2;
        bArr[4] = eHealthAlarmType.getTag();
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHeartWaringDataListener iHeartWaringDataListener) {
        Intrinsics.checkNotNullParameter(iHeartWaringDataListener, "heartWaringDataListener");
        super.vp_z(bluetoothClient, str, bleWriteResponse);
        this.vp_d = iHeartWaringDataListener;
        super.send(com.veepoo.protocol.profile.vp_a.vp_bg, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(HealthAlarmInterval healthAlarmInterval, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IHealthAlarmIntervalListener iHealthAlarmIntervalListener) {
        Intrinsics.checkNotNullParameter(healthAlarmInterval, "healthAlarmInterval");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(iHealthAlarmIntervalListener, "listener");
        super.vp_a(healthAlarmInterval, bluetoothClient, str, bleWriteResponse, iHealthAlarmIntervalListener);
        this.vp_c = iHealthAlarmIntervalListener;
        send(vp_b(healthAlarmInterval), bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, HeartWaringSetting heartWaringSetting, IHeartWaringDataListener iHeartWaringDataListener) {
        Intrinsics.checkNotNullParameter(heartWaringSetting, "heartWaringSetting");
        Intrinsics.checkNotNullParameter(iHeartWaringDataListener, "heartWaringDataListener");
        this.vp_d = iHeartWaringDataListener;
        super.vp_a(bluetoothClient, str, bleWriteResponse, heartWaringSetting);
        super.send(vp_a(heartWaringSetting), bluetoothClient, str, bleWriteResponse);
    }
}
