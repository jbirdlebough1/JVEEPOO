package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TemptureDetectData.class */
public class TemptureDetectData {
    private int oprate;
    private int deviceState;
    private int progress;
    private float tempture;
    private float temptureBase;

    public TemptureDetectData(int i, int i2, int i3, float f, float f2) {
        this.oprate = i;
        this.deviceState = i2;
        this.progress = i3;
        this.tempture = f;
        this.temptureBase = f2;
    }

    public int getOprate() {
        return this.oprate;
    }

    public void setOprate(int i) {
        this.oprate = i;
    }

    public int getDeviceState() {
        return this.deviceState;
    }

    public void setDeviceState(int i) {
        this.deviceState = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public float getTempture() {
        return this.tempture;
    }

    public void setTempture(float f) {
        this.tempture = f;
    }

    public float getTemptureBase() {
        return this.temptureBase;
    }

    public void setTemptureBase(float f) {
        this.temptureBase = f;
    }

    public String toString() {
        return "TemptureDetectBean{progress=" + this.progress + ", tempture=" + this.tempture + ", deviceState=" + this.deviceState + ", oprate=" + this.oprate + ", temptureBase=" + this.temptureBase + '}';
    }
}
