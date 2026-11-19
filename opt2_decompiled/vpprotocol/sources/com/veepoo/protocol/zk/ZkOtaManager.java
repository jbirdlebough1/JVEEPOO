package com.veepoo.protocol.zk;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Handler;
import android.os.Looper;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.bluetrum.abota.ABOta;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/zk/ZkOtaManager.class */
public class ZkOtaManager implements ABOta.EventListener, ABOta.SendCallback {
    private static volatile ZkOtaManager vp_f;
    private BluetoothClient vp_a;
    private ABOta vp_b;
    private IZkOtaUpgradeListener vp_c;
    private boolean vp_d = false;
    private String vp_e = "";

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/zk/ZkOtaManager$vp_a.class */
    class vp_a implements BleNotifyResponse {
        vp_a() {
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            ZkOtaManager.this.vp_b.handleData(bArr);
        }

        public void onResponse(int i) {
            if (i == 0) {
                BluetoothLog.i("onNotify DATA,onResponse,success");
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/zk/ZkOtaManager$vp_b.class */
    class vp_b implements BleWriteResponse {
        vp_b() {
        }

        public void onResponse(int i) {
            new StringBuilder().append("zk ota writeDescriptor ").append(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/zk/ZkOtaManager$vp_c.class */
    class vp_c implements BleWriteResponse {
        vp_c() {
        }

        public void onResponse(int i) {
            if (i == 0) {
                ZkOtaManager.this.vp_b.nextRun();
            }
        }
    }

    private ZkOtaManager() {
        if (vp_f != null) {
            throw new RuntimeException("Use getInstance()");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.zk.ZkOtaManager>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static ZkOtaManager getInstance() {
        if (vp_f == null) {
            ?? r0 = ZkOtaManager.class;
            synchronized (r0) {
                try {
                    if (vp_f == null) {
                        vp_f = new ZkOtaManager();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_f;
    }

    private void vp_b() {
        if (this.vp_d) {
            return;
        }
        this.vp_d = true;
        BluetoothClient bluetoothClient = this.vp_a;
        String str = this.vp_e;
        UUID uuid = com.veepoo.protocol.profile.vp_a.vp_r;
        UUID uuid2 = com.veepoo.protocol.profile.vp_a.vp_s;
        bluetoothClient.notify(str, uuid, uuid2, new vp_a());
        this.vp_a.writeDescriptor(this.vp_e, uuid, uuid2, Constants.CLIENT_CHARACTERISTIC_CONFIG, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE, new vp_b());
    }

    private String vp_a(int i) {
        String str = "unknown error";
        if (i == 1) {
            str = "Same firmware";
        } else if (i == 2) {
            str = "wrong key";
        } else if (i == 11) {
            str = "wrong crc";
        } else if (i == 4097) {
            str = "The device refuses to upgrade";
        } else if (i == 4099) {
            str = "receive timeout";
        } else if (i == 4100) {
            str = "TWS has been disconnected, stop upgrading";
        }
        return str;
    }

    public void init(BluetoothClient bluetoothClient, String str) {
        this.vp_d = false;
        this.vp_e = str;
        ABOta aBOta = new ABOta();
        this.vp_b = aBOta;
        aBOta.setEventListener(this);
        this.vp_b.setSendCallback(this);
        this.vp_a = bluetoothClient;
        vp_b();
    }

    public void startOtaUpgrade(String str, IZkOtaUpgradeListener iZkOtaUpgradeListener) {
        this.vp_c = iZkOtaUpgradeListener;
        ABOta aBOta = this.vp_b;
        if (aBOta == null) {
            iZkOtaUpgradeListener.onOtaUpgradeFail(601, "Please init first");
            return;
        }
        try {
            this.vp_b.setOtaData(vp_a(str));
            this.vp_b.prepareToUpdate();
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                this.vp_b.startOTA();
            }, 1500L);
        } catch (IOException unused) {
            aBOta.printStackTrace();
            iZkOtaUpgradeListener.onOtaUpgradeFail(602, "read file fail");
        }
    }

    public void onStatusChanged(int i, int i2) {
        new StringBuilder().append("zk ota upgrade onStatusChanged:").append(i);
        switch (i) {
            case 0:
                this.vp_c.onOtaUpgradeReady();
                break;
            case 1:
                this.vp_c.onOtaUpgradeStart();
                break;
            case 2:
                this.vp_c.onOtaUpgradeUpdating(i2);
                break;
            case 3:
                this.vp_c.onOtaUpgradePause();
                break;
            case 4:
                this.vp_c.onOtaUpgradeContinue();
                break;
            case 5:
                this.vp_c.onOtaUpgradeSuccess();
                break;
            case 6:
                this.vp_c.onOtaUpgradeWaitFinish();
                break;
            case 7:
                this.vp_c.onOtaUpgradeFail(i2, vp_a(i2));
                break;
        }
    }

    public void onReceiveVersion(int i) {
        new StringBuilder().append("zk ota upgrade version number:").append(String.format(Locale.getDefault(), "%d.%d.%d.%d", Integer.valueOf((i >> 12) & 15), Integer.valueOf((i >> 8) & 15), Integer.valueOf((i >> 4) & 15), Integer.valueOf(i & 15)));
    }

    public void onReceiveTwsInfo(boolean z, boolean z2) {
    }

    public void onReceiveChannel(boolean z) {
    }

    public void sendData(byte[] bArr) {
        String str;
        BluetoothClient bluetoothClient = this.vp_a;
        if (bluetoothClient == null || (str = this.vp_e) == null) {
            return;
        }
        bluetoothClient.writeNoRsp(str, com.veepoo.protocol.profile.vp_a.vp_r, com.veepoo.protocol.profile.vp_a.vp_t, bArr, new vp_c());
    }

    private byte[] vp_a(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(str));
        byte[] bArr = new byte[fileInputStream.available()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return bArr;
    }
}
