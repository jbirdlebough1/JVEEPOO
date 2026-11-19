package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.PPGRawData;
import com.veepoo.protocol.model.datas.PPGReadData;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IPPGRawDataReadListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IPPGRawDataReadListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onPPGReadStart", "", "count", "", "onPPGRawDataRead", "index", "ppgRawData", "Lcom/veepoo/protocol/model/datas/PPGRawData;", "onPPGRawDataReadComplete", "ppgReadData", "Lcom/veepoo/protocol/model/datas/PPGReadData;", "onPPGRawDataReadStop", "vpprotocol_release"})
public interface IPPGRawDataReadListener extends IListener {
    void onPPGReadStart(int count);

    void onPPGRawDataRead(int index, int count, @NotNull PPGRawData ppgRawData);

    void onPPGRawDataReadComplete(@NotNull PPGReadData ppgReadData);

    void onPPGRawDataReadStop();
}
