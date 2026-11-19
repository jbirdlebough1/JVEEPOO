package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HalfHourRateData.class */
public class HalfHourRateData {
    private String date;
    private TimeData time;
    private int rateValue;
    private int ecgCount = 0;
    private int ppgCount = 0;

    public HalfHourRateData(TimeData timeData, int i) {
        this.time = timeData;
        this.date = timeData.toDatabaseDateString();
        this.rateValue = i;
    }

    public int getEcgCount() {
        return this.ecgCount;
    }

    public void setEcgCount(int i) {
        this.ecgCount = i;
    }

    public int getPpgCount() {
        return this.ppgCount;
    }

    public void setPpgCount(int i) {
        this.ppgCount = i;
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

    public int getRateValue() {
        return this.rateValue;
    }

    public void setRateValue(int i) {
        this.rateValue = i;
    }

    public String toString() {
        return "HalfHourRateData{date='" + this.date + "', time=" + this.time + ", rateValue=" + this.rateValue + ", ecgCount=" + this.ecgCount + ", ppgCount=" + this.ppgCount + '}';
    }
}
