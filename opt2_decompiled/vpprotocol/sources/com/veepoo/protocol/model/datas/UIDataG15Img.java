package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UIDataG15Img.class */
public class UIDataG15Img {
    private int dataReceiveAddress;
    private int dataCanSendLength;
    private long fileLength;
    private int crc;
    private int packageIndex;
    private long timeStamp;

    public UIDataG15Img() {
    }

    public UIDataG15Img(int i, int i2, int i3, int i4, int i5, long j) {
        this.dataReceiveAddress = i;
        this.dataCanSendLength = i2;
        this.fileLength = i3;
        this.crc = i4;
        this.packageIndex = i5;
        this.timeStamp = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public int getDataReceiveAddress() {
        return this.dataReceiveAddress;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public void setFileLength(long j) {
        this.fileLength = j;
    }

    public void setDataReceiveAddress(int i) {
        this.dataReceiveAddress = i;
    }

    public int getDataCanSendLength() {
        return this.dataCanSendLength;
    }

    public void setDataCanSendLength(int i) {
        this.dataCanSendLength = i;
    }

    public int getCrc() {
        return this.crc;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public int getPackageIndex() {
        return this.packageIndex;
    }

    public void setPackageIndex(int i) {
        this.packageIndex = i;
    }

    public String toString() {
        return "UIDataG15Img{dataReceiveAddress=" + this.dataReceiveAddress + ", dataCanSendLength=" + this.dataCanSendLength + ", fileLength=" + this.fileLength + ", crc=" + this.crc + ", packageIndex=" + this.packageIndex + '}';
    }
}
