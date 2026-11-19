package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EHeartWaringStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/HeartWaringData.class */
public class HeartWaringData {
    private EHeartWaringStatus status;
    private boolean isOpen;
    private int heartHigh;
    private int heartLow;

    public EHeartWaringStatus getStatus() {
        return this.status;
    }

    public void setStatus(EHeartWaringStatus eHeartWaringStatus) {
        this.status = eHeartWaringStatus;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public int getHeartHigh() {
        return this.heartHigh;
    }

    public void setHeartHigh(int i) {
        this.heartHigh = i;
    }

    public int getHeartLow() {
        return this.heartLow;
    }

    public void setHeartLow(int i) {
        this.heartLow = i;
    }

    public String toString() {
        return "HeartWaringData{status=" + this.status + ", isOpen=" + this.isOpen + ", heartHigh=" + this.heartHigh + ", heartLow=" + this.heartLow + '}';
    }
}
