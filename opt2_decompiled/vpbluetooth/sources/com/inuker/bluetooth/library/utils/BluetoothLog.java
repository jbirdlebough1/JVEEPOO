package com.inuker.bluetooth.library.utils;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/BluetoothLog.class */
public class BluetoothLog {
    private static final String LOG_TAG = "vepo-bluetooth";
    public static boolean isDebug = true;

    public static void setDebug(boolean isdebug) {
        isDebug = isdebug;
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(LOG_TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(LOG_TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(LOG_TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(LOG_TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(LOG_TAG, msg);
        }
    }

    public static void e(Throwable e) {
        if (isDebug) {
            e(getThrowableString(e));
        }
    }

    public static void w(Throwable e) {
        if (isDebug) {
            w(getThrowableString(e));
        }
    }

    private static String getThrowableString(Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        while (e != null) {
            e.printStackTrace(printWriter);
            e = e.getCause();
        }
        String text = writer.toString();
        printWriter.close();
        return text;
    }
}
