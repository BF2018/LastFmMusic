package com.example.lastfmmusic.screens.track.mvp;

import com.example.lastfmmusic.common.Constants;
import com.example.lastfmmusic.network.WebService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TrackPresenter implements TrackContract.TrackPresenter{

   private TrackContract.TrackView trackView;
    private WebService service;
   private CompositeDisposable disposable;

     public TrackPresenter(TrackContract.TrackView trackView, WebService service){
         this.service =service;
         this.trackView = trackView;
         disposable = new CompositeDisposable();
     }



    @Override
    public void getTrack(String artistName) {

        disposable.add(service.getTracks("track.search", artistName, Constants.API_KEY, Constants.FORMAT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trackView::showDetail,
                        throwable -> trackView.showMessage(throwable.getMessage())));

    }

    @Override
    public void onStop() {
       disposable.clear();
    }
}
