package com.veepoo.protocol.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.bluetrum.abpartool.ParTool;
import com.bluetrum.abpartool.utils.ImageUtils;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleUnnotifyResponse;
import com.inuker.bluetooth.library.utils.BluetoothLog;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.customui.WatchUIType;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IAiRecordListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.listener.data.IMtuChangeListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormAGPSListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormCustomListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormG15ImgListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormImagePushListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoFormServerListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoListener;
import com.veepoo.protocol.listener.data.IUIOprateListener;
import com.veepoo.protocol.listener.data.IUiUpdateListener;
import com.veepoo.protocol.model.datas.BatteryData;
import com.veepoo.protocol.model.datas.UICustomSetData;
import com.veepoo.protocol.model.datas.UIDataAGPS;
import com.veepoo.protocol.model.datas.UIDataCustom;
import com.veepoo.protocol.model.datas.UIDataG15Img;
import com.veepoo.protocol.model.datas.UIDataImagePush;
import com.veepoo.protocol.model.datas.UIDataServer;
import com.veepoo.protocol.model.datas.UiData;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.model.enums.EUiUpdateError;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil.class */
public class UiUpdateUtil {
    private static volatile UiUpdateUtil vp_ab;
    public static final byte ACTION_RECODING = -48;
    private Context vp_b;
    private UIDataServer vp_c;
    private UIDataCustom vp_d;
    private UIDataAGPS vp_e;
    private UIDataImagePush vp_f;
    private UIDataG15Img vp_g;
    private UIDataG15Img vp_h;
    private UIDataG15Img vp_i;
    private UIDataG15Img vp_j;
    private IUiUpdateListener vp_k;
    int vp_l;
    long vp_m;
    private vp_z vp_n;
    private byte[] vp_o;
    private IAiRecordListener vp_v;
    private String vp_a = UiUpdateUtil.class.getSimpleName();
    private int vp_p = 0;
    private int vp_q = 0;
    private int vp_r = 0;
    private int vp_s = 0;
    private int vp_t = 0;
    private boolean vp_u = false;
    private boolean vp_w = false;
    IBleWriteResponse vp_x = new vp_k();
    private int vp_y = 0;
    private BleNotifyResponse vp_z = new vp_q();
    long vp_aa = 0;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_a.class */
    class vp_a implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormG15ImgListener vp_a;

        vp_a(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
            this.vp_a = iUIBaseInfoFormG15ImgListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataG15Img uIDataG15Img) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_h = uIDataG15Img;
            uiUpdateUtil.vp_l = uIDataG15Img.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormG15Img(UiUpdateUtil.this.vp_h);
            new StringBuilder().append("uiDataG15ImgProfile:").append(UiUpdateUtil.this.vp_h.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_b.class */
    class vp_b implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormG15ImgListener vp_a;

        vp_b(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
            this.vp_a = iUIBaseInfoFormG15ImgListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataG15Img uIDataG15Img) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_i = uIDataG15Img;
            uiUpdateUtil.vp_l = uIDataG15Img.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormG15Img(UiUpdateUtil.this.vp_i);
            new StringBuilder().append("uiDataG15ImgQRCode1:").append(UiUpdateUtil.this.vp_i.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_c.class */
    class vp_c implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormG15ImgListener vp_a;

        vp_c(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
            this.vp_a = iUIBaseInfoFormG15ImgListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataG15Img uIDataG15Img) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_j = uIDataG15Img;
            uiUpdateUtil.vp_l = uIDataG15Img.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormG15Img(UiUpdateUtil.this.vp_j);
            new StringBuilder().append("uiDataG15ImgQRCode2:").append(UiUpdateUtil.this.vp_j.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_d.class */
    class vp_d implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormCustomListener vp_a;

        vp_d(IUIBaseInfoFormCustomListener iUIBaseInfoFormCustomListener) {
            this.vp_a = iUIBaseInfoFormCustomListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataCustom uIDataCustom) {
            new StringBuilder().append("setCustomWacthUi uiDataCustom:").append(uIDataCustom.toString());
            this.vp_a.onBaseUiInfoFormCustom(uIDataCustom);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_e.class */
    class vp_e implements IBatteryDataListener {
        final /* synthetic */ EUIFromType vp_a;
        final /* synthetic */ int vp_b;

        vp_e(EUIFromType eUIFromType, int i) {
            this.vp_a = eUIFromType;
            this.vp_b = i;
        }

        @Override // com.veepoo.protocol.listener.data.IBatteryDataListener
        public void onDataChange(BatteryData batteryData) {
            if (batteryData.getBatteryLevel() < 1 && (!batteryData.isPercent() || batteryData.isLowBattery())) {
                UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
                uiUpdateUtil.vp_w = false;
                uiUpdateUtil.vp_k.onUiUpdateFail(EUiUpdateError.LOW_BATTERY);
                return;
            }
            UiUpdateUtil.this.vp_k.onUiUpdateStart();
            UiUpdateUtil.this.vp_w = true;
            switch (vp_p.vp_a[this.vp_a.ordinal()]) {
                case 1:
                    UiUpdateUtil.this.vp_c();
                    break;
                case 2:
                    UiUpdateUtil.this.vp_a(this.vp_b);
                    break;
                case 3:
                    UiUpdateUtil.this.vp_i();
                    break;
                case 4:
                    UiUpdateUtil.this.vp_d();
                    break;
                case 5:
                    UiUpdateUtil.this.vp_e();
                    break;
                case 6:
                    UiUpdateUtil.this.vp_f();
                    break;
                case 7:
                    UiUpdateUtil.this.vp_g();
                    break;
                case 8:
                    UiUpdateUtil.this.vp_h();
                    break;
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_f.class */
    class vp_f implements IUIOprateListener {
        vp_f() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            new StringBuilder().append("UiData================> ").append(uiData.toString());
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_g.class */
    class vp_g implements IUIOprateListener {
        vp_g() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            new StringBuilder().append("UiData================> ").append(uiData.toString());
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_h.class */
    class vp_h implements IUIOprateListener {
        vp_h() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_i.class */
    class vp_i implements IUIOprateListener {
        vp_i() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_j.class */
    class vp_j implements IUIOprateListener {
        vp_j() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_k.class */
    class vp_k implements IBleWriteResponse {
        vp_k() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
            new StringBuilder().append("onResponse code:").append(i);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_l.class */
    class vp_l implements IUIOprateListener {
        vp_l() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_m.class */
    class vp_m implements IUIOprateListener {
        vp_m() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(true);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_n.class */
    class vp_n implements IUIOprateListener {
        vp_n() {
        }

        @Override // com.veepoo.protocol.listener.data.IUIOprateListener
        public void onUIOprateCallckback(UiData uiData) {
            if (uiData.getStatus() == 1) {
                UiUpdateUtil.this.vp_a(false);
            } else {
                UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.INTO_UPDATE_MODE_FAIL);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_o.class */
    class vp_o implements IBleWriteResponse {
        vp_o() {
        }

        @Override // com.veepoo.protocol.listener.base.IBleWriteResponse
        public void onResponse(int i) {
            if (i == 0) {
                UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
                if (uiUpdateUtil.vp_w) {
                    int i2 = uiUpdateUtil.vp_q + 1;
                    uiUpdateUtil.vp_q = i2;
                    uiUpdateUtil.vp_b(i2);
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_p.class */
    static /* synthetic */ class vp_p {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EUIFromType.values().length];
            vp_a = iArr;
            try {
                iArr[EUIFromType.CUSTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EUIFromType.A_GPS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EUIFromType.SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[EUIFromType.G15_IMG_QR_CODE_APP_DOWNLOAD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[EUIFromType.G15_IMG_PROFILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[EUIFromType.G15_IMG_THEME_1.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[EUIFromType.G15_IMG_THEME_2.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[EUIFromType.IMAGE_PUSH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_q.class */
    class vp_q implements BleNotifyResponse {
        vp_q() {
        }

        public void onResponse(int i) {
            if (i == 0) {
                BluetoothLog.i("onNotify DATA,onResponse,success");
            }
        }

        public void onNotify(UUID uuid, UUID uuid2, byte[] bArr) {
            BluetoothLog.i("notifyUI ：" + VpBleByteUtil.byte2HexForShow(bArr));
            if (bArr.length >= 6 && bArr[1] == -48 && UiUpdateUtil.this.vp_v != null) {
                BluetoothLog.i("aiRecordListener == null ->" + (UiUpdateUtil.this.vp_v == null));
                int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[3], bArr[2]);
                byte[] bArrVp_b = UiUpdateUtil.this.vp_b(bArr);
                IAiRecordListener iAiRecordListener = UiUpdateUtil.this.vp_v;
                if (iAiRecordListener != null) {
                    iAiRecordListener.onAiRecording(bArrVp_b, iVp_b);
                    return;
                }
                return;
            }
            if (bArr.length >= 1) {
                switch (bArr[0]) {
                    case 0:
                        UiUpdateUtil.this.vp_k.onUiUpdateFail(EUiUpdateError.FILE_LENGTH_NOT_4_POWER);
                        break;
                    case 1:
                        if (bArr.length > 2) {
                            byte b = bArr[1];
                            if (b == 1) {
                                int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
                                UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
                                int i = iArrByte2HexToIntArr[2];
                                uiUpdateUtil.vp_s = i;
                                uiUpdateUtil.vp_k.onStartClearCache(i);
                                new StringBuilder().append("UI擦除开始:").append((int) VpBleByteUtil.loUint16(bArr[2]));
                                break;
                            } else if (b == 2) {
                                int[] iArrByte2HexToIntArr2 = VpBleByteUtil.byte2HexToIntArr(bArr);
                                UiUpdateUtil uiUpdateUtil2 = UiUpdateUtil.this;
                                int i2 = iArrByte2HexToIntArr2[2];
                                uiUpdateUtil2.vp_t = i2;
                                float f = i2 * 100.0f;
                                int i3 = uiUpdateUtil2.vp_s;
                                uiUpdateUtil2.vp_k.onClearCacheProgress(i2, i3, (int) (f / i3));
                                new StringBuilder().append("UI擦除中:").append((int) VpBleByteUtil.loUint16(bArr[2]));
                                break;
                            } else if (b == 3) {
                                UiUpdateUtil.this.vp_k.onFinishClearCache();
                                UiUpdateUtil.this.vp_q = 0;
                                new StringBuilder().append("oneBlockLength:").append(UiUpdateUtil.this.vp_p);
                                UiUpdateUtil uiUpdateUtil3 = UiUpdateUtil.this;
                                uiUpdateUtil3.vp_n = new vp_z(uiUpdateUtil3.vp_o, uiUpdateUtil3.vp_p);
                                UiUpdateUtil uiUpdateUtil4 = UiUpdateUtil.this;
                                uiUpdateUtil4.vp_b(uiUpdateUtil4.vp_q);
                                break;
                            }
                        }
                        break;
                    case 2:
                        new StringBuilder().append("UI发送结束了,校验CRC,app计算值:").append(UiUpdateUtil.this.vp_r);
                        UiUpdateUtil.this.vp_a();
                        break;
                    case 3:
                        int i4 = (bArr[1] & 255) | ((bArr[2] << 8) & 65280);
                        new StringBuilder().append("获取CRC,设备的计算值:").append(i4);
                        UiUpdateUtil uiUpdateUtil5 = UiUpdateUtil.this;
                        if (i4 != uiUpdateUtil5.vp_r) {
                            uiUpdateUtil5.vp_k.onUiUpdateFail(EUiUpdateError.CHECK_CRC_FAIL);
                        } else {
                            uiUpdateUtil5.vp_k.onUiUpdateSuccess();
                        }
                        break;
                    case 5:
                        if (bArr.length >= 3 && bArr[1] == 1) {
                            byte b2 = bArr[2];
                        }
                        UiUpdateUtil uiUpdateUtil6 = UiUpdateUtil.this;
                        uiUpdateUtil6.vp_a(uiUpdateUtil6.vp_l, uiUpdateUtil6.vp_m);
                        break;
                }
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_r.class */
    class vp_r implements BleUnnotifyResponse {
        vp_r() {
        }

        public void onResponse(int i) {
            if (i == 0) {
                UiUpdateUtil.this.vp_u = false;
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_s.class */
    class vp_s implements IMtuChangeListener {
        vp_s() {
        }

        @Override // com.veepoo.protocol.listener.data.IMtuChangeListener
        public void onChangeMtuLength(int i) {
            new StringBuilder().append("onChangeMtuLength cmdLength:").append(i);
            int mtuValue = VpSpGetUtil.getVpSpVariInstance(UiUpdateUtil.this.vp_b).getMtuValue();
            UiUpdateUtil.this.vp_p = Math.max(20, mtuValue);
            new StringBuilder().append("onChangeMtuLength mOneBlockLength:").append(UiUpdateUtil.this.vp_p);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_t.class */
    class vp_t implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormServerListener vp_a;

        vp_t(IUIBaseInfoFormServerListener iUIBaseInfoFormServerListener) {
            this.vp_a = iUIBaseInfoFormServerListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataServer uIDataServer) {
            new StringBuilder().append("2.服务器的表盘基本信息 uiDataServer:").append(uIDataServer.toString());
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_c = uIDataServer;
            uiUpdateUtil.vp_l = uIDataServer.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormServer(uIDataServer);
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_u.class */
    class vp_u implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormCustomListener vp_a;

        vp_u(IUIBaseInfoFormCustomListener iUIBaseInfoFormCustomListener) {
            this.vp_a = iUIBaseInfoFormCustomListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataCustom uIDataCustom) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_d = uIDataCustom;
            uiUpdateUtil.vp_l = uIDataCustom.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormCustom(uIDataCustom);
            new StringBuilder().append("uiDataCustom:").append(uIDataCustom.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_v.class */
    class vp_v implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormImagePushListener vp_a;

        vp_v(IUIBaseInfoFormImagePushListener iUIBaseInfoFormImagePushListener) {
            this.vp_a = iUIBaseInfoFormImagePushListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataImagePush uIDataImagePush) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_f = uIDataImagePush;
            uiUpdateUtil.vp_l = uIDataImagePush.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormImagePush(uIDataImagePush);
            new StringBuilder().append("uiDataImagePush:").append(uIDataImagePush.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_w.class */
    class vp_w implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormAGPSListener vp_a;

        vp_w(IUIBaseInfoFormAGPSListener iUIBaseInfoFormAGPSListener) {
            this.vp_a = iUIBaseInfoFormAGPSListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataAGPS uIDataAGPS) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_e = uIDataAGPS;
            uiUpdateUtil.vp_l = uIDataAGPS.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormAgps(UiUpdateUtil.this.vp_e);
            new StringBuilder().append("uiDataAGPS:").append(uIDataAGPS.toString());
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/UiUpdateUtil$vp_x.class */
    class vp_x implements IUIBaseInfoListener {
        final /* synthetic */ IUIBaseInfoFormG15ImgListener vp_a;

        vp_x(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
            this.vp_a = iUIBaseInfoFormG15ImgListener;
        }

        @Override // com.veepoo.protocol.listener.data.IUIBaseInfoListener
        /* JADX INFO: renamed from: vp_a, reason: merged with bridge method [inline-methods] */
        public void onBaseUiInfo(UIDataG15Img uIDataG15Img) {
            UiUpdateUtil uiUpdateUtil = UiUpdateUtil.this;
            uiUpdateUtil.vp_g = uIDataG15Img;
            uiUpdateUtil.vp_l = uIDataG15Img.getDataReceiveAddress();
            this.vp_a.onBaseUiInfoFormG15Img(UiUpdateUtil.this.vp_g);
            new StringBuilder().append("mUiDataG15ImgAppDownloadQRCode:").append(UiUpdateUtil.this.vp_g.toString());
        }
    }

    public void resetNotifyFlag() {
        this.vp_u = false;
    }

    public void init(Context context) {
        if (this.vp_b == null) {
            this.vp_b = context.getApplicationContext();
        }
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_b).getMtuValue() == -1) {
            getOneUiBlockCanSendLength();
        } else {
            this.vp_p = VpSpGetUtil.getVpSpVariInstance(this.vp_b).getMtuValue();
        }
        vp_j();
        new StringBuilder().append("Mtu协商值:").append(this.vp_p);
    }

    public void release() {
        VPOperateManager.getMangerInstance(this.vp_b).setOnUIDataUnNotify(new vp_r());
    }

    public boolean isSupportChangeServerUi() {
        return VpSpGetUtil.getVpSpVariInstance(this.vp_b).getBigTranType() == 2 && VpSpGetUtil.getVpSpVariInstance(this.vp_b).getWatchuiServer() > 0;
    }

    public boolean isSupportChangeCustomUi() {
        return VpSpGetUtil.getVpSpVariInstance(this.vp_b).getBigTranType() == 2 && VpSpGetUtil.getVpSpVariInstance(this.vp_b).getWatchuiCoustom() > 0;
    }

    public boolean isSupportChangeCustomAGPS() {
        return VpSpGetUtil.getVpSpVariInstance(this.vp_b).getBigTranType() == 2 && VpSpGetUtil.getVpSpVariInstance(this.vp_b).isSupoortAGPS();
    }

    public void getOneUiBlockCanSendLength() {
        VPOperateManager.getMangerInstance(this.vp_b).changeMTU(247, new vp_s());
    }

    public void getServerWatchUiInfo(IUIBaseInfoFormServerListener iUIBaseInfoFormServerListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.SERVER, new vp_t(iUIBaseInfoFormServerListener));
    }

    public void getCustomWatchUiInfo(IUIBaseInfoFormCustomListener iUIBaseInfoFormCustomListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.CUSTOM, new vp_u(iUIBaseInfoFormCustomListener));
    }

    public void getImagePushUiInfo(IUIBaseInfoFormImagePushListener iUIBaseInfoFormImagePushListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.IMAGE_PUSH, new vp_v(iUIBaseInfoFormImagePushListener));
    }

    public void getAGPSWacthUiInfo(IUIBaseInfoFormAGPSListener iUIBaseInfoFormAGPSListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.A_GPS, new vp_w(iUIBaseInfoFormAGPSListener));
    }

    public void getG15ImgAppDownloadQRCode(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.G15_IMG_QR_CODE_APP_DOWNLOAD, new vp_x(iUIBaseInfoFormG15ImgListener));
    }

    public void getG15ImgProfileInfo(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.G15_IMG_PROFILE, new vp_a(iUIBaseInfoFormG15ImgListener));
    }

    public void getG15ImgTheme1Info(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.G15_IMG_THEME_1, new vp_b(iUIBaseInfoFormG15ImgListener));
    }

    public void getG15ImgTheme2Info(IUIBaseInfoFormG15ImgListener iUIBaseInfoFormG15ImgListener) {
        VPOperateManager.getMangerInstance(this.vp_b).readWatchUiInfo(this.vp_x, EUIFromType.G15_IMG_THEME_2, new vp_c(iUIBaseInfoFormG15ImgListener));
    }

    public void setCustomWacthUi(UICustomSetData uICustomSetData, IUIBaseInfoFormCustomListener iUIBaseInfoFormCustomListener) {
        VPOperateManager.getMangerInstance(this.vp_b).setCustomWacthUi(this.vp_x, uICustomSetData, new vp_d(iUIBaseInfoFormCustomListener));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.io.FileInputStream, java.io.InputStream] */
    public InputStream getInputStreamFromFilePath(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        ?? file = new File(str);
        try {
            file = new FileInputStream((File) file);
            return file;
        } catch (FileNotFoundException unused) {
            file.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void startSetUiStream(EUIFromType eUIFromType, InputStream inputStream, IUiUpdateListener iUiUpdateListener) {
        this.vp_q = 0;
        this.vp_r = 0;
        this.vp_s = 0;
        this.vp_t = 0;
        if (iUiUpdateListener == null) {
            this.vp_k.onUiUpdateFail(EUiUpdateError.LISTENTER_IS_NULL);
            return;
        }
        EUIFromType eUIFromType2 = EUIFromType.A_GPS;
        if (eUIFromType == eUIFromType2) {
            this.vp_l = this.vp_e.getDataReceiveAddress();
        } else if (eUIFromType == EUIFromType.CUSTOM) {
            this.vp_l = this.vp_d.getDataReceiveAddress();
        } else if (eUIFromType == EUIFromType.IMAGE_PUSH) {
            this.vp_l = this.vp_f.getDataReceiveAddress();
        } else {
            this.vp_l = this.vp_c.getDataReceiveAddress();
        }
        this.vp_k = iUiUpdateListener;
        try {
            if (inputStream == 0) {
                iUiUpdateListener.onUiUpdateFail(EUiUpdateError.FILE_UNEXIST);
                return;
            }
            byte[] inputStreamByte = getInputStreamByte(inputStream, eUIFromType != eUIFromType2);
            this.vp_o = inputStreamByte;
            if (inputStreamByte == null || inputStreamByte.length == 0) {
                this.vp_k.onUiUpdateFail(EUiUpdateError.FILE_UNEXIST);
                return;
            }
            this.vp_m = inputStreamByte.length;
            this.vp_r = com.veepoo.protocol.util.vp_a.vp_a(inputStreamByte);
            switch (vp_p.vp_a[eUIFromType.ordinal()]) {
                case 1:
                    UIDataCustom uIDataCustom = this.vp_d;
                    if (uIDataCustom == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataCustom.setFileLength(this.vp_m);
                    if (this.vp_r == this.vp_d.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 2:
                    UIDataAGPS uIDataAGPS = this.vp_e;
                    if (uIDataAGPS == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataAGPS.setFileLength(this.vp_m);
                    this.vp_e.setTimeStamp(this.vp_aa);
                    if (this.vp_r == this.vp_e.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 3:
                    UIDataServer uIDataServer = this.vp_c;
                    if (uIDataServer == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataServer.setDataFileLength(this.vp_m);
                    if (this.vp_r == this.vp_c.getImgCrcId()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 4:
                    UIDataG15Img uIDataG15Img = this.vp_g;
                    if (uIDataG15Img == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataG15Img.setFileLength(this.vp_m);
                    this.vp_g.setTimeStamp(this.vp_aa);
                    if (this.vp_r == this.vp_g.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 5:
                    UIDataG15Img uIDataG15Img2 = this.vp_h;
                    if (uIDataG15Img2 == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataG15Img2.setFileLength(this.vp_m);
                    this.vp_h.setTimeStamp(this.vp_aa);
                    if (this.vp_r == this.vp_h.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 6:
                    UIDataG15Img uIDataG15Img3 = this.vp_i;
                    if (uIDataG15Img3 == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataG15Img3.setFileLength(this.vp_m);
                    this.vp_i.setTimeStamp(this.vp_aa);
                    if (this.vp_r == this.vp_i.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 7:
                    UIDataG15Img uIDataG15Img4 = this.vp_j;
                    if (uIDataG15Img4 == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataG15Img4.setFileLength(this.vp_m);
                    this.vp_j.setTimeStamp(this.vp_aa);
                    if (this.vp_r == this.vp_j.getCrc()) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.APP_CRC_SAME_DEVICE_CRC);
                        return;
                    }
                    break;
                case 8:
                    UIDataImagePush uIDataImagePush = this.vp_f;
                    if (uIDataImagePush == null) {
                        this.vp_k.onUiUpdateFail(EUiUpdateError.NEED_READ_BASE_INFO);
                        return;
                    }
                    uIDataImagePush.setFileLength(this.vp_m);
                    break;
            }
            vp_a(eUIFromType, this.vp_r);
        } catch (IOException unused) {
            inputStream.printStackTrace();
        }
    }

    public void stopUITransfer() {
        this.vp_w = false;
    }

    public byte[] getInputStreamByte(InputStream inputStream, boolean z) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException unused) {
                }
                throw th;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && byteArray.length % 4 != 0) {
            byteArray = vp_a(byteArray);
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused2) {
        }
        return byteArray;
    }

    public byte[] getZKInputStreamByte(InputStream inputStream, WatchUIType watchUIType) throws Throwable {
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStream);
        Bitmap bitmapVp_a = com.veepoo.protocol.util.vp_k.vp_a(this.vp_b, watchUIType, bitmapDecodeStream);
        if (watchUIType.getBigBitmapRoundedCorner() != 0) {
            bitmapDecodeStream = com.veepoo.protocol.util.vp_k.vp_a(bitmapDecodeStream, watchUIType.getBigBitmapRoundedCorner());
        }
        byte[] rawImageData = ImageUtils.getRawImageData(bitmapDecodeStream);
        Bitmap bitmap = bitmapDecodeStream;
        boolean zIsZK569XDevice = VpSpGetUtil.getVpSpVariInstance(this.vp_b).isZK569XDevice();
        byte[] bArrRawToPar = ParTool.rawToPar(rawImageData, bitmap.getWidth(), bitmap.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(this.vp_b, rawImageData, "bigPar.bin");
        byte[] bArrRawToPar2 = ParTool.rawToPar(ImageUtils.getRawImageData(bitmapVp_a), bitmapVp_a.getWidth(), bitmapVp_a.getHeight(), zIsZK569XDevice, false, zIsZK569XDevice);
        vp_ah.vp_a(this.vp_b, bArrRawToPar2, "smallPar.bin");
        int length = bArrRawToPar.length + 4;
        int length2 = bArrRawToPar2.length + 4;
        byte[] bArrVp_b = com.veepoo.protocol.util.vp_g.vp_b(length);
        byte[] bArrVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(length2);
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArrVp_b, 0, bArr, 0, 4);
        System.arraycopy(bArrRawToPar, 0, bArr, 4, bArrRawToPar.length);
        System.arraycopy(bArrVp_b2, 0, bArr2, 0, 4);
        System.arraycopy(bArrRawToPar2, 0, bArr2, 4, bArrRawToPar2.length);
        return vp_a(bArr, bArr2);
    }

    public byte[] getFlipContent(byte[] bArr) {
        if (bArr.length % 4 != 0) {
            return new byte[0];
        }
        for (int i = 0; i < bArr.length / 4; i++) {
            vp_a(bArr, i);
        }
        return bArr;
    }

    public void setAiRecordListener(IAiRecordListener iAiRecordListener) {
        this.vp_v = iAiRecordListener;
    }

    public void setAGPSTimeStamp(long j) {
        this.vp_aa = j;
    }

    private UiUpdateUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.veepoo.protocol.util.UiUpdateUtil>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static synchronized UiUpdateUtil getInstance() {
        if (vp_ab == null) {
            ?? r0 = UiUpdateUtil.class;
            synchronized (r0) {
                try {
                    if (vp_ab == null) {
                        vp_ab = new UiUpdateUtil();
                    }
                    r0 = r0;
                } catch (Throwable unused) {
                    throw r0;
                }
            }
        }
        return vp_ab;
    }

    private void vp_j() {
        if (this.vp_u) {
            return;
        }
        this.vp_u = true;
        VPOperateManager.getMangerInstance(this.vp_b).setOnUIDataNotify(this.vp_z);
    }

    private void vp_d() {
        new StringBuilder().append("mUIDataG15Img-[appDownloadQRCode]:").append(this.vp_g.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeG15ImgAppDownloadQRCode(this.vp_x, this.vp_g, new vp_f());
    }

    private void vp_e() {
        new StringBuilder().append("mUIDataG15Img-[Profile]:").append(this.vp_h.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeG15ImgProfile(this.vp_x, this.vp_h, new vp_g());
    }

    private void vp_f() {
        new StringBuilder().append("mUIDataG15Img-[Theme-1]:").append(this.vp_i.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeG15ImgTheme1(this.vp_x, this.vp_i, new vp_h());
    }

    private void vp_g() {
        new StringBuilder().append("mUIDataG15Img-[Theme-2]:").append(this.vp_j.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeG15ImgTheme2(this.vp_x, this.vp_j, new vp_i());
    }

    private void vp_c() {
        new StringBuilder().append("mUiDataCustom:").append(this.vp_d.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeAGPS(this.vp_x, this.vp_d, new vp_j());
    }

    private void vp_i() {
        new StringBuilder().append("mUiDataServer:").append(this.vp_c.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeServer(this.vp_x, this.vp_c, new vp_l());
    }

    private void vp_h() {
        new StringBuilder().append("mUiDataImagePush:").append(this.vp_f.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeImagePush(this.vp_x, this.vp_f, new vp_m());
    }

    private void vp_b() {
        VPOperateManager.getMangerInstance(this.vp_b).endUiUpdate(this.vp_x);
    }

    private void vp_a() {
        VPOperateManager.getMangerInstance(this.vp_b).checkUiCrc(this.vp_x, this.vp_r);
    }

    public void startSetUiStream(EUIFromType eUIFromType, String str, IUiUpdateListener iUiUpdateListener) {
        if (eUIFromType.code != 6) {
            startSetUiStream(eUIFromType, getInputStreamFromFilePath(str), iUiUpdateListener);
            return;
        }
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        byte[] rawImageData = ImageUtils.getRawImageData(bitmapDecodeFile);
        VpSpGetUtil.getVpSpVariInstance(this.vp_b).isZK569XDevice();
        startSetUiStream(eUIFromType, new ByteArrayInputStream(ParTool.rawToPar(rawImageData, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), false, false, false)), iUiUpdateListener);
    }

    private byte[] vp_b(byte[] bArr) {
        com.veepoo.protocol.util.vp_g.vp_b(bArr[3], bArr[2]);
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]);
        if (bArr.length != iVp_b + 6) {
            return null;
        }
        byte[] bArr2 = new byte[iVp_b];
        System.arraycopy(bArr, 6, bArr2, 0, bArr.length - 6);
        return bArr2;
    }

    private void vp_c(byte[] bArr) {
        if (this.vp_w) {
            VPOperateManager.getMangerInstance(this.vp_b).sendUiData(new vp_o(), bArr);
        }
    }

    private byte[] vp_a(byte[] bArr) {
        int length = ((bArr.length / 4) + 1) * 4;
        byte[] bArr2 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr2[i] = -1;
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private void vp_a(int i) {
        new StringBuilder().append("agps:").append(this.vp_e.toString());
        VPOperateManager.getMangerInstance(this.vp_b).makeDeviceIntoUpdateModeAGPS(this.vp_x, this.vp_e, i, new vp_n());
    }

    private void vp_b(int i) {
        byte[] bArrVp_a = this.vp_n.vp_a(i);
        int iVp_b = this.vp_n.vp_b();
        if (i >= iVp_b) {
            vp_b();
            return;
        }
        new StringBuilder().append("发送UI块:").append(i).append("/").append(iVp_b);
        int i2 = i + 1;
        this.vp_k.onUiUpdateProgress(i2, iVp_b, (int) ((i2 * 100.0f) / iVp_b));
        vp_c(bArrVp_a);
    }

    private byte[] vp_a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private void vp_a(EUIFromType eUIFromType, int i) {
        VPOperateManager.getMangerInstance(this.vp_b).readBattery(this.vp_x, new vp_e(eUIFromType, i));
    }

    private void vp_a(int i, long j) {
        VPOperateManager.getMangerInstance(this.vp_b).startClearUi(this.vp_x, i, j);
    }

    private void vp_a(boolean z) {
        this.vp_w = true;
        VPOperateManager.getMangerInstance(this.vp_b).startUiUpdate(this.vp_x, z);
    }

    private void vp_a(byte[] bArr, int i) {
        int i2 = i * 4;
        byte b = bArr[i2];
        int i3 = i2 + 1;
        byte b2 = bArr[i3];
        int i4 = i2 + 2;
        byte b3 = bArr[i4];
        int i5 = i2 + 3;
        bArr[i2] = bArr[i5];
        bArr[i3] = b3;
        bArr[i4] = b2;
        bArr[i5] = b;
    }
}
