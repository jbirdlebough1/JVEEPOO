package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EAllSetStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/KaaBaData.class */
public class KaaBaData {
    private EAllSetStatus eAllSetStatus;

    public EAllSetStatus geteAllSetStatus() {
        return this.eAllSetStatus;
    }

    public void seteAllSetStatus(EAllSetStatus eAllSetStatus) {
        this.eAllSetStatus = eAllSetStatus;
    }

    public String toString() {
        return "KaaBaData{eAllSetStatus=" + this.eAllSetStatus + '}';
    }
}
