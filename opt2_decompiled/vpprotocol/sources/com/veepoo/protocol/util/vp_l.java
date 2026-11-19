package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.FunSwitchFlags;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_l.class */
public final class vp_l {
    public static final vp_l vp_a = new vp_l();

    private vp_l() {
    }

    private final int vp_c(int i) {
        return ((i >> 17) & 1) == 0 ? i & 262143 : i | (-262144);
    }

    private final int vp_b(int i) {
        return ((i >> 23) & 1) == 0 ? i & 8388607 : i | (-8388608);
    }

    private final int vp_d(int i) {
        return ((i >> 23) & 1) == 0 ? i & 8388607 : i | (-8388608);
    }

    private final int vp_e(int i) {
        return ((i >> 15) & 1) == 0 ? i & 32767 : i | (-32768);
    }

    public final int[] vp_a(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        switch (i) {
            case 1:
                return vp_a(bArr);
            case 2:
                return vp_d(bArr);
            case 3:
                return vp_b(bArr);
            case 4:
            case 8:
            case 11:
                return vp_c(bArr);
            case 5:
            case 6:
                return vp_e(bArr);
            case 7:
            case 13:
            default:
                return vp_a(bArr);
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
            case 10:
            case 12:
            case 14:
                return vp_f(bArr);
        }
    }

    public final int vp_g(int i) {
        return ((i >> 21) & 1) == 0 ? i & 2097151 : i | (-2097152);
    }

    public final int vp_f(int i) {
        int i2 = (i >> 17) & 1;
        if (i2 == 0) {
            return i & 262143;
        }
        if (i2 != 1) {
            return 131072;
        }
        return i | (-262144);
    }

    private final int vp_a(int i) {
        int i2 = 0;
        int i3 = (i >> 21) & 7;
        if (i3 == 0) {
            i2 = i & 2097151;
        } else if (i3 == 1 || i3 == 6) {
            i2 = 2097152;
        } else if (i3 == 7) {
            i2 = i | (-16777216);
        }
        return i2;
    }

    private final int[] vp_d(byte[] bArr) {
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2] + strArrVp_f[i2 + 3], 16);
            if (numValueOf != null && numValueOf.intValue() == 131586) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                Intrinsics.checkNotNull(numValueOf);
                iArr[i] = vp_c(numValueOf.intValue());
            }
        }
        return iArr;
    }

    private final int[] vp_b(byte[] bArr) {
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2] + strArrVp_f[i2 + 3], 16);
            if (numValueOf != null && numValueOf.intValue() == 8421504) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                Intrinsics.checkNotNull(numValueOf);
                iArr[i] = vp_b(numValueOf.intValue());
            }
        }
        return iArr;
    }

    private final int[] vp_c(byte[] bArr) {
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2] + strArrVp_f[i2 + 3], 16);
            if (numValueOf != null && numValueOf.intValue() == 16777215) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                Intrinsics.checkNotNull(numValueOf);
                iArr[i] = vp_d(numValueOf.intValue());
            }
        }
        return iArr;
    }

    private final int[] vp_e(byte[] bArr) {
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2] + strArrVp_f[i2 + 3], 16);
            if (numValueOf != null && numValueOf.intValue() == 8421504) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                Intrinsics.checkNotNull(numValueOf);
                iArr[i] = vp_e(numValueOf.intValue());
            }
        }
        return iArr;
    }

    public final int[] vp_f(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        int length = (bArr.length - 5) / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2] + strArrVp_f[i2 + 3], 16);
            if (numValueOf != null && numValueOf.intValue() == 8421504) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                Intrinsics.checkNotNull(numValueOf);
                iArr[i] = vp_g(numValueOf.intValue());
            }
        }
        return iArr;
    }

    private final int[] vp_a(byte[] bArr) {
        int[] iArr = new int[5];
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        for (int i = 0; i < 5; i++) {
            int i2 = i * 3;
            Integer numValueOf = Integer.valueOf(strArrByte2HexToStrArr[i2 + 1] + strArrByte2HexToStrArr[i2 + 2] + strArrByte2HexToStrArr[i2 + 3], 16);
            Intrinsics.checkNotNull(numValueOf);
            iArr[i] = vp_a(numValueOf.intValue());
        }
        return iArr;
    }
}
