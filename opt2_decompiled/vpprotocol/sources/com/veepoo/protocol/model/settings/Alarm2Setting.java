package com.veepoo.protocol.model.settings;

import android.text.TextUtils;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/Alarm2Setting.class */
public class Alarm2Setting implements Comparable<Alarm2Setting> {
    public String MAFlag;
    public String BluetoothAddress;
    public int alarmId;
    public int alarmHour;
    public int alarmMinute;
    public String repeatStatus;
    public String unRepeatDate;
    public int scene;
    public boolean isOpen;

    public Alarm2Setting() {
    }

    public Alarm2Setting(int i, int i2, int i3, String str, int i4, String str2, boolean z) {
        this.alarmId = i;
        this.alarmHour = i2;
        this.alarmMinute = i3;
        this.repeatStatus = str;
        this.scene = i4;
        this.unRepeatDate = str2;
        this.isOpen = z;
    }

    public String getMAFlag() {
        return this.MAFlag;
    }

    public void setMAFlag(String str) {
        this.MAFlag = str;
    }

    public String getBluetoothAddress() {
        return this.BluetoothAddress;
    }

    public void setBluetoothAddress(String str) {
        this.BluetoothAddress = str;
    }

    public int getAlarmId() {
        return this.alarmId;
    }

    public void setAlarmId(int i) {
        this.alarmId = i;
    }

    public int getAlarmHour() {
        return this.alarmHour;
    }

    public void setAlarmHour(int i) {
        this.alarmHour = i;
    }

    public int getAlarmMinute() {
        return this.alarmMinute;
    }

    public void setAlarmMinute(int i) {
        this.alarmMinute = i;
    }

    public String getRepeatStatus() {
        return this.repeatStatus;
    }

    public int getRepeatIntStatus() {
        if (TextUtils.isEmpty(this.repeatStatus)) {
            return -1;
        }
        try {
            try {
                return Integer.valueOf(this.repeatStatus, 2).intValue();
            } catch (Exception unused) {
                throw new NumberFormatException("闹钟的repeat转化不了整型=" + this.repeatStatus);
            }
        } catch (Throwable unused2) {
            return -1;
        }
    }

    public int getScene() {
        return this.scene;
    }

    public void setScene(int i) {
        this.scene = i;
    }

    public void setRepeatStatus(String str) {
        this.repeatStatus = str;
    }

    public String getUnRepeatDate() {
        return this.unRepeatDate;
    }

    public void setUnRepeatDate(String str) {
        this.unRepeatDate = str;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public String toString() {
        return "\nMultiAlarmData{MAFlag='" + this.MAFlag + "', BluetoothAddress='" + this.BluetoothAddress + "', alarmId=" + this.alarmId + ", alarmHour=" + this.alarmHour + ", alarmMinute=" + this.alarmMinute + ", repeatStatus='" + this.repeatStatus + "', unRepeatDate='" + this.unRepeatDate + "', scene=" + this.scene + ", isOpen=" + this.isOpen + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(Alarm2Setting alarm2Setting) {
        return Integer.compare(this.alarmId, alarm2Setting.alarmId);
    }

    public String toDes() {
        return String.format(Locale.CHINA, "ID:%02d, 时间:%02d:%02d", Integer.valueOf(this.alarmId), Integer.valueOf(this.alarmHour), Integer.valueOf(this.alarmMinute)) + ", 开关：" + (this.isOpen ? "开" : "关");
    }

    public Alarm2Setting(int i, int i2, String str, int i3, String str2, boolean z) {
        this.alarmHour = i;
        this.alarmMinute = i2;
        this.repeatStatus = str;
        this.scene = i3;
        this.unRepeatDate = str2;
        this.isOpen = z;
    }
}
