package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.TimeData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IECGAutoReportListener.class */
public interface IECGAutoReportListener {
    void onECGAutoReport(int i, TimeData timeData);

    void onECGDataReport(int[] iArr);
}
