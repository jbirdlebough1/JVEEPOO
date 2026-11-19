package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.MagneticTherapy;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IMagneticTherapyListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IMagneticTherapyListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "functionNotSupport", "", "onMagneticTherapyDurationError", "min", "", "max", "onMagneticTherapyChange", "data", "Lcom/veepoo/protocol/model/datas/MagneticTherapy;", "onMagneticTherapyOpen", "onMagneticTherapyClose", "vpprotocol_release"})
public interface IMagneticTherapyListener extends IListener {
    void functionNotSupport();

    void onMagneticTherapyDurationError(int min, int max);

    void onMagneticTherapyChange(@NotNull MagneticTherapy data);

    void onMagneticTherapyOpen(@NotNull MagneticTherapy data);

    void onMagneticTherapyClose(@NotNull MagneticTherapy data);
}
