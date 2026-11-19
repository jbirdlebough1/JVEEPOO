package com.timaimee.vpdemo.activity.v2.custom;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseActivity;
import com.veepoo.protocol.listener.data.IYM23ProTestStatusListener;
import com.veepoo.protocol.listener.data.IYM23ProWearReportListener;
import com.veepoo.protocol.model.datas.YM23ProWearReportData;
import com.veepoo.protocol.model.enums.EYM23ProOptStatus;
import com.veepoo.protocol.model.enums.EYM23ProTestStatus;

/**
 * YM23PROCustomized Function - Wear Status Reporting and Test Status Test Page
 * (定制功能，需要设备支持)
 * <p>
 * 交互闭环说明：
 * 1.The test status can only be sent after the wear status reporting is successfully enabled, so the test status button area is hidden by default.，
 * Displayed after successful activation, hidden again after successful deactivation；
 * 2.If the wear status report is still enabled when the page is exited, the system will automatically send a command to disable it.，保证状态闭环。
 */
public class YM23ProTestActivity extends BaseActivity {

    /**
     * Whether the wear status report has been successfully enabled (local flag)，Used for button visibility and automatic closing when exiting the page)
     */
    private boolean isWearReportOpened = false;

    private TextView tvTestStatusTitle;
    private LinearLayout llTestStatusButtons;
    private TextView tvWearReportState;

    @Override
    public int getLayoutID() {
        return R.layout.activity_ym23_pro_test;
    }

    @Override
    public String pageTitle() {
        return "YM23PRO佩戴上报与测试状态";
    }

    @Override
    public void initView() {
        tvWearReportState = findViewById(R.id.tvWearReportState);
        tvTestStatusTitle = findViewById(R.id.tvTestStatusTitle);
        llTestStatusButtons = findViewById(R.id.llTestStatusButtons);
        // Wear status reporting is disabled by default, and the test status button area is hidden by default.
        updateWearReportStateUI();
        updateTestStatusUI(false);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initEvent() {
        findViewById(R.id.btnOpen).setOnClickListener(v ->
                vpBleManager.openYM23ProWearReport(mWearReportListener, code -> {
                }));
        findViewById(R.id.btnClose).setOnClickListener(v ->
                vpBleManager.closeYM23ProWearReport(mWearReportListener, code -> {
                }));
        findViewById(R.id.btnTesting).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.TESTING));
        findViewById(R.id.btnRepairing).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.REPAIRING));
        findViewById(R.id.btnTestDone).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.TEST_DONE));
        findViewById(R.id.btnRepairDone).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.REPAIR_DONE));
        findViewById(R.id.btnTestPause).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.TEST_PAUSE));
        findViewById(R.id.btnRepairPause).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.REPAIR_PAUSE));
        findViewById(R.id.btnIdle).setOnClickListener(v -> sendTestStatus(EYM23ProTestStatus.IDLE));
    }

    private void sendTestStatus(EYM23ProTestStatus testStatus) {
        vpBleManager.setYM23ProTestStatus(testStatus, mTestStatusListener, code -> {
        });
    }

    /**
     * Wear Status Report Monitoring: Switch Response + 主动上报
     * (协议2.3：开关应答仅回ACK；Wear/Heart Rate data is pushed via active reporting)
     */
    private final IYM23ProWearReportListener mWearReportListener = new IYM23ProWearReportListener() {
        @Override
        public void onYM23ProWearReportOpenResult(EYM23ProOptStatus status) {
            if (status == EYM23ProOptStatus.SUCCESS) {
                isWearReportOpened = true;
                updateWearReportStateUI();
                updateTestStatusUI(true);
                appendResult("✅️Wear status reporting enabled successfully. Awaiting device to report wear status andHeart Rate数据");
            } else {
                appendRedLargeText("❌️开启佩戴状态上报失败：" + status.getDes());
            }
        }

        @Override
        public void onYM23ProWearReportCloseResult(EYM23ProOptStatus status) {
            if (status == EYM23ProOptStatus.SUCCESS) {
                isWearReportOpened = false;
                updateWearReportStateUI();
                updateTestStatusUI(false);
                appendResult("✅️关闭佩戴状态上报成功");
            } else {
                appendRedLargeText("❌️关闭佩戴状态上报失败：" + status.getDes());
            }
        }

        @Override
        public void onYM23ProWearReport(YM23ProWearReportData data) {
            appendResult("📩佩戴状态上报：" + data.toString());
        }
    };

    /**
     * 测试状态下发监听
     */
    private final IYM23ProTestStatusListener mTestStatusListener = this::onTestStatusResult;

    private void onTestStatusResult(EYM23ProOptStatus status, EYM23ProTestStatus testStatus) {
        if (status == EYM23ProOptStatus.SUCCESS) {
            if (testStatus == EYM23ProTestStatus.IDLE) {
                // Exiting the test interface when idle is equivalent to turning off the wear status reporting.，同步更新状态并隐藏按钮区
                isWearReportOpened = false;
                updateWearReportStateUI();
                updateTestStatusUI(false);
                appendResult("✅️Idle mode successfully issued, exited the test interface，佩戴状态上报已关闭");
            } else {
                appendResult("✅️Test status sent successfully. Current device status:：" + testStatus.getDes());
            }
        } else {
            appendRedLargeText("❌️Failed to send test status: " + status.getDes() + ", device status：" + testStatus.getDes());
        }
    }

    private void updateTestStatusUI(boolean opened) {
        if (tvTestStatusTitle != null) {
            tvTestStatusTitle.setVisibility(opened ? View.VISIBLE : View.GONE);
        }
        if (llTestStatusButtons != null) {
            llTestStatusButtons.setVisibility(opened ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * Real-time refresh of the switch status display for reported wear status (above the button area))
     */
    private void updateWearReportStateUI() {
        if (tvWearReportState == null) return;
        if (isWearReportOpened) {
            tvWearReportState.setText("佩戴状态上报：已开启");
            tvWearReportState.setTextColor(Color.parseColor("#12A30F"));
        } else {
            tvWearReportState.setText("佩戴状态上报：未开启");
            tvWearReportState.setTextColor(Color.parseColor("#666666"));
        }
    }

    @Override
    protected void onDestroy() {
        // If the wear status reporting is still enabled when the page is exited, it will be automatically disabled.，保证状态闭环
        if (isWearReportOpened) {
            isWearReportOpened = false;
            vpBleManager.closeYM23ProWearReport(new IYM23ProWearReportListener() {
                @Override
                public void onYM23ProWearReportOpenResult(EYM23ProOptStatus status) {
                }

                @Override
                public void onYM23ProWearReportCloseResult(EYM23ProOptStatus status) {
                }

                @Override
                public void onYM23ProWearReport(YM23ProWearReportData data) {
                }
            }, code -> {
            });
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public boolean hasCommonMsgUI() {
        return true;
    }
}
