package com.veepoo.protocol.listener.oad;

import com.veepoo.protocol.model.OadFileBean;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/oad/OnGetOadVersionListener.class */
public interface OnGetOadVersionListener {
    void onNetOadInfo(int i, String str, String str2, boolean z);

    void onNetOadDetailInfo(OadFileBean oadFileBean, boolean z);
}
