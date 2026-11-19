package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.settings.Alarm2Setting;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAlarm2DataReadListener.class */
public interface IAlarm2DataReadListener extends IListener {
    void onAlarmDataChangeListener(Alarm2Setting alarm2Setting);
}
