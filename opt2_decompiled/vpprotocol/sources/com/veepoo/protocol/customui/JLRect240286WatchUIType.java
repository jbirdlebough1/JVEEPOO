package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect240286WatchUIType.class */
public class JLRect240286WatchUIType extends Rect240286QFNWatchUIType {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect240286WatchUIType$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;
        static final /* synthetic */ int[] vp_b;

        static {
            int[] iArr = new int[EWatchUIElementPosition.values().length];
            vp_b = iArr;
            try {
                iArr[EWatchUIElementPosition.LEFT_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_b[EWatchUIElementPosition.LEFT_BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_b[EWatchUIElementPosition.RIGHT_TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_b[EWatchUIElementPosition.RIGHT_BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[EWatchUIElementType.values().length];
            vp_a = iArr2;
            try {
                iArr2[EWatchUIElementType.BLUETOOTH_BATTERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[EWatchUIElementType.STEP.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[EWatchUIElementType.SLEEP.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[EWatchUIElementType.KCAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                vp_a[EWatchUIElementType.HEART.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                vp_a[EWatchUIElementType.DISTANCE.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                vp_a[EWatchUIElementType.TIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                vp_a[EWatchUIElementType.NONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    @Override // com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap);
    }

    @Override // com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.kdial_preview_bg_default1_280;
    }

    @Override // com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_240_286;
    }

    @Override // com.veepoo.protocol.customui.Rect240286QFNWatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getElementImg(EWatchUIElementType eWatchUIElementType, EWatchUIElementPosition eWatchUIElementPosition) {
        switch (vp_a.vp_b[eWatchUIElementPosition.ordinal()]) {
            case 1:
            case 2:
                switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
                    case 1:
                        return R.drawable.kdial_edit_function_bt_ba_280l;
                    case 2:
                        return R.drawable.kdial_edit_function_date_280l;
                    case 3:
                        return R.drawable.kdial_edit_function_date2_280l;
                    case 4:
                        return R.drawable.kdial_edit_function_step_280l;
                    case 5:
                        return R.drawable.kdial_edit_function_sleep_280l;
                    case 6:
                        return R.drawable.kdial_edit_function_calorie_280l;
                    case 7:
                        return R.drawable.kdial_edit_function_hr_280l;
                    case 8:
                        return R.drawable.kdial_edit_function_distance_280l;
                    case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                        return R.drawable.kdial_edit_function_time_280l;
                    default:
                        return 0;
                }
            case 3:
            case 4:
                switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
                    case 1:
                        return R.drawable.kdial_edit_function_bt_ba_280r;
                    case 2:
                        return R.drawable.kdial_edit_function_date_280r;
                    case 3:
                        return R.drawable.kdial_edit_function_date2_280r;
                    case 4:
                        return R.drawable.kdial_edit_function_step_280r;
                    case 5:
                        return R.drawable.kdial_edit_function_sleep_280r;
                    case 6:
                        return R.drawable.kdial_edit_function_calorie_280r;
                    case 7:
                        return R.drawable.kdial_edit_function_hr_280r;
                    case 8:
                        return R.drawable.kdial_edit_function_distance_280r;
                    case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                        return R.drawable.kdial_edit_function_time_280r;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }
}
