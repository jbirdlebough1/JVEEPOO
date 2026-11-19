package com.veepoo.protocol.operate;

import android.os.SystemClock;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.ICustomProtocolStateListener;
import com.veepoo.protocol.listener.data.IQH15HealthDataOptListener;
import com.veepoo.protocol.listener.data.IReadIMEIInfoListener;
import com.veepoo.protocol.listener.data.ITCMDataListener;
import com.veepoo.protocol.model.datas.AutoDetectStateData;
import com.veepoo.protocol.model.datas.QH15HealthData;
import com.veepoo.protocol.model.datas.TCMDataReport;
import com.veepoo.protocol.model.enums.ECustomStatus;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.model.enums.EQH15ComplianceType;
import com.veepoo.protocol.model.enums.TCMType;
import com.veepoo.protocol.model.settings.AutoDetectStateSetting;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_y.class */
public final class vp_y extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_i = new vp_a(null);
    private ICustomProtocolStateListener vp_c;
    private ITCMDataListener vp_d;
    private IQH15HealthDataOptListener vp_e;
    private IReadIMEIInfoListener vp_f;
    private byte[] vp_g;
    private int vp_h;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_y$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_y vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_y$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_y vp_b = new vp_y();

        private vp_b() {
        }

        public final vp_y vp_a() {
            return vp_b;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_y$vp_c.class */
    public /* synthetic */ class vp_c {
        public static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EFunctionStatus.values().length];
            try {
                iArr[EFunctionStatus.SUPPORT_OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EFunctionStatus.SUPPORT_CLOSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            vp_a = iArr;
        }
    }

    private final AutoDetectStateData vp_i(byte[] bArr) {
        AutoDetectStateData autoDetectStateData = new AutoDetectStateData();
        autoDetectStateData.setOprateType(vp_b(bArr[3]));
        autoDetectStateData.setSpo2h24Hour(vp_a(bArr[3]));
        return autoDetectStateData;
    }

    private final void vp_k(byte[] bArr) {
        if (bArr.length >= 7 && bArr[0] == 2 && bArr[1] == 5) {
            byte b = bArr[4];
            int i = ((bArr[6] << 8) | (bArr[5] & 255)) + 7;
            if (i > bArr.length) {
                return;
            }
            vp_l(ArraysKt.copyOfRange(bArr, 7, i));
        }
    }

    private final void vp_l(byte[] bArr) {
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM) 解析TLV Data=" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        int i = 0;
        int length = bArr.length;
        while (true) {
            int i2 = i + 2;
            if (i2 > length) {
                return;
            }
            int i3 = bArr[i] & 255;
            int i4 = bArr[i + 1] & 255;
            int i5 = i2 + i4;
            if (i5 > length) {
                return;
            }
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, i2, i5);
            com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM) 解析TLV:T=" + i3 + " L=" + i4 + " V=" + com.veepoo.protocol.util.vp_g.vp_d(bArrCopyOfRange));
            if (i3 != 160) {
                if (i3 == 161 && bArrCopyOfRange.length == 4) {
                    long jVp_d = vp_d(bArrCopyOfRange);
                    IQH15HealthDataOptListener iQH15HealthDataOptListener = this.vp_e;
                    if (iQH15HealthDataOptListener != null) {
                        iQH15HealthDataOptListener.onLastHealthDataTimestampRead(jVp_d);
                    }
                }
            } else if (bArrCopyOfRange.length == 10) {
                long jVp_d2 = vp_d(ArraysKt.copyOfRange(bArrCopyOfRange, 0, 4));
                int iVp_c = vp_c(ArraysKt.copyOfRange(bArrCopyOfRange, 4, 8));
                int iVp_b = vp_b(ArraysKt.copyOfRange(bArrCopyOfRange, 8, 10));
                IQH15HealthDataOptListener iQH15HealthDataOptListener2 = this.vp_e;
                if (iQH15HealthDataOptListener2 != null) {
                    iQH15HealthDataOptListener2.onStepReport(jVp_d2, iVp_c, iVp_b);
                }
            }
            i = i5;
        }
    }

    private final long vp_d(byte[] bArr) {
        return (((long) bArr[0]) & 255) | ((((long) bArr[1]) & 255) << 8) | ((((long) bArr[2]) & 255) << 16) | ((((long) bArr[3]) & 255) << 24);
    }

    private final int vp_c(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[1] & 255) << 8) | ((bArr[2] & 255) << 16) | ((bArr[3] & 255) << 24);
    }

    private final int vp_b(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[1] & 255) << 8);
    }

    private final byte[] vp_e() {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 3;
        bArr[2] = 1;
        bArr[3] = 1;
        bArr[4] = 1;
        return bArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect negative register number in instruction: 0x0038: INVOKE (r-1 I:java.util.Map) INTERFACE call: java.util.Map.entrySet():java.util.Set
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:76)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private final byte[] vp_a(long r11, java.util.Map r13) {
        /*
            r10 = this;
            r0 = r13
            java.util.ArrayList r1 = new java.util.ArrayList
            r2 = r1
            r13 = r2
            r2 = r10
            r3 = r11
            r4 = r13
            r5 = r4
            r6 = r5; r0 = r0; 
            r6.<init>()
            r6 = 0
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = 4
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            boolean r4 = r4.add(r5)
            r4 = 0
            java.lang.Byte r4 = java.lang.Byte.valueOf(r4)
            boolean r3 = r3.add(r4)
            byte[] r1 = r1.vp_a(r2)
            java.util.List r1 = kotlin.collections.ArraysKt.toList(r1)
            boolean r0 = r0.addAll(r1)
            r-1.entrySet()
            r-1.iterator()
            r10 = r-1
        L43:
            r0 = r10
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Lb4
            r0 = r13
            r1 = r10
            java.lang.Object r1 = r1.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r2 = r1
            java.lang.Object r2 = r2.getKey()
            com.veepoo.protocol.model.enums.TCMType r2 = (com.veepoo.protocol.model.enums.TCMType) r2
            r11 = r2
            java.lang.Object r1 = r1.getValue()
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            r2 = r1
            r3 = r13
            r4 = r2; r2 = r3; r3 = r4; 
            r4 = r13
            r5 = r4
            r6 = r5
            r7 = r11
            byte r7 = r7.getTag()
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)
            boolean r6 = r6.add(r7)
            r6 = 2
            java.lang.Byte r6 = java.lang.Byte.valueOf(r6)
            boolean r5 = r5.add(r6)
            r5 = 0
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            boolean r4 = r4.add(r5)
            r4 = 255(0xff, float:3.57E-43)
            r3 = r3 & r4
            byte r3 = (byte) r3
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            boolean r2 = r2.add(r3)
            r2 = 8
            int r1 = r1 >> r2
            r2 = 255(0xff, float:3.57E-43)
            r1 = r1 & r2
            byte r1 = (byte) r1
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)
            boolean r0 = r0.add(r1)
            goto L43
        Lb4:
            r0 = r13
            byte[] r0 = kotlin.collections.CollectionsKt.toByteArray(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.veepoo.protocol.operate.vp_y.vp_a(long, java.util.Map):byte[]");
    }

    private final TCMDataReport vp_j(byte[] bArr) {
        long j = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            int i3 = i2 + 3;
            if (i3 > bArr.length) {
                break;
            }
            byte b = bArr[i2];
            int i4 = (bArr[i2 + 1] & 255) | ((bArr[i2 + 2] & 255) << 8);
            int i5 = i3 + i4;
            if (i5 > bArr.length) {
                break;
            }
            if (b != 0) {
                TCMType tCMTypeFromTag = TCMType.INSTANCE.fromTag(b);
                if (tCMTypeFromTag != null && i4 >= 2) {
                    linkedHashMap.put(tCMTypeFromTag, Integer.valueOf((bArr[i3] & 255) | ((bArr[i2 + 4] & 255) << 8)));
                }
            } else if (i4 >= 4) {
                j = (((long) bArr[i3]) & 255) | ((((long) bArr[i2 + 4]) & 255) << 8) | ((((long) bArr[i2 + 5]) & 255) << 16) | ((((long) bArr[i2 + 6]) & 255) << 24);
            }
            i = i5;
        }
        return new TCMDataReport(j, linkedHashMap);
    }

    private final void vp_g(byte[] bArr) {
        AutoDetectStateData autoDetectStateDataVp_i = vp_i(bArr);
        ICustomProtocolStateListener iCustomProtocolStateListener = this.vp_c;
        if (iCustomProtocolStateListener != null) {
            iCustomProtocolStateListener.onS22AutoDetectStateChangeListener(autoDetectStateDataVp_i);
        }
    }

    private final void vp_h(byte[] bArr) {
        int i = bArr[2] & 255;
        int i2 = bArr[3] & 255;
        byte b = bArr[4];
        int i3 = i == 1 ? (bArr[5] & 255) | ((bArr[6] & 255) << 8) : 0;
        if (b == 1) {
            com.veepoo.protocol.util.vp_w.vp_b("-客户定制APP协议-", "回复数据： 第{" + i + "/" + i2 + "}包 = " + com.veepoo.protocol.util.vp_i.vp_a(bArr));
            vp_b(bArr, i, i2, i3);
        } else if (b == 2) {
            com.veepoo.protocol.util.vp_w.vp_b("-客户定制APP协议-", "设备接受完数据");
            ITCMDataListener iTCMDataListener = this.vp_d;
            if (iTCMDataListener != null) {
                iTCMDataListener.onTCMWriteSuccess();
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ae(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ae(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_d = vp_d();
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM)读取计步：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_d));
        send(bArrVp_d, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_f(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_f(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_c = vp_c();
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM)读取健康数据时间戳：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_c));
        send(bArrVp_c, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ag(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_ag(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_e = vp_e();
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "读取中医数据：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_e));
        super.send(bArrVp_e, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_aa(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_aa(bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_b = vp_b();
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "读取蜂窝IMEI码：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_b));
        super.send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 2) {
                vp_g(bArr);
                return;
            }
            if (b == 3) {
                vp_h(bArr);
                return;
            }
            if (b == 5) {
                vp_e(bArr);
            } else if (b == 4 && bArr[4] == 4) {
                vp_f(bArr);
            }
        }
    }

    private final ECustomStatus vp_b(byte b) {
        if (b == 2) {
            return ECustomStatus.READ_SUCCESS;
        }
        return b == 1 ? ECustomStatus.SETTING_SUCCESS : ECustomStatus.UNKNOW;
    }

    private final byte[] vp_d() {
        return new byte[]{2, 5, 1, 1, vp_x.vp_a.vp_a(), 1, 0, -96};
    }

    private final byte[] vp_c() {
        return new byte[]{2, 5, 1, 1, vp_x.vp_a.vp_a(), 1, 0, -95};
    }

    private final byte[] vp_a(int i, int i2, byte[] bArr) {
        int length = bArr.length;
        int i3 = i * 13;
        int i4 = i3 + 13 <= length ? 13 : length - i3;
        byte[] bArr2 = new byte[i4 + 7];
        bArr2[0] = 2;
        bArr2[1] = 3;
        bArr2[2] = (byte) (i + 1);
        bArr2[3] = (byte) i2;
        bArr2[4] = 2;
        bArr2[5] = (byte) (length & 255);
        bArr2[6] = (byte) ((length >> 8) & 255);
        System.arraycopy(bArr, i3, bArr2, 7, i4);
        return bArr2;
    }

    private final void vp_f(byte[] bArr) {
        vp_a(bArr, bArr[2] & 255, bArr[3] & 255, com.veepoo.protocol.util.vp_d.vp_a.vp_b(bArr, 5));
    }

    private final void vp_e(byte[] bArr) {
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM) handlerData>>> " + com.veepoo.protocol.util.vp_g.vp_d(bArr) + "  " + (this.vp_e == null ? "监听为空" : "监听不为空"));
        byte b = bArr[4];
        vp_x vp_xVar = vp_x.vp_a;
        if (b == vp_xVar.vp_a() || b == vp_xVar.vp_b()) {
            vp_k(bArr);
            return;
        }
        if (b == vp_xVar.vp_c()) {
            if (bArr[7] == 0) {
                com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM) handlerData>>> 设置成功");
                IQH15HealthDataOptListener iQH15HealthDataOptListener = this.vp_e;
                if (iQH15HealthDataOptListener != null) {
                    iQH15HealthDataOptListener.onDataSettingSuccess();
                    return;
                }
                return;
            }
            com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM) handlerData>>> 设置失败");
            IQH15HealthDataOptListener iQH15HealthDataOptListener2 = this.vp_e;
            if (iQH15HealthDataOptListener2 != null) {
                iQH15HealthDataOptListener2.onDataSettingFailed();
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_k(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "mClient");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_k(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    private final byte vp_a(EFunctionStatus eFunctionStatus) {
        int i = eFunctionStatus == null ? -1 : vp_c.vp_a[eFunctionStatus.ordinal()];
        if (i != 1) {
            return i != 2 ? (byte) 0 : (byte) 2;
        }
        return (byte) 1;
    }

    private final byte[] vp_b() {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 4;
        bArr[2] = 1;
        bArr[3] = 1;
        bArr[4] = 4;
        return bArr;
    }

    private final EFunctionStatus vp_a(byte b) {
        if (b == 1) {
            return EFunctionStatus.SUPPORT_OPEN;
        }
        if (b == 2) {
            return EFunctionStatus.SUPPORT_CLOSE;
        }
        return b == 0 ? EFunctionStatus.UNSUPPORT : EFunctionStatus.UNKONW;
    }

    private final void vp_b(byte[] bArr, int i, int i2, int i3) {
        if (i3 <= 0 && i == 1) {
            ITCMDataListener iTCMDataListener = this.vp_d;
            if (iTCMDataListener != null) {
                iTCMDataListener.onTCMDataResponse(new TCMDataReport(0L, null, 3, null));
                return;
            }
            return;
        }
        if (i == 1) {
            this.vp_g = new byte[i3];
        }
        byte[] bArr2 = this.vp_g;
        if (bArr2 == null) {
            return;
        }
        int i4 = (i - 1) * 13;
        int length = bArr.length - 7;
        if (length > 0 && i4 + length <= bArr2.length) {
            System.arraycopy(bArr, 7, bArr2, i4, length);
        }
        if (i == i2) {
            TCMDataReport tCMDataReportVp_j = vp_j(bArr2);
            ITCMDataListener iTCMDataListener2 = this.vp_d;
            if (iTCMDataListener2 != null) {
                iTCMDataListener2.onTCMDataResponse(tCMDataReportVp_j);
            }
            this.vp_g = null;
        }
    }

    private final byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 2;
        bArr[2] = 2;
        return bArr;
    }

    private final byte[] vp_a(EQH15ComplianceType eQH15ComplianceType) {
        return new byte[]{2, 5, 1, 1, vp_x.vp_a.vp_c(), 3, 0, 86, 1, eQH15ComplianceType.getTag()};
    }

    private final byte[] vp_a(int i, int i2, int i3, byte[] bArr) {
        int i4 = i3 - 5;
        int i5 = i * i4;
        byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, i5, Math.min(i5 + i4, bArr.length));
        byte[] bArr2 = new byte[bArrCopyOfRange.length + 5];
        bArr2[0] = 2;
        bArr2[1] = 5;
        bArr2[2] = (byte) (i + 1);
        bArr2[3] = (byte) i2;
        bArr2[4] = vp_x.vp_a.vp_c();
        ArraysKt.copyInto$default(bArrCopyOfRange, bArr2, 5, 0, 0, 12, (Object) null);
        return bArr2;
    }

    private final byte[] vp_a(AutoDetectStateSetting autoDetectStateSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = 2;
        bArr[1] = 2;
        bArr[2] = 1;
        bArr[3] = vp_a(autoDetectStateSetting != null ? autoDetectStateSetting.getSpo2h24Hour() : null);
        return bArr;
    }

    private final List vp_a(Map map) {
        byte[] bArrVp_a = vp_a(System.currentTimeMillis() / 1000, map);
        int length = (bArrVp_a.length + 12) / 13;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length; i++) {
            arrayList.add(vp_a(i, length, bArrVp_a));
        }
        return arrayList;
    }

    private final void vp_a(byte[] bArr, int i, int i2, int i3) {
        if (i3 <= 0 && i == 1) {
            IReadIMEIInfoListener iReadIMEIInfoListener = this.vp_f;
            if (iReadIMEIInfoListener != null) {
                iReadIMEIInfoListener.onIMEIInfoRead("");
                return;
            }
            return;
        }
        if (i == 1) {
            this.vp_h = 0;
            this.vp_g = new byte[i3];
        }
        byte[] bArr2 = this.vp_g;
        if (bArr2 != null) {
            int i4 = i == 1 ? 7 : 5;
            int length = bArr.length;
            for (int i5 = i4; i5 < length; i5++) {
                int i6 = (this.vp_h + i5) - i4;
                if (i6 < bArr2.length) {
                    bArr2[i6] = bArr[i5];
                }
            }
            this.vp_h += length - i4;
            if (i == i2) {
                com.veepoo.protocol.util.vp_w.vp_b("-客户定制APP协议-", "IMEI：长度=" + i3 + " -> 数据上报：" + com.veepoo.protocol.util.vp_i.vp_a(bArr2));
                String str = new String(bArr2, Charsets.US_ASCII);
                IReadIMEIInfoListener iReadIMEIInfoListener2 = this.vp_f;
                if (iReadIMEIInfoListener2 != null) {
                    iReadIMEIInfoListener2.onIMEIInfoRead(str);
                }
            }
        }
    }

    private final byte[] vp_a(long j) {
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, AutoDetectStateSetting autoDetectStateSetting) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "mClient");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(autoDetectStateSetting, "autoDetectStateSetting");
        super.vp_a(bluetoothClient, str, bleWriteResponse, autoDetectStateSetting);
        super.send(vp_a(autoDetectStateSetting), bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(ICustomProtocolStateListener iCustomProtocolStateListener) {
        this.vp_c = iCustomProtocolStateListener;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EQH15ComplianceType eQH15ComplianceType) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(eQH15ComplianceType, "complianceType");
        super.vp_a(bluetoothClient, str, bleWriteResponse, eQH15ComplianceType);
        byte[] bArrVp_a = vp_a(eQH15ComplianceType);
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM)设置达标(" + eQH15ComplianceType + ")提醒事件：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, QH15HealthData qH15HealthData) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(qH15HealthData, "data");
        super.vp_a(bluetoothClient, str, bleWriteResponse, qH15HealthData);
        int currentMtu = VPOperateManager.getInstance().getCurrentMtu();
        com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM)发送健康数据：mtu=" + currentMtu + " 数据=$" + qH15HealthData);
        byte[] bArrVp_a = com.veepoo.protocol.util.vp_aa.vp_a.vp_a(qH15HealthData);
        int i = currentMtu - 5;
        int length = (bArrVp_a.length / i) + (bArrVp_a.length % i == 0 ? 0 : 1);
        int i2 = 0;
        while (i2 < length) {
            byte[] bArrVp_a2 = vp_a(i2, length, currentMtu, bArrVp_a);
            int i3 = i2 + 1;
            i2 = i3;
            com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "QH15(PSAIM)发送健康数据：[" + i3 + "/" + length + "] " + bArrVp_a2.length + " >> 数据=" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
        }
    }

    public final void vp_a(IQH15HealthDataOptListener iQH15HealthDataOptListener) {
        this.vp_e = iQH15HealthDataOptListener;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, Map map) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(map, "tcmData");
        super.vp_a(bluetoothClient, str, bleWriteResponse, map);
        List listVp_a = vp_a(map);
        int i = 0;
        for (Object obj : listVp_a) {
            int i2 = i;
            i++;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            byte[] bArr = (byte[]) obj;
            com.veepoo.protocol.util.vp_w.vp_a("-客户定制APP协议-", "设置中医数据：第" + i + "包=" + com.veepoo.protocol.util.vp_i.vp_a(bArr));
            send(bArr, bluetoothClient, str, bleWriteResponse);
            SystemClock.sleep(30L);
        }
    }

    public final void vp_a(IReadIMEIInfoListener iReadIMEIInfoListener) {
        this.vp_f = iReadIMEIInfoListener;
    }

    public final void vp_a(ITCMDataListener iTCMDataListener) {
        this.vp_d = iTCMDataListener;
    }
}
