package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.ESocailMsg;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ContentPhoneSetting.class */
public class ContentPhoneSetting extends ContentSetting {
    private String contactName;
    private String contactPhoneNumber;

    @Deprecated
    public ContentPhoneSetting(ESocailMsg eSocailMsg, int i, int i2, String str) {
        super(eSocailMsg, i, i2);
        this.contactName = "";
        this.contactPhoneNumber = str;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public String getContactPhoneNumber() {
        return this.contactPhoneNumber;
    }

    public void setContactPhoneNumber(String str) {
        this.contactPhoneNumber = str;
    }

    @Override // com.veepoo.protocol.model.settings.ContentSetting
    public String toString() {
        return "ContentPhoneSetting{contactName='" + this.contactName + "', contactPhoneNumber='" + this.contactPhoneNumber + "'}";
    }

    @Deprecated
    public ContentPhoneSetting(ESocailMsg eSocailMsg, int i, int i2, String str, String str2) {
        super(eSocailMsg, i, i2);
        this.contactName = str;
        this.contactPhoneNumber = str2;
    }

    public ContentPhoneSetting(ESocailMsg eSocailMsg, String str) {
        super(eSocailMsg);
        this.contactName = "";
        this.contactPhoneNumber = str;
    }

    public ContentPhoneSetting(ESocailMsg eSocailMsg, String str, String str2) {
        super(eSocailMsg);
        this.contactName = str;
        this.contactPhoneNumber = str2;
    }

    public ContentPhoneSetting(String str, String str2) {
        super(ESocailMsg.PHONE);
        this.contactName = str;
        this.contactPhoneNumber = str2;
    }
}
