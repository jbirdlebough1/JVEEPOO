package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IZT163DeviceAlwaysOffScreenOptListener;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_di.class */
public final class vp_di extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_d = new vp_a(null);
    private IZT163DeviceAlwaysOffScreenOptListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_di$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_di vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_di$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_di vp_b = new vp_di(null);

        private vp_b() {
        }

        public final vp_di vp_a() {
            return vp_b;
        }
    }

    private vp_di() {
    }

    public /* synthetic */ vp_di(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iZT163DeviceAlwaysOffScreenOptListener);
        this.vp_c = iZT163DeviceAlwaysOffScreenOptListener;
        byte[] bArr = {-113, 7, 3};
        com.veepoo.protocol.util.vp_w.vp_a("-合镁ZT163设备常灭屏-", "读取设备常灭屏状态：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-合镁ZT163设备常灭屏-", "设备常灭屏上报数据->：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr != null) {
            byte b = bArr[2];
            byte b2 = bArr[3];
            if (b == 1 || b == 2) {
                if (b2 == 1) {
                    IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener = this.vp_c;
                    if (iZT163DeviceAlwaysOffScreenOptListener != null) {
                        iZT163DeviceAlwaysOffScreenOptListener.onZT163DeviceAlwaysOffScreenSettingSuccess(b == 1);
                        return;
                    }
                    return;
                }
                IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener2 = this.vp_c;
                if (iZT163DeviceAlwaysOffScreenOptListener2 != null) {
                    iZT163DeviceAlwaysOffScreenOptListener2.onZT163DeviceAlwaysOffScreenSettingFailed();
                    return;
                }
                return;
            }
            if (b != 3) {
                com.veepoo.protocol.util.vp_w.vp_b("-合镁ZT163设备常灭屏-", "设备常灭屏上报异常数据！！！");
                return;
            }
            if (b2 == 0) {
                IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener3 = this.vp_c;
                if (iZT163DeviceAlwaysOffScreenOptListener3 != null) {
                    iZT163DeviceAlwaysOffScreenOptListener3.onFunctionNotSupport();
                    return;
                }
                return;
            }
            IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener4 = this.vp_c;
            if (iZT163DeviceAlwaysOffScreenOptListener4 != null) {
                iZT163DeviceAlwaysOffScreenOptListener4.onZT163DeviceAlwaysOffScreenReport(b2 == 2);
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IZT163DeviceAlwaysOffScreenOptListener iZT163DeviceAlwaysOffScreenOptListener) {
        super.vp_a(z, bluetoothClient, str, bleWriteResponse, iZT163DeviceAlwaysOffScreenOptListener);
        this.vp_c = iZT163DeviceAlwaysOffScreenOptListener;
        byte[] bArr = new byte[3];
        bArr[0] = -113;
        bArr[1] = 7;
        bArr[2] = z ? (byte) 1 : (byte) 2;
        com.veepoo.protocol.util.vp_w.vp_a("-合镁ZT163设备常灭屏-", "设置设备常灭屏>" + (z ? "开启" : "关闭") + "：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
