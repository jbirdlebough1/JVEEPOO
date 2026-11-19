package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.CheckWearData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICheckWearDataListener.class */
public interface ICheckWearDataListener extends IListener {
    void onCheckWearDataChange(CheckWearData checkWearData);
}
