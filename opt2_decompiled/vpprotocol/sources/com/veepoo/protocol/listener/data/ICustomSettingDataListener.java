package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.settings.CustomSettingData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICustomSettingDataListener.class */
public interface ICustomSettingDataListener extends IListener {
    void OnSettingDataChange(CustomSettingData customSettingData);
}
