package com.inuker.bluetooth.library.utils;

import android.os.Build;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/utils/Version.class */
public class Version {
    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
