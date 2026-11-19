package com.veepoo.protocol.model.settings;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/KaabaSetting.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/veepoo/protocol/model/settings/KaabaSetting;", "", "lon", "", "lat", "altitude", "", "<init>", "(DDS)V", "getLon", "()D", "setLon", "(D)V", "getLat", "setLat", "getAltitude", "()S", "setAltitude", "(S)V", "toString", "", "vpprotocol_release"})
public final class KaabaSetting {
    private double lon;
    private double lat;
    private short altitude;

    public KaabaSetting(double d, double d2, short s) {
        this.lon = d;
        this.lat = d2;
        this.altitude = s;
    }

    public final double getLon() {
        return this.lon;
    }

    public final void setLon(double d) {
        this.lon = d;
    }

    public final double getLat() {
        return this.lat;
    }

    public final void setLat(double d) {
        this.lat = d;
    }

    public final short getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(short s) {
        this.altitude = s;
    }

    @NotNull
    public String toString() {
        double d = this.lon;
        double d2 = this.lat;
        short s = this.altitude;
        return "KaabaSetting(lon=" + d + ", lat=" + d + ",  altitude=" + d2 + ")";
    }
}
