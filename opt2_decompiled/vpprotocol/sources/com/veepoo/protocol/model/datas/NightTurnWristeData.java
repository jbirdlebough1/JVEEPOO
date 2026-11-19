package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ENightTurnWristeStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/NightTurnWristeData.class */
public class NightTurnWristeData {
    private ENightTurnWristeStatus OprateStauts;
    private boolean isSupportCustomSettingTime;
    private boolean nightTureWirsteStatusOpen;
    private TimeData startTime;
    private TimeData endTime;
    private int level;
    private int defaultLevel;

    public int getDefaultLevel() {
        return this.defaultLevel;
    }

    public void setDefaultLevel(int i) {
        this.defaultLevel = i;
    }

    public boolean isSupportCustomSettingTime() {
        return this.isSupportCustomSettingTime;
    }

    public void setSupportCustomSettingTime(boolean z) {
        this.isSupportCustomSettingTime = z;
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

    public ENightTurnWristeStatus getOprateStauts() {
        return this.OprateStauts;
    }

    public void setOprateStauts(ENightTurnWristeStatus eNightTurnWristeStatus) {
        this.OprateStauts = eNightTurnWristeStatus;
    }

    public boolean isNightTureWirsteStatusOpen() {
        return this.nightTureWirsteStatusOpen;
    }

    public void setNightTureWirsteStatusOpen(boolean z) {
        this.nightTureWirsteStatusOpen = z;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String toString() {
        return "NightTurnWristeData{OprateStauts=" + this.OprateStauts + ", nightTureWirsteStatusOpen=" + this.nightTureWirsteStatusOpen + ", startTime=" + this.startTime.getHour() + ":" + this.startTime.getMinute() + ", endTime=" + this.endTime.getHour() + ":" + this.endTime.getMinute() + ", level=" + this.level + ", defaultLevel=" + this.defaultLevel + '}';
    }
}
