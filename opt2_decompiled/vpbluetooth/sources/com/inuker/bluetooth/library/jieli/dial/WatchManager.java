package com.inuker.bluetooth.library.jieli.dial;

import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.inuker.bluetooth.library.bluetrum.abota.ABOta;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.sender.JLBleDataManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.jl_bt_ota.util.BluetoothUtil;
import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_rcsp.impl.WatchOpImpl;
import com.jieli.jl_rcsp.interfaces.listener.ThreadStateListener;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import com.jieli.jl_rcsp.model.response.ExternalFlashMsgResponse;
import com.jieli.jl_rcsp.tool.DeviceStatusManager;
import com.jieli.jl_rcsp.util.CHexConver;
import com.jieli.jl_rcsp.util.JL_Log;
import com.jieli.jl_rcsp.util.RcspUtil;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/WatchManager.class */
public class WatchManager extends WatchOpImpl {
    private static final String TAG = WatchManager.class.getSimpleName() + "-杰理-";
    private static volatile WatchManager instance;
    private final DeviceStatusManager mStatusManager;
    private BluetoothDevice mTargetDevice;
    private GetWatchMsgTask mGetWatchMsgTask;
    private final ArrayList<WatchInfo> watchInfoList;
    private ArrayList<FatFile> devFatFileList;

    private WatchManager(int func) {
        super(func);
        this.mStatusManager = DeviceStatusManager.getInstance();
        this.watchInfoList = new ArrayList<>();
        sUseNewDataHandler = true;
    }

    public void init(BluetoothDevice device) {
        this.mTargetDevice = device;
        if (RcspAuthManager.getInstance().isAuthPass()) {
            BluetoothLog.e(TAG + "-init- 初始化杰理健康库 （已通过设备认证）更新连接状态");
            Log.e(TAG, "==============================更新Watch库连接状态==============================");
            notifyBtDeviceConnection(device, 1);
            return;
        }
        RcspAuthManager.getInstance().startDevAuthByWatchManager(device);
    }

    public static int convertWatchConnectStatus(int status) {
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

    public static WatchManager getInstance() {
        if (null == instance) {
            synchronized (WatchManager.class) {
                if (null == instance) {
                    instance = new WatchManager(1);
                }
            }
        }
        return instance;
    }

    public void handlerNotifyBtDeviceConnection(BluetoothDevice device, int connectStatus) {
        int newStatus = RcspUtil.changeConnectStatus(connectStatus);
        System.out.printf(Locale.getDefault(), "HBand-JLFace 原连接状态: %d ==> 转换后连接状态: %d%n", Integer.valueOf(connectStatus), Integer.valueOf(newStatus));
        notifyBtDeviceConnection(device, newStatus);
    }

    public void handReceiveDeviceData(BluetoothDevice device, byte[] data) {
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
            notifyReceiveDeviceData(device, data);
        }
    }

    public void notifyBtDeviceConnection(BluetoothDevice bluetoothDevice, int i) {
        BluetoothLog.e(TAG + "-notifyBtDeviceConnection- address = " + bluetoothDevice.getAddress() + " state = " + i);
        super.notifyBtDeviceConnection(bluetoothDevice, i);
    }

    public boolean isWatchSystemInit(BluetoothDevice device) {
        return null != device && BluetoothUtil.deviceEquals(getConnectedDevice(), device) && isWatchSystemOk();
    }

    public DeviceInfo getDeviceInfo(BluetoothDevice device) {
        return this.mStatusManager.getDeviceInfo(device);
    }

    public ExternalFlashMsgResponse getExternalFlashMsg(BluetoothDevice device) {
        return this.mStatusManager.getExtFlashMsg(device);
    }

    public boolean isExistSDCard() {
        DeviceInfo deviceInfo = getDeviceInfo(getConnectedDevice());
        if (deviceInfo == null) {
            return false;
        }
        boolean ret = deviceInfo.isSupportSd1();
        BluetoothLog.e(TAG + "-isExistSDCard- ret = " + ret);
        return ret;
    }

    public void setDevice(BluetoothDevice device) {
        this.mTargetDevice = device;
    }

    public BluetoothDevice getConnectedDevice() {
        return this.mTargetDevice;
    }

    public boolean sendDataToDevice(BluetoothDevice device, byte[] data) {
        JLBleDataManager.getInstance().writeDataByBleAsync(data);
        return true;
    }

    public void release() {
        super.release();
        BluetoothLog.e("【杰理】-WatchManager-release");
        if (null != this.mGetWatchMsgTask) {
            this.mGetWatchMsgTask.interrupt();
            this.mGetWatchMsgTask = null;
        }
        instance = null;
    }

    public ArrayList<WatchInfo> getWatchInfoList() {
        return this.watchInfoList;
    }

    public ArrayList<FatFile> getDevFatFileList() {
        return this.devFatFileList;
    }

    public void listWatchFileList(final OnWatchOpCallback<ArrayList<WatchInfo>> callback) {
        if (this.watchInfoList.isEmpty()) {
            listWatchList(new OnWatchOpCallback<ArrayList<FatFile>>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.1
                public void onSuccess(ArrayList<FatFile> result) {
                    WatchManager.this.devFatFileList = result;
                    if (WatchManager.this.devFatFileList == null) {
                        WatchManager.this.devFatFileList = new ArrayList<>();
                    }
                    ArrayList<FatFile> list = WatchManager.this.getWatchList(result);
                    if (!list.isEmpty()) {
                        if (null == WatchManager.this.mGetWatchMsgTask) {
                            WatchManager.this.mGetWatchMsgTask = new GetWatchMsgTask(WatchManager.this, list, WatchManager.this.new CustomListWatchFileListCallback(callback), new ThreadStateListener() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.1.1
                                public void onStart(long threadId) {
                                }

                                public void onFinish(long threadId) {
                                    if (WatchManager.this.mGetWatchMsgTask != null && WatchManager.this.mGetWatchMsgTask.getId() == threadId) {
                                        WatchManager.this.mGetWatchMsgTask = null;
                                    }
                                }
                            });
                            WatchManager.this.mGetWatchMsgTask.start();
                            return;
                        }
                        return;
                    }
                    WatchManager.this.watchInfoList.clear();
                    if (callback != null) {
                        callback.onSuccess(new ArrayList());
                    }
                }

                public void onFailed(BaseError error) {
                    WatchManager.this.watchInfoList.clear();
                    if (callback != null) {
                        callback.onFailed(error);
                    }
                }
            });
        } else if (callback != null) {
            callback.onSuccess(this.watchInfoList);
        }
    }

    public void updateWatchFileListByDevice(OnWatchOpCallback<ArrayList<WatchInfo>> callback) {
        this.watchInfoList.clear();
        listWatchFileList(callback);
    }

    public void getCurrentWatchMsg(final OnWatchOpCallback<WatchInfo> callback) {
        listWatchFileList(new OnWatchOpCallback<ArrayList<WatchInfo>>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.2
            public void onSuccess(ArrayList<WatchInfo> result) {
                if (!result.isEmpty()) {
                    WatchManager.this.getCurrentWatchInfo(new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.2.1
                        public void onSuccess(FatFile result2) {
                            WatchInfo info = WatchManager.this.getWatchInfoByFatFile(result2);
                            if (info != null) {
                                if (callback != null) {
                                    callback.onSuccess(info);
                                }
                            } else if (callback != null) {
                                callback.onFailed(new BaseError(ABOta.ERROR_CODE_DEVICE_REFUSED, "not found watch info."));
                            }
                        }

                        public void onFailed(BaseError error) {
                            if (callback != null) {
                                callback.onFailed(error);
                            }
                        }
                    });
                } else if (callback != null) {
                    callback.onFailed(new BaseError(ABOta.ERROR_CODE_DEVICE_REFUSED, "not found watch info."));
                }
            }

            public void onFailed(BaseError error) {
                if (callback != null) {
                    callback.onFailed(error);
                }
            }
        });
    }

    public void enableWatchCustomBg(final String fatFilePath, final OnWatchOpCallback<FatFile> callback) {
        enableCustomWatchBg(fatFilePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.3
            public void onSuccess(final FatFile result) {
                WatchManager.this.getCurrentWatchMsg(new OnWatchOpCallback<WatchInfo>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.3.1
                    public void onSuccess(WatchInfo result2) {
                        result2.setCustomBgFatPath(fatFilePath);
                        JL_Log.d(WatchManager.TAG, "-enableWatchCustomBg- result = " + result2);
                        if (callback != null) {
                            callback.onSuccess(result);
                        }
                    }

                    public void onFailed(BaseError error) {
                        if (callback != null) {
                            callback.onFailed(error);
                        }
                    }
                });
            }

            public void onFailed(BaseError error) {
                if (callback != null) {
                    callback.onFailed(error);
                }
            }
        });
    }

    public void deleteWatch(final WatchInfo info, final OnFatFileProgressListener listener) {
        if (null == info || null == info.getFatFile()) {
            return;
        }
        JL_Log.d(TAG, "-deleteWatch- info = " + info.toString());
        if (info.hasCustomBgFatPath()) {
            JL_Log.w(TAG, "-deleteWatch- CustomBgFatPath = " + info.getCustomBgFatPath());
            deleteWatchFile(info.getCustomBgFatPath(), new AnonymousClass4(listener, info));
        } else {
            JL_Log.d(TAG, "-deleteWatch- 111 deleteWatchFile = " + info.getFatFile().getPath());
            deleteWatchFile(info.getFatFile().getPath(), new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.5
                public void onStart(String filePath) {
                    JL_Log.i(WatchManager.TAG, "-deleteWatch- 111 onStart = " + filePath);
                    if (listener != null) {
                        listener.onStart(filePath);
                    }
                }

                public void onProgress(float progress) {
                    JL_Log.d(WatchManager.TAG, "-deleteWatch-111  onProgress = " + progress);
                    if (listener != null) {
                        listener.onProgress(progress);
                    }
                }

                public void onStop(int result) {
                    JL_Log.w(WatchManager.TAG, "-deleteWatch- 111 onStop = " + result);
                    if (result == 0 && (info.getFatFile().getName().startsWith("WATCH") || info.getFatFile().getName().startsWith("BGP"))) {
                        WatchManager.this.updateWatchFileListByDevice(new OnWatchOpCallback<ArrayList<WatchInfo>>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.5.1
                            public void onSuccess(ArrayList<WatchInfo> result2) {
                                if (listener != null) {
                                    listener.onStop(0);
                                }
                            }

                            public void onFailed(BaseError error) {
                                if (listener != null) {
                                    listener.onStop(12288);
                                }
                            }
                        });
                    } else if (listener != null) {
                        listener.onStop(result);
                    }
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.inuker.bluetooth.library.jieli.dial.WatchManager$4, reason: invalid class name */
    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/WatchManager$4.class */
    class AnonymousClass4 implements OnFatFileProgressListener {
        final /* synthetic */ OnFatFileProgressListener val$listener;
        final /* synthetic */ WatchInfo val$info;

        AnonymousClass4(OnFatFileProgressListener onFatFileProgressListener, WatchInfo watchInfo) {
            this.val$listener = onFatFileProgressListener;
            this.val$info = watchInfo;
        }

        public void onStart(String filePath) {
            JL_Log.i(WatchManager.TAG, "-deleteWatch- onStart = " + filePath);
            if (this.val$listener != null) {
                this.val$listener.onStart(this.val$info.getFatFile().getPath());
            }
        }

        public void onProgress(float progress) {
            float cProgress = (progress * 100.0f) / 200.0f;
            JL_Log.d(WatchManager.TAG, "-deleteWatch- onProgress = " + cProgress);
            if (this.val$listener != null) {
                this.val$listener.onProgress(cProgress);
            }
        }

        public void onStop(int result) {
            JL_Log.i(WatchManager.TAG, "-deleteWatch- onStop = " + result);
            if (result == 0) {
                JL_Log.d(WatchManager.TAG, "-deleteWatch- deleteWatchFile = " + this.val$info.getFatFile().getPath());
                WatchManager.this.deleteWatchFile(this.val$info.getFatFile().getPath(), new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.4.1
                    public void onStart(String filePath) {
                        JL_Log.i(WatchManager.TAG, "-deleteWatch- onStart = " + filePath);
                    }

                    public void onProgress(float progress) {
                        float cProgress = ((progress + 100.0f) * 100.0f) / 200.0f;
                        JL_Log.d(WatchManager.TAG, "-deleteWatch- onProgress = " + cProgress);
                        if (AnonymousClass4.this.val$listener != null) {
                            AnonymousClass4.this.val$listener.onProgress(cProgress);
                        }
                    }

                    public void onStop(final int result2) {
                        JL_Log.w(WatchManager.TAG, "-deleteWatch- onStop = " + result2);
                        WatchManager.this.updateWatchFileListByDevice(new OnWatchOpCallback<ArrayList<WatchInfo>>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchManager.4.1.1
                            public void onSuccess(ArrayList<WatchInfo> result3) {
                                if (AnonymousClass4.this.val$listener != null) {
                                    AnonymousClass4.this.val$listener.onStop(result2);
                                }
                            }

                            public void onFailed(BaseError error) {
                                if (AnonymousClass4.this.val$listener != null) {
                                    AnonymousClass4.this.val$listener.onStop(12288);
                                }
                            }
                        });
                    }
                });
            } else if (this.val$listener != null) {
                this.val$listener.onStop(result);
            }
        }
    }

    private ArrayList<FatFile> getWatchList(ArrayList<FatFile> list) {
        if (null == list) {
            return new ArrayList<>();
        }
        ArrayList<FatFile> result = new ArrayList<>();
        for (FatFile fatFile : list) {
            if (fatFile.getName().startsWith("WATCH") || fatFile.getName().startsWith(WatchConstant.DIR_WATCH)) {
                result.add(fatFile);
            }
        }
        return result;
    }

    public WatchInfo getWatchInfoByFatFile(FatFile fatFile) {
        if (fatFile == null || this.watchInfoList.isEmpty()) {
            return null;
        }
        WatchInfo result = null;
        for (WatchInfo watchInfo : this.watchInfoList) {
            JL_Log.d(TAG, "-getWatchInfoByList- watchInfo = " + watchInfo + ", target = " + fatFile);
            if (fatFile.getPath() != null && fatFile.getPath().equals(watchInfo.getFatFile().getPath())) {
                result = watchInfo;
                break;
            }
        }
        return result;
    }

    public WatchInfo getWatchInfoByPath(String fatFilePath) {
        if (null == fatFilePath || null == this.devFatFileList || this.devFatFileList.isEmpty()) {
            return null;
        }
        FatFile fatFile = null;
        for (FatFile file : this.devFatFileList) {
            if (fatFilePath.toUpperCase().equals(file.getPath())) {
                fatFile = file;
                break;
            }
        }
        return getWatchInfoByFatFile(fatFile);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/WatchManager$CustomListWatchFileListCallback.class */
    private final class CustomListWatchFileListCallback implements OnWatchOpCallback<ArrayList<WatchInfo>> {
        private final OnWatchOpCallback<ArrayList<WatchInfo>> mCallback;

        public CustomListWatchFileListCallback(OnWatchOpCallback<ArrayList<WatchInfo>> callback) {
            this.mCallback = callback;
        }

        public void onSuccess(ArrayList<WatchInfo> result) {
            WatchManager.this.watchInfoList.clear();
            WatchManager.this.watchInfoList.addAll(result);
            if (this.mCallback != null) {
                this.mCallback.onSuccess(result);
            }
        }

        public void onFailed(BaseError error) {
            WatchManager.this.watchInfoList.clear();
            if (this.mCallback != null) {
                this.mCallback.onFailed(error);
            }
        }
    }
}
