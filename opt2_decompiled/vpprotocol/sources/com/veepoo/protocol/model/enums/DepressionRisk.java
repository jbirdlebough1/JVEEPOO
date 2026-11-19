package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DepressionRisk.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/veepoo/protocol/model/enums/DepressionRisk;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "LOW", "MEDIUM", "HIGH", "Companion", "vpprotocol_release"})
public enum DepressionRisk {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DepressionRisk$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/DepressionRisk$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/enums/DepressionRisk;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nDepressionRisk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DepressionRisk.kt\ncom/veepoo/protocol/model/enums/DepressionRisk$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,21:1\n1#2:22\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DepressionRisk fromValue(int value) {
            DepressionRisk depressionRisk;
            DepressionRisk[] depressionRiskArrValues = DepressionRisk.values();
            int i = 0;
            int length = depressionRiskArrValues.length;
            while (true) {
                if (i >= length) {
                    depressionRisk = null;
                    break;
                }
                DepressionRisk depressionRisk2 = depressionRiskArrValues[i];
                depressionRisk = depressionRisk2;
                if (depressionRisk2.getValue() == value) {
                    break;
                }
                i++;
            }
            if (depressionRisk == null) {
                depressionRisk = DepressionRisk.LOW;
            }
            return depressionRisk;
        }
    }

    DepressionRisk(int i) {
        this.value = i;
    }

    @NotNull
    public static EnumEntries<DepressionRisk> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
