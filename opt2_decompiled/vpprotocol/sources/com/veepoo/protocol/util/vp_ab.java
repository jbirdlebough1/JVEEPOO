package com.veepoo.protocol.util;

import android.graphics.Color;
import com.veepoo.protocol.model.datas.RRLorenzInfo;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_ab.class */
public abstract class vp_ab {
    public static final double vp_a = (Math.sqrt(2.0d) * 10.0d) * 2.5d;
    private static int[] vp_b = {1, 2, 4, 4, 4, 6};
    private static int[] vp_c = {0, 0, 0, 0, 0, 0, 1};
    private static int vp_d = 0;
    private static final int vp_e = Color.parseColor("#9900CC");
    private static final int vp_f = Color.parseColor("#8800FF");
    private static final int vp_g = Color.parseColor("#FFA500");

    private static RRLorenzInfo vp_b(int[] iArr) {
        int i;
        int i2;
        vp_d = 0;
        RRLorenzInfo rRLorenzInfo = new RRLorenzInfo();
        int length = iArr.length;
        int[][] iArr2 = new int[2201][2201];
        int[] iArr3 = new int[360000];
        if (length < 2000) {
            return rRLorenzInfo;
        }
        System.arraycopy(iArr, 0, iArr3, 0, Math.min(length, 360000));
        int i3 = 0;
        while (true) {
            int i4 = i3;
            int i5 = 0;
            int i6 = length - 2;
            if (i4 >= i6) {
                break;
            }
            int i7 = i4 + 1;
            if (iArr3[i7] <= 2200) {
                int i8 = iArr3[i4];
                if (i8 > 2200) {
                    iArr3[i4] = i8 - 2560;
                }
                int i9 = iArr3[i4];
                if (i9 >= 300 && i9 <= 2200 && (i = iArr3[i7]) >= 300 && i <= 2200 && iArr2[i9][i] != 1) {
                    int i10 = 0;
                    while (i10 < i6) {
                        double dPow = Math.pow(iArr3[i4] - iArr3[i10], 2.0d);
                        int i11 = i10 + 1;
                        i10 = i11;
                        if (Math.sqrt(dPow + Math.pow(iArr3[i7] - iArr3[i11], 2.0d)) <= vp_a) {
                            int i12 = i5 + 1;
                            i5 = i12;
                            int[] iArr4 = vp_b;
                            if (i12 > iArr4[iArr4.length - 1]) {
                                break;
                            }
                        }
                    }
                    int i13 = i5;
                    int[] iArr5 = vp_b;
                    if (i13 <= iArr5[0]) {
                        i2 = vp_c[0];
                    } else if (i5 <= iArr5[1]) {
                        i2 = vp_c[1];
                    } else if (i5 <= iArr5[2]) {
                        i2 = vp_c[2];
                    } else if (i5 <= iArr5[3]) {
                        i2 = vp_c[3];
                    } else if (i5 <= iArr5[4]) {
                        i2 = vp_c[4];
                    } else {
                        i2 = i5 <= iArr5[5] ? vp_c[5] : vp_c[6];
                    }
                    int i14 = iArr3[i4];
                    int i15 = iArr3[i7];
                    boolean z = ((float) i14) / (((float) i15) * 1.0f) >= 2.0f;
                    boolean z2 = ((float) i14) / (((float) i15) * 1.0f) <= 0.5f;
                    if (((!z && !z2) || i2 != 1) && i2 != 0 && iArr2[i14][i15] == 0) {
                        if (vp_d >= 9216) {
                            break;
                        }
                        rRLorenzInfo.rrLorenzX.add(Integer.valueOf(i14));
                        rRLorenzInfo.rrLorenzY.add(Integer.valueOf(iArr3[i7]));
                        vp_d++;
                    }
                    iArr2[iArr3[i4]][iArr3[i7]] = 1;
                }
            }
            i3 = i7;
        }
        return rRLorenzInfo;
    }

    public static RRLorenzInfo vp_a(int[] iArr) {
        return vp_b(iArr);
    }
}
