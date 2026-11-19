package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IUIBaseInfoListener;
import com.veepoo.protocol.listener.data.IUIOprateListener;
import com.veepoo.protocol.model.datas.UICustomSetData;
import com.veepoo.protocol.model.datas.UIDataAGPS;
import com.veepoo.protocol.model.datas.UIDataCustom;
import com.veepoo.protocol.model.datas.UIDataG15Img;
import com.veepoo.protocol.model.datas.UIDataImagePush;
import com.veepoo.protocol.model.datas.UIDataServer;
import com.veepoo.protocol.model.datas.UiData;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dc.class */
public class vp_dc extends com.veepoo.protocol.vp_a {
    byte vp_c = 2;
    byte vp_d = 1;
    byte vp_e = 3;
    IUIBaseInfoListener vp_f;
    IUIOprateListener vp_g;

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_d(bluetoothClient, str, bleWriteResponse);
        super.vp_a(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, i);
        super.vp_a(vp_a(i), bluetoothClient, str, bleWriteResponse);
    }

    public UIDataServer vp_e(int[] iArr) {
        int i = iArr[1];
        return new UIDataServer(iArr[2], i, iArr[3], (iArr[4] & 255) | ((iArr[5] << 8) & 65280) | ((iArr[6] << 16) & 16711680) | ((iArr[7] << 24) & (-16777216)), (iArr[8] & 255) | ((iArr[9] << 8) & 65280) | ((iArr[10] << 16) & 16711680) | ((iArr[11] << 24) & (-16777216)), (iArr[12] & 255) | ((iArr[13] << 8) & 65280), (iArr[14] & 255) | ((iArr[15] << 8) & 65280), (iArr[16] & 255) | ((iArr[17] << 8) & 65280), iArr[19]);
    }

    private byte[] vp_a(UIDataAGPS uIDataAGPS, int i) {
        int dataReceiveAddress = uIDataAGPS.getDataReceiveAddress();
        long fileLength = uIDataAGPS.getFileLength();
        long timeStamp = uIDataAGPS.getTimeStamp();
        bArr[11] = (byte) (timeStamp & 255);
        bArr[12] = (byte) ((timeStamp >> 8) & 255);
        byte[] bArr = {80, this.vp_d, EUIFromType.A_GPS.code, (byte) (dataReceiveAddress & 255), (byte) ((dataReceiveAddress >> 8) & 255), (byte) ((dataReceiveAddress >> 16) & 255), (byte) ((dataReceiveAddress >> 24) & 255), (byte) (fileLength & 255), (byte) ((fileLength >> 8) & 255), (byte) ((fileLength >> 16) & 255), (byte) ((fileLength >> 24) & 255), (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((timeStamp >> 16) & 255), (byte) ((timeStamp >> 24) & 255), 0, 0, 0, 0, 0};
        return bArr;
    }

    private byte[] vp_c(UIDataG15Img uIDataG15Img) {
        return vp_a(uIDataG15Img, EUIFromType.G15_IMG_THEME_1.code);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_e(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_e(bluetoothClient, str, bleWriteResponse, z);
        super.vp_a(z ? vp_c() : vp_b(), bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_d(UIDataG15Img uIDataG15Img) {
        return vp_a(uIDataG15Img, EUIFromType.G15_IMG_THEME_2.code);
    }

    private byte[] vp_b(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private byte[] vp_c() {
        return new byte[]{68, 79, 79, 71, 5, 0};
    }

    private byte[] vp_a(int i) {
        byte[] bArr = new byte[20];
        bArr[0] = 68;
        bArr[1] = 79;
        bArr[2] = 79;
        bArr[3] = 71;
        bArr[4] = 3;
        bArr[5] = (byte) (i & 255);
        bArr[6] = (byte) ((i >> 8) & 255);
        bArr[7] = (byte) ((i >> 16) & 255);
        bArr[8] = (byte) ((i >> 24) & 255);
        return bArr;
    }

    public UIDataCustom vp_d(int[] iArr) {
        int i = iArr[1];
        int i2 = iArr[2];
        int i3 = iArr[3];
        int i4 = iArr[19];
        int i5 = (iArr[4] & 255) | ((iArr[5] << 8) & 65280) | ((iArr[6] << 16) & 16711680) | ((iArr[7] << 24) & (-16777216));
        int i6 = (iArr[8] & 255) | ((iArr[9] << 8) & 65280) | ((iArr[10] << 16) & 16711680);
        int i7 = iArr[11];
        int i8 = iArr[12];
        int i9 = (i8 >> 5) & 7;
        int i10 = i8 & 31;
        int i11 = iArr[13];
        int i12 = (i11 >> 4) & 15;
        int i13 = i11 & 15;
        return new UIDataCustom(i5, i6, EWatchUIType.getEWatchUIType(i7), i9 == 1, EWatchUIElementPosition.getWatchUIElementPosition(i10), EWatchUIElementType.getEWatchUIElementType(i12), EWatchUIElementType.getEWatchUIElementType(i13), (iArr[14] & 255) | ((iArr[15] << 8) & 65280) | ((iArr[16] << 16) & 16711680), (iArr[17] & 255) | ((iArr[18] << 8) & 65280), 0, i4);
    }

    private byte[] vp_b(UIDataG15Img uIDataG15Img) {
        return vp_a(uIDataG15Img, EUIFromType.G15_IMG_PROFILE.code);
    }

    private byte[] vp_a(int i, long j) {
        return new byte[]{68, 79, 79, 71, 1, (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)};
    }

    private UIDataAGPS vp_c(int[] iArr) {
        int i = iArr[1];
        int i2 = iArr[2];
        int i3 = iArr[3];
        int i4 = iArr[19];
        int i5 = (iArr[4] & 255) | ((iArr[5] << 8) & 65280) | ((iArr[6] << 16) & 16711680) | ((iArr[7] << 24) & (-16777216));
        int i6 = (iArr[8] & 255) | ((iArr[9] << 8) & 65280) | ((iArr[10] << 16) & 16711680) | ((iArr[11] << 24) & (-16777216));
        int i7 = (iArr[12] & 255) | ((iArr[13] << 8) & 65280);
        int i8 = iArr[14];
        int i9 = (iArr[15] & 255) | ((iArr[16] << 8) & 65280);
        UIDataAGPS uIDataAGPS = new UIDataAGPS(i5, i6, 0, i7, i4, 0L);
        uIDataAGPS.setValidDay(i8);
        uIDataAGPS.setValidMinute(i9);
        return uIDataAGPS;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_c(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
        super.vp_c(bluetoothClient, str, bleWriteResponse, uIDataG15Img);
        super.send(vp_c(uIDataG15Img), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_d(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
        super.vp_d(bluetoothClient, str, bleWriteResponse, uIDataG15Img);
        super.send(vp_d(uIDataG15Img), bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(UIDataCustom uIDataCustom) {
        int dataReceiveAddress = uIDataCustom.getDataReceiveAddress();
        long fileLength = uIDataCustom.getFileLength();
        return new byte[]{80, this.vp_d, EUIFromType.CUSTOM.code, (byte) (dataReceiveAddress & 255), (byte) ((dataReceiveAddress >> 8) & 255), (byte) ((dataReceiveAddress >> 16) & 255), (byte) ((dataReceiveAddress >> 24) & 255), (byte) (fileLength & 255), (byte) ((fileLength >> 8) & 255), (byte) ((fileLength >> 16) & 255), (byte) ((fileLength >> 24) & 255), 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private byte[] vp_b() {
        return new byte[]{68, 79, 79, 71, 5, 1};
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = 68;
        bArr[1] = 79;
        bArr[2] = 79;
        bArr[3] = 71;
        bArr[4] = 2;
        return bArr;
    }

    private void vp_b(byte[] bArr) {
        byte b;
        UIDataCustom uIDataCustomVp_d;
        IUIBaseInfoListener iUIBaseInfoListener;
        if (bArr == null || bArr.length < 20) {
            return;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[1];
        int i2 = iArrByte2HexToIntArr[2];
        int i3 = iArrByte2HexToIntArr[3];
        int i4 = iArrByte2HexToIntArr[19];
        byte b2 = bArr[1];
        if (b2 == 2 && bArr[3] == 1) {
            if (i2 == EUIFromType.SERVER.code) {
                uIDataCustomVp_d = vp_e(iArrByte2HexToIntArr);
                IUIBaseInfoListener iUIBaseInfoListener2 = this.vp_f;
                iUIBaseInfoListener = iUIBaseInfoListener2;
                if (iUIBaseInfoListener2 == null) {
                    return;
                }
            } else if (i2 == EUIFromType.CUSTOM.code) {
                uIDataCustomVp_d = vp_d(iArrByte2HexToIntArr);
                IUIBaseInfoListener iUIBaseInfoListener3 = this.vp_f;
                iUIBaseInfoListener = iUIBaseInfoListener3;
                if (iUIBaseInfoListener3 == null) {
                    return;
                }
            } else {
                byte b3 = bArr[2];
                if (b3 == EUIFromType.A_GPS.code) {
                    uIDataCustomVp_d = vp_c(iArrByte2HexToIntArr);
                    IUIBaseInfoListener iUIBaseInfoListener4 = this.vp_f;
                    iUIBaseInfoListener = iUIBaseInfoListener4;
                    if (iUIBaseInfoListener4 == null) {
                        return;
                    }
                } else if (b3 == EUIFromType.G15_IMG_QR_CODE_APP_DOWNLOAD.code || b3 == EUIFromType.G15_IMG_PROFILE.code || b3 == EUIFromType.G15_IMG_THEME_1.code || b3 == EUIFromType.G15_IMG_THEME_2.code) {
                    uIDataCustomVp_d = vp_a(iArrByte2HexToIntArr);
                    IUIBaseInfoListener iUIBaseInfoListener5 = this.vp_f;
                    iUIBaseInfoListener = iUIBaseInfoListener5;
                    if (iUIBaseInfoListener5 == null) {
                        return;
                    }
                } else {
                    if (b3 != EUIFromType.IMAGE_PUSH.code) {
                        return;
                    }
                    uIDataCustomVp_d = vp_b(iArrByte2HexToIntArr);
                    IUIBaseInfoListener iUIBaseInfoListener6 = this.vp_f;
                    iUIBaseInfoListener = iUIBaseInfoListener6;
                    if (iUIBaseInfoListener6 == null) {
                        return;
                    }
                }
            }
        } else {
            if (b2 != 3 || bArr[3] != 1) {
                if (b2 == 1) {
                    new StringBuilder().append("进入UI升级模式:").append(i2);
                    if (i2 == EUIFromType.SERVER.code || i2 == EUIFromType.CUSTOM.code || i2 == EUIFromType.IMAGE_PUSH.code || (b = bArr[2]) == EUIFromType.A_GPS.code || b == EUIFromType.G15_IMG_QR_CODE_APP_DOWNLOAD.code || b == EUIFromType.G15_IMG_PROFILE.code || b == EUIFromType.G15_IMG_THEME_1.code || b == EUIFromType.G15_IMG_THEME_2.code) {
                        UiData uiData = new UiData(1, i2, iArrByte2HexToIntArr[3]);
                        IUIOprateListener iUIOprateListener = this.vp_g;
                        if (iUIOprateListener != null) {
                            iUIOprateListener.onUIOprateCallckback(uiData);
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            if (i2 != 2) {
                return;
            }
            uIDataCustomVp_d = vp_d(iArrByte2HexToIntArr);
            IUIBaseInfoListener iUIBaseInfoListener7 = this.vp_f;
            iUIBaseInfoListener = iUIBaseInfoListener7;
            if (iUIBaseInfoListener7 == null) {
                return;
            }
        }
        iUIBaseInfoListener.onBaseUiInfo(uIDataCustomVp_d);
    }

    public UIDataImagePush vp_b(int[] iArr) {
        int i = iArr[1];
        int i2 = iArr[2];
        int i3 = iArr[3];
        return new UIDataImagePush(0, (iArr[4] & 255) | ((iArr[5] << 8) & 65280) | ((iArr[6] << 16) & 16711680) | ((iArr[7] << 24) & (-16777216)), (iArr[10] & 255) | ((iArr[11] << 8) & 65280), (iArr[8] & 255) | ((iArr[9] << 8) & 65280), (iArr[12] & 255) | ((iArr[13] << 8) & 65280));
    }

    private byte[] vp_a(UIDataG15Img uIDataG15Img, byte b) {
        int dataReceiveAddress = uIDataG15Img.getDataReceiveAddress();
        long fileLength = uIDataG15Img.getFileLength();
        return new byte[]{80, this.vp_d, b, (byte) (dataReceiveAddress & 255), (byte) ((dataReceiveAddress >> 8) & 255), (byte) ((dataReceiveAddress >> 16) & 255), (byte) ((dataReceiveAddress >> 24) & 255), (byte) (fileLength & 255), (byte) ((fileLength >> 8) & 255), (byte) ((fileLength >> 16) & 255), (byte) ((fileLength >> 24) & 255), 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, uIDataG15Img);
        super.send(vp_b(uIDataG15Img), bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(UIDataG15Img uIDataG15Img) {
        return vp_a(uIDataG15Img, EUIFromType.G15_IMG_QR_CODE_APP_DOWNLOAD.code);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, byte[] bArr) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, bArr);
        super.vp_b(bArr, bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(UIDataImagePush uIDataImagePush) {
        int dataReceiveAddress = uIDataImagePush.getDataReceiveAddress();
        long fileLength = uIDataImagePush.getFileLength();
        return new byte[]{80, this.vp_d, EUIFromType.IMAGE_PUSH.code, (byte) (dataReceiveAddress & 255), (byte) ((dataReceiveAddress >> 8) & 255), (byte) ((dataReceiveAddress >> 16) & 255), (byte) ((dataReceiveAddress >> 24) & 255), (byte) (fileLength & 255), (byte) ((fileLength >> 8) & 255), (byte) ((fileLength >> 16) & 255), (byte) ((fileLength >> 24) & 255), 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private byte[] vp_a(UIDataServer uIDataServer) {
        int dataReceiveAddress = uIDataServer.getDataReceiveAddress();
        long dataFileLength = uIDataServer.getDataFileLength();
        int binDataType = uIDataServer.getBinDataType();
        uIDataServer.getDeviceAialShape();
        int imgCrcId = uIDataServer.getImgCrcId();
        return new byte[]{80, this.vp_d, EUIFromType.SERVER.code, (byte) (dataReceiveAddress & 255), (byte) ((dataReceiveAddress >> 8) & 255), (byte) ((dataReceiveAddress >> 16) & 255), (byte) ((dataReceiveAddress >> 24) & 255), (byte) (dataFileLength & 255), (byte) ((dataFileLength >> 8) & 255), (byte) ((dataFileLength >> 16) & 255), (byte) ((dataFileLength >> 24) & 255), (byte) (binDataType & 255), (byte) ((binDataType >> 8) & 255), (byte) (imgCrcId & 255), (byte) ((imgCrcId >> 8) & 255), 1, 0, 0, 0, 0};
    }

    private byte[] vp_a(UICustomSetData uICustomSetData) {
        boolean zIsDefalutUI = uICustomSetData.isDefalutUI();
        EWatchUIElementPosition timePosition = uICustomSetData.getTimePosition();
        EWatchUIElementType upTimeType = uICustomSetData.getUpTimeType();
        EWatchUIElementType downTimeType = uICustomSetData.getDownTimeType();
        int color888 = uICustomSetData.getColor888();
        byte[] bArr = new byte[20];
        bArr[0] = 80;
        bArr[1] = 3;
        bArr[2] = 2;
        bArr[3] = vp_a(zIsDefalutUI, timePosition);
        bArr[4] = vp_a(upTimeType, downTimeType);
        byte[] bArrVp_b = vp_b(color888);
        bArr[5] = bArrVp_b[3];
        bArr[6] = bArrVp_b[2];
        bArr[7] = bArrVp_b[1];
        return bArr;
    }

    private byte vp_a(EWatchUIElementType eWatchUIElementType, EWatchUIElementType eWatchUIElementType2) {
        int value = eWatchUIElementType.getValue();
        int value2 = eWatchUIElementType2.getValue();
        byte bLoUint16 = VpBleByteUtil.loUint16((short) value);
        return (byte) ((bLoUint16 << 4) | VpBleByteUtil.loUint16((short) value2));
    }

    private byte vp_a(boolean z, EWatchUIElementPosition eWatchUIElementPosition) {
        return (byte) (((z ? 1 : 0) << 5) | VpBleByteUtil.loUint16((short) eWatchUIElementPosition.getValue()));
    }

    private UIDataG15Img vp_a(int[] iArr) {
        int i = iArr[1];
        int i2 = iArr[2];
        int i3 = iArr[3];
        return new UIDataG15Img((iArr[4] & 255) | ((iArr[5] << 8) & 65280) | ((iArr[6] << 16) & 16711680) | ((iArr[7] << 24) & (-16777216)), (iArr[8] & 255) | ((iArr[9] << 8) & 65280) | ((iArr[10] << 16) & 16711680) | ((iArr[11] << 24) & (-16777216)), 0, (iArr[12] & 255) | ((iArr[13] << 8) & 65280), iArr[19], 0L);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        if (iListener != null && (iListener instanceof IUIBaseInfoListener)) {
            this.vp_f = (IUIBaseInfoListener) iListener;
        }
        if (iListener != null && (iListener instanceof IUIOprateListener)) {
            this.vp_g = (IUIOprateListener) iListener;
        }
        vp_b(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataAGPS uIDataAGPS, int i) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uIDataAGPS, i);
        super.send(vp_a(uIDataAGPS, i), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataCustom uIDataCustom) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uIDataCustom);
        super.send(vp_a(uIDataCustom), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataG15Img uIDataG15Img) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uIDataG15Img);
        super.send(vp_a(uIDataG15Img), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataImagePush uIDataImagePush) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uIDataImagePush);
        super.send(vp_a(uIDataImagePush), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UIDataServer uIDataServer) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uIDataServer);
        super.send(vp_a(uIDataServer), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, EUIFromType eUIFromType) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, eUIFromType);
        super.send(vp_a(eUIFromType.code), bluetoothClient, str, bleWriteResponse);
    }

    private byte[] vp_a(byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = 80;
        bArr[1] = this.vp_c;
        bArr[2] = b;
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, UICustomSetData uICustomSetData) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, uICustomSetData);
        super.send(vp_a(uICustomSetData), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, long j) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, i, j);
        super.vp_a(vp_a(i, j), bluetoothClient, str, bleWriteResponse);
    }
}
