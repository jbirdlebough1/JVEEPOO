package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/MagneticTherapyAckState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/veepoo/protocol/model/enums/MagneticTherapyAckState;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getValue", "()B", "getDes", "()Ljava/lang/String;", "OPT_SUCCESS", "OPT_FAILED", "DEVICE_BUSY", "IN_CHARGING", "WEARING_FAILED", "LOW_POWER", "UNKNOWN_DEFAULT", "Companion", "vpprotocol_release"})
public enum MagneticTherapyAckState {
    OPT_SUCCESS((byte) 1, "成功"),
    OPT_FAILED((byte) 2, "未知失败"),
    DEVICE_BUSY((byte) 3, "设备正忙"),
    IN_CHARGING((byte) 4, "充电中"),
    WEARING_FAILED((byte) 5, "佩戴检测未通过"),
    LOW_POWER((byte) 6, "设备端低电"),
    UNKNOWN_DEFAULT((byte) 0, "未知状态-默认");

    private final byte value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/MagneticTherapyAckState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/MagneticTherapyAckState$Companion;", "", "<init>", "()V", "getAckTypeWithCMD", "Lcom/veepoo/protocol/model/enums/MagneticTherapyAckState;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MagneticTherapyAckState getAckTypeWithCMD(byte cmd) {
            MagneticTherapyAckState magneticTherapyAckState;
            MagneticTherapyAckState[] magneticTherapyAckStateArrValues = MagneticTherapyAckState.values();
            int length = magneticTherapyAckStateArrValues.length - 1;
            int i = length;
            if (length < 0) {
                magneticTherapyAckState = null;
                break;
            }
            while (true) {
                int i2 = i;
                i = i2 - 1;
                MagneticTherapyAckState magneticTherapyAckState2 = magneticTherapyAckStateArrValues[i2];
                magneticTherapyAckState = magneticTherapyAckState2;
                if (magneticTherapyAckState2.getValue() == cmd) {
                    break;
                }
                if (i < 0) {
                    magneticTherapyAckState = null;
                    break;
                }
            }
            if (magneticTherapyAckState == null) {
                magneticTherapyAckState = MagneticTherapyAckState.UNKNOWN_DEFAULT;
            }
            return magneticTherapyAckState;
        }
    }

    MagneticTherapyAckState(byte b, String str) {
        this.value = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<MagneticTherapyAckState> getEntries() {
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
