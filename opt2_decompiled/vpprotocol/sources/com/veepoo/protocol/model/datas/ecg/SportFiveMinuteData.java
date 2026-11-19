package com.veepoo.protocol.model.datas.ecg;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ecg/SportFiveMinuteData.class */
public class SportFiveMinuteData {
    private int step;
    private int sport;
    private int dis;
    private int kal;
    private int pose;
    private int wear;

    public SportFiveMinuteData() {
    }

    public SportFiveMinuteData(int i, int i2, int i3, int i4, int i5, int i6) {
        this.step = i;
        this.sport = i2;
        this.dis = i3;
        this.kal = i4;
        this.pose = i5;
        this.wear = i6;
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public int getSport() {
        return this.sport;
    }

    public void setSport(int i) {
        this.sport = i;
    }

    public int getDis() {
        return this.dis;
    }

    public void setDis(int i) {
        this.dis = i;
    }

    public int getKal() {
        return this.kal;
    }

    public void setKal(int i) {
        this.kal = i;
    }

    public int getPose() {
        return this.pose;
    }

    public void setPose(int i) {
        this.pose = i;
    }

    public int getWear() {
        return this.wear;
    }

    public void setWear(int i) {
        this.wear = i;
    }

    public String toString() {
        return "SportFiveMinuteData{step=" + this.step + ", sport=" + this.sport + ", dis=" + this.dis + ", kal=" + this.kal + ", pose=" + this.pose + ", wear=" + this.wear + '}';
    }
}
