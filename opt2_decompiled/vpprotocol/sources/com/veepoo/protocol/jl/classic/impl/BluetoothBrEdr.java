package com.veepoo.protocol.jl.classic.impl;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
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
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.jl.classic.vp_f;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr.class */
public class BluetoothBrEdr {
    private static final String vp_s = BluetoothBrEdr.class.getSimpleName() + "【经典蓝牙】";
    private BluetoothAdapter vp_a;
    private BluetoothHeadset vp_b;
    private BluetoothA2dp vp_c;
    private final Context vp_d;
    private final com.veepoo.protocol.jl.classic.impl.vp_a vp_e;
    private final com.veepoo.protocol.jl.classic.tool.vp_d vp_f;
    private volatile boolean vp_g;
    private volatile boolean vp_h;
    private volatile BluetoothDevice vp_i;
    private vp_d vp_j;
    private final Handler vp_k = new Handler(Looper.getMainLooper(), new vp_a());
    private final BluetoothProfile.ServiceListener vp_l = new vp_b();
    private final com.veepoo.protocol.jl.classic.interfaces.listener.vp_b vp_m;
    private int vp_n;
    private BluetoothDevice vp_o;
    private BluetoothDevice vp_p;
    private CBTDiscoveredBroadcastReceiver vp_q;
    private boolean vp_r;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr$CBTDiscoveredBroadcastReceiver.class */
    public class CBTDiscoveredBroadcastReceiver extends BroadcastReceiver {
        private CBTDiscoveredBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CBTDiscoveredBroadcastReceiver cBTDiscoveredBroadcastReceiver;
            String action = intent.getAction();
            new StringBuilder().append("-Classic-BT扫描- #onReceive# action = ").append(action);
            if ("android.bluetooth.device.action.FOUND".equals(intent.getAction())) {
                BluetoothBrEdr.this.vp_o = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                new StringBuilder().append("-Classic-BT扫描- #onReceive# 扫描到当前的设备：").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(BluetoothBrEdr.this.vp_o));
                BluetoothDevice bluetoothDevice = BluetoothBrEdr.this.vp_o;
                if (bluetoothDevice == null || bluetoothDevice.getAddress() == null) {
                    return;
                }
                BluetoothBrEdr bluetoothBrEdr = BluetoothBrEdr.this;
                if (bluetoothBrEdr.vp_p == null || !bluetoothBrEdr.vp_o.getAddress().equals(BluetoothBrEdr.this.vp_p.getAddress())) {
                    return;
                }
                BluetoothBrEdr bluetoothBrEdr2 = BluetoothBrEdr.this;
                bluetoothBrEdr2.vp_r = true;
                bluetoothBrEdr2.vp_n = 0;
                if (bluetoothBrEdr2.vp_a.isDiscovering()) {
                    BluetoothBrEdr.this.vp_a.cancelDiscovery();
                }
                BluetoothBrEdr bluetoothBrEdr3 = BluetoothBrEdr.this;
                Context context2 = bluetoothBrEdr3.vp_d;
                if (context2 != null && (cBTDiscoveredBroadcastReceiver = bluetoothBrEdr3.vp_q) != null) {
                    context2.unregisterReceiver(cBTDiscoveredBroadcastReceiver);
                    BluetoothBrEdr.this.vp_q = null;
                }
                new StringBuilder().append("-Classic-BT扫描- #onReceive# 扫描到【目标设备】：").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(BluetoothBrEdr.this.vp_o));
                BluetoothBrEdr bluetoothBrEdr4 = BluetoothBrEdr.this;
                bluetoothBrEdr4.vp_a(bluetoothBrEdr4.vp_o);
                return;
            }
            if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(intent.getAction())) {
                return;
            }
            if (!"android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(intent.getAction())) {
                new StringBuilder().append("-Classic-BT扫描- #onReceive# return:").append(action);
                return;
            }
            BluetoothBrEdr bluetoothBrEdr5 = BluetoothBrEdr.this;
            BluetoothDevice bluetoothDevice2 = bluetoothBrEdr5.vp_o;
            if (bluetoothDevice2 == null || bluetoothBrEdr5.vp_p == null) {
                BluetoothBrEdr bluetoothBrEdr6 = BluetoothBrEdr.this;
                bluetoothBrEdr6.vp_a(bluetoothBrEdr6.vp_p);
                return;
            }
            if (bluetoothDevice2.getAddress().equals(BluetoothBrEdr.this.vp_p.getAddress())) {
                return;
            }
            new StringBuilder().append("-Classic-BT扫描- #onReceive# 扫描结束结束 第").append(BluetoothBrEdr.this.vp_n).append("次扫描没有扫描到指定设备");
            BluetoothBrEdr bluetoothBrEdr7 = BluetoothBrEdr.this;
            int i = bluetoothBrEdr7.vp_n + 1;
            bluetoothBrEdr7.vp_n = i;
            if (i % 2 != 0) {
                BluetoothBrEdr bluetoothBrEdr8 = BluetoothBrEdr.this;
                bluetoothBrEdr8.vp_h(bluetoothBrEdr8.vp_p);
                return;
            }
            bluetoothBrEdr7.vp_n = 0;
            BluetoothBrEdr bluetoothBrEdr9 = BluetoothBrEdr.this;
            bluetoothBrEdr9.vp_a(bluetoothBrEdr9.vp_p);
            if (BluetoothBrEdr.this.vp_a.isDiscovering()) {
                BluetoothBrEdr.this.vp_a.cancelDiscovery();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr$vp_a.class */
    class vp_a implements Handler.Callback {
        vp_a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what != 26145) {
                return false;
            }
            new StringBuilder().append("MSG_CONNECT_EDR_TIMEOUT , connectingEdr : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(BluetoothBrEdr.this.vp_i));
            if (BluetoothBrEdr.this.vp_i == null) {
                return false;
            }
            BluetoothBrEdr bluetoothBrEdr = BluetoothBrEdr.this;
            if (bluetoothBrEdr.vp_k(bluetoothBrEdr.vp_i) != 2) {
                BluetoothBrEdr bluetoothBrEdr2 = BluetoothBrEdr.this;
                bluetoothBrEdr2.vp_b(bluetoothBrEdr2.vp_i, 0);
            }
            BluetoothBrEdr.this.vp_l(null);
            return false;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr$vp_b.class */
    class vp_b implements BluetoothProfile.ServiceListener {
        vp_b() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            new StringBuilder().append("------------onServiceConnected--------profile=").append(i);
            if (2 == i) {
                BluetoothBrEdr bluetoothBrEdr = BluetoothBrEdr.this;
                bluetoothBrEdr.vp_c = (BluetoothA2dp) bluetoothProfile;
                bluetoothBrEdr.vp_g = false;
            } else if (1 == i) {
                BluetoothBrEdr bluetoothBrEdr2 = BluetoothBrEdr.this;
                bluetoothBrEdr2.vp_b = (BluetoothHeadset) bluetoothProfile;
                bluetoothBrEdr2.vp_h = false;
            }
            BluetoothBrEdr.this.vp_f.vp_a(true, i, bluetoothProfile);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (2 == i) {
                BluetoothBrEdr bluetoothBrEdr = BluetoothBrEdr.this;
                bluetoothBrEdr.vp_c = null;
                bluetoothBrEdr.vp_g = false;
            } else if (1 == i) {
                BluetoothBrEdr bluetoothBrEdr2 = BluetoothBrEdr.this;
                bluetoothBrEdr2.vp_b = null;
                bluetoothBrEdr2.vp_h = false;
            }
            BluetoothBrEdr.this.vp_f.vp_a(false, i, null);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr$vp_c.class */
    class vp_c implements com.veepoo.protocol.jl.classic.interfaces.listener.vp_b {
        vp_c() {
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void onAdapterStatus(boolean z, boolean z2) {
            if (z || BluetoothBrEdr.this.vp_i == null) {
                return;
            }
            BluetoothBrEdr.this.vp_k.removeMessages(26145);
            BluetoothBrEdr.this.vp_k.sendEmptyMessage(26145);
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, int i) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, BluetoothBrEdr.this.vp_i)) {
                String.format(Locale.CHINA, "-onBtDeviceBond- device : [%s], status : %d", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), Integer.valueOf(i));
                if (i == 10) {
                    BluetoothBrEdr.this.vp_b(bluetoothDevice, 0);
                    return;
                }
                if (i == 12) {
                    if (bluetoothDevice.getType() != 1 || VPOperateManager.isFindJLService()) {
                        BluetoothBrEdr.this.vp_d(bluetoothDevice);
                    }
                    BluetoothBrEdr.this.vp_k.removeMessages(26145);
                    BluetoothBrEdr.this.vp_k.sendEmptyMessageDelayed(26145, 30000L);
                }
            }
        }

        @Override // com.veepoo.protocol.jl.classic.interfaces.listener.vp_b
        public void vp_a(BluetoothDevice bluetoothDevice, vp_f vp_fVar) {
            if (com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, BluetoothBrEdr.this.vp_i)) {
                String.format(Locale.CHINA, "-onPairError- device : [%s], error : %s", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), vp_fVar);
                BluetoothBrEdr.this.vp_b(bluetoothDevice, 0);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/jl/classic/impl/BluetoothBrEdr$vp_d.class */
    private class vp_d extends BroadcastReceiver {
        private vp_d() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code duplicated, block: B:45:0x0151  */
        /* JADX WARN: Code duplicated, block: B:47:0x015b  */
        /* JADX WARN: Code duplicated, block: B:50:0x016b A[LOOP:0: B:48:0x0164->B:50:0x016b, LOOP_END] */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v13, types: [com.veepoo.protocol.jl.classic.impl.BluetoothBrEdr] */
        /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v18, types: [com.veepoo.protocol.jl.classic.impl.BluetoothBrEdr] */
        /* JADX WARN: Type inference failed for: r0v49 */
        /* JADX WARN: Type inference failed for: r0v50 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable[] parcelableArrayExtra;
            ParcelUuid[] parcelUuidArr;
            int i;
            if (intent != null) {
                String action = intent.getAction();
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (TextUtils.isEmpty(action) || bluetoothDevice == null) {
                    return;
                }
                action.hashCode();
                char c = -1;
                switch (action.hashCode()) {
                    case -855499628:
                        if (action.equals("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED")) {
                            c = 0;
                        }
                        break;
                    case -377527494:
                        if (action.equals("android.bluetooth.device.action.UUID")) {
                            c = 1;
                        }
                        break;
                    case 545516589:
                        if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 2;
                        }
                        break;
                    case 1244161670:
                        if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                            c = 3;
                        }
                        break;
                }
                ?? r0 = c;
                ?? Append = r0;
                switch (r0) {
                    case 0:
                        Append = new StringBuilder().append("A2DP  ACTION_PLAYING_STATE_CHANGED : ").append(intent.getIntExtra("android.bluetooth.profile.extra.STATE", 11));
                        try {
                            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            new StringBuilder().append("onReceive: hfp ACTION_CONNECTION_STATE_CHANGED device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", state : ").append(intExtra);
                            Append = BluetoothBrEdr.this;
                            Append.vp_c(bluetoothDevice, intExtra);
                        } catch (Exception unused) {
                            Append.printStackTrace();
                        }
                        parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, (ParcelUuid[]) null);
                        } else {
                            parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                            for (i = 0; i < parcelableArrayExtra.length; i++) {
                                parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                                new StringBuilder().append("onReceive: ACTION_UUID ").append(parcelUuidArr[i].toString());
                            }
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, parcelUuidArr);
                        }
                        break;
                    case 1:
                        parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, (ParcelUuid[]) null);
                        } else {
                            parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                            while (i < parcelableArrayExtra.length) {
                                parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                                new StringBuilder().append("onReceive: ACTION_UUID ").append(parcelUuidArr[i].toString());
                            }
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, parcelUuidArr);
                        }
                        break;
                    case 2:
                        int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        new StringBuilder().append("onReceive: hfp ACTION_CONNECTION_STATE_CHANGED device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", state : ").append(intExtra2);
                        Append = BluetoothBrEdr.this;
                        Append.vp_c(bluetoothDevice, intExtra2);
                        parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, (ParcelUuid[]) null);
                        } else {
                            parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                            while (i < parcelableArrayExtra.length) {
                                parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                                new StringBuilder().append("onReceive: ACTION_UUID ").append(parcelUuidArr[i].toString());
                            }
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, parcelUuidArr);
                        }
                        break;
                    case 3:
                        try {
                            int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                            new StringBuilder().append("onReceive: a2dp ACTION_CONNECTION_STATE_CHANGED device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", state : ").append(intExtra3);
                            if (intExtra3 != -1) {
                                r0 = BluetoothBrEdr.this;
                                r0.vp_a(bluetoothDevice, intExtra3);
                                break;
                            }
                        } catch (Exception unused2) {
                            r0.printStackTrace();
                        }
                        Append = new StringBuilder().append("A2DP  ACTION_PLAYING_STATE_CHANGED : ").append(intent.getIntExtra("android.bluetooth.profile.extra.STATE", 11));
                        int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                        new StringBuilder().append("onReceive: hfp ACTION_CONNECTION_STATE_CHANGED device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(", state : ").append(intExtra4);
                        Append = BluetoothBrEdr.this;
                        Append.vp_c(bluetoothDevice, intExtra4);
                        parcelableArrayExtra = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                        if (parcelableArrayExtra == null) {
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, (ParcelUuid[]) null);
                        } else {
                            parcelUuidArr = new ParcelUuid[parcelableArrayExtra.length];
                            while (i < parcelableArrayExtra.length) {
                                parcelUuidArr[i] = ParcelUuid.fromString(parcelableArrayExtra[i].toString());
                                new StringBuilder().append("onReceive: ACTION_UUID ").append(parcelUuidArr[i].toString());
                            }
                            BluetoothBrEdr.this.vp_a(bluetoothDevice, parcelUuidArr);
                        }
                        break;
                }
            }
        }
    }

    public BluetoothBrEdr(Context context, com.veepoo.protocol.jl.classic.impl.vp_a vp_aVar, com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar2) {
        vp_c vp_cVar = new vp_c();
        this.vp_m = vp_cVar;
        this.vp_n = 0;
        this.vp_o = null;
        this.vp_p = null;
        this.vp_q = new CBTDiscoveredBroadcastReceiver();
        this.vp_r = false;
        this.vp_d = context;
        this.vp_e = vp_aVar;
        vp_aVar.vp_a(vp_cVar);
        this.vp_f = new com.veepoo.protocol.jl.classic.tool.vp_d();
        vp_a(vp_aVar2);
        vp_a(context);
        vp_d();
    }

    private void vp_l(BluetoothDevice bluetoothDevice) {
        this.vp_i = bluetoothDevice;
    }

    public void vp_a(com.veepoo.protocol.jl.classic.interfaces.listener.vp_a vp_aVar) {
        this.vp_f.vp_a(vp_aVar);
    }

    public int vp_k(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return 0;
        }
        if (this.vp_b == null || this.vp_c == null) {
            vp_a(this.vp_d);
            return 0;
        }
        if (bluetoothDevice.getType() == 2 && !VPOperateManager.isFindJLService()) {
            return 0;
        }
        Iterator<BluetoothDevice> it = this.vp_c.getConnectedDevices().iterator();
        while (it.hasNext()) {
            if (it.next().getAddress().equals(bluetoothDevice.getAddress())) {
                return 2;
            }
        }
        Iterator<BluetoothDevice> it2 = this.vp_b.getConnectedDevices().iterator();
        while (it2.hasNext()) {
            if (it2.next().getAddress().equals(bluetoothDevice.getAddress())) {
                return 2;
            }
        }
        return 0;
    }

    public int vp_i(BluetoothDevice bluetoothDevice) {
        BluetoothA2dp bluetoothA2dp;
        if (bluetoothDevice == null || (bluetoothA2dp = this.vp_c) == null) {
            return 0;
        }
        List<BluetoothDevice> connectedDevices = bluetoothA2dp.getConnectedDevices();
        if (connectedDevices != null && !connectedDevices.isEmpty()) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(bluetoothDevice.getAddress())) {
                    return 2;
                }
            }
        }
        int connectionState = this.vp_c.getConnectionState(bluetoothDevice);
        new StringBuilder().append("-isConnectedByA2dp- ret : ").append(connectionState);
        return connectionState;
    }

    public boolean vp_d(BluetoothDevice bluetoothDevice) {
        if ((bluetoothDevice == null || bluetoothDevice.getType() == 2) && !VPOperateManager.isFindJLService()) {
            return false;
        }
        new StringBuilder().append("-connectByProfiles- : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        boolean zVp_c = false;
        int iVp_i = vp_i(bluetoothDevice);
        new StringBuilder().append("-connectByProfiles- deviceA2dpStatus : ").append(iVp_i);
        if (iVp_i == 0) {
            boolean zVp_b = vp_b(bluetoothDevice);
            zVp_c = zVp_b;
            new StringBuilder().append("-connectByProfiles- connectByA2dp  ret : ").append(zVp_c);
            if (zVp_b) {
                return true;
            }
        } else if (iVp_i == 2 || iVp_i == 1) {
            zVp_c = true;
        }
        if (com.veepoo.protocol.jl.classic.vp_d.vp_c(bluetoothDevice)) {
            int iVp_j = vp_j(bluetoothDevice);
            new StringBuilder().append("-connectByProfiles- deviceHfpStatus : ").append(iVp_j);
            if (iVp_j == 0) {
                zVp_c = vp_c(bluetoothDevice);
                new StringBuilder().append("-connectByProfiles- connectByHfp  ret : ").append(zVp_c);
            } else if (iVp_j == 2 || iVp_j == 1) {
                zVp_c = true;
            }
        } else {
            new StringBuilder().append("-connectByProfiles- no hfp. device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        }
        boolean z = zVp_c;
        new StringBuilder().append("-connectByProfiles- end ret : ").append(zVp_c);
        return z;
    }

    public boolean vp_e(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        new StringBuilder().append("-disconnectByProfiles- device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
        boolean zVp_g = false;
        if (bluetoothDevice.getType() != 2 || VPOperateManager.isFindJLService()) {
            int iVp_i = vp_i(bluetoothDevice);
            if (iVp_i == 2) {
                zVp_g = vp_f(bluetoothDevice);
                new StringBuilder().append("-disconnectByProfiles- disconnectFromA2dp ret : ").append(zVp_g);
            }
            int iVp_j = vp_j(bluetoothDevice);
            if (iVp_j == 2) {
                zVp_g = vp_g(bluetoothDevice);
                new StringBuilder().append("-disconnectByProfiles- disconnectFromHfp ret : ").append(zVp_g);
            }
            if (iVp_i == 0 && iVp_j == 0) {
                zVp_g = true;
                vp_b(bluetoothDevice, 0);
            }
        }
        return zVp_g;
    }

    public boolean vp_b(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        int iVp_i = vp_i(bluetoothDevice);
        new StringBuilder().append("-connectByA2dp- isConnectedByA2dp : ").append(iVp_i);
        BluetoothA2dp bluetoothA2dp = this.vp_c;
        if (bluetoothA2dp == null) {
            return false;
        }
        if (iVp_i == 2) {
            return true;
        }
        boolean zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothA2dp, bluetoothDevice);
        new StringBuilder().append("-connectByA2dp- ret : ").append(zVp_a);
        return zVp_a;
    }

    public boolean vp_f(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.vp_c == null) {
            return false;
        }
        boolean zVp_b = false;
        int iVp_i = vp_i(bluetoothDevice);
        if (iVp_i == 0) {
            return true;
        }
        if (iVp_i == 2) {
            zVp_b = com.veepoo.protocol.jl.classic.vp_d.vp_b(this.vp_c, bluetoothDevice);
        }
        boolean z = zVp_b;
        new StringBuilder().append("-disconnectFromA2dp- ret : ").append(zVp_b);
        return z;
    }

    public boolean vp_c(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.vp_b == null || !com.veepoo.protocol.jl.classic.vp_d.vp_c(bluetoothDevice)) {
            return false;
        }
        int iVp_j = vp_j(bluetoothDevice);
        new StringBuilder().append("connectByHfp  ------------ isConnectedByHfp : ").append(iVp_j);
        boolean zVp_a = false;
        if (iVp_j == 2) {
            return true;
        }
        if (iVp_j == 0) {
            zVp_a = com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_b, bluetoothDevice);
        }
        boolean z = zVp_a;
        new StringBuilder().append("connectByHfp  ------------ ret ").append(zVp_a);
        return z;
    }

    public boolean vp_g(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.vp_b == null) {
            return false;
        }
        boolean zVp_b = false;
        int iVp_j = vp_j(bluetoothDevice);
        if (iVp_j == 0) {
            return true;
        }
        if (iVp_j == 2) {
            zVp_b = com.veepoo.protocol.jl.classic.vp_d.vp_b(this.vp_b, bluetoothDevice);
        }
        boolean z = zVp_b;
        new StringBuilder().append("-disconnectFromHfp- ret : ").append(zVp_b);
        return z;
    }

    public int vp_j(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || this.vp_b == null || !com.veepoo.protocol.jl.classic.vp_d.vp_c(bluetoothDevice)) {
            return 0;
        }
        List<BluetoothDevice> connectedDevices = this.vp_b.getConnectedDevices();
        if (connectedDevices != null) {
            Iterator<BluetoothDevice> it = connectedDevices.iterator();
            while (it.hasNext()) {
                if (it.next().getAddress().equals(bluetoothDevice.getAddress())) {
                    return 2;
                }
            }
        }
        int connectionState = this.vp_b.getConnectionState(bluetoothDevice);
        new StringBuilder().append("-isConnectedByHfp- state : ").append(connectionState);
        return connectionState;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [int, java.lang.Throwable] */
    public void vp_h(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return;
        }
        this.vp_p = bluetoothDevice;
        ?? bondState = bluetoothDevice.getBondState();
        if (bondState != 10) {
            if (bluetoothDevice.getBondState() == 12) {
                new StringBuilder().append("-initBond- |||| ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(" | 蓝牙已绑定，直接连接A2DP");
                vp_d(bluetoothDevice);
                return;
            }
            return;
        }
        try {
            if (this.vp_a != null && !this.vp_r) {
                if (this.vp_q == null) {
                    this.vp_q = new CBTDiscoveredBroadcastReceiver();
                }
                vp_c();
                if (this.vp_a.isDiscovering()) {
                    this.vp_a.cancelDiscovery();
                }
                new StringBuilder().append("-initBond- |||| ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(" | 开始第").append(this.vp_n + 1).append("次扫描Classic BT......");
                this.vp_a.startDiscovery();
                return;
            }
            Context context = this.vp_d;
            if (context != null) {
                CBTDiscoveredBroadcastReceiver cBTDiscoveredBroadcastReceiver = this.vp_q;
                if (cBTDiscoveredBroadcastReceiver != null) {
                    context.unregisterReceiver(cBTDiscoveredBroadcastReceiver);
                    this.vp_q = null;
                }
            }
            new StringBuilder().append("-initBond- |||| ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice)).append(" | isDiscovered = ").append(true).append(" 开始绑定");
            vp_a(this.vp_o);
        } catch (Exception unused) {
            bondState.printStackTrace();
        }
    }

    private void vp_d() {
        if (this.vp_j == null) {
            this.vp_j = new vp_d();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.bluetooth.device.action.UUID");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            if (Build.VERSION.SDK_INT >= 33) {
                this.vp_d.registerReceiver(this.vp_j, intentFilter, 2);
            } else {
                this.vp_d.registerReceiver(this.vp_j, intentFilter);
            }
        }
    }

    private void vp_c(BluetoothDevice bluetoothDevice, int i) {
        String.format(Locale.CHINA, "-onHfpStatus- device : [%s], status : %d", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), Integer.valueOf(i));
        this.vp_f.onHfpStatus(bluetoothDevice, i);
        if (i != 0) {
            if (i == 2) {
                int iVp_i = vp_i(bluetoothDevice);
                new StringBuilder().append("-onHfpStatus- hfp is connected, a2dp status = ").append(iVp_i);
                if (iVp_i != 2) {
                    if (iVp_i != 0) {
                        return;
                    }
                    boolean zVp_b = com.veepoo.protocol.jl.classic.vp_d.vp_b(bluetoothDevice);
                    new StringBuilder().append("-onHfpStatus- devHasA2dp = ").append(zVp_b);
                    if (zVp_b) {
                        if (vp_d(bluetoothDevice)) {
                            return;
                        }
                    }
                }
                vp_b(bluetoothDevice, 2);
                return;
            }
            return;
        }
        vp_b(bluetoothDevice, 0);
    }

    public BluetoothDevice vp_b() {
        return this.vp_i;
    }

    public boolean vp_a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return false;
        }
        new StringBuilder().append("-connectBrEdrDevice- device : ").append(bluetoothDevice.getType());
        if (bluetoothDevice.getType() == 2 && !VPOperateManager.isFindJLService()) {
            new StringBuilder().append("【蓝牙3.0】 - 错误 设备类型错误 非双模  => deviceType ").append(bluetoothDevice.getType());
            new StringBuilder().append("-connectBrEdrDevice- param is error. device : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice));
            return false;
        }
        if (this.vp_i != null) {
            new StringBuilder().append("-connectBrEdrDevice- mConnectingEdr is connecting. mConnectingEdr : ").append(com.veepoo.protocol.jl.classic.vp_d.vp_f(this.vp_i));
            return false;
        }
        vp_l(bluetoothDevice);
        boolean zVp_a = this.vp_e.vp_a(bluetoothDevice);
        new StringBuilder().append("【蓝牙3.0】-connectBrEdrDevice- isPaired : ").append(zVp_a);
        new StringBuilder().append("-connectBrEdrDevice- isPaired : ").append(zVp_a);
        if (!zVp_a) {
            boolean zVp_f = this.vp_e.vp_f(bluetoothDevice);
            new StringBuilder().append("【蓝牙3.0】-connectBrEdrDevice- not paired tryToPair ret : ").append(zVp_f);
            if (!zVp_f) {
                vp_b(bluetoothDevice, 0);
                return false;
            }
        } else if (bluetoothDevice.getUuids() != null && com.veepoo.protocol.jl.classic.vp_d.vp_b(bluetoothDevice)) {
            boolean zVp_d = vp_d(bluetoothDevice);
            new StringBuilder().append("【蓝牙3.0】 -connectBrEdrDevice- 已配对 connectByProfiles ret =  ").append(zVp_d);
            if (!zVp_d) {
                vp_b(bluetoothDevice, 0);
                return false;
            }
        } else if (!bluetoothDevice.fetchUuidsWithSdp()) {
            vp_b(bluetoothDevice, 0);
            return false;
        }
        vp_b(bluetoothDevice, 1);
        this.vp_k.removeMessages(26145);
        this.vp_k.sendEmptyMessageDelayed(26145, 40000L);
        return true;
    }

    private void vp_c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.FOUND");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
        intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
        this.vp_d.registerReceiver(this.vp_q, intentFilter);
    }

    private void vp_b(BluetoothDevice bluetoothDevice, int i) {
        Locale locale = Locale.CHINA;
        String.format(locale, "-onBrEdrConnection- device : [%s], status : %d", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), Integer.valueOf(i));
        if (i != 1 && (this.vp_i == null || bluetoothDevice == null || com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_i))) {
            new StringBuilder().append(String.format(locale, "-onBrEdrConnection- MSG_CONNECT_EDR_TIMEOUT device : [%s], status : %d", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), Integer.valueOf(i))).append(" mConnectingEdr is :").append(this.vp_i == null ? " null" : " not null");
            vp_l(null);
            this.vp_k.removeMessages(26145);
        }
        this.vp_f.vp_a(bluetoothDevice, i);
    }

    public com.veepoo.protocol.jl.classic.impl.vp_a vp_a() {
        return this.vp_e;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0023, code lost:
    
        r0 = r6.vp_g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005c, code lost:
    
        r0 = r6.vp_h;
     */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [boolean, java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void vp_a(android.content.Context r7) {
        /*
            r6 = this;
            r0 = r7
            if (r0 == 0) goto L8e
            r0 = r6
            android.bluetooth.BluetoothAdapter r0 = r0.vp_a
            if (r0 != 0) goto L12
            r0 = r6
            android.bluetooth.BluetoothAdapter r1 = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
            r0.vp_a = r1
        L12:
            r0 = r6
            android.bluetooth.BluetoothAdapter r0 = r0.vp_a
            if (r0 != 0) goto L1c
            goto L8e
        L1c:
            r0 = r6
            android.bluetooth.BluetoothA2dp r0 = r0.vp_c
            if (r0 != 0) goto L55
            r0 = r6
            boolean r0 = r0.vp_g
            if (r0 != 0) goto L55
            r0 = r6
            r1 = r0
            android.bluetooth.BluetoothAdapter r1 = r1.vp_a     // Catch: java.lang.Exception -> L52
            r2 = r7
            r3 = r6
            android.bluetooth.BluetoothProfile$ServiceListener r3 = r3.vp_l     // Catch: java.lang.Exception -> L52
            r4 = 2
            boolean r1 = r1.getProfileProxy(r2, r3, r4)     // Catch: java.lang.Exception -> L52
            r0.vp_g = r1     // Catch: java.lang.Exception -> L52
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L52
            r1 = r0
            r1.<init>()     // Catch: java.lang.Exception -> L52
            java.lang.String r1 = "initBrEdrService----- isInitA2dp = "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L52
            r1 = r6
            boolean r1 = r1.vp_g     // Catch: java.lang.Exception -> L52
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L52
            goto L55
        L52:
            r0.printStackTrace()
        L55:
            r0 = r6
            android.bluetooth.BluetoothHeadset r0 = r0.vp_b
            if (r0 != 0) goto L8e
            r0 = r6
            boolean r0 = r0.vp_h
            if (r0 != 0) goto L8e
            r0 = r6
            r1 = r0
            android.bluetooth.BluetoothAdapter r1 = r1.vp_a     // Catch: java.lang.Exception -> L8b
            r2 = r7
            r3 = r6
            android.bluetooth.BluetoothProfile$ServiceListener r3 = r3.vp_l     // Catch: java.lang.Exception -> L8b
            r4 = 1
            boolean r1 = r1.getProfileProxy(r2, r3, r4)     // Catch: java.lang.Exception -> L8b
            r0.vp_h = r1     // Catch: java.lang.Exception -> L8b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8b
            r1 = r0
            r1.<init>()     // Catch: java.lang.Exception -> L8b
            java.lang.String r1 = "initBrEdrService----- isInitHfp = "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L8b
            r1 = r6
            boolean r1 = r1.vp_h     // Catch: java.lang.Exception -> L8b
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> L8b
            goto L8e
        L8b:
            r0.printStackTrace()
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.veepoo.protocol.jl.classic.impl.BluetoothBrEdr.vp_a(android.content.Context):void");
    }

    private void vp_a(BluetoothDevice bluetoothDevice, int i) {
        String.format(Locale.CHINA, "-onA2dpStatus- device : [%s], status : %d", com.veepoo.protocol.jl.classic.vp_d.vp_f(bluetoothDevice), Integer.valueOf(i));
        this.vp_f.onA2dpStatus(bluetoothDevice, i);
        if (i == 0) {
            vp_b(bluetoothDevice, 0);
            return;
        }
        if (i == 2) {
            vp_b(bluetoothDevice, 2);
            int iVp_j = vp_j(bluetoothDevice);
            new StringBuilder().append("-onA2dpStatus- a2dp is connected, hfp status = ").append(iVp_j);
            if (iVp_j == 0) {
                vp_d(bluetoothDevice);
            }
        }
    }

    private void vp_a(BluetoothDevice bluetoothDevice, ParcelUuid[] parcelUuidArr) {
        this.vp_f.vp_a(bluetoothDevice, parcelUuidArr);
        if (!com.veepoo.protocol.jl.classic.vp_d.vp_a(bluetoothDevice, this.vp_i) || vp_d(bluetoothDevice)) {
            return;
        }
        vp_b(bluetoothDevice, 0);
    }

    public void vp_a(boolean z) {
        this.vp_r = z;
    }
}
