package com.veepoo.protocol.multi_lead.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDes", "()Ljava/lang/String;", "HANDHELD", "WIRED", "Companion", "vpprotocol_release"})
public enum EMultiEcgConnectMode {
    HANDHELD(0, "手持测量"),
    WIRED(1, "线连测量");

    private final int value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEMultiEcgConnectMode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EMultiEcgConnectMode.kt\ncom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,23:1\n295#2,2:24\n*S KotlinDebug\n*F\n+ 1 EMultiEcgConnectMode.kt\ncom/veepoo/protocol/multi_lead/enums/EMultiEcgConnectMode$Companion\n*L\n19#1:24,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EMultiEcgConnectMode fromValue(int value) {
            Object obj;
            for (Object obj2 : EMultiEcgConnectMode.getEntries()) {
                obj = obj2;
                if (((EMultiEcgConnectMode) obj2).getValue() == value) {
                    return (EMultiEcgConnectMode) obj;
                }
            }
            obj = null;
            return (EMultiEcgConnectMode) obj;
        }
    }

    EMultiEcgConnectMode(int i, String str) {
        this.value = i;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EMultiEcgConnectMode> getEntries() {
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
