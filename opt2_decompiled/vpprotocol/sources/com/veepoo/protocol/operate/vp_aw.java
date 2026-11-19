package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IAppReportGpsDataListener;
import com.veepoo.protocol.listener.data.IChantingDataListener;
import com.veepoo.protocol.listener.data.IGpsLatLonDataListener;
import com.veepoo.protocol.listener.data.IKaaBaDataListener;
import com.veepoo.protocol.listener.data.IReportGpsDataListener;
import com.veepoo.protocol.model.datas.ChantingData;
import com.veepoo.protocol.model.datas.GpsLatlonData;
import com.veepoo.protocol.model.datas.KaaBaData;
import com.veepoo.protocol.model.datas.ReportGpsData;
import com.veepoo.protocol.model.datas.ReportGpsLatLongData;
import com.veepoo.protocol.model.enums.EAllSetStatus;
import com.veepoo.protocol.model.settings.ChantingSetting;
import com.veepoo.protocol.model.settings.GpsLatLongSetting;
import com.veepoo.protocol.model.settings.GpsLatLongSettingFromApp;
import com.veepoo.protocol.model.settings.KaabaSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_aw.class */
public class vp_aw extends com.veepoo.protocol.vp_a {
    byte vp_c = 1;
    byte vp_d = 2;
    byte vp_e = 3;
    byte vp_f = 4;
    byte vp_g = 5;
    IGpsLatLonDataListener vp_h;
    IChantingDataListener vp_i;
    IKaaBaDataListener vp_j;
    IReportGpsDataListener vp_k;
    IAppReportGpsDataListener vp_l;

    private ReportGpsLatLongData vp_g(byte[] bArr) {
        return new ReportGpsLatLongData(bArr[2] == 1, ((double) vp_a(bArr[3], bArr[4], bArr[5], bArr[6])) / 100000.0d, ((double) vp_a(bArr[7], bArr[8], bArr[9], bArr[10])) / 100000.0d, VpBleByteUtil.twoByteToUnsignedInt(bArr[12], bArr[11]));
    }

    private ReportGpsData vp_c(byte[] bArr) {
        return new ReportGpsData(bArr[2] == 1);
    }

    private KaaBaData vp_e(byte[] bArr) {
        KaaBaData kaaBaData = new KaaBaData();
        kaaBaData.seteAllSetStatus(vp_b(bArr));
        return kaaBaData;
    }

    private EAllSetStatus vp_b(byte[] bArr) {
        if (bArr == null || bArr.length <= 3) {
            return EAllSetStatus.UNKONW;
        }
        byte b = bArr[2];
        if (b == 0) {
            return EAllSetStatus.UNSUPPORT;
        }
        if (b == 1) {
            return EAllSetStatus.SETTING_SUCCESS;
        }
        return b == 2 ? EAllSetStatus.SETTING_FAIL : EAllSetStatus.UNKONW;
    }

    private ChantingData vp_d(byte[] bArr) {
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
        return new ChantingData(iArrByte2HexToIntArr[2], iArrByte2HexToIntArr[3], vp_a(iArrByte2HexToIntArr[9], iArrByte2HexToIntArr[10], iArrByte2HexToIntArr[11], iArrByte2HexToIntArr[12]), vp_a(iArrByte2HexToIntArr[13], iArrByte2HexToIntArr[14], iArrByte2HexToIntArr[15], iArrByte2HexToIntArr[16]), iArrByte2HexToIntArr[4], vp_a(iArrByte2HexToIntArr[5], iArrByte2HexToIntArr[6], iArrByte2HexToIntArr[7], iArrByte2HexToIntArr[8]), Integer.valueOf(strArrByte2HexToStrArr[18] + strArrByte2HexToStrArr[17], 16).intValue());
    }

    private GpsLatlonData vp_f(byte[] bArr) {
        GpsLatlonData gpsLatlonData = new GpsLatlonData();
        byte b = bArr[1];
        if (b == 1) {
            gpsLatlonData.setAllSetStatus(EAllSetStatus.SETTING_SUCCESS);
        } else if (b == 2) {
            gpsLatlonData.setAllSetStatus(EAllSetStatus.SETTING_FAIL);
        } else {
            gpsLatlonData.setAllSetStatus(EAllSetStatus.UNKONW);
        }
        return gpsLatlonData;
    }

    public byte[] vp_a(long j) {
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)};
    }

    public byte[] vp_a(short s) {
        return new byte[]{VpBleByteUtil.loUint16(s), VpBleByteUtil.hiUint16(s)};
    }

    private byte[] vp_a(ChantingSetting chantingSetting, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -113;
        bArr[1] = b;
        bArr[2] = (byte) 0;
        byte[] bArrVp_a = vp_a(chantingSetting.getTimestamp());
        bArr[3] = bArrVp_a[0];
        bArr[4] = bArrVp_a[1];
        bArr[5] = bArrVp_a[2];
        bArr[6] = bArrVp_a[3];
        return bArr;
    }

    private byte[] vp_a(GpsLatLongSetting gpsLatLongSetting, byte b) {
        byte[] bArrVp_a = vp_a((int) (gpsLatLongSetting.getLonv() * 100000.0d));
        byte[] bArrVp_a2 = vp_a((int) (gpsLatLongSetting.getLatv() * 100000.0d));
        byte[] bArrVp_a3 = vp_a(gpsLatLongSetting.getTimeZone());
        byte[] bArrVp_a4 = vp_a(gpsLatLongSetting.getTimestamp());
        byte[] bArrVp_a5 = vp_a(gpsLatLongSetting.getAltitude());
        return new byte[]{-113, b, bArrVp_a[0], bArrVp_a[1], bArrVp_a[2], bArrVp_a[3], bArrVp_a2[0], bArrVp_a2[1], bArrVp_a2[2], bArrVp_a2[3], bArrVp_a3[0], bArrVp_a3[1], bArrVp_a4[0], bArrVp_a4[1], bArrVp_a4[2], bArrVp_a4[3], bArrVp_a5[0], bArrVp_a5[1], 0, 0};
    }

    private byte[] vp_a(GpsLatLongSettingFromApp gpsLatLongSettingFromApp, byte b) {
        byte[] bArr = new byte[20];
        bArr[0] = -113;
        bArr[1] = b;
        bArr[2] = gpsLatLongSettingFromApp.isReqeustRepo() ? (byte) 1 : (byte) 2;
        bArr[3] = gpsLatLongSettingFromApp.getGpsState().getValue();
        byte[] bArrVp_a = vp_a((int) (gpsLatLongSettingFromApp.getLonv() * 100000.0d));
        bArr[4] = bArrVp_a[0];
        bArr[5] = bArrVp_a[1];
        bArr[6] = bArrVp_a[2];
        bArr[7] = bArrVp_a[3];
        byte[] bArrVp_a2 = vp_a((int) (gpsLatLongSettingFromApp.getLatv() * 100000.0d));
        bArr[8] = bArrVp_a2[0];
        bArr[9] = bArrVp_a2[1];
        bArr[10] = bArrVp_a2[2];
        bArr[11] = bArrVp_a2[3];
        byte[] bArrVp_a3 = vp_a(gpsLatLongSettingFromApp.getTimeZone());
        bArr[12] = bArrVp_a3[0];
        bArr[13] = bArrVp_a3[1];
        byte[] bArrVp_a4 = vp_a(gpsLatLongSettingFromApp.getTimestamp());
        bArr[14] = bArrVp_a4[0];
        bArr[15] = bArrVp_a4[1];
        bArr[16] = bArrVp_a4[2];
        bArr[17] = bArrVp_a4[3];
        return bArr;
    }

    private int vp_a(int i, int i2, int i3, int i4) {
        return (i & 255) | ((i2 << 8) & 65280) | ((i3 << 16) & 16711680) | ((i4 << 24) & (-16777216));
    }

    private byte[] vp_a(KaabaSetting kaabaSetting, byte b) {
        byte[] bArrVp_a = vp_a((int) (kaabaSetting.getLon() * 100000.0d));
        byte[] bArrVp_a2 = vp_a((int) (kaabaSetting.getLat() * 100000.0d));
        byte[] bArrVp_a3 = vp_a(kaabaSetting.getAltitude());
        return new byte[]{-113, b, bArrVp_a[0], bArrVp_a[1], bArrVp_a[2], bArrVp_a[3], bArrVp_a2[0], bArrVp_a2[1], bArrVp_a2[2], bArrVp_a2[3], bArrVp_a3[0], bArrVp_a3[1], 0, 0, 0, 0, 0, 0, 0, 0};
    }

    private byte[] vp_a(boolean z) {
        byte[] bArr = new byte[20];
        bArr[0] = -113;
        bArr[1] = this.vp_f;
        if (z) {
            bArr[2] = 1;
        } else {
            bArr[2] = 2;
        }
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        byte b = bArr[1];
        if (b == this.vp_c) {
            if (iListener instanceof IGpsLatLonDataListener) {
                this.vp_h = (IGpsLatLonDataListener) iListener;
            }
            GpsLatlonData gpsLatlonDataVp_f = vp_f(bArr);
            IGpsLatLonDataListener iGpsLatLonDataListener = this.vp_h;
            if (iGpsLatLonDataListener != null) {
                iGpsLatLonDataListener.onGpsLatLonDataChange(gpsLatlonDataVp_f);
                return;
            }
            return;
        }
        if (b == this.vp_d) {
            if (iListener instanceof IChantingDataListener) {
                this.vp_i = (IChantingDataListener) iListener;
            }
            ChantingData chantingDataVp_d = vp_d(bArr);
            IChantingDataListener iChantingDataListener = this.vp_i;
            if (iChantingDataListener != null) {
                iChantingDataListener.onChantingDataChange(chantingDataVp_d);
                return;
            }
            return;
        }
        if (b == this.vp_e) {
            if (iListener instanceof IKaaBaDataListener) {
                this.vp_j = (IKaaBaDataListener) iListener;
            }
            KaaBaData kaaBaDataVp_e = vp_e(bArr);
            IKaaBaDataListener iKaaBaDataListener = this.vp_j;
            if (iKaaBaDataListener != null) {
                iKaaBaDataListener.onKaaBaDataChange(kaaBaDataVp_e);
                return;
            }
            return;
        }
        if (b == this.vp_f) {
            if (iListener instanceof IReportGpsDataListener) {
                this.vp_k = (IReportGpsDataListener) iListener;
            }
            if (this.vp_k != null) {
                ReportGpsLatLongData reportGpsLatLongDataVp_g = vp_g(bArr);
                this.vp_k.onReportGpsDataDataChange(reportGpsLatLongDataVp_g);
                new StringBuilder().append("reportGpsLatLongData:").append(reportGpsLatLongDataVp_g);
                return;
            }
            return;
        }
        if (b == this.vp_g) {
            if (iListener instanceof IAppReportGpsDataListener) {
                this.vp_l = (IAppReportGpsDataListener) iListener;
            }
            ReportGpsData reportGpsDataVp_c = vp_c(bArr);
            IAppReportGpsDataListener iAppReportGpsDataListener = this.vp_l;
            if (iAppReportGpsDataListener != null) {
                iAppReportGpsDataListener.onReportGpsDataDataChange(reportGpsDataVp_c);
            }
        }
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ChantingSetting chantingSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, chantingSetting);
        super.send(vp_a(chantingSetting, this.vp_d), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, boolean z) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, z);
        super.send(vp_a(z), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, GpsLatLongSetting gpsLatLongSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, gpsLatLongSetting);
        super.send(vp_a(gpsLatLongSetting, this.vp_c), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, GpsLatLongSettingFromApp gpsLatLongSettingFromApp) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, gpsLatLongSettingFromApp);
        super.send(vp_a(gpsLatLongSettingFromApp, this.vp_g), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, KaabaSetting kaabaSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, kaabaSetting);
        super.send(vp_a(kaabaSetting, this.vp_e), bluetoothClient, str, bleWriteResponse);
    }
}
