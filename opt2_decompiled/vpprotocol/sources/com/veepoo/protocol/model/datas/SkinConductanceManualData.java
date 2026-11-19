package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SkinConductanceManualData.class */
public class SkinConductanceManualData {
    private int timeStamp;
    private int version;
    private int emotionLevel;
    private int skinMoisture;
    private int depressionRisk;
    private int snsActivation;
    private int cortisolValue;

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

    public int getEmotionLevel() {
        return this.emotionLevel;
    }

    public void setEmotionLevel(int i) {
        this.emotionLevel = i;
    }

    public int getSkinMoisture() {
        return this.skinMoisture;
    }

    public void setSkinMoisture(int i) {
        this.skinMoisture = i;
    }

    public int getDepressionRisk() {
        return this.depressionRisk;
    }

    public void setDepressionRisk(int i) {
        this.depressionRisk = i;
    }

    public int getSnsActivation() {
        return this.snsActivation;
    }

    public void setSnsActivation(int i) {
        this.snsActivation = i;
    }

    public int getCortisolValue() {
        return this.cortisolValue;
    }

    public void setCortisolValue(int i) {
        this.cortisolValue = i;
    }

    public String toString() {
        return "SkinConductanceManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", emotionLevel=" + this.emotionLevel + ", skinMoisture=" + this.skinMoisture + ", depressionRisk=" + this.depressionRisk + ", snsActivation=" + this.snsActivation + ", cortisolValue=" + this.cortisolValue + '}';
    }
}
