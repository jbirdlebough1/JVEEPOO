package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupBloodComponent.class */
public class MiniCheckupBloodComponent {
    private float uricAcid;
    private float tCHO;
    private float tAG;
    private float hDL;
    private float lDL;

    public MiniCheckupBloodComponent() {
    }

    public MiniCheckupBloodComponent(float f, float f2, float f3, float f4, float f5) {
        this.uricAcid = f;
        this.tCHO = f2;
        this.tAG = f3;
        this.hDL = f4;
        this.lDL = f5;
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
        return "MiniCheckupBloodComponent{uricAcid=" + this.uricAcid + ", tCHO=" + this.tCHO + ", tAG=" + this.tAG + ", hDL=" + this.hDL + ", lDL=" + this.lDL + '}';
    }
}
