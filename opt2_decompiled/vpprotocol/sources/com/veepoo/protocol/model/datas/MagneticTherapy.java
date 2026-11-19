package com.veepoo.protocol.model.datas;

import androidx.annotation.IntRange;
import com.veepoo.protocol.model.enums.MagneticTherapyAckState;
import com.veepoo.protocol.model.enums.MagneticTherapyType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/datas/MagneticTherapy.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\fJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lcom/veepoo/protocol/model/datas/MagneticTherapy;", "", "type", "Lcom/veepoo/protocol/model/enums/MagneticTherapyType;", "isOpen", "", "level", "", "ackState", "Lcom/veepoo/protocol/model/enums/MagneticTherapyAckState;", "<init>", "(Lcom/veepoo/protocol/model/enums/MagneticTherapyType;ZILcom/veepoo/protocol/model/enums/MagneticTherapyAckState;)V", "(Lcom/veepoo/protocol/model/enums/MagneticTherapyType;ZI)V", "getType", "()Lcom/veepoo/protocol/model/enums/MagneticTherapyType;", "()Z", "getLevel", "()I", "getAckState", "()Lcom/veepoo/protocol/model/enums/MagneticTherapyAckState;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "vpprotocol_release"})
public final /* data */ class MagneticTherapy {

    @NotNull
    private final MagneticTherapyType type;
    private final boolean isOpen;
    private final int level;

    @NotNull
    private final MagneticTherapyAckState ackState;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MagneticTherapy(@NotNull MagneticTherapyType magneticTherapyType, boolean z, int i) {
        this(magneticTherapyType, z, i, MagneticTherapyAckState.UNKNOWN_DEFAULT);
        Intrinsics.checkNotNullParameter(magneticTherapyType, "type");
    }

    public static /* synthetic */ MagneticTherapy copy$default(MagneticTherapy magneticTherapy, MagneticTherapyType magneticTherapyType, boolean z, int i, MagneticTherapyAckState magneticTherapyAckState, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            magneticTherapyType = magneticTherapy.type;
        }
        if ((i2 & 2) != 0) {
            z = magneticTherapy.isOpen;
        }
        if ((i2 & 4) != 0) {
            i = magneticTherapy.level;
        }
        if ((i2 & 8) != 0) {
            magneticTherapyAckState = magneticTherapy.ackState;
        }
        return magneticTherapy.copy(magneticTherapyType, z, i, magneticTherapyAckState);
    }

    @NotNull
    public final MagneticTherapyType getType() {
        return this.type;
    }

    public final boolean isOpen() {
        return this.isOpen;
    }

    public final int getLevel() {
        return this.level;
    }

    @NotNull
    public final MagneticTherapyAckState getAckState() {
        return this.ackState;
    }

    @NotNull
    public final MagneticTherapyType component1() {
        return this.type;
    }

    public final boolean component2() {
        return this.isOpen;
    }

    public final int component3() {
        return this.level;
    }

    @NotNull
    public final MagneticTherapyAckState component4() {
        return this.ackState;
    }

    @NotNull
    public final MagneticTherapy copy(@NotNull MagneticTherapyType type, boolean isOpen, @IntRange(from = 1, to = 7) int level, @NotNull MagneticTherapyAckState ackState) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(ackState, "ackState");
        return new MagneticTherapy(type, isOpen, level, ackState);
    }

    @NotNull
    public String toString() {
        return "MagneticTherapy(type=" + this.type + ", isOpen=" + this.isOpen + ", level=" + this.level + ", ackState=" + this.ackState + ")";
    }

    public int hashCode() {
        return (((((this.type.hashCode() * 31) + Boolean.hashCode(this.isOpen)) * 31) + Integer.hashCode(this.level)) * 31) + this.ackState.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MagneticTherapy)) {
            return false;
        }
        MagneticTherapy magneticTherapy = (MagneticTherapy) other;
        return this.type == magneticTherapy.type && this.isOpen == magneticTherapy.isOpen && this.level == magneticTherapy.level && this.ackState == magneticTherapy.ackState;
    }

    public MagneticTherapy(@NotNull MagneticTherapyType magneticTherapyType, boolean z, @IntRange(from = 1, to = 7) int i, @NotNull MagneticTherapyAckState magneticTherapyAckState) {
        Intrinsics.checkNotNullParameter(magneticTherapyType, "type");
        Intrinsics.checkNotNullParameter(magneticTherapyAckState, "ackState");
        this.type = magneticTherapyType;
        this.isOpen = z;
        this.level = i;
        this.ackState = magneticTherapyAckState;
    }
}
