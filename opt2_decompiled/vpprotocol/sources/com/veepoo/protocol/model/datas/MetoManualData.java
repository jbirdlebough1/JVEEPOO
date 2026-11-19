package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MetoManualData.class */
public class MetoManualData {
    private int timeStamp;
    private int version;
    private int meto;

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

    public int getMeto() {
        return this.meto;
    }

    public void setMeto(int i) {
        this.meto = i;
    }

    public String toString() {
        return "MetoManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", meto=" + this.meto + '}';
    }
}
