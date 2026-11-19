package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HeartRateManualData.class */
public class HeartRateManualData {
    private int timeStamp;
    private int version;
    private int[] rate;

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

    public int[] getRate() {
        return this.rate;
    }

    public void setRate(int[] iArr) {
        this.rate = iArr;
    }

    public String toString() {
        return "HeartRateManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", rate=" + Arrays.toString(this.rate) + '}';
    }
}
