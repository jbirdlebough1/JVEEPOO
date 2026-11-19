package com.inuker.bluetooth.library.model;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/model/BleGattCharacter.class */
public class BleGattCharacter implements Parcelable {
    private ParcelUuid uuid;
    private int property;
    private int permissions;
    private List<BleGattDescriptor> descriptors;
    public static final Parcelable.Creator<BleGattCharacter> CREATOR = new Parcelable.Creator<BleGattCharacter>() { // from class: com.inuker.bluetooth.library.model.BleGattCharacter.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattCharacter createFromParcel(Parcel in) {
            return new BleGattCharacter(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattCharacter[] newArray(int size) {
            return new BleGattCharacter[size];
        }
    };

    protected BleGattCharacter(Parcel in) {
        this.uuid = (ParcelUuid) in.readParcelable(ParcelUuid.class.getClassLoader());
        this.property = in.readInt();
        this.permissions = in.readInt();
        this.descriptors = in.createTypedArrayList(BleGattDescriptor.CREATOR);
    }

    public BleGattCharacter(BluetoothGattCharacteristic characteristic) {
        this.uuid = new ParcelUuid(characteristic.getUuid());
        this.property = characteristic.getProperties();
        this.permissions = characteristic.getPermissions();
        for (BluetoothGattDescriptor descriptor : characteristic.getDescriptors()) {
            getDescriptors().add(new BleGattDescriptor(descriptor));
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.uuid, flags);
        dest.writeInt(this.property);
        dest.writeInt(this.permissions);
        dest.writeTypedList(this.descriptors);
    }

    public UUID getUuid() {
        return this.uuid.getUuid();
    }

    public void setUuid(ParcelUuid uuid) {
        this.uuid = uuid;
    }

    public int getProperty() {
        return this.property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public int getPermissions() {
        return this.permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public List<BleGattDescriptor> getDescriptors() {
        if (this.descriptors == null) {
            this.descriptors = new ArrayList();
        }
        return this.descriptors;
    }

    public void setDescriptors(List<BleGattDescriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public String toString() {
        return "BleGattCharacter{uuid=" + this.uuid + ", property=" + this.property + ", permissions=" + this.permissions + ", descriptors=" + this.descriptors + '}';
    }
}
