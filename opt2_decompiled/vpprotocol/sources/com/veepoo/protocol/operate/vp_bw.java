package com.veepoo.protocol.operate;

import android.content.Context;
import android.util.Log;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.data.IActiveMeasurementDataListener;
import com.veepoo.protocol.listener.data.IPPGRawDataReadListener;
import com.veepoo.protocol.listener.data.IPPGRealTimeTransferOptListener;
import com.veepoo.protocol.listener.data.IPPGSwitchOperaterListener;
import com.veepoo.protocol.model.datas.PPGRawData;
import com.veepoo.protocol.model.datas.PPGReadData;
import com.veepoo.protocol.model.datas.PPGSecondData;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.enums.EJH58MeasurementState;
import com.veepoo.protocol.model.enums.PPGSwitchStatus;
import com.veepoo.protocol.model.enums.PPGTestMode;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bw.class */
public final class vp_bw extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_m = new vp_a(null);
    private BluetoothClient vp_c;
    private String vp_d;
    private BleWriteResponse vp_e;
    private final List vp_f;
    private final PPGReadData vp_g;
    private PPGRawData vp_h;
    private IPPGSwitchOperaterListener vp_i;
    private IPPGRawDataReadListener vp_j;
    private IPPGRealTimeTransferOptListener vp_k;
    private IActiveMeasurementDataListener vp_l;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bw$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final vp_bw vp_a() {
            return vp_b.vp_a.vp_a();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_bw$vp_b.class */
    private static final class vp_b {
        public static final vp_b vp_a = new vp_b();
        private static final vp_bw vp_b = new vp_bw(null);

        private vp_b() {
        }

        public final vp_bw vp_a() {
            return vp_b;
        }
    }

    private vp_bw() {
        this.vp_f = new ArrayList();
        this.vp_g = new PPGReadData();
    }

    private final void vp_d() {
        byte[] bArr = {-21, 2, 4, 1};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "单组PPG数据接受结束，发送【继续】发送指令 --> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        BluetoothClient bluetoothClient = this.vp_c;
        BluetoothClient bluetoothClient2 = bluetoothClient;
        if (bluetoothClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            bluetoothClient2 = null;
        }
        String str = this.vp_d;
        String str2 = str;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mac");
            str2 = null;
        }
        BleWriteResponse bleWriteResponse = this.vp_e;
        BleWriteResponse bleWriteResponse2 = bleWriteResponse;
        if (bleWriteResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleWriteResponse");
            bleWriteResponse2 = null;
        }
        send(bArr, bluetoothClient2, str2, bleWriteResponse2);
    }

    private final void vp_c() {
        List<byte[]> list = this.vp_f;
        ArrayList arrayList = new ArrayList();
        for (byte[] bArr : list) {
            com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", ">>>>>>>>>>>>> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            CollectionsKt.addAll(arrayList, CollectionsKt.toList(ArraysKt.drop(bArr, 7)));
        }
        byte[] byteArray = CollectionsKt.toByteArray(arrayList);
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(byteArray).order(ByteOrder.LITTLE_ENDIAN);
        int length = byteArray.length;
        int i = 0;
        byte[] bArr2 = null;
        com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", ">>>>> 数据总长度=" + byteArray.length + ", 内容 = " + com.veepoo.protocol.util.vp_g.vp_d(byteArray));
        Context context = VPOperateManager.mContext;
        Intrinsics.checkNotNullExpressionValue(context, "mContext");
        PPGRawData pPGRawData = this.vp_h;
        Intrinsics.checkNotNull(pPGRawData);
        String str = "PPGRawData_" + pPGRawData.getIndex() + ".txt";
        String strVp_d = com.veepoo.protocol.util.vp_g.vp_d(byteArray);
        Intrinsics.checkNotNullExpressionValue(strVp_d, "byte2HexForShow(...)");
        vp_a(context, str, strVp_d);
        while (byteBufferOrder.hasRemaining()) {
            int i2 = byteBufferOrder.get() & 255;
            switch (i2) {
                case 160:
                    bArr2 = null;
                    byte b = byteBufferOrder.get();
                    byte b2 = byteBufferOrder.get();
                    byte[] bArr3 = {b, b2};
                    int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(b2, b);
                    i = iVp_b;
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "-parseSinglePPGRawData- oneSecondDataLength = " + iVp_b + " bytes = " + com.veepoo.protocol.util.vp_g.vp_d(bArr3));
                    break;
                case 161:
                    int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(byteBufferOrder.get(), byteBufferOrder.get());
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "-parseSinglePPGRawData- greenDataLength = " + iVp_b2);
                    byte[] bArr4 = new byte[iVp_b2];
                    bArr2 = bArr4;
                    byteBufferOrder.get(bArr4);
                    break;
                case 162:
                    byte[] bArr5 = bArr2;
                    int i3 = byteBufferOrder.getShort() & 65535;
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "-parseSinglePPGRawData- accDataLength = " + i3);
                    byte[] bArr6 = new byte[i3];
                    byteBufferOrder.get(bArr6);
                    if (bArr5 != null) {
                        PPGSecondData pPGSecondData = new PPGSecondData(i, bArr2, bArr6);
                        PPGRawData pPGRawData2 = this.vp_h;
                        Intrinsics.checkNotNull(pPGRawData2);
                        pPGRawData2.getPpgSecondDataList().add(pPGSecondData);
                    }
                    break;
                default:
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str2 = String.format("0x%02X", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                    System.out.println((Object) ("Error: Unknown data type " + str2 + " encountered at offset " + (byteBufferOrder.position() - 1) + ", stopping parse."));
                    return;
            }
        }
    }

    private final void vp_b(byte[] bArr) {
        byte b = bArr[2];
        if (b == 1) {
            EJH58MeasurementState eJH58MeasurementStateFromValue = EJH58MeasurementState.INSTANCE.fromValue(bArr[3]);
            byte b2 = bArr[4];
            com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "JH58主动测量状态控制#应答：state=" + eJH58MeasurementStateFromValue.getDes() + ", ack=" + b2 + " (0x01=成功, 0x02=设备正忙, 0x03=低电)");
            IActiveMeasurementDataListener iActiveMeasurementDataListener = this.vp_l;
            if (iActiveMeasurementDataListener != null) {
                iActiveMeasurementDataListener.onMeasurementStateResult(eJH58MeasurementStateFromValue, b2);
                return;
            }
            return;
        }
        if (b != 2) {
            com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", "模式三业务#【异常Con】-->" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            return;
        }
        byte b3 = bArr[3];
        if (b3 == 0) {
            this.vp_f.clear();
            int i = bArr[4] & 255;
            this.vp_g.setDataCount(i);
            this.vp_g.getPpgRawDataList().clear();
            IActiveMeasurementDataListener iActiveMeasurementDataListener2 = this.vp_l;
            if (iActiveMeasurementDataListener2 != null) {
                iActiveMeasurementDataListener2.onActiveMeasurementDataStart(i);
            }
            com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "实时数据#【总数据】首包 一共需要接收" + i + "秒数据。");
            return;
        }
        if (b3 != 3) {
            com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", "实时数据#【异常State】-->" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
            return;
        }
        int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[5], bArr[4]);
        int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArr[7], bArr[6]);
        if (iVp_b == 0) {
            this.vp_f.clear();
            int i2 = bArr[8] & 256;
            this.vp_h = new PPGRawData(i2, PPGTestMode.MODE3);
            int iVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr, 9);
            PPGRawData pPGRawData = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData);
            pPGRawData.setTimestamp(((long) iVp_a) - ((long) vp_a()));
            int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArr[14], bArr[13]);
            PPGRawData pPGRawData2 = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData2);
            pPGRawData2.setCrc(iVp_b3);
            int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArr[16], bArr[15]);
            PPGRawData pPGRawData3 = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData3);
            pPGRawData3.setCount(iVp_b4);
            com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "实时数据#【单秒】首包 第" + i2 + "秒数据。timestamp=" + iVp_a + " 总长=" + iVp_b4);
            return;
        }
        this.vp_f.add(vp_c(bArr));
        if (iVp_b == iVp_b2) {
            vp_c();
            List<PPGRawData> ppgRawDataList = this.vp_g.getPpgRawDataList();
            PPGRawData pPGRawData4 = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData4);
            ppgRawDataList.add(pPGRawData4);
            IActiveMeasurementDataListener iActiveMeasurementDataListener3 = this.vp_l;
            if (iActiveMeasurementDataListener3 != null) {
                PPGRawData pPGRawData5 = this.vp_h;
                Intrinsics.checkNotNull(pPGRawData5);
                iActiveMeasurementDataListener3.onActiveMeasurementDataReceived(pPGRawData5);
            }
            PPGRawData pPGRawData6 = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData6);
            com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "实时数据#【单秒】接收完成 第" + pPGRawData6.getIndex() + "/" + this.vp_g.getDataCount() + "秒数据");
            int dataCount = this.vp_g.getDataCount();
            PPGRawData pPGRawData7 = this.vp_h;
            Intrinsics.checkNotNull(pPGRawData7);
            if (dataCount == pPGRawData7.getIndex()) {
                com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "实时数据#【总数据】接收完成");
                this.vp_l = null;
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ad(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_ad(bluetoothClient, str, bleWriteResponse);
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        byte[] bArr = {-21, 1, 1, 0};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取PPG测量状态：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public void vp_a(EJH58MeasurementState eJH58MeasurementState, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IActiveMeasurementDataListener iActiveMeasurementDataListener) {
        Intrinsics.checkNotNullParameter(eJH58MeasurementState, "state");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        this.vp_l = iActiveMeasurementDataListener;
        byte[] bArr = {-21, 4, 1, eJH58MeasurementState.getValue()};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "JH58主动测量状态控制：state=" + eJH58MeasurementState.getDes() + " --> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Type inference failed for: r1v71, types: [com.veepoo.protocol.model.datas.TimeData, long] */
    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        IPPGSwitchOperaterListener iPPGSwitchOperaterListener;
        super.handler(bArr);
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "收到PPG测量状态->：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        if (bArr != null) {
            byte b = bArr[1];
            if (b == 1) {
                byte b2 = bArr[2];
                PPGSwitchStatus pPGSwitchStatusValue2PPGSwitchStatus = PPGSwitchStatus.INSTANCE.value2PPGSwitchStatus(bArr[3]);
                if (b2 == 1) {
                    IPPGSwitchOperaterListener iPPGSwitchOperaterListener2 = this.vp_i;
                    if (iPPGSwitchOperaterListener2 != null) {
                        iPPGSwitchOperaterListener2.onPPGSwitchStatusRead(pPGSwitchStatusValue2PPGSwitchStatus);
                        return;
                    }
                    return;
                }
                if (b2 == 2) {
                    IPPGSwitchOperaterListener iPPGSwitchOperaterListener3 = this.vp_i;
                    if (iPPGSwitchOperaterListener3 != null) {
                        iPPGSwitchOperaterListener3.onPPGSwitchStatusSetting(pPGSwitchStatusValue2PPGSwitchStatus);
                        return;
                    }
                    return;
                }
                if (b2 != 3 || (iPPGSwitchOperaterListener = this.vp_i) == null) {
                    return;
                }
                iPPGSwitchOperaterListener.onPPGSwitchStatusReport(pPGSwitchStatusValue2PPGSwitchStatus);
                return;
            }
            if (b != 2) {
                if (b != 3) {
                    if (b == 4) {
                        vp_b(bArr);
                        return;
                    }
                    com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", "PPG原始数据#【异常数据】-->" + com.veepoo.protocol.util.vp_g.vp_d(bArr) + "。");
                }
                byte b3 = bArr[2];
                if (b3 == 1) {
                    boolean z = bArr[3] == 2;
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "收到授权请求PPG高频实时传输->" + (z ? "开启" : "关闭"));
                    IPPGRealTimeTransferOptListener iPPGRealTimeTransferOptListener = this.vp_k;
                    if (iPPGRealTimeTransferOptListener != null) {
                        iPPGRealTimeTransferOptListener.onDeviceRequestPPGRealTimeTransfer(z);
                        return;
                    }
                    return;
                }
                if (b3 != 2) {
                    return;
                }
                boolean z2 = bArr[3] == 1;
                IPPGRealTimeTransferOptListener iPPGRealTimeTransferOptListener2 = this.vp_k;
                if (iPPGRealTimeTransferOptListener2 != null) {
                    iPPGRealTimeTransferOptListener2.onAppRequestPPGRealTimeTransfer(z2);
                    return;
                }
                return;
            }
            byte b4 = bArr[2];
            switch (b4) {
                case 0:
                    this.vp_f.clear();
                    byte b5 = bArr[3];
                    this.vp_g.setDataCount(b5);
                    this.vp_g.getPpgRawDataList().clear();
                    IPPGRawDataReadListener iPPGRawDataReadListener = this.vp_j;
                    if (iPPGRawDataReadListener != null) {
                        iPPGRawDataReadListener.onPPGReadStart(b5);
                    }
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【总数据】首包 一共需要读取" + b5 + "组数据。");
                    break;
                case 1:
                case 2:
                case 3:
                    PPGTestMode testMode = PPGTestMode.INSTANCE.toTestMode(b4);
                    int iVp_b = com.veepoo.protocol.util.vp_g.vp_b(bArr[4], bArr[3]);
                    int iVp_b2 = com.veepoo.protocol.util.vp_g.vp_b(bArr[6], bArr[5]);
                    if (iVp_b != 0) {
                        this.vp_f.add(bArr);
                        if (iVp_b == iVp_b2) {
                            vp_c();
                            List<PPGRawData> ppgRawDataList = this.vp_g.getPpgRawDataList();
                            PPGRawData pPGRawData = this.vp_h;
                            Intrinsics.checkNotNull(pPGRawData);
                            ppgRawDataList.add(pPGRawData);
                            IPPGRawDataReadListener iPPGRawDataReadListener2 = this.vp_j;
                            if (iPPGRawDataReadListener2 != null) {
                                PPGRawData pPGRawData2 = this.vp_h;
                                Intrinsics.checkNotNull(pPGRawData2);
                                int index = pPGRawData2.getIndex();
                                int dataCount = this.vp_g.getDataCount();
                                PPGRawData pPGRawData3 = this.vp_h;
                                Intrinsics.checkNotNull(pPGRawData3);
                                iPPGRawDataReadListener2.onPPGRawDataRead(index, dataCount, pPGRawData3);
                            }
                            PPGRawData pPGRawData4 = this.vp_h;
                            Intrinsics.checkNotNull(pPGRawData4);
                            int index2 = pPGRawData4.getIndex();
                            int dataCount2 = this.vp_g.getDataCount();
                            PPGRawData pPGRawData5 = this.vp_h;
                            Intrinsics.checkNotNull(pPGRawData5);
                            com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【单组】读取完成 第" + index2 + "/" + dataCount2 + "组数据:" + pPGRawData5);
                            vp_d();
                            int dataCount3 = this.vp_g.getDataCount();
                            PPGRawData pPGRawData6 = this.vp_h;
                            Intrinsics.checkNotNull(pPGRawData6);
                            if (dataCount3 != pPGRawData6.getIndex()) {
                                com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【单组】读取完成,开始读取下一组数据...");
                            } else {
                                IPPGRawDataReadListener iPPGRawDataReadListener3 = this.vp_j;
                                if (iPPGRawDataReadListener3 != null) {
                                    iPPGRawDataReadListener3.onPPGRawDataReadComplete(this.vp_g);
                                }
                                com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【总数据】读取完成 ");
                            }
                        }
                    } else {
                        this.vp_f.clear();
                        int i = bArr[7] + 1;
                        this.vp_h = new PPGRawData(i, testMode);
                        long jVp_a = com.veepoo.protocol.util.vp_g.vp_a(bArr, 8);
                        ?? timeData = new TimeData(timeData);
                        PPGRawData pPGRawData7 = this.vp_h;
                        Intrinsics.checkNotNull(pPGRawData7);
                        pPGRawData7.setTimestamp(jVp_a - ((long) vp_a()));
                        int iVp_b3 = com.veepoo.protocol.util.vp_g.vp_b(bArr[13], bArr[12]);
                        PPGRawData pPGRawData8 = this.vp_h;
                        Intrinsics.checkNotNull(pPGRawData8);
                        pPGRawData8.setCrc(iVp_b3);
                        int iVp_b4 = com.veepoo.protocol.util.vp_g.vp_b(bArr[15], bArr[14]);
                        PPGRawData pPGRawData9 = this.vp_h;
                        Intrinsics.checkNotNull(pPGRawData9);
                        pPGRawData9.setCount(iVp_b4);
                        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【单组】首包 第" + i + "组数据。" + timeData.toFullDateTimeString() + "  总长=" + iVp_b4);
                    }
                    break;
                case 4:
                default:
                    com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", "读取原始数据#【异常数据】-->" + com.veepoo.protocol.util.vp_g.vp_d(bArr) + "。");
                    break;
                case 5:
                    com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取原始数据#【总数据】收到读取终止包-->读取终止。");
                    IPPGRawDataReadListener iPPGRawDataReadListener4 = this.vp_j;
                    if (iPPGRawDataReadListener4 != null) {
                        iPPGRawDataReadListener4.onPPGRawDataReadStop();
                    }
                    break;
            }
        }
    }

    private final boolean vp_b() {
        VpSpGetUtil vPDeviceSP = VPOperateManager.getInstance().getVPDeviceSP();
        String deviceNumber = vPDeviceSP.getDeviceNumber();
        String testVersion = vPDeviceSP.getTestVersion();
        boolean z = Intrinsics.areEqual("5902", deviceNumber) && Intrinsics.areEqual("00.58.01.00", testVersion);
        if (z) {
            com.veepoo.protocol.util.vp_w.vp_b("-JH58定制原始数据上报-", "检测到 Bug 固件版本：" + deviceNumber + " / " + testVersion + "，启动时区补偿");
        }
        return z;
    }

    private final int vp_a() {
        if (!vp_b()) {
            return 0;
        }
        int offset = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 1000;
        com.veepoo.protocol.util.vp_w.vp_c("-JH58定制原始数据上报-", "固件Bug版本兼容：检测到时区偏移量为 " + offset + " 秒");
        return offset;
    }

    private final byte[] vp_c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length - 1];
        System.arraycopy(bArr, 0, bArr2, 0, 3);
        System.arraycopy(bArr, 4, bArr2, 3, bArr.length - 4);
        return bArr2;
    }

    public /* synthetic */ vp_bw(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(TimeData timeData, PPGTestMode pPGTestMode, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, IPPGRawDataReadListener iPPGRawDataReadListener) {
        Intrinsics.checkNotNullParameter(timeData, "startTime");
        Intrinsics.checkNotNullParameter(pPGTestMode, "mode");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        Intrinsics.checkNotNullParameter(iPPGRawDataReadListener, "listener");
        super.vp_a(timeData, pPGTestMode, bluetoothClient, str, bleWriteResponse, iPPGRawDataReadListener);
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        this.vp_j = iPPGRawDataReadListener;
        byte[] bArrVp_b = com.veepoo.protocol.util.vp_g.vp_b(timeData.getTimestampSeconds() + vp_a());
        byte[] bArr = {-21, 2, pPGTestMode.getValue(), bArrVp_b[0], bArrVp_b[1], bArrVp_b[2], bArrVp_b[3]};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取PPG测量状态：读取" + timeData.toFullDateTimeString() + "  " + timeData.getTimestampSeconds() + " => " + com.veepoo.protocol.util.vp_g.vp_d(bArrVp_b) + "之后的" + pPGTestMode + "数据 ---> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(boolean z, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_a(z, bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[4];
        bArr[0] = -21;
        bArr[1] = 3;
        bArr[2] = 2;
        bArr[3] = z ? (byte) 2 : (byte) 1;
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "请求【" + (z ? "开启" : "停止") + "】设备PPG高频实时传输--> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.File, java.lang.Throwable] */
    private final void vp_a(Context context, String str, String str2) {
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            Log.e("FileSave", "无法获取外部存储目录，请检查设备状态或存储是否可用。");
            return;
        }
        ?? file = new File(externalFilesDir, str);
        Log.d("FileSave", "目标文件路径: " + file.getAbsolutePath());
        try {
            FilesKt.writeText$default((File) file, str2, (Charset) null, 2, (Object) null);
            Log.i("FileSave", "文本已成功保存到: " + file.getAbsolutePath());
        } catch (IOException e) {
            Log.e("FileSave", "保存文件失败: " + e.getMessage());
            file.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void vp_a(boolean z) {
        byte[] bArr = {-21, 3, 1, z};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "发送PPG数据上报通道开启结果，发送【" + (z ? "开启成功" : "开启失败") + "】指令 --> " + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        BluetoothClient bluetoothClient = this.vp_c;
        BluetoothClient bluetoothClient2 = bluetoothClient;
        if (bluetoothClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("client");
            bluetoothClient2 = null;
        }
        String str = this.vp_d;
        String str2 = str;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mac");
            str2 = null;
        }
        BleWriteResponse bleWriteResponse = this.vp_e;
        BleWriteResponse bleWriteResponse2 = bleWriteResponse;
        if (bleWriteResponse == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleWriteResponse");
            bleWriteResponse2 = null;
        }
        send(bArr, bluetoothClient2, str2, bleWriteResponse2);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(PPGSwitchStatus pPGSwitchStatus, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(pPGSwitchStatus, "ppgSwitchStatus");
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        Intrinsics.checkNotNullParameter(bleWriteResponse, "bleWriteResponse");
        super.vp_a(pPGSwitchStatus, bluetoothClient, str, bleWriteResponse);
        this.vp_c = bluetoothClient;
        this.vp_d = str;
        this.vp_e = bleWriteResponse;
        byte[] bArr = {-21, 1, 2, pPGSwitchStatus.getValue()};
        com.veepoo.protocol.util.vp_w.vp_a("-JH58定制原始数据上报-", "读取PPG测量状态：" + com.veepoo.protocol.util.vp_g.vp_d(bArr));
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    public final void vp_a(IPPGRealTimeTransferOptListener iPPGRealTimeTransferOptListener) {
        this.vp_k = iPPGRealTimeTransferOptListener;
    }

    public final void vp_a(IPPGSwitchOperaterListener iPPGSwitchOperaterListener) {
        this.vp_i = iPPGSwitchOperaterListener;
    }
}
