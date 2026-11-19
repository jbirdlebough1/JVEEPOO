package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ECheckWear;
import com.veepoo.protocol.model.enums.ESportModelStateStauts;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportModelStateData.class */
public class SportModelStateData {
    private ECheckWear oprateStauts;
    private ESportModelStateStauts deviceStauts;
    private int sportModeType;

    public ESportModelStateStauts getDeviceStauts() {
        return this.deviceStauts;
    }

    public void setDeviceStauts(ESportModelStateStauts eSportModelStateStauts) {
        this.deviceStauts = eSportModelStateStauts;
    }

    public ECheckWear getOprateStauts() {
        return this.oprateStauts;
    }

    public void setOprateStauts(ECheckWear eCheckWear) {
        this.oprateStauts = eCheckWear;
    }

    public int getSportModeType() {
        return this.sportModeType;
    }

    public void setSportModeType(int i) {
        this.sportModeType = i;
    }

    public String toString() {
        return "SportModelStateData{oprateStauts=" + this.oprateStauts + ",sportModeType=" + this.sportModeType + ", deviceStauts=" + this.deviceStauts + '}';
    }
}
