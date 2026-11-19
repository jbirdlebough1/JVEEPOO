package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IWomenDataListener;
import com.veepoo.protocol.model.datas.TimeData;
import com.veepoo.protocol.model.datas.WomenData;
import com.veepoo.protocol.model.enums.ESex;
import com.veepoo.protocol.model.enums.EWomenOprateStatus;
import com.veepoo.protocol.model.enums.EWomenStatus;
import com.veepoo.protocol.model.settings.WomenSetting;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_df.class */
public class vp_df extends com.veepoo.protocol.vp_a {
    IWomenDataListener vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_df$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;
        static final /* synthetic */ int[] vp_b;

        static {
            int[] iArr = new int[EWomenStatus.values().length];
            vp_b = iArr;
            try {
                iArr[EWomenStatus.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_b[EWomenStatus.MENES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_b[EWomenStatus.PREREADY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_b[EWomenStatus.PREING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_b[EWomenStatus.MAMAMI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[ESex.values().length];
            vp_a = iArr2;
            try {
                iArr2[ESex.WOMEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[ESex.MAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:17:0x00d7  */
    private WomenSetting vp_c(byte[] bArr) {
        ESex eSex;
        int womenTag = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getWomenTag();
        WomenSetting womenSetting = new WomenSetting();
        if (womenTag == 3) {
            womenSetting.setWomenStatus(vp_a(bArr[4]));
            womenSetting.setMenesInterval(bArr[8] & 255);
            womenSetting.setMenseLength(bArr[10] & 255);
            if (womenSetting.getWomenStatus() == EWomenStatus.MENES || womenSetting.getWomenStatus() == EWomenStatus.PREREADY) {
                womenSetting.setMenesLasterday(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[5], bArr[3]), bArr[6] & 255, bArr[7] & 255));
            } else if (womenSetting.getWomenStatus() == EWomenStatus.PREING) {
                womenSetting.setConfinementDay(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[5], bArr[3]), bArr[6] & 255, bArr[7] & 255));
            } else if (womenSetting.getWomenStatus() == EWomenStatus.MAMAMI) {
                womenSetting.setBabyBirthday(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[5], bArr[3]), bArr[6] & 255, bArr[7] & 255));
                if (bArr[15] == 1) {
                    eSex = ESex.MAN;
                } else {
                    eSex = ESex.WOMEN;
                }
                womenSetting.setBabySex(eSex);
            }
        } else {
            womenSetting.setWomenStatus(vp_a(bArr[3]));
            womenSetting.setMenesInterval(bArr[8] & 255);
            womenSetting.setMenseLength(bArr[9] & 255);
            if (womenSetting.getWomenStatus() == EWomenStatus.MENES || womenSetting.getWomenStatus() == EWomenStatus.PREREADY) {
                womenSetting.setMenesLasterday(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[5]), bArr[6] & 255, bArr[7] & 255));
            } else if (womenSetting.getWomenStatus() == EWomenStatus.PREING) {
                womenSetting.setConfinementDay(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[5]), bArr[6] & 255, bArr[7] & 255));
            } else if (womenSetting.getWomenStatus() == EWomenStatus.MAMAMI) {
                womenSetting.setBabyBirthday(new TimeData(VpBleByteUtil.twoByteToUnsignedInt(bArr[4], bArr[5]), bArr[6] & 255, bArr[7] & 255));
                if (bArr[14] == 1) {
                    eSex = ESex.MAN;
                } else {
                    eSex = ESex.WOMEN;
                }
                womenSetting.setBabySex(eSex);
            }
        }
        return womenSetting;
    }

    private WomenData vp_b(byte[] bArr) {
        WomenData womenData = new WomenData(EWomenOprateStatus.UNKONW);
        if (bArr.length < 19) {
            throw new IllegalArgumentException("返回的女性数据有误，不足20个字节 ");
        }
        womenData.setOprateStatus(vp_a(bArr[1], bArr[2]));
        return womenData;
    }

    private byte[] vp_a(TimeData timeData) {
        if (!com.veepoo.protocol.util.vp_j.vp_b(timeData)) {
            throw new IllegalArgumentException("日期不合法！");
        }
        int year = timeData.getYear();
        int month = timeData.getMonth();
        int day = timeData.getDay();
        String str = "0" + Integer.toHexString(year);
        return new byte[]{VpBleByteUtil.HexStringToBinary(str.substring(0, 2))[0], VpBleByteUtil.HexStringToBinary(str.substring(2, 4))[0], VpBleByteUtil.loUint16((short) month), VpBleByteUtil.loUint16((short) day)};
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_at(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_at(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    private EWomenOprateStatus vp_a(byte b, byte b2) {
        if ((b == 0 || b == 1) && !vp_b(b2)) {
            if (b == 1) {
                return b2 == 5 ? EWomenOprateStatus.READ_SUCCESS : EWomenOprateStatus.SETTING_SUCCESS;
            }
            return b2 == 5 ? EWomenOprateStatus.READ_FAIL : EWomenOprateStatus.SETTING_FAIL;
        }
        return EWomenOprateStatus.UNKONW;
    }

    private boolean vp_b(byte b) {
        return (b == 0 || b == 1 || b == 3 || b == 2 || b == 4 || b == 5) ? false : true;
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -123;
        bArr[1] = 5;
        return bArr;
    }

    private byte[] vp_a(WomenSetting womenSetting) {
        byte[] bArr = new byte[20];
        bArr[0] = -123;
        int menesInterval = womenSetting.getMenesInterval();
        int menseLength = womenSetting.getMenseLength();
        TimeData menesLasterday = womenSetting.getMenesLasterday();
        TimeData confinementDay = womenSetting.getConfinementDay();
        TimeData babyBirthday = womenSetting.getBabyBirthday();
        EWomenStatus womenStatus = womenSetting.getWomenStatus();
        ESex babySex = womenSetting.getBabySex();
        bArr[1] = vp_a(womenStatus);
        if (womenStatus == EWomenStatus.NONE) {
            return bArr;
        }
        EWomenStatus eWomenStatus = EWomenStatus.PREING;
        if (womenStatus == eWomenStatus) {
            menesLasterday = confinementDay;
        }
        if (menesLasterday == null) {
            throw new NullPointerException("日期设置为空");
        }
        byte[] bArrVp_a = vp_a(menesLasterday);
        bArr[2] = bArrVp_a[0];
        bArr[3] = bArrVp_a[1];
        bArr[4] = bArrVp_a[2];
        bArr[5] = bArrVp_a[3];
        if (womenStatus == eWomenStatus) {
            return bArr;
        }
        bArr[6] = VpBleByteUtil.loUint16((short) menesInterval);
        bArr[7] = VpBleByteUtil.loUint16((short) menseLength);
        if (womenStatus == EWomenStatus.MENES || womenStatus == EWomenStatus.PREREADY) {
            return bArr;
        }
        if (babyBirthday == null) {
            throw new NullPointerException("baby日期设置为空");
        }
        byte[] bArrVp_a2 = vp_a(babyBirthday);
        bArr[8] = bArrVp_a2[0];
        bArr[9] = bArrVp_a2[1];
        bArr[10] = bArrVp_a2[2];
        bArr[11] = bArrVp_a2[3];
        bArr[12] = vp_a(babySex);
        return bArr;
    }

    private byte vp_a(ESex eSex) {
        byte b = 0;
        int i = vp_a.vp_a[eSex.ordinal()];
        if (i == 1) {
            b = 2;
        } else if (i == 2) {
            b = 1;
        }
        return b;
    }

    private byte vp_a(EWomenStatus eWomenStatus) {
        byte b = 0;
        switch (vp_a.vp_b[eWomenStatus.ordinal()]) {
            case 1:
                b = 0;
                break;
            case 2:
                b = 1;
                break;
            case 3:
                b = 2;
                break;
            case 4:
                b = 3;
                break;
            case 5:
                b = 4;
                break;
        }
        return b;
    }

    private EWomenStatus vp_a(byte b) {
        EWomenStatus eWomenStatus = EWomenStatus.NONE;
        switch (b) {
            case 1:
                eWomenStatus = EWomenStatus.MENES;
                break;
            case 2:
                eWomenStatus = EWomenStatus.PREREADY;
                break;
            case 3:
                eWomenStatus = EWomenStatus.PREING;
                break;
            case 4:
                eWomenStatus = EWomenStatus.MAMAMI;
                break;
        }
        return eWomenStatus;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        this.vp_c = (IWomenDataListener) iListener;
        WomenData womenDataVp_b = vp_b(bArr);
        if (womenDataVp_b.getOprateStatus() == EWomenOprateStatus.READ_SUCCESS) {
            womenDataVp_b.setWomenSetting(vp_c(bArr));
        }
        this.vp_c.onWomenDataChange(womenDataVp_b);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, WomenSetting womenSetting) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, womenSetting);
        super.send(vp_a(womenSetting), bluetoothClient, str, bleWriteResponse);
    }
}
