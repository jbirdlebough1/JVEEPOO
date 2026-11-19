package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BloodComponentManualData.class */
public class BloodComponentManualData {
    private int timeStamp;
    private int version;
    private float uricAcid = 0.0f;
    private float tCHO = 0.0f;
    private float tAG = 0.0f;
    private float hDL = 0.0f;
    private float lDL = 0.0f;

    public int getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(int i) {
        this.timeStamp = i;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public float getUricAcid() {
        return this.uricAcid;
    }

    public void setUricAcid(float f) {
        this.uricAcid = f;
    }

    public float gettCHO() {
        return this.tCHO;
    }

    public void settCHO(float f) {
        this.tCHO = f;
    }

    public float gettAG() {
        return this.tAG;
    }

    public void settAG(float f) {
        this.tAG = f;
    }

    public float gethDL() {
        return this.hDL;
    }

    public void sethDL(float f) {
        this.hDL = f;
    }

    public float getlDL() {
        return this.lDL;
    }

    public void setlDL(float f) {
        this.lDL = f;
    }

    public String toString() {
        return "BloodComponentManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", uricAcid=" + this.uricAcid + ", tCHO=" + this.tCHO + ", tAG=" + this.tAG + ", hDL=" + this.hDL + ", lDL=" + this.lDL + '}';
    }
}
