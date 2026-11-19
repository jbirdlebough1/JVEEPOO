package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.DayState;
import com.veepoo.protocol.model.datas.RRIntervalData;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IRRIntervalProgressListener.class */
public interface IRRIntervalProgressListener extends IListener {
    void onReadRRIntervalProgressChanged(float f, RRIntervalData rRIntervalData);

    void onReadRRIntervalComplete(DayState dayState, List<RRIntervalData> list);
}
