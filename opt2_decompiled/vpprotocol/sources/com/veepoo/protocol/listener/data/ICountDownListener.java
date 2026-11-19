package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.CountDownData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICountDownListener.class */
public interface ICountDownListener extends IListener {
    void OnCountDownDataChange(CountDownData countDownData);
}
