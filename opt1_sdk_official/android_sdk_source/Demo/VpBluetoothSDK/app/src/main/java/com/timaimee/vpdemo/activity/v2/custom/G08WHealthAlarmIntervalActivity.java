package com.timaimee.vpdemo.activity.v2.custom;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseVPBLETestActivity;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.listener.base.IBleWriteResponse;
import com.veepoo.protocol.listener.data.IHealthAlarmIntervalListener;
import com.veepoo.protocol.model.datas.HealthAlarmInterval;
import com.veepoo.protocol.model.enums.EHealthAlarmType;

public class G08WHealthAlarmIntervalActivity extends BaseVPBLETestActivity {

    private static final String TAG = "G08WHealthAlarmInterval";

    Spinner spinner, spinner1;
    EditText etSX, etXX;
    ToggleButton tbSwitch;
    Button btnSetting, btnRead;
    TextView tvHealthAlarmIntervalInfo;
    String[] data = {"Heart RateAlarm", "Blood Pressure Alarm", "Body TemperatureToo High Alarm", "Blood Oxygen (SpO2) Too Low Alarm"};
    HealthAlarmInterval healthAlarmInterval;

    @Override
    public int getLayoutID() {
        return R.layout.activity_g08w_health_alarm_interval;
    }

    @Override
    public String pageTitle() {
        return "G08W-健康报警区间";
    }

    @Override
    public void initView() {
        spinner = findViewById(R.id.spHealthType);
        spinner1 = findViewById(R.id.spHealthType1);
        etSX = findViewById(R.id.etSX);
        etXX = findViewById(R.id.etXX);
        tbSwitch = findViewById(R.id.tgSwitch);
        btnSetting = findViewById(R.id.btnSetting);
        btnRead = findViewById(R.id.btnRead);
        tvHealthAlarmIntervalInfo = findViewById(R.id.tvHealthAlarmIntervalInfo);
    }

    @Override
    public void initData() {
        initSpinner(spinner);
        initSpinner(spinner1);
    }

    @Override
    public void initEvent() {
        btnSetting.setOnClickListener(v -> setHealthAlarmInterval2Device());
        btnRead.setOnClickListener(v -> readHealthAlarmIntervalFDevice());
    }

    private void initSpinner(Spinner sp) {
        //Declare an array adapter for a drop-down list
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this, R.layout.item_select, data);
        //设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //Get the drop-down box named sp_dialog from the layout file
        //Set the title of the drop-down box. If not set, there will be no unsightly title.
        sp.setPrompt("请选择行星");
        //设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        //Set the default display of the first item in the drop-down box
        sp.setSelection(0);
        //Set a selection listener for the drop-down box. Once the user selects an item,，Triggers the onItemSelected method of the listener
        sp.setOnItemSelectedListener(new MySelectedListener(sp));
    }

    private HealthAlarmInterval getHealthAlarmInterval() {
        int selectIndex = spinner.getSelectedItemPosition();
        EHealthAlarmType eHealthAlarmType = EHealthAlarmType.Companion.getEHealthAlarmTypeWithCMD((byte) selectIndex);
        float ceilingValue = 0;
        float floorValue = 0;
        String sxStr = etSX.getText().toString();
        String xxStr = etXX.getText().toString();
        if (!TextUtils.isEmpty(sxStr)) {
            ceilingValue = Float.parseFloat(sxStr);
        }
        if (!TextUtils.isEmpty(xxStr)) {
            floorValue = Float.parseFloat(xxStr);
        }
        boolean isOpen = tbSwitch.isChecked();
        if (selectIndex == 3) {
            //无上限
            ceilingValue = 0;
        }
        return new HealthAlarmInterval(eHealthAlarmType, ceilingValue, floorValue, isOpen);
    }

    public void setHealthAlarmInterval2Device() {
        HealthAlarmInterval health = getHealthAlarmInterval();
        VPOperateManager.getInstance().setHealthAlarmInterval(health, new IBleWriteResponse() {
            @Override
            public void onResponse(int code) {

            }
        }, new IHealthAlarmIntervalListener() {
            @Override
            public void functionNotSupport() {

            }

            @Override
            public void onHealthAlarmIntervalReadSuccess(@NonNull HealthAlarmInterval data, boolean isOptSuccess) {

            }

            @Override
            public void onHealthAlarmIntervalSetting(@NonNull HealthAlarmInterval data, boolean isOptSuccess) {
                if (isOptSuccess) {
                    Logger.t(TAG).i("设置成功-" + data.toString());
                    tvHealthAlarmIntervalInfo.setText("设置成功-" + data.toString());
                } else {
                    Logger.t(TAG).i("设置失败-" + data.toString());
                    tvHealthAlarmIntervalInfo.setText("设置失败-" + data.toString());
                }
            }
        });
    }

    public void readHealthAlarmIntervalFDevice() {
        int selectIndex = spinner1.getSelectedItemPosition();
        EHealthAlarmType eHealthAlarmType = EHealthAlarmType.Companion.getEHealthAlarmTypeWithCMD((byte) selectIndex);
        VPOperateManager.getInstance().readHealthAlarmInterval(eHealthAlarmType, new IBleWriteResponse() {
            @Override
            public void onResponse(int code) {

            }
        }, new IHealthAlarmIntervalListener() {
            @Override
            public void functionNotSupport() {

            }

            @Override
            public void onHealthAlarmIntervalReadSuccess(@NonNull HealthAlarmInterval data, boolean isOptSuccess) {
                if (isOptSuccess) {
                    Logger.t(TAG).i("读取成功-" + data.toString());
                    tvHealthAlarmIntervalInfo.setText("读取成功-" + data.toString());
                } else {
                    Logger.t(TAG).i("读取失败-" + data.toString());
                    tvHealthAlarmIntervalInfo.setText("读取失败-" + data.toString());
                }
            }

            @Override
            public void onHealthAlarmIntervalSetting(@NonNull HealthAlarmInterval data, boolean isOptSuccess) {

            }
        });
    }


    class MySelectedListener implements AdapterView.OnItemSelectedListener {

        Spinner sp;

        MySelectedListener(Spinner spinner) {
            this.sp = spinner;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (sp == spinner) {
                Toast.makeText(G08WHealthAlarmIntervalActivity.this, "您选择的设置是：" + data[i], Toast.LENGTH_SHORT).show();
                if (i == 2) {
                    etSX.setText("");
                    etSX.setHint("请输入高温报警上限值");
                    etSX.setHintTextColor(Color.GRAY);
                    etSX.setEnabled(true);
                    etSX.setFocusable(true);

                    etXX.setText("");
                    etXX.setEnabled(false);
                    etXX.setHint("高温报警下限值无需设置");
                    etXX.setHintTextColor(Color.RED);

                } else if (i == 3) {
                    etSX.setText("");
                    etSX.setHint("No need to set the upper limit for the low oxygen level alarm");
                    etSX.setHintTextColor(Color.RED);
                    etSX.setEnabled(false);

                    etXX.setEnabled(true);
                    etXX.setHint("Please enter the lower limit for the low oxygen level alarm");
                    etXX.setHintTextColor(Color.GRAY);
                    etXX.setFocusable(true);
                } else {
                    etSX.setFocusable(true);
                    etSX.setEnabled(true);
                    etXX.setEnabled(true);
                    etSX.setText("");
                    etXX.setText("");
                    etSX.setHint("");
                    etXX.setHint("");
                }


            }
            if (sp == spinner1) {
                Toast.makeText(G08WHealthAlarmIntervalActivity.this, "您选择的读取是：" + data[i], Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}
