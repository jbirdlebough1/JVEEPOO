package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ESportType;
import com.veepoo.protocol.util.vp_b;
import com.veepoo.protocol.util.vp_v;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportModelGPSWatchOriginHeadData.class */
public class SportModelGPSWatchOriginHeadData implements Comparable<SportModelGPSWatchOriginHeadData> {
    private String date;
    private TimeData startTime;
    private TimeData stopTime;
    private int sportTime;
    private int stepCount;
    private int sportCount;
    private double calories;
    private double distance;
    private int recordCount;
    private int pauseCount;
    private int pauseTime;
    private int crc;
    private int oxSportDuration;
    private int averRate;
    private int sportType;
    private boolean hasGpsInfo;
    private int sportDuration;
    private int maxHeartRate;
    private int minHeartRate;
    private int avgHeartRate;
    private int maxPace;
    private int minPace;
    private int avgPace;
    private int maxSpeed;
    private int minSpeed;
    private int avgSpeed;
    private int maxCadence;
    private int minCadence;
    private int avgCadence;
    private long maxAltitude;
    private long minAltitude;
    private long avgAltitude;
    private long cumulativeClimb;
    private long cumulativeDecline;
    private int dataType;
    private int a7ProtectSportType;

    public SportModelGPSWatchOriginHeadData() {
    }

    public SportModelGPSWatchOriginHeadData(TimeData timeData, TimeData timeData2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.startTime = timeData;
        this.stopTime = timeData2;
        this.stepCount = i;
        this.distance = i2;
        this.calories = i3;
        this.sportCount = i4;
        this.recordCount = i5;
        this.pauseCount = i6;
        this.pauseTime = i7;
        this.crc = i8;
        setSportTime();
    }

    public static String m2Speed(int i, boolean z, boolean z2) {
        if (z) {
            return vp_v.vp_a(((double) i) / 1000.0d) + "" + (z2 ? "mile/h" : "");
        }
        return vp_b.vp_a("" + (((double) i) / 1000.0d), 2) + "" + (z2 ? "km/h" : "");
    }

    private static String getPaceStr(boolean z, int i) {
        if (z) {
            i = (int) (((double) i) / 0.62138d);
        }
        int i2 = i;
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        if (i2 > 6039) {
            i3 = 0;
            i4 = 0;
        }
        return getTwoStr(i3) + "'" + getTwoStr(i4) + "''";
    }

    public static String getTwoStr(int i) {
        String str = i + "";
        String str2 = str;
        if (str.length() < 2) {
            str2 = "0" + str2;
        }
        return str2;
    }

    public void setSportTime() {
        int year = this.stopTime.getYear() - this.startTime.getYear();
        int month = this.stopTime.getMonth() - this.startTime.getMonth();
        int day = this.stopTime.getDay() - this.startTime.getDay();
        int second = (this.stopTime.getSecond() - this.startTime.getSecond()) + ((this.stopTime.getMinute() - this.startTime.getMinute()) * 60) + ((this.stopTime.getHour() - this.startTime.getHour()) * 3600) + (day * 86400) + (month * 2592000) + (year * 31104000);
        this.sportTime = second;
        if (second <= 0) {
            this.sportTime = this.recordCount * 60;
        }
        this.sportTime -= this.pauseTime;
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

    public double getCalories() {
        return this.calories;
    }

    public void setCalories(double d) {
        this.calories = d;
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

    public int getOxSportDuration() {
        return this.oxSportDuration;
    }

    public void setOxSportDuration(int i) {
        this.oxSportDuration = i;
    }

    public int getAverRate() {
        return this.averRate;
    }

    public void setAverRate(int i) {
        this.averRate = i;
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

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public int getMinHeartRate() {
        return this.minHeartRate;
    }

    public void setMinHeartRate(int i) {
        this.minHeartRate = i;
    }

    public int getAvgHeartRate() {
        return this.avgHeartRate;
    }

    public void setAvgHeartRate(int i) {
        this.avgHeartRate = i;
    }

    public int getMaxPace() {
        return this.maxPace;
    }

    public void setMaxPace(int i) {
        this.maxPace = i;
    }

    public int getMinPace() {
        return this.minPace;
    }

    public void setMinPace(int i) {
        this.minPace = i;
    }

    public int getAvgPace() {
        return this.avgPace;
    }

    public void setAvgPace(int i) {
        this.avgPace = i;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int i) {
        this.maxSpeed = i;
    }

    public int getMinSpeed() {
        return this.minSpeed;
    }

    public void setMinSpeed(int i) {
        this.minSpeed = i;
    }

    public int getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(int i) {
        this.avgSpeed = i;
    }

    public int getMaxCadence() {
        return this.maxCadence;
    }

    public void setMaxCadence(int i) {
        this.maxCadence = i;
    }

    public int getMinCadence() {
        return this.minCadence;
    }

    public void setMinCadence(int i) {
        this.minCadence = i;
    }

    public int getAvgCadence() {
        return this.avgCadence;
    }

    public void setAvgCadence(int i) {
        this.avgCadence = i;
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

    public String getMaxPaceStr(boolean z) {
        return getPaceStr(z, this.maxPace);
    }

    public String getAvgPaceStr(boolean z) {
        return getPaceStr(z, this.avgPace);
    }

    public String getMinPaceStr(boolean z) {
        return getPaceStr(z, this.minPace);
    }

    public String getMaxSpeedStr(boolean z) {
        return m2Speed(this.maxSpeed, z, false);
    }

    public String getAvgSpeedStr(boolean z) {
        return m2Speed(this.avgSpeed, z, false);
    }

    public String getMinSpeedStr(boolean z) {
        return m2Speed(this.minSpeed, z, false);
    }

    public long getMaxAltitude() {
        return this.maxAltitude;
    }

    public void setMaxAltitude(long j) {
        this.maxAltitude = j;
    }

    public long getMinAltitude() {
        return this.minAltitude;
    }

    public void setMinAltitude(long j) {
        this.minAltitude = j;
    }

    public long getAvgAltitude() {
        return this.avgAltitude;
    }

    public void setAvgAltitude(long j) {
        this.avgAltitude = j;
    }

    public long getCumulativeClimb() {
        return this.cumulativeClimb;
    }

    public void setCumulativeClimb(long j) {
        this.cumulativeClimb = j;
    }

    public long getCumulativeDecline() {
        return this.cumulativeDecline;
    }

    public void setCumulativeDecline(long j) {
        this.cumulativeDecline = j;
    }

    public boolean isHaveAltitude() {
        new StringBuilder().append("是否有海拔：dataType = ").append(this.dataType).append(" --> a7ProtectSportType = ").append(this.a7ProtectSportType);
        int i = this.dataType;
        return (i == 5 || i == 6 || i == 7) && this.a7ProtectSportType == 6;
    }

    @Override // java.lang.Comparable
    public int compareTo(SportModelGPSWatchOriginHeadData sportModelGPSWatchOriginHeadData) {
        return -this.startTime.toFullDateTimeString().compareTo(sportModelGPSWatchOriginHeadData.getStartTime().toFullDateTimeString());
    }

    public String toString() {
        return "SportModelGPSWatchOriginHeadData{ crc=" + this.crc + ",date='" + this.date + "', startTime=" + this.startTime + ", stopTime=" + this.stopTime + ", sportTime=" + this.sportTime + "(s), stepCount=" + this.stepCount + ", distance=" + this.distance + "(m), calories=" + this.calories + "(cal), sportCount=" + this.sportCount + ", recordCount=" + this.recordCount + ", pauseCount=" + this.pauseCount + ", pauseTime=" + this.pauseTime + ", oxSportDuration=" + this.oxSportDuration + ", averRate=" + this.averRate + ", sportType=" + ESportType.getSportType(this.sportType) + ":" + this.sportType + ", hasGpsInfo=" + this.hasGpsInfo + ", maxHeartRate=" + this.maxHeartRate + ", minHeartRate=" + this.minHeartRate + ", avgHeartRate=" + this.avgHeartRate + ", maxPace=" + getMaxPaceStr(false) + ", minPace=" + getMinPaceStr(false) + ", avgPace=" + getAvgPaceStr(false) + ", maxSpeed=" + getMaxSpeedStr(false) + ", minSpeed=" + getMinSpeedStr(false) + ", avgSpeed=" + getAvgSpeedStr(false) + ", maxCadence=" + this.maxCadence + ", minCadence=" + this.minCadence + ", avgCadence=" + this.avgCadence + ", a7ProtectSportType=" + this.a7ProtectSportType + ", dataType=" + this.dataType + '}';
    }

    public SportModelGPSWatchOriginHeadData(TimeData timeData, TimeData timeData2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
        this(timeData, timeData2, i, i2, i3, i4, i5, i6, i7, i8);
        this.date = timeData.toDatabaseDateString();
        this.sportType = i9;
        this.hasGpsInfo = z;
    }

    public void setSportTime(int i) {
        this.sportTime = i;
    }

    public SportModelGPSWatchOriginHeadData(String str, TimeData timeData, TimeData timeData2, int i, int i2, int i3, double d, double d2, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.date = str;
        this.startTime = timeData;
        this.stopTime = timeData2;
        this.stepCount = i;
        this.sportCount = i3;
        this.distance = d;
        this.calories = d2;
        this.recordCount = i4;
        this.pauseCount = i5;
        this.pauseTime = i6;
        this.crc = i7;
        this.oxSportDuration = i8;
        this.averRate = i9;
        this.sportType = i10;
        this.sportTime = i2 - i6;
    }
}
