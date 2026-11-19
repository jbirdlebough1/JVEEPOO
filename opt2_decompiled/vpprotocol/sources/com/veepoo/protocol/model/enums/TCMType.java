package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/TCMType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\b\u0086\u0081\u0002\u0018\u0000 \u001f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001fB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001e¨\u0006 "}, d2 = {"Lcom/veepoo/protocol/model/enums/TCMType;", "", "tag", "", "description", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getTag", "()B", "getDescription", "()Ljava/lang/String;", "BLOOD_STASIS", "DAMP_HEAT", "SPECIAL_ALLERGY", "YANG_DEFICIENCY", "YIN_DEFICIENCY", "PHLEGM_DAMPNESS", "BALANCED", "QI_STAGNATION", "QI_DEFICIENCY", "LARGE_INTESTINE", "GALLBLADDER", "LIVER", "SPLEEN", "LUNG", "SMALL_INTESTINE", "SANJIAO_BLADDER", "KIDNEY", "STOMACH", "HEART", "Companion", "vpprotocol_release"})
public enum TCMType {
    BLOOD_STASIS((byte) 1, "血瘀"),
    DAMP_HEAT((byte) 2, "湿热"),
    SPECIAL_ALLERGY((byte) 3, "特禀"),
    YANG_DEFICIENCY((byte) 4, "阳虚"),
    YIN_DEFICIENCY((byte) 5, "阴虚"),
    PHLEGM_DAMPNESS((byte) 6, "痰湿"),
    BALANCED((byte) 7, "平和"),
    QI_STAGNATION((byte) 8, "气郁"),
    QI_DEFICIENCY((byte) 9, "气虚"),
    LARGE_INTESTINE((byte) 10, "大肠"),
    GALLBLADDER((byte) 11, "胆"),
    LIVER((byte) 12, "肝"),
    SPLEEN((byte) 13, "脾"),
    LUNG((byte) 14, "肺"),
    SMALL_INTESTINE((byte) 15, "小肠"),
    SANJIAO_BLADDER((byte) 16, "三焦膀胱"),
    KIDNEY((byte) 17, "肾"),
    STOMACH((byte) 18, "胃"),
    HEART((byte) 19, "心脏");

    private final byte tag;

    @NotNull
    private final String description;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/TCMType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/TCMType$Companion;", "", "<init>", "()V", "fromTag", "Lcom/veepoo/protocol/model/enums/TCMType;", "tag", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nTCMType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TCMType.kt\ncom/veepoo/protocol/model/enums/TCMType$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,30:1\n1#2:31\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final TCMType fromTag(byte tag) {
            TCMType tCMType;
            for (TCMType tCMType2 : TCMType.values()) {
                tCMType = tCMType2;
                if (tCMType2.getTag() == tag) {
                    return tCMType;
                }
            }
            tCMType = null;
            return tCMType;
        }
    }

    TCMType(byte b, String str) {
        this.tag = b;
        this.description = str;
    }

    @NotNull
    public static EnumEntries<TCMType> getEntries() {
        return $ENTRIES;
    }

    public final byte getTag() {
        return this.tag;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }
}
