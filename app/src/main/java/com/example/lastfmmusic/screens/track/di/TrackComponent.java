package com.example.lastfmmusic.screens.track.di;


import com.example.lastfmmusic.di.AppComponent;
import com.example.lastfmmusic.screens.track.mvp.TrackActivity;
import com.example.lastfmmusic.screens.track.mvp.TrackContract;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = TrackModule.class,dependencies = AppComponent.class)
@TrackScope
public interface TrackComponent {
    void inject(TrackActivity activity);

    @Component.Builder
    interface Builder {
        TrackComponent build();
        Builder appComponent(AppComponent component);
        @BindsInstance Builder trackView(TrackContract.TrackView trackView);
    }
}
