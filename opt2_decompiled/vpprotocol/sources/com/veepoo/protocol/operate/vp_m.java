package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IBodyComponentDetectListener;
import com.veepoo.protocol.model.datas.BodyComponent;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.DetectState;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_m.class */
public final class vp_m extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_e = new vp_a(null);
    private final List vp_c = new ArrayList();
    private IBodyComponentDetectListener vp_d;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_m$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_m$vp_b.class */
    public /* synthetic */ class vp_b {
        public static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[DetectState.values().length];
            try {
                iArr[DetectState.PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DetectState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DetectState.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DetectState.BUSY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DetectState.LOW_POWER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            vp_a = iArr;
        }
    }

    private final BodyComponent vp_a(List list) {
        BodyComponent bodyComponent = null;
        if (list.isEmpty()) {
            return null;
        }
        byte[] bArr = new byte[list.size() * 14];
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
                if (i6 >= 6) {
                    bArr[((i * 14) + i4) - 6] = b;
                }
                i5++;
                i4 = i7;
            }
            i = i3;
        }
        if (bArr[0] == -96) {
            BodyComponent bodyComponent2 = new BodyComponent(com.veepoo.protocol.util.vp_g.vp_b(bArr[3], bArr[2]), com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]), com.veepoo.protocol.util.vp_g.vp_b(bArr[7], bArr[6]), com.veepoo.protocol.util.vp_g.vp_b(bArr[9], bArr[8]), com.veepoo.protocol.util.vp_g.vp_b(bArr[11], bArr[10]), com.veepoo.protocol.util.vp_g.vp_b(bArr[13], bArr[12]), com.veepoo.protocol.util.vp_g.vp_b(bArr[15], bArr[14]), com.veepoo.protocol.util.vp_g.vp_b(bArr[17], bArr[16]), com.veepoo.protocol.util.vp_g.vp_b(bArr[19], bArr[18]), com.veepoo.protocol.util.vp_g.vp_b(bArr[21], bArr[20]), com.veepoo.protocol.util.vp_g.vp_b(bArr[23], bArr[22]), com.veepoo.protocol.util.vp_g.vp_b(bArr[25], bArr[24]), com.veepoo.protocol.util.vp_g.vp_b(bArr[27], bArr[26]), com.veepoo.protocol.util.vp_g.vp_b(bArr[29], bArr[28]));
            bodyComponent2.setTimeBean(new TimeData());
            bodyComponent2.setIdType(2);
            bodyComponent = bodyComponent2;
        }
        return bodyComponent;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bl(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_bl(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 4;
        bArr[2] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) throws NoWhenBranchMatchedException {
        IBodyComponentDetectListener iBodyComponentDetectListener;
        BodyComponent bodyComponentVp_a;
        Intrinsics.checkNotNullParameter(bArr, "data");
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-身体成分测量-", "收到数据: " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr[1] == 4) {
            byte b = bArr[2];
            if (b != 1) {
                if (b != 2 || (iBodyComponentDetectListener = this.vp_d) == null) {
                    return;
                }
                iBodyComponentDetectListener.onDetectStop();
                return;
            }
            DetectState detectStateCode2State = DetectState.INSTANCE.code2State(bArr[3]);
            switch (vp_b.vp_a[detectStateCode2State.ordinal()]) {
                case 1:
                    byte b2 = bArr[4];
                    byte b3 = bArr[5];
                    IBodyComponentDetectListener iBodyComponentDetectListener2 = this.vp_d;
                    if (iBodyComponentDetectListener2 != null) {
                        iBodyComponentDetectListener2.onDetecting(b2, b3);
                        return;
                    }
                    return;
                case 2:
                    byte b4 = bArr[4];
                    byte b5 = bArr[5];
                    if (b4 == 1) {
                        this.vp_c.clear();
                    }
                    this.vp_c.add(bArr);
                    if (b4 != b5 || (bodyComponentVp_a = vp_a(this.vp_c)) == null) {
                        return;
                    }
                    bodyComponentVp_a.setId(-1);
                    bodyComponentVp_a.setIdType(2);
                    IBodyComponentDetectListener iBodyComponentDetectListener3 = this.vp_d;
                    if (iBodyComponentDetectListener3 != null) {
                        iBodyComponentDetectListener3.onDetectSuccess(bodyComponentVp_a);
                        return;
                    }
                    return;
                case 3:
                case 4:
                case 5:
                    IBodyComponentDetectListener iBodyComponentDetectListener4 = this.vp_d;
                    if (iBodyComponentDetectListener4 != null) {
                        iBodyComponentDetectListener4.onDetectFailed(detectStateCode2State);
                        return;
                    }
                    return;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IBodyComponentDetectListener iBodyComponentDetectListener) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, iBodyComponentDetectListener);
        this.vp_d = iBodyComponentDetectListener;
        byte[] bArr = new byte[20];
        bArr[0] = -109;
        bArr[1] = 4;
        bArr[2] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
