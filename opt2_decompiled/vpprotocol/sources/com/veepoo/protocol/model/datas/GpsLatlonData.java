package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EAllSetStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/GpsLatlonData.class */
public class GpsLatlonData {
    private EAllSetStatus allSetStatus;

    public EAllSetStatus getAllSetStatus() {
        return this.allSetStatus;
    }

    public void setAllSetStatus(EAllSetStatus eAllSetStatus) {
        this.allSetStatus = eAllSetStatus;
    }

    public String toString() {
        return "GpsLatlonData{allSetStatus=" + this.allSetStatus + '}';
    }
}
