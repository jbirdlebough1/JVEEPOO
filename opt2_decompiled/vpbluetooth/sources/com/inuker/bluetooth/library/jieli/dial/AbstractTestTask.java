package com.inuker.bluetooth.library.jieli.dial;

import com.jieli.jl_rcsp.util.JL_Log;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/AbstractTestTask.class */
public abstract class AbstractTestTask implements ITestTask {
    protected String tag = getClass().getSimpleName();
    private INextTask nextTask;
    private OnTestLogCallback mLogCallback;

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void onTestLog(String msg) {
        JL_Log.e(this.tag, "onTestLog------>" + msg);
        if (this.mLogCallback != null) {
            this.mLogCallback.onLog(msg);
        }
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void setOnTestLogCallback(OnTestLogCallback callback) {
        this.mLogCallback = callback;
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.INextTask
    public void next(TestError error) {
        onTestLog(error.msg);
        if (this.nextTask != null) {
            this.nextTask.next(error);
        }
    }

    @Override // com.inuker.bluetooth.library.jieli.dial.ITestTask
    public void setINextTask(INextTask nextTask) {
        this.nextTask = nextTask;
    }
}
