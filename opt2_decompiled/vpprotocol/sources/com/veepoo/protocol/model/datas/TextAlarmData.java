package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EMultiAlarmOprate;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TextAlarmData.class */
public class TextAlarmData {
    private EMultiAlarmOprate oprate;
    private List<TextAlarm2Setting> textAlarm2SettingList;

    public TextAlarmData(EMultiAlarmOprate eMultiAlarmOprate, List<TextAlarm2Setting> list) {
        this.oprate = eMultiAlarmOprate;
        this.textAlarm2SettingList = list;
    }

    public EMultiAlarmOprate getOprate() {
        return this.oprate;
    }

    public void setOprate(EMultiAlarmOprate eMultiAlarmOprate) {
        this.oprate = eMultiAlarmOprate;
    }

    public List<TextAlarm2Setting> getTextAlarm2SettingList() {
        return this.textAlarm2SettingList;
    }

    public void setTextAlarm2SettingList(List<TextAlarm2Setting> list) {
        this.textAlarm2SettingList = list;
    }

    public String toString() {
        return "TextAlarmData{oprate=" + this.oprate + ", textAlarm2SettingList=" + this.textAlarm2SettingList + '}';
    }
}
