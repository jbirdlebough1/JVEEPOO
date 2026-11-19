package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupManualData.class */
public class MiniCheckupManualData {
    private int timeStamp;
    private int version;
    private int type;
    private int heart;
    private int oxygen;
    private int pressure;
    private int emotion;
    private int fatigue;
    private float bloodGlucose;
    private int bloodGlucoseRisk;
    private float temperature;
    private float baseTemperature;
    private int highValue;
    private int lowValue;
    private int hrv;
    private int id;
    private int bloodGlucoseType;
    private MiniCheckupBasePersonalInfo basePersonalInfo;
    private MiniCheckupBPPhotoelectric bpPhotoelectric;
    private MiniCheckupBPAirPump bpAirPump;
    private MiniCheckupSkinElectricity skinElectricity;
    private MiniCheckupBloodComponent bloodComponent;
    private MiniCheckupBodyComponent bodyComponent;

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

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getHeart() {
        return this.heart;
    }

    public void setHeart(int i) {
        this.heart = i;
    }

    public int getOxygen() {
        return this.oxygen;
    }

    public void setOxygen(int i) {
        this.oxygen = i;
    }

    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int i) {
        this.pressure = i;
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

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float f) {
        this.temperature = f;
    }

    public float getBaseTemperature() {
        return this.baseTemperature;
    }

    public void setBaseTemperature(float f) {
        this.baseTemperature = f;
    }

    public int getHighValue() {
        return this.highValue;
    }

    public void setHighValue(int i) {
        this.highValue = i;
    }

    public int getLowValue() {
        return this.lowValue;
    }

    public void setLowValue(int i) {
        this.lowValue = i;
    }

    public int getHrv() {
        return this.hrv;
    }

    public void setHrv(int i) {
        this.hrv = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getBloodGlucoseRisk() {
        return this.bloodGlucoseRisk;
    }

    public void setBloodGlucoseRisk(int i) {
        this.bloodGlucoseRisk = i;
    }

    public int getBloodGlucoseType() {
        return this.bloodGlucoseType;
    }

    public void setBloodGlucoseType(int i) {
        this.bloodGlucoseType = i;
    }

    public MiniCheckupBasePersonalInfo getBasePersonalInfo() {
        return this.basePersonalInfo;
    }

    public void setBasePersonalInfo(MiniCheckupBasePersonalInfo miniCheckupBasePersonalInfo) {
        this.basePersonalInfo = miniCheckupBasePersonalInfo;
    }

    public MiniCheckupBPPhotoelectric getBpPhotoelectric() {
        return this.bpPhotoelectric;
    }

    public void setBpPhotoelectric(MiniCheckupBPPhotoelectric miniCheckupBPPhotoelectric) {
        this.bpPhotoelectric = miniCheckupBPPhotoelectric;
    }

    public MiniCheckupBPAirPump getBpAirPump() {
        return this.bpAirPump;
    }

    public void setBpAirPump(MiniCheckupBPAirPump miniCheckupBPAirPump) {
        this.bpAirPump = miniCheckupBPAirPump;
    }

    public MiniCheckupSkinElectricity getSkinElectricity() {
        return this.skinElectricity;
    }

    public void setSkinElectricity(MiniCheckupSkinElectricity miniCheckupSkinElectricity) {
        this.skinElectricity = miniCheckupSkinElectricity;
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

    public String toString() {
        return "MiniCheckUpManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", type=" + this.type + ", heart=" + this.heart + ", oxygen=" + this.oxygen + ", pressure=" + this.pressure + ", emotion=" + this.emotion + ", fatigue=" + this.fatigue + ", bloodGlucose=" + this.bloodGlucose + ", bloodGlucoseRisk=" + this.bloodGlucoseRisk + ", temperature=" + this.temperature + ", baseTemperature=" + this.baseTemperature + ", highValue=" + this.highValue + ", lowValue=" + this.lowValue + ", hrv=" + this.hrv + ", id=" + this.id + ", bloodGlucoseType=" + this.bloodGlucoseType + ", basePersonalInfo=" + this.basePersonalInfo + ", bpPhotoelectric=" + this.bpPhotoelectric + ", bpAirPump=" + this.bpAirPump + ", skinElectricity=" + this.skinElectricity + ", bloodComponent=" + this.bloodComponent + ", bodyComponent=" + this.bodyComponent + '}';
    }
}
