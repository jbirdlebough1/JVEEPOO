package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.SportModelStateData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISportModelStateListener.class */
public interface ISportModelStateListener extends IListener {
    void onSportModelStateChange(SportModelStateData sportModelStateData);

    void onSportStopped();
}
