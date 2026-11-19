package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EHealthAlarmType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HealthAlarmInterval.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/veepoo/protocol/model/datas/HealthAlarmInterval;", "", "healthType", "Lcom/veepoo/protocol/model/enums/EHealthAlarmType;", "ceilingValue", "", "floorValue", "isOpen", "", "<init>", "(Lcom/veepoo/protocol/model/enums/EHealthAlarmType;FFZ)V", "getHealthType", "()Lcom/veepoo/protocol/model/enums/EHealthAlarmType;", "getCeilingValue", "()F", "getFloorValue", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "vpprotocol_release"})
public final /* data */ class HealthAlarmInterval {

    @NotNull
    private final EHealthAlarmType healthType;
    private final float ceilingValue;
    private final float floorValue;
    private final boolean isOpen;

    public HealthAlarmInterval(@NotNull EHealthAlarmType eHealthAlarmType, float f, float f2, boolean z) {
        Intrinsics.checkNotNullParameter(eHealthAlarmType, "healthType");
        this.healthType = eHealthAlarmType;
        this.ceilingValue = f;
        this.floorValue = f2;
        this.isOpen = z;
    }

    public static /* synthetic */ HealthAlarmInterval copy$default(HealthAlarmInterval healthAlarmInterval, EHealthAlarmType eHealthAlarmType, float f, float f2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            eHealthAlarmType = healthAlarmInterval.healthType;
        }
        if ((i & 2) != 0) {
            f = healthAlarmInterval.ceilingValue;
        }
        if ((i & 4) != 0) {
            f2 = healthAlarmInterval.floorValue;
        }
        if ((i & 8) != 0) {
            z = healthAlarmInterval.isOpen;
        }
        return healthAlarmInterval.copy(eHealthAlarmType, f, f2, z);
    }

    @NotNull
    public final EHealthAlarmType getHealthType() {
        return this.healthType;
    }

    public final float getCeilingValue() {
        return this.ceilingValue;
    }

    public final float getFloorValue() {
        return this.floorValue;
    }

    public final boolean isOpen() {
        return this.isOpen;
    }

    @NotNull
    public final EHealthAlarmType component1() {
        return this.healthType;
    }

    public final float component2() {
        return this.ceilingValue;
    }

    public final float component3() {
        return this.floorValue;
    }

    public final boolean component4() {
        return this.isOpen;
    }

    @NotNull
    public final HealthAlarmInterval copy(@NotNull EHealthAlarmType healthType, float ceilingValue, float floorValue, boolean isOpen) {
        Intrinsics.checkNotNullParameter(healthType, "healthType");
        return new HealthAlarmInterval(healthType, ceilingValue, floorValue, isOpen);
    }

    @NotNull
    public String toString() {
        return "HealthAlarmInterval(healthType=" + this.healthType + ", ceilingValue=" + this.ceilingValue + ", floorValue=" + this.floorValue + ", isOpen=" + this.isOpen + ")";
    }

    public int hashCode() {
        return (((((this.healthType.hashCode() * 31) + Float.hashCode(this.ceilingValue)) * 31) + Float.hashCode(this.floorValue)) * 31) + Boolean.hashCode(this.isOpen);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthAlarmInterval)) {
            return false;
        }
        HealthAlarmInterval healthAlarmInterval = (HealthAlarmInterval) other;
        return this.healthType == healthAlarmInterval.healthType && Float.compare(this.ceilingValue, healthAlarmInterval.ceilingValue) == 0 && Float.compare(this.floorValue, healthAlarmInterval.floorValue) == 0 && this.isOpen == healthAlarmInterval.isOpen;
    }
}
