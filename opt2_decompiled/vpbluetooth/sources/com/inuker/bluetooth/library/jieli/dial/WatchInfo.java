package com.inuker.bluetooth.library.jieli.dial;

import android.os.Parcel;
import android.os.Parcelable;
import com.jieli.jl_fatfs.model.FatFile;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/jieli/dial/WatchInfo.class */
public class WatchInfo implements Parcelable {
    public static final int WATCH_STATUS_NONE_EXIST = 0;
    public static final int WATCH_STATUS_EXIST = 1;
    public static final int WATCH_STATUS_USING = 2;
    private String name;
    private int status;
    private String bitmapUri;
    private String uuid;
    private String version;
    private long size;
    private String fileUrl;
    private FatFile mFatFile;
    private String updateUUID;
    private String updateVersion;
    private String updateUrl;
    private String customBgFatPath;
    public static final Parcelable.Creator<WatchInfo> CREATOR = new Parcelable.Creator<WatchInfo>() { // from class: com.inuker.bluetooth.library.jieli.dial.WatchInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchInfo createFromParcel(Parcel in) {
            return new WatchInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WatchInfo[] newArray(int size) {
            return new WatchInfo[size];
        }
    };

    public WatchInfo() {
    }

    protected WatchInfo(Parcel in) {
        this.name = in.readString();
        this.status = in.readInt();
        this.bitmapUri = in.readString();
        this.uuid = in.readString();
        this.version = in.readString();
        this.size = in.readLong();
        this.fileUrl = in.readString();
        this.mFatFile = in.readParcelable(FatFile.class.getClassLoader());
        this.updateUUID = in.readString();
        this.updateVersion = in.readString();
        this.updateUrl = in.readString();
        this.customBgFatPath = in.readString();
    }

    public String getName() {
        return this.name;
    }

    public WatchInfo setName(String name) {
        this.name = name;
        return this;
    }

    public int getStatus() {
        return this.status;
    }

    public WatchInfo setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getBitmapUri() {
        return this.bitmapUri;
    }

    public WatchInfo setBitmapUri(String bitmapUri) {
        this.bitmapUri = bitmapUri;
        return this;
    }

    public String getUuid() {
        return this.uuid;
    }

    public WatchInfo setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public WatchInfo setVersion(String version) {
        this.version = version;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public WatchInfo setSize(long size) {
        this.size = size;
        return this;
    }

    public String getFileUrl() {
        return this.fileUrl;
    }

    public WatchInfo setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        return this;
    }

    public FatFile getFatFile() {
        return this.mFatFile;
    }

    public WatchInfo setFatFile(FatFile fatFile) {
        this.mFatFile = fatFile;
        return this;
    }

    public String getUpdateUUID() {
        return this.updateUUID;
    }

    public WatchInfo setUpdateUUID(String updateUUID) {
        this.updateUUID = updateUUID;
        return this;
    }

    public String getUpdateVersion() {
        return this.updateVersion;
    }

    public WatchInfo setUpdateVersion(String updateVersion) {
        this.updateVersion = updateVersion;
        return this;
    }

    public String getUpdateUrl() {
        return this.updateUrl;
    }

    public WatchInfo setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
        return this;
    }

    public boolean hasUpdate() {
        return (this.updateUUID == null || this.updateVersion == null || this.version == null || this.updateVersion.compareTo(this.version) <= 0 || this.updateUrl == null) ? false : true;
    }

    public String getCustomBgFatPath() {
        return this.customBgFatPath;
    }

    public WatchInfo setCustomBgFatPath(String customBgFatPath) {
        this.customBgFatPath = customBgFatPath;
        return this;
    }

    public boolean hasCustomBgFatPath() {
        return (this.customBgFatPath == null || this.customBgFatPath.equals("null") || !this.customBgFatPath.contains("/BGP")) ? false : true;
    }

    public String toString() {
        return "WatchInfo{name='" + this.name + "', status=" + this.status + ", bitmapUri='" + this.bitmapUri + "', uuid='" + this.uuid + "', version='" + this.version + "', size=" + this.size + ", fileUrl='" + this.fileUrl + "', mFatFile=" + this.mFatFile + ", updateUUID='" + this.updateUUID + "', updateVersion='" + this.updateVersion + "', updateUrl='" + this.updateUrl + "', customBgFatPath='" + this.customBgFatPath + "'}";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.status);
        dest.writeString(this.bitmapUri);
        dest.writeString(this.uuid);
        dest.writeString(this.version);
        dest.writeLong(this.size);
        dest.writeString(this.fileUrl);
        dest.writeParcelable(this.mFatFile, flags);
        dest.writeString(this.updateUUID);
        dest.writeString(this.updateVersion);
        dest.writeString(this.updateUrl);
        dest.writeString(this.customBgFatPath);
    }
}
