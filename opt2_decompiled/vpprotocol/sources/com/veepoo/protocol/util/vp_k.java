package com.veepoo.protocol.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.veepoo.protocol.customui.WatchUIType;
import java.io.File;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k.class */
public abstract class vp_k {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k$vp_a.class */
    class vp_a implements Runnable {
        final /* synthetic */ vp_c vp_a;

        vp_a(vp_c vp_cVar) {
            this.vp_a = vp_cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.vp_a.onStart();
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k$vp_b.class */
    class vp_b implements Runnable {
        final /* synthetic */ String vp_a;
        final /* synthetic */ WatchUIType vp_b;
        final /* synthetic */ Context vp_c;
        final /* synthetic */ Handler vp_d;
        final /* synthetic */ vp_c vp_e;

        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k$vp_b$vp_a.class */
        class vp_a implements Runnable {
            final /* synthetic */ String vp_a;

            vp_a(String str) {
                this.vp_a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                vp_b.this.vp_e.onComplete(this.vp_a);
            }
        }

        /* JADX INFO: renamed from: com.veepoo.protocol.util.vp_k$vp_b$vp_b, reason: collision with other inner class name */
        /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k$vp_b$vp_b.class */
        class RunnableC0011vp_b implements Runnable {
            final /* synthetic */ Exception vp_a;

            RunnableC0011vp_b(Exception exc) {
                this.vp_a = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                vp_b.this.vp_e.vp_a(this.vp_a);
            }
        }

        vp_b(String str, WatchUIType watchUIType, Context context, Handler handler, vp_c vp_cVar) {
            this.vp_a = str;
            this.vp_b = watchUIType;
            this.vp_c = context;
            this.vp_d = handler;
            this.vp_e = vp_cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            String strVp_b = vp_k.vp_b(this.vp_a);
            new StringBuilder().append("【杰理表盘传输】开始生成和保存预览图\n照片表盘本地路径:").append(this.vp_a).append("\n默认的预览图路径:").append(strVp_b).append("\n当前表盘类型:").append(this.vp_b.getUIType()).append("\n预览图四角弧度:").append(this.vp_b.getSmallBroad()).append("\n预览图外框大小:[").append(this.vp_b.getSmallBitmapWidth()).append("/").append(this.vp_b.getSmallBitmapHeight()).append("]\n预览图内框大小:[").append(this.vp_b.getSmallBitmapContentWidth()).append("/").append(this.vp_b.getSmallBitmapContentHeight()).append("]");
            Bitmap bitmapCopy = vp_k.vp_a(this.vp_c, this.vp_b, this.vp_a).copy(Bitmap.Config.RGB_565, false);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(strVp_b));
                bitmapCopy.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.vp_d.post(new vp_a(strVp_b));
            } catch (Exception e) {
                e.printStackTrace();
                new StringBuilder().append("创建预览图失败：").append(e.getMessage());
                this.vp_d.post(new RunnableC0011vp_b(e));
            }
        }
    }

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/vp_k$vp_c.class */
    public interface vp_c {
        void onStart();

        void onComplete(String str);

        void vp_a(Exception exc);
    }

    public static String vp_b(String str) {
        String strReplace = str.replace(str.substring(str.lastIndexOf("/") + 1), "bgp_w000.png");
        Log.d("【杰理表盘传输】", "杰理照片表盘预览图：" + strReplace);
        return strReplace;
    }

    public static void vp_a(Context context, WatchUIType watchUIType, String str, vp_c vp_cVar) {
        if (vp_cVar == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new vp_a(vp_cVar));
        new Thread(new vp_b(str, watchUIType, context, handler, vp_cVar)).start();
    }

    public static Bitmap vp_a(String str) {
        return BitmapFactory.decodeFile(new File(str).getAbsolutePath(), null);
    }

    public static Bitmap vp_a(Context context, WatchUIType watchUIType, Bitmap bitmap) {
        Bitmap bitmapVp_a = vp_a(watchUIType.getSmallBackgroundBitmap(context), watchUIType.getSmallBitmapWidth(), watchUIType.getSmallBitmapHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(watchUIType.getSmallBitmapWidth(), watchUIType.getSmallBitmapHeight(), bitmapVp_a.getConfig());
        Bitmap bitmapVp_a2 = vp_a(bitmap, watchUIType.getSmallBitmapContentWidth(), watchUIType.getSmallBitmapContentHeight());
        if (watchUIType.getSmallBitmapRadius() != 0) {
            bitmapVp_a2 = vp_a(bitmapVp_a2, watchUIType.getSmallBitmapRadius());
        }
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapVp_a2, watchUIType.getSmallBroad(), watchUIType.getSmallBroad(), (Paint) null);
        canvas.drawBitmap(bitmapVp_a, new Rect(0, 0, bitmapVp_a.getWidth(), bitmapVp_a.getHeight()), new Rect(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight()), (Paint) null);
        System.out.println("小图====================CNM>>>>>>>>>>>>>>>>>>");
        return bitmapCreateBitmap;
    }

    public static Bitmap vp_a(Context context, WatchUIType watchUIType, String str) {
        Bitmap bitmapVp_a = vp_a(str);
        Bitmap bitmapVp_a2 = vp_a(watchUIType.getSmallBackgroundBitmap(context), watchUIType.getSmallBitmapWidth(), watchUIType.getSmallBitmapHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(watchUIType.getSmallBitmapWidth(), watchUIType.getSmallBitmapHeight(), bitmapVp_a2.getConfig());
        Bitmap bitmapVp_a3 = vp_a(bitmapVp_a, watchUIType.getSmallBitmapContentWidth(), watchUIType.getSmallBitmapContentHeight());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmapVp_a3, watchUIType.getSmallBroad(), watchUIType.getSmallBroad(), (Paint) null);
        canvas.drawBitmap(bitmapVp_a2, new Rect(0, 0, bitmapVp_a2.getWidth(), bitmapVp_a2.getHeight()), new Rect(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight()), (Paint) null);
        System.out.println("小图====================CNM>>>>>>>>>>>>>>>>>>");
        return bitmapCreateBitmap;
    }

    public static Bitmap vp_a(Bitmap bitmap, float f) {
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

    public static Bitmap vp_a(Bitmap bitmap, int i, int i2) {
        return Bitmap.createScaledBitmap(bitmap, i, i2, true);
    }
}
