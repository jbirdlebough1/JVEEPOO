package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/HeartWaringSetting.class */
public class HeartWaringSetting {
    private int heartHigh;
    private int heartLow;
    private boolean isOpen;

    public HeartWaringSetting(int i, int i2, boolean z) {
        this.heartHigh = i;
        this.heartLow = i2;
        this.isOpen = z;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public int getHeartHigh() {
        return this.heartHigh;
    }

    public void setHeartHigh(int i) {
        this.heartHigh = i;
    }

    public int getHeartLow() {
        return this.heartLow;
    }

    public void setHeartLow(int i) {
        this.heartLow = i;
    }

    public String toString() {
        return "HeartWaringSetting{isOpen=" + this.isOpen + ", heartHigh=" + this.heartHigh + ", heartLow=" + this.heartLow + '}';
    }
}
