package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EContactOpt.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/veepoo/protocol/model/enums/EContactOpt;", "", "des", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getDes", "()Ljava/lang/String;", "setDes", "(Ljava/lang/String;)V", "READ", "SETTING", "SETTING_SOS", "MOVE", "DELETE", "vpprotocol_release"})
public enum EContactOpt {
    READ("read contact list"),
    SETTING("setting contact"),
    SETTING_SOS("setting contact"),
    MOVE("move contact position"),
    DELETE("delete one contact");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    @NotNull
    private String des;

    EContactOpt(String str) {
        this.des = str;
    }

    @NotNull
    public static EnumEntries<EContactOpt> getEntries() {
        return $ENTRIES;
    }

    @NotNull
    public final String getDes() {
        return this.des;
    }

    public final void setDes(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.des = str;
    }
}
