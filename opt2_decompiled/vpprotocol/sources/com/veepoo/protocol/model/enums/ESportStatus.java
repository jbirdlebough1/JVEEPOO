package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESportStatus.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/model/enums/ESportStatus;", "", "tag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getTag", "()B", "getDes", "()Ljava/lang/String;", "UNKNOWN", "WALK", "RUNNING", "REST", "Companion", "vpprotocol_release"})
public enum ESportStatus {
    UNKNOWN((byte) 0, "未知"),
    WALK((byte) 1, "走路"),
    RUNNING((byte) 2, "跑步"),
    REST((byte) 3, "静息");

    private final byte tag;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/ESportStatus$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/ESportStatus$Companion;", "", "<init>", "()V", "getStatusByTag", "Lcom/veepoo/protocol/model/enums/ESportStatus;", "tag", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nESportStatus.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ESportStatus.kt\ncom/veepoo/protocol/model/enums/ESportStatus$Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,12:1\n37#2,2:13\n1#3:15\n*S KotlinDebug\n*F\n+ 1 ESportStatus.kt\ncom/veepoo/protocol/model/enums/ESportStatus$Companion\n*L\n10#1:13,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ESportStatus getStatusByTag(byte tag) {
            Object obj;
            Object[] array = ESportStatus.getEntries().toArray(new ESportStatus[0]);
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
                if (((ESportStatus) obj2).getTag() == tag) {
                    break;
                }
                if (i < 0) {
                    obj = null;
                    break;
                }
            }
            ESportStatus eSportStatus = (ESportStatus) obj;
            ESportStatus eSportStatus2 = eSportStatus;
            if (eSportStatus == null) {
                eSportStatus2 = ESportStatus.UNKNOWN;
            }
            return eSportStatus2;
        }
    }

    ESportStatus(byte b, String str) {
        this.tag = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<ESportStatus> getEntries() {
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
