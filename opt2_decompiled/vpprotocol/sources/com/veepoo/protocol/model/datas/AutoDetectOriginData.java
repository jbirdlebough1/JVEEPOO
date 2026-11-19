package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AutoDetectOriginData.class */
public class AutoDetectOriginData {
    private TimeData timeData;
    private int spo2hValue;
    private int heartRateValue;

    public AutoDetectOriginData() {
    }

    public AutoDetectOriginData(TimeData timeData, int i, int i2) {
        this.timeData = timeData;
        this.spo2hValue = i;
        this.heartRateValue = i2;
    }

    public TimeData getTimeData() {
        return this.timeData;
    }

    public void setTimeData(TimeData timeData) {
        this.timeData = timeData;
    }

    public int getSpo2hValue() {
        return this.spo2hValue;
    }

    public void setSpo2hValue(int i) {
        this.spo2hValue = i;
    }

    public int getHeartRateValue() {
        return this.heartRateValue;
    }

    public void setHeartRateValue(int i) {
        this.heartRateValue = i;
    }

    public String toString() {
        return "AutoDetectOriginData{timeData=" + this.timeData + ", spo2hValue=" + this.spo2hValue + ", heartRateValue=" + this.heartRateValue + '}';
    }
}
