package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BloodOxygenManualData.class */
public class BloodOxygenManualData {
    private int timeStamp;
    private int version;
    private int[] oxygen;

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

    public int[] getOxygen() {
        return this.oxygen;
    }

    public void setOxygen(int[] iArr) {
        this.oxygen = iArr;
    }

    public String toString() {
        return "BloodOxygenManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", oxygen=" + Arrays.toString(this.oxygen) + '}';
    }
}
