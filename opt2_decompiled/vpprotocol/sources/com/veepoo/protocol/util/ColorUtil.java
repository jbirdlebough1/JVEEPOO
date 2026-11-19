package com.veepoo.protocol.util;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ColorUtil.class */
public class ColorUtil {
    public static byte[] intColorToRGB(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static String intColorToHexStr(int i) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(intColorToRGB(i));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#");
        for (int i2 = 1; i2 < strArrByte2HexToStrArr.length; i2++) {
            stringBuffer.append(strArrByte2HexToStrArr[i2]);
        }
        return stringBuffer.toString();
    }
}
