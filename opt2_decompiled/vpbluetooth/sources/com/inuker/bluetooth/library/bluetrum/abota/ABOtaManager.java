package com.inuker.bluetooth.library.bluetrum.abota;

import androidx.annotation.NonNull;
import com.inuker.bluetooth.library.bluetrum.manager.OtaManager;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/abota/ABOtaManager.class */
public class ABOtaManager extends OtaManager {
    private final EventListener eventListener;

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/abota/ABOtaManager$EventListener.class */
    public interface EventListener extends OtaManager.EventListener {
        void sendOtaData(byte[] bArr);
    }

    public ABOtaManager(@NonNull EventListener eventListener) {
        super(eventListener);
        this.eventListener = eventListener;
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager
    protected void btSendData(byte[] data) {
        if (this.isUpdatePause) {
            return;
        }
        this.eventListener.sendOtaData(data);
    }

    public void nextRun() {
        runDataSend();
    }
}
