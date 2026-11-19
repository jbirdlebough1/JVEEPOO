package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IScreenStyleListener;
import com.veepoo.protocol.model.datas.ScreenStyleData;
import com.veepoo.protocol.model.enums.EScreenStyle;
import com.veepoo.protocol.model.enums.EUIFromType;
import com.veepoo.protocol.shareprence.SpUtil;
import com.veepoo.protocol.shareprence.SputilVari;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cj.class */
public class vp_cj extends com.veepoo.protocol.vp_a {
    List vp_c = new ArrayList();

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_cj$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EUIFromType.values().length];
            vp_a = iArr;
            try {
                iArr[EUIFromType.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EUIFromType.SERVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EUIFromType.CUSTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private ScreenStyleData vp_b(byte[] bArr) {
        ScreenStyleData screenStyleData = new ScreenStyleData();
        if (bArr.length < 4) {
            return screenStyleData;
        }
        EScreenStyle eScreenStyleVp_c = vp_c(bArr);
        EUIFromType eUIFromTypeVp_a = vp_a(bArr[4]);
        int[] iArrVp_d = vp_d(bArr);
        int i = VpBleByteUtil.byte2HexToIntArr(bArr)[3];
        int i2 = VpBleByteUtil.byte2HexToIntArr(bArr)[1];
        int i3 = SpUtil.getInt(this.vp_a, SputilVari.COUNT_SCREEN_STYLE_TYPE, 0);
        int i4 = SpUtil.getInt(this.vp_a, SputilVari.COUNT_WATCHUI_COUSTOM, 1);
        if (i2 == 2 && i >= i3) {
            eUIFromTypeVp_a = i < i3 + i4 ? EUIFromType.CUSTOM : EUIFromType.SERVER;
        }
        screenStyleData.setScreenIndex(i);
        screenStyleData.setStatus(eScreenStyleVp_c);
        screenStyleData.setScreenType(eUIFromTypeVp_a);
        screenStyleData.setWatchIDArr(iArrVp_d);
        return screenStyleData;
    }

    private int[] vp_d(byte[] bArr) {
        int[] iArr = new int[7];
        int length = bArr.length;
        for (int i = 0; i < 7; i++) {
            int i2 = i * 2;
            int i3 = i2 + 6;
            if (length > i3) {
                iArr[i] = (bArr[i2 + 5] & 255) | ((bArr[i3] << 8) & 65280);
            }
        }
        return iArr;
    }

    private EUIFromType vp_a(int i) {
        if (i == 0) {
            return EUIFromType.DEFAULT;
        }
        if (i == 1) {
            return EUIFromType.SERVER;
        }
        return i == 2 ? EUIFromType.CUSTOM : EUIFromType.DEFAULT;
    }

    private EScreenStyle vp_c(byte[] bArr) {
        if (bArr.length < 9) {
            return EScreenStyle.UNKONW;
        }
        byte b = bArr[1];
        byte b2 = bArr[2];
        if (b == 1) {
            return b2 == 1 ? EScreenStyle.SETTING_SUCCESS : EScreenStyle.SETTING_FAIL;
        }
        if (b == 2) {
            return b2 == 1 ? EScreenStyle.READ_SUCCESS : EScreenStyle.READ_FAIL;
        }
        return EScreenStyle.UNKONW;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_al(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_al(bluetoothClient, str, bleWriteResponse);
        super.send(vp_a(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        vp_c(bArr);
    }

    private byte[] vp_a() {
        byte[] bArr = new byte[20];
        bArr[0] = -57;
        bArr[1] = 2;
        return bArr;
    }

    private ScreenStyleData vp_b() {
        ScreenStyleData screenStyleData = new ScreenStyleData();
        if (this.vp_c.isEmpty()) {
            return screenStyleData;
        }
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.vp_c.size(); i2++) {
            byte[] bArr = (byte[]) this.vp_c.get(i2);
            int length = bArr.length - 4;
            byte[] bArr2 = new byte[length];
            if (bArr.length - 4 >= 0) {
                System.arraycopy(bArr, 4, bArr2, 0, bArr.length - 4);
            }
            arrayList.add(bArr2);
            i += length;
        }
        byte[] bArr3 = new byte[i];
        int length2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            byte[] bArr4 = (byte[]) arrayList.get(i3);
            System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
            length2 += bArr4.length;
        }
        byte b = bArr3[2];
        byte b2 = bArr3[3];
        int i4 = bArr3[5] / 2;
        int[] iArr = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 2;
            iArr[i5] = com.veepoo.protocol.util.vp_g.vp_b(bArr3[i6 + 7], bArr3[6 + i6]);
        }
        EUIFromType eUIFromTypeVp_a = vp_a(b2);
        screenStyleData.setStatus(EScreenStyle.READ_SUCCESS);
        screenStyleData.setScreenIndex(b);
        screenStyleData.setScreenType(eUIFromTypeVp_a);
        screenStyleData.setWatchIDArr(iArr);
        return screenStyleData;
    }

    private byte[] vp_a(int i, int i2) {
        byte[] bArr = new byte[20];
        bArr[0] = -57;
        bArr[1] = 1;
        bArr[2] = VpBleByteUtil.loUint16((short) i);
        bArr[3] = VpBleByteUtil.loUint16((short) i2);
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        IScreenStyleListener iScreenStyleListener = (IScreenStyleListener) iListener;
        if (bArr[1] != 3) {
            iScreenStyleListener.onScreenStyleDataChange(vp_b(bArr));
            return;
        }
        byte b = bArr[2];
        byte b2 = bArr[3];
        if (b == 1) {
            this.vp_c.clear();
        }
        this.vp_c.add(bArr);
        if (b == b2) {
            iScreenStyleListener.onScreenStyleDataChange(vp_b());
        }
    }

    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, EUIFromType eUIFromType) {
        super.vp_e(bluetoothClient, str, bleWriteResponse, i);
        byte[] bArrVp_a = null;
        int i2 = vp_a.vp_a[eUIFromType.ordinal()];
        if (i2 == 1) {
            bArrVp_a = vp_a(i, 0);
        } else if (i2 == 2) {
            bArrVp_a = vp_a(i, 1);
        } else if (i2 == 3) {
            bArrVp_a = vp_a(i, 2);
        }
        super.send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
