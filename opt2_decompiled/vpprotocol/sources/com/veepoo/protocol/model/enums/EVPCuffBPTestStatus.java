package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EVPCuffBPTestStatus.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\b\u0086\u0081\u0002\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0016B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0017"}, d2 = {"Lcom/veepoo/protocol/model/enums/EVPCuffBPTestStatus;", "", "rawValue", "", "<init>", "(Ljava/lang/String;II)V", "getRawValue", "()I", "EMPTY", "SUCCESS", "POSTURE_ERROR", "LOOSE", "NOT_WEAR", "TIME_OUT", "HEART_ERROR", "SLOW_DEFLATION", "TOO_MUCH_PRESSURE", "POOR_SIGNAL_QUALITY", "NOT_REMAINING_STATIONARY", "CHARGING", "LOW_POWER", "DEVICE_BUSY", "Companion", "vpprotocol_release"})
public enum EVPCuffBPTestStatus {
    EMPTY(0),
    SUCCESS(1),
    POSTURE_ERROR(2),
    LOOSE(3),
    NOT_WEAR(4),
    TIME_OUT(5),
    HEART_ERROR(6),
    SLOW_DEFLATION(7),
    TOO_MUCH_PRESSURE(8),
    POOR_SIGNAL_QUALITY(9),
    NOT_REMAINING_STATIONARY(10),
    CHARGING(253),
    LOW_POWER(254),
    DEVICE_BUSY(255);

    private final int rawValue;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EVPCuffBPTestStatus$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EVPCuffBPTestStatus$Companion;", "", "<init>", "()V", "fromRawValue", "Lcom/veepoo/protocol/model/enums/EVPCuffBPTestStatus;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEVPCuffBPTestStatus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EVPCuffBPTestStatus.kt\ncom/veepoo/protocol/model/enums/EVPCuffBPTestStatus$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,31:1\n1400#2,2:32\n*S KotlinDebug\n*F\n+ 1 EVPCuffBPTestStatus.kt\ncom/veepoo/protocol/model/enums/EVPCuffBPTestStatus$Companion\n*L\n27#1:32,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EVPCuffBPTestStatus fromRawValue(int value) {
            EVPCuffBPTestStatus eVPCuffBPTestStatus;
            for (EVPCuffBPTestStatus eVPCuffBPTestStatus2 : EVPCuffBPTestStatus.values()) {
                eVPCuffBPTestStatus = eVPCuffBPTestStatus2;
                if (eVPCuffBPTestStatus2.getRawValue() == value) {
                    return eVPCuffBPTestStatus;
                }
            }
            eVPCuffBPTestStatus = null;
            return eVPCuffBPTestStatus;
        }
    }

    EVPCuffBPTestStatus(int i) {
        this.rawValue = i;
    }

    @NotNull
    public static EnumEntries<EVPCuffBPTestStatus> getEntries() {
        return $ENTRIES;
    }

    public final int getRawValue() {
        return this.rawValue;
    }
}
