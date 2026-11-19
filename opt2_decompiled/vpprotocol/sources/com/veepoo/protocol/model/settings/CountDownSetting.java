package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/CountDownSetting.class */
public class CountDownSetting {
    private int countDownID;
    private int countDownSecond;
    private boolean isOpenWatchUI;
    private boolean isTestByWatch;

    public CountDownSetting(int i, int i2, boolean z, boolean z2) {
        this.countDownID = i;
        this.countDownSecond = i2;
        this.isOpenWatchUI = z;
        this.isTestByWatch = z2;
    }

    public int getCountDownID() {
        return this.countDownID;
    }

    public void setCountDownID(int i) {
        this.countDownID = i;
    }

    public int getCountDownSecond() {
        return this.countDownSecond;
    }

    public void setCountDownSecond(int i) {
        this.countDownSecond = i;
    }

    public boolean isOpenWatchUI() {
        return this.isOpenWatchUI;
    }

    public void setOpenWatchUI(boolean z) {
        this.isOpenWatchUI = z;
    }

    public boolean isTestByWatch() {
        return this.isTestByWatch;
    }

    public void setTestByWatch(boolean z) {
        this.isTestByWatch = z;
    }

    public String toString() {
        return "CountDownSetting{countDownID=" + this.countDownID + ", countDownSecond=" + this.countDownSecond + ", isOpenWatchUI=" + this.isOpenWatchUI + ", isCountDownByWatch=" + this.isTestByWatch + '}';
    }

    public CountDownSetting(int i, boolean z, boolean z2) {
        this.countDownID = 0;
        this.countDownSecond = i;
        this.isOpenWatchUI = z;
        this.isTestByWatch = z2;
    }
}
