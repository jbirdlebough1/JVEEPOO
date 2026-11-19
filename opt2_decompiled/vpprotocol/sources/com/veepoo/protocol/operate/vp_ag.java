package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IECGReadDataListener;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.EcgDiagnosis;
import com.veepoo.protocol.model.datas.EcgSingleData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.EcgUtil;
import com.veepoo.protocol.util.FunctionCheckUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ag.class */
public class vp_ag extends com.veepoo.protocol.vp_a {
    int[] vp_h;
    IECGReadDataListener vp_i;
    BluetoothClient vp_j;
    String vp_k;
    BleWriteResponse vp_l;
    private FunctionCheckUtil vp_n;
    List vp_c = new ArrayList();
    List vp_d = new ArrayList();
    List vp_e = new ArrayList();
    int vp_f = 0;
    int vp_g = 0;
    boolean vp_m = false;

    public vp_ag() {
        this.vp_n = null;
        if (this.vp_a == null) {
            this.vp_a = VPOperateManager.mContext;
        }
        if (this.vp_a != null) {
            this.vp_n = new FunctionCheckUtil(VpSpGetUtil.getVpSpVariInstance(this.vp_a), this.vp_a);
        }
    }

    private void vp_c(byte[] bArr, EcgDiagnosis ecgDiagnosis) {
        if (this.vp_n == null) {
            if (this.vp_a == null) {
                this.vp_a = VPOperateManager.mContext;
            }
            if (this.vp_a != null) {
                this.vp_n = new FunctionCheckUtil(VpSpGetUtil.getVpSpVariInstance(this.vp_a), this.vp_a);
            }
        }
        FunctionCheckUtil functionCheckUtil = this.vp_n;
        if (functionCheckUtil != null) {
            this.vp_m = functionCheckUtil.checkEcgDisease();
        }
        if (this.vp_m) {
            int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
            String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
            int i = iArrVp_e[0];
            int i2 = iArrVp_e[1];
            int i3 = iArrVp_e[2];
            int i4 = iArrVp_e[3];
            int i5 = iArrVp_e[4];
            int i6 = iArrVp_e[5];
            int[] iArr = new int[32];
            System.arraycopy(iArrVp_e, 6, iArr, 0, 32);
            int iIntValue = Integer.valueOf(strArrVp_f[39] + strArrVp_f[38], 16).intValue();
            int iIntValue2 = Integer.valueOf(strArrVp_f[41] + strArrVp_f[40], 16).intValue();
            int iIntValue3 = Integer.valueOf(strArrVp_f[43] + strArrVp_f[42], 16).intValue();
            int iIntValue4 = Integer.valueOf(strArrVp_f[45] + strArrVp_f[44], 16).intValue();
            int iIntValue5 = Integer.valueOf(strArrVp_f[49] + strArrVp_f[48] + strArrVp_f[47] + strArrVp_f[46], 16).intValue();
            int iIntValue6 = Integer.valueOf(strArrVp_f[53] + strArrVp_f[52] + strArrVp_f[51] + strArrVp_f[50], 16).intValue();
            ecgDiagnosis.setDiseaseRisk(i);
            ecgDiagnosis.setPressureIndex(i2);
            ecgDiagnosis.setFatigueIndex(i3);
            ecgDiagnosis.setMyocarditisRisk(i4);
            ecgDiagnosis.setChdRisk(i5);
            ecgDiagnosis.setAngioscleroticRisk(i6);
            ecgDiagnosis.setRisk32(iArr);
            ecgDiagnosis.setQrsTime(iIntValue);
            ecgDiagnosis.setQrsAmp(iIntValue2);
            ecgDiagnosis.setPwvMeanVal(iIntValue3);
            ecgDiagnosis.setStMeanAmp(iIntValue4);
            ecgDiagnosis.setDiseaseSdnn(iIntValue5);
            ecgDiagnosis.setDiseaseRmssd(iIntValue6);
        }
    }

    private TimeData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        TimeData timeData = new TimeData();
        timeData.setYear(VpBleByteUtil.twoByteToUnsignedInt(bArr[1], bArr[0]));
        timeData.setMonth(iArrByte2HexToIntArr[2]);
        timeData.setDay(iArrByte2HexToIntArr[3]);
        timeData.setHour(iArrByte2HexToIntArr[4]);
        timeData.setMinute(iArrByte2HexToIntArr[5]);
        timeData.setSecond(iArrByte2HexToIntArr[6]);
        return timeData;
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [boolean, java.lang.Throwable] */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        List list;
        EcgDetectResult ecgDetectResult;
        new StringBuilder().append("手动:单条->value=").append(VpBleByteUtil.byte2HexForShow(bArr));
        byte b = bArr[2];
        if (b == 0) {
            this.vp_c.clear();
            this.vp_c.add(bArr);
            return;
        }
        if (b != 3) {
            if (b == 1) {
                this.vp_c.add(bArr);
                return;
            }
            if (b != 2) {
                return;
            }
            byte[] bArrVp_a = vp_a(this.vp_c);
            new StringBuilder().append("手动:内容-> byteData=").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
            ?? IsSupportEcgMultiDiagnoses = VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses();
            try {
                if (IsSupportEcgMultiDiagnoses != 0) {
                    EcgDiagnosis ecgDiagnosisVp_d = vp_d(bArrVp_a);
                    EcgDiagnosis ecgDiagnosis = ecgDiagnosisVp_d;
                    if ((ecgDiagnosisVp_d.getDuration() - 5) * ecgDiagnosis.getFrequency() <= ecgDiagnosis.getFilterSignals().length) {
                        list = this.vp_e;
                        ecgDetectResult = ecgDiagnosis;
                        list.add(ecgDetectResult);
                    }
                } else {
                    EcgDetectResult ecgDetectResultVp_c = vp_c(bArrVp_a);
                    EcgDetectResult ecgDetectResult2 = ecgDetectResultVp_c;
                    if ((ecgDetectResultVp_c.getDuration() - 5) * ecgDetectResult2.getFrequency() <= ecgDetectResult2.getFilterSignals().length) {
                        list = this.vp_d;
                        ecgDetectResult = ecgDetectResult2;
                        list.add(ecgDetectResult);
                    }
                }
            } catch (Exception unused) {
                IsSupportEcgMultiDiagnoses.printStackTrace();
            }
        }
        vp_a();
    }

    public EcgDiagnosis vp_d(byte[] bArr) {
        EcgDiagnosis ecgDiagnosis = new EcgDiagnosis();
        ecgDiagnosis.setSuccess(true);
        ecgDiagnosis.setType(2);
        ecgDiagnosis.setId(this.vp_h[this.vp_g]);
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        int i = 20;
        int i2 = 1;
        while (byteBufferOrder.position() < length) {
            switch (byteBufferOrder.get()) {
                case -79:
                    byte[] bArrVp_a = vp_a(byteBufferOrder, "0xB1", bArr, byteBufferOrder.get());
                    if (bArrVp_a != null) {
                        if (bArrVp_a.length >= 7) {
                            ecgDiagnosis.setTimeBean(vp_b(bArrVp_a));
                        }
                        if (bArrVp_a.length >= 8) {
                            ecgDiagnosis.setDuration(bArrVp_a[7] & 255);
                        }
                    }
                    break;
                case -78:
                    arrayList.add(vp_c(vp_a(byteBufferOrder, "0xB2", bArr, com.veepoo.protocol.util.vp_g.vp_c(byteBufferOrder.get(), byteBufferOrder.get())), i2));
                    break;
                case -77:
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xB3", bArr, byteBufferOrder.get());
                    if (bArrVp_a2.length >= 14) {
                        vp_b(bArrVp_a2, ecgDiagnosis);
                    }
                    break;
                case -76:
                    byte[] bArrVp_a3 = vp_a(byteBufferOrder, "0xB4", bArr, byteBufferOrder.get());
                    if (bArrVp_a3.length >= 4) {
                        vp_a(bArrVp_a3, ecgDiagnosis);
                    }
                    if (bArrVp_a3.length >= 5) {
                        i = bArrVp_a3[4] & 255;
                    }
                    break;
                case -73:
                    byte[] bArrVp_a4 = vp_a(byteBufferOrder, "0xB7", bArr, byteBufferOrder.get());
                    if (bArrVp_a4.length >= 1) {
                        i2 = bArrVp_a4[0] & 255;
                    }
                    break;
                case -72:
                    vp_c(vp_a(byteBufferOrder, "0xB8", bArr, byteBufferOrder.get()), ecgDiagnosis);
                    break;
            }
        }
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        int[] iArr = new int[size];
        int[] iArr2 = new int[size];
        int[] iArr3 = new int[size];
        int[] iArr4 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            EcgSingleData ecgSingleData = (EcgSingleData) arrayList.get(i3);
            iArr[i3] = ecgSingleData.getHeart();
            iArr2[i3] = ecgSingleData.getBreath();
            iArr3[i3] = ecgSingleData.getHrv();
            iArr4[i3] = ecgSingleData.getQt();
            int[] origin = ecgSingleData.getOrigin();
            if (origin != null) {
                for (int i4 : origin) {
                    arrayList2.add(Integer.valueOf(i4));
                }
            }
        }
        int size2 = arrayList2.size();
        int[] iArr5 = new int[size2];
        for (int i5 = 0; i5 < size2; i5++) {
            iArr5[i5] = ((Integer) arrayList2.get(i5)).intValue();
        }
        String[] strArr = new String[size2];
        for (int i6 = 0; i6 < size2; i6++) {
            strArr[i6] = iArr5[i6] + "$" + i;
        }
        ecgDiagnosis.setDetectHeart(iArr);
        ecgDiagnosis.setDetectBreath(iArr2);
        ecgDiagnosis.setDetectHrv(iArr3);
        ecgDiagnosis.setDetectQT(iArr4);
        int[] iArr6 = new int[size2];
        int[] iArr7 = new int[size2];
        for (int i7 = 0; i7 < size2; i7++) {
            iArr6[i7] = ((Integer) arrayList2.get(i7)).intValue();
            iArr7[i7] = i;
        }
        ecgDiagnosis.setFilterSignals(iArr6);
        ecgDiagnosis.setPowers(iArr7);
        return ecgDiagnosis;
    }

    private void vp_b(byte[] bArr, EcgDetectResult ecgDetectResult) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        ecgDetectResult.setLeadSign(iArrByte2HexToIntArr[0]);
        int[] iArr = {iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8]};
        ecgDetectResult.setResult8(iArr);
        if (this.vp_m) {
            ecgDetectResult.setDiseaseResult(new vp_ac().vp_a(iArr));
        }
        ecgDetectResult.setAveHeart(iArrByte2HexToIntArr[9]);
        ecgDetectResult.setAveResRate(iArrByte2HexToIntArr[10]);
        ecgDetectResult.setAveHrv(iArrByte2HexToIntArr[11]);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        ecgDetectResult.setAveQT(Integer.valueOf(strArrByte2HexToStrArr[13] + strArrByte2HexToStrArr[12], 16).intValue());
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr, int i) {
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + i);
        return vp_a(bArr, i, iPosition);
    }

    public EcgDetectResult vp_c(byte[] bArr) {
        EcgDetectResult ecgDetectResult = new EcgDetectResult();
        ecgDetectResult.setSuccess(true);
        ecgDetectResult.setType(EECGResultType.DATA_FORM_HANDLER);
        ecgDetectResult.setId(this.vp_h[this.vp_g]);
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        int i = 20;
        int i2 = 1;
        while (byteBufferOrder.position() < length) {
            switch (byteBufferOrder.get()) {
                case -79:
                    byte[] bArrVp_a = vp_a(byteBufferOrder, "0xB1", bArr, byteBufferOrder.get());
                    if (bArrVp_a.length >= 7) {
                        ecgDetectResult.setTimeBean(vp_b(bArrVp_a));
                    }
                    if (bArrVp_a.length >= 8) {
                        ecgDetectResult.setDuration(bArrVp_a[7] & 255);
                    }
                    break;
                case -78:
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xB2", bArr, com.veepoo.protocol.util.vp_g.vp_c(byteBufferOrder.get(), byteBufferOrder.get()));
                    if (bArrVp_a2 != null) {
                        arrayList.add(vp_c(bArrVp_a2, i2));
                    }
                    break;
                case -77:
                    byte[] bArrVp_a3 = vp_a(byteBufferOrder, "0xB3", bArr, byteBufferOrder.get());
                    if (bArrVp_a3 != null && bArrVp_a3.length >= 14) {
                        vp_b(bArrVp_a3, ecgDetectResult);
                    }
                    break;
                case -76:
                    byte[] bArrVp_a4 = vp_a(byteBufferOrder, "0xB4", bArr, byteBufferOrder.get());
                    if (bArrVp_a4 != null) {
                        if (bArrVp_a4.length >= 4) {
                            vp_a(bArrVp_a4, ecgDetectResult);
                        }
                        if (bArrVp_a4.length >= 5) {
                            i = bArrVp_a4[4] & 255;
                        }
                    }
                    break;
                case -73:
                    byte[] bArrVp_a5 = vp_a(byteBufferOrder, "0xB7", bArr, byteBufferOrder.get());
                    if (bArrVp_a5 != null && bArrVp_a5.length >= 1) {
                        i2 = bArrVp_a5[0] & 255;
                    }
                    break;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        int[] iArr = new int[size];
        int[] iArr2 = new int[size];
        int[] iArr3 = new int[size];
        int[] iArr4 = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            EcgSingleData ecgSingleData = (EcgSingleData) arrayList.get(i3);
            iArr[i3] = ecgSingleData.getHeart();
            iArr2[i3] = ecgSingleData.getBreath();
            iArr3[i3] = ecgSingleData.getHrv();
            iArr4[i3] = ecgSingleData.getQt();
            int[] origin = ecgSingleData.getOrigin();
            if (origin != null) {
                for (int i4 : origin) {
                    arrayList2.add(Integer.valueOf(i4));
                }
            }
        }
        ecgDetectResult.setDetectHeart(iArr);
        ecgDetectResult.setDetectBreath(iArr2);
        ecgDetectResult.setDetectHrv(iArr3);
        ecgDetectResult.setDetectQT(iArr4);
        int size2 = arrayList2.size();
        int[] iArr5 = new int[size2];
        int[] iArr6 = new int[size2];
        for (int i5 = 0; i5 < size2; i5++) {
            iArr5[i5] = ((Integer) arrayList2.get(i5)).intValue();
            iArr6[i5] = i;
        }
        ecgDetectResult.setOriginSign(iArr5);
        ecgDetectResult.setFilterSignals(iArr5);
        ecgDetectResult.setPowers(iArr6);
        return ecgDetectResult;
    }

    private byte[] vp_a(List list) {
        if (list.isEmpty()) {
            return new byte[0];
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr = (byte[]) list.get(i);
            int length = bArr.length;
            if (length > 5) {
                for (int i2 = 5; i2 < length; i2++) {
                    arrayList.add(Byte.valueOf(bArr[i2]));
                }
            }
        }
        int size = arrayList.size();
        byte[] bArr2 = new byte[size];
        for (int i3 = 0; i3 < size; i3++) {
            bArr2[i3] = ((Byte) arrayList.get(i3)).byteValue();
        }
        return bArr2;
    }

    private EcgSingleData vp_c(byte[] bArr, int i) {
        byte[] bArrVp_a;
        EcgSingleData ecgSingleData = new EcgSingleData();
        int length = bArr.length;
        VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses();
        int eCGType = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            byte b = byteBufferOrder.get();
            if (b == -75) {
                byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xB5", bArr, VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
                ecgSingleData.setOrigin(eCGType == 2 ? vp_b(bArrVp_a2, eCGType) : vp_a(bArrVp_a2, eCGType));
            } else if (b == -74 && (bArrVp_a = vp_a(byteBufferOrder, "0xB6", bArr, byteBufferOrder.get())) != null) {
                if (bArrVp_a.length >= 1) {
                    ecgSingleData.setHeart(bArrVp_a[0] & 255);
                }
                if (bArrVp_a.length >= 2) {
                    ecgSingleData.setBreath(bArrVp_a[1] & 255);
                }
                if (bArrVp_a.length >= 3) {
                    ecgSingleData.setHrv(bArrVp_a[2] & 255);
                }
                if (bArrVp_a.length >= 5) {
                    ecgSingleData.setQt(VpBleByteUtil.twoByteToUnsignedInt(bArrVp_a[4], bArrVp_a[3]));
                }
            }
        }
        new StringBuilder().append("ecg single:").append(ecgSingleData.toString());
        return ecgSingleData;
    }

    private void vp_b(byte[] bArr, EcgDiagnosis ecgDiagnosis) {
        int[] iArrVp_e = com.veepoo.protocol.util.vp_g.vp_e(bArr);
        ecgDiagnosis.setLeadOffType(iArrVp_e[0]);
        int[] iArr = {iArrVp_e[1], iArrVp_e[2], iArrVp_e[3], iArrVp_e[4], iArrVp_e[5], iArrVp_e[6], iArrVp_e[7], iArrVp_e[8]};
        ecgDiagnosis.setDiagnosis8(iArr);
        if (this.vp_m) {
            ecgDiagnosis.setDiseaseResult(new vp_ac().vp_a(iArr));
        }
        ecgDiagnosis.setHeartRate(iArrVp_e[9]);
        ecgDiagnosis.setRespRate(iArrVp_e[10]);
        ecgDiagnosis.setHrv(iArrVp_e[11]);
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        ecgDiagnosis.setQtTime(Integer.valueOf(strArrVp_f[13] + strArrVp_f[12], 16).intValue());
    }

    private int[] vp_b(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return new int[0];
        }
        new StringBuilder().append("blockContentB5:").append(bArr.length);
        new StringBuilder().append("blockContentB5:").append(VpBleByteUtil.byte2HexForShow(bArr));
        int length = bArr.length / 4;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 4;
            byte b = bArr[i3];
            byte b2 = bArr[i3 + 1];
            byte b3 = bArr[i3 + 2];
            byte b4 = bArr[i3 + 3];
            iArr[i2] = EcgUtil.getEcgSignalWithType(((b3 << 16) & 16711680) | ((b2 << 8) & 65280) | (b & 255), i);
        }
        new StringBuilder().append("blockContentB5 data:").append(Arrays.toString(iArr));
        return iArr;
    }

    protected byte[] vp_a(byte b, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -105;
        bArr[1] = b;
        short s = (short) i;
        bArr[2] = VpBleByteUtil.loUint16(s);
        bArr[3] = VpBleByteUtil.hiUint16(s);
        return bArr;
    }

    private void vp_a(byte[] bArr, EcgDetectResult ecgDetectResult) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[0], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[3] + strArrByte2HexToStrArr[2], 16).intValue();
        ecgDetectResult.setFrequency(iIntValue);
        ecgDetectResult.setDrawfrequency(iIntValue2);
    }

    private void vp_a(byte[] bArr, EcgDiagnosis ecgDiagnosis) {
        String[] strArrVp_f = com.veepoo.protocol.util.vp_g.vp_f(bArr);
        int iIntValue = Integer.valueOf(strArrVp_f[1] + strArrVp_f[0], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrVp_f[3] + strArrVp_f[2], 16).intValue();
        ecgDiagnosis.setFrequency(iIntValue);
        ecgDiagnosis.setDrawfrequency(iIntValue2);
    }

    private int[] vp_a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return new int[0];
        }
        new StringBuilder().append("parseBlockContentB5:").append(bArr.length);
        new StringBuilder().append("parseBlockContentB5:").append(com.veepoo.protocol.util.vp_g.vp_d(bArr));
        int length = bArr.length / 2;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            iArr[i2] = EcgUtil.getEcgSignalWithType((short) ((bArr[i3 + 1] << 8) | (bArr[i3] & 255)), i);
        }
        new StringBuilder().append("parseBlockContentB5 data:").append(Arrays.toString(iArr));
        return iArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, boolean z, IECGReadDataListener iECGReadDataListener) {
        int i;
        this.vp_j = bluetoothClient;
        this.vp_k = str;
        this.vp_l = bleWriteResponse;
        this.vp_h = iArr;
        this.vp_i = iECGReadDataListener;
        this.vp_m = z;
        this.vp_g = 0;
        new StringBuilder().append("手动:ID列表->").append(Arrays.toString(this.vp_h));
        int[] iArr2 = this.vp_h;
        if (iArr2 == null || iArr2.length == 0 || (iArr2.length == 1 && ((i = iArr2[0]) == 65535 || i == 0))) {
            if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses()) {
                iECGReadDataListener.readDiagnosisDataFinish(Collections.EMPTY_LIST);
                return;
            } else {
                iECGReadDataListener.readDataFinish(Collections.EMPTY_LIST);
                return;
            }
        }
        this.vp_f = iArr2.length;
        List list = this.vp_d;
        if (list != null && !list.isEmpty()) {
            this.vp_d.clear();
        }
        List list2 = this.vp_e;
        if (list2 != null && !list2.isEmpty()) {
            this.vp_e.clear();
        }
        vp_a(iArr[0]);
    }

    private void vp_a(int i) {
        byte[] bArrVp_a = vp_a((byte) 2, i);
        new StringBuilder().append("readEcgDataById: cmdECGData：").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        super.send(bArrVp_a, this.vp_j, this.vp_k, this.vp_l);
    }

    private void vp_a() {
        int i = this.vp_g + 1;
        this.vp_g = i;
        if (i < this.vp_f) {
            vp_a(this.vp_h[i]);
        } else if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isSupportEcgMultiDiagnoses()) {
            this.vp_i.readDiagnosisDataFinish(this.vp_e);
        } else {
            this.vp_i.readDataFinish(this.vp_d);
        }
    }
}
