package com.veepoo.protocol.operate;

import com.veepoo.protocol.listener.data.IDeviceControlPhoneModelState;
import com.veepoo.protocol.util.VpBleByteUtil;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_o.class */
public class vp_o {
    private void vp_c(IDeviceControlPhoneModelState iDeviceControlPhoneModelState, int[] iArr) {
        int i = iArr[3];
        if (i == 1) {
            iDeviceControlPhoneModelState.inPttModel();
        } else if (i == 2) {
            iDeviceControlPhoneModelState.outPttModel();
        }
    }

    private void vp_b(IDeviceControlPhoneModelState iDeviceControlPhoneModelState, int[] iArr) {
        if (vp_a(iArr)) {
            iDeviceControlPhoneModelState.nextMusic();
        } else if (vp_c(iArr)) {
            iDeviceControlPhoneModelState.previousMusic();
        } else if (vp_b(iArr)) {
            iDeviceControlPhoneModelState.pauseAndPlayMusic();
        }
    }

    public void vp_a(String str, byte[] bArr, IDeviceControlPhoneModelState iDeviceControlPhoneModelState) {
        if (bArr.length < 20) {
        }
        str.hashCode();
        switch (str) {
            case "knock_notify_oprate":
                if (vp_b(bArr)) {
                    iDeviceControlPhoneModelState.knocknotify(VpBleByteUtil.byte2HexToIntArr(bArr)[2]);
                    break;
                }
                break;
            case "sos_oprate":
                if (vp_d(bArr)) {
                    iDeviceControlPhoneModelState.sos();
                    break;
                }
                break;
            case "auto_callback_oprate":
                int[] iArrByte2HexToIntArr = VpBleByteUtil.byte2HexToIntArr(bArr);
                int i = iArrByte2HexToIntArr[1];
                if (i == 1) {
                    vp_b(iDeviceControlPhoneModelState, iArrByte2HexToIntArr);
                    break;
                } else if (i == 2) {
                    vp_c(iDeviceControlPhoneModelState, iArrByte2HexToIntArr);
                    break;
                } else if (i == 3) {
                    vp_a(iDeviceControlPhoneModelState, iArrByte2HexToIntArr);
                    break;
                }
                break;
            case "pnone_message":
                if (vp_c(bArr)) {
                    iDeviceControlPhoneModelState.rejectPhone();
                    break;
                } else {
                    if (vp_e(bArr)) {
                        iDeviceControlPhoneModelState.cliencePhone();
                    } else if (vp_a(bArr)) {
                        iDeviceControlPhoneModelState.appAnswerCall();
                    }
                    break;
                }
                break;
        }
    }

    public boolean vp_e(byte[] bArr) {
        return bArr[1] == 3;
    }

    public boolean vp_d(byte[] bArr) {
        byte b = bArr[1];
        return (b == 1 && bArr[2] == 1) || b == 4;
    }

    private void vp_a(IDeviceControlPhoneModelState iDeviceControlPhoneModelState, int[] iArr) {
        int i = iArr[3];
        if (i == 1) {
            iDeviceControlPhoneModelState.inMultiEcgModel();
        } else if (i == 2) {
            iDeviceControlPhoneModelState.outMultiEcgModel();
        }
    }

    public boolean vp_b(byte[] bArr) {
        return bArr[1] == 1;
    }

    public boolean vp_c(int[] iArr) {
        return iArr[2] == 1 && iArr[3] == 4;
    }

    public boolean vp_c(byte[] bArr) {
        boolean z = false;
        if (bArr[1] == 2) {
            z = true;
            for (int i = 2; i <= 19; i++) {
                if (bArr[i] != 0) {
                    z = false;
                    break;
                }
            }
        }
        return z;
    }

    public boolean vp_a(byte[] bArr) {
        return bArr[1] == 4;
    }

    public boolean vp_b(int[] iArr) {
        return iArr[2] == 1 && iArr[3] == 2;
    }

    public boolean vp_a(int[] iArr) {
        return iArr[2] == 1 && iArr[3] == 1;
    }
}
