package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e.class */
public class vp_e implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_c {
    private final List vp_a = new ArrayList();
    private final Handler vp_b = new Handler(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e$vp_a.class */
    class vp_a extends vp_d {
        final /* synthetic */ boolean vp_a;
        final /* synthetic */ boolean vp_b;

        vp_a(boolean z, boolean z2) {
            this.vp_a = z;
            this.vp_b = z2;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_e.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar) {
            vp_cVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e$vp_b.class */
    class vp_b extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ BleScanMessage vp_b;

        vp_b(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
            this.vp_a = bluetoothDevice;
            this.vp_b = bleScanMessage;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_e.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar) {
            vp_cVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e$vp_c.class */
    class vp_c extends vp_d {
        final /* synthetic */ com.veepoo.protocol.jl.classic.vp_f vp_a;

        vp_c(com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
            this.vp_a = vp_fVar;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_e.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar) {
            vp_cVar.vp_a(this.vp_a);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e$vp_d.class */
    private static abstract class vp_d {
        private vp_d() {
        }

        public abstract void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar);
    }

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.tool.vp_e$vp_e, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_e$vp_e.class */
    private class RunnableC0007vp_e implements Runnable {
        private final vp_d vp_a;

        public RunnableC0007vp_e(vp_d vp_dVar) {
            this.vp_a = vp_dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.vp_a == null || vp_e.this.vp_a.isEmpty()) {
                return;
            }
            Iterator it = vp_e.this.vp_a.iterator();
            while (it.hasNext()) {
                this.vp_a.vp_a((com.veepoo.protocol.jl.classic.interfaces.listener.vp_c) it.next());
            }
        }
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar) {
        if (vp_cVar != null) {
            this.vp_a.add(vp_cVar);
        }
    }

    private void vp_a(vp_d vp_dVar) {
        RunnableC0007vp_e runnableC0007vp_e = new RunnableC0007vp_e(vp_dVar);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.vp_b.post(runnableC0007vp_e);
        } else {
            runnableC0007vp_e.run();
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
    public void vp_a(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
        vp_a(new vp_b(bluetoothDevice, bleScanMessage));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
    public void vp_a(com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
        vp_a(new vp_c(vp_fVar));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
    public void vp_a(boolean z, boolean z2) {
        vp_a(new vp_a(z, z2));
    }
}
