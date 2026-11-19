package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.util.vp_j;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SleepData.class */
public class SleepData implements Comparable<Object> {
    public String Date;
    public int cali_flag;
    public int sleepQulity;
    public int wakeCount;
    public int deepSleepTime;
    public int lowSleepTime;
    public int allSleepTime;
    public String sleepLine;
    public TimeData sleepDown;
    public TimeData sleepUp;

    public SleepData() {
    }

    public SleepData(int i, int i2, int i3, int i4, int i5, int i6, String str, TimeData timeData, TimeData timeData2) {
        this.Date = getSleepDate(timeData);
        this.cali_flag = i;
        this.sleepQulity = i2;
        this.wakeCount = i3;
        this.deepSleepTime = i4;
        this.lowSleepTime = i5;
        this.allSleepTime = i6;
        this.sleepLine = str;
        this.sleepDown = timeData;
        this.sleepUp = timeData2;
    }

    public static String getSleepDate(TimeData timeData) {
        String databaseDateString = timeData.toDatabaseDateString();
        if (timeData.getHour() < 8) {
            databaseDateString = vp_j.vp_a(databaseDateString, -1);
        }
        return databaseDateString;
    }

    public int getAllSleepTime() {
        return this.allSleepTime;
    }

    public void setAllSleepTime(int i) {
        this.allSleepTime = i;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String str) {
        this.Date = str;
    }

    public int getCali_flag() {
        return this.cali_flag;
    }

    public void setCali_flag(int i) {
        this.cali_flag = i;
    }

    public int getSleepQulity() {
        return this.sleepQulity;
    }

    public void setSleepQulity(int i) {
        this.sleepQulity = i;
    }

    public int getWakeCount() {
        return this.wakeCount;
    }

    public void setWakeCount(int i) {
        this.wakeCount = i;
    }

    public int getDeepSleepTime() {
        return this.deepSleepTime;
    }

    public void setDeepSleepTime(int i) {
        this.deepSleepTime = i;
    }

    public int getLowSleepTime() {
        return this.lowSleepTime;
    }

    public void setLowSleepTime(int i) {
        this.lowSleepTime = i;
    }

    public String getSleepLine() {
        return this.sleepLine;
    }

    public void setSleepLine(String str) {
        this.sleepLine = str;
    }

    public TimeData getSleepDown() {
        return this.sleepDown;
    }

    public void setSleepDown(TimeData timeData) {
        this.sleepDown = timeData;
    }

    public TimeData getSleepUp() {
        return this.sleepUp;
    }

    public void setSleepUp(TimeData timeData) {
        this.sleepUp = timeData;
    }

    public String toString() {
        return "SleepData{, date='" + this.Date + "', cali_flag=" + this.cali_flag + ", sleepQulity=" + this.sleepQulity + ", wakeCount=" + this.wakeCount + ", deepSleepTime=" + this.deepSleepTime + ", lowSleepTime=" + this.lowSleepTime + ", allSleepTime=" + this.allSleepTime + ", sleepLine='" + this.sleepLine + "', sleepDown=" + this.sleepDown + ", sleepUp=" + this.sleepUp + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        int iCompareTo = 0;
        TimeData timeData = this.sleepDown;
        if (timeData == null) {
            return 0;
        }
        String dateAndClockForDb = timeData.getDateAndClockForDb();
        if (obj instanceof SleepData) {
            SleepData sleepData = (SleepData) obj;
            if (sleepData.getSleepDown() == null) {
                return 0;
            }
            iCompareTo = dateAndClockForDb.compareTo(sleepData.getSleepDown().getDateAndClockForDb());
        }
        return iCompareTo;
    }
}
