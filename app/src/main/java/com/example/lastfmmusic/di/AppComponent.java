package com.example.lastfmmusic.di;


import android.app.Application;

import com.example.lastfmmusic.network.WebService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,NetModule.class})
public interface AppComponent {

    WebService service();

    @Component.Builder
    interface Builder {


        @BindsInstance Builder application(Application application);
        AppComponent build();
    }
}
