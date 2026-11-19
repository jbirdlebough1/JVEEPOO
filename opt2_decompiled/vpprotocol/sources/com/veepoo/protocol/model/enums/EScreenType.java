package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EScreenType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/veepoo/protocol/model/enums/EScreenType;", "", "type", "", "typeName", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getType", "()I", "getTypeName", "()Ljava/lang/String;", "UNKNOWN", "SCREENLESS_WRISTBAND", "RING", "SQUARE_SCREEN", "ROUND_SCREEN", "Companion", "vpprotocol_release"})
public enum EScreenType {
    UNKNOWN(0, "未知"),
    SCREENLESS_WRISTBAND(1, "无屏手环"),
    RING(2, "戒指"),
    SQUARE_SCREEN(3, "方屏"),
    ROUND_SCREEN(4, "圆屏");

    private final int type;

    @NotNull
    private final String typeName;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EScreenType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EScreenType$Companion;", "", "<init>", "()V", "getEScreenType", "Lcom/veepoo/protocol/model/enums/EScreenType;", "type", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEScreenType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EScreenType.kt\ncom/veepoo/protocol/model/enums/EScreenType$Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,17:1\n37#2,2:18\n1#3:20\n*S KotlinDebug\n*F\n+ 1 EScreenType.kt\ncom/veepoo/protocol/model/enums/EScreenType$Companion\n*L\n15#1:18,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EScreenType getEScreenType(int type) {
            Object obj;
            Object[] array = EScreenType.getEntries().toArray(new EScreenType[0]);
            int length = array.length - 1;
            int i = length;
            if (length < 0) {
                obj = null;
                break;
            }
            while (true) {
                int i2 = i;
                i = i2 - 1;
                Object obj2 = array[i2];
                obj = obj2;
                if (((EScreenType) obj2).getType() == type) {
                    break;
                }
                if (i < 0) {
                    obj = null;
                    break;
                }
            }
            EScreenType eScreenType = (EScreenType) obj;
            EScreenType eScreenType2 = eScreenType;
            if (eScreenType == null) {
                eScreenType2 = EScreenType.UNKNOWN;
            }
            return eScreenType2;
        }
    }

    EScreenType(int i, String str) {
        this.type = i;
        this.typeName = str;
    }

    @NotNull
    public static EnumEntries<EScreenType> getEntries() {
        return $ENTRIES;
    }

    public final int getType() {
        return this.type;
    }

    @NotNull
    public final String getTypeName() {
        return this.typeName;
    }
}
