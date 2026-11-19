package com.inuker.bluetooth.library.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.inuker.bluetooth.library.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/SearchRequest.class */
public class SearchRequest implements Parcelable {
    private List<SearchTask> tasks;
    public static final Parcelable.Creator<SearchRequest> CREATOR = new Parcelable.Creator<SearchRequest>() { // from class: com.inuker.bluetooth.library.search.SearchRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchRequest createFromParcel(Parcel source) {
            return new SearchRequest(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.tasks);
    }

    public SearchRequest() {
    }

    protected SearchRequest(Parcel in) {
        this.tasks = new ArrayList();
        in.readTypedList(this.tasks, SearchTask.CREATOR);
    }

    public List<SearchTask> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<SearchTask> tasks) {
        this.tasks = tasks;
    }

    /* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/SearchRequest$Builder.class */
    public static class Builder {
        private List<SearchTask> tasks = new ArrayList();

        public Builder searchBluetoothLeDevice(int duration) {
            if (BluetoothUtils.isBleSupported()) {
                SearchTask search = new SearchTask();
                search.setSearchType(2);
                search.setSearchDuration(duration);
                this.tasks.add(search);
            }
            return this;
        }

        public Builder searchBluetoothLeDevice(int duration, int times) {
            for (int i = 0; i < times; i++) {
                searchBluetoothLeDevice(duration);
            }
            return this;
        }

        public Builder searchBluetoothClassicDevice(int duration) {
            SearchTask search = new SearchTask();
            search.setSearchType(1);
            search.setSearchDuration(duration);
            this.tasks.add(search);
            return this;
        }

        public Builder searchBluetoothClassicDevice(int duration, int times) {
            for (int i = 0; i < times; i++) {
                searchBluetoothClassicDevice(duration);
            }
            return this;
        }

        public SearchRequest build() {
            SearchRequest group = new SearchRequest();
            group.setTasks(this.tasks);
            return group;
        }
    }
}
