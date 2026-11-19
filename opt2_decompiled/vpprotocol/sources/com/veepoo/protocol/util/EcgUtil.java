package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.FunSwitchFlags;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/EcgUtil.class */
public class EcgUtil {
    public static float convertToMvWithValue(int i, int i2, boolean z, int i3) {
        switch (i2) {
            case 1:
            case 3:
                return i * 1.0f;
            case 2:
                return (i * 1000.0f) / (i3 * 131072);
            case 4:
            case 7:
            case 8:
                return ((i * 1000) * 1.8f) / 1.6777216E8f;
            case 5:
            case 6:
                return i * 0.8f * 0.001f;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                return z ? i / 100.0f : ((i * 1000.0f) * 1.2f) / 4.404019E7f;
            case 10:
            case 12:
            case 14:
                return z ? i / 100.0f : ((i * 1000.0f) * 1.2f) / (i3 * 2097152);
            case 11:
                return z ? i / 100.0f : ((i * 1000) * 1.8f) / 1.6777216E8f;
            case 13:
            default:
                return 0.0f;
        }
    }

    public static int getEcgSignalWithType(int i, int i2) {
        switch (i2) {
            case 2:
                return vp_b(i);
            case 3:
            case 4:
            case 8:
            case 11:
                return vp_a(i);
            case 5:
            case 6:
                return vp_c(i);
            case 7:
            case 13:
            default:
                return Integer.MAX_VALUE;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
            case 10:
            case 14:
                return convert_ecg_value_TI4950(i);
            case 12:
                return i;
        }
    }

    private static int vp_b(int i) {
        return ((i >> 17) & 1) == 0 ? i & 262143 : i | (-262144);
    }

    private static int vp_a(int i) {
        return ((i >> 23) & 1) == 0 ? i & 8388607 : i | (-8388608);
    }

    private static int vp_c(int i) {
        return ((i >> 15) & 1) == 0 ? i & 32767 : i | (-32768);
    }

    public static int convert_ecg_value_TI4950(int i) {
        return ((i >> 21) & 1) == 0 ? i & 2097151 : i | (-2097152);
    }
}
