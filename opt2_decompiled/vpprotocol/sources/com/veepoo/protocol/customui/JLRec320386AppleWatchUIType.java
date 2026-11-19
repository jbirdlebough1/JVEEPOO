package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRec320386AppleWatchUIType.class */
public class JLRec320386AppleWatchUIType extends JLRect320386WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_320_386_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.dial_edit_bg_default_386_apple;
    }
}
