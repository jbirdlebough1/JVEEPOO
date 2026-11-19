package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EHealthLightStatus.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/enums/EHealthLightStatus;", "", "tag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getTag", "()B", "getDes", "()Ljava/lang/String;", "OFF", "SLOW_FLASH", "RAPID_FLASHING", "ALWAYS_ON", "Companion", "vpprotocol_release"})
public enum EHealthLightStatus {
    OFF((byte) 0, "关闭"),
    SLOW_FLASH((byte) 1, "慢闪"),
    RAPID_FLASHING((byte) 2, "连续闪"),
    ALWAYS_ON((byte) 3, "常亮");

    private final byte tag;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EHealthLightStatus$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EHealthLightStatus$Companion;", "", "<init>", "()V", "getStatusWithCMD", "Lcom/veepoo/protocol/model/enums/EHealthLightStatus;", "tag", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEHealthLightStatus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EHealthLightStatus.kt\ncom/veepoo/protocol/model/enums/EHealthLightStatus$Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,12:1\n37#2,2:13\n1#3:15\n*S KotlinDebug\n*F\n+ 1 EHealthLightStatus.kt\ncom/veepoo/protocol/model/enums/EHealthLightStatus$Companion\n*L\n10#1:13,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EHealthLightStatus getStatusWithCMD(byte tag) {
            Object obj;
            Object[] array = EHealthLightStatus.getEntries().toArray(new EHealthLightStatus[0]);
            int length = array.length - 1;
            int i = length;
            if (length >= 0) {
                do {
                    int i2 = i;
                    i = i2 - 1;
                    Object obj2 = array[i2];
                    obj = obj2;
                    if (((EHealthLightStatus) obj2).getTag() == tag) {
                    }
                } while (i >= 0);
                obj = null;
            } else {
                obj = null;
            }
            return (EHealthLightStatus) obj;
        }
    }

    EHealthLightStatus(byte b, String str) {
        this.tag = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EHealthLightStatus> getEntries() {
        return $ENTRIES;
    }

    public final byte getTag() {
        return this.tag;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }
}
