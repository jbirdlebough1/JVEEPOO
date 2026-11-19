package com.veepoo.protocol.listener.oad;

import com.veepoo.protocol.model.OadFileBean;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/oad/OnUpdateCheckListener.class */
public interface OnUpdateCheckListener extends OnFindOadDeviceListener {
    void onNetVersionInfo(int i, String str, String str2);

    void onRemoteOadFileGet(OadFileBean oadFileBean);

    void onDownLoadOadFile(float f);

    void onCheckFail(int i);

    void onCheckSuccess(String str);
}
