package com.example.lastfmmusic.screens.track.di;

import com.example.lastfmmusic.screens.music.di.MusicScope;
import com.example.lastfmmusic.screens.track.mvp.TrackContract;
import com.example.lastfmmusic.screens.track.mvp.TrackPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TrackModule {
    @Provides
    @TrackScope
    public TrackContract.TrackPresenter providePresenter(TrackContract.TrackView trackView){

        return new TrackPresenter(trackView);

    }
}
