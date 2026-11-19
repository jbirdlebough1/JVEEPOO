package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupDetailData.class */
public class MiniCheckupDetailData {
    private MiniCheckupBasePersonalInfo basePersonalInfo;
    private int heartRate;
    private int bloodOxygen;
    private int stress;
    private int emotion;
    private int fatigue;
    private int bloodGlucoseType;
    private float bloodGlucose;
    private float bodyTemperature = -273.15f;
    private float originalTemperature = -273.15f;
    private MiniCheckupBPAirPump bpAirPump;
    private MiniCheckupBPPhotoelectric bpPhotoelectric;
    private int hrv;
    private MiniCheckupBloodComponent bloodComponent;
    private MiniCheckupBodyComponent bodyComponent;
    private MiniCheckupSkinElectricity skinElectricity;
    private int funSp;

    public MiniCheckupBasePersonalInfo getBasePersonalInfo() {
        return this.basePersonalInfo;
    }

    public void setBasePersonalInfo(MiniCheckupBasePersonalInfo miniCheckupBasePersonalInfo) {
        this.basePersonalInfo = miniCheckupBasePersonalInfo;
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

    public int getHrv() {
        return this.hrv;
    }

    public void setHrv(int i) {
        this.hrv = i;
    }

    public int getBloodGlucoseType() {
        return this.bloodGlucoseType;
    }

    public void setBloodGlucoseType(int i) {
        this.bloodGlucoseType = i;
    }

    public float getOriginalTemperature() {
        return this.originalTemperature;
    }

    public void setOriginalTemperature(float f) {
        this.originalTemperature = f;
    }

    public MiniCheckupBPAirPump getBpAirPump() {
        return this.bpAirPump;
    }

    public void setBpAirPump(MiniCheckupBPAirPump miniCheckupBPAirPump) {
        this.bpAirPump = miniCheckupBPAirPump;
    }

    public MiniCheckupBPPhotoelectric getBpPhotoelectric() {
        return this.bpPhotoelectric;
    }

    public void setBpPhotoelectric(MiniCheckupBPPhotoelectric miniCheckupBPPhotoelectric) {
        this.bpPhotoelectric = miniCheckupBPPhotoelectric;
    }

    public MiniCheckupBloodComponent getBloodComponent() {
        return this.bloodComponent;
    }

    public void setBloodComponent(MiniCheckupBloodComponent miniCheckupBloodComponent) {
        this.bloodComponent = miniCheckupBloodComponent;
    }

    public MiniCheckupBodyComponent getBodyComponent() {
        return this.bodyComponent;
    }

    public void setBodyComponent(MiniCheckupBodyComponent miniCheckupBodyComponent) {
        this.bodyComponent = miniCheckupBodyComponent;
    }

    public int getFunSp() {
        return this.funSp;
    }

    public void setFunSp(int i) {
        this.funSp = i;
    }

    public MiniCheckupSkinElectricity getSkinElectricity() {
        return this.skinElectricity;
    }

    public void setSkinElectricity(MiniCheckupSkinElectricity miniCheckupSkinElectricity) {
        this.skinElectricity = miniCheckupSkinElectricity;
    }

    public String toString() {
        return "MiniCheckupDetailData{basePersonalInfo=" + this.basePersonalInfo + ", heartRate=" + this.heartRate + ", bloodOxygen=" + this.bloodOxygen + ", stress=" + this.stress + ", emotion=" + this.emotion + ", fatigue=" + this.fatigue + ", bloodGlucoseType=" + this.bloodGlucoseType + ", bloodGlucose=" + this.bloodGlucose + ", bodyTemperature=" + this.bodyTemperature + ", originalTemperature=" + this.originalTemperature + ", bpAirPump=" + this.bpAirPump + ", bpPhotoelectric=" + this.bpPhotoelectric + ", hrv=" + this.hrv + ", bloodComponent=" + this.bloodComponent + ", bodyComponent=" + this.bodyComponent + ", skinElectricity=" + this.skinElectricity + ", funSp=" + this.funSp + '}';
    }
}
