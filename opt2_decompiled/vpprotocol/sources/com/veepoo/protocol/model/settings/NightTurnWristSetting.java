package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.datas.TimeData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/NightTurnWristSetting.class */
public class NightTurnWristSetting {
    private boolean isOpen;
    private TimeData startTime;
    private TimeData endTime;
    private int level;

    public NightTurnWristSetting() {
    }

    public NightTurnWristSetting(boolean z, TimeData timeData, TimeData timeData2, int i) {
        this.isOpen = z;
        this.startTime = timeData;
        this.endTime = timeData2;
        this.level = i;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public TimeData getStartTime() {
        return this.startTime;
    }

    public void setStartTime(TimeData timeData) {
        this.startTime = timeData;
    }

    public TimeData getEndTime() {
        return this.endTime;
    }

    public void setEndTime(TimeData timeData) {
        this.endTime = timeData;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String toString() {
        return "NightTurnWristSetting{isOpen=" + this.isOpen + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", level=" + this.level + '}';
    }
}
