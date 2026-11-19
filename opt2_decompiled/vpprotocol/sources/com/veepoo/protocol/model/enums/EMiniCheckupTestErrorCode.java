package com.veepoo.protocol.model.enums;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/enums/EMiniCheckupTestErrorCode.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/veepoo/protocol/model/enums/EMiniCheckupTestErrorCode;", "", "errorCode", "", "<init>", "(Ljava/lang/String;II)V", "getErrorCode", "()I", "FUNCTION_NOT_SUPPORT", "TEST_COMPLETE_NO_RESULT", "DEVICE_BUSY", "LOW_POWER", "WEARING_ABNORMALITY", "ECG_LEAD_DETACHMENT", "vpprotocol_release"})
public enum EMiniCheckupTestErrorCode {
    FUNCTION_NOT_SUPPORT(0),
    TEST_COMPLETE_NO_RESULT(2),
    DEVICE_BUSY(3),
    LOW_POWER(4),
    WEARING_ABNORMALITY(6),
    ECG_LEAD_DETACHMENT(7);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final int errorCode;

    EMiniCheckupTestErrorCode(int i) {
        this.errorCode = i;
    }

    @NotNull
    public static EnumEntries<EMiniCheckupTestErrorCode> getEntries() {
        return $ENTRIES;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
