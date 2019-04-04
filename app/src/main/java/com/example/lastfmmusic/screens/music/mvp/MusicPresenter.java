package com.example.lastfmmusic.screens.music.mvp;

import com.example.lastfmmusic.common.Constants;
import com.example.lastfmmusic.network.WebService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MusicPresenter implements MusicContract.MusicPresenter {


    private MusicContract.MusicView musicView;
    private WebService service;
    private CompositeDisposable disposable;

    public MusicPresenter(MusicContract.MusicView musicView,
            WebService service) {
        this.musicView = musicView;
        this.service = service;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getArtists(String artist) {
        disposable.add(service.getArtist(Constants.ARTIST_SEARCH, artist, Constants.API_KEY, Constants.FORMAT)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                .subscribe(artists -> musicView.showArtists(artists),
                        throwable -> handleError(throwable)));

    }

    @Override
    public void handleError(Throwable error) {
        musicView.showMessages(error.getMessage());
    }

    @Override
    public void clear() {
        disposable.clear();
    }
}
