package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/InsomniaTimeData.class */
public class InsomniaTimeData {
    private TimeData startTime;
    private TimeData stopTime;

    public InsomniaTimeData(TimeData timeData, TimeData timeData2) {
        this.startTime = timeData;
        this.stopTime = timeData2;
    }

    public TimeData getStartTime() {
        return this.startTime;
    }

    public void setStartTime(TimeData timeData) {
        this.startTime = timeData;
    }

    public TimeData getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(TimeData timeData) {
        this.stopTime = timeData;
    }

    public String toString() {
        return "InsomniaTimeData{startTime=" + this.startTime.getClock() + ", stopTime=" + this.stopTime.getClock() + '}';
    }
}
