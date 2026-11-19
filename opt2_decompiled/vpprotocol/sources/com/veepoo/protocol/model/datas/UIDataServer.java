package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UIDataServer.class */
public class UIDataServer {
    private int useType;
    private int oprateType;
    private int oprateState;
    private int dataReceiveAddress;
    private int dataCanSendLength;
    private int binDataType;
    private int deviceAialShape;
    private int imgCrcId;
    private long dataFileLength;
    private int packageIndex;

    public UIDataServer(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.useType = i;
        this.oprateType = i2;
        this.oprateState = i3;
        this.dataReceiveAddress = i4;
        this.dataCanSendLength = i5;
        this.binDataType = i6;
        this.deviceAialShape = i7;
        this.imgCrcId = i8;
        this.packageIndex = i9;
    }

    public long getDataFileLength() {
        return this.dataFileLength;
    }

    public void setDataFileLength(long j) {
        this.dataFileLength = j;
    }

    public int getDataReceiveAddress() {
        return this.dataReceiveAddress;
    }

    public int getDataCanSendLength() {
        return this.dataCanSendLength;
    }

    public int getBinDataType() {
        return this.binDataType;
    }

    public int getDeviceAialShape() {
        return this.deviceAialShape;
    }

    public int getImgCrcId() {
        return this.imgCrcId;
    }

    public int getUseType() {
        return this.useType;
    }

    public void setUseType(int i) {
        this.useType = i;
    }

    public int getOprateType() {
        return this.oprateType;
    }

    public void setOprateType(int i) {
        this.oprateType = i;
    }

    public int getOprateState() {
        return this.oprateState;
    }

    public void setOprateState(int i) {
        this.oprateState = i;
    }

    public void setDataReceiveAddress(int i) {
        this.dataReceiveAddress = i;
    }

    public void setDataCanSendLength(int i) {
        this.dataCanSendLength = i;
    }

    public void setBinDataType(int i) {
        this.binDataType = i;
    }

    public void setDeviceAialShape(int i) {
        this.deviceAialShape = i;
    }

    public void setImgCrcId(int i) {
        this.imgCrcId = i;
    }

    public int getPackageIndex() {
        return this.packageIndex;
    }

    public void setPackageIndex(int i) {
        this.packageIndex = i;
    }

    public String toString() {
        return "UIDataServer{imgCrcId=" + this.imgCrcId + ", packageIndex=" + this.packageIndex + ", useType=" + this.useType + ", oprateType=" + this.oprateType + ", oprateState=" + this.oprateState + ", dataReceiveAddress=" + this.dataReceiveAddress + ", dataCanSendLength=" + this.dataCanSendLength + ", binDataType=" + this.binDataType + ", deviceAialShape=" + this.deviceAialShape + ", dataFileLength=" + this.dataFileLength + '}';
    }
}
