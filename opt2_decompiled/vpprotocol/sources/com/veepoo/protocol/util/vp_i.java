package com.veepoo.protocol.util;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_i.class */
public abstract class vp_i {
    public static final String vp_a(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        String strVp_d = vp_g.vp_d(bArr);
        Intrinsics.checkNotNullExpressionValue(strVp_d, "byte2HexForShow(...)");
        return strVp_d;
    }
}
