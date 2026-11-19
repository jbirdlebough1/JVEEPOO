package com.timaimee.vpdemo.activity.v2.other;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.iielse.switchbutton.SwitchView;
import com.timaimee.vpdemo.R;
import com.timaimee.vpdemo.activity.v2.BaseActivity;
import com.timaimee.vpdemo.adapter.GameClockAdapter;
import com.timaimee.vpdemo.utils.CollapseCardLogView;
import com.timaimee.vpdemo.utils.TimeUtils;
import com.veepoo.protocol.listener.data.IGameClockOptListener;
import com.veepoo.protocol.model.datas.GameClockBean;
import com.veepoo.protocol.model.enums.EGameClockType;
import com.veepoo.protocol.model.enums.EGameClockWeekday;
import com.veepoo.protocol.shareprence.VpSpGetUtil;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/**
 * Game Controls (ZT79 Customized)）Demo。
 * <p>
 * 演示 SDK 的 5 个接口：
 * <ol>
 *     <li>readZT79GameClocks —— Read Master Switch + Time Period List</li>
 *     <li>setZT79GameClockMainSwitch —— 设置总开关</li>
 *     <li>setZT79GameClocks —— 添加 / 修改（单个 / 批量）</li>
 *     <li>deleteZT79GameClock —— 删除单个（右滑）</li>
 *     <li>deleteZT79GameClockWithIDs —— 按 id 批量删除（清空全部）</li>
 * </ol>
 * <p>
 * 页面分区（解决“一屏塞满”的问题）：
 * <pre>
 * ① 状态栏   总开关 / 读取 / 清空  —— 始终可见
 * ② 配置卡   时段表单             —— 可折叠，收起时不占空间
 * ③ 列表     时段列表             —— 自适应剩余高度
 * ④ 日志卡   运行日志             —— 可折叠，220dp independent scrolling when expanded
 * </pre>
 * The configuration tab and the log tab are mutually exclusive, ensuring that the list always has visible height.。
 * <p>
 * 注意：SDK 不做持久化，The {@link #mClocks} maintained on this page and {@link #mMainSwitchState} 就是“宿主侧状态”，
 * Each time it is issued, the "full list after the operation takes effect"」Send to the device for CRC consistency check；
 * Roll back the local state according to the recommended sequence when a failure occurs。
 * <p>
 * The SDK is stateless: the list and switch kept in this page are the host-side state. Every
 * request passes the post-operation list for the device-side CRC check, and rolls back on failure.
 */
public class GameClockActivity extends BaseActivity
        implements GameClockAdapter.OnGameClockActionListener {

    private static final String TAG = GameClockActivity.class.getSimpleName();

    /**
     * Spinner 中展示的类型顺序，与 {@link #TYPES} 一一对应
     */
    private static final EGameClockType[] TYPES = {
            EGameClockType.WEEKLY_REPEAT,
            EGameClockType.SINGLE,
            EGameClockType.TEMPORARY
    };

    /**
     * Host-side time slot list (equal to the full amount of data to be stored on the device side)）
     * Host-side clock list, i.e. the full data the device should hold.
     */
    private final List<GameClockBean> mClocks = new ArrayList<>();

    /**
     * Host-side master switch status; null indicates that it has never been synchronized with the device
     * Host-side main switch, null means never synced.
     */
    private Boolean mMainSwitchState = null;

    /**
     * The time slot ID being edited; -1 indicates a new entry
     * Clock id under edition, -1 for a new one.
     */
    private int mEditingId = -1;

    private boolean supported = true;

    /**
     * Set to  when programmatically refreshing the Switch true，避免触发一次假回调
     */
    private boolean ignoreMainSwitchCallback = false;

    private SwipeRecyclerView rvGameClock;
    private GameClockAdapter mAdapter;

    private SwitchView svMainSwitch;
    private TextView tvMainSwitchState;
    private TextView tvEditHint;
    private TextView tvListHeader;
    private TextView tvListEmpty;
    private TextView btnAddClock;

    /**
     * Time Period Configuration Card: Internal Log Area Hidden，只用来收纳表单
     */
    private CollapseCardLogView ccvConfig;

    /**
     * Execution Log Card: All callback results are written here.
     */
    private CollapseCardLogView ccvLog;

    private Spinner spClockType;

    /**
     * 时段 id 展示位：Read-only, automatically assigned by code from 1 to 10, to avoid errors caused by manual entry id 重复
     * Read-only id field: auto-assigned from 1~10 so duplicates are impossible.
     */
    private TextView tvFormId;
    private EditText etDuration;
    private Button btnDate;
    private Button btnTime;
    private CheckBox cbClockOpen;
    private LinearLayout llWeekdays;

    private final CheckBox[] cbWeekdays = new CheckBox[7];

    @Override
    public int getLayoutID() {
        return R.layout.activity_game_clock;
    }

    @Override
    public String pageTitle() {
        return "游戏控制";
    }

    /**
     * This page no longer uses the fixed part in BaseActivity. tvTestInfo，日志统一走 {@link #ccvLog}
     * This page logs into {@link #ccvLog} instead of the built-in tvTestInfo.
     */
    @Override
    public boolean hasCommonMsgUI() {
        return false;
    }

    // ==================================================================================
    // 生命周期
    // ==================================================================================

    @Override
    public void initView() {
        rvGameClock = findViewById(R.id.rvGameClock);
        svMainSwitch = findViewById(R.id.svMainSwitch);
        tvMainSwitchState = findViewById(R.id.tvMainSwitchState);
        tvEditHint = findViewById(R.id.tvEditHint);
        tvListHeader = findViewById(R.id.tvListHeader);
        tvListEmpty = findViewById(R.id.tvListEmpty);
        btnAddClock = findViewById(R.id.btnAddClock);
        ccvConfig = findViewById(R.id.ccvConfig);
        ccvLog = findViewById(R.id.ccvLog);

        // The configuration card only accommodates forms and does not require a built-in console.，After hiding, the content will automatically move up to align with the title.
        // The config card holds the form only, so hide its built-in console
        ccvConfig.setLogAreaVisible(false);

        spClockType = findViewById(R.id.spClockType);
        tvFormId = findViewById(R.id.tvFormId);
        etDuration = findViewById(R.id.etDuration);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        cbClockOpen = findViewById(R.id.cbClockOpen);
        llWeekdays = findViewById(R.id.llWeekdays);

        setupWeekdayViews();
        setupTypeSpinner();
    }

    @Override
    public void initData() {
        supported = VpSpGetUtil.getVpSpVariInstance(this).isSupportGameClock();
        initListView();
        resetForm();
        refreshList();
        // Read the device after the first frame to avoid competing with list measurements for the main thread.
        rvGameClock.post(this::afterUiReady);
    }

    @Override
    public void initEvent() {
        findViewById(R.id.btnRead).setOnClickListener(v -> readGameClocks());
        findViewById(R.id.btnSaveClock).setOnClickListener(v -> saveClock());
        findViewById(R.id.btnResetForm).setOnClickListener(v -> resetForm());
        findViewById(R.id.btnDeleteAll).setOnClickListener(v -> deleteAllClocks());
        btnDate.setOnClickListener(v -> showDatePicker());
        btnTime.setOnClickListener(v -> showTimePicker());

        // “＋ 新增”：先确认还有空闲 id，再清空表单并展开配置卡
        btnAddClock.setOnClickListener(v -> {
            if (nextFreeId() < 0) {
                showToast("The time slots are full (id 1~" + GameClockBean.MAX_CLOCK_ID + "). Please delete one first.");
                logErr("新增失败：时段已满，Up to " + GameClockBean.MAX_CLOCK_COUNT + " items");
                return;
            }
            resetForm();
            ccvConfig.expand();
        });

        spClockType.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                applyTypeToForm(TYPES[position]);
                updateConfigSubtitle();
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
            }
        });

        svMainSwitch.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
            @Override
            public void toggleToOn(SwitchView view) {
                if (ignoreMainSwitchCallback || !supported) {
                    return;
                }
                setMainSwitch(true);
            }

            @Override
            public void toggleToOff(SwitchView view) {
                if (ignoreMainSwitchCallback || !supported) {
                    return;
                }
                setMainSwitch(false);
            }
        });

        bindExclusiveCollapse();
    }

    /**
     * Configuration card and log card are mutually exclusive: Expanding one will collapse the other，保证列表始终有余量
     * <p>
     * Keep the two cards mutually exclusive so the list always has room left.
     */
    private void bindExclusiveCollapse() {
        ccvConfig.setOnExpandStateChangeListener(new CollapseCardLogView.OnExpandStateChangeListener() {
            @Override
            public void onExpanded(CollapseCardLogView cardView) {
                ccvLog.collapse();
            }

            @Override
            public void onCollapsed(CollapseCardLogView cardView) {
                // nothing
            }
        });
        ccvLog.setOnExpandStateChangeListener(new CollapseCardLogView.OnExpandStateChangeListener() {
            @Override
            public void onExpanded(CollapseCardLogView cardView) {
                ccvConfig.collapse();
            }

            @Override
            public void onCollapsed(CollapseCardLogView cardView) {
                // nothing
            }
        });
    }

    /**
     * When the interface is ready: Disable the entry point if not supported，支持则自动读取一次
     */
    private void afterUiReady() {
        if (!supported) {
            logErr("The current device does not support game control functions（gameType = 0）");
            ccvConfig.setSubTitle("⚠️ 当前设备不支持游戏控制");
            setFormEnabled(false);
            findViewById(R.id.btnRead).setEnabled(false);
            findViewById(R.id.btnDeleteAll).setEnabled(false);
            btnAddClock.setEnabled(false);
            btnAddClock.setAlpha(0.4f);
            svMainSwitch.setEnabled(false);
            return;
        }
        readGameClocks();
    }

    // ==================================================================================
    // 列表 / 表单初始化
    // ==================================================================================

    private void initListView() {
        rvGameClock.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new GameClockAdapter(mClocks, this);
        rvGameClock.setSwipeMenuCreator(swipeMenuCreator);
        rvGameClock.setOnItemMenuClickListener(mMenuItemClickListener);
        rvGameClock.setAdapter(mAdapter);
    }

    private void setupWeekdayViews() {
        EGameClockWeekday[] days = EGameClockWeekday.weekdays();
        for (int i = 0; i < days.length; i++) {
            CheckBox checkBox = new CheckBox(this);
            // "周一" -> "一"，7 个选项一行放得下
            checkBox.setText(days[i].getCnName().replace("周", ""));
            checkBox.setTextSize(12f);
            checkBox.setPadding(0, 0, 0, 0);
            checkBox.setGravity(android.view.Gravity.CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            checkBox.setLayoutParams(lp);
            llWeekdays.addView(checkBox);
            cbWeekdays[i] = checkBox;
        }
    }

    private void setupTypeSpinner() {
        List<String> names = new ArrayList<>();
        for (EGameClockType type : TYPES) {
            names.add(type.getCnName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClockType.setAdapter(adapter);
    }

    /**
     * 表单重置为“新增”状态：id 自动Take the smallest free space from 1 to 10值
     * Reset the form to "add" mode; the id is the smallest free slot in 1~10.
     */
    private void resetForm() {
        mEditingId = -1;
        spClockType.setSelection(0);
        int freeId = nextFreeId();
        tvFormId.setText(freeId > 0 ? String.valueOf(freeId) : "满");
        etDuration.setText("30");
        cbClockOpen.setChecked(true);
        btnDate.setText(TimeUtils.getCurrentDateStr());
        btnTime.setText(TimeUtils.getCurrentHourMinuteStr());
        for (CheckBox checkBox : cbWeekdays) {
            checkBox.setChecked(true);
        }
        applyTypeToForm(TYPES[0]);
        applyModeStyle(false);
        updateConfigSubtitle();
        if (mAdapter != null) {
            mAdapter.setHighlight(-1, -1);
        }
    }

    /**
     * 表头「新增/"Edit" prompt capsule style switch（新增=橙，编辑=蓝）
     * <p>Switch the mode pill style: orange for "add", blue for "edit".
     */
    private void applyModeStyle(boolean editing) {
        if (editing) {
            tvEditHint.setText("✏️ 编辑 ID=" + mEditingId);
            tvEditHint.setTextColor(0xFF283593);
            tvEditHint.setBackgroundResource(R.drawable.bg_game_clock_hint_edit);
        } else {
            tvEditHint.setText("🆕 新增模式");
            tvEditHint.setTextColor(0xFFE65100);
            tvEditHint.setBackgroundResource(R.drawable.bg_game_clock_hint_new);
        }
    }

    /**
     * Synchronize form availability after type switching (all grayed out when the device does not support it)）
     */
    private void applyTypeToForm(EGameClockType type) {
        boolean weekly = type.isWeeklyRepeat();
        boolean weeklyEnabled = supported && weekly;
        boolean dateEnabled = supported && !weekly;
        for (CheckBox checkBox : cbWeekdays) {
            checkBox.setEnabled(weeklyEnabled);
            checkBox.setAlpha(weeklyEnabled ? 1f : 0.4f);
        }
        btnDate.setEnabled(dateEnabled);
        btnDate.setAlpha(dateEnabled ? 1f : 0.4f);
    }

    private void setFormEnabled(boolean enabled) {
        spClockType.setEnabled(enabled);
        etDuration.setEnabled(enabled);
        btnDate.setEnabled(enabled);
        btnTime.setEnabled(enabled);
        cbClockOpen.setEnabled(enabled);
        for (CheckBox checkBox : cbWeekdays) {
            checkBox.setEnabled(enabled);
        }
        findViewById(R.id.btnSaveClock).setEnabled(enabled);
        findViewById(R.id.btnResetForm).setEnabled(enabled);
    }

    /**
     * Refresh the configuration card subtitle, which can also be seen when collapsed.“当前在编辑哪一条”
     * <p>Refresh the card subtitle so the collapsed header is still informative.
     */
    private void updateConfigSubtitle() {
        EGameClockType type = TYPES[Math.max(0, Math.min(spClockType.getSelectedItemPosition(), TYPES.length - 1))];
        String prefix;
        if (mEditingId > 0) {
            prefix = "✏️ 编辑 ID=" + mEditingId;
        } else {
            int freeId = nextFreeId();
            prefix = freeId > 0 ? ("🆕 Add ID=" + freeId + " (Automatic)）") : "🆕 新增（时段已满）";
        }
        ccvConfig.setSubTitle(String.format(Locale.US, "%s · %s %s",
                prefix, type.getCnName(), btnTime.getText()));
    }

    // ==================================================================================
    // 接口 1：读取
    // ==================================================================================

    private void readGameClocks() {
        logInfo("读取：宿主总开关=" + mainSwitchDesc() + "，Local Time Periods " + mClocks.size() + " items");
        vpBleManager.readZT79GameClocks(mMainSwitchState, mClocks, new SimpleGameClockListener() {

            @Override
            public void onGameTimePlanningRead(int crc, boolean isOpen, List<GameClockBean> dataList) {
                mMainSwitchState = isOpen;
                mClocks.clear();
                if (dataList != null) {
                    // 按 id 去重：When the device returns an error, the duplicate ID will not be included in the host list.
                    // De-duplicate by id so a bad device payload cannot poison the host list
                    LinkedHashMap<Integer, GameClockBean> unique = new LinkedHashMap<>();
                    for (GameClockBean clock : dataList) {
                        if (clock != null) {
                            unique.put(clock.getClockId(), clock);
                        }
                    }
                    mClocks.addAll(unique.values());
                    if (unique.size() != dataList.size()) {
                        logErr("Duplicate ID exists in device feedback. Sorted by id 去重：" + dataList.size() + " -> " + unique.size());
                    }
                }
                Collections.sort(mClocks);
                if (mEditingId > 0 && indexOfClock(mEditingId) < 0) {
                    resetForm();
                }
                syncMainSwitchUi();
                refreshList();
                logOk("Read successful (FullData): CRC=" + crc + ", Main Switch=" + (isOpen ? "开" : "关")
                        + "，Total " + mClocks.size() + " items");
                for (GameClockBean bean : mClocks) {
                    logInfo("  " + bean.toSmartStr());
                }
            }

            @Override
            public void onGameClockNotNeed2Update() {
                logOk("Device data is consistent with local data (Synced); local data will be used. " + mClocks.size() + " 条时段");
            }

            @Override
            public void onGameClockEmpty() {
                mClocks.clear();
                refreshList();
                logInfo("No time period data for the device (Empty), host main switch=" + mainSwitchDesc());
            }
        }, defaultResponse);
    }

    // ==================================================================================
    // 接口 2：总开关
    // ==================================================================================

    private void setMainSwitch(boolean target) {
        final Boolean backup = mMainSwitchState;
        // Recommended sequence: Update the host-side status first → 下发 → 失败回滚
        mMainSwitchState = target;
        syncMainSwitchUi();
        logInfo("Issue main switch=" + (target ? "On" : "Off") + ", Carry-on Period " + mClocks.size() + " 条");

        vpBleManager.setZT79GameClockMainSwitch(target, mClocks, new SimpleGameClockListener() {
            @Override
            public void onSettingResult(boolean isSuccess) {
                if (!isSuccess) {
                    mMainSwitchState = backup;
                    syncMainSwitchUi();
                    logErr("总开关设置失败，已回滚为 " + mainSwitchDesc());
                } else {
                    logOk("Master switch set successfully -> " + (target ? "On" : "关"));
                }
            }
        }, defaultResponse);
    }

    // ==================================================================================
    // 接口 3：添加 / 修改
    // ==================================================================================

    private void saveClock() {
        // id 只读：编辑模式沿用原 id，In the new mode, take the smallest available slot from 1 to 10.
        // The id is read-only: editing keeps its own id, adding takes the smallest free slot
        final int clockId;
        final boolean isNew;
        if (mEditingId > 0) {
            clockId = mEditingId;
            isNew = indexOfClock(clockId) < 0;
        } else {
            clockId = nextFreeId();
            if (clockId < 0) {
                showToast("The time slots are full (id 1~" + GameClockBean.MAX_CLOCK_ID + "). Please delete one first.");
                logErr("保存失败：时段已满，Up to " + GameClockBean.MAX_CLOCK_COUNT + " items");
                return;
            }
            isNew = true;
        }

        // Double insurance: If the ID is already taken, it will not be issued.，避免设备端出现重复 id
        // Guard against duplicates before sending anything to the device
        if (isNew && indexOfClock(clockId) >= 0) {
            showToast("id " + clockId + " 已被占用，请重新新增");
            logErr("Save failed: ID " + clockId + " already exists，禁止重复");
            resetForm();
            return;
        }
        if (isNew && mClocks.size() >= GameClockBean.MAX_CLOCK_COUNT) {
            showToast("The device supports up to " + GameClockBean.MAX_CLOCK_COUNT + " time slots，请先删除");
            return;
        }

        final GameClockBean bean = buildBeanFromForm(clockId);
        if (bean == null) {
            return;
        }
        final List<GameClockBean> backup = new ArrayList<>(mClocks);
        int index = indexOfClock(clockId);
        if (index >= 0) {
            mClocks.set(index, bean);
        } else {
            mClocks.add(bean);
        }
        Collections.sort(mClocks);
        mEditingId = bean.getClockId();
        applyModeStyle(true);
        refreshList();
        highlightClock(bean.getClockId(), isNew);
        logInfo((isNew ? "Add Time Slot: " : "Edit Time Slot：") + bean.toSmartStr());

        vpBleManager.setZT79GameClocks(currentSwitch(), bean, mClocks, new SimpleGameClockListener() {
            @Override
            public void onSettingResult(boolean isSuccess) {
                if (!isSuccess) {
                    restoreClocks(backup);
                    logErr("Time period setting failed. The local list has been rolled back.");
                } else {
                    logOk("Time slot [" + bean.getClockId() + "] " + (isNew ? "Add" : "修改") + "成功");
                    // After successful deployment, collapse the configuration card and return the space to the list.（回调可能在蓝牙线程）
                    runOnUiThread(() -> ccvConfig.collapse());
                }
            }
        }, defaultResponse);
    }

    /**
     * Highlight the corresponding entry after the change and scroll to a visible position: New=橙色「新增」，编辑=蓝色「编辑中」
     * <p>Highlight the affected row after a change and scroll it into view.
     */
    private void highlightClock(final int clockId, final boolean isNew) {
        runOnUiThread(() -> {
            mAdapter.setHighlight(clockId, isNew ? clockId : -1);
            int position = indexOfClock(clockId);
            if (position >= 0) {
                rvGameClock.smoothScrollToPosition(position);
            }
        });
    }

    @Override
    public void onToggleOpen(GameClockBean bean, boolean open) {
        int index = indexOfClock(bean.getClockId());
        if (index < 0) {
            return;
        }
        final List<GameClockBean> backup = new ArrayList<>(mClocks);
        final GameClockBean updated = bean.copy();
        updated.setOpen(open);
        mClocks.set(index, updated);
        refreshList();

        vpBleManager.setZT79GameClocks(currentSwitch(), updated, mClocks, new SimpleGameClockListener() {
            @Override
            public void onSettingResult(boolean isSuccess) {
                if (!isSuccess) {
                    restoreClocks(backup);
                    logErr("Failed to toggle time period [" + updated.getClockId() + "]，已回滚");
                } else {
                    logOk("Time slot [" + updated.getClockId() + "] has been" + (open ? "开启" : "关闭"));
                }
            }
        }, defaultResponse);
    }

    @Override
    public void onItemClick(GameClockBean bean) {
        fillForm(bean);
        // Tap an item to edit it, and easily expand the configuration card.（会互斥收起日志卡）
        ccvConfig.expand();
    }

    // ==================================================================================
    // 接口 4 / 5：删除
    // ==================================================================================

    private final SwipeMenuCreator swipeMenuCreator = (leftMenu, rightMenu, position) -> {
        int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
        SwipeMenuItem deleteItem = new SwipeMenuItem(GameClockActivity.this)
                .setBackgroundColor(getResources().getColor(R.color.colorAccent))
                .setText("删除")
                .setTextColor(0xFFFFFFFF)
                .setWidth(width)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        rightMenu.addMenuItem(deleteItem);
    };

    private final OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();
            if (position < 0 || position >= mClocks.size()) {
                return;
            }
            deleteSingleClock(mClocks.get(position));
        }
    };

    /**
     * 接口 4：删除单个时段
     */
    private void deleteSingleClock(GameClockBean bean) {
        final List<GameClockBean> backup = new ArrayList<>(mClocks);
        mClocks.remove(bean);
        // The one being edited is the one that was deleted, and the form returns to「新增模式」并让出 id
        if (mEditingId == bean.getClockId()) {
            resetForm();
        }
        refreshList();

        vpBleManager.deleteZT79GameClock(currentSwitch(), bean, mClocks,
                new SimpleGameClockListener() {
                    @Override
                    public void onDeleteResult(boolean isSuccess) {
                        if (!isSuccess) {
                            restoreClocks(backup);
                            logErr("Failed to delete time period [" + bean.getClockId() + "]，已恢复");
                        } else {
                            logOk("Time period [" + bean.getClockId() + "] deleted successfully");
                        }
                    }
                }, defaultResponse);
    }

    /**
     * 接口 5：批量删除（清空全部）
     */
    private void deleteAllClocks() {
        if (mClocks.isEmpty()) {
            showToast("当前没有可删除的时段");
            return;
        }
        final List<GameClockBean> backup = new ArrayList<>(mClocks);
        final List<Integer> ids = new ArrayList<>();
        for (GameClockBean bean : mClocks) {
            ids.add(bean.getClockId());
        }
        mClocks.clear();
        resetForm();
        refreshList();
        logInfo("批量删除 id：" + ids);

        vpBleManager.deleteZT79GameClockWithIDs(currentSwitch(), ids, mClocks,
                new SimpleGameClockListener() {
                    @Override
                    public void onDeleteResult(boolean isSuccess) {
                        if (!isSuccess) {
                            restoreClocks(backup);
                            logErr("批量删除失败，已恢复 " + backup.size() + " 条");
                        } else {
                            logOk("批量删除成功，共删除 " + ids.size() + " 条");
                        }
                    }
                }, defaultResponse);
    }

    // ==================================================================================
    // 表单 -> 模型
    // ==================================================================================

    private GameClockBean buildBeanFromForm(int clockId) {
        int duration = parseInt(etDuration.getText().toString(), 30);
        EGameClockType type = TYPES[Math.max(0, spClockType.getSelectedItemPosition())];
        int[] hm = parseTime(btnTime.getText().toString());

        GameClockBean bean = new GameClockBean();
        bean.setClockId(clockId);
        // Set the type first, and the fields not related to that type will be automatically cleared.
        bean.setClockType(type);
        bean.setStartHour(hm[0]);
        bean.setStartMinute(hm[1]);
        bean.setStartSecond(0);
        bean.setDuration(duration);
        bean.setOpen(cbClockOpen.isChecked());

        if (type.isWeeklyRepeat()) {
            List<EGameClockWeekday> days = new ArrayList<>();
            EGameClockWeekday[] all = EGameClockWeekday.weekdays();
            for (int i = 0; i < all.length; i++) {
                if (cbWeekdays[i].isChecked()) {
                    days.add(all[i]);
                }
            }
            bean.setRepeatWeekdays(days);
        } else {
            int[] ymd = parseDate(btnDate.getText().toString());
            bean.setYear(ymd[0]);
            bean.setMonth(ymd[1]);
            bean.setDay(ymd[2]);
        }

        String error = bean.getVerifyError();
        if (error != null) {
            showToast(error);
            logErr("参数校验不通过：" + error);
            return null;
        }
        return bean;
    }

    private void fillForm(GameClockBean bean) {
        mEditingId = bean.getClockId();
        tvFormId.setText(String.format(Locale.US, "%d", bean.getClockId()));
        spClockType.setSelection(indexOfType(bean.getClockType()));
        applyTypeToForm(bean.getClockType());

        EGameClockWeekday[] all = EGameClockWeekday.weekdays();
        for (int i = 0; i < all.length; i++) {
            cbWeekdays[i].setChecked(bean.isRepeatOn(all[i]));
        }
        btnTime.setText(String.format(Locale.US, "%02d:%02d", bean.getStartHour(), bean.getStartMinute()));
        if (bean.getYear() >= 2000) {
            btnDate.setText(String.format(Locale.US, "%04d-%02d-%02d",
                    bean.getYear(), bean.getMonth(), bean.getDay()));
        } else {
            btnDate.setText(TimeUtils.getCurrentDateStr());
        }
        etDuration.setText(String.format(Locale.US, "%d", bean.getDuration()));
        cbClockOpen.setChecked(bean.isOpen());
        applyModeStyle(true);
        updateConfigSubtitle();
        // 选中该条目：清掉「新增」高亮，避免两个橙色条目同时存在
        mAdapter.setHighlight(bean.getClockId(), -1);
    }

    // ==================================================================================
    // 选择器
    // ==================================================================================

    private void showTimePicker() {
        int[] hm = parseTime(btnTime.getText().toString());
        new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            btnTime.setText(String.format(Locale.CHINA, "%02d:%02d", hourOfDay, minute));
            updateConfigSubtitle();
        }, hm[0], hm[1], true).show();
    }

    private void showDatePicker() {
        int[] ymd = parseDate(btnDate.getText().toString());
        new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                btnDate.setText(String.format(Locale.CHINA, "%04d-%02d-%02d", year, month + 1, dayOfMonth)),
                ymd[0], ymd[1] - 1, ymd[2]).show();
    }

    // ==================================================================================
    // 工具方法
    // ==================================================================================

    @Override
    public void onClick(View view) {
        // This page uniformly uses setOnClickListener(lambda), so there is no need to distribute it here.
    }

    /**
     * Empty implementation of the read class callback to avoid having to write it in full every time. 5 个方法
     */
    private abstract static class SimpleGameClockListener implements IGameClockOptListener {

        @Override
        public void onDeleteResult(boolean isSuccess) {
        }

        @Override
        public void onSettingResult(boolean isSuccess) {
        }

        @Override
        public void onGameTimePlanningRead(int crc, boolean isOpen, List<GameClockBean> dataList) {
        }

        @Override
        public void onGameClockNotNeed2Update() {
        }

        @Override
        public void onGameClockEmpty() {
        }
    }

    private boolean currentSwitch() {
        return Boolean.TRUE.equals(mMainSwitchState);
    }

    private String mainSwitchDesc() {
        if (mMainSwitchState == null) {
            return "未同步";
        }
        return mMainSwitchState ? "开" : "关";
    }

    /**
     * Sync Master Switch UI (Callbacks may come from Bluetooth thread，统一切回主线程）
     */
    private void syncMainSwitchUi() {
        runOnUiThread(() -> {
            ignoreMainSwitchCallback = true;
            svMainSwitch.setOpened(Boolean.TRUE.equals(mMainSwitchState));
            ignoreMainSwitchCallback = false;
            tvMainSwitchState.setText(mainSwitchDesc());
            tvMainSwitchState.setTextColor(mMainSwitchState == null
                    ? 0xFF9E9E9E
                    : (mMainSwitchState ? 0xFF2E7D32 : 0xFFC62828));
        });
    }

    /**
     * 刷新列表 + 条数统计 + 空态提示
     */
    private void refreshList() {
        runOnUiThread(() -> {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
            tvListHeader.setText(String.format(Locale.US, "时段列表 %d/%d",
                    mClocks.size(), GameClockBean.MAX_CLOCK_COUNT));
            tvListEmpty.setVisibility(mClocks.isEmpty() ? View.VISIBLE : View.GONE);
        });
    }

    private void restoreClocks(List<GameClockBean> backup) {
        mClocks.clear();
        mClocks.addAll(backup);
        refreshList();
    }

    private int indexOfClock(int clockId) {
        for (int i = 0; i < mClocks.size(); i++) {
            if (mClocks.get(i).getClockId() == clockId) {
                return i;
            }
        }
        return -1;
    }

    private int indexOfType(EGameClockType type) {
        for (int i = 0; i < TYPES.length; i++) {
            if (TYPES[i] == type) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Take the smallest free space from 1 to 10 id；全部占满时返回 -1
     * <p>Smallest free id in 1~10, or -1 when all slots are taken.
     */
    private int nextFreeId() {
        for (int id = GameClockBean.MIN_CLOCK_ID; id <= GameClockBean.MAX_CLOCK_ID; id++) {
            if (indexOfClock(id) < 0) {
                return id;
            }
        }
        return -1;
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private int[] parseTime(String text) {
        int hour = 8;
        int minute = 0;
        try {
            String[] parts = text.split(":");
            hour = Integer.parseInt(parts[0].trim());
            minute = Integer.parseInt(parts[1].trim());
        } catch (Exception ignored) {
            Calendar calendar = Calendar.getInstance();
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            minute = calendar.get(Calendar.MINUTE);
        }
        return new int[]{hour, minute};
    }

    private int[] parseDate(String text) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        try {
            String[] parts = text.split("-");
            year = Integer.parseInt(parts[0].trim());
            month = Integer.parseInt(parts[1].trim());
            day = Integer.parseInt(parts[2].trim());
        } catch (Exception ignored) {
            // 解析失败就用今天
        }
        return new int[]{year, month, day};
    }

    // ============================ 日志 ============================
    // The callback may come from the Bluetooth thread. Switch back to the main thread and then write to the log card.

    private void logOk(String msg) {
        runOnUiThread(() -> {
            ccvLog.appendResult("✅️ " + msg);
            updateLogSubtitle(msg);
        });
    }

    private void logErr(String msg) {
        runOnUiThread(() -> {
            ccvLog.appendRedLargeText("❌️ " + msg);
            updateLogSubtitle(msg);
        });
    }

    private void logInfo(String msg) {
        runOnUiThread(() -> {
            ccvLog.appendBlueMiddleText("🔹 " + msg);
            updateLogSubtitle(msg);
        });
    }

    /**
     * Refresh the log card subtitle with the summary of the latest log entry, so that the latest result can be seen even when collapsed.
     * <p>Mirror the newest log line into the subtitle for the collapsed state.
     */
    private void updateLogSubtitle(String msg) {
        if (msg == null) {
            return;
        }
        String summary = msg.replace("\n", " ").trim();
        if (summary.length() > 32) {
            summary = summary.substring(0, 32) + "…";
        }
        ccvLog.setSubTitle(summary);
    }
}
