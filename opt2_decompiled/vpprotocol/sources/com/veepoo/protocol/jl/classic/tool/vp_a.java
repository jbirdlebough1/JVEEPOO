package com.veepoo.protocol.jl.classic.tool;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jieli.jl_bt_ota.model.BleScanMessage;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_a.class */
public class vp_a {
    private final Context vp_c;
    private final vp_e vp_d;
    private BluetoothAdapter vp_e;
    private vp_d vp_f;
    private BluetoothLeScanner vp_g;
    private final com.veepoo.protocol.jl.classic.vp_c vp_h;
    private int vp_i;
    private final List vp_a = new ArrayList();
    private final List vp_b = new ArrayList();
    private volatile boolean vp_j = false;
    private long vp_k = 8000;
    private final Handler vp_l = new Handler(Looper.getMainLooper(), new C0004vp_a());
    private final BluetoothAdapter.LeScanCallback vp_m = new vp_b();
    private final ScanCallback vp_n = new vp_c();

    /* JADX INFO: renamed from: com.veepoo.protocol.jl.classic.tool.vp_a$vp_a, reason: collision with other inner class name */
    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_a$vp_a.class */
    class C0004vp_a implements Handler.Callback {
        C0004vp_a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1011) {
                if (!vp_a.this.vp_j) {
                    return false;
                }
                vp_a.this.vp_d();
                return false;
            }
            if (i != 1022 || !vp_a.this.vp_e.isDiscovering()) {
                return false;
            }
            vp_a.this.vp_f();
            return false;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_a$vp_b.class */
    class vp_b implements BluetoothAdapter.LeScanCallback {
        vp_b() {
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            vp_a.this.vp_a(bluetoothDevice, i, bArr, true);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_a$vp_c.class */
    class vp_c extends ScanCallback {
        vp_c() {
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List list) {
            super.onBatchScanResults(list);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            if (scanResult == null || scanResult.getScanRecord() == null) {
                return;
            }
            BluetoothDevice device = scanResult.getDevice();
            boolean zIsConnectable = true;
            if (Build.VERSION.SDK_INT >= 26) {
                zIsConnectable = scanResult.isConnectable();
            }
            vp_a.this.vp_a(device, scanResult.getRssi(), scanResult.getScanRecord().getBytes(), zIsConnectable);
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i) {
            super.onScanFailed(i);
            vp_a.this.vp_d.vp_a(new com.veepoo.protocol.jl.classic.vp_f(i, "scan ble error."));
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/tool/vp_a$vp_d.class */
    private class vp_d extends BroadcastReceiver {
        private vp_d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent == null || !"android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED".equals(intent.getAction()) || (intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0)) == 0 || intExtra != 10) {
                return;
            }
            vp_a.this.vp_l.removeCallbacksAndMessages(null);
            vp_a.this.vp_j = false;
            vp_a.this.vp_a.clear();
            vp_a.this.vp_b.clear();
            vp_a.this.vp_g();
        }
    }

    public vp_a(Context context, com.veepoo.protocol.jl.classic.vp_c vp_cVar, com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar2) {
        this.vp_c = context;
        this.vp_h = vp_cVar == null ? com.veepoo.protocol.jl.classic.vp_c.vp_a() : vp_cVar;
        this.vp_d = new vp_e();
        vp_a(vp_cVar2);
        this.vp_e = BluetoothAdapter.getDefaultAdapter();
        vp_a();
        vp_c();
    }

    private void vp_c() {
        if (this.vp_f == null) {
            this.vp_f = new vp_d();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            if (Build.VERSION.SDK_INT >= 33) {
                this.vp_c.registerReceiver(this.vp_f, intentFilter, 2);
            } else {
                this.vp_c.registerReceiver(this.vp_f, intentFilter);
            }
        }
    }

    private void vp_g() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v12, types: [android.bluetooth.le.BluetoothLeScanner] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [android.bluetooth.le.BluetoothLeScanner] */
    /* JADX WARN: Type inference failed for: r0v8, types: [android.bluetooth.BluetoothAdapter] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    private void vp_e() {
        ?? Vp_a;
        if (this.vp_j) {
            this.vp_j = false;
            if (!com.veepoo.protocol.jl.classic.vp_d.vp_b()) {
                return;
            }
            if (Build.VERSION.SDK_INT < 21 || (Vp_a = vp_a()) == 0) {
                ?? r0 = this.vp_e;
                if (r0 != 0) {
                    try {
                        r0 = r0;
                        r0.stopLeScan(this.vp_m);
                    } catch (Exception unused) {
                        r0.printStackTrace();
                    }
                }
            } else {
                try {
                    Vp_a = this.vp_g;
                    Vp_a.stopScan(this.vp_n);
                } catch (Exception unused2) {
                    Vp_a.printStackTrace();
                }
            }
        }
        this.vp_l.removeMessages(1011);
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_c vp_cVar) {
        this.vp_d.vp_a(vp_cVar);
    }

    public ArrayList vp_b() {
        ArrayList arrayList;
        if (this.vp_i == 1) {
            arrayList = arrayList;
            ArrayList arrayList2 = new ArrayList(this.vp_b);
        } else {
            arrayList = arrayList;
            ArrayList arrayList3 = new ArrayList(this.vp_a);
        }
        return arrayList;
    }

    public boolean vp_f() {
        BluetoothAdapter bluetoothAdapter = this.vp_e;
        if (bluetoothAdapter == null) {
            return false;
        }
        if (!bluetoothAdapter.isDiscovering()) {
            return true;
        }
        if (!this.vp_e.isEnabled()) {
            vp_g();
            return true;
        }
        boolean zCancelDiscovery = this.vp_e.cancelDiscovery();
        new StringBuilder().append("-cancelDiscovery- >>>>>> bRet = ").append(zCancelDiscovery);
        if (!zCancelDiscovery) {
            return false;
        }
        this.vp_l.removeMessages(1022);
        return true;
    }

    public boolean vp_d() {
        if (this.vp_e == null) {
            return false;
        }
        if (!this.vp_j) {
            return true;
        }
        vp_e();
        vp_a(true, false);
        return true;
    }

    private void vp_a(BluetoothDevice bluetoothDevice, int i, byte[] bArr, boolean z) {
        int iVp_a;
        if (bluetoothDevice != null) {
            if (this.vp_h.vp_b() != 0) {
                BleScanMessage bleScanMessage = new BleScanMessage();
                if (TextUtils.isEmpty(bluetoothDevice.getName()) || !com.veepoo.protocol.jl.classic.vp_d.vp_b()) {
                    return;
                }
                if (bleScanMessage.isEnableConnect() && !z) {
                    bleScanMessage.setEnableConnect(false);
                }
                bleScanMessage.setRawData(bArr).setRssi(i);
                if (this.vp_a.contains(bluetoothDevice)) {
                    return;
                }
                this.vp_a.add(bluetoothDevice);
                this.vp_d.vp_a(bluetoothDevice, bleScanMessage);
                return;
            }
            if (!com.veepoo.protocol.jl.classic.vp_d.vp_b() || TextUtils.isEmpty(bluetoothDevice.getName()) || this.vp_a.contains(bluetoothDevice)) {
                return;
            }
            if (bArr != null && bArr.length > 2) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    int i4 = i3 + 2;
                    if (i4 > bArr.length || (iVp_a = vp_g.vp_a(bArr[i3])) <= 1 || iVp_a >= bArr.length) {
                        break;
                    }
                    if (vp_g.vp_a(bArr[i3 + 1]) == 255) {
                        int i5 = iVp_a - 1;
                        System.arraycopy(bArr, i4, new byte[i5], 0, i5);
                    }
                    i2 = i3 + iVp_a + 1;
                }
            }
            BleScanMessage bleScanMessage2 = new BleScanMessage();
            this.vp_a.add(bluetoothDevice);
            this.vp_d.vp_a(bluetoothDevice, bleScanMessage2.setRawData(bArr).setRssi(i).setEnableConnect(z));
        }
    }

    private BluetoothLeScanner vp_a() {
        BluetoothAdapter bluetoothAdapter;
        if (Build.VERSION.SDK_INT >= 21 && (bluetoothAdapter = this.vp_e) != null && this.vp_g == null) {
            this.vp_g = bluetoothAdapter.getBluetoothLeScanner();
        }
        return this.vp_g;
    }

    private void vp_a(boolean z, boolean z2) {
        new StringBuilder().append("-notifyDiscoveryStatus- bBle : ").append(z).append(" ,bStart : ").append(z2);
        int i = this.vp_i;
        if (i == 0 && z) {
            this.vp_d.vp_a(true, z2);
        } else if (i == 1 && !z) {
            this.vp_d.vp_a(false, z2);
        }
        if (z2) {
            return;
        }
        this.vp_j = false;
        this.vp_i = 0;
    }
}
