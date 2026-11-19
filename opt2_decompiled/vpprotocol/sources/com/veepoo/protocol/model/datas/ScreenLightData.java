package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EScreenLight;
import com.veepoo.protocol.model.settings.ScreenSetting;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ScreenLightData.class */
public class ScreenLightData {
    private EScreenLight status;
    private ScreenSetting screenSetting;

    public ScreenLightData(EScreenLight eScreenLight, ScreenSetting screenSetting) {
        this.status = eScreenLight;
        this.screenSetting = screenSetting;
    }

    public EScreenLight getStatus() {
        return this.status;
    }

    public void setStatus(EScreenLight eScreenLight) {
        this.status = eScreenLight;
    }

    public ScreenSetting getScreenSetting() {
        return this.screenSetting;
    }

    public void setScreenSetting(ScreenSetting screenSetting) {
        this.screenSetting = screenSetting;
    }

    public String toString() {
        return "ScreenLightData{status=" + this.status + ", screenSetting=" + this.screenSetting + '}';
    }
}
