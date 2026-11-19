package com.veepoo.protocol.util.ai;

import com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiDialJLPreviewSenderImpl.class */
public class AiDialJLPreviewSenderImpl implements IAiDialJLPreviewSender {
    private IAiDialJLPreviewSender.JLTransferListener vp_a;
    private AiCropWatchFaceBitmap vp_b;

    private void vp_a() {
        JLWatchFaceManager.getInstance().setJLTransferPicDialListener(new JLWatchFaceManager.JLTransferPicDialListener() { // from class: com.veepoo.protocol.util.ai.AiDialJLPreviewSenderImpl.1
            public void onLowPower() {
                IAiDialJLPreviewSender.JLTransferListener jLTransferListener = AiDialJLPreviewSenderImpl.this.vp_a;
                if (jLTransferListener != null) {
                    jLTransferListener.onTransferFailed();
                }
            }

            public void onJLTransferPicDialStart() {
            }

            public void onTransferPicDialProgress(int i) {
            }

            public void onScaleBGPFileTransferComplete() {
            }

            public void onAIPreviewTransferComplete() {
            }

            public void onBigBGPFileTransferComplete() {
            }

            public void onTransferComplete() {
                IAiDialJLPreviewSender.JLTransferListener jLTransferListener = AiDialJLPreviewSenderImpl.this.vp_a;
                if (jLTransferListener != null) {
                    jLTransferListener.onTransferComplete();
                }
            }

            public void onTransferError(int i, String str) {
                IAiDialJLPreviewSender.JLTransferListener jLTransferListener = AiDialJLPreviewSenderImpl.this.vp_a;
                if (jLTransferListener != null) {
                    jLTransferListener.onTransferFailed();
                }
            }
        });
        JLWatchFaceManager jLWatchFaceManager = JLWatchFaceManager.getInstance();
        AiCropWatchFaceBitmap aiCropWatchFaceBitmap = this.vp_b;
        jLWatchFaceManager.startTransferAIPreview(aiCropWatchFaceBitmap.filePath, aiCropWatchFaceBitmap.getFileName());
    }

    @Override // com.veepoo.protocol.util.ai.IAiDialJLPreviewSender
    public void startTransfer(AiCropWatchFaceBitmap aiCropWatchFaceBitmap, IAiDialJLPreviewSender.JLTransferListener jLTransferListener) {
        this.vp_b = aiCropWatchFaceBitmap;
        this.vp_a = jLTransferListener;
        vp_a();
    }
}
