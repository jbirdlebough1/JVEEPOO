package com.veepoo.protocol.util.thread;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/AbsThreadPoolManager.class */
public abstract class AbsThreadPoolManager {
    private ThreadPoolsUtil vp_a;
    private final Handler vp_b = new Handler(Looper.getMainLooper());

    public AbsThreadPoolManager() {
        this.vp_a = null;
        if (this.vp_a == null) {
            this.vp_a = new ThreadPoolsUtil(getThreadPoolName(), corePoolSize(), maximumPoolSize(), keepAliveTime(), TimeUnit.SECONDS, waitingCount());
        }
    }

    public void shutdown() {
        this.vp_a.shutdown();
    }

    public void postOnUIThread(Runnable runnable) {
        this.vp_b.post(runnable);
    }

    public void postDelay(Runnable runnable, long j) {
        this.vp_b.postDelayed(runnable, j);
    }

    public void execute(Runnable runnable) {
        this.vp_a.execute(runnable);
    }

    public void executeWithCall(Runnable runnable, ThreadPoolsUtil.Call call) {
        this.vp_a.executeWithCallback(runnable, call);
    }

    public <T> void executeWitSmartCall(ThreadPoolsUtil.SmartCall<T> smartCall) {
        this.vp_a.executeWithSmartCallback(smartCall);
    }

    protected abstract String getThreadPoolName();

    protected int corePoolSize() {
        return 5;
    }

    protected int maximumPoolSize() {
        return 10;
    }

    protected int waitingCount() {
        return 200000;
    }

    protected long keepAliveTime() {
        return 10L;
    }
}
