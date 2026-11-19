package com.veepoo.protocol.listener;

import com.veepoo.protocol.model.datas.MiniCheckupDetailData;
import com.veepoo.protocol.model.datas.MiniCheckupResultData;
import com.veepoo.protocol.model.enums.EMiniCheckupTestErrorCode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/IMiniCheckupOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/IMiniCheckupOptListener;", "", "onMiniCheckupTestProgress", "", "progress", "", "onMiniCheckupStopSuccess", "onMiniCheckupTestFailed", "errorCode", "Lcom/veepoo/protocol/model/enums/EMiniCheckupTestErrorCode;", "onMiniCheckupSuccess", "testResultData", "Lcom/veepoo/protocol/model/datas/MiniCheckupResultData;", "onMiniCheckupDetailTestSuccess", "miniCheckupDetailData", "Lcom/veepoo/protocol/model/datas/MiniCheckupDetailData;", "vpprotocol_release"})
public interface IMiniCheckupOptListener {
    void onMiniCheckupTestProgress(int progress);

    void onMiniCheckupStopSuccess();

    void onMiniCheckupTestFailed(@NotNull EMiniCheckupTestErrorCode errorCode);

    void onMiniCheckupSuccess(@NotNull MiniCheckupResultData testResultData);

    void onMiniCheckupDetailTestSuccess(@NotNull MiniCheckupDetailData miniCheckupDetailData);
}
