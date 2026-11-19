package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGnssOptType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/enums/EGnssOptType;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()I", "getDes", "()Ljava/lang/String;", "GNSS_SPORT", "SAFETY_PROTECTION", "SOS", "UNKNOWN", "Companion", "vpprotocol_release"})
public enum EGnssOptType {
    GNSS_SPORT(1, "GNSS运动"),
    SAFETY_PROTECTION(2, "安全守护"),
    SOS(3, "SOS紧急求助"),
    UNKNOWN(-1, "未知");

    private final int code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EGnssOptType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EGnssOptType$Companion;", "", "<init>", "()V", "getTypeWithCMD", "Lcom/veepoo/protocol/model/enums/EGnssOptType;", "cmd", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EGnssOptType getTypeWithCMD(int cmd) {
            EGnssOptType eGnssOptType = EGnssOptType.GNSS_SPORT;
            EGnssOptType eGnssOptType2 = eGnssOptType;
            if (cmd != eGnssOptType.getCode()) {
                EGnssOptType eGnssOptType3 = EGnssOptType.SAFETY_PROTECTION;
                eGnssOptType2 = eGnssOptType3;
                if (cmd != eGnssOptType3.getCode()) {
                    EGnssOptType eGnssOptType4 = EGnssOptType.SOS;
                    eGnssOptType2 = eGnssOptType4;
                    if (cmd != eGnssOptType4.getCode()) {
                        eGnssOptType2 = EGnssOptType.UNKNOWN;
                    }
                }
            }
            return eGnssOptType2;
        }
    }

    EGnssOptType(int i, String str) {
        this.code = i;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EGnssOptType> getEntries() {
        return $ENTRIES;
    }

    public final int getCode() {
        return this.code;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
