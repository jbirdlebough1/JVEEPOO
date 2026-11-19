package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/HealthStatus.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/veepoo/protocol/model/enums/HealthStatus;", "", "tag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getTag", "()B", "INCREASE", "STABLE", "DECREASE", "vpprotocol_release"})
public enum HealthStatus {
    INCREASE((byte) 1, "增加/上升"),
    STABLE((byte) 2, "稳定"),
    DECREASE((byte) 3, "减少/下降");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final byte tag;

    HealthStatus(byte b, String str) {
        this.tag = b;
    }

    @NotNull
    public static EnumEntries<HealthStatus> getEntries() {
        return $ENTRIES;
    }

    public final byte getTag() {
        return this.tag;
    }
}
