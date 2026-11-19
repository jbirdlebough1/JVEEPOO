package com.inuker.bluetooth.library.jieli.dial;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.bmp_convert.BmpConvert;
import com.jieli.bmp_convert.ConvertResult;
import com.jieli.bmp_convert.OnConvertListener;
import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_fatfs.utils.FatUtil;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchCallback;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.jieli.jl_rcsp.model.device.DeviceInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchFaceManager.class */
public class JLWatchFaceManager {
    public static final String SYSTEM_DIAL = "00000000-0000-0000-0000-000000000000";
    public static final String PIC_DIAL = "11111111-1111-1111-1111-111111111111";
    public static final String SERVICE_DIAL = "22222222-2222-2222-2222-222222222222";
    public FatFile picFatFile;
    private String bigBgInPath;
    private String bigBgOutPath;
    private String smallBgInPath;
    private String smallBgOutPath;
    public OnJLWatchFileSystemInitListener initListener;
    public OnWatchDialInfoGetListener dialInfoGetListener;
    private static final String JL_PIC_BGP_BIG = "bgp_w001";
    private static final String JL_PIC_BGP_SMALL = "bgp_w000";
    public static final String AI_PREVIEW_DIAL_FACE_FILE = "bgp_w_ai";
    private JLTransferPicDialListener listener;
    public static final String TAG = JLWatchFaceManager.class.getSimpleName() + "-杰理-";
    public static volatile boolean isJLFileSystemInit = false;
    List<FatFile> serverFatFiles = new ArrayList();
    List<FatFile> systemFatFiles = new ArrayList();
    List<FatFile> allFatFile = new ArrayList();
    List<FatFile> originFatFile = new ArrayList();
    public FatFile currentFatFile = null;
    String jlBGPFilePath = null;
    String jlBGPFileAIPreviewPath = null;
    String jlBGPFileScalePath = null;
    private final BmpConvert mBmpConvert = new BmpConvert();
    private volatile boolean isGettingFileSystem = false;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    public String currentServerDialName = null;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchFaceManager$Holder.class */
    public static final class Holder {
        private static final JLWatchFaceManager INSTANCE = new JLWatchFaceManager();
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchFaceManager$JLTransferPicDialListener.class */
    public interface JLTransferPicDialListener {
        void onLowPower();

        void onJLTransferPicDialStart();

        void onTransferPicDialProgress(int i);

        void onScaleBGPFileTransferComplete();

        void onAIPreviewTransferComplete();

        void onBigBGPFileTransferComplete();

        void onTransferComplete();

        void onTransferError(int i, String str);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchFaceManager$OnJLWatchFileSystemInitListener.class */
    public interface OnJLWatchFileSystemInitListener {
        void onSuccess();

        void onAuthPass();

        void onFailed(BaseError baseError);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchFaceManager$OnWatchDialInfoGetListener.class */
    public interface OnWatchDialInfoGetListener {
        void onGettingWatchDialInfo();

        void onWatchDialInfoGetStart();

        void onWatchDialInfoGetComplete();

        void onWatchDialInfoGetSuccess(List<FatFile> list, List<FatFile> list2, FatFile fatFile);

        void onWatchDialInfoGetFailed(BaseError baseError);
    }

    private JLWatchFaceManager() {
    }

    public static JLWatchFaceManager getInstance() {
        return Holder.INSTANCE;
    }

    public void release() {
        BluetoothLog.e("【杰理】-JLWatchFaceManager-release");
        try {
            this.systemFatFiles.clear();
            this.serverFatFiles.clear();
            this.picFatFile = null;
            this.currentFatFile = null;
            this.currentServerDialName = "";
            this.listener = null;
            this.dialInfoGetListener = null;
            isJLFileSystemInit = false;
            WatchManager.getInstance().release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isJLFatFileSystemInitSuccess() {
        return this.systemFatFiles != null && this.systemFatFiles.size() > 0;
    }

    public void checkJLSDKAndInit(OnWatchDialInfoGetListener listener) {
        this.dialInfoGetListener = listener;
        Log.d(TAG, "杰理---- isJLFileSystemInit = " + isJLFileSystemInit + " isJLAuthPass = " + RcspAuthManager.getInstance().isAuthPass());
        this.dialInfoGetListener.onWatchDialInfoGetStart();
        if (isJLFileSystemInit && RcspAuthManager.getInstance().isAuthPass()) {
            getWatchDialInfo(listener);
        } else {
            initWatchManger();
        }
    }

    public String getCurrentServerDialName() {
        if (this.serverFatFiles.isEmpty()) {
            return "";
        }
        return this.serverFatFiles.get(0).getName();
    }

    public String getPicDialName() {
        if (this.picFatFile == null) {
            return "";
        }
        return this.picFatFile.getName();
    }

    public void setCurrentServerDialName(String dialName) {
        this.currentServerDialName = dialName;
    }

    public void checkJLSDKAndInit() {
        checkJLSDKAndInit(null);
    }

    private void initWatchManger() {
        Log.d(TAG, "JL-SDK 初始化文件系统====================>");
        WatchManager.getInstance().registerOnWatchCallback(new OnWatchCallback() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.1
            public void onWatchSystemInit(int code) {
                super.onWatchSystemInit(code);
                JLWatchFaceManager.isJLFileSystemInit = code == 0;
                Log.d(JLWatchFaceManager.TAG, "JL-SDK 初始化文件系统====================> code = " + code);
                if (JLWatchFaceManager.isJLFileSystemInit) {
                    Log.d(JLWatchFaceManager.TAG, "JL-SDK 系统初始化成功");
                    JLWatchFaceManager.this.getWatchDialInfo(JLWatchFaceManager.this.dialInfoGetListener);
                } else {
                    if (JLWatchFaceManager.this.initListener != null) {
                        JLWatchFaceManager.this.initListener.onFailed(new BaseError(-1, "系统初始化失败"));
                    }
                    Log.d(JLWatchFaceManager.TAG, "JL-SDK 系统初始化失败");
                }
            }
        });
        WatchManager.getInstance().init(RcspAuthManager.getInstance().device);
    }

    public List<FatFile> getServerFatFiles() {
        return this.serverFatFiles;
    }

    public List<FatFile> getSystemFatFiles() {
        return this.systemFatFiles;
    }

    public int getSystemFatFileSize() {
        return this.systemFatFiles.size();
    }

    public boolean isSystemDailGetSuccess() {
        return this.systemFatFiles.size() > 0;
    }

    private void printFatFiles() {
        StringBuilder sb = new StringBuilder("杰理表盘系统更新=============================Start");
        sb.append("\t\t\t\n").append("[照片表盘] picFatFile = ").append(this.picFatFile == null ? "NULL" : this.picFatFile.getPath());
        Iterator<FatFile> it = this.serverFatFiles.iterator();
        while (it.hasNext()) {
            FatFile serverFatFile = it.next();
            sb.append("\t\t\t\n").append("[服务器表盘] serverFatFile = ").append(serverFatFile == null ? "NULL" : serverFatFile.getPath());
        }
        Iterator<FatFile> it2 = this.systemFatFiles.iterator();
        while (it2.hasNext()) {
            FatFile systemFatFile = it2.next();
            sb.append("\t\t\t\n").append("[系统表盘] systemFatFile = ").append(systemFatFile == null ? "NULL" : systemFatFile.getPath());
        }
        sb.append("\t\t\t\n").append("[当前的服务器表盘] serverFatFile = ").append(this.currentServerDialName == null ? "【还未设置】" : this.currentServerDialName).append("\n").append("杰理表盘系统更新=============================End");
        Log.e(TAG, sb.toString());
    }

    public static void switch2ServerDial() {
        String filePath = getInstance().getCurrentServerDialName();
        if (!TextUtils.isEmpty(filePath)) {
            Log.e(TAG, "切换到市场表盘：" + filePath);
            getInstance().setDial("/" + filePath);
        } else {
            Log.e(TAG, "不存在服务器表盘");
        }
    }

    public static void switch2SystemDial(int index) {
        int count = getInstance().getSystemFatFiles().size();
        if (index >= 0 && index < count) {
            String filePath = getInstance().getSystemFatFiles().get(index).getPath();
            Log.e(TAG, "切换到系统表盘：" + filePath);
            getInstance().setDial(filePath);
            return;
        }
        Log.e(TAG, "切换到系统表盘错误，系统表盘个数：" + count + " ,切换的表盘index:" + index + "不在0-" + (count - 1) + "范围内");
    }

    public static void switch2SystemDial() {
        String filePath = getInstance().getSystemFatFiles().get(0).getPath();
        Log.e(TAG, "切换到系统表盘：" + filePath);
        getInstance().setDial(filePath);
    }

    public static void switch2PicDial() {
        String filePath = getInstance().getPicDialName();
        if (!TextUtils.isEmpty(filePath)) {
            Log.i(TAG, "切换到照片表盘：" + filePath);
            getInstance().setDial("/" + filePath);
        } else {
            Log.e(TAG, "不存在照片表盘");
        }
    }

    public void getWatchDialInfo(OnWatchDialInfoGetListener listener) {
        if (this.isGettingFileSystem) {
            if (this.dialInfoGetListener != null) {
                this.dialInfoGetListener.onGettingWatchDialInfo();
            }
        } else {
            Log.e(TAG, "------开始更新表盘系统------");
            this.dialInfoGetListener = listener;
            Log.e(TAG, "getWatchDialInfo=====================> listWatchList");
            this.isGettingFileSystem = true;
            WatchManager.getInstance().listWatchList(new OnWatchOpCallback<ArrayList<FatFile>>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.2
                public void onSuccess(ArrayList<FatFile> fatFiles) {
                    Log.e(JLWatchFaceManager.TAG, "【getWatchDialInfo】-获取杰理文件系统成功");
                    JLWatchFaceManager.this.getAllWatchInfo(fatFiles);
                    JLWatchFaceManager.this.isGettingFileSystem = false;
                }

                public void onFailed(BaseError baseError) {
                    Log.e(JLWatchFaceManager.TAG, "JL-SDK 获取表盘列表失败 222:" + baseError);
                    Log.e(JLWatchFaceManager.TAG, "获取表盘列表失败：" + baseError.toString());
                    JLWatchFaceManager.this.isGettingFileSystem = false;
                    if (JLWatchFaceManager.this.initListener != null) {
                        JLWatchFaceManager.this.initListener.onFailed(baseError);
                    }
                    if (JLWatchFaceManager.this.dialInfoGetListener != null) {
                        JLWatchFaceManager.this.dialInfoGetListener.onWatchDialInfoGetFailed(baseError);
                    }
                    JLWatchFaceManager.getInstance().release();
                }
            });
        }
    }

    private void getAllWatchInfo(ArrayList<FatFile> fatFiles) {
        Log.e(TAG, "******************开始获取所有表盘信息****************** fatFiles :" + fatFiles.size() + "个");
        this.systemFatFiles.clear();
        this.serverFatFiles.clear();
        this.picFatFile = null;
        this.allFatFile.clear();
        this.originFatFile.clear();
        this.originFatFile.addAll(fatFiles);
        for (FatFile fatFile : fatFiles) {
            Log.e(TAG, "获取杰理文件系统成功 fatFile = " + fatFile.toString());
            if (fatFile.getPath().toLowerCase().contains("/watch")) {
                this.allFatFile.add(fatFile);
            }
        }
        if (this.allFatFile.size() > 0) {
            this.uiHandler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.3
                @Override // java.lang.Runnable
                public void run() {
                    JLWatchFaceManager.this.getWatchInfoByFatFile(JLWatchFaceManager.this.allFatFile.get(0));
                }
            }, 100L);
        } else {
            Log.e(TAG, "获取的文件系统为空 ");
        }
    }

    private void getWatchInfoByFatFile(final FatFile fatFile) {
        String path = fatFile.getPath();
        WatchManager.getInstance().getWatchMessage(path, new OnWatchOpCallback<String>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.4
            public void onSuccess(String s) {
                Log.e(JLWatchFaceManager.TAG, "获取杰理文件系统成功-[getWatchMessage] = " + s);
                if (s != null) {
                    if (s.contains(JLWatchFaceManager.PIC_DIAL)) {
                        JLWatchFaceManager.this.picFatFile = fatFile;
                        Log.e(JLWatchFaceManager.TAG, "获取杰理文件系统成功-[照片表盘信息] = " + JLWatchFaceManager.this.picFatFile.toString());
                    }
                    if (s.contains(JLWatchFaceManager.SERVICE_DIAL) && !JLWatchFaceManager.this.serverFatFiles.contains(fatFile)) {
                        JLWatchFaceManager.this.serverFatFiles.add(fatFile);
                        Log.e(JLWatchFaceManager.TAG, "获取杰理文件系统成功-[服务器表盘信息] = " + fatFile.toString());
                    }
                    if (JLWatchFaceManager.this.serverFatFiles.size() > 0) {
                        JLWatchFaceManager.this.currentServerDialName = JLWatchFaceManager.this.serverFatFiles.get(0).getName();
                    }
                    if (s.contains(JLWatchFaceManager.SYSTEM_DIAL) && !JLWatchFaceManager.this.systemFatFiles.contains(fatFile)) {
                        JLWatchFaceManager.this.systemFatFiles.add(fatFile);
                        Log.e(JLWatchFaceManager.TAG, "获取杰理文件系统成功-[系统表盘信息] = " + fatFile.toString());
                    }
                }
                JLWatchFaceManager.this.allFatFile.remove(fatFile);
                if (JLWatchFaceManager.this.allFatFile.isEmpty()) {
                    JLWatchFaceManager.this.printFatFiles();
                    if (JLWatchFaceManager.this.initListener != null) {
                        JLWatchFaceManager.this.initListener.onSuccess();
                    }
                    if (JLWatchFaceManager.this.dialInfoGetListener != null) {
                        JLWatchFaceManager.this.dialInfoGetListener.onWatchDialInfoGetSuccess(JLWatchFaceManager.this.systemFatFiles, JLWatchFaceManager.this.serverFatFiles, JLWatchFaceManager.this.picFatFile);
                        JLWatchFaceManager.this.dialInfoGetListener.onWatchDialInfoGetComplete();
                        return;
                    }
                    return;
                }
                JLWatchFaceManager.this.uiHandler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (JLWatchFaceManager.this.allFatFile.size() > 0) {
                            JLWatchFaceManager.this.getWatchInfoByFatFile(JLWatchFaceManager.this.allFatFile.get(0));
                        }
                    }
                }, 5L);
            }

            public void onFailed(BaseError baseError) {
                Log.e(JLWatchFaceManager.TAG, "-initWatchServiceDials-JL-SDK 获取单个表盘信息失败 err:" + baseError + " ,filePath = " + fatFile.getPath());
                if (JLWatchFaceManager.this.dialInfoGetListener != null) {
                    JLWatchFaceManager.this.dialInfoGetListener.onWatchDialInfoGetFailed(baseError);
                }
            }
        });
    }

    public void initWatchServiceDials() {
        Log.e(TAG, "initWatchServiceDials=====================> listWatchList");
        Log.e(TAG, "STEP.4-【START】==============================获取文件系统==============================");
        WatchManager.getInstance().listWatchList(new OnWatchOpCallback<ArrayList<FatFile>>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.5
            public void onSuccess(ArrayList<FatFile> fatFiles) {
                Log.e(JLWatchFaceManager.TAG, "获取杰理文件系统成功");
                Log.e(JLWatchFaceManager.TAG, "STEP.4-【START】==============================获取文件系统成功============================== 文件数:" + fatFiles.size());
                JLWatchFaceManager.this.getAllWatchInfo(fatFiles);
            }

            public void onFailed(BaseError baseError) {
                Log.e(JLWatchFaceManager.TAG, "STEP.4-【START】==============================获取文件系统失败==============================");
                Log.e(JLWatchFaceManager.TAG, "获取表盘列表失败：" + baseError.toString());
                if (JLWatchFaceManager.this.initListener != null) {
                    JLWatchFaceManager.this.initListener.onFailed(baseError);
                }
                JLWatchFaceManager.getInstance().release();
            }
        });
    }

    public void setPictureDial(String absolutePicPath, JLTransferPicDialListener listener) {
    }

    public void startTransferPicDial(String absolutePicPath, String absoluteSmallPicPath, JLTransferPicDialListener listener) {
        Log.v(TAG, "开始传输杰理照片表盘，absolutePicPath = " + absolutePicPath);
        if (TextUtils.isEmpty(absolutePicPath)) {
            Log.e(TAG, "请输入有效的照片文件地址");
            return;
        }
        File picFile = new File(absolutePicPath);
        if (!picFile.exists()) {
            Log.e(TAG, "当前照片文件不存在，请检查当前文件是否正确。absolutePicPath = " + absolutePicPath);
            return;
        }
        this.listener = listener;
        int fileNameIndex = absolutePicPath.lastIndexOf("/");
        String fileName = absolutePicPath.substring(fileNameIndex + 1);
        String JLBigBgOutPath = absolutePicPath.replace(fileName, JL_PIC_BGP_BIG);
        String JLSmallBgOutPath = absolutePicPath.replace(fileName, JL_PIC_BGP_SMALL);
        startBmpConvert(absolutePicPath, JLBigBgOutPath, absoluteSmallPicPath, JLSmallBgOutPath);
    }

    public void transferPicDial() {
        if (TextUtils.isEmpty(this.jlBGPFilePath) || TextUtils.isEmpty(this.jlBGPFileScalePath)) {
            Log.e(TAG, "缩略图地址和大图地址不能为空");
        } else if (this.picFatFile == null) {
            Log.e(TAG, "杰理表盘获取照片表盘【FatFile】失败");
        } else {
            transferScaleBGPFile();
        }
    }

    public void setServiceDial() {
        if (this.serverFatFiles.isEmpty()) {
            Log.e(TAG, "服务器表盘列表为空");
        } else {
            WatchManager.getInstance().setCurrentWatchInfo(this.serverFatFiles.get(0).getPath(), null);
        }
    }

    public void setPicDial() {
        if (this.picFatFile == null) {
            Log.e(TAG, "Get picture dial failed!");
        } else {
            WatchManager.getInstance().setCurrentWatchInfo(this.picFatFile.getPath(), null);
        }
    }

    private void transferScaleBGPFile() {
        Log.e(TAG, "-transferScaleBGPFile- jlBGPFileScalePath  = " + this.jlBGPFileScalePath + " , picFatFile = " + this.picFatFile);
        if (this.picFatFile == null || this.jlBGPFileScalePath == null) {
            Log.e(TAG, "-transferScaleBGPFile- 杰理照片表盘sdk获取照片文件初始化失败  重新开始 ");
            initWatchServiceDials();
        } else {
            WatchManager.getInstance().setCurrentWatchInfo(this.picFatFile.getPath(), new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.6
                public void onSuccess(FatFile fatFile) {
                    Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 切换到照片表盘 ---》设置成功 ---》 fatFile = " + fatFile.toString());
                    Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 切换到照片表盘 ---》设置成功 ---》 jlBGPFileScalePath = " + JLWatchFaceManager.this.jlBGPFileScalePath);
                    boolean isReplace = false;
                    for (int i = 0; i < JLWatchFaceManager.this.originFatFile.size(); i++) {
                        FatFile fatFile1 = JLWatchFaceManager.this.originFatFile.get(i);
                        if (fatFile1.getName().equalsIgnoreCase("BGP_W000")) {
                            isReplace = true;
                            break;
                        }
                    }
                    OnFatFileProgressListener listener1 = new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.6.1
                        public void onStart(String s) {
                            Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 缩略图  onStart s = " + s);
                            if (JLWatchFaceManager.this.listener != null) {
                                JLWatchFaceManager.this.listener.onJLTransferPicDialStart();
                            }
                        }

                        public void onProgress(float v) {
                            Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 缩略图  onProgress v = " + v);
                            float v2 = v * 0.5f;
                            if (JLWatchFaceManager.this.listener != null) {
                                JLWatchFaceManager.this.listener.onTransferPicDialProgress((int) v2);
                            }
                        }

                        public void onStop(int i2) {
                            Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 缩略图   onStop i = " + i2);
                            String msg = String.format(Locale.CHINA, "----插入%s结束----\n结果：", "自定义背景");
                            if (i2 == 0) {
                                String fatFilePath = FatUtil.getFatFilePath(JLWatchFaceManager.this.jlBGPFileScalePath);
                                JLWatchFaceManager.this.enableFatFile(fatFilePath);
                                JLWatchFaceManager.this.listener.onScaleBGPFileTransferComplete();
                                JLWatchFaceManager.this.transferBigBGPFile();
                                return;
                            }
                            if (i2 == 20) {
                                String msg2 = msg + "失败， 空间不足";
                                if (JLWatchFaceManager.this.listener != null) {
                                    JLWatchFaceManager.this.listener.onTransferError(i2, msg2);
                                    return;
                                }
                                return;
                            }
                            String msg3 = msg + "失败， " + i2 + " " + FatUtil.getFatFsErrorCodeMsg(i2);
                            if (JLWatchFaceManager.this.listener != null) {
                                JLWatchFaceManager.this.listener.onTransferError(i2, msg3);
                            }
                        }
                    };
                    if (isReplace) {
                        WatchManager.getInstance().replaceWatchFile(JLWatchFaceManager.this.jlBGPFileScalePath, listener1);
                    } else {
                        WatchManager.getInstance().createWatchFile(JLWatchFaceManager.this.jlBGPFileScalePath, true, listener1);
                    }
                }

                public void onFailed(BaseError baseError) {
                    Log.e(JLWatchFaceManager.TAG, "切换到照片表盘 ---》设置失败 ---》 fatFile = " + (JLWatchFaceManager.this.picFatFile == null ? " is null" : JLWatchFaceManager.this.picFatFile.toString()));
                    if (JLWatchFaceManager.this.listener != null) {
                        JLWatchFaceManager.this.listener.onTransferError(baseError.getCode(), baseError.getMessage());
                    }
                }
            });
        }
    }

    private void transferBigBGPFile() {
        if (this.jlBGPFilePath == null || this.picFatFile == null) {
            return;
        }
        Log.e(TAG, "切换到照片表盘 ---》 fatFile = " + this.picFatFile.toString());
        boolean isReplace = false;
        for (int i = 0; i < this.originFatFile.size(); i++) {
            FatFile fatFile1 = this.originFatFile.get(i);
            if (fatFile1.getName().equalsIgnoreCase("BGP_W001")) {
                isReplace = true;
                break;
            }
            Log.e("Test", fatFile1.toString());
        }
        OnFatFileProgressListener listener1 = new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.7
            public void onStart(String s) {
                Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 大图  onStart s = " + s);
            }

            public void onProgress(float v) {
                Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 大图  onProgress v = " + v);
                float v2 = (v * 0.5f) + 50.0f;
                if (JLWatchFaceManager.this.listener != null) {
                    JLWatchFaceManager.this.listener.onTransferPicDialProgress((int) v2);
                }
            }

            public void onStop(int i2) {
                Log.e(JLWatchFaceManager.TAG, "-createWatchFile- 大图  onStop i = " + i2);
                String msg = String.format(Locale.CHINA, "----插入%s结束----\n结果：", "自定义背景");
                if (i2 == 0) {
                    Log.e(JLWatchFaceManager.TAG, "-传输完成- 大图  onStop i = " + i2);
                    final String fatFilePath = FatUtil.getFatFilePath(JLWatchFaceManager.this.jlBGPFilePath);
                    JLWatchFaceManager.this.uiHandler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.7.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JLWatchFaceManager.this.enableFatFile(fatFilePath);
                        }
                    }, 100L);
                    if (JLWatchFaceManager.this.listener != null) {
                        JLWatchFaceManager.this.listener.onBigBGPFileTransferComplete();
                        JLWatchFaceManager.this.listener.onTransferComplete();
                        return;
                    }
                    return;
                }
                if (i2 == 20) {
                    String msg2 = msg + "失败， 空间不足";
                    if (JLWatchFaceManager.this.listener != null) {
                        JLWatchFaceManager.this.listener.onTransferError(i2, msg2);
                        return;
                    }
                    return;
                }
                String msg3 = msg + "失败， " + i2 + " " + FatUtil.getFatFsErrorCodeMsg(i2);
                if (JLWatchFaceManager.this.listener != null) {
                    JLWatchFaceManager.this.listener.onTransferError(i2, msg3);
                }
            }
        };
        if (isReplace) {
            WatchManager.getInstance().replaceWatchFile(this.jlBGPFilePath, listener1);
        } else {
            WatchManager.getInstance().createWatchFile(this.jlBGPFilePath, true, listener1);
        }
    }

    private void transferAIPreviewBGPFile() {
        if (this.jlBGPFileAIPreviewPath == null) {
            return;
        }
        WatchManager.getInstance().createWatchFile(this.jlBGPFileAIPreviewPath, true, new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.8
            public void onStart(String s) {
            }

            public void onProgress(float v) {
                if (JLWatchFaceManager.this.listener != null) {
                    JLWatchFaceManager.this.listener.onTransferPicDialProgress((int) v);
                    if (v == 100.0f) {
                        JLWatchFaceManager.this.listener.onAIPreviewTransferComplete();
                    }
                }
            }

            public void onStop(int i) {
                Log.e(JLWatchFaceManager.TAG, "-createWatchFile- AI预览图  onStop i = " + i);
                String msg = String.format(Locale.US, "----插入%s结束----\n结果：", "自定义背景");
                if (i == 0) {
                    Log.e(JLWatchFaceManager.TAG, "-传输完成- AI预览图  onStop i = " + i);
                    String fatFilePath = FatUtil.getFatFilePath(JLWatchFaceManager.this.jlBGPFileAIPreviewPath);
                    JLWatchFaceManager.this.enableFatFile(fatFilePath);
                    JLWatchFaceManager.this.deleteAllTempFile();
                    if (JLWatchFaceManager.this.listener != null) {
                        JLWatchFaceManager.this.listener.onTransferComplete();
                        return;
                    }
                    return;
                }
                if (i == 20) {
                    String msg2 = msg + "失败， 空间不足";
                    if (JLWatchFaceManager.this.listener != null) {
                        JLWatchFaceManager.this.listener.onTransferError(i, msg2);
                    }
                    JLWatchFaceManager.getInstance().release();
                    return;
                }
                String msg3 = msg + "失败， " + i + " " + FatUtil.getFatFsErrorCodeMsg(i);
                if (JLWatchFaceManager.this.listener != null) {
                    JLWatchFaceManager.this.listener.onTransferError(i, msg3);
                }
                JLWatchFaceManager.getInstance().release();
            }
        });
    }

    public void setDial(String filePath) {
        WatchManager.getInstance().setCurrentWatchInfo(filePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.9
            public void onSuccess(FatFile fatFile) {
                if (fatFile != null) {
                    Log.e(JLWatchFaceManager.TAG, "-setDial- 使能表盘成功   FatFile = " + fatFile.toString());
                } else {
                    Log.e(JLWatchFaceManager.TAG, "-setDial- 使能表盘成功   FatFile = null ");
                }
            }

            public void onFailed(BaseError baseError) {
                Log.e(JLWatchFaceManager.TAG, "-setDial- onFailed   baseError " + baseError.toString());
            }
        });
    }

    public void enableFatFile(String fatFilePath) {
        Log.e(TAG, "-enableFatFile- 使能表盘成功   fatFilePath = " + fatFilePath);
        WatchManager.getInstance().enableWatchCustomBg(fatFilePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.10
            public void onSuccess(FatFile fatFile) {
                if (fatFile != null) {
                    Log.e(JLWatchFaceManager.TAG, "-enableFatFile- 使能表盘成功   FatFile = " + fatFile.toString());
                } else {
                    Log.e(JLWatchFaceManager.TAG, "-enableFatFile- 使能表盘成功   FatFile = null ");
                }
            }

            public void onFailed(BaseError baseError) {
                Log.e(JLWatchFaceManager.TAG, "-enableFatFile- onFailed   baseError " + baseError.toString());
                String msg = String.format(Locale.CHINA, "----激活自定义表盘----\n结果: 失败，发送异常，%s", baseError.getMessage());
                if (JLWatchFaceManager.this.listener != null) {
                    JLWatchFaceManager.this.listener.onTransferError(-1, msg);
                }
            }
        });
    }

    public void startBmpConvert(String bigBgInPath, String bigBgOutPath, String smallBgInPath, String smallBgOutPath) {
        if (!checkNotNull(bigBgInPath, bigBgOutPath, smallBgInPath, smallBgOutPath)) {
            Log.e(TAG, "|---> error 错误 参数不能为空");
            return;
        }
        this.bigBgInPath = bigBgInPath;
        this.bigBgOutPath = bigBgOutPath;
        this.smallBgInPath = smallBgInPath;
        this.smallBgOutPath = smallBgOutPath;
        Log.d(TAG, "开始转换图片为杰理表盘格式：\n【 bigBgInPath = " + this.bigBgInPath + " , bigBgOutPath = " + this.bigBgOutPath + " 】\n 【 smallBgInPath = " + this.smallBgInPath + " , smallBgOutPath = " + this.smallBgOutPath + " 】");
        convertCropFile2JLBGPFile(this.bigBgInPath, this.bigBgOutPath);
    }

    private void convertCropFile2JLBGPFile(String inPath, final String outPath) {
        if (outPath.contains(JL_PIC_BGP_BIG)) {
            Log.i(TAG, "开始转换杰理表盘大图：inPath = " + inPath + ", outPath = " + outPath);
        }
        if (outPath.contains(JL_PIC_BGP_SMALL)) {
            Log.i(TAG, "开始转换杰理表盘缩略图：inPath = " + inPath + ", outPath = " + outPath);
        }
        Log.e(TAG, "转换为JL的图片文件格式：inPath = " + inPath + " , outPath = " + outPath);
        DeviceInfo deviceInfo = WatchManager.getInstance().getDeviceInfo(WatchManager.getInstance().getConnectedDevice());
        int type = 1;
        if (deviceInfo != null) {
            type = deviceInfo.getSdkType() == 9 ? 1 : 0;
        }
        this.mBmpConvert.bitmapConvert(type, inPath, outPath, new OnConvertListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.11
            public void onStart(String path) {
                Log.e(JLWatchFaceManager.TAG, "-bitmapConvert- 开始转换 图片 path = " + path);
            }

            public void onStop(boolean result, String output) {
            }

            public void onStop(ConvertResult convertResult, String output) {
                Log.e(JLWatchFaceManager.TAG, "-bitmapConvert-  转换完成 result = " + convertResult.isConvertSuccess() + ", output = " + output);
                if (convertResult.isConvertSuccess()) {
                    if (output.toLowerCase().contains(JLWatchFaceManager.JL_PIC_BGP_SMALL)) {
                        JLWatchFaceManager.this.jlBGPFileScalePath = outPath;
                        JLWatchFaceManager.this.transferPicDial();
                    } else if (output.toLowerCase().contains(JLWatchFaceManager.JL_PIC_BGP_BIG)) {
                        JLWatchFaceManager.this.jlBGPFilePath = outPath;
                        JLWatchFaceManager.this.convertCropFile2JLBGPFile(JLWatchFaceManager.this.smallBgInPath, JLWatchFaceManager.this.smallBgOutPath);
                    } else if (output.toLowerCase().contains(JLWatchFaceManager.AI_PREVIEW_DIAL_FACE_FILE)) {
                        JLWatchFaceManager.this.jlBGPFileAIPreviewPath = output;
                        JLWatchFaceManager.this.transferAIPreviewBGPFile();
                    }
                }
            }
        });
    }

    private void deleteAllTempFile() {
    }

    public void setJLTransferPicDialListener(JLTransferPicDialListener listener) {
        this.listener = listener;
    }

    public void startTransferAIPreview(String filePath, String fileName) {
        String aiPreviewOutPath = filePath.replace(fileName, AI_PREVIEW_DIAL_FACE_FILE);
        convertCropFile2JLBGPFile(filePath, aiPreviewOutPath);
    }

    private boolean checkNotNull(String... strings) {
        for (String string : strings) {
            if (TextUtils.isEmpty(string)) {
                return false;
            }
        }
        return true;
    }
}
