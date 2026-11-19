package com.inuker.bluetooth.library.jieli.dial;

import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_fatfs.utils.FatUtil;
import com.jieli.jl_rcsp.interfaces.watch.OnWatchOpCallback;
import com.jieli.jl_rcsp.model.base.BaseError;
import java.util.Locale;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/FatInsertWatchTask.class */
public class FatInsertWatchTask extends AbstractTestTask implements OnFatFileProgressListener {
    private final WatchManager watchManager;
    private final String mPath;
    public boolean run;
    private OnJLDialTransferProgressListener progressListener;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/FatInsertWatchTask$OnJLDialTransferProgressListener.class */
    public interface OnJLDialTransferProgressListener {
        void onJLDialTransferProgress(int i);
    }

    public FatInsertWatchTask(WatchManager watchManager, String path) {
        this.watchManager = watchManager;
        this.mPath = path;
    }

    public void setProgressListener(OnJLDialTransferProgressListener listener) {
        this.progressListener = listener;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void startTest() {
        if (this.run) {
            return;
        }
        this.run = true;
        if (this.mPath != null) {
            this.watchManager.createWatchFile(this.mPath, false, this);
        }
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void stopTest() {
        if (this.run) {
            this.run = false;
        }
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public String getName() {
        return "表盘测试";
    }

    public void onStart(String s) {
        if (this.run) {
            String msg = String.format(Locale.CHINA, "----插入%s开始----\n路径: %s", "表盘", FatUtil.getFatFilePath(s));
            onTestLog(msg);
        }
    }

    public void onProgress(float v) {
        if (this.run) {
            int progress = Math.round(v);
            if (progress > 100) {
                progress = 99;
            }
            String msg = String.format(Locale.CHINA, "----插入%s进度---- %d", "表盘", Integer.valueOf(progress));
            onTestLog(msg);
            if (this.progressListener != null) {
                this.progressListener.onJLDialTransferProgress(progress);
            }
        }
    }

    public void onStop(int i) {
        if (this.run) {
            String msg = String.format(Locale.CHINA, "----插入%s结束----\n结果：", "表盘");
            if (i == 0) {
                String fatFilePath = FatUtil.getFatFilePath(this.mPath);
                onTestLog(msg + "成功，路径：" + fatFilePath);
                enableFatFile(fatFilePath);
            } else if (i == 20) {
                onTestLog(msg + "失败， 空间不足");
                next(new TestError(i, "空间不够"));
            } else if (i == 12545) {
                onTestLog(msg + "失败， 设备繁忙，处于通话状态");
                next(new TestError(i, "设备繁忙"));
            } else {
                next(new TestError(i, msg + "失败， " + i + " " + FatUtil.getFatFsErrorCodeMsg(i)));
            }
        }
    }

    public void enableFatFile(String fatFilePath) {
        if (this.run) {
            this.watchManager.setCurrentWatchInfo(fatFilePath, new OnWatchOpCallback<FatFile>() { // from class: com.inuker.bluetooth.library.jieli.dial.FatInsertWatchTask.1
                public void onSuccess(FatFile fatFile) {
                    String msg = String.format(Locale.CHINA, "----设置当前表盘----\n结果: 成功，路径：%s", fatFile.getPath());
                    FatInsertWatchTask.this.next(new TestError(0, msg));
                }

                public void onFailed(BaseError baseError) {
                    String msg = String.format(Locale.CHINA, "----设置当前表盘----\n结果: 失败，发送异常，%s", baseError.getMessage());
                    FatInsertWatchTask.this.next(new TestError(baseError.getSubCode(), msg));
                }
            });
        }
    }
}
