package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.util.ColorUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UIDataCustom.class */
public class UIDataCustom {
    private int dataReceiveAddress;
    private int dataCanSendLength;
    private long fileLength;
    private EWatchUIType customUIType;
    private boolean isDefalutUI;
    private EWatchUIElementPosition timePosition;
    private EWatchUIElementType upTimeType;
    private EWatchUIElementType downTimeType;
    private int color888;
    private int crc;
    private int packageIndex;

    public UIDataCustom() {
    }

    public UIDataCustom(int i, int i2, EWatchUIType eWatchUIType, boolean z, EWatchUIElementPosition eWatchUIElementPosition, EWatchUIElementType eWatchUIElementType, EWatchUIElementType eWatchUIElementType2, int i3, int i4, int i5, int i6) {
        this.dataReceiveAddress = i;
        this.dataCanSendLength = i2;
        this.customUIType = eWatchUIType;
        this.isDefalutUI = z;
        this.timePosition = eWatchUIElementPosition;
        this.upTimeType = eWatchUIElementType;
        this.downTimeType = eWatchUIElementType2;
        this.color888 = i3;
        this.crc = i4;
        this.fileLength = i5;
        this.packageIndex = i6;
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

    public EWatchUIType getCustomUIType() {
        return this.customUIType;
    }

    public void setCustomUIType(EWatchUIType eWatchUIType) {
        this.customUIType = eWatchUIType;
    }

    public boolean getIsDefalutUI() {
        return this.isDefalutUI;
    }

    public void setIsDefalutUI(boolean z) {
        this.isDefalutUI = z;
    }

    public EWatchUIElementPosition getTimePosition() {
        return this.timePosition;
    }

    public void setTimePosition(EWatchUIElementPosition eWatchUIElementPosition) {
        this.timePosition = eWatchUIElementPosition;
    }

    public EWatchUIElementType getUpTimeType() {
        return this.upTimeType;
    }

    public void setUpTimeType(EWatchUIElementType eWatchUIElementType) {
        this.upTimeType = eWatchUIElementType;
    }

    public EWatchUIElementType getDownTimeType() {
        return this.downTimeType;
    }

    public void setDownTimeType(EWatchUIElementType eWatchUIElementType) {
        this.downTimeType = eWatchUIElementType;
    }

    public int getColor888() {
        return this.color888;
    }

    public void setColor888(int i) {
        this.color888 = i;
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
        return "UIDataCustom{dataReceiveAddress=" + this.dataReceiveAddress + ", dataCanSendLength=" + this.dataCanSendLength + ", customUIType=" + this.customUIType + ", isDefalutUI=" + this.isDefalutUI + ", timePosition=" + this.timePosition + ", upTimeType=" + this.upTimeType + ", downTimeType=" + this.downTimeType + ", color888=" + ColorUtil.intColorToHexStr(this.color888) + ", crc=" + this.crc + ", fileLength=" + this.fileLength + ", packageIndex=" + this.packageIndex + '}';
    }
}
