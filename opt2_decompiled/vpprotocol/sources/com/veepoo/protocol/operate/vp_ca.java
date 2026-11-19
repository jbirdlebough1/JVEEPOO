package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.log.VPLocalLogger;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EPwdStatus;
import com.veepoo.protocol.model.settings.DeviceTimeSetting;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ca.class */
public class vp_ca extends com.veepoo.protocol.vp_a {
    IPwdDataListener vp_c;
    byte vp_d = 0;
    byte vp_e = 1;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, String str2, boolean z, boolean z2, DeviceTimeSetting deviceTimeSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, str2, z, z2, deviceTimeSetting);
        if (deviceTimeSetting == null) {
            deviceTimeSetting = vp_a();
        }
        byte[] bArrVp_a = vp_a(str2, this.vp_d, z, deviceTimeSetting);
        bArrVp_a[14] = z2;
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        PwdData pwdDataVp_b = vp_b(bArr);
        if (this.vp_c != null) {
            VPOperateManager.getInstance().removeConnectionConfirmationTask();
            String strVp_a = com.veepoo.protocol.util.vp_ae.vp_a.vp_a(VPOperateManager.mContext, pwdDataVp_b.getDeviceNumber() + "", pwdDataVp_b.getDeviceTestVersion());
            VPLocalLogger.bleConnectLog(strVp_a);
            VPLocalLogger.bleChangeLog(strVp_a);
            VPLocalLogger.bleWriteLog(strVp_a);
            this.vp_c.onPwdDataChange(pwdDataVp_b);
        }
        VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDeviceFunctionA1(pwdDataVp_b);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x014c  */
    /* JADX WARN: Code duplicated, block: B:32:0x0180  */
    public PwdData vp_b(byte[] bArr) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        PwdData pwdData = new PwdData();
        if (bArr.length < 20) {
            pwdData.setmStatus(EPwdStatus.UNKNOW);
            return pwdData;
        }
        VpBleByteUtil.hexStrToInt(strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[2]);
        pwdData.setDeviceNumber(Integer.valueOf(strArrByte2HexToStrArr[4] + strArrByte2HexToStrArr[5], 16).intValue());
        pwdData.setDeviceVersion(strArrByte2HexToStrArr[6] + "." + strArrByte2HexToStrArr[7] + "." + strArrByte2HexToStrArr[8]);
        pwdData.setDeviceTestVersion(strArrByte2HexToStrArr[6] + "." + strArrByte2HexToStrArr[7] + "." + strArrByte2HexToStrArr[8] + "." + strArrByte2HexToStrArr[9]);
        byte b = bArr[3];
        byte b2 = bArr[10];
        byte b3 = bArr[11];
        byte b4 = bArr[18];
        byte b5 = bArr[19];
        if (b2 == 0) {
            pwdData.setHaveDrinkData(false);
        } else if (b2 == 1) {
            pwdData.setHaveDrinkData(true);
        }
        if (b3 == 0) {
            pwdData.setOpenNightTurnWriste(EFunctionStatus.SUPPORT_CLOSE);
        } else if (b3 == 1) {
            pwdData.setOpenNightTurnWriste(EFunctionStatus.SUPPORT_OPEN);
        } else if (b3 == 2) {
            pwdData.setOpenNightTurnWriste(EFunctionStatus.UNSUPPORT);
        }
        if (b4 == 0) {
            pwdData.setFindPhoneFunction(EFunctionStatus.UNSUPPORT);
        } else if (b4 == 1) {
            pwdData.setFindPhoneFunction(EFunctionStatus.SUPPORT_OPEN);
        } else if (b4 == 2) {
            pwdData.setFindPhoneFunction(EFunctionStatus.SUPPORT_CLOSE);
        } else {
            pwdData.setFindPhoneFunction(EFunctionStatus.UNSUPPORT);
        }
        if (b5 == 0) {
            pwdData.setWearDetectFunction(EFunctionStatus.UNSUPPORT);
        } else if (b5 == 1) {
            pwdData.setWearDetectFunction(EFunctionStatus.SUPPORT_OPEN);
        } else if (b5 == 2) {
            pwdData.setWearDetectFunction(EFunctionStatus.SUPPORT_CLOSE);
        } else {
            pwdData.setWearDetectFunction(EFunctionStatus.UNSUPPORT);
        }
        if (b == 1) {
            pwdData.setmStatus(EPwdStatus.CHECK_SUCCESS);
        } else if (b == 0) {
            pwdData.setmStatus(EPwdStatus.CHECK_FAIL);
        } else if (b == 3) {
            pwdData.setmStatus(EPwdStatus.SETTING_SUCCESS);
        } else if (b == 2) {
            pwdData.setmStatus(EPwdStatus.SETTING_FAIL);
        } else if (b == 5) {
            pwdData.setmStatus(EPwdStatus.READ_SUCCESS);
        } else if (b == 4) {
            pwdData.setmStatus(EPwdStatus.READ_FAIL);
        } else if (b == 6) {
            pwdData.setmStatus(EPwdStatus.CHECK_AND_TIME_SUCCESS);
        } else {
            pwdData.setmStatus(EPwdStatus.UNKNOW);
        }
        return pwdData;
    }

    public byte[] vp_a(DeviceTimeSetting deviceTimeSetting) {
        int year = deviceTimeSetting.getYear();
        int month = deviceTimeSetting.getMonth();
        int day = deviceTimeSetting.getDay();
        int hour = deviceTimeSetting.getHour();
        int minute = deviceTimeSetting.getMinute();
        int second = deviceTimeSetting.getSecond();
        String str = "0" + Integer.toHexString(year);
        return new byte[]{VpBleByteUtil.HexStringToBinary(str.substring(0, 2))[0], VpBleByteUtil.HexStringToBinary(str.substring(2, 4))[0], VpBleByteUtil.loUint16((short) month), VpBleByteUtil.loUint16((short) day), VpBleByteUtil.loUint16((short) hour), VpBleByteUtil.loUint16((short) minute), VpBleByteUtil.loUint16((short) second)};
    }

    public DeviceTimeSetting vp_a() {
        Calendar calendar = Calendar.getInstance();
        return new DeviceTimeSetting(calendar.get(1), calendar.get(2) + 1, calendar.get(5), calendar.get(11), calendar.get(12), calendar.get(13));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IPwdDataListener) iListener;
        handler(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public byte[] vp_a(String str, byte b, boolean z, DeviceTimeSetting deviceTimeSetting) {
        byte[] bArrIntToBytes = VpBleByteUtil.intToBytes(Integer.parseInt(str, 10));
        byte[] bArr = new byte[20];
        bArr[0] = -95;
        bArr[1] = bArrIntToBytes[3];
        bArr[2] = bArrIntToBytes[2];
        bArr[3] = b;
        if (b == this.vp_e) {
            return bArr;
        }
        byte[] bArrVp_a = vp_a(deviceTimeSetting);
        bArr[4] = bArrVp_a[0];
        bArr[5] = bArrVp_a[1];
        bArr[6] = bArrVp_a[2];
        bArr[7] = bArrVp_a[3];
        bArr[8] = bArrVp_a[4];
        bArr[9] = bArrVp_a[5];
        bArr[10] = bArrVp_a[6];
        bArr[11] = z;
        bArr[12] = 1;
        bArr[13] = (byte) (((TimeZone.getDefault().getRawOffset() / 1000) / 60) / 15);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, bArr);
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
