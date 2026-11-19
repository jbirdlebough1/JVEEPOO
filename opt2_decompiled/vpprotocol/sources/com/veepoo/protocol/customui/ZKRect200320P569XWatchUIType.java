package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect200320P569XWatchUIType.class */
public class ZKRect200320P569XWatchUIType extends ZKRect200320WatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_200x320;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_200_320_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg2_200x320_4);
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 200;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 320;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 104;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 159;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 6;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 92;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 147;
    }

    @Override // com.veepoo.protocol.customui.ZKRect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 22;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapRadius() {
        return 10;
    }
}
