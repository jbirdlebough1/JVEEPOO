package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/EcgDetectInfo.class */
public class EcgDetectInfo {
    private int frequency;
    private int drawFrequency;

    public EcgDetectInfo() {
    }

    public EcgDetectInfo(int i, int i2) {
        this.frequency = i;
        this.drawFrequency = i2;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getDrawFrequency() {
        return this.drawFrequency;
    }

    public void setDrawFrequency(int i) {
        this.drawFrequency = i;
    }

    public String toString() {
        return "EcgDetectInfo{frequency=" + this.frequency + ", drawFrequency=" + this.drawFrequency + '}';
    }
}
