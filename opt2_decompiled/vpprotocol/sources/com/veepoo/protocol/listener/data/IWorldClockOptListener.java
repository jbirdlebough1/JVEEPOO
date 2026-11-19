package com.veepoo.protocol.listener.data;

import com.veepoo.protocol.listener.base.IListener;
import com.veepoo.protocol.model.datas.WorldClock;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IWorldClockOptListener.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\rJ\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lcom/veepoo/protocol/listener/data/IWorldClockOptListener;", "Lcom/veepoo/protocol/listener/base/IListener;", "onWorldClockReadSuccess", "", "worldClock", "", "Lcom/veepoo/protocol/model/datas/WorldClock;", "crc", "", "onWorldClockOptSuccess", "opt", "Lcom/veepoo/protocol/listener/data/IWorldClockOptListener$WorldClockOpt;", "onWorldClockOptFailed", "WorldClockOpt", "vpprotocol_release"})
public interface IWorldClockOptListener extends IListener {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IWorldClockOptListener$WorldClockOpt.class */
    @Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/veepoo/protocol/listener/data/IWorldClockOptListener$WorldClockOpt;", "", "<init>", "(Ljava/lang/String;I)V", "READ", "ADD", "MOVE", "DELETE", "DELETE_BY_DEVICE", "vpprotocol_release"})
    public enum WorldClockOpt {
        READ,
        ADD,
        MOVE,
        DELETE,
        DELETE_BY_DEVICE;

        private static final /* synthetic */ EnumEntries vp_b = EnumEntriesKt.enumEntries(vp_a());

        @NotNull
        public static EnumEntries<WorldClockOpt> getEntries() {
            return vp_b;
        }
    }

    void onWorldClockReadSuccess(@NotNull List<WorldClock> worldClock, int crc);

    void onWorldClockOptSuccess(@NotNull WorldClockOpt opt, int crc);

    void onWorldClockOptFailed(@NotNull WorldClockOpt opt);
}
