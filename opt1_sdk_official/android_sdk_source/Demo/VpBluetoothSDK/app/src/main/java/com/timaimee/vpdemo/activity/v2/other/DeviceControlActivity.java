package com.timaimee.vpdemo.activity.v2.other;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseActivity;
import com.veepoo.protocol.listener.data.IResponseListener;

/**
 * 设备控制示例（对应 SDK Document 1.4.0 Device Control Functions）
 * 包含：复位 resetDeviceData、Power off powerOffDevice, restore to factory settings clearDeviceData
 */
public class DeviceControlActivity extends BaseActivity {

    public static final String EXTRA_MODE = "extra_mode";
    public static final int MODE_NONE = -1;
    public static final int MODE_RESET = 0;
    public static final int MODE_POWER_OFF = 1;
    public static final int MODE_FACTORY_RESET = 2;

    Button btnReset, btnPowerOff, btnFactoryReset;

    @Override
    public int getLayoutID() {
        return R.layout.activity_device_control;
    }

    @Override
    public String pageTitle() {
        return "设备控制";
    }

    @Override
    public boolean hasCommonMsgUI() {
        return true;
    }

    @Override
    public void initView() {
        btnReset = findViewById(R.id.btnReset);
        btnPowerOff = findViewById(R.id.btnPowerOff);
        btnFactoryReset = findViewById(R.id.btnFactoryReset);
    }

    @Override
    public void initData() {
        int mode = getIntent().getIntExtra(EXTRA_MODE, MODE_NONE);
        if (mode == MODE_RESET) {
            doReset();
        } else if (mode == MODE_POWER_OFF) {
            doPowerOff();
        } else if (mode == MODE_FACTORY_RESET) {
            doFactoryReset();
        }
    }

    @Override
    public void initEvent() {
        btnReset.setOnClickListener(v -> doReset());
        btnPowerOff.setOnClickListener(v -> doPowerOff());
        btnFactoryReset.setOnClickListener(v -> doFactoryReset());
    }

    @Override
    public void onClick(View view) {

    }

    private void doReset() {
        clearTestInfo();
        appendBlueMiddleText("发送复位指令...");
        vpBleManager.resetDeviceData(defaultResponse);
    }

    private void doFactoryReset() {
        clearTestInfo();
        appendBlueMiddleText("发送恢复出厂设置指令...");
        vpBleManager.clearDeviceData(defaultResponse);
    }

    private void doPowerOff() {
        clearTestInfo();
        appendBlueMiddleText("Sending shutdown command... (The device will automatically disconnect after it shuts down)，The callback may not return. Please consider the disconnection status when making a judgment.）");
        vpBleManager.powerOffDevice(defaultResponse, new IResponseListener() {
            @Override
            public void response(int state) {
                // 0=失败，1=成功
                if (state == 1) {
                    appendBlueMiddleText("✅️ 关机成功");
                } else {
                    appendRedLargeText("❌️ 关机失败, state=" + state);
                }
            }
        });
    }
}
