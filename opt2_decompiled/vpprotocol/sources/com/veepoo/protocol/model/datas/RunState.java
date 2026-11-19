package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/RunState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/veepoo/protocol/model/datas/RunState;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "NOT_STARTED", "RUNNING", "PAUSED", "RESUMING", "FINISHED", "Companion", "vpprotocol_release"})
public enum RunState {
    NOT_STARTED(0),
    RUNNING(1),
    PAUSED(2),
    RESUMING(3),
    FINISHED(4);

    private final int value;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/RunState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/datas/RunState$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/datas/RunState;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nSportControlDataInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SportControlDataInfo.kt\ncom/veepoo/protocol/model/datas/RunState$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,115:1\n1#2:116\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final RunState fromValue(int value) {
            RunState runState;
            for (RunState runState2 : RunState.values()) {
                runState = runState2;
                if (runState2.getValue() == value) {
                    return runState;
                }
            }
            runState = null;
            return runState;
        }
    }

    RunState(int i) {
        this.value = i;
    }

    @NotNull
    public static EnumEntries<RunState> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
