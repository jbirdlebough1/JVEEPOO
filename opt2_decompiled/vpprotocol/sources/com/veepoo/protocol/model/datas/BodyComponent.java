package com.veepoo.protocol.model.datas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BodyComponent.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 F2\u00020\u0001:\u0001FBw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003¢\u0006\u0004\b\u0011\u0010\u0012By\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0013\u0012\u0006\u0010\u0004\u001a\u00020\u0013\u0012\u0006\u0010\u0005\u001a\u00020\u0013\u0012\u0006\u0010\u0006\u001a\u00020\u0013\u0012\u0006\u0010\u0007\u001a\u00020\u0013\u0012\u0006\u0010\b\u001a\u00020\u0013\u0012\u0006\u0010\t\u001a\u00020\u0013\u0012\u0006\u0010\n\u001a\u00020\u0013\u0012\u0006\u0010\u000b\u001a\u00020\u0013\u0012\u0006\u0010\f\u001a\u00020\u0013\u0012\u0006\u0010\r\u001a\u00020\u0013\u0012\u0006\u0010\u000e\u001a\u00020\u0013\u0012\u0006\u0010\u000f\u001a\u00020\u0013\u0012\u0006\u0010\u0010\u001a\u00020\u0013¢\u0006\u0004\b\u0011\u0010\u0014J\b\u0010D\u001a\u00020EH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0016\"\u0004\b(\u0010\u0018R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0016\"\u0004\b.\u0010\u0018R\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0016\"\u0004\b2\u0010\u0018R\u001a\u00103\u001a\u000204X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010;\"\u0004\b@\u0010=R\u001a\u0010A\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010;\"\u0004\bC\u0010=¨\u0006G"}, d2 = {"Lcom/veepoo/protocol/model/datas/BodyComponent;", "", "BMI", "", "bodyFatRate", "fatRate", "FFM", "muscleRate", "muscleMass", "subcutaneousFat", "bodyWater", "waterContent", "skeletalMuscleRate", "boneMass", "proteinProportion", "proteinMass", "basalMetabolicRate", "<init>", "(FFFFFFFFFFFFFF)V", "", "(IIIIIIIIIIIIII)V", "getBMI", "()F", "setBMI", "(F)V", "getBodyFatRate", "setBodyFatRate", "getFatRate", "setFatRate", "getFFM", "setFFM", "getMuscleRate", "setMuscleRate", "getMuscleMass", "setMuscleMass", "getSubcutaneousFat", "setSubcutaneousFat", "getBodyWater", "setBodyWater", "getWaterContent", "setWaterContent", "getSkeletalMuscleRate", "setSkeletalMuscleRate", "getBoneMass", "setBoneMass", "getProteinProportion", "setProteinProportion", "getProteinMass", "setProteinMass", "getBasalMetabolicRate", "setBasalMetabolicRate", "timeBean", "Lcom/veepoo/protocol/model/datas/TimeData;", "getTimeBean", "()Lcom/veepoo/protocol/model/datas/TimeData;", "setTimeBean", "(Lcom/veepoo/protocol/model/datas/TimeData;)V", "duration", "getDuration", "()I", "setDuration", "(I)V", "idType", "getIdType", "setIdType", "id", "getId", "setId", "toString", "", "Companion", "vpprotocol_release"})
public final class BodyComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private float BMI;
    private float bodyFatRate;
    private float fatRate;
    private float FFM;
    private float muscleRate;
    private float muscleMass;
    private float subcutaneousFat;
    private float bodyWater;
    private float waterContent;
    private float skeletalMuscleRate;
    private float boneMass;
    private float proteinProportion;
    private float proteinMass;
    private float basalMetabolicRate;
    public TimeData timeBean;
    private int duration;
    private int idType;
    private int id;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BodyComponent$Companion.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/veepoo/protocol/model/datas/BodyComponent$Companion;", "", "<init>", "()V", "floatScale", "", "input", "scale", "", "vpprotocol_release"})
    public static final class Companion {
        private Companion() {
        }

        public static /* synthetic */ float floatScale$default(Companion companion, float f, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 1;
            }
            return companion.floatScale(f, i);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final float floatScale(float input, int scale) {
            return new BigDecimal(String.valueOf(input)).setScale(scale, RoundingMode.DOWN).floatValue();
        }
    }

    public BodyComponent(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14) {
        this.BMI = f;
        this.bodyFatRate = f2;
        this.fatRate = f3;
        this.FFM = f4;
        this.muscleRate = f5;
        this.muscleMass = f6;
        this.subcutaneousFat = f7;
        this.bodyWater = f8;
        this.waterContent = f9;
        this.skeletalMuscleRate = f10;
        this.boneMass = f11;
        this.proteinProportion = f12;
        this.proteinMass = f13;
        this.basalMetabolicRate = f14;
        this.idType = 1;
        this.id = -1;
    }

    public final float getBMI() {
        return this.BMI;
    }

    public final void setBMI(float f) {
        this.BMI = f;
    }

    public final float getBodyFatRate() {
        return this.bodyFatRate;
    }

    public final void setBodyFatRate(float f) {
        this.bodyFatRate = f;
    }

    public final float getFatRate() {
        return this.fatRate;
    }

    public final void setFatRate(float f) {
        this.fatRate = f;
    }

    public final float getFFM() {
        return this.FFM;
    }

    public final void setFFM(float f) {
        this.FFM = f;
    }

    public final float getMuscleRate() {
        return this.muscleRate;
    }

    public final void setMuscleRate(float f) {
        this.muscleRate = f;
    }

    public final float getMuscleMass() {
        return this.muscleMass;
    }

    public final void setMuscleMass(float f) {
        this.muscleMass = f;
    }

    public final float getSubcutaneousFat() {
        return this.subcutaneousFat;
    }

    public final void setSubcutaneousFat(float f) {
        this.subcutaneousFat = f;
    }

    public final float getBodyWater() {
        return this.bodyWater;
    }

    public final void setBodyWater(float f) {
        this.bodyWater = f;
    }

    public final float getWaterContent() {
        return this.waterContent;
    }

    public final void setWaterContent(float f) {
        this.waterContent = f;
    }

    public final float getSkeletalMuscleRate() {
        return this.skeletalMuscleRate;
    }

    public final void setSkeletalMuscleRate(float f) {
        this.skeletalMuscleRate = f;
    }

    public final float getBoneMass() {
        return this.boneMass;
    }

    public final void setBoneMass(float f) {
        this.boneMass = f;
    }

    public final float getProteinProportion() {
        return this.proteinProportion;
    }

    public final void setProteinProportion(float f) {
        this.proteinProportion = f;
    }

    public final float getProteinMass() {
        return this.proteinMass;
    }

    public final void setProteinMass(float f) {
        this.proteinMass = f;
    }

    public final float getBasalMetabolicRate() {
        return this.basalMetabolicRate;
    }

    public final void setBasalMetabolicRate(float f) {
        this.basalMetabolicRate = f;
    }

    @NotNull
    public final TimeData getTimeBean() {
        TimeData timeData = this.timeBean;
        if (timeData != null) {
            return timeData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("timeBean");
        return null;
    }

    public final void setTimeBean(@NotNull TimeData timeData) {
        Intrinsics.checkNotNullParameter(timeData, "<set-?>");
        this.timeBean = timeData;
    }

    public final int getDuration() {
        return this.duration;
    }

    public final void setDuration(int i) {
        this.duration = i;
    }

    public final int getIdType() {
        return this.idType;
    }

    public final void setIdType(int i) {
        this.idType = i;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    @NotNull
    public String toString() {
        return "BodyComponent(BMI=" + this.BMI + ", 体脂率=" + this.bodyFatRate + ", 脂肪量=" + this.fatRate + ", 去脂体重=" + this.FFM + ", 肌肉率=" + this.muscleRate + ", 肌肉量=" + this.muscleMass + ", 皮下脂肪=" + this.subcutaneousFat + ", 体内水分=" + this.bodyWater + ", 含水量=" + this.waterContent + ", 骨骼肌率=" + this.skeletalMuscleRate + ", 骨量=" + this.boneMass + ", 蛋白质占比=" + this.proteinProportion + ", 时间=" + getTimeBean() + ", 蛋白质量=" + this.proteinMass + ", 基础代谢率=" + this.basalMetabolicRate + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BodyComponent(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        Companion companion = INSTANCE;
        this(Companion.floatScale$default(companion, i / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i2 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i3 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i4 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i5 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i6 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i7 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i8 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i9 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i10 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i11 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i12 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i13 / 10.0f, 0, 2, null), Companion.floatScale$default(companion, i14 / 10.0f, 0, 2, null));
    }
}
