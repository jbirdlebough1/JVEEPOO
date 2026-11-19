package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.BpData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IBPDetectDataListener.class */
public interface IBPDetectDataListener extends IListener {
    void onDataChange(BpData bpData);
}
