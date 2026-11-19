package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EMultiEcgConnectMode;
import com.veepoo.protocol.multi_lead.enums.EMultiEcgTestMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/MultiEcgConfig.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/MultiEcgConfig;", "", "testMode", "Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;", "connectMode", "Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "duration", "", "<init>", "(Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;I)V", "getTestMode", "()Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;", "setTestMode", "(Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;)V", "getConnectMode", "()Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "setConnectMode", "(Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;)V", "getDuration", "()I", "setDuration", "(I)V", "vpprotocol_release"})
public final class MultiEcgConfig {

    @NotNull
    private EMultiEcgTestMode testMode;

    @NotNull
    private EMultiEcgConnectMode connectMode;
    private int duration;

    public MultiEcgConfig() {
        this(null, null, 0, 7, null);
    }

    @NotNull
    public final EMultiEcgTestMode getTestMode() {
        return this.testMode;
    }

    public final void setTestMode(@NotNull EMultiEcgTestMode eMultiEcgTestMode) {
        Intrinsics.checkNotNullParameter(eMultiEcgTestMode, "<set-?>");
        this.testMode = eMultiEcgTestMode;
    }

    @NotNull
    public final EMultiEcgConnectMode getConnectMode() {
        return this.connectMode;
    }

    public final void setConnectMode(@NotNull EMultiEcgConnectMode eMultiEcgConnectMode) {
        Intrinsics.checkNotNullParameter(eMultiEcgConnectMode, "<set-?>");
        this.connectMode = eMultiEcgConnectMode;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public MultiEcgConfig(@NotNull EMultiEcgTestMode eMultiEcgTestMode, @NotNull EMultiEcgConnectMode eMultiEcgConnectMode, int i) {
        Intrinsics.checkNotNullParameter(eMultiEcgTestMode, "testMode");
        Intrinsics.checkNotNullParameter(eMultiEcgConnectMode, "connectMode");
        this.testMode = eMultiEcgTestMode;
        this.connectMode = eMultiEcgConnectMode;
        this.duration = i;
    }

    public /* synthetic */ MultiEcgConfig(EMultiEcgTestMode eMultiEcgTestMode, EMultiEcgConnectMode eMultiEcgConnectMode, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? EMultiEcgTestMode.SINGLE : eMultiEcgTestMode, (i2 & 2) != 0 ? EMultiEcgConnectMode.HANDHELD : eMultiEcgConnectMode, (i2 & 4) != 0 ? 0 : i);
    }
}
