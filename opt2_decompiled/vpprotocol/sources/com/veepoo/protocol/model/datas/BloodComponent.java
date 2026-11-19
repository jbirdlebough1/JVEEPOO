package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BloodComponent.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J;\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\r¨\u0006#"}, d2 = {"Lcom/veepoo/protocol/model/datas/BloodComponent;", "", "uricAcid", "", "tCHO", "tAG", "hDL", "lDL", "<init>", "(FFFFF)V", "getUricAcid", "()F", "setUricAcid", "(F)V", "getTCHO", "setTCHO", "getTAG", "setTAG", "getHDL", "setHDL", "getLDL", "setLDL", "toString", "", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "vpprotocol_release"})
public final /* data */ class BloodComponent {
    private float uricAcid;
    private float tCHO;
    private float tAG;
    private float hDL;
    private float lDL;

    public static /* synthetic */ BloodComponent copy$default(BloodComponent bloodComponent, float f, float f2, float f3, float f4, float f5, int i, Object obj) {
        if ((i & 1) != 0) {
            f = bloodComponent.uricAcid;
        }
        if ((i & 2) != 0) {
            f2 = bloodComponent.tCHO;
        }
        if ((i & 4) != 0) {
            f3 = bloodComponent.tAG;
        }
        if ((i & 8) != 0) {
            f4 = bloodComponent.hDL;
        }
        if ((i & 16) != 0) {
            f5 = bloodComponent.lDL;
        }
        return bloodComponent.copy(f, f2, f3, f4, f5);
    }

    public BloodComponent() {
        this(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 31, null);
    }

    public final float getUricAcid() {
        return this.uricAcid;
    }

    public final void setUricAcid(float f) {
        this.uricAcid = f;
    }

    public final float getTCHO() {
        return this.tCHO;
    }

    public final void setTCHO(float f) {
        this.tCHO = f;
    }

    public final float getTAG() {
        return this.tAG;
    }

    public final void setTAG(float f) {
        this.tAG = f;
    }

    public final float getHDL() {
        return this.hDL;
    }

    public final void setHDL(float f) {
        this.hDL = f;
    }

    public final float getLDL() {
        return this.lDL;
    }

    public final void setLDL(float f) {
        this.lDL = f;
    }

    @NotNull
    public String toString() {
        return "血液成分(尿酸=" + this.uricAcid + " μmol/L, 总胆固醇=" + this.tCHO + " mmol/L, 甘油三酯=" + this.tAG + " mmol/L, 高密度脂蛋白=" + this.hDL + " mmol/L, 低密度脂蛋白=" + this.lDL + " mmol/L)";
    }

    public final float component1() {
        return this.uricAcid;
    }

    public final float component2() {
        return this.tCHO;
    }

    public final float component3() {
        return this.tAG;
    }

    public final float component4() {
        return this.hDL;
    }

    public final float component5() {
        return this.lDL;
    }

    @NotNull
    public final BloodComponent copy(float uricAcid, float tCHO, float tAG, float hDL, float lDL) {
        return new BloodComponent(uricAcid, tCHO, tAG, hDL, lDL);
    }

    public int hashCode() {
        return (((((((Float.hashCode(this.uricAcid) * 31) + Float.hashCode(this.tCHO)) * 31) + Float.hashCode(this.tAG)) * 31) + Float.hashCode(this.hDL)) * 31) + Float.hashCode(this.lDL);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BloodComponent)) {
            return false;
        }
        BloodComponent bloodComponent = (BloodComponent) other;
        return Float.compare(this.uricAcid, bloodComponent.uricAcid) == 0 && Float.compare(this.tCHO, bloodComponent.tCHO) == 0 && Float.compare(this.tAG, bloodComponent.tAG) == 0 && Float.compare(this.hDL, bloodComponent.hDL) == 0 && Float.compare(this.lDL, bloodComponent.lDL) == 0;
    }

    public BloodComponent(float f, float f2, float f3, float f4, float f5) {
        this.uricAcid = f;
        this.tCHO = f2;
        this.tAG = f3;
        this.hDL = f4;
        this.lDL = f5;
    }

    public /* synthetic */ BloodComponent(float f, float f2, float f3, float f4, float f5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0.0f : f, (i & 2) != 0 ? 0.0f : f2, (i & 4) != 0 ? 0.0f : f3, (i & 8) != 0 ? 0.0f : f4, (i & 16) != 0 ? 0.0f : f5);
    }
}
