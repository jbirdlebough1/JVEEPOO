package com.inuker.bluetooth.library.bluetrum.abota;

import androidx.annotation.NonNull;
import com.inuker.bluetooth.library.bluetrum.log.Logger;
import com.inuker.bluetooth.library.bluetrum.manager.OtaError;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/abota/ABOta.class */
public class ABOta implements ABOtaManager.EventListener {
    public static final int ERROR_CODE_DEVICE_REFUSED = 4097;
    public static final int ERROR_CODE_TIMEOUT = 4099;
    public static final int ERROR_CODE_TWS_DISCONNECTED = 4100;
    public static final int ERROR_CODE_UNKNOWN = 4352;
    public static final int ERROR_CODE_SAME_FIRMWARE = 1;
    public static final int ERROR_CODE_KEY_MISMATCH = 2;
    public static final int ERROR_CODE_CRC_ERROR = 11;
    public static final int OTA_STATUS_READY = 0;
    public static final int OTA_STATUS_START = 1;
    public static final int OTA_STATUS_UPDATING = 2;
    public static final int OTA_STATUS_PAUSE = 3;
    public static final int OTA_STATUS_CONTINUE = 4;
    public static final int OTA_STATUS_SUCCESS = 5;
    public static final int OTA_STATUS_WAIT_FINISH = 6;
    public static final int OTA_STATUS_FAIL = 7;
    private SendCallback sendCallback;
    private EventListener eventListener;
    private final ABOtaManager otaManager = new ABOtaManager(this);

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/abota/ABOta$EventListener.class */
    public interface EventListener {
        void onStatusChanged(int i, int i2);

        void onReceiveVersion(int i);

        void onReceiveTwsInfo(boolean z, boolean z2);

        void onReceiveChannel(boolean z);
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/bluetrum/abota/ABOta$SendCallback.class */
    public interface SendCallback {
        void sendData(byte[] bArr);
    }

    public void setSendCallback(SendCallback sendCallback) {
        this.sendCallback = sendCallback;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void removeEventListener() {
        this.eventListener = null;
    }

    public void prepareToUpdate() {
        Logger.d("中科", "=============>>>> prepareToUpdate");
        this.otaManager.prepareToUpdate();
    }

    public void setOtaData(@NonNull byte[] otaData) {
        this.otaManager.setOtaData(otaData);
    }

    public void startOTA() {
        this.otaManager.startOTA();
    }

    public boolean isUpdating() {
        return this.otaManager.isUpdating();
    }

    private void handleTWSMessage() {
        if (this.eventListener != null) {
            this.eventListener.onReceiveTwsInfo(this.otaManager.isTwsDevice(), this.otaManager.isTwsConnected());
        }
    }

    private void notifyStatusChanged(int status, int value) {
        if (this.eventListener != null) {
            this.eventListener.onStatusChanged(status, value);
        }
    }

    public void handleData(byte[] data) {
        this.otaManager.processData(data);
    }

    public void nextRun() {
        this.otaManager.nextRun();
    }

    @Override // com.inuker.bluetooth.library.bluetrum.abota.ABOtaManager.EventListener
    public void sendOtaData(byte[] data) {
        if (this.sendCallback != null) {
            this.sendCallback.sendData(data);
        }
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaReady() {
        notifyStatusChanged(0, 0);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaStart() {
        notifyStatusChanged(1, 0);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaProgress(int progress) {
        notifyStatusChanged(2, progress);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaStop() {
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaFinish() {
        if (this.otaManager.isCompressedData()) {
            notifyStatusChanged(6, 0);
        } else {
            notifyStatusChanged(5, 0);
        }
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaPause() {
        notifyStatusChanged(3, 0);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaContinue() {
        notifyStatusChanged(4, 0);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onOtaError(OtaError error) {
        int errorCode;
        if (error == OtaError.REPORT_FROM_DEVICE) {
            int deviceErrorCode = OtaError.REPORT_FROM_DEVICE.getDeviceErrorCode();
            notifyStatusChanged(7, deviceErrorCode);
            return;
        }
        switch (error) {
            case REFUSED_BY_DEVICE:
                errorCode = 4097;
                break;
            case TIMEOUT_RECEIVE_RESPONSE:
                errorCode = 4099;
                break;
            default:
                errorCode = 4352;
                break;
        }
        notifyStatusChanged(7, errorCode);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onTWSDisconnected() {
        notifyStatusChanged(7, ERROR_CODE_TWS_DISCONNECTED);
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onReceiveVersion(int version) {
        if (this.eventListener != null) {
            this.eventListener.onReceiveVersion(version);
        }
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onReceiveIsTWS(boolean isTWS) {
        handleTWSMessage();
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onReceiveTWSConnected(boolean connected) {
        handleTWSMessage();
    }

    @Override // com.inuker.bluetooth.library.bluetrum.manager.OtaManager.EventListener
    public void onReceiveChannel(boolean isLeftChannel) {
        if (this.eventListener != null) {
            this.eventListener.onReceiveChannel(isLeftChannel);
        }
    }
}
