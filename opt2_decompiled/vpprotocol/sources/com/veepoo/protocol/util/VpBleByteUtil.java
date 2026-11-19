package com.veepoo.protocol.util;

import android.text.TextUtils;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.model.BleGattService;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.model.enums.ECPUPlatform;
import com.veepoo.protocol.model.enums.ECpuType;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/VpBleByteUtil.class */
public class VpBleByteUtil {
    public static double getDoubleValue(String str) {
        double dDoubleValue = 0.0d;
        if (TextUtils.isEmpty(str)) {
            return dDoubleValue;
        }
        try {
            dDoubleValue = Double.valueOf(str).doubleValue();
        } catch (Exception unused) {
        }
        return dDoubleValue;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [boolean, java.lang.Throwable] */
    public static int getInterValue(String str) {
        int iFloatValue = 0;
        ?? IsNotNull = isNotNull(str);
        if (IsNotNull != 0) {
            try {
                iFloatValue = (int) Float.valueOf(str).floatValue();
            } catch (Exception unused) {
                IsNotNull.printStackTrace();
            }
        }
        return iFloatValue;
    }

    public static boolean isNotNull(String str) {
        return (str == null || str.equals("")) ? false : true;
    }

    public static final String byte2Hex(byte[] bArr) {
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

    public static final String byte2HexForShow(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return spilt2wordstr(new String(cArr2), ",");
    }

    public static byte[] hexString2Byte(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.replace(",", "").toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((vp_a(charArray[i2]) << 4) | vp_a(charArray[i2 + 1]));
        }
        return bArr;
    }

    private static byte vp_a(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static final String byte2HexForHardware(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return spilt2wordstr(new String(cArr2), " ");
    }

    public static final int[] byte2HexForAdcTranslateIntArr(byte[] bArr) {
        int[] iArr = new int[8];
        String[] strArrByte2HexToStrArr = byte2HexToStrArr(bArr);
        for (int i = 0; i < 8; i++) {
            int i2 = i * 2;
            iArr[i] = Integer.valueOf(strArrByte2HexToStrArr[i2 + 1] + strArrByte2HexToStrArr[i2], 16).intValue();
        }
        return iArr;
    }

    public static final String byte2HexForHardwareDebug(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return spilt2wordstr(new String(cArr2), ",", "0x");
    }

    public static final String byte2HexForHardwareDebugDaily(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return spilt2wordDaily(new String(cArr2), ",", "0x");
    }

    public static final int hexStrToInt(String str) {
        return Integer.valueOf(str, 16).intValue();
    }

    public static final String byte2HexToIntForShow(byte[] bArr) {
        String[] strArrByte2HexToStrArr = byte2HexToStrArr(bArr);
        int length = strArrByte2HexToStrArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.valueOf(strArrByte2HexToStrArr[i], 16).intValue();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < length; i2++) {
            stringBuffer.append(iArr[i2] + ",");
        }
        return stringBuffer.toString();
    }

    public static final int[] byte2HexToIntArr(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String[] strArrByte2HexToStrArr = byte2HexToStrArr(bArr);
        int length = strArrByte2HexToStrArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.valueOf(strArrByte2HexToStrArr[i], 16).intValue();
        }
        return iArr;
    }

    public static final int[] HexStrArrToIntArr(String[] strArr) {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.valueOf(strArr[i], 16).intValue();
        }
        return iArr;
    }

    public static final String[] byte2HexToStrArr(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            byte b = bArr[i2];
            cArr2[i3] = cArr[(b & 240) >> 4];
            cArr2[i3 + 1] = cArr[b & 15];
        }
        return spilt2word(new String(cArr2));
    }

    public static byte[] HexStringToBinary(String str) {
        if (str == null) {
            return null;
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Integer.parseInt(str.substring(i2, i2 + 2), 16) & 255);
        }
        return bArr;
    }

    public static String[] spilt2word(String str) {
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

    public static String spilt2wordstr(String str, String str2) {
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i; i4++) {
            stringBuffer.append(strArr[i4] + str2);
        }
        return new String(stringBuffer.toString());
    }

    public static String spilt2wordDaily(String str, String str2, String str3) {
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 3; i4 < i - 1; i4++) {
            stringBuffer.append(str3 + strArr[i4] + str2);
        }
        return new String(stringBuffer.toString());
    }

    public static byte[] intToBytes(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[i2] = (byte) (i >>> (24 - (i2 * 8)));
        }
        return bArr;
    }

    public static byte loUint16(short s) {
        return (byte) (s & 255);
    }

    public static byte hiUint16(short s) {
        return (byte) (s >> 8);
    }

    public static String TranslateBlueAddressAdd(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        int length = str.length();
        if (length >= 2) {
            int i = length - 2;
            String upperCase = (str.substring(0, i) + TranslateBlueStr(str.substring(i, length))).toUpperCase();
            System.out.println(str + "-->" + upperCase);
            str = upperCase;
        }
        return str;
    }

    public static String TranslateBlueStr(String str) {
        try {
            int iIntValue = Integer.valueOf(str, 16).intValue() + 1;
            str = Integer.toHexString(iIntValue);
            if (iIntValue <= 15) {
                str = "0" + str;
            }
            if (str.length() > 2) {
                str = str.substring(1, 3);
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static String TranslateBlueAddressDelete(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        int length = str.length();
        if (length >= 2) {
            int i = length - 2;
            String upperCase = (str.substring(0, i) + TranslateBlueStrDelete(str.substring(i, length))).toUpperCase();
            System.out.println(str + "-->" + upperCase);
            str = upperCase;
        }
        return str;
    }

    public static String TranslateBlueStrDelete(String str) {
        try {
            int iIntValue = Integer.valueOf(str, 16).intValue() - 1;
            str = Integer.toHexString(iIntValue);
            if (iIntValue <= 15) {
                str = "0" + str;
            }
            if (str.length() > 2) {
                str = str.substring(1, 3);
            }
        } catch (Exception unused) {
        }
        return str;
    }

    public static boolean isBeyondVp(byte[] bArr) {
        byte b;
        boolean z = false;
        if (bArr == null) {
            return false;
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr2 = null;
        while (byteBufferOrder.remaining() > 2 && (b = byteBufferOrder.get()) != 0) {
            byte b2 = byteBufferOrder.get();
            if (b2 == -1) {
                byteBufferOrder.get(new byte[b - 1]);
            } else if (b2 != 3) {
                try {
                    byteBufferOrder.position((byteBufferOrder.position() + b) - 1);
                } catch (IllegalArgumentException unused) {
                    return false;
                }
            } else {
                byte[] bArr3 = new byte[b - 1];
                bArr2 = bArr3;
                byteBufferOrder.get(bArr3);
            }
        }
        if (bArr2 != null && bArr2.length >= 2) {
            byte b3 = bArr2[0];
            byte b4 = bArr2[1];
            if ((b3 == -1 && b4 == -1) || ((b3 == 13 && b4 == 24) || ((b3 == -25 && b4 == -2) || (b3 == 1 && b4 == 0)))) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isBeyondVpV2(byte[] bArr) {
        byte b;
        boolean z = false;
        if (bArr == null) {
            return false;
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr2 = null;
        while (byteBufferOrder.remaining() > 2 && (b = byteBufferOrder.get()) != 0) {
            byte b2 = byteBufferOrder.get();
            if (b2 == -1) {
                byte[] bArr3 = new byte[b - 1];
                bArr2 = bArr3;
                byteBufferOrder.get(bArr3);
            } else if (b2 != 3) {
                try {
                    byteBufferOrder.position((byteBufferOrder.position() + b) - 1);
                } catch (IllegalArgumentException unused) {
                    return false;
                }
            } else {
                byteBufferOrder.get(new byte[b - 1]);
            }
        }
        if (bArr2 != null && bArr2.length >= 2) {
            byte b3 = bArr2[0];
            byte b4 = bArr2[1];
            if (b3 == -8 && b4 == -8) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v17, types: [byte, int, java.lang.Throwable] */
    public static boolean isBrandDevice(byte[] bArr) {
        byte b;
        boolean z = false;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byte[] bArr2 = null;
        while (byteBufferOrder.remaining() > 2 && (b = byteBufferOrder.get()) != 0) {
            ?? r0 = byteBufferOrder.get();
            System.out.println("length=" + ((int) b) + ",type=" + ((int) r0));
            if (r0 == -1) {
                byte[] bArr3 = new byte[b - 1];
                bArr2 = bArr3;
                byteBufferOrder.get(bArr3);
            } else {
                try {
                    byteBufferOrder.position((byteBufferOrder.position() + b) - 1);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw r0;
                }
            }
        }
        if (bArr2 != null && bArr2.length >= 2) {
            byte b2 = bArr2[0];
            byte b3 = bArr2[1];
            if (b2 == -7 && b3 == -7) {
                z = true;
            }
        }
        return z;
    }

    public static boolean isOadModel(BleGattProfile bleGattProfile) {
        boolean z;
        List services = bleGattProfile.getServices();
        new StringBuilder().append("BleGattService OAD_UUID_51:").append(com.veepoo.protocol.profile.vp_a.vp_i);
        new StringBuilder().append("BleGattService OAD_UUID_52_EP02_SERVICE:").append(com.veepoo.protocol.profile.vp_a.vp_j);
        Iterator it = services.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            UUID uuid = ((BleGattService) it.next()).getUUID();
            new StringBuilder().append("BleGattService:").append(uuid);
            if (uuid.equals(com.veepoo.protocol.profile.vp_a.vp_i) || uuid.equals(com.veepoo.protocol.profile.vp_a.vp_j) || uuid.equals(com.veepoo.protocol.profile.vp_a.vp_k)) {
                z = true;
                break;
            }
        }
        if (z) {
            BluetoothLog.i("设备-固件升级模式");
        } else {
            BluetoothLog.i("设备-正常模式");
        }
        return z;
    }

    public static ECPUPlatform getCPUPlatform(BleGattProfile bleGattProfile) {
        ECPUPlatform eCPUPlatform = ECPUPlatform.NORDIC;
        List services = bleGattProfile.getServices();
        new StringBuilder().append("BleGattService OAD_UUID_51:").append(com.veepoo.protocol.profile.vp_a.vp_i);
        new StringBuilder().append("BleGattService OAD_UUID_52_EP02_SERVICE:").append(com.veepoo.protocol.profile.vp_a.vp_j);
        new StringBuilder().append("BleGattService OAD_UUID_SERVICE_HUIDING:").append(com.veepoo.protocol.profile.vp_a.vp_k);
        Iterator it = services.iterator();
        while (it.hasNext()) {
            UUID uuid = ((BleGattService) it.next()).getUUID();
            new StringBuilder().append("BleGattService:").append(uuid);
            if (uuid.equals(com.veepoo.protocol.profile.vp_a.vp_i) || uuid.equals(com.veepoo.protocol.profile.vp_a.vp_j)) {
                eCPUPlatform = ECPUPlatform.NORDIC;
                break;
            }
            if (uuid.equals(com.veepoo.protocol.profile.vp_a.vp_k)) {
                eCPUPlatform = ECPUPlatform.GOODIX;
                break;
            }
            eCPUPlatform = ECPUPlatform.NORDIC;
        }
        return eCPUPlatform;
    }

    public static ECpuType getCpuType(BleGattProfile bleGattProfile) {
        ECpuType eCpuType = ECpuType.NORIC;
        List services = bleGattProfile.getServices();
        new StringBuilder().append("BleGattService OAD_UUID_51:").append(com.veepoo.protocol.profile.vp_a.vp_i);
        new StringBuilder().append("BleGattService OAD_UUID_52_EP02_SERVICE:").append(com.veepoo.protocol.profile.vp_a.vp_j);
        new StringBuilder().append("BleGattService OAD_UUID_SERVICE_HUIDING:").append(com.veepoo.protocol.profile.vp_a.vp_k);
        Iterator it = services.iterator();
        while (it.hasNext()) {
            UUID uuid = ((BleGattService) it.next()).getUUID();
            new StringBuilder().append("BleGattService:").append(uuid);
            if (uuid.equals(com.veepoo.protocol.profile.vp_a.vp_i) || uuid.equals(com.veepoo.protocol.profile.vp_a.vp_j)) {
                eCpuType = ECpuType.NORIC;
                break;
            }
            if (uuid.equals(com.veepoo.protocol.profile.vp_a.vp_k)) {
                eCpuType = ECpuType.HUITOP;
                break;
            }
            eCpuType = ECpuType.NORIC;
        }
        return eCpuType;
    }

    public static String intArrtoString(int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (iArr == null || iArr.length == 0) {
            return stringBuffer.toString();
        }
        for (int i = 0; i < iArr.length; i++) {
            if (i == iArr.length - 1) {
                stringBuffer.append(iArr[i]);
            } else {
                stringBuffer.append(iArr[i] + ",");
            }
        }
        return stringBuffer.toString();
    }

    public static int[] stringToIntArr(String str) {
        String[] strArrSplit = str.split(",");
        int length = strArrSplit.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = Integer.valueOf(strArrSplit[i]).intValue();
        }
        return iArr;
    }

    public static int twoByteToUnsignedInt(byte b, byte b2) {
        return ((b << 8) & 65535) | (b2 & 255);
    }

    public static int twoByteToUnsignedInt2(byte b, byte b2) {
        return ((b2 << 8) & 65535) | (b & 255);
    }

    public static byte[] byteMerger(byte[] bArr, byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return new byte[0];
        }
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public static int convertByteToSignInt(byte b) {
        int i = ((b & 255) >> 7) & 1;
        int i2 = b & 127;
        if (i == 1) {
            i2 = -i2;
        }
        return i2;
    }

    public static int convert_ecg_value_J(int i) {
        return ((i >> 15) & 1) == 0 ? i & 32767 : i | (-32768);
    }

    public static int littleEndianByteArray2Int(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferWrap.getInt();
    }

    public static String spilt2wordstr(String str, String str2, String str3) {
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
        StringBuffer stringBuffer = new StringBuffer();
        for (int i4 = 0; i4 < i; i4++) {
            stringBuffer.append(str3 + strArr[i4] + str2);
        }
        return new String(stringBuffer.toString());
    }
}
