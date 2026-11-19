package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ESex;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/PersonInfoData.class */
public class PersonInfoData {
    private ESex ESex;
    private int height;
    private int weight;
    private int age;
    private int stepAim;
    private int sleepAim;

    public PersonInfoData(ESex eSex, int i, int i2, int i3, int i4) {
        this.sleepAim = 480;
        this.ESex = eSex;
        this.height = i;
        this.weight = i2;
        this.age = i3;
        this.stepAim = i4;
        if (i < 55 || i > 255) {
            this.height = 175;
        }
        if (i2 < 25 || i2 > 255) {
            this.weight = 60;
        }
        if (i3 < 1 || i3 > 255) {
            this.age = 25;
        }
        if (i4 < 1 || i4 > 65535) {
            this.stepAim = 9000;
        }
    }

    public ESex getESex() {
        return this.ESex;
    }

    public void setESex(ESex eSex) {
        this.ESex = eSex;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public int getStepAim() {
        return this.stepAim;
    }

    public void setStepAim(int i) {
        this.stepAim = i;
    }

    public int getSleepAim() {
        return this.sleepAim;
    }

    public void setSleepAim(int i) {
        this.sleepAim = i;
    }

    public String toString() {
        return "PersonInfoData{ESex=" + this.ESex + ", height=" + this.height + ", weight=" + this.weight + ", age=" + this.age + ", stepAim=" + this.stepAim + ", sleepAim=" + this.sleepAim + '}';
    }

    public PersonInfoData(ESex eSex, int i, int i2, int i3, int i4, int i5) {
        this.ESex = eSex;
        this.height = i;
        this.weight = i2;
        this.age = i3;
        this.stepAim = i4;
        this.sleepAim = i5;
        if (i < 55 || i > 255) {
            this.height = 175;
        }
        if (i2 < 25 || i2 > 255) {
            this.weight = 60;
        }
        if (i3 < 1 || i3 > 255) {
            this.age = 25;
        }
        if (i4 < 1 || i4 > 65535) {
            this.stepAim = 9000;
        }
        if (i5 < 0 || i5 > 1440) {
            this.stepAim = 480;
        }
    }
}
