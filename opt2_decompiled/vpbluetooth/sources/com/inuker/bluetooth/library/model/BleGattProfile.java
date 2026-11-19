package com.inuker.bluetooth.library.model;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;
import com.inuker.bluetooth.library.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/model/BleGattProfile.class */
public class BleGattProfile implements Parcelable {
    private List<BleGattService> services;
    public static final Parcelable.Creator<BleGattProfile> CREATOR = new Parcelable.Creator<BleGattProfile>() { // from class: com.inuker.bluetooth.library.model.BleGattProfile.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattProfile createFromParcel(Parcel in) {
            return new BleGattProfile(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BleGattProfile[] newArray(int size) {
            return new BleGattProfile[size];
        }
    };

    public BleGattProfile(Map<UUID, Map<UUID, BluetoothGattCharacteristic>> map) {
        List<BleGattService> serviceList = new ArrayList<>();
        for (Map.Entry<UUID, Map<UUID, BluetoothGattCharacteristic>> entry : map.entrySet()) {
            UUID serviceUUID = entry.getKey();
            Map<UUID, BluetoothGattCharacteristic> characters = entry.getValue();
            BleGattService service = new BleGattService(serviceUUID, characters);
            if (!serviceList.contains(service)) {
                serviceList.add(service);
            }
        }
        addServices(serviceList);
    }

    public BleGattProfile(Parcel in) {
        in.readTypedList(getServices(), BleGattService.CREATOR);
    }

    public void addServices(List<BleGattService> services) {
        Collections.sort(services);
        getServices().addAll(services);
    }

    public List<BleGattService> getServices() {
        if (this.services == null) {
            this.services = new ArrayList();
        }
        return this.services;
    }

    public BleGattService getService(UUID serviceId) {
        if (serviceId == null) {
            return null;
        }
        List<BleGattService> services = getServices();
        for (BleGattService service : services) {
            if (service.getUUID().equals(serviceId)) {
                return service;
            }
        }
        return null;
    }

    public boolean containsCharacter(UUID serviceId, UUID characterId) {
        BleGattService service;
        if (serviceId != null && characterId != null && (service = getService(serviceId)) != null) {
            List<BleGattCharacter> characters = service.getCharacters();
            if (!ListUtils.isEmpty(characters)) {
                for (BleGattCharacter character : characters) {
                    if (characterId.equals(character.getUuid())) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(getServices());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (BleGattService service : this.services) {
            sb.append(service).append("\n");
        }
        return sb.toString();
    }
}
