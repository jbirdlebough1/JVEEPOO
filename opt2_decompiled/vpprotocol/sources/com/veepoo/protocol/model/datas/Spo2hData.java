package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.ESPO2HStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/Spo2hData.class */
public class Spo2hData {
    private ESPO2HStatus spState;
    private EDeviceStatus deviceState;
    private int value;
    private boolean isChecking;
    private int checkingProgress;
    private int rateValue;

    public int getRateValue() {
        return this.rateValue;
    }

    public void setRateValue(int i) {
        this.rateValue = i;
    }

    public ESPO2HStatus getSpState() {
        return this.spState;
    }

    public void setSpState(ESPO2HStatus eSPO2HStatus) {
        this.spState = eSPO2HStatus;
    }

    public EDeviceStatus getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceState(EDeviceStatus eDeviceStatus) {
        this.deviceState = eDeviceStatus;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public boolean isChecking() {
        return this.isChecking;
    }

    public void setChecking(boolean z) {
        this.isChecking = z;
    }

    public int getCheckingProgress() {
        return this.checkingProgress;
    }

    public void setCheckingProgress(int i) {
        this.checkingProgress = i;
    }

    public String toString() {
        return "Spo2hData{spState=" + this.spState + ", deviceState=" + this.deviceState + ", spo2hValue=" + this.value + ", rateValue=" + this.rateValue + ", isChecking=" + this.isChecking + ", checkingProgress=" + this.checkingProgress + ", Blood Oxygen (SpO2)Calibration中=" + this.isChecking + ", Blood Oxygen (SpO2)Calibration进度=" + this.checkingProgress + '}';
    }
}
