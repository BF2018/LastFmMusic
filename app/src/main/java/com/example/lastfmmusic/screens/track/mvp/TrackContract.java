package com.example.lastfmmusic.screens.track.mvp;

import com.example.lastfmmusic.data.track.Tracks;

public interface TrackContract {
    interface TrackView{
        void showDetail(Tracks tracks);
        void showMessage(String message);
    }
    interface TrackPresenter {
        void getTrack(String artistName);
        void onStop();
    }
}
