package com.inuker.bluetooth.library.search;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/SearchTask.class */
public class SearchTask implements Parcelable {
    private int searchType;
    private int searchDuration;
    public static final Parcelable.Creator<SearchTask> CREATOR = new Parcelable.Creator<SearchTask>() { // from class: com.inuker.bluetooth.library.search.SearchTask.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchTask createFromParcel(Parcel source) {
            return new SearchTask(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SearchTask[] newArray(int size) {
            return new SearchTask[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.searchType);
        dest.writeInt(this.searchDuration);
    }

    public SearchTask() {
    }

    public int getSearchType() {
        return this.searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getSearchDuration() {
        return this.searchDuration;
    }

    public void setSearchDuration(int searchDuration) {
        this.searchDuration = searchDuration;
    }

    protected SearchTask(Parcel in) {
        this.searchType = in.readInt();
        this.searchDuration = in.readInt();
    }
}
