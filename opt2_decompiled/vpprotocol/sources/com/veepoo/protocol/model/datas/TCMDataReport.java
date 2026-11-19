package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.TCMType;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TCMDataReport.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/veepoo/protocol/model/datas/TCMDataReport;", "", "timestamp", "", "metrics", "", "Lcom/veepoo/protocol/model/enums/TCMType;", "", "<init>", "(JLjava/util/Map;)V", "getTimestamp", "()J", "getMetrics", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class TCMDataReport {
    private final long timestamp;

    @NotNull
    private final Map<TCMType, Integer> metrics;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TCMDataReport copy$default(TCMDataReport tCMDataReport, long j, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            j = tCMDataReport.timestamp;
        }
        if ((i & 2) != 0) {
            map = tCMDataReport.metrics;
        }
        return tCMDataReport.copy(j, map);
    }

    public TCMDataReport() {
        this(0L, null, 3, null);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    @NotNull
    public final Map<TCMType, Integer> getMetrics() {
        return this.metrics;
    }

    public final long component1() {
        return this.timestamp;
    }

    @NotNull
    public final Map<TCMType, Integer> component2() {
        return this.metrics;
    }

    @NotNull
    public final TCMDataReport copy(long timestamp, @NotNull Map<TCMType, Integer> metrics) {
        Intrinsics.checkNotNullParameter(metrics, "metrics");
        return new TCMDataReport(timestamp, metrics);
    }

    @NotNull
    public String toString() {
        long j = this.timestamp;
        Map<TCMType, Integer> map = this.metrics;
        return "TCMDataReport(timestamp=" + j + ", metrics=" + j + ")";
    }

    public int hashCode() {
        return (Long.hashCode(this.timestamp) * 31) + this.metrics.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TCMDataReport)) {
            return false;
        }
        TCMDataReport tCMDataReport = (TCMDataReport) other;
        return this.timestamp == tCMDataReport.timestamp && Intrinsics.areEqual(this.metrics, tCMDataReport.metrics);
    }

    public TCMDataReport(long j, @NotNull Map<TCMType, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "metrics");
        this.timestamp = j;
        this.metrics = map;
    }

    public /* synthetic */ TCMDataReport(long j, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        j = (i & 1) != 0 ? 0L : j;
        if ((i & 2) != 0) {
            map = linkedHashMap;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
        }
        this(j, map);
    }
}
