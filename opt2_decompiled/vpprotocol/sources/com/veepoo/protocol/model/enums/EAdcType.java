package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EAdcType.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/veepoo/protocol/model/enums/EAdcType;", "", "tag", "", "<init>", "(Ljava/lang/String;IB)V", "getTag", "()B", "NO_ADC_DATA", "ADC_DATA_FILTERING", "ADC_DATA_ORIGINAL", "vpprotocol_release"})
public enum EAdcType {
    NO_ADC_DATA((byte) 0),
    ADC_DATA_FILTERING((byte) 1),
    ADC_DATA_ORIGINAL((byte) 2);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final byte tag;

    EAdcType(byte b) {
        this.tag = b;
    }

    @NotNull
    public static EnumEntries<EAdcType> getEntries() {
        return $ENTRIES;
    }

    public final byte getTag() {
        return this.tag;
    }
}
