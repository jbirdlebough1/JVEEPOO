package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect320386P569XWatchUIType.class */
public class ZKRect320386P569XWatchUIType extends ZKRect320386WatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_320_386_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_385;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 320;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 386;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg_385_4);
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 160;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 188;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 10;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 140;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 168;
    }

    @Override // com.veepoo.protocol.customui.ZKRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 44;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapRadius() {
        return 19;
    }
}
