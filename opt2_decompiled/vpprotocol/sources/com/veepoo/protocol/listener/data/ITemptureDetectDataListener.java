package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.TemptureDetectData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ITemptureDetectDataListener.class */
public interface ITemptureDetectDataListener extends IListener {
    void onDataChange(TemptureDetectData temptureDetectData);
}
