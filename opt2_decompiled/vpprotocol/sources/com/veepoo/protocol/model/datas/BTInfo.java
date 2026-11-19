package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BTInfo.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000f\"\u0004\b\u0012\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/veepoo/protocol/model/datas/BTInfo;", "", "status", "Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;", "isBTOpen", "", "isAutoCon", "isAudioOpen", "isHavePairInfo", "<init>", "(Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;ZZZZ)V", "getStatus", "()Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;", "setStatus", "(Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;)V", "()Z", "setBTOpen", "(Z)V", "setAutoCon", "setAudioOpen", "setHavePairInfo", "toString", "", "BTStatus", "vpprotocol_release"})
public final class BTInfo {

    @NotNull
    private BTStatus status;
    private boolean isBTOpen;
    private boolean isAutoCon;
    private boolean isAudioOpen;
    private boolean isHavePairInfo;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BTInfo$BTStatus.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "setValue", "(I)V", "DISCONNECTED", "CONNECTED", "BROADCASTING", "Companion", "vpprotocol_release"})
    public enum BTStatus {
        DISCONNECTED(0),
        CONNECTED(1),
        BROADCASTING(2);

        private int value;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BTInfo$BTStatus$Companion.class */
        @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus$Companion;", "", "<init>", "()V", "getBTStatusByValue", "Lcom/veepoo/protocol/model/datas/BTInfo$BTStatus;", "value", "", "vpprotocol_release"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final BTStatus getBTStatusByValue(int value) {
                for (BTStatus bTStatus : BTStatus.values()) {
                    if (bTStatus.getValue() == value) {
                        return bTStatus;
                    }
                }
                return BTStatus.DISCONNECTED;
            }
        }

        BTStatus(int i) {
            this.value = i;
        }

        @NotNull
        public static EnumEntries<BTStatus> getEntries() {
            return $ENTRIES;
        }

        public final int getValue() {
            return this.value;
        }

        public final void setValue(int i) {
            this.value = i;
        }
    }

    public BTInfo(@NotNull BTStatus bTStatus, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(bTStatus, "status");
        this.status = bTStatus;
        this.isBTOpen = z;
        this.isAutoCon = z2;
        this.isAudioOpen = z3;
        this.isHavePairInfo = z4;
    }

    @NotNull
    public final BTStatus getStatus() {
        return this.status;
    }

    public final void setStatus(@NotNull BTStatus bTStatus) {
        Intrinsics.checkNotNullParameter(bTStatus, "<set-?>");
        this.status = bTStatus;
    }

    public final boolean isBTOpen() {
        return this.isBTOpen;
    }

    public final void setBTOpen(boolean z) {
        this.isBTOpen = z;
    }

    public final boolean isAutoCon() {
        return this.isAutoCon;
    }

    public final void setAutoCon(boolean z) {
        this.isAutoCon = z;
    }

    public final boolean isAudioOpen() {
        return this.isAudioOpen;
    }

    public final void setAudioOpen(boolean z) {
        this.isAudioOpen = z;
    }

    public final boolean isHavePairInfo() {
        return this.isHavePairInfo;
    }

    public final void setHavePairInfo(boolean z) {
        this.isHavePairInfo = z;
    }

    @NotNull
    public String toString() {
        return "BTInfo(BT状态=" + this.status + ",\n BT开关是否打开=" + this.isBTOpen + ", 是否自动回连=" + this.isAutoCon + ",\n多媒体是否打开=" + this.isAudioOpen + ", 设备是否有配对信息=" + this.isHavePairInfo + ")";
    }
}
