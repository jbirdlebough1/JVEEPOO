package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EEcgPatchSpo2Switch.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/veepoo/protocol/model/enums/EEcgPatchSpo2Switch;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDes", "()Ljava/lang/String;", "NONE", "CLOSE", "OPEN", "Companion", "vpprotocol_release"})
public enum EEcgPatchSpo2Switch {
    NONE(0, "无此功能"),
    CLOSE(1, "关闭ECGBlood Oxygen (SpO2)测量"),
    OPEN(2, "开启ECGBlood Oxygen (SpO2)测量");

    private final int value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EEcgPatchSpo2Switch$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EEcgPatchSpo2Switch$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/enums/EEcgPatchSpo2Switch;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEEcgPatchSpo2Switch.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EEcgPatchSpo2Switch.kt\ncom/veepoo/protocol/model/enums/EEcgPatchSpo2Switch$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,28:1\n295#2,2:29\n*S KotlinDebug\n*F\n+ 1 EEcgPatchSpo2Switch.kt\ncom/veepoo/protocol/model/enums/EEcgPatchSpo2Switch$Companion\n*L\n24#1:29,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EEcgPatchSpo2Switch fromValue(int value) {
            Object obj;
            for (Object obj2 : EEcgPatchSpo2Switch.getEntries()) {
                obj = obj2;
                if (((EEcgPatchSpo2Switch) obj2).getValue() == value) {
                    return (EEcgPatchSpo2Switch) obj;
                }
            }
            obj = null;
            return (EEcgPatchSpo2Switch) obj;
        }
    }

    EEcgPatchSpo2Switch(int i, String str) {
        this.value = i;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EEcgPatchSpo2Switch> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
