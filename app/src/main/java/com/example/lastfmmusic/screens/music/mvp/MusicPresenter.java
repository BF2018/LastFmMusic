package com.example.lastfmmusic.screens.music.mvp;

import android.util.Log;

import com.example.lastfmmusic.common.Constants;
import com.example.lastfmmusic.data.Artists;
import com.example.lastfmmusic.network.WebService;
import com.example.lastfmmusic.screens.music.mvp.MusicContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MusicPresenter implements MusicContract.MusicPresenter {


    MusicContract.MusicView musicView;
    WebService service;
    CompositeDisposable disposable;
    String keyWord ="cold";


    public MusicPresenter(MusicContract.MusicView musicView,
            WebService service) {
        this.musicView = musicView;
        this.service = service;
        disposable = new CompositeDisposable();
    }

    @Override
    public void getArtists() {
           disposable.add(service.getArtist(Constants.ARTIST_SEARCH,"cold",Constants._KEY,Constants.FORMAT)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Consumer<Artists>() {
                          @Override
                          public void accept(Artists artists) throws Exception {
                                musicView.showArtists(artists);
                          }
                      }, new Consumer<Throwable>() {
                          @Override
                          public void accept(Throwable throwable) throws Exception {
                              musicView.showMessages(throwable.getMessage());
                              Log.e("Presenter error",throwable.getLocalizedMessage());
                          }
                      }));

    }

    @Override
    public void searchAlbums() {

    }

    @Override
    public void clear() {
        disposable.clear();
    }
}
