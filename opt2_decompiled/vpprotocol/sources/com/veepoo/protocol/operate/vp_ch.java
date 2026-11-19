package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IGNSSOptListener;
import com.veepoo.protocol.listener.data.IQH15GnssLocationListener;
import com.veepoo.protocol.listener.data.IQH15SafetyProtectionListener;
import com.veepoo.protocol.listener.data.ISafetyProtectionOptListener;
import com.veepoo.protocol.model.datas.GnssLocationData;
import com.veepoo.protocol.model.datas.QH15SecurityProtectionModel;
import com.veepoo.protocol.model.enums.EGNSSState;
import com.veepoo.protocol.model.enums.EGnssOptType;
import com.veepoo.protocol.model.enums.EQH15SPType;
import com.veepoo.protocol.model.enums.EQH15SetProtectionFuncAckState;
import com.veepoo.protocol.model.enums.ESafetyProtectionState;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ch.class */
public final class vp_ch extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_i = new vp_a(null);
    private final SimpleDateFormat vp_c;
    private ISafetyProtectionOptListener vp_d;
    private IGNSSOptListener vp_e;
    private IQH15SafetyProtectionListener vp_f;
    private IQH15GnssLocationListener vp_g;
    private boolean vp_h;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ch$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_ch vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ch$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_ch vp_b = new vp_ch(null);

        private vp_b() {
        }

        public final vp_ch vp_a() {
            return vp_b;
        }
    }

    private vp_ch() {
        this.vp_c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    private final byte[] vp_b(int i) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(18);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put((byte) -113);
        byteBufferAllocate.put((byte) 8);
        byteBufferAllocate.put((byte) i);
        byteBufferAllocate.put((byte) 1);
        byte[] bArrArray = byteBufferAllocate.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        return bArrArray;
    }

    private final String vp_a(int i) {
        switch (i) {
            case 1:
                return "GNSS运动";
            case 2:
                return "亲情安全守护";
            case 3:
                return "SOS紧急求助";
            case 4:
                return "预留";
            default:
                return "未知";
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_bh(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_bh(bluetoothClient, str, bleWriteResponse);
        this.vp_h = true;
        byte[] bArrVp_a = vp_a(true);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "开启安全守护：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_cb(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_cb(bluetoothClient, str, bleWriteResponse);
        this.vp_h = false;
        byte[] bArrVp_a = vp_a(false);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "结束安全守护：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(gnssLocationData, "data");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_c(gnssLocationData, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(2, gnssLocationData);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "【" + vp_a(2) + "：推送位置信息】：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x0072 A[PHI: r7 r9
  0x0072: PHI (r7v7 com.veepoo.protocol.listener.data.IQH15GnssLocationListener) = 
  (r7v4 com.veepoo.protocol.listener.data.IQH15GnssLocationListener)
  (r7v8 com.veepoo.protocol.listener.data.IQH15GnssLocationListener)
 binds: [B:39:0x00d3, B:19:0x006f] A[DONT_GENERATE, DONT_INLINE]
  0x0072: PHI (r9v11 com.veepoo.protocol.model.enums.EQH15SPType) = (r9v8 com.veepoo.protocol.model.enums.EQH15SPType), (r9v12 com.veepoo.protocol.model.enums.EQH15SPType) binds: [B:39:0x00d3, B:19:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x007d A[PHI: r7 r9
  0x007d: PHI (r7v5 com.veepoo.protocol.listener.data.IQH15GnssLocationListener) = 
  (r7v4 com.veepoo.protocol.listener.data.IQH15GnssLocationListener)
  (r7v8 com.veepoo.protocol.listener.data.IQH15GnssLocationListener)
 binds: [B:39:0x00d3, B:19:0x006f] A[DONT_GENERATE, DONT_INLINE]
  0x007d: PHI (r9v9 com.veepoo.protocol.model.enums.EQH15SPType) = (r9v8 com.veepoo.protocol.model.enums.EQH15SPType), (r9v12 com.veepoo.protocol.model.enums.EQH15SPType) binds: [B:39:0x00d3, B:19:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IQH15GnssLocationListener iQH15GnssLocationListener;
        EQH15SPType eQH15SPType;
        QH15SecurityProtectionModel qH15SecurityProtectionModelFromGnssLocationData;
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "GNSS运动&安全守护&SOS上报指令->：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 8) {
                byte b2 = bArr[2];
                byte b3 = bArr[3];
                GnssLocationData gnssLocationDataVp_b = vp_b(bArr);
                EGnssOptType typeWithCMD = EGnssOptType.INSTANCE.getTypeWithCMD(b2);
                if (b3 == 1) {
                    IGNSSOptListener iGNSSOptListener = this.vp_e;
                    if (iGNSSOptListener != null) {
                        iGNSSOptListener.onDeviceGNSSOptRequest(typeWithCMD, gnssLocationDataVp_b != null ? gnssLocationDataVp_b.getLongitude() : 0.0d, gnssLocationDataVp_b != null ? gnssLocationDataVp_b.getLatitude() : 0.0d);
                    }
                    if (typeWithCMD != EGnssOptType.SAFETY_PROTECTION) {
                        return;
                    }
                    IQH15GnssLocationListener iQH15GnssLocationListener2 = this.vp_g;
                    iQH15GnssLocationListener = iQH15GnssLocationListener2;
                    if (iQH15GnssLocationListener2 == null) {
                        return;
                    }
                    eQH15SPType = EQH15SPType.REQ;
                    if (gnssLocationDataVp_b != null) {
                        qH15SecurityProtectionModelFromGnssLocationData = QH15SecurityProtectionModel.INSTANCE.fromGnssLocationData(gnssLocationDataVp_b);
                    } else {
                        qH15SecurityProtectionModelFromGnssLocationData = null;
                    }
                } else {
                    if (b3 != 2) {
                        return;
                    }
                    IGNSSOptListener iGNSSOptListener2 = this.vp_e;
                    if (iGNSSOptListener2 != null) {
                        Intrinsics.checkNotNull(gnssLocationDataVp_b);
                        iGNSSOptListener2.onDeviceGNSSOptReport(typeWithCMD, gnssLocationDataVp_b);
                    }
                    if (typeWithCMD != EGnssOptType.SAFETY_PROTECTION) {
                        return;
                    }
                    IQH15GnssLocationListener iQH15GnssLocationListener3 = this.vp_g;
                    iQH15GnssLocationListener = iQH15GnssLocationListener3;
                    if (iQH15GnssLocationListener3 == null) {
                        return;
                    }
                    eQH15SPType = EQH15SPType.PUSH;
                    if (gnssLocationDataVp_b != null) {
                        qH15SecurityProtectionModelFromGnssLocationData = QH15SecurityProtectionModel.INSTANCE.fromGnssLocationData(gnssLocationDataVp_b);
                    } else {
                        qH15SecurityProtectionModelFromGnssLocationData = null;
                    }
                }
                iQH15GnssLocationListener.onQH15GnssLocation(eQH15SPType, qH15SecurityProtectionModelFromGnssLocationData);
                return;
            }
            if (b != 9) {
                com.veepoo.protocol.util.vp_w.vp_b("-亲情安全守护-", "GNSS运动&安全守护&SOS上报指令【异常】！！！");
                return;
            }
            byte b4 = bArr[2];
            if (b4 == 0) {
                boolean z = bArr[3] == 1;
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 4, 4);
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                long j = ((long) byteBufferWrap.getInt()) & 4294967295L;
                new Date(j * ((long) 1000));
                ISafetyProtectionOptListener iSafetyProtectionOptListener = this.vp_d;
                if (iSafetyProtectionOptListener != null) {
                    iSafetyProtectionOptListener.onWatchSafetyProtectionOpt(z, j);
                }
                IQH15SafetyProtectionListener iQH15SafetyProtectionListener = this.vp_f;
                if (iQH15SafetyProtectionListener != null) {
                    iQH15SafetyProtectionListener.onQH15SafetyProtectionStateChanged(z, j);
                    return;
                }
                return;
            }
            if (b4 != 1) {
                com.veepoo.protocol.util.vp_w.vp_b("-亲情安全守护-", "安全守护上报指令【异常】！！！");
                return;
            }
            byte b5 = bArr[3];
            if (b5 == 1) {
                ISafetyProtectionOptListener iSafetyProtectionOptListener2 = this.vp_d;
                if (iSafetyProtectionOptListener2 != null) {
                    iSafetyProtectionOptListener2.onPhoneSafetyProtectionOpt(this.vp_h);
                }
                IQH15SafetyProtectionListener iQH15SafetyProtectionListener2 = this.vp_f;
                if (iQH15SafetyProtectionListener2 != null) {
                    iQH15SafetyProtectionListener2.onQH15SafetyProtectionSetResult(EQH15SetProtectionFuncAckState.SUCCESS);
                    return;
                }
                return;
            }
            ISafetyProtectionOptListener iSafetyProtectionOptListener3 = this.vp_d;
            if (iSafetyProtectionOptListener3 != null) {
                iSafetyProtectionOptListener3.onPhoneSafetyProtectionStartError(ESafetyProtectionState.INSTANCE.getStateWithCMD(b5));
            }
            IQH15SafetyProtectionListener iQH15SafetyProtectionListener3 = this.vp_f;
            if (iQH15SafetyProtectionListener3 != null) {
                iQH15SafetyProtectionListener3.onQH15SafetyProtectionSetResult(EQH15SetProtectionFuncAckState.INSTANCE.getStateWithCMD(b5));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.veepoo.protocol.model.datas.GnssLocationData, java.nio.ByteBuffer] */
    private final byte[] vp_a(int i, GnssLocationData gnssLocationData) {
        ?? Allocate = ByteBuffer.allocate(18);
        Allocate.order(ByteOrder.LITTLE_ENDIAN);
        Allocate.put((byte) -113);
        Allocate.put((byte) 8);
        Allocate.put((byte) i);
        Allocate.put((byte) 2);
        Allocate.put(gnssLocationData.getState().getCode());
        Allocate.put((byte) gnssLocationData.getRssi());
        double d = 100000;
        int longitude = (int) (gnssLocationData.getLongitude() * d);
        int latitude = (int) (gnssLocationData.getLatitude() * d);
        int altitude = Allocate.getAltitude();
        Allocate.putInt(longitude);
        Allocate.putInt(latitude);
        Allocate.putInt(altitude);
        byte[] bArrArray = Allocate.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        return bArrArray;
    }

    private final GnssLocationData vp_b(byte[] bArr) {
        if (bArr.length < 18 || bArr[0] != -113 || bArr[1] != 8) {
            return null;
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        byte b = bArr[2];
        byte b2 = bArr[3];
        return new GnssLocationData(EGNSSState.INSTANCE.getStateWithCMD(bArr[4]), bArr[5], ((double) byteBufferWrap.getInt(6)) / 100000.0d, ((double) byteBufferWrap.getInt(10)) / 100000.0d, byteBufferWrap.getInt(14));
    }

    public /* synthetic */ vp_ch(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final byte[] vp_a(boolean z) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put((byte) -113);
        byteBufferAllocate.put((byte) 9);
        byteBufferAllocate.put((byte) 0);
        byteBufferAllocate.put(z ? (byte) 1 : (byte) 2);
        byteBufferAllocate.putInt((int) (System.currentTimeMillis() / ((long) 1000)));
        byte[] bArrArray = byteBufferAllocate.array();
        Intrinsics.checkNotNullExpressionValue(bArrArray, "array(...)");
        return bArrArray;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(gnssLocationData, "data");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_b(gnssLocationData, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(3, gnssLocationData);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "【" + vp_a(3) + "：推送位置信息】：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(GnssLocationData gnssLocationData, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(gnssLocationData, "data");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_a(gnssLocationData, bluetoothClient, str, bleWriteResponse);
        byte[] bArrVp_a = vp_a(1, gnssLocationData);
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "【" + vp_a(1) + "：推送位置信息】：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(QH15SecurityProtectionModel qH15SecurityProtectionModel, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(qH15SecurityProtectionModel, "model");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        vp_c(qH15SecurityProtectionModel.toGnssLocationData(), bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(EGnssOptType eGnssOptType, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eGnssOptType, "optType");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        byte[] bArrVp_b = vp_b(eGnssOptType.getCode());
        com.veepoo.protocol.util.vp_w.vp_a("-亲情安全守护-", "【" + eGnssOptType.getDes() + "：请求位置信息】：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_b));
        send(bArrVp_b, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IGNSSOptListener iGNSSOptListener) {
        this.vp_e = iGNSSOptListener;
    }

    public final void vp_a(IQH15GnssLocationListener iQH15GnssLocationListener) {
        this.vp_g = iQH15GnssLocationListener;
    }

    public final void vp_a(IQH15SafetyProtectionListener iQH15SafetyProtectionListener) {
        this.vp_f = iQH15SafetyProtectionListener;
    }

    public final void vp_a(ISafetyProtectionOptListener iSafetyProtectionOptListener) {
        this.vp_d = iSafetyProtectionOptListener;
    }
}
