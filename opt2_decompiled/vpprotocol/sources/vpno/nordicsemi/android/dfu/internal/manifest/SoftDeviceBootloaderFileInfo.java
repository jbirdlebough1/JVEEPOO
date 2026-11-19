package vpno.nordicsemi.android.dfu.internal.manifest;

import com.google.gson.annotations.SerializedName;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/internal/manifest/SoftDeviceBootloaderFileInfo.class */
public class SoftDeviceBootloaderFileInfo extends FileInfo {

    @SerializedName("bl_size")
    protected int bootloaderSize;

    @SerializedName("sd_size")
    protected int softdeviceSize;

    public int getSoftdeviceSize() {
        return this.softdeviceSize;
    }

    public int getBootloaderSize() {
        return this.bootloaderSize;
    }
}
