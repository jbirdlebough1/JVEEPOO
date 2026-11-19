package com.veepoo.protocol.operate;

import android.os.Handler;
import android.os.Looper;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IG15MessageListener;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_at.class */
public final class vp_at extends com.veepoo.protocol.vp_a {
    private Handler vp_c = new Handler(Looper.getMainLooper());
    private vp_a vp_d = new vp_a();
    public IG15MessageListener vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_at$vp_a.class */
    public final class vp_a implements Runnable {
        public vp_a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            vp_at.this.vp_a().onG15MessageSendFailed();
        }
    }

    public final IG15MessageListener vp_a() {
        IG15MessageListener iG15MessageListener = this.vp_e;
        if (iG15MessageListener != null) {
            return iG15MessageListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mG15MessageListener");
        return null;
    }

    public final void vp_b(IG15MessageListener iG15MessageListener) {
        Intrinsics.checkNotNullParameter(iG15MessageListener, "<set-?>");
        this.vp_e = iG15MessageListener;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        byte b;
        super.vp_a(bArr, iListener);
        if (iListener != null) {
            vp_b((IG15MessageListener) iListener);
            if (bArr == null || bArr.length <= 10 || bArr[1] != -2 || (b = bArr[3]) <= 0 || b != bArr[4]) {
                return;
            }
            this.vp_c.removeCallbacks(this.vp_d);
            vp_a().onG15MessageSendSuccess();
        }
    }

    public final void vp_b() {
        this.vp_c.removeCallbacks(this.vp_d);
        this.vp_c.postDelayed(this.vp_d, 500L);
    }

    public final void vp_a(IG15MessageListener iG15MessageListener) {
        Intrinsics.checkNotNullParameter(iG15MessageListener, "listener");
        vp_b(iG15MessageListener);
    }
}
