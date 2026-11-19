package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.SleepData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAllHealthDataListener.class */
public interface IAllHealthDataListener {
    void onProgress(float f);

    void onSleepDataChange(String str, SleepData sleepData);

    void onReadSleepComplete();

    void onOringinFiveMinuteDataChange(OriginData originData);

    void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData);

    void onReadOriginComplete();

    void onReadTimeout(int i);
}
