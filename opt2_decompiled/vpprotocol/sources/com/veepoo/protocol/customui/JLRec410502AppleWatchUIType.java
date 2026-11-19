package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRec410502AppleWatchUIType.class */
public class JLRec410502AppleWatchUIType extends JLRect410502WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_410_502_APPLE;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdial_edit_bg_default_502_apple;
    }
}
