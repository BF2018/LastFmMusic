package com.example.lastfmmusic.screens.track.mvp;

public class TrackPresenter implements TrackContract.TrackPresenter{

   private TrackContract.TrackView trackView;


     public TrackPresenter(TrackContract.TrackView trackView){
         this.trackView = trackView;

     }



    @Override
    public void getTrack() {

    }
}
