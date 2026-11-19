package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/BreathBreakRemindSetting.class */
public class BreathBreakRemindSetting {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int oprateSetting;
    private int duringTime;
    private int remindTime;
    private int remindTimeDefault;
    private int minOxygen;
    private int openStatus;

    public BreathBreakRemindSetting() {
    }

    public BreathBreakRemindSetting(int i, int i2, int i3) {
        this.openStatus = i;
        this.minOxygen = i2;
        this.remindTime = i3;
        this.remindTimeDefault = 1;
    }

    public int getDuringTime() {
        return this.duringTime;
    }

    public void setDuringTime(int i) {
        this.duringTime = i;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public int getOprateSetting() {
        return this.oprateSetting;
    }

    public void setOprateSetting(int i) {
        this.oprateSetting = i;
    }

    public int getRemindTime() {
        return this.remindTime;
    }

    public void setRemindTime(int i) {
        this.remindTime = i;
    }

    public int getRemindTimeDefault() {
        return this.remindTimeDefault;
    }

    public void setRemindTimeDefault(int i) {
        this.remindTimeDefault = i;
    }

    public int getMinOxygen() {
        return this.minOxygen;
    }

    public void setMinOxygen(int i) {
        this.minOxygen = i;
    }

    public int getOpenStatus() {
        return this.openStatus;
    }

    public void setOpenStatus(int i) {
        this.openStatus = i;
    }

    public String toString() {
        return "BreathBreakRemindBean{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", oprateSetting=" + this.oprateSetting + ", remindTime=" + this.remindTime + ", remindTimeDefault=" + this.remindTimeDefault + ", minOxygen=" + this.minOxygen + ", openStatus=" + this.openStatus + '}';
    }
}
