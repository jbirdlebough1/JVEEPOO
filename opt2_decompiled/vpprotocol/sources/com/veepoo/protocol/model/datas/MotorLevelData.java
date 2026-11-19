package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EMotorLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MotorLevelData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/veepoo/protocol/model/datas/MotorLevelData;", "", "level", "Lcom/veepoo/protocol/model/enums/EMotorLevel;", "isSuccess", "", "isDeviceReport", "<init>", "(Lcom/veepoo/protocol/model/enums/EMotorLevel;ZZ)V", "getLevel", "()Lcom/veepoo/protocol/model/enums/EMotorLevel;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "vpprotocol_release"})
public final /* data */ class MotorLevelData {

    @NotNull
    private final EMotorLevel level;
    private final boolean isSuccess;
    private final boolean isDeviceReport;

    public MotorLevelData(@NotNull EMotorLevel eMotorLevel, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(eMotorLevel, "level");
        this.level = eMotorLevel;
        this.isSuccess = z;
        this.isDeviceReport = z2;
    }

    public static /* synthetic */ MotorLevelData copy$default(MotorLevelData motorLevelData, EMotorLevel eMotorLevel, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            eMotorLevel = motorLevelData.level;
        }
        if ((i & 2) != 0) {
            z = motorLevelData.isSuccess;
        }
        if ((i & 4) != 0) {
            z2 = motorLevelData.isDeviceReport;
        }
        return motorLevelData.copy(eMotorLevel, z, z2);
    }

    @NotNull
    public final EMotorLevel getLevel() {
        return this.level;
    }

    public final boolean isSuccess() {
        return this.isSuccess;
    }

    public final boolean isDeviceReport() {
        return this.isDeviceReport;
    }

    @NotNull
    public final EMotorLevel component1() {
        return this.level;
    }

    public final boolean component2() {
        return this.isSuccess;
    }

    public final boolean component3() {
        return this.isDeviceReport;
    }

    @NotNull
    public final MotorLevelData copy(@NotNull EMotorLevel level, boolean isSuccess, boolean isDeviceReport) {
        Intrinsics.checkNotNullParameter(level, "level");
        return new MotorLevelData(level, isSuccess, isDeviceReport);
    }

    @NotNull
    public String toString() {
        return "MotorLevelData(level=" + this.level + ", isSuccess=" + this.isSuccess + ", isDeviceReport=" + this.isDeviceReport + ")";
    }

    public int hashCode() {
        return (((this.level.hashCode() * 31) + Boolean.hashCode(this.isSuccess)) * 31) + Boolean.hashCode(this.isDeviceReport);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MotorLevelData)) {
            return false;
        }
        MotorLevelData motorLevelData = (MotorLevelData) other;
        return this.level == motorLevelData.level && this.isSuccess == motorLevelData.isSuccess && this.isDeviceReport == motorLevelData.isDeviceReport;
    }
}
