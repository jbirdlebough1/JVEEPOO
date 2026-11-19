package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EFindDeviceStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/FindDeviceData.class */
public class FindDeviceData {
    private EFindDeviceStatus status;

    public EFindDeviceStatus getStatus() {
        return this.status;
    }

    public void setStatus(EFindDeviceStatus eFindDeviceStatus) {
        this.status = eFindDeviceStatus;
    }

    public String toString() {
        return "FindDeviceData{status=" + this.status + '}';
    }
}
