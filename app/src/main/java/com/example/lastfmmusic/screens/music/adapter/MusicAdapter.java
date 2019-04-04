package com.example.lastfmmusic.screens.music.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.data.Artist;
import com.example.lastfmmusic.data.Artists;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicAdapterViewHolder> {

     private Artists artists;

    public MusicAdapter(Artists artists) {
        this.artists = artists;
    }

    @NonNull
    @Override
    public MusicAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.artist_card,viewGroup,false);

        return new MusicAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapterViewHolder viewHolder, int i) {
         viewHolder.bind(artists.getResults().getArtistmatches().getArtist().get(i));
    }

    @Override
    public int getItemCount() {
        return artists.getResults().getArtistmatches().getArtist().size();
    }


    public class MusicAdapterViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.thumbnail)
        ImageView thumbnail;
         @BindView(R.id.artist_title)
        TextView artistName;
         @BindView(R.id.count) TextView notDecidedYet;



        public MusicAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        public void bind(Artist artist) {
            if(artist!=null) {

                artistName.setText(artist.getName());

                Picasso.get()
                        .load(artist.getUrl())
                        .fit()
                        .into(thumbnail);
            }



        }
    }
}
