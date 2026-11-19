package com.veepoo.protocol.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.veepoo.protocol.R;
import com.veepoo.protocol.model.enums.EWatchUIElementPosition;
import com.veepoo.protocol.model.enums.EWatchUIType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/customui/ZKRect410502P569XWatchUIType.class */
public class ZKRect410502P569XWatchUIType extends JLRect410502WatchUIType {
    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public List<EWatchUIElementPosition> getSupportPosition() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(EWatchUIElementPosition.LEFT_TOP);
        arrayList.add(EWatchUIElementPosition.LEFT_BOTTOM);
        arrayList.add(EWatchUIElementPosition.RIGHT_TOP);
        arrayList.add(EWatchUIElementPosition.RIGHT_BOTTOM);
        return arrayList;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public EWatchUIType getUIType() {
        return EWatchUIType.ZK_RECT_410_502_P569X;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getDefaultImg() {
        return R.drawable.zdial_preview_bg_default1_502;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getBigBitmap(Context context, Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapWidth() {
        return 410;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapHeight() {
        return 502;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBitmap(Bitmap bitmap) {
        return bitmap;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public Bitmap getSmallBackgroundBitmap(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.zdial_preview_bg_502_4);
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapWidth() {
        return 204;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapHeight() {
        return 244;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBroad() {
        return 12;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentWidth() {
        return 180;
    }

    @Override // com.veepoo.protocol.customui.JLRect410502WatchUIType, com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapContentHeight() {
        return 220;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getBigBitmapRoundedCorner() {
        return 116;
    }

    @Override // com.veepoo.protocol.customui.WatchUIType
    public int getSmallBitmapRadius() {
        return 51;
    }
}
