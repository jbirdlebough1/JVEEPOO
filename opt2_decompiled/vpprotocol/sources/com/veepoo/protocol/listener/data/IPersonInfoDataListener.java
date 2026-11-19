package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.EOprateStauts;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IPersonInfoDataListener.class */
public interface IPersonInfoDataListener extends IListener {
    void OnPersoninfoDataChange(EOprateStauts eOprateStauts);
}
