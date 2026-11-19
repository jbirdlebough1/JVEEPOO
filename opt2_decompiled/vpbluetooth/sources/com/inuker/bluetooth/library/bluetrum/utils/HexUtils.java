package com.inuker.bluetooth.library.bluetrum.utils;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/utils/HexUtils.class */
public final class HexUtils {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 3];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 3] = HEX_ARRAY[v >>> 4];
            hexChars[(j * 3) + 1] = HEX_ARRAY[v & 15];
            hexChars[(j * 3) + 2] = ' ';
        }
        return new String(hexChars);
    }
}
