package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ReadOriginSetting.class */
public class ReadOriginSetting {
    private int day;
    private int watchday;
    private int position;
    private boolean onlyReadOneDay;

    public ReadOriginSetting(int i, int i2, boolean z, int i3) {
        this.day = i;
        this.position = i2;
        this.onlyReadOneDay = z;
        this.watchday = i3;
    }

    public int getWatchday() {
        return this.watchday;
    }

    public void setWatchday(int i) {
        this.watchday = i;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i) {
        this.day = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public boolean isReadOneDay() {
        return this.onlyReadOneDay;
    }

    public void setOnlyReadOneDay(boolean z) {
        this.onlyReadOneDay = z;
    }

    public String toString() {
        return "ReadOriginSetting{day=" + this.day + ", position=" + this.position + ", onlyReadOneDay=" + this.onlyReadOneDay + '}';
    }
}
