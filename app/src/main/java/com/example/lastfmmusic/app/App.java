package com.example.lastfmmusic.app;

import android.app.Application;

import com.example.lastfmmusic.di.AppComponent;
import com.example.lastfmmusic.di.AppModule;
import com.example.lastfmmusic.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate(){
        super.onCreate();
        component = DaggerAppComponent.builder()
                  .application(this)
                  .build();

    }

    public AppComponent getComponent(){
        return component;
    }

}
