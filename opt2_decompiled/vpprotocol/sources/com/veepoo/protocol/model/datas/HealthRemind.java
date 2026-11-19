package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.HealthRemindType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HealthRemind.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J;\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010#\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\bHÖ\u0001J\t\u0010&\u001a\u00020'HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006("}, d2 = {"Lcom/veepoo/protocol/model/datas/HealthRemind;", "", "remindType", "Lcom/veepoo/protocol/model/enums/HealthRemindType;", "startTime", "Lcom/veepoo/protocol/model/datas/TimeData;", "endTime", "interval", "", "status", "", "<init>", "(Lcom/veepoo/protocol/model/enums/HealthRemindType;Lcom/veepoo/protocol/model/datas/TimeData;Lcom/veepoo/protocol/model/datas/TimeData;IZ)V", "getRemindType", "()Lcom/veepoo/protocol/model/enums/HealthRemindType;", "getStartTime", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setStartTime", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "getEndTime", "setEndTime", "getInterval", "()I", "setInterval", "(I)V", "getStatus", "()Z", "setStatus", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class HealthRemind {

    @NotNull
    private final HealthRemindType remindType;

    @NotNull
    private TimeData startTime;

    @NotNull
    private TimeData endTime;
    private int interval;
    private boolean status;

    public HealthRemind(@NotNull HealthRemindType healthRemindType, @NotNull TimeData timeData, @NotNull TimeData timeData2, int i, boolean z) {
        Intrinsics.checkNotNullParameter(healthRemindType, "remindType");
        Intrinsics.checkNotNullParameter(timeData, "startTime");
        Intrinsics.checkNotNullParameter(timeData2, "endTime");
        this.remindType = healthRemindType;
        this.startTime = timeData;
        this.endTime = timeData2;
        this.interval = i;
        this.status = z;
    }

    public static /* synthetic */ HealthRemind copy$default(HealthRemind healthRemind, HealthRemindType healthRemindType, TimeData timeData, TimeData timeData2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            healthRemindType = healthRemind.remindType;
        }
        if ((i2 & 2) != 0) {
            timeData = healthRemind.startTime;
        }
        if ((i2 & 4) != 0) {
            timeData2 = healthRemind.endTime;
        }
        if ((i2 & 8) != 0) {
            i = healthRemind.interval;
        }
        if ((i2 & 16) != 0) {
            z = healthRemind.status;
        }
        return healthRemind.copy(healthRemindType, timeData, timeData2, i, z);
    }

    @NotNull
    public final HealthRemindType getRemindType() {
        return this.remindType;
    }

    @NotNull
    public final TimeData getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(@NotNull TimeData timeData) {
        Intrinsics.checkNotNullParameter(timeData, "<set-?>");
        this.startTime = timeData;
    }

    @NotNull
    public final TimeData getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(@NotNull TimeData timeData) {
        Intrinsics.checkNotNullParameter(timeData, "<set-?>");
        this.endTime = timeData;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final void setInterval(int i) {
        this.interval = i;
    }

    public final boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean z) {
        this.status = z;
    }

    @NotNull
    public final HealthRemindType component1() {
        return this.remindType;
    }

    @NotNull
    public final TimeData component2() {
        return this.startTime;
    }

    @NotNull
    public final TimeData component3() {
        return this.endTime;
    }

    public final int component4() {
        return this.interval;
    }

    public final boolean component5() {
        return this.status;
    }

    @NotNull
    public final HealthRemind copy(@NotNull HealthRemindType remindType, @NotNull TimeData startTime, @NotNull TimeData endTime, int interval, boolean status) {
        Intrinsics.checkNotNullParameter(remindType, "remindType");
        Intrinsics.checkNotNullParameter(startTime, "startTime");
        Intrinsics.checkNotNullParameter(endTime, "endTime");
        return new HealthRemind(remindType, startTime, endTime, interval, status);
    }

    @NotNull
    public String toString() {
        return "HealthRemind(remindType=" + this.remindType + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", interval=" + this.interval + ", status=" + this.status + ")";
    }

    public int hashCode() {
        return (((((((this.remindType.hashCode() * 31) + this.startTime.hashCode()) * 31) + this.endTime.hashCode()) * 31) + Integer.hashCode(this.interval)) * 31) + Boolean.hashCode(this.status);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthRemind)) {
            return false;
        }
        HealthRemind healthRemind = (HealthRemind) other;
        return this.remindType == healthRemind.remindType && Intrinsics.areEqual(this.startTime, healthRemind.startTime) && Intrinsics.areEqual(this.endTime, healthRemind.endTime) && this.interval == healthRemind.interval && this.status == healthRemind.status;
    }
}
