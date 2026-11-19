package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.EBPStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/BpSettingData.class */
public class BpSettingData {
    private EBPStatus status;
    private EBPDetectModel model;
    private int highPressure;
    private int lowPressure;
    private int angioAdjusterProgress;
    private boolean isAngioAdjuster;

    public BpSettingData(EBPStatus eBPStatus, EBPDetectModel eBPDetectModel, int i, int i2) {
        this.status = eBPStatus;
        this.model = eBPDetectModel;
        this.highPressure = i;
        this.lowPressure = i2;
    }

    public boolean isAngioAdjuster() {
        return this.isAngioAdjuster;
    }

    public void setAngioAdjuster(boolean z) {
        this.isAngioAdjuster = z;
    }

    public int getAngioAdjusterProgress() {
        return this.angioAdjusterProgress;
    }

    public void setAngioAdjusterProgress(int i) {
        this.angioAdjusterProgress = i;
    }

    public EBPStatus getStatus() {
        return this.status;
    }

    public void setStatus(EBPStatus eBPStatus) {
        this.status = eBPStatus;
    }

    public EBPDetectModel getModel() {
        return this.model;
    }

    public void setModel(EBPDetectModel eBPDetectModel) {
        this.model = eBPDetectModel;
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

    public String toString() {
        return "BpSettingData{status=" + this.status + ", model=" + this.model + ", highPressure=" + this.highPressure + ", lowPressure=" + this.lowPressure + ", angioAdjusterProgress=" + this.angioAdjusterProgress + ", isAngioAdjuster=" + this.isAngioAdjuster + '}';
    }
}
