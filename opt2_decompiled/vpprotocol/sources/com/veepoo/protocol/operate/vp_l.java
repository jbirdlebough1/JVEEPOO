package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IBodyComponentReadDataListener;
import com.veepoo.protocol.model.datas.BodyComponent;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_l.class */
public final class vp_l extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_l = new vp_a(null);
    private int vp_e;
    private int vp_f;
    private IBodyComponentReadDataListener vp_h;
    private BluetoothClient vp_i;
    private String vp_j;
    private BleWriteResponse vp_k;
    private List vp_c = new ArrayList();
    private final List vp_d = new ArrayList();
    private int[] vp_g = new int[0];

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_l$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void vp_b(int i) {
        byte[] bArrVp_a = vp_a(i);
        String str = "readBodyComponentDataById: cmdBodyComponentData：" + VpBleByteUtil.byte2HexForShow(bArrVp_a);
        super.send(bArrVp_a, this.vp_i, this.vp_j, this.vp_k);
    }

    private final BodyComponent vp_a(List list) {
        BodyComponent bodyComponent = null;
        if (list.isEmpty()) {
            return null;
        }
        byte[] bArr = new byte[list.size() * 15];
        int i = 0;
        for (Object obj : list) {
            int i2 = i;
            int i3 = i + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            byte[] bArr2 = (byte[]) obj;
            int i4 = 0;
            int i5 = 0;
            int length = bArr2.length;
            while (i5 < length) {
                int i6 = i4;
                byte b = bArr2[i5];
                int i7 = i4 + 1;
                if (i6 >= 5) {
                    bArr[((i * 15) + i4) - 5] = b;
                }
                i5++;
                i4 = i7;
            }
            i = i3;
        }
        if (bArr[0] == -96) {
            byte b2 = bArr[1];
            BodyComponent bodyComponent2 = new BodyComponent(com.veepoo.protocol.util.vp_g.vp_b(bArr[3], bArr[2]), com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]), com.veepoo.protocol.util.vp_g.vp_b(bArr[7], bArr[6]), com.veepoo.protocol.util.vp_g.vp_b(bArr[9], bArr[8]), com.veepoo.protocol.util.vp_g.vp_b(bArr[11], bArr[10]), com.veepoo.protocol.util.vp_g.vp_b(bArr[13], bArr[12]), com.veepoo.protocol.util.vp_g.vp_b(bArr[15], bArr[14]), com.veepoo.protocol.util.vp_g.vp_b(bArr[17], bArr[16]), com.veepoo.protocol.util.vp_g.vp_b(bArr[19], bArr[18]), com.veepoo.protocol.util.vp_g.vp_b(bArr[21], bArr[20]), com.veepoo.protocol.util.vp_g.vp_b(bArr[23], bArr[22]), com.veepoo.protocol.util.vp_g.vp_b(bArr[25], bArr[24]), com.veepoo.protocol.util.vp_g.vp_b(bArr[27], bArr[26]), com.veepoo.protocol.util.vp_g.vp_b(bArr[29], bArr[28]));
            bodyComponent2.setId(this.vp_g[this.vp_f]);
            bodyComponent2.setIdType(1);
            if (bArr[30] == -95 && bArr[31] == 7) {
                byte b3 = bArr[32];
                byte b4 = bArr[33];
                byte b5 = bArr[34];
                byte b6 = bArr[35];
                byte b7 = bArr[36];
                byte b8 = bArr[37];
                byte b9 = bArr[38];
                TimeData timeData = new TimeData();
                timeData.year = b3 + 2000;
                timeData.month = b4;
                timeData.day = b5;
                timeData.hour = b6;
                timeData.minute = b7;
                timeData.second = b8;
                if (b3 == 0 && b4 == 0 && b5 == 0) {
                    return null;
                }
                bodyComponent2.setTimeBean(timeData);
                bodyComponent2.setDuration(b9);
            }
            bodyComponent = bodyComponent2;
        }
        return bodyComponent;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr == null) {
            return;
        }
        byte b = bArr[2];
        byte b2 = bArr[3];
        byte b3 = bArr[4];
        if (b2 == 1) {
            this.vp_d.clear();
        }
        this.vp_d.add(bArr);
        if (b2 == b3) {
            BodyComponent bodyComponentVp_a = vp_a(this.vp_d);
            if (bodyComponentVp_a != null) {
                bodyComponentVp_a.setIdType(b);
                this.vp_c.add(bodyComponentVp_a);
            }
            vp_a();
        }
    }

    private final byte[] vp_a(int i) {
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 6;
        bArr[2] = 1;
        short s = (short) i;
        bArr[3] = VpBleByteUtil.loUint16(s);
        bArr[4] = VpBleByteUtil.hiUint16(s);
        return bArr;
    }

    public final void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int[] iArr, IBodyComponentReadDataListener iBodyComponentReadDataListener) {
        Intrinsics.checkNotNullParameter(iArr, "ids");
        this.vp_i = bluetoothClient;
        this.vp_j = str;
        this.vp_k = bleWriteResponse;
        this.vp_g = iArr;
        this.vp_h = iBodyComponentReadDataListener;
        String string = Arrays.toString(iArr);
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String str2 = "手动:ID列表->" + string;
        int[] iArr2 = this.vp_g;
        if (iArr2.length == 0) {
            if (iBodyComponentReadDataListener != null) {
                iBodyComponentReadDataListener.readBodyComponentDataFinish(new ArrayList());
            }
        } else {
            this.vp_f = 0;
            this.vp_e = iArr2.length;
            if (!this.vp_c.isEmpty()) {
                this.vp_c.clear();
            }
            vp_b(iArr[0]);
        }
    }

    private final void vp_a() {
        int i = this.vp_f + 1;
        this.vp_f = i;
        if (i < this.vp_e) {
            vp_b(this.vp_g[i]);
            return;
        }
        IBodyComponentReadDataListener iBodyComponentReadDataListener = this.vp_h;
        if (iBodyComponentReadDataListener != null) {
            iBodyComponentReadDataListener.readBodyComponentDataFinish(this.vp_c);
        }
    }
}
