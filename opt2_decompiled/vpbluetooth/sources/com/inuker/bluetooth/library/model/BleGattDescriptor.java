package com.inuker.bluetooth.library.model;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/model/BleGattDescriptor.class */
public class BleGattDescriptor implements Parcelable {
    private ParcelUuid mUuid;
    private int mPermissions;
    private byte[] mValue;
    public static final Parcelable.Creator<BleGattDescriptor> CREATOR = new Parcelable.Creator<BleGattDescriptor>() { // from class: com.inuker.bluetooth.library.model.BleGattDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattDescriptor createFromParcel(Parcel in) {
            return new BleGattDescriptor(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattDescriptor[] newArray(int size) {
            return new BleGattDescriptor[size];
        }
    };

    protected BleGattDescriptor(Parcel in) {
        this.mUuid = (ParcelUuid) in.readParcelable(ParcelUuid.class.getClassLoader());
        this.mPermissions = in.readInt();
        this.mValue = in.createByteArray();
    }

    public BleGattDescriptor(BluetoothGattDescriptor descriptor) {
        this.mUuid = new ParcelUuid(descriptor.getUuid());
        this.mPermissions = descriptor.getPermissions();
        this.mValue = descriptor.getValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mUuid, flags);
        dest.writeInt(this.mPermissions);
        dest.writeByteArray(this.mValue);
    }

    public ParcelUuid getmUuid() {
        return this.mUuid;
    }

    public void setmUuid(ParcelUuid mUuid) {
        this.mUuid = mUuid;
    }

    public int getmPermissions() {
        return this.mPermissions;
    }

    public void setmPermissions(int mPermissions) {
        this.mPermissions = mPermissions;
    }

    public byte[] getmValue() {
        return this.mValue;
    }

    public void setmValue(byte[] mValue) {
        this.mValue = mValue;
    }

    public String toString() {
        return "BleGattDescriptor{mUuid=" + this.mUuid + ", mPermissions=" + this.mPermissions + ", mValue=" + Arrays.toString(this.mValue) + '}';
    }
}
