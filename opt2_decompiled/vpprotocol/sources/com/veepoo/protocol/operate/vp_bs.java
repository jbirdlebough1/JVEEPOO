package com.veepoo.protocol.operate;

import android.content.Context;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.base.IConnectResponse;
import com.veepoo.protocol.listener.base.INotifyResponse;
import com.veepoo.protocol.listener.oad.OnFindOadDeviceListener;
import com.veepoo.protocol.model.enums.ECPUPlatform;
import com.veepoo.protocol.model.enums.ECpuType;
import com.veepoo.protocol.model.settings.OadSetting;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs.class */
public class vp_bs {
    private Context vp_a;
    private boolean vp_b;
    private String vp_c;
    private String vp_d;
    private String vp_e;
    private OnFindOadDeviceListener vp_f;
    int vp_g = 3000;
    int vp_h = 6000;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_a.class */
    class vp_a implements IBleWriteResponse {

        /* JADX INFO: renamed from: com.veepoo.protocol.operate.vp_bs$vp_a$vp_a, reason: collision with other inner class name */
        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_a$vp_a.class */
        class C0010vp_a extends TimerTask {
            C0010vp_a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                vp_bs.this.vp_d();
            }
        }

        vp_a() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
            if (i == 0) {
                new Timer().schedule(new C0010vp_a(), vp_bs.this.vp_h);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_b.class */
    class vp_b implements IBleWriteResponse {

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_b$vp_a.class */
        class vp_a extends TimerTask {
            vp_a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                new StringBuilder().append("设备是由升级模式升级 找到要升级的目标设备:").append(vp_bs.this.vp_d);
                ECpuType cpuType = VPOperateManager.getMangerInstance(vp_bs.this.vp_a).getCpuType();
                ECPUPlatform cPUPlatform = VPOperateManager.getMangerInstance(vp_bs.this.vp_a).getCPUPlatform();
                vp_bs vp_bsVar = vp_bs.this;
                vp_bsVar.vp_f.findOadDevice(vp_bsVar.vp_d, cpuType);
                vp_bs vp_bsVar2 = vp_bs.this;
                vp_bsVar2.vp_f.findOadDevice(vp_bsVar2.vp_d, cPUPlatform);
            }
        }

        vp_b() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
            new Timer().schedule(new vp_a(), vp_bs.this.vp_g);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_c.class */
    class vp_c implements IBleWriteResponse {
        vp_c() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
            vp_bs.this.vp_f.unKnowCpu();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_d.class */
    class vp_d implements SearchResponse {
        vp_d() {
        }

        public void onSearchStarted() {
        }

        public void onDeviceFounded(SearchResult searchResult) {
            if (searchResult.getAddress().equals(vp_bs.this.vp_d)) {
                VPOperateManager.getMangerInstance(vp_bs.this.vp_a).stopScanDevice();
                vp_bs vp_bsVar = vp_bs.this;
                vp_bsVar.vp_b(vp_bsVar.vp_d);
            }
        }

        public void onSearchStopped() {
        }

        public void onSearchCanceled() {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_e.class */
    class vp_e implements IConnectResponse {
        vp_e() {
        }

        @Override // com.veepoo.protocol.listener.base.IConnectResponse
        public void connectState(int i, BleGattProfile bleGattProfile, boolean z) {
            if (i == 0) {
                vp_bs.this.vp_a();
            } else {
                vp_bs.this.vp_e();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bs$vp_f.class */
    class vp_f implements INotifyResponse {
        vp_f() {
        }

        @Override // com.veepoo.protocol.listener.base.INotifyResponse
        public void notifyState(int i) {
        }
    }

    public vp_bs(Context context, OadSetting oadSetting, OnFindOadDeviceListener onFindOadDeviceListener) {
        this.vp_a = context;
        this.vp_b = oadSetting.isOadModel();
        this.vp_c = oadSetting.getDeviceAddress();
        this.vp_f = onFindOadDeviceListener;
    }

    private void vp_e() {
        VPOperateManager.getMangerInstance(this.vp_a).disconnectWatch(new vp_c());
    }

    private void vp_d() {
        VPOperateManager.getMangerInstance(this.vp_a).startScanDevice(new vp_d());
    }

    private void vp_b(String str) {
        VPOperateManager.getMangerInstance(this.vp_a).connectDevice(str, "deviceName", new vp_e(), new vp_f());
    }

    private String vp_a(String str) {
        try {
            int iIntValue = Integer.valueOf(str, 16).intValue() + 1;
            str = Integer.toHexString(iIntValue);
            if (iIntValue <= 15) {
                str = "0" + str;
            }
            if (str.length() > 2) {
                str = str.substring(1, 3);
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public void vp_c() {
        if (this.vp_b) {
            this.vp_d = this.vp_c;
            vp_a();
        } else {
            String strVp_c = vp_c(this.vp_c);
            this.vp_e = strVp_c;
            this.vp_d = strVp_c;
            vp_b();
        }
    }

    private void vp_b() {
        VPOperateManager.getMangerInstance(this.vp_a).enterOad(new vp_a());
    }

    private void vp_a() {
        VPOperateManager.getMangerInstance(this.vp_a).disconnectWatch(new vp_b());
    }

    private String vp_c(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        int length = str.length();
        if (length >= 2) {
            int i = length - 2;
            String upperCase = (str.substring(0, i) + vp_a(str.substring(i, length))).toUpperCase();
            new StringBuilder().append(str).append("-->").append(upperCase);
            str = upperCase;
        }
        return str;
    }
}
