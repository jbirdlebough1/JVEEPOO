package com.veepoo.protocol.operate;

import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBPDetectDataListener;
import com.veepoo.protocol.model.datas.BpData;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.EBPDetectStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.shareprence.VpSpSaveUtil;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_f.class */
public class vp_f extends com.veepoo.protocol.vp_a {
    IBPDetectDataListener vp_c;
    ScheduledExecutorService vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_f$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EBPDetectModel.values().length];
            vp_a = iArr;
            try {
                iArr[EBPDetectModel.DETECT_MODEL_PRIVATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EBPDetectModel.DETECT_MODEL_PUBLIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_f$vp_b.class */
    class vp_b implements Runnable {
        private BpData vp_a;
        private int vp_b;
        private IBPDetectDataListener vp_c;

        public vp_b(BpData bpData, IBPDetectDataListener iBPDetectDataListener) {
            this.vp_a = bpData;
            this.vp_c = iBPDetectDataListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.vp_b + 2;
            this.vp_b = i;
            this.vp_a.setProgress(i);
            this.vp_c.onDataChange(this.vp_a);
            if (this.vp_b >= 100) {
                vp_f vp_fVar = vp_f.this;
                if (vp_fVar.vp_d != null) {
                    VpSpSaveUtil.getVpSpVariInstance(((com.veepoo.protocol.vp_a) vp_fVar).vp_a).saveDetectBp(false);
                    vp_f.this.vp_a();
                }
            }
        }
    }

    private BpData vp_b(byte[] bArr) {
        BpData bpData = new BpData();
        if (bArr.length < 6) {
            return bpData;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        int i3 = iArrByte2HexToIntArr[3];
        int i4 = iArrByte2HexToIntArr[4];
        if (bArr.length > 6) {
            if (iArrByte2HexToIntArr[5] == 1) {
                bpData.setHaveProgress(true);
            } else {
                bpData.setHaveProgress(false);
            }
        }
        bpData.setHighPressure(i);
        bpData.setLowPressure(i2);
        bpData.setProgress(i3);
        if (i4 == 1 || i4 == 2 || i4 == 4 || i4 == 5 || i4 == 255) {
            bpData.setStatus(EBPDetectStatus.STATE_BP_BUSY);
        } else if (i4 == 254) {
            bpData.setStatus(EBPDetectStatus.STATE_BP_LOW_BATTERY);
        } else if (i4 == 253) {
            bpData.setStatus(EBPDetectStatus.STATE_BP_CHARGING);
        } else if (i4 == 252) {
            bpData.setStatus(EBPDetectStatus.STATE_BP_WEAR_OFF);
        } else {
            bpData.setStatus(EBPDetectStatus.STATE_BP_NORMAL);
        }
        return bpData;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        BpData bpDataVp_b = vp_b(bArr);
        Log.e("Blood Pressure", com.veepoo.protocol.util.vp_g.vp_b(bArr) + ">>>> bpData = " + bpDataVp_b.toString());
        if (bArr.length <= 6 || bArr[5] != 1) {
            vp_a(bpDataVp_b);
        } else {
            this.vp_c.onDataChange(bpDataVp_b);
            if (bpDataVp_b.getProgress() >= 100) {
                VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDetectBp(false);
            } else {
                VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDetectBp(true);
            }
        }
        if (bpDataVp_b.getStatus() == EBPDetectStatus.STATE_BP_BUSY) {
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDetectBp(false);
        }
    }

    private byte[] vp_b(EBPDetectModel eBPDetectModel) {
        int i = vp_a.vp_a[eBPDetectModel.ordinal()];
        if (i != 1) {
            return i != 2 ? com.veepoo.protocol.profile.vp_a.vp_ar : com.veepoo.protocol.profile.vp_a.vp_ar;
        }
        return com.veepoo.protocol.profile.vp_a.vp_as;
    }

    private void vp_a(BpData bpData) {
        vp_a();
        this.vp_d = Executors.newSingleThreadScheduledExecutor();
        if (bpData.getHighPressure() == 0 && bpData.getLowPressure() == 0) {
            this.vp_d.scheduleAtFixedRate(new vp_b(bpData, this.vp_c), 0L, 1100L, TimeUnit.MILLISECONDS);
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDetectBp(true);
        } else {
            VpSpSaveUtil.getVpSpVariInstance(this.vp_a).saveDetectBp(false);
            bpData.setProgress(100);
            this.vp_c.onDataChange(bpData);
            vp_a();
        }
    }

    private byte[] vp_a(EBPDetectModel eBPDetectModel) {
        int i = vp_a.vp_a[eBPDetectModel.ordinal()];
        if (i != 1) {
            return i != 2 ? com.veepoo.protocol.profile.vp_a.vp_ap : com.veepoo.protocol.profile.vp_a.vp_ap;
        }
        return com.veepoo.protocol.profile.vp_a.vp_aq;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBPDetectModel eBPDetectModel) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, eBPDetectModel);
        super.send(vp_b(eBPDetectModel), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IBPDetectDataListener) iListener;
        Log.e("Blood Pressure", com.veepoo.protocol.util.vp_g.vp_b(bArr) + ">>>> listener is null? = " + (iListener == null));
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isDetectingBp()) {
            handler(bArr);
        } else {
            vp_a();
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EBPDetectModel eBPDetectModel) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, eBPDetectModel);
        super.send(vp_a(eBPDetectModel), bluetoothClient, str, bleWriteResponse);
    }

    private void vp_a() {
        ScheduledExecutorService scheduledExecutorService = this.vp_d;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            return;
        }
        this.vp_d.shutdown();
    }
}
