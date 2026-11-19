package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.HeartWaringData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IHeartWaringDataListener.class */
public interface IHeartWaringDataListener extends IListener {
    void onHeartWaringDataChange(HeartWaringData heartWaringData);
}
