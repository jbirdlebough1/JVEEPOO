package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BatteryData.class */
public class BatteryData {
    private int batteryLevel;
    private int batteryPercent;
    private int powerModel;
    private int state;
    private byte bat;
    private boolean isLowBattery = false;
    private boolean isPercent = false;

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
    }

    public int getBatteryPercent() {
        return this.batteryPercent;
    }

    public void setBatteryPercent(int i) {
        this.batteryPercent = i;
    }

    public boolean isPercent() {
        return this.isPercent;
    }

    public void setPercent(boolean z) {
        this.isPercent = z;
    }

    public int getPowerModel() {
        return this.powerModel;
    }

    public void setPowerModel(int i) {
        this.powerModel = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public boolean isLowBattery() {
        return this.isLowBattery;
    }

    public void setLowBattery(boolean z) {
        this.isLowBattery = z;
    }

    public byte getBat() {
        return this.bat;
    }

    public void setBat(byte b) {
        this.bat = b;
    }

    public String toString() {
        return "BatteryData{batteryLevel=" + this.batteryLevel + ", batteryPercent=" + this.batteryPercent + ", powerModel=" + this.powerModel + ", state=" + this.state + ", bat=" + ((int) this.bat) + ", isLowBattery=" + this.isLowBattery + ", isPercent=" + this.isPercent + '}';
    }
}
