package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MiniCheckupBasePersonalInfo.class */
public class MiniCheckupBasePersonalInfo {
    private int gender;
    private int age;
    private int height;
    private int weight;

    public int getGender() {
        return this.gender;
    }

    public void setGender(int i) {
        this.gender = i;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int i) {
        this.age = i;
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

    public String toString() {
        return "MiniCheckupBaseUseInfo{gender=" + this.gender + ", age=" + this.age + ", height=" + this.height + ", weight=" + this.weight + '}';
    }
}
