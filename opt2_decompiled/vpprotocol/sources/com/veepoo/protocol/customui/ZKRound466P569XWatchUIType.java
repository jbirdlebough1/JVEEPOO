package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRound466P569XWatchUIType.class */
public class ZKRound466P569XWatchUIType extends ZKRound466WatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial466_preview_bg_default2;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_ROUND_466_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 466;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 466;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial466_preview_bg_circle4);
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 200;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 200;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 12;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 176;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 176;
    }

    @Override // com.veepoo.protocol.customui.ZKRound466WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Boolean isRoundScreen() {
        return Boolean.TRUE;
    }
}
