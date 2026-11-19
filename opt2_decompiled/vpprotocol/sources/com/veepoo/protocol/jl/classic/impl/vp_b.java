package com.veepoo.protocol.jl.classic.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import com.veepoo.protocol.jl.classic.tool.vp_g;
import com.veepoo.protocol.jl.classic.tool.vp_h;
import com.veepoo.protocol.jl.classic.tool.vp_i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b.class */
public class vp_b {
    private final Context vp_e;
    private final com.veepoo.protocol.jl.classic.impl.vp_a vp_f;
    private final BluetoothBrEdr vp_g;
    private final vp_i vp_h;
    private com.veepoo.protocol.jl.classic.vp_c vp_i;
    private volatile BluetoothDevice vp_j;
    private volatile BluetoothDevice vp_k;
    private vp_f vp_l;
    private vp_e vp_m;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_o;
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_p;
    private final vp_h.vp_a vp_q;
    private final List vp_a = Collections.synchronizedList(new ArrayList());
    private final Map vp_b = Collections.synchronizedMap(new HashMap());
    private final Map vp_c = Collections.synchronizedMap(new HashMap());
    private final ExecutorService vp_d = Executors.newFixedThreadPool(8);
    private final Handler vp_n = new Handler(Looper.getMainLooper(), new vp_a());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_a.class */
    class vp_a implements Handler.Callback {
        vp_a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 41013:
                    new StringBuilder().append("-MSG_CONNECT_SPP_TIMEOUT-  mConnectingSppDevice : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(vp_b.this.vp_k));
                    if (vp_b.this.vp_k != null) {
                        vp_b vp_bVar = vp_b.this;
                        if (!vp_bVar.vp_c(vp_bVar.vp_k)) {
                            vp_b vp_bVar2 = vp_b.this;
                            vp_bVar2.vp_b(vp_bVar2.vp_k, 0);
                        }
                    }
                    break;
                case 41014:
                    new StringBuilder().append("-MSG_CONNECT_SPP-  device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(vp_b.this.vp_k));
                    if (vp_b.this.vp_k != null) {
                        vp_b vp_bVar3 = vp_b.this;
                        vp_bVar3.vp_g(vp_bVar3.vp_k);
                    }
                    break;
                case 41015:
                    new StringBuilder().append("-MSG_WAIT_SYSTEM_CONNECT_EDR_TIMEOUT-  device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(vp_b.this.vp_k));
                    if (vp_b.this.vp_k != null) {
                        vp_b.this.vp_n.sendEmptyMessage(41014);
                    }
                    break;
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.impl.vp_b$vp_b, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_b.class */
    class C0003vp_b implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_b {
        C0003vp_b() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void onAdapterStatus(boolean z, boolean z2) {
            if (z) {
                return;
            }
            vp_b.this.vp_a();
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, int i) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_b.this.vp_k)) {
                new StringBuilder().append("spp >> -onBtDeviceBond- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", status : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_c(i));
                if (i != 12) {
                    if (i == 10) {
                        vp_b.this.vp_b(bluetoothDevice, 0);
                        return;
                    }
                    return;
                }
                vp_b.this.vp_n.removeMessages(41013);
                vp_b.this.vp_n.sendEmptyMessageDelayed(41013, 30000L);
                if (!com.veepoo.protocol.jl.classic.vp_d.vp_b(bluetoothDevice) && !com.veepoo.protocol.jl.classic.vp_d.vp_c(bluetoothDevice)) {
                    vp_b.this.vp_n.sendEmptyMessage(41014);
                } else {
                    vp_b.this.vp_n.removeMessages(41015);
                    vp_b.this.vp_n.sendEmptyMessageDelayed(41015, 3000L);
                }
            }
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_b.this.vp_k)) {
                vp_b.this.vp_b(bluetoothDevice, 0);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_c.class */
    class vp_c implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_a {
        vp_c() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void onA2dpStatus(BluetoothDevice bluetoothDevice, int i) {
            vp_b.this.vp_a(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void onHfpStatus(BluetoothDevice bluetoothDevice, int i) {
            vp_b.this.vp_a(bluetoothDevice, i);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(BluetoothDevice bluetoothDevice, int i) {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_a
        public void vp_a(boolean z, int i, BluetoothProfile bluetoothProfile) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_d.class */
    class vp_d implements vp_h.vp_a {
        vp_d() {
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_h.vp_a
        public void vp_a(long j, BluetoothDevice bluetoothDevice, byte[] bArr) {
            String.format(Locale.CHINA, "-onRecvSppData- data [ %s ], device : %s.", vp_g.vp_a(bArr), bluetoothDevice);
            vp_b vp_bVar = vp_b.this;
            vp_bVar.vp_h.vp_a(bluetoothDevice, vp_bVar.vp_i.vp_d(), bArr);
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_h.vp_a
        public void vp_a(long j) {
        }

        @Override // com.veepoo.protocol.jl.classic.tool.vp_h.vp_a
        public void vp_a(long j, int i, BluetoothDevice bluetoothDevice) {
            new StringBuilder().append("-onThreadStop- >> reason : ").append(i);
            vp_b.this.vp_a(bluetoothDevice);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_e.class */
    private class vp_e extends BroadcastReceiver {
        private vp_e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice bluetoothDevice;
            if (intent != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action) || !"android.bluetooth.device.action.UUID".equals(action) || (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
                    return;
                }
                Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                if (parcelableArrayExtra == null) {
                    vp_b.this.vp_a(bluetoothDevice, (ParcelUuid[]) null);
                    return;
                }
                ParcelUuid[] parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                for (int i = 0; i < parcelableArrayExtra.length; i++) {
                    parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                    new StringBuilder().append("-BluetoothSppReceiver- onReceive: ACTION_UUID ").append(parcelUuidArr[i].toString());
                }
                vp_b.this.vp_a(bluetoothDevice, parcelUuidArr);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_b$vp_f.class */
    private class vp_f extends Thread {
        private final BluetoothDevice vp_a;

        private vp_f(BluetoothDevice bluetoothDevice) {
            super("ConnectionThread");
            this.vp_a = bluetoothDevice;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public synchronized void run() {
            new StringBuilder().append("spp connect start. mDevice : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(this.vp_a)).append(", uuid : ").append(vp_b.this.vp_i.vp_d());
            BluetoothDevice bluetoothDevice = this.vp_a;
            if (bluetoothDevice != null) {
                int i = 0;
                if (!com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, vp_b.this.vp_i.vp_d())) {
                    new StringBuilder().append("device no spp uuid. ").append(vp_b.this.vp_i.vp_d());
                }
                BluetoothSocket bluetoothSocket = null;
                try {
                    BluetoothSocket bluetoothSocketCreateRfcommSocketToServiceRecord = this.vp_a.createRfcommSocketToServiceRecord(vp_b.this.vp_i.vp_d());
                    bluetoothSocket = bluetoothSocketCreateRfcommSocketToServiceRecord;
                    bluetoothSocketCreateRfcommSocketToServiceRecord.connect();
                    i = 2;
                } catch (Exception e) {
                    e.printStackTrace();
                    new StringBuilder().append("spp connect occurred exception : ").append(e.getMessage());
                }
                if (2 != i) {
                    new StringBuilder().append("spp connect failed. ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(this.vp_a));
                    vp_b.this.vp_b(this.vp_a, i);
                    vp_b.this.vp_l = null;
                    return;
                }
                new StringBuilder().append("spp connect ok. ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(this.vp_a));
                if (vp_b.this.vp_j == null) {
                    vp_b.this.vp_e(this.vp_a);
                }
                if (!vp_b.this.vp_a.contains(this.vp_a)) {
                    vp_b.this.vp_a.add(this.vp_a);
                    vp_b.this.vp_b.put(this.vp_a.getAddress(), bluetoothSocket);
                    vp_b.this.vp_a(this.vp_a, bluetoothSocket);
                }
                vp_b.this.vp_b(this.vp_a, 2);
            }
            vp_b.this.vp_l = null;
        }
    }

    public vp_b(Context context, BluetoothBrEdr bluetoothBrEdr, com.veepoo.protocol.jl.classic.vp_c vp_cVar, com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
        C0003vp_b c0003vp_b = new C0003vp_b();
        this.vp_o = c0003vp_b;
        vp_c vp_cVar2 = new vp_c();
        this.vp_p = vp_cVar2;
        this.vp_q = new vp_d();
        this.vp_e = context;
        this.vp_g = bluetoothBrEdr;
        com.veepoo.protocol.jl.classic.impl.vp_a vp_aVarVp_a = bluetoothBrEdr.vp_a();
        this.vp_f = vp_aVarVp_a;
        bluetoothBrEdr.vp_a(vp_cVar2);
        vp_aVarVp_a.vp_a(c0003vp_b);
        this.vp_h = new vp_i();
        this.vp_i = vp_cVar == null ? com.veepoo.protocol.jl.classic.vp_c.vp_a() : vp_cVar;
        vp_a(vp_dVar);
        vp_d();
    }

    private void vp_d() {
        if (this.vp_m == null) {
            this.vp_m = new vp_e();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.UUID");
            if (Build.VERSION.SDK_INT >= 33) {
                this.vp_e.registerReceiver(this.vp_m, intentFilter, 2);
            } else {
                this.vp_e.registerReceiver(this.vp_m, intentFilter);
            }
        }
    }

    private void vp_g(BluetoothDevice bluetoothDevice) {
        new StringBuilder().append("-startConnectSpp- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", mConnectionThread : ").append(this.vp_l);
        if (bluetoothDevice == null || this.vp_l != null) {
            return;
        }
        vp_f vp_fVar = new vp_f(bluetoothDevice);
        this.vp_l = vp_fVar;
        vp_fVar.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.bluetooth.BluetoothSocket] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.bluetooth.BluetoothSocket] */
    private boolean vp_b(BluetoothDevice bluetoothDevice) {
        new StringBuilder().append("-disconnectSpp- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        if (bluetoothDevice == null) {
            return false;
        }
        if (!this.vp_a.contains(bluetoothDevice)) {
            return true;
        }
        ?? r0 = (BluetoothSocket) this.vp_b.remove(bluetoothDevice.getAddress());
        if (r0 != 0) {
            try {
                r0 = r0;
                r0.close();
            } catch (Exception unused) {
                r0.printStackTrace();
            }
        }
        vp_h vp_hVar = (vp_h) this.vp_c.remove(bluetoothDevice.getAddress());
        if (vp_hVar != null) {
            vp_hVar.vp_a();
        }
        this.vp_a.remove(bluetoothDevice);
        return true;
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_d vp_dVar) {
        this.vp_h.vp_a(vp_dVar);
    }

    public void vp_f(BluetoothDevice bluetoothDevice) {
        this.vp_k = bluetoothDevice;
    }

    public void vp_e(BluetoothDevice bluetoothDevice) {
        if (com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_j, bluetoothDevice)) {
            return;
        }
        this.vp_j = bluetoothDevice;
        if (bluetoothDevice != null) {
            this.vp_h.vp_a(bluetoothDevice);
        }
    }

    public boolean vp_c(BluetoothDevice bluetoothDevice) {
        BluetoothSocket bluetoothSocket;
        if (bluetoothDevice == null || (bluetoothSocket = (BluetoothSocket) this.vp_b.get(bluetoothDevice.getAddress())) == null) {
            return false;
        }
        return bluetoothSocket.isConnected();
    }

    private void vp_d(BluetoothDevice bluetoothDevice) {
        if (this.vp_a.isEmpty()) {
            vp_e(null);
        } else if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_j)) {
            List list = this.vp_a;
            vp_e((BluetoothDevice) list.get(list.size() - 1));
        }
    }

    private void vp_a(BluetoothDevice bluetoothDevice, int i) {
        if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_k)) {
            if (i == 1) {
                this.vp_n.removeMessages(41015);
            } else {
                this.vp_n.removeMessages(41014);
                this.vp_n.sendEmptyMessageDelayed(41014, 1000L);
            }
        }
    }

    public boolean vp_c() {
        return this.vp_k != null;
    }

    public BluetoothDevice vp_b() {
        return this.vp_k;
    }

    private void vp_a() {
        Iterator it = this.vp_a.iterator();
        while (it.hasNext()) {
            vp_b((BluetoothDevice) it.next());
        }
        this.vp_b.clear();
        this.vp_c.clear();
        this.vp_a.clear();
        vp_f(null);
        vp_e(null);
    }

    private void vp_b(BluetoothDevice bluetoothDevice, int i) {
        vp_a(bluetoothDevice, this.vp_i.vp_d(), i);
    }

    public boolean vp_a(BluetoothDevice bluetoothDevice) {
        new StringBuilder().append("-disconnectSPPDevice- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        boolean zVp_b = vp_b(bluetoothDevice);
        if (zVp_b) {
            vp_b(bluetoothDevice, 0);
        }
        return zVp_b;
    }

    private void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_k)) {
            this.vp_n.sendEmptyMessage(41014);
        }
    }

    private void vp_a(BluetoothDevice bluetoothDevice, UUID uuid, int i) {
        new StringBuilder().append("-notifySppState- status ： ").append(com.veepoo.protocol.jl.classic.vp_d.vp_c(i)).append(", device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        if (i != 1) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_k, bluetoothDevice)) {
                vp_f(null);
                this.vp_n.removeMessages(41013);
            }
            if (i == 0) {
                vp_d(bluetoothDevice);
            }
        }
        this.vp_h.vp_a(bluetoothDevice, uuid, i);
    }

    private void vp_a(BluetoothDevice bluetoothDevice, BluetoothSocket bluetoothSocket) {
        new StringBuilder().append("-startReceiveDataThread- socket : ").append(bluetoothSocket);
        vp_h vp_hVar = new vp_h(bluetoothDevice, bluetoothSocket, this.vp_q);
        if (this.vp_d.isShutdown()) {
            return;
        }
        this.vp_d.submit(vp_hVar);
        this.vp_c.put(bluetoothDevice.getAddress(), vp_hVar);
    }
}
