package com.veepoo.protocol.jl.classic;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.jl.classic.impl.BluetoothBrEdr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e.class */
public class vp_e {
    private final Context vp_a;
    private com.veepoo.protocol.jl.classic.vp_c vp_b;
    private final com.veepoo.protocol.jl.classic.tool.vp_a vp_c;
    private final com.veepoo.protocol.jl.classic.impl.vp_a vp_d;
    private final BluetoothBrEdr vp_e;
    private final com.veepoo.protocol.jl.classic.impl.vp_b vp_f;
    private volatile BluetoothDevice vp_h;
    private volatile BluetoothDevice vp_i;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_l;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_m;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_n;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_o;
    private final com.veepoo.protocol.jl.classic.tool.vp_b vp_g = new com.veepoo.protocol.jl.classic.tool.vp_b();
    private final List vp_j = Collections.synchronizedList(new ArrayList());
    private final Handler vp_k = new Handler(Looper.getMainLooper(), new vp_a());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_a.class */
    class vp_a implements Handler.Callback {
        vp_a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            BluetoothDevice bluetoothDevice;
            int i = message.what;
            if (i != 4112) {
                if (i != 4113 || (bluetoothDevice = (BluetoothDevice) message.obj) == null || !vp_e.this.vp_e(bluetoothDevice)) {
                    return true;
                }
                vp_e.this.vp_g.vp_d(bluetoothDevice, 2);
                return true;
            }
            BluetoothDevice bluetoothDevice2 = (BluetoothDevice) message.obj;
            if (bluetoothDevice2 == null) {
                return true;
            }
            if (!vp_e.this.vp_e(bluetoothDevice2)) {
                new StringBuilder().append("-MSG_CONNECT_DEVICE_TIMEOUT- connect timeout, mDevice : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice2));
                vp_e.this.vp_a(bluetoothDevice2, 0);
                if (vp_e.this.vp_f()) {
                    vp_e.this.vp_b(bluetoothDevice2);
                }
            }
            vp_e.this.vp_i(null);
            return true;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_b.class */
    class vp_b implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_c {
        vp_b() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
        public void vp_a(BluetoothDevice bluetoothDevice, BleScanMessage bleScanMessage) {
            vp_e.this.vp_g.vp_a(bluetoothDevice, bleScanMessage);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
        public void vp_a(com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
            new StringBuilder().append("-onDiscoveryError- ").append(vp_fVar);
            vp_e.this.vp_g.vp_b(true, false);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_c
        public void vp_a(boolean z, boolean z2) {
            vp_e.this.vp_g.vp_b(z, z2);
            if (z2) {
                vp_e.this.vp_a(z);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_c.class */
    class vp_c implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_b {
        vp_c() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void onAdapterStatus(boolean z, boolean z2) {
            vp_e.this.vp_g.vp_a(z, z2);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, int i) {
            vp_e.this.vp_g.vp_b(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
            if (vp_fVar != null) {
                vp_fVar.vp_a(bluetoothDevice);
            }
            vp_e.this.vp_g.vp_a(vp_fVar);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_d.class */
    class vp_d implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_a {
        vp_d() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
            vp_e.this.vp_g.vp_a(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
            vp_e.this.vp_g.vp_e(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(BluetoothDevice bluetoothDevice, int i) {
            vp_e.this.vp_g.vp_c(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
            vp_e.this.vp_g.vp_a(bluetoothDevice, parcelUuidArr);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(boolean z, int i, BluetoothProfile bluetoothProfile) {
        }
    }

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.vp_e$vp_e, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_e.class */
    class C0008vp_e implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_d {
        C0008vp_e() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
        public void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
            vp_e.this.vp_g.vp_f(bluetoothDevice, i);
            new StringBuilder().append("onSppConnection >>>>> ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", uuid = ").append(uuid).append(", state : ").append(i);
            if (i != 1) {
                vp_e.this.vp_a(bluetoothDevice, i);
            }
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
        public void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, byte[] bArr) {
            vp_e.this.vp_g.vp_a(bluetoothDevice, uuid, bArr);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_d
        public void vp_a(BluetoothDevice bluetoothDevice) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/vp_e$vp_f.class */
    private static final class vp_f {
        private static final vp_e vp_a = new vp_e();
    }

    public static vp_e vp_e() {
        return vp_f.vp_a;
    }

    private vp_e() {
        vp_b vp_bVar = new vp_b();
        this.vp_l = vp_bVar;
        vp_c vp_cVar = new vp_c();
        this.vp_m = vp_cVar;
        vp_d vp_dVar = new vp_d();
        this.vp_n = vp_dVar;
        C0008vp_e c0008vp_e = new C0008vp_e();
        this.vp_o = c0008vp_e;
        Context context = VPOperateManager.mContext;
        this.vp_a = context;
        if (this.vp_b == null) {
            this.vp_b = com.veepoo.protocol.jl.classic.vp_c.vp_a();
        }
        this.vp_c = new com.veepoo.protocol.jl.classic.tool.vp_a(context, this.vp_b, vp_bVar);
        com.veepoo.protocol.jl.classic.impl.vp_a vp_aVar = new com.veepoo.protocol.jl.classic.impl.vp_a(context, vp_cVar);
        this.vp_d = vp_aVar;
        BluetoothBrEdr bluetoothBrEdr = new BluetoothBrEdr(context, vp_aVar, vp_dVar);
        this.vp_e = bluetoothBrEdr;
        this.vp_f = new com.veepoo.protocol.jl.classic.impl.vp_b(context, bluetoothBrEdr, this.vp_b, c0008vp_e);
    }

    private void vp_i(BluetoothDevice bluetoothDevice) {
        this.vp_h = bluetoothDevice;
    }

    private void vp_g(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            this.vp_j.remove(bluetoothDevice);
            if (vp_a().isEmpty()) {
                vp_h(null);
            } else if (com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_i, bluetoothDevice)) {
                List list = this.vp_j;
                vp_h((BluetoothDevice) list.get(list.size() - 1));
            }
        }
    }

    private boolean vp_a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        return com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_c()) || com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_b()) || vp_e(bluetoothDevice) || this.vp_j.contains(bluetoothDevice);
    }

    public void vp_h(BluetoothDevice bluetoothDevice) {
        if (com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_i, bluetoothDevice)) {
            return;
        }
        BluetoothDevice bluetoothDevice2 = this.vp_i;
        this.vp_i = bluetoothDevice;
        if (bluetoothDevice2 != null && vp_e(bluetoothDevice)) {
            if (this.vp_f.vp_c(bluetoothDevice)) {
                this.vp_f.vp_e(bluetoothDevice);
            }
            this.vp_g.vp_a(bluetoothDevice);
        }
        new StringBuilder().append("-setConnectedDevice- ConnectedDevice : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(this.vp_i));
    }

    public boolean vp_k(BluetoothDevice bluetoothDevice) {
        return this.vp_d.vp_g(bluetoothDevice);
    }

    public boolean vp_c(BluetoothDevice bluetoothDevice) {
        return this.vp_e.vp_e(bluetoothDevice);
    }

    public boolean vp_f(BluetoothDevice bluetoothDevice) {
        return this.vp_f.vp_c(bluetoothDevice);
    }

    public boolean vp_d(BluetoothDevice bluetoothDevice) {
        return this.vp_f.vp_a(bluetoothDevice);
    }

    public void vp_b(BluetoothDevice bluetoothDevice) {
        new StringBuilder().append("-disconnectBtDevice-- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        if (bluetoothDevice == null) {
            return;
        }
        if (vp_f(bluetoothDevice)) {
            vp_d(bluetoothDevice);
        } else {
            vp_a(bluetoothDevice, 0);
        }
    }

    public boolean vp_j(BluetoothDevice bluetoothDevice) {
        this.vp_e.vp_h(bluetoothDevice);
        return true;
    }

    public boolean vp_f() {
        return this.vp_h != null || this.vp_f.vp_c();
    }

    public List vp_a() {
        return new ArrayList(this.vp_j);
    }

    public boolean vp_e(BluetoothDevice bluetoothDevice) {
        return this.vp_f.vp_c(bluetoothDevice);
    }

    public ArrayList vp_d() {
        return this.vp_c.vp_b();
    }

    public BluetoothDevice vp_b() {
        return this.vp_e.vp_b();
    }

    public BluetoothDevice vp_c() {
        if (this.vp_h == null) {
            this.vp_h = this.vp_f.vp_b();
        }
        return this.vp_h;
    }

    public void vp_g() {
        this.vp_e.vp_a(false);
    }

    private void vp_a(BluetoothDevice bluetoothDevice, int i) {
        boolean zVp_a = vp_a(bluetoothDevice);
        new StringBuilder().append("-notifyConnectionStatus- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", status : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_c(i)).append(", checkIsValidDevice = ").append(zVp_a);
        if (zVp_a) {
            if (i != 1) {
                if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_c())) {
                    vp_i(null);
                    this.vp_k.removeMessages(4112);
                }
                if (i == 0) {
                    vp_g(bluetoothDevice);
                } else if (i == 2) {
                    if (!this.vp_j.contains(bluetoothDevice)) {
                        this.vp_j.add(bluetoothDevice);
                    }
                    if (this.vp_i == null) {
                        vp_h(bluetoothDevice);
                    }
                }
            }
            new StringBuilder().append("-notifyConnectionStatus- onConnection >>> device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", status : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_c(i));
            this.vp_g.vp_d(bluetoothDevice, i);
        }
    }

    private void vp_a(boolean z) {
        List<BluetoothDevice> listVp_a = z ? com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_a) : com.veepoo.protocol.jl.classic.vp_d.vp_b(this.vp_a);
        if (listVp_a != null) {
            String strVp_c = this.vp_b.vp_c();
            for (BluetoothDevice bluetoothDevice : listVp_a) {
                if (!vp_e(bluetoothDevice) && !vp_d().contains(bluetoothDevice) && ((z && (bluetoothDevice.getType() == 2 || bluetoothDevice.getType() == 3)) || (bluetoothDevice.getName() != null && strVp_c != null && bluetoothDevice.getName().startsWith(strVp_c)))) {
                    BleScanMessage bleScanMessage = new BleScanMessage();
                    bleScanMessage.setEnableConnect(true);
                    this.vp_g.vp_a(bluetoothDevice, bleScanMessage);
                }
            }
        }
    }
}
