package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ChantingData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JO\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006$"}, d2 = {"Lcom/veepoo/protocol/model/datas/ChantingData;", "", "totalPackage", "", "currentPackage", "startTimestamp", "", "stopTimestamp", "passagePosition", "sumPassageCount", "oneDayChantingCount", "<init>", "(IIJJIJI)V", "getTotalPackage", "()I", "getCurrentPackage", "getStartTimestamp", "()J", "getStopTimestamp", "getPassagePosition", "getSumPassageCount", "getOneDayChantingCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class ChantingData {
    private final int totalPackage;
    private final int currentPackage;
    private final long startTimestamp;
    private final long stopTimestamp;
    private final int passagePosition;
    private final long sumPassageCount;
    private final int oneDayChantingCount;

    public ChantingData(int i, int i2, long j, long j2, int i3, long j3, int i4) {
        this.totalPackage = i;
        this.currentPackage = i2;
        this.startTimestamp = j;
        this.stopTimestamp = j2;
        this.passagePosition = i3;
        this.sumPassageCount = j3;
        this.oneDayChantingCount = i4;
    }

    public static /* synthetic */ ChantingData copy$default(ChantingData chantingData, int i, int i2, long j, long j2, int i3, long j3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = chantingData.totalPackage;
        }
        if ((i5 & 2) != 0) {
            i2 = chantingData.currentPackage;
        }
        if ((i5 & 4) != 0) {
            j = chantingData.startTimestamp;
        }
        if ((i5 & 8) != 0) {
            j2 = chantingData.stopTimestamp;
        }
        if ((i5 & 16) != 0) {
            i3 = chantingData.passagePosition;
        }
        if ((i5 & 32) != 0) {
            j3 = chantingData.sumPassageCount;
        }
        if ((i5 & 64) != 0) {
            i4 = chantingData.oneDayChantingCount;
        }
        return chantingData.copy(i, i2, j, j2, i3, j3, i4);
    }

    public final int getTotalPackage() {
        return this.totalPackage;
    }

    public final int getCurrentPackage() {
        return this.currentPackage;
    }

    public final long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final long getStopTimestamp() {
        return this.stopTimestamp;
    }

    public final int getPassagePosition() {
        return this.passagePosition;
    }

    public final long getSumPassageCount() {
        return this.sumPassageCount;
    }

    public final int getOneDayChantingCount() {
        return this.oneDayChantingCount;
    }

    public final int component1() {
        return this.totalPackage;
    }

    public final int component2() {
        return this.currentPackage;
    }

    public final long component3() {
        return this.startTimestamp;
    }

    public final long component4() {
        return this.stopTimestamp;
    }

    public final int component5() {
        return this.passagePosition;
    }

    public final long component6() {
        return this.sumPassageCount;
    }

    public final int component7() {
        return this.oneDayChantingCount;
    }

    @NotNull
    public final ChantingData copy(int totalPackage, int currentPackage, long startTimestamp, long stopTimestamp, int passagePosition, long sumPassageCount, int oneDayChantingCount) {
        return new ChantingData(totalPackage, currentPackage, startTimestamp, stopTimestamp, passagePosition, sumPassageCount, oneDayChantingCount);
    }

    @NotNull
    public String toString() {
        int i = this.totalPackage;
        int i2 = this.currentPackage;
        long j = this.startTimestamp;
        long j2 = this.stopTimestamp;
        int i3 = this.passagePosition;
        long j3 = this.sumPassageCount;
        int i4 = this.oneDayChantingCount;
        return "ChantingData(totalPackage=" + i + ", currentPackage=" + i2 + ", startTimestamp=" + j + ", stopTimestamp=" + i + ", passagePosition=" + j2 + ", sumPassageCount=" + i + ", oneDayChantingCount=" + i3 + ")";
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.totalPackage) * 31) + Integer.hashCode(this.currentPackage)) * 31) + Long.hashCode(this.startTimestamp)) * 31) + Long.hashCode(this.stopTimestamp)) * 31) + Integer.hashCode(this.passagePosition)) * 31) + Long.hashCode(this.sumPassageCount)) * 31) + Integer.hashCode(this.oneDayChantingCount);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChantingData)) {
            return false;
        }
        ChantingData chantingData = (ChantingData) other;
        return this.totalPackage == chantingData.totalPackage && this.currentPackage == chantingData.currentPackage && this.startTimestamp == chantingData.startTimestamp && this.stopTimestamp == chantingData.stopTimestamp && this.passagePosition == chantingData.passagePosition && this.sumPassageCount == chantingData.sumPassageCount && this.oneDayChantingCount == chantingData.oneDayChantingCount;
    }
}
