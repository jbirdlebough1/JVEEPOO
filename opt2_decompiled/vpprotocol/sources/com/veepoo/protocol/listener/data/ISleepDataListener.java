package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.SleepData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISleepDataListener.class */
public interface ISleepDataListener {
    void onSleepDataChange(String str, SleepData sleepData);

    void onSleepProgress(float f);

    void onSleepProgressDetail(String str, int i);

    void onReadSleepComplete();
}
