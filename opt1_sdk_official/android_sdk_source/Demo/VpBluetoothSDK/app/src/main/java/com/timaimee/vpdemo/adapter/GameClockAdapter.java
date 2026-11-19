package com.timaimee.vpdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.iielse.switchbutton.SwitchView;
import com.timaimee.vpdemo.R;
import com.veepoo.protocol.model.datas.GameClockBean;

import java.util.List;
import java.util.Locale;

/**
 * 游戏控制时段列表适配器。
 * <p>
 * Items have three visual states for easy differentiation at a glance「哪条正在编辑」与「哪条是刚新增的」：
 * <ul>
 *     <li>普通：白底 + 灰色 id 徽章</li>
 *     <li>Editing ({@link #selectedId}): Light Blue Background + 蓝描边 + 「编辑中」标签</li>
 *     <li>Newly added ({@link #newId}): Warm Orange Background + 橙描边 + 「新增」标签，优先级高于编辑中</li>
 * </ul>
 * Game control clock list adapter. Each row has three visual states so the row under edition and
 * the row just added are both unmistakable.
 */
public class GameClockAdapter extends RecyclerView.Adapter<GameClockAdapter.ViewHolder> {

    /**
     * 列表交互回调 / interaction callbacks
     */
    public interface OnGameClockActionListener {

        /**
         * 点击条目，加载到编辑区 / load the clock into the edit form
         */
        void onItemClick(GameClockBean bean);

        /**
         * 切换单个时段开关 / toggle a single clock
         */
        void onToggleOpen(GameClockBean bean, boolean open);
    }

    private final List<GameClockBean> data;
    private final OnGameClockActionListener listener;

    /**
     * 当前高亮（正在编辑）Time slot ID, -1 indicates none
     * Highlighted clock id (under edition), -1 for none.
     */
    private int selectedId = -1;

    /**
     * The ID of the time slot that was just successfully added; -1 indicates none
     * Id of the clock just added, -1 for none.
     */
    private int newId = -1;

    public GameClockAdapter(List<GameClockBean> data, OnGameClockActionListener listener) {
        this.data = data;
        this.listener = listener;
    }

    /**
     * Only update the selected status and retain the added status / update the selected state only
     */
    public void setSelectedId(int selectedId) {
        if (this.selectedId == selectedId) {
            return;
        }
        this.selectedId = selectedId;
        notifyDataSetChanged();
    }

    /**
     * 同时设置「编辑中」与「刚新增」Two highlighted IDs, -1 indicates cancellation
     * <p>
     * Set both highlight ids at once; -1 clears the corresponding one.
     */
    public void setHighlight(int selectedId, int newId) {
        if (this.selectedId == selectedId && this.newId == newId) {
            return;
        }
        this.selectedId = selectedId;
        this.newId = newId;
        notifyDataSetChanged();
    }

    /**
     * Clear the new state highlight (called when the user starts editing other entries)）
     * Clear the "just added" highlight.
     */
    public void clearNewId() {
        setHighlight(selectedId, -1);
    }

    public int getSelectedId() {
        return selectedId;
    }

    public int getNewId() {
        return newId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_clock, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameClockBean bean = data.get(position);
        int clockId = bean.getClockId();
        // Newness takes precedence over editing: The newly added item is bound to be the most eye-catching.
        // The "new" state wins over the "editing" state
        boolean isNew = clockId == newId;
        boolean isEditing = !isNew && clockId == selectedId;
        holder.updateUi(bean, isEditing, isNew, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final View vBg;
        private final TextView tvClockId;
        private final TextView tvClockTime;
        private final TextView tvClockDesc;
        private final TextView tvItemBadge;
        private final SwitchView svClockOpen;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            vBg = itemView.findViewById(R.id.vBg);
            tvClockId = itemView.findViewById(R.id.tvClockId);
            tvClockTime = itemView.findViewById(R.id.tvClockTime);
            tvClockDesc = itemView.findViewById(R.id.tvClockDesc);
            tvItemBadge = itemView.findViewById(R.id.tvItemBadge);
            svClockOpen = itemView.findViewById(R.id.svClockOpen);
        }

        void updateUi(final GameClockBean bean, boolean editing, boolean isNew,
                      final OnGameClockActionListener listener) {
            tvClockId.setText(String.format(Locale.US, "%d", bean.getClockId()));
            tvClockTime.setText(bean.getTimeRangeDesc());
            tvClockDesc.setText(String.format(Locale.US, "%s · %s · %d 分钟",
                    bean.getClockType().getCnName(), bean.getRepeatDesc(), bean.getDuration()));

            // 背景/徽章/Change the color of the text in three places simultaneously to avoid the issue caused by relying solely on a thin outline.“看不出选中的是谁”
            // Change background, id badge and text color together so the state is obvious
            if (isNew) {
                vBg.setBackgroundResource(R.drawable.bg_game_clock_item_new);
                tvClockId.setBackgroundResource(R.drawable.bg_game_clock_id_new);
                tvClockTime.setTextColor(0xFFE65100);
                tvItemBadge.setBackgroundResource(R.drawable.bg_game_clock_badge_new);
                tvItemBadge.setText("新增");
                tvItemBadge.setVisibility(View.VISIBLE);
            } else if (editing) {
                vBg.setBackgroundResource(R.drawable.bg_game_clock_item_selected);
                tvClockId.setBackgroundResource(R.drawable.bg_game_clock_id_selected);
                tvClockTime.setTextColor(0xFF283593);
                tvItemBadge.setBackgroundResource(R.drawable.bg_game_clock_badge_editing);
                tvItemBadge.setText("编辑中");
                tvItemBadge.setVisibility(View.VISIBLE);
            } else {
                vBg.setBackgroundResource(R.drawable.bg_game_clock_item);
                tvClockId.setBackgroundResource(R.drawable.bg_game_clock_id);
                tvClockTime.setTextColor(0xFF212121);
                tvItemBadge.setVisibility(View.GONE);
            }

            vBg.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(bean);
                }
            });

            // Sync UI state before registering listeners，Avoid setOpened triggering a false callback
            // Sync the UI first, then register the listener, so setOpened never fires a fake callback
            svClockOpen.setOpened(bean.isOpen());
            svClockOpen.setOnStateChangedListener(new SwitchView.OnStateChangedListener() {
                @Override
                public void toggleToOn(SwitchView view) {
                    if (listener != null) {
                        listener.onToggleOpen(bean, true);
                    }
                }

                @Override
                public void toggleToOff(SwitchView view) {
                    if (listener != null) {
                        listener.onToggleOpen(bean, false);
                    }
                }
            });
        }
    }
}
