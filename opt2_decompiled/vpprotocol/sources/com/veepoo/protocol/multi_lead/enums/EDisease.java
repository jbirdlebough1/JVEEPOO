package com.veepoo.protocol.multi_lead.enums;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EDisease.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b1\b\u0086\u0081\u0002\u0018\u0000 52\b\u0012\u0004\u0012\u00020\u00000\u0001:\u00015B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u00104\u001a\u00020\u0005H\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3¨\u00066"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EDisease;", "", "value", "", "description", "", "<init>", "(Ljava/lang/String;IILjava/lang/String;)V", "getValue", "()I", "getDescription", "()Ljava/lang/String;", "AtrialPrematureBeat", "PrematureContractions", "VentricularPrematureBeatType1", "VentricularPrematureBeatType2", "EarlyVentricularPrematureBeatType1", "EarlyVentricularPrematureBeatType2", "RonTType1", "RonTType2", "MultifocalVentricularPrematureBeat", "AtrialFibrillationCoarse", "AtrialFibrillationFine", "AtrialFlutter", "AtrialTachycardia", "ParoxysmalSupraventricularTachycardia", "SupraventricularTachycardia", "SinusArrhythmia", "MissedBeat", "JunctionalRhythm", "Bigeminy", "Trigeminy", "VentricularFibrillation", "Asystole", "FirstDegreeAVBlock", "SecondDegreeAVBlock", "ThirdDegreeAVBlock", "RightBundleBranchBlock", "LeftBundleBranchBlock", "LAFB", "LPFB", "wpw", "atrialEscape", "ventilationEscape", "junctionalEscape", "lmyocardialInfarction1", "lmyocardialInfarction2", "lmyocardialInfarction3", "ventricularHypertrophy", "atrialHypertrophy", "myocardialIschemia", "ventricularFlutter", "toString", "Companion", "vpprotocol_release"})
@SourceDebugExtension({"SMAP\nEDisease.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EDisease.kt\ncom/veepoo/protocol/multi_lead/enums/EDisease\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,120:1\n9037#2,2:121\n9297#2,4:123\n*S KotlinDebug\n*F\n+ 1 EDisease.kt\ncom/veepoo/protocol/multi_lead/enums/EDisease\n*L\n93#1:121,2\n93#1:123,4\n*E\n"})
public enum EDisease {
    AtrialPrematureBeat(1, "房性早搏"),
    PrematureContractions(2, "房室节期前收缩"),
    VentricularPrematureBeatType1(3, "1型室性早搏"),
    VentricularPrematureBeatType2(4, "2型室性早搏"),
    EarlyVentricularPrematureBeatType1(5, "早期1型室性早搏"),
    EarlyVentricularPrematureBeatType2(6, "早期2型室性早搏"),
    RonTType1(7, "RonT1型室性早搏"),
    RonTType2(8, "RonT2型室性早搏"),
    MultifocalVentricularPrematureBeat(9, "多源性室性早搏"),
    AtrialFibrillationCoarse(10, "房颤--粗"),
    AtrialFibrillationFine(11, "房颤--细"),
    AtrialFlutter(12, "房扑"),
    AtrialTachycardia(13, "房性心动过速"),
    ParoxysmalSupraventricularTachycardia(14, "室性心动过速"),
    SupraventricularTachycardia(15, "室上性心动过速"),
    SinusArrhythmia(16, "窦性心律失常"),
    MissedBeat(17, "漏博"),
    JunctionalRhythm(18, "房室交界性Heart Rate"),
    Bigeminy(19, "二联律"),
    Trigeminy(20, "三联律"),
    VentricularFibrillation(21, "心室颤动"),
    Asystole(22, "停博"),
    FirstDegreeAVBlock(23, "I 度传导阻滞"),
    SecondDegreeAVBlock(24, "II 度传导阻滞"),
    ThirdDegreeAVBlock(25, "III 度传导阻滞"),
    RightBundleBranchBlock(26, "右束支传导阻滞"),
    LeftBundleBranchBlock(27, "左束支传导阻滞"),
    LAFB(28, "左前分支传导阻滞"),
    LPFB(29, "左后分支传导阻滞"),
    wpw(30, "预激综合征"),
    atrialEscape(31, "房性逸搏"),
    ventilationEscape(32, "室性逸搏"),
    junctionalEscape(33, "交界性逸搏"),
    lmyocardialInfarction1(34, "心肌梗死(损伤型)"),
    lmyocardialInfarction2(35, "心肌梗死(缺血型)"),
    lmyocardialInfarction3(36, "心肌梗死(坏死型)"),
    ventricularHypertrophy(37, "心室肥大"),
    atrialHypertrophy(38, "心房肥大"),
    myocardialIschemia(39, "心肌缺血"),
    ventricularFlutter(40, "室扑");

    private static final Map vp_a;

    @SerializedName("value")
    private final int value;

    @NotNull
    private final String description;
    private static final /* synthetic */ EnumEntries vp_c = EnumEntriesKt.enumEntries(vp_a());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/enums/EDisease$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0006J\u0010\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/multi_lead/enums/EDisease$Companion;", "", "<init>", "()V", "map", "", "", "Lcom/veepoo/protocol/multi_lead/enums/EDisease;", "fromValue", "value", "fromDescription", "description", "", "getDiseasesFromBitMask", "", "bitMask", "", "vpprotocol_release"})
    @SourceDebugExtension({"SMAP\nEDisease.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EDisease.kt\ncom/veepoo/protocol/multi_lead/enums/EDisease$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,120:1\n1#2:121\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EDisease fromValue(int value) {
            return (EDisease) EDisease.vp_a.get(Integer.valueOf(value));
        }

        @Nullable
        public final EDisease fromDescription(@NotNull String description) {
            EDisease eDisease;
            Intrinsics.checkNotNullParameter(description, "description");
            for (EDisease eDisease2 : EDisease.values()) {
                eDisease = eDisease2;
                if (Intrinsics.areEqual(eDisease2.getDescription(), description)) {
                    return eDisease;
                }
            }
            eDisease = null;
            return eDisease;
        }

        @NotNull
        public final List<EDisease> getDiseasesFromBitMask(long bitMask) {
            ArrayList arrayList = new ArrayList();
            for (EDisease eDisease : EDisease.values()) {
                if ((bitMask & (1 << (eDisease.getValue() - 1))) != 0) {
                    arrayList.add(eDisease);
                }
            }
            return arrayList;
        }
    }

    EDisease(int i, String str) {
        this.value = i;
        this.description = str;
    }

    @NotNull
    public static EnumEntries<EDisease> getEntries() {
        return vp_c;
    }

    static {
        EDisease[] eDiseaseArrValues = values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(eDiseaseArrValues.length), 16));
        for (EDisease eDisease : eDiseaseArrValues) {
            linkedHashMap.put(Integer.valueOf(eDisease.value), eDisease);
        }
        vp_a = linkedHashMap;
    }

    public final int getValue() {
        return this.value;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        return "EDisease(value=" + this.value + ", description='" + this.description + "')";
    }
}
