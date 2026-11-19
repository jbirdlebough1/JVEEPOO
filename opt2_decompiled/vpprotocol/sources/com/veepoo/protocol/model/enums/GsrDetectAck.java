package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/GsrDetectAck.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, d2 = {"Lcom/veepoo/protocol/model/enums/GsrDetectAck;", "", "value", "", "description", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDescription", "()Ljava/lang/String;", "AVAILABLE", "BUSY_MEASURING_PRESSURE", "LOW_BATTERY", "BUSY_MEASURING_OTHER", "WEARING_CHECK_FAILED", "MEASURE_FAILED", "Companion", "vpprotocol_release"})
public enum GsrDetectAck {
    AVAILABLE(0, "可用，走正常测量流程"),
    BUSY_MEASURING_PRESSURE(1, "设备正忙：测量压力"),
    LOW_BATTERY(2, "设备处于低电状态"),
    BUSY_MEASURING_OTHER(3, "设备正忙：测量其它数据"),
    WEARING_CHECK_FAILED(4, "佩戴检测未通过"),
    MEASURE_FAILED(5, "测量失败");

    private final int value;

    @NotNull
    private final String description;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/GsrDetectAck$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/GsrDetectAck$Companion;", "", "<init>", "()V", "fromValue", "Lcom/veepoo/protocol/model/enums/GsrDetectAck;", "value", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nGsrDetectAck.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GsrDetectAck.kt\ncom/veepoo/protocol/model/enums/GsrDetectAck$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,40:1\n1#2:41\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final GsrDetectAck fromValue(int value) {
            GsrDetectAck gsrDetectAck;
            GsrDetectAck[] gsrDetectAckArrValues = GsrDetectAck.values();
            int i = 0;
            int length = gsrDetectAckArrValues.length;
            while (true) {
                if (i >= length) {
                    gsrDetectAck = null;
                    break;
                }
                GsrDetectAck gsrDetectAck2 = gsrDetectAckArrValues[i];
                gsrDetectAck = gsrDetectAck2;
                if (gsrDetectAck2.getValue() == value) {
                    break;
                }
                i++;
            }
            if (gsrDetectAck == null) {
                gsrDetectAck = GsrDetectAck.MEASURE_FAILED;
            }
            return gsrDetectAck;
        }
    }

    GsrDetectAck(int i, String str) {
        this.value = i;
        this.description = str;
    }

    @NotNull
    public static EnumEntries<GsrDetectAck> getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }
}
