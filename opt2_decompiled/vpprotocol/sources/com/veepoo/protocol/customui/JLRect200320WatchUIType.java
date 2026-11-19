package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import java.io.InputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect200320WatchUIType.class */
public class JLRect200320WatchUIType extends Rect200320WatchUIType {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/JLRect200320WatchUIType$vp_a.class */
    static /* synthetic */ class vp_a {
        static final /* synthetic */ int[] vp_a;

        static {
            int[] iArr = new int[EWatchUIElementType.values().length];
            vp_a = iArr;
            try {
                iArr[EWatchUIElementType.BLUETOOTH_BATTERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_a[EWatchUIElementType.STEP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_a[EWatchUIElementType.SLEEP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_a[EWatchUIElementType.KCAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_a[EWatchUIElementType.HEART.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                vp_a[EWatchUIElementType.DISTANCE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[EWatchUIElementType.TIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[EWatchUIElementType.NONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap);
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.dial157_edit_bg_default1;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_JL_200_320;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getElementImg(EWatchUIElementType eWatchUIElementType, EWatchUIElementPosition eWatchUIElementPosition) {
        switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
            case 1:
                return R.drawable.kdial157_edit_function_bt_ba;
            case 2:
                return R.drawable.dial157_edit_function_date_week;
            case 3:
                return R.drawable.dial157_edit_function_week_date;
            case 4:
                return R.drawable.dial157_edit_function_step;
            case 5:
                return R.drawable.dial157_edit_function_sleep;
            case 6:
                return R.drawable.dial157_edit_function_calorie;
            case 7:
                return R.drawable.dial157_edit_function_hr;
            case 8:
                return R.drawable.dial157_edit_function_distance;
            case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                return R.drawable.dial157_edit_function_time;
            default:
                return 0;
        }
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 200;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 320;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 156;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 242;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 8;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT;
    }

    @Override // com.veepoo.protocol.customui.Rect200320WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 230;
    }
}
