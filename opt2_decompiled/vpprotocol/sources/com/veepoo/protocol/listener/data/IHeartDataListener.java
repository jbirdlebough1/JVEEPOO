package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.HeartData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IHeartDataListener.class */
public interface IHeartDataListener extends IListener {
    void onDataChange(HeartData heartData);
}
