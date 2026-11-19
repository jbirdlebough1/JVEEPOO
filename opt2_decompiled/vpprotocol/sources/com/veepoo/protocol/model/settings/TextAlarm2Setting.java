package com.veepoo.protocol.model.settings;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/TextAlarm2Setting.class */
public class TextAlarm2Setting extends Alarm2Setting {
    private String content;

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    @Override // com.veepoo.protocol.model.settings.Alarm2Setting
    public String toString() {
        return "TextAlarm2Setting{content='" + this.content + "', MAFlag='" + this.MAFlag + "', BluetoothAddress='" + this.BluetoothAddress + "', alarmId=" + this.alarmId + ", alarmHour=" + this.alarmHour + ", alarmMinute=" + this.alarmMinute + ", repeatStatus='" + this.repeatStatus + "', unRepeatDate='" + this.unRepeatDate + "', scene=" + this.scene + ", isOpen=" + this.isOpen + '}';
    }
}
