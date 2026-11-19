package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/QX17IMUData.class */
public class QX17IMUData {
    private short ax;
    private short ay;
    private short az;
    private short gx;
    private short gy;
    private short gz;
    private short mx;
    private short my;
    private short mz;
    private int timestamp;

    public short getAx() {
        return this.ax;
    }

    public void setAx(short s) {
        this.ax = s;
    }

    public short getAy() {
        return this.ay;
    }

    public void setAy(short s) {
        this.ay = s;
    }

    public short getAz() {
        return this.az;
    }

    public void setAz(short s) {
        this.az = s;
    }

    public short getGx() {
        return this.gx;
    }

    public void setGx(short s) {
        this.gx = s;
    }

    public short getGy() {
        return this.gy;
    }

    public void setGy(short s) {
        this.gy = s;
    }

    public short getGz() {
        return this.gz;
    }

    public void setGz(short s) {
        this.gz = s;
    }

    public short getMx() {
        return this.mx;
    }

    public void setMx(short s) {
        this.mx = s;
    }

    public short getMy() {
        return this.my;
    }

    public void setMy(short s) {
        this.my = s;
    }

    public short getMz() {
        return this.mz;
    }

    public void setMz(short s) {
        this.mz = s;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
    }

    public String toString() {
        return "QX17IMUData{ax=" + ((int) this.ax) + ", ay=" + ((int) this.ay) + ", az=" + ((int) this.az) + ", gx=" + ((int) this.gx) + ", gy=" + ((int) this.gy) + ", gz=" + ((int) this.gz) + ", mx=" + ((int) this.mx) + ", my=" + ((int) this.my) + ", mz=" + ((int) this.mz) + ", ts=" + this.timestamp + '}';
    }
}
