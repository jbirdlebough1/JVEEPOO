package com.veepoo.protocol.nordic;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.veepoo.protocol.util.ZipPackage;
import io.runtime.mcumgr.ble.McuMgrBleTransport;
import io.runtime.mcumgr.dfu.FirmwareUpgradeCallback;
import io.runtime.mcumgr.dfu.FirmwareUpgradeController;
import io.runtime.mcumgr.dfu.mcuboot.FirmwareUpgradeManager;
import io.runtime.mcumgr.dfu.mcuboot.model.ImageSet;
import io.runtime.mcumgr.dfu.suit.model.CacheImageSet;
import io.runtime.mcumgr.exception.McuMgrException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/nordic/McuMgrOtaManager.class */
public class McuMgrOtaManager {
    private Context vp_a;
    private BluetoothDevice vp_b;
    private McuMgrBleTransport vp_c;
    private FirmwareUpgradeManager vp_d;
    private OnMcuMgrOtaListener vp_e;
    private int vp_f = 23;
    private final Handler vp_g = new Handler(Looper.getMainLooper());

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/nordic/McuMgrOtaManager$vp_a.class */
    class vp_a implements FirmwareUpgradeCallback {
        vp_a() {
        }

        public void onUpgradeStarted(FirmwareUpgradeController firmwareUpgradeController) {
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onUpgradeStarted(firmwareUpgradeController);
                });
            }
        }

        /* JADX INFO: renamed from: vp_b, reason: merged with bridge method [inline-methods] */
        public void onStateChanged(FirmwareUpgradeManager.State state, FirmwareUpgradeManager.State state2) {
            new StringBuilder().append("-onStateChanged-: | prevState = ").append(state).append(" newState = ").append(state2);
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onStateChanged(state, state2);
                });
            }
        }

        public void onUpgradeCompleted() {
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onUpgradeCompleted();
                    McuMgrOtaManager.this.vp_a();
                });
            } else {
                mcuMgrOtaManager.vp_a();
            }
        }

        public void onUploadProgressChanged(int i, int i2, long j) {
            new StringBuilder().append("-onUploadProgressChanged-: | 升级中 bytesSent = ").append(i).append(", imageSize = ").append(i2).append(", timestamp = ").append(j);
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onUploadProgressChanged(i, i2, j);
                });
            }
        }

        /* JADX INFO: renamed from: vp_b, reason: merged with bridge method [inline-methods] */
        public void onUpgradeCanceled(FirmwareUpgradeManager.State state) {
            new StringBuilder().append("-onUpgradeCanceled-: | 升级取消 state = ").append(state);
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onUpgradeCanceled(state);
                    McuMgrOtaManager.this.vp_a();
                });
            } else {
                mcuMgrOtaManager.vp_a();
            }
        }

        /* JADX INFO: renamed from: vp_b, reason: merged with bridge method [inline-methods] */
        public void onUpgradeFailed(FirmwareUpgradeManager.State state, McuMgrException mcuMgrException) {
            new StringBuilder().append("-onUpgradeFailed-: | 升级失败，state = ").append(state).append(" error = ").append(mcuMgrException.toString());
            McuMgrOtaManager mcuMgrOtaManager = McuMgrOtaManager.this;
            if (mcuMgrOtaManager.vp_e != null) {
                mcuMgrOtaManager.vp_g.post(() -> {
                    McuMgrOtaManager.this.vp_e.onUpgradeFailed(state, mcuMgrException);
                    McuMgrOtaManager.this.vp_a();
                });
            } else {
                mcuMgrOtaManager.vp_a();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/nordic/McuMgrOtaManager$vp_b.class */
    private static class vp_b {
        static McuMgrOtaManager vp_a = new McuMgrOtaManager();
    }

    public static McuMgrOtaManager getInstance() {
        return vp_b.vp_a;
    }

    private void vp_a() {
        McuMgrBleTransport mcuMgrBleTransport = this.vp_c;
        if (mcuMgrBleTransport != null) {
            mcuMgrBleTransport.release();
        }
        this.vp_c = null;
        this.vp_d = null;
    }

    private void vp_b() {
        McuMgrBleTransport mcuMgrBleTransport = this.vp_c;
        if (mcuMgrBleTransport != null) {
            mcuMgrBleTransport.requestConnPriority(1);
        }
    }

    public void addMcuMgrOtaListener(OnMcuMgrOtaListener onMcuMgrOtaListener) {
        this.vp_e = onMcuMgrOtaListener;
    }

    public void init(Context context, BluetoothDevice bluetoothDevice) {
        this.vp_a = context;
        this.vp_b = bluetoothDevice;
        this.vp_c = new McuMgrBleTransport(context, bluetoothDevice);
        this.vp_d = new FirmwareUpgradeManager(this.vp_c, new vp_a());
    }

    public void setMtu(int i) {
        McuMgrBleTransport mcuMgrBleTransport = this.vp_c;
        if (mcuMgrBleTransport != null) {
            this.vp_f = i;
            mcuMgrBleTransport.setInitialMtu(i);
        }
    }

    public int getMtu() {
        return this.vp_f;
    }

    public void upgrade(@NonNull byte[] bArr, @NonNull FirmwareUpgradeManager.Mode mode, boolean z, int i, int i2, int i3) {
        ImageSet imageSetVp_a;
        ImageSet imageSetAdd;
        if (this.vp_b == null) {
            return;
        }
        try {
            imageSetVp_a = new ImageSet().add(bArr);
        } catch (Exception e) {
            try {
                ZipPackage zipPackage = new ZipPackage(bArr);
                byte[] bArrVp_c = zipPackage.vp_c();
                if (bArrVp_c != null) {
                    imageSetAdd = new ImageSet().add(bArrVp_c);
                    CacheImageSet cacheImageSetVp_b = zipPackage.vp_b();
                    if (cacheImageSetVp_b != null) {
                        imageSetAdd.set(cacheImageSetVp_b.getImages());
                    }
                } else {
                    imageSetVp_a = zipPackage.vp_a();
                }
                vp_a(imageSetAdd, mode, z, i, i2, i3);
            } catch (Exception unused) {
                e.printStackTrace();
                return;
            }
        }
        imageSetAdd = imageSetVp_a;
        vp_a(imageSetAdd, mode, z, i, i2, i3);
    }

    private void vp_a(ImageSet imageSet, FirmwareUpgradeManager.Mode mode, boolean z, int i, int i2, int i3) {
        if (this.vp_b == null) {
            return;
        }
        vp_b();
        this.vp_d.setMode(mode);
        this.vp_d.start(imageSet, new FirmwareUpgradeManager.Settings.Builder().setEraseAppSettings(z).setEstimatedSwapTime(i).setWindowCapacity(i2).setMemoryAlignment(i3).build());
    }
}
