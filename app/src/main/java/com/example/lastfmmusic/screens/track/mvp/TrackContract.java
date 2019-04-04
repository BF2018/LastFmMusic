package com.example.lastfmmusic.screens.track.mvp;

public interface TrackContract {
    interface TrackView{
        void showTrack();
    }
    interface TrackPresenter {
        void getTrack();
    }
}
