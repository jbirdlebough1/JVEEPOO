package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EHealthAlarmType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/veepoo/protocol/model/enums/EHealthAlarmType;", "", "tag", "", "<init>", "(Ljava/lang/String;IB)V", "getTag", "()B", "HEART_RATE_ALARM", "BLOOD_PRESSURE_ALARM", "HIGH_TEMPERATURE_ALARM", "LOW_BLOOD_OXYGEN_ALARM", "UNKNOWN", "Companion", "vpprotocol_release"})
public enum EHealthAlarmType {
    HEART_RATE_ALARM((byte) 0),
    BLOOD_PRESSURE_ALARM((byte) 1),
    HIGH_TEMPERATURE_ALARM((byte) 2),
    LOW_BLOOD_OXYGEN_ALARM((byte) 3),
    UNKNOWN((byte) -1);

    private final byte tag;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EHealthAlarmType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EHealthAlarmType$Companion;", "", "<init>", "()V", "getEHealthAlarmTypeWithCMD", "Lcom/veepoo/protocol/model/enums/EHealthAlarmType;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EHealthAlarmType getEHealthAlarmTypeWithCMD(byte cmd) {
            EHealthAlarmType eHealthAlarmType = EHealthAlarmType.HEART_RATE_ALARM;
            EHealthAlarmType eHealthAlarmType2 = eHealthAlarmType;
            if (cmd != eHealthAlarmType.getTag()) {
                EHealthAlarmType eHealthAlarmType3 = EHealthAlarmType.BLOOD_PRESSURE_ALARM;
                eHealthAlarmType2 = eHealthAlarmType3;
                if (cmd != eHealthAlarmType3.getTag()) {
                    EHealthAlarmType eHealthAlarmType4 = EHealthAlarmType.HIGH_TEMPERATURE_ALARM;
                    eHealthAlarmType2 = eHealthAlarmType4;
                    if (cmd != eHealthAlarmType4.getTag()) {
                        EHealthAlarmType eHealthAlarmType5 = EHealthAlarmType.LOW_BLOOD_OXYGEN_ALARM;
                        eHealthAlarmType2 = eHealthAlarmType5;
                        if (cmd != eHealthAlarmType5.getTag()) {
                            eHealthAlarmType2 = EHealthAlarmType.UNKNOWN;
                        }
                    }
                }
            }
            return eHealthAlarmType2;
        }
    }

    EHealthAlarmType(byte b) {
        this.tag = b;
    }

    @NotNull
    public static EnumEntries<EHealthAlarmType> getEntries() {
        return $ENTRIES;
    }

    public final byte getTag() {
        return this.tag;
    }
}
