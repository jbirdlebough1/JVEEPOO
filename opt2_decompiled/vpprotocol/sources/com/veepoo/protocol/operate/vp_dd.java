package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IWearHandConfigListener;
import com.veepoo.protocol.model.datas.WearHandConfigData;
import com.veepoo.protocol.model.enums.EWearHandMode;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dd.class */
public final class vp_dd extends com.veepoo.protocol.vp_a {
    public static final vp_a vp_c = new vp_a(null);

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_dd$vp_a.class */
    public static final class vp_a {
        private vp_a() {
        }

        public /* synthetic */ vp_a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final void vp_cd(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        byte[] bArrVp_a = vp_a(1, EWearHandMode.NONE);
        String str2 = "读取左右手佩戴配置：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }

    public final byte[] vp_a(int i, EWearHandMode eWearHandMode) {
        Intrinsics.checkNotNullParameter(eWearHandMode, "wearMode");
        byte[] bArr = new byte[20];
        bArr[0] = -30;
        bArr[1] = 5;
        bArr[2] = (byte) i;
        bArr[3] = (byte) eWearHandMode.getValue();
        return bArr;
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        super.vp_a(bArr, iListener);
        if (bArr == null) {
            return;
        }
        IWearHandConfigListener iWearHandConfigListener = iListener instanceof IWearHandConfigListener ? (IWearHandConfigListener) iListener : null;
        if (iWearHandConfigListener == null) {
            return;
        }
        if (bArr.length < 5) {
            String str = "左右手佩戴配置回复数据残帧：" + com.veepoo.protocol.util.vp_i.vp_a(bArr);
            return;
        }
        if (bArr[0] == -30 && bArr[1] == 5) {
            int i = bArr[2] & 255;
            int i2 = bArr[3] & 255;
            boolean z = i2 == 1;
            EWearHandMode eWearHandModeFromValue = EWearHandMode.INSTANCE.fromValue(bArr[4] & 255);
            EWearHandMode eWearHandMode = eWearHandModeFromValue;
            if (eWearHandModeFromValue == null) {
                eWearHandMode = EWearHandMode.NONE;
            }
            String str2 = "左右手佩戴配置回复：cmd=" + i + ", ack=" + i2 + ", wearMode=" + eWearHandMode.getDes();
            iWearHandConfigListener.onWearHandConfigResult(new WearHandConfigData(i, z, eWearHandMode));
        }
    }

    public final void vp_a(EWearHandMode eWearHandMode, BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        Intrinsics.checkNotNullParameter(eWearHandMode, "wearMode");
        byte[] bArrVp_a = vp_a(0, eWearHandMode);
        String str2 = "设置左右手佩戴配置：" + com.veepoo.protocol.util.vp_i.vp_a(bArrVp_a);
        send(bArrVp_a, bluetoothClient, str, bleWriteResponse);
    }
}
