package com.timaimee.vpdemo.activity;

import static com.timaimee.vpdemo.activity.v2.health.AutoMeasureActivity.selectData;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.iielse.switchbutton.SwitchView;
import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IAutoMeasureSettingDataListener;
import com.veepoo.protocol.model.datas.AutoMeasureData;
import com.veepoo.protocol.model.enums.EAutoMeasureType;
import com.veepoo.protocol.model.enums.ESensitivity;

import java.util.List;
import java.util.Locale;

public class AutoMeasureEditActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvAutoMeasureType;
    TextView tvStepUnitSet;
    TextView tvSupportInfo;
    TextView tvLowOxygen;
    EditText etInterval;
    TextView tvSensitivity;
    Button btnSensitivityLow, btnSensitivityStandard, btnSensitivityHigh;
    Button btnStartTimePicker, btnEndTimePicker, btnSetting;
    SwitchView svStatus;
    SwitchView svLowOxygen;

    private int startMinutes, endMinutes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_auto_measure_edit);
        tvAutoMeasureType = findViewById(R.id.tvAutoMeasureType);
        tvStepUnitSet = findViewById(R.id.tvStepUnitSet);
        tvSupportInfo = findViewById(R.id.tvSupportInfo);
        etInterval = findViewById(R.id.etInterval);
        btnStartTimePicker = findViewById(R.id.btnStartTimePicker);
        btnEndTimePicker = findViewById(R.id.btnEndTimePicker);
        svStatus = findViewById(R.id.svStatus);
        tvLowOxygen = findViewById(R.id.tvLowOxygen);
        svLowOxygen = findViewById(R.id.svLowOxygen);
        tvSensitivity = findViewById(R.id.tvSensitivity);
        btnSensitivityLow = findViewById(R.id.btnSensitivityLow);
        btnSensitivityStandard = findViewById(R.id.btnSensitivityStandard);
        btnSensitivityHigh = findViewById(R.id.btnSensitivityHigh);
        btnSetting = findViewById(R.id.btnSetting);

        btnSetting.setOnClickListener(this);
        btnEndTimePicker.setOnClickListener(this);
        btnStartTimePicker.setOnClickListener(this);
        btnSensitivityLow.setOnClickListener(this);
        btnSensitivityStandard.setOnClickListener(this);
        btnSensitivityHigh.setOnClickListener(this);
        initData();
    }

    private String getMeasureTime(AutoMeasureData autoMeasureData){
        int startTime = autoMeasureData.getSupportStartMinute();
        int endTime = autoMeasureData.getSupportEndMinute();
        return String.format(Locale.CHINA,"支持设置的时间段: %02d:%02d-%02d:%02d",
                startTime / 60, startTime % 60,
                endTime / 60, endTime % 60);
    }

    private void initData(){
        if(selectData != null) {
            Logger.t("").e("-initData-: | selectData = " + selectData);
            tvAutoMeasureType.setText(getAutoMeasureDataName(selectData));
            etInterval.setText(selectData.getMeasureInterval()+"");
            tvStepUnitSet.setText("测量间隔（步长"+selectData.getStepUnit()+"m):");
            String supportInfo =
                    getMeasureTime(selectData)
                            + "\n是否支持修改时间段:" + selectData.isSlotModify()
                            + "\n是否支持修改间隔: "+ selectData.isIntervalModify()
                    ;
            tvSupportInfo.setText(supportInfo);
            svStatus.setOpened(selectData.isSwitchOpen());
            //低氧提醒开关显示逻辑：仅Blood Oxygen (SpO2)Displayed when the measurement item is supported by the device (lowOxygenAlertSwitch>=0)，否则隐藏
            //Do not attach OnStateChangedListener (custom listener not called)toggleSwitchThis will cause the click to get stuck in the intermediate state), andsvStatus一致：点击时视图自行切换，保存时读isOpened()
            boolean showLowOxygen = selectData.getFunType() == EAutoMeasureType.BLOOD_OXYGEN
                    && selectData.getLowOxygenAlertSwitch() >= 0;
            tvLowOxygen.setVisibility(showLowOxygen ? View.VISIBLE : View.GONE);
            svLowOxygen.setVisibility(showLowOxygen ? View.VISIBLE : View.GONE);
            if (showLowOxygen) {
                svLowOxygen.setOpened(selectData.getLowOxygenAlertSwitch() == 1);
            }
            //Sensitivity Setting Row Display Logic: Device Returns Excessive Sensitivity(Only displayed if not UNSUPPORTED)；Click to update the data object directly, and it will be sent with the package when saved.
            boolean showSensitivity = selectData.getSensitivity() != ESensitivity.UNSUPPORT;
            tvSensitivity.setVisibility(showSensitivity ? View.VISIBLE : View.GONE);
            if (showSensitivity) {
                refreshSensitivityUI();
            }
            startMinutes = selectData.getCurrentStartMinute();
            endMinutes = selectData.getCurrentEndMinute();
            btnStartTimePicker.setText(String.format(Locale.US, "%02d:%02d", startMinutes/60, startMinutes%60));
            btnEndTimePicker.setText(String.format(Locale.US, "%02d:%02d", endMinutes/60, endMinutes%60));
            etInterval.setEnabled(selectData.isIntervalModify());
        }
    }

    /**Refresh the selected state of the three-level buttons according to the current sensitivity of selectData(Selected items are highlighted, unselected items are semi-transparent)*/
    private void refreshSensitivityUI() {
        ESensitivity cur = selectData.getSensitivity();
        btnSensitivityLow.setAlpha(cur == ESensitivity.LOW ? 1f : 0.4f);
        btnSensitivityStandard.setAlpha(cur == ESensitivity.STANDARD ? 1f : 0.4f);
        btnSensitivityHigh.setAlpha(cur == ESensitivity.HIGH ? 1f : 0.4f);
    }

    @Override
    public void onClick(View v) {
        // 开始时间
        if (v.getId() == R.id.btnStartTimePicker) {
            if (!selectData.isSlotModify()) {
                showMsg("暂不支持设置测量时间段");
                return;
            }
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                startMinutes = hourOfDay * 60 + minute;
                btnStartTimePicker.setText(String.format(Locale.CHINA, "%02d:%02d", hourOfDay, minute));
            }, startMinutes / 60, startMinutes % 60, true).show();
        }

        // 结束时间
        else if (v.getId() == R.id.btnEndTimePicker) {
            if (!selectData.isSlotModify()) {
                showMsg("暂不支持设置测量时间段");
                return;
            }
            new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                endMinutes = hourOfDay * 60 + minute;
                btnEndTimePicker.setText(String.format(Locale.CHINA, "%02d:%02d", hourOfDay, minute));
            }, endMinutes / 60, endMinutes % 60, true).show();
        }

        // Sensitivity level: Click to update the data object，点「设置」时随包下发
        else if (v.getId() == R.id.btnSensitivityLow) {
            selectData.setSensitivity(ESensitivity.LOW);
            refreshSensitivityUI();
        } else if (v.getId() == R.id.btnSensitivityStandard) {
            selectData.setSensitivity(ESensitivity.STANDARD);
            refreshSensitivityUI();
        } else if (v.getId() == R.id.btnSensitivityHigh) {
            selectData.setSensitivity(ESensitivity.HIGH);
            refreshSensitivityUI();
        }

        // 保存设置
        else if (v.getId() == R.id.btnSetting) {
            // 修复：时间范围判断逻辑
            if (selectData.getSupportStartMinute() != 0 || selectData.getSupportEndMinute() != 0) {
                if (startMinutes < selectData.getSupportStartMinute() || endMinutes > selectData.getSupportEndMinute()) {
                    showMsg(getMeasureTime(selectData));
                    return;
                }
            }

            // 结束时间不能早于开始时间
            if (endMinutes <= startMinutes) {
                showMsg("End Time must be greater than start time");
                return;
            }

            String intervalStr = etInterval.getText().toString();
            if(TextUtils.isEmpty(intervalStr)) {
                showMsg("时间间隔不能为空");
                return;
            }
            int interval = Integer.parseInt(intervalStr);
            //if (interval < selectData.getStepUnit() || interval % selectData.getStepUnit() != 0 ) {
            //    showMsg("The interval cannot be less than: " + selectData.getStepUnit() + " minutes，And it is an integer multiple of "+selectData.getStepUnit()+"");
            //    return;
            //}
            selectData.setCurrentStartMinute(startMinutes);
            selectData.setCurrentEndMinute(endMinutes);
            selectData.setMeasureInterval(interval);
            selectData.setSwitchOpen(svStatus.isOpened());
            //The low oxygen reminder switch is issued together with the Blood Oxygen (SpO2) measurement item.；Other measurement items remain -1 (not issued))
            if (selectData.getFunType() == EAutoMeasureType.BLOOD_OXYGEN && selectData.getLowOxygenAlertSwitch() >= 0) {
                selectData.setLowOxygenAlertSwitch(svLowOxygen.isOpened() ? 1 : 0);
            }

            VPOperateManager.getInstance().setAutoMeasureSettingData(new IBleWriteResponse() {
                @Override
                public void onResponse(int code) {}
            }, selectData, new IAutoMeasureSettingDataListener() {
                @Override
                public void onSettingDataChange(List<AutoMeasureData> autoMeasureDataList) {}

                @Override
                public void onSettingDataChangeFail() {
                    showMsg("设置失败");
                }

                @Override
                public void onSettingDataChangeSuccess() {
                    showMsg("设置成功");
                    finish();
                }
            });
        }
    }

    private String getAutoMeasureDataName(AutoMeasureData autoMeasureData){
        switch (autoMeasureData.getFunType()) {
            case PULSE_RATE:
                return "Heart Rate";
            case BLOOD_PRESSURE:
                return "Blood Pressure";
            case BLOOD_GLUCOSE:
                return "Blood Glucose";
            case STRESS:
                return "压力";
            case BLOOD_OXYGEN:
                return "Blood Oxygen (SpO2)";
            case BODY_TEMPERATURE:
                return "Body Temperature";
            case LORENZ:
                return "洛伦兹";
            case HRV:
                return "HRV";
            case BLOOD_COMPOSITION:
                return "血液成分";
            case FALL_DETECTION:
                return "跌倒提醒";
        }
        return "UNKNOWN";
    }

    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}