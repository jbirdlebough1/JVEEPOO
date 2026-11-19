package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ERemindEvent;
import com.veepoo.protocol.model.enums.ESportStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/RemindEvent.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J1\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\tHÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/veepoo/protocol/model/datas/RemindEvent;", "", "remindEvent", "Lcom/veepoo/protocol/model/enums/ERemindEvent;", "timestamp", "", "sportStatus", "Lcom/veepoo/protocol/model/enums/ESportStatus;", "attitude", "", "<init>", "(Lcom/veepoo/protocol/model/enums/ERemindEvent;JLcom/veepoo/protocol/model/enums/ESportStatus;I)V", "getRemindEvent", "()Lcom/veepoo/protocol/model/enums/ERemindEvent;", "getTimestamp", "()J", "getSportStatus", "()Lcom/veepoo/protocol/model/enums/ESportStatus;", "getAttitude", "()I", "version", "getVersion", "setVersion", "(I)V", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class RemindEvent {

    @NotNull
    private final ERemindEvent remindEvent;
    private final long timestamp;

    @NotNull
    private final ESportStatus sportStatus;
    private final int attitude;
    private int version;

    public RemindEvent(@NotNull ERemindEvent eRemindEvent, long j, @NotNull ESportStatus eSportStatus, int i) {
        Intrinsics.checkNotNullParameter(eRemindEvent, "remindEvent");
        Intrinsics.checkNotNullParameter(eSportStatus, "sportStatus");
        this.remindEvent = eRemindEvent;
        this.timestamp = j;
        this.sportStatus = eSportStatus;
        this.attitude = i;
        this.version = 1;
    }

    public static /* synthetic */ RemindEvent copy$default(RemindEvent remindEvent, ERemindEvent eRemindEvent, long j, ESportStatus eSportStatus, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            eRemindEvent = remindEvent.remindEvent;
        }
        if ((i2 & 2) != 0) {
            j = remindEvent.timestamp;
        }
        if ((i2 & 4) != 0) {
            eSportStatus = remindEvent.sportStatus;
        }
        if ((i2 & 8) != 0) {
            i = remindEvent.attitude;
        }
        return remindEvent.copy(eRemindEvent, j, eSportStatus, i);
    }

    @NotNull
    public final ERemindEvent getRemindEvent() {
        return this.remindEvent;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    @NotNull
    public final ESportStatus getSportStatus() {
        return this.sportStatus;
    }

    public final int getAttitude() {
        return this.attitude;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int i) {
        this.version = i;
    }

    @NotNull
    public final ERemindEvent component1() {
        return this.remindEvent;
    }

    public final long component2() {
        return this.timestamp;
    }

    @NotNull
    public final ESportStatus component3() {
        return this.sportStatus;
    }

    public final int component4() {
        return this.attitude;
    }

    @NotNull
    public final RemindEvent copy(@NotNull ERemindEvent remindEvent, long timestamp, @NotNull ESportStatus sportStatus, int attitude) {
        Intrinsics.checkNotNullParameter(remindEvent, "remindEvent");
        Intrinsics.checkNotNullParameter(sportStatus, "sportStatus");
        return new RemindEvent(remindEvent, timestamp, sportStatus, attitude);
    }

    @NotNull
    public String toString() {
        ERemindEvent eRemindEvent = this.remindEvent;
        long j = this.timestamp;
        ESportStatus eSportStatus = this.sportStatus;
        int i = this.attitude;
        return "RemindEvent(remindEvent=" + eRemindEvent + ", timestamp=" + j + ", sportStatus=" + eRemindEvent + ", attitude=" + eSportStatus + ")";
    }

    public int hashCode() {
        return (((((this.remindEvent.hashCode() * 31) + Long.hashCode(this.timestamp)) * 31) + this.sportStatus.hashCode()) * 31) + Integer.hashCode(this.attitude);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RemindEvent)) {
            return false;
        }
        RemindEvent remindEvent = (RemindEvent) other;
        return this.remindEvent == remindEvent.remindEvent && this.timestamp == remindEvent.timestamp && this.sportStatus == remindEvent.sportStatus && this.attitude == remindEvent.attitude;
    }
}
