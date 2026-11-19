package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/HealthRemindType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0017B\u001d\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0018"}, d2 = {"Lcom/veepoo/protocol/model/enums/HealthRemindType;", "", "flag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getFlag", "()B", "getDes", "()Ljava/lang/String;", "setDes", "(Ljava/lang/String;)V", "ALL", "SEDENTARY", "DRINK_WATER", "OVERLOOK", "SPORTS", "TAKE_MEDICINE", "READING", "GOING_OUT", "WASH", "Companion", "vpprotocol_release"})
public enum HealthRemindType {
    ALL((byte) 0, "所有提醒"),
    SEDENTARY((byte) 1, "久坐"),
    DRINK_WATER((byte) 2, "喝水"),
    OVERLOOK((byte) 3, "远眺"),
    SPORTS((byte) 4, "运动"),
    TAKE_MEDICINE((byte) 5, "吃药"),
    READING((byte) 6, "看书"),
    GOING_OUT((byte) 7, "出行"),
    WASH((byte) 8, "洗手");

    private final byte flag;

    @Nullable
    private String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/HealthRemindType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/HealthRemindType$Companion;", "", "<init>", "()V", "getHealthRemindTypeByFlag", "Lcom/veepoo/protocol/model/enums/HealthRemindType;", "byte", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nHealthRemindType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HealthRemindType.kt\ncom/veepoo/protocol/model/enums/HealthRemindType$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,65:1\n13805#2,2:66\n*S KotlinDebug\n*F\n+ 1 HealthRemindType.kt\ncom/veepoo/protocol/model/enums/HealthRemindType$Companion\n*L\n57#1:66,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final HealthRemindType getHealthRemindTypeByFlag(byte b) {
            for (HealthRemindType healthRemindType : HealthRemindType.values()) {
                if (healthRemindType.getFlag() == b) {
                    return healthRemindType;
                }
            }
            return HealthRemindType.SEDENTARY;
        }
    }

    HealthRemindType(byte b, String str) {
        this.flag = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<HealthRemindType> getEntries() {
        return $ENTRIES;
    }

    public final byte getFlag() {
        return this.flag;
    }

    @Nullable
    public final String getDes() {
        return this.des;
    }

    public final void setDes(@Nullable String str) {
        this.des = str;
    }
}
