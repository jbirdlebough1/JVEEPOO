package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.datas.FunSwitchFlags;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIElementType;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.util.Spo2hOriginUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Round240240WatchUIType.class */
public class Round240240WatchUIType extends WatchUIType {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/Round240240WatchUIType$vp_a.class */
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

    @Override // com.veepoo.protocol.customui.WatchUIType
    public boolean isSupportPosition(EWatchUIElementPosition eWatchUIElementPosition) {
        return super.isSupportPosition(eWatchUIElementPosition);
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public List<EWatchUIElementPosition> getSupportPosition() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(EWatchUIElementPosition.CENTER_TOP);
        arrayList.add(EWatchUIElementPosition.CENTER_CENTER);
        arrayList.add(EWatchUIElementPosition.CENTER_BOTTOM);
        return arrayList;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ROUND_240_240;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.round_240_240_a_default_bg;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getElementImg(EWatchUIElementType eWatchUIElementType, EWatchUIElementPosition eWatchUIElementPosition) {
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
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getBigBitmap(Context context, Bitmap bitmap) {
        int bigBitmapWidth = getBigBitmapWidth();
        int bigBitmapHeight = getBigBitmapHeight();
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, bigBitmapWidth, bigBitmapHeight, true);
        Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.round_240_240_cover_rect_round), bigBitmapWidth, bigBitmapHeight, true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bigBitmapWidth, bigBitmapHeight, bitmapCreateScaledBitmap2.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapCreateScaledBitmap, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmapCreateScaledBitmap2, 0.0f, 0.0f, (Paint) null);
        return bitmapCreateBitmap;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.round_240_240_small_bg);
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
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
    public int getSmallBitmapWidth() {
        return Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return Spo2hOriginUtil.TIME_FLAG_ONE_DAY_POINT;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 8;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 128;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 128;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public InputStream getSendInputStream(Context context, Bitmap bitmap) {
        return super.getSendInputStream(context, bitmap, false);
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public Boolean isRoundScreen() {
        return Boolean.TRUE;
    }
}
