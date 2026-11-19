package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBatteryDataListener;
import com.veepoo.protocol.model.datas.BatteryData;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_h.class */
public class vp_h extends com.veepoo.protocol.vp_a {
    private IBatteryDataListener vp_c;

    private BatteryData vp_b(byte[] bArr) {
        BatteryData batteryData = new BatteryData();
        if (bArr.length < 5) {
            return batteryData;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        byte b4 = bArr[4];
        byte b5 = bArr[5];
        byte b6 = bArr[6];
        byte b7 = bArr[7];
        batteryData.setBatteryLevel(vp_a(b4));
        batteryData.setPercent(b5 == 1);
        batteryData.setBatteryPercent(b6);
        batteryData.setPowerModel(b);
        batteryData.setState(b3);
        batteryData.setLowBattery(b7 == 2);
        batteryData.setBat(b2);
        return batteryData;
    }

    private int vp_a(byte b) {
        if (b == 0) {
            return 0;
        }
        if (b == 1) {
            return 1;
        }
        if (b == 2) {
            return 2;
        }
        if (b == 3) {
            return 3;
        }
        return b == 4 ? 4 : 0;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_o(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_o(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ad, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.send(com.veepoo.protocol.profile.vp_a.vp_aj, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onDataChange(vp_b(bArr));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IBatteryDataListener) iListener;
        handler(bArr);
    }
}
