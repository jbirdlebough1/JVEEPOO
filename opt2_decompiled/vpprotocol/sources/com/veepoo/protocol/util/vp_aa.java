package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.QH15HealthData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_aa.class */
public final class vp_aa {
    public static final vp_aa vp_a = new vp_aa();

    private vp_aa() {
    }

    private final void vp_a(ByteArrayOutputStream byteArrayOutputStream, int i, int i2, byte[] bArr) throws IOException {
        byteArrayOutputStream.write(i);
        byteArrayOutputStream.write(i2);
        byteArrayOutputStream.write(bArr);
    }

    public final byte[] vp_a(QH15HealthData qH15HealthData) throws IOException {
        Intrinsics.checkNotNullParameter(qH15HealthData, "data");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        vp_a(byteArrayOutputStream, 16, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAge())});
        vp_a(byteArrayOutputStream, 17, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAge())});
        vp_a(byteArrayOutputStream, 18, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAge())});
        vp_a(byteArrayOutputStream, 19, 1, new byte[]{qH15HealthData.getBiosAgeStatus().getTag()});
        vp_a(byteArrayOutputStream, 20, 1, new byte[]{qH15HealthData.getHeartAgeStatus().getTag()});
        vp_a(byteArrayOutputStream, 21, 1, new byte[]{qH15HealthData.getFitnessAgeStatus().getTag()});
        vp_a(byteArrayOutputStream, 22, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAgeChange())});
        vp_a(byteArrayOutputStream, 23, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAgeChange())});
        vp_a(byteArrayOutputStream, 24, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAgeChange())});
        vp_a(byteArrayOutputStream, 25, 90, vp_a(qH15HealthData.getBiosAge90Days(), 90));
        vp_a(byteArrayOutputStream, 26, 90, vp_a(qH15HealthData.getHeartAge90Days(), 90));
        vp_a(byteArrayOutputStream, 27, 90, vp_a(qH15HealthData.getFitnessAge90Days(), 90));
        vp_a(byteArrayOutputStream, 28, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAgeLastMonth())});
        vp_a(byteArrayOutputStream, 29, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAgeLastMonth())});
        vp_a(byteArrayOutputStream, 30, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAgeLastMonth())});
        vp_a(byteArrayOutputStream, 31, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAgeLastYear())});
        vp_a(byteArrayOutputStream, 32, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAgeLastYear())});
        vp_a(byteArrayOutputStream, 33, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAgeLastYear())});
        vp_a(byteArrayOutputStream, 34, 1, new byte[]{qH15HealthData.getBiosAgeLastMonthStatus().getTag()});
        vp_a(byteArrayOutputStream, 35, 1, new byte[]{qH15HealthData.getHeartAgeLastMonthStatus().getTag()});
        vp_a(byteArrayOutputStream, 36, 1, new byte[]{qH15HealthData.getFitnessAgeLastMonthStatus().getTag()});
        vp_a(byteArrayOutputStream, 37, 1, new byte[]{qH15HealthData.getBiosAgeLastYearStatus().getTag()});
        vp_a(byteArrayOutputStream, 38, 1, new byte[]{qH15HealthData.getHeartAgeLastYearStatus().getTag()});
        vp_a(byteArrayOutputStream, 39, 1, new byte[]{qH15HealthData.getFitnessAgeLastYearStatus().getTag()});
        vp_a(byteArrayOutputStream, 40, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAgeLastMonthChange())});
        vp_a(byteArrayOutputStream, 41, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAgeLastMonthChange())});
        vp_a(byteArrayOutputStream, 42, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAgeLastMonthChange())});
        vp_a(byteArrayOutputStream, 43, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getBiosAgeLastYearChange())});
        vp_a(byteArrayOutputStream, 44, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAgeLastYearChange())});
        vp_a(byteArrayOutputStream, 45, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFitnessAgeLastYearChange())});
        vp_a(byteArrayOutputStream, 48, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getCardiovascularRisk())});
        vp_a(byteArrayOutputStream, 49, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getDementiaRisk())});
        vp_a(byteArrayOutputStream, 50, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getDiabetesRisk())});
        vp_a(byteArrayOutputStream, 51, 1, new byte[]{qH15HealthData.getCardiovascularRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 52, 1, new byte[]{qH15HealthData.getDementiaRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 53, 1, new byte[]{qH15HealthData.getDiabetesRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 54, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getCardiovascularRiskChange())});
        vp_a(byteArrayOutputStream, 55, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getDementiaRiskChange())});
        vp_a(byteArrayOutputStream, 56, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getDiabetesRiskChange())});
        vp_a(byteArrayOutputStream, 57, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAttackRisk())});
        vp_a(byteArrayOutputStream, 58, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getStrokeRisk())});
        vp_a(byteArrayOutputStream, 59, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartFailureRisk())});
        vp_a(byteArrayOutputStream, 60, 1, new byte[]{qH15HealthData.getHeartAttackRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 61, 1, new byte[]{qH15HealthData.getStrokeRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 62, 1, new byte[]{qH15HealthData.getHeartFailureRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 63, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartAttackRiskChange())});
        vp_a(byteArrayOutputStream, 64, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getStrokeRiskChange())});
        vp_a(byteArrayOutputStream, 65, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getHeartFailureRiskChange())});
        vp_a(byteArrayOutputStream, 66, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getMemoryLossRisk())});
        vp_a(byteArrayOutputStream, 67, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFallInjuryRisk())});
        vp_a(byteArrayOutputStream, 68, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getLoseIndependentRisk())});
        vp_a(byteArrayOutputStream, 69, 1, new byte[]{qH15HealthData.getMemoryLossRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 70, 1, new byte[]{qH15HealthData.getFallInjuryRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 71, 1, new byte[]{qH15HealthData.getLoseIndependentRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 72, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getMemoryLossRiskChange())});
        vp_a(byteArrayOutputStream, 73, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getFallInjuryRiskChange())});
        vp_a(byteArrayOutputStream, 74, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getLoseIndependentRiskChange())});
        vp_a(byteArrayOutputStream, 75, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getKidneyDiseaseRisk())});
        vp_a(byteArrayOutputStream, 76, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getNerveDamageRisk())});
        vp_a(byteArrayOutputStream, 77, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getVisionLossRisk())});
        vp_a(byteArrayOutputStream, 78, 1, new byte[]{qH15HealthData.getKidneyDiseaseRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 79, 1, new byte[]{qH15HealthData.getNerveDamageRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 80, 1, new byte[]{qH15HealthData.getVisionLossRiskStatus().getTag()});
        vp_a(byteArrayOutputStream, 81, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getKidneyDiseaseRiskChange())});
        vp_a(byteArrayOutputStream, 82, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getNerveDamageRiskChange())});
        vp_a(byteArrayOutputStream, 83, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getVisionLossRiskChange())});
        vp_a(byteArrayOutputStream, 84, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getNutritionStatus())});
        vp_a(byteArrayOutputStream, 85, 1, new byte[]{UByte.constructor-impl((byte) qH15HealthData.getGoalStatus())});
        vp_a(byteArrayOutputStream, 161, 4, vp_a(qH15HealthData.getTimestamp()));
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bArrVp_a = vp_a((short) byteArray.length);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        byteArrayOutputStream2.write(bArrVp_a);
        byteArrayOutputStream2.write(byteArray);
        byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray2, "toByteArray(...)");
        return byteArray2;
    }

    private final byte[] vp_a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)};
    }

    private final byte[] vp_a(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s >> 8) & 255)};
    }

    private final byte[] vp_a(int[] iArr, int i) {
        byte[] bArr = new byte[i];
        int iCoerceAtMost = RangesKt.coerceAtMost(i, iArr.length);
        for (int i2 = 0; i2 < iCoerceAtMost; i2++) {
            bArr[i2] = UByte.constructor-impl((byte) iArr[i2]);
        }
        return bArr;
    }
}
