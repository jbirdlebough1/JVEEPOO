package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EHeartStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HeartData.class */
public class HeartData {
    private EHeartStatus heartStatus;
    private int data;

    public EHeartStatus getHeartStatus() {
        return this.heartStatus;
    }

    public void setHeartStatus(EHeartStatus eHeartStatus) {
        this.heartStatus = eHeartStatus;
    }

    public int getData() {
        return this.data;
    }

    public void setData(int i) {
        this.data = i;
    }

    public String toString() {
        return "HeartData{heartStatus=" + this.heartStatus + ", data=" + this.data + '}';
    }
}
