package com.veepoo.protocol.model.enums;

import androidx.annotation.IntRange;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/PPGTestMode.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/veepoo/protocol/model/enums/PPGTestMode;", "", "value", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getValue", "()B", "getDes", "()Ljava/lang/String;", "MODE1", "MODE2", "MODE3", "Companion", "vpprotocol_release"})
public enum PPGTestMode {
    MODE1((byte) 1, "每15分钟采集10秒时长，绿光+加速度原始数据"),
    MODE2((byte) 2, "每5分钟采集1分钟时长，绿光+加速度原始数据"),
    MODE3((byte) 3, "数据采集实时上报，保存最后一次测量最近5分钟原始数据");

    private final byte value;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/PPGTestMode$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/PPGTestMode$Companion;", "", "<init>", "()V", "toTestMode", "Lcom/veepoo/protocol/model/enums/PPGTestMode;", "value", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PPGTestMode toTestMode(@IntRange(from = 1, to = 2) int value) {
            PPGTestMode pPGTestMode;
            PPGTestMode[] pPGTestModeArrValues = PPGTestMode.values();
            int length = pPGTestModeArrValues.length - 1;
            int i = length;
            if (length >= 0) {
                do {
                    int i2 = i;
                    i = i2 - 1;
                    PPGTestMode pPGTestMode2 = pPGTestModeArrValues[i2];
                    pPGTestMode = pPGTestMode2;
                    if (pPGTestMode2.getValue() == value) {
                    }
                } while (i >= 0);
                pPGTestMode = null;
            } else {
                pPGTestMode = null;
            }
            PPGTestMode pPGTestMode3 = pPGTestMode;
            Intrinsics.checkNotNull(pPGTestMode3);
            return pPGTestMode3;
        }
    }

    PPGTestMode(byte b, String str) {
        this.value = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<PPGTestMode> getEntries() {
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
