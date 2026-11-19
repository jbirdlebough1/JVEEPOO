package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IFatigueDataListener;
import com.veepoo.protocol.model.datas.FatigueData;
import com.veepoo.protocol.model.enums.EDeviceStatus;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_an.class */
public final class vp_an extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IFatigueDataListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_an$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IFatigueDataListener iFatigueDataListener) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iFatigueDataListener, "detectListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, iFatigueDataListener);
        this.vp_c = iFatigueDataListener;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 10;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bo(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_bo(bluetoothClient, str, bleWriteResponse);
        this.vp_c = this.vp_c;
        byte[] bArr = new byte[20];
        bArr[0] = -119;
        bArr[1] = 10;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IFatigueDataListener iFatigueDataListener;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-疲劳度测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 10) {
            FatigueData fatigueData = new FatigueData();
            byte b = bArr[2];
            if (b == 1) {
                switch (bArr[3] & 255) {
                    case 0:
                        int i = bArr[4] & 255;
                        int i2 = bArr[5] & 255;
                        if (i == 100) {
                            fatigueData.setDeviceState(EDeviceStatus.FINISH);
                        } else {
                            fatigueData.setDeviceState(EDeviceStatus.FREE);
                        }
                        fatigueData.setValue(i2);
                        fatigueData.setProgress(i);
                        IFatigueDataListener iFatigueDataListener2 = this.vp_c;
                        iFatigueDataListener = iFatigueDataListener2;
                        if (iFatigueDataListener2 == null) {
                            return;
                        }
                        break;
                    case 1:
                    case 3:
                        fatigueData.setDeviceState(EDeviceStatus.BUSY);
                        IFatigueDataListener iFatigueDataListener3 = this.vp_c;
                        iFatigueDataListener = iFatigueDataListener3;
                        if (iFatigueDataListener3 == null) {
                            return;
                        }
                        break;
                    case 2:
                        fatigueData.setDeviceState(EDeviceStatus.CHARG_LOW);
                        IFatigueDataListener iFatigueDataListener4 = this.vp_c;
                        iFatigueDataListener = iFatigueDataListener4;
                        if (iFatigueDataListener4 == null) {
                            return;
                        }
                        break;
                    case 4:
                        fatigueData.setDeviceState(EDeviceStatus.UNPASS_WEAR);
                        IFatigueDataListener iFatigueDataListener5 = this.vp_c;
                        iFatigueDataListener = iFatigueDataListener5;
                        if (iFatigueDataListener5 == null) {
                            return;
                        }
                        break;
                    default:
                        return;
                }
            } else {
                if (b != 2) {
                    return;
                }
                fatigueData.setDeviceState(EDeviceStatus.FINISH);
                IFatigueDataListener iFatigueDataListener6 = this.vp_c;
                iFatigueDataListener = iFatigueDataListener6;
                if (iFatigueDataListener6 == null) {
                    return;
                }
            }
            iFatigueDataListener.onFatigueDataListener(fatigueData);
        }
    }
}
