package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBreathDataListener;
import com.veepoo.protocol.model.datas.BreathData;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_n.class */
public class vp_n extends com.veepoo.protocol.vp_a {
    IBreathDataListener vp_c;

    private byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -126;
        bArr[1] = b;
        return bArr;
    }

    private BreathData vp_b(byte[] bArr) {
        BreathData breathData = new BreathData();
        if (bArr.length >= 4) {
            int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
            breathData.setFunctionState(iArrByte2HexToIntArr[1]);
            breathData.setDeviceState(iArrByte2HexToIntArr[2]);
            breathData.setProgressValue(iArrByte2HexToIntArr[3]);
            breathData.setValue(iArrByte2HexToIntArr[4]);
        }
        return breathData;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ba(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ba(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((byte) 1), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bm(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bm(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a((byte) 2), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IBreathDataListener) iListener;
        BreathData breathDataVp_b = vp_b(bArr);
        IBreathDataListener iBreathDataListener = this.vp_c;
        if (iBreathDataListener != null) {
            iBreathDataListener.onDataChange(breathDataVp_b);
        }
    }
}
