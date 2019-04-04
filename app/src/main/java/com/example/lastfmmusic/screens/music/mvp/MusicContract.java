package com.example.lastfmmusic.screens.music.mvp;

import com.example.lastfmmusic.data.artist.Artists;

import io.reactivex.annotations.Nullable;

public interface MusicContract {
    interface MusicView {
        void showArtists(Artists artists);
        void showMessages(String messsge);
    }

    interface MusicPresenter {
        void getArtists(@Nullable String artist);
        void searchAlbums();
        void clear();
    }
}
