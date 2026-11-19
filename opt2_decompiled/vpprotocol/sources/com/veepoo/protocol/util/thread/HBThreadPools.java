package com.veepoo.protocol.util.thread;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/HBThreadPools.class */
public class HBThreadPools extends AbsThreadPoolManager {

    /* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/util/thread/HBThreadPools$HBThreadPoolsHandler.class */
    public static final class HBThreadPoolsHandler {
        private static final HBThreadPools vp_a = new HBThreadPools();
    }

    public static AbsThreadPoolManager getInstance() {
        return HBThreadPoolsHandler.vp_a;
    }

    @Override // com.veepoo.protocol.util.thread.AbsThreadPoolManager
    protected String getThreadPoolName() {
        return "HB-ThreadPools";
    }
}
