package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EBPDetectStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BpData.class */
public class BpData {
    private EBPDetectStatus status;
    private int progress;
    private int highPressure;
    private int lowPressure;
    private boolean isHaveProgress;

    public EBPDetectStatus getStatus() {
        return this.status;
    }

    public void setStatus(EBPDetectStatus eBPDetectStatus) {
        this.status = eBPDetectStatus;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getHighPressure() {
        return this.highPressure;
    }

    public void setHighPressure(int i) {
        this.highPressure = i;
    }

    public int getLowPressure() {
        return this.lowPressure;
    }

    public void setLowPressure(int i) {
        this.lowPressure = i;
    }

    public boolean isHaveProgress() {
        return this.isHaveProgress;
    }

    public void setHaveProgress(boolean z) {
        this.isHaveProgress = z;
    }

    public String toString() {
        return "BpData{status=" + this.status + ", progress=" + this.progress + ", highPressure=" + this.highPressure + ", lowPressure=" + this.lowPressure + '}';
    }
}
