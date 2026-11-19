package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/SportData.class */
public class SportData {
    private int step;
    private double dis;
    private double kcal;
    private int calcType;
    private int triaxialX;
    private int triaxialY;
    private int triaxialZ;

    public int getStep() {
        return this.step;
    }

    public void setStep(int i) {
        this.step = i;
    }

    public double getDis() {
        return this.dis;
    }

    public void setDis(double d) {
        this.dis = d;
    }

    public double getKcal() {
        return this.kcal;
    }

    public void setKcal(double d) {
        this.kcal = d;
    }

    public int getCalcType() {
        return this.calcType;
    }

    public void setCalcType(int i) {
        this.calcType = i;
    }

    public int getTriaxialX() {
        return this.triaxialX;
    }

    public void setTriaxialX(int i) {
        this.triaxialX = i;
    }

    public int getTriaxialY() {
        return this.triaxialY;
    }

    public void setTriaxialY(int i) {
        this.triaxialY = i;
    }

    public int getTriaxialZ() {
        return this.triaxialZ;
    }

    public void setTriaxialZ(int i) {
        this.triaxialZ = i;
    }

    public String toString() {
        return "SportData{step=" + this.step + ", dis=" + this.dis + ", kcal=" + this.kcal + ", calcType=" + this.calcType + ", triaxialX=" + this.triaxialX + ", triaxialY=" + this.triaxialY + ", triaxialZ=" + this.triaxialZ + '}';
    }
}
