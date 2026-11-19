package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ECheckWear;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/CheckWearData.class */
public class CheckWearData {
    private ECheckWear checkWearState;

    public ECheckWear getCheckWearState() {
        return this.checkWearState;
    }

    public void setCheckWearState(ECheckWear eCheckWear) {
        this.checkWearState = eCheckWear;
    }

    public String toString() {
        return "CheckWearData{checkWearState=" + this.checkWearState + '}';
    }
}
