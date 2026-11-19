package com.veepoo.protocol.multi_lead.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.multi_lead.data.EcgDiseaseInfo;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadDetectResult;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadDetectState;
import com.veepoo.protocol.multi_lead.data.EcgMultiLeadPreInfo;
import com.veepoo.protocol.multi_lead.data.EcgWaveformsInfo;
import com.veepoo.protocol.multi_lead.enums.EDisease;
import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import com.veepoo.protocol.multi_lead.listener.IECGMultiLeadDetectListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_g;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_a.class */
public final class vp_a extends com.veepoo.protocol.vp_a {
    private IECGMultiLeadDetectListener vp_c;
    private TimeData vp_d;
    private long vp_e;
    private boolean vp_i;
    private byte vp_f = 7;
    private byte vp_g = 1;
    private byte vp_h = 2;
    private int vp_j = 250;
    private List vp_k = new ArrayList();
    private final List vp_l = new ArrayList();

    private final List vp_d(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            byte[] bArr2 = new byte[751];
            System.arraycopy(bArr, i * 751, bArr2, 0, 751);
            EcgWaveformsInfo ecgWaveformsInfo = new EcgWaveformsInfo();
            byte b = bArr2[0];
            ArrayList<Integer> arrayList2 = new ArrayList<>();
            ArrayList<Integer> arrayList3 = new ArrayList<>();
            int i2 = 0;
            while (i2 < 250) {
                int i3 = i2;
                int i4 = i2 + 1;
                i2 = i4;
                arrayList2.add(Integer.valueOf(bArr2[i4] & 255));
                int i5 = i3 * 2;
                arrayList3.add(Integer.valueOf(VpBleByteUtil.twoByteToUnsignedInt(bArr2[i5 + 252], bArr2[i5 + 251])));
            }
            ecgWaveformsInfo.setFlag(ELeadFlag.values()[i]);
            ecgWaveformsInfo.setWaveCount(b);
            ecgWaveformsInfo.setWaveTypeArr(arrayList2);
            ecgWaveformsInfo.setWavePositionArr(arrayList3);
            arrayList.add(ecgWaveformsInfo);
        }
        return arrayList;
    }

    private final int[] vp_c(byte[] bArr) {
        int[] iArr = new int[27];
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            byte b = bArr[i2];
            for (int i3 = 0; i3 < 8 && i < 27; i3++) {
                iArr[i] = (b >> (7 - i3)) & 1;
                i++;
            }
        }
        return iArr;
    }

    private final EcgMultiLeadPreInfo vp_g(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgMultiLeadPreInfo();
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        Integer numValueOf = Integer.valueOf(strArrByte2HexToStrArr[5] + strArrByte2HexToStrArr[4], 16);
        byte b = bArr[6];
        byte b2 = bArr[7];
        Intrinsics.checkNotNull(numValueOf);
        return new EcgMultiLeadPreInfo(numValueOf.intValue(), b, b2);
    }

    private final int vp_a(int i) {
        return ((i >> 23) & 1) == 0 ? i & 8388607 : i | (-8388608);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ca(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_ca(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = this.vp_f;
        bArr[2] = this.vp_h;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        super.handler(bArr);
        String str = "**ecg---Handlers-->" + vp_g.vp_d(bArr);
        String str2 = "**ecg---size-->" + bArr.length;
        IECGMultiLeadDetectListener iECGMultiLeadDetectListener = this.vp_c;
        if (iECGMultiLeadDetectListener == null) {
            return;
        }
        byte b = bArr[3];
        byte b2 = bArr[2];
        if (b == 0 && b2 == 1) {
            EcgMultiLeadPreInfo ecgMultiLeadPreInfoVp_g = vp_g(bArr);
            this.vp_j = ecgMultiLeadPreInfoVp_g.getFrequency();
            TimeData timeData = new TimeData();
            this.vp_d = timeData;
            Intrinsics.checkNotNull(timeData);
            timeData.setCurrentTime();
            this.vp_e = System.currentTimeMillis();
            String str3 = "ECGDetectOprate frequency ：" + this.vp_j;
            IECGMultiLeadDetectListener iECGMultiLeadDetectListener2 = this.vp_c;
            Intrinsics.checkNotNull(iECGMultiLeadDetectListener2);
            iECGMultiLeadDetectListener2.onEcgDetectPreInfoChange(ecgMultiLeadPreInfoVp_g);
            return;
        }
        if (b == 1) {
            EcgMultiLeadDetectState ecgMultiLeadDetectStateVp_f = vp_f(bArr);
            IECGMultiLeadDetectListener iECGMultiLeadDetectListener3 = this.vp_c;
            Intrinsics.checkNotNull(iECGMultiLeadDetectListener3);
            iECGMultiLeadDetectListener3.onEcgDetectStateChange(ecgMultiLeadDetectStateVp_f);
            return;
        }
        if (b == 2) {
            Intrinsics.checkNotNull(iECGMultiLeadDetectListener);
            iECGMultiLeadDetectListener.onEcgDetectFail();
            return;
        }
        if (b == 4) {
            Intrinsics.checkNotNull(iECGMultiLeadDetectListener);
            iECGMultiLeadDetectListener.onEcgDetectSuccess();
            return;
        }
        if (b == 3) {
            int i = bArr[4] & 255;
            int i2 = bArr[5] & 255;
            if (i == 1) {
                this.vp_k.clear();
            }
            this.vp_k.add(bArr);
            if (i == i2) {
                EcgMultiLeadDetectResult ecgMultiLeadDetectResultVp_e = vp_e(vp_a());
                ecgMultiLeadDetectResultVp_e.setTimeBean(this.vp_d);
                ecgMultiLeadDetectResultVp_e.setDuration((int) ((System.currentTimeMillis() - this.vp_e) / ((long) 1000)));
                ecgMultiLeadDetectResultVp_e.setFrequency(this.vp_j);
                ecgMultiLeadDetectResultVp_e.setType(EECGResultType.DATA_FORM_APP);
                IECGMultiLeadDetectListener iECGMultiLeadDetectListener4 = this.vp_c;
                Intrinsics.checkNotNull(iECGMultiLeadDetectListener4);
                iECGMultiLeadDetectListener4.onDiseaseDiagnosisResults(ecgMultiLeadDetectResultVp_e);
            }
        }
    }

    public final EcgMultiLeadDetectResult vp_e(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgMultiLeadDetectResult();
        }
        EcgMultiLeadDetectResult ecgMultiLeadDetectResult = new EcgMultiLeadDetectResult();
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.get();
        while (byteBufferOrder.position() < length) {
            byte b = byteBufferOrder.get();
            if (b == -96) {
                byteBufferOrder.get();
                int i = byteBufferOrder.get() & 255;
                int iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get());
                int i2 = byteBufferOrder.get() & 255;
                ecgMultiLeadDetectResult.setAvgHeart(i);
                ecgMultiLeadDetectResult.setAvgQT(iTwoByteToUnsignedInt);
                ecgMultiLeadDetectResult.setAvgHRV(i2);
            } else if (b == -95) {
                int i3 = byteBufferOrder.get();
                int iPosition = byteBufferOrder.position();
                byteBufferOrder.position(iPosition + i3);
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, iPosition, bArr2, 0, i3);
                int[] iArrVp_c = vp_c(bArr2);
                if (this.vp_i) {
                    ecgMultiLeadDetectResult.setDiseaseType(iArrVp_c);
                }
            } else if (b == -94) {
                int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get());
                byte[] bArr3 = new byte[iTwoByteToUnsignedInt2];
                int iPosition2 = byteBufferOrder.position();
                byteBufferOrder.position(iPosition2 + iTwoByteToUnsignedInt2);
                System.arraycopy(bArr, iPosition2, bArr3, 0, iTwoByteToUnsignedInt2);
                List<EcgDiseaseInfo> listVp_b = vp_b(bArr3);
                if (this.vp_i) {
                    ecgMultiLeadDetectResult.setDiseaseInfoList(listVp_b);
                }
            } else if (b == -93) {
                int iTwoByteToUnsignedInt3 = VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get());
                byte[] bArr4 = new byte[iTwoByteToUnsignedInt3];
                int iPosition3 = byteBufferOrder.position();
                byteBufferOrder.position(iPosition3 + iTwoByteToUnsignedInt3);
                System.arraycopy(bArr, iPosition3, bArr4, 0, iTwoByteToUnsignedInt3);
                if (this.vp_i) {
                    ecgMultiLeadDetectResult.setWaveformsInfoList(vp_d(bArr4));
                }
            }
        }
        return ecgMultiLeadDetectResult;
    }

    public final EcgMultiLeadDetectState vp_f(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new EcgMultiLeadDetectState();
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int[] iArr = new int[6];
        for (int i = 0; i < 6; i++) {
            iArr[i] = (bArr[4] >> i) & 1;
        }
        int i2 = iArrByte2HexToIntArr[5];
        int i3 = iArrByte2HexToIntArr[6];
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        Integer numValueOf = Integer.valueOf(strArrByte2HexToStrArr[8] + strArrByte2HexToStrArr[7], 16);
        int i4 = iArrByte2HexToIntArr[9];
        Intrinsics.checkNotNull(numValueOf);
        return new EcgMultiLeadDetectState(iArr, i2, i3, i4, numValueOf.intValue());
    }

    public final int vp_b(int i) {
        return ((i >> 21) & 1) == 0 ? i & 2097151 : i | (-2097152);
    }

    private final List vp_b(byte[] bArr) {
        Log.e("Test", "getDiseaseInfo-->" + VpBleByteUtil.byte2HexForShow(bArr));
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 27; i++) {
            byte[] bArr2 = new byte[25];
            System.arraycopy(bArr, i * 25, bArr2, 0, 25);
            Log.e("Test", "getDiseaseInfo-->" + i + "-->" + VpBleByteUtil.byte2HexForShow(bArr2));
            EcgDiseaseInfo ecgDiseaseInfo = new EcgDiseaseInfo();
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr2).order(ByteOrder.LITTLE_ENDIAN);
            byte b = 0;
            while (byteBufferOrder.position() < 25) {
                byte b2 = byteBufferOrder.get();
                if (b2 == -80) {
                    byteBufferOrder.get();
                    byteBufferOrder.get();
                } else if (b2 == -79) {
                    byteBufferOrder.get();
                    EDisease eDiseaseFromValue = EDisease.INSTANCE.fromValue(byteBufferOrder.get());
                    Intrinsics.checkNotNull(eDiseaseFromValue);
                    ecgDiseaseInfo.setType(eDiseaseFromValue);
                } else if (b2 == -78) {
                    byteBufferOrder.get();
                    b = byteBufferOrder.get();
                } else if (b2 == -77) {
                    byte b3 = b;
                    VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get());
                    if (b3 == 0) {
                        int iPosition = byteBufferOrder.position();
                        int i2 = iPosition + 13;
                        if (i2 <= 25) {
                            ecgDiseaseInfo.setProd(bArr2[iPosition]);
                            ecgDiseaseInfo.setLen(bArr2[iPosition + 1]);
                            ecgDiseaseInfo.setRiskLevel(bArr2[iPosition + 2]);
                            int[] iArr = new int[5];
                            for (int i3 = 0; i3 < 5; i3++) {
                                int i4 = iPosition + (i3 * 2);
                                iArr[i3] = VpBleByteUtil.twoByteToUnsignedInt(bArr2[i4 + 4], bArr2[i4 + 3]);
                            }
                            ecgDiseaseInfo.setPosition(iArr);
                        }
                        byteBufferOrder.position(i2);
                    }
                    arrayList.add(ecgDiseaseInfo);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:31:0x00fd  */
    private final int[] vp_a(byte[] bArr, int i) {
        int length = bArr.length / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 3;
            String str = strArrVp_f[i3] + strArrVp_f[i3 + 1] + strArrVp_f[i3 + 2];
            Integer numValueOf = Integer.valueOf(str, 16);
            if (i != 7) {
                if (i == 10 || i == 15) {
                    if (numValueOf != null && numValueOf.intValue() == 8421504) {
                        iArr[i2] = Integer.MAX_VALUE;
                    } else {
                        Locale locale = Locale.ROOT;
                        Intrinsics.checkNotNullExpressionValue(locale, "ROOT");
                        String upperCase = str.toUpperCase(locale);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                        if (Intrinsics.areEqual(upperCase, "0x808080")) {
                            iArr[i2] = Integer.MAX_VALUE;
                        } else {
                            Intrinsics.checkNotNull(numValueOf);
                            iArr[i2] = vp_b(numValueOf.intValue());
                        }
                    }
                }
            } else if (numValueOf != null && numValueOf.intValue() == 16777215) {
                iArr[i2] = Integer.MAX_VALUE;
            } else {
                Locale locale2 = Locale.ROOT;
                Intrinsics.checkNotNullExpressionValue(locale2, "ROOT");
                String upperCase2 = str.toUpperCase(locale2);
                Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                if (Intrinsics.areEqual(upperCase2, "FFFFFF")) {
                    iArr[i2] = Integer.MAX_VALUE;
                } else {
                    Intrinsics.checkNotNull(numValueOf);
                    iArr[i2] = vp_a(numValueOf.intValue());
                }
            }
        }
        return iArr;
    }

    private final byte[] vp_a() {
        int length = 0;
        int i = 0;
        for (Object obj : this.vp_k) {
            int i2 = i;
            i++;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            length += ((byte[]) obj).length - 6;
        }
        byte[] bArr = new byte[length];
        int length2 = 0;
        int size = this.vp_k.size();
        for (int i3 = 0; i3 < size; i3++) {
            byte[] bArr2 = (byte[]) this.vp_k.get(i3);
            System.arraycopy(bArr2, 6, bArr, length2, bArr2.length - 6);
            length2 += bArr2.length - 6;
        }
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.multi_lead.enums.ELeadFlag] */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        ?? length = bArr.length;
        if (length < 20) {
            return;
        }
        try {
            ELeadFlag.Companion companion = ELeadFlag.INSTANCE;
            length = companion.fromValue(bArr[1]);
            if (length == 0) {
                return;
            }
            ELeadFlag eLeadFlagFromValue = companion.fromValue(bArr[1]);
            int iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(bArr[bArr.length - 1], bArr[bArr.length - 2]);
            int eCGType = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
            boolean z = eCGType == 10 || eCGType == 15;
            byte b = !z ? (byte) 12 : bArr[2];
            int[] iArrVp_a = vp_a(ArraysKt.copyOfRange(bArr, z ? 3 : 2, (bArr.length - (z ? 5 : 4)) + (z ? 3 : 2)), eCGType);
            ArrayList arrayList = new ArrayList();
            for (int i : iArrVp_a) {
                if (i != Integer.MAX_VALUE) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            if (eLeadFlagFromValue == ELeadFlag.I) {
                this.vp_l.clear();
                this.vp_l.addAll(CollectionsKt.toMutableList(arrayList));
            }
            IECGMultiLeadDetectListener iECGMultiLeadDetectListener = this.vp_c;
            if (iECGMultiLeadDetectListener != null) {
                Intrinsics.checkNotNull(eLeadFlagFromValue);
                iECGMultiLeadDetectListener.onEcgADCChange(eLeadFlagFromValue, CollectionsKt.toIntArray(arrayList), b, iTwoByteToUnsignedInt);
            }
            if (eLeadFlagFromValue == ELeadFlag.II) {
                if (this.vp_l.size() > 0) {
                    int iMin = Math.min(this.vp_l.size(), arrayList.size());
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    for (int i2 = 0; i2 < iMin; i2++) {
                        int iIntValue = ((Number) arrayList.get(i2)).intValue();
                        int iIntValue2 = ((Number) this.vp_l.get(i2)).intValue();
                        arrayList5.add(Integer.valueOf(iIntValue - iIntValue2));
                        arrayList2.add(Integer.valueOf((iIntValue + iIntValue2) / (-2)));
                        arrayList3.add(Integer.valueOf(iIntValue2 - (iIntValue / 2)));
                        arrayList4.add(Integer.valueOf(iIntValue - (iIntValue2 / 2)));
                    }
                    IECGMultiLeadDetectListener iECGMultiLeadDetectListener2 = this.vp_c;
                    if (iECGMultiLeadDetectListener2 != null) {
                        iECGMultiLeadDetectListener2.onEcgADCChange(ELeadFlag.III, CollectionsKt.toIntArray(arrayList5), b, iTwoByteToUnsignedInt);
                    }
                    IECGMultiLeadDetectListener iECGMultiLeadDetectListener3 = this.vp_c;
                    if (iECGMultiLeadDetectListener3 != null) {
                        iECGMultiLeadDetectListener3.onEcgADCChange(ELeadFlag.AVR, CollectionsKt.toIntArray(arrayList2), b, iTwoByteToUnsignedInt);
                    }
                    IECGMultiLeadDetectListener iECGMultiLeadDetectListener4 = this.vp_c;
                    if (iECGMultiLeadDetectListener4 != null) {
                        iECGMultiLeadDetectListener4.onEcgADCChange(ELeadFlag.AVL, CollectionsKt.toIntArray(arrayList3), b, iTwoByteToUnsignedInt);
                    }
                    IECGMultiLeadDetectListener iECGMultiLeadDetectListener5 = this.vp_c;
                    if (iECGMultiLeadDetectListener5 != null) {
                        iECGMultiLeadDetectListener5.onEcgADCChange(ELeadFlag.AVF, CollectionsKt.toIntArray(arrayList4), b, iTwoByteToUnsignedInt);
                    }
                    if (this.vp_l.size() != arrayList.size()) {
                        Log.e("Test", "I导II导过滤后长度不一致-->I:" + this.vp_l.size() + ",II:" + arrayList.size());
                    }
                } else {
                    Log.e("Test", "tempIAdcList.size == 0");
                }
                this.vp_l.clear();
            }
        } catch (Exception unused) {
            length.printStackTrace();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, boolean z2, IECGMultiLeadDetectListener iECGMultiLeadDetectListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "mClient");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iECGMultiLeadDetectListener, "ecgDetectListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, z2, iECGMultiLeadDetectListener);
        this.vp_i = z2;
        this.vp_c = iECGMultiLeadDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = this.vp_f;
        bArr[2] = this.vp_g;
        bArr[3] = z ? (byte) 1 : (byte) 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
