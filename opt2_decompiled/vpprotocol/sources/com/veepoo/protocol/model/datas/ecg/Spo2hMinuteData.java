package com.veepoo.protocol.model.datas.ecg;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ecg/Spo2hMinuteData.class */
public class Spo2hMinuteData {
    private int[] spo2hValue;
    private int[] apneaCount;
    private int[] apneaResult;
    private int[] hypoxiaTime;
    private int[] cardiacLoad;
    private int[] checkFlag;

    public Spo2hMinuteData() {
    }

    public Spo2hMinuteData(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5, int[] iArr6) {
        this.spo2hValue = iArr;
        this.apneaCount = iArr2;
        this.apneaResult = iArr3;
        this.hypoxiaTime = iArr4;
        this.cardiacLoad = iArr5;
        this.checkFlag = iArr6;
    }

    public int[] getSpo2hValue() {
        return this.spo2hValue;
    }

    public void setSpo2hValue(int[] iArr) {
        this.spo2hValue = iArr;
    }

    public int[] getApneaCount() {
        return this.apneaCount;
    }

    public void setApneaCount(int[] iArr) {
        this.apneaCount = iArr;
    }

    public int[] getApneaResult() {
        return this.apneaResult;
    }

    public void setApneaResult(int[] iArr) {
        this.apneaResult = iArr;
    }

    public int[] getHypoxiaTime() {
        return this.hypoxiaTime;
    }

    public void setHypoxiaTime(int[] iArr) {
        this.hypoxiaTime = iArr;
    }

    public int[] getCardiacLoad() {
        return this.cardiacLoad;
    }

    public void setCardiacLoad(int[] iArr) {
        this.cardiacLoad = iArr;
    }

    public int[] getCheckFlag() {
        return this.checkFlag;
    }

    public void setCheckFlag(int[] iArr) {
        this.checkFlag = iArr;
    }

    public String toString() {
        return "Spo2hMinuteData{spo2hValue=" + Arrays.toString(this.spo2hValue) + ", apneaCount=" + Arrays.toString(this.apneaCount) + ", apneaResult=" + Arrays.toString(this.apneaResult) + ", hypoxiaTime=" + Arrays.toString(this.hypoxiaTime) + ", cardiacLoad=" + Arrays.toString(this.cardiacLoad) + ", checkFlag=" + Arrays.toString(this.checkFlag) + '}';
    }
}
