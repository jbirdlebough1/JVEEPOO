package com.inuker.bluetooth.library.jieli.dial;

import android.os.Handler;
import android.os.Looper;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_rcsp.impl.WatchOpImpl;
import com.jieli.jl_rcsp.interfaces.listener.ThreadStateListener;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/GetWatchMsgTask.class */
public class GetWatchMsgTask extends Thread {
    private static final String TAG = GetWatchMsgTask.class.getSimpleName();
    private final WatchOpImpl mWatchOp;
    private final List<FatFile> taskList;
    private final OnWatchOpCallback<ArrayList<WatchInfo>> mCallback;
    private final ThreadStateListener mStateListener;
    private final ArrayList<WatchInfo> watchList = new ArrayList<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Object mLock = new Object();
    private volatile boolean isLock;

    public GetWatchMsgTask(WatchOpImpl impl, List<FatFile> list, OnWatchOpCallback<ArrayList<WatchInfo>> callback, ThreadStateListener listener) {
        this.mWatchOp = impl;
        this.taskList = list;
        this.mCallback = callback;
        this.mStateListener = listener;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (null != this.mStateListener) {
            this.mStateListener.onStart(getId());
        }
        if (this.taskList == null || this.taskList.isEmpty()) {
            return;
        }
        synchronized (this.mLock) {
            for (final FatFile fatFile : this.taskList) {
                this.isLock = false;
                this.mWatchOp.getWatchMessage(fatFile.getPath(), new OnWatchOpCallback<String>() { // from class: com.inuker.bluetooth.library.jieli.dial.GetWatchMsgTask.1
                    public void onSuccess(final String result) {
                        BluetoothLog.i("getWatchMessage >>> -onSuccess- result = " + result + ", path = " + fatFile.getPath());
                        GetWatchMsgTask.this.mWatchOp.getCustomWatchBgInfo(fatFile.getPath(), new OnWatchOpCallback<String>() { // from class: com.inuker.bluetooth.library.jieli.dial.GetWatchMsgTask.1.1
                            public void onSuccess(String result2) {
                                BluetoothLog.i("getCustomWatchBgInfo >>> -onSuccess- result = " + result2 + ", path = " + fatFile.getPath());
                                String customBgPath = result2;
                                if (!"null".equals(customBgPath)) {
                                    customBgPath = "/" + GetWatchMsgTask.getFileNameByPath(result2).toUpperCase();
                                }
                                WatchInfo watchInfo = new WatchInfo().setName(fatFile.getName()).setFatFile(fatFile).setVersion(result).setSize(fatFile.getSize() * 4 * 1024).setStatus(1).setCustomBgFatPath(customBgPath);
                                GetWatchMsgTask.this.watchList.add(watchInfo);
                                synchronized (GetWatchMsgTask.this.mLock) {
                                    if (GetWatchMsgTask.this.isLock) {
                                        GetWatchMsgTask.this.mLock.notify();
                                    }
                                }
                            }

                            public void onFailed(BaseError error) {
                                synchronized (GetWatchMsgTask.this.mLock) {
                                    if (GetWatchMsgTask.this.isLock) {
                                        GetWatchMsgTask.this.mLock.notify();
                                    }
                                }
                            }
                        });
                    }

                    public void onFailed(BaseError error) {
                        synchronized (GetWatchMsgTask.this.mLock) {
                            if (GetWatchMsgTask.this.isLock) {
                                GetWatchMsgTask.this.mLock.notify();
                            }
                        }
                    }
                });
                this.isLock = true;
                try {
                    this.mLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.isLock = false;
                }
            }
            if (this.mCallback != null) {
                this.mHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.GetWatchMsgTask.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (GetWatchMsgTask.this.watchList.isEmpty()) {
                            GetWatchMsgTask.this.mCallback.onFailed(new BaseError(12288, "request watch message failed."));
                        } else {
                            GetWatchMsgTask.this.mCallback.onSuccess(GetWatchMsgTask.this.watchList);
                        }
                    }
                });
            }
        }
        if (null != this.mStateListener) {
            this.mStateListener.onFinish(getId());
        }
    }

    public static String getFileNameByPath(String filePath) {
        if (filePath == null) {
            return null;
        }
        int index = filePath.lastIndexOf("/");
        if (index > -1) {
            return filePath.substring(index + 1);
        }
        return filePath;
    }
}
