package com.veepoo.protocol.jl.classic.impl;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.jl.classic.tool.vp_f;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_a.class */
public class vp_a {
    public static int vp_g = 0;
    private final Context vp_a;
    private volatile BluetoothDevice vp_c;
    private vp_b vp_d;
    private vp_c vp_e;
    private final Handler vp_f = new Handler(Looper.getMainLooper(), new C0002vp_a());
    private final vp_f vp_b = new vp_f();

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.impl.vp_a$vp_a, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_a$vp_a.class */
    class C0002vp_a implements Handler.Callback {
        C0002vp_a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            BluetoothDevice bluetoothDevice;
            if (message.what != 1014 || (bluetoothDevice = (BluetoothDevice) message.obj) == null) {
                return false;
            }
            vp_a.this.vp_i(bluetoothDevice);
            vp_a.this.vp_a(bluetoothDevice, new com.veepoo.protocol.jl.classic.vp_f(4, bluetoothDevice.getAddress()));
            return false;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_a$vp_b.class */
    private class vp_b extends BroadcastReceiver {
        private vp_b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if (TextUtils.isEmpty(action)) {
                    return;
                }
                action.hashCode();
                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    if (intExtra != -1) {
                        if (intExtra == 10) {
                            vp_a.this.vp_c();
                            vp_a vp_aVar = vp_a.this;
                            vp_aVar.vp_a(vp_aVar.vp_c, new com.veepoo.protocol.jl.classic.vp_f(2, "bluetooth adapter is close."));
                            vp_a vp_aVar2 = vp_a.this;
                            vp_aVar2.vp_e(vp_aVar2.vp_c);
                        }
                        vp_a.this.vp_b.onAdapterStatus(intExtra == 12, com.veepoo.protocol.jl.classic.vp_d.vp_c(vp_a.this.vp_a));
                    }
                } else if (!action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
                    return;
                }
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice != null) {
                    int bondState = bluetoothDevice.getBondState();
                    new StringBuilder().append("recv action :ACTION_BOND_STATE_CHANGED ... device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(" ,bound : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_b(bondState));
                    if (bondState == 10 || bondState == 12) {
                        vp_a.this.vp_e(bluetoothDevice);
                        vp_a.this.vp_i(bluetoothDevice);
                    }
                    vp_a.this.vp_b.vp_a(bluetoothDevice, bondState);
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_a$vp_c.class */
    private class vp_c extends Thread {
        private boolean vp_a;
        private boolean vp_b;
        private boolean vp_c;
        private final LinkedBlockingQueue vp_d;
        private BluetoothDevice vp_e;

        private vp_c() {
            super("PairBtDeviceThread");
            this.vp_d = new LinkedBlockingQueue();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Throwable, java.util.concurrent.LinkedBlockingQueue] */
        private boolean vp_a(vp_d vp_dVar) {
            boolean z = false;
            if (vp_dVar != 0) {
                try {
                    this.vp_d.put(vp_dVar);
                    z = true;
                } catch (InterruptedException unused) {
                    vp_dVar.printStackTrace();
                }
            }
            if (z && this.vp_b && !this.vp_c) {
                this.vp_b = false;
                ?? r0 = this.vp_d;
                synchronized (r0) {
                    try {
                        this.vp_d.notify();
                    } catch (Throwable th) {
                        throw r0;
                    }
                }
            }
            return z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v18, types: [com.veepoo.protocol.jl.classic.impl.vp_a$vp_c] */
        /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.concurrent.LinkedBlockingQueue] */
        /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Object, java.util.concurrent.LinkedBlockingQueue] */
        /* JADX WARN: Type inference failed for: r0v21 */
        /* JADX WARN: Type inference failed for: r0v29, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v38, types: [com.veepoo.protocol.jl.classic.impl.vp_a$vp_c] */
        /* JADX WARN: Type inference failed for: r0v39, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v40, types: [java.lang.Object, java.util.concurrent.LinkedBlockingQueue] */
        /* JADX WARN: Type inference failed for: r0v7 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.vp_a = true;
            ?? Poll = this.vp_d;
            synchronized (Poll) {
                while (this.vp_a) {
                    try {
                        this.vp_c = false;
                        this.vp_e = null;
                        if (this.vp_d.isEmpty()) {
                            this.vp_b = true;
                            Poll = this;
                            try {
                                Poll = Poll.vp_d;
                                Poll.wait();
                            } catch (InterruptedException unused) {
                                Poll.printStackTrace();
                            }
                        } else {
                            this.vp_b = false;
                            vp_d vp_dVar = (vp_d) this.vp_d.peek();
                            if (vp_dVar != null) {
                                this.vp_e = vp_dVar.vp_a();
                                boolean zVp_b = vp_dVar.vp_b() == 0 ? vp_a.this.vp_b(this.vp_e) : vp_a.this.vp_h(this.vp_e);
                                new StringBuilder().append("-PairBtDeviceThread- do task ret : ").append(zVp_b);
                                if (zVp_b) {
                                    this.vp_c = true;
                                    ?? r0 = this;
                                    try {
                                        r0 = r0.vp_d;
                                        r0.wait(30000L);
                                    } catch (InterruptedException unused2) {
                                        r0.printStackTrace();
                                    }
                                } else {
                                    vp_a vp_aVar = vp_a.this;
                                    BluetoothDevice bluetoothDevice = this.vp_e;
                                    vp_aVar.vp_a(bluetoothDevice, new com.veepoo.protocol.jl.classic.vp_f(3, bluetoothDevice == null ? "" : bluetoothDevice.getAddress()));
                                }
                            }
                            Poll = this.vp_d.poll();
                        }
                    } catch (Throwable unused3) {
                        throw Poll;
                    }
                }
                Poll = Poll;
                this.vp_d.clear();
                vp_a.this.vp_e = null;
            }
        }

        private void vp_a() {
            this.vp_a = false;
            this.vp_e = null;
            vp_a((BluetoothDevice) null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.veepoo.protocol.jl.classic.impl.vp_a$vp_c] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v9 */
        private void vp_a(BluetoothDevice bluetoothDevice) {
            LinkedBlockingQueue linkedBlockingQueue;
            if (bluetoothDevice == null || com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_e, bluetoothDevice)) {
                ?? r0 = this;
                LinkedBlockingQueue linkedBlockingQueue2 = r0.vp_d;
                synchronized (linkedBlockingQueue2) {
                    try {
                        if (r0.vp_c) {
                            if (this.vp_b) {
                                this.vp_d.notifyAll();
                            } else {
                                linkedBlockingQueue = this.vp_d;
                                linkedBlockingQueue.notify();
                            }
                        } else if (this.vp_b) {
                            linkedBlockingQueue = this.vp_d;
                            linkedBlockingQueue.notify();
                        }
                        r0 = linkedBlockingQueue2;
                    } catch (Throwable unused) {
                        throw r0;
                    }
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/vp_a$vp_d.class */
    public static class vp_d {
        private final int vp_a;
        private final BluetoothDevice vp_b;

        public vp_d(int i, BluetoothDevice bluetoothDevice) {
            this.vp_a = i;
            this.vp_b = bluetoothDevice;
        }

        public int vp_b() {
            return this.vp_a;
        }

        public BluetoothDevice vp_a() {
            return this.vp_b;
        }

        public String toString() {
            return "PairTask{mOp=" + this.vp_a + ", mDevice=" + this.vp_b + '}';
        }
    }

    public vp_a(Context context, com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
        this.vp_a = context;
        vp_a(vp_bVar);
        vp_a();
        vp_b();
    }

    private void vp_c(BluetoothDevice bluetoothDevice) {
        this.vp_c = bluetoothDevice;
    }

    private void vp_d(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            vp_a(null, new com.veepoo.protocol.jl.classic.vp_f(1, "param is error."));
        } else {
            if (this.vp_f.hasMessages(1014)) {
                vp_a(bluetoothDevice, new com.veepoo.protocol.jl.classic.vp_f(5, "device is pairing."));
                return;
            }
            Message messageObtainMessage = this.vp_f.obtainMessage(1014, bluetoothDevice);
            vp_c(bluetoothDevice);
            this.vp_f.sendMessageDelayed(messageObtainMessage, 30000L);
        }
    }

    private void vp_e(BluetoothDevice bluetoothDevice) {
        if (this.vp_f.hasMessages(1014) && com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_c)) {
            this.vp_f.removeMessages(1014);
            vp_c(null);
        }
    }

    private void vp_i(BluetoothDevice bluetoothDevice) {
        vp_c vp_cVar = this.vp_e;
        if (vp_cVar != null) {
            vp_cVar.vp_a(bluetoothDevice);
        }
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_bVar) {
        this.vp_b.vp_a(vp_bVar);
    }

    public boolean vp_b(BluetoothDevice bluetoothDevice) {
        int i;
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zVp_a = false;
        if (VPOperateManager.isUseDefaultBTType) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_e(bluetoothDevice) && Build.VERSION.SDK_INT >= 23) {
                zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, 1);
                new StringBuilder().append("-pair- createBond 双模配对 PAIR_TYPE:0 TRANSPORT_BR-EDR ret = ").append(zVp_a).append(" type = ").append(bluetoothDevice.getType());
            }
            if (!zVp_a) {
                zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice);
                new StringBuilder().append("-pair- createBond 单模系统默认配对 PAIR_TYPE:0 ret = ").append(zVp_a).append(" type = ").append(bluetoothDevice.getType());
            }
        } else {
            int i2 = VPOperateManager.customBTConnectType;
            vp_g = i2;
            if (i2 == 0) {
                if (com.veepoo.protocol.jl.classic.vp_d.vp_e(bluetoothDevice) && (i = Build.VERSION.SDK_INT) >= 23) {
                    zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, 1);
                    new StringBuilder().append("-pair- createBond 双模配对 PAIR_TYPE:0 TRANSPORT_BR-EDR ret = ").append(zVp_a).append(" type = ").append(bluetoothDevice.getType()).append("mode").append(com.veepoo.protocol.jl.classic.vp_d.vp_e(bluetoothDevice)).append("ver").append(i);
                }
                if (!zVp_a) {
                    zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice);
                    new StringBuilder().append("-pair- createBond 单模系统默认配对 PAIR_TYPE:0 ret = ").append(zVp_a).append(" type = ").append(bluetoothDevice.getType());
                }
            } else {
                zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice);
                new StringBuilder().append("-pair- createBond 单模系统默认配对 PAIR_TYPE:1 ret = ").append(zVp_a).append(" type = ").append(bluetoothDevice.getType());
            }
        }
        if (!zVp_a) {
            return false;
        }
        vp_d(bluetoothDevice);
        return true;
    }

    public boolean vp_h(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        boolean zVp_g = com.veepoo.protocol.jl.classic.vp_d.vp_g(bluetoothDevice);
        new StringBuilder().append("-unPair- result : ").append(zVp_g);
        if (!zVp_g) {
            return false;
        }
        vp_d(bluetoothDevice);
        return true;
    }

    public boolean vp_f(BluetoothDevice bluetoothDevice) {
        boolean zVp_a = false;
        if (bluetoothDevice != null) {
            zVp_a = vp_a(new vp_d(0, bluetoothDevice));
        }
        return zVp_a;
    }

    public boolean vp_g(BluetoothDevice bluetoothDevice) {
        boolean zVp_a = false;
        if (bluetoothDevice != null) {
            zVp_a = vp_a(new vp_d(1, bluetoothDevice));
        }
        return zVp_a;
    }

    private void vp_b() {
        if (this.vp_e == null) {
            vp_c vp_cVar = new vp_c();
            this.vp_e = vp_cVar;
            vp_cVar.start();
        }
    }

    private boolean vp_a(vp_d vp_dVar) {
        boolean zVp_a = false;
        if (vp_dVar != null) {
            vp_b();
            zVp_a = this.vp_e.vp_a(vp_dVar);
        }
        return zVp_a;
    }

    private void vp_c() {
        vp_c vp_cVar = this.vp_e;
        if (vp_cVar != null) {
            vp_cVar.vp_a();
        }
    }

    public boolean vp_a(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && 12 == bluetoothDevice.getBondState();
    }

    private void vp_a(BluetoothDevice bluetoothDevice, com.veepoo.protocol.jl.classic.vp_f vp_fVar) {
        this.vp_b.vp_a(bluetoothDevice, vp_fVar);
    }

    private void vp_a() {
        if (this.vp_d == null) {
            this.vp_d = new vp_b();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            if (Build.VERSION.SDK_INT >= 33) {
                this.vp_a.registerReceiver(this.vp_d, intentFilter, 2);
            } else {
                this.vp_a.registerReceiver(this.vp_d, intentFilter);
            }
        }
    }
}
