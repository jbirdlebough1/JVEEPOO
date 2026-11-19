package com.inuker.bluetooth.library.jieli.ota;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.BluetoothContext;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleMtuResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.jieli.jl_bt_ota.interfaces.BtEventCallback;
import com.jieli.jl_bt_ota.interfaces.IUpgradeCallback;
import com.jieli.jl_bt_ota.model.base.BaseError;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_bt_ota.util.CHexConver;
import com.jieli.jl_bt_ota.util.FileUtil;
import com.jieli.jl_bt_ota.util.JL_Log;
import com.jieli.jl_fatfs.utils.ZipUtil;
import java.io.File;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAHolder.class */
public class JLOTAHolder {
    private String address;
    private String dfuLangAddress = "";
    private boolean isFindDFULangDevice = false;
    public boolean hasCallReconnect = false;
    private OnJLDeviceOTAListener listener;
    BluetoothClient mClient;
    public static final String TAG = JLOTAHolder.class.getSimpleName();
    public static boolean isOTAUpgrading = false;
    public static boolean isReconnect = false;
    public static final UUID JL_BLE_UUID_SERVICE = UUID.fromString("0000ae00-0000-1000-8000-00805F9B34FB");
    public static final UUID JL_BLE_UUID_WRITE = UUID.fromString("0000ae01-0000-1000-8000-00805F9B34FB");
    public static final UUID JL_BLE_UUID_NOTIFICATION = UUID.fromString("0000ae02-0000-1000-8000-00805F9B34FB");

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAHolder$OnJLDeviceOTAListener.class */
    public interface OnJLDeviceOTAListener {
        void onOTAStart();

        void onProgress(float f);

        void onNeedReconnect(String str, String str2, boolean z);

        void onDFULangConnectSuccess(String str);

        void onDFULangConnectFailed(String str);

        void onOTASuccess();

        void onOTAFailed(BaseError baseError);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/ota/JLOTAHolder$InstanceHolder.class */
    private static final class InstanceHolder {
        static final JLOTAHolder instance = new JLOTAHolder();

        private InstanceHolder() {
        }
    }

    public static JLOTAHolder getInstance() {
        return InstanceHolder.instance;
    }

    private JLOTAHolder() {
    }

    public static boolean isNeedInitOTAWithDfuLang() {
        return isOTAUpgrading && isReconnect;
    }

    public void release() {
        isOTAUpgrading = false;
        isReconnect = false;
        this.hasCallReconnect = false;
        this.isFindDFULangDevice = false;
        this.address = null;
        this.dfuLangAddress = null;
    }

    public void removeOTAListener() {
        this.listener = null;
    }

    public void checkInit2StartOTA(final String filePath, final OnJLDeviceOTAListener listener) {
        if (RcspAuthManager.getInstance().isAuthPass() && JLOTAManager.getInstance().isJLOTAInitSuccess()) {
            Log.d(TAG, "-checkInit2StartOTA- 设备认证已通过且杰理OTA库已初始化");
            startOTA(filePath, listener);
        } else {
            JLOTAManager.getInstance().initSDK(new JLOTAManager.OnOTASDKInitListener() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.1
                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void onInitSuccess() {
                    JLOTAHolder.this.startOTA(filePath, listener);
                }

                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void onInitFailed() {
                    JLOTAManager.getInstance().removeInitListener();
                }

                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void queryMandatoryUpdateFinish() {
                    JLOTAManager.getInstance().removeInitListener();
                }
            });
        }
    }

    public void checkOtaInit() {
        if (RcspAuthManager.getInstance().isAuthPass() && JLOTAManager.getInstance().isJLOTAInitSuccess()) {
            Log.d(TAG, "-checkInit2StartOTA- 设备认证已通过且杰理OTA库已初始化");
        } else {
            JLOTAManager.getInstance().initSDK(new JLOTAManager.OnOTASDKInitListener() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.2
                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void onInitSuccess() {
                }

                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void onInitFailed() {
                    JLOTAManager.getInstance().removeInitListener();
                }

                @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                public void queryMandatoryUpdateFinish() {
                    JLOTAManager.getInstance().removeInitListener();
                }
            });
        }
    }

    public void startOTA(String filePath, OnJLDeviceOTAListener listener) {
        if (!JLOTAManager.getInstance().isOTA() && filePath.endsWith(JLOTAManager.OTA_ZIP_SUFFIX)) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf("/"));
            String fileName = filePath.replace(dirPath + File.separator, "").trim();
            Log.d(TAG, "startOTA==================dirPath : " + dirPath);
            Log.d(TAG, "startOTA==================fileName : " + fileName);
            try {
                ZipUtil.unZipFolder(filePath, dirPath);
                String otaFilePath = obtainUpdateFilePath(dirPath, JLOTAManager.OTA_FILE_SUFFIX);
                Log.d(TAG, "==================unZipFolder : " + otaFilePath);
                File resource = new File(dirPath + "/res.ori");
                if (resource.exists()) {
                    FileUtil.deleteFile(resource);
                }
                if (null != otaFilePath) {
                    otaFirmware(otaFilePath, listener);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void otaFirmware(final String filePath, final OnJLDeviceOTAListener listener) {
        this.listener = listener;
        JLOTAManager.getInstance().getBluetoothOption().setFirmwareFilePath(filePath);
        JLOTAManager.getInstance().registerBluetoothCallback(new BtEventCallback() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.3
            public void onConnection(BluetoothDevice bluetoothDevice, int i) {
                super.onConnection(bluetoothDevice, i);
            }
        });
        JLOTAManager.getInstance().startOTA(new IUpgradeCallback() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.4
            public void onStartOTA() {
                Log.d(JLOTAHolder.TAG, "-onStartOTA- 开始OTA升级");
                JLOTAHolder.isOTAUpgrading = true;
                listener.onOTAStart();
            }

            public void onNeedReconnect(String devAddr, boolean isUseAdv) {
                JLOTAHolder.isOTAUpgrading = true;
                JLOTAHolder.this.hasCallReconnect = true;
                JLOTAHolder.this.address = devAddr;
                byte[] data = BluetoothUtil.addressCovertToByteArray(devAddr);
                int value = CHexConver.byteToInt(data[data.length - 1]) + 1;
                data[data.length - 1] = CHexConver.intToByte(value);
                JLOTAHolder.this.dfuLangAddress = BluetoothUtil.hexDataCovetToAddress(data);
                Log.d(JLOTAHolder.TAG, "-onNeedReconnect- 【重新开始连接DFULang 设备】 old mac = " + devAddr + " , new mac = " + JLOTAHolder.this.dfuLangAddress);
                JLOTAManager.getInstance().setReconnectAddr(JLOTAHolder.this.dfuLangAddress);
                listener.onNeedReconnect(JLOTAHolder.this.address, JLOTAHolder.this.dfuLangAddress, true);
                JLOTAHolder.this.scanDfuLang2Connected(JLOTAHolder.this.dfuLangAddress);
            }

            public void onProgress(int i, float v) {
                Log.d(JLOTAHolder.TAG, "-otaFirmware- onProgress >>>>>> type = " + i + ", progress = " + v);
                listener.onProgress(v);
            }

            public void onStopOTA() {
                JL_Log.e(JLOTAHolder.TAG, "-otaFirmware- :: onStopOTA");
                listener.onOTASuccess();
                JLOTAHolder.this.release();
                if (filePath != null) {
                    FileUtil.deleteFile(new File(filePath));
                }
            }

            public void onCancelOTA() {
                listener.onOTAFailed(new BaseError(153, "Ota upgrade is canceled"));
                FileUtil.deleteFile(new File(filePath));
            }

            public void onError(BaseError baseError) {
                Log.e(JLOTAHolder.TAG, "-otaFirmware- :: onError, baseError = " + baseError);
                JLOTAHolder.this.release();
                listener.onOTAFailed(baseError);
                if (filePath != null) {
                    FileUtil.deleteFile(new File(filePath));
                }
            }
        });
    }

    public static String obtainUpdateFilePath(String dirPath, String suffix) {
        if (null == dirPath) {
            return null;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            return null;
        }
        if (dir.isFile()) {
            if (dirPath.endsWith(suffix)) {
                return dirPath;
            }
            return null;
        }
        if (dir.isDirectory()) {
            String filePath = null;
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    filePath = obtainUpdateFilePath(file.getPath(), suffix);
                    if (filePath != null) {
                        break;
                    }
                }
            }
            return filePath;
        }
        return null;
    }

    private void scanDfuLang2Connected(final String dfuLangAddress) {
        this.isFindDFULangDevice = false;
        JLOTAManager.getInstance().setReconnectAddr(dfuLangAddress);
        isOTAUpgrading = true;
        Log.d(TAG, "-scanDfuLang2Connected- 【搜索并连接DFULang设备】 dfuLangAddress = " + dfuLangAddress);
        if (dfuLangAddress == null) {
            return;
        }
        BLEScanner.getInstance().initBLE(BluetoothContext.get());
        BLEScanner.getInstance().setListener(new BLEScanner.OnBLEDeviceScanListener() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.5
            @Override // com.inuker.bluetooth.library.jieli.ota.BLEScanner.OnBLEDeviceScanListener
            public void onStartScan() {
                JLOTAHolder.this.isFindDFULangDevice = false;
                Log.d(JLOTAHolder.TAG, "-onStartScan- 【搜索并连接DFULang设备】 dfuLangAddress = " + dfuLangAddress);
            }

            @Override // com.inuker.bluetooth.library.jieli.ota.BLEScanner.OnBLEDeviceScanListener
            public void onDeviceFound(BluetoothDevice device) {
                String deviceInfo = "device [name = " + device.getName() + " , address = " + device.getAddress() + "]";
                Log.d(JLOTAHolder.TAG, "-onDeviceFound- 【搜索并连接DFULang设备】 " + deviceInfo);
                if (device.getAddress().equals(dfuLangAddress)) {
                    JLOTAHolder.this.isFindDFULangDevice = true;
                    BLEScanner.getInstance().stopScanBluetoothDevice();
                    JLOTAHolder.this.connectDfuLangDevice(dfuLangAddress);
                    JLOTAHolder.isReconnect = true;
                    Log.d(JLOTAHolder.TAG, "===杰理OTA,找到设备重连===");
                }
            }

            @Override // com.inuker.bluetooth.library.jieli.ota.BLEScanner.OnBLEDeviceScanListener
            public void onStopScan(List<BluetoothDevice> scanDevices) {
                Log.d(JLOTAHolder.TAG, "-onStopScan- 【搜索并连接DFULang设备】 dfuLangAddress = " + dfuLangAddress);
                if (!JLOTAHolder.this.isFindDFULangDevice) {
                    Log.d(JLOTAHolder.TAG, "#######################没有搜索到设备直接连接设备 dfuLangAddress = " + dfuLangAddress);
                }
            }
        });
        BLEScanner.getInstance().startScanBluetoothDevice();
    }

    private void connectDfuLangDevice(final String dfuLangAddress) {
        BleConnectOptions options = new BleConnectOptions.Builder().setConnectRetry(3).setConnectTimeout(30000).setServiceDiscoverRetry(3).setServiceDiscoverTimeout(20000).build();
        if (this.mClient == null) {
            this.mClient = new BluetoothClient(BluetoothContext.get());
        }
        this.mClient.connect(dfuLangAddress, options, new BleConnectResponse() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.6
            @Override // com.inuker.bluetooth.library.connect.response.BleTResponse
            public void onResponse(int code, BleGattProfile data) {
                if (code == 0) {
                    if (JLOTAHolder.this.listener != null) {
                        JLOTAHolder.this.listener.onDFULangConnectSuccess(dfuLangAddress);
                    }
                    RcspAuthManager.getInstance().setAuthPass(false);
                    JLOTAHolder.this.mClient.notify(dfuLangAddress, JLOTAHolder.JL_BLE_UUID_SERVICE, JLOTAHolder.JL_BLE_UUID_NOTIFICATION, new BleNotifyResponse() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.6.1
                        @Override // com.inuker.bluetooth.library.connect.response.BleNotifyResponse
                        public void onNotify(UUID service, UUID character, byte[] value) {
                        }

                        @Override // com.inuker.bluetooth.library.connect.response.BleResponse
                        public void onResponse(int code2) {
                            if (code2 == 0) {
                                Log.i(JLOTAHolder.TAG, "杰理通知打开成功->申请MTU");
                                JLOTAHolder.this.mClient.requestMtu(dfuLangAddress, 247, new BleMtuResponse() { // from class: com.inuker.bluetooth.library.jieli.ota.JLOTAHolder.6.1.1
                                    @Override // com.inuker.bluetooth.library.connect.response.BleTResponse
                                    public void onResponse(int code3, Integer data2) {
                                        if (code3 == 0) {
                                            Log.i(JLOTAHolder.TAG, "MTU申请成功 mtu = " + data2);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    return;
                }
                if (JLOTAHolder.this.listener != null) {
                    JLOTAHolder.this.listener.onDFULangConnectFailed(dfuLangAddress);
                }
            }
        });
    }
}
