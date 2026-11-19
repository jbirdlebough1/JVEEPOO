package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ScreenSetting.class */
public class ScreenSetting {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int level;
    private int otherLeverl;
    private int auto;
    private int maxLevel;

    public ScreenSetting(int i, int i2, int i3, int i4, int i5, int i6) {
        this.auto = 0;
        this.maxLevel = 4;
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
        this.level = i5;
        this.otherLeverl = i6;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public int getOtherLeverl() {
        return this.otherLeverl;
    }

    public void setOtherLeverl(int i) {
        this.otherLeverl = i;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getAuto() {
        return this.auto;
    }

    public void setAuto(int i) {
        this.auto = i;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public void setMaxLevel(int i) {
        this.maxLevel = i;
    }

    public String toString() {
        return "ScreenSetting{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", level=" + this.level + ", otherLeverl=" + this.otherLeverl + ", auto=" + this.auto + ", maxLevel=" + this.maxLevel + '}';
    }

    public ScreenSetting(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.maxLevel = 4;
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
        this.level = i5;
        this.otherLeverl = i6;
        this.auto = i7;
    }
}
