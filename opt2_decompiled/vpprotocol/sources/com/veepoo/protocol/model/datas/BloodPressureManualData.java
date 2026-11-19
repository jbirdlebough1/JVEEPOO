package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BloodPressureManualData.class */
public class BloodPressureManualData {
    private int timeStamp;
    private int version;
    private int measurementMode;
    private int heartRate;
    private int systolic;
    private int diastolic;
    private int testStatus;
    private int resultCredibility;
    private int height;
    private int weight;
    private int age;
    private boolean isMale;
    private int testTime;
    private int testFailTimeoutCount;
    private int pumpModel;
    private int afeModel;
    private int accelerationModel;
    private int mcuModel;
    private String algorithmVersion;
    private String softwareVersion;
    private int[] attitudeArray;
    private int[] sportArray;
    private int[] pressureAdcArray;
    private int[] ppgAdcArray;
    private int[] accelerationXArray;
    private int[] accelerationYArray;
    private int[] accelerationZArray;

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

    public int getMeasurementMode() {
        return this.measurementMode;
    }

    public void setMeasurementMode(int i) {
        this.measurementMode = i;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public int getSystolic() {
        return this.systolic;
    }

    public void setSystolic(int i) {
        this.systolic = i;
    }

    public int getDiastolic() {
        return this.diastolic;
    }

    public void setDiastolic(int i) {
        this.diastolic = i;
    }

    public int getTestStatus() {
        return this.testStatus;
    }

    public void setTestStatus(int i) {
        this.testStatus = i;
    }

    public int getResultCredibility() {
        return this.resultCredibility;
    }

    public void setResultCredibility(int i) {
        this.resultCredibility = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public boolean isMale() {
        return this.isMale;
    }

    public void setMale(boolean z) {
        this.isMale = z;
    }

    public int getTestTime() {
        return this.testTime;
    }

    public void setTestTime(int i) {
        this.testTime = i;
    }

    public int getTestFailTimeoutCount() {
        return this.testFailTimeoutCount;
    }

    public void setTestFailTimeoutCount(int i) {
        this.testFailTimeoutCount = i;
    }

    public int getPumpModel() {
        return this.pumpModel;
    }

    public void setPumpModel(int i) {
        this.pumpModel = i;
    }

    public int getAfeModel() {
        return this.afeModel;
    }

    public void setAfeModel(int i) {
        this.afeModel = i;
    }

    public int getAccelerationModel() {
        return this.accelerationModel;
    }

    public void setAccelerationModel(int i) {
        this.accelerationModel = i;
    }

    public int getMcuModel() {
        return this.mcuModel;
    }

    public void setMcuModel(int i) {
        this.mcuModel = i;
    }

    public String getAlgorithmVersion() {
        return this.algorithmVersion;
    }

    public void setAlgorithmVersion(String str) {
        this.algorithmVersion = str;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public void setSoftwareVersion(String str) {
        this.softwareVersion = str;
    }

    public int[] getAttitudeArray() {
        return this.attitudeArray;
    }

    public void setAttitudeArray(int[] iArr) {
        this.attitudeArray = iArr;
    }

    public int[] getSportArray() {
        return this.sportArray;
    }

    public void setSportArray(int[] iArr) {
        this.sportArray = iArr;
    }

    public int[] getPressureAdcArray() {
        return this.pressureAdcArray;
    }

    public void setPressureAdcArray(int[] iArr) {
        this.pressureAdcArray = iArr;
    }

    public int[] getPpgAdcArray() {
        return this.ppgAdcArray;
    }

    public void setPpgAdcArray(int[] iArr) {
        this.ppgAdcArray = iArr;
    }

    public int[] getAccelerationXArray() {
        return this.accelerationXArray;
    }

    public void setAccelerationXArray(int[] iArr) {
        this.accelerationXArray = iArr;
    }

    public int[] getAccelerationYArray() {
        return this.accelerationYArray;
    }

    public void setAccelerationYArray(int[] iArr) {
        this.accelerationYArray = iArr;
    }

    public int[] getAccelerationZArray() {
        return this.accelerationZArray;
    }

    public void setAccelerationZArray(int[] iArr) {
        this.accelerationZArray = iArr;
    }

    public String toString() {
        return "BloodPressureManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", measurementMode=" + this.measurementMode + ", heartRate=" + this.heartRate + ", systolic=" + this.systolic + ", diastolic=" + this.diastolic + ", testStatus=" + this.testStatus + ", resultCredibility=" + this.resultCredibility + ", height=" + this.height + ", weight=" + this.weight + ", age=" + this.age + ", isMale=" + this.isMale + ", testTime=" + this.testTime + ", testFailTimeoutCount=" + this.testFailTimeoutCount + ", pumpModel=" + this.pumpModel + ", afeModel=" + this.afeModel + ", accelerationModel=" + this.accelerationModel + ", mcuModel=" + this.mcuModel + ", algorithmVersion='" + this.algorithmVersion + "', softwareVersion='" + this.softwareVersion + "', attitudeArray=" + Arrays.toString(this.attitudeArray) + ", sportArray=" + Arrays.toString(this.sportArray) + ", pressureAdcArray=" + Arrays.toString(this.pressureAdcArray) + ", ppgAdcArray=" + Arrays.toString(this.ppgAdcArray) + ", accelerationXArray=" + Arrays.toString(this.accelerationXArray) + ", accelerationYArray=" + Arrays.toString(this.accelerationYArray) + ", accelerationZArray=" + Arrays.toString(this.accelerationZArray) + '}';
    }
}
