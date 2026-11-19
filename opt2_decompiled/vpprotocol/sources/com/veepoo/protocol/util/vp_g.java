package com.veepoo.protocol.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_g.class */
public abstract class vp_g {
    static final char[] vp_a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String vp_b(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return new String(cArr2);
    }

    public static String vp_d(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        if (bArr.length - 1 == -1) {
            return "[]";
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            char[] cArr2 = vp_a;
            byte b = bArr[i];
            cArr[i2] = cArr2[(b & 240) >> 4];
            cArr[i2 + 1] = cArr2[b & 15];
        }
        return vp_a(new String(cArr), ",");
    }

    public static String vp_c(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return vp_a(new String(cArr2), " ");
    }

    public static int[] vp_e(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        String[] strArrVp_f = vp_f(bArr);
        int length = strArrVp_f.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.valueOf(strArrVp_f[i], 16).intValue();
        }
        return iArr;
    }

    public static String[] vp_f(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return vp_b(new String(cArr2));
    }

    public static int vp_g(byte[] bArr) {
        if (bArr == null || bArr.length != 2) {
            throw new IllegalArgumentException("The byte array must be exactly 2 bytes long");
        }
        return ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    public static int vp_a(byte[] bArr) {
        if (bArr.length == 4) {
            return (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680) | ((bArr[3] << 24) & (-16777216));
        }
        throw new IllegalArgumentException("The byte array must be exactly 4 bytes long");
    }

    public static byte[] vp_c(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String vp_b(String str, int i) {
        String str2 = "";
        try {
            str2 = new String(vp_a(str, i), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            str.printStackTrace();
        }
        return str2;
    }

    public static byte[] vp_d(int i) {
        byte[] bArr = new byte[2];
        if (i > 65535) {
            return null;
        }
        bArr[0] = (byte) (i >> 8);
        bArr[1] = (byte) (i & 255);
        return bArr;
    }

    public static int vp_a(byte[] bArr, int i) {
        byte[] bArrVp_a = vp_a(bArr, i, 4);
        if (bArrVp_a == null || bArrVp_a.length != 4) {
            throw new IllegalArgumentException("The byte array must be exactly 4 bytes long");
        }
        return vp_a(bArrVp_a);
    }

    public static int vp_c(byte b, byte b2) {
        return ((b << 8) & 65535) | (b2 & 255);
    }

    public static int vp_d(byte b, byte b2) {
        return (short) ((b << 8) | (b2 & 255));
    }

    public static byte[] vp_b(int i) {
        return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    public static int vp_a(byte b) {
        return b & 255;
    }

    public static byte vp_b(short s) {
        return (byte) (s & 255);
    }

    public static byte[] vp_a(String str, int i) {
        Charset charset = StandardCharsets.UTF_8;
        byte[] bytes = str.getBytes(charset);
        if (bytes.length <= i) {
            return bytes;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(bytes, 0, bArr, 0, i);
        byte[] bytes2 = new String(bArr, charset).getBytes();
        int length = bytes2.length;
        if (bytes2.length > 6) {
            int i2 = length - 6;
            if (bytes2[i2] == -17 && bytes2[length - 5] == -65 && bytes2[length - 4] == -67 && bytes2[length - 3] == -17 && bytes2[length - 2] == -65 && bytes2[length - 1] == -67) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bytes2, 0, bArr2, 0, i2);
                return bArr2;
            }
        }
        if (bytes2.length > 3) {
            int i3 = length - 3;
            if (bytes2[i3] == -17 && bytes2[length - 2] == -65 && bytes2[length - 1] == -67) {
                byte[] bArr3 = new byte[i3];
                System.arraycopy(bytes2, 0, bArr3, 0, i3);
                return bArr3;
            }
        }
        if (bytes2.length > 2) {
            int i4 = length - 2;
            if (bytes2[i4] == -17 && bytes2[length - 1] == -65) {
                byte[] bArr4 = new byte[i4];
                System.arraycopy(bytes2, 0, bArr4, 0, i4);
                return bArr4;
            }
        }
        return bytes2;
    }

    public static int vp_a(String str) {
        return Integer.valueOf(str, 16).intValue();
    }

    public static String[] vp_b(String str) {
        int length = str.length() / 2;
        int i = length;
        if (length * 2 < str.length()) {
            i++;
        }
        String[] strArr = new String[i];
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (i3 % 2 == 0) {
                strArr[i2] = str.charAt(i3) + "";
            } else {
                strArr[i2] = strArr[i2] + str.charAt(i3);
                i2++;
            }
        }
        return strArr;
    }

    public static byte vp_a(short s) {
        return (byte) (s >> 8);
    }

    public static int vp_b(byte b, byte b2) {
        return ((b & 255) << 8) | (b2 & 255);
    }

    public static byte[] vp_a(int i) {
        byte[] bArr = new byte[2];
        if (i > 65535) {
            return null;
        }
        bArr[0] = (byte) (i >> 8);
        bArr[1] = (byte) (i & 255);
        return bArr;
    }

    public static int vp_a(byte b, byte b2) {
        return ((b & 255) << 8) | (b2 & 255);
    }

    public static String vp_a(String str, String str2) {
        int length = str.length() / 2;
        int i = length;
        if (length * 2 < str.length()) {
            i++;
        }
        String[] strArr = new String[i];
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (i3 % 2 == 0) {
                strArr[i2] = str.charAt(i3) + "";
            } else {
                strArr[i2] = strArr[i2] + str.charAt(i3);
                i2++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < i; i4++) {
            sb.append(strArr[i4]).append(str2);
        }
        return sb.toString();
    }

    public static byte[] vp_a(byte[] bArr, int i, int i2) {
        if (bArr.length < i + i2) {
            return null;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }
}
