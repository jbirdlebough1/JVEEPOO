package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EYM23ProReportType;
import com.veepoo.protocol.model.enums.EYM23ProWearState;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/YM23ProWearReportData.class */
public class YM23ProWearReportData {
    private EYM23ProReportType reportType = EYM23ProReportType.UNKONW;
    private EYM23ProWearState wearState = EYM23ProWearState.UNKONW;
    private int heartRate = 0;

    public EYM23ProReportType getReportType() {
        return this.reportType;
    }

    public void setReportType(EYM23ProReportType eYM23ProReportType) {
        this.reportType = eYM23ProReportType;
    }

    public EYM23ProWearState getWearState() {
        return this.wearState;
    }

    public void setWearState(EYM23ProWearState eYM23ProWearState) {
        this.wearState = eYM23ProWearState;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public String toString() {
        return "YM23ProWearReportData{reportType=" + this.reportType + ", wearState=" + this.wearState + ", heartRate=" + this.heartRate + '}';
    }
}
