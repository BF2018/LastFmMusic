package com.example.lastfmmusic.screens.track.mvp;

import android.util.Log;
import android.webkit.WebView;

import com.example.lastfmmusic.common.Constants;
import com.example.lastfmmusic.data.artist.Artists;
import com.example.lastfmmusic.data.track.Tracks;
import com.example.lastfmmusic.network.WebService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TrackPresenter implements TrackContract.TrackPresenter{

   private TrackContract.TrackView trackView;
   WebService service;
   private CompositeDisposable disposable;

     public TrackPresenter(TrackContract.TrackView trackView, WebService service){
         this.service =service;
         this.trackView = trackView;
         disposable = new CompositeDisposable();
     }



    @Override
    public void getTrack(String artistName) {

        disposable.add(service.getTracks("track.search",artistName,Constants._KEY,Constants.FORMAT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Tracks>() {
                    @Override
                    public void accept(Tracks tracks) throws Exception {
                        trackView.showDetail(tracks);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                       trackView.showMessage(throwable.getMessage());
                    }
                }));

    }

    @Override
    public void onStop() {
       disposable.clear();
    }
}
