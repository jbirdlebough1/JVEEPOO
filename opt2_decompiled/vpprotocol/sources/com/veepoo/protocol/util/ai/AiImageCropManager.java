package com.veepoo.protocol.util.ai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.veepoo.protocol.AiDialPreviewManager;
import com.veepoo.protocol.VPOperateManager;
import com.veepoo.protocol.customui.WatchUIType;
import com.veepoo.protocol.model.datas.AIDeviceConfigBean;
import com.veepoo.protocol.model.enums.EWatchUIType;
import com.veepoo.protocol.util.thread.HBThreadPools;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiImageCropManager.class */
public class AiImageCropManager {
    public static final String SCALED_AI_DRAW_FILE_NAME = "scaled_ai_draw_image.jpeg";
    public static final String AI_DRAW_IMAGE_NAME = "ai_draw_center_crop_watch_face.jpeg";
    public static final String AI_DRAW_SCALE_IMAGE_NAME = "ai_draw_center_crop_scale_watch_face.jpeg";
    public static final String AI_DRAW_PREVIEW_IMAGE_NAME = "ai_draw_center_crop_preview_watch_face.jpeg";
    public static final String AI_DRAW_ORIGINAL_IMAGE_SCALE_NAME = "ai_draw_original_scale_img.jpeg";
    public static final String AI_DRAW_ORIGINAL_IMAGE_NAME = "ai_draw_original_img.jpeg";
    private final Context vp_a;
    private final Handler vp_b = new Handler(Looper.getMainLooper());
    private final String vp_c;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiImageCropManager$ClassHolder.class */
    private static final class ClassHolder {
        private static final AiImageCropManager vp_a = new AiImageCropManager();
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/ai/AiImageCropManager$OnAiDrawImageCropListener.class */
    public interface OnAiDrawImageCropListener {
        void onAiWatchFaceCropped(AiCropWatchFaceBitmap aiCropWatchFaceBitmap, AiCropWatchFaceBitmap aiCropWatchFaceBitmap2, AiCropWatchFaceBitmap aiCropWatchFaceBitmap3);

        void onAiWatchFaceCropFailed();
    }

    private AiImageCropManager() {
        Context context = VPOperateManager.mContext;
        this.vp_a = context;
        File externalFilesDir = context != null ? context.getExternalFilesDir(null) : null;
        StringBuilder sbAppend = new StringBuilder().append(externalFilesDir != null ? externalFilesDir.getAbsolutePath() : "/sdcard");
        String str = File.separator;
        this.vp_c = sbAppend.append(str).append("ai_dial").append(str).toString();
    }

    public static AiImageCropManager getInstance() {
        return ClassHolder.vp_a;
    }

    private WatchUIType vp_a() {
        EWatchUIType watchUIType = AiDialPreviewManager.getInstance().getWatchUIType();
        return watchUIType == null ? WatchUIType.getInstance(EWatchUIType.RECT_240_280) : WatchUIType.getInstance(watchUIType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.FileInputStream, java.io.InputStream] */
    public Bitmap loadImageFromStream(String str) {
        ?? fileInputStream = 0;
        Bitmap bitmapDecodeStream = null;
        try {
            fileInputStream = new FileInputStream(str);
            bitmapDecodeStream = BitmapFactory.decodeStream(fileInputStream);
            fileInputStream.close();
        } catch (IOException unused) {
            fileInputStream.printStackTrace();
        }
        return bitmapDecodeStream;
    }

    public byte[] convertImageToByteArray(String str, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmapDecodeFile != null) {
            bitmapDecodeFile.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public void cropAiDrawImage2WatchFace(String str, int i, int i2, boolean z, OnAiDrawImageCropListener onAiDrawImageCropListener) {
        HBThreadPools.getInstance().execute(() -> {
            try {
                Thread.sleep(1000L);
                AIDeviceConfigBean aiDeviceConfig = AiDialPreviewManager.getInstance().getAiDeviceConfig();
                if (!new File(str).exists() || aiDeviceConfig == null || !aiDeviceConfig.isValid()) {
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 文件不存在");
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                        return;
                    }
                    return;
                }
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
                if (bitmapDecodeFile == null) {
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 文件不存在-filePath：" + str + " 是否真的存在 = " + new File(str).exists());
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                        return;
                    }
                    return;
                }
                Bitmap bitmapScaleBitmap = scaleBitmap(bitmapDecodeFile, i, i2);
                if (bitmapScaleBitmap == null) {
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 文件不存在-filePath：" + str);
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                        return;
                    }
                    return;
                }
                saveBitmapToFile(bitmapScaleBitmap, this.vp_c, AI_DRAW_ORIGINAL_IMAGE_SCALE_NAME);
                Bitmap bitmapCropBitmapCentered = cropBitmapCentered(bitmapScaleBitmap, i, i2);
                if (z) {
                    bitmapCropBitmapCentered = getCircularBitmap(bitmapCropBitmapCentered);
                }
                if (bitmapCropBitmapCentered == null) {
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 文件不存在-filePath：" + str);
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                        return;
                    }
                    return;
                }
                saveBitmapToFile(bitmapCropBitmapCentered, this.vp_c, AI_DRAW_IMAGE_NAME);
                AiCropWatchFaceBitmap aiCropWatchFaceBitmap = new AiCropWatchFaceBitmap(bitmapCropBitmapCentered, this.vp_c + AI_DRAW_IMAGE_NAME);
                Bitmap bigBitmap = vp_a().getBigBitmap(this.vp_a, bitmapCropBitmapCentered);
                if (!saveBitmapToFile(bigBitmap, this.vp_c, AI_DRAW_SCALE_IMAGE_NAME)) {
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                    }
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 图片保存失败：" + str);
                    return;
                }
                AiCropWatchFaceBitmap aiCropWatchFaceBitmap2 = new AiCropWatchFaceBitmap(bigBitmap, this.vp_c + AI_DRAW_SCALE_IMAGE_NAME);
                int previewAngleRadius = aiDeviceConfig.getPreviewAngleRadius();
                Bitmap bitmapScaleBitmap2 = scaleBitmap(bitmapCropBitmapCentered, aiDeviceConfig.getPreviewResolutionW(), aiDeviceConfig.getPreviewResolutionH());
                if (bitmapScaleBitmap2 == null) {
                    Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: 文件不存在-filePath：" + str);
                    if (onAiDrawImageCropListener != null) {
                        this.vp_b.post(onAiDrawImageCropListener::onAiWatchFaceCropFailed);
                        return;
                    }
                    return;
                }
                Bitmap roundedCornerBitmap = getInstance().getRoundedCornerBitmap(bitmapScaleBitmap2, previewAngleRadius);
                saveBitmapToFile(roundedCornerBitmap, this.vp_c, AI_DRAW_PREVIEW_IMAGE_NAME);
                Log.e("AiImageCropManager", "-cropAiDrawImage2WatchFace-: filePath：" + this.vp_c + AI_DRAW_PREVIEW_IMAGE_NAME + " ， 圆角半径 = " + previewAngleRadius);
                AiCropWatchFaceBitmap aiCropWatchFaceBitmap3 = new AiCropWatchFaceBitmap(roundedCornerBitmap, this.vp_c + AI_DRAW_PREVIEW_IMAGE_NAME);
                if (onAiDrawImageCropListener != null) {
                    this.vp_b.post(() -> {
                        onAiDrawImageCropListener.onAiWatchFaceCropped(aiCropWatchFaceBitmap, aiCropWatchFaceBitmap2, aiCropWatchFaceBitmap3);
                    });
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Bitmap scaleBitmap(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float fMax = Math.max(f / width, f2 / height);
        Matrix matrix = new Matrix();
        matrix.postScale(fMax, fMax);
        return Bitmap.createBitmap(bitmap, 0, 0, (int) width, (int) height, matrix, false);
    }

    public Bitmap cropBitmapCentered(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int iMax = Math.max((width - i) / 2, 0);
        int iMax2 = Math.max((height - i2) / 2, 0);
        return Bitmap.createBitmap(bitmap, iMax, iMax2, Math.min(iMax + i, width) - iMax, Math.min(iMax2 + i2, height) - iMax2);
    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, float f) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public Bitmap getCircularBitmap(Bitmap bitmap) {
        int iMin = Math.min(bitmap.getWidth(), bitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        float f = iMin / 2.0f;
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, (Rect) null, new Rect(0, 0, iMin, iMin), paint);
        return bitmapCreateBitmap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.io.FileOutputStream, java.io.OutputStream, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.graphics.Bitmap] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v5 */
    public boolean saveBitmapToFile(Bitmap bitmap, String str, String str2) throws Throwable {
        ?? r8;
        Throwable th;
        if (bitmap == 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str2);
        if (file2.exists()) {
            file2.delete();
        }
        ?? fileOutputStream = file2;
        IOException iOException = null;
        try {
            try {
                fileOutputStream.createNewFile();
                fileOutputStream = new FileOutputStream(file2);
                try {
                    boolean zCompress = bitmap.compress(Bitmap.CompressFormat.JPEG, 70, fileOutputStream);
                    fileOutputStream.flush();
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        fileOutputStream.printStackTrace();
                    }
                    return zCompress;
                } catch (IOException e) {
                    iOException = e;
                    fileOutputStream.printStackTrace();
                    ?? r0 = iOException;
                    if (r0 != 0) {
                        try {
                            r0 = iOException;
                            r0.close();
                        } catch (IOException unused2) {
                            r0.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    r8 = fileOutputStream;
                    ?? r1 = r8;
                    if (r1 != 0) {
                        try {
                            r1 = r8;
                            r1.close();
                        } catch (IOException unused3) {
                            r1.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                r8 = 0;
                th = th3;
            }
        } catch (IOException unused4) {
        }
    }
}
