package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/LowPowerData.class */
public class LowPowerData {
    private int oprateType;
    private int readState;
    private int openState;
    private int notify;
    private int trunwrister;
    private int normallight;
    private int shockdelay;
    private int shocktime;

    public LowPowerData() {
    }

    public LowPowerData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.readState = i;
        this.oprateType = i2;
        this.openState = i3;
        this.notify = i4;
        this.trunwrister = i5;
        this.normallight = i6;
        this.shockdelay = i7;
        this.shocktime = i8;
    }

    public int getOprateType() {
        return this.oprateType;
    }

    public void setOprateType(int i) {
        this.oprateType = i;
    }

    public int getReadState() {
        return this.readState;
    }

    public void setReadState(int i) {
        this.readState = i;
    }

    public int getOpenState() {
        return this.openState;
    }

    public void setOpenState(int i) {
        this.openState = i;
    }

    public int getNotify() {
        return this.notify;
    }

    public void setNotify(int i) {
        this.notify = i;
    }

    public int getTrunwrister() {
        return this.trunwrister;
    }

    public void setTrunwrister(int i) {
        this.trunwrister = i;
    }

    public int getNormallight() {
        return this.normallight;
    }

    public void setNormallight(int i) {
        this.normallight = i;
    }

    public int getShockdelay() {
        return this.shockdelay;
    }

    public void setShockdelay(int i) {
        this.shockdelay = i;
    }

    public int getShocktime() {
        return this.shocktime;
    }

    public void setShocktime(int i) {
        this.shocktime = i;
    }

    public String toString() {
        return "LowPowerData{oprateType=" + (this.oprateType == 1 ? "设置" : "读取") + ",readState=" + (this.readState == 1 ? "成功" : "失败") + ",openState=" + (this.openState == 1 ? "开" : "关") + '}';
    }
}
