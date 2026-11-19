package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.DepressionRisk;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/GsrDetectResult.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J;\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020\u001a2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006)"}, d2 = {"Lcom/veepoo/protocol/model/datas/GsrDetectResult;", "", "emotionLevel", "", "skinMoisture", "depressionRisk", "Lcom/veepoo/protocol/model/enums/DepressionRisk;", "snsActivation", "cortisolValue", "<init>", "(IILcom/veepoo/protocol/model/enums/DepressionRisk;II)V", "getEmotionLevel", "()I", "setEmotionLevel", "(I)V", "getSkinMoisture", "setSkinMoisture", "getDepressionRisk", "()Lcom/veepoo/protocol/model/enums/DepressionRisk;", "setDepressionRisk", "(Lcom/veepoo/protocol/model/enums/DepressionRisk;)V", "getSnsActivation", "setSnsActivation", "getCortisolValue", "setCortisolValue", "isCortisolNormal", "", "getCortisolUgPerDl", "", "getCortisolNmolPerL", "toString", "", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "vpprotocol_release"})
public final /* data */ class GsrDetectResult {
    private int emotionLevel;
    private int skinMoisture;

    @NotNull
    private DepressionRisk depressionRisk;
    private int snsActivation;
    private int cortisolValue;

    public static /* synthetic */ GsrDetectResult copy$default(GsrDetectResult gsrDetectResult, int i, int i2, DepressionRisk depressionRisk, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = gsrDetectResult.emotionLevel;
        }
        if ((i5 & 2) != 0) {
            i2 = gsrDetectResult.skinMoisture;
        }
        if ((i5 & 4) != 0) {
            depressionRisk = gsrDetectResult.depressionRisk;
        }
        if ((i5 & 8) != 0) {
            i3 = gsrDetectResult.snsActivation;
        }
        if ((i5 & 16) != 0) {
            i4 = gsrDetectResult.cortisolValue;
        }
        return gsrDetectResult.copy(i, i2, depressionRisk, i3, i4);
    }

    public GsrDetectResult() {
        this(0, 0, null, 0, 0, 31, null);
    }

    public final int getEmotionLevel() {
        return this.emotionLevel;
    }

    public final void setEmotionLevel(int i) {
        this.emotionLevel = i;
    }

    public final int getSkinMoisture() {
        return this.skinMoisture;
    }

    public final void setSkinMoisture(int i) {
        this.skinMoisture = i;
    }

    @NotNull
    public final DepressionRisk getDepressionRisk() {
        return this.depressionRisk;
    }

    public final void setDepressionRisk(@NotNull DepressionRisk depressionRisk) {
        Intrinsics.checkNotNullParameter(depressionRisk, "<set-?>");
        this.depressionRisk = depressionRisk;
    }

    public final int getSnsActivation() {
        return this.snsActivation;
    }

    public final void setSnsActivation(int i) {
        this.snsActivation = i;
    }

    public final int getCortisolValue() {
        return this.cortisolValue;
    }

    public final void setCortisolValue(int i) {
        this.cortisolValue = i;
    }

    public final boolean isCortisolNormal() {
        int i = this.cortisolValue;
        return i >= 0 && i < 231;
    }

    public final float getCortisolUgPerDl() {
        return this.cortisolValue / 10.0f;
    }

    public final float getCortisolNmolPerL() {
        return this.cortisolValue / 0.36247f;
    }

    @NotNull
    public String toString() {
        return "GsrDetectResult(emotionLevel=" + this.emotionLevel + ", skinMoisture=" + this.skinMoisture + ", depressionRisk=" + this.depressionRisk + ", snsActivation=" + this.snsActivation + ", cortisolValue=" + this.cortisolValue + ")";
    }

    public final int component1() {
        return this.emotionLevel;
    }

    public final int component2() {
        return this.skinMoisture;
    }

    @NotNull
    public final DepressionRisk component3() {
        return this.depressionRisk;
    }

    public final int component4() {
        return this.snsActivation;
    }

    public final int component5() {
        return this.cortisolValue;
    }

    @NotNull
    public final GsrDetectResult copy(int emotionLevel, int skinMoisture, @NotNull DepressionRisk depressionRisk, int snsActivation, int cortisolValue) {
        Intrinsics.checkNotNullParameter(depressionRisk, "depressionRisk");
        return new GsrDetectResult(emotionLevel, skinMoisture, depressionRisk, snsActivation, cortisolValue);
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.emotionLevel) * 31) + Integer.hashCode(this.skinMoisture)) * 31) + this.depressionRisk.hashCode()) * 31) + Integer.hashCode(this.snsActivation)) * 31) + Integer.hashCode(this.cortisolValue);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GsrDetectResult)) {
            return false;
        }
        GsrDetectResult gsrDetectResult = (GsrDetectResult) other;
        return this.emotionLevel == gsrDetectResult.emotionLevel && this.skinMoisture == gsrDetectResult.skinMoisture && this.depressionRisk == gsrDetectResult.depressionRisk && this.snsActivation == gsrDetectResult.snsActivation && this.cortisolValue == gsrDetectResult.cortisolValue;
    }

    public GsrDetectResult(int i, int i2, @NotNull DepressionRisk depressionRisk, int i3, int i4) {
        Intrinsics.checkNotNullParameter(depressionRisk, "depressionRisk");
        this.emotionLevel = i;
        this.skinMoisture = i2;
        this.depressionRisk = depressionRisk;
        this.snsActivation = i3;
        this.cortisolValue = i4;
    }

    public /* synthetic */ GsrDetectResult(int i, int i2, DepressionRisk depressionRisk, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? DepressionRisk.LOW : depressionRisk, (i5 & 8) != 0 ? 0 : i3, (i5 & 16) != 0 ? 0 : i4);
    }
}
