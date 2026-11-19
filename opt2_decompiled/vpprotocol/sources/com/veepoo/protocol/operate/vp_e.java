package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IBPSettingDataListener;
import com.veepoo.protocol.model.datas.BpSettingData;
import com.veepoo.protocol.model.enums.EBPDetectModel;
import com.veepoo.protocol.model.enums.EBPStatus;
import com.veepoo.protocol.model.settings.BpSetting;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_e.class */
public class vp_e extends com.veepoo.protocol.vp_a {
    IBPSettingDataListener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_u(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_u(bluetoothClient, str, bleWriteResponse);
        super.send(com.veepoo.protocol.profile.vp_a.vp_ae, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BpSetting bpSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, bpSetting);
        super.send(vp_a(bpSetting), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        this.vp_c.onDataChange(vp_b(bArr));
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:47:0x010a  */
    /* JADX WARN: Code duplicated, block: B:50:0x0118  */
    /* JADX WARN: Code duplicated, block: B:53:0x0126  */
    public BpSettingData vp_b(byte[] bArr) {
        EBPStatus eBPStatus = EBPStatus.UNKONW;
        BpSettingData bpSettingData = new BpSettingData(eBPStatus, EBPDetectModel.DETECT_MODEL_PUBLIC, 0, 0);
        if (bArr.length < 5) {
            bpSettingData.setStatus(eBPStatus);
            return bpSettingData;
        }
        int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
        int i = iArrByte2HexToIntArr[2];
        int i2 = iArrByte2HexToIntArr[3];
        byte b = bArr[1];
        byte b2 = bArr[4];
        byte b3 = bArr[5];
        if (bArr.length >= 20) {
            int i3 = VpBleByteUtil.byte2HexToIntArr(bArr)[6];
            boolean z = bArr[7] != 0;
            bpSettingData.setAngioAdjusterProgress(i3);
            bpSettingData.setAngioAdjuster(z);
        }
        bpSettingData.setHighPressure(i);
        bpSettingData.setLowPressure(i2);
        bpSettingData.setModel(vp_a(b2));
        if (b3 != 1 && b3 != 0 && b3 != 3 && b3 != 2) {
            bpSettingData.setStatus(eBPStatus);
            return bpSettingData;
        }
        if (b != 1 && b != 0 && b != 6 && b != 7 && b != 8 && b != 9) {
            bpSettingData.setStatus(eBPStatus);
            return bpSettingData;
        }
        if (b3 == 0) {
            if (b == 1) {
                eBPStatus = EBPStatus.SETTING_NORMAL_SUCCESS;
            } else if (b == 6) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_ING;
            } else if (b == 7) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_FAIL;
            } else if (b == 8) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_SUCCESS;
            } else if (b == 9) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_DEVICE_BUSY;
            } else {
                eBPStatus = EBPStatus.SETTING_NORMAL_FAIL;
            }
        } else if (b3 == 1) {
            if (b == 1) {
                eBPStatus = EBPStatus.SETTING_PRIVATE_SUCCESS;
            } else if (b == 6) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_ING;
            } else if (b == 7) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_FAIL;
            } else if (b == 8) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_SUCCESS;
            } else if (b == 9) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_DEVICE_BUSY;
            } else {
                eBPStatus = EBPStatus.SETTING_PRIVATE_FAIL;
            }
        } else if (b3 == 2) {
            if (b == 1) {
                eBPStatus = EBPStatus.READ_SUCCESS;
            } else if (b == 6) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_ING;
            } else if (b == 7) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_FAIL;
            } else if (b == 8) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_SUCCESS;
            } else if (b == 9) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_DEVICE_BUSY;
            } else {
                eBPStatus = EBPStatus.READ_FAIL;
            }
        } else if (b3 == 3) {
            if (b == 1) {
                eBPStatus = EBPStatus.CANCLE_ANGIO_ADJUSTER_SUCCESS;
            } else if (b == 6) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_ING;
            } else if (b == 7) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_FAIL;
            } else if (b == 8) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_SUCCESS;
            } else if (b == 9) {
                eBPStatus = EBPStatus.ANGIO_ADJUSTER_DEVICE_BUSY;
            } else {
                eBPStatus = EBPStatus.CANCLE_ANGIO_ADJUSTER_FAIL;
            }
        }
        bpSettingData.setStatus(eBPStatus);
        return bpSettingData;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] vp_a(BpSetting bpSetting) {
        byte bLoUint16 = VpBleByteUtil.loUint16((short) bpSetting.getHigh());
        byte bLoUint17 = VpBleByteUtil.loUint16((short) bpSetting.getLow());
        boolean zIsangiocheck = bpSetting.isangiocheck();
        byte[] bArr = new byte[20];
        bArr[0] = -111;
        bArr[1] = 3;
        bArr[2] = bLoUint16;
        bArr[3] = bLoUint17;
        bArr[4] = zIsangiocheck;
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] vp_b(BpSetting bpSetting) {
        byte bLoUint16 = VpBleByteUtil.loUint16((short) bpSetting.getHigh());
        byte bLoUint17 = VpBleByteUtil.loUint16((short) bpSetting.getLow());
        byte bVp_a = vp_a(bpSetting.isOpenPrivateModel());
        boolean zIsangiocheck = bpSetting.isangiocheck();
        byte[] bArr = new byte[20];
        bArr[0] = -111;
        bArr[1] = bVp_a;
        bArr[2] = bLoUint16;
        bArr[3] = bLoUint17;
        bArr[4] = zIsangiocheck;
        return bArr;
    }

    private EBPDetectModel vp_a(byte b) {
        if (b != 0 && b == 1) {
            return EBPDetectModel.DETECT_MODEL_PRIVATE;
        }
        return EBPDetectModel.DETECT_MODEL_PUBLIC;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_b(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, BpSetting bpSetting) {
        super.vp_b(bluetoothClient, str, bleWriteResponse, bpSetting);
        super.send(vp_b(bpSetting), bluetoothClient, str, bleWriteResponse);
    }

    private byte vp_a(boolean z) {
        return z ? (byte) 1 : (byte) 0;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IBPSettingDataListener) iListener;
        handler(bArr);
    }
}
