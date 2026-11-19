package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HalfHourBpData.class */
public class HalfHourBpData {
    private String date;
    private TimeData time;
    private int highValue;
    private int lowValue;

    public HalfHourBpData(TimeData timeData, int i, int i2) {
        this.date = timeData.toDatabaseDateString();
        this.time = timeData;
        this.highValue = i;
        this.lowValue = i2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public TimeData getTime() {
        return this.time;
    }

    public void setTime(TimeData timeData) {
        this.time = timeData;
    }

    public int getHighValue() {
        return this.highValue;
    }

    public void setHighValue(int i) {
        this.highValue = i;
    }

    public int getLowValue() {
        return this.lowValue;
    }

    public void setLowValue(int i) {
        this.lowValue = i;
    }

    public String toString() {
        return "HalfHourBpData{date='" + this.date + "', time=" + this.time + ", highValue=" + this.highValue + ", lowValue=" + this.lowValue + '}';
    }
}
