package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.QX17GPSData;
import com.veepoo.protocol.model.datas.QX17HeartRateData;
import com.veepoo.protocol.model.datas.QX17IMUData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IQX17DataAcquisitionListener.class */
public interface IQX17DataAcquisitionListener extends IListener {
    void onQX17DataAcquisitionStatus(boolean z);

    void onQX17IMUData(List<QX17IMUData> list);

    void onQX17GPSData(QX17GPSData qX17GPSData);

    void onQX17HeartRateData(QX17HeartRateData qX17HeartRateData);
}
