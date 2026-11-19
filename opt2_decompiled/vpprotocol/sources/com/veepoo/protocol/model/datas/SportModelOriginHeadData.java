package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ESportType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportModelOriginHeadData.class */
public class SportModelOriginHeadData implements Comparable<SportModelOriginHeadData> {
    private String date;
    private TimeData startTime;
    private TimeData stopTime;
    private int sportTime;
    private int stepCount;
    private int sportCount;
    private double kcals;
    private double distance;
    private int recordCount;
    private int pauseCount;
    private int pauseTime;
    private int crc;
    private int peisu;
    private int oxsporttimes;
    private int averRate;
    private int sportType;
    private boolean hasGpsInfo;
    private int sportDuration;
    private int dataType;
    private int a7ProtectSportType;

    public SportModelOriginHeadData() {
    }

    public SportModelOriginHeadData(String str, TimeData timeData, TimeData timeData2, int i, int i2, int i3, double d, double d2, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.date = str;
        this.startTime = timeData;
        this.stopTime = timeData2;
        this.stepCount = i;
        this.sportCount = i3;
        this.distance = d;
        this.kcals = d2;
        this.recordCount = i4;
        this.pauseCount = i5;
        this.pauseTime = i6;
        this.crc = i7;
        this.peisu = i8;
        this.oxsporttimes = i9;
        this.averRate = i10;
        this.sportType = i11;
        this.sportTime = i2 - i6;
    }

    public int getSportType() {
        return this.sportType;
    }

    public void setSportType(int i) {
        this.sportType = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public TimeData getStartTime() {
        return this.startTime;
    }

    public void setStartTime(TimeData timeData) {
        this.startTime = timeData;
    }

    public TimeData getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(TimeData timeData) {
        this.stopTime = timeData;
    }

    public int getSportTime() {
        return this.sportTime;
    }

    public void setSportTime(int i) {
        this.sportTime = i;
    }

    public int getStepCount() {
        return this.stepCount;
    }

    public void setStepCount(int i) {
        this.stepCount = i;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getKcals() {
        return this.kcals;
    }

    public void setKcals(double d) {
        this.kcals = d;
    }

    public int getSportCount() {
        return this.sportCount;
    }

    public void setSportCount(int i) {
        this.sportCount = i;
    }

    public int getRecordCount() {
        return this.recordCount;
    }

    public void setRecordCount(int i) {
        this.recordCount = i;
    }

    public int getPauseCount() {
        return this.pauseCount;
    }

    public void setPauseCount(int i) {
        this.pauseCount = i;
    }

    public int getPauseTime() {
        return this.pauseTime;
    }

    public void setPauseTime(int i) {
        this.pauseTime = i;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public int getPeisu() {
        return this.peisu;
    }

    public void setPeisu(int i) {
        this.peisu = i;
    }

    public int getOxsporttimes() {
        return this.oxsporttimes;
    }

    public void setOxsporttimes(int i) {
        this.oxsporttimes = i;
    }

    public int getAverRate() {
        return this.averRate;
    }

    public void setAverRate(int i) {
        this.averRate = i;
    }

    public String getPeisuFormat() {
        return TimeData.getTwoStr(this.peisu / 60) + "'" + TimeData.getTwoStr(this.peisu % 60) + "''";
    }

    public boolean isHasGpsInfo() {
        return this.hasGpsInfo;
    }

    public void setHasGpsInfo(boolean z) {
        this.hasGpsInfo = z;
    }

    public int getSportDuration() {
        return this.sportDuration;
    }

    public void setSportDuration(int i) {
        this.sportDuration = i;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public int getA7ProtectSportType() {
        return this.a7ProtectSportType;
    }

    public void setA7ProtectSportType(int i) {
        this.a7ProtectSportType = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(SportModelOriginHeadData sportModelOriginHeadData) {
        return -this.startTime.toFullDateTimeString().compareTo(sportModelOriginHeadData.getStartTime().toFullDateTimeString());
    }

    public String toString() {
        return "SportModelOriginHeadData{ crc=" + this.crc + ",date='" + this.date + "', startTime=" + this.startTime + ", stopTime=" + this.stopTime + ", sportTime=" + this.sportTime + "(秒), stepCount=" + this.stepCount + ", distance=" + this.distance + "(km), kcals=" + this.kcals + "(kcal), sportCount=" + this.sportCount + ", recordCount=" + this.recordCount + ", pauseCount=" + this.pauseCount + "or pauseCount=" + getPeisuFormat() + ", pauseTime=" + this.pauseTime + ", peisu=" + this.peisu + ", oxsporttimes=" + this.oxsporttimes + ", averRate=" + this.averRate + ", sportType=" + ESportType.getSportType(this.sportType) + ":" + this.sportType + ", hasGpsInfo=" + this.hasGpsInfo + '}';
    }
}
