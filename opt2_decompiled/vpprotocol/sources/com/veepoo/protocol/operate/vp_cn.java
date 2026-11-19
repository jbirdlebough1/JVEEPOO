package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IResponseListener;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cn.class */
public class vp_cn extends com.veepoo.protocol.vp_a {
    IResponseListener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_ax(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ax(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -73;
        bArr[1] = 5;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        IResponseListener iResponseListener = (IResponseListener) iListener;
        this.vp_c = iResponseListener;
        if (bArr[1] == 5) {
            iResponseListener.response(bArr[2]);
        }
    }
}
