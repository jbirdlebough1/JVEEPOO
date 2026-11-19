package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IOriginProgressListener.class */
public interface IOriginProgressListener extends IListener {
    void onReadOriginProgressDetail(int i, String str, int i2, int i3);

    void onReadOriginProgress(float f);

    void onReadOriginComplete();

    void onReadTimeout(int i);
}
