package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HalfHourSportData.class */
public class HalfHourSportData {
    private String date;
    private TimeData time;
    public int stepValue;
    public int sportValue;
    private double disValue;
    private double calValue;

    public HalfHourSportData(TimeData timeData, int i, int i2, double d, double d2) {
        this.date = timeData.toDatabaseDateString();
        this.time = timeData;
        this.stepValue = i;
        this.sportValue = i2;
        this.disValue = d;
        this.calValue = d2;
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

    public int getStepValue() {
        return this.stepValue;
    }

    public void setStepValue(int i) {
        this.stepValue = i;
    }

    public int getSportValue() {
        return this.sportValue;
    }

    public void setSportValue(int i) {
        this.sportValue = i;
    }

    public double getDisValue() {
        return this.disValue;
    }

    public void setDisValue(double d) {
        this.disValue = d;
    }

    public double getCalValue() {
        return this.calValue;
    }

    public void setCalValue(double d) {
        this.calValue = d;
    }

    public String toString() {
        return "HalfHourSportData{date='" + this.date + "', time=" + this.time + ", stepValue=" + this.stepValue + ", sportValue=" + this.sportValue + ", disValue=" + this.disValue + ", calValue=" + this.calValue + '}';
    }
}
