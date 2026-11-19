package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ReportGpsLatLongData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\bHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcom/veepoo/protocol/model/datas/ReportGpsLatLongData;", "", "isOpen", "", "lon", "", "lat", "altitude", "", "<init>", "(ZDDI)V", "()Z", "setOpen", "(Z)V", "getLon", "()D", "setLon", "(D)V", "getLat", "setLat", "getAltitude", "()I", "setAltitude", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class ReportGpsLatLongData {
    private boolean isOpen;
    private double lon;
    private double lat;
    private int altitude;

    public ReportGpsLatLongData(boolean z, double d, double d2, int i) {
        this.isOpen = z;
        this.lon = d;
        this.lat = d2;
        this.altitude = i;
    }

    public static /* synthetic */ ReportGpsLatLongData copy$default(ReportGpsLatLongData reportGpsLatLongData, boolean z, double d, double d2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = reportGpsLatLongData.isOpen;
        }
        if ((i2 & 2) != 0) {
            d = reportGpsLatLongData.lon;
        }
        if ((i2 & 4) != 0) {
            d2 = reportGpsLatLongData.lat;
        }
        if ((i2 & 8) != 0) {
            i = reportGpsLatLongData.altitude;
        }
        return reportGpsLatLongData.copy(z, d, d2, i);
    }

    public final boolean isOpen() {
        return this.isOpen;
    }

    public final void setOpen(boolean z) {
        this.isOpen = z;
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

    public final int getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(int i) {
        this.altitude = i;
    }

    public final boolean component1() {
        return this.isOpen;
    }

    public final double component2() {
        return this.lon;
    }

    public final double component3() {
        return this.lat;
    }

    public final int component4() {
        return this.altitude;
    }

    @NotNull
    public final ReportGpsLatLongData copy(boolean isOpen, double lon, double lat, int altitude) {
        return new ReportGpsLatLongData(isOpen, lon, lat, altitude);
    }

    @NotNull
    public String toString() {
        boolean z = this.isOpen;
        double d = this.lon;
        double d2 = this.lat;
        int i = this.altitude;
        return "ReportGpsLatLongData(isOpen=" + z + ", lon=" + d + ", lat=" + z + ", altitude=" + d2 + ")";
    }

    public int hashCode() {
        return (((((Boolean.hashCode(this.isOpen) * 31) + Double.hashCode(this.lon)) * 31) + Double.hashCode(this.lat)) * 31) + Integer.hashCode(this.altitude);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportGpsLatLongData)) {
            return false;
        }
        ReportGpsLatLongData reportGpsLatLongData = (ReportGpsLatLongData) other;
        return this.isOpen == reportGpsLatLongData.isOpen && Double.compare(this.lon, reportGpsLatLongData.lon) == 0 && Double.compare(this.lat, reportGpsLatLongData.lat) == 0 && this.altitude == reportGpsLatLongData.altitude;
    }
}
