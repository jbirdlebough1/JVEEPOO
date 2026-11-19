package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect172320WatchUIType.class */
public class JLRect172320WatchUIType extends Rect172320WatchUIType {
    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap);
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.dial147_edit_bg_default;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_172_320;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.dial147_preview_bg2);
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 172;
    }

    @Override // com.veepoo.protocol.customui.Rect172320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 320;
    }
}
