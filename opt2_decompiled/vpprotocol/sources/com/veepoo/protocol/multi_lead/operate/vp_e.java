package com.veepoo.protocol.multi_lead.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGReadIdListener;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EMultiEcgDataType;
import com.veepoo.protocol.multi_lead.data.MultiEcgConfig;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgDetectListener;
import com.veepoo.protocol.multi_lead.listener.IMultiEcgReadDataListener;
import com.veepoo.protocol.multi_lead.listener.INewMultiECGDataReportListener;
import com.veepoo.protocol.util.VpBleByteUtil;
import com.veepoo.protocol.util.vp_r;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/operate/vp_e.class */
public class vp_e extends com.veepoo.protocol.vp_a {
    public static boolean vp_j = false;
    BluetoothClient vp_c;
    IECGReadIdListener vp_d;
    private INewMultiECGDataReportListener vp_e;
    vp_d vp_f = new vp_d();
    vp_f vp_g = new vp_f();
    List vp_h = new ArrayList();
    vp_r vp_i;

    @Override // com.veepoo.protocol.vp_a
    public void vp_br(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_br(bluetoothClient, str, bleWriteResponse);
        this.vp_c = bluetoothClient;
        if (vp_j) {
            return;
        }
        this.vp_g.vp_br(bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        byte b = bArr[0];
        if (b == -106) {
            vp_b(bArr);
            return;
        }
        if (b == -105) {
            if (bArr[1] == 5) {
                this.vp_f.handler(bArr);
            }
        } else if (b == -109 && bArr[1] == 8) {
            this.vp_g.handler(bArr);
        }
    }

    protected byte[] vp_a(byte b, TimeData timeData) {
        byte[] bArr = new byte[20];
        bArr[0] = -106;
        bArr[1] = b;
        short year = (short) timeData.getYear();
        bArr[2] = VpBleByteUtil.loUint16(year);
        bArr[3] = VpBleByteUtil.hiUint16(year);
        bArr[4] = VpBleByteUtil.loUint16((short) timeData.getMonth());
        bArr[5] = VpBleByteUtil.loUint16((short) timeData.getDay());
        bArr[6] = VpBleByteUtil.loUint16((short) timeData.getHour());
        bArr[7] = VpBleByteUtil.loUint16((short) timeData.getMinute());
        bArr[8] = VpBleByteUtil.loUint16((short) timeData.getSecond());
        return bArr;
    }

    public void vp_b(byte[] bArr) {
        INewMultiECGDataReportListener iNewMultiECGDataReportListener;
        byte b = bArr[1];
        byte b2 = bArr[2];
        new StringBuilder().append("ecg->id=").append(VpBleByteUtil.byte2HexForShow(bArr));
        if (b2 == 1) {
            this.vp_h.clear();
            this.vp_h.add(bArr);
            return;
        }
        if (b2 == 2) {
            this.vp_h.add(bArr);
            return;
        }
        if (b2 == 3) {
            vp_r vp_rVar = new vp_r(this.vp_h);
            this.vp_i = vp_rVar;
            this.vp_d.readIdFinish(vp_rVar.vp_a());
        } else if (b2 == 4) {
            this.vp_d.readIdFinish(null);
        } else {
            if (b != -91 || (iNewMultiECGDataReportListener = this.vp_e) == null) {
                return;
            }
            iNewMultiECGDataReportListener.onNewMultiECGDataReport();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, IECGReadIdListener iECGReadIdListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, timeData, iECGReadIdListener);
        this.vp_c = bluetoothClient;
        this.vp_d = iECGReadIdListener;
        byte[] bArrVp_a = vp_a((byte) 5, timeData);
        if (vp_j) {
            return;
        }
        super.send(bArrVp_a, this.vp_c, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, IMultiEcgReadDataListener iMultiEcgReadDataListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iArr, iMultiEcgReadDataListener);
        this.vp_c = bluetoothClient;
        this.vp_f.vp_a(bluetoothClient, str, bleWriteResponse, iArr, EMultiEcgDataType.ALL, iMultiEcgReadDataListener);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr) {
        super.vp_a(bArr);
        this.vp_g.vp_a(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, boolean z2) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2);
        this.vp_c = bluetoothClient;
        if (vp_j) {
            return;
        }
        this.vp_g.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2);
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z, IMultiEcgDetectListener iMultiEcgDetectListener, boolean z2, MultiEcgConfig multiEcgConfig) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2);
        this.vp_c = bluetoothClient;
        if (vp_j) {
            return;
        }
        this.vp_g.vp_a(bluetoothClient, str, bleWriteResponse, z, iMultiEcgDetectListener, z2, multiEcgConfig);
    }
}
