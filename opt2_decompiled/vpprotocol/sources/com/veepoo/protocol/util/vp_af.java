package com.veepoo.protocol.util;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_af.class */
public abstract class vp_af {
    public static final StringBuilder vp_a(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "str");
        StringBuilder sbAppend = sb.append("\n").append("--------------------------->|").append(str);
        Intrinsics.checkNotNullExpressionValue(sbAppend, "append(...)");
        return sbAppend;
    }
}
