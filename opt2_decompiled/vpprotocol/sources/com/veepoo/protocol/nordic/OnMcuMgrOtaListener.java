package com.veepoo.protocol.nordic;

import io.runtime.mcumgr.dfu.FirmwareUpgradeController;
import io.runtime.mcumgr.dfu.mcuboot.FirmwareUpgradeManager;
import io.runtime.mcumgr.exception.McuMgrException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/nordic/OnMcuMgrOtaListener.class */
public interface OnMcuMgrOtaListener {
    void onUpgradeStarted(FirmwareUpgradeController firmwareUpgradeController);

    void onStateChanged(FirmwareUpgradeManager.State state, FirmwareUpgradeManager.State state2);

    void onUpgradeCompleted();

    void onUpgradeFailed(FirmwareUpgradeManager.State state, McuMgrException mcuMgrException);

    void onUpgradeCanceled(FirmwareUpgradeManager.State state);

    void onUploadProgressChanged(int i, int i2, long j);
}
