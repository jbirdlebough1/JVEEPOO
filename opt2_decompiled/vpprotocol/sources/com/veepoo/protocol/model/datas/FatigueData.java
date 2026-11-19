package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.EFatigueStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/FatigueData.class */
public class FatigueData {
    private EFatigueStatus fatigueState;
    private EDeviceStatus deviceState;
    private int progress;
    private int value;

    public EFatigueStatus getFatigueState() {
        return this.fatigueState;
    }

    public void setFatigueState(EFatigueStatus eFatigueStatus) {
        this.fatigueState = eFatigueStatus;
    }

    public EDeviceStatus getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceState(EDeviceStatus eDeviceStatus) {
        this.deviceState = eDeviceStatus;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public String toString() {
        return "FatigueData{fatigueState=" + this.fatigueState + ", deviceState=" + this.deviceState + ", progress=" + this.progress + ", value=" + this.value + '}';
    }
}
