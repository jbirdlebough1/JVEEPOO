package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BreathData.class */
public class BreathData {
    private int functionState;
    private int deviceState;
    private int progressValue;
    private int value;

    public BreathData() {
    }

    public BreathData(int i, int i2, int i3, int i4) {
        this.functionState = i;
        this.deviceState = i2;
        this.progressValue = i3;
        this.value = i4;
    }

    public int getProgressValue() {
        return this.progressValue;
    }

    public void setProgressValue(int i) {
        this.progressValue = i;
    }

    public int getFunctionState() {
        return this.functionState;
    }

    public void setFunctionState(int i) {
        this.functionState = i;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceState(int i) {
        this.deviceState = i;
    }

    public EFunctionStatus getFunctionStateEnum() {
        int i = this.functionState;
        if (i == 0) {
            return EFunctionStatus.UNSUPPORT;
        }
        if (i == 1) {
            return EFunctionStatus.SUPPORT_OPEN;
        }
        return i == 2 ? EFunctionStatus.SUPPORT_CLOSE : EFunctionStatus.UNKONW;
    }

    public EDeviceStatus getDeviceStateEnum() {
        int i = this.deviceState;
        if (i == 0) {
            return EDeviceStatus.FREE;
        }
        if (i == 1) {
            return EDeviceStatus.DETECT_BP;
        }
        if (i == 2) {
            return EDeviceStatus.DETECT_HEART;
        }
        if (i == 3) {
            return EDeviceStatus.DETECT_AUTO_FIVE;
        }
        if (i == 4) {
            return EDeviceStatus.DETECT_SP;
        }
        return i == 5 ? EDeviceStatus.DETECT_FTG : EDeviceStatus.UNKONW;
    }

    public String toString() {
        return "BreathData{functionState =" + this.functionState + ", deviceState =" + this.deviceState + ", functionState enum=" + getFunctionStateEnum() + ", deviceState enum=" + getDeviceStateEnum() + ", progressValue=" + this.progressValue + ", value=" + this.value + '}';
    }
}
