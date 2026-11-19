package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.ISportModelDataCrcListener;
import com.veepoo.protocol.listener.data.ISportModelStateListener;
import com.veepoo.protocol.model.datas.SportModelStateData;
import com.veepoo.protocol.model.enums.ECheckWear;
import com.veepoo.protocol.model.enums.ESportModelStateStauts;
import com.veepoo.protocol.model.enums.ESportType;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ct.class */
public class vp_ct extends com.veepoo.protocol.vp_a {
    private ISportModelStateListener vp_c;
    private ISportModelDataCrcListener vp_d;
    int vp_e = 1;
    int vp_f = 0;
    int vp_g = 2;

    private int[] vp_c(byte[] bArr) {
        int sportModelDay = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getSportModelDay();
        int[] iArr = new int[sportModelDay];
        for (int i = 0; i < sportModelDay; i++) {
            int i2 = i * 2;
            iArr[i] = com.veepoo.protocol.util.vp_g.vp_a(bArr[i2 + 4], bArr[i2 + 3]);
        }
        return iArr;
    }

    private SportModelStateData vp_b(byte[] bArr) {
        SportModelStateData sportModelStateData = new SportModelStateData();
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        if (bArr.length < 20) {
            return sportModelStateData;
        }
        sportModelStateData.setOprateStauts(vp_a(bArr[1], bArr[2]));
        sportModelStateData.setDeviceStauts(vp_a(bArr[3]));
        sportModelStateData.setSportModeType(iArrByte2HexToIntArr[4]);
        return sportModelStateData;
    }

    private ESportModelStateStauts vp_a(byte b) {
        if (b == 0) {
            return ESportModelStateStauts.DEVICE_FREE;
        }
        if (b == 1) {
            return ESportModelStateStauts.DEVICE_HAD_START_BEFORE;
        }
        return b == 2 ? ESportModelStateStauts.DEVICE_BUSY : ESportModelStateStauts.UNKNOW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ao(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ao(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = new byte[20];
        bArr[0] = -45;
        bArr[1] = 1;
        super.send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_cc(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_cc(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(this.vp_f, 0), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_ap(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_ap(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(this.vp_g, 0), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        byte b = bArr[0];
        if (b == -43) {
            SportModelStateData sportModelStateDataVp_b = vp_b(bArr);
            ISportModelStateListener iSportModelStateListener = this.vp_c;
            if (iSportModelStateListener != null) {
                iSportModelStateListener.onSportModelStateChange(sportModelStateDataVp_b);
                return;
            }
            return;
        }
        if (b == -45) {
            byte b2 = bArr[1];
            if (b2 == 2) {
                ISportModelStateListener iSportModelStateListener2 = this.vp_c;
                if (iSportModelStateListener2 != null) {
                    iSportModelStateListener2.onSportStopped();
                    return;
                }
                return;
            }
            if (b2 == 0) {
                ISportModelDataCrcListener iSportModelDataCrcListener = this.vp_d;
                if (iSportModelDataCrcListener != null) {
                    iSportModelDataCrcListener.onReadFail();
                    return;
                }
                return;
            }
            int[] iArrVp_c = vp_c(bArr);
            ISportModelDataCrcListener iSportModelDataCrcListener2 = this.vp_d;
            if (iSportModelDataCrcListener2 != null) {
                iSportModelDataCrcListener2.onSportCrcDataChange(iArrVp_c);
            }
        }
    }

    private ECheckWear vp_a(byte b, byte b2) {
        if (b == 1 && b2 == 1) {
            return ECheckWear.OPEN_SUCCESS;
        }
        if (b == 1 && b2 == 0) {
            return ECheckWear.OPEN_FAIL;
        }
        if (b == 0 && b2 == 1) {
            return ECheckWear.CLOSE_SUCCESS;
        }
        if (b == 0 && b2 == 0) {
            return ECheckWear.CLOSE_FAIL;
        }
        if (b == 2 && b2 == 1) {
            return ECheckWear.READ_SUCCESS;
        }
        return (b == 2 && b2 == 0) ? ECheckWear.READ_FAIL : ECheckWear.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, ESportType eSportType) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, eSportType);
        super.send(vp_a(this.vp_e, eSportType.getValue()), bluetoothClient, str, bleWriteResponse);
    }

    public byte[] vp_a(int i, int i2) {
        byte[] bArr = new byte[20];
        bArr[0] = -43;
        bArr[1] = VpBleByteUtil.loUint16((short) i);
        bArr[2] = VpBleByteUtil.loUint16((short) i2);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        if (iListener != null && (iListener instanceof ISportModelStateListener)) {
            this.vp_c = (ISportModelStateListener) iListener;
        }
        if (iListener != null && (iListener instanceof ISportModelDataCrcListener)) {
            this.vp_d = (ISportModelDataCrcListener) iListener;
        }
        handler(bArr);
    }
}
