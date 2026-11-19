package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BodyTemperatureManualData.class */
public class BodyTemperatureManualData {
    private int timeStamp;
    private int version;
    private float baseTemperature;
    private float temperature;

    public int getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(int i) {
        this.timeStamp = i;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public float getBaseTemperature() {
        return this.baseTemperature;
    }

    public void setBaseTemperature(float f) {
        this.baseTemperature = f;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float f) {
        this.temperature = f;
    }

    public String toString() {
        return "BodyTemperatureManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", baseTemperature=" + this.baseTemperature + ", temperature=" + this.temperature + '}';
    }
}
