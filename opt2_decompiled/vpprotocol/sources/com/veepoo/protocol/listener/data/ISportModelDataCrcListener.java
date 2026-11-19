package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISportModelDataCrcListener.class */
public interface ISportModelDataCrcListener extends IListener {
    void onSportCrcDataChange(int[] iArr);

    void onReadFail();
}
