package com.veepoo.protocol.util.ai;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.veepoo.protocol.AiDialPreviewManager;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.OnAiDialPreviewSendListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.vp_g;
import java.util.Locale;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiPreviewDataSender.class */
public class AiPreviewDataSender {
    private byte[] vp_a;
    private byte[] vp_c;
    private byte[] vp_d;
    private AiCropWatchFaceBitmap vp_i;
    public OnAiDialPreviewSendListener listener;
    public IAiDialJLPreviewSender jlPreviewSender;
    private int vp_b = 20;
    private int vp_e = 0;
    private int vp_f = 0;
    private long vp_g = 0;
    private long vp_h = 0;
    private final Handler vp_j = new Handler(Looper.getMainLooper());
    public int sendCount = 0;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiPreviewDataSender$ClassHolder.class */
    private static final class ClassHolder {
        private static final AiPreviewDataSender vp_a = new AiPreviewDataSender();
    }

    public static AiPreviewDataSender getInstance() {
        return ClassHolder.vp_a;
    }

    private AiPreviewDataSender() {
        vp_a();
    }

    private boolean vp_b() {
        Context context = VPOperateManager.mContext;
        if (context == null) {
            return false;
        }
        return VpSpGetUtil.getVpSpVariInstance(context).isJieLiDevice();
    }

    private void vp_e() {
        this.vp_f = 0;
        byte[] bArr = this.vp_d;
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        byte[] bArrVp_a = vp_a(0, this.vp_b);
        this.vp_c = bArrVp_a;
        if (bArrVp_a == null) {
            return;
        }
        vp_a(bArrVp_a, "发送预览图数据 startSendData2Device 第" + this.vp_f + "包， [" + this.sendCount + "/" + this.vp_d.length + "]");
    }

    private void vp_d() {
        int i = this.sendCount;
        if (i == this.vp_d.length && i < this.vp_a.length) {
            Log.e("-AiPreviewDataSender-", "阶段性发送完数据：" + this.sendCount + " 等待下一次发送");
            return;
        }
        int i2 = this.vp_f + 1;
        this.vp_f = i2;
        if (i >= this.vp_a.length) {
            this.vp_h = System.currentTimeMillis();
            Log.e("-AiPreviewDataSender-", "发送预览图数据:>> 结束时间：" + System.currentTimeMillis());
            int i3 = (int) ((this.vp_h - this.vp_g) / 1000);
            Log.e("-AiPreviewDataSender-", "发送预览图数据:>> 用时" + String.format(Locale.US, "%02d:%02d", Integer.valueOf(i3 / 60), Integer.valueOf(i3 % 60)) + "一共" + this.vp_f + "包数据，mtu：" + this.vp_b + "，预览图大小：" + (this.vp_a.length / 1024) + "kb余" + (this.vp_a.length % 1024) + "b");
            Log.e("-AiPreviewDataSender-", "开始发送 -sendNext-: 发送完成");
            return;
        }
        byte[] bArrVp_a = vp_a(i2, this.vp_b);
        this.vp_c = bArrVp_a;
        OnAiDialPreviewSendListener onAiDialPreviewSendListener = this.listener;
        if (onAiDialPreviewSendListener != null) {
            int i4 = this.sendCount;
            float f = i4 * 1.0f;
            byte[] bArr = this.vp_a;
            onAiDialPreviewSendListener.onDataSendProgress(bArrVp_a, this.vp_f, i4, bArr.length, (int) ((f / bArr.length) * 100.0f));
        }
        byte[] bArr2 = this.vp_c;
        if (bArr2 == null || bArr2.length == 0) {
            Log.e("-AiPreviewDataSender-", "需要发送的数据为null，不发送");
        } else {
            vp_a(bArr2, "发送预览图数据 sendNextData 第" + this.vp_f + "包， [" + this.sendCount + "/" + this.vp_d.length + "]");
        }
    }

    private byte[] vp_a(int i, int i2) {
        byte[] bArr = this.vp_d;
        int length = bArr.length;
        int i3 = this.sendCount;
        int i4 = i3 + i2;
        int length2 = i4;
        if (i4 >= bArr.length) {
            length2 = bArr.length;
        }
        int i5 = length2 - i3;
        if (i3 == length2) {
            this.sendCount = this.vp_a.length;
            Log.e("-AiPreviewDataSender-", "-getCmd-:  第" + i + "包 [" + i3 + "-" + length2 + "] -- 数据【" + this.sendCount + "/" + length + "】 mtu = " + i2 + " ， 真实的发送长度 = " + i5);
        }
        if (i5 <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[i5];
        this.sendCount += i5;
        Log.e("-AiPreviewDataSender-", "-getCmd-: 开始和结束 => 第" + i + "包 [" + i3 + "-" + length2 + "] -- 数据【" + this.sendCount + "/" + length + "】 mtu = " + i2 + " ， 真实的发送长度 = " + i5);
        if (i5 >= 0) {
            System.arraycopy(this.vp_d, i3, bArr2, 0, i5);
        }
        return bArr2;
    }

    public void initSendData(byte[] bArr, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
        this.vp_a = bArr;
        this.vp_b = VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getMtuValue();
        this.sendCount = 0;
        this.vp_e = 0;
        this.vp_c = null;
        this.listener = onAiDialPreviewSendListener;
        Log.e("-AiPreviewDataSender-", "ai预览图原始数据 总数: " + vp_g.vp_d(bArr));
    }

    public void initJlSendData(AiCropWatchFaceBitmap aiCropWatchFaceBitmap, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
        this.vp_e = 0;
        this.vp_i = aiCropWatchFaceBitmap;
        this.listener = onAiDialPreviewSendListener;
    }

    public void onDataRequest(int i) {
        this.vp_e += i;
        if (!vp_b()) {
            byte[] bArr = this.vp_a;
            if (bArr == null) {
                return;
            }
            if (this.vp_e > bArr.length) {
                this.vp_e = bArr.length;
            }
            int i2 = this.vp_e;
            byte[] bArr2 = new byte[i2];
            this.vp_d = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, i2);
        }
        if (this.vp_e != i) {
            Log.e("-AiPreviewDataSender-", "-onDataRequest-：" + i + " ----> 更新长度 = " + this.vp_d.length);
            vp_d();
            return;
        }
        this.vp_g = System.currentTimeMillis();
        Log.e("-AiPreviewDataSender-", "发送预览图数据:>> 开始时间：" + System.currentTimeMillis());
        if (!vp_b()) {
            vp_e();
            return;
        }
        IAiDialJLPreviewSender iAiDialJLPreviewSender = this.jlPreviewSender;
        if (iAiDialJLPreviewSender != null) {
            iAiDialJLPreviewSender.startTransfer(this.vp_i, new IAiDialJLPreviewSender.JLTransferListener() { // from class: com.veepoo.protocol.util.ai.AiPreviewDataSender.1
                @Override // com.veepoo.protocol.util.ai.IAiDialJLPreviewSender.JLTransferListener
                public void onTransferComplete() {
                    Log.e("-AiPreviewDataSender-", "JL 预览图传输完成 → 通知 AiDialPreviewManager");
                    AiDialPreviewManager.getInstance().onJLPreviewTransferComplete();
                }

                @Override // com.veepoo.protocol.util.ai.IAiDialJLPreviewSender.JLTransferListener
                public void onTransferFailed() {
                    OnAiDialPreviewSendListener onAiDialPreviewSendListener = AiPreviewDataSender.this.listener;
                    if (onAiDialPreviewSendListener != null) {
                        onAiDialPreviewSendListener.onPreviewSendFailed();
                    }
                }
            });
            return;
        }
        Log.e("-AiPreviewDataSender-", "JL设备预览图传输未注入 IAiDialJLPreviewSender 实现");
        OnAiDialPreviewSendListener onAiDialPreviewSendListener = this.listener;
        if (onAiDialPreviewSendListener != null) {
            onAiDialPreviewSendListener.onPreviewSendFailed();
        }
    }

    public void onSendPreviewComplete() {
    }

    public void resetForSend(byte[] bArr, OnAiDialPreviewSendListener onAiDialPreviewSendListener) {
        initSendData(bArr, onAiDialPreviewSendListener);
    }

    private void vp_a() {
        Context context = VPOperateManager.mContext;
        if (context != null) {
            this.vp_b = VpSpGetUtil.getVpSpVariInstance(context).getMtuValue();
        }
    }

    private void vp_a(byte[] bArr, String str) {
        Log.e("-AiPreviewDataSender-", "开始发送: " + str + " -> " + vp_g.vp_d(bArr));
        VPOperateManager.getInstance().sendUiData(new IBleWriteResponse() { // from class: com.veepoo.protocol.util.ai.AiPreviewDataSender.2
            @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
            public void onResponse(int i) {
            }
        }, bArr);
        this.vp_j.postDelayed(() -> {
            vp_d();
        }, 5L);
    }
}
