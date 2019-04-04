package com.example.lastfmmusic.di;

import android.app.Application;
import android.content.Context;

import com.example.lastfmmusic.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    public Context provideApplicationContext(Application application)
    {
        return application;
    }
}
