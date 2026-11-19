package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/EcgSingleData.class */
public class EcgSingleData {
    int vp_a;
    int[] vp_b;
    int vp_c;
    int vp_d;
    int vp_e;
    int vp_f;
    int vp_g;

    public EcgSingleData() {
    }

    public EcgSingleData(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        this.vp_a = i;
        this.vp_b = iArr;
        this.vp_c = i2;
        this.vp_e = i4;
        this.vp_f = i5;
        this.vp_g = i6;
    }

    public int getBreath() {
        return this.vp_d;
    }

    public void setBreath(int i) {
        this.vp_d = i;
    }

    public int getPosition() {
        return this.vp_a;
    }

    public void setPosition(int i) {
        this.vp_a = i;
    }

    public int[] getOrigin() {
        return this.vp_b;
    }

    public void setOrigin(int[] iArr) {
        this.vp_b = iArr;
    }

    public int getHeart() {
        return this.vp_c;
    }

    public void setHeart(int i) {
        this.vp_c = i;
    }

    public int getHrv() {
        return this.vp_e;
    }

    public void setHrv(int i) {
        this.vp_e = i;
    }

    public int getQt() {
        return this.vp_f;
    }

    public void setQt(int i) {
        this.vp_f = i;
    }

    public int getLead() {
        return this.vp_g;
    }

    public void setLead(int i) {
        this.vp_g = i;
    }

    public String toString() {
        return "EcgSingle{position=" + this.vp_a + ", heart=" + this.vp_c + ", breath=" + this.vp_d + ", hrv=" + this.vp_e + ", qt=" + this.vp_f + ", lead=" + this.vp_g + '}';
    }
}
