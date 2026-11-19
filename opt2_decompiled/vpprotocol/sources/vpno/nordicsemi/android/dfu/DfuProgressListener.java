package vpno.nordicsemi.android.dfu;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuProgressListener.class */
public interface DfuProgressListener {
    void onDeviceConnecting(String str);

    void onDeviceConnected(String str);

    void onDfuProcessStarting(String str);

    void onDfuProcessStarted(String str);

    void onEnablingDfuMode(String str);

    void onProgressChanged(String str, int i, float f, float f2, int i2, int i3);

    void onFirmwareValidating(String str);

    void onDeviceDisconnecting(String str);

    void onDeviceDisconnected(String str);

    void onDfuCompleted(String str);

    void onDfuAborted(String str);

    void onError(String str, int i, int i2, String str2);
}
