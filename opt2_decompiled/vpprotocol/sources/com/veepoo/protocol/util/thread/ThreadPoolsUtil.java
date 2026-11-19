package com.veepoo.protocol.util.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/ThreadPoolsUtil.class */
public class ThreadPoolsUtil {
    private static Map vp_e = new Hashtable();
    private Handler vp_a;
    private ThreadPoolExecutor vp_b;
    private int vp_c;
    private String vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/ThreadPoolsUtil$Call.class */
    public interface Call {
        void runOnForeground();
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/ThreadPoolsUtil$SmartCall.class */
    public interface SmartCall<T> {
        @WorkerThread
        T doBackground();

        @MainThread
        void onDo(T t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.thread.ThreadPoolsUtil] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8 */
    public ThreadPoolsUtil(String str, int i, int i2, long j, TimeUnit timeUnit, int i3) {
        ?? r0 = this;
        synchronized (this) {
            try {
                this.vp_d = str;
                this.vp_c = (int) (((double) i3) * 1.5d);
                this.vp_a = new Handler(Looper.getMainLooper());
                String strVp_a = r0.vp_a(str, i, i2, j, timeUnit, i3, "#");
                if (vp_e.containsKey(strVp_a)) {
                    this.vp_b = (ThreadPoolExecutor) vp_e.get(strVp_a);
                } else {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i2, j, timeUnit, new LinkedBlockingQueue(this.vp_c));
                    this.vp_b = threadPoolExecutor;
                    vp_e.put(strVp_a, threadPoolExecutor);
                }
                r0 = this;
            } catch (Throwable unused) {
                throw r0;
            }
        }
    }

    private String vp_a(String str, int i, int i2, long j, TimeUnit timeUnit, int i3, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(str2);
        sb.append(i).append(str2);
        sb.append(i2).append(str2);
        sb.append(j).append(str2);
        sb.append(timeUnit.toString()).append(str2);
        sb.append(i3);
        return sb.toString();
    }

    public void execute(Runnable runnable) {
        vp_a();
        this.vp_b.execute(runnable);
    }

    public void executeWithCallback(Runnable runnable, Call call) {
        vp_a();
        this.vp_b.execute(() -> {
            runnable.run();
            this.vp_a.post(() -> {
                if (call != null) {
                    call.runOnForeground();
                }
            });
        });
    }

    public <T> void executeWithSmartCallback(@NonNull SmartCall<T> smartCall) {
        vp_a();
        this.vp_b.execute(() -> {
            Object objDoBackground = smartCall.doBackground();
            this.vp_a.post(() -> {
                smartCall.onDo(objDoBackground);
            });
        });
    }

    public void shutdown() {
        this.vp_b.shutdown();
    }

    public int getTaskSize() {
        return this.vp_b.getQueue().size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.veepoo.protocol.util.thread.ThreadPoolsUtil] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8 */
    public ThreadPoolsUtil(String str, int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue) {
        ?? r0 = this;
        synchronized (this) {
            try {
                this.vp_d = str;
                this.vp_c = blockingQueue.size();
                this.vp_a = new Handler(Looper.getMainLooper());
                String strVp_a = r0.vp_a(str, i, i2, j, timeUnit, blockingQueue.size(), "#");
                if (vp_e.containsKey(strVp_a)) {
                    this.vp_b = (ThreadPoolExecutor) vp_e.get(strVp_a);
                } else {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i2, j, timeUnit, blockingQueue);
                    this.vp_b = threadPoolExecutor;
                    vp_e.put(strVp_a, threadPoolExecutor);
                }
                r0 = this;
            } catch (Throwable unused) {
                throw r0;
            }
        }
    }

    private void vp_a() {
        while (getTaskSize() >= this.vp_c) {
            SystemClock.sleep(100L);
        }
    }
}
