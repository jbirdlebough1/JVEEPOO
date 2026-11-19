package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.AutoDetectStateData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICustomProtocolStateListener.class */
public interface ICustomProtocolStateListener extends IListener {
    void onS22AutoDetectStateChangeListener(AutoDetectStateData autoDetectStateData);
}
