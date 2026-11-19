package com.veepoo.protocol.model.datas;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/AIDeviceConfigBean.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fB\t\b\u0016¢\u0006\u0004\b\u000b\u0010\rJ\u0006\u0010 \u001a\u00020!J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003JY\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010+\u001a\u00020!2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020\u0003HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000f\"\u0004\b\u001f\u0010\u0011¨\u00060"}, d2 = {"Lcom/veepoo/protocol/model/datas/AIDeviceConfigBean;", "", "aiQAContentMaxLength", "", "previewMaxSize", "dialMaxSize", "aiDrawingStyle", "previewResolutionW", "previewResolutionH", "previewAngleRadius", "customDialFlag", "<init>", "(IIIIIIII)V", "()V", "getAiQAContentMaxLength", "()I", "setAiQAContentMaxLength", "(I)V", "getPreviewMaxSize", "setPreviewMaxSize", "getDialMaxSize", "setDialMaxSize", "getAiDrawingStyle", "setAiDrawingStyle", "getPreviewResolutionW", "setPreviewResolutionW", "getPreviewResolutionH", "setPreviewResolutionH", "getPreviewAngleRadius", "setPreviewAngleRadius", "getCustomDialFlag", "setCustomDialFlag", "isValid", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class AIDeviceConfigBean {
    private int aiQAContentMaxLength;
    private int previewMaxSize;
    private int dialMaxSize;
    private int aiDrawingStyle;
    private int previewResolutionW;
    private int previewResolutionH;
    private int previewAngleRadius;
    private int customDialFlag;

    public AIDeviceConfigBean() {
        this(0, 0, 0, 0, 0, 0, 0, 0);
        this.aiQAContentMaxLength = 0;
        this.previewMaxSize = 0;
        this.dialMaxSize = 0;
        this.aiDrawingStyle = 0;
        this.previewResolutionW = 0;
        this.previewResolutionH = 0;
        this.previewAngleRadius = 0;
        this.customDialFlag = 0;
    }

    public static /* synthetic */ AIDeviceConfigBean copy$default(AIDeviceConfigBean aIDeviceConfigBean, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i = aIDeviceConfigBean.aiQAContentMaxLength;
        }
        if ((i9 & 2) != 0) {
            i2 = aIDeviceConfigBean.previewMaxSize;
        }
        if ((i9 & 4) != 0) {
            i3 = aIDeviceConfigBean.dialMaxSize;
        }
        if ((i9 & 8) != 0) {
            i4 = aIDeviceConfigBean.aiDrawingStyle;
        }
        if ((i9 & 16) != 0) {
            i5 = aIDeviceConfigBean.previewResolutionW;
        }
        if ((i9 & 32) != 0) {
            i6 = aIDeviceConfigBean.previewResolutionH;
        }
        if ((i9 & 64) != 0) {
            i7 = aIDeviceConfigBean.previewAngleRadius;
        }
        if ((i9 & 128) != 0) {
            i8 = aIDeviceConfigBean.customDialFlag;
        }
        return aIDeviceConfigBean.copy(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public final int getAiQAContentMaxLength() {
        return this.aiQAContentMaxLength;
    }

    public final void setAiQAContentMaxLength(int i) {
        this.aiQAContentMaxLength = i;
    }

    public final int getPreviewMaxSize() {
        return this.previewMaxSize;
    }

    public final void setPreviewMaxSize(int i) {
        this.previewMaxSize = i;
    }

    public final int getDialMaxSize() {
        return this.dialMaxSize;
    }

    public final void setDialMaxSize(int i) {
        this.dialMaxSize = i;
    }

    public final int getAiDrawingStyle() {
        return this.aiDrawingStyle;
    }

    public final void setAiDrawingStyle(int i) {
        this.aiDrawingStyle = i;
    }

    public final int getPreviewResolutionW() {
        return this.previewResolutionW;
    }

    public final void setPreviewResolutionW(int i) {
        this.previewResolutionW = i;
    }

    public final int getPreviewResolutionH() {
        return this.previewResolutionH;
    }

    public final void setPreviewResolutionH(int i) {
        this.previewResolutionH = i;
    }

    public final int getPreviewAngleRadius() {
        return this.previewAngleRadius;
    }

    public final void setPreviewAngleRadius(int i) {
        this.previewAngleRadius = i;
    }

    public final int getCustomDialFlag() {
        return this.customDialFlag;
    }

    public final void setCustomDialFlag(int i) {
        this.customDialFlag = i;
    }

    public final boolean isValid() {
        return ((((((this.aiQAContentMaxLength + this.previewMaxSize) + this.dialMaxSize) + this.aiDrawingStyle) + this.previewResolutionW) + this.previewResolutionH) + this.previewAngleRadius) + this.customDialFlag != 0;
    }

    public final int component1() {
        return this.aiQAContentMaxLength;
    }

    public final int component2() {
        return this.previewMaxSize;
    }

    public final int component3() {
        return this.dialMaxSize;
    }

    public final int component4() {
        return this.aiDrawingStyle;
    }

    public final int component5() {
        return this.previewResolutionW;
    }

    public final int component6() {
        return this.previewResolutionH;
    }

    public final int component7() {
        return this.previewAngleRadius;
    }

    public final int component8() {
        return this.customDialFlag;
    }

    @NotNull
    public final AIDeviceConfigBean copy(int aiQAContentMaxLength, int previewMaxSize, int dialMaxSize, int aiDrawingStyle, int previewResolutionW, int previewResolutionH, int previewAngleRadius, int customDialFlag) {
        return new AIDeviceConfigBean(aiQAContentMaxLength, previewMaxSize, dialMaxSize, aiDrawingStyle, previewResolutionW, previewResolutionH, previewAngleRadius, customDialFlag);
    }

    @NotNull
    public String toString() {
        return "AIDeviceConfigBean(aiQAContentMaxLength=" + this.aiQAContentMaxLength + ", previewMaxSize=" + this.previewMaxSize + ", dialMaxSize=" + this.dialMaxSize + ", aiDrawingStyle=" + this.aiDrawingStyle + ", previewResolutionW=" + this.previewResolutionW + ", previewResolutionH=" + this.previewResolutionH + ", previewAngleRadius=" + this.previewAngleRadius + ", customDialFlag=" + this.customDialFlag + ")";
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.aiQAContentMaxLength) * 31) + Integer.hashCode(this.previewMaxSize)) * 31) + Integer.hashCode(this.dialMaxSize)) * 31) + Integer.hashCode(this.aiDrawingStyle)) * 31) + Integer.hashCode(this.previewResolutionW)) * 31) + Integer.hashCode(this.previewResolutionH)) * 31) + Integer.hashCode(this.previewAngleRadius)) * 31) + Integer.hashCode(this.customDialFlag);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AIDeviceConfigBean)) {
            return false;
        }
        AIDeviceConfigBean aIDeviceConfigBean = (AIDeviceConfigBean) other;
        return this.aiQAContentMaxLength == aIDeviceConfigBean.aiQAContentMaxLength && this.previewMaxSize == aIDeviceConfigBean.previewMaxSize && this.dialMaxSize == aIDeviceConfigBean.dialMaxSize && this.aiDrawingStyle == aIDeviceConfigBean.aiDrawingStyle && this.previewResolutionW == aIDeviceConfigBean.previewResolutionW && this.previewResolutionH == aIDeviceConfigBean.previewResolutionH && this.previewAngleRadius == aIDeviceConfigBean.previewAngleRadius && this.customDialFlag == aIDeviceConfigBean.customDialFlag;
    }

    public AIDeviceConfigBean(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.aiQAContentMaxLength = i;
        this.previewMaxSize = i2;
        this.dialMaxSize = i3;
        this.aiDrawingStyle = i4;
        this.previewResolutionW = i5;
        this.previewResolutionH = i6;
        this.previewAngleRadius = i7;
        this.customDialFlag = i8;
    }
}
