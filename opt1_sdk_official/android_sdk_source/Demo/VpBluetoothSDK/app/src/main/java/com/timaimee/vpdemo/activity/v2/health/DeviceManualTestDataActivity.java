package com.timaimee.vpdemo.activity.v2.health;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseVPBLETestActivity;
import com.timaimee.vpdemo.activity.v2.DeviceMenu;
import com.timaimee.vpdemo.utils.CollapseCardLogView;
import com.timaimee.vpdemo.utils.TimeUtils;
import com.veepoo.protocol.listener.data.IDeviceManualDetectDataListener;
import com.veepoo.protocol.model.datas.BloodComponent;
import com.veepoo.protocol.model.datas.BloodComponentManualData;
import com.veepoo.protocol.model.datas.BloodGlucoseManualData;
import com.veepoo.protocol.model.datas.BloodOxygenManualData;
import com.veepoo.protocol.model.datas.BloodPressureManualData;
import com.veepoo.protocol.model.datas.BodyTemperatureManualData;
import com.veepoo.protocol.model.datas.EmotionManualData;
import com.veepoo.protocol.model.datas.FatigueManualData;
import com.veepoo.protocol.model.datas.HeartRateManualData;
import com.veepoo.protocol.model.datas.HrvManualData;
import com.veepoo.protocol.model.datas.MetoManualData;
import com.veepoo.protocol.model.datas.MiniCheckupManualData;
import com.veepoo.protocol.model.datas.PressureManualData;
import com.veepoo.protocol.model.datas.SkinConductanceManualData;
import com.veepoo.protocol.model.enums.DeviceManualDataType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DeviceManualTestDataActivity extends BaseVPBLETestActivity implements IDeviceManualDetectDataListener{
    CollapseCardLogView ccvGetDeviceManualTestData;
    CheckBox cbBloodPress, cbHeartRate, cbBloodGlucose, cbStress, cbBloodOxygen, cbBodyTemperature,
            cbMeto, cbHrv, cbBloodComponent, cbMiniCheckup, cbEmotion, cbFatigue, cbEDA, cbAll;
    Button btnGetDeviceManualTestData, btnDatePicker, btnTimePicker;

    @Override
    public int getLayoutID() {
        return R.layout.activity_device_manual_test_data;
    }

    @Override
    public String pageTitle() {
        return DeviceMenu.Health.DeviceManualTestData;
    }

    @Override
    public void initView() {
        ccvGetDeviceManualTestData = findViewById(R.id.ccvGetDeviceManualTestData);
        cbBloodPress = findViewById(R.id.cbBloodPress);
        cbHeartRate = findViewById(R.id.cbHeartRate);
        cbBloodGlucose = findViewById(R.id.cbBloodGlucose);
        cbStress = findViewById(R.id.cbStress);
        cbBloodOxygen = findViewById(R.id.cbBloodOxygen);
        cbBodyTemperature = findViewById(R.id.cbBodyTemperature);
        cbMeto = findViewById(R.id.cbMeto);
        cbHrv = findViewById(R.id.cbHrv);
        cbBloodComponent = findViewById(R.id.cbBloodComponent);
        cbMiniCheckup = findViewById(R.id.cbMiniCheckup);
        cbEmotion = findViewById(R.id.cbEmotion);
        cbFatigue = findViewById(R.id.cbFatigue);
        cbEDA = findViewById(R.id.cbEDA);
        cbAll = findViewById(R.id.cbAll);
        btnGetDeviceManualTestData = findViewById(R.id.btnGetDeviceManualTestData);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
    }

    @Override
    public void initData() {
        btnDatePicker.setText(TimeUtils.getCurrentDateStr());
        btnTimePicker.setText("00:00:00");
    }

    @Override
    public void initEvent() {
        btnGetDeviceManualTestData.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnDatePicker) {
            showDatePicker();
        } else if (id == R.id.btnTimePicker) {
            showTimePicker();
        } else if (id == R.id.btnGetDeviceManualTestData) {
            getDeviceManualTestData();
        }
    }

    private void showDatePicker() {
        // 1. 获取按钮当前的文本
        String currentText = btnDatePicker.getText().toString().trim();
        // 2. Initialize default year, month, and day (downgrade plan)：当前系统时间）
        Calendar calendar = Calendar.getInstance();
        int defYear = calendar.get(Calendar.YEAR);
        int defMonth = calendar.get(Calendar.MONTH); // Note: Calendar months from 0 开始
        int defDay = calendar.get(Calendar.DAY_OF_MONTH);

        // 3. 尝试解析按钮上的 "yyyy-MM-dd"
        if (currentText.contains("-")) {
            String[] parts = currentText.split("-");
            if (parts.length == 3) {
                try {
                    defYear = Integer.parseInt(parts[0]);
                    defMonth = Integer.parseInt(parts[1]) - 1; // Converted to Calendar 0-11 月
                    defDay = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // If parsing fails, the system's current time will be retained.
                }
            }
        }

        // 4. Pop up the selector and pass in the parsed default value
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            // 更新按钮显示的文本
            String selectedDate = String.format(Locale.CHINA, "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            btnDatePicker.setText(selectedDate);

        }, defYear, defMonth, defDay).show();
    }

    private void showTimePicker() {
        // 1. 获取按钮当前的文本
        String currentText = btnTimePicker.getText().toString().trim();
        // 2. Initialize default time (fallback scheme)：当前系统时间）
        Calendar calendar = Calendar.getInstance();
        int defHour = calendar.get(Calendar.HOUR_OF_DAY);
        int defMin = calendar.get(Calendar.MINUTE);

        // 3. Try parsing "HH:mm:ss" on the button or "HH:mm"
        if (currentText.contains(":")) {
            String[] parts = currentText.split(":");
            if (parts.length >= 2) {
                try {
                    defHour = Integer.parseInt(parts[0]);
                    defMin = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace(); // If parsing fails, the system's current time will be retained.
                }
            }
        }
        // 4. Pop up the selector and pass in the parsed default value
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            // Always output the second-level suffix as "HH:mm:00"
            String selectedTime = String.format(Locale.CHINA, "%02d:%02d:00", hourOfDay, minute);
            btnTimePicker.setText(selectedTime);
        }, defHour, defMin, true).show();
    }

    /**
     * Get the combined second-level timestamp based on the text of the date and time buttons.
     * * @param dateBtnText 日期按钮的文本 (例如: "2026-05-27")
     * @param timeBtnText 时间按钮的文本 (例如: "15:30:00")
     * @return 秒级时间戳（10位）。If parsing fails, the current system timestamp is returned by default.。
     */
    public long getSelectedEpochSecond(String dateBtnText, String timeBtnText) {
        // 1. Null check to prevent button text anomalies
        if (dateBtnText == null || dateBtnText.trim().isEmpty() ||
                timeBtnText == null || timeBtnText.trim().isEmpty()) {
            return System.currentTimeMillis() / 1000; // 降级返回当前系统时间戳
        }

        // 2. Concatenate the text of the two buttons into a complete date and time string
        // 例如: "2026-05-27" + " " + "15:30:00" -> "2026-05-27 15:30:00"
        String fullDateTimeStr = dateBtnText.trim() + " " + timeBtnText.trim();

        try {
            // 3. Use a thread-safe classic formatter (or directly new SimpleDateFormat）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

            // 4. 解析为 Date 对象
            Date date = sdf.parse(fullDateTimeStr);

            if (date != null) {
                // 5. date.getTime() 返回的是毫秒级(13位)，Divide by 1000 to get a timestamp in seconds(10位)
                return date.getTime() / 1000;
            }
        } catch (Exception e) {
            e.printStackTrace(); // 解析异常（比如格式不匹配）
        }

        // If an error occurs midway, return the current system timestamp to be on the safe side.
        return System.currentTimeMillis() / 1000;
    }

    private void getDeviceManualTestData() {
        List<DeviceManualDataType> types = getDeviceManualTypeList();
        if (types.isEmpty()) {
            showToast("请至少勾选一个");
            return;
        }
        long startReadTimestamp = getSelectedEpochSecond(btnDatePicker.getText().toString(), btnTimePicker.getText().toString());
        vpBleManager.readDeviceManualData(defaultResponse, startReadTimestamp, types, types, this);
    }

    private List<DeviceManualDataType> getDeviceManualTypeList() {
        List<DeviceManualDataType> types = new ArrayList<>();
        if (cbAll.isChecked()) {
            types.add(DeviceManualDataType.ALL);
            return types;
        }
        if (cbBloodPress.isChecked()) {
            types.add(DeviceManualDataType.BLOOD_PRESSURE);
        }
        if (cbHeartRate.isChecked()) {
            types.add(DeviceManualDataType.HEART_RATE);
        }
        if (cbBloodGlucose.isChecked()) {
            types.add(DeviceManualDataType.BLOOD_GLUCOSE);
        }
        if (cbStress.isChecked()) {
            types.add(DeviceManualDataType.STRESS);
        }
        if (cbBloodOxygen.isChecked()) {
            types.add(DeviceManualDataType.BLOOD_OXYGEN);
        }
        if (cbBodyTemperature.isChecked()) {
            types.add(DeviceManualDataType.BODY_TEMPERATURE);
        }
        if (cbMeto.isChecked()) {
            types.add(DeviceManualDataType.MET);
        }
        if (cbHrv.isChecked()) {
            types.add(DeviceManualDataType.HRV);
        }
        if (cbBloodComponent.isChecked()) {
            types.add(DeviceManualDataType.BLOOD_COMPOSITION);
        }
        if (cbMiniCheckup.isChecked()) {
            types.add(DeviceManualDataType.MINI_CHECKUP);
        }
        if (cbEmotion.isChecked()) {
            types.add(DeviceManualDataType.EMOTION);
        }
        if (cbFatigue.isChecked()) {
            types.add(DeviceManualDataType.FATIGUE);
        }
        if (cbEDA.isChecked()) {
            types.add(DeviceManualDataType.SKIN_CONDUCTANCE);
        }
        return types;
    }

    @Override
    public void onBloodPressureDataChange(List<BloodPressureManualData> bloodPressureManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取Blood Pressure=====");
        for (BloodPressureManualData data : bloodPressureManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText(getSimpleBloodPressureManualData(data));
        }
    }

    private String getSimpleBloodPressureManualData(BloodPressureManualData data){
        return "▶ [Blood Pressure]>" + timeFormat(data.getTimeStamp()) + " : Systolic="+data.getSystolic() + ",低压="+data.getDiastolic();
    }

    @Override
    public void onHeartRateDataChange(List<HeartRateManualData> heartRateManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取Heart Rate=====");
        for (HeartRateManualData data : heartRateManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Heart Rate]>" + timeFormat(data.getTimeStamp()) + " : Heart Rate=" + data.getRate());
        }
    }

    @Override
    public void onBloodGlucoseDataChange(List<BloodGlucoseManualData> bloodGlucoseManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取Blood Glucose=====");
        for (BloodGlucoseManualData data : bloodGlucoseManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Heart Rate]>" + timeFormat(data.getTimeStamp()) + " : Blood Glucose Level=" + data.getBloodGlucoseValue()+"mmol/L");
        }
    }

    @Override
    public void onPressureManualDataChange(List<PressureManualData> pressureManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取压力=====");
        for (PressureManualData data : pressureManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Heart Rate]>" + timeFormat(data.getTimeStamp()) + " : Index=" + data.getPressure());
        }
    }

    @Override
    public void onBloodOxygenDataChange(List<BloodOxygenManualData> bloodOxygenManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取Blood Oxygen (SpO2)=====");
        for (BloodOxygenManualData data : bloodOxygenManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Blood Oxygen (SpO2)]>" + timeFormat(data.getTimeStamp()) + " : " + Arrays.toString(data.getOxygen()));
        }
    }

    @Override
    public void onBodyTemperatureDataChange(List<BodyTemperatureManualData> bodyTemperatureManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取Body Temperature=====");
        for (BodyTemperatureManualData data : bodyTemperatureManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Body Temperature]>" + timeFormat(data.getTimeStamp()) + " : Body Temperature=" + data.getTemperature() + ", 皮肤温度=" + data.getBaseTemperature());
        }
    }

    @Override
    public void onMetoManualDataChange(List<MetoManualData> metoManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取MET=====");
        for (MetoManualData data : metoManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [MET]>" + timeFormat(data.getTimeStamp()) + " : 梅脱=" + data.getMeto());
        }
    }

    @Override
    public void onHrvManualDataChange(List<HrvManualData> hrvManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取HRV=====");
        for (HrvManualData data : hrvManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [HRV]>" + timeFormat(data.getTimeStamp()) + " : " + Arrays.toString(data.getHrv()));
        }
    }

    @Override
    public void onBloodComponentManualDataChange(List<BloodComponentManualData> bloodComponentManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取血液成分=====");
        for (BloodComponentManualData data : bloodComponentManualDataList) {
            BloodComponent bloodComponent = new BloodComponent(data.getUricAcid(), data.gettCHO(), data.getUricAcid(), data.gethDL(), data.gethDL());
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [血液成分]>" + timeFormat(data.getTimeStamp()) + " : " + bloodComponent.toString());
        }
    }

    @Override
    public void onMiniCheckupManualDataChange(List<MiniCheckupManualData> miniCheckupManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取微体检=====");
        for (MiniCheckupManualData data : miniCheckupManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText(getSimpleMiniCheckupData(data));
        }
    }

    private String getSimpleMiniCheckupData(MiniCheckupManualData data){
        return "▶ [Mini Checkup]>" + timeFormat(data.getTimeStamp()) + " : Heart Rate="+data.getHeart()
                + ",Blood Oxygen (SpO2)="+data.getOxygen()
                + ",Blood Glucose="+data.getBloodGlucose()
                + ",高压="+data.getHighValue()
                + ",低压="+data.getLowValue()
                + ",HRV="+data.getHrv()
                + ",压力="+data.getPressure()
                + ",情绪="+data.getEmotion()
                + ",疲劳度="+data.getFatigue()
                ;
    }

    @Override
    public void onEmotionManualDataChange(List<EmotionManualData> emotionManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取情绪=====");
        for (EmotionManualData data : emotionManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Sentiment]>" + timeFormat(data.getTimeStamp()) + " : Index=" + data.getEmotion());
        }
    }

    @Override
    public void onFatigueManualDataChange(List<FatigueManualData> fatigueManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取疲劳度=====");
        for (FatigueManualData data : fatigueManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText("▶ [Fatigue Level]>" + timeFormat(data.getTimeStamp()) + " : Index=" + data.getFatigue());
        }
    }

    @Override
    public void onSkinConductanceManualDataChange(List<SkinConductanceManualData> skinConductanceManualDataList) {
        ccvGetDeviceManualTestData.appendWhiteText("=====获取皮电=====");
        for (SkinConductanceManualData data : skinConductanceManualDataList) {
            ccvGetDeviceManualTestData.appendBlueMiddleText(getSimpleSkinConductanceManualData(data));
        }
    }

    private String getSimpleSkinConductanceManualData(SkinConductanceManualData data){
        return "▶ [皮电]>" + timeFormat(data.getTimeStamp()) + " : " +
                "情绪="+data.getEmotionLevel()
                + ",皮肤含水量="+data.getSkinMoisture()
                + ",抑郁症风险="+data.getDepressionRisk()
                + ",交感神经活跃度="+data.getSnsActivation()
                + ",皮质醇浓度="+data.getCortisolValue()
                ;
    }

    @Override
    public void onReadProgress(float progress) {
        ccvGetDeviceManualTestData.appendOrangeText("▶ [读取进度]:"+progress+"%");
    }

    @Override
    public void onReadComplete() {
        ccvGetDeviceManualTestData.appendOrangeText("✅️ [手动测量数据] 读取完成！");
    }

    @Override
    public void onReadFail() {
        ccvGetDeviceManualTestData.appendRedLargeText("❌ [Manual Measurement Data] Read failed！");
    }

    @Override
    public void onCMDWriteFailed(int cmdTag) {
        super.onCMDWriteFailed(cmdTag);
        ccvGetDeviceManualTestData.appendRedLargeText("❌ [[Manual Measurement Data] Read command write failed！");
    }

    @Override
    public void onCMDWriteSuccess(int cmdTag) {
        super.onCMDWriteSuccess(cmdTag);
        ccvGetDeviceManualTestData.appendOrangeText("✅️ [[Manual Measurement Data] Read command written successfully！");
    }

    private String timeFormat(int timestamp) {
        return TimeUtils.secondsTimestampFormat(timestamp);
    }
}
