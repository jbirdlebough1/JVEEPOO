package com.veepoo.protocol.model.datas;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PPGSecondData.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0003H\u0002J\u0018\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0003H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/veepoo/protocol/model/datas/PPGSecondData;", "", "contentLength", "", "greenRawData", "", "accRawData", "<init>", "(I[B[B)V", "getContentLength", "()I", "getGreenRawData", "()[B", "getAccRawData", "greenLightSignalList", "", "getGreenLightSignalList", "()Ljava/util/List;", "accelerationList", "Lcom/veepoo/protocol/model/datas/AccelerationData;", "getAccelerationList", "toDataStr", "", "toString", "parseGreenLightData", "", "parseAccelerationData", "parseSignedLittleEndianInt3", "data", "offset", "parseSignedLittleEndianInt2", "vpprotocol_release"})
public final class PPGSecondData {
    private final int contentLength;

    @NotNull
    private final byte[] greenRawData;

    @NotNull
    private final byte[] accRawData;

    @NotNull
    private final List<Integer> greenLightSignalList;

    @NotNull
    private final List<AccelerationData> accelerationList;

    public PPGSecondData(int i, @NotNull byte[] bArr, @NotNull byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, "greenRawData");
        Intrinsics.checkNotNullParameter(bArr2, "accRawData");
        this.contentLength = i;
        this.greenRawData = bArr;
        this.accRawData = bArr2;
        ArrayList arrayList = new ArrayList();
        this.greenLightSignalList = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.accelerationList = arrayList2;
        arrayList.clear();
        arrayList2.clear();
        parseGreenLightData();
        parseAccelerationData();
    }

    private final void parseGreenLightData() {
        byte[] bArr = this.greenRawData;
        if (bArr.length != 300) {
            return;
        }
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, bArr.length - 1, 3);
        if (progressionLastElement < 0) {
            return;
        }
        while (true) {
            int signedLittleEndianInt3 = parseSignedLittleEndianInt3(this.greenRawData, i);
            if (signedLittleEndianInt3 != 8421504) {
                this.greenLightSignalList.add(Integer.valueOf(signedLittleEndianInt3));
            }
            if (i == progressionLastElement) {
                return;
            } else {
                i += 3;
            }
        }
    }

    private final void parseAccelerationData() {
        byte[] bArr = this.accRawData;
        if (bArr.length != 300) {
            return;
        }
        int i = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, bArr.length - 1, 6);
        if (progressionLastElement < 0) {
            return;
        }
        while (true) {
            int i2 = i;
            this.accelerationList.add(new AccelerationData(parseSignedLittleEndianInt2(this.accRawData, i), parseSignedLittleEndianInt2(this.accRawData, i + 2), parseSignedLittleEndianInt2(this.accRawData, i + 4)));
            if (i2 == progressionLastElement) {
                return;
            } else {
                i += 6;
            }
        }
    }

    private final int parseSignedLittleEndianInt3(byte[] data, int offset) {
        int i = data[offset + 2] & 255;
        int i2 = data[offset + 1] & 255;
        return (i << 16) | (i2 << 8) | (data[offset] & 255);
    }

    private final int parseSignedLittleEndianInt2(byte[] data, int offset) {
        return (data[offset + 1] << 8) | (data[offset] & 255);
    }

    public final int getContentLength() {
        return this.contentLength;
    }

    @NotNull
    public final byte[] getGreenRawData() {
        return this.greenRawData;
    }

    @NotNull
    public final byte[] getAccRawData() {
        return this.accRawData;
    }

    @NotNull
    public final List<Integer> getGreenLightSignalList() {
        return this.greenLightSignalList;
    }

    @NotNull
    public final List<AccelerationData> getAccelerationList() {
        return this.accelerationList;
    }

    @NotNull
    public final String toDataStr() {
        return "PPG数据:" + this.greenLightSignalList + "\n加速度数据:" + this.accelerationList;
    }

    @NotNull
    public String toString() {
        return "每秒PPG数据：总长=" + this.contentLength + " 绿光原始信号=" + this.greenLightSignalList.size() + "个 加速度原始信号=" + this.accelerationList.size() + "个";
    }
}
