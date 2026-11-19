package com.veepoo.protocol.customui;

import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIType;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRound240AppleWatchUIType.class */
public class JLRound240AppleWatchUIType extends JLRound240WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRound240WatchUIType, com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_JL_240_240_APPLE;
    }

    @Override // com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdialround_edit_bg_default480;
    }

    @Override // com.veepoo.protocol.customui.Round240240WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Boolean isRoundScreen() {
        return Boolean.TRUE;
    }
}
