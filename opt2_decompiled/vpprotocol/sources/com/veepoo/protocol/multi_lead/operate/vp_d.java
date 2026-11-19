package com.veepoo.protocol.multi_lead.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.model.enums.EMultiEcgDataType;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.multi_lead.data.DiseaseInfo;
import com.veepoo.protocol.multi_lead.data.EcgRRDataInfo;
import com.veepoo.protocol.multi_lead.data.EcgWaveformsInfo;
import com.veepoo.protocol.multi_lead.data.MultiEcgDataResult;
import com.veepoo.protocol.multi_lead.data.MultiEcgWaveBaseInfo;
import com.veepoo.protocol.multi_lead.enums.EDisease;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgReadDataListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.EcgUtil;
import com.veepoo.protocol.util.UiUpdateUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_g;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_d.class */
public class vp_d extends com.veepoo.protocol.vp_a {
    int[] vp_e;
    IMultiEcgReadDataListener vp_f;
    BluetoothClient vp_g;
    String vp_h;
    BleWriteResponse vp_i;
    EMultiEcgDataType vp_j;
    int vp_c = 0;
    int vp_d = 0;
    private int vp_k = 0;
    private int vp_l = 0;
    List vp_m = Collections.synchronizedList(new ArrayList());

    private List vp_b(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        EcgRRDataInfo ecgRRDataInfo = null;
        while (byteBufferOrder.position() < bArr.length) {
            byte b = byteBufferOrder.get();
            if (b == -32) {
                byteBufferOrder.get();
                int iLittleEndianByteArray2Int = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()});
                EcgRRDataInfo ecgRRDataInfo2 = new EcgRRDataInfo();
                ecgRRDataInfo2.setOffsetTime(iLittleEndianByteArray2Int);
                ecgRRDataInfo = ecgRRDataInfo2;
            } else if (b == -31) {
                byte[] bArrVp_a = vp_a(byteBufferOrder, "0xE1", bArr, VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()));
                if (bArrVp_a.length != 0) {
                    new StringBuilder().append("blockContentE1:").append(vp_g.vp_d(bArrVp_a));
                    int length = bArrVp_a.length / 2;
                    int[] iArr = new int[length];
                    for (int i = 0; i < length; i++) {
                        int i2 = i * 2;
                        iArr[i] = VpBleByteUtil.twoByteToUnsignedInt2(bArrVp_a[i2], bArrVp_a[i2 + 1]) * 4;
                    }
                    if (ecgRRDataInfo != null) {
                        ecgRRDataInfo.setRrValues(iArr);
                    }
                }
                arrayList.add(ecgRRDataInfo);
            }
        }
        return arrayList;
    }

    private EcgWaveformsInfo vp_c(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        EcgWaveformsInfo ecgWaveformsInfo = new EcgWaveformsInfo();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -16:
                    byteBufferOrder.get();
                    ecgWaveformsInfo.setWaveCount(byteBufferOrder.get() & 255);
                    break;
                case -15:
                    byte[] bArrVp_a = vp_a(byteBufferOrder, "0xF1", bArr, byteBufferOrder.get() & 255);
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    for (int i = 0; i < bArrVp_a.length; i++) {
                        arrayList.add(Integer.valueOf(bArrVp_a[0] & 255));
                    }
                    ecgWaveformsInfo.setWaveTypeArr(arrayList);
                    break;
                case -14:
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xF2", bArr, byteBufferOrder.get() & 255);
                    ArrayList<Integer> arrayList2 = new ArrayList<>();
                    int length = bArrVp_a2.length / 2;
                    for (int i2 = 0; i2 < length; i2++) {
                        int i3 = i2 * 2;
                        arrayList2.add(Integer.valueOf(VpBleByteUtil.twoByteToUnsignedInt(bArrVp_a2[i3], bArrVp_a2[i3 + 1])));
                    }
                    ecgWaveformsInfo.setWavePositionArr(arrayList2);
                    break;
            }
        }
        return ecgWaveformsInfo;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.io.ByteArrayOutputStream, java.io.OutputStream, java.lang.Throwable] */
    private byte[] vp_a(List list) {
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

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IMultiEcgReadDataListener iMultiEcgReadDataListener;
        new StringBuilder().append("多导:单条->value=").append(VpBleByteUtil.byte2HexForShow(bArr));
        int iLittleEndianByteArray2Int = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{bArr[2], bArr[3], bArr[4], bArr[5]});
        if (iLittleEndianByteArray2Int == 0) {
            this.vp_m.clear();
            this.vp_k = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{bArr[8], bArr[9], bArr[10], bArr[11]});
            new StringBuilder().append("------------------------------------totalPkgNum------------------------------------").append(this.vp_k);
        } else if (this.vp_l + 1 != iLittleEndianByteArray2Int && (iMultiEcgReadDataListener = this.vp_f) != null) {
            iMultiEcgReadDataListener.onReadDataFail();
        }
        this.vp_l = iLittleEndianByteArray2Int;
        int length = bArr.length - 6;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 6, bArr2, 0, length);
        this.vp_m.add(bArr2);
        float f = (iLittleEndianByteArray2Int / this.vp_k) * 100.0f;
        int i = this.vp_d;
        int[] iArr = this.vp_e;
        if (i < iArr.length) {
            this.vp_f.onReadDataProgress(iArr[i], f);
        }
        if (iLittleEndianByteArray2Int + 1 == this.vp_k) {
            MultiEcgDataResult multiEcgDataResultVp_a = vp_a();
            if (multiEcgDataResultVp_a != null) {
                this.vp_f.onReadDataChange(multiEcgDataResultVp_a);
            }
            vp_b();
        }
    }

    private MultiEcgWaveBaseInfo vp_b(int i, byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        MultiEcgWaveBaseInfo multiEcgWaveBaseInfo = new MultiEcgWaveBaseInfo();
        int eCGType = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case UiUpdateUtil.ACTION_RECODING /* -48 */:
                    byteBufferOrder.get();
                    multiEcgWaveBaseInfo.setOffset(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -47:
                    multiEcgWaveBaseInfo.setWaveformsInfo(vp_c(vp_a(byteBufferOrder, "0xD1", bArr, byteBufferOrder.get())));
                    break;
                case -46:
                    byte[] bArrVp_a = vp_a(byteBufferOrder, "0xD2", bArr, VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    multiEcgWaveBaseInfo.setEcgI(i == 0 ? vp_b(bArrVp_a, eCGType) : vp_a(bArrVp_a, eCGType));
                    break;
                case -45:
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xD3", bArr, VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    multiEcgWaveBaseInfo.setEcgII(i == 0 ? vp_b(bArrVp_a2, eCGType) : vp_a(bArrVp_a2, eCGType));
                    break;
                case -44:
                    int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(vp_a(byteBufferOrder, "0xD4", bArr, byteBufferOrder.get() & 255));
                    if (iArrByte2HexToIntArr != null && iArrByte2HexToIntArr.length > 6 && multiEcgWaveBaseInfo.getEcgI().length != 0) {
                        int length = multiEcgWaveBaseInfo.getEcgI().length;
                        int[] iArr = new int[length];
                        int length2 = multiEcgWaveBaseInfo.getEcgI().length / 6;
                        new StringBuilder().append("getBlockContent[0xD4]  frequency = ").append(length2).append(",ecgLength = ").append(multiEcgWaveBaseInfo.getEcgI().length);
                        for (int i2 = 0; i2 < length; i2++) {
                            iArr[i2] = iArrByte2HexToIntArr[i2 / length2];
                        }
                        multiEcgWaveBaseInfo.setPower(iArr);
                    }
                    break;
            }
        }
        return multiEcgWaveBaseInfo;
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private int[] vp_b(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return new int[0];
        }
        new StringBuilder().append("parseEcgSignalBlockContent:").append(bArr.length);
        new StringBuilder().append("parseEcgSignalBlockContent:").append(VpBleByteUtil.byte2HexForShow(bArr));
        int length = bArr.length / 4;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 4;
            iArr[i2] = EcgUtil.getEcgSignalWithType(((bArr[i3] << 24) & (-16777216)) | ((bArr[i3 + 1] << 16) & 16711680) | ((bArr[i3 + 2] << 8) & 65280) | (bArr[i3 + 3] & 255), i);
        }
        new StringBuilder().append("blockContentB5 data:").append(Arrays.toString(iArr));
        return iArr;
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        int iLittleEndianByteArray2Int = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get()});
        int iPosition = byteBuffer.position();
        int i = iPosition + iLittleEndianByteArray2Int;
        new StringBuilder().append("getBlockContent[").append(str).append("]  startPosition = ").append(iPosition).append(",endPosition = ").append(i).append(",length = ").append(iLittleEndianByteArray2Int);
        byteBuffer.position(i);
        return vp_a(bArr, iLittleEndianByteArray2Int, iPosition);
    }

    private void vp_b() {
        int i;
        int i2 = this.vp_d + 1;
        this.vp_d = i2;
        if (i2 >= this.vp_c || (i = this.vp_e[i2]) == 65535 || i == 0) {
            this.vp_f.onReadComplete();
        } else {
            vp_a(i);
        }
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr, int i) {
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + i);
        return vp_a(bArr, i, iPosition);
    }

    protected byte[] vp_a(byte b, int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -105;
        bArr[1] = b;
        short s = (short) i;
        bArr[2] = VpBleByteUtil.loUint16(s);
        bArr[3] = VpBleByteUtil.hiUint16(s);
        EMultiEcgDataType eMultiEcgDataType = this.vp_j;
        if (eMultiEcgDataType == EMultiEcgDataType.MAIN_DATA) {
            bArr[4] = 1;
        } else if (eMultiEcgDataType == EMultiEcgDataType.DETAIL_DATA) {
            bArr[4] = 2;
        } else {
            bArr[4] = 0;
        }
        return bArr;
    }

    private int[] vp_a(int[] iArr, int[] iArr2, int i) {
        int iMin = Math.min(iArr.length, iArr2.length);
        int[] iArr3 = new int[iMin];
        switch (i) {
            case 0:
                return iArr;
            case 1:
                return iArr2;
            case 2:
                for (int i2 = 0; i2 < iMin; i2++) {
                    iArr3[i2] = iArr[i2] - iArr2[i2];
                }
                return iArr3;
            case 3:
                for (int i3 = 0; i3 < iMin; i3++) {
                    iArr3[i3] = (iArr[i3] + iArr2[i3]) / (-2);
                }
                return iArr3;
            case 4:
                for (int i4 = 0; i4 < iMin; i4++) {
                    iArr3[i4] = iArr2[i4] - (iArr[i4] / 2);
                }
                return iArr3;
            default:
                for (int i5 = 0; i5 < iMin; i5++) {
                    iArr3[i5] = iArr[i5] - (iArr2[i5] / 2);
                }
                return iArr3;
        }
    }

    private void vp_a(byte[] bArr, MultiEcgDataResult multiEcgDataResult) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -80:
                    byteBufferOrder.get();
                    int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    int i = byteBufferOrder.get() & 255;
                    int i2 = byteBufferOrder.get() & 255;
                    int i3 = byteBufferOrder.get() & 255;
                    int i4 = byteBufferOrder.get() & 255;
                    int i5 = byteBufferOrder.get() & 255;
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(iTwoByteToUnsignedInt2, i - 1, i2, i3, i4, i5);
                    calendar.set(14, 0);
                    multiEcgDataResult.setStartTime(calendar.getTimeInMillis());
                    break;
                case -79:
                    byteBufferOrder.get();
                    multiEcgDataResult.setDuration(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
                    break;
                case -78:
                    byteBufferOrder.get();
                    int i6 = byteBufferOrder.get() & 255;
                    int iTwoByteToUnsignedInt3 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    int i7 = byteBufferOrder.get() & 255;
                    multiEcgDataResult.setAvgHeart(i6);
                    multiEcgDataResult.setAvgQt(iTwoByteToUnsignedInt3);
                    multiEcgDataResult.setAvgHrv(i7);
                    break;
                case -77:
                    byteBufferOrder.get();
                    byte[] bArr2 = {byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()};
                    long j = 0;
                    for (int i8 = 0; i8 < 5; i8++) {
                        j |= ((long) (bArr2[i8] & 255)) << ((4 - i8) * 8);
                    }
                    multiEcgDataResult.setDiseaseList(EDisease.INSTANCE.getDiseasesFromBitMask(j));
                    break;
                case -76:
                    byteBufferOrder.get();
                    multiEcgDataResult.setAdcModel(byteBufferOrder.get() & 255);
                    break;
                case -75:
                    int i9 = byteBufferOrder.get() & 4095;
                    int iTwoByteToUnsignedInt4 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    Log.e("Test", "b5Len == " + i9);
                    Log.e("Test", "frequency == " + iTwoByteToUnsignedInt4);
                    multiEcgDataResult.setFrequency(iTwoByteToUnsignedInt4);
                    break;
                case -74:
                    multiEcgDataResult.setLastlyEcgBaseInfo(vp_b(multiEcgDataResult.getAdcModel(), vp_a(byteBufferOrder, "0xB6", bArr)));
                    break;
            }
        }
    }

    private List vp_a(int i, byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        DiseaseInfo diseaseInfo = null;
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case -64:
                    byteBufferOrder.get();
                    int i2 = byteBufferOrder.get() & 255;
                    DiseaseInfo diseaseInfo2 = new DiseaseInfo();
                    diseaseInfo2.setDiseaseType(EDisease.INSTANCE.fromValue(i2));
                    diseaseInfo = diseaseInfo2;
                    break;
                case -63:
                    DiseaseInfo diseaseInfo3 = diseaseInfo;
                    byteBufferOrder.get();
                    int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    if (diseaseInfo3 != null) {
                        diseaseInfo.setDiseaseTimes(iTwoByteToUnsignedInt2);
                    }
                    break;
                case -62:
                    DiseaseInfo diseaseInfo4 = diseaseInfo;
                    byteBufferOrder.get();
                    int i3 = byteBufferOrder.get() & 255;
                    if (diseaseInfo4 != null) {
                        diseaseInfo.setDiseaseLevel(i3);
                    }
                    break;
                case -61:
                    DiseaseInfo diseaseInfo5 = diseaseInfo;
                    byteBufferOrder.get();
                    if (diseaseInfo5 != null) {
                        diseaseInfo.setAvgHeart(byteBufferOrder.get() & 255);
                        diseaseInfo.setAvgQT(VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get()));
                        diseaseInfo.setAvgHrv(byteBufferOrder.get() & 255);
                    }
                    break;
                case -60:
                    DiseaseInfo diseaseInfo6 = diseaseInfo;
                    MultiEcgWaveBaseInfo multiEcgWaveBaseInfoVp_b = vp_b(i, vp_a(byteBufferOrder, "0xC4", bArr));
                    if (diseaseInfo6 != null) {
                        DiseaseInfo diseaseInfo7 = diseaseInfo;
                        diseaseInfo.setDiseaseOffset(multiEcgWaveBaseInfoVp_b.getOffset());
                        diseaseInfo.setDiseaseEcgI(multiEcgWaveBaseInfoVp_b.getEcgI());
                        diseaseInfo.setDiseaseEcgII(multiEcgWaveBaseInfoVp_b.getEcgII());
                        diseaseInfo.setDiseasePowers(multiEcgWaveBaseInfoVp_b.getPower());
                        diseaseInfo.setDiseaseEcgIII(vp_a(multiEcgWaveBaseInfoVp_b.getEcgI(), multiEcgWaveBaseInfoVp_b.getEcgII(), 2));
                        diseaseInfo.setDiseaseEcgAVR(vp_a(multiEcgWaveBaseInfoVp_b.getEcgI(), multiEcgWaveBaseInfoVp_b.getEcgII(), 3));
                        diseaseInfo.setDiseaseEcgAVL(vp_a(multiEcgWaveBaseInfoVp_b.getEcgI(), multiEcgWaveBaseInfoVp_b.getEcgII(), 4));
                        diseaseInfo7.setDiseaseEcgAVF(vp_a(multiEcgWaveBaseInfoVp_b.getEcgI(), multiEcgWaveBaseInfoVp_b.getEcgII(), 5));
                        arrayList.add(diseaseInfo7);
                    }
                    break;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.veepoo.protocol.multi_lead.data.MultiEcgDataResult] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.veepoo.protocol.multi_lead.data.MultiEcgDataResult] */
    /* JADX WARN: Type inference failed for: r0v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v20, types: [com.veepoo.protocol.multi_lead.operate.vp_d] */
    /* JADX WARN: Type inference failed for: r0v9, types: [byte] */
    private MultiEcgDataResult vp_a() {
        byte[] bArrVp_a = vp_a(this.vp_m);
        new StringBuilder().append("concatenate[").append(bArrVp_a.length).append("]");
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArrVp_a).order(ByteOrder.LITTLE_ENDIAN);
        MultiEcgDataResult multiEcgDataResult = new MultiEcgDataResult();
        multiEcgDataResult.setId(this.vp_e[this.vp_d]);
        multiEcgDataResult.setEcgType(VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType());
        while (byteBufferOrder.position() < bArrVp_a.length) {
            ?? r0 = byteBufferOrder.get();
            switch (r0) {
                case -96:
                    byteBufferOrder.position(byteBufferOrder.position() + byteBufferOrder.get());
                    break;
                case EUIFromType.UICode.A_GPS /* -95 */:
                case EUIFromType.UICode.G15ImgAPPDownloadAppQRCode /* -94 */:
                    byteBufferOrder.get();
                    if (byteBufferOrder.get() == -95) {
                        return null;
                    }
                    break;
                case EUIFromType.UICode.G15ImgProfile /* -93 */:
                    try {
                        r0 = this;
                        r0.vp_a(r0.vp_a(byteBufferOrder, "0xA3", bArrVp_a), multiEcgDataResult);
                    } catch (Exception unused) {
                        r0.printStackTrace();
                    }
                    break;
                case EUIFromType.UICode.G15ImgTheme1 /* -92 */:
                    try {
                        r0 = multiEcgDataResult;
                        r0.setDiseaseInfoList(vp_a(multiEcgDataResult.getAdcModel(), vp_a(byteBufferOrder, "0xA4", bArrVp_a)));
                    } catch (Exception unused2) {
                        r0.printStackTrace();
                    }
                    break;
                case EUIFromType.UICode.G15ImgTheme2 /* -91 */:
                    try {
                        r0 = multiEcgDataResult;
                        r0.setRrDataInfoList(vp_b(vp_a(byteBufferOrder, "0xA5", bArrVp_a)));
                    } catch (Exception unused3) {
                        r0.printStackTrace();
                    }
                    break;
            }
        }
        return multiEcgDataResult;
    }

    private int[] vp_a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return new int[0];
        }
        new StringBuilder().append("parseEcgSignalBlockContent:").append(bArr.length);
        new StringBuilder().append("parseEcgSignalBlockContent:").append(vp_g.vp_d(bArr));
        int length = bArr.length / 2;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            iArr[i2] = EcgUtil.getEcgSignalWithType(((bArr[i3 + 1] << 8) & 65280) | (bArr[i3] & 255), i);
        }
        new StringBuilder().append("parseBlockContent data:").append(Arrays.toString(iArr));
        return iArr;
    }

    private void vp_a(int i) {
        byte[] bArrVp_a = vp_a((byte) 5, i);
        new StringBuilder().append("readEcgDataById: cmdECGData：").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        if (vp_e.vp_j) {
            return;
        }
        super.send(bArrVp_a, this.vp_g, this.vp_h, this.vp_i);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, EMultiEcgDataType eMultiEcgDataType, IMultiEcgReadDataListener iMultiEcgReadDataListener) {
        int i;
        this.vp_g = bluetoothClient;
        this.vp_h = str;
        this.vp_i = bleWriteResponse;
        this.vp_e = iArr;
        this.vp_j = eMultiEcgDataType;
        this.vp_f = iMultiEcgReadDataListener;
        this.vp_d = 0;
        new StringBuilder().append("多导:ID列表->").append(Arrays.toString(this.vp_e));
        int[] iArr2 = this.vp_e;
        if (iArr2 == null || iArr2.length == 0 || (iArr2.length == 1 && ((i = iArr2[0]) == 65535 || i == 0))) {
            iMultiEcgReadDataListener.onReadComplete();
        } else {
            this.vp_c = iArr2.length;
            vp_a(iArr[0]);
        }
    }
}
