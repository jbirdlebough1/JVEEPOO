package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ELongSeatStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/LongSeatData.class */
public class LongSeatData {
    private ELongSeatStatus status;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int threshold;
    private boolean isOpen;

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public ELongSeatStatus getStatus() {
        return this.status;
    }

    public void setStatus(ELongSeatStatus eLongSeatStatus) {
        this.status = eLongSeatStatus;
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

    public int getThreshold() {
        return this.threshold;
    }

    public void setThreshold(int i) {
        this.threshold = i;
    }

    public String toString() {
        return "LongSeatData{status=" + this.status + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", threshold=" + this.threshold + ", isOpen=" + this.isOpen + '}';
    }
}
