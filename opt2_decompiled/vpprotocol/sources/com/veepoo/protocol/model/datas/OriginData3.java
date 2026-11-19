package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/OriginData3.class */
public class OriginData3 extends OriginData {
    public int[] gesture;
    public int[] ppgs;
    public int[] sleepSports;
    public int[] sleepStatusQuantity;
    public int[] resetTagContent;
    public int[] ecgs;
    public int[] resRates;
    public int[] sleepStates;
    public int[] oxygens;
    public int[] apneaResults;
    public int[] hypoxiaTimes;
    public int[] cardiacLoads;
    public int[] isHypoxias;
    public int[] corrects;
    public float bloodGlucose;
    private int pressure;
    private float met;
    public BloodComponent bloodComponent;
    public EBloodGlucoseRiskLevel bloodGlucoseRiskLevel = EBloodGlucoseRiskLevel.NONE;
    private int sportStatusVersion = 0;
    private int[] sportStatus = null;

    private String getArrayString(int[] iArr) {
        return (iArr == null || iArr.length == 0) ? "" : Arrays.toString(iArr);
    }

    public BloodComponent getBloodComponent() {
        return this.bloodComponent;
    }

    public void setBloodComponent(BloodComponent bloodComponent) {
        this.bloodComponent = bloodComponent;
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

    public int[] getGesture() {
        return this.gesture;
    }

    public void setGesture(int[] iArr) {
        this.gesture = iArr;
    }

    public int[] getPpgs() {
        return this.ppgs;
    }

    public int[] getEcgs() {
        return this.ecgs;
    }

    public void setPpgs(int[] iArr) {
        this.ppgs = iArr;
    }

    public void setEcgs(int[] iArr) {
        this.ecgs = iArr;
    }

    public int[] getResRates() {
        return this.resRates;
    }

    public void setResRates(int[] iArr) {
        this.resRates = iArr;
    }

    public int[] getSleepStates() {
        return this.sleepStates;
    }

    public void setSleepStates(int[] iArr) {
        this.sleepStates = iArr;
    }

    public int[] getOxygens() {
        return this.oxygens;
    }

    public void setOxygens(int[] iArr) {
        this.oxygens = iArr;
    }

    public int[] getApneaResults() {
        return this.apneaResults;
    }

    public void setApneaResults(int[] iArr) {
        this.apneaResults = iArr;
    }

    public int[] getHypoxiaTimes() {
        return this.hypoxiaTimes;
    }

    public void setHypoxiaTimes(int[] iArr) {
        this.hypoxiaTimes = iArr;
    }

    public int[] getCardiacLoads() {
        return this.cardiacLoads;
    }

    public void setCardiacLoads(int[] iArr) {
        this.cardiacLoads = iArr;
    }

    public int[] getIsHypoxias() {
        return this.isHypoxias;
    }

    public void setIsHypoxias(int[] iArr) {
        this.isHypoxias = iArr;
    }

    public int[] getCorrects() {
        return this.corrects;
    }

    public void setCorrects(int[] iArr) {
        this.corrects = iArr;
    }

    public float getBloodGlucose() {
        return this.bloodGlucose;
    }

    public void setBloodGlucose(float f) {
        this.bloodGlucose = f;
    }

    public BloodComponent getBloodComposition() {
        return this.bloodComponent;
    }

    public void setBloodComposition(BloodComponent bloodComponent) {
        this.bloodComponent = bloodComponent;
    }

    public EBloodGlucoseRiskLevel getBloodGlucoseRiskLevel() {
        return this.bloodGlucoseRiskLevel;
    }

    public void setBloodGlucoseRiskLevel(EBloodGlucoseRiskLevel eBloodGlucoseRiskLevel) {
        this.bloodGlucoseRiskLevel = eBloodGlucoseRiskLevel;
    }

    public int[] getSleepSports() {
        return this.sleepSports;
    }

    public void setSleepSports(int[] iArr) {
        this.sleepSports = iArr;
    }

    public int[] getSleepStatusQuantity() {
        return this.sleepStatusQuantity;
    }

    public void setSleepStatusQuantity(int[] iArr) {
        this.sleepStatusQuantity = iArr;
    }

    public int[] getResetTagContent() {
        return this.resetTagContent;
    }

    public void setResetTagContent(int[] iArr) {
        this.resetTagContent = iArr;
    }

    public int getPressure() {
        return this.pressure;
    }

    public void setPressure(int i) {
        this.pressure = i;
    }

    public float getMet() {
        return this.met;
    }

    public void setMet(float f) {
        this.met = f;
    }

    @Override // com.veepoo.protocol.model.datas.OriginData
    public String toString() {
        return "OriginData3{originData=" + super.toString() + "gesture=" + Arrays.toString(this.gesture) + ", ppgs=" + Arrays.toString(this.ppgs) + ", ecgs=" + Arrays.toString(this.ecgs) + ", resRates=" + Arrays.toString(this.resRates) + ", sleepStates=" + Arrays.toString(this.sleepStates) + ", sleepStatusQuantity=" + getArrayString(this.sleepStatusQuantity) + ", resetTagContent=" + getArrayString(this.resetTagContent) + ", oxygens=" + Arrays.toString(this.oxygens) + ", apneaResults=" + Arrays.toString(this.apneaResults) + ", hypoxiaTimes=" + Arrays.toString(this.hypoxiaTimes) + ", cardiacLoads=" + Arrays.toString(this.cardiacLoads) + ", isHypoxias=" + Arrays.toString(this.isHypoxias) + ", corrects=" + Arrays.toString(this.corrects) + ", calValue=" + this.calValue + ", disValue=" + this.disValue + ", calcType=" + this.calcType + ", drinkPartOne='" + this.drinkPartOne + "', drinkPartTwo='" + this.drinkPartTwo + "', bloodGlucose='" + this.bloodGlucose + "', bloodGlucoseRiskLevel='" + this.bloodGlucoseRiskLevel + "', bloodComposition='" + this.bloodComponent + "', pressure='" + this.pressure + ", met='" + this.met + ", sportStatusVersion='" + this.sportStatusVersion + ", sportStatus='" + Arrays.toString(this.sportStatus) + '}';
    }
}
