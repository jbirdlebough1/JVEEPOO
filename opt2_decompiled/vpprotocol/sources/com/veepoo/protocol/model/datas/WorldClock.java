package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/WorldClock.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J1\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020\u0003HÖ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006$"}, d2 = {"Lcom/veepoo/protocol/model/datas/WorldClock;", "", "id", "", "timeZone", "timeZoneName", "", "genericTimeZoneName", "<init>", "(IILjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "setId", "(I)V", "getTimeZone", "setTimeZone", "getTimeZoneName", "()Ljava/lang/String;", "setTimeZoneName", "(Ljava/lang/String;)V", "getGenericTimeZoneName", "setGenericTimeZoneName", "isOpen", "", "()Z", "setOpen", "(Z)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "vpprotocol_release"})
public final /* data */ class WorldClock {
    private int id;
    private int timeZone;

    @NotNull
    private String timeZoneName;

    @NotNull
    private String genericTimeZoneName;
    private boolean isOpen;

    public WorldClock(int i, int i2, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "timeZoneName");
        Intrinsics.checkNotNullParameter(str2, "genericTimeZoneName");
        this.id = i;
        this.timeZone = i2;
        this.timeZoneName = str;
        this.genericTimeZoneName = str2;
    }

    public static /* synthetic */ WorldClock copy$default(WorldClock worldClock, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = worldClock.id;
        }
        if ((i3 & 2) != 0) {
            i2 = worldClock.timeZone;
        }
        if ((i3 & 4) != 0) {
            str = worldClock.timeZoneName;
        }
        if ((i3 & 8) != 0) {
            str2 = worldClock.genericTimeZoneName;
        }
        return worldClock.copy(i, i2, str, str2);
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final int getTimeZone() {
        return this.timeZone;
    }

    public final void setTimeZone(int i) {
        this.timeZone = i;
    }

    @NotNull
    public final String getTimeZoneName() {
        return this.timeZoneName;
    }

    public final void setTimeZoneName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timeZoneName = str;
    }

    @NotNull
    public final String getGenericTimeZoneName() {
        return this.genericTimeZoneName;
    }

    public final void setGenericTimeZoneName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.genericTimeZoneName = str;
    }

    public final boolean isOpen() {
        return this.isOpen;
    }

    public final void setOpen(boolean z) {
        this.isOpen = z;
    }

    public final int component1() {
        return this.id;
    }

    public final int component2() {
        return this.timeZone;
    }

    @NotNull
    public final String component3() {
        return this.timeZoneName;
    }

    @NotNull
    public final String component4() {
        return this.genericTimeZoneName;
    }

    @NotNull
    public final WorldClock copy(int id, int timeZone, @NotNull String timeZoneName, @NotNull String genericTimeZoneName) {
        Intrinsics.checkNotNullParameter(timeZoneName, "timeZoneName");
        Intrinsics.checkNotNullParameter(genericTimeZoneName, "genericTimeZoneName");
        return new WorldClock(id, timeZone, timeZoneName, genericTimeZoneName);
    }

    @NotNull
    public String toString() {
        return "WorldClock(id=" + this.id + ", timeZone=" + this.timeZone + ", timeZoneName=" + this.timeZoneName + ", genericTimeZoneName=" + this.genericTimeZoneName + ")";
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.id) * 31) + Integer.hashCode(this.timeZone)) * 31) + this.timeZoneName.hashCode()) * 31) + this.genericTimeZoneName.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WorldClock)) {
            return false;
        }
        WorldClock worldClock = (WorldClock) other;
        return this.id == worldClock.id && this.timeZone == worldClock.timeZone && Intrinsics.areEqual(this.timeZoneName, worldClock.timeZoneName) && Intrinsics.areEqual(this.genericTimeZoneName, worldClock.genericTimeZoneName);
    }

    public /* synthetic */ WorldClock(int i, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, str, (i3 & 8) != 0 ? "" : str2);
    }
}
