package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EAutoMeasureType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/veepoo/protocol/model/enums/EAutoMeasureType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "PULSE_RATE", "BLOOD_PRESSURE", "BLOOD_GLUCOSE", "STRESS", "BLOOD_OXYGEN", "BODY_TEMPERATURE", "LORENZ", "HRV", "BLOOD_COMPOSITION", "FALL_DETECTION", "Companion", "vpprotocol_release"})
public enum EAutoMeasureType {
    PULSE_RATE(0),
    BLOOD_PRESSURE(1),
    BLOOD_GLUCOSE(2),
    STRESS(3),
    BLOOD_OXYGEN(4),
    BODY_TEMPERATURE(5),
    LORENZ(6),
    HRV(7),
    BLOOD_COMPOSITION(8),
    FALL_DETECTION(9);

    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EAutoMeasureType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EAutoMeasureType$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/enums/EAutoMeasureType;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEAutoMeasureType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EAutoMeasureType.kt\ncom/veepoo/protocol/model/enums/EAutoMeasureType$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,46:1\n1#2:47\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EAutoMeasureType fromValue(int value) {
            EAutoMeasureType eAutoMeasureType;
            for (EAutoMeasureType eAutoMeasureType2 : EAutoMeasureType.values()) {
                eAutoMeasureType = eAutoMeasureType2;
                if (eAutoMeasureType2.getValue() == value) {
                    return eAutoMeasureType;
                }
            }
            eAutoMeasureType = null;
            return eAutoMeasureType;
        }
    }

    EAutoMeasureType(int i) {
        this.value = i;
    }

    @NotNull
    public static EnumEntries<EAutoMeasureType> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
