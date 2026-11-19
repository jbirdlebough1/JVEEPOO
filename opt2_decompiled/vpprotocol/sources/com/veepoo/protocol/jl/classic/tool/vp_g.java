package com.veepoo.protocol.jl.classic.tool;

import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_g.class */
public abstract class vp_g {
    private static final char[] vp_a = "0123456789ABCDEF".toCharArray();

    public static String vp_a(byte[] bArr) {
        return bArr == null ? "" : vp_a(bArr, bArr.length);
    }

    public static String vp_a(byte[] bArr, int i) {
        if (bArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            char[] cArr = vp_a;
            sb.append(cArr[(bArr[i2] & 255) >> 4]);
            sb.append(cArr[bArr[i2] & 15]);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static int vp_a(byte b) {
        return b & 255;
    }
}
