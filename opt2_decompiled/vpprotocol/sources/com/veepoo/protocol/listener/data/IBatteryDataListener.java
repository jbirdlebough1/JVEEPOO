package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.BatteryData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IBatteryDataListener.class */
public interface IBatteryDataListener extends IListener {
    void onDataChange(BatteryData batteryData);
}
