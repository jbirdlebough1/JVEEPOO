package com.veepoo.protocol.model.datas;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/JH76SNCodeData.class */
public class JH76SNCodeData {
    private boolean isSet;
    private String snCode = "";

    public boolean isSet() {
        return this.isSet;
    }

    public void setSet(boolean z) {
        this.isSet = z;
    }

    public String getSnCode() {
        return this.snCode;
    }

    public void setSnCode(String str) {
        this.snCode = str;
    }

    public String toString() {
        return "JH76SNCodeData{isSet=" + this.isSet + ", snCode='" + this.snCode + "'}";
    }
}
