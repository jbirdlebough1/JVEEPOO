package com.veepoo.protocol.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_b.class */
public abstract class vp_b {
    public static float vp_b(String str, int i) {
        if (str == null || str.isEmpty()) {
            return 0.0f;
        }
        try {
            return new BigDecimal(str).setScale(i, RoundingMode.DOWN).floatValue();
        } catch (Exception unused) {
            return 0.0f;
        }
    }

    public static double vp_a(String str, int i) {
        if (str == null || str.isEmpty()) {
            return 0.0d;
        }
        try {
            return new BigDecimal(str).setScale(i, RoundingMode.DOWN).doubleValue();
        } catch (Exception unused) {
            return 0.0d;
        }
    }
}
