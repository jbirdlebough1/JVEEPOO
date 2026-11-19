package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EAutoMeasureType;
import com.veepoo.protocol.model.enums.ESensitivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AutoMeasureData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u00104\u001a\u000205H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0007\"\u0004\b\u0017\u0010\tR\u001a\u0010\u0018\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0012\"\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001a\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001a\u0010\u001f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\tR\u001a\u0010\"\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\tR\u001a\u0010%\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0007\"\u0004\b'\u0010\tR\u001a\u0010(\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0007\"\u0004\b3\u0010\t¨\u00066"}, d2 = {"Lcom/veepoo/protocol/model/datas/AutoMeasureData;", "", "<init>", "()V", "protocolType", "", "getProtocolType", "()I", "setProtocolType", "(I)V", "funType", "Lcom/veepoo/protocol/model/enums/EAutoMeasureType;", "getFunType", "()Lcom/veepoo/protocol/model/enums/EAutoMeasureType;", "setFunType", "(Lcom/veepoo/protocol/model/enums/EAutoMeasureType;)V", "isSwitchOpen", "", "()Z", "setSwitchOpen", "(Z)V", "stepUnit", "getStepUnit", "setStepUnit", "isSlotModify", "setSlotModify", "isIntervalModify", "setIntervalModify", "supportStartMinute", "getSupportStartMinute", "setSupportStartMinute", "supportEndMinute", "getSupportEndMinute", "setSupportEndMinute", "measureInterval", "getMeasureInterval", "setMeasureInterval", "currentStartMinute", "getCurrentStartMinute", "setCurrentStartMinute", "currentEndMinute", "getCurrentEndMinute", "setCurrentEndMinute", "sensitivity", "Lcom/veepoo/protocol/model/enums/ESensitivity;", "getSensitivity", "()Lcom/veepoo/protocol/model/enums/ESensitivity;", "setSensitivity", "(Lcom/veepoo/protocol/model/enums/ESensitivity;)V", "lowOxygenAlertSwitch", "getLowOxygenAlertSwitch", "setLowOxygenAlertSwitch", "toString", "", "vpprotocol_release"})
public final class AutoMeasureData {
    private int protocolType;
    private boolean isSwitchOpen;
    private int stepUnit;
    private boolean isSlotModify;
    private boolean isIntervalModify;
    private int supportStartMinute;
    private int supportEndMinute;
    private int measureInterval;
    private int currentStartMinute;
    private int currentEndMinute;

    @NotNull
    private EAutoMeasureType funType = EAutoMeasureType.PULSE_RATE;

    @NotNull
    private ESensitivity sensitivity = ESensitivity.UNSUPPORT;
    private int lowOxygenAlertSwitch = -1;

    public final int getProtocolType() {
        return this.protocolType;
    }

    public final void setProtocolType(int i) {
        this.protocolType = i;
    }

    @NotNull
    public final EAutoMeasureType getFunType() {
        return this.funType;
    }

    public final void setFunType(@NotNull EAutoMeasureType eAutoMeasureType) {
        Intrinsics.checkNotNullParameter(eAutoMeasureType, "<set-?>");
        this.funType = eAutoMeasureType;
    }

    public final boolean isSwitchOpen() {
        return this.isSwitchOpen;
    }

    public final void setSwitchOpen(boolean z) {
        this.isSwitchOpen = z;
    }

    public final int getStepUnit() {
        return this.stepUnit;
    }

    public final void setStepUnit(int i) {
        this.stepUnit = i;
    }

    public final boolean isSlotModify() {
        return this.isSlotModify;
    }

    public final void setSlotModify(boolean z) {
        this.isSlotModify = z;
    }

    public final boolean isIntervalModify() {
        return this.isIntervalModify;
    }

    public final void setIntervalModify(boolean z) {
        this.isIntervalModify = z;
    }

    public final int getSupportStartMinute() {
        return this.supportStartMinute;
    }

    public final void setSupportStartMinute(int i) {
        this.supportStartMinute = i;
    }

    public final int getSupportEndMinute() {
        return this.supportEndMinute;
    }

    public final void setSupportEndMinute(int i) {
        this.supportEndMinute = i;
    }

    public final int getMeasureInterval() {
        return this.measureInterval;
    }

    public final void setMeasureInterval(int i) {
        this.measureInterval = i;
    }

    public final int getCurrentStartMinute() {
        return this.currentStartMinute;
    }

    public final void setCurrentStartMinute(int i) {
        this.currentStartMinute = i;
    }

    public final int getCurrentEndMinute() {
        return this.currentEndMinute;
    }

    public final void setCurrentEndMinute(int i) {
        this.currentEndMinute = i;
    }

    @NotNull
    public final ESensitivity getSensitivity() {
        return this.sensitivity;
    }

    public final void setSensitivity(@NotNull ESensitivity eSensitivity) {
        Intrinsics.checkNotNullParameter(eSensitivity, "<set-?>");
        this.sensitivity = eSensitivity;
    }

    public final int getLowOxygenAlertSwitch() {
        return this.lowOxygenAlertSwitch;
    }

    public final void setLowOxygenAlertSwitch(int i) {
        this.lowOxygenAlertSwitch = i;
    }

    @NotNull
    public String toString() {
        return "AutoMeasureData(protocolType=" + this.protocolType + ", funType=" + this.funType + ", isSwitchOpen=" + this.isSwitchOpen + ", stepUnit=" + this.stepUnit + ", isSlotModify=" + this.isSlotModify + ", isIntervalModify=" + this.isIntervalModify + ", supportStartMinute=" + this.supportStartMinute + ", supportEndMinute=" + this.supportEndMinute + ", measureInterval=" + this.measureInterval + ", currentStartMinute=" + this.currentStartMinute + ", currentEndMinute=" + this.currentEndMinute + ", sensitivity=" + this.sensitivity + ", lowOxygenAlertSwitch=" + this.lowOxygenAlertSwitch + ")";
    }
}
