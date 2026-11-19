package com.veepoo.protocol.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_e.class */
public abstract class vp_e {
    public static final vp_a vp_a = new vp_a(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_e$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String vp_b(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "byteArray");
            Charset charset = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(charset, "UTF_8");
            return new String(bArr, charset);
        }

        public final int vp_a(byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "byteArray");
            switch (bArr.length) {
                case 0:
                    return 0;
                case 1:
                    return bArr[0];
                case 2:
                    return vp_a(bArr[0], bArr[1]);
                case 3:
                    return vp_a(bArr[0], bArr[1], bArr[2]);
                case 4:
                    return vp_a(bArr[0], bArr[1], bArr[2], bArr[3]);
                default:
                    return Integer.MAX_VALUE;
            }
        }

        private final int vp_a(byte b, byte b2, byte b3, byte b4) {
            return ((int) ((((long) b4) << 24) & 4278190080L)) | ((b3 << 16) & 16711680) | ((b2 << 8) & 65280) | b;
        }

        private final int vp_a(byte b, byte b2, byte b3) {
            return ((b3 << 16) & 16711680) | ((b2 << 8) & 65280) | b;
        }

        public final int vp_a(byte b, byte b2) {
            return ((b2 << 8) & 65280) | (b & 255);
        }
    }
}
