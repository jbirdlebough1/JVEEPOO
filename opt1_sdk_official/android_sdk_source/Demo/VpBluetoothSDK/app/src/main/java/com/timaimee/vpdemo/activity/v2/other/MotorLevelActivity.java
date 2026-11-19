package com.timaimee.vpdemo.activity.v2.other;

import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseActivity;
import com.veepoo.protocol.listener.data.IMotorOperateListener;
import com.veepoo.protocol.model.datas.MotorLevelData;
import com.veepoo.protocol.model.enums.EMotorLevel;
import com.veepoo.protocol.shareprence.VpSpGetUtil;

/**
 * 振动强度(马达)测试页
 * Device support required: A7 function list return vibration intensity support flag
 */
public class MotorLevelActivity extends BaseActivity implements IMotorOperateListener {

    @Override
    public int getLayoutID() {
        return R.layout.activity_motor_level;
    }

    @Override
    public String pageTitle() {
        return "振动强度";
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        if (!VpSpGetUtil.getVpSpVariInstance(this).isSupportMotor()) {
            appendRedLargeText("❌️The current device does not support the vibration intensity function");
        }
    }

    @Override
    public void initEvent() {
        findViewById(R.id.btnRead).setOnClickListener(v ->
                vpBleManager.readMotorLevel(defaultResponse, this));
        findViewById(R.id.btnWeak).setOnClickListener(v ->
                vpBleManager.setMotorLevel(EMotorLevel.WEAK, defaultResponse, this));
        findViewById(R.id.btnMedium).setOnClickListener(v ->
                vpBleManager.setMotorLevel(EMotorLevel.MEDIUM, defaultResponse, this));
        findViewById(R.id.btnStrong).setOnClickListener(v ->
                vpBleManager.setMotorLevel(EMotorLevel.STRONG, defaultResponse, this));
    }

    @Override
    public void onMotorLevelRead(MotorLevelData data) {
        if (data.isSuccess()) {
            appendResult("✅️Vibration intensity read successfully: Current level=" + data.getLevel().name());
        } else {
            appendRedLargeText("❌️振动强度读取失败");
        }
    }

    @Override
    public void onMotorLevelSetting(MotorLevelData data) {
        if (data.isSuccess()) {
            appendResult("✅️Vibration intensity set successfully: Current level=" + data.getLevel().name());
        } else {
            appendRedLargeText("❌️振动强度设置失败");
        }
    }

    @Override
    public void onMotorLevelReport(MotorLevelData data) {
        appendBlueMiddleText("📱Proactive reporting of changes on the device side: Current gear=" + data.getLevel().name());
    }

    @Override
    public void onClick(android.view.View view) {
    }

    @Override
    public boolean hasCommonMsgUI() {
        return true;
    }
}
