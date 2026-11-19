package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.enums.EHealthLightStatus;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IHealthLightListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IHealthLightListener;", "", "onHealthLightStatusSetting", "", "isSuccess", "", "status", "Lcom/veepoo/protocol/model/enums/EHealthLightStatus;", "onHealthLightStatusRead", "onHealthLightStatusReport", "vpprotocol_release"})
public interface IHealthLightListener {
    void onHealthLightStatusSetting(boolean isSuccess, @Nullable EHealthLightStatus status);

    void onHealthLightStatusRead(boolean isSuccess, @Nullable EHealthLightStatus status);

    void onHealthLightStatusReport(@Nullable EHealthLightStatus status);
}
