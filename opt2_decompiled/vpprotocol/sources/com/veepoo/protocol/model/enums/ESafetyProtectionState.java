package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESafetyProtectionState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/enums/ESafetyProtectionState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "SUCCESS", "WATCH_LOW_POWER", "LOCATION_MODULE_ERROR", "UNKNOWN", "Companion", "vpprotocol_release"})
public enum ESafetyProtectionState {
    SUCCESS((byte) 1, "成功"),
    WATCH_LOW_POWER((byte) 2, "手表电量过低"),
    LOCATION_MODULE_ERROR((byte) 3, "定位模块异常"),
    UNKNOWN((byte) -1, "未知状态");

    private final byte code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESafetyProtectionState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/ESafetyProtectionState$Companion;", "", "<init>", "()V", "getStateWithCMD", "Lcom/veepoo/protocol/model/enums/ESafetyProtectionState;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ESafetyProtectionState getStateWithCMD(byte cmd) {
            ESafetyProtectionState eSafetyProtectionState = ESafetyProtectionState.SUCCESS;
            ESafetyProtectionState eSafetyProtectionState2 = eSafetyProtectionState;
            if (cmd != eSafetyProtectionState.getCode()) {
                ESafetyProtectionState eSafetyProtectionState3 = ESafetyProtectionState.WATCH_LOW_POWER;
                eSafetyProtectionState2 = eSafetyProtectionState3;
                if (cmd != eSafetyProtectionState3.getCode()) {
                    ESafetyProtectionState eSafetyProtectionState4 = ESafetyProtectionState.LOCATION_MODULE_ERROR;
                    eSafetyProtectionState2 = eSafetyProtectionState4;
                    if (cmd != eSafetyProtectionState4.getCode()) {
                        eSafetyProtectionState2 = ESafetyProtectionState.UNKNOWN;
                    }
                }
            }
            return eSafetyProtectionState2;
        }
    }

    ESafetyProtectionState(byte b, String str) {
        this.code = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<ESafetyProtectionState> getEntries() {
        return $ENTRIES;
    }

    public final byte getCode() {
        return this.code;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
