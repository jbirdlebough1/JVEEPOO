package com.inuker.bluetooth.library.connect.request;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.util.Log;
import com.inuker.bluetooth.library.connect.BleConnectWorker;
import com.inuker.bluetooth.library.connect.response.BleGeneralResponse;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager;
import com.inuker.bluetooth.library.jieli.dial.WatchManager;
import com.inuker.bluetooth.library.jieli.ota.JLOTAManager;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/connect/request/BleJLDeviceAuthRequest.class */
public class BleJLDeviceAuthRequest extends BleRequest {
    private static final String TAG = BleJLDeviceAuthRequest.class.getSimpleName() + "-杰理-";
    private boolean isJLFileSystemInit;

    public BleJLDeviceAuthRequest(BleGeneralResponse response) {
        super(response);
        this.isJLFileSystemInit = false;
    }

    @Override // com.inuker.bluetooth.library.connect.request.BleRequest
    public void processRequest() {
        Log.e(TAG, "杰理设备认证 -- processRequest : " + getCurrentStatus());
        switch (getCurrentStatus()) {
            case 0:
                onRequestCompleted(-1);
                break;
            case 19:
                if (RcspAuthManager.getInstance().isAuthPass()) {
                    Log.e(TAG, "杰理设备认证已通过");
                    onRequestCompleted(0);
                } else {
                    startOnlyDeviceAuth();
                }
                break;
        }
    }

    private void startOnlyDeviceAuth() {
        RcspAuthManager.getInstance().init(getGatt());
        RcspAuthManager.getInstance().startAuth(getDevice(), new RcspAuthManager.RcspAuthListener() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.1
            @Override // com.inuker.bluetooth.library.jieli.RcspAuthManager.RcspAuthListener
            public void onRcspAuthSuccess() {
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理设备认证-->成功");
                if (RcspAuthManager.getInstance().isAuthPass() && JLOTAManager.getInstance().isJLOTAInitSuccess()) {
                    Log.d(BleJLDeviceAuthRequest.TAG, "-checkInit2StartOTA- 设备认证已通过且杰理OTA库已初始化");
                    BleJLDeviceAuthRequest.this.onRequestCompleted(0);
                } else {
                    JLOTAManager.getInstance().initSDK(new JLOTAManager.OnOTASDKInitListener() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.1.1
                        @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                        public void onInitSuccess() {
                        }

                        @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                        public void onInitFailed() {
                            JLOTAManager.getInstance().removeInitListener();
                            BleJLDeviceAuthRequest.this.onRequestCompleted(0);
                        }

                        @Override // com.inuker.bluetooth.library.jieli.ota.JLOTAManager.OnOTASDKInitListener
                        public void queryMandatoryUpdateFinish() {
                            BleJLDeviceAuthRequest.this.onRequestCompleted(0);
                        }
                    });
                }
            }

            @Override // com.inuker.bluetooth.library.jieli.RcspAuthManager.RcspAuthListener
            public void onRcspAuthFailed() {
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理设备认证-->失败");
                BleJLDeviceAuthRequest.this.onRequestCompleted(-1);
            }
        });
    }

    private void startDeviceAuth() {
        RcspAuthManager.getInstance().init(getGatt());
        RcspAuthManager.getInstance().setRcspAuthListener(new RcspAuthManager.RcspAuthListener() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.2
            @Override // com.inuker.bluetooth.library.jieli.RcspAuthManager.RcspAuthListener
            public void onRcspAuthSuccess() {
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理设备认证-->成功");
                BleJLDeviceAuthRequest.this.mHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BleJLDeviceAuthRequest.this.onRequestCompleted(0);
                    }
                });
            }

            @Override // com.inuker.bluetooth.library.jieli.RcspAuthManager.RcspAuthListener
            public void onRcspAuthFailed() {
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理设备认证-->失败");
                BleJLDeviceAuthRequest.this.mHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.2.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BleJLDeviceAuthRequest.this.onRequestCompleted(-1);
                    }
                });
            }
        });
        Log.e(TAG, "STEP.1-【START】==============================开始设备认证============================== registerOnWatchCallback");
        WatchManager.getInstance().registerOnWatchCallback(new OnWatchCallback() { // from class: com.inuker.bluetooth.library.connect.request.BleJLDeviceAuthRequest.3
            public void onWatchSystemInit(int code) {
                super.onWatchSystemInit(code);
                Log.e(BleJLDeviceAuthRequest.TAG, "STEP.3-【START】==============================系统初始化回调==============================");
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理文件系统初始化==============================code = " + code);
                BleJLDeviceAuthRequest.this.isJLFileSystemInit = code == 0;
                if (BleJLDeviceAuthRequest.this.isJLFileSystemInit) {
                    Log.e(BleJLDeviceAuthRequest.TAG, "杰理文件系统初始化成功");
                    Log.e(BleJLDeviceAuthRequest.TAG, "STEP.3-【E N D】==============================系统初始化成功==============================");
                    Log.e(BleJLDeviceAuthRequest.TAG, "杰理文件系统初始化成功------->开始获取文件列表");
                    JLWatchFaceManager.getInstance().initWatchServiceDials();
                    return;
                }
                Log.e(BleJLDeviceAuthRequest.TAG, "杰理文件系统初始化失败");
                Log.e(BleJLDeviceAuthRequest.TAG, "STEP.3-【E N D】==============================系统初始化失败==============================");
            }

            public void onResourceUpdateUnfinished(BluetoothDevice bluetoothDevice) {
                super.onResourceUpdateUnfinished(bluetoothDevice);
                Log.e("Test1", "onResourceUpdateUnfinished");
            }

            public void onMandatoryUpgrade(BluetoothDevice bluetoothDevice) {
                super.onMandatoryUpgrade(bluetoothDevice);
                Log.e("Test1", "onMandatoryUpgrade");
            }
        });
        Log.e(TAG, "==============================开始设备认证==============================");
        WatchManager.getInstance().init(getDevice());
    }

    public BleConnectWorker getRealBleWorker() {
        return (BleConnectWorker) this.mWorker;
    }

    public BluetoothGatt getGatt() {
        return getRealBleWorker().getBluetoothGatt();
    }

    public BluetoothDevice getDevice() {
        return getRealBleWorker().getBluetoothDevice();
    }
}
