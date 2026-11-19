package com.veepoo.protocol.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/InstitutionUtil.class */
public class InstitutionUtil {
    public static double kmToLBKM(double d, int i) {
        return SportUtil.getPositionDouble(d * 0.6213799715042114d, i);
    }

    public static double LBkmTokm(double d) {
        return getPositionDoubleUP(d / 0.6213799715042114d, 1);
    }

    public static double kmToLBKM1(double d) {
        return kmToLBKM(d, 1);
    }

    public static double kmToLBKM2(double d) {
        return kmToLBKM(d, 2);
    }

    public static float kgToLBKG(double d) {
        return (float) getPositionDoubleUP(d * 2.2046000957489014d, 1);
    }

    public static float lbkgToKG(double d) {
        return (float) getPositionDoubleUP(d / 2.2046000957489014d, 1);
    }

    public static int cmToLBCM(double d) {
        return (int) getPositionDoubleUP(d * 0.39399999380111694d, 0);
    }

    public static int lbcmToCM(double d) {
        return (int) getPositionDoubleUP(d / 0.39399999380111694d, 0);
    }

    public static double getPositionDoubleUP(double d, int i) {
        return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
    }
}
