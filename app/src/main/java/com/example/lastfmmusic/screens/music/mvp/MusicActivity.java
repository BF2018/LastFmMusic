package com.example.lastfmmusic.screens.music.mvp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Menu;
import android.widget.Toast;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.app.App;
import com.example.lastfmmusic.data.Artists;
import com.example.lastfmmusic.screens.music.adapter.MusicAdapter;
import com.example.lastfmmusic.screens.music.adapter.OnSelectedArtistListener;
import com.example.lastfmmusic.screens.music.di.DaggerMusicComponent;
import com.example.lastfmmusic.screens.track.mvp.TrackActivity;

import javax.inject.Inject;

public class MusicActivity extends AppCompatActivity implements MusicContract.MusicView, OnSelectedArtistListener {


    @Inject
    MusicContract.MusicPresenter musicPresenter;

    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    RecyclerView.LayoutManager manager;
    LinearLayoutManager man;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        DaggerMusicComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .view(this)
                .build()
                .inject(this);

       initCollapsingToolbar();

        recyclerView = findViewById(R.id.recyclerView);
        musicPresenter.getArtists();

         man = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(man);

    }

    @Override
    public void showArtists(Artists artists) {
       /* manager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new CustomGridLayout(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
        musicAdapter = new MusicAdapter(artists);
        recyclerView.setAdapter(musicAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option,menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
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

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void getSelectedArtrist() {
        Intent intent = new Intent(this, TrackActivity.class);
        intent.putExtra("artist","artist");
        startActivity(intent);
    }
}
