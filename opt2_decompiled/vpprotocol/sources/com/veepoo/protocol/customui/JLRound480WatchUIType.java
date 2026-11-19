package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRound480WatchUIType.class */
public class JLRound480WatchUIType extends JLRound466WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdialround_edit_bg_default480;
    }

    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_JL_480_480;
    }

    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 480;
    }

    @Override // com.veepoo.protocol.customui.JLRound466WatchUIType, com.veepoo.protocol.customui.Round360360WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 480;
    }
}
