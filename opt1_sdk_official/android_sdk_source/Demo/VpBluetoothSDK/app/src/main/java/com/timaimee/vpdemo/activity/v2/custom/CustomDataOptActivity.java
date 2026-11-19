package com.timaimee.vpdemo.activity.v2.custom;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.inuker.bluetooth.library.Code;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseActivity;
import com.veepoo.protocol.listener.data.ICustomDataOptListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Customer-Defined Transparent Transmission Protocol (0x04) Demo Page
 * <p>
 * 1. 通过“添加指令”Edit hexadecimal instructions one by one in the pop-up window (scroll wheel selection) 0-F 组合成一个 byte）；
 * 2. The input parameter of the sending interface is List&lt;byte[]&gt;, and the SDK only checks whether the protocol header is 0x04，不做协议组装；
 * 3. The 0x04 data reported by the device and ADC Data is returned as-is, and business parsing is done locally. Demo 中实现。
 * <p>
 * Demo of the customer custom pass-through protocol(0x04):
 * commands are edited one by one in a dialog (0-F wheel pickers compose a byte),
 * the SDK only checks the 0x04 head and passes the data through.
 */
public class CustomDataOptActivity extends BaseActivity implements ICustomDataOptListener {

    /**
     * 出现的十六进制字符
     * The hex characters displayed by the wheel pickers
     */
    private static final String[] HEX_VALUES = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "A", "B", "C", "D", "E", "F"};

    /**.
     * 新建指令的默认开始内容
     * The default start content of a new command
     */
    private static final byte[] DEFAULT_CMD = {(byte) 0x04};

    private TextView tvCmdList;
    private Button btnAddCmd;
    private Button btnSend;
    private Button btnClearCmd;
    private Button btnClearLog;
    private Button btnDeleteCmd;

    /**
     * 编辑好的指令列表
     * The commands edited by the user
     */
    private final List<byte[]> cmdList = new ArrayList<>();

    @Override
    public int getLayoutID() {
        return R.layout.activity_custom_passthrough;
    }

    @Override
    public String pageTitle() {
        return "客户自定义透传(0x04)";
    }

    @Override
    public void initView() {
        tvCmdList = findViewById(R.id.tvCmdList);
        btnAddCmd = findViewById(R.id.btnAddCmd);
        btnSend = findViewById(R.id.btnSend);
        btnClearCmd = findViewById(R.id.btnClearCmd);
        btnClearLog = findViewById(R.id.btnClearLog);
        btnDeleteCmd = findViewById(R.id.btnDeleteCmd);
    }

    @Override
    public void initData() {
        vpBleManager.setCustomDataOptListener(this);
        appendResult("Maximum length of a single command: " + vpBleManager.getCustomPassthroughMaxLength() + " bytes");
        appendResult("说明：SDK 只校验 0x04 协议头，字段解析在 Demo 中实现");
        refreshCmdList();
    }

    @Override
    public void initEvent() {
        btnAddCmd.setOnClickListener(v -> showAddCmdDialog(DEFAULT_CMD));
        btnSend.setOnClickListener(v -> sendCmdList());
        btnClearCmd.setOnClickListener(v -> {
            cmdList.clear();
            refreshCmdList();
        });
        btnClearLog.setOnClickListener(v -> clearTestInfo());
        btnDeleteCmd.setOnClickListener(view -> removeLastCmd());
    }

    private void removeLastCmd(){
        if (cmdList.isEmpty()) {
            return;
        }
        cmdList.remove(cmdList.size() - 1);
        refreshCmdList();
    }

    @Override
    public boolean hasCommonMsgUI() {
        return true;
    }

    @Override
    protected void onDestroy() {
        vpBleManager.removeCustomDataOptListener();
        super.onDestroy();
    }

    /**
     * 弹出单条指令编辑弹框
     * Show the dialog used to edit a single command
     *
     * @param defaultCmd 默认的开始内容，例如 {0x04} 表示以 0x04 开头 / the default start content, e.g. {0x04} means starting with 0x04
     */
    private void showAddCmdDialog(byte[] defaultCmd) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_custom_cmd, null);
        TextView tvHex = dialogView.findViewById(R.id.tvDialogHex);
        NumberPicker npHigh = dialogView.findViewById(R.id.npHigh);
        NumberPicker npLow = dialogView.findViewById(R.id.npLow);
        Button btnAddByte = dialogView.findViewById(R.id.btnAddByte);
        ImageButton btnDeleteByte = dialogView.findViewById(R.id.btnDeleteByte);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);
        Button btnDone = dialogView.findViewById(R.id.btnDone);

        initNumberPicker(npHigh);
        initNumberPicker(npLow);

        //当前正在编辑的指令内容 / the command being edited
        final List<Byte> editingCmd = new ArrayList<>();
        if (defaultCmd != null) {
            for (byte b : defaultCmd) {
                editingCmd.add(b);
            }
        }
        tvHex.setText(bytesToHex(editingCmd));

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();
        dialog.setCanceledOnTouchOutside(false);

        //添加一个 byte / add a byte
        btnAddByte.setOnClickListener(v -> {
            int value = (npHigh.getValue() << 4) | npLow.getValue();
            editingCmd.add((byte) value);
            tvHex.setText(bytesToHex(editingCmd));
        });
        //回退：删除最后一个 byte / backspace: remove the last byte
        btnDeleteByte.setOnClickListener(v -> {
            if (editingCmd.isEmpty()) {
                return;
            }
            editingCmd.remove(editingCmd.size() - 1);
            tvHex.setText(bytesToHex(editingCmd));
        });
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnDone.setOnClickListener(v -> {
            if (editingCmd.isEmpty()) {
                Toast.makeText(this, "指令内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            cmdList.add(toByteArray(editingCmd));
            refreshCmdList();
            dialog.dismiss();
        });
        dialog.show();
    }

    /**
     * Initialize 0-F Wheel Selector
     * Init the 0-F wheel picker
     */
    private void initNumberPicker(NumberPicker numberPicker) {
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(15);
        numberPicker.setDisplayedValues(HEX_VALUES);
        numberPicker.setValue(0);
        numberPicker.setWrapSelectorWheel(true);
    }

    /**
     * 刷新指令列表展示
     * Refresh the command list
     */
    private void refreshCmdList() {
        if (cmdList.isEmpty()) {
            tvCmdList.setText("暂未添加指令");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cmdList.size(); i++) {
            sb.append("Item ").append(i + 1).append("(").append(cmdList.get(i).length).append("字节)：")
                    .append(byte2Hex(cmdList.get(i)));
            if (i != cmdList.size() - 1) {
                sb.append("\n");
            }
        }
        tvCmdList.setText(sb.toString());
    }

    /**
     * 发送列表中全部透传指令
     * Send all the commands in the list
     */
    private void sendCmdList() {
        if (cmdList.isEmpty()) {
            appendRedLargeText("请先添加指令");
            return;
        }
        List<byte[]> sendList = new ArrayList<>(cmdList);
        appendResult("Preparing to send " + sendList.size() + " commands");
        vpBleManager.sendCustomData(code -> runOnUiThread(() -> {
            if (code == Code.REQUEST_SUCCESS) {
                appendResult("单条指令写入成功");
            } else {
                appendRedLargeText("单条指令写入失败,code=" + code);
            }
        }), sendList);
    }

    /**
     * Received 0x04 data reported by the device（SDK 原样透传）
     * Receive the 0x04 data reported by the device (passed through by the SDK)
     */
    @Override
    public void onPassthroughData(byte[] data) {
        runOnUiThread(() -> {
            if (data == null || data.length == 0 || data[0] != (byte) 0x04) {
                appendRedLargeText("数据不合法：不是以0x04为协议头的数据");
                return;
            }
            appendBlueMiddleText("收到0x04数据(" + data.length + "字节)：" + byte2Hex(data));
        });
    }

    @Override
    public void onAdcData(byte[] data) {
        runOnUiThread(() -> {
            if (data == null || data.length == 0 || data[0] != (byte) 0x04) {
                appendRedLargeText("数据不合法：不是以0x04为协议头的数据");
                return;
            }
            appendBlueMiddleText("ADC收到0x04数据(" + data.length + "字节)：" + byte2Hex(data));
        });
    }

    /**
     * 发送/校验失败，errorCode See  in ICustomPassthroughListener ERROR 常量
     * Sending / checking failed, see the ERROR constants of ICustomPassthroughListener for the errorCode
     */
    @Override
    public void onError(int errorCode, String errorMsg) {
        runOnUiThread(() -> appendRedLargeText("发送失败[" + errorCode + "]：" + errorMsg));
    }

    /**
     * 全部指令发送成功
     * All the commands have been sent successfully
     */
    @Override
    public void onSendComplete() {
        runOnUiThread(() -> appendResult("全部指令发送成功"));
    }

    /**
     * byte 列表转十六进制字符串
     * Convert a byte list to a hex string
     */
    private String bytesToHex(List<Byte> bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.size(); i++) {
            sb.append(String.format("%02X", bytes.get(i)));
            if (i != bytes.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * byte 列表转 byte 数组
     * Convert a byte list to a byte array
     */
    private byte[] toByteArray(List<Byte> bytes) {
        byte[] data = new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            data[i] = bytes.get(i);
        }
        return data;
    }

    /**
     * Convert Byte Array to Hexadecimal String
     * Convert a byte array to a hex string
     */
    private String byte2Hex(byte[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

    @Override
    public void onClick(View view) {
    }
}
