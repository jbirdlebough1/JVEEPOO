package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/AlarmSetting.class */
public class AlarmSetting {
    private int alarmTime;
    private boolean isOpen;

    public AlarmSetting(int i, int i2, boolean z) {
        this.alarmTime = (i * 60) + i2;
        this.isOpen = z;
    }

    public int getAlarmTime() {
        return this.alarmTime;
    }

    public int getHour() {
        return this.alarmTime / 60;
    }

    public int getMinute() {
        return this.alarmTime % 60;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public void setAlarmTime(int i) {
        this.alarmTime = i;
    }

    public String toString() {
        return "AlarmSetting{alarmTime=" + (this.alarmTime / 60) + ":" + (this.alarmTime % 60) + ", isOpen=" + this.isOpen + '}';
    }

    public AlarmSetting(int i, boolean z) {
        this.alarmTime = i;
        this.isOpen = z;
    }
}
