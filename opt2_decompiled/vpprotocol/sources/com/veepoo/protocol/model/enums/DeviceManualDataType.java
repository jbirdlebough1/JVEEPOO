package com.veepoo.protocol.model.enums;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DeviceManualDataType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\b\u0086\u0081\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0018B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0019"}, d2 = {"Lcom/veepoo/protocol/model/enums/DeviceManualDataType;", "", "bitPosition", "", "<init>", "(Ljava/lang/String;II)V", "getBitPosition", "()I", "BLOOD_PRESSURE", "HEART_RATE", "BLOOD_GLUCOSE", "STRESS", "BLOOD_OXYGEN", "BODY_TEMPERATURE", "MET", "HRV", "BLOOD_COMPOSITION", "MINI_CHECKUP", "EMOTION", "FATIGUE", "SKIN_CONDUCTANCE", "ALL", "bitMask", "getBitMask", "Companion", "vpprotocol_release"})
public enum DeviceManualDataType {
    BLOOD_PRESSURE(0),
    HEART_RATE(1),
    BLOOD_GLUCOSE(2),
    STRESS(3),
    BLOOD_OXYGEN(4),
    BODY_TEMPERATURE(5),
    MET(6),
    HRV(7),
    BLOOD_COMPOSITION(8),
    MINI_CHECKUP(9),
    EMOTION(10),
    FATIGUE(11),
    SKIN_CONDUCTANCE(12),
    ALL(33);

    private final int bitPosition;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/DeviceManualDataType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"Lcom/veepoo/protocol/model/enums/DeviceManualDataType$Companion;", "", "<init>", "()V", "toByteArray", "", "measurementTypes", "", "Lcom/veepoo/protocol/model/enums/DeviceManualDataType;", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nDeviceManualDataType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeviceManualDataType.kt\ncom/veepoo/protocol/model/enums/DeviceManualDataType$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,63:1\n1803#2,3:64\n*S KotlinDebug\n*F\n+ 1 DeviceManualDataType.kt\ncom/veepoo/protocol/model/enums/DeviceManualDataType$Companion\n*L\n51#1:64,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final byte[] toByteArray(@NotNull List<? extends DeviceManualDataType> measurementTypes) {
            Intrinsics.checkNotNullParameter(measurementTypes, "measurementTypes");
            if (measurementTypes.contains(DeviceManualDataType.ALL)) {
                return new byte[]{-1, -1, -1, -1};
            }
            int bitMask = 0;
            Iterator<T> it = measurementTypes.iterator();
            while (it.hasNext()) {
                bitMask |= ((DeviceManualDataType) it.next()).getBitMask();
            }
            return new byte[]{(byte) bitMask, (byte) (bitMask >> 8), (byte) (bitMask >> 16), (byte) (bitMask >> 24)};
        }
    }

    DeviceManualDataType(int i) {
        this.bitPosition = i;
    }

    @NotNull
    public static EnumEntries<DeviceManualDataType> getEntries() {
        return $ENTRIES;
    }

    public final int getBitPosition() {
        return this.bitPosition;
    }

    public final int getBitMask() {
        return 1 << this.bitPosition;
    }
}
