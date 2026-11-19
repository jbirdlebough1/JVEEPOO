package com.veepoo.protocol.model.settings;

import com.veepoo.protocol.model.enums.ESocailMsg;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ContentSocailSetting.class */
public class ContentSocailSetting extends ContentSetting {
    private String title;
    private String content;

    @Deprecated
    public ContentSocailSetting(ESocailMsg eSocailMsg, int i, int i2, String str, String str2) {
        super(eSocailMsg, i, i2);
        this.title = str;
        this.content = str2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    @Override // com.veepoo.protocol.model.settings.ContentSetting
    public String toString() {
        return "ContentSocailSetting{title='" + this.title + "', content='" + this.content + "'}";
    }

    public ContentSocailSetting(ESocailMsg eSocailMsg, String str, String str2) {
        super(eSocailMsg);
        this.title = str;
        this.content = str2;
    }
}
