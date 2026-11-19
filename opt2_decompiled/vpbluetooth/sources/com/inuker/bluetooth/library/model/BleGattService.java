package com.inuker.bluetooth.library.model;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/model/BleGattService.class */
public class BleGattService implements Parcelable, Comparable {
    private ParcelUuid uuid;
    private List<BleGattCharacter> characters;
    public static final Parcelable.Creator<BleGattService> CREATOR = new Parcelable.Creator<BleGattService>() { // from class: com.inuker.bluetooth.library.model.BleGattService.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattService createFromParcel(Parcel in) {
            return new BleGattService(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattService[] newArray(int size) {
            return new BleGattService[size];
        }
    };

    public BleGattService(UUID uuid, Map<UUID, BluetoothGattCharacteristic> characters) {
        this.uuid = new ParcelUuid(uuid);
        for (BluetoothGattCharacteristic characteristic : characters.values()) {
            getCharacters().add(new BleGattCharacter(characteristic));
        }
    }

    protected BleGattService(Parcel in) {
        this.uuid = (ParcelUuid) in.readParcelable(ParcelUuid.class.getClassLoader());
        this.characters = in.createTypedArrayList(BleGattCharacter.CREATOR);
    }

    public UUID getUUID() {
        return this.uuid.getUuid();
    }

    public List<BleGattCharacter> getCharacters() {
        if (this.characters == null) {
            this.characters = new ArrayList();
        }
        return this.characters;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Service: %s\n", this.uuid));
        List<BleGattCharacter> characters = getCharacters();
        int size = characters.size();
        for (int i = 0; i < size; i++) {
            sb.append(String.format(">>> Character: %s", characters.get(i)));
            if (i != size - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object another) {
        if (another == null) {
            return 1;
        }
        BleGattService anotherService = (BleGattService) another;
        return getUUID().compareTo(anotherService.getUUID());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.uuid, flags);
        dest.writeTypedList(this.characters);
    }
}
