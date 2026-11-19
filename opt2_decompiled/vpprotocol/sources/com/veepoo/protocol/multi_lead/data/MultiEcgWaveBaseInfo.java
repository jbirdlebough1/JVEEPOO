package com.veepoo.protocol.multi_lead.data;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/multi_lead/data/MultiEcgWaveBaseInfo.class */
public class MultiEcgWaveBaseInfo {
    private int offset = 0;
    private int[] ecgI;
    private int[] ecgII;
    private int[] ecgIII;
    private int[] ecgAVR;
    private int[] ecgAVL;
    private int[] ecgAVF;
    private int[] power;
    private EcgWaveformsInfo waveformsInfo;

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public int[] getEcgI() {
        return this.ecgI;
    }

    public void setEcgI(int[] iArr) {
        this.ecgI = iArr;
    }

    public int[] getEcgII() {
        return this.ecgII;
    }

    public void setEcgII(int[] iArr) {
        this.ecgII = iArr;
    }

    public int[] getEcgIII() {
        return this.ecgIII;
    }

    public void setEcgIII(int[] iArr) {
        this.ecgIII = iArr;
    }

    public int[] getEcgAVR() {
        return this.ecgAVR;
    }

    public void setEcgAVR(int[] iArr) {
        this.ecgAVR = iArr;
    }

    public int[] getEcgAVL() {
        return this.ecgAVL;
    }

    public void setEcgAVL(int[] iArr) {
        this.ecgAVL = iArr;
    }

    public int[] getEcgAVF() {
        return this.ecgAVF;
    }

    public void setEcgAVF(int[] iArr) {
        this.ecgAVF = iArr;
    }

    public EcgWaveformsInfo getWaveformsInfo() {
        return this.waveformsInfo;
    }

    public void setWaveformsInfo(EcgWaveformsInfo ecgWaveformsInfo) {
        this.waveformsInfo = ecgWaveformsInfo;
    }

    public int[] getPower() {
        return this.power;
    }

    public void setPower(int[] iArr) {
        this.power = iArr;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder().append("MultiEcgWaveBaseInfo{offset=").append(this.offset).append(", ecgI=");
        int[] iArr = this.ecgI;
        StringBuilder sbAppend2 = sbAppend.append(iArr == null ? "null" : Integer.valueOf(iArr.length)).append(", ecgII=");
        int[] iArr2 = this.ecgII;
        StringBuilder sbAppend3 = sbAppend2.append(iArr2 == null ? "null" : Integer.valueOf(iArr2.length)).append(", ecgIII=");
        int[] iArr3 = this.ecgIII;
        StringBuilder sbAppend4 = sbAppend3.append(iArr3 == null ? "null" : Integer.valueOf(iArr3.length)).append(", ecgAVR=");
        int[] iArr4 = this.ecgAVR;
        StringBuilder sbAppend5 = sbAppend4.append(iArr4 == null ? "null" : Integer.valueOf(iArr4.length)).append(", ecgAVL=");
        int[] iArr5 = this.ecgAVL;
        StringBuilder sbAppend6 = sbAppend5.append(iArr5 == null ? "null" : Integer.valueOf(iArr5.length)).append(", ecgAVF=");
        int[] iArr6 = this.ecgAVF;
        StringBuilder sbAppend7 = sbAppend6.append(iArr6 == null ? "null" : Integer.valueOf(iArr6.length)).append(", power=");
        int[] iArr7 = this.power;
        return sbAppend7.append(iArr7 == null ? "null" : Integer.valueOf(iArr7.length)).append(", waveformsInfo=").append(this.waveformsInfo).append('}').toString();
    }
}
