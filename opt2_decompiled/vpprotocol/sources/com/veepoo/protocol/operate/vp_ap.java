package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IFindDeviceDatalistener;
import com.veepoo.protocol.model.datas.FindDeviceData;
import com.veepoo.protocol.model.enums.EFindDeviceStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ap.class */
public class vp_ap extends com.veepoo.protocol.vp_a {
    IFindDeviceDatalistener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, z);
        if (z) {
            super.send(com.veepoo.protocol.profile.vp_a.vp_av, bluetoothClient, str, bleWriteResponse);
        } else {
            super.send(com.veepoo.protocol.profile.vp_a.vp_aw, bluetoothClient, str, bleWriteResponse);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_w(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_w(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_au, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IFindDeviceDatalistener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        FindDeviceData findDeviceData = new FindDeviceData();
        findDeviceData.setStatus(vp_b(bArr));
        this.vp_c.onFindDevice(findDeviceData);
    }

    public EFindDeviceStatus vp_b(byte[] bArr) {
        if (bArr.length < 4) {
            return EFindDeviceStatus.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b2 == 1) {
            return b == 1 ? EFindDeviceStatus.OPEN_SUCCESS : EFindDeviceStatus.OPEN_FAIL;
        }
        if (b2 == 0) {
            return b == 1 ? EFindDeviceStatus.CLOSE_SUCCESS : EFindDeviceStatus.CLOSE_FAIL;
        }
        return b == 1 ? EFindDeviceStatus.READ_SUCCESS : EFindDeviceStatus.READ_FAIL;
    }
}
