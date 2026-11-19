package com.timaimee.vpdemo.activity.v2.other;

import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.jieli.RcspAuthManager;
import com.inuker.bluetooth.library.jieli.dial.JLWatchFaceManager;
import com.inuker.bluetooth.library.jieli.response.RcspAuthResponse;
import com.jieli.jl_fatfs.model.FatFile;
import com.jieli.jl_rcsp.model.base.BaseError;
import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.CustomProgressDialog;
import com.timaimee.vpdemo.activity.v2.BaseVPBLETestActivity;
import com.veepoo.protocol.AiDialPreviewManager;
import com.veepoo.protocol.customui.WatchUIType;
import com.veepoo.protocol.listener.data.IAiRecordListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoListener;
import com.veepoo.protocol.listener.data.IUiUpdateListener;
import com.veepoo.protocol.listener.data.OnAIConfigOptListener;
import com.veepoo.protocol.listener.data.OnAIDialOptListener;
import com.veepoo.protocol.listener.data.OnAIQAOptListener;
import com.veepoo.protocol.listener.data.OnAiDialPreviewSendListener;
import com.veepoo.protocol.listener.data.OnOpusDecode2PcmListener;
import com.veepoo.protocol.model.datas.AIDeviceConfigBean;
import com.veepoo.protocol.model.datas.UICustomSetData;
import com.veepoo.protocol.model.datas.UIDataCustom;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.model.enums.EUiUpdateError;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.operate.AIFunctionOpt;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.UiUpdateUtil;
import com.veepoo.protocol.util.ai.AiCropWatchFaceBitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: SDK Demo
 * Description: AI 功能示例（AI问答 / AI表盘）
 * <p>
 * 说明：
 * 1. AI 问答/表盘的交互流程由【设备端发起】（开始录音→结束录音→结果/Start generation, etc.), the app side responds in the callback；
 * 2. When recording on the device, the recording data (Opus-encoded）分包返回，通过 IAiRecordListener Receive and save as an opus file，
 *    Then call aiOpus2Pcm to decode to pcm；
 * 3. 语音转文字、AI 问答、AI Customers need to connect to third parties for cloud capabilities such as drawing. SDK，
 *    This example uses fixed text ("This isaiQuestion in the Q&A " / " This isaiThe answer in the Q&A is "/" This isai表盘的描述"）
 *    Demonstrate the complete link with a fixed local image (assets/custom_rect_320_380_bg.png)；
 * 4. AI 表盘预览图传输（裁剪/转换/CRC/分包/设备请求应答）由 SDK sendAiDialPreview auto-completion for。
 */
public class AIFunActivity extends BaseVPBLETestActivity implements IAiRecordListener {

    private static final String TAG = "AIFunActivity";

    /** AI Text for fixed Q&A questions (customers need to replace it with speech recognition results)） */
    private static final String AI_QA_QUESTION = "这是ai问答中的问题";
    /** AI Q&A fixed answer text (to be replaced by the customer with the cloud version) AI 返回结果） */
    private static final String AI_QA_ANSWER = "This is the AI answer in Q&A. This is the answer in the AI Q&A. This isai问答中的答案";
    /** AI Fixed description text for the watch face (customer needs to replace it with the speech recognition result)） */
    private static final String AI_DIAL_DESCRIPTION = "这是ai表盘的描述";
    /** AI Watch face fixed image generation (under assets） */
    private static final String AI_DIAL_IMAGE_ASSET = "img_push2.jpg";

    private TextView tvAiQaSupport;
    private TextView tvAiDialSupport;
    private TextView tvLog;
    private ScrollView svLog;

    /** File path of the currently saved opus recording（Sub-packet writing of audio data returned by the device） */
    private String aiRecordOpusPath;
    /** 当前录音输出流 */
    private BufferedOutputStream aiRecordFileStream;
    /** Large image saved when the preview image transfer is complete (used when the device reports it as a watch face)，参照 GBand2 setAIDial2WatchFace） */
    private AiCropWatchFaceBitmap aiBigWatchFace;
    /** Path of the preview image to be sent (continue sending after successful readAiConfig callback)，避免"配置为空"校验失败） */
    private String pendingAiDialPreviewPath;

    @Override
    public int getLayoutID() {
        return R.layout.activity_aifun;
    }

    @Override
    public String pageTitle() {
        return "AI功能";
    }

    @Override
    public void initView() {
        tvAiQaSupport = findViewById(R.id.tvAiQaSupport);
        tvAiDialSupport = findViewById(R.id.tvAiDialSupport);
        tvLog = findViewById(R.id.tvLog);
        svLog = findViewById(R.id.svLog);
        //loadingDialog Must be created after onCreate（Access system services during construction; an exception will be thrown during the field initialization phase
        //"System services not available to Activities before onCreate()"）
        loadingDialog = new CustomProgressDialog(this);
    }

    @Override
    public void initData() {
        //1. Show whether the device supports AI Q&A / AI 表盘
        boolean supportAiQa = VpSpGetUtil.getVpSpVariInstance(this).isSupportAiQa();
        boolean supportAiDial = VpSpGetUtil.getVpSpVariInstance(this).isSupportAiDial();
        tvAiQaSupport.setText("AIQ&A Support: " + (supportAiQa ? "✅Supported" : "❌不支持"));
        tvAiDialSupport.setText("AIWatch face support: "+ (supportAiDial ? "✅Supported" : "❌不支持"));
        appendLog("AIQ&A Support=" + supportAiQa + ", AI Watch Face Support=" + supportAiDial);

        //2. Initialize UiUpdateUtil (ReadUI信息/设置表盘依赖其 context，Failure to initialize will result in a null pointer for getMangerInstance(null)）
        UiUpdateUtil.getInstance().init(this);

        //3. 注册 AI 功能监听（配置/问答/表盘），设备上报的 AI 指令才会回调到 App
        //   （JL Device preview image transmission is implemented natively within the SDK，客户无需注入）
        vpBleManager.setAiListener(aiConfigListener, aiqaListener, aiDialListener);
        appendLog("已注册AI功能监听（setAiListener），等待设备发起AI流程...");

        //4. JL（杰理）设备且支持 AI Watch Face: Check Jelly Notification and Device Authentication，未就绪则打开（参照 JLDeviceOPTActivity）
        if (supportAiDial && VpSpGetUtil.getVpSpVariInstance(this).isJieLiDevice()) {
            checkJLReady();
        }
    }

    //======================================================================
    // JL（杰理）设备：打开通知 + 设备认证（参照 JLDeviceOPTActivity），
    // Completed upon entering the page. AI watch face preview image transmission.（SDK 内置实现）假定已就绪
    //======================================================================
    // 注意：不能在字段初始化处 new（Activity Accessing system services during the construction phase will cause a crash.），
    // Unified in initView() (after onCreate)）创建
    private CustomProgressDialog loadingDialog;

    private void checkJLReady() {
        boolean notifyOpened = vpBleManager.isJLNotifyOpened();
        boolean authPass = RcspAuthManager.getInstance().isAuthPass();
        if (notifyOpened && authPass) {
            appendLog("【JL】通知已打开、Device authentication passed → Check file system");
            initJLFileSystem();
            return;
        }
        appendLog("【JL】Notifications=" + (notifyOpened ? "Enabled" : "Not enabled") + ", Authentication=" + (authPass ? "已通过" : "未通过") + " → 打开通知并认证");
        loadingDialog.showNoTips();
        if (notifyOpened) {
            startJLAuth();
        } else {
            vpBleManager.openJLDataNotify(new BleNotifyResponse() {
                @Override
                public void onNotify(java.util.UUID service, java.util.UUID character, byte[] value) {
                }

                @Override
                public void onResponse(int code) {
                    appendLog("【JL】Notifications enabled → Start device authentication");
                    startJLAuth();
                }
            });
        }
    }

    private void startJLAuth() {
        if (RcspAuthManager.getInstance().isAuthPass()) {
            appendLog("【JL】Device authentication passed → Check file system");
            initJLFileSystem();
            return;
        }
        vpBleManager.startJLDeviceAuth(new RcspAuthResponse() {
            @Override
            public void onRcspAuthStart() {
            }

            @Override
            public void onRcspAuthSuccess() {
                appendLog("【JL】Device authentication successful → Check file system");
                initJLFileSystem();
            }

            @Override
            public void onRcspAuthFailed() {
                loadingDialog.disMissDialog();
                appendLog("【JL】设备认证失败");
            }
        });
    }

    /**
     * 初始化 JL 文件系统（WatchManager），AI Watch face preview image transfer (startTransferAIPreview) depends on it being ready；
     * 参照 JLDeviceOPTActivity.getJLFileSystem / GBand2 openJlFileSystem。
     * When not initialized, the log will report [checkWatchManagerIsInit] Watch system has not been initialized → Transmission failed。
     */
    private void initJLFileSystem() {
        JLWatchFaceManager jlManager = JLWatchFaceManager.getInstance();
        if (jlManager.isJLFatFileSystemInitSuccess()) {
            appendLog("【JL】The file system is ready for immediate use. AI 表盘");
            loadingDialog.disMissDialog();
            return;
        }
        appendLog("【JL】File system not initialized → checkJLSDKAndInit (Initialize WatchManager 并获取表盘列表）");
        loadingDialog.showNoTips();
        jlManager.checkJLSDKAndInit(new JLWatchFaceManager.OnWatchDialInfoGetListener() {
            @Override
            public void onGettingWatchDialInfo() {
            }

            @Override
            public void onWatchDialInfoGetStart() {
            }

            @Override
            public void onWatchDialInfoGetComplete() {
                loadingDialog.disMissDialog();
                appendLog("【JL】File system initialization completed (watch face list retrieval process completed)）");
            }

            @Override
            public void onWatchDialInfoGetSuccess(List<FatFile> systemFatFiles, List<FatFile> serverFatFiles, FatFile picFatFile) {
                appendLog("【JL】File system initialization successful (watch face list retrieved successfully)）");
            }

            @Override
            public void onWatchDialInfoGetFailed(BaseError error) {
                loadingDialog.disMissDialog();
                appendLog("【JL】文件系统初始化失败: " + error);
            }
        });
    }

    @Override
    public void initEvent() {
        Button btnReadConfig = findViewById(R.id.btnReadConfig);
        btnReadConfig.setOnClickListener(v -> {
            appendLog("读取AI配置 readAiConfig...");
            vpBleManager.readAiConfig(defaultResponse);
        });
    }

    //======================================================================
    // AI 配置监听
    //======================================================================
    private final OnAIConfigOptListener aiConfigListener = new OnAIConfigOptListener() {
        @Override
        public void onAIDeviceConfigReport(AIDeviceConfigBean config) {
            appendLog("设备上报AI配置: " + config);
        }

        @Override
        public void onAIDeviceConfigSettingResult(boolean isSuccess) {
            appendLog("AI配置设置结果: " + isSuccess);
        }

        @Override
        public void onAIDeviceConfigRead(AIDeviceConfigBean config) {
            appendLog("读取AI配置成功: " + config);
            //AI Watch face preview image is waiting for configuration (in startSendAiDialPreview) readAiConfig 之后）→ 配置就绪，继续发送
            if (pendingAiDialPreviewPath != null) {
                String path = pendingAiDialPreviewPath;
                pendingAiDialPreviewPath = null;
                doSendAiDialPreview(path);
            }
        }
    };

    //======================================================================
    // AI 问答监听（设备发起 → App 应答，问题/答案使用固定文本演示）
    //======================================================================
    private final OnAIQAOptListener aiqaListener = new OnAIQAOptListener() {
        @Override
        public void onAIQAStartRecording() {
            appendLog("【AI问答】设备请求开始录音 → 注册录音监听并应答");
            //1. Prepare to receive recorded data returned by the device (opus sub-packet)），保存为 opus 文件
            prepareAiRecordFile("ai_qa.opus");
            UiUpdateUtil.getInstance().setAiRecordListener(AIFunActivity.this);
            //2. 应答设备：开始录音
            vpBleManager.sendAIQAStartRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS);
        }

        @Override
        public void onAIQAStopRecording() {
            appendLog("【AI问答】设备请求结束录音 → 取消监听，使用保存的 opus File calls aiOpus2Pcm for decoding");
            //1. Cancel audio monitoring and close the file stream（The audio data returned by the device has been fully saved as an opus file.）
            UiUpdateUtil.getInstance().setAiRecordListener(null);
            closeAiRecordFile();
            //2. Decode the Opus audio returned by the device to pcm，供语音识别使用
            String opusFilePath = aiRecordOpusPath;
            String pcmFilePath = new File(getExternalFilesDir(null), "ai_record/ai_qa.pcm").getAbsolutePath();
            vpBleManager.aiOpus2Pcm(opusFilePath, pcmFilePath, new OnOpusDecode2PcmListener() {
                @Override
                public void onDecodeComplete(String filePath) {
                    appendLog("【AI问答】opus转pcmCompleted: " + filePath + " → Send question text after speech recognition andAI答案");
                    //客户实际流程：此处对 pcm Perform speech recognition to obtain the question text (third party)SDK），
                    //Then call the cloud-based AI Q&A to get the answer；示例使用固定文本
                    //⚠️ 注意：问题与答案【Cannot send continuously and quickly] – SDK sub-packet sending does not wait for the device for each packet ACK，
                    //连续调用会把 问题2包+答案2包 在几毫秒内全部挤进 BLE Write channel; JL equipment cannot handle it.，
                    //Appears as "The problem shown on the device becomes the answer"、答案为空"。
                    //Correct approach: Wait for the device to process after sending the question./云端返回再发答案（参照 GBand2 云端异步时序）。
                    //The example uses a 1-second delay to simulate the time taken for cloud-based Q&A.：
                    vpBleManager.sendAIQARecordingContent2Device(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS, AI_QA_QUESTION);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        appendLog("【AI问答】发送答案: [" + AI_QA_ANSWER + "]");
                        vpBleManager.sendAIQAResultCmd(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS, AI_QA_ANSWER);
                    }, 1000);
                }

                @Override
                public void onDecodeFailed(String filePath) {
                    appendLog("【AI问答】opus转pcm失败 → 发送失败指令，避免设备一直等待");
                    vpBleManager.sendAIQARecordingContent2Device(defaultResponse, AIFunctionOpt.AIAppErrorCode.RECORDING_FAILED_0x04, "");
                }
            });
        }

        @Override
        public void onAIQAResult(AIFunctionOpt.AIDeviceErrorCode errorCode) {
            //Device reports Q&A results (device-side status)），For the record only; the answer is at the end of the recording.→opus转pcm完成后发送
            appendLog("【AI问答】设备上报问答结果: " + errorCode + "（仅记录，答案已发送）");
        }

        @Override
        public void onAIQARegenerate() {
            appendLog("【AI问答】设备请求重新生成 → 重新发送答案: [" + AI_QA_ANSWER + "]");
            vpBleManager.sendAIQARegenerateResultCmd(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS, AI_QA_ANSWER);
        }

        @Override
        public void onAIQATerminateREQ() {
            appendLog("【AI问答】设备请求终止AI问答，结束当前流程");
        }
    };

    //======================================================================
    // AI 表盘监听（设备发起 → App 应答，描述使用固定文本，表盘使用本地图片）
    //======================================================================
    private final OnAIDialOptListener aiDialListener = new OnAIDialOptListener() {
        @Override
        public void onAIDialStartRecording() {
            appendLog("【AI表盘】设备请求开始录音 → 注册录音监听并应答");
            //1. Prepare to receive recorded data returned by the device (opus sub-packet)），保存为 opus 文件
            prepareAiRecordFile("ai_dial.opus");
            UiUpdateUtil.getInstance().setAiRecordListener(AIFunActivity.this);
            //2. 应答设备：开始录音
            vpBleManager.sendAIDialStartRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS);
        }

        @Override
        public void onAIDialStopRecording() {
            appendLog("【AI表盘】设备请求结束录音 → 取消监听，使用保存的 opus File calls aiOpus2Pcm for decoding");
            //1. Cancel audio monitoring and close the file stream（The audio data returned by the device has been fully saved as an opus file.）
            UiUpdateUtil.getInstance().setAiRecordListener(null);
            closeAiRecordFile();
            //2. Decode the Opus audio returned by the device to pcm，Perform speech recognition to obtain the watch face description text
            String opusFilePath = aiRecordOpusPath;
            String pcmFilePath = new File(getExternalFilesDir(null), "ai_record/ai_dial.pcm").getAbsolutePath();
            vpBleManager.aiOpus2Pcm(opusFilePath, pcmFilePath, new OnOpusDecode2PcmListener() {
                @Override
                public void onDecodeComplete(String filePath) {
                    appendLog("【AI表盘】opus转pcmCompleted: " + filePath + " → Send watch face description after speech recognition");
                    //客户实际流程：此处对 pcm Perform speech recognition to obtain the watch face description text; the example uses fixed text.
                    vpBleManager.sendAIDialRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS, AI_DIAL_DESCRIPTION);
                }

                @Override
                public void onDecodeFailed(String filePath) {
                    appendLog("【AI表盘】opus转pcm失败 → 发送失败指令，避免设备一直等待");
                    vpBleManager.sendAIDialRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.RECORDING_FAILED_0x04, "");
                }
            });
        }

        @Override
        public void onAIDialStartGenerate() {
            appendLog("【AI表盘】设备请求开始生成 → Respond with sendAIDialStartGenerateACK(SUCCESS), use local image to generate watch face");
            vpBleManager.sendAIDialStartGenerateACK(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS);
            //Customer's actual process: The cloud is called here AI 绘图（文生图），After generation is complete, pass the original watch face image path to sendAiDialPreview；
            //This example uses a fixed local image directly.（assets/custom_rect_320_380_bg.png）
            startSendAiDialPreview();
        }

        @Override
        public void onAIDialProgressSetting() {
            appendLog("【AI表盘】生成进度设置应答（一般不用处理）");
        }

        @Override
        public void onAIDialResultSettingACK() {
            appendLog("【AI表盘】生成结果设置应答（一般不用处理）");
        }

        @Override
        public void onAIDialPreviewLengthGetACK(int needGetPreviewLength) {
            //Preview image transmission by SDK's sendAiDialPreview 自动应答，此处仅记录
            appendLog("【AI表盘】设备请求预览图长度=" + needGetPreviewLength + "（SDK自动应答）");
        }

        @Override
        public void onAIDialContinueGetPreviewDataREQ(int needGetPreviewLength) {
            appendLog("【AIWatch Face] Device requests to continue fetching preview images=" + needGetPreviewLength + "（SDK自动应答）");
        }

        @Override
        public void onAIDialPreviewDataReceiveComplete(AIFunctionOpt.AIDeviceErrorCode errorCode) {
            appendLog("【AIWatch Face] Device preview image data reception completed=" + errorCode + "（SDK自动应答）");
        }

        @Override
        public void onAIDialSetDial() {
            appendLog("【AI表盘】设备上报设为表盘 → 将大图设置为当前表盘");
            setAiDial2WatchFace();
        }

        @Override
        public void onAIDialRegenerate() {
            appendLog("【AI表盘】设备请求重新生成 → Resend preview image using local image");
            startSendAiDialPreview();
        }

        @Override
        public void onAIDialTerminateREQ() {
            appendLog("【AI表盘】设备请求终止 → 应答 sendAIDialTerminateACK");
            vpBleManager.sendAIDialTerminateACK(defaultResponse);
        }
    };

    //======================================================================
    // IAiRecordListener：Receive recorded audio data returned from the device (Opus sub-packet)），Save as Opus file by adding packages
    //======================================================================
    @Override
    public void onAiRecording(byte[] recordData, int pkgNum) {
        if (pkgNum == 1) {
            //第一包：清空旧文件，重新开始写入
            try {
                if (aiRecordFileStream != null) {
                    aiRecordFileStream.flush();
                    aiRecordFileStream.close();
                }
                File opusFile = new File(aiRecordOpusPath);
                if (opusFile.exists()) {
                    opusFile.delete();
                }
                aiRecordFileStream = new BufferedOutputStream(new FileOutputStream(opusFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (aiRecordFileStream != null && recordData != null) {
            try {
                aiRecordFileStream.write(recordData);
                aiRecordFileStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        appendLog("【AI录音】接收设备录音数据 Package " + pkgNum + "，长度=" + (recordData != null ? recordData.length : 0));
    }

    /**
     * Prepare audio files (delete old files and create new output streams)）
     *
     * @param fileName opus 文件名（如 ai_qa.opus / ai_dial.opus）
     */
    private void prepareAiRecordFile(String fileName) {
        aiRecordOpusPath = new File(getExternalFilesDir(null), "ai_record/" + fileName).getAbsolutePath();
        File dir = new File(getExternalFilesDir(null), "ai_record");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        closeAiRecordFile();
        File opusFile = new File(aiRecordOpusPath);
        if (opusFile.exists()) {
            opusFile.delete();
        }
        try {
            aiRecordFileStream = new BufferedOutputStream(new FileOutputStream(opusFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        appendLog("【AI录音】准备录音文件: " + aiRecordOpusPath);
    }

    /**
     * 关闭录音输出流
     */
    private void closeAiRecordFile() {
        if (aiRecordFileStream != null) {
            try {
                aiRecordFileStream.flush();
                aiRecordFileStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                aiRecordFileStream = null;
            }
        }
    }

    //======================================================================
    // 设为表盘（参照 GBand2 setAIDial2WatchFace：Set the large image from the completed preview image transfer as the current watch face）
    //======================================================================
    private void setAiDial2WatchFace() {
        if (aiBigWatchFace == null || aiBigWatchFace.bitmap == null) {
            appendLog("【AI表盘】设置表盘失败：No large image data (preview image transfer not completed)）");
            return;
        }
        IUiUpdateListener uiUpdateListener = new IUiUpdateListener() {
            @Override
            public void onUiUpdateStart() {
                appendLog("【AI表盘】开始设置表盘");
            }

            @Override
            public void onStartClearCache(int sumCount) {
            }

            @Override
            public void onClearCacheProgress(int currentCount, int sumCount, int progress) {
            }

            @Override
            public void onFinishClearCache() {
            }

            @Override
            public void onUiUpdateProgress(int currentBlock, int sumBlock, int progress) {
                appendLog("【AI表盘】设置表盘进度: " + progress + "%");
            }

            @Override
            public void onUiUpdateSuccess() {
                appendLog("【AI表盘】设置表盘成功 → 回复设备");
                vpBleManager.sendAIDialRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.SUCCESS, "");
                //⚠️ JL（杰理）照片表盘 = 背景图 + 功能元素（时间/日期/Step Count/颜色）：
                //setJLWatchPhotoDial 只传输了背景图，还需 setCustomWacthUi 重新设置功能元素，
                //Otherwise, the device will only display the default photo watch face (GBand2 will delay after successful setup) 150ms 调 bleSetCustomWatchUi）
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    setCustomWatchUiForAIDial();
                }, 150);
            }

            @Override
            public void onUiUpdateFail(EUiUpdateError eUiUpdateError) {
                appendLog("【AI表盘】设置表盘失败: " + eUiUpdateError + " → 回复设备失败");
                vpBleManager.sendAIDialRecordingResult(defaultResponse, AIFunctionOpt.AIAppErrorCode.AI_DRAWING_FAILED_0x07, "");

            }
        };

        if (VpSpGetUtil.getVpSpVariInstance(this).isJieLiDevice()) {
            //JL（杰理）设备：设置照片表盘（SDK 的 VPOperateManager.setJLWatchPhotoDial，
            //与 GBand2 的 VPBleCenter.setJLWatchPhotoDial 内部实现一致）
            appendLog("【AI表盘】JL设备设置表盘 setJLWatchPhotoDial，大图=" + aiBigWatchFace.filePath);
            vpBleManager.setJLWatchPhotoDial(aiBigWatchFace.filePath, new JLWatchFaceManager.JLTransferPicDialListener() {
                @Override
                public void onLowPower() {
                    uiUpdateListener.onUiUpdateFail(EUiUpdateError.LOW_BATTERY);
                }

                @Override
                public void onJLTransferPicDialStart() {
                    uiUpdateListener.onUiUpdateStart();
                }

                @Override
                public void onTransferPicDialProgress(int progress) {
                    uiUpdateListener.onUiUpdateProgress(0, 100, progress);
                }

                @Override
                public void onScaleBGPFileTransferComplete() {
                }

                @Override
                public void onAIPreviewTransferComplete() {
                }

                @Override
                public void onBigBGPFileTransferComplete() {
                }

                @Override
                public void onTransferComplete() {
                    appendLog("【AIWatch Face】JL watch face photo setting successful");
                    uiUpdateListener.onUiUpdateSuccess();
                }

                @Override
                public void onTransferError(int code, String errorMsg) {
                    appendLog("【AIWatch Face】JL failed to set photo watch face: " + errorMsg);
                    uiUpdateListener.onUiUpdateFail(EUiUpdateError.CHECK_CRC_FAIL);
                }
            });
        } else {
            //非 JL：读取 UI Information, after converting the large image into an input stream, is passed through UI 更新流设置表盘
            appendLog("【AI表盘】读取UI信息 getCustomWatchUiInfo...");
            UiUpdateUtil.getInstance().getCustomWatchUiInfo(uiDataCustom -> {
                EWatchUIType uiType = uiDataCustom != null ? uiDataCustom.getCustomUIType() : null;
                if (uiType == null) {
                    appendLog("【AI表盘】设置表盘失败：未获取到屏幕类型");
                    return;
                }
                InputStream sendInputStream = WatchUIType.getInstance(uiType)
                        .getSendInputStream(AIFunActivity.this, aiBigWatchFace.bitmap);
                appendLog("【AIWatch Face] startSetUiStream(CUSTOM) Set Watch Face，屏幕类型=" + uiType);
                UiUpdateUtil.getInstance().startSetUiStream(EUIFromType.CUSTOM, sendInputStream, uiUpdateListener);
            });
        }
    }

    //======================================================================
    // 发送 AI 表盘预览图（SDK 自动完成裁剪/转换/CRC/分包传输/设备请求应答）
    // Screen type is read from the device via readWatchUiInfo(CUSTOM)，不写死
    //======================================================================
    private void startSendAiDialPreview() {
        String imagePath = copyAssetToCache(AI_DIAL_IMAGE_ASSET);
        if (imagePath == null) {
            appendLog("【AI表盘】本地图片复制失败: " + AI_DIAL_IMAGE_ASSET);
            return;
        }
        //sendAiDialPreview 依赖 SDK 缓存的 AI Configuration (AIFunctionOpt.currentAiConfig, must first readAiConfig），
        //未读取时会报"参数不合法 或 配置为空 或 图片不存在"（The log uniformly displays the image path, but it is actually configured as empty.）
        AIDeviceConfigBean config = AiDialPreviewManager.getInstance().getAiDeviceConfig();
        if (config != null && config.isValid()) {
            doSendAiDialPreview(imagePath);
        } else {
            appendLog("【AI表盘】AI配置未读取 → Read AiConfig first, and continue sending preview images after configuring the callback.");
            pendingAiDialPreviewPath = imagePath;
            vpBleManager.readAiConfig(defaultResponse);
        }
    }

    /** Read the device screen type and call sendAiDialPreview (front：AI 配置已就绪） */
    private void doSendAiDialPreview(String imagePath) {
        //Read the current screen type of the device (CUSTOM) first, then call sendAiDialPreview 发送预览图
        appendLog("【AI表盘】读取设备屏幕类型 readWatchUiInfo(CUSTOM)...");
        vpBleManager.readWatchUiInfo(defaultResponse, EUIFromType.CUSTOM, new IUIBaseInfoListener<UIDataCustom>() {
            @Override
            public void onBaseUiInfo(UIDataCustom uiDataCustom) {
                EWatchUIType uiType = uiDataCustom != null ? uiDataCustom.getCustomUIType() : null;
                appendLog("【AI表盘】读取到屏幕类型: " + uiType + " → 调用 sendAiDialPreview");
                vpBleManager.sendAiDialPreview(imagePath, uiType, previewSendListener);
            }
        });
    }

    /**
     * 设置自定义 UI 元素（时间位置/上下功能/Color) – Necessary steps after JL photo watch face transfer is complete（参照 GBand2 bleSetCustomWatchUi）。
     * The watch face background image (bgp_w001/bgp_w000) only contains the background.，时间/日期/Functional elements such as step count need to be sent separately; otherwise, the device will display the default photo watch face.。
     */
    private void setCustomWatchUiForAIDial() {
        UiUpdateUtil.getInstance().getCustomWatchUiInfo(uiDataCustom -> {
            if (uiDataCustom == null) {
                appendLog("【AI表盘】设置自定义UI失败：未获取到UI信息");
                return;
            }
            appendLog("【AI表盘】设置自定义UI元素: timePosition=" + uiDataCustom.getTimePosition()
                    + " upTimeType=" + uiDataCustom.getUpTimeType()
                    + " downTimeType=" + uiDataCustom.getDownTimeType()
                    + " color888=" + uiDataCustom.getColor888());
            UICustomSetData uiCustomSetData = new UICustomSetData(false,
                    uiDataCustom.getTimePosition(),
                    uiDataCustom.getUpTimeType(),
                    uiDataCustom.getDownTimeType(),
                    uiDataCustom.getColor888());
            vpBleManager.setCustomWacthUi(defaultResponse, uiCustomSetData, new IUIBaseInfoListener<UIDataCustom>() {
                @Override
                public void onBaseUiInfo(UIDataCustom data) {
                    appendLog("【AI表盘】自定义UI设置完成");
                }
            });
        });
    }

    private final OnAiDialPreviewSendListener previewSendListener = new OnAiDialPreviewSendListener() {
        @Override
        public void onDataSendProgress(byte[] data, int currentPackage, int haveSendDataLength, int totalDataLength, int progress) {
            appendLog("【AI表盘】预览图发送进度: " + progress + "% Package " + currentPackage + " [" + haveSendDataLength + "/" + totalDataLength + "]");
        }

        @Override
        public void onDataSendFailed(byte[] data, int failedTimes) {
            appendLog("【AI表盘】预览图数据发送失败: " + failedTimes + "次");
        }

        @Override
        public void onPreviewSendFailed() {
            appendLog("【AI表盘】预览图发送失败！请确认：1.已读取AI配置 2.设备支持AIDial 3. JL device has been injectedIAiDialJLPreviewSender");
        }

        @Override
        public void onPreviewSendComplete(AiCropWatchFaceBitmap bigWatchFace, AiCropWatchFaceBitmap scaleWatchFace, AiCropWatchFaceBitmap previewWatchFace) {
            //Save the large image, and the device will report it as a watch face.（onAIDialSetDial）时用于设置表盘
            AIFunActivity.this.aiBigWatchFace = bigWatchFace;
            appendLog("【AI表盘】预览图发送完成！大图=" + (bigWatchFace != null ? bigWatchFace.filePath : "null"));
        }
    };

    /**
     * Copy the watch face image under assets to the external cache directory of the app（sendAiDialPreview 需要本地文件路径）
     */
    private String copyAssetToCache(String assetName) {
        File dir = new File(getExternalFilesDir(null), "ai_dial");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File outFile = new File(dir, assetName);
        try {
            InputStream is = getAssets().open(assetName);
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[8192];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            fos.close();
            is.close();
            return outFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 页面日志输出
     */
    private void appendLog(String msg) {
        Logger.t(TAG).i(msg);
        runOnUiThread(() -> {
            tvLog.append(msg + "\n");
            svLog.post(() -> svLog.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }
}
