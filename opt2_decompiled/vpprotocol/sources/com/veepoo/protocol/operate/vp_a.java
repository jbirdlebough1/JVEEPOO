package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IAlarmDataListener;
import com.veepoo.protocol.model.datas.AlarmData;
import com.veepoo.protocol.model.enums.EAalarmStatus;
import com.veepoo.protocol.model.settings.AlarmSetting;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_a.class */
public class vp_a extends com.veepoo.protocol.vp_a {
    IAlarmDataListener vp_c;

    private AlarmData vp_b(byte[] bArr) {
        AlarmData alarmData = new AlarmData();
        ArrayList arrayList = new ArrayList();
        alarmData.setAlarmSettingList(arrayList);
        if (bArr.length < 12) {
            alarmData.setStatus(EAalarmStatus.UNKONW);
            this.vp_c.onAlarmDataChangeListener(alarmData);
            return null;
        }
        byte b = bArr[11];
        byte b2 = bArr[1];
        if (b2 != 1 && b2 != 0) {
            alarmData.setStatus(EAalarmStatus.UNKONW);
            this.vp_c.onAlarmDataChangeListener(alarmData);
            return null;
        }
        if (b == 1) {
            if (b2 == 1) {
                alarmData.setStatus(EAalarmStatus.SETTING_SUCCESS);
            } else {
                alarmData.setStatus(EAalarmStatus.SETTING_FAIL);
            }
        } else if (b != 6) {
            alarmData.setStatus(EAalarmStatus.UNKONW);
        } else if (b2 == 1) {
            alarmData.setStatus(EAalarmStatus.READ_SUCCESS);
        } else {
            alarmData.setStatus(EAalarmStatus.READ_FAIL);
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        if (iArrByte2HexToIntArr[4] == 1) {
            z = true;
        }
        if (iArrByte2HexToIntArr[7] == 1) {
            z2 = true;
        }
        if (iArrByte2HexToIntArr[10] == 1) {
            z3 = true;
        }
        AlarmSetting alarmSetting = new AlarmSetting((iArrByte2HexToIntArr[2] * 60) + iArrByte2HexToIntArr[3], z);
        AlarmSetting alarmSetting2 = new AlarmSetting((iArrByte2HexToIntArr[5] * 60) + iArrByte2HexToIntArr[6], z2);
        AlarmSetting alarmSetting3 = new AlarmSetting((iArrByte2HexToIntArr[8] * 60) + iArrByte2HexToIntArr[9], z3);
        arrayList.add(alarmSetting);
        arrayList.add(alarmSetting2);
        arrayList.add(alarmSetting3);
        alarmData.setAlarmSettingList(arrayList);
        return alarmData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_j(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_j(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_z, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        AlarmData alarmDataVp_b = vp_b(bArr);
        if (alarmDataVp_b == null) {
            return;
        }
        this.vp_c.onAlarmDataChangeListener(alarmDataVp_b);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public byte[] vp_a(List list) {
        byte[] bArr = new byte[11];
        bArr[0] = -85;
        if (list.size() != 3) {
            return bArr;
        }
        for (int i = 0; i < list.size(); i++) {
            int i2 = i;
            boolean zIsOpen = ((AlarmSetting) list.get(i)).isOpen();
            int alarmTime = ((AlarmSetting) list.get(i)).getAlarmTime() / 60;
            int alarmTime2 = ((AlarmSetting) list.get(i)).getAlarmTime() % 60;
            if (i2 == 0) {
                bArr[1] = VpBleByteUtil.intToBytes(alarmTime)[3];
                bArr[2] = VpBleByteUtil.intToBytes(alarmTime2)[3];
                bArr[3] = zIsOpen;
            }
            if (i == 1) {
                bArr[4] = VpBleByteUtil.intToBytes(alarmTime)[3];
                bArr[5] = VpBleByteUtil.intToBytes(alarmTime2)[3];
                bArr[6] = zIsOpen;
            }
            if (i == 2) {
                bArr[7] = VpBleByteUtil.intToBytes(alarmTime)[3];
                bArr[8] = VpBleByteUtil.intToBytes(alarmTime2)[3];
                bArr[9] = zIsOpen;
            }
            bArr[10] = 1;
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list) {
        super.send(vp_a(list), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IAlarmDataListener) iListener;
        handler(bArr);
    }
}
