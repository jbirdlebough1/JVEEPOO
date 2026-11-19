package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SecurityProtectionState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0015"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "NONE", "EFFECTIVE_LOCATION", "INVALID_LOCATION", "POOR_SIGNAL", "NO_PERMISSION", "UNKNOWN", "toEGNSSState", "Lcom/veepoo/protocol/model/enums/EGNSSState;", "Companion", "vpprotocol_release"})
public enum EQH15SecurityProtectionState {
    NONE((byte) 0, "无指令"),
    EFFECTIVE_LOCATION((byte) 1, "有效定位"),
    INVALID_LOCATION((byte) 2, "无效定位"),
    POOR_SIGNAL((byte) 3, "信号差"),
    NO_PERMISSION((byte) 4, "无权限"),
    UNKNOWN((byte) -1, "未知");

    private final byte code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SecurityProtectionState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState$Companion;", "", "<init>", "()V", "getStateWithCMD", "Lcom/veepoo/protocol/model/enums/EQH15SecurityProtectionState;", "cmd", "", "fromEGNSSState", "state", "Lcom/veepoo/protocol/model/enums/EGNSSState;", "vpprotocol_release"})
    public static final class Companion {

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SecurityProtectionState$Companion$WhenMappings.class */
        @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[EGNSSState.values().length];
                try {
                    iArr[EGNSSState.VALID_POSITION.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[EGNSSState.INVALID_POSITION.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[EGNSSState.POOR_SIGNAL.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[EGNSSState.NO_PERMISSION.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EQH15SecurityProtectionState getStateWithCMD(byte cmd) {
            EQH15SecurityProtectionState eQH15SecurityProtectionState = EQH15SecurityProtectionState.NONE;
            EQH15SecurityProtectionState eQH15SecurityProtectionState2 = eQH15SecurityProtectionState;
            if (cmd != eQH15SecurityProtectionState.getCode()) {
                EQH15SecurityProtectionState eQH15SecurityProtectionState3 = EQH15SecurityProtectionState.EFFECTIVE_LOCATION;
                eQH15SecurityProtectionState2 = eQH15SecurityProtectionState3;
                if (cmd != eQH15SecurityProtectionState3.getCode()) {
                    EQH15SecurityProtectionState eQH15SecurityProtectionState4 = EQH15SecurityProtectionState.INVALID_LOCATION;
                    eQH15SecurityProtectionState2 = eQH15SecurityProtectionState4;
                    if (cmd != eQH15SecurityProtectionState4.getCode()) {
                        EQH15SecurityProtectionState eQH15SecurityProtectionState5 = EQH15SecurityProtectionState.POOR_SIGNAL;
                        eQH15SecurityProtectionState2 = eQH15SecurityProtectionState5;
                        if (cmd != eQH15SecurityProtectionState5.getCode()) {
                            EQH15SecurityProtectionState eQH15SecurityProtectionState6 = EQH15SecurityProtectionState.NO_PERMISSION;
                            eQH15SecurityProtectionState2 = eQH15SecurityProtectionState6;
                            if (cmd != eQH15SecurityProtectionState6.getCode()) {
                                eQH15SecurityProtectionState2 = EQH15SecurityProtectionState.UNKNOWN;
                            }
                        }
                    }
                }
            }
            return eQH15SecurityProtectionState2;
        }

        @NotNull
        public final EQH15SecurityProtectionState fromEGNSSState(@NotNull EGNSSState state) {
            Intrinsics.checkNotNullParameter(state, "state");
            switch (WhenMappings.$EnumSwitchMapping$0[state.ordinal()]) {
                case 1:
                    return EQH15SecurityProtectionState.EFFECTIVE_LOCATION;
                case 2:
                    return EQH15SecurityProtectionState.INVALID_LOCATION;
                case 3:
                    return EQH15SecurityProtectionState.POOR_SIGNAL;
                case 4:
                    return EQH15SecurityProtectionState.NO_PERMISSION;
                default:
                    return EQH15SecurityProtectionState.UNKNOWN;
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15SecurityProtectionState$WhenMappings.class */
    @Metadata(mv = {2, 1, 0}, k = 3, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EQH15SecurityProtectionState.values().length];
            try {
                iArr[EQH15SecurityProtectionState.EFFECTIVE_LOCATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EQH15SecurityProtectionState.INVALID_LOCATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EQH15SecurityProtectionState.POOR_SIGNAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[EQH15SecurityProtectionState.NO_PERMISSION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    EQH15SecurityProtectionState(byte b, String str) {
        this.code = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EQH15SecurityProtectionState> getEntries() {
        return $ENTRIES;
    }

    public final byte getCode() {
        return this.code;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }

    @NotNull
    public final EGNSSState toEGNSSState() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return EGNSSState.VALID_POSITION;
            case 2:
                return EGNSSState.INVALID_POSITION;
            case 3:
                return EGNSSState.POOR_SIGNAL;
            case 4:
                return EGNSSState.NO_PERMISSION;
            default:
                return EGNSSState.UNKNOWN;
        }
    }
}
