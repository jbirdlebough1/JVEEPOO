package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.YM23ProWearReportData;
import com.veepoo.protocol.model.enums.EYM23ProOptStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IYM23ProWearReportListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IYM23ProWearReportListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onYM23ProWearReportOpenResult", "", "status", "Lcom/veepoo/protocol/model/enums/EYM23ProOptStatus;", "onYM23ProWearReportCloseResult", "onYM23ProWearReport", "data", "Lcom/veepoo/protocol/model/datas/YM23ProWearReportData;", "vpprotocol_release"})
public interface IYM23ProWearReportListener extends IListener {
    void onYM23ProWearReportOpenResult(@NotNull EYM23ProOptStatus status);

    void onYM23ProWearReportCloseResult(@NotNull EYM23ProOptStatus status);

    void onYM23ProWearReport(@NotNull YM23ProWearReportData data);
}
