package com.veepoo.protocol.model.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/GpsLatLongSetting.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015¨\u0006\u001e"}, d2 = {"Lcom/veepoo/protocol/model/settings/GpsLatLongSetting;", "", "lonv", "", "latv", "timeZone", "", "Timestamp", "", "altitude", "<init>", "(DDSIS)V", "getLonv", "()D", "setLonv", "(D)V", "getLatv", "setLatv", "getTimeZone", "()S", "setTimeZone", "(S)V", "getTimestamp", "()I", "setTimestamp", "(I)V", "getAltitude", "setAltitude", "toString", "", "vpprotocol_release"})
public final class GpsLatLongSetting {
    private double lonv;
    private double latv;
    private short timeZone;
    private int Timestamp;
    private short altitude;

    public GpsLatLongSetting(double d, double d2, short s, int i, short s2) {
        this.lonv = d;
        this.latv = d2;
        this.timeZone = s;
        this.Timestamp = i;
        this.altitude = s2;
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

    public final short getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(short s) {
        this.altitude = s;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect negative register number in instruction: 0x001d: STR_CONCAT 
          ("GpsLatLongSetting(lonv=")
          (r-1 I:??)
          (", latv=")
          (r0 I:??)
          (", timeZone=")
          (r0 I:??)
          (", Timestamp=")
          (r0 I:??)
          (", altitude=")
          (r1 I:??)
          (")")
        
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:76)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        /*
            r8 = this;
            r0 = r8
            double r0 = r0.lonv
            r1 = r8
            r2 = r1
            r3 = r2; r0 = r0; 
            double r3 = r3.latv
            r9 = r3
            short r2 = r2.timeZone
            r8 = r2
            int r1 = r1.Timestamp
            r11 = r1
            short r0 = r0.altitude
            r12 = r0
            r0 = r9
            r1 = r8
            r2 = r11
            r3 = r12
            "GpsLatLongSetting(lonv=" + r-1 + ", latv=" + r0 + ", timeZone=" + r0 + ", Timestamp=" + r0 + ", altitude=" + r1 + ")"
            return r-1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.veepoo.protocol.model.settings.GpsLatLongSetting.toString():java.lang.String");
    }
}
