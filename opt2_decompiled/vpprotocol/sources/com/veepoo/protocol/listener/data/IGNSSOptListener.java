package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.GnssLocationData;
import com.veepoo.protocol.model.enums.EGnssOptType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IGNSSOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IGNSSOptListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onDeviceGNSSOptRequest", "", "gnssOptType", "Lcom/veepoo/protocol/model/enums/EGnssOptType;", "longitude", "", "latitude", "onDeviceGNSSOptReport", "data", "Lcom/veepoo/protocol/model/datas/GnssLocationData;", "vpprotocol_release"})
public interface IGNSSOptListener extends IListener {
    void onDeviceGNSSOptRequest(@NotNull EGnssOptType gnssOptType, double longitude, double latitude);

    void onDeviceGNSSOptReport(@NotNull EGnssOptType gnssOptType, @NotNull GnssLocationData data);
}
