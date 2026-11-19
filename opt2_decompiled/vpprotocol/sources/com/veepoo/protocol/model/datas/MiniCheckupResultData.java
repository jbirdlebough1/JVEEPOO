package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupResultData.class */
public class MiniCheckupResultData {
    private int heartRate;
    private int bloodOxygen;
    private int stress;

    @Deprecated
    private int emotion;

    @Deprecated
    private int fatigue;
    private float bloodGlucose;
    private float bodyTemperature;
    private int systolicBloodPressure;
    private int diastolicBloodPressure;
    private int hrv;

    public MiniCheckupResultData() {
    }

    public MiniCheckupResultData(int i, int i2, int i3, int i4, int i5, float f, float f2, int i6, int i7, int i8) {
        this.heartRate = i;
        this.bloodOxygen = i2;
        this.stress = i3;
        this.emotion = i4;
        this.fatigue = i5;
        this.bloodGlucose = f;
        this.bodyTemperature = f2;
        this.systolicBloodPressure = i6;
        this.diastolicBloodPressure = i7;
        this.hrv = i8;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public int getBloodOxygen() {
        return this.bloodOxygen;
    }

    public void setBloodOxygen(int i) {
        this.bloodOxygen = i;
    }

    public int getStress() {
        return this.stress;
    }

    public void setStress(int i) {
        this.stress = i;
    }

    public int getEmotion() {
        return this.emotion;
    }

    public void setEmotion(int i) {
        this.emotion = i;
    }

    public int getFatigue() {
        return this.fatigue;
    }

    public void setFatigue(int i) {
        this.fatigue = i;
    }

    public float getBloodGlucose() {
        return this.bloodGlucose;
    }

    public void setBloodGlucose(float f) {
        this.bloodGlucose = f;
    }

    public float getBodyTemperature() {
        return this.bodyTemperature;
    }

    public void setBodyTemperature(float f) {
        this.bodyTemperature = f;
    }

    public int getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public void setSystolicBloodPressure(int i) {
        this.systolicBloodPressure = i;
    }

    public int getDiastolicBloodPressure() {
        return this.diastolicBloodPressure;
    }

    public void setDiastolicBloodPressure(int i) {
        this.diastolicBloodPressure = i;
    }

    public int getHrv() {
        return this.hrv;
    }

    public void setHrv(int i) {
        this.hrv = i;
    }

    public String toString() {
        return "MiniCheckupResultData{heartRate=" + this.heartRate + ", bloodOxygen=" + this.bloodOxygen + ", stress=" + this.stress + ", emotion=" + this.emotion + ", fatigue=" + this.fatigue + ", bloodGlucose=" + this.bloodGlucose + ", bodyTemperature=" + this.bodyTemperature + ", systolicBloodPressure=" + this.systolicBloodPressure + ", diastolicBloodPressure=" + this.diastolicBloodPressure + ", hrv=" + this.hrv + '}';
    }
}
