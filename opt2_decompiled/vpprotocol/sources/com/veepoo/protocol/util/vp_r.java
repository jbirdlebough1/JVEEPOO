package com.veepoo.protocol.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_r.class */
public class vp_r {
    private vp_a vp_a = null;
    private vp_a vp_b = null;
    private vp_a vp_c = null;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_r$vp_a.class */
    class vp_a {
        int vp_a;
        int vp_b;
        int[] vp_c;

        public vp_a(int i, int i2, int[] iArr) {
            this.vp_a = i;
            this.vp_b = i2;
            this.vp_c = iArr;
        }

        public int[] vp_a() {
            return this.vp_c;
        }

        public String toString() {
            return "EcgByteModel{all_length=" + this.vp_a + ", item_length=" + this.vp_b + ", ids=" + Arrays.toString(this.vp_c) + '}';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public vp_r(List list) {
        try {
            vp_a(vp_a(list));
        } catch (Exception unused) {
            printStackTrace();
        }
    }

    private byte[] vp_a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        return bArr2;
    }

    public int[] vp_c() {
        vp_a vp_aVar = this.vp_a;
        if (vp_aVar != null) {
            return vp_aVar.vp_a();
        }
        return null;
    }

    public int[] vp_b() {
        vp_a vp_aVar = this.vp_b;
        if (vp_aVar != null) {
            return vp_aVar.vp_a();
        }
        return null;
    }

    public int[] vp_a() {
        vp_a vp_aVar = this.vp_c;
        if (vp_aVar != null) {
            return vp_aVar.vp_a();
        }
        return null;
    }

    private vp_a vp_a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        int iTwoByteToUnsignedInt = VpBleByteUtil.twoByteToUnsignedInt(byteBuffer.get(), byteBuffer.get()) - 1;
        byte b = byteBuffer.get();
        int iPosition = byteBuffer.position();
        byteBuffer.position(iPosition + iTwoByteToUnsignedInt);
        return new vp_a(iTwoByteToUnsignedInt, b, vp_a(vp_a(bArr, iTwoByteToUnsignedInt, iPosition), b));
    }

    private byte[] vp_a(List list) {
        if (list.isEmpty()) {
            return new byte[0];
        }
        byte[] bArr = new byte[list.size() * 16];
        for (int i = 0; i < list.size(); i++) {
            byte[] bArr2 = (byte[]) list.get(i);
            for (int i2 = 4; i2 < bArr2.length; i2++) {
                bArr[((i * 16) + i2) - 4] = bArr2[i2];
            }
        }
        return bArr;
    }

    private int[] vp_a(byte[] bArr, int i) {
        int length = bArr.length / 2;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i2 * 2;
            iArr[i2] = VpBleByteUtil.twoByteToUnsignedInt(bArr[i3 + 1], bArr[i3]);
        }
        return iArr;
    }

    public void vp_a(byte[] bArr) {
        int length = bArr.length;
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        while (byteBufferOrder.position() < length) {
            byte b = byteBufferOrder.get();
            if (b == -94) {
                this.vp_a = vp_a(byteBufferOrder, "0xA2", bArr);
            } else if (b == -93) {
                this.vp_b = vp_a(byteBufferOrder, "0xA3", bArr);
            } else if (b == -91) {
                this.vp_c = vp_a(byteBufferOrder, "0xA5", bArr);
            }
        }
    }
}
