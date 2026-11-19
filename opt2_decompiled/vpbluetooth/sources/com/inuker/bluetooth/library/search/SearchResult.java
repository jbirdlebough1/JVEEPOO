package com.inuker.bluetooth.library.search;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/SearchResult.class */
public class SearchResult implements Parcelable {
    public BluetoothDevice device;
    public int rssi;
    public byte[] scanRecord;
    public static final Parcelable.Creator<SearchResult> CREATOR = new Parcelable.Creator<SearchResult>() { // from class: com.inuker.bluetooth.library.search.SearchResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    public SearchResult(BluetoothDevice device) {
        this(device, 0, null);
    }

    public SearchResult(BluetoothDevice device, int rssi, byte[] scanRecord) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
    }

    public String getName() {
        String name = this.device.getName();
        return TextUtils.isEmpty(name) ? "NULL" : name;
    }

    public String getAddress() {
        return this.device != null ? this.device.getAddress() : "";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(", mac = " + this.device.getAddress());
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.device, 0);
        dest.writeInt(this.rssi);
        dest.writeByteArray(this.scanRecord);
    }

    public SearchResult(Parcel in) {
        this.device = (BluetoothDevice) in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.rssi = in.readInt();
        this.scanRecord = in.createByteArray();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SearchResult that = (SearchResult) o;
        return this.device.equals(that.device);
    }

    public int hashCode() {
        return this.device.hashCode();
    }
}
