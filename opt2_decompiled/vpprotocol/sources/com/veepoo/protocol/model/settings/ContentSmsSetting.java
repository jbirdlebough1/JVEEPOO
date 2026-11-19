package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.ESocailMsg;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ContentSmsSetting.class */
public class ContentSmsSetting extends ContentSetting {
    private String contactName;
    private String contactPhoneNumber;
    private String content;

    @Deprecated
    public ContentSmsSetting(ESocailMsg eSocailMsg, int i, int i2, String str, String str2) {
        super(eSocailMsg, i, i2);
        this.contactName = "";
        this.contactPhoneNumber = str;
        this.content = str2;
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    @Override // com.veepoo.protocol.model.settings.ContentSetting
    public String toString() {
        return "ContentSmsSetting{contactName='" + this.contactName + "', contactPhoneNumber='" + this.contactPhoneNumber + "', content='" + this.content + "'}";
    }

    @Deprecated
    public ContentSmsSetting(ESocailMsg eSocailMsg, int i, int i2, String str, String str2, String str3) {
        super(eSocailMsg, i, i2);
        this.contactName = str;
        this.contactPhoneNumber = str2;
        this.content = str3;
    }

    @Deprecated
    public ContentSmsSetting(ESocailMsg eSocailMsg, String str, String str2) {
        super(eSocailMsg);
        this.contactName = "";
        this.contactPhoneNumber = str;
        this.content = str2;
    }

    @Deprecated
    public ContentSmsSetting(ESocailMsg eSocailMsg, String str, String str2, String str3) {
        super(eSocailMsg);
        this.contactName = str;
        this.contactPhoneNumber = str2;
        this.content = str3;
    }

    public ContentSmsSetting(String str, String str2, String str3) {
        super(ESocailMsg.SMS);
        this.contactName = str;
        this.contactPhoneNumber = str2;
        this.content = str3;
    }
}
