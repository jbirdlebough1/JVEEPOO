package com.veepoo.protocol.model.datas.ecg;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ecg/HRVFiveMinuteData.class */
public class HRVFiveMinuteData {
    private int hrvType;
    private int[] hrvTypeBit;
    private int[] rr50;
    private int[] hrv5;

    public HRVFiveMinuteData() {
    }

    public HRVFiveMinuteData(int i, int[] iArr, int[] iArr2, int[] iArr3) {
        this.hrvType = i;
        this.hrvTypeBit = iArr;
        this.rr50 = iArr2;
        this.hrv5 = iArr3;
    }

    public int getHrvType() {
        return this.hrvType;
    }

    public void setHrvType(int i) {
        this.hrvType = i;
    }

    public int[] getHrvTypeBit() {
        return this.hrvTypeBit;
    }

    public void setHrvTypeBit(int[] iArr) {
        this.hrvTypeBit = iArr;
    }

    public int[] getRr50() {
        return this.rr50;
    }

    public void setRr50(int[] iArr) {
        this.rr50 = iArr;
    }

    public int[] getHrv5() {
        return this.hrv5;
    }

    public void setHrv5(int[] iArr) {
        this.hrv5 = iArr;
    }

    public String toString() {
        return "HRVFiveMinuteData{hrvType=" + this.hrvType + ", hrvTypeBit=" + Arrays.toString(this.hrvTypeBit) + ", rr50=" + Arrays.toString(this.rr50) + ", hrv5=" + Arrays.toString(this.hrv5) + '}';
    }
}
