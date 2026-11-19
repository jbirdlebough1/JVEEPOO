package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.GameClockBean;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IGameClockOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J&\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IGameClockOptListener;", "", "onDeleteResult", "", "isSuccess", "", "onSettingResult", "onGameTimePlanningRead", "crc", "", "isOpen", "dataList", "", "Lcom/veepoo/protocol/model/datas/GameClockBean;", "onGameClockNotNeed2Update", "onGameClockEmpty", "vpprotocol_release"})
public interface IGameClockOptListener {
    void onDeleteResult(boolean isSuccess);

    void onSettingResult(boolean isSuccess);

    void onGameTimePlanningRead(int crc, boolean isOpen, @NotNull List<GameClockBean> dataList);

    void onGameClockNotNeed2Update();

    void onGameClockEmpty();
}
