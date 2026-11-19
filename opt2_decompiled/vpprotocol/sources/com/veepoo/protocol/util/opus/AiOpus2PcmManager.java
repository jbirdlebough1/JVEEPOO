package com.veepoo.protocol.util.opus;

import android.content.Context;
import android.util.Log;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.OnOpusDecode2PcmListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.thread.HBThreadPools;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import vip.inode.demo.opusaudiodemo.utils.OpusUtils;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/opus/AiOpus2PcmManager.class */
public class AiOpus2PcmManager {
    public static final int DEFAULT_AUDIO_SAMPLE_RATE = 16000;
    public static final int DEFAULT_OPUS_CHANNEL = 1;
    public static final int BUFFER_LENGTH = 80;
    public static final int BITRATE = 32000;
    public static final int FRAME_SIZE = 80;
    private String vp_a;
    private String vp_b;
    OnOpusDecode2PcmListener vp_d;
    private BufferedOutputStream vp_c = null;
    private boolean vp_e = false;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/opus/AiOpus2PcmManager$vp_a.class */
    private static final class vp_a {
        private static final AiOpus2PcmManager vp_a = new AiOpus2PcmManager();
    }

    private AiOpus2PcmManager() {
    }

    public static AiOpus2PcmManager getInstance() {
        return vp_a.vp_a;
    }

    private void vp_d() {
        if (this.vp_d != null) {
            HBThreadPools.getInstance().postOnUIThread(() -> {
                if (this.vp_e) {
                    this.vp_d.onDecodeComplete(this.vp_b);
                } else {
                    this.vp_d.onDecodeFailed(this.vp_b);
                }
            });
        }
    }

    private Context vp_a() {
        return VPOperateManager.mContext;
    }

    public static short[] byteArrayToShortArray(byte[] bArr) {
        short[] sArr = new short[bArr.length / 2];
        ByteBuffer.wrap(bArr).order(ByteOrder.nativeOrder()).asShortBuffer().get(sArr);
        return sArr;
    }

    public static byte[] shortArrayToByteArray(short[] sArr) {
        int length = sArr.length;
        byte[] bArr = new byte[length * 2];
        for (int i = 0; i < length; i++) {
            int i2 = i;
            int i3 = i2 * 2;
            short s = sArr[i2];
            bArr[i3] = (byte) (s & 255);
            bArr[i3 + 1] = (byte) ((s >> 8) & 255);
        }
        return bArr;
    }

    public void init(String str, String str2, OnOpusDecode2PcmListener onOpusDecode2PcmListener) {
        this.vp_d = onOpusDecode2PcmListener;
        this.vp_a = str;
        this.vp_b = str2;
        File file = new File(this.vp_b);
        File file2 = new File(file.getParent());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (file.exists()) {
            file.delete();
        }
        this.vp_e = false;
        try {
            file.createNewFile();
            this.vp_c = new BufferedOutputStream(new FileOutputStream(file, true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void decodeOpus() {
        HBThreadPools.getInstance().execute(() -> {
            String str;
            String str2;
            Context contextVp_a = vp_a();
            if (contextVp_a == null) {
                this.vp_e = false;
                Log.e("-AiOpus2PcmManager-", ": 解码失败, SDK 未初始化(VPOperateManager.mContext 为 null)");
                vp_d();
                return;
            }
            int i = VpSpGetUtil.getVpSpVariInstance(contextVp_a).isJieLiDevice() ? 40 : 80;
            ?? CreateDecoder = OpusUtils.vp_a.vp_a().createDecoder(DEFAULT_AUDIO_SAMPLE_RATE, 1);
            try {
                FileInputStream fileInputStream = new FileInputStream(this.vp_a);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                while (true) {
                    byte[] bArr = new byte[i];
                    if (bufferedInputStream.read(bArr, 0, i) >= 0) {
                        short[] sArr = new short[320];
                        int iDecode = OpusUtils.vp_a.vp_a().decode(CreateDecoder, bArr, sArr);
                        if (iDecode <= 0) {
                            this.vp_e = false;
                            str = "-AiOpus2PcmManager-";
                            str2 = ": 解码异常";
                            break;
                        } else {
                            short[] sArr2 = new short[iDecode];
                            System.arraycopy(sArr, 0, sArr2, 0, iDecode);
                            BufferedOutputStream bufferedOutputStream = this.vp_c;
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.write(shortArrayToByteArray(sArr2));
                            }
                        }
                    } else {
                        this.vp_e = true;
                        str = "-AiOpus2PcmManager-";
                        str2 = ": 解码完成";
                        break;
                    }
                }
                Log.e(str, str2);
                CreateDecoder = this;
                OpusUtils.vp_a.vp_a().destroyDecoder(CreateDecoder);
                bufferedInputStream.close();
                fileInputStream.close();
                CreateDecoder.vp_d();
            } catch (Exception unused) {
                CreateDecoder.printStackTrace();
                this.vp_e = false;
                vp_d();
            }
        });
    }
}
