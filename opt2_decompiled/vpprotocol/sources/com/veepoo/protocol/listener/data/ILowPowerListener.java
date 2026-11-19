package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.LowPowerData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ILowPowerListener.class */
public interface ILowPowerListener extends IListener {
    void onLowpowerDataDataChange(LowPowerData lowPowerData);
}
