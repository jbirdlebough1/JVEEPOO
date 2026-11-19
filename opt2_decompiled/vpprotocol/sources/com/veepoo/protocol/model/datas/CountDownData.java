package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ECountDownStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/CountDownData.class */
public class CountDownData {
    private boolean isOprateSuccess;
    private int countDownID;
    private int countDownSecondWatch;
    private int countDownSecondApp;
    private boolean isOpenWatchUI;
    private boolean isCountDownByWatch;
    private ECountDownStatus status;

    public CountDownData() {
    }

    public CountDownData(boolean z, int i, int i2, int i3, boolean z2, boolean z3) {
        this.isOprateSuccess = z;
        this.countDownID = i;
        this.countDownSecondWatch = i2;
        this.countDownSecondApp = i3;
        this.isOpenWatchUI = z2;
        this.isCountDownByWatch = z3;
    }

    public boolean isOprateSuccess() {
        return this.isOprateSuccess;
    }

    public void setOprateSuccess(boolean z) {
        this.isOprateSuccess = z;
    }

    public ECountDownStatus getStatus() {
        return this.status;
    }

    public void setStatus(ECountDownStatus eCountDownStatus) {
        this.status = eCountDownStatus;
    }

    public int getCountDownID() {
        return this.countDownID;
    }

    public void setCountDownID(int i) {
        this.countDownID = i;
    }

    public int getCountDownSecondWatch() {
        return this.countDownSecondWatch;
    }

    public void setCountDownSecondWatch(int i) {
        this.countDownSecondWatch = i;
    }

    public int getCountDownSecondApp() {
        return this.countDownSecondApp;
    }

    public void setCountDownSecondApp(int i) {
        this.countDownSecondApp = i;
    }

    public boolean isOpenWatchUI() {
        return this.isOpenWatchUI;
    }

    public void setOpenWatchUI(boolean z) {
        this.isOpenWatchUI = z;
    }

    public boolean isCountDownByWatch() {
        return this.isCountDownByWatch;
    }

    public void setCountDownByWatch(boolean z) {
        this.isCountDownByWatch = z;
    }

    public String toString() {
        return "CountDownData{isOprateSuccess=" + this.isOprateSuccess + ", countDownID=" + this.countDownID + ", countDownSecondWatch=" + this.countDownSecondWatch + ", countDownSecondApp=" + this.countDownSecondApp + ", isOpenWatchUI=" + this.isOpenWatchUI + ", isCountDownByWatch=" + this.isCountDownByWatch + ", status=" + this.status + '}';
    }
}
