package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.data.IDeviceSportReportListener;
import com.veepoo.protocol.listener.data.ISportControlOptListener;
import com.veepoo.protocol.model.datas.DeviceState;
import com.veepoo.protocol.model.datas.GpsSignal;
import com.veepoo.protocol.model.datas.RunState;
import com.veepoo.protocol.model.datas.SportControlDataInfo;
import com.veepoo.protocol.model.datas.SportOpCode;
import com.veepoo.protocol.model.enums.ESportControlType;
import com.veepoo.protocol.model.enums.ESportType;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cr.class */
public final class vp_cr extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_h = new vp_a(null);
    private ISportControlOptListener vp_c;
    private IDeviceSportReportListener vp_d;
    private List vp_e = new ArrayList();
    private int vp_f;
    private int vp_g;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cr$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_cr vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cr$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_cr vp_b = new vp_cr();

        private vp_b() {
        }

        public final vp_cr vp_a() {
            return vp_b;
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cr$vp_c.class */
    public static final class vp_c implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComparisonsKt.compareValues(Byte.valueOf(((byte[]) obj)[3]), Byte.valueOf(((byte[]) obj2)[3]));
        }
    }

    private final byte[] vp_a(List list) {
        ArrayList arrayList = new ArrayList();
        for (byte[] bArr : CollectionsKt.sortedWith(list, new vp_c())) {
            com.veepoo.protocol.util.vp_g.vp_d(bArr);
            Iterator it = ArraysKt.slice(bArr, RangesKt.until(4, bArr.length)).iterator();
            while (it.hasNext()) {
                arrayList.add(Byte.valueOf(((Number) it.next()).byteValue()));
            }
        }
        return CollectionsKt.toByteArray(arrayList);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_h(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_h(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -38;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        super.handler(bArr);
        byte b = bArr[1];
        int i = bArr[2] & 255;
        if (b == 1) {
            if (i == 1) {
                ISportControlOptListener iSportControlOptListener = this.vp_c;
                if (iSportControlOptListener != null) {
                    iSportControlOptListener.onSportControlOptSuccess();
                    return;
                }
                return;
            }
            ISportControlOptListener iSportControlOptListener2 = this.vp_c;
            if (iSportControlOptListener2 != null) {
                iSportControlOptListener2.onSportControlOptFail();
                return;
            }
            return;
        }
        int i2 = bArr[3] & 255;
        if (i2 == 1) {
            this.vp_f = bArr[6] & 255;
            this.vp_e.clear();
        }
        this.vp_e.add(bArr);
        if (i2 == this.vp_f) {
            byte[] bArrVp_a = vp_a(this.vp_e);
            String str = "读取结束，拼接有效数据：" + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a);
            SportControlDataInfo sportControlDataInfoVp_a = vp_a(b, bArrVp_a);
            if (b != 3) {
                ISportControlOptListener iSportControlOptListener3 = this.vp_c;
                if (iSportControlOptListener3 != null) {
                    iSportControlOptListener3.onSportControlDataChange(sportControlDataInfoVp_a);
                    return;
                }
                return;
            }
            ISportControlOptListener iSportControlOptListener4 = this.vp_c;
            if (iSportControlOptListener4 != null) {
                iSportControlOptListener4.onSportControlDataChange(sportControlDataInfoVp_a);
            }
            IDeviceSportReportListener iDeviceSportReportListener = this.vp_d;
            if (iDeviceSportReportListener != null) {
                iDeviceSportReportListener.onSportControlDataChange(sportControlDataInfoVp_a);
            }
        }
    }

    private final SportControlDataInfo vp_a(byte b, byte[] bArr) {
        SportControlDataInfo sportControlDataInfo = new SportControlDataInfo();
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            byte b2 = byteBufferOrder.get();
            if (b2 == -96) {
                byteBufferOrder.get();
                byteBufferOrder.get();
            } else if (b2 == -95) {
                byteBufferOrder.get();
                sportControlDataInfo.setSportType(ESportType.getSportType(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get())));
            } else if (b2 == -94) {
                byteBufferOrder.get();
                sportControlDataInfo.setCon(b & 255);
                SportOpCode sportOpCodeFromValue = SportOpCode.INSTANCE.fromValue(byteBufferOrder.get() & 255);
                SportOpCode sportOpCode = sportOpCodeFromValue;
                if (sportOpCodeFromValue == null) {
                    sportOpCode = SportOpCode.START;
                }
                sportControlDataInfo.setOpCode(sportOpCode);
            } else if (b2 == -93) {
                byteBufferOrder.get();
                RunState runStateFromValue = RunState.INSTANCE.fromValue(byteBufferOrder.get() & 255);
                RunState runState = runStateFromValue;
                if (runStateFromValue == null) {
                    runState = RunState.NOT_STARTED;
                }
                sportControlDataInfo.setRunState(runState);
            } else if (b2 == -92) {
                byteBufferOrder.get();
                DeviceState deviceStateFromValue = DeviceState.INSTANCE.fromValue(byteBufferOrder.get() & 255);
                DeviceState deviceState = deviceStateFromValue;
                if (deviceStateFromValue == null) {
                    deviceState = DeviceState.NORMAL;
                }
                sportControlDataInfo.setDeviceState(deviceState);
            } else if (b2 == -91) {
                byteBufferOrder.get();
                sportControlDataInfo.setSportDuration(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
            } else if (b2 == -90) {
                byteBufferOrder.get();
                sportControlDataInfo.setDistance(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
            } else if (b2 == -89) {
                byteBufferOrder.get();
                sportControlDataInfo.setHeartRate(byteBufferOrder.get() & 255);
            } else if (b2 == -88) {
                byteBufferOrder.get();
                sportControlDataInfo.setCalories(VpBleByteUtil.littleEndianByteArray2Int(new byte[]{byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get(), byteBufferOrder.get()}));
            } else if (b2 == -87) {
                byteBufferOrder.get();
                sportControlDataInfo.setPace(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
            } else if (b2 == -86) {
                byteBufferOrder.get();
                sportControlDataInfo.setSpeed(VpBleByteUtil.twoByteToUnsignedInt(byteBufferOrder.get(), byteBufferOrder.get()));
            } else if (b2 == -85) {
                byteBufferOrder.get();
                int i = byteBufferOrder.get() & 255;
                int i2 = byteBufferOrder.get() & 255;
                sportControlDataInfo.setGnssSport(i == 1);
                sportControlDataInfo.setGpsSignal(GpsSignal.INSTANCE.fromValue(i2));
            } else {
                String str = "====> type = " + com.veepoo.protocol.util.vp_g.vp_d(new byte[]{b2});
            }
        }
        return sportControlDataInfo;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportType eSportType) {
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(eSportType, "sportType");
        super.vp_a(bluetoothClient, str, bleWriteResponse, eSportType);
        byte[] bArr = new byte[20];
        bArr[0] = -38;
        bArr[1] = 2;
        this.vp_g = 1;
        int value = eSportType.getValue();
        bArr[2] = (byte) (value & 255);
        bArr[3] = (byte) ((value >> 8) & 255);
        bArr[4] = 2;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(ISportControlOptListener iSportControlOptListener) {
        this.vp_c = iSportControlOptListener;
    }

    public final void vp_a(IDeviceSportReportListener iDeviceSportReportListener) {
        this.vp_d = iDeviceSportReportListener;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportControlType eSportControlType, ESportType eSportType) {
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(eSportControlType, "controlType");
        Intrinsics.checkNotNullParameter(eSportType, "sportType");
        super.vp_a(bluetoothClient, str, bleWriteResponse, eSportControlType, eSportType);
        byte[] bArr = new byte[20];
        bArr[0] = -38;
        bArr[1] = 1;
        this.vp_g = 1;
        int value = eSportType.getValue();
        bArr[2] = (byte) (value & 255);
        bArr[3] = (byte) ((value >> 8) & 255);
        bArr[4] = (byte) eSportControlType.getValue();
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }
}
