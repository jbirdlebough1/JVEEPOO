package vpno.nordicsemi.android.dfu;

import android.os.SystemClock;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuProgressInfo.class */
class DfuProgressInfo {
    private final ProgressListener mListener;
    private int progress;
    private int bytesSent;
    private int initalBytesSent;
    private int lastBytesSent;
    private int bytesReceived;
    private int imageSizeInBytes;
    private int maxObjectSizeInBytes;
    private int currentPart;
    private int totalParts;
    private long timeStart;
    private long lastProgressTime;

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:vpno/nordicsemi/android/dfu/DfuProgressInfo$ProgressListener.class */
    interface ProgressListener {
        void updateProgressNotification();
    }

    public DfuProgressInfo(ProgressListener progressListener) {
        this.mListener = progressListener;
    }

    public DfuProgressInfo init(int i, int i2, int i3) {
        this.imageSizeInBytes = i;
        this.maxObjectSizeInBytes = Integer.MAX_VALUE;
        this.currentPart = i2;
        this.totalParts = i3;
        return this;
    }

    public DfuProgressInfo setTotalPart(int i) {
        this.totalParts = i;
        return this;
    }

    public void setProgress(int i) {
        this.progress = i;
        this.mListener.updateProgressNotification();
    }

    public void setBytesSent(int i) {
        if (this.timeStart == 0) {
            this.timeStart = SystemClock.elapsedRealtime();
            this.initalBytesSent = i;
        }
        this.bytesSent = i;
        this.progress = (int) ((i * 100.0f) / this.imageSizeInBytes);
        this.mListener.updateProgressNotification();
    }

    public void addBytesSent(int i) {
        setBytesSent(this.bytesSent + i);
    }

    public void setBytesReceived(int i) {
        this.bytesReceived = i;
    }

    public void setMaxObjectSizeInBytes(int i) {
        this.maxObjectSizeInBytes = i;
    }

    public boolean isComplete() {
        return this.bytesSent == this.imageSizeInBytes;
    }

    public boolean isObjectComplete() {
        return this.bytesSent % this.maxObjectSizeInBytes == 0;
    }

    public int getAvailableObjectSizeIsBytes() {
        int i = this.imageSizeInBytes;
        int i2 = this.bytesSent;
        int i3 = i - i2;
        int i4 = this.maxObjectSizeInBytes;
        return Math.min(i3, i4 - (i2 % i4));
    }

    public int getProgress() {
        return this.progress;
    }

    public int getBytesSent() {
        return this.bytesSent;
    }

    public int getBytesReceived() {
        return this.bytesReceived;
    }

    public int getImageSizeInBytes() {
        return this.imageSizeInBytes;
    }

    public float getSpeed() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        float f = jElapsedRealtime - this.timeStart != 0 ? (this.bytesSent - this.lastBytesSent) / (jElapsedRealtime - this.lastProgressTime) : 0.0f;
        this.lastProgressTime = jElapsedRealtime;
        this.lastBytesSent = this.bytesSent;
        return f;
    }

    public float getAverageSpeed() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.timeStart;
        if (jElapsedRealtime - j != 0) {
            return (this.bytesSent - this.initalBytesSent) / (jElapsedRealtime - j);
        }
        return 0.0f;
    }

    public int getCurrentPart() {
        return this.currentPart;
    }

    public int getTotalParts() {
        return this.totalParts;
    }

    public boolean isLastPart() {
        return this.currentPart == this.totalParts;
    }
}
