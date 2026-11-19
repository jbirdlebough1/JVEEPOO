package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Rect240280QFNBlackWatchUIType.class */
public class Rect240280QFNBlackWatchUIType extends Rect240280WatchUIType {
    @Override // com.veepoo.protocol.customui.Rect240280WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_240_280_QFN;
    }

    @Override // com.veepoo.protocol.customui.Rect240280WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap, true);
    }

    @Override // com.veepoo.protocol.customui.Rect240280WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.dial_edit_bg_default_286_black;
    }
}
