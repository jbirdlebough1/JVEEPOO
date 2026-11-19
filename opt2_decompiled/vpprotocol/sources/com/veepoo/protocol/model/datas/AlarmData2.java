package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EMultiAlarmOprate;
import com.veepoo.protocol.model.settings.Alarm2Setting;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AlarmData2.class */
public class AlarmData2 {
    private EMultiAlarmOprate oprate;
    private List<Alarm2Setting> alarm2SettingList;

    public AlarmData2(EMultiAlarmOprate eMultiAlarmOprate, List<Alarm2Setting> list) {
        this.oprate = eMultiAlarmOprate;
        this.alarm2SettingList = list;
    }

    public EMultiAlarmOprate getOprate() {
        return this.oprate;
    }

    public void setOprate(EMultiAlarmOprate eMultiAlarmOprate) {
        this.oprate = eMultiAlarmOprate;
    }

    public List<Alarm2Setting> getAlarm2SettingList() {
        return this.alarm2SettingList;
    }

    public void setAlarm2SettingList(List<Alarm2Setting> list) {
        this.alarm2SettingList = list;
    }

    public String toString() {
        return "AlarmData2{oprate=" + this.oprate + ", alarm2SettingList=" + this.alarm2SettingList + '}';
    }
}
