package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupSkinElectricity.class */
public class MiniCheckupSkinElectricity {
    private int emotion;
    private int skinMoistureContent;
    private int depressionRisk;
    private int sympatheticActivity;
    private int cortisolConcentration;

    public MiniCheckupSkinElectricity() {
    }

    public MiniCheckupSkinElectricity(int i, int i2, int i3, int i4, int i5) {
        this.emotion = i;
        this.skinMoistureContent = i2;
        this.depressionRisk = i3;
        this.sympatheticActivity = i4;
        this.cortisolConcentration = i5;
    }

    public int getEmotion() {
        return this.emotion;
    }

    public void setEmotion(int i) {
        this.emotion = i;
    }

    public int getSkinMoistureContent() {
        return this.skinMoistureContent;
    }

    public void setSkinMoistureContent(int i) {
        this.skinMoistureContent = i;
    }

    public int getDepressionRisk() {
        return this.depressionRisk;
    }

    public void setDepressionRisk(int i) {
        this.depressionRisk = i;
    }

    public int getSympatheticActivity() {
        return this.sympatheticActivity;
    }

    public void setSympatheticActivity(int i) {
        this.sympatheticActivity = i;
    }

    public int getCortisolConcentration() {
        return this.cortisolConcentration;
    }

    public void setCortisolConcentration(int i) {
        this.cortisolConcentration = i;
    }

    public String toString() {
        return "MiniCheckupSkinElectricity{emotion=" + this.emotion + ", skinMoistureContent=" + this.skinMoistureContent + ", depressionRisk=" + this.depressionRisk + ", sympatheticActivity=" + this.sympatheticActivity + ", cortisolConcentration=" + this.cortisolConcentration + '}';
    }
}
