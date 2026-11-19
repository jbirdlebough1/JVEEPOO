package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.ELanguage;
import com.veepoo.protocol.model.enums.EOprateStauts;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/LanguageData.class */
public class LanguageData {
    private EOprateStauts stauts;
    private ELanguage language;

    public EOprateStauts getStauts() {
        return this.stauts;
    }

    public void setStauts(EOprateStauts eOprateStauts) {
        this.stauts = eOprateStauts;
    }

    public ELanguage getLanguage() {
        return this.language;
    }

    public void setLanguage(ELanguage eLanguage) {
        this.language = eLanguage;
    }

    public String toString() {
        return "LanguageData{stauts=" + this.stauts + ", language=" + this.language + '}';
    }
}
