package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.OriginData;
import com.veepoo.protocol.model.datas.OriginHalfHourData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IOriginDataListener.class */
public interface IOriginDataListener extends IOriginProgressListener {
    void onOringinFiveMinuteDataChange(OriginData originData);

    void onOringinHalfHourDataChange(OriginHalfHourData originHalfHourData);
}
