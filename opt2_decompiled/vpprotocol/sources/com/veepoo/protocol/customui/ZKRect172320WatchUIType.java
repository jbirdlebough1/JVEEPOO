package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect172320WatchUIType.class */
public class ZKRect172320WatchUIType extends Rect172320WatchUIType {
    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap);
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_172x320;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_172_320;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg_172x320);
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 172;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 320;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 91;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 159;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 6;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 79;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 147;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 16;
    }
}
