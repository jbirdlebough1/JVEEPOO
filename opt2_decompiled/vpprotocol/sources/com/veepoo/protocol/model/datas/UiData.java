package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UiData.class */
public class UiData {
    private int oprateType;
    private int dataType;
    private int status;

    public UiData(int i, int i2, int i3) {
        this.oprateType = i;
        this.dataType = i2;
        this.status = i3;
    }

    public int getOprateType() {
        return this.oprateType;
    }

    public void setOprateType(int i) {
        this.oprateType = i;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String toString() {
        return "UiData{oprateType=" + this.oprateType + ", dataType=" + this.dataType + ", status=" + this.status + '}';
    }
}
