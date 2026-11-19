package com.veepoo.protocol.util.ai;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/IAiDialJLPreviewSender.class */
public interface IAiDialJLPreviewSender {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/IAiDialJLPreviewSender$JLTransferListener.class */
    public interface JLTransferListener {
        void onTransferComplete();

        void onTransferFailed();
    }

    void startTransfer(AiCropWatchFaceBitmap aiCropWatchFaceBitmap, JLTransferListener jLTransferListener);
}
