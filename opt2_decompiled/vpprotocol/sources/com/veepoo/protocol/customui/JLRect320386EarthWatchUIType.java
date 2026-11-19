package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect320386EarthWatchUIType.class */
public class JLRect320386EarthWatchUIType extends JLRect320386WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_320_386_EARTH;
    }

    @Override // com.veepoo.protocol.customui.JLRect320386WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default2_385;
    }
}
