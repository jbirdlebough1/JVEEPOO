package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.settings.TextAlarm2Setting;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/ITextAlarmDataReadListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/ITextAlarmDataReadListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onAlarmDataChangeListener", "", "textAlarm2Setting", "Lcom/veepoo/protocol/model/settings/TextAlarm2Setting;", "vpprotocol_release"})
public interface ITextAlarmDataReadListener extends IListener {
    void onAlarmDataChangeListener(@Nullable TextAlarm2Setting textAlarm2Setting);
}
