package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.PPGRawData;
import com.veepoo.protocol.model.enums.EJH58MeasurementState;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IActiveMeasurementDataListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IActiveMeasurementDataListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onMeasurementStateResult", "", "state", "Lcom/veepoo/protocol/model/enums/EJH58MeasurementState;", "ack", "", "onActiveMeasurementDataStart", "totalSeconds", "onActiveMeasurementDataReceived", "ppgRawData", "Lcom/veepoo/protocol/model/datas/PPGRawData;", "vpprotocol_release"})
public interface IActiveMeasurementDataListener extends IListener {
    void onMeasurementStateResult(@NotNull EJH58MeasurementState state, int ack);

    void onActiveMeasurementDataStart(int totalSeconds);

    void onActiveMeasurementDataReceived(@NotNull PPGRawData ppgRawData);
}
