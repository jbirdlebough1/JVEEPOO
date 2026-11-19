package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EDisease;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/DiseaseInfo.class */
public class DiseaseInfo {
    private EDisease diseaseType;
    private int diseaseTimes;
    private int diseaseLevel;
    private int avgHeart;
    private int avgQT;
    private int avgHrv;
    private int[] diseaseEcgI;
    private int[] diseaseEcgII;
    private int[] diseaseEcgIII;
    private int[] diseaseEcgAVR;
    private int[] diseaseEcgAVL;
    private int[] diseaseEcgAVF;
    private int[] diseasePowers;
    private int diseaseOffset;
    private EcgWaveformsInfo waveformsInfo;

    public EDisease getDiseaseType() {
        return this.diseaseType;
    }

    public void setDiseaseType(EDisease eDisease) {
        this.diseaseType = eDisease;
    }

    public int getDiseaseTimes() {
        return this.diseaseTimes;
    }

    public void setDiseaseTimes(int i) {
        this.diseaseTimes = i;
    }

    public int getDiseaseLevel() {
        return this.diseaseLevel;
    }

    public void setDiseaseLevel(int i) {
        this.diseaseLevel = i;
    }

    public int getAvgHeart() {
        return this.avgHeart;
    }

    public void setAvgHeart(int i) {
        this.avgHeart = i;
    }

    public int getAvgQT() {
        return this.avgQT;
    }

    public void setAvgQT(int i) {
        this.avgQT = i;
    }

    public int getAvgHrv() {
        return this.avgHrv;
    }

    public void setAvgHrv(int i) {
        this.avgHrv = i;
    }

    public int getDiseaseOffset() {
        return this.diseaseOffset;
    }

    public void setDiseaseOffset(int i) {
        this.diseaseOffset = i;
    }

    public int[] getDiseaseEcgI() {
        return this.diseaseEcgI;
    }

    public void setDiseaseEcgI(int[] iArr) {
        this.diseaseEcgI = iArr;
    }

    public int[] getDiseaseEcgII() {
        return this.diseaseEcgII;
    }

    public void setDiseaseEcgII(int[] iArr) {
        this.diseaseEcgII = iArr;
    }

    public int[] getDiseaseEcgIII() {
        return this.diseaseEcgIII;
    }

    public void setDiseaseEcgIII(int[] iArr) {
        this.diseaseEcgIII = iArr;
    }

    public int[] getDiseaseEcgAVR() {
        return this.diseaseEcgAVR;
    }

    public void setDiseaseEcgAVR(int[] iArr) {
        this.diseaseEcgAVR = iArr;
    }

    public int[] getDiseaseEcgAVL() {
        return this.diseaseEcgAVL;
    }

    public void setDiseaseEcgAVL(int[] iArr) {
        this.diseaseEcgAVL = iArr;
    }

    public int[] getDiseaseEcgAVF() {
        return this.diseaseEcgAVF;
    }

    public void setDiseaseEcgAVF(int[] iArr) {
        this.diseaseEcgAVF = iArr;
    }

    public EcgWaveformsInfo getWaveformsInfo() {
        return this.waveformsInfo;
    }

    public void setWaveformsInfo(EcgWaveformsInfo ecgWaveformsInfo) {
        this.waveformsInfo = ecgWaveformsInfo;
    }

    public int[] getDiseasePowers() {
        return this.diseasePowers;
    }

    public void setDiseasePowers(int[] iArr) {
        this.diseasePowers = iArr;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("DiseaseInfo{diseaseType=").append(this.diseaseType).append(", diseaseTimes=").append(this.diseaseTimes).append(", diseaseLevel=").append(this.diseaseLevel).append(", avgHeart=").append(this.avgHeart).append(", avgQT=").append(this.avgQT).append(", avgHrv=").append(this.avgHrv).append(", diseaseEcgI=");
        int[] iArr = this.diseaseEcgI;
        StringBuilder sbAppend2 = sbAppend.append(iArr == null ? "null" : Integer.valueOf(iArr.length)).append(", diseaseEcgII=");
        int[] iArr2 = this.diseaseEcgII;
        StringBuilder sbAppend3 = sbAppend2.append(iArr2 == null ? "null" : Integer.valueOf(iArr2.length)).append(", diseaseEcgIII=");
        int[] iArr3 = this.diseaseEcgIII;
        StringBuilder sbAppend4 = sbAppend3.append(iArr3 == null ? "null" : Integer.valueOf(iArr3.length)).append(", diseaseEcgAVR=");
        int[] iArr4 = this.diseaseEcgAVR;
        StringBuilder sbAppend5 = sbAppend4.append(iArr4 == null ? "null" : Integer.valueOf(iArr4.length)).append(", diseaseEcgAVL=");
        int[] iArr5 = this.diseaseEcgAVL;
        StringBuilder sbAppend6 = sbAppend5.append(iArr5 == null ? "null" : Integer.valueOf(iArr5.length)).append(", diseaseEcgAVF=");
        int[] iArr6 = this.diseaseEcgAVF;
        return sbAppend6.append(iArr6 == null ? "null" : Integer.valueOf(iArr6.length)).append(", diseaseOffset=").append(this.diseaseOffset).append(", waveformsInfo=").append(this.waveformsInfo).append('}').toString();
    }
}
