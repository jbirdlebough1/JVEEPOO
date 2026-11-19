package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ICheckWearDataListener;
import com.veepoo.protocol.model.datas.CheckWearData;
import com.veepoo.protocol.model.enums.ECheckWear;
import com.veepoo.protocol.model.settings.CheckWearSetting;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_q.class */
public class vp_q extends com.veepoo.protocol.vp_a {
    private ECheckWear vp_b(byte[] bArr) {
        if (bArr.length < 20) {
            return ECheckWear.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        byte b3 = bArr[3];
        if (b2 == 1) {
            return b == 1 ? ECheckWear.OPEN_SUCCESS : ECheckWear.OPEN_FAIL;
        }
        if (b2 == 0) {
            return b == 1 ? ECheckWear.CLOSE_SUCCESS : ECheckWear.CLOSE_FAIL;
        }
        return b == 1 ? ECheckWear.READ_SUCCESS : ECheckWear.READ_FAIL;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.handler(bArr);
        CheckWearData checkWearData = new CheckWearData();
        checkWearData.setCheckWearState(vp_b(bArr));
        ((ICheckWearDataListener) iListener).onCheckWearDataChange(checkWearData);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, CheckWearSetting checkWearSetting) {
        byte[] bArr = checkWearSetting.isOpen() ? com.veepoo.protocol.profile.vp_a.vp_az : com.veepoo.protocol.profile.vp_a.vp_ba;
        super.vp_a(bluetoothClient, str, bleWriteResponse, checkWearSetting);
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
