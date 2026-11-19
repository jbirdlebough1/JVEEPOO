package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IScreenLightTimeListener;
import com.veepoo.protocol.model.datas.ScreenLightTimeData;
import com.veepoo.protocol.model.enums.EScreenLightTime;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ck.class */
public class vp_ck extends com.veepoo.protocol.vp_a {
    IScreenLightTimeListener vp_c;

    private byte[] vp_a(int i, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -76;
        bArr[1] = b;
        if (b == 2) {
            return bArr;
        }
        bArr[2] = VpBleByteUtil.intToBytes(i)[3];
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ak(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ak(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(0, (byte) 2), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        super.vp_d(bluetoothClient, str, bleWriteResponse, i);
        super.send(vp_a(i, (byte) 1), bluetoothClient, str, bleWriteResponse);
    }

    public ScreenLightTimeData vp_c(byte[] bArr) {
        EScreenLightTime eScreenLightTimeVp_b = vp_b(bArr);
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        return new ScreenLightTimeData(eScreenLightTimeVp_b, iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6]);
    }

    public EScreenLightTime vp_b(byte[] bArr) {
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b2 == 1) {
            return b == 1 ? EScreenLightTime.SETTING_SUCCESS : EScreenLightTime.SETTING_FAIL;
        }
        if (b2 == 2) {
            return b == 1 ? EScreenLightTime.READ_SUCCESS : EScreenLightTime.READ_FAIL;
        }
        return EScreenLightTime.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        this.vp_c = (IScreenLightTimeListener) iListener;
        if (bArr.length < 20) {
            return;
        }
        this.vp_c.onScreenLightTimeDataChange(vp_c(bArr));
    }
}
