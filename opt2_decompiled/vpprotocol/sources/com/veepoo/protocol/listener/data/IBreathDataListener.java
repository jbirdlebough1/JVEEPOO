package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.BreathData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IBreathDataListener.class */
public interface IBreathDataListener extends IListener {
    void onDataChange(BreathData breathData);
}
