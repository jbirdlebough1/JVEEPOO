package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.model.datas.NetworkSever4gConfigInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/AbsNetServer4gConfigListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lcom/veepoo/protocol/listener/data/AbsNetServer4gConfigListener;", "Lcom/veepoo/protocol/listener/data/INetServer4gConfigListener;", "<init>", "()V", "onReadSuccess", "", "info", "Lcom/veepoo/protocol/model/datas/NetworkSever4gConfigInfo;", "onSettingSuccess", "onSettingFailed", "vpprotocol_release"})
public class AbsNetServer4gConfigListener implements INetServer4gConfigListener {
    @Override // com.veepoo.protocol.listener.data.INetServer4gConfigListener
    public void onReadSuccess(@NotNull NetworkSever4gConfigInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
    }

    @Override // com.veepoo.protocol.listener.data.INetServer4gConfigListener
    public void onSettingSuccess() {
    }

    @Override // com.veepoo.protocol.listener.data.INetServer4gConfigListener
    public void onSettingFailed() {
    }
}
