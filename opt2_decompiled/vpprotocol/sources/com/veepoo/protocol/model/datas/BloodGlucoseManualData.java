package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BloodGlucoseManualData.class */
public class BloodGlucoseManualData {
    private int timeStamp;
    private int version;
    private float bloodGlucoseValue;
    private EBloodGlucoseRiskLevel risk;

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

    public float getBloodGlucoseValue() {
        return this.bloodGlucoseValue;
    }

    public void setBloodGlucoseValue(float f) {
        this.bloodGlucoseValue = f;
    }

    public EBloodGlucoseRiskLevel getRisk() {
        return this.risk;
    }

    public void setRisk(EBloodGlucoseRiskLevel eBloodGlucoseRiskLevel) {
        this.risk = eBloodGlucoseRiskLevel;
    }

    public String toString() {
        return "BloodGlucoseManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", bloodGlucoseValue=" + this.bloodGlucoseValue + ", risk=" + this.risk + '}';
    }
}
