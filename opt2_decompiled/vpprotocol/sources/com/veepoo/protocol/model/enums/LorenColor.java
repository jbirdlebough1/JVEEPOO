package com.veepoo.protocol.model.enums;

import android.graphics.Color;
import androidx.annotation.RequiresApi;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/LorenColor.class */
@RequiresApi(api = 26)
public enum LorenColor {
    Purple(Color.rgb(0.54509807f, 0.0f, 1.0f), "紫"),
    Blue(Color.rgb(0.0f, 0.0f, 1.0f), "蓝"),
    Cyan(Color.rgb(0.54509807f, 0.0f, 1.0f), "青"),
    Green(Color.rgb(0.0f, 1.0f, 0.0f), "绿"),
    Yellow(Color.rgb(1.0f, 1.0f, 0.0f), "黄"),
    Orange(Color.rgb(1.0f, 0.64705884f, 0.0f), "橙"),
    Red(Color.rgb(1.0f, 0.0f, 0.0f), "红");

    private int color;
    private String des;

    LorenColor(int i, String str) {
        this.color = i;
        this.des = str;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String str) {
        this.des = str;
    }
}
