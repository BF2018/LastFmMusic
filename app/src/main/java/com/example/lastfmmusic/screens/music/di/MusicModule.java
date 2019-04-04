package com.example.lastfmmusic.screens.music.di;

import com.example.lastfmmusic.network.WebService;
import com.example.lastfmmusic.screens.music.mvp.MusicContract;
import com.example.lastfmmusic.screens.music.mvp.MusicPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MusicModule {

    @Provides
    @MusicScope
    public MusicContract.MusicPresenter providePresenter(MusicContract.MusicView musicView, WebService service){

        return new MusicPresenter(musicView,service);

    }
}
