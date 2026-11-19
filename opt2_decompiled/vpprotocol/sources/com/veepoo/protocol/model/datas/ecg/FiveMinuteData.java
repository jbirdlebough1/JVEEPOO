package com.veepoo.protocol.model.datas.ecg;

import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.TimeData;
import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ecg/FiveMinuteData.class */
public class FiveMinuteData {
    private TimeData timeBean;
    private SportFiveMinuteData sportFiveMinuteData;
    private int[] sleep;
    private int[] rate;
    private int[] heart;
    private int[] breath;
    private int[] bp;
    int vp_a;
    private int pressure;
    private float met;
    private int[] sleepSport;
    private int[] sleepStatus;
    private int[] resetTagContent;
    private HRVFiveMinuteData hrvFiveMinuteData;
    private Spo2hMinuteData spo2HMinuteData;
    BloodComponent vp_c;
    private float baseTemperature;
    private float temperature;
    private int[] sportStatus;
    int vp_b = 0;
    private int sportStatusVersion = 0;

    public FiveMinuteData() {
    }

    private String getArrayString(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? "" : Arrays.toString(iArr);
    }

    public int[] getSleepSport() {
        return this.sleepSport;
    }

    public void setSleepSport(int[] iArr) {
        this.sleepSport = iArr;
    }

    public Spo2hMinuteData getSpo2HMinuteData() {
        return this.spo2HMinuteData;
    }

    public void setSpo2HMinuteData(Spo2hMinuteData spo2hMinuteData) {
        this.spo2HMinuteData = spo2hMinuteData;
    }

    public TimeData getTimeBean() {
        return this.timeBean;
    }

    public void setTimeBean(TimeData timeData) {
        this.timeBean = timeData;
    }

    public SportFiveMinuteData getSportFiveMinuteData() {
        return this.sportFiveMinuteData;
    }

    public void setSportFiveMinuteData(SportFiveMinuteData sportFiveMinuteData) {
        this.sportFiveMinuteData = sportFiveMinuteData;
    }

    public int[] getSleep() {
        return this.sleep;
    }

    public void setSleep(int[] iArr) {
        this.sleep = iArr;
    }

    public int[] getRate() {
        return this.rate;
    }

    public void setRate(int[] iArr) {
        this.rate = iArr;
    }

    public int[] getHeart() {
        return this.heart;
    }

    public void setHeart(int[] iArr) {
        this.heart = iArr;
    }

    public int[] getBreath() {
        return this.breath;
    }

    public void setBreath(int[] iArr) {
        this.breath = iArr;
    }

    public HRVFiveMinuteData getHrvFiveMinuteData() {
        return this.hrvFiveMinuteData;
    }

    public void setHrvFiveMinuteData(HRVFiveMinuteData hRVFiveMinuteData) {
        this.hrvFiveMinuteData = hRVFiveMinuteData;
    }

    public int[] getBp() {
        return this.bp;
    }

    public void setBp(int[] iArr) {
        this.bp = iArr;
    }

    public int getBloodGlucose() {
        return this.vp_a;
    }

    public void setBloodGlucose(int i) {
        this.vp_a = i;
    }

    public BloodComponent getBloodComposition() {
        return this.vp_c;
    }

    public void setBloodComposition(BloodComponent bloodComponent) {
        this.vp_c = bloodComponent;
    }

    public float getBaseTemperature() {
        return this.baseTemperature;
    }

    public void setBaseTemperature(float f) {
        this.baseTemperature = f;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public void setTemperature(float f) {
        this.temperature = f;
    }

    public int getBloodGlucoseRiskLevel() {
        return this.vp_b;
    }

    public void setBloodGlucoseRiskLevel(int i) {
        this.vp_b = i;
    }

    public int[] getSleepStatus() {
        return this.sleepStatus;
    }

    public void setSleepStatus(int[] iArr) {
        this.sleepStatus = iArr;
    }

    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int i) {
        this.pressure = i;
    }

    public int[] getResetTagContent() {
        return this.resetTagContent;
    }

    public void setResetTagContent(int[] iArr) {
        this.resetTagContent = iArr;
    }

    public float getMet() {
        return this.met;
    }

    public void setMet(float f) {
        this.met = f;
    }

    public BloodComponent getBloodComponent() {
        return this.vp_c;
    }

    public void setBloodComponent(BloodComponent bloodComponent) {
        this.vp_c = bloodComponent;
    }

    public int getSportStatusVersion() {
        return this.sportStatusVersion;
    }

    public void setSportStatusVersion(int i) {
        this.sportStatusVersion = i;
    }

    public int[] getSportStatus() {
        return this.sportStatus;
    }

    public void setSportStatus(int[] iArr) {
        this.sportStatus = iArr;
    }

    public void initSportStatus(int[] iArr) {
        if (iArr == null || iArr.length != 6) {
            return;
        }
        this.sportStatusVersion = iArr[0];
        int[] iArr2 = new int[5];
        this.sportStatus = iArr2;
        System.arraycopy(iArr, 1, iArr2, 0, iArr.length - 1);
    }

    public String getBloodGlucoseRiskLevelDes() {
        int i = this.vp_b;
        if (i == 1) {
            return "低风险";
        }
        if (i != 2) {
            return i != 3 ? "【0】：不支持或app端计算" : "高风险";
        }
        return "中风险";
    }

    public String toString() {
        return "FiveMinuteData{timeBean=" + this.timeBean + ", sportFiveMinuteData=" + this.sportFiveMinuteData + ", sleep=" + Arrays.toString(this.sleep) + ", sleepStatus=" + getArrayString(this.sleepStatus) + ", rate=" + Arrays.toString(this.rate) + ", heart=" + Arrays.toString(this.heart) + ", breath=" + Arrays.toString(this.breath) + ", bp=" + Arrays.toString(this.bp) + ", bloodGlucose=" + this.vp_a + ", bloodGlucoseRiskLevel=" + getBloodGlucoseRiskLevelDes() + ", sleepSport=" + Arrays.toString(this.sleepSport) + ", hrvFiveMinuteData=" + this.hrvFiveMinuteData + ", spo2HMinuteData=" + this.spo2HMinuteData + ", bloodComposition=" + this.vp_c + ", baseTemperature=" + this.baseTemperature + ", temperature=" + this.temperature + ", resetTagContent=" + this.resetTagContent + ", pressure=" + this.pressure + ", met=" + this.met + '}';
    }

    public FiveMinuteData(TimeData timeData, SportFiveMinuteData sportFiveMinuteData, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int i, HRVFiveMinuteData hRVFiveMinuteData, Spo2hMinuteData spo2hMinuteData) {
        this.timeBean = timeData;
        this.sportFiveMinuteData = sportFiveMinuteData;
        this.sleep = iArr;
        this.rate = iArr2;
        this.heart = iArr3;
        this.breath = iArr4;
        this.bp = iArr5;
        this.hrvFiveMinuteData = hRVFiveMinuteData;
        this.spo2HMinuteData = spo2hMinuteData;
        this.vp_a = i;
    }
}
