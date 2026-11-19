package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "SUCCESS", "LOW_BATTERY", "LOCATION_MALFUNCTION", "UNKNOWN", "Companion", "vpprotocol_release"})
public enum EQH15SetProtectionFuncAckState {
    SUCCESS((byte) 1, "成功"),
    LOW_BATTERY((byte) 2, "手表电量过低无法开启"),
    LOCATION_MALFUNCTION((byte) 3, "定位模块异常"),
    UNKNOWN((byte) -1, "未知状态");

    private final byte code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState$Companion;", "", "<init>", "()V", "getStateWithCMD", "Lcom/veepoo/protocol/model/enums/EQH15SetProtectionFuncAckState;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EQH15SetProtectionFuncAckState getStateWithCMD(byte cmd) {
            EQH15SetProtectionFuncAckState eQH15SetProtectionFuncAckState = EQH15SetProtectionFuncAckState.SUCCESS;
            EQH15SetProtectionFuncAckState eQH15SetProtectionFuncAckState2 = eQH15SetProtectionFuncAckState;
            if (cmd != eQH15SetProtectionFuncAckState.getCode()) {
                EQH15SetProtectionFuncAckState eQH15SetProtectionFuncAckState3 = EQH15SetProtectionFuncAckState.LOW_BATTERY;
                eQH15SetProtectionFuncAckState2 = eQH15SetProtectionFuncAckState3;
                if (cmd != eQH15SetProtectionFuncAckState3.getCode()) {
                    EQH15SetProtectionFuncAckState eQH15SetProtectionFuncAckState4 = EQH15SetProtectionFuncAckState.LOCATION_MALFUNCTION;
                    eQH15SetProtectionFuncAckState2 = eQH15SetProtectionFuncAckState4;
                    if (cmd != eQH15SetProtectionFuncAckState4.getCode()) {
                        eQH15SetProtectionFuncAckState2 = EQH15SetProtectionFuncAckState.UNKNOWN;
                    }
                }
            }
            return eQH15SetProtectionFuncAckState2;
        }
    }

    EQH15SetProtectionFuncAckState(byte b, String str) {
        this.code = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EQH15SetProtectionFuncAckState> getEntries() {
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
