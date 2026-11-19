package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.FunctionSocailMsgData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISocialMsgDataListener.class */
public interface ISocialMsgDataListener extends IListener {
    void onSocialMsgSupportDataChange(FunctionSocailMsgData functionSocailMsgData);

    void onSocialMsgSupportDataChange2(FunctionSocailMsgData functionSocailMsgData);
}
