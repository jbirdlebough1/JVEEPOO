package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.ReportGpsLatLongData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IReportGpsDataListener.class */
public interface IReportGpsDataListener extends IListener {
    void onReportGpsDataDataChange(ReportGpsLatLongData reportGpsLatLongData);
}
