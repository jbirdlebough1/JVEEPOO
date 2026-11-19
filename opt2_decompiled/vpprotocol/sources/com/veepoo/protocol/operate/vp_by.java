package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IPersonInfoDataListener;
import com.veepoo.protocol.model.datas.PersonInfoData;
import com.veepoo.protocol.model.enums.EOprateStauts;
import com.veepoo.protocol.model.enums.ESex;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_by.class */
public class vp_by extends com.veepoo.protocol.vp_a {
    IPersonInfoDataListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_by$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[ESex.values().length];
            vp_a = iArr;
            try {
                iArr[ESex.MAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[ESex.WOMEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private byte[] vp_a(PersonInfoData personInfoData) {
        byte[] bArr = new byte[20];
        bArr[0] = -93;
        bArr[1] = VpBleByteUtil.intToBytes(personInfoData.getHeight())[3];
        bArr[2] = VpBleByteUtil.intToBytes(personInfoData.getWeight())[3];
        bArr[3] = VpBleByteUtil.intToBytes(personInfoData.getAge())[3];
        bArr[4] = vp_a(personInfoData.getESex());
        bArr[5] = VpBleByteUtil.hiUint16((short) personInfoData.getStepAim());
        bArr[6] = VpBleByteUtil.loUint16((short) personInfoData.getStepAim());
        short sleepAim = (short) personInfoData.getSleepAim();
        bArr[7] = VpBleByteUtil.hiUint16(sleepAim);
        bArr[8] = VpBleByteUtil.loUint16(sleepAim);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length < 2) {
            this.vp_c.OnPersoninfoDataChange(EOprateStauts.UNKNOW);
            return;
        }
        byte b = bArr[1];
        if (b == 1) {
            this.vp_c.OnPersoninfoDataChange(EOprateStauts.OPRATE_SUCCESS);
        } else if (b == 0) {
            this.vp_c.OnPersoninfoDataChange(EOprateStauts.OPRATE_FAIL);
        } else {
            this.vp_c.OnPersoninfoDataChange(EOprateStauts.UNKNOW);
        }
    }

    private byte vp_a(ESex eSex) {
        byte b = 1;
        int i = vp_a.vp_a[eSex.ordinal()];
        if (i == 1) {
            b = 1;
        } else if (i == 2) {
            b = 0;
        }
        return b;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IPersonInfoDataListener) iListener;
        handler(bArr);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, PersonInfoData personInfoData) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, personInfoData);
        super.send(vp_a(personInfoData), bluetoothClient, str, bleWriteResponse);
    }
}
