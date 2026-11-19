package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QX17GPSData.class */
public class QX17GPSData {
    private float latitude;
    private float longitude;
    private float accuracy;
    private int timestamp;

    public float getLatitude() {
        return this.latitude;
    }

    public void setLatitude(float f) {
        this.latitude = f;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public void setLongitude(float f) {
        this.longitude = f;
    }

    public float getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(float f) {
        this.accuracy = f;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
    }

    public String toString() {
        return "QX17GPSData{lat=" + this.latitude + ", lon=" + this.longitude + ", acc=" + this.accuracy + "m, ts=" + this.timestamp + '}';
    }
}
