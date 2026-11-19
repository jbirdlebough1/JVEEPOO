package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect240296P569XWatchUIType.class */
public class ZKRect240296P569XWatchUIType extends ZKRect240284XmdWatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_240_296_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_296;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg2_296_4);
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 140;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 168;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 10;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 120;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 148;
    }

    @Override // com.veepoo.protocol.customui.ZKRect240284XmdWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 60;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapRadius() {
        return 30;
    }
}
