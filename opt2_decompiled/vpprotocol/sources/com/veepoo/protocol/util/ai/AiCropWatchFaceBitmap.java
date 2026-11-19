package com.veepoo.protocol.util.ai;

import android.graphics.Bitmap;
import java.io.File;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiCropWatchFaceBitmap.class */
public class AiCropWatchFaceBitmap {
    public Bitmap bitmap;
    public String filePath;

    public AiCropWatchFaceBitmap(Bitmap bitmap, String str) {
        this.bitmap = bitmap;
        this.filePath = str;
    }

    public String getFileName() {
        return new File(this.filePath).getName();
    }
}
