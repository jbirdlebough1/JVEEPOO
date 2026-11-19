package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Rect240295QFNWatchUIType.class */
public class Rect240295QFNWatchUIType extends Rect240295WatchUIType {
    @Override // com.veepoo.protocol.customui.Rect240295WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_240_295_QFN;
    }

    @Override // com.veepoo.protocol.customui.Rect240295WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap, true);
    }
}
