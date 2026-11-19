package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EAllSetType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/AllSetSetting.class */
public class AllSetSetting {
    private EAllSetType type;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int oprate;
    private int openState;

    public AllSetSetting(EAllSetType eAllSetType, int i, int i2, int i3, int i4, int i5, int i6) {
        this.type = eAllSetType;
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
        this.oprate = i5;
        this.openState = i6;
    }

    public EAllSetType getType() {
        return this.type;
    }

    public void setType(EAllSetType eAllSetType) {
        this.type = eAllSetType;
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

    public int getOprate() {
        return this.oprate;
    }

    public int getOpenState() {
        return this.openState;
    }

    public void setOpenState(int i) {
        this.openState = i;
    }

    public void setOprate(int i) {
        this.oprate = i;
    }

    public String toString() {
        return "AllSettingBean{type=" + this.type + ", startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", oprate=" + this.oprate + '}';
    }
}
