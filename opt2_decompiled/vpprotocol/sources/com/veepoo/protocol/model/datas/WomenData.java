package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EWomenOprateStatus;
import com.veepoo.protocol.model.settings.WomenSetting;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/WomenData.class */
public class WomenData {
    private EWomenOprateStatus oprateStatus;
    private WomenSetting womenSetting;

    public WomenData(EWomenOprateStatus eWomenOprateStatus) {
        this.oprateStatus = eWomenOprateStatus;
    }

    public EWomenOprateStatus getOprateStatus() {
        return this.oprateStatus;
    }

    public void setOprateStatus(EWomenOprateStatus eWomenOprateStatus) {
        this.oprateStatus = eWomenOprateStatus;
    }

    public WomenSetting getWomenSetting() {
        return this.womenSetting;
    }

    public void setWomenSetting(WomenSetting womenSetting) {
        this.womenSetting = womenSetting;
    }

    public String toString() {
        return "WomenData{oprateStatus=" + this.oprateStatus + ",womenSetting=" + this.womenSetting + '}';
    }
}
