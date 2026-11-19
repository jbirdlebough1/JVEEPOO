package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGReadDataListener;
import com.veepoo.protocol.model.datas.EcgDetectResult;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EECGResultType;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_af.class */
public class vp_af extends com.veepoo.protocol.vp_a {
    int[] vp_f;
    BluetoothClient vp_g;
    String vp_h;
    BleWriteResponse vp_i;
    IECGReadDataListener vp_j;
    List vp_c = new ArrayList();
    int vp_d = 0;
    int vp_e = 0;
    List vp_k = new ArrayList();
    boolean vp_l = false;

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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        new StringBuilder().append("自动:单条->value=").append(VpBleByteUtil.byte2HexForShow(bArr));
        byte b = bArr[2];
        if (b == 0) {
            this.vp_c.clear();
            this.vp_c.add(bArr);
            return;
        }
        if (b == 1) {
            this.vp_c.add(bArr);
            return;
        }
        if (b == 2) {
            byte[] bArrVp_a = vp_a(this.vp_c);
            new StringBuilder().append("自动:内容-> byteData=").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
            try {
                EcgDetectResult ecgDetectResultVp_c = vp_c(bArrVp_a);
                this.vp_k.add(ecgDetectResultVp_c);
                new StringBuilder().append("自动:ecg结果->current_id=").append(this.vp_e).append(",EcgDetectResult=").append(ecgDetectResultVp_c);
            } catch (Exception unused) {
                printStackTrace();
            }
            int i = this.vp_e + 1;
            this.vp_e = i;
            if (i < this.vp_d) {
                vp_a(this.vp_f[i]);
            } else {
                this.vp_j.readDataFinish(this.vp_k);
            }
        }
    }

    public EcgDetectResult vp_c(byte[] bArr) {
        EcgDetectResult ecgDetectResult = new EcgDetectResult();
        ecgDetectResult.setType(EECGResultType.DATA_FORM_AUTO);
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            switch (byteBufferOrder.get()) {
                case EUIFromType.UICode.A_GPS /* -95 */:
                    byte[] bArrVp_a = vp_a(byteBufferOrder, "0xA1", bArr);
                    ecgDetectResult.setTimeBean(vp_b(bArrVp_a));
                    ecgDetectResult.setDuration(bArrVp_a[7]);
                    new StringBuilder().append("自动:0xA1->").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
                    break;
                case EUIFromType.UICode.G15ImgAPPDownloadAppQRCode /* -94 */:
                    int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(vp_a(byteBufferOrder, "0xA2", bArr));
                    ecgDetectResult.setOriginSign(iArrByte2HexToIntArr);
                    ecgDetectResult.setFilterSignals(iArrByte2HexToIntArr);
                    break;
                case EUIFromType.UICode.G15ImgProfile /* -93 */:
                    byte[] bArrVp_a2 = vp_a(byteBufferOrder, "0xA3", bArr);
                    new StringBuilder().append("自动:0xA3->").append(VpBleByteUtil.byte2HexForShow(bArrVp_a2));
                    vp_a(bArrVp_a2, ecgDetectResult);
                    break;
                case EUIFromType.UICode.G15ImgTheme1 /* -92 */:
                    byte[] bArrVp_a3 = vp_a(byteBufferOrder, "0xA4", bArr);
                    new StringBuilder().append("自动:0xA4->").append(VpBleByteUtil.byte2HexForShow(bArrVp_a3));
                    ecgDetectResult.setFrequency(VpBleByteUtil.byte2HexToIntArr(bArrVp_a3)[0]);
                    vp_b(bArrVp_a3, ecgDetectResult);
                    break;
            }
        }
        return ecgDetectResult;
    }

    private void vp_b(byte[] bArr, EcgDetectResult ecgDetectResult) {
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        int iIntValue = Integer.valueOf(strArrByte2HexToStrArr[1] + strArrByte2HexToStrArr[0], 16).intValue();
        int iIntValue2 = Integer.valueOf(strArrByte2HexToStrArr[3] + strArrByte2HexToStrArr[2], 16).intValue();
        ecgDetectResult.setFrequency(iIntValue);
        ecgDetectResult.setDrawfrequency(iIntValue2);
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        new StringBuilder().append("--------").append(str).append("----------");
        int iTwoByteToUnsignedInt = byteBuffer.get();
        if (str.equals("0xA2")) {
            iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(byteBuffer.get(), iTwoByteToUnsignedInt == true ? (byte) 1 : (byte) 0);
        }
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + (iTwoByteToUnsignedInt == true ? 1 : 0));
        return vp_a(bArr, iTwoByteToUnsignedInt == true ? 1 : 0, iPosition);
    }

    private byte[] vp_a(List list) {
        if (list.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = new byte[list.size() * 15];
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr2 = (byte[]) list.get(i);
            for (int i2 = 5; i2 < bArr2.length; i2++) {
                bArr[((i * 15) + i2) - 5] = bArr2[i2];
            }
        }
        return bArr;
    }

    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, boolean z, IECGReadDataListener iECGReadDataListener) {
        this.vp_j = iECGReadDataListener;
        this.vp_g = bluetoothClient;
        this.vp_h = str;
        this.vp_i = bleWriteResponse;
        this.vp_f = iArr;
        this.vp_l = z;
        new StringBuilder().append("自动:ID列表->").append(Arrays.toString(this.vp_f));
        int[] iArr2 = this.vp_f;
        if (iArr2 == null || iArr2.length == 0) {
            iECGReadDataListener.readDataFinish(Collections.EMPTY_LIST);
            return;
        }
        this.vp_d = iArr2.length;
        List list = this.vp_k;
        if (list != null && !list.isEmpty()) {
            this.vp_k.clear();
        }
        vp_a(this.vp_f[0]);
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
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int[] iArr = {iArrByte2HexToIntArr[0], iArrByte2HexToIntArr[1], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7]};
        ecgDetectResult.setResult8(iArr);
        if (this.vp_l) {
            ecgDetectResult.setDiseaseResult(new vp_ac().vp_a(iArr));
        }
        ecgDetectResult.setAveHeart(iArrByte2HexToIntArr[8]);
        ecgDetectResult.setAveResRate(iArrByte2HexToIntArr[9]);
        ecgDetectResult.setAveHrv(iArrByte2HexToIntArr[10]);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        ecgDetectResult.setAveQT(Integer.valueOf(strArrByte2HexToStrArr[12] + strArrByte2HexToStrArr[11], 16).intValue());
    }

    private void vp_a(int i) {
        byte[] bArrVp_a = vp_a((byte) 3, i);
        new StringBuilder().append("readEcgDataById: cmdECGData：").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        super.send(bArrVp_a, this.vp_g, this.vp_h, this.vp_i);
    }
}
