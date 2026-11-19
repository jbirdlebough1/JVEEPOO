package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.ScreenLightData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IScreenLightListener.class */
public interface IScreenLightListener extends IListener {
    void onScreenLightDataChange(ScreenLightData screenLightData);
}
