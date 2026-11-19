package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Round240240QFNWatchUIType.class */
public class Round240240QFNWatchUIType extends Round240240WatchUIType {
    @Override // com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_240_240_QFN;
    }

    @Override // com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap, true);
    }

    @Override // com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Boolean isRoundScreen() {
        return Boolean.TRUE;
    }
}
