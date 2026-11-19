package com.veepoo.protocol.model.datas;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportModelOriginItemData.class */
public class SportModelOriginItemData {
    private String Date;
    private TimeData startTime;
    private int minute;
    private int allMinute;
    private int rate;
    private int stepCount;
    private int sportCount;
    private int distance;
    private int kcal;
    private int beathPause;
    private int crc;
    private double[] lngArray;
    private double[] latArray;
    private int[] signalArray;
    private int[] sportStateArray;
    private int a7SportModel;
    public long altitude;
    private boolean hasGpsInfo = false;

    public SportModelOriginItemData(String str, TimeData timeData, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.Date = str;
        this.startTime = timeData;
        this.minute = i;
        this.allMinute = i2;
        this.rate = i3;
        this.stepCount = i4;
        this.sportCount = i5;
        this.distance = i6;
        this.kcal = i7;
        this.beathPause = i8;
        this.crc = i9;
    }

    public long getAltitude() {
        return this.altitude;
    }

    public void setAltitude(long j) {
        this.altitude = j;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public TimeData getStartTime() {
        return this.startTime;
    }

    public void setStartTime(TimeData timeData) {
        this.startTime = timeData;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int i) {
        this.minute = i;
    }

    public int getAllMinute() {
        return this.allMinute;
    }

    public void setAllMinute(int i) {
        this.allMinute = i;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int i) {
        this.rate = i;
    }

    public int getStepCount() {
        return this.stepCount;
    }

    public void setStepCount(int i) {
        this.stepCount = i;
    }

    public int getSportCount() {
        return this.sportCount;
    }

    public void setSportCount(int i) {
        this.sportCount = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getKcal() {
        return this.kcal;
    }

    public void setKcal(int i) {
        this.kcal = i;
    }

    public int getBeathPause() {
        return this.beathPause;
    }

    public void setBeathPause(int i) {
        this.beathPause = i;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public double[] getLngArray() {
        return this.lngArray;
    }

    public void setLngArray(double[] dArr) {
        this.lngArray = dArr;
    }

    public double[] getLatArray() {
        return this.latArray;
    }

    public void setLatArray(double[] dArr) {
        this.latArray = dArr;
    }

    public int[] getSignalArray() {
        return this.signalArray;
    }

    public void setSignalArray(int[] iArr) {
        this.signalArray = iArr;
    }

    public int[] getSportStateArray() {
        return this.sportStateArray;
    }

    public void setSportStateArray(int[] iArr) {
        this.sportStateArray = iArr;
    }

    public boolean isHasGpsInfo() {
        return this.hasGpsInfo;
    }

    public void setHasGpsInfo(boolean z) {
        this.hasGpsInfo = z;
    }

    public void setA7SportModel(int i) {
        this.a7SportModel = i;
    }

    public int getA7SportModel() {
        return this.a7SportModel;
    }

    public String toString() {
        return "SportModelOriginItemData{ crc=" + this.crc + ",date='" + this.Date + "', startTime=" + this.startTime + ", minute=" + this.minute + ", allMinute=" + this.allMinute + ", rate=" + this.rate + ", stepCount=" + this.stepCount + ", sportCount=" + this.sportCount + ", distance=" + this.distance + ", kcal=" + this.kcal + ", beathPause=" + this.beathPause + ", a7SportModel=" + this.a7SportModel + ", hasGpsInfo=" + this.hasGpsInfo + ", latArray=" + Arrays.toString(this.latArray) + ", lngArray=" + Arrays.toString(this.lngArray) + '}';
    }
}
