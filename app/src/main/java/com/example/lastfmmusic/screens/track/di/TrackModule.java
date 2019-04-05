package com.example.lastfmmusic.screens.track.di;

import com.example.lastfmmusic.network.WebService;
import com.example.lastfmmusic.screens.track.mvp.TrackContract;
import com.example.lastfmmusic.screens.track.mvp.TrackPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackModule {
    @Provides
    @TrackScope
    public TrackContract.TrackPresenter providePresenter(TrackContract.TrackView trackView, WebService service){

        return new TrackPresenter(trackView,service);

    }
}
