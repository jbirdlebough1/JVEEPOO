package com.veepoo.protocol.multi_lead.data;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/EcgRRDataInfo.class */
public class EcgRRDataInfo {
    private int offsetTime;
    private int[] rrValues;

    public int getOffsetTime() {
        return this.offsetTime;
    }

    public void setOffsetTime(int i) {
        this.offsetTime = i;
    }

    public int[] getRrValues() {
        return this.rrValues;
    }

    public void setRrValues(int[] iArr) {
        this.rrValues = iArr;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("EcgRRDataInfo{offsetTime=").append(this.offsetTime).append(", rrValues=");
        int[] iArr = this.rrValues;
        return sbAppend.append(iArr == null ? "null" : Integer.valueOf(iArr.length)).append('}').toString();
    }
}
