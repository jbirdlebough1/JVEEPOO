package com.veepoo.protocol.util;

import android.util.Log;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/VPLogger.class */
public class VPLogger extends BluetoothLog {
    public static boolean isDebug = true;

    public static void setDebug(boolean z) {
        BluetoothLog.setDebug(z);
        isDebug = z;
    }

    public static void i(String str) {
        if (isDebug) {
            Log.i("vepo-profile", str);
        }
    }

    public static void e(String str) {
        if (isDebug) {
            Log.e("vepo-profile", str);
        }
    }

    public static void v(String str) {
        if (isDebug) {
            Log.v("vepo-profile", str);
        }
    }

    public static void d(String str) {
        if (isDebug) {
            Log.d("vepo-profile", str);
        }
    }

    public static void w(String str) {
        if (isDebug) {
            Log.w("vepo-profile", str);
        }
    }

    private static String getThrowableString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        while (th != null) {
            Throwable th2 = th;
            th2.printStackTrace(printWriter);
            th = th2.getCause();
        }
        String string = stringWriter.toString();
        printWriter.close();
        return string;
    }

    public static void e(Throwable th) {
        if (isDebug) {
            getThrowableString(th);
        }
    }

    public static void w(Throwable th) {
        if (isDebug) {
            getThrowableString(th);
        }
    }
}
