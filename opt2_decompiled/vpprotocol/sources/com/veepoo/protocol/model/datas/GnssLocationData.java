package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EGNSSState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/GnssLocationData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B1\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\u0002\u0010\fJ\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014¨\u0006\u001f"}, d2 = {"Lcom/veepoo/protocol/model/datas/GnssLocationData;", "", "<init>", "()V", "state", "Lcom/veepoo/protocol/model/enums/EGNSSState;", "rssi", "", "longitude", "", "latitude", "altitude", "(Lcom/veepoo/protocol/model/enums/EGNSSState;IDDI)V", "getState", "()Lcom/veepoo/protocol/model/enums/EGNSSState;", "setState", "(Lcom/veepoo/protocol/model/enums/EGNSSState;)V", "getRssi", "()I", "setRssi", "(I)V", "getLongitude", "()D", "setLongitude", "(D)V", "getLatitude", "setLatitude", "getAltitude", "setAltitude", "toString", "", "vpprotocol_release"})
public final class GnssLocationData {

    @NotNull
    private EGNSSState state;
    private int rssi;
    private double longitude;
    private double latitude;
    private int altitude;

    public GnssLocationData() {
        this.state = EGNSSState.UNKNOWN;
        this.rssi = -128;
    }

    @NotNull
    public final EGNSSState getState() {
        return this.state;
    }

    public final void setState(@NotNull EGNSSState eGNSSState) {
        Intrinsics.checkNotNullParameter(eGNSSState, "<set-?>");
        this.state = eGNSSState;
    }

    public final int getRssi() {
        return this.rssi;
    }

    public final void setRssi(int i) {
        this.rssi = i;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(double d) {
        this.longitude = d;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double d) {
        this.latitude = d;
    }

    public final int getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(int i) {
        this.altitude = i;
    }

    @NotNull
    public String toString() {
        EGNSSState eGNSSState = this.state;
        int i = this.rssi;
        double d = this.longitude;
        double d2 = this.latitude;
        int i2 = this.altitude;
        return "GNSSSafetyProtectionData(state=" + eGNSSState + ", rssi=" + i + ", longitude=" + d + ", latitude=" + eGNSSState + ", altitude=" + d2 + ")";
    }

    public GnssLocationData(@NotNull EGNSSState eGNSSState, int i, double d, double d2, int i2) {
        Intrinsics.checkNotNullParameter(eGNSSState, "state");
        EGNSSState.Companion companion = EGNSSState.INSTANCE;
        this.state = eGNSSState;
        this.rssi = i;
        this.longitude = d;
        this.latitude = d2;
        this.altitude = i2;
    }
}
