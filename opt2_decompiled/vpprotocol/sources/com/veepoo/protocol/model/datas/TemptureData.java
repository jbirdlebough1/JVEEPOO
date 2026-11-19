package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TemptureData.class */
public class TemptureData {
    private int allPackage;
    private int packageNumber;
    private TimeData mTime;
    private boolean isFromHandler;
    private float tempture;
    private float baseTempture;

    public TemptureData(int i, int i2, TimeData timeData, boolean z, float f, float f2) {
        this.allPackage = i;
        this.packageNumber = i2;
        this.mTime = timeData;
        this.isFromHandler = z;
        this.tempture = f;
        this.baseTempture = f2;
    }

    public TemptureData() {
    }

    public TimeData getmTime() {
        return this.mTime;
    }

    public void setmTime(TimeData timeData) {
        this.mTime = timeData;
    }

    public boolean isFromHandler() {
        return this.isFromHandler;
    }

    public void setFromHandler(boolean z) {
        this.isFromHandler = z;
    }

    public float getTempture() {
        return this.tempture;
    }

    public void setTempture(float f) {
        this.tempture = f;
    }

    public float getBaseTempture() {
        return this.baseTempture;
    }

    public void setBaseTempture(float f) {
        this.baseTempture = f;
    }

    public int getAllPackage() {
        return this.allPackage;
    }

    public void setAllPackage(int i) {
        this.allPackage = i;
    }

    public int getPackageNumber() {
        return this.packageNumber;
    }

    public void setPackageNumber(int i) {
        this.packageNumber = i;
    }

    public String toString() {
        return "TemptureData{mTime=" + this.mTime + ", isFromHandler=" + this.isFromHandler + ", tempture=" + this.tempture + ", baseTempture=" + this.baseTempture + '}';
    }
}
