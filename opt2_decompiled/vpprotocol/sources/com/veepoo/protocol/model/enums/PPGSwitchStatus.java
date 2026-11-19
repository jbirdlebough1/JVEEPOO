package com.veepoo.protocol.model.enums;

import androidx.annotation.IntRange;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/PPGSwitchStatus.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/veepoo/protocol/model/enums/PPGSwitchStatus;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getValue", "()B", "getDes", "()Ljava/lang/String;", "ALL_OFF", "MODE1_ON", "MODE2_ON", "Companion", "vpprotocol_release"})
public enum PPGSwitchStatus {
    ALL_OFF((byte) 1, "全关"),
    MODE1_ON((byte) 2, "开启模式1：每15分钟采集10秒时长，绿光+加速度原始数据"),
    MODE2_ON((byte) 3, "开启模式2：每5分钟采集1分钟时长，绿光+加速度原始数据");

    private final byte value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/PPGSwitchStatus$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/PPGSwitchStatus$Companion;", "", "<init>", "()V", "value2PPGSwitchStatus", "Lcom/veepoo/protocol/model/enums/PPGSwitchStatus;", "value", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PPGSwitchStatus value2PPGSwitchStatus(@IntRange(from = 1, to = 2) int value) {
            PPGSwitchStatus pPGSwitchStatus;
            PPGSwitchStatus[] pPGSwitchStatusArrValues = PPGSwitchStatus.values();
            int length = pPGSwitchStatusArrValues.length - 1;
            int i = length;
            if (length >= 0) {
                do {
                    int i2 = i;
                    i = i2 - 1;
                    PPGSwitchStatus pPGSwitchStatus2 = pPGSwitchStatusArrValues[i2];
                    pPGSwitchStatus = pPGSwitchStatus2;
                    if (pPGSwitchStatus2.getValue() == value) {
                    }
                } while (i >= 0);
                pPGSwitchStatus = null;
            } else {
                pPGSwitchStatus = null;
            }
            PPGSwitchStatus pPGSwitchStatus3 = pPGSwitchStatus;
            Intrinsics.checkNotNull(pPGSwitchStatus3);
            return pPGSwitchStatus3;
        }
    }

    PPGSwitchStatus(byte b, String str) {
        this.value = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<PPGSwitchStatus> getEntries() {
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
