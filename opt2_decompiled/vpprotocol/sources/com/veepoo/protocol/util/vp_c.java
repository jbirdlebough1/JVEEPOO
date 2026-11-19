package com.veepoo.protocol.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_c.class */
public abstract class vp_c {
    private static byte[] vp_b(Bitmap bitmap) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        byte[] bArrArray = byteBufferAllocate.array();
        vp_a(bArrArray);
        if (bArrArray.length % 4 == 0) {
            return bArrArray;
        }
        int length = ((bArrArray.length / 4) + 1) * 4;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = -1;
        }
        System.arraycopy(bArrArray, 0, bArr, 0, bArrArray.length);
        return bArr;
    }

    private static void vp_a(byte[] bArr) {
        int length = bArr.length / 2;
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            byte b = bArr[i3];
            bArr[i3] = bArr[i2];
            bArr[i2] = b;
        }
    }

    public static byte[] vp_b(byte[] bArr) {
        if (bArr.length % 4 != 0) {
            return new byte[0];
        }
        for (int i = 0; i < bArr.length / 4; i++) {
            vp_a(bArr, i);
        }
        return bArr;
    }

    private static Bitmap vp_a(Bitmap bitmap) {
        return bitmap.copy(Bitmap.Config.RGB_565, false);
    }

    private static Bitmap vp_a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, int i4, int i5) {
        Bitmap bitmapVp_a = vp_a(bitmap2, i, i2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, bitmapVp_a.getConfig());
        Bitmap bitmapVp_a2 = vp_a(bitmap, i3, i4);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        float f = i5;
        canvas.drawBitmap(bitmapVp_a2, f, f, (Paint) null);
        canvas.drawBitmap(bitmapVp_a, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    public static InputStream vp_a(Bitmap bitmap, Bitmap bitmap2, int i, int i2, int i3, int i4, int i5, boolean z) {
        byte[] bArrVp_b = vp_b(vp_a(bitmap));
        byte[] bArrVp_b2 = vp_b(vp_a(bitmap, bitmap2, i, i2, i3, i4, i5).copy(Bitmap.Config.RGB_565, false));
        if (z) {
            bArrVp_b = vp_b(bArrVp_b);
            bArrVp_b2 = vp_b(bArrVp_b2);
        }
        return new ByteArrayInputStream(vp_a(bArrVp_b, bArrVp_b2));
    }

    private static Bitmap vp_a(Bitmap bitmap, int i, int i2) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }

    public static byte[] vp_a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private static void vp_a(byte[] bArr, int i) {
        int i2 = i * 4;
        byte b = bArr[i2];
        int i3 = i2 + 1;
        byte b2 = bArr[i3];
        int i4 = i2 + 2;
        byte b3 = bArr[i4];
        int i5 = i2 + 3;
        bArr[i2] = bArr[i5];
        bArr[i3] = b3;
        bArr[i4] = b2;
        bArr[i5] = b;
    }
}
