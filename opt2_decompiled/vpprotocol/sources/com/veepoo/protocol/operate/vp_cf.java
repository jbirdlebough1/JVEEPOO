package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IRemindEventListener;
import com.veepoo.protocol.model.datas.RemindEvent;
import com.veepoo.protocol.model.enums.ERemindEvent;
import com.veepoo.protocol.model.enums.ESportStatus;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cf.class */
public final class vp_cf extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_g = new vp_a(null);
    private IRemindEventListener vp_c;
    private final ArrayList vp_d = new ArrayList();
    private int vp_e;
    private int vp_f;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cf$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_cf vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cf$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_cf vp_b = new vp_cf();

        private vp_b() {
        }

        public final vp_cf vp_a() {
            return vp_b;
        }
    }

    private final List vp_a(byte[] bArr, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i3 = 0;
        while (i < length && (i2 = i + 1) < length) {
            byte b = bArr[i];
            int i4 = bArr[i2] & 255;
            int i5 = i + 2;
            int i6 = i5 + i4;
            if (i6 > length) {
                com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- TLV数据残缺，终止解析", null, 1, null);
                break;
            }
            if (b == -95) {
                int i7 = bArr[i5] & 255;
                i3 = i7;
                com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 解析到协议版本: " + i7, null, 1, null);
                i = i6;
            } else if (b == -94) {
                arrayList.add(vp_a(i3, bArr, i5, i4));
                i = i6;
            } else {
                i = i6;
            }
        }
        return arrayList;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IRemindEventListener iRemindEventListener;
        super.handler(bArr);
        if (bArr == null || bArr.length == 0 || bArr[0] != -102) {
            return;
        }
        com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 收到原始包: " + com.veepoo.protocol.util.vp_g.vp_d(bArr), null, 1, null);
        if (bArr.length < 10) {
            com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 数据包长度不足，丢弃", null, 1, null);
            return;
        }
        com.veepoo.protocol.util.vp_d vp_dVar = com.veepoo.protocol.util.vp_d.vp_a;
        this.vp_e = vp_dVar.vp_a(bArr, 2);
        int iVp_a = vp_dVar.vp_a(bArr, 6);
        this.vp_f = iVp_a;
        com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 分页信息：" + this.vp_e + " / " + iVp_a, null, 1, null);
        if (10 < bArr.length) {
            this.vp_d.addAll(ArraysKt.toList(ArraysKt.copyOfRange(bArr, 10, bArr.length)));
        }
        if (this.vp_e == this.vp_f) {
            com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 全部分包接收完毕，开始统一解析", null, 1, null);
            ArrayList<RemindEvent> arrayList = new ArrayList<>(vp_a(CollectionsKt.toByteArray(this.vp_d), 0));
            byte b = bArr[1];
            if (b == 1) {
                IRemindEventListener iRemindEventListener2 = this.vp_c;
                if (iRemindEventListener2 != null) {
                    iRemindEventListener2.onRemindEventReport(arrayList);
                }
            } else if (b == 2 && (iRemindEventListener = this.vp_c) != null) {
                iRemindEventListener.onRemindEventRead(arrayList);
            }
            vp_a();
        }
    }

    private final RemindEvent vp_a(int i, byte[] bArr, int i2, int i3) {
        int i4;
        ERemindEvent eRemindEvent;
        int i5 = i2 + i3;
        ERemindEvent eRemindEvent2 = ERemindEvent.ALL;
        long jVp_c = 0;
        ESportStatus statusByTag = ESportStatus.UNKNOWN;
        int i6 = 0;
        while (i2 < i5 && (i4 = i2 + 1) < i5) {
            byte b = bArr[i2];
            int i7 = bArr[i4] & 255;
            int i8 = i2 + 2;
            int i9 = i8 + i7;
            if (i9 > i5) {
                break;
            }
            if (b == -79) {
                byte b2 = bArr[i8];
                if (b2 == 1) {
                    eRemindEvent = ERemindEvent.FALL;
                } else {
                    eRemindEvent = b2 == 2 ? ERemindEvent.SEDENTARY : ERemindEvent.ALL;
                }
                eRemindEvent2 = eRemindEvent;
            } else if (b == -78) {
                if (i7 == 4) {
                    jVp_c = com.veepoo.protocol.util.vp_d.vp_a.vp_c(bArr, i8);
                }
            } else if (b == -77) {
                statusByTag = ESportStatus.INSTANCE.getStatusByTag(bArr[i8]);
            } else if (b == -76) {
                i6 = bArr[i8] & 255;
            }
            i2 = i9;
        }
        RemindEvent remindEvent = new RemindEvent(eRemindEvent2, jVp_c, statusByTag, i6);
        remindEvent.setVersion(i);
        return remindEvent;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ERemindEvent eRemindEvent, long j) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(eRemindEvent, "remindEvent");
        super.vp_a(bluetoothClient, str, bleWriteResponse, eRemindEvent, j);
        vp_a();
        byte[] bArrVp_a = vp_a(eRemindEvent, j);
        com.veepoo.protocol.util.vp_w.vp_a("-提醒事件- 发送读取指令: " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a), null, 1, null);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    private final byte[] vp_a(ERemindEvent eRemindEvent, long j) {
        byte[] bArr = {-102, 2, eRemindEvent.getTag(), 0, 0, 0, 0};
        com.veepoo.protocol.util.vp_d.vp_a.vp_a(bArr, 3, j);
        return bArr;
    }

    private final void vp_a() {
        this.vp_d.clear();
        this.vp_e = 0;
        this.vp_f = 0;
    }

    public final void vp_a(IRemindEventListener iRemindEventListener) {
        this.vp_c = iRemindEventListener;
    }
}
