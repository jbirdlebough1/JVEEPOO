package com.veepoo.protocol.operate;

import android.text.TextUtils;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.AiDialPreviewManager;
import com.veepoo.protocol.listener.data.OnAIConfigOptListener;
import com.veepoo.protocol.listener.data.OnAIDialOptListener;
import com.veepoo.protocol.listener.data.OnAIQAOptListener;
import com.veepoo.protocol.model.datas.AIDeviceConfigBean;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt.class */
public final class AIFunctionOpt extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_h = new vp_a(null);
    private OnAIConfigOptListener vp_c;
    private OnAIQAOptListener vp_d;
    private OnAIDialOptListener vp_e;
    private AIDeviceConfigBean vp_f;
    private final List vp_g = new ArrayList();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt$AIAppErrorCode.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001c¨\u0006\u001d"}, d2 = {"Lcom/veepoo/protocol/operate/AIFunctionOpt$AIAppErrorCode;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "SUCCESS", "NO_MICROPHONE_PERMISSION_0x01", "NO_VOICE_RECOGNITION_PERMISSION_0x02", "NETWORK_ERROR_0x03", "RECORDING_FAILED_0x04", "SPEECH_RECOGNITION_FAILED_0x05", "AI_QA_FAILED_0x06", "AI_DRAWING_FAILED_0x07", "SENSITIVE_WORDS_0x08", "SPEECH_RECOGNITION_OVER_UPPER_LIMIT_0x09", "AI_QA_OVER_UPPER_LIMIT_0x0A", "AI_DRAWING_OVER_UPPER_LIMIT_0x0B", "NO_MICROPHONE_0x0C", "SYNCHRONIZING_DATA_0x0D", "APP_USE_AI_FUNCTION_0x0E", "REGION_NOT_SUPPORTED_0x0F", "UNKNOWN", "vpprotocol_release"})
    public enum AIAppErrorCode {
        SUCCESS((byte) 0, "成功"),
        NO_MICROPHONE_PERMISSION_0x01((byte) 1, "无麦克风权限"),
        NO_VOICE_RECOGNITION_PERMISSION_0x02((byte) 2, "无语音识别权限"),
        NETWORK_ERROR_0x03((byte) 3, "网络异常"),
        RECORDING_FAILED_0x04((byte) 4, "录音失败"),
        SPEECH_RECOGNITION_FAILED_0x05((byte) 5, "语音识别失败"),
        AI_QA_FAILED_0x06((byte) 6, "AI问答失败"),
        AI_DRAWING_FAILED_0x07((byte) 7, "AI作画失败"),
        SENSITIVE_WORDS_0x08((byte) 8, "敏感词"),
        SPEECH_RECOGNITION_OVER_UPPER_LIMIT_0x09((byte) 9, "语音识别次数达上限"),
        AI_QA_OVER_UPPER_LIMIT_0x0A((byte) 10, "问答次数达上限"),
        AI_DRAWING_OVER_UPPER_LIMIT_0x0B((byte) 11, "AI作画次数达上限"),
        NO_MICROPHONE_0x0C((byte) 12, "未找到蓝牙麦克风设备"),
        SYNCHRONIZING_DATA_0x0D((byte) 13, "设备上报ai数据时，app回复同Step Count据中"),
        APP_USE_AI_FUNCTION_0x0E((byte) 14, "app使用中"),
        REGION_NOT_SUPPORTED_0x0F((byte) 15, "设备是国内或国外，但app不支持该地区时下发"),
        UNKNOWN((byte) 21, "未知错误");

        private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());
        private final byte code;

        @NotNull
        private final String des;

        AIAppErrorCode(byte b, String str) {
            this.code = b;
            this.des = str;
        }

        @NotNull
        public static EnumEntries<AIAppErrorCode> getEntries() {
            return vp_b;
        }

        public final byte getCode() {
            return this.code;
        }

        @NotNull
        public final String getDes() {
            return this.des;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt$AIDeviceErrorCode.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/veepoo/protocol/operate/AIFunctionOpt$AIDeviceErrorCode;", "", "code", "", "des", "", "<init>", "(Ljava/lang/String;IBLjava/lang/String;)V", "getCode", "()B", "getDes", "()Ljava/lang/String;", "SUCCESS", "PACKET_LOSS_OR_PREVIEW_CHECK_FAILED_0x80", "DATA_RECEIVER_TIMEOUT_0x81", "UNKNOWN", "Companion", "vpprotocol_release"})
    public enum AIDeviceErrorCode {
        SUCCESS((byte) 0, "成功"),
        PACKET_LOSS_OR_PREVIEW_CHECK_FAILED_0x80((byte) -128, "设备端发现丢包or预览图校验失败"),
        DATA_RECEIVER_TIMEOUT_0x81((byte) -127, "数据接受超时"),
        UNKNOWN((byte) 21, "未知错误");

        private final byte code;

        @NotNull
        private final String des;
        private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: com.veepoo.protocol.operate.AIFunctionOpt$AIDeviceErrorCode$vp_a, reason: from kotlin metadata */
        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt$AIDeviceErrorCode$vp_a.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final AIDeviceErrorCode vp_a(byte b) {
                AIDeviceErrorCode aIDeviceErrorCode;
                AIDeviceErrorCode[] aIDeviceErrorCodeArrValues = AIDeviceErrorCode.values();
                int length = aIDeviceErrorCodeArrValues.length - 1;
                int i = length;
                if (length < 0) {
                    aIDeviceErrorCode = null;
                    break;
                }
                while (true) {
                    int i2 = i;
                    i = i2 - 1;
                    AIDeviceErrorCode aIDeviceErrorCode2 = aIDeviceErrorCodeArrValues[i2];
                    aIDeviceErrorCode = aIDeviceErrorCode2;
                    if (aIDeviceErrorCode2.getCode() == b) {
                        break;
                    }
                    if (i < 0) {
                        aIDeviceErrorCode = null;
                        break;
                    }
                }
                if (aIDeviceErrorCode == null) {
                    aIDeviceErrorCode = AIDeviceErrorCode.UNKNOWN;
                }
                return aIDeviceErrorCode;
            }
        }

        AIDeviceErrorCode(byte b, String str) {
            this.code = b;
            this.des = str;
        }

        @NotNull
        public static EnumEntries<AIDeviceErrorCode> getEntries() {
            return vp_b;
        }

        public final byte getCode() {
            return this.code;
        }

        @NotNull
        public final String getDes() {
            return this.des;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AIFunctionOpt vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/AIFunctionOpt$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final AIFunctionOpt vp_b = new AIFunctionOpt();

        private vp_b() {
        }

        public final AIFunctionOpt vp_a() {
            return vp_b;
        }
    }

    private final byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private final byte[] vp_b() {
        byte[] bArr = new byte[20];
        bArr[0] = -24;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -14;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = -1;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        OnAIQAOptListener onAIQAOptListener;
        Intrinsics.checkNotNullParameter(bArr, "byteArray");
        super.handler(bArr);
        byte b = bArr[2];
        byte b2 = bArr[1];
        byte b3 = bArr[3];
        if (b3 == -16) {
            Log.e("-AI功能- =协议处理=", "收到AI配置信息上报[" + b2 + " / " + b + "] : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            if (b2 == 1) {
                this.vp_g.clear();
            }
            this.vp_g.add(bArr);
            if (b2 == b) {
                Log.v("-AI功能- =协议处理=", "收到AI配置信息上报,接受完成开始解析数据---> START");
                AIDeviceConfigBean aIDeviceConfigBeanVp_a = vp_a(this.vp_g);
                this.vp_f = aIDeviceConfigBeanVp_a;
                Log.e("-AI功能- =协议处理=", "解析设备AI配置信息：" + aIDeviceConfigBeanVp_a);
                Log.v("-AI功能- =协议处理=", "收到AI配置信息上报,接受完成开始解析数据---> End");
                OnAIConfigOptListener onAIConfigOptListener = this.vp_c;
                if (onAIConfigOptListener != null) {
                    onAIConfigOptListener.onAIDeviceConfigReport(aIDeviceConfigBeanVp_a);
                    return;
                }
                return;
            }
            return;
        }
        if (b3 == -15) {
            boolean z = bArr[6] == 0;
            Log.v("-AI功能- =协议处理=", "收到AI配置设置结果 :" + (z ? "设置成功！" : "设置失败"));
            OnAIConfigOptListener onAIConfigOptListener2 = this.vp_c;
            if (onAIConfigOptListener2 != null) {
                onAIConfigOptListener2.onAIDeviceConfigSettingResult(z);
                return;
            }
            return;
        }
        if (b3 == -14) {
            Log.e("-AI功能- =协议处理=", "收到AI配置信息读取回复[" + b2 + " / " + b + "] : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            if (b2 == 1) {
                this.vp_g.clear();
            }
            this.vp_g.add(bArr);
            if (b2 == b) {
                Log.v("-AI功能- =协议处理=", "收到AI配置信息读取回复,接受完成开始解析数据---> START");
                AIDeviceConfigBean aIDeviceConfigBeanVp_a2 = vp_a(this.vp_g);
                this.vp_f = aIDeviceConfigBeanVp_a2;
                Log.v("-AI功能- =协议处理=", "收到AI配置信息读取回复,接受完成开始解析数据---> End");
                OnAIConfigOptListener onAIConfigOptListener3 = this.vp_c;
                if (onAIConfigOptListener3 != null) {
                    onAIConfigOptListener3.onAIDeviceConfigRead(aIDeviceConfigBeanVp_a2);
                    return;
                }
                return;
            }
            return;
        }
        if (b3 == -96) {
            Log.e("-AI功能- =协议处理=", "【智能问答】>>>收到开始录音上报指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIQAOptListener onAIQAOptListener2 = this.vp_d;
            if (onAIQAOptListener2 != null) {
                onAIQAOptListener2.onAIQAStartRecording();
                return;
            }
            return;
        }
        if (b3 == -95) {
            Log.e("-AI功能- =协议处理=", "【智能问答】>>>收到停止录音上报指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIQAOptListener onAIQAOptListener3 = this.vp_d;
            if (onAIQAOptListener3 != null) {
                onAIQAOptListener3.onAIQAStopRecording();
                return;
            }
            return;
        }
        if (b3 == -94) {
            AIDeviceErrorCode aIDeviceErrorCodeVp_a = AIDeviceErrorCode.INSTANCE.vp_a(bArr[6]);
            Log.e("-AI功能- =协议处理=", "【智能问答】>>>收到【AI问答】结果上报指令 " + aIDeviceErrorCodeVp_a + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIQAOptListener onAIQAOptListener4 = this.vp_d;
            if (onAIQAOptListener4 != null) {
                onAIQAOptListener4.onAIQAResult(aIDeviceErrorCodeVp_a);
                return;
            }
            return;
        }
        if (b3 == -93) {
            Log.e("-AI功能- =协议处理=", "【智能问答】>>>收到【AI问答】需要重新生成的指令: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIQAOptListener onAIQAOptListener5 = this.vp_d;
            if (onAIQAOptListener5 != null) {
                onAIQAOptListener5.onAIQARegenerate();
                return;
            }
            return;
        }
        if (b3 == -92) {
            boolean z2 = bArr[6] == 1;
            boolean z3 = z2;
            Log.e("-AI功能- =协议处理=", "【智能问答】>>>收到【终止AI " + (z2 ? "请求" : "确认") + "】: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            if (!z3 || (onAIQAOptListener = this.vp_d) == null) {
                return;
            }
            onAIQAOptListener.onAIQATerminateREQ();
            return;
        }
        if (b3 == -80) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到开始录音上报指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener = this.vp_e;
            if (onAIDialOptListener != null) {
                onAIDialOptListener.onAIDialStartRecording();
                return;
            }
            return;
        }
        if (b3 == -79) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到停止录音上报指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener2 = this.vp_e;
            if (onAIDialOptListener2 != null) {
                onAIDialOptListener2.onAIDialStopRecording();
                return;
            }
            return;
        }
        if (b3 == -78) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到开始生成表盘指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener3 = this.vp_e;
            if (onAIDialOptListener3 != null) {
                onAIDialOptListener3.onAIDialStartGenerate();
                return;
            }
            return;
        }
        if (b3 == -77) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到表盘进度设置应答指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener4 = this.vp_e;
            if (onAIDialOptListener4 != null) {
                onAIDialOptListener4.onAIDialProgressSetting();
                return;
            }
            return;
        }
        if (b3 == -76) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到表盘结果设置应答指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener5 = this.vp_e;
            if (onAIDialOptListener5 != null) {
                onAIDialOptListener5.onAIDialResultSettingACK();
                return;
            }
            return;
        }
        if (b3 == -75) {
            int iVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr, 6);
            AiDialPreviewManager.getInstance().onPreviewLengthGetACK(iVp_a);
            OnAIDialOptListener onAIDialOptListener6 = this.vp_e;
            if (onAIDialOptListener6 != null) {
                onAIDialOptListener6.onAIDialPreviewLengthGetACK(iVp_a);
                return;
            }
            return;
        }
        if (b3 == -74) {
            int iVp_a2 = com.veepoo.protocol.util.vp_g.vp_a(bArr, 6);
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>继续获取AI表盘预览图数据指令 传输长度:" + iVp_a2 + " ,指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            AiDialPreviewManager.getInstance().onContinueGetPreviewDataREQ(iVp_a2);
            OnAIDialOptListener onAIDialOptListener7 = this.vp_e;
            if (onAIDialOptListener7 != null) {
                onAIDialOptListener7.onAIDialContinueGetPreviewDataREQ(iVp_a2);
                return;
            }
            return;
        }
        if (b3 == -73) {
            AIDeviceErrorCode aIDeviceErrorCodeVp_a2 = AIDeviceErrorCode.INSTANCE.vp_a(bArr[6]);
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到AI表盘预览图传输结束回复, " + aIDeviceErrorCodeVp_a2 + " : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            AiDialPreviewManager.getInstance().onPreviewDataReceiveComplete(aIDeviceErrorCodeVp_a2);
            OnAIDialOptListener onAIDialOptListener8 = this.vp_e;
            if (onAIDialOptListener8 != null) {
                onAIDialOptListener8.onAIDialPreviewDataReceiveComplete(aIDeviceErrorCodeVp_a2);
                return;
            }
            return;
        }
        if (b3 == -72) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到设为表盘指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener9 = this.vp_e;
            if (onAIDialOptListener9 != null) {
                onAIDialOptListener9.onAIDialSetDial();
                return;
            }
            return;
        }
        if (b3 == -71) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到重新生成表盘指令 : " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener10 = this.vp_e;
            if (onAIDialOptListener10 != null) {
                onAIDialOptListener10.onAIDialRegenerate();
                return;
            }
            return;
        }
        if (b3 == -70) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】>>>收到【终止AI表盘 " + (bArr[6] == 1 ? "请求" : "确认") + "】: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            OnAIDialOptListener onAIDialOptListener11 = this.vp_e;
            if (onAIDialOptListener11 != null) {
                onAIDialOptListener11.onAIDialTerminateREQ();
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_i(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_i(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_b = vp_b();
        Log.e("-AI功能- =协议处理=", "读取【所有】AI配置信息: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_b));
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode) {
        Intrinsics.checkNotNullParameter(aIAppErrorCode, "errorCode");
        super.vp_e(bluetoothClient, str, bleWriteResponse, aIAppErrorCode);
        byte[] bArr = {-24, 1, 1, -96, 1, 0, aIAppErrorCode.getCode()};
        Log.e("-AI功能- =协议处理=", "【开始录音】结果的命令 " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_av(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_av(bluetoothClient, str, bleWriteResponse);
        Log.e("-AI功能- =协议处理=", "【AI表盘】功能不可用");
        byte[] bArr = {-24, 1, 1, -80, 1, 0, AIAppErrorCode.REGION_NOT_SUPPORTED_0x0F.getCode()};
        Log.e("-AI功能- =协议处理=", "【AI表盘】功能不可用命令: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode) {
        Intrinsics.checkNotNullParameter(aIAppErrorCode, "appErrorCode");
        super.vp_d(bluetoothClient, str, bleWriteResponse, aIAppErrorCode);
        byte[] bArr = {-24, 1, 1, -80, 1, 0, aIAppErrorCode.getCode()};
        Log.e("-AI功能- =协议处理=", "【AI表盘-开始录音】结果的命令 " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, aIAppErrorCode);
        byte[] bArr = new byte[7];
        bArr[0] = -24;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -78;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = aIAppErrorCode != null ? aIAppErrorCode.getCode() : (byte) 0;
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_aw(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_aw(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(false);
        Log.v("-AI功能- =协议处理=", "【AI表盘-终止确认】: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    private final byte[] vp_a(byte b, int i, int i2, int i3, byte[] bArr) {
        int i4 = i3 <= 20 ? 20 : i3;
        byte[] bArr2 = new byte[i4];
        bArr2[0] = -24;
        bArr2[1] = (byte) i;
        bArr2[2] = (byte) i2;
        bArr2[3] = b;
        for (int i5 = 4; i5 < i4; i5++) {
            int i6 = (((i3 - 4) * (i - 1)) + i5) - 4;
            if (i6 < bArr.length) {
                bArr2[i5] = bArr[i6];
            } else {
                bArr2[i5] = 0;
            }
        }
        return bArr2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode, String str2) {
        Intrinsics.checkNotNullParameter(aIAppErrorCode, "errorCode");
        Intrinsics.checkNotNullParameter(str2, "content");
        super.vp_d(bluetoothClient, str, bleWriteResponse, aIAppErrorCode, str2);
        AIAppErrorCode aIAppErrorCode2 = AIAppErrorCode.SUCCESS;
        if (aIAppErrorCode != aIAppErrorCode2 || TextUtils.isEmpty(str2)) {
            Log.e("-AI功能- =协议处理=", "【AI问答】失败");
            byte[] bArr = new byte[7];
            bArr[0] = -24;
            bArr[1] = 1;
            bArr[2] = 1;
            bArr[3] = -94;
            bArr[4] = 1;
            bArr[5] = 0;
            if (aIAppErrorCode == aIAppErrorCode2) {
                bArr[6] = AIAppErrorCode.AI_QA_FAILED_0x06.getCode();
            } else {
                bArr[6] = aIAppErrorCode.getCode();
            }
            Log.e("-AI功能- =协议处理=", "【AI问答】失败的命令, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length + 3;
        byte[] bArr2 = new byte[length];
        byte[] bArrVp_a = com.veepoo.protocol.util.vp_g.vp_a(bytes.length + 1);
        bArr2[0] = bArrVp_a[1];
        bArr2[1] = bArrVp_a[0];
        bArr2[2] = aIAppErrorCode.getCode();
        int i = 0;
        for (byte b : bytes) {
            int i2 = i;
            i++;
            bArr2[i2 + 3] = b;
        }
        int i3 = (length / 16) + (length % 16 == 0 ? 0 : 1);
        Log.e("-AI功能- =协议处理=", "【AI问答】 一共需要发送" + i3 + " 包数据， 内容：" + str2 + "\n byte内容：" + com.veepoo.protocol.util.vp_g.vp_d(bytes));
        int i4 = 1;
        if (1 > i3) {
            return;
        }
        while (true) {
            int i5 = i4;
            byte[] bArrVp_a2 = vp_a((byte) -94, i4, i3, 20, bArr2);
            Log.v("-AI功能- =协议处理=", "【AI问答】：第[" + i4 + " / " + i3 + "]包: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
            if (i5 == i3) {
                return;
            } else {
                i4++;
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode, String str2) {
        Intrinsics.checkNotNullParameter(aIAppErrorCode, "errorCode");
        Intrinsics.checkNotNullParameter(str2, "content");
        super.vp_c(bluetoothClient, str, bleWriteResponse, aIAppErrorCode, str2);
        if (aIAppErrorCode != AIAppErrorCode.SUCCESS || TextUtils.isEmpty(str2)) {
            Log.e("-AI功能- =协议处理=", "【AI问答】失败");
            byte[] bArr = {-24, 1, 1, -93, 1, 0, aIAppErrorCode.getCode()};
            Log.e("-AI功能- =协议处理=", "【AI问答-重新生成】失败的命令, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length + 3;
        byte[] bArr2 = new byte[length];
        byte[] bArrVp_a = com.veepoo.protocol.util.vp_g.vp_a(bytes.length + 1);
        bArr2[0] = bArrVp_a[1];
        bArr2[1] = bArrVp_a[0];
        bArr2[2] = aIAppErrorCode.getCode();
        int i = 0;
        for (byte b : bytes) {
            int i2 = i;
            i++;
            bArr2[i2 + 3] = b;
        }
        int i3 = (length / 16) + (length % 16 == 0 ? 0 : 1);
        Log.e("-AI功能- =协议处理=", "【AI问答-重新生成】 一共需要发送" + i3 + " 包数据， 内容：" + str2 + "\n byte内容：" + com.veepoo.protocol.util.vp_g.vp_d(bytes));
        int i4 = 1;
        if (1 > i3) {
            return;
        }
        while (true) {
            int i5 = i4;
            byte[] bArrVp_a2 = vp_a((byte) -93, i4, i3, 20, bArr2);
            Log.v("-AI功能- =协议处理=", "【AI问答-重新生成】：第[" + i4 + " / " + i3 + "]包: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
            if (i5 == i3) {
                return;
            } else {
                i4++;
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, aIAppErrorCode);
        byte[] bArr = new byte[7];
        bArr[0] = -24;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -73;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = aIAppErrorCode != null ? aIAppErrorCode.getCode() : (byte) 0;
        Log.e("-AI功能- =协议处理=", "【AI表盘-预览图数据传输完成应答】, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final byte[] vp_a(boolean z) {
        return new byte[]{-24, 1, 1, -92, 1, 0, z};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode, String str2) {
        Intrinsics.checkNotNullParameter(aIAppErrorCode, "errorCode");
        super.vp_b(bluetoothClient, str, bleWriteResponse, aIAppErrorCode, str2);
        if (aIAppErrorCode != AIAppErrorCode.SUCCESS || TextUtils.isEmpty(str2)) {
            Log.e("-AI功能- =协议处理=", "【AI问答-录音结果解析】解析失败");
            byte[] bArr = new byte[20];
            bArr[0] = -24;
            bArr[1] = 1;
            bArr[2] = 1;
            bArr[3] = -95;
            bArr[4] = 1;
            bArr[5] = 0;
            bArr[6] = aIAppErrorCode.getCode();
            Log.e("-AI功能- =协议处理=", "【AI问答-录音结果解析】失败的命令, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        Intrinsics.checkNotNull(str2);
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length + 3;
        byte[] bArr2 = new byte[length];
        byte[] bArrVp_a = com.veepoo.protocol.util.vp_g.vp_a(bytes.length + 1);
        bArr2[0] = bArrVp_a[1];
        bArr2[1] = bArrVp_a[0];
        bArr2[2] = aIAppErrorCode.getCode();
        int i = 0;
        for (byte b : bytes) {
            int i2 = i;
            i++;
            bArr2[i2 + 3] = b;
        }
        int i3 = (length / 16) + (length % 16 == 0 ? 0 : 1);
        Log.e("-AI功能- =协议处理=", "【AI问答-录音结果解析】 一共需要发送" + i3 + " 包数据， 内容：" + str2 + "\n byte内容：" + com.veepoo.protocol.util.vp_g.vp_d(bytes));
        int i4 = 1;
        if (1 > i3) {
            return;
        }
        while (true) {
            int i5 = i4;
            byte[] bArrVp_a2 = vp_a((byte) -95, i4, i3, 20, bArr2);
            Log.v("-AI功能- =协议处理=", "【AI问答-录音结果解析】：第[" + i4 + " / " + i3 + "]包: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
            if (i5 == i3) {
                return;
            } else {
                i4++;
            }
        }
    }

    private final byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(byteBuffer.get(), byteBuffer.get());
        int iPosition = byteBuffer.position();
        int i = iPosition + iVp_b;
        if (i > bArr.length) {
            return null;
        }
        byteBuffer.position(i);
        vp_a(bArr, iVp_b, iPosition);
        return vp_a(bArr, iVp_b, iPosition);
    }

    public final AIDeviceConfigBean vp_a() {
        return this.vp_f;
    }

    private final AIDeviceConfigBean vp_a(List list) {
        AIDeviceConfigBean aIDeviceConfigBean = this.vp_f;
        AIDeviceConfigBean aIDeviceConfigBean2 = aIDeviceConfigBean;
        if (aIDeviceConfigBean == null) {
            aIDeviceConfigBean2 = aIDeviceConfigBean;
            AIDeviceConfigBean aIDeviceConfigBean3 = new AIDeviceConfigBean();
        }
        if (list.isEmpty() || ((byte[]) list.get(0)).length < 6) {
            return aIDeviceConfigBean2;
        }
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(((byte[]) list.get(0))[5], ((byte[]) list.get(0))[4]);
        int i = iVp_b + 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        for (Object obj : list) {
            int i4 = i3;
            i3++;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int i5 = 0;
            for (byte b : (byte[]) obj) {
                int i6 = i5;
                i5++;
                if (i6 >= 4 && i2 < i) {
                    bArr[i2] = b;
                    i2++;
                }
            }
        }
        Log.e("-AI功能- =协议处理=", "AI配置数据：【-" + iVp_b + "-】" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.position(2);
        Log.e("-AI功能- =协议处理=", "AI配置数据：【-长度" + iVp_b + "-】" + com.veepoo.protocol.util.vp_g.vp_d(bArr) + "  数据长度 = " + i + " 当前的position = " + byteBufferOrder.position());
        boolean z = true;
        while (byteBufferOrder.position() < i && z) {
            z = true;
            byte b2 = byteBufferOrder.get();
            if (b2 == 1) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a = vp_a(byteBufferOrder, "0x01", bArr);
                if (bArrVp_a != null) {
                    aIDeviceConfigBean2.setAiQAContentMaxLength(com.veepoo.protocol.util.vp_g.vp_a(bArrVp_a));
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：值域最大长度 = " + aIDeviceConfigBean2.getAiQAContentMaxLength() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
            } else if (b2 == 2) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0x02", bArr);
                if (bArrVp_a2 != null) {
                    aIDeviceConfigBean2.setPreviewMaxSize(com.veepoo.protocol.util.vp_g.vp_a(bArrVp_a2));
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：预览图最大大小 = " + aIDeviceConfigBean2.getPreviewMaxSize() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            } else if (b2 == 3) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a3 = vp_a(byteBufferOrder, "0x03", bArr);
                if (bArrVp_a3 != null) {
                    aIDeviceConfigBean2.setDialMaxSize(com.veepoo.protocol.util.vp_g.vp_a(bArrVp_a3));
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：表盘最大大小 = " + aIDeviceConfigBean2.getDialMaxSize() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a3));
            } else if (b2 == 4) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a4 = vp_a(byteBufferOrder, "0x04", bArr);
                if (bArrVp_a4 != null) {
                    aIDeviceConfigBean2.setAiDrawingStyle(bArrVp_a4[0]);
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：文生图风格 = " + aIDeviceConfigBean2.getAiDrawingStyle() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a4));
            } else if (b2 == 5) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a5 = vp_a(byteBufferOrder, "0x05", bArr);
                if (bArrVp_a5 != null && bArrVp_a5.length == 4) {
                    AIDeviceConfigBean aIDeviceConfigBean4 = aIDeviceConfigBean2;
                    int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a5[1], bArrVp_a5[0]);
                    aIDeviceConfigBean4.setPreviewResolutionH(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a5[3], bArrVp_a5[2]));
                    aIDeviceConfigBean4.setPreviewResolutionW(iVp_b2);
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：预览图分辨率 = " + aIDeviceConfigBean2.getPreviewResolutionW() + " X " + aIDeviceConfigBean2.getPreviewResolutionH() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a5));
            } else if (b2 == 6) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a6 = vp_a(byteBufferOrder, "0x06", bArr);
                if (bArrVp_a6 != null) {
                    aIDeviceConfigBean2.setPreviewAngleRadius(com.veepoo.protocol.util.vp_g.vp_g(bArrVp_a6));
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：预览图风格 = " + aIDeviceConfigBean2.getPreviewAngleRadius() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a6));
            } else if (b2 == 7) {
                Intrinsics.checkNotNull(byteBufferOrder);
                byte[] bArrVp_a7 = vp_a(byteBufferOrder, "0x07", bArr);
                if (bArrVp_a7 != null) {
                    aIDeviceConfigBean2.setCustomDialFlag(bArrVp_a7[0]);
                }
                Log.i("-AI功能- =协议处理=", "解析配置信息：照片表盘标志位 = " + aIDeviceConfigBean2.getCustomDialFlag() + "  data = " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a7));
            } else {
                z = false;
            }
        }
        return aIDeviceConfigBean2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode, int i, int i2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, aIAppErrorCode, i, i2);
        if (aIAppErrorCode == AIAppErrorCode.SUCCESS) {
            Log.v("-AI功能- =协议处理=", "【AI表盘-生成结果】成功,发送预览图长度：" + i + " crc：" + i2);
            byte[] bArrVp_b = com.veepoo.protocol.util.vp_g.vp_b(i);
            byte[] bArrVp_a = com.veepoo.protocol.util.vp_g.vp_a(i2);
            byte[] bArr = {-24, 1, 1, -76, 7, 0, aIAppErrorCode.getCode(), bArrVp_b[0], bArrVp_b[1], bArrVp_b[2], bArrVp_b[3], bArrVp_a[1], bArrVp_a[0]};
            Log.e("-AI功能- =协议处理=", "【AI表盘-生成结果】, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        byte[] bArr2 = new byte[7];
        bArr2[0] = -24;
        bArr2[1] = 1;
        bArr2[2] = 1;
        bArr2[3] = -76;
        bArr2[4] = 1;
        bArr2[5] = 0;
        bArr2[6] = aIAppErrorCode != null ? aIAppErrorCode.getCode() : (byte) 0;
        Log.e("-AI功能- =协议处理=", "【AI表盘-生成结果】失败, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr2));
        send(bArr2, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, aIAppErrorCode);
        byte[] bArr = new byte[7];
        bArr[0] = -24;
        bArr[1] = 1;
        bArr[2] = 1;
        bArr[3] = -75;
        bArr[4] = 1;
        bArr[5] = 0;
        bArr[6] = aIAppErrorCode != null ? aIAppErrorCode.getCode() : (byte) 0;
        Log.e("-AI功能- =协议处理=", "【AI表盘-获取预览图长度应答】, " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AIAppErrorCode aIAppErrorCode, String str2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, aIAppErrorCode, str2);
        if (aIAppErrorCode != AIAppErrorCode.SUCCESS) {
            Log.e("-AI功能- =协议处理=", "【AI表盘】完成");
            byte[] bArr = new byte[7];
            bArr[0] = -24;
            bArr[1] = 1;
            bArr[2] = 1;
            bArr[3] = -79;
            bArr[4] = 1;
            bArr[5] = 0;
            bArr[6] = aIAppErrorCode != null ? aIAppErrorCode.getCode() : (byte) 0;
            Log.e("-AI功能- =协议处理=", "【AI表盘-录制完成】 " + aIAppErrorCode + ": " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Intrinsics.checkNotNull(str2);
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        int length = bytes.length + 3;
        byte[] bArr2 = new byte[length];
        byte[] bArrVp_a = com.veepoo.protocol.util.vp_g.vp_a(bytes.length + 1);
        bArr2[0] = bArrVp_a[1];
        bArr2[1] = bArrVp_a[0];
        bArr2[2] = aIAppErrorCode.getCode();
        int i = 0;
        for (byte b : bytes) {
            int i2 = i;
            i++;
            bArr2[i2 + 3] = b;
        }
        int i3 = (length / 16) + (length % 16 == 0 ? 0 : 1);
        Log.e("-AI功能- =协议处理=", "【AI表盘-录制完成】 一共需要发送" + i3 + " 包数据， 内容：" + str2 + "\n byte内容：" + com.veepoo.protocol.util.vp_g.vp_d(bytes));
        int i4 = 1;
        if (1 > i3) {
            return;
        }
        while (true) {
            int i5 = i4;
            byte[] bArrVp_a2 = vp_a((byte) -79, i4, i3, 20, bArr2);
            Log.v("-AI功能- =协议处理=", "【AI表盘-录制完成】：第[" + i4 + " / " + i3 + "]包: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
            if (i5 == i3) {
                return;
            } else {
                i4++;
            }
        }
    }

    public final void vp_a(OnAIConfigOptListener onAIConfigOptListener) {
        this.vp_c = onAIConfigOptListener;
    }

    public final void vp_a(OnAIDialOptListener onAIDialOptListener) {
        this.vp_e = onAIDialOptListener;
    }

    public final void vp_a(OnAIQAOptListener onAIQAOptListener) {
        this.vp_d = onAIQAOptListener;
    }

    public final void vp_a(AIDeviceConfigBean aIDeviceConfigBean) {
        this.vp_f = aIDeviceConfigBean;
    }
}
