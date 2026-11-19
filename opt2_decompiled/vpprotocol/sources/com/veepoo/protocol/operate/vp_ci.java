package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IScreenLightListener;
import com.veepoo.protocol.model.datas.ScreenLightData;
import com.veepoo.protocol.model.enums.EScreenLight;
import com.veepoo.protocol.model.settings.ScreenSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ci.class */
public class vp_ci extends com.veepoo.protocol.vp_a {
    byte vp_c = 2;

    private byte[] vp_a(ScreenSetting screenSetting, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -79;
        bArr[1] = b;
        if (b != this.vp_c && screenSetting != null) {
            bArr[2] = VpBleByteUtil.intToBytes(screenSetting.getStartHour())[3];
            bArr[3] = VpBleByteUtil.intToBytes(screenSetting.getStartMinute())[3];
            bArr[4] = VpBleByteUtil.intToBytes(screenSetting.getEndHour())[3];
            bArr[5] = VpBleByteUtil.intToBytes(screenSetting.getEndMinute())[3];
            bArr[6] = VpBleByteUtil.intToBytes(screenSetting.getLevel())[3];
            bArr[7] = VpBleByteUtil.intToBytes(screenSetting.getOtherLeverl())[3];
            bArr[8] = VpBleByteUtil.intToBytes(screenSetting.getAuto())[3];
            return bArr;
        }
        return bArr;
    }

    private ScreenSetting vp_c(byte[] bArr) {
        if (bArr.length < 9) {
            return null;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        ScreenSetting screenSetting = new ScreenSetting(iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8]);
        if (bArr.length > 9 && bArr.length <= 20) {
            int i = iArrByte2HexToIntArr[9];
            int i2 = iArrByte2HexToIntArr[10];
            screenSetting.setAuto(i);
            screenSetting.setMaxLevel(i2);
        }
        return screenSetting;
    }

    public static EScreenLight vp_b(byte[] bArr) {
        if (bArr.length < 9) {
            return EScreenLight.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b2 == 1) {
            return b == 1 ? EScreenLight.SETTING_SUCCESS : EScreenLight.SETTING_FAIL;
        }
        if (b2 == 2) {
            return b == 1 ? EScreenLight.READ_SUCCESS : EScreenLight.READ_FAIL;
        }
        return EScreenLight.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_aj(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_aj(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((ScreenSetting) null, this.vp_c), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        ((IScreenLightListener) iListener).onScreenLightDataChange(new ScreenLightData(vp_b(bArr), vp_c(bArr)));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ScreenSetting screenSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, screenSetting);
        super.send(vp_a(screenSetting, (byte) 1), bluetoothClient, str, bleWriteResponse);
    }
}
