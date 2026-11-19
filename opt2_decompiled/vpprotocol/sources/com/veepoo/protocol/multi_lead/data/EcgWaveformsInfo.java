package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgWaveformsInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0011j\b\u0012\u0004\u0012\u00020\u000b`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R*\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0011j\b\u0012\u0004\u0012\u00020\u000b`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016¨\u0006\u001c"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/EcgWaveformsInfo;", "", "<init>", "()V", "flag", "Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "getFlag", "()Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "setFlag", "(Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;)V", "waveCount", "", "getWaveCount", "()I", "setWaveCount", "(I)V", "waveTypeArr", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getWaveTypeArr", "()Ljava/util/ArrayList;", "setWaveTypeArr", "(Ljava/util/ArrayList;)V", "wavePositionArr", "getWavePositionArr", "setWavePositionArr", "toString", "", "vpprotocol_release"})
public final class EcgWaveformsInfo {
    private int waveCount;

    @NotNull
    private ELeadFlag flag = ELeadFlag.I;

    @NotNull
    private ArrayList<Integer> waveTypeArr = new ArrayList<>();

    @NotNull
    private ArrayList<Integer> wavePositionArr = new ArrayList<>();

    @NotNull
    public final ELeadFlag getFlag() {
        return this.flag;
    }

    public final void setFlag(@NotNull ELeadFlag eLeadFlag) {
        Intrinsics.checkNotNullParameter(eLeadFlag, "<set-?>");
        this.flag = eLeadFlag;
    }

    public final int getWaveCount() {
        return this.waveCount;
    }

    public final void setWaveCount(int i) {
        this.waveCount = i;
    }

    @NotNull
    public final ArrayList<Integer> getWaveTypeArr() {
        return this.waveTypeArr;
    }

    public final void setWaveTypeArr(@NotNull ArrayList<Integer> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.waveTypeArr = arrayList;
    }

    @NotNull
    public final ArrayList<Integer> getWavePositionArr() {
        return this.wavePositionArr;
    }

    public final void setWavePositionArr(@NotNull ArrayList<Integer> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.wavePositionArr = arrayList;
    }

    @NotNull
    public String toString() {
        return "EcgWaveformsInfo(waveCount=" + this.waveCount + ", waveTypeArr=" + this.waveTypeArr + ", wavePositionArr=" + this.wavePositionArr + ")";
    }
}
