package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ReadSleepSetting.class */
public class ReadSleepSetting {
    private int dayInt;
    private int watchDataDay;
    private boolean onlyReadOneDay;

    public ReadSleepSetting(int i, boolean z, int i2) {
        this.dayInt = i;
        this.watchDataDay = i2;
        this.onlyReadOneDay = z;
    }

    public int getWatchDataDay() {
        return this.watchDataDay;
    }

    public int getDayInt() {
        return this.dayInt;
    }

    public void setDayInt(int i) {
        this.dayInt = i;
    }

    public boolean isReadOneDay() {
        return this.onlyReadOneDay;
    }

    public void setOnlyReadOneDay(boolean z) {
        this.onlyReadOneDay = z;
    }

    public String toString() {
        return "ReadOriginSetting{, onlyReadOneDay=" + this.onlyReadOneDay + '}';
    }
}
