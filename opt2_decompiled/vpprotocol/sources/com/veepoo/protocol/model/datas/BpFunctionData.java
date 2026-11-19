package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BpFunctionData.class */
public class BpFunctionData {
    private boolean isSupport = false;
    private boolean isOpen = false;

    public boolean isSupport() {
        return this.isSupport;
    }

    public void setSupport(boolean z) {
        this.isSupport = z;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean z) {
        this.isOpen = z;
    }

    public String toString() {
        return "BpFunctionData{isSupport=" + this.isSupport + ",isOpen=" + this.isOpen + '}';
    }
}
