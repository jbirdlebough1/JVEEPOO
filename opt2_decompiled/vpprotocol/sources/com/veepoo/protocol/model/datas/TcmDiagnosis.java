package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TcmDiagnosis.class */
public class TcmDiagnosis {
    private boolean isSuccess;
    private TimeData timeBean;
    private int frequency;
    private int[] powers;
    private int[] filterSignals;
    private int[] ppgSignals;
    private int duration;
    private int leadOffType;
    private int[] diagnosis8;
    private int heartRate;
    private int respRate;
    private int hrv;
    private int qtTime;
    private int diseaseRisk;
    private int pressureIndex;
    private int fatigueIndex;
    private int myocarditisRisk;
    private int chdRisk;
    private int angioscleroticRisk;
    private int[] risk32;
    private int qrsTime;
    private int qrsAmp;
    private int pwvMeanVal;
    private int stMeanAmp;
    private int diseaseSdnn;
    private int diseaseRmssd;
    private float pat;
    private int type;
    private int state;
    private int[] detectHeart;
    private int[] detectBreath;
    private int[] detectHrv;
    private int[] detectQT;
    private int id;

    public TcmDiagnosis() {
        this.powers = new int[1];
        this.filterSignals = new int[1];
        this.ppgSignals = new int[1];
        this.detectHeart = new int[1];
        this.detectBreath = new int[1];
        this.detectHrv = new int[1];
        this.detectQT = new int[1];
    }

    public int getLeadOffType() {
        return this.leadOffType;
    }

    public void setLeadOffType(int i) {
        this.leadOffType = i;
    }

    public int[] getDiagnosis8() {
        return this.diagnosis8;
    }

    public void setDiagnosis8(int[] iArr) {
        this.diagnosis8 = iArr;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public int getRespRate() {
        return this.respRate;
    }

    public void setRespRate(int i) {
        this.respRate = i;
    }

    public int getHrv() {
        return this.hrv;
    }

    public void setHrv(int i) {
        this.hrv = i;
    }

    public int getQtTime() {
        return this.qtTime;
    }

    public void setQtTime(int i) {
        this.qtTime = i;
    }

    public int getDiseaseRisk() {
        return this.diseaseRisk;
    }

    public void setDiseaseRisk(int i) {
        this.diseaseRisk = i;
    }

    public int getPressureIndex() {
        return this.pressureIndex;
    }

    public void setPressureIndex(int i) {
        this.pressureIndex = i;
    }

    public int getFatigueIndex() {
        return this.fatigueIndex;
    }

    public void setFatigueIndex(int i) {
        this.fatigueIndex = i;
    }

    public int getMyocarditisRisk() {
        return this.myocarditisRisk;
    }

    public void setMyocarditisRisk(int i) {
        this.myocarditisRisk = i;
    }

    public int getChdRisk() {
        return this.chdRisk;
    }

    public void setChdRisk(int i) {
        this.chdRisk = i;
    }

    public int getAngioscleroticRisk() {
        return this.angioscleroticRisk;
    }

    public void setAngioscleroticRisk(int i) {
        this.angioscleroticRisk = i;
    }

    public int[] getRisk32() {
        return this.risk32;
    }

    public void setRisk32(int[] iArr) {
        this.risk32 = iArr;
    }

    public int getQrsTime() {
        return this.qrsTime;
    }

    public void setQrsTime(int i) {
        this.qrsTime = i;
    }

    public int getQrsAmp() {
        return this.qrsAmp;
    }

    public void setQrsAmp(int i) {
        this.qrsAmp = i;
    }

    public int getPwvMeanVal() {
        return this.pwvMeanVal;
    }

    public void setPwvMeanVal(int i) {
        this.pwvMeanVal = i;
    }

    public int getStMeanAmp() {
        return this.stMeanAmp;
    }

    public void setStMeanAmp(int i) {
        this.stMeanAmp = i;
    }

    public int getDiseaseSdnn() {
        return this.diseaseSdnn;
    }

    public void setDiseaseSdnn(int i) {
        this.diseaseSdnn = i;
    }

    public int getDiseaseRmssd() {
        return this.diseaseRmssd;
    }

    public void setDiseaseRmssd(int i) {
        this.diseaseRmssd = i;
    }

    public TimeData getTimeBean() {
        return this.timeBean;
    }

    public void setTimeBean(TimeData timeData) {
        this.timeBean = timeData;
    }

    public int getFrequency() {
        return this.frequency;
    }

    public void setFrequency(int i) {
        this.frequency = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public int[] getPowers() {
        return this.powers;
    }

    public void setPowers(int[] iArr) {
        this.powers = iArr;
    }

    public int[] getFilterSignals() {
        return this.filterSignals;
    }

    public void setFilterSignals(int[] iArr) {
        this.filterSignals = iArr;
    }

    public int[] getPpgSignals() {
        return this.ppgSignals;
    }

    public void setPpgSignals(int[] iArr) {
        this.ppgSignals = iArr;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int[] getDetectHeart() {
        return this.detectHeart;
    }

    public void setDetectHeart(int[] iArr) {
        this.detectHeart = iArr;
    }

    public int[] getDetectBreath() {
        return this.detectBreath;
    }

    public void setDetectBreath(int[] iArr) {
        this.detectBreath = iArr;
    }

    public int[] getDetectHrv() {
        return this.detectHrv;
    }

    public void setDetectHrv(int[] iArr) {
        this.detectHrv = iArr;
    }

    public int[] getDetectQT() {
        return this.detectQT;
    }

    public void setDetectQT(int[] iArr) {
        this.detectQT = iArr;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public float getPat() {
        return this.pat;
    }

    public void setPat(float f) {
        this.pat = f;
    }

    public String toString() {
        return "TcmDiagnosis{isSuccess=" + this.isSuccess + ", timeBean=" + this.timeBean + ", frequency=" + this.frequency + ", powers=" + Arrays.toString(this.powers) + ", filterSignals=" + Arrays.toString(this.filterSignals) + ", ppgSignals=" + Arrays.toString(this.ppgSignals) + ", duration=" + this.duration + ", leadOffType=" + this.leadOffType + ", diagnosis8=" + Arrays.toString(this.diagnosis8) + ", heartRate=" + this.heartRate + ", respRate=" + this.respRate + ", hrv=" + this.hrv + ", qtTime=" + this.qtTime + ", diseaseRisk=" + this.diseaseRisk + ", pressureIndex=" + this.pressureIndex + ", fatigueIndex=" + this.fatigueIndex + ", myocarditisRisk=" + this.myocarditisRisk + ", chdRisk=" + this.chdRisk + ", angioscleroticRisk=" + this.angioscleroticRisk + ", risk32=" + Arrays.toString(this.risk32) + ", qrsTime=" + this.qrsTime + ", qrsAmp=" + this.qrsAmp + ", pwvMeanVal=" + this.pwvMeanVal + ", stMeanAmp=" + this.stMeanAmp + ", diseaseSdnn=" + this.diseaseSdnn + ", diseaseRmssd=" + this.diseaseRmssd + ", pat=" + this.pat + ", type=" + this.type + ", state=" + this.state + ", detectHeart=" + Arrays.toString(this.detectHeart) + ", detectBreath=" + Arrays.toString(this.detectBreath) + ", detectHrv=" + Arrays.toString(this.detectHrv) + ", detectQT=" + Arrays.toString(this.detectQT) + ", id=" + this.id + '}';
    }

    public TcmDiagnosis(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr2, int i12, int i13, int i14, int i15, int i16, int i17) {
        this.powers = new int[1];
        this.filterSignals = new int[1];
        this.ppgSignals = new int[1];
        this.detectHeart = new int[1];
        this.detectBreath = new int[1];
        this.detectHrv = new int[1];
        this.detectQT = new int[1];
        this.leadOffType = i;
        this.diagnosis8 = iArr;
        this.heartRate = i2;
        this.respRate = i3;
        this.hrv = i4;
        this.qtTime = i5;
        this.diseaseRisk = i6;
        this.pressureIndex = i7;
        this.fatigueIndex = i8;
        this.myocarditisRisk = i9;
        this.chdRisk = i10;
        this.angioscleroticRisk = i11;
        this.risk32 = iArr2;
        this.qrsTime = i12;
        this.qrsAmp = i13;
        this.pwvMeanVal = i14;
        this.stMeanAmp = i15;
        this.diseaseSdnn = i16;
        this.diseaseRmssd = i17;
    }
}
