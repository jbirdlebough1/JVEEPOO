package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.PPGTestMode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PPGRawData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u0015R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/veepoo/protocol/model/datas/PPGRawData;", "", "index", "", "ppgTestMode", "Lcom/veepoo/protocol/model/enums/PPGTestMode;", "<init>", "(ILcom/veepoo/protocol/model/enums/PPGTestMode;)V", "getIndex", "()I", "getPpgTestMode", "()Lcom/veepoo/protocol/model/enums/PPGTestMode;", "timestamp", "", "getTimestamp", "()J", "setTimestamp", "(J)V", "crc", "getCrc", "setCrc", "(I)V", "count", "getCount", "setCount", "ppgSecondDataList", "", "Lcom/veepoo/protocol/model/datas/PPGSecondData;", "getPpgSecondDataList", "()Ljava/util/List;", "toString", "", "vpprotocol_release"})
public final class PPGRawData {
    private final int index;

    @NotNull
    private final PPGTestMode ppgTestMode;
    private long timestamp;
    private int crc;
    private int count;

    @NotNull
    private final List<PPGSecondData> ppgSecondDataList;

    public PPGRawData(int i, @NotNull PPGTestMode pPGTestMode) {
        Intrinsics.checkNotNullParameter(pPGTestMode, "ppgTestMode");
        this.index = i;
        this.ppgTestMode = pPGTestMode;
        this.ppgSecondDataList = new ArrayList();
    }

    public final int getIndex() {
        return this.index;
    }

    @NotNull
    public final PPGTestMode getPpgTestMode() {
        return this.ppgTestMode;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(long j) {
        this.timestamp = j;
    }

    public final int getCrc() {
        return this.crc;
    }

    public final void setCrc(int i) {
        this.crc = i;
    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    @NotNull
    public final List<PPGSecondData> getPpgSecondDataList() {
        return this.ppgSecondDataList;
    }

    @NotNull
    public String toString() {
        return this.ppgTestMode == PPGTestMode.MODE3 ? new TimeData(this.timestamp).toFullDateTimeString() + " -> 共" + this.ppgSecondDataList.size() + "秒数据, ppg每秒数据=" + this.ppgSecondDataList : "第" + this.index + "组, " + new TimeData(this.timestamp).toFullDateTimeString() + " -> crc=" + this.crc + ", 共" + this.ppgSecondDataList.size() + "秒数据, ppg每秒数据=" + this.ppgSecondDataList;
    }
}
