package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EQH15SecurityProtectionState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QH15SecurityProtectionModel.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B1\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\fJ\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020 H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010¨\u0006\""}, d2 = {"Lcom/veepoo/protocol/model/datas/QH15SecurityProtectionModel;", "", "<init>", "()V", "rssi", "", "latitude", "", "longitude", "gnssState", "Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;", "altitude", "(IDDLcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;I)V", "getRssi", "()I", "setRssi", "(I)V", "getLatitude", "()D", "setLatitude", "(D)V", "getLongitude", "setLongitude", "getGnssState", "()Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;", "setGnssState", "(Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;)V", "getAltitude", "setAltitude", "toGnssLocationData", "Lcom/veepoo/protocol/model/datas/GnssLocationData;", "toString", "", "Companion", "vpprotocol_release"})
public final class QH15SecurityProtectionModel {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private int rssi;
    private double latitude;
    private double longitude;

    @NotNull
    private EQH15SecurityProtectionState gnssState;
    private int altitude;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QH15SecurityProtectionModel$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/datas/QH15SecurityProtectionModel$Companion;", "", "<init>", "()V", "fromGnssLocationData", "Lcom/veepoo/protocol/model/datas/QH15SecurityProtectionModel;", "data", "Lcom/veepoo/protocol/model/datas/GnssLocationData;", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final QH15SecurityProtectionModel fromGnssLocationData(@NotNull GnssLocationData data) {
            Intrinsics.checkNotNullParameter(data, "data");
            return new QH15SecurityProtectionModel(data.getRssi(), data.getLatitude(), data.getLongitude(), EQH15SecurityProtectionState.INSTANCE.fromEGNSSState(data.getState()), data.getAltitude());
        }
    }

    public QH15SecurityProtectionModel() {
        this.rssi = -128;
        this.gnssState = EQH15SecurityProtectionState.NONE;
    }

    public final int getRssi() {
        return this.rssi;
    }

    public final void setRssi(int i) {
        this.rssi = i;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(double d) {
        this.latitude = d;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(double d) {
        this.longitude = d;
    }

    @NotNull
    public final EQH15SecurityProtectionState getGnssState() {
        return this.gnssState;
    }

    public final void setGnssState(@NotNull EQH15SecurityProtectionState eQH15SecurityProtectionState) {
        Intrinsics.checkNotNullParameter(eQH15SecurityProtectionState, "<set-?>");
        this.gnssState = eQH15SecurityProtectionState;
    }

    public final int getAltitude() {
        return this.altitude;
    }

    public final void setAltitude(int i) {
        this.altitude = i;
    }

    @NotNull
    public final GnssLocationData toGnssLocationData() {
        return new GnssLocationData(this.gnssState.toEGNSSState(), this.rssi, this.longitude, this.latitude, this.altitude);
    }

    @NotNull
    public String toString() {
        int i = this.rssi;
        double d = this.latitude;
        double d2 = this.longitude;
        EQH15SecurityProtectionState eQH15SecurityProtectionState = this.gnssState;
        int i2 = this.altitude;
        return "QH15SecurityProtectionModel(rssi=" + i + ", latitude=" + d + ", longitude=" + i + ", gnssState=" + d2 + ", altitude=" + i + ")";
    }

    public QH15SecurityProtectionModel(int i, double d, double d2, @NotNull EQH15SecurityProtectionState eQH15SecurityProtectionState, int i2) {
        Intrinsics.checkNotNullParameter(eQH15SecurityProtectionState, "gnssState");
        this.rssi = -128;
        EQH15SecurityProtectionState.Companion companion = EQH15SecurityProtectionState.INSTANCE;
        this.rssi = i;
        this.latitude = d;
        this.longitude = d2;
        this.gnssState = eQH15SecurityProtectionState;
        this.altitude = i2;
    }
}
