package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.ReportGpsData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IAppReportGpsDataListener.class */
public interface IAppReportGpsDataListener extends IListener {
    void onReportGpsDataDataChange(ReportGpsData reportGpsData);
}
