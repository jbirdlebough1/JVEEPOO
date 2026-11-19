package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QX17HeartRateData.class */
public class QX17HeartRateData {
    private int heartRate;
    private int timestamp;

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
    }

    public String toString() {
        return "QX17HeartRateData{hr=" + this.heartRate + ", ts=" + this.timestamp + '}';
    }
}
