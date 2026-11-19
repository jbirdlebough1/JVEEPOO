package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect368448AppleWatchUIType.class */
public class JLRect368448AppleWatchUIType extends JLRect368448WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_368_448_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect368448WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdial_edit_bg_default_368_apple;
    }
}
