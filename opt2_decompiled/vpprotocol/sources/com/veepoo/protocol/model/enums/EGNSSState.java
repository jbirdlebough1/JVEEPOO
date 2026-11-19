package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGNSSState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/veepoo/protocol/model/enums/EGNSSState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "VALID_POSITION", "INVALID_POSITION", "POOR_SIGNAL", "NO_PERMISSION", "UNKNOWN", "Companion", "vpprotocol_release"})
public enum EGNSSState {
    VALID_POSITION((byte) 1, "有效定位（高精度，如3D定位）"),
    INVALID_POSITION((byte) 2, "无效定位（搜索中/未定位）"),
    POOR_SIGNAL((byte) 3, "信号差/不可靠（基站定位或多径干扰）"),
    NO_PERMISSION((byte) 4, "无权限"),
    UNKNOWN((byte) -1, "未知");

    private final byte code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGNSSState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EGNSSState$Companion;", "", "<init>", "()V", "getStateWithCMD", "Lcom/veepoo/protocol/model/enums/EGNSSState;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EGNSSState getStateWithCMD(byte cmd) {
            EGNSSState eGNSSState = EGNSSState.VALID_POSITION;
            EGNSSState eGNSSState2 = eGNSSState;
            if (cmd != eGNSSState.getCode()) {
                EGNSSState eGNSSState3 = EGNSSState.INVALID_POSITION;
                eGNSSState2 = eGNSSState3;
                if (cmd != eGNSSState3.getCode()) {
                    EGNSSState eGNSSState4 = EGNSSState.POOR_SIGNAL;
                    eGNSSState2 = eGNSSState4;
                    if (cmd != eGNSSState4.getCode()) {
                        EGNSSState eGNSSState5 = EGNSSState.NO_PERMISSION;
                        eGNSSState2 = eGNSSState5;
                        if (cmd != eGNSSState5.getCode()) {
                            eGNSSState2 = EGNSSState.UNKNOWN;
                        }
                    }
                }
            }
            return eGNSSState2;
        }
    }

    EGNSSState(byte b, String str) {
        this.code = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EGNSSState> getEntries() {
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
