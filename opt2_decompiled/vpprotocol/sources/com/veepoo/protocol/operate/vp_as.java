package com.veepoo.protocol.operate;

import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.listener.data.IFunSwitchListener;
import com.veepoo.protocol.model.enums.EFunctionStatus;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/operate/vp_as.class */
public class vp_as extends com.veepoo.protocol.vp_a {
    public static byte vp_f = -95;
    private IFunSwitchListener vp_c;
    Map vp_d = new HashMap();
    private final List vp_e = new ArrayList();

    private byte[] vp_a(Map map) {
        int[] iArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(map.size());
        for (int i = 0; i < 17; i++) {
            EFunctionStatus eFunctionStatus = (EFunctionStatus) map.get(Integer.valueOf(iArr[i]));
            EFunctionStatus eFunctionStatus2 = eFunctionStatus;
            if (eFunctionStatus == null) {
                eFunctionStatus2 = EFunctionStatus.UNSUPPORT;
            }
            if (eFunctionStatus2 == EFunctionStatus.SUPPORT_OPEN) {
                byteBufferAllocate.put((byte) 1);
            } else if (eFunctionStatus2 == EFunctionStatus.SUPPORT_CLOSE) {
                byteBufferAllocate.put((byte) 2);
            } else {
                byteBufferAllocate.put((byte) 0);
            }
        }
        return byteBufferAllocate.array();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_x(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse) {
        super.vp_x(bluetoothClient, str, bleWriteResponse);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(20).order(ByteOrder.BIG_ENDIAN);
        byteBufferOrder.put((byte) -108);
        byteBufferOrder.put(vp_f);
        byteBufferOrder.put((byte) 2);
        byteBufferOrder.putInt(1081278805);
        byteBufferOrder.put((byte) 0);
        byteBufferOrder.put((byte) 0);
        send(byteBufferOrder.array(), bluetoothClient, str, bleWriteResponse);
    }

    @Override // com.veepoo.protocol.vp_a, com.veepoo.protocol.listener.base.IHandler
    public void handler(byte[] bArr) {
        super.handler(bArr);
        if (bArr.length >= 20 && bArr[3] == 85 && bArr[4] == 1 && bArr[5] == 115 && bArr[6] == 64) {
            byte b = bArr[8];
            boolean z = b == 1;
            boolean z2 = b == bArr[7];
            boolean z3 = z;
            int i = bArr[2] & 255;
            if (z3) {
                this.vp_e.clear();
            }
            boolean z4 = z2;
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 9, bArr.length);
            new StringBuilder().append("功能开关-=：").append(com.veepoo.protocol.util.vp_g.vp_d(bArrCopyOfRange));
            this.vp_e.add(bArrCopyOfRange);
            if (z4) {
                byte[] bArrVp_a = vp_a(this.vp_e);
                new StringBuilder().append("功能开关-=：").append(com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a));
                this.vp_d.clear();
                for (int i2 = 0; i2 < bArrVp_a.length; i2++) {
                    int i3 = bArrVp_a[i2] & 255;
                    if (i3 == 1) {
                        this.vp_d.put(Integer.valueOf(i2 + 1), EFunctionStatus.SUPPORT_OPEN);
                    } else if (i3 == 2) {
                        this.vp_d.put(Integer.valueOf(i2 + 1), EFunctionStatus.SUPPORT_CLOSE);
                    } else {
                        this.vp_d.put(Integer.valueOf(i2 + 1), EFunctionStatus.UNSUPPORT);
                    }
                }
                IFunSwitchListener iFunSwitchListener = this.vp_c;
                if (iFunSwitchListener != null) {
                    iFunSwitchListener.onFunSwitchStatusChanged(i, this.vp_d);
                }
            }
        }
    }

    private byte[] vp_a(int i, int i2, byte[] bArr) {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(bArr.length + 9).order(ByteOrder.BIG_ENDIAN);
        byteBufferOrder.put((byte) -108);
        byteBufferOrder.put(vp_f);
        byteBufferOrder.put((byte) 1);
        byteBufferOrder.putInt(1081278805);
        byteBufferOrder.put((byte) i);
        byteBufferOrder.put((byte) i2);
        byteBufferOrder.put(bArr);
        return byteBufferOrder.array();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(byte[] bArr, IListener iListener) {
        this.vp_c = (IFunSwitchListener) iListener;
        handler(bArr);
    }

    private byte[] vp_a(List list) {
        int length = 0;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            length += ((byte[]) it.next()).length;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            byte[] bArr = (byte[]) it2.next();
            byteBufferAllocate.put(bArr, 0, bArr.length);
        }
        return byteBufferAllocate.array();
    }

    @Override // com.veepoo.protocol.vp_a
    public void vp_a(BluetoothClient bluetoothClient, String str, BleWriteResponse bleWriteResponse, int i, EFunctionStatus eFunctionStatus) {
        super.vp_a(bluetoothClient, str, bleWriteResponse, i, eFunctionStatus);
        if (this.vp_d.size() == 0) {
            return;
        }
        this.vp_d.put(Integer.valueOf(i), eFunctionStatus);
        byte[] bArrVp_a = vp_a(this.vp_d);
        new StringBuilder().append("----- MTU = ").append(VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue());
        int mtuValue = VpSpGetUtil.getVpSpVariInstance(this.vp_a).getMtuValue();
        if (VpSpGetUtil.getVpSpVariInstance(this.vp_a).isZKDevice()) {
            mtuValue = 20;
        }
        List listVp_a = vp_a(bArrVp_a, mtuValue - 9);
        int size = listVp_a.size();
        int i2 = 0;
        while (i2 < listVp_a.size()) {
            int i3 = i2;
            i2 = i3 + 1;
            byte[] bArrVp_a2 = vp_a(size, i2, (byte[]) listVp_a.get(i3));
            new StringBuilder().append("功能开关设置-发送  => ").append(com.veepoo.protocol.util.vp_g.vp_d(bArrVp_a2));
            send(bArrVp_a2, bluetoothClient, str, bleWriteResponse);
        }
    }

    private List vp_a(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i2;
            int iMin = i3 + Math.min(i, bArr.length - i2);
            i2 = iMin;
            arrayList.add(Arrays.copyOfRange(bArr, i3, iMin));
        }
        return arrayList;
    }
}
