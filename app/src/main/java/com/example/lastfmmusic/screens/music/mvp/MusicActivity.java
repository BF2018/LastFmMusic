package com.example.lastfmmusic.screens.music.mvp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.Toast;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.app.App;
import com.example.lastfmmusic.data.artist.Artists;
import com.example.lastfmmusic.screens.music.adapter.CustomGridLayout;
import com.example.lastfmmusic.screens.music.adapter.MusicAdapter;
import com.example.lastfmmusic.screens.music.adapter.OnSelectedArtistListener;
import com.example.lastfmmusic.screens.music.di.DaggerMusicComponent;
import com.example.lastfmmusic.screens.track.mvp.TrackActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MusicActivity extends AppCompatActivity implements MusicContract.MusicView, OnSelectedArtistListener {


    @Inject
    MusicContract.MusicPresenter musicPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MusicAdapter musicAdapter;
    private RecyclerView.LayoutManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);
        ButterKnife.bind(this);
        DaggerMusicComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .view(this)
                .build()
                .inject(this);

        musicAdapter = new MusicAdapter(this);
        manager = new GridLayoutManager(getApplicationContext(),2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CustomGridLayout(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(musicAdapter);

        // just no to return empty view for the user
        musicPresenter.getArtists("coldplay");

    }

    @Override
    public void showArtists(Artists artists) {

        musicAdapter.updateData(artists.getResults().getArtistmatches().getArtist());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option,menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                    if (query != null && !query.isEmpty()) {
                        musicPresenter.getArtists(query);
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                if (s != null && !s.isEmpty()) {
                    musicPresenter.getArtists(s);
                }


                return false;
            }
        });
        return true;
    }



    @Override
    public void showMessages(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
    private int dpToPx(int dp) {

        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public void getSelectedArtist(String artistName) {
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra("query",artistName);
        startActivity(intent);
    }
}
