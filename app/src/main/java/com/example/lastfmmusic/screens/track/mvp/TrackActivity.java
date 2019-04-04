package com.example.lastfmmusic.screens.track.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.app.App;
import com.example.lastfmmusic.data.Image;
import com.example.lastfmmusic.screens.track.di.DaggerTrackComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackActivity extends AppCompatActivity implements TrackContract.TrackView {


    @Inject
    TrackContract.TrackPresenter presenter;

     @BindView(R.id.song_title)
    TextView songTitile;
     @BindView(R.id.song) TextView song;
     @BindView(R.id.track_thumbnail)
    ImageView trackPic;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        ButterKnife.bind(this);

        DaggerTrackComponent.builder()
                .appComponent(((App)getApplication()).getComponent())
                .trackView(this)
                .build()
                .inject(this);
    }

    @Override
    public void showTrack() {

    }
}
