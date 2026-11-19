package com.inuker.bluetooth.library.jieli.ota;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.AppUtil;
import com.inuker.bluetooth.library.jieli.dial.WatchConstant;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.jl_bt_ota.impl.BluetoothOTAManager;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.interfaces.IActionCallback;
import com.jieli.jl_bt_ota.model.BluetoothOTAConfigure;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.model.response.TargetInfoResponse;
import com.jieli.jl_bt_ota.tool.DeviceReConnectManager;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback;
import com.jieli.jl_rcsp.util.CHexConver;
import java.io.File;
import java.util.Locale;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAManager.class */
public class JLOTAManager extends BluetoothOTAManager {
    private static final String TAG = JLOTAManager.class.getSimpleName() + "-杰理-";
    private BluetoothDevice mTargetDevice;
    public static final String OTA_FILE_SUFFIX = ".ufw";
    public static final String OTA_FILE_NAME = "update.ufw";
    public static final String OTA_ZIP_SUFFIX = ".zip";
    private BluetoothGatt mConnectGatt;
    private boolean isJLOTAInitSuccess;
    public boolean isDeviceInMandatoryUpdate;
    private final Handler uiHandler;
    DeviceReConnectManager reConnectManager;
    private boolean isJlDeviceDfuModel;
    OnOTASDKInitListener listener;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAManager$OnOTASDKInitListener.class */
    public interface OnOTASDKInitListener {
        void onInitSuccess();

        void onInitFailed();

        void queryMandatoryUpdateFinish();
    }

    public boolean isJlDeviceDfuModel() {
        return this.isJlDeviceDfuModel;
    }

    public void setJlDeviceDfuModel(boolean jlDeviceDfuModel) {
        this.isJlDeviceDfuModel = jlDeviceDfuModel;
    }

    private JLOTAManager() {
        super(BluetoothContext.get());
        this.isJLOTAInitSuccess = false;
        this.isDeviceInMandatoryUpdate = false;
        this.uiHandler = new Handler(Looper.getMainLooper());
        this.reConnectManager = null;
        this.isJlDeviceDfuModel = false;
        this.isJLOTAInitSuccess = false;
        configureOTA();
        if (BluetoothContext.get() != null) {
            this.reConnectManager = new DeviceReConnectManager(BluetoothContext.get(), this);
        }
    }

    public void init(BluetoothGatt connectedGatt) {
        this.mTargetDevice = connectedGatt.getDevice();
        this.mConnectGatt = connectedGatt;
        if (RcspAuthManager.getInstance().isAuthPass()) {
            BluetoothLog.e(TAG + "-init- 初始化杰理OTA库 （已通过设备认证）更新连接状态");
            onBtDeviceConnection(this.mTargetDevice, 1);
        } else {
            RcspAuthManager.getInstance().startDevAuthByOTAManager(this.mTargetDevice);
        }
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAManager$InstanceHolder.class */
    private static final class InstanceHolder {
        static final JLOTAManager instance = new JLOTAManager();

        private InstanceHolder() {
        }
    }

    public static JLOTAManager getInstance() {
        return InstanceHolder.instance;
    }

    public boolean isJLOTAInitSuccess() {
        return this.isJLOTAInitSuccess;
    }

    public void setJLOTAInitSuccess(boolean isInitSuccess) {
        this.isJLOTAInitSuccess = isInitSuccess;
    }

    private void configureOTA() {
        BluetoothOTAConfigure configure = BluetoothOTAConfigure.createDefault();
        configure.setPriority(0).setNeedChangeMtu(true).setMtu(20).setUseAuthDevice(false).setUseReconnect(true);
        String otaDir = AppUtil.createFilePath(BluetoothContext.get(), WatchConstant.DIR_UPDATE, "jlOta");
        File dir = new File(otaDir);
        boolean isExistDir = dir.exists();
        if (!isExistDir) {
            isExistDir = dir.mkdir();
        }
        if (isExistDir) {
            String otaFilePath = AppUtil.obtainUpdateFilePath(otaDir, OTA_FILE_SUFFIX);
            if (null == otaFilePath) {
                otaFilePath = otaDir + "/" + OTA_FILE_NAME;
            }
            BluetoothLog.e(TAG + "otaFilePath = " + otaFilePath);
            configure.setFirmwareFilePath(otaFilePath);
        }
        configure(configure);
        WatchManager.getInstance().registerOnWatchCallback(new OnWatchCallback() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.1
            public void onResourceUpdateUnfinished(BluetoothDevice bluetoothDevice) {
                super.onResourceUpdateUnfinished(bluetoothDevice);
                JLOTAManager.this.isJlDeviceDfuModel = true;
            }

            public void onMandatoryUpgrade(BluetoothDevice bluetoothDevice) {
                super.onMandatoryUpgrade(bluetoothDevice);
                JLOTAManager.this.isJlDeviceDfuModel = true;
            }
        });
        registerBluetoothCallback(new AnonymousClass2());
    }

    /* JADX INFO: renamed from: com.inuker.bluetooth.library.jieli.ota.JLOTAManager$2, reason: invalid class name */
    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAManager$2.class */
    class AnonymousClass2 extends BtEventCallback {
        AnonymousClass2() {
        }

        public void onConnection(BluetoothDevice device, int status) {
            super.onConnection(device, status);
            BluetoothLog.e(JLOTAManager.TAG + "onConnection->device:" + device.toString() + " , status = " + status);
            if (status == 1) {
                JLOTAManager.this.isJLOTAInitSuccess = true;
                if (JLOTAManager.this.listener != null) {
                    JLOTAManager.this.uiHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JLOTAManager.this.listener.onInitSuccess();
                        }
                    });
                }
                BluetoothLog.e(JLOTAManager.TAG + "-onConnection- -> device:" + device.toString() + " , status = " + status + "=>" + JLOTAManager.jlStatus2Str(status));
                JLOTAManager.this.queryMandatoryUpdate(new IActionCallback<TargetInfoResponse>() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.2.2
                    public void onSuccess(TargetInfoResponse targetInfoResponse) {
                        int versionCode = targetInfoResponse.getVersionCode();
                        String versionName = targetInfoResponse.getVersionName();
                        String projectCode = targetInfoResponse.getProjectCode();
                        JLOTAManager.this.isDeviceInMandatoryUpdate = true;
                        BluetoothLog.e(JLOTAManager.TAG + "需要强制升级->设备版本号:" + versionCode + " , 设备版本名 = " + versionName + " , 设备产品ID = " + projectCode);
                        JLOTAManager.this.isJlDeviceDfuModel = targetInfoResponse.isMandatoryUpgrade() || targetInfoResponse.getExpandMode() == 1;
                        if (JLOTAManager.this.listener != null) {
                            JLOTAManager.this.uiHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.2.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    JLOTAManager.this.listener.queryMandatoryUpdateFinish();
                                }
                            });
                        }
                    }

                    public void onError(BaseError baseError) {
                        JLOTAManager.this.isDeviceInMandatoryUpdate = false;
                        BluetoothLog.e(JLOTAManager.TAG + "-onError- 不需要强制升级:" + baseError);
                        if (baseError.getCode() == 0 && baseError.getSubCode() == 0) {
                            TargetInfoResponse deviceInfo = JLOTAManager.this.getDeviceInfo();
                            int versionCode = deviceInfo.getVersionCode();
                            String versionName = deviceInfo.getVersionName();
                            String projectCode = deviceInfo.getProjectCode();
                            BluetoothLog.e(JLOTAManager.TAG + "不需要强制升级->设备版本号:" + versionCode + " , 设备版本名 = " + versionName + " , 设备产品ID = " + projectCode);
                            JLOTAManager.this.isJlDeviceDfuModel = deviceInfo.isMandatoryUpgrade() || deviceInfo.getExpandMode() == 1;
                        }
                        if (JLOTAManager.this.listener != null) {
                            JLOTAManager.this.uiHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.2.2.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    JLOTAManager.this.listener.queryMandatoryUpdateFinish();
                                }
                            });
                        }
                    }
                });
                return;
            }
            if (status == 0) {
                JLOTAManager.this.isJLOTAInitSuccess = false;
                if (JLOTAManager.this.listener != null) {
                    JLOTAManager.this.uiHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            JLOTAManager.this.listener.onInitFailed();
                        }
                    });
                }
            }
        }
    }

    public void checkDeviceIsInMandatoryUpdate() {
        queryMandatoryUpdate(new IActionCallback<TargetInfoResponse>() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAManager.3
            public void onSuccess(TargetInfoResponse targetInfoResponse) {
                int versionCode = targetInfoResponse.getVersionCode();
                String versionName = targetInfoResponse.getVersionName();
                String projectCode = targetInfoResponse.getProjectCode();
                BluetoothLog.e(JLOTAManager.TAG + "需要强制升级->设备版本号:" + versionCode + " , 设备版本名 = " + versionName + " , 设备产品ID = " + projectCode);
            }

            public void onError(BaseError baseError) {
                BluetoothLog.e(JLOTAManager.TAG + "-onError- 不需要强制升级:" + baseError);
                if (baseError.getCode() == 0 && baseError.getSubCode() == 0) {
                    TargetInfoResponse deviceInfo = JLOTAManager.this.getDeviceInfo();
                    int versionCode = deviceInfo.getVersionCode();
                    String versionName = deviceInfo.getVersionName();
                    String projectCode = deviceInfo.getProjectCode();
                    BluetoothLog.e(JLOTAManager.TAG + "不需要强制升级->设备版本号:" + versionCode + " , 设备版本名 = " + versionName + " , 设备产品ID = " + projectCode);
                }
            }
        });
    }

    public void initConnection(BluetoothDevice connectedDevice, BluetoothGatt connectedGatt) {
        BluetoothLog.e(TAG + "JL-OTA 更新连接状态");
        if (connectedDevice != null) {
            this.mTargetDevice = connectedDevice;
            this.mConnectGatt = connectedGatt;
            handlerBtDeviceConnection(this.mTargetDevice, 2);
        }
    }

    private void initByConnect(BluetoothGatt gatt) {
        this.mConnectGatt = gatt;
        this.mTargetDevice = gatt.getDevice();
        onBtDeviceConnection(this.mTargetDevice, 1);
    }

    public void handlerBtDeviceConnection(BluetoothDevice device, int connectStatus) {
        if (connectStatus == 0) {
            this.mTargetDevice = null;
        }
        if (connectStatus == 2) {
            this.mTargetDevice = device;
        }
        int newStatus = changeConnectStatus(connectStatus);
        BluetoothLog.d(TAG + String.format(Locale.CHINA, "JL-OTA 原连接状态: %d ==> 转换后连接状态: %d%n", Integer.valueOf(connectStatus), Integer.valueOf(newStatus)));
        BluetoothLog.d(TAG + "JL-OTA 传递连接状态 " + newStatus2Str(connectStatus) + "->" + jlStatus2Str(newStatus));
        System.out.printf(Locale.getDefault(), "JL-OTA 原连接状态: %d ==> 转换后连接状态: %d%n", Integer.valueOf(connectStatus), Integer.valueOf(newStatus));
        onBtDeviceConnection(device, newStatus);
    }

    public void handlerReceiveDeviceData(BluetoothDevice device, byte[] data) {
        StringBuilder sbAppend = new StringBuilder().append(TAG);
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[3];
        objArr[0] = RcspAuthManager.getInstance().isAuthPass() ? "是" : "否";
        objArr[1] = device;
        objArr[2] = CHexConver.byte2HexStr(data);
        BluetoothLog.d(sbAppend.append(String.format(locale, "HBand-JLFace 是否通过设备认证[%s] 接收到设备[%s]的数据[%s]", objArr)).toString());
        if (!RcspAuthManager.getInstance().isAuthPass()) {
            RcspAuthManager.getInstance().handleAuthData(device, data);
        } else {
            onReceiveDeviceData(device, data);
        }
    }

    public void handlerMtuChanged(BluetoothGatt gatt, int block, int status) {
        BluetoothLog.d(TAG + "HBand-JL-OTA handlerMtuChanged mtu = " + block);
        onMtuChanged(gatt, block, status);
    }

    public BluetoothDevice getConnectedDevice() {
        return this.mTargetDevice;
    }

    public BluetoothGatt getConnectedBluetoothGatt() {
        return this.mConnectGatt;
    }

    public void connectBluetoothDevice(BluetoothDevice bluetoothDevice) {
        Log.e(TAG, "=======连接蓝牙:" + bluetoothDevice.getName() + "|" + bluetoothDevice.getAddress() + "=======");
    }

    public void disconnectBluetoothDevice(BluetoothDevice bluetoothDevice) {
        Log.e(TAG, "=======断开蓝牙:" + bluetoothDevice.getName() + "|" + bluetoothDevice.getAddress() + "=======");
    }

    public boolean sendDataToDevice(BluetoothDevice bluetoothDevice, byte[] bytes) {
        JLBleDataManager.getInstance().writeDataByBleAsync(bytes);
        return true;
    }

    public void removeInitListener() {
        this.listener = null;
    }

    public void initSDK(OnOTASDKInitListener listener) {
        this.listener = listener;
        initByConnect(RcspAuthManager.getInstance().gatt);
    }

    public void release() {
        super.release();
    }

    private int changeConnectStatus(int status) {
        int changeStatus = 0;
        switch (status) {
            case 0:
            case 3:
                changeStatus = 0;
                break;
            case 1:
                changeStatus = 3;
                break;
            case 2:
                changeStatus = 1;
                break;
        }
        return changeStatus;
    }

    public static String jlStatus2Str(int status) {
        switch (status) {
            case 0:
                return "JL-CONNECTION_DISCONNECT";
            case 1:
                return "JL-CONNECTION_OK";
            case 2:
                return "JL-CONNECTION_FAILED";
            case 3:
                return "JL-CONNECTION_CONNECTING";
            case 4:
                return "JL-CONNECTION_CONNECTED";
            default:
                return "JL-Status-unknown";
        }
    }

    public static String newStatus2Str(int newStatus) {
        switch (newStatus) {
            case 0:
                return "STATE_DISCONNECTED";
            case 1:
                return "STATE_CONNECTING";
            case 2:
                return "STATE_CONNECTED";
            case 3:
                return "STATE_DISCONNECTING";
            default:
                return "BLE-Status-unknown";
        }
    }

    public void setReconnectAddr(String addr) {
        if (BluetoothAdapter.checkBluetoothAddress(addr)) {
            this.reConnectManager.setReconnectAddress(addr);
        }
    }
}
