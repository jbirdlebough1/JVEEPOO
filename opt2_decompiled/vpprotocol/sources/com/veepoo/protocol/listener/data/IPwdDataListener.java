package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.PwdData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IPwdDataListener.class */
public interface IPwdDataListener extends IListener {
    void onPwdDataChange(PwdData pwdData);

    void onConnectionConfirmTimeout();
}
