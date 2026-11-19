package com.veepoo.protocol.listener;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.HealthRemind;
import com.veepoo.protocol.model.enums.HealthRemindType;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/IHealthRemindListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/IHealthRemindListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "functionNotSupport", "", "onHealthRemindRead", "healthRemind", "Lcom/veepoo/protocol/model/datas/HealthRemind;", "onHealthRemindReadingComplete", "onHealthRemindReadFailed", "onHealthRemindReport", "onHealthRemindReportFailed", "onHealthRemindSettingSuccess", "onHealthRemindSettingFailed", "healthRemindType", "Lcom/veepoo/protocol/model/enums/HealthRemindType;", "vpprotocol_release"})
public interface IHealthRemindListener extends IListener {
    void functionNotSupport();

    void onHealthRemindRead(@NotNull HealthRemind healthRemind);

    void onHealthRemindReadingComplete();

    void onHealthRemindReadFailed();

    void onHealthRemindReport(@NotNull HealthRemind healthRemind);

    void onHealthRemindReportFailed();

    void onHealthRemindSettingSuccess(@NotNull HealthRemind healthRemind);

    void onHealthRemindSettingFailed(@NotNull HealthRemindType healthRemindType);
}
