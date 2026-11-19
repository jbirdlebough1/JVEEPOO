package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/FatigueManualData.class */
public class FatigueManualData {
    private int timeStamp;
    private int version;
    private int fatigue;

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

    public int getFatigue() {
        return this.fatigue;
    }

    public void setFatigue(int i) {
        this.fatigue = i;
    }

    public String toString() {
        return "FatigueManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", fatigue=" + this.fatigue + '}';
    }
}
