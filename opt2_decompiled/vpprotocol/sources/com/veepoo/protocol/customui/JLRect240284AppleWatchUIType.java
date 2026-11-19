package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect240284AppleWatchUIType.class */
public class JLRect240284AppleWatchUIType extends JLRect240284WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect240284WatchUIType, com.veepoo.protocol.customui.Rect240284QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_240_284_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect240284WatchUIType, com.veepoo.protocol.customui.Rect240284QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdial_edit_bg_default_284_apple;
    }
}
