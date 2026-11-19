package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EDeviceStatus;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/TcmDiagnosisDetectState.class */
public class TcmDiagnosisDetectState {
    private int ecgType;
    private int con;
    private int dataType;
    private EDeviceStatus deviceState;
    private int hr1;
    private int hr2;
    private int hrv;
    private int rr1;
    private int rr2;
    private int br1;
    private int br2;
    private int wear;
    private int mid;
    private int qtc;
    private float pwv;
    private float pat;
    private int progress;

    public TcmDiagnosisDetectState() {
    }

    public TcmDiagnosisDetectState(int i, int i2, int i3, EDeviceStatus eDeviceStatus, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14) {
        this.ecgType = i;
        this.con = i2;
        this.dataType = i3;
        this.deviceState = eDeviceStatus;
        this.hr1 = i4;
        this.hr2 = i5;
        this.hrv = i6;
        this.rr1 = i7;
        this.rr2 = i8;
        this.br1 = i9;
        this.br2 = i10;
        this.wear = i11;
        this.mid = i12;
        this.qtc = i13;
        this.progress = i14;
    }

    public int getEcgType() {
        return this.ecgType;
    }

    public int getCon() {
        return this.con;
    }

    public int getDataType() {
        return this.dataType;
    }

    public EDeviceStatus getDeviceState() {
        return this.deviceState;
    }

    public int getHr1() {
        return this.hr1;
    }

    public int getHr2() {
        return this.hr2;
    }

    public int getHrv() {
        return this.hrv;
    }

    public int getRr1() {
        return this.rr1;
    }

    public int getRr2() {
        return this.rr2;
    }

    public int getBr1() {
        return this.br1;
    }

    public int getBr2() {
        return this.br2;
    }

    public int getWear() {
        return this.wear;
    }

    public int getMid() {
        return this.mid;
    }

    public int getQtc() {
        return this.qtc;
    }

    public float getPwv() {
        return this.pwv;
    }

    public void setPwv(float f) {
        this.pwv = f;
    }

    public int getProgress() {
        return this.progress;
    }

    public float getPat() {
        return this.pat;
    }

    public void setPat(float f) {
        this.pat = f;
    }

    public String toString() {
        return "TcmDiagnosisDetectState{ecgType=" + this.ecgType + ", con=" + this.con + ", dataType=" + this.dataType + ", deviceState=" + this.deviceState + ", hr1=" + this.hr1 + ", hr2=" + this.hr2 + ", hrv=" + this.hrv + ", rr1=" + this.rr1 + ", rr2=" + this.rr2 + ", br1=" + this.br1 + ", br2=" + this.br2 + ", wear=" + this.wear + ", mid=" + this.mid + ", qtc=" + this.qtc + ", pwv=" + this.pwv + ", pat=" + this.pat + ", progress=" + this.progress + '}';
    }
}
