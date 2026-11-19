package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/LowPowerSetting.class */
public class LowPowerSetting {
    private int oprateType;
    private int resultState;
    private int notifyInt;
    private int trunwristerInt;
    private int normallightInt;
    private int shockdelayInt;
    private int shocktimeInt;

    public LowPowerSetting(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.oprateType = i;
        this.resultState = i2;
        this.notifyInt = i3;
        this.trunwristerInt = i4;
        this.normallightInt = i5;
        this.shockdelayInt = i6;
        this.shocktimeInt = i7;
    }

    public int getOprateType() {
        return this.oprateType;
    }

    public void setOprateType(int i) {
        this.oprateType = i;
    }

    public int getResultState() {
        return this.resultState;
    }

    public void setResultState(int i) {
        this.resultState = i;
    }

    public int getNotifyInt() {
        return this.notifyInt;
    }

    public void setNotifyInt(int i) {
        this.notifyInt = i;
    }

    public int getTrunwristerInt() {
        return this.trunwristerInt;
    }

    public void setTrunwristerInt(int i) {
        this.trunwristerInt = i;
    }

    public int getNormallightInt() {
        return this.normallightInt;
    }

    public void setNormallightInt(int i) {
        this.normallightInt = i;
    }

    public int getShockdelayInt() {
        return this.shockdelayInt;
    }

    public void setShockdelayInt(int i) {
        this.shockdelayInt = i;
    }

    public int getShocktimeInt() {
        return this.shocktimeInt;
    }

    public void setShocktimeInt(int i) {
        this.shocktimeInt = i;
    }

    public String toString() {
        return "LowPowerSetting{resultState=" + (this.resultState == 1 ? "开" : "关") + "notifyInt=" + this.notifyInt + ", trunwristerInt=" + this.trunwristerInt + ", normallightInt=" + this.normallightInt + ", shockdelayInt=" + this.shockdelayInt + ", shocktimeInt=" + this.shocktimeInt + '}';
    }
}
