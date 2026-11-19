package com.veepoo.protocol.multi_lead.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDes", "()Ljava/lang/String;", "SINGLE_MEASURE_DURATION", "DO_NOT_DISTURB", "HEART_ABNORMAL_REMIND", "DISEASE_REMIND", "LEAD_OFF_REMIND", "Companion", "vpprotocol_release"})
public enum EEcgPatchSettingType {
    SINGLE_MEASURE_DURATION(11, "多导联单次测量时长"),
    DO_NOT_DISTURB(12, "多导联连续测量免打扰"),
    HEART_ABNORMAL_REMIND(13, "多导联连续测量Heart Rate异常提醒"),
    DISEASE_REMIND(14, "多导联连续测量疾病提醒"),
    LEAD_OFF_REMIND(15, "多导联导联脱落提醒开关");

    private final int value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEEcgPatchSettingType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EEcgPatchSettingType.kt\ncom/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,38:1\n295#2,2:39\n*S KotlinDebug\n*F\n+ 1 EEcgPatchSettingType.kt\ncom/veepoo/protocol/multi_lead/enums/EEcgPatchSettingType$Companion\n*L\n34#1:39,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EEcgPatchSettingType fromValue(int value) {
            Object obj;
            for (Object obj2 : EEcgPatchSettingType.getEntries()) {
                obj = obj2;
                if (((EEcgPatchSettingType) obj2).getValue() == value) {
                    return (EEcgPatchSettingType) obj;
                }
            }
            obj = null;
            return (EEcgPatchSettingType) obj;
        }
    }

    EEcgPatchSettingType(int i, String str) {
        this.value = i;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EEcgPatchSettingType> getEntries() {
        return vp_b;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
