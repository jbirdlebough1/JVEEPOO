package com.inuker.bluetooth.library.jieli.ota;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/BLEScanner.class */
public class BLEScanner implements BluetoothAdapter.LeScanCallback {
    BluetoothManager mBManager;
    BluetoothAdapter mBAdapter;
    public static int SCAN_TIME = 15000;
    private static int REQUEST_CODE = 520;
    private Context context = null;
    private OnBLEDeviceScanListener listener = null;
    boolean isScanning = false;
    private Handler handler = null;
    private ArrayList<BluetoothDevice> scanBlueDeviceArray = new ArrayList<>();

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/BLEScanner$BLEScannerHolder.class */
    public static final class BLEScannerHolder {
        private static final BLEScanner INSTANCE = new BLEScanner();
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/BLEScanner$OnBLEDeviceScanListener.class */
    public interface OnBLEDeviceScanListener {
        void onStartScan();

        void onDeviceFound(BluetoothDevice bluetoothDevice);

        void onStopScan(List<BluetoothDevice> list);
    }

    public static BLEScanner getInstance() {
        return BLEScannerHolder.INSTANCE;
    }

    public void initBLE(Context context) {
        this.handler = new Handler(Looper.getMainLooper());
        this.context = context.getApplicationContext();
        if (!context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            Toast.makeText(context, "SORRY!BLE NOT SUPPORTED!", 0).show();
            return;
        }
        this.mBManager = (BluetoothManager) context.getSystemService("bluetooth");
        if (null != this.mBManager) {
            this.mBAdapter = this.mBManager.getAdapter();
            if (this.mBAdapter == null) {
                Toast.makeText(context, "SORRY!YOU PHONE IS NOT SUPPORTED!", 0).show();
                return;
            }
        }
        if (!this.mBAdapter.isEnabled()) {
            BluetoothLog.e("Bluetooth is close");
        }
    }

    public boolean isBluetoothEnable(Activity activity) {
        if (!this.mBAdapter.isEnabled()) {
            if (!this.mBAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                activity.startActivityForResult(enableBtIntent, REQUEST_CODE);
                return true;
            }
            return true;
        }
        return false;
    }

    public void setListener(OnBLEDeviceScanListener listener) {
        this.listener = listener;
    }

    public static boolean isBluetoothAdapterPermissionEnable(Context context) {
        return Build.VERSION.SDK_INT < 31 || ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_SCAN") == 0;
    }

    public void startScanBluetoothDevice() {
        if (this.scanBlueDeviceArray == null) {
            this.scanBlueDeviceArray = new ArrayList<>();
        }
        this.scanBlueDeviceArray.clear();
        this.handler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.ota.BLEScanner.1
            @Override // java.lang.Runnable
            public void run() {
                BLEScanner.this.stopScanBluetoothDevice();
            }
        }, SCAN_TIME);
        if (isBluetoothAdapterPermissionEnable(BluetoothContext.get())) {
            this.isScanning = true;
            if (this.listener != null) {
                this.listener.onStartScan();
            }
            Log.e("*********", "开始搜索............");
            this.mBAdapter.startLeScan(this);
            return;
        }
        BluetoothLog.e("HBand not have Bluetooth scan permission, you need give it permission!");
    }

    @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
        if (!this.scanBlueDeviceArray.contains(bluetoothDevice)) {
            this.scanBlueDeviceArray.add(bluetoothDevice);
            if (this.listener != null) {
                this.listener.onDeviceFound(bluetoothDevice);
            }
        }
    }

    public void stopScanBluetoothDevice() {
        this.isScanning = false;
        this.handler.removeCallbacksAndMessages(null);
        if (isBluetoothAdapterPermissionEnable(BluetoothContext.get())) {
            if (this.mBAdapter != null) {
                this.mBAdapter.stopLeScan(this);
            }
        } else {
            BluetoothLog.e("HBand not have Bluetooth scan permission, you need give it permission!");
        }
        if (this.listener != null) {
            this.listener.onStopScan(this.scanBlueDeviceArray);
        }
    }
}
