package com.veepoo.protocol.model;

import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/G15QRCode.class */
public class G15QRCode {
    private int qrType;
    private String qrContent;
    private byte[] qrContentBytes;
    private int qrContentByteLength;
    private String name;
    private byte[] qrNameBytes;
    private int qrNameByteLength;
    private String group;
    private byte[] qrGroupBytes;
    private int qrGroupByteLength;
    private String number;
    private byte[] qrNumberBytes;
    private int qrNumberByteLength;

    public G15QRCode(int i, String str, String str2, String str3, String str4) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("二维码的类型只能为 0x00 或 0x01");
        }
        this.qrType = i;
        byte[] bytes = str.getBytes();
        this.qrContentBytes = bytes;
        int length = bytes.length;
        this.qrContentByteLength = length;
        if (length > 50 || length <= 0) {
            throw new IllegalArgumentException("Error : 二维码文本信息byte数组长度为(0,50] -> 文本长度为：" + this.qrContentByteLength);
        }
        this.qrContent = str;
        byte[] bytes2 = str2.getBytes();
        this.qrNameBytes = bytes2;
        int length2 = bytes2.length;
        this.qrNameByteLength = length2;
        if (length2 > 30 || length2 <= 0) {
            throw new IllegalArgumentException("Error : 二维码姓名内容byte数组长度为(0,30] -> 文本长度为：" + this.qrNameByteLength);
        }
        this.name = str2;
        byte[] bytes3 = str3.getBytes();
        this.qrGroupBytes = bytes3;
        int length3 = bytes3.length;
        this.qrGroupByteLength = length3;
        if (length3 > 30 || length3 <= 0) {
            throw new IllegalArgumentException("Error : 二维码组别的内容byte数组长度为(0,30] -> 文本长度为：" + this.qrGroupByteLength);
        }
        this.group = str3;
        byte[] bytes4 = str4.getBytes();
        this.qrNumberBytes = bytes4;
        int length4 = bytes4.length;
        this.qrNumberByteLength = length4;
        if (length4 > 30 || length4 <= 0) {
            throw new IllegalArgumentException("Error : 二维码编号的内容byte数组长度为(0,30] -> 文本长度为：" + this.qrNumberByteLength);
        }
        this.number = str4;
    }

    public int getQRCodeCmdByteLength() {
        return this.qrContentByteLength + 11 + this.qrNameByteLength + this.qrGroupByteLength + this.qrNumberByteLength;
    }

    public int getQrType() {
        return this.qrType;
    }

    public void setQrType(int i) {
        this.qrType = i;
    }

    public String getQrContent() {
        return this.qrContent;
    }

    public void setQrContent(String str) {
        this.qrContent = str;
    }

    public int getQrContentByteLength() {
        return this.qrContentByteLength;
    }

    public void setQrContentByteLength(int i) {
        this.qrContentByteLength = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getQrNameByteLength() {
        return this.qrNameByteLength;
    }

    public void setQrNameByteLength(int i) {
        this.qrNameByteLength = i;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String str) {
        this.group = str;
    }

    public int getQrGroupByteLength() {
        return this.qrGroupByteLength;
    }

    public void setQrGroupByteLength(int i) {
        this.qrGroupByteLength = i;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public int getQrNumberByteLength() {
        return this.qrNumberByteLength;
    }

    public void setQrNumberByteLength(int i) {
        this.qrNumberByteLength = i;
    }

    public byte[] getQrContentBytes() {
        return this.qrContentBytes;
    }

    public void setQrContentBytes(byte[] bArr) {
        this.qrContentBytes = bArr;
    }

    public byte[] getQrNameBytes() {
        return this.qrNameBytes;
    }

    public void setQrNameBytes(byte[] bArr) {
        this.qrNameBytes = bArr;
    }

    public byte[] getQrGroupBytes() {
        return this.qrGroupBytes;
    }

    public void setQrGroupBytes(byte[] bArr) {
        this.qrGroupBytes = bArr;
    }

    public byte[] getQrNumberBytes() {
        return this.qrNumberBytes;
    }

    public void setQrNumberBytes(byte[] bArr) {
        this.qrNumberBytes = bArr;
    }

    public String toString() {
        return "G15QRCode{qrType=" + this.qrType + ", qrContent='" + this.qrContent + "', qrContentBytes=" + Arrays.toString(this.qrContentBytes) + ", qrContentByteLength=" + this.qrContentByteLength + ", name='" + this.name + "', qrNameBytes=" + Arrays.toString(this.qrNameBytes) + ", qrNameByteLength=" + this.qrNameByteLength + ", group='" + this.group + "', qrGroupBytes=" + Arrays.toString(this.qrGroupBytes) + ", qrGroupByteLength=" + this.qrGroupByteLength + ", number='" + this.number + "', qrNumberBytes=" + Arrays.toString(this.qrNumberBytes) + ", qrNumberByteLength=" + this.qrNumberByteLength + '}';
    }
}
