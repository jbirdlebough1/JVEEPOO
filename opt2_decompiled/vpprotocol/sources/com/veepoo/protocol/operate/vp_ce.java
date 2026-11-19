package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IReadProductionInfoListener;
import com.veepoo.protocol.model.datas.ProductionInfo;
import com.veepoo.protocol.util.VpBleByteUtil;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_ce.class */
public final class vp_ce extends com.veepoo.protocol.vp_a {
    private IReadProductionInfoListener vp_c;

    @Override // com.veepoo.protocol.vp_a
    public void vp_af(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(bluetoothClient, "client");
        Intrinsics.checkNotNullParameter(str, "mac");
        super.vp_af(bluetoothClient, str, bleWriteResponse);
        byte[] bArr = com.veepoo.protocol.profile.vp_a.vp_be;
        String str2 = "读取生产信息：" + com.veepoo.protocol.util.vp_g.vp_d(bArr);
        send(bArr, bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        Intrinsics.checkNotNull(iListener, "null cannot be cast to non-null type com.veepoo.protocol.listener.data.IReadProductionInfoListener");
        this.vp_c = (IReadProductionInfoListener) iListener;
        if (bArr != null) {
            String[] strArrByte2HexToStrArr = VpBleByteUtil.byte2HexToStrArr(bArr);
            if (bArr.length == 20 && bArr[18] == 2) {
                ProductionInfo productionInfo = new ProductionInfo();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = strArrByte2HexToStrArr[9];
                Intrinsics.checkNotNullExpressionValue(str, "get(...)");
                Locale locale = Locale.ROOT;
                String lowerCase = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String str2 = strArrByte2HexToStrArr[10];
                Intrinsics.checkNotNullExpressionValue(str2, "get(...)");
                String lowerCase2 = str2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                String str3 = strArrByte2HexToStrArr[11];
                Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
                String lowerCase3 = str3.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                String str4 = strArrByte2HexToStrArr[12];
                Intrinsics.checkNotNullExpressionValue(str4, "get(...)");
                String lowerCase4 = str4.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                String str5 = strArrByte2HexToStrArr[13];
                Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
                String lowerCase5 = str5.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                String str6 = strArrByte2HexToStrArr[14];
                Intrinsics.checkNotNullExpressionValue(str6, "get(...)");
                String lowerCase6 = str6.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
                String str7 = String.format("%1s.%1s.%1s.%1s.%1s.%1s", Arrays.copyOf(new Object[]{lowerCase, lowerCase2, lowerCase3, lowerCase4, lowerCase5, lowerCase6}, 6));
                Intrinsics.checkNotNullExpressionValue(str7, "format(...)");
                productionInfo.setTpVersion(str7);
                IReadProductionInfoListener iReadProductionInfoListener = this.vp_c;
                if (iReadProductionInfoListener != null) {
                    iReadProductionInfoListener.onReadSuccess(productionInfo);
                }
            }
        }
    }
}
