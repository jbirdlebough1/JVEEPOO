package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IECGReadDataListener;
import com.veepoo.protocol.listener.data.IECGReadIdListener;
import com.veepoo.protocol.listener.data.INewECGDataReportListener;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EEcgDataType;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ah.class */
public class vp_ah extends com.veepoo.protocol.vp_a {
    IECGReadDataListener vp_c;
    IECGReadIdListener vp_d;
    private INewECGDataReportListener vp_e;
    vp_ag vp_f = new vp_ag();
    vp_af vp_g = new vp_af();
    List vp_h = new ArrayList();
    com.veepoo.protocol.util.vp_r vp_i;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ah$vp_a.class */
    class vp_a implements IECGReadIdListener {
        vp_a() {
        }

        @Override // com.veepoo.protocol.listener.data.IECGReadIdListener
        public void readIdFinish(int[] iArr) {
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ah$vp_b.class */
    class vp_b implements IECGReadIdListener {
        final /* synthetic */ BluetoothClient vp_a;
        final /* synthetic */ String vp_b;
        final /* synthetic */ BleWriteResponse vp_c;
        final /* synthetic */ boolean vp_d;

        vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
            this.vp_a = bluetoothClient;
            this.vp_b = str;
            this.vp_c = bleWriteResponse;
            this.vp_d = z;
        }

        @Override // com.veepoo.protocol.listener.data.IECGReadIdListener
        public void readIdFinish(int[] iArr) {
            if (iArr == null || iArr.length == 0) {
                vp_ah.this.vp_c.readDataFinish(Collections.EMPTY_LIST);
            } else {
                vp_ah vp_ahVar = vp_ah.this;
                vp_ahVar.vp_f.vp_a(this.vp_a, this.vp_b, this.vp_c, iArr, this.vp_d, vp_ahVar.vp_c);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ah$vp_c.class */
    class vp_c implements IECGReadIdListener {
        final /* synthetic */ BluetoothClient vp_a;
        final /* synthetic */ String vp_b;
        final /* synthetic */ BleWriteResponse vp_c;
        final /* synthetic */ boolean vp_d;

        vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
            this.vp_a = bluetoothClient;
            this.vp_b = str;
            this.vp_c = bleWriteResponse;
            this.vp_d = z;
        }

        @Override // com.veepoo.protocol.listener.data.IECGReadIdListener
        public void readIdFinish(int[] iArr) {
            if (iArr == null || iArr.length == 0) {
                vp_ah.this.vp_c.readDataFinish(Collections.EMPTY_LIST);
            } else {
                vp_ah vp_ahVar = vp_ah.this;
                vp_ahVar.vp_g.vp_b(this.vp_a, this.vp_b, this.vp_c, iArr, this.vp_d, vp_ahVar.vp_c);
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ah$vp_d.class */
    static /* synthetic */ class vp_d {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EEcgDataType.values().length];
            vp_a = iArr;
            try {
                iArr[EEcgDataType.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EEcgDataType.AUTO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EEcgDataType.MANUALLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
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
            byte b2 = bArr[1];
            if (b2 == 2) {
                this.vp_f.handler(bArr);
            } else if (b2 == 3) {
                this.vp_g.handler(bArr);
            }
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
        INewECGDataReportListener iNewECGDataReportListener;
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
        if (b2 != 3) {
            if (b2 == 4) {
                new StringBuilder().append("ecg->no id get:").append((int) b);
                this.vp_d.readIdFinish(null);
                return;
            } else {
                if ((b == -94 || b == -93) && (iNewECGDataReportListener = this.vp_e) != null) {
                    iNewECGDataReportListener.onNewECGDetectDataReport();
                    return;
                }
                return;
            }
        }
        com.veepoo.protocol.util.vp_r vp_rVar = new com.veepoo.protocol.util.vp_r(this.vp_h);
        this.vp_i = vp_rVar;
        int[] iArrVp_c = vp_rVar.vp_c();
        int[] iArrVp_b = this.vp_i.vp_b();
        if (b == 2) {
            this.vp_d.readIdFinish(iArrVp_c);
        } else if (b == 3) {
            this.vp_d.readIdFinish(iArrVp_b);
        }
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, EEcgDataType eEcgDataType, IECGReadDataListener iECGReadDataListener, boolean z) {
        this.vp_c = iECGReadDataListener;
        int i = vp_d.vp_a[eEcgDataType.ordinal()];
        if (i == 1) {
            vp_a(bluetoothClient, str, bleWriteResponse, timeData, eEcgDataType, new vp_a());
        } else if (i == 2) {
            vp_a(bluetoothClient, str, bleWriteResponse, timeData, eEcgDataType, new vp_c(bluetoothClient, str, bleWriteResponse, z));
        } else {
            if (i != 3) {
                return;
            }
            vp_a(bluetoothClient, str, bleWriteResponse, timeData, eEcgDataType, new vp_b(bluetoothClient, str, bleWriteResponse, z));
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, TimeData timeData, EEcgDataType eEcgDataType, IECGReadIdListener iECGReadIdListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, timeData, eEcgDataType, iECGReadIdListener);
        this.vp_d = iECGReadIdListener;
        byte[] bArrVp_a = null;
        int i = vp_d.vp_a[eEcgDataType.ordinal()];
        if (i == 1) {
            bArrVp_a = vp_a((byte) 1, timeData);
        } else if (i == 2) {
            bArrVp_a = vp_a((byte) 3, timeData);
        } else if (i == 3) {
            bArrVp_a = vp_a((byte) 2, timeData);
        }
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, boolean z, IECGReadDataListener iECGReadDataListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iArr, z, iECGReadDataListener);
        this.vp_f.vp_a(bluetoothClient, str, bleWriteResponse, iArr, z, iECGReadDataListener);
    }

    public void vp_a(INewECGDataReportListener iNewECGDataReportListener) {
        this.vp_e = iNewECGDataReportListener;
    }
}
