package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Rect240240WatchUIType.class */
public class Rect240240WatchUIType extends WatchUIType {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Rect240240WatchUIType$vp_a.class */
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
                vp_b[EWatchUIElementPosition.CENTER_TOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                vp_b[EWatchUIElementPosition.CENTER_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                vp_b[EWatchUIElementPosition.CENTER_BOTTOM.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                vp_b[EWatchUIElementPosition.RIGHT_TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                vp_b[EWatchUIElementPosition.RIGHT_BOTTOM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[EWatchUIElementType.values().length];
            vp_a = iArr2;
            try {
                iArr2[EWatchUIElementType.BLUETOOTH_BATTERY.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                vp_a[EWatchUIElementType.DATE_STYLE_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                vp_a[EWatchUIElementType.STEP.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                vp_a[EWatchUIElementType.SLEEP.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                vp_a[EWatchUIElementType.KCAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                vp_a[EWatchUIElementType.HEART.ordinal()] = 7;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                vp_a[EWatchUIElementType.DISTANCE.ordinal()] = 8;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                vp_a[EWatchUIElementType.TIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                vp_a[EWatchUIElementType.NONE.ordinal()] = 10;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public List<EWatchUIElementPosition> getSupportPosition() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(EWatchUIElementPosition.CENTER_TOP);
        arrayList.add(EWatchUIElementPosition.CENTER_CENTER);
        arrayList.add(EWatchUIElementPosition.CENTER_BOTTOM);
        arrayList.add(EWatchUIElementPosition.LEFT_TOP);
        arrayList.add(EWatchUIElementPosition.LEFT_BOTTOM);
        arrayList.add(EWatchUIElementPosition.RIGHT_TOP);
        arrayList.add(EWatchUIElementPosition.RIGHT_BOTTOM);
        return arrayList;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.RECT_240_240;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.rect_240_240_a_default_bg;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getElementImg(EWatchUIElementType eWatchUIElementType, EWatchUIElementPosition eWatchUIElementPosition) {
        switch (vp_a.vp_b[eWatchUIElementPosition.ordinal()]) {
            case 1:
            case 2:
                switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
                    case 1:
                        return R.drawable.rect_240_240_left_battery;
                    case 2:
                        return R.drawable.rect_240_240_left_datestyle_1;
                    case 3:
                        return R.drawable.rect_240_240_left_datestyle_2;
                    case 4:
                        return R.drawable.rect_240_240_left_step;
                    case 5:
                        return R.drawable.rect_240_240_left_sleep;
                    case 6:
                        return R.drawable.rect_240_240_left_calorie;
                    case 7:
                        return R.drawable.rect_240_240_left_heart;
                    case 8:
                        return R.drawable.rect_240_240_left_distance;
                    case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                        return R.drawable.rect_240_280_left_time;
                    default:
                        return 0;
                }
            case 3:
            case 4:
            case 5:
                switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
                    case 1:
                        return R.drawable.round_240_240_battery;
                    case 2:
                        return R.drawable.round_240_240_datestyle_1;
                    case 3:
                        return R.drawable.round_240_240_datestyle_2;
                    case 4:
                        return R.drawable.round_240_240_step;
                    case 5:
                        return R.drawable.round_240_240_sleep;
                    case 6:
                        return R.drawable.round_240_240_calorie;
                    case 7:
                        return R.drawable.round_240_240_heart;
                    case 8:
                        return R.drawable.round_240_240_distance;
                    case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                        return R.drawable.round_240_240_time;
                    default:
                        return 0;
                }
            case 6:
            case 7:
                switch (vp_a.vp_a[eWatchUIElementType.ordinal()]) {
                    case 1:
                        return R.drawable.rect_240_240_right_battery;
                    case 2:
                        return R.drawable.rect_240_240_right_datestyle_1;
                    case 3:
                        return R.drawable.rect_240_240_right_datestyle_2;
                    case 4:
                        return R.drawable.rect_240_240_right_step;
                    case 5:
                        return R.drawable.rect_240_240_right_sleep;
                    case 6:
                        return R.drawable.rect_240_240_right_calorie;
                    case 7:
                        return R.drawable.rect_240_240_right_heart;
                    case 8:
                        return R.drawable.rect_240_240_right_distance;
                    case FunSwitchFlags.BODY_COMPONENT /* 9 */:
                        return R.drawable.rect_240_280_right_time;
                    default:
                        return 0;
                }
            default:
                return 0;
        }
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getBigBitmap(Context context, Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 240;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 240;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.rect_240_240_small_bg);
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 188;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 188;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 7;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 174;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 174;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap, false);
    }
}
