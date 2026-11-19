package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.WeatherStatusData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IMusicDataListener.class */
public interface IMusicDataListener extends IListener {
    void onMusicDataChange(WeatherStatusData weatherStatusData);
}
