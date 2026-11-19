package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ReportGpsData.class */
public class ReportGpsData {
    boolean vp_a;

    public ReportGpsData(boolean z) {
        this.vp_a = z;
    }

    public boolean isReport() {
        return this.vp_a;
    }

    public void setReport(boolean z) {
        this.vp_a = z;
    }

    public String toString() {
        return "ReportGpsData{isReport=" + this.vp_a + '}';
    }
}
