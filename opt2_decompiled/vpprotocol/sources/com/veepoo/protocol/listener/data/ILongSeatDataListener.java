package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.LongSeatData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ILongSeatDataListener.class */
public interface ILongSeatDataListener extends IListener {
    void onLongSeatDataChange(LongSeatData longSeatData);
}
