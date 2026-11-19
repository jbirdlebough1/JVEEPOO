package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportOpCode.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0081\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/veepoo/protocol/model/datas/SportOpCode;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "START", "PAUSE", "RESUME", "STOP", "Companion", "vpprotocol_release"})
public enum SportOpCode {
    START(1),
    PAUSE(2),
    RESUME(3),
    STOP(4);

    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportOpCode$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/datas/SportOpCode$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/datas/SportOpCode;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nSportControlDataInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SportControlDataInfo.kt\ncom/veepoo/protocol/model/datas/SportOpCode$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n1#2:116\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SportOpCode fromValue(int value) {
            SportOpCode sportOpCode;
            for (SportOpCode sportOpCode2 : SportOpCode.values()) {
                sportOpCode = sportOpCode2;
                if (sportOpCode2.getValue() == value) {
                    return sportOpCode;
                }
            }
            sportOpCode = null;
            return sportOpCode;
        }
    }

    SportOpCode(int i) {
        this.value = i;
    }

    @NotNull
    public static EnumEntries<SportOpCode> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
