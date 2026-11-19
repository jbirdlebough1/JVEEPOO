package com.veepoo.protocol.model.enums;

import androidx.annotation.IntRange;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EJH58MeasurementState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/veepoo/protocol/model/enums/EJH58MeasurementState;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getValue", "()B", "getDes", "()Ljava/lang/String;", "ACTIVE_MEASUREMENT_REAL_TIME", "ACTIVE_MEASUREMENT_BREAKPOINT", "CLOSE_ACTIVE_MEASUREMENT", "Companion", "vpprotocol_release"})
public enum EJH58MeasurementState {
    ACTIVE_MEASUREMENT_REAL_TIME((byte) 1, "主动测量-实时传输"),
    ACTIVE_MEASUREMENT_BREAKPOINT((byte) 2, "主动测量-断点传输"),
    CLOSE_ACTIVE_MEASUREMENT((byte) 3, "关闭主动测量");

    private final byte value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EJH58MeasurementState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EJH58MeasurementState$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/enums/EJH58MeasurementState;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEJH58MeasurementState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EJH58MeasurementState.kt\ncom/veepoo/protocol/model/enums/EJH58MeasurementState$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,21:1\n1#2:22\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EJH58MeasurementState fromValue(@IntRange(from = 1, to = 3) int value) {
            EJH58MeasurementState eJH58MeasurementState;
            EJH58MeasurementState[] eJH58MeasurementStateArrValues = EJH58MeasurementState.values();
            int i = 0;
            int length = eJH58MeasurementStateArrValues.length;
            while (true) {
                if (i >= length) {
                    eJH58MeasurementState = null;
                    break;
                }
                EJH58MeasurementState eJH58MeasurementState2 = eJH58MeasurementStateArrValues[i];
                eJH58MeasurementState = eJH58MeasurementState2;
                if (eJH58MeasurementState2.getValue() == value) {
                    break;
                }
                i++;
            }
            if (eJH58MeasurementState == null) {
                eJH58MeasurementState = EJH58MeasurementState.CLOSE_ACTIVE_MEASUREMENT;
            }
            return eJH58MeasurementState;
        }
    }

    EJH58MeasurementState(byte b, String str) {
        this.value = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EJH58MeasurementState> getEntries() {
        return $ENTRIES;
    }

    public final byte getValue() {
        return this.value;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
