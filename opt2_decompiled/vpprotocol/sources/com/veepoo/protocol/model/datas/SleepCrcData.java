package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SleepCrcData.class */
public class SleepCrcData {
    private int baseSleepCrc;
    private int insomniaCrc;
    private int sleepCurveCrc;
    private int allSleepCrc;

    public SleepCrcData() {
    }

    public SleepCrcData(int i, int i2, int i3, int i4) {
        this.baseSleepCrc = i;
        this.insomniaCrc = i2;
        this.sleepCurveCrc = i3;
        this.allSleepCrc = i4;
    }

    public int getBaseSleepCrc() {
        return this.baseSleepCrc;
    }

    public void setBaseSleepCrc(int i) {
        this.baseSleepCrc = i;
    }

    public int getInsomniaCrc() {
        return this.insomniaCrc;
    }

    public void setInsomniaCrc(int i) {
        this.insomniaCrc = i;
    }

    public int getSleepCurveCrc() {
        return this.sleepCurveCrc;
    }

    public void setSleepCurveCrc(int i) {
        this.sleepCurveCrc = i;
    }

    public int getAllSleepCrc() {
        return this.allSleepCrc;
    }

    public void setAllSleepCrc(int i) {
        this.allSleepCrc = i;
    }

    public String toString() {
        return "SleepCrcData{baseSleepCrc=" + this.baseSleepCrc + ", insomniaCrc=" + this.insomniaCrc + ", sleepCurveCrc=" + this.sleepCurveCrc + ", allSleepCrc=" + this.allSleepCrc + '}';
    }
}
