package com.veepoo.protocol.model.datas;

import java.io.Serializable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UserDateInfo.class */
public class UserDateInfo implements Serializable {
    private static final long serialVersionUID = -4526244314188425098L;
    public int deviceNubmer;
    public String updateDate;
    public String expireDate;
    public String basicType;
    public String oxygenType;
    public String hrvType;
    public String femaleType;
    public String ecgDisearseType;

    public int getDeviceNubmer() {
        return this.deviceNubmer;
    }

    public void setDeviceNubmer(int i) {
        this.deviceNubmer = i;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String str) {
        this.updateDate = str;
    }

    public String getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(String str) {
        this.expireDate = str;
    }

    public String getBasicType() {
        return this.basicType;
    }

    public void setBasicType(String str) {
        this.basicType = str;
    }

    public String getOxygenType() {
        return this.oxygenType;
    }

    public void setOxygenType(String str) {
        this.oxygenType = str;
    }

    public String getHrvType() {
        return this.hrvType;
    }

    public void setHrvType(String str) {
        this.hrvType = str;
    }

    public String getFemaleType() {
        return this.femaleType;
    }

    public void setFemaleType(String str) {
        this.femaleType = str;
    }

    public String getEcgDisearseType() {
        return this.ecgDisearseType;
    }

    public void setEcgDisearseType(String str) {
        this.ecgDisearseType = str;
    }

    public String toString() {
        return "UserDateInfo{deviceNubmer=" + this.deviceNubmer + ", updateDate='" + this.updateDate + "', expireDate='" + this.expireDate + "',basicType=" + this.basicType + "',oxygenType=" + this.oxygenType + "',hrvType=" + this.hrvType + "',femaleType=" + this.femaleType + "',ecgDisearseType=" + this.ecgDisearseType + "'}";
    }
}
