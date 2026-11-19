package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IAllSetDataListener;
import com.veepoo.protocol.listener.data.IDeviceFunctionStatusChangeListener;
import com.veepoo.protocol.model.datas.AllSetData;
import com.veepoo.protocol.model.enums.EAllSetStatus;
import com.veepoo.protocol.model.enums.EAllSetType;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.settings.AllSetSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_b.class */
public class vp_b extends com.veepoo.protocol.vp_a {
    IAllSetDataListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_b$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EAllSetType.values().length];
            vp_a = iArr;
            try {
                iArr[EAllSetType.SPO2H_NIGHT_AUTO_DETECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private byte[] vp_a(AllSetSetting allSetSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = -77;
        bArr[1] = VpBleByteUtil.intToBytes(vp_a(allSetSetting.getType()))[3];
        bArr[2] = VpBleByteUtil.intToBytes(allSetSetting.getOprate())[3];
        bArr[3] = VpBleByteUtil.intToBytes(allSetSetting.getStartHour())[3];
        bArr[4] = VpBleByteUtil.intToBytes(allSetSetting.getStartMinute())[3];
        bArr[5] = VpBleByteUtil.intToBytes(allSetSetting.getEndHour())[3];
        bArr[6] = VpBleByteUtil.intToBytes(allSetSetting.getEndMinute())[3];
        bArr[7] = VpBleByteUtil.intToBytes(allSetSetting.getOpenState())[3];
        return bArr;
    }

    private AllSetData vp_c(byte[] bArr) {
        if (bArr.length < 20) {
            return null;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        EAllSetStatus eAllSetStatusVp_b = vp_b(bArr);
        return new AllSetData(vp_a(bArr[2]), iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[3], eAllSetStatusVp_b, iArrByte2HexToIntArr[8]);
    }

    private static EAllSetStatus vp_b(byte[] bArr) {
        if (bArr.length < 9) {
            return EAllSetStatus.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[3];
        if (b2 == 0) {
            return b == 1 ? EAllSetStatus.SETTING_SUCCESS : EAllSetStatus.SETTING_FAIL;
        }
        if (b2 == 1) {
            return b == 1 ? EAllSetStatus.READ_SUCCESS : EAllSetStatus.READ_FAIL;
        }
        return EAllSetStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_m(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_m(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -77;
        bArr[1] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        AllSetData allSetDataVp_c = vp_c(bArr);
        if (allSetDataVp_c == null) {
            return;
        }
        allSetDataVp_c.toString();
        EFunctionStatus eFunctionStatus = EFunctionStatus.SUPPORT;
        if (allSetDataVp_c.getIsOpen() == 0) {
            eFunctionStatus = EFunctionStatus.SUPPORT_CLOSE;
        } else if (allSetDataVp_c.getIsOpen() == 1) {
            eFunctionStatus = EFunctionStatus.SUPPORT_OPEN;
        }
        VPOperateManager.getInstance().postFunctionStatusChanged(IDeviceFunctionStatusChangeListener.DeviceFunction.BloodOxygenAutoDetect, eFunctionStatus);
        IAllSetDataListener iAllSetDataListener = this.vp_c;
        if (iAllSetDataListener != null) {
            iAllSetDataListener.onAllSetDataChangeListener(allSetDataVp_c);
        }
    }

    private EAllSetType vp_a(byte b) {
        return b == 0 ? EAllSetType.SPO2H_NIGHT_AUTO_DETECT : EAllSetType.SPO2H_NIGHT_AUTO_DETECT;
    }

    private byte vp_a(EAllSetType eAllSetType) {
        int i = vp_a.vp_a[eAllSetType.ordinal()];
        return (byte) 0;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        if (iListener instanceof IAllSetDataListener) {
            this.vp_c = (IAllSetDataListener) iListener;
            handler(bArr);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AllSetSetting allSetSetting) {
        super.send(vp_a(allSetSetting), bluetoothClient, str, bleWriteResponse);
    }
}
