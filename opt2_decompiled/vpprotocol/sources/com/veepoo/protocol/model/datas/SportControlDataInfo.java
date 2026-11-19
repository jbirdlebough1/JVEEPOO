package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ESportType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportControlDataInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010?\u001a\u00020@H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0013\"\u0004\b$\u0010\u0015R\u001a\u0010%\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0013\"\u0004\b*\u0010\u0015R\u001a\u0010+\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015R\u001a\u0010.\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0013\"\u0004\b0\u0010\u0015R\u001a\u00101\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0013\"\u0004\b3\u0010\u0015R\u001a\u00104\u001a\u000205X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>¨\u0006A"}, d2 = {"Lcom/veepoo/protocol/model/datas/SportControlDataInfo;", "", "<init>", "()V", "sportType", "Lcom/veepoo/protocol/model/enums/ESportType;", "getSportType", "()Lcom/veepoo/protocol/model/enums/ESportType;", "setSportType", "(Lcom/veepoo/protocol/model/enums/ESportType;)V", "opCode", "Lcom/veepoo/protocol/model/datas/SportOpCode;", "getOpCode", "()Lcom/veepoo/protocol/model/datas/SportOpCode;", "setOpCode", "(Lcom/veepoo/protocol/model/datas/SportOpCode;)V", "con", "", "getCon", "()I", "setCon", "(I)V", "runState", "Lcom/veepoo/protocol/model/datas/RunState;", "getRunState", "()Lcom/veepoo/protocol/model/datas/RunState;", "setRunState", "(Lcom/veepoo/protocol/model/datas/RunState;)V", "deviceState", "Lcom/veepoo/protocol/model/datas/DeviceState;", "getDeviceState", "()Lcom/veepoo/protocol/model/datas/DeviceState;", "setDeviceState", "(Lcom/veepoo/protocol/model/datas/DeviceState;)V", "sportDuration", "getSportDuration", "setSportDuration", "distance", "getDistance", "setDistance", "heartRate", "getHeartRate", "setHeartRate", "calories", "getCalories", "setCalories", "pace", "getPace", "setPace", "speed", "getSpeed", "setSpeed", "isGnssSport", "", "()Z", "setGnssSport", "(Z)V", "gpsSignal", "Lcom/veepoo/protocol/model/datas/GpsSignal;", "getGpsSignal", "()Lcom/veepoo/protocol/model/datas/GpsSignal;", "setGpsSignal", "(Lcom/veepoo/protocol/model/datas/GpsSignal;)V", "toString", "", "vpprotocol_release"})
public final class SportControlDataInfo {
    private int con;
    private int sportDuration;
    private int distance;
    private int heartRate;
    private int calories;
    private int pace;
    private int speed;
    private boolean isGnssSport;

    @NotNull
    private ESportType sportType = ESportType.NONE;

    @NotNull
    private SportOpCode opCode = SportOpCode.START;

    @NotNull
    private RunState runState = RunState.NOT_STARTED;

    @NotNull
    private DeviceState deviceState = DeviceState.NORMAL;

    @NotNull
    private GpsSignal gpsSignal = GpsSignal.NONE;

    @NotNull
    public final ESportType getSportType() {
        return this.sportType;
    }

    public final void setSportType(@NotNull ESportType eSportType) {
        Intrinsics.checkNotNullParameter(eSportType, "<set-?>");
        this.sportType = eSportType;
    }

    @NotNull
    public final SportOpCode getOpCode() {
        return this.opCode;
    }

    public final void setOpCode(@NotNull SportOpCode sportOpCode) {
        Intrinsics.checkNotNullParameter(sportOpCode, "<set-?>");
        this.opCode = sportOpCode;
    }

    public final int getCon() {
        return this.con;
    }

    public final void setCon(int i) {
        this.con = i;
    }

    @NotNull
    public final RunState getRunState() {
        return this.runState;
    }

    public final void setRunState(@NotNull RunState runState) {
        Intrinsics.checkNotNullParameter(runState, "<set-?>");
        this.runState = runState;
    }

    @NotNull
    public final DeviceState getDeviceState() {
        return this.deviceState;
    }

    public final void setDeviceState(@NotNull DeviceState deviceState) {
        Intrinsics.checkNotNullParameter(deviceState, "<set-?>");
        this.deviceState = deviceState;
    }

    public final int getSportDuration() {
        return this.sportDuration;
    }

    public final void setSportDuration(int i) {
        this.sportDuration = i;
    }

    public final int getDistance() {
        return this.distance;
    }

    public final void setDistance(int i) {
        this.distance = i;
    }

    public final int getHeartRate() {
        return this.heartRate;
    }

    public final void setHeartRate(int i) {
        this.heartRate = i;
    }

    public final int getCalories() {
        return this.calories;
    }

    public final void setCalories(int i) {
        this.calories = i;
    }

    public final int getPace() {
        return this.pace;
    }

    public final void setPace(int i) {
        this.pace = i;
    }

    public final int getSpeed() {
        return this.speed;
    }

    public final void setSpeed(int i) {
        this.speed = i;
    }

    public final boolean isGnssSport() {
        return this.isGnssSport;
    }

    public final void setGnssSport(boolean z) {
        this.isGnssSport = z;
    }

    @NotNull
    public final GpsSignal getGpsSignal() {
        return this.gpsSignal;
    }

    public final void setGpsSignal(@NotNull GpsSignal gpsSignal) {
        Intrinsics.checkNotNullParameter(gpsSignal, "<set-?>");
        this.gpsSignal = gpsSignal;
    }

    @NotNull
    public String toString() {
        return "SportControlDataInfo(sportType=" + this.sportType + ", opCode=" + this.opCode + ", con=" + this.con + ", runState=" + this.runState + ", deviceState=" + this.deviceState + ", sportDuration=" + this.sportDuration + ", distance=" + this.distance + ", heartRate=" + this.heartRate + ", calories=" + this.calories + ", pace=" + this.pace + ", speed=" + this.speed + ", isGnss=" + this.isGnssSport + ", gpsSignal=" + this.gpsSignal + ")";
    }
}
