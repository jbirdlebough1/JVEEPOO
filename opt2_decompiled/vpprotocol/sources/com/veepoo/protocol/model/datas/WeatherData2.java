package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/WeatherData2.class */
public class WeatherData2 {
    private TimeData timeBean;
    private int tempF;
    private int tempC;
    private int weathStatus;
    private int uvintensityYellow;
    private int crc;

    public WeatherData2(TimeData timeData, int i, int i2, int i3, int i4, int i5) {
        this.timeBean = timeData;
        this.tempF = i;
        this.tempC = i2;
        this.weathStatus = i3;
        this.uvintensityYellow = i4;
        this.crc = i5;
    }

    public TimeData getTimeBean() {
        return this.timeBean;
    }

    public void setTimeBean(TimeData timeData) {
        this.timeBean = timeData;
    }

    public int getTempF() {
        return this.tempF;
    }

    public void setTempF(int i) {
        this.tempF = i;
    }

    public int getTempC() {
        return this.tempC;
    }

    public void setTempC(int i) {
        this.tempC = i;
    }

    public int getWeathStatus() {
        return this.weathStatus;
    }

    public void setWeathStatus(int i) {
        this.weathStatus = i;
    }

    public int getUvintensityYellow() {
        return this.uvintensityYellow;
    }

    public void setUvintensityYellow(int i) {
        this.uvintensityYellow = i;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public String toString() {
        return "WeatherData2{, timeBean=" + this.timeBean + ", tempF=" + this.tempF + ", tempC=" + this.tempC + ", weathStatus=" + this.weathStatus + ", uvintensityYellow=" + this.uvintensityYellow + ", crc=" + this.crc + '}';
    }
}
