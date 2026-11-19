package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IFindDevicelistener;
import com.veepoo.protocol.listener.data.IFindPhonelistener;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_aq.class */
public final class vp_aq extends com.veepoo.protocol.vp_a {
    private byte vp_c = 10;
    private byte vp_d = 11;

    private final void vp_b(byte[] bArr, IFindDevicelistener iFindDevicelistener) {
        if (bArr == null || bArr.length <= 3) {
            return;
        }
        byte b = bArr[1];
        if (b == 10) {
            vp_a(bArr, iFindDevicelistener);
        } else if (b == 11) {
            vp_a(bArr, iFindDevicelistener);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, i);
        byte[] bArrVp_a = new byte[20];
        if (i == 1) {
            bArrVp_a = vp_a(this.vp_c);
        } else if (i == 2) {
            bArrVp_a = vp_a(this.vp_d);
        }
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bw(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bw(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -75;
        bArr[1] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -75;
        bArr[1] = b;
        return bArr;
    }

    private final void vp_a(byte[] bArr, IFindDevicelistener iFindDevicelistener) {
        byte b = bArr[2];
        if (b == 0) {
            iFindDevicelistener.unSupportFindDeviceByPhone();
            return;
        }
        if (b == 1) {
            iFindDevicelistener.findingDevice();
        } else if (b == 2) {
            iFindDevicelistener.findedDevice();
        } else if (b == 3) {
            iFindDevicelistener.unFindDevice();
        }
    }

    public final boolean vp_b(byte[] bArr) {
        boolean z = false;
        if (bArr != null) {
            if ((bArr.length > 2) & (bArr[1] == 0)) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        Intrinsics.checkNotNullParameter(iListener, "listener");
        super.handler(bArr);
        if (bArr != null) {
            if (bArr[1] == 2) {
                if (iListener instanceof IFindPhonelistener) {
                    ((IFindPhonelistener) iListener).deviceFindingCYPhone();
                }
            } else if (vp_b(bArr)) {
                if (iListener instanceof IFindPhonelistener) {
                    ((IFindPhonelistener) iListener).findPhone();
                }
            } else if (iListener instanceof IFindDevicelistener) {
                vp_b(bArr, (IFindDevicelistener) iListener);
            }
        }
    }
}
