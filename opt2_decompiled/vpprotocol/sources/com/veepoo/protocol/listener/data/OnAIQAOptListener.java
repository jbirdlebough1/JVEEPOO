package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.operate.AIFunctionOpt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/OnAIQAOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/OnAIQAOptListener;", "", "onAIQAStartRecording", "", "onAIQAStopRecording", "onAIQAResult", "errorCode", "Lcom/veepoo/protocol/operate/AIFunctionOpt$AIDeviceErrorCode;", "onAIQARegenerate", "onAIQATerminateREQ", "vpprotocol_release"})
public interface OnAIQAOptListener {
    void onAIQAStartRecording();

    void onAIQAStopRecording();

    void onAIQAResult(@NotNull AIFunctionOpt.AIDeviceErrorCode errorCode);

    void onAIQARegenerate();

    void onAIQATerminateREQ();
}
