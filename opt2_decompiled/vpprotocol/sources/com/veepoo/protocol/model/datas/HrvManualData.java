package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HrvManualData.class */
public class HrvManualData {
    private int timeStamp;
    private int version;
    private int[] hrv;

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

    public int[] getHrv() {
        return this.hrv;
    }

    public void setHrv(int[] iArr) {
        this.hrv = iArr;
    }

    public String toString() {
        return "HrvManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", hrv=" + Arrays.toString(this.hrv) + '}';
    }
}
