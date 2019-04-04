package com.example.lastfmmusic.screens.music.mvp;

import com.example.lastfmmusic.data.Artists;

public interface MusicContract {
    interface MusicView {
        void showArtists(Artists artists);
        void showMessages(String messsge);
    }

    interface MusicPresenter {
        void getArtists();
        void searchAlbums();
        void clear();
    }
}
