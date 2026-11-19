package com.veepoo.protocol.util;

import com.veepoo.protocol.model.enums.ESex;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/SportUtil.class */
public class SportUtil {
    public static double getDistance(int i, double d, int i2) {
        return getPositionDouble((((double) i) * getStepLength(d)) / 1000.0d, i2);
    }

    public static double getDistance1(int i, double d) {
        return getDistance(i, d, 1);
    }

    public static double getDistance2(int i, double d) {
        return getDistance(i, d, 2);
    }

    public static double getDistance3(int i, double d) {
        return getDistance(i, d, 3);
    }

    public static double getKcal(int i, double d, int i2, boolean z) {
        return getPositionDouble(z ? getDistance(i, d, 3) * 65.4d : getDistance(i, d, 1) * 65.4d, i2);
    }

    public static int getKcal0(int i, double d, boolean z) {
        return (int) getKcal(i, d, 0, z);
    }

    public static double getKcal1(int i, double d, boolean z) {
        return getKcal(i, d, 1, z);
    }

    public static double getStepLength(double d) {
        double d2;
        if (d < 155.0d) {
            d2 = ((d * 20.0d) / 42.0d) / 100.0d;
        } else {
            d2 = d < 174.0d ? ((d * 13.0d) / 28.0d) / 100.0d : ((d * 19.0d) / 42.0d) / 100.0d;
        }
        return getPositionDouble(d2, 3);
    }

    public static double getBMI(double d, double d2) {
        return getPositionDouble(d2 / ((d / 100.0d) * d2), 1);
    }

    public static int getAimSportCount(ESex eSex, double d, double d2) {
        return vp_a((int) ((vp_a(eSex, d, d2) / getStepLength(d2)) * 1000.0d));
    }

    public static double getAimKcal(ESex eSex, double d, double d2, boolean z) {
        return getKcal1(getAimSportCount(eSex, d, d2), d2, z);
    }

    public static double getAimDistance(ESex eSex, double d, double d2) {
        return getDistance3(getAimSportCount(eSex, d, d2), d2);
    }

    private static double vp_b(ESex eSex, double d, double d2) {
        double d3 = 1.0d;
        double bmi = getBMI(d2, d);
        if (bmi < 19.0d) {
            if (eSex.equals(ESex.MAN)) {
                d3 = d * 77.0d * 0.08d;
            } else if (eSex.equals(ESex.WOMEN)) {
                d3 = d * 77.0d * 0.09d;
            }
        }
        if (19.0d <= bmi && bmi <= 25.0d) {
            if (eSex.equals(ESex.MAN)) {
                d3 = d * 77.0d * 0.1d;
            } else if (eSex.equals(ESex.WOMEN)) {
                d3 = d * 77.0d * 0.15d;
            }
        }
        if (bmi > 25.0d) {
            if (eSex.equals(ESex.MAN)) {
                d3 = d * 77.0d * 0.16d;
            } else if (eSex.equals(ESex.WOMEN)) {
                d3 = d * 77.0d * 0.17d;
            }
        }
        return getPositionDouble(d3, 1);
    }

    private static double vp_a(ESex eSex, double d, double d2) {
        return getPositionDouble(vp_b(eSex, d, d2) / 65.4d, 3);
    }

    public static double getPositionDouble(double d, int i) {
        return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
    }

    private static int vp_a(int i) {
        if (i % 1000 != 0) {
            i = ((i / 1000) + 1) * 1000;
        }
        return i;
    }
}
