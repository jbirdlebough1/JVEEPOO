package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d.class */
public class vp_d implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_a {
    private final List vp_a = new ArrayList();
    private final Handler vp_b = new Handler(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_a.class */
    class vp_a extends vp_g {
        final /* synthetic */ boolean vp_a;
        final /* synthetic */ int vp_b;
        final /* synthetic */ BluetoothProfile vp_c;

        vp_a(boolean z, int i, BluetoothProfile bluetoothProfile) {
            this.vp_a = z;
            this.vp_b = i;
            this.vp_c = bluetoothProfile;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_d.vp_g
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
            vp_aVar.vp_a(this.vp_a, this.vp_b, this.vp_c);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_b.class */
    class vp_b extends vp_g {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ ParcelUuid[] vp_b;

        vp_b(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
            this.vp_a = bluetoothDevice;
            this.vp_b = parcelUuidArr;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_d.vp_g
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
            vp_aVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_c.class */
    class vp_c extends vp_g {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ int vp_b;

        vp_c(BluetoothDevice bluetoothDevice, int i) {
            this.vp_a = bluetoothDevice;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_d.vp_g
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
            vp_aVar.onA2dpStatus(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.tool.vp_d$vp_d, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_d.class */
    class C0006vp_d extends vp_g {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ int vp_b;

        C0006vp_d(BluetoothDevice bluetoothDevice, int i) {
            this.vp_a = bluetoothDevice;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_d.vp_g
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
            vp_aVar.onHfpStatus(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_e.class */
    class vp_e extends vp_g {
        final /* synthetic */ BluetoothDevice vp_a;
        final /* synthetic */ int vp_b;

        vp_e(BluetoothDevice bluetoothDevice, int i) {
            this.vp_a = bluetoothDevice;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_d.vp_g
        public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
            vp_aVar.vp_a(this.vp_a, this.vp_b);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_f.class */
    private class vp_f implements Runnable {
        private final vp_g vp_a;

        public vp_f(vp_g vp_gVar) {
            this.vp_a = vp_gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.vp_a == null || vp_d.this.vp_a.isEmpty()) {
                return;
            }
            Iterator it = vp_d.this.vp_a.iterator();
            while (it.hasNext()) {
                this.vp_a.vp_a((com.veepoo.protocol.jl.classic.interfaces.listener.vp_a) it.next());
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_d$vp_g.class */
    private static abstract class vp_g {
        private vp_g() {
        }

        public abstract void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar);
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
        if (vp_aVar != null) {
            this.vp_a.add(vp_aVar);
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
    public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
        vp_a(new vp_c(bluetoothDevice, i));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
    public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
        vp_a(new C0006vp_d(bluetoothDevice, i));
    }

    private void vp_a(vp_g vp_gVar) {
        vp_f vp_fVar = new vp_f(vp_gVar);
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            this.vp_b.post(vp_fVar);
        } else {
            vp_fVar.run();
        }
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
    public void vp_a(BluetoothDevice bluetoothDevice, int i) {
        vp_a(new vp_e(bluetoothDevice, i));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
    public void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        vp_a(new vp_b(bluetoothDevice, parcelUuidArr));
    }

    @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
    public void vp_a(boolean z, int i, BluetoothProfile bluetoothProfile) {
        vp_a(new vp_a(z, i, bluetoothProfile));
    }
}
