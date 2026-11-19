package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.AlarmData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAlarmDataListener.class */
public interface IAlarmDataListener extends IListener {
    void onAlarmDataChangeListener(AlarmData alarmData);
}
