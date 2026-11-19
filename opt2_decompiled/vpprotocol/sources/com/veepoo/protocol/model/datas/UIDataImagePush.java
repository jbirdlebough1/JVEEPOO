package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UIDataImagePush.class */
public class UIDataImagePush {
    private int dataReceiveAddress;
    private int dataCanSendLength;
    private long fileLength;
    private int width;
    private int height;
    private int roundedCorners;

    public UIDataImagePush() {
    }

    public UIDataImagePush(int i, int i2, int i3, int i4, int i5) {
        this.dataReceiveAddress = i;
        this.dataCanSendLength = i2;
        this.width = i3;
        this.height = i4;
        this.roundedCorners = i5;
        this.fileLength = 0L;
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

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getRoundedCorners() {
        return this.roundedCorners;
    }

    public void setRoundedCorners(int i) {
        this.roundedCorners = i;
    }

    public boolean isRoundScreen() {
        int i = this.width;
        return i == this.height && this.roundedCorners == i / 2;
    }

    public String toString() {
        return "UIDataImagePush{dataReceiveAddress=" + this.dataReceiveAddress + ", dataCanSendLength=" + this.dataCanSendLength + ", width=" + this.width + ", height=" + this.height + ", roundedCorners=" + this.roundedCorners + ", fileLength=" + this.fileLength + '}';
    }
}
