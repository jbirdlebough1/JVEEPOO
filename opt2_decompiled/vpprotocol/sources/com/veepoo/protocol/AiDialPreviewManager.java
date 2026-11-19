package com.veepoo.protocol;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.bluetrum.abpartool.ParTool;
import com.bluetrum.abpartool.utils.ImageUtils;
import com.veepoo.protocol.customui.WatchUIType;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.OnAiDialPreviewSendListener;
import com.veepoo.protocol.model.datas.AIDeviceConfigBean;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.operate.AIFunctionOpt;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.ai.AiCropWatchFaceBitmap;
import com.veepoo.protocol.util.ai.AiDialJLPreviewSenderImpl;
import com.veepoo.protocol.util.ai.AiImageCropManager;
import com.veepoo.protocol.util.ai.AiPreviewDataSender;
import com.veepoo.protocol.util.ai.IAiDialJLPreviewSender;
import com.veepoo.protocol.util.thread.HBThreadPools;
import com.veepoo.protocol.util.thread.ThreadPoolsUtil;
import com.veepoo.protocol.util.vp_h;
import java.io.File;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager.class */
public class AiDialPreviewManager {
    private EWatchUIType vp_a;
    private IAiDialJLPreviewSender vp_b = new AiDialJLPreviewSenderImpl();
    private AIDeviceConfigBean vp_c;
    private AiCropWatchFaceBitmap vp_d;
    private AiCropWatchFaceBitmap vp_e;
    private AiCropWatchFaceBitmap vp_f;
    private OnAiDialPreviewSendListener vp_g;
    private boolean vp_h;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_a.class */
    class vp_a implements AiImageCropManager.OnAiDrawImageCropListener {
        final /* synthetic */ boolean vp_a;
        final /* synthetic */ OnAiDialPreviewSendListener vp_b;

        /* JADX INFO: renamed from: com.veepoo.protocol.AiDialPreviewManager$vp_a$vp_a, reason: collision with other inner class name */
        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_a$vp_a.class */
        class C0000vp_a implements ThreadPoolsUtil.SmartCall {
            int vp_a = -1;
            final /* synthetic */ AiCropWatchFaceBitmap vp_b;

            C0000vp_a(AiCropWatchFaceBitmap aiCropWatchFaceBitmap) {
                this.vp_b = aiCropWatchFaceBitmap;
            }

            @Override // com.veepoo.protocol.util.thread.ThreadPoolsUtil.SmartCall
            /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
            public byte[] doBackground() {
                byte[] rawImageData = ImageUtils.getRawImageData(this.vp_b.bitmap);
                int width = this.vp_b.bitmap.getWidth();
                int height = this.vp_b.bitmap.getHeight();
                boolean z = vp_a.this.vp_a;
                byte[] bArrRawToPar = ParTool.rawToPar(rawImageData, width, height, z, false, z);
                if (bArrRawToPar != null) {
                    this.vp_a = vp_h.vp_a.vp_a(bArrRawToPar);
                }
                return bArrRawToPar;
            }

            @Override // com.veepoo.protocol.util.thread.ThreadPoolsUtil.SmartCall
            /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
            public void onDo(byte[] bArr) {
                if (bArr != null && bArr.length > 20) {
                    AiDialPreviewManager.this.vp_a(bArr, this.vp_a, this.vp_b);
                    return;
                }
                OnAiDialPreviewSendListener onAiDialPreviewSendListener = vp_a.this.vp_b;
                if (onAiDialPreviewSendListener != null) {
                    onAiDialPreviewSendListener.onPreviewSendFailed();
                }
            }
        }

        vp_a(boolean z, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
            this.vp_a = z;
            this.vp_b = onAiDialPreviewSendListener;
        }

        @Override // com.veepoo.protocol.util.ai.AiImageCropManager.OnAiDrawImageCropListener
        public void onAiWatchFaceCropped(AiCropWatchFaceBitmap aiCropWatchFaceBitmap, AiCropWatchFaceBitmap aiCropWatchFaceBitmap2, AiCropWatchFaceBitmap aiCropWatchFaceBitmap3) {
            Log.e("-AiDialPreviewManager-", "-sendAiDialPreview-: 裁剪成功 onAiWatchFaceCropped");
            AiDialPreviewManager aiDialPreviewManager = AiDialPreviewManager.this;
            aiDialPreviewManager.vp_d = aiCropWatchFaceBitmap;
            aiDialPreviewManager.vp_e = aiCropWatchFaceBitmap2;
            aiDialPreviewManager.vp_f = aiCropWatchFaceBitmap3;
            HBThreadPools.getInstance().executeWitSmartCall(new C0000vp_a(aiCropWatchFaceBitmap3));
        }

        @Override // com.veepoo.protocol.util.ai.AiImageCropManager.OnAiDrawImageCropListener
        public void onAiWatchFaceCropFailed() {
            AiDialPreviewManager.this.vp_a(AIFunctionOpt.AIAppErrorCode.AI_DRAWING_FAILED_0x07, 0, 0, "AI图片裁剪失败");
            OnAiDialPreviewSendListener onAiDialPreviewSendListener = this.vp_b;
            if (onAiDialPreviewSendListener != null) {
                onAiDialPreviewSendListener.onPreviewSendFailed();
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_b.class */
    class vp_b implements IBleWriteResponse {
        vp_b() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_c.class */
    class vp_c implements IBleWriteResponse {
        vp_c() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_d.class */
    class vp_d implements IBleWriteResponse {
        vp_d() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/AiDialPreviewManager$vp_e.class */
    private static final class vp_e {
        private static final AiDialPreviewManager vp_a = new AiDialPreviewManager();
    }

    private AiDialPreviewManager() {
    }

    public static AiDialPreviewManager getInstance() {
        return vp_e.vp_a;
    }

    private void vp_a(byte[] bArr, int i, AiCropWatchFaceBitmap aiCropWatchFaceBitmap) {
        AiPreviewDataSender aiPreviewDataSender = AiPreviewDataSender.getInstance();
        aiPreviewDataSender.listener = this.vp_g;
        if (!vp_a()) {
            aiPreviewDataSender.initSendData(bArr, this.vp_g);
        } else {
            if (getJlPreviewSender() == null) {
                Log.e("-AiDialPreviewManager-", "JL设备未注入 IAiDialJLPreviewSender，无法传输预览图");
                OnAiDialPreviewSendListener onAiDialPreviewSendListener = this.vp_g;
                if (onAiDialPreviewSendListener != null) {
                    onAiDialPreviewSendListener.onPreviewSendFailed();
                    return;
                }
                return;
            }
            aiPreviewDataSender.jlPreviewSender = getJlPreviewSender();
            aiPreviewDataSender.initJlSendData(aiCropWatchFaceBitmap, this.vp_g);
        }
        vp_a(AIFunctionOpt.AIAppErrorCode.SUCCESS, bArr.length, i, "");
    }

    public EWatchUIType getWatchUIType() {
        EWatchUIType eWatchUIType = this.vp_a;
        EWatchUIType eWatchUIType2 = eWatchUIType;
        if (eWatchUIType == null) {
            eWatchUIType2 = EWatchUIType.RECT_JL_240_284;
        }
        return eWatchUIType2;
    }

    public void setWatchUIType(EWatchUIType eWatchUIType) {
        this.vp_a = eWatchUIType;
    }

    public IAiDialJLPreviewSender getJlPreviewSender() {
        return this.vp_b;
    }

    public void setJlPreviewSender(IAiDialJLPreviewSender iAiDialJLPreviewSender) {
        if (iAiDialJLPreviewSender == null) {
            iAiDialJLPreviewSender = aiDialJLPreviewSenderImpl;
            AiDialJLPreviewSenderImpl aiDialJLPreviewSenderImpl = new AiDialJLPreviewSenderImpl();
        }
        this.vp_b = iAiDialJLPreviewSender;
    }

    public AIDeviceConfigBean getAiDeviceConfig() {
        AIDeviceConfigBean aIDeviceConfigBean = this.vp_c;
        return aIDeviceConfigBean != null ? aIDeviceConfigBean : AIFunctionOpt.vp_h.vp_a().vp_a();
    }

    public void setAiDeviceConfig(AIDeviceConfigBean aIDeviceConfigBean) {
        this.vp_c = aIDeviceConfigBean;
    }

    public AiCropWatchFaceBitmap getAiBigWatchFace() {
        return this.vp_d;
    }

    public AiCropWatchFaceBitmap getAiScaleWatchFace() {
        return this.vp_e;
    }

    public AiCropWatchFaceBitmap getAiPreviewWatchFace() {
        return this.vp_f;
    }

    public void sendAiDialPreview(String str, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
        this.vp_g = onAiDialPreviewSendListener;
        this.vp_d = null;
        this.vp_e = null;
        this.vp_f = null;
        this.vp_h = false;
        AIDeviceConfigBean aiDeviceConfig = getAiDeviceConfig();
        if (aiDeviceConfig == null || !aiDeviceConfig.isValid() || TextUtils.isEmpty(str) || !new File(str).exists()) {
            Log.e("-AiDialPreviewManager-", "-sendAiDialPreview-: 参数不合法 或 配置为空 或 图片不存在 -> " + str);
            if (onAiDialPreviewSendListener != null) {
                onAiDialPreviewSendListener.onPreviewSendFailed();
                return;
            }
            return;
        }
        EWatchUIType watchUIType = getWatchUIType();
        WatchUIType watchUIType2 = WatchUIType.getInstance(watchUIType);
        if (watchUIType2 == null) {
            if (onAiDialPreviewSendListener != null) {
                onAiDialPreviewSendListener.onPreviewSendFailed();
            }
        } else {
            boolean zVp_a = vp_a(watchUIType2);
            Log.e("-AiDialPreviewManager-", "-sendAiDialPreview-: 图片路径 = " + str + " ， 表盘类型 = " + watchUIType + " ， 配置信息 = " + aiDeviceConfig);
            AiImageCropManager.getInstance().cropAiDrawImage2WatchFace(str, watchUIType2.getBigBitmapWidth(), watchUIType2.getBigBitmapHeight(), watchUIType2.isRoundScreen().booleanValue(), new vp_a(zVp_a, onAiDialPreviewSendListener));
        }
    }

    public void onPreviewLengthGetACK(int i) {
        Log.e("-AiDialPreviewManager-", "-onPreviewLengthGetACK-: needGetPreviewLength = " + i);
        VPOperateManager.getInstance().vp_a(new vp_c(), AIFunctionOpt.AIAppErrorCode.SUCCESS);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AiPreviewDataSender.getInstance().onDataRequest(i);
        }, 300L);
    }

    public void onContinueGetPreviewDataREQ(int i) {
        Log.e("-AiDialPreviewManager-", "-onContinueGetPreviewDataREQ-: needGetPreviewLength = " + i);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            AiPreviewDataSender.getInstance().onDataRequest(i);
        }, 300L);
    }

    public void onPreviewDataReceiveComplete(AIFunctionOpt.AIDeviceErrorCode aIDeviceErrorCode) {
        Log.e("-AiDialPreviewManager-", "-onPreviewDataReceiveComplete-: errorCode = " + aIDeviceErrorCode);
        VPOperateManager.getInstance().vp_b(new vp_d(), AIFunctionOpt.AIAppErrorCode.SUCCESS);
        AiPreviewDataSender.getInstance().onSendPreviewComplete();
        vp_b();
    }

    public void onJLPreviewTransferComplete() {
        Log.e("-AiDialPreviewManager-", "-onJLPreviewTransferComplete-: JL 通道预览图传输完成");
        vp_b();
    }

    private boolean vp_a() {
        Context context = VPOperateManager.mContext;
        if (context == null) {
            return false;
        }
        return VpSpGetUtil.getVpSpVariInstance(context).isJieLiDevice();
    }

    private void vp_b() {
        OnAiDialPreviewSendListener onAiDialPreviewSendListener = this.vp_g;
        if (onAiDialPreviewSendListener == null || this.vp_h) {
            return;
        }
        this.vp_h = true;
        onAiDialPreviewSendListener.onPreviewSendComplete(this.vp_d, this.vp_e, this.vp_f);
    }

    private boolean vp_a(WatchUIType watchUIType) {
        if (watchUIType == null) {
            return false;
        }
        return watchUIType.getClass().getSimpleName().contains("P569X");
    }

    private void vp_a(AIFunctionOpt.AIAppErrorCode aIAppErrorCode, int i, int i2, String str) {
        Log.e("-AiDialPreviewManager-", "-sendAiDialGenerateResult-: " + aIAppErrorCode + "-" + str);
        VPOperateManager.getInstance().sendAIDialGenerateResult(new vp_b(), aIAppErrorCode, i, i2);
    }
}
