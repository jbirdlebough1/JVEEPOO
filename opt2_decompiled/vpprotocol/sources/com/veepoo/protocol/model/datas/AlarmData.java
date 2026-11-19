package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EAalarmStatus;
import com.veepoo.protocol.model.settings.AlarmSetting;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AlarmData.class */
public class AlarmData {
    private EAalarmStatus status;
    private List<AlarmSetting> alarmSettingList;

    public EAalarmStatus getStatus() {
        return this.status;
    }

    public void setStatus(EAalarmStatus eAalarmStatus) {
        this.status = eAalarmStatus;
    }

    public List<AlarmSetting> getAlarmSettingList() {
        return this.alarmSettingList;
    }

    public void setAlarmSettingList(List<AlarmSetting> list) {
        this.alarmSettingList = list;
    }

    public String toString() {
        return "AlarmData{status=" + this.status + ", alarmSettingList=" + this.alarmSettingList + '}';
    }
}
