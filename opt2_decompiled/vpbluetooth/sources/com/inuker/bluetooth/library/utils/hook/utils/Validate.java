package com.inuker.bluetooth.library.utils.hook.utils;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/hook/utils/Validate.class */
public class Validate {
    public static void isTrue(boolean expression, String message, Object... values) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }
}
