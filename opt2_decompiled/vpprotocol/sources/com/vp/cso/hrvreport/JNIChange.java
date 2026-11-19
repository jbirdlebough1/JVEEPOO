package com.vp.cso.hrvreport;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/vp/cso/hrvreport/JNIChange.class */
public class JNIChange {
    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();

    public native int[] hrvAnalysisReport(int[] iArr, int i);

    public native int ecgFilter(int[] iArr, int[] iArr2, int[] iArr3, int i, int i2, int i3, int i4, float f, int i5, int i6, int i7, int i8);
}
