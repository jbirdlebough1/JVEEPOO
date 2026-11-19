package com.veepoo.protocol.operate;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IDeviceBTConnectionListener;
import com.veepoo.protocol.listener.data.IDeviceBTInfoListener;
import com.veepoo.protocol.model.datas.BTInfo;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_g.class */
public final class vp_g extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_r = new vp_a(null);
    private BTInfo.BTStatus vp_c;
    private boolean vp_d;
    private boolean vp_e;
    private boolean vp_f;
    private boolean vp_g;
    private boolean vp_h;
    private boolean vp_i;
    private int vp_j;
    private IDeviceBTInfoListener vp_k;
    private IDeviceBTConnectionListener vp_l;
    public BluetoothClient vp_m;
    private String vp_n;
    public BTInfo vp_p;
    private BleWriteResponse vp_o = vp_g::vp_a;
    private Handler vp_q = new vp_c(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_g$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_g$vp_b.class */
    public /* synthetic */ class vp_b {
        public static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[BTInfo.BTStatus.values().length];
            try {
                iArr[BTInfo.BTStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BTInfo.BTStatus.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BTInfo.BTStatus.BROADCASTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            vp_a = iArr;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_g$vp_c.class */
    public static final class vp_c extends Handler {
        vp_c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Intrinsics.checkNotNullParameter(message, "msg");
            super.handleMessage(message);
            int i = message.what;
            if (i == 17) {
                vp_g.this.vp_a("等待A1校验通过，循环监测到密码后开始连接BT");
                return;
            }
            if (i == 18) {
                String str = "【BT】-********连接超时 timeOutCount = " + vp_g.this.vp_j;
                vp_g.this.vp_a(false);
                if (vp_g.this.vp_j % 2 != 0) {
                    vp_g vp_gVar = vp_g.this;
                    vp_g.vp_a(vp_gVar, false, vp_gVar.vp_d(), 1, (Object) null);
                    return;
                }
                vp_g.this.vp_j = 0;
                IDeviceBTConnectionListener iDeviceBTConnectionListenerVp_b = vp_g.this.vp_b();
                if (iDeviceBTConnectionListenerVp_b != null) {
                    iDeviceBTConnectionListenerVp_b.onDeviceBTConnectTimeout();
                }
            }
        }
    }

    private static final void vp_d(vp_g vp_gVar) {
        vp_gVar.vp_a(false, vp_gVar.vp_f);
    }

    private static final void vp_b(vp_g vp_gVar) {
        vp_gVar.vp_q.sendEmptyMessage(17);
    }

    private static final void vp_c(vp_g vp_gVar) {
        vp_gVar.vp_q.sendEmptyMessage(18);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_n(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_n(bluetoothClient, str, bleWriteResponse);
        send(com.veepoo.protocol.profile.vp_a.vp_bx, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0174 A[PHI: r8
  0x0174: PHI (r8v5 com.veepoo.protocol.listener.data.IDeviceBTConnectionListener) = 
  (r8v4 com.veepoo.protocol.listener.data.IDeviceBTConnectionListener)
  (r8v6 com.veepoo.protocol.listener.data.IDeviceBTConnectionListener)
 binds: [B:63:0x01c8, B:52:0x0171] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IDeviceBTConnectionListener iDeviceBTConnectionListener;
        super.handler(bArr);
        if (bArr == null || bArr.length < 20) {
            return;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        byte b5 = bArr[5];
        byte b6 = bArr[6];
        com.veepoo.protocol.jl.classic.impl.vp_a.vp_g = bArr[7];
        this.vp_d = b4 == 1 || b4 == 2;
        this.vp_e = b4 == 1;
        this.vp_f = b5 == 1;
        this.vp_g = b6 == 1;
        BTInfo.BTStatus bTStatusByValue = BTInfo.BTStatus.INSTANCE.getBTStatusByValue(b3);
        this.vp_c = bTStatusByValue;
        Intrinsics.checkNotNull(bTStatusByValue);
        vp_a(new BTInfo(bTStatusByValue, this.vp_d, this.vp_e, this.vp_f, this.vp_g));
        switch (b) {
            case 0:
                IDeviceBTInfoListener iDeviceBTInfoListener = this.vp_k;
                if (iDeviceBTInfoListener != null) {
                    iDeviceBTInfoListener.onDeviceBTFunctionNotSupport();
                }
                break;
            case 1:
                if (b2 == 0) {
                    IDeviceBTInfoListener iDeviceBTInfoListener2 = this.vp_k;
                    if (iDeviceBTInfoListener2 != null) {
                        iDeviceBTInfoListener2.onDeviceBTInfoSettingFailed();
                    }
                    break;
                } else if (b2 == 1) {
                    String str = "【BT】-设备BT信息设置:-> 成功 \n " + vp_c();
                    IDeviceBTInfoListener iDeviceBTInfoListener3 = this.vp_k;
                    if (iDeviceBTInfoListener3 != null) {
                        iDeviceBTInfoListener3.onDeviceBTInfoSettingSuccess(vp_c());
                    }
                    if ((this.vp_c == BTInfo.BTStatus.BROADCASTING || b4 == 2) && !this.vp_e) {
                        vp_a("设置打开BT开关，设备不自动回连 =》 开始连接");
                    }
                    break;
                }
                break;
            case 2:
                if (b2 == 0) {
                    IDeviceBTInfoListener iDeviceBTInfoListener4 = this.vp_k;
                    if (iDeviceBTInfoListener4 != null) {
                        iDeviceBTInfoListener4.onDeviceBTInfoReadFailed();
                    }
                    break;
                } else if (b2 == 1) {
                    String str2 = "【BT】-设备BT信息读取:-> 成功 \n " + vp_c();
                    IDeviceBTInfoListener iDeviceBTInfoListener5 = this.vp_k;
                    if (iDeviceBTInfoListener5 != null) {
                        iDeviceBTInfoListener5.onDeviceBTInfoReadSuccess(vp_c());
                    }
                    break;
                }
                break;
            case 3:
                boolean z = VPOperateManager.isFirstGetBDReport;
                this.vp_h = true;
                if (b2 == 1) {
                    String str3 = "【BT】-设备BT信息主动上报-> \n " + vp_c();
                    IDeviceBTInfoListener iDeviceBTInfoListener6 = this.vp_k;
                    if (iDeviceBTInfoListener6 != null) {
                        iDeviceBTInfoListener6.onDeviceBTInfoReport(vp_c());
                    }
                    BTInfo.BTStatus bTStatus = this.vp_c;
                    int i = bTStatus == null ? -1 : vp_b.vp_a[bTStatus.ordinal()];
                    if (i == 1) {
                        this.vp_i = false;
                        IDeviceBTConnectionListener iDeviceBTConnectionListener2 = this.vp_l;
                        if (iDeviceBTConnectionListener2 != null) {
                            iDeviceBTConnectionListener2.onDeviceBTConnected();
                        }
                        this.vp_q.removeCallbacksAndMessages(null);
                    } else if (i == 2) {
                        if (VPOperateManager.getInstance().isAutoConnectBTBySdk() && z && this.vp_d) {
                            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                                vp_d(r1);
                            }, 500L);
                        } else {
                            this.vp_i = false;
                            this.vp_q.removeCallbacksAndMessages(null);
                            IDeviceBTConnectionListener iDeviceBTConnectionListener3 = this.vp_l;
                            iDeviceBTConnectionListener = iDeviceBTConnectionListener3;
                            if (iDeviceBTConnectionListener3 != null) {
                                iDeviceBTConnectionListener.onDeviceBTDisconnected();
                            }
                        }
                        break;
                    } else if (i == 3 && VPOperateManager.getInstance().isAutoConnectBTBySdk()) {
                        if (this.vp_e) {
                            boolean z2 = VPOperateManager.isPasswordConfirmBack;
                            String str4 = "【BT】-【REP-BROADCASTING】-设备自动回连- A1指令是否返回=" + z2;
                            if (z2) {
                                IDeviceBTConnectionListener iDeviceBTConnectionListener4 = this.vp_l;
                                iDeviceBTConnectionListener = iDeviceBTConnectionListener4;
                                if (iDeviceBTConnectionListener4 != null) {
                                    iDeviceBTConnectionListener.onDeviceBTDisconnected();
                                }
                            } else {
                                String str5 = "【BT】-【REP-BROADCASTING】- BLE手动连接且设备自动回连->发送不回连命令-->等待设备重新返回BT状态  isPasswordCallback = " + z2;
                                vp_a(false, this.vp_f);
                            }
                        } else {
                            vp_a("收到设备上报 设备不回连 App发起连接->connectBT");
                        }
                    }
                }
                VPOperateManager.isFirstGetBDReport = false;
                break;
        }
    }

    public final boolean vp_d() {
        return this.vp_f;
    }

    public final IDeviceBTConnectionListener vp_b() {
        return this.vp_l;
    }

    public final BTInfo vp_c() {
        BTInfo bTInfo = this.vp_p;
        if (bTInfo != null) {
            return bTInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("currentBTInfo");
        return null;
    }

    public final void vp_b(boolean z, boolean z2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        send(vp_a(z, this.vp_e, z2, false), bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(String str) {
        Intrinsics.checkNotNullParameter(str, "msg");
        if (this.vp_i) {
            return;
        }
        if (this.vp_j % 2 == 0) {
            this.vp_j = 0;
        }
        String str2 = "【BT】--connectBT- type:[" + str + "] ******开始连接经典蓝牙3.0 密码校验是否返回 = " + VPOperateManager.isPasswordConfirmBack + ", MAC = " + this.vp_n;
        IDeviceBTConnectionListener iDeviceBTConnectionListener = this.vp_l;
        if (iDeviceBTConnectionListener != null) {
            iDeviceBTConnectionListener.onDeviceBTConnecting();
        }
        if (!VPOperateManager.isPasswordConfirmBack) {
            this.vp_q.postDelayed(() -> {
                vp_b(r1);
            }, 300L);
            return;
        }
        this.vp_j++;
        this.vp_i = true;
        vp_a(com.veepoo.protocol.jl.classic.vp_d.vp_a(this.vp_n));
        this.vp_q.postDelayed(() -> {
            vp_c(r1);
        }, 15000L);
    }

    public final BluetoothClient vp_a() {
        BluetoothClient bluetoothClient = this.vp_m;
        if (bluetoothClient != null) {
            return bluetoothClient;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bleClient");
        return null;
    }

    public final void vp_b(String str) {
        this.vp_n = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(boolean z, boolean z2, boolean z3, boolean z4) {
        char c;
        byte[] bArr = new byte[20];
        bArr[0] = -67;
        bArr[1] = 1;
        if (z) {
            c = z2 ? (char) 1 : (char) 2;
        } else {
            c = 0;
        }
        bArr[2] = c;
        bArr[3] = z3;
        bArr[4] = z4 ? (char) 2 : (char) 1;
        return bArr;
    }

    private static final void vp_a(int i) {
    }

    private final void vp_a(boolean z, boolean z2) {
        String str = "【BT】- 打开开关 openBT- isAutoConnect = " + z + "  Mac = " + this.vp_n + "  isA1Callback = " + VPOperateManager.isPasswordConfirmBack + " \n bleClient = " + vp_a();
        vp_a(z, z2, vp_a(), this.vp_n, this.vp_o);
    }

    static /* synthetic */ void vp_a(vp_g vp_gVar, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        vp_gVar.vp_a(z, z2);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, boolean z2, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_a(z, z2, bluetoothClient, str, bleWriteResponse);
        send(vp_a(true, z, z2, false), bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(boolean z) {
        this.vp_i = z;
    }

    public final void vp_a(BluetoothClient bluetoothClient) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "<set-?>");
        this.vp_m = bluetoothClient;
    }

    public final void vp_a(IDeviceBTConnectionListener iDeviceBTConnectionListener) {
        this.vp_l = iDeviceBTConnectionListener;
    }

    public final void vp_a(IDeviceBTInfoListener iDeviceBTInfoListener) {
        this.vp_k = iDeviceBTInfoListener;
    }

    public final void vp_a(BTInfo bTInfo) {
        Intrinsics.checkNotNullParameter(bTInfo, "<set-?>");
        this.vp_p = bTInfo;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, boolean z2, boolean z3, boolean z4, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_a(z, z2, z3, z4, bluetoothClient, str, bleWriteResponse);
        send(vp_a(z2, z, z3, z4), bluetoothClient, str, bleWriteResponse);
    }

    private final void vp_a(BluetoothDevice bluetoothDevice) {
        com.veepoo.protocol.jl.classic.vp_e.vp_e().vp_j(bluetoothDevice);
    }
}
