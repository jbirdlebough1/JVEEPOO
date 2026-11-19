package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISpo2hBreathBreakRemainListener;
import com.veepoo.protocol.model.datas.BreathBreakRemindData;
import com.veepoo.protocol.model.enums.ESBBRStatus;
import com.veepoo.protocol.model.settings.BreathBreakRemindSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_co.class */
public class vp_co extends com.veepoo.protocol.vp_a {
    ISpo2hBreathBreakRemainListener vp_c;
    byte vp_d = 1;
    byte vp_e = 2;

    private byte[] vp_a(BreathBreakRemindSetting breathBreakRemindSetting, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -122;
        bArr[1] = b;
        if (b != this.vp_e && breathBreakRemindSetting != null) {
            bArr[2] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getStartHour());
            bArr[3] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getStartMinute());
            bArr[4] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getEndHour());
            bArr[5] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getEndMinute());
            bArr[6] = -1;
            bArr[7] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getRemindTime());
            bArr[8] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getMinOxygen());
            bArr[9] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getRemindTimeDefault());
            bArr[10] = VpBleByteUtil.loUint16((short) breathBreakRemindSetting.getOpenStatus());
            return bArr;
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ah(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ah(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((BreathBreakRemindSetting) null, this.vp_e), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        BreathBreakRemindData breathBreakRemindData = new BreathBreakRemindData();
        if (bArr.length < 20) {
            this.vp_c.onSpo2hBreathBreakRemainDataChange(breathBreakRemindData);
        }
        breathBreakRemindData.setESBBRStatus(vp_b(bArr));
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        breathBreakRemindData.setStartHour(iArrByte2HexToIntArr[3]);
        breathBreakRemindData.setStartMinute(iArrByte2HexToIntArr[4]);
        breathBreakRemindData.setEndHour(iArrByte2HexToIntArr[5]);
        breathBreakRemindData.setEndMinute(iArrByte2HexToIntArr[6]);
        breathBreakRemindData.setRemindTimeDefault(iArrByte2HexToIntArr[7]);
        breathBreakRemindData.setRemindTime(iArrByte2HexToIntArr[8]);
        breathBreakRemindData.setMinOxygen(iArrByte2HexToIntArr[9]);
        breathBreakRemindData.setRemindTimeDefault(iArrByte2HexToIntArr[10]);
        breathBreakRemindData.setOpenStatus(iArrByte2HexToIntArr[11]);
        this.vp_c.onSpo2hBreathBreakRemainDataChange(breathBreakRemindData);
    }

    public ESBBRStatus vp_b(byte[] bArr) {
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b2 == 1 && b == 1) {
            return ESBBRStatus.SETTING_SUCCESS;
        }
        if (b2 == 1 && b == 0) {
            return ESBBRStatus.SETTING_FAIL;
        }
        if (b2 == 2 && b == 1) {
            return ESBBRStatus.READ_SUCCESS;
        }
        return (b2 == 2 && b == 0) ? ESBBRStatus.READ_FAIL : ESBBRStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ISpo2hBreathBreakRemainListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BreathBreakRemindSetting breathBreakRemindSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, breathBreakRemindSetting);
        super.send(vp_a(breathBreakRemindSetting, this.vp_d), bluetoothClient, str, bleWriteResponse);
    }
}
