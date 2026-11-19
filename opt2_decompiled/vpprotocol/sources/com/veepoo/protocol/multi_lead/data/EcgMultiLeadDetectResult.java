package com.veepoo.protocol.multi_lead.data;

import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectResult.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u00103\u001a\u000204H\u0016J\u000e\u00105\u001a\u00020\u00112\u0006\u00106\u001a\u000207J\u0006\u00108\u001a\u000209R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\u001c\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001a\u0010\u001f\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001a\u0010\"\u001a\u00020#X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010(\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\"\u0010/\u001a\n\u0012\u0004\u0012\u000200\u0018\u00010)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010,\"\u0004\b2\u0010.¨\u0006:"}, d2 = {"Lcom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectResult;", "", "<init>", "()V", "type", "Lcom/veepoo/protocol/model/enums/EECGResultType;", "getType", "()Lcom/veepoo/protocol/model/enums/EECGResultType;", "setType", "(Lcom/veepoo/protocol/model/enums/EECGResultType;)V", "timeBean", "Lcom/veepoo/protocol/model/datas/TimeData;", "getTimeBean", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setTimeBean", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "frequency", "", "getFrequency", "()I", "setFrequency", "(I)V", "duration", "getDuration", "setDuration", "avgHeart", "getAvgHeart", "setAvgHeart", "avgQT", "getAvgQT", "setAvgQT", "avgHRV", "getAvgHRV", "setAvgHRV", "diseaseType", "", "getDiseaseType", "()[I", "setDiseaseType", "([I)V", "diseaseInfoList", "", "Lcom/veepoo/protocol/multi_lead/data/EcgDiseaseInfo;", "getDiseaseInfoList", "()Ljava/util/List;", "setDiseaseInfoList", "(Ljava/util/List;)V", "waveformsInfoList", "Lcom/veepoo/protocol/multi_lead/data/EcgWaveformsInfo;", "getWaveformsInfoList", "setWaveformsInfoList", "toString", "", "getLastLeadWaveformTypePosition", "leadFlag", "Lcom/veepoo/protocol/multi_lead/enums/ELeadFlag;", "filter6SecondData", "", "vpprotocol_release"})
@SourceDebugExtension({"SMAP\nEcgMultiLeadDetectResult.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EcgMultiLeadDetectResult.kt\ncom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectResult\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,116:1\n1#2:117\n1869#3:118\n1878#3,3:119\n1870#3:122\n1869#3:123\n1870#3:127\n13900#4,3:124\n*S KotlinDebug\n*F\n+ 1 EcgMultiLeadDetectResult.kt\ncom/veepoo/protocol/multi_lead/data/EcgMultiLeadDetectResult\n*L\n97#1:118\n98#1:119,3\n97#1:122\n105#1:123\n105#1:127\n106#1:124,3\n*E\n"})
public final class EcgMultiLeadDetectResult {

    @Nullable
    private EECGResultType type;

    @Nullable
    private TimeData timeBean;
    private int frequency;
    private int duration;
    private int avgHeart;
    private int avgQT;
    private int avgHRV;

    @NotNull
    private int[] diseaseType = new int[27];

    @Nullable
    private List<EcgDiseaseInfo> diseaseInfoList;

    @Nullable
    private List<EcgWaveformsInfo> waveformsInfoList;

    @Nullable
    public final EECGResultType getType() {
        return this.type;
    }

    public final void setType(@Nullable EECGResultType eECGResultType) {
        this.type = eECGResultType;
    }

    @Nullable
    public final TimeData getTimeBean() {
        return this.timeBean;
    }

    public final void setTimeBean(@Nullable TimeData timeData) {
        this.timeBean = timeData;
    }

    public final int getFrequency() {
        return this.frequency;
    }

    public final void setFrequency(int i) {
        this.frequency = i;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final int getAvgHeart() {
        return this.avgHeart;
    }

    public final void setAvgHeart(int i) {
        this.avgHeart = i;
    }

    public final int getAvgQT() {
        return this.avgQT;
    }

    public final void setAvgQT(int i) {
        this.avgQT = i;
    }

    public final int getAvgHRV() {
        return this.avgHRV;
    }

    public final void setAvgHRV(int i) {
        this.avgHRV = i;
    }

    @NotNull
    public final int[] getDiseaseType() {
        return this.diseaseType;
    }

    public final void setDiseaseType(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.diseaseType = iArr;
    }

    @Nullable
    public final List<EcgDiseaseInfo> getDiseaseInfoList() {
        return this.diseaseInfoList;
    }

    public final void setDiseaseInfoList(@Nullable List<EcgDiseaseInfo> list) {
        this.diseaseInfoList = list;
    }

    @Nullable
    public final List<EcgWaveformsInfo> getWaveformsInfoList() {
        return this.waveformsInfoList;
    }

    public final void setWaveformsInfoList(@Nullable List<EcgWaveformsInfo> list) {
        this.waveformsInfoList = list;
    }

    @NotNull
    public String toString() {
        EECGResultType eECGResultType = this.type;
        TimeData timeData = this.timeBean;
        int i = this.frequency;
        int i2 = this.duration;
        int i3 = this.avgHeart;
        int i4 = this.avgQT;
        int i5 = this.avgHRV;
        String string = Arrays.toString(this.diseaseType);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return "EcgMultiLeadDetectResult{, type=" + eECGResultType + ", timeBean=" + timeData + ", frequency=" + i + ", duration=" + i2 + ", avgHeart=" + i3 + ", avgQT=" + i4 + ", avgHRV=" + i5 + ", diseaseType=" + string + ", diseaseInfoList=" + this.diseaseInfoList + "}";
    }

    public final int getLastLeadWaveformTypePosition(@NotNull ELeadFlag leadFlag) {
        EcgWaveformsInfo ecgWaveformsInfo;
        EcgWaveformsInfo ecgWaveformsInfoPrevious;
        Intrinsics.checkNotNullParameter(leadFlag, "leadFlag");
        int iIntValue = 0;
        List<EcgWaveformsInfo> list = this.waveformsInfoList;
        if (list == null) {
            return 0;
        }
        Intrinsics.checkNotNull(list);
        ListIterator<EcgWaveformsInfo> listIterator = list.listIterator(list.size());
        do {
            if (!listIterator.hasPrevious()) {
                ecgWaveformsInfo = null;
                break;
            }
            ecgWaveformsInfoPrevious = listIterator.previous();
            ecgWaveformsInfo = ecgWaveformsInfoPrevious;
        } while (ecgWaveformsInfoPrevious.getFlag() != leadFlag);
        EcgWaveformsInfo ecgWaveformsInfo2 = ecgWaveformsInfo;
        Intrinsics.checkNotNull(ecgWaveformsInfo2);
        EcgWaveformsInfo ecgWaveformsInfo3 = ecgWaveformsInfo2;
        int waveCount = ecgWaveformsInfo3.getWaveCount() - 1;
        if (waveCount > 0 && ecgWaveformsInfo3.getWavePositionArr().size() > waveCount) {
            iIntValue = ecgWaveformsInfo3.getWavePositionArr().get(ecgWaveformsInfo3.getWaveCount() - 1).intValue();
        }
        return iIntValue;
    }

    public final void filter6SecondData() {
        List<EcgWaveformsInfo> list = this.waveformsInfoList;
        if (list != null) {
            for (EcgWaveformsInfo ecgWaveformsInfo : list) {
                int i = 0;
                for (Object obj : ecgWaveformsInfo.getWavePositionArr()) {
                    int i2 = i;
                    int i3 = i + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    int iIntValue = ((Number) obj).intValue();
                    if (iIntValue >= 1500) {
                        ecgWaveformsInfo.getWavePositionArr().set(i, Integer.valueOf(iIntValue - 1500));
                    }
                    i = i3;
                }
            }
        }
        List<EcgDiseaseInfo> list2 = this.diseaseInfoList;
        if (list2 != null) {
            for (EcgDiseaseInfo ecgDiseaseInfo : list2) {
                int[] position = ecgDiseaseInfo.getPosition();
                int i4 = 0;
                int i5 = 0;
                int length = position.length;
                while (i5 < length) {
                    int i6 = position[i5];
                    int i7 = i4 + 1;
                    if (i6 >= 1500) {
                        ecgDiseaseInfo.getPosition()[i4] = i6 - 1500;
                    }
                    i5++;
                    i4 = i7;
                }
            }
        }
    }
}
