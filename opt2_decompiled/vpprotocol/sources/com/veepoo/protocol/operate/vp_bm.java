package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.IMiniCheckupOptListener;
import com.veepoo.protocol.model.datas.MiniCheckupBPAirPump;
import com.veepoo.protocol.model.datas.MiniCheckupBPPhotoelectric;
import com.veepoo.protocol.model.datas.MiniCheckupBasePersonalInfo;
import com.veepoo.protocol.model.datas.MiniCheckupBloodComponent;
import com.veepoo.protocol.model.datas.MiniCheckupBodyComponent;
import com.veepoo.protocol.model.datas.MiniCheckupDetailData;
import com.veepoo.protocol.model.datas.MiniCheckupResultData;
import com.veepoo.protocol.model.datas.MiniCheckupSkinElectricity;
import com.veepoo.protocol.model.enums.EMiniCheckupTestErrorCode;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bm.class */
public final class vp_bm extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private IMiniCheckupOptListener vp_c;
    private final List vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bm$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_bm vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bm$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_bm vp_b = new vp_bm(null);

        private vp_b() {
        }

        public final vp_bm vp_a() {
            return vp_b;
        }
    }

    private vp_bm() {
        this.vp_d = new ArrayList();
    }

    private final byte[] vp_a(ByteBuffer byteBuffer, int i) {
        if (i < 0 || i > byteBuffer.remaining()) {
            throw new IllegalArgumentException(("Requested length (" + i + ") exceeds remaining bytes (" + byteBuffer.remaining() + ") in buffer.").toString());
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bg(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bg(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = {-109, 9, 1};
        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "开启微体检测量：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bz(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bz(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = {-109, 9, 2};
        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "关闭微体检测量：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IMiniCheckupOptListener iMiniCheckupOptListener;
        IMiniCheckupOptListener iMiniCheckupOptListener2;
        super.handler(bArr);
        if (bArr != null) {
            com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "收到微体检数据: " + com.veepoo.protocol.util.vp_i.vp_a(bArr));
            byte b = bArr[2];
            if (b == 1) {
                byte b2 = bArr[3];
                if (b2 == 0) {
                    byte b3 = bArr[4];
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量中: progress = " + b3 + "%");
                    IMiniCheckupOptListener iMiniCheckupOptListener3 = this.vp_c;
                    if (iMiniCheckupOptListener3 != null) {
                        iMiniCheckupOptListener3.onMiniCheckupTestProgress(b3);
                        return;
                    }
                    return;
                }
                if (b2 == 3) {
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，设备正忙！");
                    IMiniCheckupOptListener iMiniCheckupOptListener4 = this.vp_c;
                    iMiniCheckupOptListener = iMiniCheckupOptListener4;
                    if (iMiniCheckupOptListener4 == null) {
                        return;
                    }
                    iMiniCheckupOptListener.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.DEVICE_BUSY);
                    return;
                }
                if (b2 != 4) {
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量异常标志data[3] = " + b2);
                    return;
                }
                com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，设备端低电！");
                IMiniCheckupOptListener iMiniCheckupOptListener5 = this.vp_c;
                iMiniCheckupOptListener2 = iMiniCheckupOptListener5;
                if (iMiniCheckupOptListener5 == null) {
                    return;
                }
                iMiniCheckupOptListener2.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.LOW_POWER);
                return;
            }
            if (b != 2) {
                com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量异常标志data[2] = " + b);
            }
            byte b4 = bArr[3];
            switch (b4) {
                case 0:
                    byte b5 = bArr[4];
                    if (b5 == 0) {
                        IMiniCheckupOptListener iMiniCheckupOptListener6 = this.vp_c;
                        if (iMiniCheckupOptListener6 != null) {
                            iMiniCheckupOptListener6.onMiniCheckupStopSuccess();
                        }
                    } else if (b5 != 100) {
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量异常标志data[3] = 0x00, data[4] = " + b4);
                    } else {
                        IMiniCheckupOptListener iMiniCheckupOptListener7 = this.vp_c;
                        if (iMiniCheckupOptListener7 != null) {
                            iMiniCheckupOptListener7.onMiniCheckupTestProgress(100);
                        }
                    }
                    break;
                case 1:
                    if (bArr[4] != 100) {
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量异常标志data[3] = 0x01, data[4] = " + b4);
                    } else {
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量结束 100%");
                        IMiniCheckupOptListener iMiniCheckupOptListener8 = this.vp_c;
                        if (iMiniCheckupOptListener8 != null) {
                            iMiniCheckupOptListener8.onMiniCheckupTestProgress(100);
                        }
                        MiniCheckupResultData miniCheckupResultData = new MiniCheckupResultData(bArr[7], bArr[8], bArr[9], bArr[10], bArr[11], com.veepoo.protocol.util.vp_g.vp_b(bArr[13], bArr[12]) / 100.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[15], bArr[14]) / 10.0f, bArr[16], bArr[17], bArr[18]);
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量结束 测量结果:" + miniCheckupResultData);
                        IMiniCheckupOptListener iMiniCheckupOptListener9 = this.vp_c;
                        if (iMiniCheckupOptListener9 != null) {
                            iMiniCheckupOptListener9.onMiniCheckupSuccess(miniCheckupResultData);
                        }
                    }
                    break;
                case 2:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，测量结束后无结果！");
                    IMiniCheckupOptListener iMiniCheckupOptListener10 = this.vp_c;
                    if (iMiniCheckupOptListener10 != null) {
                        iMiniCheckupOptListener10.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.TEST_COMPLETE_NO_RESULT);
                    }
                    break;
                case 3:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，设备正忙！");
                    IMiniCheckupOptListener iMiniCheckupOptListener11 = this.vp_c;
                    iMiniCheckupOptListener = iMiniCheckupOptListener11;
                    if (iMiniCheckupOptListener11 == null) {
                    }
                    iMiniCheckupOptListener.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.DEVICE_BUSY);
                    break;
                case 4:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，设备端低电！");
                    IMiniCheckupOptListener iMiniCheckupOptListener12 = this.vp_c;
                    iMiniCheckupOptListener2 = iMiniCheckupOptListener12;
                    if (iMiniCheckupOptListener12 == null) {
                    }
                    iMiniCheckupOptListener2.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.LOW_POWER);
                    break;
                case 5:
                    byte b6 = bArr[4];
                    byte b7 = bArr[5];
                    if (b6 == 1) {
                        this.vp_d.clear();
                    }
                    this.vp_d.add(bArr);
                    if (b6 != b7) {
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "测量结束，上报结果[" + b6 + " / " + b7 + "] : " + com.veepoo.protocol.util.vp_i.vp_a(bArr));
                    } else {
                        MiniCheckupDetailData miniCheckupDetailDataVp_a = vp_a();
                        com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量完成！结果：" + miniCheckupDetailDataVp_a);
                        IMiniCheckupOptListener iMiniCheckupOptListener13 = this.vp_c;
                        if (iMiniCheckupOptListener13 != null) {
                            iMiniCheckupOptListener13.onMiniCheckupDetailTestSuccess(miniCheckupDetailDataVp_a);
                        }
                    }
                    break;
                case 6:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，设备正忙！");
                    IMiniCheckupOptListener iMiniCheckupOptListener14 = this.vp_c;
                    if (iMiniCheckupOptListener14 != null) {
                        iMiniCheckupOptListener14.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.WEARING_ABNORMALITY);
                    }
                    break;
                case 7:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量停止，具有ECG功能的导联脱落！");
                    IMiniCheckupOptListener iMiniCheckupOptListener15 = this.vp_c;
                    if (iMiniCheckupOptListener15 != null) {
                        iMiniCheckupOptListener15.onMiniCheckupTestFailed(EMiniCheckupTestErrorCode.ECG_LEAD_DETACHMENT);
                    }
                    break;
                default:
                    com.veepoo.protocol.util.vp_w.vp_a("-微体检-", "微体检测量异常标志data[3] = " + b4);
                    break;
            }
        }
    }

    private final MiniCheckupDetailData vp_a() {
        String str;
        byte b = 0;
        byte b2 = 0;
        int i = 0;
        int i2 = 0;
        List list = this.vp_d;
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ArraysKt.drop((byte[]) it.next(), 6));
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(CollectionsKt.toByteArray(arrayList)).order(ByteOrder.LITTLE_ENDIAN);
        MiniCheckupDetailData miniCheckupDetailData = new MiniCheckupDetailData();
        int i3 = 0;
        while (byteBufferOrder.hasRemaining()) {
            switch (byteBufferOrder.get() & 255) {
                case 160:
                    int i4 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a = vp_a(byteBufferOrder, i4);
                    b = bArrVp_a[0];
                    b2 = bArrVp_a[1];
                    i = bArrVp_a[2] & 255;
                    i2 = bArrVp_a[3] & 255;
                    MiniCheckupBasePersonalInfo miniCheckupBasePersonalInfo = new MiniCheckupBasePersonalInfo();
                    miniCheckupBasePersonalInfo.setGender(b);
                    miniCheckupBasePersonalInfo.setAge(b2);
                    miniCheckupBasePersonalInfo.setHeight(i);
                    miniCheckupBasePersonalInfo.setWeight(i2);
                    miniCheckupDetailData.setBasePersonalInfo(miniCheckupBasePersonalInfo);
                    str = "解析-0xA0 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a) + ", 个人信息: " + miniCheckupBasePersonalInfo;
                    break;
                case 161:
                    i3 |= 1;
                    int i5 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, i5);
                    int i6 = bArrVp_a2[0] & 255;
                    miniCheckupDetailData.setHeartRate(i6);
                    str = "解析-0xA1 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a2) + ", 平均Heart Rate值: " + i6;
                    break;
                case 162:
                    i3 |= 2;
                    int i7 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a3 = vp_a(byteBufferOrder, i7);
                    int i8 = bArrVp_a3[0] & 255;
                    miniCheckupDetailData.setBloodOxygen(i8);
                    str = "解析-0xA2 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a3) + ", Blood Oxygen (SpO2)值: " + i8;
                    break;
                case 163:
                    i3 |= 4;
                    int i9 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a4 = vp_a(byteBufferOrder, i9);
                    int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a4[1], bArrVp_a4[0]);
                    int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a4[3], bArrVp_a4[2]);
                    MiniCheckupBPPhotoelectric miniCheckupBPPhotoelectric = new MiniCheckupBPPhotoelectric();
                    miniCheckupBPPhotoelectric.setDiastolicBloodPressure(iVp_b2);
                    miniCheckupBPPhotoelectric.setSystolicBloodPressure(iVp_b);
                    miniCheckupDetailData.setBpPhotoelectric(miniCheckupBPPhotoelectric);
                    str = "解析-0xA3 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a4) + ", 光电Blood Pressure: " + miniCheckupBPPhotoelectric;
                    break;
                case 164:
                    i3 |= 8;
                    int i10 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a5 = vp_a(byteBufferOrder, i10);
                    int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a5[1], bArrVp_a5[0]);
                    int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a5[3], bArrVp_a5[2]);
                    MiniCheckupBPAirPump miniCheckupBPAirPump = new MiniCheckupBPAirPump();
                    miniCheckupBPAirPump.setDiastolicBloodPressure(iVp_b4);
                    miniCheckupBPAirPump.setSystolicBloodPressure(iVp_b3);
                    miniCheckupDetailData.setBpAirPump(miniCheckupBPAirPump);
                    str = "解析-0xA4 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a5) + ", 气泵Blood Pressure: " + miniCheckupBPAirPump;
                    break;
                case 165:
                    i3 |= 16;
                    int i11 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a6 = vp_a(byteBufferOrder, i11);
                    byte b3 = bArrVp_a6[0];
                    float fVp_b = (com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a6[2], bArrVp_a6[1]) & 8191) / 100.0f;
                    miniCheckupDetailData.setBloodGlucoseType(b3);
                    miniCheckupDetailData.setBloodGlucose(fVp_b);
                    str = "解析-0xA5 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a6) + ", Blood Glucose类型: " + b3 + " Blood Glucose值:" + fVp_b;
                    break;
                case 166:
                    i3 |= 32;
                    int i12 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a7 = vp_a(byteBufferOrder, i12);
                    int iVp_b5 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a7[1], bArrVp_a7[0]);
                    int iVp_b6 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a7[3], bArrVp_a7[2]);
                    float f = 10;
                    float fVp_b2 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iVp_b5 * 1.0f) / f), 1);
                    float fVp_b3 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iVp_b6 * 1.0f) / f), 1);
                    miniCheckupDetailData.setOriginalTemperature(fVp_b2);
                    miniCheckupDetailData.setBodyTemperature(fVp_b3);
                    str = "解析-0xA6 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a7) + ", 原始温度: " + fVp_b2 + " Body Temperature:" + fVp_b3;
                    break;
                case 167:
                    i3 |= 64;
                    int i13 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a8 = vp_a(byteBufferOrder, i13);
                    int i14 = bArrVp_a8[0] & 255;
                    miniCheckupDetailData.setStress(i14);
                    str = "解析-0xA7 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a8) + ", 压力: " + i14;
                    break;
                case 168:
                    i3 |= 128;
                    int i15 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a9 = vp_a(byteBufferOrder, i15);
                    int iConvertByteToSignInt = VpBleByteUtil.convertByteToSignInt(bArrVp_a9[0]);
                    miniCheckupDetailData.setEmotion(iConvertByteToSignInt);
                    str = "解析-0xA8 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a9) + ", 情绪: " + iConvertByteToSignInt;
                    break;
                case 169:
                    i3 |= 256;
                    int i16 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a10 = vp_a(byteBufferOrder, i16);
                    int i17 = bArrVp_a10[0] & 255;
                    miniCheckupDetailData.setFatigue(i17);
                    str = "解析-0xA9 :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a10) + ", 疲劳度: " + i17;
                    break;
                case 170:
                    i3 |= 512;
                    int i18 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a11 = vp_a(byteBufferOrder, i18);
                    int i19 = bArrVp_a11[0] & 255;
                    miniCheckupDetailData.setHrv(i19);
                    str = "解析-0xAA :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a11) + ", hrv: " + i19;
                    break;
                case 171:
                    i3 |= 1024;
                    int i20 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a12 = vp_a(byteBufferOrder, i20);
                    byte b4 = bArrVp_a12[0];
                    byte b5 = bArrVp_a12[1];
                    byte b6 = bArrVp_a12[2];
                    byte b7 = bArrVp_a12[3];
                    int iVp_b7 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a12[5], bArrVp_a12[4]);
                    MiniCheckupSkinElectricity miniCheckupSkinElectricity = new MiniCheckupSkinElectricity();
                    miniCheckupSkinElectricity.setEmotion(b4);
                    miniCheckupSkinElectricity.setSkinMoistureContent(b5);
                    miniCheckupSkinElectricity.setDepressionRisk(b6);
                    miniCheckupSkinElectricity.setSympatheticActivity(b7);
                    miniCheckupSkinElectricity.setCortisolConcentration(iVp_b7);
                    miniCheckupDetailData.setSkinElectricity(miniCheckupSkinElectricity);
                    str = "解析-0xAB :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a12) + ", 皮电: " + miniCheckupSkinElectricity;
                    break;
                case 172:
                    i3 |= 2048;
                    int i21 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a13 = vp_a(byteBufferOrder, i21);
                    float fVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a13[1], bArrVp_a13[0]) / 10.0f;
                    float fVp_b5 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a13[3], bArrVp_a13[2]) / 100.0f;
                    float fVp_b6 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a13[5], bArrVp_a13[4]) / 100.0f;
                    float fVp_b7 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a13[7], bArrVp_a13[6]) / 100.0f;
                    float fVp_b8 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a13[9], bArrVp_a13[8]) / 100.0f;
                    MiniCheckupBloodComponent miniCheckupBloodComponent = new MiniCheckupBloodComponent();
                    miniCheckupBloodComponent.setUricAcid(fVp_b4);
                    miniCheckupBloodComponent.settCHO(fVp_b5);
                    miniCheckupBloodComponent.settAG(fVp_b6);
                    miniCheckupBloodComponent.sethDL(fVp_b7);
                    miniCheckupBloodComponent.setlDL(fVp_b8);
                    miniCheckupDetailData.setBloodComponent(miniCheckupBloodComponent);
                    str = "解析-0xAC :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a13) + ", 血液成分: " + miniCheckupBloodComponent;
                    break;
                case 173:
                    i3 |= 4096;
                    int i22 = byteBufferOrder.get() & 255;
                    Intrinsics.checkNotNull(byteBufferOrder);
                    byte[] bArrVp_a14 = vp_a(byteBufferOrder, i22);
                    MiniCheckupBodyComponent miniCheckupBodyComponent = new MiniCheckupBodyComponent(b, b2, i, i2, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[1], bArrVp_a14[0]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[3], bArrVp_a14[2]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[5], bArrVp_a14[4]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[7], bArrVp_a14[6]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[9], bArrVp_a14[8]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[11], bArrVp_a14[10]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[13], bArrVp_a14[12]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[15], bArrVp_a14[14]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[17], bArrVp_a14[16]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[19], bArrVp_a14[18]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[21], bArrVp_a14[20]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[23], bArrVp_a14[22]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[25], bArrVp_a14[24]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a14[27], bArrVp_a14[26]) / 10.0f);
                    miniCheckupDetailData.setBodyComponent(miniCheckupBodyComponent);
                    str = "解析-0xAD :" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a14) + ", 身体成分: " + miniCheckupBodyComponent;
                    break;
                default:
                    continue;
            }
            com.veepoo.protocol.util.vp_w.vp_a("-微体检-", str);
        }
        if (miniCheckupDetailData.getSkinElectricity() != null) {
            miniCheckupDetailData.setEmotion(miniCheckupDetailData.getSkinElectricity().getEmotion());
        }
        miniCheckupDetailData.setFunSp(i3);
        return miniCheckupDetailData;
    }

    public /* synthetic */ vp_bm(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void vp_a(IMiniCheckupOptListener iMiniCheckupOptListener) {
        this.vp_c = iMiniCheckupOptListener;
    }
}
