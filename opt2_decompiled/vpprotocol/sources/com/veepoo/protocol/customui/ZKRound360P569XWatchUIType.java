package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRound360P569XWatchUIType.class */
public class ZKRound360P569XWatchUIType extends ZKRound360WatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_ROUND_360_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial360_preview_bg_default2;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 360;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 360;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial360_preview_bg2_circle4);
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 156;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 156;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 9;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 138;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 138;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Boolean isRoundScreen() {
        return Boolean.TRUE;
    }
}
