package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.ESex;
import com.veepoo.protocol.model.enums.EWomenStatus;
import com.veepoo.protocol.util.vp_j;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/WomenSetting.class */
public class WomenSetting {
    private int menseLength;
    private int menesInterval;
    private TimeData menesLasterday;
    private TimeData babyBirthday;
    private TimeData confinementDay;
    private EWomenStatus womenStatus;
    private ESex babySex;

    public WomenSetting() {
    }

    public WomenSetting(EWomenStatus eWomenStatus, int i, int i2, TimeData timeData) {
        if (!vp_j.vp_b(timeData)) {
            throw new IllegalArgumentException("日期不合法");
        }
        this.womenStatus = eWomenStatus;
        this.menseLength = i;
        this.menesInterval = i2;
        this.menesLasterday = timeData;
    }

    public EWomenStatus getWomenStatus() {
        return this.womenStatus;
    }

    public int getMenseLength() {
        return this.menseLength;
    }

    public int getMenesInterval() {
        return this.menesInterval;
    }

    public TimeData getMenesLasterday() {
        return this.menesLasterday;
    }

    public ESex getBabySex() {
        return this.babySex;
    }

    public TimeData getBabyBirthday() {
        return this.babyBirthday;
    }

    public TimeData getConfinementDay() {
        return this.confinementDay;
    }

    public void setMenseLength(int i) {
        this.menseLength = i;
    }

    public void setMenesInterval(int i) {
        this.menesInterval = i;
    }

    public void setMenesLasterday(TimeData timeData) {
        this.menesLasterday = timeData;
    }

    public void setBabyBirthday(TimeData timeData) {
        this.babyBirthday = timeData;
    }

    public void setConfinementDay(TimeData timeData) {
        this.confinementDay = timeData;
    }

    public void setWomenStatus(EWomenStatus eWomenStatus) {
        this.womenStatus = eWomenStatus;
    }

    public void setBabySex(ESex eSex) {
        this.babySex = eSex;
    }

    public String toString() {
        return "WomenSetting{womenStatus=" + this.womenStatus + ", menseLength=" + this.menseLength + ", menesInterval=" + this.menesInterval + ", menesLasterday=" + this.menesLasterday + ", babySex=" + this.babySex + ", babyBirthday=" + this.babyBirthday + ", confinementDay=" + this.confinementDay + '}';
    }

    public WomenSetting(EWomenStatus eWomenStatus, int i, int i2, TimeData timeData, ESex eSex, TimeData timeData2) {
        this(eWomenStatus, i, i2, timeData);
        if (!vp_j.vp_b(timeData2)) {
            throw new IllegalArgumentException("baby日期不合法");
        }
        this.babySex = eSex;
        this.babyBirthday = timeData2;
    }

    public WomenSetting(EWomenStatus eWomenStatus, TimeData timeData, TimeData timeData2) {
        if (!vp_j.vp_b(timeData)) {
            throw new IllegalArgumentException("最近一次月份经日期不合法");
        }
        if (!vp_j.vp_b(timeData2)) {
            throw new IllegalArgumentException("预产期日期不合法");
        }
        this.womenStatus = eWomenStatus;
        this.menesLasterday = timeData;
        this.confinementDay = timeData2;
    }
}
