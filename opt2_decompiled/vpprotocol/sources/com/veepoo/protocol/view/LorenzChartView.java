package com.veepoo.protocol.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.veepoo.protocol.model.datas.HRVOriginData;
import com.veepoo.protocol.util.HrvScoreUtil;
import java.util.List;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/view/LorenzChartView.class */
public class LorenzChartView extends View {
    private int vp_a;
    private int vp_b;
    private Paint vp_c;
    private Paint vp_d;
    private Paint vp_e;
    Bitmap vp_f;
    Canvas vp_g;
    int vp_h;
    int vp_i;
    int vp_j;
    int vp_k;
    int vp_l;
    float vp_m;
    double[] vp_n;
    Context vp_o;

    public LorenzChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.vp_f = null;
        this.vp_g = null;
        this.vp_h = 2;
        this.vp_i = 1;
        this.vp_l = 0;
        this.vp_m = 20.0f;
        this.vp_n = null;
        this.vp_o = context;
        this.vp_j = Color.parseColor("#ec1a3b");
        this.vp_k = Color.parseColor("#888888");
        this.vp_l = Color.parseColor("#888888");
        vp_b();
    }

    private void vp_c() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int i = this.vp_a;
        layoutParams.width = i;
        layoutParams.height = (i / 4) * 3;
        setLayoutParams(layoutParams);
        invalidate();
        requestLayout();
    }

    private void vp_b(Canvas canvas) {
        Rect rect = new Rect();
        this.vp_e.getTextBounds("2000", 0, 4, rect);
        int iWidth = rect.width();
        int iHeight = rect.height();
        float f = iWidth;
        this.vp_m = f * 1.2f;
        canvas.drawText("2000", 0.0f, f, this.vp_e);
        canvas.save();
        float f2 = iWidth / 4;
        canvas.rotate(90.0f, f2, (this.vp_b / 2) - this.vp_m);
        canvas.drawText("RRN+1(ms)", f2, (this.vp_b / 2) - this.vp_m, this.vp_e);
        canvas.restore();
        float f3 = iWidth / 2;
        canvas.drawText("0", f3, this.vp_b - iHeight, this.vp_e);
        float f4 = this.vp_m;
        canvas.drawText("RRN(ms)", (f4 + ((this.vp_a - (f4 * 2.0f)) / 2.0f)) - f3, this.vp_b - iHeight, this.vp_e);
        canvas.drawText("2000", this.vp_a - iWidth, this.vp_b - iHeight, this.vp_e);
        float f5 = this.vp_m;
        canvas.drawLine(f5, this.vp_b - f5, f5, f5, this.vp_c);
        float f6 = this.vp_m;
        int i = this.vp_b;
        canvas.drawLine(f6, i - f6, (this.vp_a - f6) + f6, i - f6, this.vp_c);
        float f7 = this.vp_m;
        canvas.drawLine(f7, this.vp_b - f7, this.vp_a - f7, f7, this.vp_c);
    }

    private float vp_a(float f, Context context) {
        return f / (context.getResources().getDisplayMetrics().densityDpi / 160.0f);
    }

    public void updateData(List<HRVOriginData> list) {
        new HrvScoreUtil();
        this.vp_n = HrvScoreUtil.getLorenData(list);
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        this.vp_a = View.MeasureSpec.getSize(i);
        this.vp_b = View.MeasureSpec.getSize(i2);
        vp_c();
        setMeasuredDimension(this.vp_a, this.vp_b);
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        vp_a();
        canvas.drawBitmap(this.vp_f, 0.0f, 0.0f, new Paint());
        vp_a(this.vp_g);
    }

    public void setTextColor(int i) {
        this.vp_e.setColor(i);
    }

    public void setTextSize(int i) {
        this.vp_e.setTextSize(vp_a(i, this.vp_o));
    }

    public void setLineWidth(int i) {
        this.vp_c.setStrokeWidth(vp_a(i, this.vp_o));
    }

    public void setLineColor(int i) {
        this.vp_c.setColor(i);
    }

    public void setDotColor(int i) {
        this.vp_d.setColor(i);
    }

    public void setDotSize(int i) {
        this.vp_i = (int) vp_a(i, this.vp_o);
    }

    private void vp_a(Canvas canvas, double[] dArr) {
        int iVp_a = vp_a(dArr);
        int i = 0;
        while (i < dArr.length - 1) {
            float fVp_a = vp_a(dArr[i], iVp_a);
            int i2 = i + 1;
            i = i2;
            canvas.drawCircle(fVp_a, vp_b(dArr[i2], iVp_a), this.vp_i, this.vp_d);
        }
    }

    private float vp_b(double d, int i) {
        int i2 = this.vp_b;
        float f = this.vp_m;
        return (i2 - f) - ((((float) d) / i) * (i2 - (f * 2.0f)));
    }

    private void vp_b() {
        Paint paint = new Paint(1);
        this.vp_c = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.vp_c.setAntiAlias(true);
        this.vp_c.setStrokeWidth(this.vp_h);
        this.vp_c.setColor(this.vp_k);
        Paint paint2 = new Paint(1);
        this.vp_e = paint2;
        paint2.setStyle(style);
        this.vp_e.setColor(this.vp_l);
        this.vp_e.setAntiAlias(true);
        this.vp_e.setStrokeWidth(5.0f);
        this.vp_e.setTextSize(35.0f);
        Paint paint3 = new Paint(1);
        this.vp_d = paint3;
        paint3.setStyle(style);
        this.vp_d.setColor(this.vp_j);
        this.vp_d.setAntiAlias(true);
        this.vp_d.setStrokeWidth(1.0f);
    }

    private void vp_a(Canvas canvas) {
        this.vp_g.drawColor(-1);
        vp_b(canvas);
        double[] dArr = this.vp_n;
        if (dArr == null || dArr.length <= 0) {
            return;
        }
        vp_a(canvas, dArr);
    }

    private int vp_a(double[] dArr) {
        double d = 2000.0d;
        for (double d2 : dArr) {
            if (d2 > d) {
                d = d2;
            }
        }
        return (int) d;
    }

    private float vp_a(double d, int i) {
        float f = this.vp_m;
        return f + ((((float) d) / i) * (this.vp_a - (f * 2.0f)));
    }

    private void vp_a() {
        if (this.vp_f == null) {
            this.vp_f = Bitmap.createBitmap(this.vp_a, this.vp_b, Bitmap.Config.ARGB_8888);
            this.vp_g = new Canvas(this.vp_f);
        }
    }
}
