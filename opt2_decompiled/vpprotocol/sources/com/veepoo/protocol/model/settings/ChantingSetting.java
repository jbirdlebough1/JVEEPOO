package com.veepoo.protocol.model.settings;

import kotlin.Metadata;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/model/settings/ChantingSetting.class */
@Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/veepoo/protocol/model/settings/ChantingSetting;", "", "timestamp", "", "<init>", "(J)V", "getTimestamp", "()J", "vpprotocol_release"})
public final class ChantingSetting {
    private final long timestamp;

    public ChantingSetting(long j) {
        this.timestamp = j;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
