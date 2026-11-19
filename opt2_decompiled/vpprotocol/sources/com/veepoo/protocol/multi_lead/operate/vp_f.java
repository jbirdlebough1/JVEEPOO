package com.veepoo.protocol.multi_lead.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.multi_lead.data.DiseaseInfo;
import com.veepoo.protocol.multi_lead.data.MultiEcgConfig;
import com.veepoo.protocol.multi_lead.data.MultiEcgDetectInfo;
import com.veepoo.protocol.multi_lead.data.MultiEcgPreInfo;
import com.veepoo.protocol.multi_lead.enums.EDisease;
import com.veepoo.protocol.multi_lead.enums.ELeadFlag;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgDetectListener;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
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

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_f.class */
public class vp_f extends com.veepoo.protocol.vp_a {
    private IMultiEcgDetectListener vp_c;
    private final byte vp_d = 8;
    private final byte vp_e = 1;
    private final byte vp_f = 2;
    private boolean vp_g = false;
    private int vp_h = 0;
    private int vp_i = 0;
    List vp_j = Collections.synchronizedList(new ArrayList());
    private List vp_k = new ArrayList();

    private MultiEcgPreInfo vp_c(byte[] bArr) {
        if (bArr == null || bArr.length < 20) {
            return new MultiEcgPreInfo();
        }
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int i = Integer.parseInt(strArrByte2HexToStrArr[6] + strArrByte2HexToStrArr[5], 16);
        int i2 = Integer.parseInt(strArrByte2HexToStrArr[8] + strArrByte2HexToStrArr[7], 16);
        byte b = bArr[9];
        byte b2 = bArr[10];
        byte b3 = bArr[11];
        byte b4 = bArr[12];
        byte b5 = bArr[13];
        Calendar calendar = Calendar.getInstance();
        calendar.set(i2, b - 1, b2, b3, b4, b5);
        calendar.set(14, 0);
        return new MultiEcgPreInfo(i, calendar.getTimeInMillis());
    }

    private List vp_d(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        DiseaseInfo diseaseInfo = null;
        while (byteBufferOrder.position() < bArr.length) {
            byte b = byteBufferOrder.get();
            if (b != -32) {
                switch (b) {
                    case -64:
                        byteBufferOrder.get();
                        int i = byteBufferOrder.get() & 255;
                        DiseaseInfo diseaseInfo2 = new DiseaseInfo();
                        diseaseInfo2.setDiseaseType(EDisease.INSTANCE.fromValue(i));
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
                        int i2 = byteBufferOrder.get() & 255;
                        if (diseaseInfo4 != null) {
                            diseaseInfo.setDiseaseLevel(i2);
                        }
                        break;
                }
            } else {
                DiseaseInfo diseaseInfo5 = diseaseInfo;
                byteBufferOrder.get();
                int iLittleEndianByteArray2Int = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()});
                if (diseaseInfo5 != null) {
                    diseaseInfo.setDiseaseOffset(iLittleEndianByteArray2Int);
                    arrayList.add(diseaseInfo);
                }
            }
        }
        return arrayList;
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

    @Override // com.veepoo.protocol.vp_a
    public void vp_br(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 8;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        new StringBuilder().append("**ecg---Handlers-->").append(vp_g.vp_d(bArr));
        new StringBuilder().append("**ecg---size-->").append(bArr.length);
        IMultiEcgDetectListener iMultiEcgDetectListener = this.vp_c;
        if (iMultiEcgDetectListener == null) {
            return;
        }
        byte b = bArr[2];
        if (b == 1) {
            byte b2 = bArr[4];
            if (b2 == 0) {
                iMultiEcgDetectListener.onEcgDeviceNotSupport();
                return;
            }
            if (b2 == 2) {
                iMultiEcgDetectListener.onEcgDeviceNotInDetect();
                return;
            }
            MultiEcgPreInfo multiEcgPreInfoVp_c = vp_c(bArr);
            IMultiEcgDetectListener iMultiEcgDetectListener2 = this.vp_c;
            if (iMultiEcgDetectListener2 != null) {
                iMultiEcgDetectListener2.onEcgDetectPreStart(multiEcgPreInfoVp_c);
                return;
            }
            return;
        }
        if (b == 3) {
            int i = bArr[3] & 255;
            if (i == 1) {
                this.vp_j.clear();
                this.vp_h = bArr[6] & 255;
            }
            this.vp_i = i;
            int length = bArr.length - 4;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 4, bArr2, 0, length);
            this.vp_j.add(bArr2);
            if (this.vp_i == this.vp_h) {
                MultiEcgDetectInfo multiEcgDetectInfoVp_a = vp_a();
                IMultiEcgDetectListener iMultiEcgDetectListener3 = this.vp_c;
                if (iMultiEcgDetectListener3 != null) {
                    iMultiEcgDetectListener3.onEcgDetectStateChange(multiEcgDetectInfoVp_a);
                }
            }
        }
    }

    public int[] vp_b(byte[] bArr) {
        int length = bArr.length / 3;
        int[] iArr = new int[length];
        String[] strArrVp_f = vp_g.vp_f(bArr);
        for (int i = 0; i < length; i++) {
            int i2 = i * 3;
            int i3 = Integer.parseInt(strArrVp_f[i2] + strArrVp_f[i2 + 1] + strArrVp_f[i2 + 2], 16);
            if (i3 == 8421504) {
                iArr[i] = Integer.MAX_VALUE;
            } else {
                iArr[i] = vp_a(i3);
            }
        }
        return iArr;
    }

    private int[] vp_b(List list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = ((Integer) list.get(i)).intValue();
        }
        return iArr;
    }

    public int vp_a(int i) {
        return ((i >> 21) & 1) == 0 ? i & 2097151 : i | (-2097152);
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        int iLittleEndianByteArray2Int = VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBuffer.get(), byteBuffer.get(), byteBuffer.get(), byteBuffer.get()});
        int iPosition = byteBuffer.position();
        int i = iPosition + iLittleEndianByteArray2Int;
        new StringBuilder().append("getBlockContent[").append(str).append("]  startPosition = ").append(iPosition).append(",endPosition = ").append(i).append(",length = ").append(iLittleEndianByteArray2Int);
        byteBuffer.position(i);
        return vp_a(bArr, iLittleEndianByteArray2Int, iPosition);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v20, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v21, types: [com.veepoo.protocol.multi_lead.data.MultiEcgDetectInfo] */
    /* JADX WARN: Type inference failed for: r0v9, types: [byte] */
    private MultiEcgDetectInfo vp_a() {
        byte[] bArrVp_a = vp_a(this.vp_j);
        new StringBuilder().append("concatenate[").append(bArrVp_a.length).append("]");
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArrVp_a).order(ByteOrder.LITTLE_ENDIAN);
        MultiEcgDetectInfo multiEcgDetectInfo = new MultiEcgDetectInfo();
        while (byteBufferOrder.position() < bArrVp_a.length) {
            ?? r0 = byteBufferOrder.get();
            switch (r0) {
                case -96:
                    byteBufferOrder.get();
                    byteBufferOrder.get();
                    break;
                case EUIFromType.UICode.A_GPS /* -95 */:
                    byteBufferOrder.get();
                    int i = byteBufferOrder.get() & 255;
                    int iTwoByteToUnsignedInt2 = VpBleByteUtil.twoByteToUnsignedInt2(byteBufferOrder.get(), byteBufferOrder.get());
                    int i2 = byteBufferOrder.get() & 255;
                    multiEcgDetectInfo.setAvgHeart(i);
                    multiEcgDetectInfo.setAvgQt(iTwoByteToUnsignedInt2);
                    multiEcgDetectInfo.setAvgHrv(i2);
                    break;
                case EUIFromType.UICode.G15ImgAPPDownloadAppQRCode /* -94 */:
                    byteBufferOrder.get();
                    multiEcgDetectInfo.setLeadOff((byteBufferOrder.get() & 255) == 1);
                    break;
                case EUIFromType.UICode.G15ImgTheme1 /* -92 */:
                    try {
                        r0 = multiEcgDetectInfo;
                        r0.setDiseaseInfoList(vp_d(vp_a(byteBufferOrder, "0xA4", bArrVp_a)));
                    } catch (Exception unused) {
                        r0.printStackTrace();
                    }
                    break;
            }
        }
        return multiEcgDetectInfo;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.veepoo.protocol.multi_lead.enums.ELeadFlag] */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        super.vp_a(bArr);
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
            boolean z = eCGType == 10 || eCGType == 12 || eCGType == 15;
            byte b = z ? bArr[2] : (byte) 12;
            boolean z2 = z;
            int i = z ? 3 : 2;
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i + (bArr.length - (z2 ? 5 : 4)));
            int[] iArrVp_b = vp_b(bArrCopyOfRange);
            ArrayList arrayList = new ArrayList();
            for (int i2 : iArrVp_b) {
                if (i2 != Integer.MAX_VALUE) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            if (eLeadFlagFromValue == ELeadFlag.I) {
                this.vp_k.clear();
                this.vp_k.addAll(arrayList);
                Log.e("Test", "I ecg-->:" + VpBleByteUtil.byte2HexForShow(bArrCopyOfRange));
                Log.e("Test", "I power-->:" + ((int) b));
            }
            IMultiEcgDetectListener iMultiEcgDetectListener = this.vp_c;
            if (iMultiEcgDetectListener != null) {
                iMultiEcgDetectListener.onEcgADCChange(eLeadFlagFromValue, vp_b(arrayList), b, iTwoByteToUnsignedInt);
            }
            if (eLeadFlagFromValue == ELeadFlag.II) {
                if (this.vp_k.isEmpty()) {
                    Log.e("Test", "tempIAdcList.size == 0");
                } else {
                    int iMin = Math.min(this.vp_k.size(), arrayList.size());
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayList3 = new ArrayList();
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    for (int i3 = 0; i3 < iMin; i3++) {
                        int iIntValue = ((Integer) arrayList.get(i3)).intValue();
                        int iIntValue2 = ((Integer) this.vp_k.get(i3)).intValue();
                        arrayList5.add(Integer.valueOf(iIntValue - iIntValue2));
                        arrayList2.add(Integer.valueOf((-(iIntValue + iIntValue2)) / 2));
                        arrayList3.add(Integer.valueOf(iIntValue2 - (iIntValue / 2)));
                        arrayList4.add(Integer.valueOf(iIntValue - (iIntValue2 / 2)));
                    }
                    IMultiEcgDetectListener iMultiEcgDetectListener2 = this.vp_c;
                    if (iMultiEcgDetectListener2 != null) {
                        iMultiEcgDetectListener2.onEcgADCChange(ELeadFlag.III, vp_b(arrayList5), b, iTwoByteToUnsignedInt);
                        this.vp_c.onEcgADCChange(ELeadFlag.AVR, vp_b(arrayList2), b, iTwoByteToUnsignedInt);
                        this.vp_c.onEcgADCChange(ELeadFlag.AVL, vp_b(arrayList3), b, iTwoByteToUnsignedInt);
                        this.vp_c.onEcgADCChange(ELeadFlag.AVF, vp_b(arrayList4), b, iTwoByteToUnsignedInt);
                    }
                    if (this.vp_k.size() != arrayList.size()) {
                        Log.e("Test", "I导II导过滤后长度不一致-->I:" + this.vp_k.size() + ",II:" + arrayList.size());
                    }
                }
                this.vp_k.clear();
            }
        } catch (Exception unused) {
            length.printStackTrace();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, boolean z2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2);
        this.vp_g = z2;
        this.vp_c = iMultiEcgDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 8;
        bArr[2] = 1;
        bArr[3] = z ? (byte) 1 : (byte) 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, boolean z2, MultiEcgConfig multiEcgConfig) {
        int eCGType = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getECGType();
        if (eCGType != 15) {
            new StringBuilder().append("多导ECG扩展配置(测试模式/连接方式/测量时长)仅当ECG类型=0x0F(心电贴)时生效，当前ecgType=0x").append(Integer.toHexString(eCGType)).append("，本次下发的指令不携带扩展字段");
        }
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2);
        this.vp_g = z2;
        this.vp_c = iMultiEcgDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 8;
        bArr[2] = 1;
        bArr[3] = z ? (byte) 1 : (byte) 2;
        if (eCGType == 15 && multiEcgConfig != null) {
            bArr[4] = (byte) multiEcgConfig.getTestMode().getValue();
            bArr[5] = (byte) multiEcgConfig.getConnectMode().getValue();
            bArr[6] = (byte) (multiEcgConfig.getDuration() & 255);
            bArr[7] = (byte) ((multiEcgConfig.getDuration() >> 8) & 255);
        }
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
