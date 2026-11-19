package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EEcgPatchAutoDetectAck;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchLeadMode;
import com.veepoo.protocol.multi_lead.enums.EEcgPatchMeasureStatus;
import com.veepoo.protocol.multi_lead.enums.EMultiEcgConnectMode;
import com.veepoo.protocol.multi_lead.enums.EMultiEcgTestMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/ECGPatchAutoDetectData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/ECGPatchAutoDetectData;", "", "ack", "Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchAutoDetectAck;", "leadDetectStatus", "Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchMeasureStatus;", "leadMode", "Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchLeadMode;", "testMode", "Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;", "connectMode", "Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "duration", "", "<init>", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchAutoDetectAck;Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchMeasureStatus;Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchLeadMode;Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;I)V", "getAck", "()Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchAutoDetectAck;", "setAck", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchAutoDetectAck;)V", "getLeadDetectStatus", "()Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchMeasureStatus;", "setLeadDetectStatus", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchMeasureStatus;)V", "getLeadMode", "()Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchLeadMode;", "setLeadMode", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchLeadMode;)V", "getTestMode", "()Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;", "setTestMode", "(Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgTestMode;)V", "getConnectMode", "()Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "setConnectMode", "(Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;)V", "getDuration", "()I", "setDuration", "(I)V", "vpprotocol_release"})
public final class ECGPatchAutoDetectData {

    @NotNull
    private EEcgPatchAutoDetectAck ack;

    @NotNull
    private EEcgPatchMeasureStatus leadDetectStatus;

    @Nullable
    private EEcgPatchLeadMode leadMode;

    @Nullable
    private EMultiEcgTestMode testMode;

    @Nullable
    private EMultiEcgConnectMode connectMode;
    private int duration;

    public ECGPatchAutoDetectData() {
        this(null, null, null, null, null, 0, 63, null);
    }

    @NotNull
    public final EEcgPatchAutoDetectAck getAck() {
        return this.ack;
    }

    public final void setAck(@NotNull EEcgPatchAutoDetectAck eEcgPatchAutoDetectAck) {
        Intrinsics.checkNotNullParameter(eEcgPatchAutoDetectAck, "<set-?>");
        this.ack = eEcgPatchAutoDetectAck;
    }

    @NotNull
    public final EEcgPatchMeasureStatus getLeadDetectStatus() {
        return this.leadDetectStatus;
    }

    public final void setLeadDetectStatus(@NotNull EEcgPatchMeasureStatus eEcgPatchMeasureStatus) {
        Intrinsics.checkNotNullParameter(eEcgPatchMeasureStatus, "<set-?>");
        this.leadDetectStatus = eEcgPatchMeasureStatus;
    }

    @Nullable
    public final EEcgPatchLeadMode getLeadMode() {
        return this.leadMode;
    }

    public final void setLeadMode(@Nullable EEcgPatchLeadMode eEcgPatchLeadMode) {
        this.leadMode = eEcgPatchLeadMode;
    }

    @Nullable
    public final EMultiEcgTestMode getTestMode() {
        return this.testMode;
    }

    public final void setTestMode(@Nullable EMultiEcgTestMode eMultiEcgTestMode) {
        this.testMode = eMultiEcgTestMode;
    }

    @Nullable
    public final EMultiEcgConnectMode getConnectMode() {
        return this.connectMode;
    }

    public final void setConnectMode(@Nullable EMultiEcgConnectMode eMultiEcgConnectMode) {
        this.connectMode = eMultiEcgConnectMode;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public ECGPatchAutoDetectData(@NotNull EEcgPatchAutoDetectAck eEcgPatchAutoDetectAck, @NotNull EEcgPatchMeasureStatus eEcgPatchMeasureStatus, @Nullable EEcgPatchLeadMode eEcgPatchLeadMode, @Nullable EMultiEcgTestMode eMultiEcgTestMode, @Nullable EMultiEcgConnectMode eMultiEcgConnectMode, int i) {
        Intrinsics.checkNotNullParameter(eEcgPatchAutoDetectAck, "ack");
        Intrinsics.checkNotNullParameter(eEcgPatchMeasureStatus, "leadDetectStatus");
        this.ack = eEcgPatchAutoDetectAck;
        this.leadDetectStatus = eEcgPatchMeasureStatus;
        this.leadMode = eEcgPatchLeadMode;
        this.testMode = eMultiEcgTestMode;
        this.connectMode = eMultiEcgConnectMode;
        this.duration = i;
    }

    public /* synthetic */ ECGPatchAutoDetectData(EEcgPatchAutoDetectAck eEcgPatchAutoDetectAck, EEcgPatchMeasureStatus eEcgPatchMeasureStatus, EEcgPatchLeadMode eEcgPatchLeadMode, EMultiEcgTestMode eMultiEcgTestMode, EMultiEcgConnectMode eMultiEcgConnectMode, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? EEcgPatchAutoDetectAck.UNSUPPORT : eEcgPatchAutoDetectAck, (i2 & 2) != 0 ? EEcgPatchMeasureStatus.NOT_DETECTED : eEcgPatchMeasureStatus, (i2 & 4) != 0 ? null : eEcgPatchLeadMode, (i2 & 8) != 0 ? null : eMultiEcgTestMode, (i2 & 16) != 0 ? null : eMultiEcgConnectMode, (i2 & 32) != 0 ? 0 : i);
    }
}
