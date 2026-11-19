package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRound466EarthWatchUIType.class */
public class JLRound466EarthWatchUIType extends JLRound466WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_JL_466_466_EARTH;
    }

    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial466_preview_bg_default3;
    }
}
