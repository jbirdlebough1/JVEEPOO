package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i.class */
public class vp_i implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_d {
    private final List vp_a = new ArrayList();
    private final Handler vp_b = new Handler(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i$vp_a.class */
    class vp_a extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ UUID vp_b;
        final /* synthetic */ int vp_c;

        vp_a(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
            this.vp_a = bluetoothDevice;
            this.vp_b = uuid;
            this.vp_c = i;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_i.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
            vp_dVar.vp_a(this.vp_a, this.vp_b, this.vp_c);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i$vp_b.class */
    class vp_b extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ UUID vp_b;
        final /* synthetic */ byte[] vp_c;

        vp_b(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr) {
            this.vp_a = bluetoothDevice;
            this.vp_b = uuid;
            this.vp_c = bArr;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_i.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
            vp_dVar.vp_a(this.vp_a, this.vp_b, this.vp_c);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i$vp_c.class */
    class vp_c extends vp_d {
        final /* synthetic */ BluetoothDevice vp_a;

        vp_c(BluetoothDevice bluetoothDevice) {
            this.vp_a = bluetoothDevice;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_i.vp_d
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
            vp_dVar.vp_a(this.vp_a);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i$vp_d.class */
    private static abstract class vp_d {
        private vp_d() {
        }

        public abstract void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar);
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_i$vp_e.class */
    private class vp_e implements Runnable {
        private final vp_d vp_a;

        public vp_e(vp_d vp_dVar) {
            this.vp_a = vp_dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.vp_a == null || vp_i.this.vp_a.isEmpty()) {
                return;
            }
            Iterator it = vp_i.this.vp_a.iterator();
            while (it.hasNext()) {
                this.vp_a.vp_a((com.veepoo.protocol.jl.classic.interfaces.listener.vp_d) it.next());
            }
        }
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
        if (vp_dVar != null) {
            this.vp_a.add(vp_dVar);
        }
    }

    private void vp_a(vp_d vp_dVar) {
        vp_e vp_eVar = new vp_e(vp_dVar);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.vp_b.post(vp_eVar);
        } else {
            vp_eVar.run();
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
    public void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
        vp_a(new vp_a(bluetoothDevice, uuid, i));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
    public void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr) {
        if (bArr != null) {
            vp_a(new vp_b(bluetoothDevice, uuid, bArr));
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
    public void vp_a(BluetoothDevice bluetoothDevice) {
        vp_a(new vp_c(bluetoothDevice));
    }
}
