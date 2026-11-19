package com.veepoo.protocol.model.datas;

import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/UICustomSetData.class */
public class UICustomSetData {
    private boolean isDefalutUI;
    private EWatchUIElementPosition timePosition;
    private EWatchUIElementType upTimeType;
    private EWatchUIElementType downTimeType;
    private int color888;

    public UICustomSetData(boolean z, EWatchUIElementPosition eWatchUIElementPosition, EWatchUIElementType eWatchUIElementType, EWatchUIElementType eWatchUIElementType2, int i) {
        this.isDefalutUI = z;
        this.timePosition = eWatchUIElementPosition;
        this.upTimeType = eWatchUIElementType;
        this.downTimeType = eWatchUIElementType2;
        this.color888 = i;
    }

    public boolean isDefalutUI() {
        return this.isDefalutUI;
    }

    public void setDefalutUI(boolean z) {
        this.isDefalutUI = z;
    }

    public EWatchUIElementPosition getTimePosition() {
        return this.timePosition;
    }

    public void setTimePosition(EWatchUIElementPosition eWatchUIElementPosition) {
        this.timePosition = eWatchUIElementPosition;
    }

    public EWatchUIElementType getUpTimeType() {
        return this.upTimeType;
    }

    public void setUpTimeType(EWatchUIElementType eWatchUIElementType) {
        this.upTimeType = eWatchUIElementType;
    }

    public EWatchUIElementType getDownTimeType() {
        return this.downTimeType;
    }

    public void setDownTimeType(EWatchUIElementType eWatchUIElementType) {
        this.downTimeType = eWatchUIElementType;
    }

    public int getColor888() {
        return this.color888;
    }

    public void setColor888(int i) {
        this.color888 = i;
    }

    public String toString() {
        return "UICustomSetData{isDefalutUI=" + this.isDefalutUI + ", timePosition=" + this.timePosition + ", upTimeType=" + this.upTimeType + ", downTimeType=" + this.downTimeType + ", color888=" + this.color888 + '}';
    }
}
