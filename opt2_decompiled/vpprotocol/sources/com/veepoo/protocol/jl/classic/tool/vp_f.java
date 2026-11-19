package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f.class */
public class vp_f implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_b {
    private final List vp_a = new ArrayList();
    private final Handler vp_b = new Handler(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f$vp_a.class */
    class vp_a extends vp_d {
        final /* synthetic */ boolean vp_a;
        final /* synthetic */ boolean vp_b;

        vp_a(boolean z, boolean z2) {
            this.vp_a = z;
            this.vp_b = z2;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_f.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
            vp_bVar.onAdapterStatus(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f$vp_b.class */
    class vp_b extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ int vp_b;

        vp_b(BluetoothDevice bluetoothDevice, int i) {
            this.vp_a = bluetoothDevice;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_f.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
            vp_bVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f$vp_c.class */
    class vp_c extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ com.veepoo.protocol.jl.classic.vp_f vp_b;

        vp_c(BluetoothDevice bluetoothDevice, com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
            this.vp_a = bluetoothDevice;
            this.vp_b = vp_fVar;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_f.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
            vp_bVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f$vp_d.class */
    private static abstract class vp_d {
        private vp_d() {
        }

        public abstract void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar);
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_f$vp_e.class */
    private class vp_e implements Runnable {
        private final vp_d vp_a;

        public vp_e(vp_d vp_dVar) {
            this.vp_a = vp_dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.vp_a == null || vp_f.this.vp_a.isEmpty()) {
                return;
            }
            Iterator it = vp_f.this.vp_a.iterator();
            while (it.hasNext()) {
                this.vp_a.vp_a((com.veepoo.protocol.jl.classic.interfaces.listener.vp_b) it.next());
            }
        }
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
        if (vp_bVar != null) {
            this.vp_a.add(vp_bVar);
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
    public void onAdapterStatus(boolean z, boolean z2) {
        vp_a(new vp_a(z, z2));
    }

    private void vp_a(vp_d vp_dVar) {
        vp_e vp_eVar = new vp_e(vp_dVar);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.vp_b.post(vp_eVar);
        } else {
            vp_eVar.run();
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
    public void vp_a(BluetoothDevice bluetoothDevice, int i) {
        vp_a(new vp_b(bluetoothDevice, i));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
    public void vp_a(BluetoothDevice bluetoothDevice, com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
        vp_a(new vp_c(bluetoothDevice, vp_fVar));
    }
}
