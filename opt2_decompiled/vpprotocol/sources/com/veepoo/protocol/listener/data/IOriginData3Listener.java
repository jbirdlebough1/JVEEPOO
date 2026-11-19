package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.model.datas.OriginData3;
import com.veepoo.protocol.model.datas.OriginHalfHourData;
import com.veepoo.protocol.model.datas.Spo2hOriginData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IOriginData3Listener.class */
public interface IOriginData3Listener extends IOriginProgressListener {
    void onOriginFiveMinuteListDataChange(List<OriginData3> list);

    void onOriginHalfHourDataChange(OriginHalfHourData originHalfHourData);

    void onOriginHRVOriginListDataChange(List<HRVOriginData> list);

    void onOriginSpo2OriginListDataChange(List<Spo2hOriginData> list);
}
