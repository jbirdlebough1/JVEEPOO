package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect240292AppleWatchUIType.class */
public class JLRect240292AppleWatchUIType extends JLRect240292WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect240292WatchUIType, com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_240_292_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect240292WatchUIType, com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdial_edit_bg_default_292_apple;
    }
}
