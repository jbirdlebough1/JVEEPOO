package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EScreenStyle;
import com.veepoo.protocol.model.enums.EUIFromType;
import java.util.Arrays;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/ScreenStyleData.class */
public class ScreenStyleData {
    private EScreenStyle status;
    private int screenIndex;
    EUIFromType vp_a;
    private int[] watchIDArr;

    public ScreenStyleData() {
    }

    public ScreenStyleData(EScreenStyle eScreenStyle, int i) {
        this.status = eScreenStyle;
        this.screenIndex = i;
    }

    public EScreenStyle getStatus() {
        return this.status;
    }

    public void setStatus(EScreenStyle eScreenStyle) {
        this.status = eScreenStyle;
    }

    public void setScreenIndex(int i) {
        this.screenIndex = i;
    }

    public int getScreenIndex() {
        return this.screenIndex;
    }

    public EUIFromType getScreenType() {
        return this.vp_a;
    }

    public void setScreenType(EUIFromType eUIFromType) {
        this.vp_a = eUIFromType;
    }

    public int[] getWatchIDArr() {
        return this.watchIDArr;
    }

    public void setWatchIDArr(int[] iArr) {
        this.watchIDArr = iArr;
    }

    public String toString() {
        return "ScreenStyleData{status=" + this.status + ", screenIndex=" + this.screenIndex + ", screenType=" + this.vp_a + ", watchIDArr=" + Arrays.toString(this.watchIDArr) + '}';
    }
}
