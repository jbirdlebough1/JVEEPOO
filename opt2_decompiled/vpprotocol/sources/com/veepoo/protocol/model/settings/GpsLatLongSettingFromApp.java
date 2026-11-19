package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EGpsStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/GpsLatLongSettingFromApp.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010$\u001a\u00020%H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/veepoo/protocol/model/settings/GpsLatLongSettingFromApp;", "", "isReqeustRepo", "", "gpsState", "Lcom/veepoo/protocol/model/enums/EGpsStatus;", "lonv", "", "latv", "timeZone", "", "Timestamp", "", "<init>", "(ZLcom/veepoo/protocol/model/enums/EGpsStatus;DDSI)V", "()Z", "setReqeustRepo", "(Z)V", "getGpsState", "()Lcom/veepoo/protocol/model/enums/EGpsStatus;", "setGpsState", "(Lcom/veepoo/protocol/model/enums/EGpsStatus;)V", "getLonv", "()D", "setLonv", "(D)V", "getLatv", "setLatv", "getTimeZone", "()S", "setTimeZone", "(S)V", "getTimestamp", "()I", "setTimestamp", "(I)V", "toString", "", "vpprotocol_release"})
public final class GpsLatLongSettingFromApp {
    private boolean isReqeustRepo;

    @NotNull
    private EGpsStatus gpsState;
    private double lonv;
    private double latv;
    private short timeZone;
    private int Timestamp;

    public GpsLatLongSettingFromApp(boolean z, @NotNull EGpsStatus eGpsStatus, double d, double d2, short s, int i) {
        Intrinsics.checkNotNullParameter(eGpsStatus, "gpsState");
        this.isReqeustRepo = z;
        this.gpsState = eGpsStatus;
        this.lonv = d;
        this.latv = d2;
        this.timeZone = s;
        this.Timestamp = i;
    }

    public final boolean isReqeustRepo() {
        return this.isReqeustRepo;
    }

    public final void setReqeustRepo(boolean z) {
        this.isReqeustRepo = z;
    }

    @NotNull
    public final EGpsStatus getGpsState() {
        return this.gpsState;
    }

    public final void setGpsState(@NotNull EGpsStatus eGpsStatus) {
        Intrinsics.checkNotNullParameter(eGpsStatus, "<set-?>");
        this.gpsState = eGpsStatus;
    }

    public final double getLonv() {
        return this.lonv;
    }

    public final void setLonv(double d) {
        this.lonv = d;
    }

    public final double getLatv() {
        return this.latv;
    }

    public final void setLatv(double d) {
        this.latv = d;
    }

    public final short getTimeZone() {
        return this.timeZone;
    }

    public final void setTimeZone(short s) {
        this.timeZone = s;
    }

    public final int getTimestamp() {
        return this.Timestamp;
    }

    public final void setTimestamp(int i) {
        this.Timestamp = i;
    }

    @NotNull
    public String toString() {
        double d = this.lonv;
        double d2 = this.latv;
        short s = this.timeZone;
        int i = this.Timestamp;
        return "GpsLatLongSetting(lonv=" + d + ", latv=" + d + ", timeZone=" + d2 + ", Timestamp=" + d + ")";
    }
}
