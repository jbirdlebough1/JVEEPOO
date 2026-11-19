package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISpo2hDataListener;
import com.veepoo.protocol.model.datas.Spo2hData;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import com.veepoo.protocol.model.enums.ESPO2HStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cp.class */
public class vp_cp extends com.veepoo.protocol.vp_a {
    ISpo2hDataListener vp_c;

    private Spo2hData vp_b(byte[] bArr) {
        Spo2hData spo2hData = new Spo2hData();
        if (bArr.length >= 4) {
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            spo2hData.setSpState(vp_b(iArrByte2HexToIntArr[1]));
            spo2hData.setDeviceState(vp_a(iArrByte2HexToIntArr[2]));
            spo2hData.setValue(iArrByte2HexToIntArr[3]);
            int i = iArrByte2HexToIntArr[4];
            if (i == 1 || i == 2) {
                spo2hData.setChecking(true);
                spo2hData.setCheckingProgress(iArrByte2HexToIntArr[5]);
            } else {
                spo2hData.setChecking(false);
            }
        }
        if (bArr.length < 20) {
            return spo2hData;
        }
        int[] iArrByte2HexToIntArr2 = VpBleByteUtil.byte2HexToIntArr(bArr);
        if (bArr[6] == 6) {
            spo2hData.setRateValue(iArrByte2HexToIntArr2[7]);
        }
        if (spo2hData.getValue() == 1) {
            spo2hData.setDeviceState(EDeviceStatus.UNPASS_WEAR);
        }
        return spo2hData;
    }

    private EDeviceStatus vp_a(int i) {
        return (i == 0 || i == 4) ? EDeviceStatus.FREE : EDeviceStatus.BUSY;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bd(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bj, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bt(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bt(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_bk, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onSpO2HADataChange(vp_b(bArr));
    }

    private ESPO2HStatus vp_b(int i) {
        if (i == 0) {
            return ESPO2HStatus.NOT_SUPPORT;
        }
        if (i == 1) {
            return ESPO2HStatus.OPEN;
        }
        return i == 2 ? ESPO2HStatus.CLOSE : ESPO2HStatus.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (ISpo2hDataListener) iListener;
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isDetectingSpo2h()) {
            handler(bArr);
        }
    }
}
