package com.inuker.bluetooth.library.jieli;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.util.Log;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.jl_rcsp.impl.RcspAuth;
import com.jieli.jl_rcsp.util.CHexConver;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/RcspAuthManager.class */
public class RcspAuthManager implements RcspAuth.OnRcspAuthListener, RcspAuth.IRcspAuthOp {
    public static final String TAG = RcspAuthManager.class.getSimpleName() + "-杰理-";
    private RcspAuth mRcspAuth;
    private boolean isOTAOpt;
    public BluetoothDevice device;
    public BluetoothGatt gatt;
    private RcspAuthListener listener;
    private boolean isAuthPass = false;
    private boolean isJustDeviceAuth = false;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/RcspAuthManager$RcspAuthListener.class */
    public interface RcspAuthListener {
        void onRcspAuthSuccess();

        void onRcspAuthFailed();
    }

    private RcspAuthManager() {
        initRcsp();
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/RcspAuthManager$RcspAuthManagerHolder.class */
    private static class RcspAuthManagerHolder {
        private static RcspAuthManager INSTANCE = new RcspAuthManager();

        private RcspAuthManagerHolder() {
        }
    }

    public static RcspAuthManager getInstance() {
        return RcspAuthManagerHolder.INSTANCE;
    }

    public void startDevAuthByWatchManager(BluetoothDevice device) {
        BluetoothLog.d(TAG + "-startDevAuthByWatchManager- 开始设备认证 【通过杰理健康库调用】");
        this.isAuthPass = false;
        this.device = device;
        this.isOTAOpt = false;
        this.isJustDeviceAuth = false;
        this.mRcspAuth.stopAuth(device, false);
        this.mRcspAuth.startAuth(device);
    }

    public void init(BluetoothGatt gatt) {
        this.gatt = gatt;
        this.device = gatt.getDevice();
    }

    public void startDevAuthByOTAManager(BluetoothDevice device) {
        BluetoothLog.d(TAG + "-startDevAuthByOTAManager- 开始设备认证 【通过杰理OTA库调用】");
        Log.e(TAG, "STEP.1.....==========>>>-RcspAuth.startAuth(device)- 开始设备认证 【通过杰理OTA库调用】 ");
        this.isAuthPass = false;
        this.device = device;
        this.isOTAOpt = true;
        this.isJustDeviceAuth = false;
        this.mRcspAuth.stopAuth(device, false);
        this.mRcspAuth.startAuth(device);
    }

    public void startAuth(BluetoothDevice device, RcspAuthListener listener) {
        initRcsp();
        this.device = device;
        this.isJustDeviceAuth = true;
        this.listener = listener;
        this.mRcspAuth.stopAuth(device, false);
        Log.e(TAG, "STEP.1.....==========>>>-RcspAuth.startAuth(device)- 调用设备认证 ");
        this.mRcspAuth.startAuth(device);
    }

    private void initRcsp() {
        this.isAuthPass = false;
        if (this.mRcspAuth != null) {
            this.mRcspAuth = null;
        }
        this.mRcspAuth = new RcspAuth(this, this);
    }

    public void handleAuthData(BluetoothDevice device, byte[] data) {
        if (this.mRcspAuth != null) {
            this.mRcspAuth.handleAuthData(device, data);
        }
    }

    public boolean sendAuthDataToDevice(BluetoothDevice bluetoothDevice, byte[] bytes) {
        Log.e(TAG, "STEP.1.....==========>>>-sendAuthDataToDevice- 发送设备认证数据 : " + CHexConver.byte2HexStr(bytes));
        JLBleDataManager.getInstance().writeDataByBleAsync(bytes);
        return true;
    }

    public void onInitResult(boolean b) {
        BluetoothLog.d(TAG + "-onInitResult-  设备认证 初始化结果 = " + b);
    }

    public void onAuthSuccess(BluetoothDevice dev) {
        BluetoothLog.d(TAG + "-onAuthSuccess- 设备认证通过 【 name = " + dev.getName() + " , address = " + dev.getAddress() + " 】");
        Log.e(TAG, "STEP.1-【E N D】 -onAuthSuccess-==============================设备认证通过==============================");
        this.isAuthPass = true;
        if (!this.isJustDeviceAuth) {
            if (this.isOTAOpt) {
                BluetoothLog.d(TAG + "-onAuthSuccess- 设备认证通过 更新OTA库连接状态");
                Log.e(TAG, "==============================更新OTA库连接状态==============================");
                JLOTAManager.getInstance().onBtDeviceConnection(dev, 1);
            } else {
                BluetoothLog.d(TAG + "-onAuthSuccess- 设备认证通过 更新Watch库连接状态");
                Log.e(TAG, "==============================更新Watch库连接状态==============================");
                Log.e(TAG, "STEP.2-【START】==============================更新Watch库连接状态==============================");
                WatchManager.getInstance().notifyBtDeviceConnection(dev, 1);
            }
        }
        if (this.listener != null) {
            this.listener.onRcspAuthSuccess();
        }
    }

    public void onAuthFailed(BluetoothDevice dev, int code, String msg) {
        BluetoothLog.d(TAG + "-onAuthSuccess- 设备认证失败 【 name = " + dev.getName() + " , address = " + dev.getAddress() + " 】 code = " + code + " , msg = " + msg);
        this.isAuthPass = false;
        Log.e(TAG, "STEP.2==============================设备认证失败==============================");
        if (this.listener != null) {
            this.listener.onRcspAuthFailed();
        }
    }

    public boolean isAuthPass() {
        return this.isAuthPass;
    }

    public void setAuthPass(boolean authPass) {
        this.isAuthPass = authPass;
    }

    public void setRcspAuthListener(RcspAuthListener listener) {
        this.listener = listener;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public void setDevice(BluetoothDevice device) {
        this.device = device;
    }

    public BluetoothGatt getGatt() {
        return this.gatt;
    }

    public void setGatt(BluetoothGatt gatt) {
        this.gatt = gatt;
    }
}
