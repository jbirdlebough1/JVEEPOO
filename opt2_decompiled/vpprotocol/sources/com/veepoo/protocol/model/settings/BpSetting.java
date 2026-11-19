package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/BpSetting.class */
public class BpSetting {
    private boolean isOpenPrivateModel;
    private int high;
    private int low;
    private boolean isAngioAdjuste = false;

    public BpSetting(boolean z, int i, int i2) {
        this.isOpenPrivateModel = z;
        this.high = i;
        this.low = i2;
    }

    public boolean isangiocheck() {
        return this.isAngioAdjuste;
    }

    public void setAngioAdjuste(boolean z) {
        this.isAngioAdjuste = z;
    }

    public boolean isOpenPrivateModel() {
        return this.isOpenPrivateModel;
    }

    public void setOpenPrivateModel(boolean z) {
        this.isOpenPrivateModel = z;
    }

    public int getHigh() {
        return this.high;
    }

    public void setHigh(int i) {
        this.high = i;
    }

    public int getLow() {
        return this.low;
    }

    public void setLow(int i) {
        this.low = i;
    }

    public String toString() {
        return "BpSetting{isOpenPrivateModel=" + this.isOpenPrivateModel + ", high=" + this.high + ", low=" + this.low + '}';
    }
}
