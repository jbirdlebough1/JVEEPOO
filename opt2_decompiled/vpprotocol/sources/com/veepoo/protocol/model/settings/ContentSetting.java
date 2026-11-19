package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.ESocailMsg;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ContentSetting.class */
public abstract class ContentSetting {
    private ESocailMsg eSocailMsg;
    private int contactLength;
    private int allLength;

    public ContentSetting(ESocailMsg eSocailMsg) {
        this.contactLength = 0;
        this.allLength = 4;
        this.eSocailMsg = eSocailMsg;
    }

    public ESocailMsg geteSocailMsg() {
        return this.eSocailMsg;
    }

    public void seteSocailMsg(ESocailMsg eSocailMsg) {
        this.eSocailMsg = eSocailMsg;
    }

    public int getContactLength() {
        return this.contactLength;
    }

    public void setContactLength(int i) {
        this.contactLength = i;
    }

    public int getAllLength() {
        return this.allLength;
    }

    public void setAllLength(int i) {
        this.allLength = i;
    }

    public String toString() {
        return "ContentSetting{eSocailMsg=" + this.eSocailMsg + ", contactLength=" + this.contactLength + ", allLength=" + this.allLength + '}';
    }

    public ContentSetting(ESocailMsg eSocailMsg, int i, int i2) {
        this.eSocailMsg = eSocailMsg;
        this.contactLength = i;
        this.allLength = i2;
    }
}
