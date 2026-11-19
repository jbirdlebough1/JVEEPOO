package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.ICuffBPDetectDataListener;
import com.veepoo.protocol.model.datas.VPCuffBPTestData;
import com.veepoo.protocol.model.enums.EVPCuffBPTestStatus;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_t.class */
public final class vp_t extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_h = new vp_a(null);
    private final List vp_c = new ArrayList();
    private final List vp_d = new ArrayList();
    private final List vp_e = new ArrayList();
    private final List vp_f = new ArrayList();
    private ICuffBPDetectDataListener vp_g;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_t$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.veepoo.protocol.vp_a
    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_f(bluetoothClient, str, bleWriteResponse, z);
        byte[] bArr = new byte[20];
        bArr[0] = -112;
        bArr[1] = 0;
        bArr[2] = 5;
        bArr[3] = z;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        VPCuffBPTestData vPCuffBPTestData = new VPCuffBPTestData();
        vPCuffBPTestData.setSbpValue(VpBleByteUtil.twoByteToUnsignedInt2(bArr[1], bArr[2]));
        vPCuffBPTestData.setDbpValue(VpBleByteUtil.twoByteToUnsignedInt2(bArr[3], bArr[4]));
        vPCuffBPTestData.setStatus(EVPCuffBPTestStatus.INSTANCE.fromRawValue(bArr[6] & 255));
        vPCuffBPTestData.setHeartValue(bArr[8] & 255);
        vPCuffBPTestData.setPressureValue(VpBleByteUtil.twoByteToUnsignedInt2(bArr[9], bArr[10]));
        vPCuffBPTestData.setRate(bArr[11] & 255);
        boolean z = bArr[5] == 10;
        if (z) {
            vPCuffBPTestData.setPpg80Datas(this.vp_c);
            vPCuffBPTestData.setPpg85Datas(this.vp_d);
            vPCuffBPTestData.setGsensorDatas(this.vp_e);
            vPCuffBPTestData.setCuffDatas(this.vp_f);
        }
        ICuffBPDetectDataListener iCuffBPDetectDataListener = this.vp_g;
        if (iCuffBPDetectDataListener != null) {
            iCuffBPDetectDataListener.onCuffDataChange(z, vPCuffBPTestData);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        super.vp_a(bArr);
        if (bArr == null) {
            return;
        }
        byte b = bArr[0];
        if (b == -128) {
            this.vp_c.add(bArr);
            return;
        }
        if (b == -123) {
            this.vp_d.add(bArr);
        } else if (b == -119) {
            this.vp_e.add(bArr);
        } else if (b == -108) {
            this.vp_f.add(bArr);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, ICuffBPDetectDataListener iCuffBPDetectDataListener) {
        Intrinsics.checkNotNullParameter(iCuffBPDetectDataListener, "bpDetectDataListener");
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, iCuffBPDetectDataListener);
        this.vp_g = iCuffBPDetectDataListener;
        this.vp_c.clear();
        this.vp_d.clear();
        this.vp_e.clear();
        this.vp_f.clear();
        byte[] bArr = new byte[20];
        bArr[0] = -112;
        bArr[1] = 1;
        bArr[2] = 5;
        bArr[3] = z;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
