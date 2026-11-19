package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.INightTurnWristeDataListener;
import com.veepoo.protocol.model.datas.NightTurnWristeData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.ENightTurnWristeStatus;
import com.veepoo.protocol.model.settings.NightTurnWristSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_br.class */
public class vp_br extends com.veepoo.protocol.vp_a {
    INightTurnWristeDataListener vp_c;

    private byte[] vp_a(NightTurnWristSetting nightTurnWristSetting) {
        byte[] bArr = new byte[7];
        bArr[0] = -86;
        if (nightTurnWristSetting.isOpen()) {
            bArr[1] = 1;
        } else {
            bArr[1] = 0;
        }
        bArr[2] = VpBleByteUtil.intToBytes(nightTurnWristSetting.getStartTime().getHour())[3];
        bArr[3] = VpBleByteUtil.intToBytes(nightTurnWristSetting.getStartTime().getMinute())[3];
        bArr[4] = VpBleByteUtil.intToBytes(nightTurnWristSetting.getEndTime().getHour())[3];
        bArr[5] = VpBleByteUtil.intToBytes(nightTurnWristSetting.getEndTime().getMinute())[3];
        bArr[6] = VpBleByteUtil.intToBytes(nightTurnWristSetting.getLevel())[3];
        return bArr;
    }

    private NightTurnWristeData vp_c(byte[] bArr) {
        NightTurnWristeData nightTurnWristeData = new NightTurnWristeData();
        nightTurnWristeData.setSupportCustomSettingTime(!vp_f(bArr));
        nightTurnWristeData.setOprateStauts(vp_d(bArr));
        nightTurnWristeData.setNightTureWirsteStatusOpen(vp_b(bArr));
        int[] iArrVp_e = vp_e(bArr);
        nightTurnWristeData.setStartTime(new TimeData(iArrVp_e[0], iArrVp_e[1]));
        nightTurnWristeData.setEndTime(new TimeData(iArrVp_e[2], iArrVp_e[3]));
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        if (!vp_f(bArr) && iArrByte2HexToIntArr.length > 10) {
            nightTurnWristeData.setLevel(iArrByte2HexToIntArr[8]);
            nightTurnWristeData.setDefaultLevel(iArrByte2HexToIntArr[9]);
        }
        return nightTurnWristeData;
    }

    private boolean vp_b(byte[] bArr) {
        return vp_f(bArr) ? vp_a(bArr[2]) : vp_b(bArr[7]);
    }

    private boolean vp_f(byte[] bArr) {
        return bArr.length <= 3;
    }

    private int[] vp_e(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int[] iArr = new int[4];
        if (vp_f(bArr)) {
            iArr[0] = 8;
            iArr[1] = 0;
            iArr[2] = 22;
            iArr[3] = 0;
        } else {
            iArr[0] = iArrByte2HexToIntArr[3];
            iArr[1] = iArrByte2HexToIntArr[4];
            iArr[2] = iArrByte2HexToIntArr[5];
            iArr[3] = iArrByte2HexToIntArr[6];
        }
        return iArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ac(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ac(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ac, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        this.vp_c.onNightTurnWristeDataChange(vp_c(bArr));
    }

    public ENightTurnWristeStatus vp_d(byte[] bArr) {
        if (bArr.length < 2) {
            return ENightTurnWristeStatus.UNKONW;
        }
        byte b = bArr[1];
        if (b == 1 || b == 0) {
            return b == 1 ? ENightTurnWristeStatus.SUCCESS : ENightTurnWristeStatus.FAIL;
        }
        return ENightTurnWristeStatus.UNKONW;
    }

    private byte[] vp_a(boolean z, TimeData timeData, TimeData timeData2) {
        byte[] bArr = new byte[6];
        bArr[0] = -86;
        if (z) {
            bArr[1] = 1;
        } else {
            bArr[1] = 0;
        }
        bArr[2] = VpBleByteUtil.intToBytes(timeData.getHour())[3];
        bArr[3] = VpBleByteUtil.intToBytes(timeData.getMinute())[3];
        bArr[4] = VpBleByteUtil.intToBytes(timeData2.getHour())[3];
        bArr[5] = VpBleByteUtil.intToBytes(timeData2.getMinute())[3];
        return bArr;
    }

    private boolean vp_b(byte b) {
        return (b == 1 || b == 2) && b == 1;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_d(bluetoothClient, str, bleWriteResponse, z);
        if (z) {
            super.send(com.veepoo.protocol.profile.vp_a.vp_aa, bluetoothClient, str, bleWriteResponse);
        } else {
            super.send(com.veepoo.protocol.profile.vp_a.vp_ab, bluetoothClient, str, bleWriteResponse);
        }
    }

    private boolean vp_a(byte b) {
        return (b == 1 || b == 0) && b == 1;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (INightTurnWristeDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, NightTurnWristSetting nightTurnWristSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, nightTurnWristSetting);
        super.send(vp_a(nightTurnWristSetting), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, TimeData timeData, TimeData timeData2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, timeData, timeData2);
        super.send(vp_a(z, timeData, timeData2), bluetoothClient, str, bleWriteResponse);
    }
}
