package com.inuker.bluetooth.library.search.response;

import com.inuker.bluetooth.library.search.SearchResult;

/* JADX INFO: loaded from: vpbluetooth-1.20.aar:classes.jar:com/inuker/bluetooth/library/search/response/SearchResponse.class */
public interface SearchResponse {
    void onSearchStarted();

    void onDeviceFounded(SearchResult searchResult);

    void onSearchStopped();

    void onSearchCanceled();
}
