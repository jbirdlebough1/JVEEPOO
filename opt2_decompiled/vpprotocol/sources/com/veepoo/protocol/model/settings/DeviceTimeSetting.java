package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.ETimeMode;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/DeviceTimeSetting.class */
public class DeviceTimeSetting {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private ETimeMode eTimeMode;

    public DeviceTimeSetting(int i, int i2, int i3, int i4, int i5, int i6) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int i) {
        this.month = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int i) {
        this.hour = i;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int i) {
        this.second = i;
    }

    public ETimeMode geteTimeMode() {
        return this.eTimeMode;
    }

    public void seteTimeMode(ETimeMode eTimeMode) {
        this.eTimeMode = eTimeMode;
    }

    public DeviceTimeSetting(int i, int i2, int i3, int i4, int i5, int i6, ETimeMode eTimeMode) {
        this.year = i;
        this.month = i2;
        this.day = i3;
        this.hour = i4;
        this.minute = i5;
        this.second = i6;
        this.eTimeMode = eTimeMode;
    }
}
