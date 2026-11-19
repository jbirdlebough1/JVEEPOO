package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.Contact;
import com.veepoo.protocol.model.enums.EContactOpt;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IContactOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IContactOptListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onContactOptSuccess", "", "opt", "Lcom/veepoo/protocol/model/enums/EContactOpt;", "crc", "", "onContactOptFailed", "onContactReadSuccess", "contactList", "", "Lcom/veepoo/protocol/model/datas/Contact;", "onContactReadASSameCRC", "onContactReadFailed", "vpprotocol_release"})
public interface IContactOptListener extends IListener {
    void onContactOptSuccess(@NotNull EContactOpt opt, int crc);

    void onContactOptFailed(@NotNull EContactOpt opt);

    void onContactReadSuccess(@NotNull List<Contact> contactList);

    void onContactReadASSameCRC();

    void onContactReadFailed();
}
