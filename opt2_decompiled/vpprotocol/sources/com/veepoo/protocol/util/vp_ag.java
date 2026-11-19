package com.veepoo.protocol.util;

import android.text.TextUtils;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.model.settings.ContentPhoneSetting;
import com.veepoo.protocol.model.settings.ContentSmsSetting;
import com.veepoo.protocol.model.settings.ContentSocailSetting;
import com.veepoo.protocol.model.settings.SoldierContentSetting;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_ag.class */
public abstract class vp_ag {
    private static final byte[] vp_a = {-17, -65, -67};

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

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String, java.lang.Throwable] */
    public static byte[] vp_a(String str, int i) {
        ?? string = str.toString();
        try {
            byte[] bytes = string.getBytes("UTF-8");
            if (bytes.length <= i) {
                return bytes;
            }
            byte[] bArr = new byte[i];
            System.arraycopy(bytes, 0, bArr, 0, i);
            byte[] bytes2 = new String(bArr, "UTF-8").getBytes();
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
        } catch (UnsupportedEncodingException unused) {
            string.printStackTrace();
            return new byte[0];
        }
    }

    public static List vp_b(ContentPhoneSetting contentPhoneSetting) {
        ArrayList arrayList = new ArrayList();
        boolean zVp_a = vp_a(contentPhoneSetting.getContactName());
        byte[] bArrVp_a = vp_a(zVp_a ? contentPhoneSetting.getContactName() : contentPhoneSetting.getContactPhoneNumber(), 12);
        int length = bArrVp_a.length;
        byte[] bArr = new byte[20];
        bArr[0] = -62;
        bArr[1] = 0;
        bArr[2] = VpBleByteUtil.loUint16((short) length);
        bArr[3] = 1;
        bArr[4] = 1;
        if (zVp_a) {
            bArr[5] = 1;
        } else {
            bArr[5] = 0;
        }
        System.arraycopy(bArrVp_a, 0, bArr, 6, length);
        arrayList.add(bArr);
        return arrayList;
    }

    public static List vp_a(ContentPhoneSetting contentPhoneSetting) throws UnsupportedEncodingException {
        boolean zVp_a = vp_a(contentPhoneSetting.getContactName());
        String contactName = zVp_a ? contentPhoneSetting.getContactName() : contentPhoneSetting.getContactPhoneNumber();
        int contactLength = contentPhoneSetting.getContactLength();
        byte[] bytes = contactName.getBytes("UTF-8");
        int length = bytes.length;
        int i = length / 14;
        if (length % 14 != 0) {
            i++;
        }
        if (i <= contactLength) {
            contactLength = i;
        }
        ArrayList arrayList = new ArrayList(contactLength);
        for (int i2 = 1; i2 <= contactLength; i2++) {
            byte[] bArr = new byte[20];
            bArr[0] = -62;
            bArr[1] = 0;
            bArr[3] = VpBleByteUtil.loUint16((short) contactLength);
            bArr[4] = VpBleByteUtil.loUint16((short) i2);
            if (zVp_a) {
                bArr[5] = 1;
            } else {
                bArr[5] = 0;
            }
            if (i2 == i) {
                int i3 = (i2 - 1) * 14;
                short s = (short) (length - i3);
                bArr[2] = VpBleByteUtil.loUint16(s);
                System.arraycopy(bytes, i3, bArr, 6, VpBleByteUtil.loUint16(s));
            } else {
                bArr[2] = VpBleByteUtil.loUint16((short) 14);
                System.arraycopy(bytes, (i2 - 1) * 14, bArr, 6, 14);
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public static List vp_b(ContentSmsSetting contentSmsSetting) throws UnsupportedEncodingException {
        boolean zVp_a = vp_a(contentSmsSetting.getContactName());
        String contactName = zVp_a ? contentSmsSetting.getContactName() : contentSmsSetting.getContactPhoneNumber();
        if (TextUtils.isEmpty(contactName)) {
            contactName = " ";
        }
        String content = contentSmsSetting.getContent();
        int allLength = contentSmsSetting.getAllLength();
        byte[] bArrVp_a = vp_a(contactName, 12);
        int length = bArrVp_a.length;
        byte[] bytes = content.getBytes("UTF-8");
        int length2 = bytes.length;
        int i = length2 / 14;
        int i2 = i + 1;
        if (length2 % 14 != 0) {
            i2 = i + 2;
        }
        if (i2 <= allLength) {
            allLength = i2;
        }
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 1; i3 <= i2; i3++) {
            int i4 = i3;
            byte[] bArr = new byte[20];
            bArr[0] = -62;
            bArr[1] = 1;
            bArr[3] = VpBleByteUtil.loUint16((short) allLength);
            bArr[4] = VpBleByteUtil.loUint16((short) i3);
            bArr[5] = 2;
            if (i4 == 1) {
                if (zVp_a) {
                    bArr[5] = 1;
                } else {
                    bArr[5] = 0;
                }
                bArr[2] = VpBleByteUtil.loUint16((short) length);
                System.arraycopy(bArrVp_a, 0, bArr, 6, length);
            } else if (i3 == i2) {
                int i5 = (i3 - 2) * 14;
                short s = (short) (length2 - i5);
                bArr[2] = VpBleByteUtil.loUint16(s);
                System.arraycopy(bytes, i5, bArr, 6, VpBleByteUtil.loUint16(s));
            } else {
                System.arraycopy(bytes, (i3 - 2) * 14, bArr, 6, 14);
                bArr[2] = VpBleByteUtil.loUint16((short) 14);
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public static List vp_a(ContentSmsSetting contentSmsSetting) throws UnsupportedEncodingException {
        int contactLength = contentSmsSetting.getContactLength();
        int allLength = contentSmsSetting.getAllLength();
        boolean zVp_a = vp_a(contentSmsSetting.getContactName());
        String contactName = zVp_a ? contentSmsSetting.getContactName() : contentSmsSetting.getContactPhoneNumber();
        if (TextUtils.isEmpty(contactName)) {
            contactName = " ";
        }
        String content = contentSmsSetting.getContent();
        byte[] bArrVp_a = vp_a(contactName, contactLength * 14);
        int length = bArrVp_a.length;
        int i = length / 14;
        if (length % 14 != 0) {
            i++;
        }
        if (i <= contactLength) {
            contactLength = i;
        }
        byte[] bytes = content.getBytes("UTF-8");
        int length2 = bytes.length;
        int i2 = length2 / 14;
        if (length2 % 14 != 0) {
            i2++;
        }
        int i3 = contactLength + i2;
        if (i3 <= allLength) {
            allLength = i3;
        }
        if (contactLength > allLength) {
            allLength = contactLength;
        }
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 1; i4 <= contactLength; i4++) {
            byte[] bArr = new byte[20];
            bArr[0] = -62;
            bArr[1] = 1;
            bArr[3] = VpBleByteUtil.loUint16((short) allLength);
            bArr[4] = VpBleByteUtil.loUint16((short) i4);
            if (zVp_a) {
                bArr[5] = 1;
            } else {
                bArr[5] = 0;
            }
            if (i4 == contactLength) {
                int i5 = (i4 - 1) * 14;
                int i6 = length - i5;
                int i7 = i6;
                if (i6 > 14) {
                    i7 = 14;
                }
                short s = (short) i7;
                bArr[2] = VpBleByteUtil.loUint16(s);
                System.arraycopy(bArrVp_a, i5, bArr, 6, VpBleByteUtil.loUint16(s));
            } else {
                bArr[2] = VpBleByteUtil.loUint16((short) 14);
                System.arraycopy(bArrVp_a, (i4 - 1) * 14, bArr, 6, 14);
            }
            arrayList.add(bArr);
        }
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
        }
        for (int i9 = contactLength + 1; i9 <= allLength; i9++) {
            int i10 = i9;
            int i11 = allLength;
            byte[] bArr2 = new byte[20];
            bArr2[0] = -62;
            bArr2[1] = 1;
            bArr2[3] = VpBleByteUtil.loUint16((short) allLength);
            bArr2[4] = VpBleByteUtil.loUint16((short) i9);
            bArr2[5] = 2;
            if (i10 == i11) {
                int i12 = length2 - (((allLength - contactLength) - 1) * 14);
                int i13 = i12;
                if (i12 > 14) {
                    i13 = 14;
                }
                short s2 = (short) i13;
                bArr2[2] = VpBleByteUtil.loUint16(s2);
                System.arraycopy(bytes, ((i9 - contactLength) - 1) * 14, bArr2, 6, VpBleByteUtil.loUint16(s2));
            } else {
                bArr2[2] = VpBleByteUtil.loUint16((short) 14);
                System.arraycopy(bytes, ((i9 - contactLength) - 1) * 14, bArr2, 6, 14);
            }
            arrayList.add(bArr2);
        }
        for (int i14 = 0; i14 < arrayList.size(); i14++) {
        }
        return arrayList;
    }

    public static List vp_a(ContentSocailSetting contentSocailSetting) throws UnsupportedEncodingException {
        int allLength = contentSocailSetting.getAllLength();
        int contactLength = contentSocailSetting.getContactLength();
        byte value = contentSocailSetting.geteSocailMsg().getValue();
        String title = contentSocailSetting.getTitle();
        String content = contentSocailSetting.getContent();
        String strVp_b = contactLength == 0 ? vp_b(title, 12) : vp_b(title, contactLength * 14);
        if (!TextUtils.isEmpty(strVp_b) || TextUtils.isEmpty(content)) {
            if (!TextUtils.isEmpty(strVp_b) && TextUtils.isEmpty(content)) {
                content = strVp_b;
            } else if (TextUtils.isEmpty(strVp_b) || TextUtils.isEmpty(content)) {
                content = "";
            } else {
                content = strVp_b + ":" + content;
            }
        }
        byte[] bytes = content.getBytes("UTF-8");
        int length = bytes.length;
        int i = length / 14;
        if (length % 14 != 0) {
            i++;
        }
        if (i <= allLength) {
            allLength = i;
        }
        new StringBuilder().append("发送内容 = ").append(content).append(", 一包内容长度 = ").append(14).append(" - 数据长度 = ").append(length).append(" 总包数 = ").append(i).append(" readSendAllPackage = ").append(allLength);
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 1; i2 <= i; i2++) {
            int i3 = i2;
            int i4 = i;
            byte[] bArr = new byte[20];
            bArr[0] = -62;
            bArr[1] = value;
            bArr[3] = VpBleByteUtil.loUint16((short) allLength);
            bArr[4] = VpBleByteUtil.loUint16((short) i2);
            bArr[5] = 2;
            if (i3 == i4) {
                int i5 = (i2 - 1) * 14;
                short s = (short) (length - i5);
                bArr[2] = VpBleByteUtil.loUint16(s);
                System.arraycopy(bytes, i5, bArr, 6, VpBleByteUtil.loUint16(s));
            } else {
                bArr[2] = VpBleByteUtil.loUint16((short) 14);
                System.arraycopy(bytes, (i2 - 1) * 14, bArr, 6, 14);
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public static List vp_b(ContentSocailSetting contentSocailSetting) throws UnsupportedEncodingException {
        int allLength = contentSocailSetting.getAllLength();
        int contactLength = contentSocailSetting.getContactLength();
        byte value = contentSocailSetting.geteSocailMsg().getValue();
        String title = contentSocailSetting.getTitle();
        byte[] bytes = ((contactLength == 0 ? vp_b(title, 12) : vp_b(title, contactLength * 14)) + ":" + contentSocailSetting.getContent()).getBytes("UTF-8");
        int length = bytes.length;
        int iMax = Math.max(20, VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getMtuValue());
        int i = iMax - 6;
        int i2 = length / i;
        if (length % i != 0) {
            i2++;
        }
        if (i2 <= allLength) {
            allLength = i2;
        }
        new StringBuilder().append("一包内容长度 = 14- 数据长度  =").append(length).append(" 一包数据长度 = ").append(i).append(" 总包数 = ").append(i2).append(" readSendAllPackage = ").append(allLength);
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 1; i3 <= i2; i3++) {
            int i4 = i3;
            int i5 = i2;
            byte[] bArr = new byte[iMax];
            bArr[0] = -62;
            bArr[1] = value;
            bArr[3] = VpBleByteUtil.loUint16((short) allLength);
            bArr[4] = VpBleByteUtil.loUint16((short) i3);
            bArr[5] = 2;
            if (i4 == i5) {
                int i6 = (i3 - 1) * i;
                int i7 = length - i6;
                bArr[2] = VpBleByteUtil.loUint16((short) i7);
                int i8 = iMax - i;
                new StringBuilder().append("最后一包，第 ").append(i3).append(" 包 ，最后一包长度 =").append(i7).append(" 原数据开始位置 = ").append(i6).append(" cmd复制开始位置 = ").append(i8);
                System.arraycopy(bytes, i6, bArr, i8, i7);
            } else {
                bArr[2] = VpBleByteUtil.loUint16((short) i);
                int i9 = (i3 - 1) * i;
                int i10 = iMax - i;
                new StringBuilder().append("非最后一包，第 ").append(i3).append(" 包 ，复制长度 =").append(i).append(" 原数据开始位置 = ").append(i9).append(" cmd复制开始位置 = ").append(i10);
                System.arraycopy(bytes, i9, bArr, i10, i);
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public static List vp_a(SoldierContentSetting soldierContentSetting) {
        byte[] bArrVp_a = vp_a(soldierContentSetting.getContent(), 196);
        int length = bArrVp_a.length % 14 == 0 ? bArrVp_a.length / 14 : (bArrVp_a.length / 14) + 1;
        int length2 = bArrVp_a.length;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 1; i <= length; i++) {
            int i2 = length;
            byte[] bArr = new byte[20];
            bArr[0] = -62;
            bArr[1] = -1;
            bArr[3] = VpBleByteUtil.loUint16((short) length);
            bArr[4] = VpBleByteUtil.loUint16((short) i);
            bArr[5] = 2;
            if (i2 == 1) {
                bArr[2] = VpBleByteUtil.loUint16((short) length2);
                System.arraycopy(bArrVp_a, 0, bArr, 6, length2);
            } else if (i == length) {
                int i3 = (i - 1) * 14;
                short s = (short) (length2 - i3);
                bArr[2] = VpBleByteUtil.loUint16(s);
                System.arraycopy(bArrVp_a, i3, bArr, 6, VpBleByteUtil.loUint16(s));
            } else {
                System.arraycopy(bArrVp_a, (i - 1) * 14, bArr, 6, 14);
                bArr[2] = VpBleByteUtil.loUint16((short) 14);
            }
            arrayList.add(bArr);
        }
        return arrayList;
    }

    public static boolean vp_a(String str) {
        return !TextUtils.isEmpty(str);
    }
}
