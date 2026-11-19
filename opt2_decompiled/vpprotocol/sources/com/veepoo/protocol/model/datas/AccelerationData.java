package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AccelerationData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/veepoo/protocol/model/datas/AccelerationData;", "", "x", "", "y", "z", "<init>", "(III)V", "getX", "()I", "getY", "getZ", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class AccelerationData {
    private final int x;
    private final int y;
    private final int z;

    public AccelerationData(int i, int i2, int i3) {
        this.x = i;
        this.y = i2;
        this.z = i3;
    }

    public static /* synthetic */ AccelerationData copy$default(AccelerationData accelerationData, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = accelerationData.x;
        }
        if ((i4 & 2) != 0) {
            i2 = accelerationData.y;
        }
        if ((i4 & 4) != 0) {
            i3 = accelerationData.z;
        }
        return accelerationData.copy(i, i2, i3);
    }

    public final int getX() {
        return this.x;
    }

    public final int getY() {
        return this.y;
    }

    public final int getZ() {
        return this.z;
    }

    public final int component1() {
        return this.x;
    }

    public final int component2() {
        return this.y;
    }

    public final int component3() {
        return this.z;
    }

    @NotNull
    public final AccelerationData copy(int x, int y, int z) {
        return new AccelerationData(x, y, z);
    }

    @NotNull
    public String toString() {
        return "AccelerationData(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
    }

    public int hashCode() {
        return (((Integer.hashCode(this.x) * 31) + Integer.hashCode(this.y)) * 31) + Integer.hashCode(this.z);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccelerationData)) {
            return false;
        }
        AccelerationData accelerationData = (AccelerationData) other;
        return this.x == accelerationData.x && this.y == accelerationData.y && this.z == accelerationData.z;
    }
}
