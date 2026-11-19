package com.veepoo.protocol.multi_lead.enums;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/ELeadFlag.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "", "value", "", "description", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDescription", "()Ljava/lang/String;", "I", "II", "III", "AVR", "AVL", "AVF", "Companion", "vpprotocol_release"})
@SourceDebugExtension({"SMAP\nELeadFlag.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ELeadFlag.kt\ncom/veepoo/protocol/multi_lead/enums/ELeadFlag\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,24:1\n9037#2,2:25\n9297#2,4:27\n*S KotlinDebug\n*F\n+ 1 ELeadFlag.kt\ncom/veepoo/protocol/multi_lead/enums/ELeadFlag\n*L\n17#1:25,2\n17#1:27,4\n*E\n"})
public enum ELeadFlag {
    I(1, "I导联"),
    II(2, "II导联"),
    III(3, "III导联"),
    AVR(4, "AVR导联"),
    AVL(5, "AVL导联"),
    AVF(6, "AVF导联");

    private static final Map vp_a;
    private final int value;

    @NotNull
    private final String description;
    private static final /* synthetic */ EnumEntries vp_c = EnumEntriesKt.enumEntries(vp_a());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/ELeadFlag$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag$Companion;", "", "<init>", "()V", "map", "", "", "Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "fromValue", "value", "fromDescription", "description", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nELeadFlag.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ELeadFlag.kt\ncom/veepoo/protocol/multi_lead/enums/ELeadFlag$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,24:1\n1#2:25\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final ELeadFlag fromValue(int value) {
            return (ELeadFlag) ELeadFlag.vp_a.get(Integer.valueOf(value));
        }

        @Nullable
        public final ELeadFlag fromDescription(@NotNull String description) {
            ELeadFlag eLeadFlag;
            Intrinsics.checkNotNullParameter(description, "description");
            for (ELeadFlag eLeadFlag2 : ELeadFlag.values()) {
                eLeadFlag = eLeadFlag2;
                if (Intrinsics.areEqual(eLeadFlag2.getDescription(), description)) {
                    return eLeadFlag;
                }
            }
            eLeadFlag = null;
            return eLeadFlag;
        }
    }

    ELeadFlag(int i, String str) {
        this.value = i;
        this.description = str;
    }

    @NotNull
    public static EnumEntries<ELeadFlag> getEntries() {
        return vp_c;
    }

    static {
        ELeadFlag[] eLeadFlagArrValues = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(eLeadFlagArrValues.length), 16));
        for (ELeadFlag eLeadFlag : eLeadFlagArrValues) {
            linkedHashMap.put(Integer.valueOf(eLeadFlag.value), eLeadFlag);
        }
        vp_a = linkedHashMap;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }
}
