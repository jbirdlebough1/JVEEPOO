package com.inuker.bluetooth.library.bluetrum.log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/log/Logger.class */
public class Logger {
    private static ILogger logger;
    private static int logLevel = 4;

    public static void setLogger(ILogger logger2) {
        logger = logger2;
    }

    public static void setLogLevel(int logLevel2) {
        logLevel = logLevel2;
    }

    public static void log(int logLevel2, @Nullable String tag, @NonNull String message, @Nullable Throwable t) {
        if (logger != null && logLevel2 >= logLevel) {
            logger.log(logLevel2, tag, message, t);
        }
    }

    public static void v(@Nullable String tag, @NonNull String msg) {
        log(2, tag, msg, null);
    }

    public static void v(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(2, tag, msg, tr);
    }

    public static void d(@Nullable String tag, @NonNull String msg) {
        log(3, tag, msg, null);
    }

    public static void d(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(3, tag, msg, tr);
    }

    public static void i(@Nullable String tag, @NonNull String msg) {
        log(4, tag, msg, null);
    }

    public static void i(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(4, tag, msg, tr);
    }

    public static void w(@Nullable String tag, @NonNull String msg) {
        log(5, tag, msg, null);
    }

    public static void w(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(5, tag, msg, tr);
    }

    public static void w(@Nullable String tag, @NonNull Throwable tr) {
        log(5, tag, "", tr);
    }

    public static void e(@Nullable String tag, @NonNull String msg) {
        log(6, tag, msg, null);
    }

    public static void e(@Nullable String tag, @Nullable Throwable tr) {
        log(6, tag, "", tr);
    }

    public static void e(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(6, tag, msg, tr);
    }

    public static void wtf(@Nullable String tag, @NonNull String msg) {
        log(7, tag, msg, null);
    }

    public static void wtf(@Nullable String tag, @NonNull Throwable tr) {
        log(7, tag, "", tr);
    }

    public static void wtf(@Nullable String tag, @NonNull String msg, @Nullable Throwable tr) {
        log(7, tag, msg, tr);
    }
}
