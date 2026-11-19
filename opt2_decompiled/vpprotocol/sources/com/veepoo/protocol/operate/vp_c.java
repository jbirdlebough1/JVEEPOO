package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IAutoMeasureSettingDataListener;
import com.veepoo.protocol.model.datas.AutoMeasureData;
import com.veepoo.protocol.model.enums.EAutoMeasureType;
import com.veepoo.protocol.model.enums.ESensitivity;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.UiUpdateUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_c.class */
public class vp_c extends com.veepoo.protocol.vp_a {
    IAutoMeasureSettingDataListener vp_c;
    private int vp_d = 0;
    private int vp_e = 0;
    private int vp_f = 42;
    private int vp_g = 45;
    List vp_h = Collections.synchronizedList(new ArrayList());

    private void vp_b(byte[] bArr) {
        super.handler(bArr);
        byte b = bArr[2];
        if (b == 0) {
            IAutoMeasureSettingDataListener iAutoMeasureSettingDataListener = this.vp_c;
            if (iAutoMeasureSettingDataListener != null) {
                iAutoMeasureSettingDataListener.onSettingDataChangeFail();
                return;
            }
            return;
        }
        if (bArr[1] == 1) {
            IAutoMeasureSettingDataListener iAutoMeasureSettingDataListener2 = this.vp_c;
            if (iAutoMeasureSettingDataListener2 != null) {
                if (b == 1) {
                    iAutoMeasureSettingDataListener2.onSettingDataChangeSuccess();
                    return;
                } else {
                    iAutoMeasureSettingDataListener2.onSettingDataChangeFail();
                    return;
                }
            }
            return;
        }
        if (bArr[3] == 1) {
            this.vp_h.clear();
            this.vp_d = bArr[6] & 255;
        }
        this.vp_e = bArr[3];
        int length = bArr.length - 4;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 4, bArr2, 0, length);
        this.vp_h.add(bArr2);
        if (this.vp_e == this.vp_d) {
            List<AutoMeasureData> listVp_a = vp_a();
            IAutoMeasureSettingDataListener iAutoMeasureSettingDataListener3 = this.vp_c;
            if (iAutoMeasureSettingDataListener3 != null) {
                iAutoMeasureSettingDataListener3.onSettingDataChange(listVp_a);
            }
        }
    }

    private AutoMeasureData vp_c(byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        AutoMeasureData autoMeasureData = new AutoMeasureData();
        while (byteBufferOrder.position() < bArr.length) {
            switch (byteBufferOrder.get()) {
                case UiUpdateUtil.ACTION_RECODING /* -48 */:
                    byteBufferOrder.get();
                    autoMeasureData.setProtocolType(byteBufferOrder.get() & 255);
                    break;
                case -47:
                    byteBufferOrder.get();
                    autoMeasureData.setFunType(EAutoMeasureType.INSTANCE.fromValue(byteBufferOrder.get() & 255));
                    break;
                case -46:
                    byteBufferOrder.get();
                    autoMeasureData.setSwitchOpen((byteBufferOrder.get() & 255) == 1);
                    break;
                case -45:
                    byteBufferOrder.get();
                    autoMeasureData.setStepUnit(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
                    break;
                case -44:
                    byteBufferOrder.get();
                    autoMeasureData.setSlotModify((byteBufferOrder.get() & 255) == 1);
                    autoMeasureData.setIntervalModify((byteBufferOrder.get() & 255) == 1);
                    break;
                case -43:
                    byteBufferOrder.get();
                    int i = ((byteBufferOrder.get() & 255) * 60) + (byteBufferOrder.get() & 255);
                    int i2 = ((byteBufferOrder.get() & 255) * 60) + (byteBufferOrder.get() & 255);
                    autoMeasureData.setSupportStartMinute(i);
                    autoMeasureData.setSupportEndMinute(i2);
                    break;
                case -42:
                    byteBufferOrder.get();
                    autoMeasureData.setMeasureInterval(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
                    break;
                case -41:
                    byteBufferOrder.get();
                    int i3 = ((byteBufferOrder.get() & 255) * 60) + (byteBufferOrder.get() & 255);
                    int i4 = ((byteBufferOrder.get() & 255) * 60) + (byteBufferOrder.get() & 255);
                    autoMeasureData.setCurrentStartMinute(i3);
                    autoMeasureData.setCurrentEndMinute(i4);
                    break;
                case -40:
                    byteBufferOrder.get();
                    ESensitivity eSensitivityFromValue = ESensitivity.INSTANCE.fromValue(byteBufferOrder.get() & 255);
                    if (eSensitivityFromValue != null) {
                        autoMeasureData.setSensitivity(eSensitivityFromValue);
                    }
                    break;
                case -39:
                    byteBufferOrder.get();
                    autoMeasureData.setLowOxygenAlertSwitch(byteBufferOrder.get() & 255);
                    break;
            }
        }
        return autoMeasureData;
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
    public void vp_l(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_l(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -77;
        bArr[1] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    private byte[] vp_a(ByteBuffer byteBuffer, String str, byte[] bArr, int i) {
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + i);
        return vp_a(bArr, i, iPosition);
    }

    private byte[] vp_a(int i, int i2, byte[] bArr, int i3) {
        int i4;
        int length = i == i2 ? bArr.length - ((i - 1) * (i3 - 3)) : i3 - 3;
        byte[] bArr2 = new byte[length + 3];
        bArr2[0] = -77;
        bArr2[1] = 1;
        bArr2[2] = (byte) i;
        for (int i5 = 0; i5 < length && (i4 = ((i - 1) * (i3 - 3)) + i5) < bArr.length; i5++) {
            bArr2[i5 + 3] = bArr[i4];
        }
        if (i == 1) {
            bArr2[5] = (byte) i2;
        }
        return bArr2;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        if (iListener != null) {
            this.vp_c = (IAutoMeasureSettingDataListener) iListener;
        }
        vp_b(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] vp_a(AutoMeasureData autoMeasureData) {
        int value = autoMeasureData.getSensitivity().getValue();
        Object[] objArr = autoMeasureData.getSensitivity() != ESensitivity.UNSUPPORT && value >= 1 && value <= 3;
        int lowOxygenAlertSwitch = autoMeasureData.getLowOxygenAlertSwitch();
        Object[] objArr2 = lowOxygenAlertSwitch == 0 || lowOxygenAlertSwitch == 1;
        Object[] objArr3 = objArr;
        byte[] bArr = new byte[(objArr != false ? this.vp_g : this.vp_f) + (objArr2 != false ? 3 : 0)];
        bArr[0] = -96;
        bArr[1] = 5;
        bArr[2] = 1;
        int i = (objArr3 != false ? 3 : 0) + 35;
        Object[] objArr4 = objArr;
        byte[] bArrIntToBytes = VpBleByteUtil.intToBytes(i + (objArr2 != false ? 3 : 0));
        bArr[3] = bArrIntToBytes[3];
        bArr[4] = bArrIntToBytes[2];
        bArr[5] = bArrIntToBytes[1];
        bArr[6] = bArrIntToBytes[0];
        bArr[7] = -95;
        bArr[8] = 33;
        bArr[9] = -48;
        bArr[10] = 1;
        bArr[11] = (byte) autoMeasureData.getProtocolType();
        bArr[12] = -47;
        bArr[13] = 1;
        bArr[14] = (byte) autoMeasureData.getFunType().getValue();
        bArr[15] = -46;
        bArr[16] = 1;
        bArr[17] = autoMeasureData.isSwitchOpen() ? (byte) 1 : (byte) 0;
        bArr[18] = -45;
        bArr[19] = 2;
        bArr[20] = (byte) (autoMeasureData.getStepUnit() & 255);
        bArr[21] = (byte) ((autoMeasureData.getStepUnit() >> 8) & 255);
        bArr[22] = -44;
        bArr[23] = 2;
        bArr[24] = autoMeasureData.isSlotModify() ? (byte) 1 : (byte) 0;
        bArr[25] = autoMeasureData.isIntervalModify() ? (byte) 1 : (byte) 0;
        bArr[26] = -43;
        bArr[27] = 4;
        bArr[28] = (byte) (autoMeasureData.getSupportStartMinute() / 60);
        bArr[29] = (byte) (autoMeasureData.getSupportStartMinute() % 60);
        bArr[30] = (byte) (autoMeasureData.getSupportEndMinute() / 60);
        bArr[31] = (byte) (autoMeasureData.getSupportEndMinute() % 60);
        bArr[32] = -42;
        bArr[33] = 2;
        bArr[34] = (byte) (autoMeasureData.getMeasureInterval() & 255);
        bArr[35] = (byte) ((autoMeasureData.getMeasureInterval() >> 8) & 255);
        bArr[36] = -41;
        bArr[37] = 4;
        bArr[38] = (byte) (autoMeasureData.getCurrentStartMinute() / 60);
        bArr[39] = (byte) (autoMeasureData.getCurrentStartMinute() % 60);
        bArr[40] = (byte) (autoMeasureData.getCurrentEndMinute() / 60);
        bArr[41] = (byte) (autoMeasureData.getCurrentEndMinute() % 60);
        if (objArr4 != false) {
            bArr[42] = -40;
            bArr[43] = 1;
            bArr[44] = (byte) (value & 255);
        }
        if (objArr2 != false) {
            int i2 = objArr != false ? 45 : 42;
            bArr[i2] = -39;
            bArr[i2 + 1] = 1;
            bArr[i2 + 2] = (byte) lowOxygenAlertSwitch;
        }
        bArr[8] = (byte) ((objArr != false ? 3 : 0) + 33 + (objArr2 != false ? 3 : 0));
        return bArr;
    }

    private List vp_a() {
        byte[] bArrVp_a = vp_a(this.vp_h);
        new StringBuilder().append("concatenate[").append(bArrVp_a.length).append("]");
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArrVp_a).order(ByteOrder.LITTLE_ENDIAN);
        ArrayList arrayList = new ArrayList();
        while (byteBufferOrder.position() < bArrVp_a.length) {
            byte b = byteBufferOrder.get();
            if (b == -96) {
                byte b2 = byteBufferOrder.get();
                new StringBuilder().append("0xA0 -> A0len:").append((int) b2).append(",totalPkg:").append(byteBufferOrder.get() & 255).append(",totalLength:").append(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
            } else if (b == -95) {
                arrayList.add(vp_c(vp_a(byteBufferOrder, "0xA1", bArrVp_a, byteBufferOrder.get() & 255)));
            }
        }
        return arrayList;
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AutoMeasureData autoMeasureData) {
        int mtuValue = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue();
        int i = mtuValue;
        new StringBuilder().append("自动测量-设置 MTU = ").append(i);
        if (mtuValue == -1 || VpSpGetUtil.getVpSpVariInstance(this.vp_a).isZKDevice()) {
            i = 20;
        }
        int i2 = i;
        byte[] bArrVp_a = vp_a(autoMeasureData);
        if (i2 >= bArrVp_a.length + 3) {
            byte[] bArr = new byte[bArrVp_a.length + 3];
            bArr[0] = -77;
            bArr[1] = 1;
            bArr[2] = 1;
            System.arraycopy(bArrVp_a, 0, bArr, 3, bArrVp_a.length);
            new StringBuilder().append("自动测量-一包发送 内容 = ").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            return;
        }
        new StringBuilder().append("自动测量-分包发送 packageLength = ").append(i);
        new StringBuilder().append("自动测量 data Length = ").append(bArrVp_a.length).append(" - - ").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
        int iCeil = (int) Math.ceil(((double) bArrVp_a.length) / ((double) (i - 3)));
        int i3 = 0;
        while (i3 < iCeil) {
            int i4 = i3 + 1;
            i3 = i4;
            byte[] bArrVp_a2 = vp_a(i4, iCeil, bArrVp_a, i);
            new StringBuilder().append("自动测量-分包发送 内容 = ").append(VpBleByteUtil.byte2HexForShow(bArrVp_a));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
        }
    }
}
