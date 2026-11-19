package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.QH15SecurityProtectionModel;
import com.veepoo.protocol.model.enums.EQH15SPType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IQH15GnssLocationListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IQH15GnssLocationListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onQH15GnssLocation", "", "type", "Lcom/veepoo/protocol/model/enums/EQH15SPType;", "model", "Lcom/veepoo/protocol/model/datas/QH15SecurityProtectionModel;", "vpprotocol_release"})
public interface IQH15GnssLocationListener extends IListener {
    void onQH15GnssLocation(@NotNull EQH15SPType type, @Nullable QH15SecurityProtectionModel model);
}
