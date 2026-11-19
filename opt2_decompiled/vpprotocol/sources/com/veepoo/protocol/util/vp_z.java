package com.veepoo.protocol.util;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_z.class */
public class vp_z {
    private byte[] vp_a;
    private int vp_b;
    private int vp_c;

    public vp_z(byte[] bArr, int i) {
        this.vp_a = bArr;
        this.vp_b = i;
        vp_a();
    }

    public void vp_a() {
        byte[] bArr = this.vp_a;
        if (bArr == null || bArr.length == 0) {
            return;
        }
        int length = bArr.length;
        int i = this.vp_b;
        if (length % i == 0) {
            this.vp_c = length / i;
        } else {
            this.vp_c = (length / i) + 1;
        }
    }

    public int vp_b() {
        return this.vp_c;
    }

    public byte[] vp_a(int i) {
        if (i < this.vp_c) {
            return vp_a(this.vp_a, i, this.vp_b);
        }
        return null;
    }

    public byte[] vp_a(byte[] bArr, int i, int i2) {
        if (bArr.length < i2) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        int i3 = i * i2;
        if (i2 + i3 > bArr.length) {
            int length = bArr.length - i3;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, i3, bArr3, 0, length);
            bArr2 = bArr3;
        } else {
            System.arraycopy(bArr, i3, bArr2, 0, i2);
        }
        return bArr2;
    }
}
