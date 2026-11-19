package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/MagneticTherapyType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/veepoo/protocol/model/enums/MagneticTherapyType;", "", "flag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getFlag", "()B", "getDes", "()Ljava/lang/String;", "PULSE_MAGNETIC_THERAPY", "CONVENTIONAL_MAGNETIC_THERAPY", "vpprotocol_release"})
public enum MagneticTherapyType {
    PULSE_MAGNETIC_THERAPY((byte) 0, "脉冲磁疗"),
    CONVENTIONAL_MAGNETIC_THERAPY((byte) 1, "常规磁疗");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final byte flag;

    @NotNull
    private final String des;

    MagneticTherapyType(byte b, String str) {
        this.flag = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<MagneticTherapyType> getEntries() {
        return $ENTRIES;
    }

    public final byte getFlag() {
        return this.flag;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
