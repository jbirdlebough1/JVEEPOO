package com.veepoo.protocol.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IDeviceManualDetectDataListener;
import com.veepoo.protocol.model.datas.BloodComponentManualData;
import com.veepoo.protocol.model.datas.BloodGlucoseManualData;
import com.veepoo.protocol.model.datas.BloodOxygenManualData;
import com.veepoo.protocol.model.datas.BloodPressureManualData;
import com.veepoo.protocol.model.datas.BodyTemperatureManualData;
import com.veepoo.protocol.model.datas.EmotionManualData;
import com.veepoo.protocol.model.datas.FatigueManualData;
import com.veepoo.protocol.model.datas.HeartRateManualData;
import com.veepoo.protocol.model.datas.HrvManualData;
import com.veepoo.protocol.model.datas.MetoManualData;
import com.veepoo.protocol.model.datas.MiniCheckupBPAirPump;
import com.veepoo.protocol.model.datas.MiniCheckupBPPhotoelectric;
import com.veepoo.protocol.model.datas.MiniCheckupBasePersonalInfo;
import com.veepoo.protocol.model.datas.MiniCheckupBloodComponent;
import com.veepoo.protocol.model.datas.MiniCheckupBodyComponent;
import com.veepoo.protocol.model.datas.MiniCheckupManualData;
import com.veepoo.protocol.model.datas.MiniCheckupSkinElectricity;
import com.veepoo.protocol.model.datas.PressureManualData;
import com.veepoo.protocol.model.datas.SkinConductanceManualData;
import com.veepoo.protocol.model.enums.DeviceManualDataType;
import com.veepoo.protocol.model.enums.EBloodGlucoseRiskLevel;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ab.class */
public class vp_ab extends com.veepoo.protocol.vp_a {
    private IDeviceManualDetectDataListener vp_c;
    private int vp_d = 0;
    private int vp_e = 0;
    List vp_f = Collections.synchronizedList(new ArrayList());
    List vp_g = new ArrayList();
    List vp_h = new ArrayList();
    List vp_i = new ArrayList();
    List vp_j = new ArrayList();
    List vp_k = new ArrayList();
    List vp_l = new ArrayList();
    List vp_m = new ArrayList();
    List vp_n = new ArrayList();
    List vp_o = new ArrayList();
    List vp_p = new ArrayList();
    List vp_q = new ArrayList();
    List vp_r = new ArrayList();
    List vp_s = new ArrayList();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20, types: [byte] */
    /* JADX WARN: Type inference failed for: r0v89, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v90, types: [com.veepoo.protocol.operate.vp_ab] */
    private void vp_f(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        BloodPressureManualData bloodPressureManualData = new BloodPressureManualData();
        while (byteBufferOrder.position() < bArr.length) {
            ?? r0 = byteBufferOrder.get();
            switch (r0) {
                case -64:
                    byteBufferOrder.get();
                    bloodPressureManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    bloodPressureManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    if (bloodPressureManualData.getVersion() != 1) {
                        bloodPressureManualData.setSystolic(byteBufferOrder.get() & 255);
                        bloodPressureManualData.setDiastolic(byteBufferOrder.get() & 255);
                    } else {
                        bloodPressureManualData.setMeasurementMode(byteBufferOrder.get() & 255);
                        bloodPressureManualData.setHeartRate(byteBufferOrder.get() & 255);
                        bloodPressureManualData.setSystolic(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
                        bloodPressureManualData.setDiastolic(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
                        bloodPressureManualData.setTestStatus(byteBufferOrder.get() & 255);
                        bloodPressureManualData.setResultCredibility(byteBufferOrder.get() & 255);
                    }
                    break;
                case -61:
                    byteBufferOrder.get();
                    bloodPressureManualData.setHeight(byteBufferOrder.get() & 255);
                    bloodPressureManualData.setWeight(byteBufferOrder.get() & 255);
                    bloodPressureManualData.setAge(byteBufferOrder.get() & 255);
                    bloodPressureManualData.setMale((byteBufferOrder.get() & 255) == 1);
                    break;
                case -60:
                    try {
                        r0 = this;
                        r0.vp_a(bloodPressureManualData, r0.vp_a(byteBufferOrder, "0xC4", bArr));
                    } catch (Exception unused) {
                        r0.printStackTrace();
                    }
                    break;
            }
        }
        Log.e("Test", "time == " + bloodPressureManualData.getTestTime() + ",systolic == " + bloodPressureManualData.getSystolic());
        if (bloodPressureManualData.getSystolic() != 0) {
            this.vp_g.add(bloodPressureManualData);
        }
    }

    private void vp_j(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        HeartRateManualData heartRateManualData = new HeartRateManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    heartRateManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    heartRateManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    int i = byteBufferOrder.get() & 255;
                    int iPosition = byteBufferOrder.position();
                    byteBufferOrder.position(iPosition + i);
                    byte[] bArrVp_a = vp_a(bArr, i, iPosition);
                    int[] iArr = new int[bArrVp_a.length];
                    for (int i2 = 0; i2 < bArrVp_a.length; i2++) {
                        iArr[i2] = bArrVp_a[i2] & 255;
                    }
                    heartRateManualData.setRate(iArr);
                    break;
            }
        }
        if (heartRateManualData.getRate() == null || heartRateManualData.getRate().length == 0) {
            return;
        }
        this.vp_h.add(heartRateManualData);
    }

    private void vp_d(byte[] bArr) {
        int iVp_a;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        BloodGlucoseManualData bloodGlucoseManualData = new BloodGlucoseManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    bloodGlucoseManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    bloodGlucoseManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    if ((byteBufferOrder.get() & 255) == 2) {
                        int iVp_a2 = com.veepoo.protocol.util.vp_g.vp_a(byteBufferOrder.get(), byteBufferOrder.get());
                        if (bloodGlucoseManualData.getVersion() == 1) {
                            iVp_a = (iVp_a2 & 57344) >> 13;
                            iVp_a2 &= 8191;
                        } else {
                            iVp_a = vp_a(iVp_a2);
                        }
                        bloodGlucoseManualData.setBloodGlucoseValue(iVp_a2 / 100.0f);
                        bloodGlucoseManualData.setRisk(EBloodGlucoseRiskLevel.fromValue(iVp_a));
                    }
                    break;
            }
        }
        this.vp_i.add(bloodGlucoseManualData);
    }

    private void vp_n(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        PressureManualData pressureManualData = new PressureManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    pressureManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    pressureManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    pressureManualData.setPressure(byteBufferOrder.get() & 255);
                    break;
            }
        }
    }

    private void vp_e(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        BloodOxygenManualData bloodOxygenManualData = new BloodOxygenManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    bloodOxygenManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    bloodOxygenManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    int i = byteBufferOrder.get() & 255;
                    int iPosition = byteBufferOrder.position();
                    byteBufferOrder.position(iPosition + i);
                    byte[] bArrVp_a = vp_a(bArr, i, iPosition);
                    int[] iArr = new int[bArrVp_a.length];
                    for (int i2 = 0; i2 < bArrVp_a.length; i2++) {
                        iArr[i2] = bArrVp_a[i2] & 255;
                    }
                    bloodOxygenManualData.setOxygen(iArr);
                    break;
            }
        }
        this.vp_j.add(bloodOxygenManualData);
    }

    private void vp_g(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        BodyTemperatureManualData bodyTemperatureManualData = new BodyTemperatureManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    bodyTemperatureManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    bodyTemperatureManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    int iTwoByteToUnsignedInt3 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    float fVp_b = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iTwoByteToUnsignedInt2 * 1.0f) / 10.0f), 1);
                    float fVp_b2 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iTwoByteToUnsignedInt3 * 1.0f) / 10.0f), 1);
                    bodyTemperatureManualData.setBaseTemperature(fVp_b);
                    bodyTemperatureManualData.setTemperature(fVp_b2);
                    break;
            }
        }
        this.vp_k.add(bodyTemperatureManualData);
    }

    private void vp_l(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        MetoManualData metoManualData = new MetoManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    metoManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    metoManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    metoManualData.setMeto(byteBufferOrder.get() & 255);
                    break;
            }
        }
        this.vp_m.add(metoManualData);
    }

    private void vp_k(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        HrvManualData hrvManualData = new HrvManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    hrvManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    hrvManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    int i = byteBufferOrder.get() & 255;
                    int iPosition = byteBufferOrder.position();
                    byteBufferOrder.position(iPosition + i);
                    byte[] bArrVp_a = vp_a(bArr, i, iPosition);
                    int[] iArr = new int[bArrVp_a.length];
                    for (int i2 = 0; i2 < bArrVp_a.length; i2++) {
                        iArr[i2] = bArrVp_a[i2] & 255;
                    }
                    hrvManualData.setHrv(iArr);
                    break;
            }
        }
        this.vp_n.add(hrvManualData);
    }

    private void vp_c(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        BloodComponentManualData bloodComponentManualData = new BloodComponentManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    bloodComponentManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    bloodComponentManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    bloodComponentManualData.setUricAcid(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()) / 10.0f);
                    bloodComponentManualData.settCHO(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()) / 100.0f);
                    bloodComponentManualData.settAG(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()) / 100.0f);
                    bloodComponentManualData.sethDL(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()) / 100.0f);
                    bloodComponentManualData.setlDL(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()) / 100.0f);
                    break;
            }
        }
        this.vp_o.add(bloodComponentManualData);
    }

    private void vp_m(byte[] bArr) {
        int iVp_a;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        MiniCheckupManualData miniCheckupManualData = new MiniCheckupManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    miniCheckupManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    int i = byteBufferOrder.get() & 255;
                    miniCheckupManualData.setVersion(i);
                    miniCheckupManualData.setType(i);
                    break;
                case -62:
                    int i2 = byteBufferOrder.get() & 255;
                    if (miniCheckupManualData.getType() != 2) {
                        miniCheckupManualData.setHeart(byteBufferOrder.get() & 255);
                        miniCheckupManualData.setOxygen(byteBufferOrder.get() & 255);
                        miniCheckupManualData.setPressure(byteBufferOrder.get() & 255);
                        miniCheckupManualData.setEmotion(VpBleByteUtil.convertByteToSignInt(byteBufferOrder.get()));
                        miniCheckupManualData.setFatigue(byteBufferOrder.get() & 255);
                        byte b = byteBufferOrder.get();
                        int iVp_a2 = com.veepoo.protocol.util.vp_g.vp_a(b, new byte[]{byteBufferOrder.get(), b}[0]);
                        if (VpSpGetUtil.getVpSpVariInstance(VPOperateManager.mContext).getBloodGlucoseTag() == 5) {
                            iVp_a = (iVp_a2 & 57344) >> 13;
                            iVp_a2 &= 8191;
                        } else {
                            iVp_a = vp_a(iVp_a2);
                        }
                        miniCheckupManualData.setBloodGlucose(iVp_a2 / 100.0f);
                        miniCheckupManualData.setBloodGlucoseRisk(iVp_a);
                        int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                        int iTwoByteToUnsignedInt3 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                        float fVp_b = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iTwoByteToUnsignedInt2 * 1.0f) / 10.0f), 1);
                        float fVp_b2 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iTwoByteToUnsignedInt3 * 1.0f) / 10.0f), 1);
                        miniCheckupManualData.setBaseTemperature(fVp_b);
                        miniCheckupManualData.setTemperature(fVp_b2);
                        miniCheckupManualData.setHighValue(byteBufferOrder.get());
                        miniCheckupManualData.setLowValue(byteBufferOrder.get());
                        if (miniCheckupManualData.getType() == 1 && i2 >= 15) {
                            miniCheckupManualData.setHrv(byteBufferOrder.get() & 255);
                            miniCheckupManualData.setId(byteBufferOrder.get() & 255);
                        }
                    } else {
                        int iPosition = byteBufferOrder.position();
                        byte[] bArrVp_a = vp_a(bArr, i2, iPosition);
                        byteBufferOrder.position(iPosition + i2);
                        vp_a(bArrVp_a, miniCheckupManualData);
                    }
                    break;
            }
        }
        this.vp_p.add(miniCheckupManualData);
    }

    private void vp_h(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        EmotionManualData emotionManualData = new EmotionManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    emotionManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    emotionManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    emotionManualData.setEmotion(VpBleByteUtil.convertByteToSignInt(byteBufferOrder.get()));
                    break;
            }
        }
        this.vp_q.add(emotionManualData);
    }

    private void vp_i(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        FatigueManualData fatigueManualData = new FatigueManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    fatigueManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    fatigueManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    fatigueManualData.setFatigue(byteBufferOrder.get());
                    break;
            }
        }
        this.vp_r.add(fatigueManualData);
    }

    private void vp_o(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        SkinConductanceManualData skinConductanceManualData = new SkinConductanceManualData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    skinConductanceManualData.setTimeStamp(vp_b(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -63:
                    byteBufferOrder.get();
                    skinConductanceManualData.setVersion(byteBufferOrder.get() & 255);
                    break;
                case -62:
                    byteBufferOrder.get();
                    skinConductanceManualData.setEmotionLevel(VpBleByteUtil.convertByteToSignInt(byteBufferOrder.get()));
                    skinConductanceManualData.setSkinMoisture(byteBufferOrder.get() & 255);
                    skinConductanceManualData.setDepressionRisk(byteBufferOrder.get() & 255);
                    skinConductanceManualData.setSnsActivation(byteBufferOrder.get() & 255);
                    skinConductanceManualData.setCortisolValue(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()));
                    break;
            }
        }
        this.vp_s.add(skinConductanceManualData);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.ByteArrayOutputStream, java.io.OutputStream, java.lang.Throwable] */
    private static byte[] vp_a(List list) {
        ?? byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                byteArrayOutputStream.write((byte[]) it.next());
            } catch (Exception unused) {
                byteArrayOutputStream.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private int vp_b(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferWrap.getInt();
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        int iVp_b = vp_b(new byte[]{byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get()});
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + iVp_b);
        return vp_a(bArr, iVp_b, iPosition);
    }

    private int vp_a(int i) {
        int i2 = 0;
        if (i >= 300 && i <= 700) {
            i2 = 1;
        }
        if (i > 700 && i <= 1100) {
            i2 = 2;
        }
        if (i > 1100 && i <= 1500) {
            i2 = 3;
        }
        return i2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        IDeviceManualDetectDataListener iDeviceManualDetectDataListener = (IDeviceManualDetectDataListener) iListener;
        this.vp_c = iDeviceManualDetectDataListener;
        if (iDeviceManualDetectDataListener == null) {
            return;
        }
        int iVp_b = vp_b(new byte[]{bArr[2], bArr[3], bArr[4], bArr[5]});
        new StringBuilder().append("pkgNum[").append(iVp_b).append("]");
        if (iVp_b == 0) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, 8, bArr2, 0, 4);
            this.vp_d = vp_b(bArr2);
            new StringBuilder().append("totalPkgNum[").append(this.vp_d).append("]");
            if (vp_b(new byte[]{bArr[8], bArr[9], bArr[10], bArr[11]}) == 0) {
                this.vp_c.onReadComplete();
            }
        } else if (this.vp_e + 1 != iVp_b) {
            this.vp_c.onReadFail();
        }
        this.vp_e = iVp_b;
        int length = bArr.length - 6;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 6, bArr3, 0, length);
        this.vp_f.add(bArr3);
        int i = this.vp_d;
        if (i != 0) {
            this.vp_c.onReadProgress((iVp_b / i) * 100.0f);
        }
        if (iVp_b + 1 == this.vp_d) {
            try {
                vp_a();
                if (!this.vp_g.isEmpty()) {
                    this.vp_g.toString();
                    this.vp_c.onBloodPressureDataChange(this.vp_g);
                }
                if (!this.vp_h.isEmpty()) {
                    this.vp_h.toString();
                    this.vp_c.onHeartRateDataChange(this.vp_h);
                }
                if (!this.vp_i.isEmpty()) {
                    this.vp_i.toString();
                    this.vp_c.onBloodGlucoseDataChange(this.vp_i);
                }
                if (!this.vp_l.isEmpty()) {
                    this.vp_l.toString();
                    this.vp_c.onPressureManualDataChange(this.vp_l);
                }
                if (!this.vp_j.isEmpty()) {
                    this.vp_j.toString();
                    this.vp_c.onBloodOxygenDataChange(this.vp_j);
                }
                if (!this.vp_k.isEmpty()) {
                    this.vp_k.toString();
                    this.vp_c.onBodyTemperatureDataChange(this.vp_k);
                }
                if (!this.vp_m.isEmpty()) {
                    this.vp_m.toString();
                    this.vp_c.onMetoManualDataChange(this.vp_m);
                }
                if (!this.vp_n.isEmpty()) {
                    this.vp_n.toString();
                    this.vp_c.onHrvManualDataChange(this.vp_n);
                }
                if (!this.vp_o.isEmpty()) {
                    this.vp_o.toString();
                    this.vp_c.onBloodComponentManualDataChange(this.vp_o);
                }
                if (!this.vp_p.isEmpty()) {
                    this.vp_p.toString();
                    this.vp_c.onMiniCheckupManualDataChange(this.vp_p);
                }
                if (!this.vp_q.isEmpty()) {
                    this.vp_q.toString();
                    this.vp_c.onEmotionManualDataChange(this.vp_q);
                }
                if (!this.vp_r.isEmpty()) {
                    this.vp_r.toString();
                    this.vp_c.onFatigueManualDataChange(this.vp_r);
                }
                if (!this.vp_s.isEmpty()) {
                    this.vp_s.toString();
                    this.vp_c.onSkinConductanceManualDataChange(this.vp_s);
                }
                this.vp_c.onReadComplete();
            } catch (Exception unused) {
                this.vp_c.onReadFail();
            }
        }
    }

    private byte[] vp_a(long j) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(4);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.putInt((int) j);
        return byteBufferAllocate.array();
    }

    private void vp_a(int i, byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < bArr.length) {
            if (byteBufferOrder.get() == -80) {
                byte[] bArrVp_a = vp_a(byteBufferOrder, "0xB0", bArr);
                if (i == 0) {
                    vp_f(bArrVp_a);
                } else if (i == 1) {
                    vp_j(bArrVp_a);
                } else if (i == 2) {
                    vp_d(bArrVp_a);
                } else if (i == 3) {
                    vp_n(bArrVp_a);
                } else if (i == 4) {
                    vp_e(bArrVp_a);
                } else if (i == 5) {
                    vp_g(bArrVp_a);
                } else if (i == 6) {
                    vp_l(bArrVp_a);
                } else if (i == 7) {
                    vp_k(bArrVp_a);
                } else if (i == 8) {
                    vp_c(bArrVp_a);
                } else if (i == 9) {
                    vp_m(bArrVp_a);
                } else if (i == 10) {
                    vp_h(bArrVp_a);
                } else if (i == 11) {
                    vp_i(bArrVp_a);
                } else if (i == 12) {
                    vp_o(bArrVp_a);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void vp_a() {
        byte[] bArrVp_a = vp_a(this.vp_f);
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArrVp_a).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < bArrVp_a.length) {
            byte b = byteBufferOrder.get();
            if (b == -96) {
                byteBufferOrder.position(byteBufferOrder.position() + byteBufferOrder.get());
            } else if (b != -95) {
                continue;
            } else {
                byteBufferOrder.get();
                try {
                    vp_a(byteBufferOrder.get(), vp_a(byteBufferOrder, "0xA1", bArrVp_a));
                } catch (Exception e) {
                    e.printStackTrace();
                    throw this;
                }
            }
        }
    }

    private void vp_a(BloodPressureManualData bloodPressureManualData, byte[] bArr) {
        int i;
        int i2;
        int i3 = bArr[0] & 255;
        bloodPressureManualData.setTestTime(i3);
        bloodPressureManualData.setTestFailTimeoutCount(bArr[1] & 255);
        bloodPressureManualData.setPumpModel(bArr[2] & 255);
        bloodPressureManualData.setAfeModel(bArr[3] & 255);
        bloodPressureManualData.setAccelerationModel(bArr[4] & 255);
        bloodPressureManualData.setMcuModel(VpBleByteUtil.twoByteToUnsignedInt(bArr[6], bArr[5]));
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        bloodPressureManualData.setAlgorithmVersion(strArrByte2HexToStrArr[7] + "." + strArrByte2HexToStrArr[8] + "." + strArrByte2HexToStrArr[9] + "." + strArrByte2HexToStrArr[10]);
        bloodPressureManualData.setSoftwareVersion(strArrByte2HexToStrArr[11] + "." + strArrByte2HexToStrArr[12] + "." + strArrByte2HexToStrArr[13] + "." + strArrByte2HexToStrArr[14]);
        int[] iArr = new int[i3];
        int i4 = 15;
        while (true) {
            i = i3 + 15;
            if (i4 >= i) {
                break;
            }
            iArr[i4 - 15] = bArr[i4] & 255;
            i4++;
        }
        bloodPressureManualData.setAttitudeArray(iArr);
        int[] iArr2 = new int[i3];
        int i5 = i;
        while (true) {
            i2 = i + i3;
            if (i5 >= i2) {
                break;
            }
            iArr2[i5 - i] = bArr[i5] & 255;
            i5++;
        }
        bloodPressureManualData.setSportArray(iArr2);
        int i6 = i3 * 50;
        int[] iArr3 = new int[i6];
        for (int i7 = 0; i7 < i6; i7++) {
            int i8 = i2 + (i7 * 4);
            iArr3[i7] = vp_b(new byte[]{bArr[i8], bArr[i8 + 1], bArr[i8 + 2], bArr[i8 + 3]});
        }
        bloodPressureManualData.setPressureAdcArray(iArr3);
        int i9 = i3 * 200;
        int i10 = i2 + i9;
        int[] iArr4 = new int[i6];
        for (int i11 = 0; i11 < i6; i11++) {
            int i12 = i10 + (i11 * 4);
            iArr4[i11] = vp_b(new byte[]{bArr[i12], bArr[i12 + 1], bArr[i12 + 2], bArr[i12 + 3]});
        }
        bloodPressureManualData.setPpgAdcArray(iArr4);
        int i13 = i10 + i9;
        int[] iArr5 = new int[i6];
        for (int i14 = 0; i14 < i6; i14++) {
            int i15 = i13 + (i14 * 2);
            iArr5[i14] = VpBleByteUtil.twoByteToUnsignedInt(bArr[i15 + 1], bArr[i15]);
        }
        bloodPressureManualData.setAccelerationXArray(iArr5);
        int i16 = i3 * 100;
        int i17 = i13 + i16;
        int[] iArr6 = new int[i6];
        for (int i18 = 0; i18 < i6; i18++) {
            int i19 = i17 + (i18 * 2);
            iArr6[i18] = VpBleByteUtil.twoByteToUnsignedInt(bArr[i19 + 1], bArr[i19]);
        }
        bloodPressureManualData.setAccelerationYArray(iArr6);
        int i20 = i17 + i16;
        int[] iArr7 = new int[i6];
        for (int i21 = 0; i21 < i6; i21++) {
            int i22 = i20 + (i21 * 2);
            iArr7[i21] = VpBleByteUtil.twoByteToUnsignedInt(bArr[i22 + 1], bArr[i22]);
        }
        bloodPressureManualData.setAccelerationZArray(iArr7);
    }

    private void vp_a(byte[] bArr, MiniCheckupManualData miniCheckupManualData, int i, int i2, int i3, int i4) {
        if (bArr.length < 28) {
            return;
        }
        miniCheckupManualData.setBodyComponent(new MiniCheckupBodyComponent(i, i2, i3, i4, com.veepoo.protocol.util.vp_g.vp_b(bArr[1], bArr[0]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[3], bArr[2]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[7], bArr[6]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[9], bArr[8]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[11], bArr[10]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[13], bArr[12]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[15], bArr[14]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[17], bArr[16]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[19], bArr[18]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[21], bArr[20]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[23], bArr[22]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[25], bArr[24]) / 10.0f, com.veepoo.protocol.util.vp_g.vp_b(bArr[27], bArr[26]) / 10.0f));
    }

    private void vp_a(byte[] bArr, MiniCheckupManualData miniCheckupManualData) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (byteBufferOrder.remaining() >= 2) {
            int i5 = byteBufferOrder.get() & 255;
            int i6 = byteBufferOrder.get() & 255;
            if (i6 <= byteBufferOrder.remaining()) {
                int iPosition = byteBufferOrder.position();
                byte[] bArrVp_a = vp_a(bArr, i6, iPosition);
                byteBufferOrder.position(iPosition + i6);
                switch (i5) {
                    case 208:
                        if (bArrVp_a.length >= 4) {
                            i = bArrVp_a[0] & 255;
                            i2 = bArrVp_a[1] & 255;
                            i3 = bArrVp_a[2] & 255;
                            i4 = bArrVp_a[3] & 255;
                            MiniCheckupBasePersonalInfo miniCheckupBasePersonalInfo = new MiniCheckupBasePersonalInfo();
                            miniCheckupBasePersonalInfo.setGender(i);
                            miniCheckupBasePersonalInfo.setAge(i2);
                            miniCheckupBasePersonalInfo.setHeight(i3);
                            miniCheckupBasePersonalInfo.setWeight(i4);
                            miniCheckupManualData.setBasePersonalInfo(miniCheckupBasePersonalInfo);
                        }
                        break;
                    case 209:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setHeart(bArrVp_a[0] & 255);
                        }
                        break;
                    case 210:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setOxygen(bArrVp_a[0] & 255);
                        }
                        break;
                    case 211:
                        if (bArrVp_a.length >= 4) {
                            int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[1], bArrVp_a[0]);
                            int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[3], bArrVp_a[2]);
                            MiniCheckupBPPhotoelectric miniCheckupBPPhotoelectric = new MiniCheckupBPPhotoelectric();
                            miniCheckupBPPhotoelectric.setSystolicBloodPressure(iVp_b);
                            miniCheckupBPPhotoelectric.setDiastolicBloodPressure(iVp_b2);
                            miniCheckupManualData.setBpPhotoelectric(miniCheckupBPPhotoelectric);
                            miniCheckupManualData.setHighValue(iVp_b);
                            miniCheckupManualData.setLowValue(iVp_b2);
                        }
                        break;
                    case 212:
                        if (bArrVp_a.length >= 4) {
                            int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[1], bArrVp_a[0]);
                            int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[3], bArrVp_a[2]);
                            MiniCheckupBPAirPump miniCheckupBPAirPump = new MiniCheckupBPAirPump();
                            miniCheckupBPAirPump.setSystolicBloodPressure(iVp_b3);
                            miniCheckupBPAirPump.setDiastolicBloodPressure(iVp_b4);
                            miniCheckupManualData.setBpAirPump(miniCheckupBPAirPump);
                            miniCheckupManualData.setHighValue(iVp_b3);
                            miniCheckupManualData.setLowValue(iVp_b4);
                        }
                        break;
                    case 213:
                        if (bArrVp_a.length >= 3) {
                            int i7 = bArrVp_a[0] & 255;
                            int iVp_b5 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[2], bArrVp_a[1]);
                            miniCheckupManualData.setBloodGlucoseType(i7);
                            miniCheckupManualData.setBloodGlucose((iVp_b5 & 8191) / 100.0f);
                            miniCheckupManualData.setBloodGlucoseRisk((iVp_b5 & 57344) >> 13);
                        }
                        break;
                    case 214:
                        if (bArrVp_a.length >= 4) {
                            int iVp_b6 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[1], bArrVp_a[0]);
                            int iVp_b7 = com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[3], bArrVp_a[2]);
                            float fVp_b = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iVp_b6 * 1.0f) / 10.0f), 1);
                            float fVp_b2 = com.veepoo.protocol.util.vp_b.vp_b(String.valueOf((iVp_b7 * 1.0f) / 10.0f), 1);
                            miniCheckupManualData.setBaseTemperature(fVp_b);
                            miniCheckupManualData.setTemperature(fVp_b2);
                        }
                        break;
                    case 215:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setPressure(bArrVp_a[0] & 255);
                        }
                        break;
                    case 216:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setEmotion(VpBleByteUtil.convertByteToSignInt(bArrVp_a[0]));
                        }
                        break;
                    case 217:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setFatigue(bArrVp_a[0] & 255);
                        }
                        break;
                    case 218:
                        if (bArrVp_a.length >= 1) {
                            miniCheckupManualData.setHrv(bArrVp_a[0] & 255);
                        }
                        break;
                    case 219:
                        if (bArrVp_a.length >= 6) {
                            MiniCheckupSkinElectricity miniCheckupSkinElectricity = new MiniCheckupSkinElectricity();
                            miniCheckupSkinElectricity.setEmotion(VpBleByteUtil.convertByteToSignInt(bArrVp_a[0]));
                            miniCheckupSkinElectricity.setSkinMoistureContent(bArrVp_a[1] & 255);
                            miniCheckupSkinElectricity.setDepressionRisk(bArrVp_a[2] & 255);
                            miniCheckupSkinElectricity.setSympatheticActivity(bArrVp_a[3] & 255);
                            miniCheckupSkinElectricity.setCortisolConcentration(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[5], bArrVp_a[4]));
                            miniCheckupManualData.setSkinElectricity(miniCheckupSkinElectricity);
                        }
                        break;
                    case 220:
                        if (bArrVp_a.length >= 10) {
                            MiniCheckupBloodComponent miniCheckupBloodComponent = new MiniCheckupBloodComponent();
                            miniCheckupBloodComponent.setUricAcid(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[1], bArrVp_a[0]) / 10.0f);
                            miniCheckupBloodComponent.settCHO(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[3], bArrVp_a[2]) / 100.0f);
                            miniCheckupBloodComponent.settAG(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[5], bArrVp_a[4]) / 100.0f);
                            miniCheckupBloodComponent.sethDL(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[7], bArrVp_a[6]) / 100.0f);
                            miniCheckupBloodComponent.setlDL(com.veepoo.protocol.util.vp_g.vp_b(bArrVp_a[9], bArrVp_a[8]) / 100.0f);
                            miniCheckupManualData.setBloodComponent(miniCheckupBloodComponent);
                        }
                        break;
                    case 221:
                        if (bArrVp_a.length != 1) {
                            vp_a(bArrVp_a, miniCheckupManualData, i, i2, i3, i4);
                        } else {
                            miniCheckupManualData.setHrv(bArrVp_a[0] & 255);
                        }
                        break;
                }
            } else {
                return;
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, long j, List list, List list2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, j, list, list2);
        this.vp_f.clear();
        this.vp_g.clear();
        this.vp_h.clear();
        this.vp_i.clear();
        this.vp_j.clear();
        this.vp_k.clear();
        this.vp_l.clear();
        this.vp_m.clear();
        this.vp_n.clear();
        this.vp_o.clear();
        this.vp_p.clear();
        this.vp_q.clear();
        this.vp_r.clear();
        this.vp_s.clear();
        byte[] bArrVp_a = vp_a(j);
        DeviceManualDataType.Companion companion = DeviceManualDataType.INSTANCE;
        byte[] byteArray = companion.toByteArray(list);
        byte[] byteArray2 = companion.toByteArray(list2);
        byte[] bArr = {-34, 2, bArrVp_a[0], bArrVp_a[1], bArrVp_a[2], bArrVp_a[3], byteArray[0], byteArray[1], byteArray[2], byteArray[3], byteArray2[0], byteArray2[1], byteArray2[2], byteArray2[3], 0, 0, 0, 0, 0, 0};
        new StringBuilder().append("数据DE读取处理：").append(VpBleByteUtil.byte2HexForShow(bArr));
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
