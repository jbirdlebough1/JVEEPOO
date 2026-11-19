package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.enums.ECameraStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ICameraDataListener.class */
public interface ICameraDataListener extends IListener {
    void OnCameraDataChange(ECameraStatus eCameraStatus);
}
