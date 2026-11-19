package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleUnnotifyResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.listener.base.INotifyResponse;
import com.veepoo.protocol.listener.data.IECGLightDataCallback;
import com.veepoo.protocol.listener.data.IG08ProjectPPGLightCallBack;
import com.veepoo.protocol.listener.data.ILightDataCallBack;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bh.class */
public class vp_bh {
    IECGLightDataCallback vp_a;
    ILightDataCallBack vp_b;
    IG08ProjectPPGLightCallBack vp_c;
    BluetoothClient vp_d;
    String vp_e;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bh$vp_a.class */
    class vp_a implements INotifyResponse {
        final /* synthetic */ INotifyResponse vp_a;

        /* JADX INFO: renamed from: com.veepoo.protocol.operate.vp_bh$vp_a$vp_a, reason: collision with other inner class name */
        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bh$vp_a$vp_a.class */
        class C0009vp_a implements BleNotifyResponse {
            C0009vp_a() {
            }

            public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
                BluetoothLog.i("onNotify ADC,value=" + VpBleByteUtil.byte2HexForShow(bArr));
                ILightDataCallBack iLightDataCallBack = vp_bh.this.vp_b;
                if (iLightDataCallBack != null && bArr != null) {
                    if ((bArr.length > 1) & (bArr[0] == -126)) {
                        iLightDataCallBack.onGreenLightDataChange(com.veepoo.protocol.util.vp_l.vp_a.vp_a(bArr, 1));
                    }
                }
                if (vp_bh.this.vp_c != null) {
                    ArrayList arrayList = new ArrayList();
                    int length = bArr.length;
                    for (int i = 1; i < length; i += 3) {
                        int i2 = (bArr[i] << 16) | (bArr[i + 1] << 8) | bArr[i + 2];
                        if (i2 != 526344) {
                            arrayList.add(Integer.valueOf(vp_bh.vp_a(i2)));
                        }
                    }
                    byte b = bArr[0];
                    int i3 = b == -128 ? 0 : -1;
                    if (b == -125) {
                        i3 = 1;
                    }
                    if (b == -124) {
                        i3 = 2;
                    }
                    vp_bh.this.vp_c.onPPGLightCallBack(i3, arrayList);
                }
                IECGLightDataCallback iECGLightDataCallback = vp_bh.this.vp_a;
                if (iECGLightDataCallback != null) {
                    iECGLightDataCallback.onEcgLightDataChange(bArr);
                }
            }

            public void onResponse(int i) {
                BluetoothLog.i("onNotify ADC,onResponse,success：" + i);
                INotifyResponse iNotifyResponse = vp_a.this.vp_a;
                if (iNotifyResponse != null) {
                    iNotifyResponse.notifyState(i);
                }
            }
        }

        vp_a(INotifyResponse iNotifyResponse) {
            this.vp_a = iNotifyResponse;
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
            vp_bh vp_bhVar = vp_bh.this;
            vp_bhVar.vp_d.notify(vp_bhVar.vp_e, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new C0009vp_a());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bh$vp_b.class */
    class vp_b implements BleUnnotifyResponse {
        final /* synthetic */ INotifyResponse vp_a;

        vp_b(INotifyResponse iNotifyResponse) {
            this.vp_a = iNotifyResponse;
        }

        public void onResponse(int i) {
            BluetoothLog.i("unnotify ADC,onResponse,success");
            INotifyResponse iNotifyResponse = this.vp_a;
            if (iNotifyResponse != null) {
                iNotifyResponse.notifyState(i);
            }
        }
    }

    public vp_bh(BluetoothClient bluetoothClient, String str) {
        this.vp_d = bluetoothClient;
        this.vp_e = str;
    }

    public void vp_b(INotifyResponse iNotifyResponse) {
        vp_a(new vp_a(iNotifyResponse));
    }

    public void vp_a(INotifyResponse iNotifyResponse) {
        this.vp_d.unnotify(this.vp_e, com.veepoo.protocol.profile.vp_a.vp_f, com.veepoo.protocol.profile.vp_a.vp_g, new vp_b(iNotifyResponse));
    }

    public static int vp_a(int i) {
        return ((i >> 19) & 1) == 0 ? i & 524287 : i | (-1048576);
    }

    public void vp_a(IECGLightDataCallback iECGLightDataCallback) {
        this.vp_a = iECGLightDataCallback;
    }

    public void vp_a(IG08ProjectPPGLightCallBack iG08ProjectPPGLightCallBack) {
        this.vp_c = iG08ProjectPPGLightCallBack;
    }

    public void vp_a(ILightDataCallBack iLightDataCallBack) {
        this.vp_b = iLightDataCallBack;
    }
}
