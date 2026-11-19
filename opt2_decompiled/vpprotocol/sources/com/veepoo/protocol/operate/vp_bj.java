package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ILowPowerListener;
import com.veepoo.protocol.model.datas.LowPowerData;
import com.veepoo.protocol.model.settings.LowPowerSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bj.class */
public class vp_bj extends com.veepoo.protocol.vp_a {
    ILowPowerListener vp_c;
    byte vp_d = 2;
    byte vp_e = 1;
    byte vp_f = 2;
    byte vp_g = 1;
    byte vp_h = 2;

    private byte vp_a(int i) {
        return VpBleByteUtil.loUint16((short) i);
    }

    private LowPowerData vp_b(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        return new LowPowerData(iArrByte2HexToIntArr[3], iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[4], iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8], iArrByte2HexToIntArr[9]);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        LowPowerData lowPowerDataVp_b = vp_b(bArr);
        ILowPowerListener iLowPowerListener = this.vp_c;
        if (iLowPowerListener != null) {
            iLowPowerListener.onLowpowerDataDataChange(lowPowerDataVp_b);
        }
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = 3;
        bArr[1] = this.vp_d;
        bArr[2] = this.vp_f;
        return bArr;
    }

    private byte[] vp_a(LowPowerSetting lowPowerSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = 3;
        bArr[1] = this.vp_d;
        bArr[2] = this.vp_e;
        bArr[3] = vp_a(lowPowerSetting.getResultState());
        bArr[4] = vp_a(lowPowerSetting.getNotifyInt());
        bArr[5] = vp_a(lowPowerSetting.getTrunwristerInt());
        bArr[6] = vp_a(lowPowerSetting.getNormallightInt());
        bArr[7] = vp_a(lowPowerSetting.getShockdelayInt());
        bArr[8] = vp_a(lowPowerSetting.getShocktimeInt());
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ILowPowerListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, LowPowerSetting lowPowerSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, lowPowerSetting);
        int oprateType = lowPowerSetting.getOprateType();
        byte[] bArrVp_a = null;
        if (oprateType == 1) {
            bArrVp_a = vp_a(lowPowerSetting);
        } else if (oprateType == 2) {
            bArrVp_a = vp_a();
        }
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
