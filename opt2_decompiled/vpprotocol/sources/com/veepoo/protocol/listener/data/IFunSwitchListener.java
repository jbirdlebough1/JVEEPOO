package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IFunSwitchListener.class */
public interface IFunSwitchListener extends IListener {
    void onFunSwitchStatusChanged(int i, Map<Integer, EFunctionStatus> map);
}
