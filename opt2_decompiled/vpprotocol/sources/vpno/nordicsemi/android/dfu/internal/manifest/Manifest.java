package vpno.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/manifest/Manifest.class */
public class Manifest {
    protected FileInfo application;
    protected FileInfo bootloader;
    protected FileInfo softdevice;

    @SerializedName("softdevice_bootloader")
    protected SoftDeviceBootloaderFileInfo softdeviceBootloader;

    public FileInfo getApplicationInfo() {
        return this.application;
    }

    public FileInfo getBootloaderInfo() {
        return this.bootloader;
    }

    public FileInfo getSoftdeviceInfo() {
        return this.softdevice;
    }

    public SoftDeviceBootloaderFileInfo getSoftdeviceBootloaderInfo() {
        return this.softdeviceBootloader;
    }
}
