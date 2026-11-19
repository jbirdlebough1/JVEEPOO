package com.inuker.bluetooth.library.search.le;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.os.Process;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.search.BluetoothSearcher;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.BluetoothSearchResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/le/BluetoothLESearcher.class */
public class BluetoothLESearcher extends BluetoothSearcher {
    BluetoothLeScanner mScanner;
    private boolean isEnable = true;
    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() { // from class: com.inuker.bluetooth.library.search.le.BluetoothLESearcher.1
        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            BluetoothLESearcher.this.notifyDeviceFounded(new SearchResult(device, rssi, scanRecord));
        }
    };
    ParcelUuid serviceUuid = ParcelUuid.fromString("0000fee7-0000-1000-8000-00805f9b34fb");

    @TargetApi(26)
    private final ScanCallback mScanCallback = new ScanCallback() { // from class: com.inuker.bluetooth.library.search.le.BluetoothLESearcher.2
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            Log.e("BluetoothLESearcher", "onScanResult-------------> results = " + result);
            if (result.getScanRecord() != null) {
                BluetoothLESearcher.this.notifyDeviceFounded(new SearchResult(result.getDevice(), result.getRssi(), result.getScanRecord().getBytes()));
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            Log.e("BluetoothLESearcher", "onBatchScanResults-------------> results = " + results);
            for (ScanResult result : results) {
                Log.e("BluetoothLESearcher", result.getDevice().getName() + " ---- " + result.getDevice().getAddress());
            }
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            Log.e("BluetoothLESearcher", "onScanFailed-------------> errorCode = " + errorCode);
        }
    };
    private List<ScanFilter> scanFilterList;
    private ScanFilter.Builder scanFilterBuilder;
    private ScanSettings.Builder scanSettingBuilder;

    private BluetoothLESearcher() {
        this.mBluetoothAdapter = BluetoothUtils.getBluetoothAdapter();
        if (Build.VERSION.SDK_INT >= 26) {
            this.mScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        }
    }

    public static BluetoothLESearcher getInstance() {
        return BluetoothLESearcherHolder.instance;
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/le/BluetoothLESearcher$BluetoothLESearcherHolder.class */
    private static class BluetoothLESearcherHolder {
        private static BluetoothLESearcher instance = new BluetoothLESearcher();

        private BluetoothLESearcherHolder() {
        }
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    @TargetApi(18)
    public void startScanBluetooth(BluetoothSearchResponse response) {
        super.startScanBluetooth(response);
        if (Build.VERSION.SDK_INT >= 26 && this.isEnable) {
            Log.e("BluetoothLESearcher", "startScanDevice-------------> 版本>=26 开始搜索");
            startScanDevice2();
        } else {
            Log.e("BluetoothLESearcher", "startScanBluetooth------------->startLeScan 低版本 开始搜索");
            this.mBluetoothAdapter.startLeScan(this.mLeScanCallback);
        }
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    @TargetApi(18)
    public void stopScanBluetooth() {
        try {
            if (Build.VERSION.SDK_INT >= 26 && this.isEnable) {
                Log.e("BluetoothLESearcher", "stopScanBluetooth-------------> 版本>=26 搜索停止");
                this.mScanner.stopScan(this.mScanCallback);
            } else {
                Log.e("BluetoothLESearcher", "stopScanBluetooth------------->stopLeScan 低版本 搜索停止");
                this.mBluetoothAdapter.stopLeScan(this.mLeScanCallback);
            }
        } catch (Exception e) {
            BluetoothLog.e(e);
        }
        super.stopScanBluetooth();
    }

    @Override // com.inuker.bluetooth.library.search.BluetoothSearcher
    @TargetApi(18)
    protected void cancelScanBluetooth() {
        if (Build.VERSION.SDK_INT >= 26 && this.isEnable) {
            Log.e("BluetoothLESearcher", "cancelScanBluetooth-------------> 版本>=26 搜索停止");
            this.mScanner.stopScan(this.mScanCallback);
        } else {
            this.mBluetoothAdapter.stopLeScan(this.mLeScanCallback);
            Log.e("BluetoothLESearcher", "cancelScanBluetooth-------------> 低版本 搜索停止");
        }
        super.cancelScanBluetooth();
    }

    @TargetApi(26)
    public void startScanDevice() {
        List<ScanFilter> filters = new ArrayList<>();
        ScanFilter.Builder scanFilterBuilder = new ScanFilter.Builder();
        scanFilterBuilder.setServiceUuid(this.serviceUuid);
        ScanFilter scanFilter = scanFilterBuilder.build();
        filters.add(scanFilter);
        ScanSettings settings = new ScanSettings.Builder().build();
        this.mScanner.startScan(filters, settings, this.mScanCallback);
    }

    @TargetApi(26)
    private void startScanDevice2() {
        Log.e("BluetoothLESearcher", "开始搜索设备-------------> 当前进程名 = " + getCurProcessName(BluetoothContext.get()));
        this.mBluetoothAdapter.getBluetoothLeScanner().startScan(buildScanFilters(), buildScanSettings(), this.mScanCallback);
    }

    @TargetApi(26)
    private List<ScanFilter> buildScanFilters() {
        this.scanFilterList = new ArrayList();
        this.scanFilterBuilder = new ScanFilter.Builder();
        ParcelUuid parcelUuidMask = ParcelUuid.fromString("FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF");
        this.scanFilterBuilder.setServiceUuid(this.serviceUuid, parcelUuidMask);
        this.scanFilterBuilder.setServiceUuid(this.serviceUuid);
        this.scanFilterList.add(this.scanFilterBuilder.build());
        this.scanFilterList.add(new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString("0000ffff-0000-1000-8000-00805f9bfffb")).build());
        return this.scanFilterList;
    }

    @TargetApi(26)
    private ScanSettings buildScanSettings() {
        this.scanSettingBuilder = new ScanSettings.Builder();
        this.scanSettingBuilder.setScanMode(2);
        this.scanSettingBuilder.setMatchMode(1);
        this.scanSettingBuilder.setCallbackType(1);
        this.scanSettingBuilder.setLegacy(true);
        return new ScanSettings.Builder().build();
    }

    private boolean isAppForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = activityManager.getRunningAppProcesses();
        if (runningAppProcessInfoList == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo processInfo : runningAppProcessInfoList) {
            if (processInfo.processName.equals(context.getPackageName()) && processInfo.importance == 100) {
                return true;
            }
        }
        return false;
    }

    String getCurProcessName(Context context) {
        int pid = Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService("activity");
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
