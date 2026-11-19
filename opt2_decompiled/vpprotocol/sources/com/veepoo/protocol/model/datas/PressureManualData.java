package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PressureManualData.class */
public class PressureManualData {
    private int timeStamp;
    private int version;
    private int pressure;

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

    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int i) {
        this.pressure = i;
    }

    public String toString() {
        return "PressureManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", pressure=" + this.pressure + '}';
    }
}
