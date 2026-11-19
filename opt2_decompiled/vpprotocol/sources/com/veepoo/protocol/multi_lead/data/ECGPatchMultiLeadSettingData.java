package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.multi_lead.enums.EEcgPatchSwitch;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/ECGPatchMultiLeadSettingData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015¨\u0006\""}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/ECGPatchMultiLeadSettingData;", "", "ecgSwitch", "Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSwitch;", "startHour", "", "startMinute", "endHour", "endMinute", "minHeart", "maxHeart", "duration", "<init>", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSwitch;IIIIIII)V", "getEcgSwitch", "()Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSwitch;", "setEcgSwitch", "(Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSwitch;)V", "getStartHour", "()I", "setStartHour", "(I)V", "getStartMinute", "setStartMinute", "getEndHour", "setEndHour", "getEndMinute", "setEndMinute", "getMinHeart", "setMinHeart", "getMaxHeart", "setMaxHeart", "getDuration", "setDuration", "vpprotocol_release"})
public final class ECGPatchMultiLeadSettingData {

    @NotNull
    private EEcgPatchSwitch ecgSwitch;
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    private int minHeart;
    private int maxHeart;
    private int duration;

    public ECGPatchMultiLeadSettingData() {
        this(null, 0, 0, 0, 0, 0, 0, 0, 255, null);
    }

    @NotNull
    public final EEcgPatchSwitch getEcgSwitch() {
        return this.ecgSwitch;
    }

    public final void setEcgSwitch(@NotNull EEcgPatchSwitch eEcgPatchSwitch) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "<set-?>");
        this.ecgSwitch = eEcgPatchSwitch;
    }

    public final int getStartHour() {
        return this.startHour;
    }

    public final void setStartHour(int i) {
        this.startHour = i;
    }

    public final int getStartMinute() {
        return this.startMinute;
    }

    public final void setStartMinute(int i) {
        this.startMinute = i;
    }

    public final int getEndHour() {
        return this.endHour;
    }

    public final void setEndHour(int i) {
        this.endHour = i;
    }

    public final int getEndMinute() {
        return this.endMinute;
    }

    public final void setEndMinute(int i) {
        this.endMinute = i;
    }

    public final int getMinHeart() {
        return this.minHeart;
    }

    public final void setMinHeart(int i) {
        this.minHeart = i;
    }

    public final int getMaxHeart() {
        return this.maxHeart;
    }

    public final void setMaxHeart(int i) {
        this.maxHeart = i;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public ECGPatchMultiLeadSettingData(@NotNull EEcgPatchSwitch eEcgPatchSwitch, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        Intrinsics.checkNotNullParameter(eEcgPatchSwitch, "ecgSwitch");
        this.ecgSwitch = eEcgPatchSwitch;
        this.startHour = i;
        this.startMinute = i2;
        this.endHour = i3;
        this.endMinute = i4;
        this.minHeart = i5;
        this.maxHeart = i6;
        this.duration = i7;
    }

    public /* synthetic */ ECGPatchMultiLeadSettingData(EEcgPatchSwitch eEcgPatchSwitch, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this((i8 & 1) != 0 ? EEcgPatchSwitch.NONE : eEcgPatchSwitch, (i8 & 2) != 0 ? 0 : i, (i8 & 4) != 0 ? 0 : i2, (i8 & 8) != 0 ? 0 : i3, (i8 & 16) != 0 ? 0 : i4, (i8 & 32) != 0 ? 0 : i5, (i8 & 64) != 0 ? 0 : i6, (i8 & 128) != 0 ? 0 : i7);
    }
}
