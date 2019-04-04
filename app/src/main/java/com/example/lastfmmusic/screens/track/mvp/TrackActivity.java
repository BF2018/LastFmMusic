package com.example.lastfmmusic.screens.track.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.app.App;
import com.example.lastfmmusic.data.artist.Image;
import com.example.lastfmmusic.data.track.Track;
import com.example.lastfmmusic.data.track.Tracks;
import com.example.lastfmmusic.screens.track.di.DaggerTrackComponent;
import com.squareup.picasso.Picasso;

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
        Intent intent = getIntent();
        String artistName = intent.getStringExtra("query");
        if(artistName!=null){
            presenter.getTrack(artistName);

        }
    }


    @Override
    public void showDetail(Tracks tracks) {
        for (Track track:tracks.getResults().getTrackmatches().getTrack())
        {
            songTitile.setText(track.getName());
            song.setText(track.getArtist());
            Picasso.get()
                    .load(track.getImage().get(0).getText())
                    .fit()
                    .into(trackPic);
            
        }
        
        

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }
}
