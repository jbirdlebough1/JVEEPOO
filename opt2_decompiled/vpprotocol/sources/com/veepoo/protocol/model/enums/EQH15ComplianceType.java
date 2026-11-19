package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15ComplianceType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15ComplianceType;", "", "tag", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getTag", "()B", "getDes", "()Ljava/lang/String;", "BIOS_AGE", "HEART_AGE", "FITNESS_AGE", "STEPS_GOAL", "NUTRITION_GOAL", "ALL_GOALS_ACHIEVED", "NEW_FITNESS_GOAL", "Companion", "vpprotocol_release"})
public enum EQH15ComplianceType {
    BIOS_AGE((byte) 0, "BIOS Age"),
    HEART_AGE((byte) 1, "Heart Age"),
    FITNESS_AGE((byte) 2, "Fitness Age"),
    STEPS_GOAL((byte) 3, "Steps goal"),
    NUTRITION_GOAL((byte) 4, "Nutrition goal"),
    ALL_GOALS_ACHIEVED((byte) 5, "All goals achieved"),
    NEW_FITNESS_GOAL((byte) 6, "New fitness goals");

    private final byte tag;

    @NotNull
    private final String des;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EQH15ComplianceType$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EQH15ComplianceType$Companion;", "", "<init>", "()V", "getTypeWithTag", "Lcom/veepoo/protocol/model/enums/EQH15ComplianceType;", "tag", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEQH15ComplianceType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EQH15ComplianceType.kt\ncom/veepoo/protocol/model/enums/EQH15ComplianceType$Companion\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,25:1\n37#2,2:26\n1#3:28\n*S KotlinDebug\n*F\n+ 1 EQH15ComplianceType.kt\ncom/veepoo/protocol/model/enums/EQH15ComplianceType$Companion\n*L\n22#1:26,2\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final EQH15ComplianceType getTypeWithTag(int tag) {
            Object obj;
            Object[] array = EQH15ComplianceType.getEntries().toArray(new EQH15ComplianceType[0]);
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
                if (((EQH15ComplianceType) obj2).getTag() == tag) {
                    break;
                }
                if (i < 0) {
                    obj = null;
                    break;
                }
            }
            EQH15ComplianceType eQH15ComplianceType = (EQH15ComplianceType) obj;
            EQH15ComplianceType eQH15ComplianceType2 = eQH15ComplianceType;
            if (eQH15ComplianceType == null) {
                eQH15ComplianceType2 = EQH15ComplianceType.BIOS_AGE;
            }
            return eQH15ComplianceType2;
        }
    }

    EQH15ComplianceType(byte b, String str) {
        this.tag = b;
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EQH15ComplianceType> getEntries() {
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
