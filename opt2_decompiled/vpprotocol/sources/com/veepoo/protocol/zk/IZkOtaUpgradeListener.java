package com.veepoo.protocol.zk;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/zk/IZkOtaUpgradeListener.class */
public interface IZkOtaUpgradeListener {
    void onOtaUpgradeReady();

    void onOtaUpgradeStart();

    void onOtaUpgradeUpdating(int i);

    void onOtaUpgradePause();

    void onOtaUpgradeContinue();

    void onOtaUpgradeSuccess();

    void onOtaUpgradeWaitFinish();

    void onOtaUpgradeFail(int i, String str);
}
