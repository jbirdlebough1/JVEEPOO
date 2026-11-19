package com.veepoo.protocol.util;

import java.io.FileInputStream;
import java.security.MessageDigest;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_x.class */
public abstract class vp_x {
    private static final char[] vp_a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String vp_a(String str) {
        byte[] bArr = new byte[1024];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i <= 0) {
                    fileInputStream.close();
                    return vp_a(messageDigest.digest());
                }
                messageDigest.update(bArr, 0, i);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static String vp_a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            char[] cArr = vp_a;
            sb.append(cArr[(bArr[i] & 240) >>> 4]);
            sb.append(cArr[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
