package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/LongSeatSetting.class */
public class LongSeatSetting {
    private int startHour;
    private int startMiute;
    private int endHour;
    private int endMinute;
    private int threshold;
    private boolean isOpen;

    public LongSeatSetting(int i, int i2, int i3, int i4, int i5, boolean z) {
        this.startHour = i;
        this.startMiute = i2;
        this.endHour = i3;
        this.endMinute = i4;
        this.threshold = i5;
        this.isOpen = z;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public int getStartMiute() {
        return this.startMiute;
    }

    public void setStartMiute(int i) {
        this.startMiute = i;
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

    public int getThreshold() {
        return this.threshold;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public String toString() {
        return "LongSeatSetting{startHour=" + this.startHour + ", startMiute=" + this.startMiute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", threshold=" + this.threshold + ", isOpen=" + this.isOpen + '}';
    }
}
