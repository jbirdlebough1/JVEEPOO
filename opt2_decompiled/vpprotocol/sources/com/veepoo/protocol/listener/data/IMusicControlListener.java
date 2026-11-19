package com.veepoo.protocol.listener.data;

/* JADX INFO: loaded from: vpprotocol-2.3.96.15.aar:classes.jar:com/veepoo/protocol/listener/data/IMusicControlListener.class */
public interface IMusicControlListener {
    void nextMusic();

    void previousMusic();

    void pauseAndPlayMusic();

    void pauseMusic();

    void playMusic();

    void voiceUp();

    void voiceDown();

    void oprateMusicSuccess();

    void oprateMusicFail();
}
