package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.enums.EUiUpdateError;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IUiUpdateListener.class */
public interface IUiUpdateListener {
    void onUiUpdateStart();

    void onStartClearCache(int i);

    void onClearCacheProgress(int i, int i2, int i3);

    void onFinishClearCache();

    void onUiUpdateProgress(int i, int i2, int i3);

    void onUiUpdateSuccess();

    void onUiUpdateFail(EUiUpdateError eUiUpdateError);
}
