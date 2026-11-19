package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.EFunctionStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/AutoDetectStateSetting.class */
public class AutoDetectStateSetting {
    private EFunctionStatus spo2h24Hour = EFunctionStatus.UNSUPPORT;

    public EFunctionStatus getSpo2h24Hour() {
        return this.spo2h24Hour;
    }

    public void setSpo2h24Hour(EFunctionStatus eFunctionStatus) {
        this.spo2h24Hour = eFunctionStatus;
    }

    public String toString() {
        return "AutoDetectStateData{spo2h24Hour=" + this.spo2h24Hour + '}';
    }
}
