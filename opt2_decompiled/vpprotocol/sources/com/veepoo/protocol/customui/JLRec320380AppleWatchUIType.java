package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRec320380AppleWatchUIType.class */
public class JLRec320380AppleWatchUIType extends JLRect320380WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect320380WatchUIType, com.veepoo.protocol.customui.Rect320380QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_320_380_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect320380WatchUIType, com.veepoo.protocol.customui.Rect320380QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.dial_edit_bg_default_320_apple;
    }
}
