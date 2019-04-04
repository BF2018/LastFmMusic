package com.example.lastfmmusic.screens.music.di;

import com.example.lastfmmusic.di.AppComponent;
import com.example.lastfmmusic.screens.music.mvp.MusicActivity;
import com.example.lastfmmusic.screens.music.mvp.MusicContract;


import dagger.BindsInstance;
import dagger.Component;

@Component(modules = MusicModule.class,dependencies = AppComponent.class)
@MusicScope
public interface MusicComponent {

    void inject(MusicActivity activity);

    @Component.Builder
    interface  Builder {
        MusicComponent build();
        Builder appComponent(AppComponent component);
        @BindsInstance Builder view(MusicContract.MusicView musicView);
    }

}
