package com.inuker.bluetooth.library.jieli.dial;

import com.jieli.jl_fatfs.interfaces.OnFatFileProgressListener;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_fatfs.utils.FatUtil;
import java.util.Locale;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/FatDeleteWatchTask.class */
public class FatDeleteWatchTask extends AbstractTestTask implements OnFatFileProgressListener {
    private final WatchManager watchManager;
    private boolean run;
    private final FatFile fatFile;

    public FatDeleteWatchTask(WatchManager watchManager, FatFile fatFile) {
        this.watchManager = watchManager;
        this.fatFile = fatFile;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void startTest() {
        this.run = true;
        this.watchManager.deleteWatchFile(this.fatFile.getPath(), this);
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void stopTest() {
        if (this.run) {
            this.run = false;
        }
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public String getName() {
        return "表盘删除";
    }

    public void onStart(String s) {
        String msg = String.format(Locale.CHINA, "----删除%s开始----\n路径: %s", getName(), s);
        onTestLog(msg);
    }

    public void onProgress(float v) {
        int progress = Math.round(v);
        if (progress > 100) {
            progress = 99;
        }
        String msg = String.format(Locale.CHINA, "----删除%s进度----%d", getName(), Integer.valueOf(progress));
        onTestLog(msg);
    }

    public void onStop(int i) {
        if (i == 0) {
            next(new TestError(0, getName() + "成功"));
        } else {
            String msg = String.format(Locale.CHINA, "----删除%s结束----\n结果: 失败，%s", getName(), FatUtil.getFatFsErrorCodeMsg(i));
            next(new TestError(i, msg));
        }
    }
}
