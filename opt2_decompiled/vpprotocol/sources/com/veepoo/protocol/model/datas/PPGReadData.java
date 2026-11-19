package com.veepoo.protocol.model.datas;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PPGReadData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/datas/PPGReadData;", "", "<init>", "()V", "dataCount", "", "getDataCount", "()I", "setDataCount", "(I)V", "ppgRawDataList", "", "Lcom/veepoo/protocol/model/datas/PPGRawData;", "getPpgRawDataList", "()Ljava/util/List;", "toString", "", "vpprotocol_release"})
public final class PPGReadData {
    private int dataCount;

    @NotNull
    private final List<PPGRawData> ppgRawDataList = new ArrayList();

    public final int getDataCount() {
        return this.dataCount;
    }

    public final void setDataCount(int i) {
        this.dataCount = i;
    }

    @NotNull
    public final List<PPGRawData> getPpgRawDataList() {
        return this.ppgRawDataList;
    }

    @NotNull
    public String toString() {
        return "总数据：一共" + this.dataCount + "组数据.\n" + this.ppgRawDataList;
    }
}
