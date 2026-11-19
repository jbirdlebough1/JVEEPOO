package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/Spo2hOriginData.class */
public class Spo2hOriginData {
    private String date;
    private TimeData mTime;
    private int heartValue;
    private int sportValue;
    private int oxygenValue;
    private int apneaResult;
    private int isHypoxia;
    private int hypoxiaTime;
    private int hypopnea;
    private int cardiacLoad;
    private int hRVariation;
    private int stepValue;
    private int respirationRate;
    private int temp1;
    private int allPackNumer;
    private int currentPackNumber;

    public Spo2hOriginData() {
    }

    public Spo2hOriginData(String str, TimeData timeData, int i, int i2, int i3) {
        this.date = str;
        this.mTime = timeData;
        this.oxygenValue = i;
        this.allPackNumer = i2;
        this.currentPackNumber = i3;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public TimeData getmTime() {
        return this.mTime;
    }

    public void setmTime(TimeData timeData) {
        this.mTime = timeData;
    }

    public int getHeartValue() {
        return this.heartValue;
    }

    public void setHeartValue(int i) {
        this.heartValue = i;
    }

    public int getSportValue() {
        return this.sportValue;
    }

    public void setSportValue(int i) {
        this.sportValue = i;
    }

    public int getOxygenValue() {
        return this.oxygenValue;
    }

    public void setOxygenValue(int i) {
        this.oxygenValue = i;
    }

    public int getApneaResult() {
        return this.apneaResult;
    }

    public void setApneaResult(int i) {
        this.apneaResult = i;
    }

    public int getIsHypoxia() {
        return this.isHypoxia;
    }

    public void setIsHypoxia(int i) {
        this.isHypoxia = i;
    }

    public int getHypoxiaTime() {
        return this.hypoxiaTime;
    }

    public void setHypoxiaTime(int i) {
        this.hypoxiaTime = i;
    }

    public int getHypopnea() {
        return this.hypopnea;
    }

    public void setHypopnea(int i) {
        this.hypopnea = i;
    }

    public int getCardiacLoad() {
        return this.cardiacLoad;
    }

    public void setCardiacLoad(int i) {
        this.cardiacLoad = i;
    }

    public int gethRVariation() {
        return this.hRVariation;
    }

    public void sethRVariation(int i) {
        this.hRVariation = i;
    }

    public int getStepValue() {
        return this.stepValue;
    }

    public void setStepValue(int i) {
        this.stepValue = i;
    }

    public int getRespirationRate() {
        return this.respirationRate;
    }

    public void setRespirationRate(int i) {
        this.respirationRate = i;
    }

    public int getTemp1() {
        return this.temp1;
    }

    public void setTemp1(int i) {
        this.temp1 = i;
    }

    public int getAllPackNumner() {
        return this.allPackNumer;
    }

    public void setAllPackNumber(int i) {
        this.allPackNumer = i;
    }

    public int getCurrentPackNumber() {
        return this.currentPackNumber;
    }

    public void setCurrentPackNumber(int i) {
        this.currentPackNumber = i;
    }

    public String toString() {
        return "Spo2hOriginData{allPackNumber='" + this.allPackNumer + "'currentPackNumber='" + this.currentPackNumber + "'date='" + this.date + "', mTime=" + this.mTime + ", heartValue=" + this.heartValue + ", sportValue=" + this.sportValue + ", oxygenValue=" + this.oxygenValue + ", apneaResult=" + this.apneaResult + ", isHypoxia=" + this.isHypoxia + ", hypoxiaTime=" + this.hypoxiaTime + ", hypopnea=" + this.hypopnea + ", cardiacLoad=" + this.cardiacLoad + ", hRVariation=" + this.hRVariation + ", stepValue=" + this.stepValue + ", respirationRate=" + this.respirationRate + ", temp1=" + this.temp1 + '}';
    }

    public Spo2hOriginData(String str, TimeData timeData, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        this.date = str;
        this.mTime = timeData;
        this.heartValue = i;
        this.sportValue = i2;
        this.oxygenValue = i3;
        this.apneaResult = i4;
        this.isHypoxia = i5;
        this.hypoxiaTime = i6;
        this.hypopnea = i7;
        this.cardiacLoad = i8;
        this.hRVariation = i9;
        this.stepValue = i10;
        this.respirationRate = i11;
        this.temp1 = i12;
        this.allPackNumer = i13;
        this.currentPackNumber = i14;
    }
}
