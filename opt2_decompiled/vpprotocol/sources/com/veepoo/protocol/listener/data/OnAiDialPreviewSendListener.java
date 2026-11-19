package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.util.ai.AiCropWatchFaceBitmap;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/OnAiDialPreviewSendListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H&J\u001a\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u0007H&J\b\u0010\r\u001a\u00020\u0003H&J&\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/OnAiDialPreviewSendListener;", "", "onDataSendProgress", "", "data", "", "currentPackage", "", "haveSendDataLength", "totalDataLength", "progress", "onDataSendFailed", "failedTimes", "onPreviewSendFailed", "onPreviewSendComplete", "bigWatchFace", "Lcom/veepoo/protocol/util/ai/AiCropWatchFaceBitmap;", "scaleWatchFace", "previewWatchFace", "vpprotocol_release"})
public interface OnAiDialPreviewSendListener {
    void onDataSendProgress(@Nullable byte[] data, int currentPackage, int haveSendDataLength, int totalDataLength, int progress);

    void onDataSendFailed(@Nullable byte[] data, int failedTimes);

    void onPreviewSendFailed();

    void onPreviewSendComplete(@Nullable AiCropWatchFaceBitmap bigWatchFace, @Nullable AiCropWatchFaceBitmap scaleWatchFace, @Nullable AiCropWatchFaceBitmap previewWatchFace);
}
