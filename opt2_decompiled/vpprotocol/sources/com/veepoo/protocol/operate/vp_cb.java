package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IPwdDataListener;
import com.veepoo.protocol.model.datas.PwdData;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EPwdStatus;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cb.class */
public class vp_cb extends com.veepoo.protocol.vp_a {
    IPwdDataListener vp_c;

    /* JADX WARN: Code duplicated, block: B:23:0x014c  */
    /* JADX WARN: Code duplicated, block: B:32:0x0180  */
    private PwdData vp_b(byte[] bArr) {
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

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IPwdDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        PwdData pwdDataVp_b = vp_b(bArr);
        IPwdDataListener iPwdDataListener = this.vp_c;
        if (iPwdDataListener != null) {
            iPwdDataListener.onPwdDataChange(pwdDataVp_b);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, String str2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, str2);
        super.send(vp_a(str2), bluetoothClient, str, bleWriteResponse);
    }

    public byte[] vp_a(String str) {
        byte[] bArrIntToBytes = VpBleByteUtil.intToBytes(Integer.parseInt(str, 10));
        byte[] bArr = new byte[13];
        bArr[0] = -95;
        bArr[1] = bArrIntToBytes[3];
        bArr[2] = bArrIntToBytes[2];
        bArr[3] = 1;
        return bArr;
    }
}
