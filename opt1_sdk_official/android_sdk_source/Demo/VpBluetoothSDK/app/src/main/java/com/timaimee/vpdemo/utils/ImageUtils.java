package com.timaimee.vpdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    /**
     * Crop the local image to the specified size and save it locally。
     *
     * @param inputPath   本地图片文件路径
     * @param outputPath  裁剪后图片保存路径
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @return Returns true on success, returns  on failure false
     */
    public static boolean centerCropAndSave(String inputPath, String outputPath, int targetWidth, int targetHeight) {
        Bitmap sourceBitmap = BitmapFactory.decodeFile(inputPath);
        if (sourceBitmap == null) {
            Log.e(TAG, "无法加载源图片: " + inputPath);
            return false;
        }

        try {
            Bitmap croppedBitmap = centerCrop(sourceBitmap, targetWidth, targetHeight);
            if (croppedBitmap == null) {
                return false;
            }

            // 保存裁剪后的图片
            return saveBitmap(croppedBitmap, outputPath);
        } finally {
            // Release the source Bitmap if it is not reused
            if (sourceBitmap != null && !sourceBitmap.isRecycled()) {
                sourceBitmap.recycle();
            }
        }
    }

    /**
     * Center crop and scale a given Bitmap。
     *
     * @param sourceBitmap 源Bitmap
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @return 裁剪并缩放后的Bitmap
     */
    private static Bitmap centerCrop(Bitmap sourceBitmap, int targetWidth, int targetHeight) {
        int sourceWidth = sourceBitmap.getWidth();
        int sourceHeight = sourceBitmap.getHeight();

        float targetRatio = (float) targetWidth / targetHeight;
        float sourceRatio = (float) sourceWidth / sourceHeight;

        int cropX = 0;
        int cropY = 0;
        int cropWidth = sourceWidth;
        int cropHeight = sourceHeight;

        if (sourceRatio > targetRatio) {
            // The source image is wider than the target size (cropping is required horizontally).）
            // The height to be cropped remains unchanged, and the width is scaled proportionally
            cropWidth = (int) (sourceHeight * targetRatio);
            cropX = (sourceWidth - cropWidth) / 2;
        } else if (sourceRatio < targetRatio) {
            // The source image is taller than the target size (cropping is required in the vertical direction).）
            // The width to be cropped remains unchanged, and the height is scaled proportionally
            cropHeight = (int) (sourceWidth / targetRatio);
            cropY = (sourceHeight - cropHeight) / 2;
        }

        // 1. 裁剪操作 (Creates a sub-bitmap)
        Bitmap cropped = Bitmap.createBitmap(sourceBitmap, cropX, cropY, cropWidth, cropHeight);

        // 2. 缩放操作 (Resize to final target dimensions)
        // The size of the cropped image may not be exactly equal to the target size and will need to be scaled in the end.
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(cropped, targetWidth, targetHeight, true);

        // Release the intermediately cropped Bitmap (if it is not the sourceBitmap本身）
        if (cropped != sourceBitmap && !cropped.isRecycled()) {
            cropped.recycle();
        }

        return scaledBitmap;
    }

    /**
     * Save Bitmap to Local File。
     */
    public static boolean saveBitmap(Bitmap bitmap, String outputPath) {
        FileOutputStream out = null;
        try {
            File outputFile = new File(outputPath);
            // 确保目录存在
            if (outputFile.getParentFile() != null && !outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }

            out = new FileOutputStream(outputFile);
            // JPEG format is commonly used，压缩质量为 90
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            Log.d(TAG, "图片裁剪并保存成功: " + outputPath);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "保存图片失败", e);
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 释放最终的Bitmap
//            if (bitmap != null && !bitmap.isRecycled()) {
//                bitmap.recycle();
//            }
        }
    }

    /**
     * 获得圆角图片的方法
     *
     * @param bitmap
     * @param roundPx 一般设成14
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        // Create a blank of the same size as the original Bitmap Bitmap
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 创建画布来绘制 Bitmap
        Canvas canvas = new Canvas(output);
        // 设置画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);  // 开启抗锯齿
        // 创建一个圆形路径
        Path path = new Path();
        path.addCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2, Path.Direction.CCW);
        // 裁剪画布
        canvas.clipPath(path);
        // 在画布上绘制原始 Bitmap
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return output;
    }
}
