package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EScreenLightTime;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ScreenLightTimeData.class */
public class ScreenLightTimeData {
    private EScreenLightTime screenLightState;
    private int currentDuration;
    private int recommendDuration;
    private int maxDuration;
    private int minDuration;

    public ScreenLightTimeData(EScreenLightTime eScreenLightTime, int i, int i2, int i3, int i4) {
        this.screenLightState = eScreenLightTime;
        this.currentDuration = i;
        this.recommendDuration = i2;
        this.maxDuration = i3;
        this.minDuration = i4;
    }

    public EScreenLightTime getScreenLightState() {
        return this.screenLightState;
    }

    public void setScreenLightState(EScreenLightTime eScreenLightTime) {
        this.screenLightState = eScreenLightTime;
    }

    public int getCurrentDuration() {
        return this.currentDuration;
    }

    public void setCurrentDuration(int i) {
        this.currentDuration = i;
    }

    public int getRecommendDuration() {
        return this.recommendDuration;
    }

    public void setRecommendDuration(int i) {
        this.recommendDuration = i;
    }

    public int getMaxDuration() {
        return this.maxDuration;
    }

    public void setMaxDuration(int i) {
        this.maxDuration = i;
    }

    public int getMinDuration() {
        return this.minDuration;
    }

    public void setMinDuration(int i) {
        this.minDuration = i;
    }

    public String toString() {
        return "ScreenLightTimeData{currentDuration=" + this.currentDuration + ", recommendDuration=" + this.recommendDuration + ", maxDuration=" + this.maxDuration + ", minDuration=" + this.minDuration + '}';
    }
}
