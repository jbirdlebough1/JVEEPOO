package com.inuker.bluetooth.library.log;

import com.inuker.bluetooth.library.utils.ByteUtils;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/log/ConvertHelper.class */
public class ConvertHelper {
    static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String TAG = ConvertHelper.class.getSimpleName();

    public static int mergeByte2Hex(byte high, byte low) {
        return ((high & 255) << 8) | (low & 255);
    }

    public static byte[] intToByteArray(int value) {
        byte[] mValue = {(byte) (value & ByteUtils.BYTE_MAX), (byte) ((value >> 8) & ByteUtils.BYTE_MAX)};
        return mValue;
    }

    public static String byte2Hex(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return new String(newChar);
    }

    public static String byte2HexForShow(byte[] b) {
        if (b == null) {
            return "null";
        }
        int iMax = b.length - 1;
        if (iMax == -1) {
            return "[]";
        }
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex[b[i] & 15];
        }
        return spilt2wordstr(new String(newChar), ",");
    }

    public static String byte2HexForMac(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return spilt2wordstr(new String(newChar), ":");
    }

    public static String byte2HexForHardware(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return spilt2wordstr(new String(newChar), " ");
    }

    public static String byte2HexForIOS(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return new String(newChar);
    }

    public static int[] byte2HexForAdcTranslateIntArr(byte[] b) {
        int[] rateViewArr = new int[8];
        String[] strArr = byte2HexToStrArr(b);
        for (int i = 0; i < rateViewArr.length; i++) {
            String s = strArr[(i * 2) + 1] + strArr[i * 2];
            rateViewArr[i] = Integer.valueOf(s, 16).intValue();
        }
        return rateViewArr;
    }

    public static int[] byte2HexForAdcTranslateShortArr(byte[] b) {
        int[] rateViewArr = new int[8];
        String[] strArr = byte2HexToStrArr(b);
        for (int i = 0; i < rateViewArr.length; i++) {
            String s = strArr[(i * 2) + 1] + strArr[i * 2];
            rateViewArr[i] = Integer.valueOf(s, 16).shortValue();
        }
        return rateViewArr;
    }

    public static String byte2HexForHardwareDebug(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return spilt2wordstr(new String(newChar), ",", "0x");
    }

    public static String byte2HexForHardwareDebugDaily(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return spilt2wordDaily(new String(newChar), ",", "0x");
    }

    public static int hexStrToInt(String str) {
        return Integer.valueOf(str, 16).intValue();
    }

    public static String byte2HexToIntForShow(byte[] value) {
        String[] str = byte2HexToStrArr(value);
        int[] data = new int[str.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.valueOf(str[i], 16).intValue();
        }
        StringBuilder sb = new StringBuilder();
        for (int datum : data) {
            sb.append(datum).append(",");
        }
        return sb.toString();
    }

    public static int[] byte2HexToIntArr(byte[] value) {
        if (value == null || value.length == 0) {
            return null;
        }
        String[] str = byte2HexToStrArr(value);
        int[] data = new int[str.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.valueOf(str[i], 16).intValue();
        }
        return data;
    }

    public static int[] HexStrArrToIntArr(String[] str) {
        int[] data = new int[str.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.valueOf(str[i], 16).intValue();
        }
        return data;
    }

    public static String[] byte2HexToStrArr(byte[] b) {
        char[] hex2 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex2[(b[i] & 240) >> 4];
            newChar[(2 * i) + 1] = hex2[b[i] & 15];
        }
        return spilt2word(new String(newChar));
    }

    public static byte[] HexStringToBinary(String hexString) {
        if (hexString != null) {
            byte[] result = new byte[hexString.length() / 2];
            for (int i = 0; i < hexString.length() / 2; i++) {
                result[i] = (byte) (Integer.parseInt(hexString.substring(i * 2, (i * 2) + 2), 16) & ByteUtils.BYTE_MAX);
            }
            return result;
        }
        return null;
    }

    public static String[] spilt2word(String str) {
        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strs = new String[m];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                strs[j] = str.charAt(i) + "";
            } else {
                strs[j] = strs[j] + str.charAt(i);
                j++;
            }
        }
        return strs;
    }

    public static String spilt2wordstr(String str, String rex) {
        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strs = new String[m];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                strs[j] = str.charAt(i) + "";
            } else {
                strs[j] = strs[j] + str.charAt(i);
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s).append(rex);
        }
        return sb.toString();
    }

    public static String spilt2wordDaily(String str, String rex, String head) {
        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strs = new String[m];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                strs[j] = str.charAt(i) + "";
            } else {
                strs[j] = strs[j] + str.charAt(i);
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 3; i2 < strs.length - 1; i2++) {
            sb.append(head).append(strs[i2]).append(rex);
        }
        return sb.toString();
    }

    public static String spilt2wordstr(String str, String rex, String head) {
        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strs = new String[m];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {
                strs[j] = str.charAt(i) + "";
            } else {
                strs[j] = strs[j] + str.charAt(i);
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(head).append(s).append(rex);
        }
        return sb.toString();
    }

    public static byte[] intToBytes(int res) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (res >>> (24 - (i * 8)));
        }
        return b;
    }

    public static byte loUint16(short v) {
        return (byte) (v & 255);
    }

    public static byte hiUint16(short v) {
        return (byte) (v >> 8);
    }

    public static String getNickString(String title, int length) {
        byte[] value = getNickByte(title, length);
        String str = new String(value, StandardCharsets.UTF_8);
        return str;
    }

    public static byte[] getNickByte(String title, int length) {
        byte[] aArray = title.getBytes(StandardCharsets.UTF_8);
        if (aArray.length > length) {
            byte[] aimByteUtf = new byte[length];
            System.arraycopy(aArray, 0, aimByteUtf, 0, length);
            String title2 = new String(aimByteUtf, StandardCharsets.UTF_8);
            byte[] bArray = title2.getBytes();
            int lengthNew = bArray.length;
            if (bArray.length > 6 && bArray[lengthNew - 6] == -17 && bArray[lengthNew - 5] == -65 && bArray[lengthNew - 4] == -67 && bArray[lengthNew - 3] == -17 && bArray[lengthNew - 2] == -65 && bArray[lengthNew - 1] == -67) {
                byte[] dArray = new byte[lengthNew - 6];
                System.arraycopy(bArray, 0, dArray, 0, lengthNew - 6);
                return dArray;
            }
            if (bArray.length > 3 && bArray[lengthNew - 3] == -17 && bArray[lengthNew - 2] == -65 && bArray[lengthNew - 1] == -67) {
                byte[] cArray = new byte[lengthNew - 3];
                System.arraycopy(bArray, 0, cArray, 0, lengthNew - 3);
                return cArray;
            }
            if (bArray.length > 2 && bArray[lengthNew - 2] == -17 && bArray[lengthNew - 1] == -65) {
                byte[] cArray2 = new byte[lengthNew - 2];
                System.arraycopy(bArray, 0, cArray2, 0, lengthNew - 2);
                return cArray2;
            }
            return bArray;
        }
        return aArray;
    }

    public static int getFirstBit16(int value, int position) {
        return value >> (16 - position);
    }

    public static int getFirstBit24(int value, int position) {
        return value >> (24 - position);
    }

    public static int getLastBit16(int value, int position) {
        return value % ((int) Math.pow(2.0d, position));
    }

    public static int getMiddleBit16(int value, int lastPosition, int middlePosition) {
        int values = getFirstBit16(value, lastPosition);
        return getLastBit16(values, middlePosition);
    }

    public static int twoByteToSignedInt(byte high, byte low) {
        return (high << 8) | low;
    }

    public static int twoByteToUnsignedInt(byte high, byte low) {
        return ((high << 8) & 65535) | (low & 255);
    }

    public static byte[] intToTwoByte(int numInt) {
        byte[] rest = new byte[2];
        if (numInt < -32768 || numInt > 32767) {
            return null;
        }
        rest[0] = (byte) (numInt >> 8);
        rest[1] = (byte) (numInt & ByteUtils.BYTE_MAX);
        return rest;
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        if (bt1 == null && bt2 == null) {
            return new byte[0];
        }
        if (bt1 == null) {
            return bt2;
        }
        if (bt2 == null) {
            return bt1;
        }
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static int twoByteToInt(byte high, byte low) {
        int high_id = high & 255;
        int low_id = low & 255;
        int combineValue = (high_id << 8) | low_id;
        return combineValue;
    }

    public static byte[] string2ByteArray(String str) {
        String s = str.replace(" ", "");
        int stringLen = s.length();
        int len = stringLen / 2;
        if (stringLen % 2 == 1) {
            s = "0" + s;
            int i = stringLen + 1;
            len++;
        }
        byte[] data = new byte[len];
        for (int i2 = 0; i2 < len; i2++) {
            data[i2] = (byte) Integer.parseInt(s.substring(2 * i2, (2 * i2) + 2), 16);
        }
        return data;
    }
}
