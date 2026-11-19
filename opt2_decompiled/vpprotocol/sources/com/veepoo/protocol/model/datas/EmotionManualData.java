package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/EmotionManualData.class */
public class EmotionManualData {
    private int timeStamp;
    private int version;
    private int emotion;

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

    public int getEmotion() {
        return this.emotion;
    }

    public void setEmotion(int i) {
        this.emotion = i;
    }

    public String toString() {
        return "EmotionManualData{timeStamp=" + this.timeStamp + ", version=" + this.version + ", emotion=" + this.emotion + '}';
    }
}
