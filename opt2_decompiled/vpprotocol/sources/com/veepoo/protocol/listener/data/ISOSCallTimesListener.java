package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ISOSCallTimesListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J \u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\b\u0010\n\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/ISOSCallTimesListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onSOSCallTimesSettingSuccess", "", "times", "", "onSOSCallTimesSettingFailed", "onSOSCallTimesReadSuccess", "minTimes", "maxTimes", "onSOSCallTimesReadFailed", "vpprotocol_release"})
public interface ISOSCallTimesListener extends IListener {
    void onSOSCallTimesSettingSuccess(int times);

    void onSOSCallTimesSettingFailed();

    void onSOSCallTimesReadSuccess(int times, int minTimes, int maxTimes);

    void onSOSCallTimesReadFailed();
}
