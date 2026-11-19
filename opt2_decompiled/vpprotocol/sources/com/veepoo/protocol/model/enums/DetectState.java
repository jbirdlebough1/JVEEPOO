package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DetectState.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/veepoo/protocol/model/enums/DetectState;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()I", "getDes", "()Ljava/lang/String;", "PROGRESS", "SUCCESS", "FAILED", "BUSY", "LOW_POWER", "Companion", "vpprotocol_release"})
public enum DetectState {
    PROGRESS(0, "测量中"),
    SUCCESS(1, "测量成功-结果包"),
    FAILED(2, "测量失败-无结果"),
    BUSY(3, "设备正忙"),
    LOW_POWER(4, "低电");

    private final int code;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DetectState$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/DetectState$Companion;", "", "<init>", "()V", "code2State", "Lcom/veepoo/protocol/model/enums/DetectState;", "byte", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DetectState code2State(byte b) {
            DetectState detectState;
            DetectState[] detectStateArrValues = DetectState.values();
            int length = detectStateArrValues.length - 1;
            int i = length;
            if (length >= 0) {
                do {
                    int i2 = i;
                    i = i2 - 1;
                    DetectState detectState2 = detectStateArrValues[i2];
                    detectState = detectState2;
                    if (detectState2.getCode() == b) {
                    }
                } while (i >= 0);
                detectState = null;
            } else {
                detectState = null;
            }
            DetectState detectState3 = detectState;
            Intrinsics.checkNotNull(detectState3);
            return detectState3;
        }
    }

    DetectState(int i, String str) {
        this.code = i;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<DetectState> getEntries() {
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
