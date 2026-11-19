package com.veepoo.protocol.util;

import com.veepoo.protocol.model.datas.TimeData;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_f.class */
public abstract class vp_f {
    public static final vp_a vp_a = new vp_a(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_f$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final List vp_c(Integer num, byte b) {
            byte[] bArr = new byte[4];
            bArr[0] = b;
            bArr[1] = 2;
            Intrinsics.checkNotNull(num);
            bArr[2] = num.intValue() > 0 ? (byte) 1 : (byte) 0;
            bArr[3] = VpBleByteUtil.loUint16((short) Math.abs(num.intValue()));
            return ArraysKt.toList(bArr);
        }

        public final List vp_a(Integer num, byte b) {
            Intrinsics.checkNotNull(num);
            return ArraysKt.toList(new byte[]{b, 1, VpBleByteUtil.loUint16((short) num.intValue())});
        }

        public final List vp_b(Integer num, byte b) {
            byte[] bArr = new byte[4];
            bArr[0] = b;
            bArr[1] = 2;
            if (num != null) {
                bArr[2] = VpBleByteUtil.loUint16((short) num.intValue());
            }
            if (num != null) {
                bArr[3] = VpBleByteUtil.hiUint16((short) num.intValue());
            }
            return ArraysKt.toList(bArr);
        }

        public final List vp_b(TimeData timeData, byte b) {
            return timeData == null ? CollectionsKt.emptyList() : ArraysKt.toList(new byte[]{b, 5, VpBleByteUtil.loUint16((short) (timeData.year - 2000)), VpBleByteUtil.loUint16((short) timeData.month), VpBleByteUtil.loUint16((short) timeData.day), VpBleByteUtil.loUint16((short) timeData.hour), VpBleByteUtil.loUint16((short) timeData.minute)});
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v15 */
        /* JADX WARN: Type inference failed for: r0v3, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
        public final List vp_a(String str, byte b) {
            ?? length;
            if (str == null || (length = str.length()) == 0) {
                return CollectionsKt.emptyList();
            }
            try {
                Charset charsetForName = Charset.forName("UTF-8");
                Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
                byte[] bytes = str.getBytes(charsetForName);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                int length2 = bytes.length;
                byte[] bArr = new byte[length2 + 2];
                bArr[0] = b;
                bArr[1] = VpBleByteUtil.loUint16((short) length2);
                for (int i = 0; i < length2; i++) {
                    length = bArr;
                    length[i + 2] = bytes[i];
                }
                return ArraysKt.toList(bArr);
            } catch (UnsupportedEncodingException unused) {
                length.printStackTrace();
                return CollectionsKt.emptyList();
            }
        }

        public final List vp_b(String str, byte b) {
            List listSplit$default = str != null ? StringsKt.split$default(str, new String[]{"-"}, false, 0, 6, (Object) null) : null;
            Integer numValueOf = listSplit$default != null ? Integer.valueOf(listSplit$default.size()) : null;
            Intrinsics.checkNotNull(numValueOf);
            if (numValueOf.intValue() < 2) {
                return CollectionsKt.emptyList();
            }
            List list = listSplit$default;
            return ArraysKt.toList(new byte[]{b, 2, VpBleByteUtil.loUint16((short) VpBleByteUtil.getInterValue((String) list.get(0))), VpBleByteUtil.loUint16((short) VpBleByteUtil.getInterValue((String) list.get(1)))});
        }

        public final List vp_a(Integer num, Integer num2, byte b) {
            byte[] bArr = new byte[6];
            bArr[0] = b;
            bArr[1] = 4;
            Intrinsics.checkNotNull(num);
            bArr[2] = num.intValue() > 0 ? (byte) 1 : (byte) 0;
            bArr[3] = VpBleByteUtil.loUint16((short) Math.abs(num.intValue()));
            Intrinsics.checkNotNull(num2);
            bArr[4] = num2.intValue() > 0 ? (byte) 1 : (byte) 0;
            bArr[5] = VpBleByteUtil.loUint16((short) Math.abs(num2.intValue()));
            return ArraysKt.toList(bArr);
        }

        public final List vp_a(TimeData timeData, byte b) {
            return timeData == null ? CollectionsKt.emptyList() : ArraysKt.toList(new byte[]{b, 3, VpBleByteUtil.loUint16((short) (timeData.year - 2000)), VpBleByteUtil.loUint16((short) timeData.month), VpBleByteUtil.loUint16((short) timeData.day)});
        }
    }
}
