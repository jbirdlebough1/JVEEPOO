package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect368448P569XWatchUIType.class */
public class ZKRect368448P569XWatchUIType extends ZKRect368448WatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_368_448_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_368;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getBigBitmap(Context context, Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 368;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 448;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg_368_4);
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 184;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 219;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 12;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 160;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 195;
    }

    @Override // com.veepoo.protocol.customui.ZKRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 56;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapRadius() {
        return 28;
    }
}
