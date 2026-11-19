package com.veepoo.protocol.operate;

import android.content.Context;
import android.text.TextUtils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IAlarm2DataListListener;
import com.veepoo.protocol.listener.data.IAlarm2DataReadListener;
import com.veepoo.protocol.listener.data.OnDeviceAlarm2ChangedListener;
import com.veepoo.protocol.model.datas.AlarmData2;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EMultiAlarmOprate;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import com.veepoo.protocol.util.Alarm2Util;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bo.class */
public class vp_bo extends com.veepoo.protocol.vp_a {
    public byte vp_c = 1;
    public byte vp_d = 0;
    private final List vp_e = new ArrayList();
    private String vp_f;
    IAlarm2DataReadListener vp_g;
    OnDeviceAlarm2ChangedListener vp_h;
    private byte[] vp_i;

    public void vp_c(byte[] bArr, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        this.vp_f = str;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a() {
        if (this.vp_e.isEmpty()) {
            return;
        }
        this.vp_e.clear();
    }

    public byte[] vp_b(List list) {
        byte[] bArr = new byte[20];
        bArr[0] = -71;
        bArr[1] = 2;
        byte[] bArrVp_a = vp_a(list);
        this.vp_i = bArrVp_a;
        System.arraycopy(bArrVp_a, 0, bArr, 18, 2);
        return bArr;
    }

    private boolean vp_b(Context context, byte[] bArr, String str) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte b3 = bArr[5];
        if (b != 0 && b2 != 0) {
            return b3 != 0;
        }
        if (b != b2) {
            return false;
        }
        com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(str);
        return false;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Alarm2Setting alarm2Setting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, alarm2Setting);
        vp_c(vp_a(alarm2Setting, this.vp_d), bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] vp_a(Alarm2Setting alarm2Setting) {
        byte[] bArr = new byte[10];
        bArr[0] = VpBleByteUtil.loUint16((short) alarm2Setting.getAlarmId());
        bArr[1] = VpBleByteUtil.loUint16((short) alarm2Setting.getAlarmHour());
        bArr[2] = VpBleByteUtil.loUint16((short) alarm2Setting.getAlarmMinute());
        bArr[3] = alarm2Setting.isOpen;
        bArr[4] = VpBleByteUtil.loUint16((short) alarm2Setting.getRepeatIntStatus());
        bArr[5] = VpBleByteUtil.loUint16((short) alarm2Setting.getScene());
        String unRepeatDate = alarm2Setting.getUnRepeatDate();
        if (alarm2Setting.getRepeatIntStatus() == 0 && !unRepeatDate.equals("0000-00-00")) {
            TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(unRepeatDate);
            int year = timeDataVp_a.getYear();
            int month = timeDataVp_a.getMonth();
            int day = timeDataVp_a.getDay();
            short s = (short) year;
            bArr[6] = VpBleByteUtil.loUint16(s);
            bArr[7] = VpBleByteUtil.hiUint16(s);
            bArr[8] = VpBleByteUtil.loUint16((short) month);
            bArr[9] = VpBleByteUtil.loUint16((short) day);
        }
        return bArr;
    }

    public static boolean vp_b(Alarm2Setting alarm2Setting) {
        int iVp_a;
        String unRepeatDate = alarm2Setting.getUnRepeatDate();
        int alarmHour = alarm2Setting.getAlarmHour();
        int alarmMinute = alarm2Setting.getAlarmMinute();
        if (!unRepeatDate.equals("0000-00-00") && (iVp_a = com.veepoo.protocol.util.vp_j.vp_a(unRepeatDate, com.veepoo.protocol.util.vp_j.vp_c())) >= 0) {
            return iVp_a != 0 || (alarmHour * 60) + alarmMinute <= (TimeData.getSysHour() * 60) + TimeData.getSysMiute();
        }
        return false;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Alarm2Setting alarm2Setting) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, alarm2Setting);
        vp_c(vp_a(alarm2Setting, this.vp_c), bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public byte[] vp_a(Alarm2Setting alarm2Setting, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -71;
        if (alarm2Setting == null) {
            bArr[1] = -1;
            return bArr;
        }
        int alarmId = alarm2Setting.getAlarmId();
        boolean zIsOpen = alarm2Setting.isOpen();
        int alarmHour = alarm2Setting.getAlarmHour();
        int alarmMinute = alarm2Setting.getAlarmMinute();
        int repeatIntStatus = alarm2Setting.getRepeatIntStatus();
        int scene = alarm2Setting.getScene();
        String unRepeatDate = alarm2Setting.getUnRepeatDate();
        bArr[1] = b;
        bArr[2] = VpBleByteUtil.loUint16((short) alarmId);
        bArr[3] = VpBleByteUtil.loUint16((short) alarmHour);
        bArr[4] = VpBleByteUtil.loUint16((short) alarmMinute);
        bArr[5] = zIsOpen;
        bArr[6] = VpBleByteUtil.loUint16((short) repeatIntStatus);
        bArr[7] = VpBleByteUtil.loUint16((short) scene);
        if (alarm2Setting.getRepeatIntStatus() == 0 && !TextUtils.equals("0000-00-00", unRepeatDate)) {
            System.arraycopy(vp_a(unRepeatDate), 0, bArr, 8, 4);
        }
        return bArr;
    }

    private byte[] vp_a(List list) {
        byte[] bArr = new byte[2];
        if (list == null || list.isEmpty()) {
            return bArr;
        }
        byte[] bArr2 = new byte[list.size() * 10];
        for (int i = 0; i < list.size(); i++) {
            Alarm2Setting alarm2Setting = (Alarm2Setting) list.get(i);
            if (alarm2Setting.getRepeatIntStatus() == 0 && vp_b(alarm2Setting)) {
                alarm2Setting.setOpen(false);
                this.vp_g.onAlarmDataChangeListener(alarm2Setting);
            }
            System.arraycopy(vp_a(alarm2Setting), 0, bArr2, i * 10, 10);
        }
        short alarmCrc16 = (short) Alarm2Util.getAlarmCrc16(bArr2);
        bArr[0] = VpBleByteUtil.loUint16(alarmCrc16);
        bArr[1] = VpBleByteUtil.hiUint16(alarmCrc16);
        return bArr;
    }

    private byte[] vp_a(String str) {
        TimeData timeDataVp_a = com.veepoo.protocol.util.vp_j.vp_a(str);
        int year = timeDataVp_a.getYear();
        int month = timeDataVp_a.getMonth();
        int day = timeDataVp_a.getDay();
        String str2 = "0" + Integer.toHexString(year);
        return new byte[]{VpBleByteUtil.HexStringToBinary(str2.substring(2, 4))[0], VpBleByteUtil.HexStringToBinary(str2.substring(0, 2))[0], VpBleByteUtil.loUint16((short) month), VpBleByteUtil.loUint16((short) day)};
    }

    private Alarm2Setting vp_a(String str, byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[5];
        int i2 = iArrByte2HexToIntArr[6];
        int i3 = iArrByte2HexToIntArr[7];
        boolean z = iArrByte2HexToIntArr[8] == 1;
        String strVp_a = vp_a(iArrByte2HexToIntArr[9]);
        int i4 = iArrByte2HexToIntArr[10];
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[11], 16).intValue();
        String strSubstring = "" + iIntValue;
        int i5 = iArrByte2HexToIntArr[13];
        String str2 = "" + i5;
        int i6 = iArrByte2HexToIntArr[14];
        String str3 = "" + i6;
        if (iIntValue > 9999) {
            strSubstring = TimeData.getSysYear() + "";
        }
        if (iIntValue < 1000) {
            strSubstring = ("0000" + strSubstring).substring(strSubstring.length());
        }
        if (i5 < 10) {
            str2 = "0" + i5;
        }
        if (i6 < 10) {
            str3 = "0" + i6;
        }
        Alarm2Setting alarm2Setting = new Alarm2Setting(i2, i3, strVp_a, i4, strSubstring + "-" + str2 + "-" + str3, z);
        alarm2Setting.setBluetoothAddress(str);
        alarm2Setting.setAlarmId(i);
        alarm2Setting.setMAFlag(str + "-" + i);
        return alarm2Setting;
    }

    private List vp_a(Context context, String str) {
        return com.veepoo.protocol.util.vp_y.vp_a(context).vp_b(str);
    }

    private String vp_a(int i) {
        if (i > 127) {
            return "0000000";
        }
        String binaryString = Integer.toBinaryString(i);
        return ("0000000" + binaryString).substring(binaryString.length());
    }

    public void vp_a(String str, Context context, IAlarm2DataListListener iAlarm2DataListListener, byte[] bArr) {
        EMultiAlarmOprate eMultiAlarmOprateVp_a = vp_a(str, bArr, context);
        if (eMultiAlarmOprateVp_a == EMultiAlarmOprate.UNKONW || eMultiAlarmOprateVp_a == EMultiAlarmOprate.READ_SUCCESS) {
            return;
        }
        if (eMultiAlarmOprateVp_a != EMultiAlarmOprate.DEVICE_ALARM_MODIFY) {
            if (iAlarm2DataListListener != null) {
                iAlarm2DataListListener.onAlarmDataChangeListListener(new AlarmData2(eMultiAlarmOprateVp_a, vp_a(context, str)));
            }
        } else {
            OnDeviceAlarm2ChangedListener onDeviceAlarm2ChangedListener = this.vp_h;
            if (onDeviceAlarm2ChangedListener != null) {
                onDeviceAlarm2ChangedListener.onDeviceAlarm2Changed();
            }
        }
    }

    public EMultiAlarmOprate vp_a(String str, byte[] bArr, Context context) {
        new StringBuilder().append("新闹钟===》handlerMultiAlarm->").append(Arrays.toString(VpBleByteUtil.byte2HexToStrArr(bArr)));
        if (bArr.length < 20) {
            return EMultiAlarmOprate.UNKONW;
        }
        byte b = bArr[1];
        if (b == 3) {
            return EMultiAlarmOprate.DEVICE_ALARM_MODIFY;
        }
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        if (b4 != 2) {
            if (b4 == 1) {
                if (b != 1) {
                    return EMultiAlarmOprate.SETTING_FAIL;
                }
                com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(vp_a(str, bArr));
                return EMultiAlarmOprate.SETTING_SUCCESS;
            }
            if (b4 != 0) {
                return EMultiAlarmOprate.UNKONW;
            }
            if (b != 1) {
                return EMultiAlarmOprate.CLEAR_FAIL;
            }
            com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(str, VpBleByteUtil.byte2HexToIntArr(bArr)[5]);
            return EMultiAlarmOprate.CLEAR_SUCCESS;
        }
        if (b != 1) {
            com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(str);
            return EMultiAlarmOprate.READ_FAIL;
        }
        byte[] bArr2 = this.vp_i;
        if (bArr2[0] == bArr[18] && bArr2[1] == bArr[19]) {
            new StringBuilder().append("新闹钟===》 crc一致， crc = ").append(com.veepoo.protocol.util.vp_g.vp_b(this.vp_i));
            return vp_a(context, bArr, str) ? EMultiAlarmOprate.READ_SUCCESS_NULL : EMultiAlarmOprate.READ_SUCCESS_SAME_CRC;
        }
        if (vp_b(context, bArr, str)) {
            this.vp_e.add(bArr);
            if (b2 == b3) {
                new StringBuilder().append("新闹钟===》 最后一包 =》 清除所有的闹钟").append(com.veepoo.protocol.util.vp_g.vp_b(this.vp_i));
                com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(str);
                Iterator it = this.vp_e.iterator();
                while (it.hasNext()) {
                    Alarm2Setting alarm2SettingVp_a = vp_a(str, (byte[]) it.next());
                    new StringBuilder().append("新闹钟===》 解析的设备端返回闹钟----》 ").append(alarm2SettingVp_a.toDes());
                    com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(alarm2SettingVp_a);
                }
                return EMultiAlarmOprate.READ_SUCCESS_SAVE;
            }
        }
        return EMultiAlarmOprate.READ_SUCCESS;
    }

    private boolean vp_a(Context context, byte[] bArr, String str) {
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte b3 = bArr[5];
        if (b != 0 || b2 != 0 || b3 != 0) {
            return false;
        }
        com.veepoo.protocol.util.vp_y.vp_a(context).vp_a(str);
        return true;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, List list, IAlarm2DataReadListener iAlarm2DataReadListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, list, iAlarm2DataReadListener);
        this.vp_g = iAlarm2DataReadListener;
        vp_a();
        vp_c(vp_b(list), bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(OnDeviceAlarm2ChangedListener onDeviceAlarm2ChangedListener) {
        this.vp_h = onDeviceAlarm2ChangedListener;
    }
}
