package com.veepoo.protocol.listener.data;

import androidx.annotation.MainThread;
import com.veepoo.protocol.model.datas.HrvAnalysisReport;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IHrvAnalysisReportListener.class */
public interface IHrvAnalysisReportListener {
    @MainThread
    void onHrvAnalysisReport(String str, List<HrvAnalysisReport> list);
}
