package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IBodyComponentReadDataListener;
import com.veepoo.protocol.listener.data.IBodyComponentReadIdListener;
import com.veepoo.protocol.listener.data.INewBodyComponentReportListener;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_k.class */
public final class vp_k extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_g = new vp_a(null);
    private IBodyComponentReadIdListener vp_c;
    private IBodyComponentReadDataListener vp_d;
    private INewBodyComponentReportListener vp_e;
    private final vp_l vp_f = new vp_l();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_k$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_k$vp_b.class */
    public static final class vp_b implements IBodyComponentReadIdListener {
        final /* synthetic */ BluetoothClient vp_b;
        final /* synthetic */ String vp_c;
        final /* synthetic */ BleWriteResponse vp_d;
        final /* synthetic */ IBodyComponentReadDataListener vp_e;

        vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
            this.vp_b = bluetoothClient;
            this.vp_c = str;
            this.vp_d = bleWriteResponse;
            this.vp_e = iBodyComponentReadDataListener;
        }

        @Override // com.veepoo.protocol.listener.data.IBodyComponentReadIdListener
        public void readIdFinish(ArrayList arrayList) {
            Intrinsics.checkNotNullParameter(arrayList, "ids");
            if (!arrayList.isEmpty()) {
                vp_k.this.vp_f.vp_a(this.vp_b, this.vp_c, this.vp_d, CollectionsKt.toIntArray(arrayList), this.vp_e);
                return;
            }
            IBodyComponentReadDataListener iBodyComponentReadDataListener = this.vp_e;
            if (iBodyComponentReadDataListener != null) {
                iBodyComponentReadDataListener.readBodyComponentDataFinish(new ArrayList());
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        byte b = bArr[1];
        if (b != 5) {
            if (b == 6) {
                this.vp_f.handler(bArr);
                return;
            }
            return;
        }
        com.veepoo.protocol.util.vp_w.vp_a("-身体成分测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[2] == -95) {
            INewBodyComponentReportListener iNewBodyComponentReportListener = this.vp_e;
            if (iNewBodyComponentReportListener != null) {
                iNewBodyComponentReportListener.onNewBodyComponentReport();
                return;
            }
            return;
        }
        byte b2 = bArr[4];
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (b2 <= 7) {
            for (int i = 0; i < b2; i++) {
                int i2 = i * 2;
                arrayList.add(Integer.valueOf(com.veepoo.protocol.util.vp_g.vp_b(bArr[i2 + 6], bArr[i2 + 5])));
            }
        }
        com.veepoo.protocol.util.vp_w.vp_a("-身体成分测量数据处理-", "读取身体成分ID => id个数：" + b2 + " array :" + Arrays.toString(arrayList.toArray()));
        IBodyComponentReadIdListener iBodyComponentReadIdListener = this.vp_c;
        if (iBodyComponentReadIdListener != null) {
            iBodyComponentReadIdListener.readIdFinish(arrayList);
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iBodyComponentReadDataListener);
        this.vp_d = iBodyComponentReadDataListener;
        vp_a(bluetoothClient, str, bleWriteResponse, new vp_b(bluetoothClient, str, bleWriteResponse, iBodyComponentReadDataListener));
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ArrayList arrayList, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, arrayList, iBodyComponentReadDataListener);
        if (arrayList != null && !arrayList.isEmpty()) {
            this.vp_f.vp_a(bluetoothClient, str, bleWriteResponse, CollectionsKt.toIntArray(arrayList), iBodyComponentReadDataListener);
        } else if (iBodyComponentReadDataListener != null) {
            iBodyComponentReadDataListener.readBodyComponentDataFinish(new ArrayList());
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentReadIdListener iBodyComponentReadIdListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iBodyComponentReadIdListener);
        this.vp_c = iBodyComponentReadIdListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 5;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(INewBodyComponentReportListener iNewBodyComponentReportListener) {
        this.vp_e = iNewBodyComponentReportListener;
    }
}
