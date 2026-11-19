package com.veepoo.protocol.util;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_d.class */
public final class vp_d {
    public static final vp_d vp_a = new vp_d();

    private vp_d() {
    }

    public final int vp_b(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "src");
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
    }

    public final int vp_a(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "src");
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24);
    }

    public final long vp_c(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "src");
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24);
    }

    public final void vp_a(byte[] bArr, int i, long j) {
        Intrinsics.checkNotNullParameter(bArr, "dst");
        bArr[i] = (byte) (j & 255);
        bArr[i + 1] = (byte) ((j >> 8) & 255);
        bArr[i + 2] = (byte) ((j >> 16) & 255);
        bArr[i + 3] = (byte) ((j >> 24) & 255);
    }
}
