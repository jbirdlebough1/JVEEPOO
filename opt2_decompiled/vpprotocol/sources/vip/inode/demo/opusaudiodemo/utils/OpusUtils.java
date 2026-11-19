package vip.inode.demo.opusaudiodemo.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vip/inode/demo/opusaudiodemo/utils/OpusUtils.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0086 J\u0019\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0086 J)\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0086 J!\u0010\u0012\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u000eH\u0086 J\u0011\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0005H\u0086 J\u0011\u0010\u0015\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\u0005H\u0086 ¨\u0006\u0017"}, d2 = {"Lvip/inode/demo/opusaudiodemo/utils/OpusUtils;", "", "<init>", "()V", "createEncoder", "", "sampleRateInHz", "", "channelConfig", "complexity", "createDecoder", "encode", "handle", "lin", "", "offset", "encoded", "", "decode", "destroyEncoder", "", "destroyDecoder", "Companion", "vpprotocol_release"})
public final class OpusUtils {
    public static final vp_a vp_a = new vp_a(null);
    private static OpusUtils vp_b;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vip/inode/demo/opusaudiodemo/utils/OpusUtils$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<vip.inode.demo.opusaudiodemo.utils.OpusUtils>] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v5 */
        public final OpusUtils vp_a() {
            if (OpusUtils.vp_b == null) {
                ?? r0 = OpusUtils.class;
                synchronized (r0) {
                    try {
                        if (OpusUtils.vp_b == null) {
                            OpusUtils.vp_b = new OpusUtils();
                        }
                        r0 = r0;
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
            }
            OpusUtils opusUtils = OpusUtils.vp_b;
            Intrinsics.checkNotNull(opusUtils);
            return opusUtils;
        }
    }

    public OpusUtils() {
        System.loadLibrary("opusJni");
    }

    public final native long createDecoder(int sampleRateInHz, int channelConfig);

    public final native int decode(long handle, @NotNull byte[] encoded, @NotNull short[] lin);

    public final native void destroyDecoder(long handle);
}
