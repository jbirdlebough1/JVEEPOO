package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.BpSettingData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IBPSettingDataListener.class */
public interface IBPSettingDataListener extends IListener {
    void onDataChange(BpSettingData bpSettingData);
}
