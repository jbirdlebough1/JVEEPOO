package com.veepoo.protocol.multi_lead.data;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/MultiEcgDetectInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/MultiEcgDetectInfo;", "", "<init>", "()V", "avgHeart", "", "getAvgHeart", "()I", "setAvgHeart", "(I)V", "avgQt", "getAvgQt", "setAvgQt", "avgHrv", "getAvgHrv", "setAvgHrv", "isLeadOff", "", "()Z", "setLeadOff", "(Z)V", "diseaseInfoList", "", "Lcom/veepoo/protocol/multi_lead/data/DiseaseInfo;", "getDiseaseInfoList", "()Ljava/util/List;", "setDiseaseInfoList", "(Ljava/util/List;)V", "toString", "", "vpprotocol_release"})
public final class MultiEcgDetectInfo {
    private int avgHeart;
    private int avgQt;
    private int avgHrv;
    private boolean isLeadOff;

    @NotNull
    private List<DiseaseInfo> diseaseInfoList = new ArrayList();

    public final int getAvgHeart() {
        return this.avgHeart;
    }

    public final void setAvgHeart(int i) {
        this.avgHeart = i;
    }

    public final int getAvgQt() {
        return this.avgQt;
    }

    public final void setAvgQt(int i) {
        this.avgQt = i;
    }

    public final int getAvgHrv() {
        return this.avgHrv;
    }

    public final void setAvgHrv(int i) {
        this.avgHrv = i;
    }

    public final boolean isLeadOff() {
        return this.isLeadOff;
    }

    public final void setLeadOff(boolean z) {
        this.isLeadOff = z;
    }

    @NotNull
    public final List<DiseaseInfo> getDiseaseInfoList() {
        return this.diseaseInfoList;
    }

    public final void setDiseaseInfoList(@NotNull List<DiseaseInfo> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.diseaseInfoList = list;
    }

    @NotNull
    public String toString() {
        return "MultiEcgDetectInfo(avgHeart=" + this.avgHeart + ", avgQt=" + this.avgQt + ", avgHrv=" + this.avgHrv + ", isLeadOff=" + this.isLeadOff + ", diseaseInfoList=" + this.diseaseInfoList + ")";
    }
}
