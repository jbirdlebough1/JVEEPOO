package com.inuker.bluetooth.library.channel;

import android.os.Handler;
import android.os.Looper;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/Timer.class */
public class Timer {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static TimerCallback mCallback;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/channel/Timer$TimerCallback.class */
    public static abstract class TimerCallback implements Runnable {
        private String name;

        public abstract void onTimerCallback() throws TimeoutException;

        public TimerCallback(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override // java.lang.Runnable
        public final void run() {
            BluetoothLog.e(String.format("%s: Timer expired!!!", this.name));
            try {
                onTimerCallback();
            } catch (TimeoutException e) {
                BluetoothLog.e(e);
            }
            Timer.mCallback = null;
        }
    }

    public static synchronized void stop() {
        mHandler.removeCallbacksAndMessages(null);
        mCallback = null;
    }

    public static synchronized boolean isRunning() {
        return mCallback != null;
    }

    public static synchronized String getName() {
        return isRunning() ? mCallback.getName() : "";
    }

    public static synchronized void start(TimerCallback callback, long duration) {
        mHandler.removeCallbacksAndMessages(null);
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        mHandler = new Handler(looper);
        mHandler.postDelayed(callback, duration);
        mCallback = callback;
    }
}
