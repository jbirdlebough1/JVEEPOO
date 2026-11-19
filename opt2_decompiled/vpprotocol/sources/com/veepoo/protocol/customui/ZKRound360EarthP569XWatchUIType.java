package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRound360EarthP569XWatchUIType.class */
public class ZKRound360EarthP569XWatchUIType extends ZKRound360P569XWatchUIType {
    @Override // com.veepoo.protocol.customui.ZKRound360P569XWatchUIType, com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_ROUND_360_360_EARTH_P569X;
    }

    @Override // com.veepoo.protocol.customui.ZKRound360P569XWatchUIType, com.veepoo.protocol.customui.ZKRound360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial360_preview_bg_default_earth;
    }
}
