package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRound480EarthWatchUIType.class */
public class JLRound480EarthWatchUIType extends JLRound480WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRound480WatchUIType, com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_edit_bg_default_480_yihui1;
    }

    @Override // com.veepoo.protocol.customui.JLRound480WatchUIType, com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_JL_480_480_EARTH;
    }
}
