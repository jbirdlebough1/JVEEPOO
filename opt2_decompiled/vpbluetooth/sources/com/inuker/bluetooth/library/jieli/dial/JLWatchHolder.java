package com.inuker.bluetooth.library.jieli.dial;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_rcsp.model.base.BaseError;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchHolder.class */
public class JLWatchHolder {
    public static final String TAG = JLWatchHolder.class.getSimpleName();
    private final Handler uiHandler = new Handler(Looper.getMainLooper());
    int needDeleteSize = 0;
    int deleteCount = 0;
    private OnSetJLWatchDialListener listener;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchHolder$OnSetJLWatchDialListener.class */
    public interface OnSetJLWatchDialListener {
        void onStart();

        void onProgress(int i);

        void onComplete(String str);

        void onFiled(int i, String str);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchHolder$InstanceHolder.class */
    private static final class InstanceHolder {
        static final JLWatchHolder instance = new JLWatchHolder();

        private InstanceHolder() {
        }
    }

    public static JLWatchHolder getInstance() {
        return InstanceHolder.instance;
    }

    private JLWatchHolder() {
    }

    public void updateJLWatchServerDial(String localDialFilePath, OnSetJLWatchDialListener listener) {
        this.listener = listener;
        deleteOldDialAndUpdate(localDialFilePath);
    }

    private void deleteOldDialAndUpdate(String filePath) {
        this.listener.onStart();
        this.needDeleteSize = JLWatchFaceManager.getInstance().getServerFatFiles().size();
        Log.d(TAG, "杰理文件系统---deleteOldDialAndUpdate need delete size = " + this.needDeleteSize);
        this.deleteCount = 0;
        if (this.needDeleteSize == 0) {
            Log.i(TAG, "需要删除市场表盘为空");
        }
        for (FatFile fatFile : JLWatchFaceManager.getInstance().getServerFatFiles()) {
            Log.v(TAG, "杰理文件系统->>>> need delete = " + fatFile.getPath());
        }
        deleteServiceDialByIndex(0, filePath);
    }

    private void deleteServiceDialByIndex(final int index, final String newDialPath) {
        Log.d(TAG, "杰理文件系统 deleteServiceDialByIndex 删除第 " + index + " 个表盘 newDialPath = " + newDialPath);
        if (index <= JLWatchFaceManager.getInstance().getServerFatFiles().size() - 1) {
            final String filePath = JLWatchFaceManager.getInstance().getServerFatFiles().get(index).getPath();
            Log.i(TAG, "删除表盘" + filePath);
            WatchManager.getInstance().deleteWatchFile(filePath, new OnFatFileProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.1
                public void onStart(String s) {
                    Log.i(JLWatchHolder.TAG, "杰理文件系统 deleteServiceDialByIndex -[onStart] : " + s);
                }

                public void onProgress(float v) {
                    Log.i(JLWatchHolder.TAG, "杰理文件系统 deleteServiceDialByIndex -[onProgress] : " + v);
                    if (v == 100.0f) {
                        Log.i(JLWatchHolder.TAG, "删除表盘" + filePath + "成功");
                        JLWatchHolder.this.deleteServiceDialByIndex(index + 1, newDialPath);
                    }
                }

                public void onStop(int i) {
                    Log.i(JLWatchHolder.TAG, "杰理文件系统 deleteServiceDialByIndex -[onStop] : " + i);
                    if (i != 0) {
                        Log.i(JLWatchHolder.TAG, "删除表盘" + filePath + "失败");
                        JLWatchHolder.this.updateDialPreViewsWithTransferDialSuccess();
                    }
                }
            });
        } else if (index == JLWatchFaceManager.getInstance().getServerFatFiles().size()) {
            updateJLDial(newDialPath);
        }
    }

    private void updateDialPreViewsWithTransferDialSuccess() {
        Log.i(TAG, "传输表盘后更新表盘");
        this.uiHandler.postDelayed(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.2
            @Override // java.lang.Runnable
            public void run() {
                JLWatchFaceManager.getInstance().getWatchDialInfo(new JLWatchFaceManager.OnWatchDialInfoGetListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.2.1
                    @Override // com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.OnWatchDialInfoGetListener
                    public void onGettingWatchDialInfo() {
                    }

                    @Override // com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.OnWatchDialInfoGetListener
                    public void onWatchDialInfoGetStart() {
                    }

                    @Override // com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.OnWatchDialInfoGetListener
                    public void onWatchDialInfoGetComplete() {
                    }

                    @Override // com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.OnWatchDialInfoGetListener
                    public void onWatchDialInfoGetSuccess(List<FatFile> systemFatFiles, List<FatFile> serverFatFiles, FatFile picFatFile) {
                        Log.i(JLWatchHolder.TAG, "传输表盘后更新表盘:-> 成功！");
                        JLWatchFaceManager.switch2ServerDial();
                        String watchPath = "";
                        if (serverFatFiles != null && !serverFatFiles.isEmpty()) {
                            watchPath = serverFatFiles.get(0).getPath();
                        }
                        if (JLWatchHolder.this.listener != null) {
                            JLWatchHolder.this.listener.onComplete(watchPath);
                        }
                    }

                    @Override // com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager.OnWatchDialInfoGetListener
                    public void onWatchDialInfoGetFailed(BaseError error) {
                    }
                });
            }
        }, 500L);
    }

    /* JADX INFO: renamed from: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder$3, reason: invalid class name */
    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/JLWatchHolder$3.class */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ String val$filePath;

        AnonymousClass3(String str) {
            this.val$filePath = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            FatInsertWatchTask task = new FatInsertWatchTask(WatchManager.getInstance(), this.val$filePath);
            task.setProgressListener(new FatInsertWatchTask.OnJLDialTransferProgressListener() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.3.1
                @Override // com.inuker.bluetooth.library.jieli.dial.FatInsertWatchTask.OnJLDialTransferProgressListener
                public void onJLDialTransferProgress(int progress) {
                    JLWatchHolder.this.listener.onProgress(progress);
                }
            });
            task.setINextTask(new INextTask() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.3.2
                @Override // com.inuker.bluetooth.library.jieli.dial.INextTask
                public void next(final TestError error) {
                    if (error.code == 0) {
                        JLWatchHolder.this.updateDialPreViewsWithTransferDialSuccess();
                    } else {
                        Log.e(JLWatchHolder.TAG, "表盘插入失败");
                        JLWatchHolder.this.uiHandler.post(new Runnable() { // from class: com.inuker.bluetooth.library.jieli.dial.JLWatchHolder.3.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (JLWatchHolder.this.listener != null) {
                                    JLWatchHolder.this.listener.onFiled(error.code, error.msg);
                                }
                            }
                        });
                    }
                }
            });
            task.startTest();
        }
    }

    private void updateJLDial(String filePath) {
        Log.d(TAG, "开始传输表盘");
        this.uiHandler.postDelayed(new AnonymousClass3(filePath), 500L);
    }
}
