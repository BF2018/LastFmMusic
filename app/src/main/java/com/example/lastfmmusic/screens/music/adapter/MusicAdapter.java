package com.example.lastfmmusic.screens.music.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lastfmmusic.R;
import com.example.lastfmmusic.data.artist.Artist;
import com.example.lastfmmusic.data.artist.Artists;
import com.example.lastfmmusic.data.artist.Image;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicAdapterViewHolder> {

     private Artists artists;
     private OnSelectedArtistListener selectedArtistListener;

    public MusicAdapter(Artists artists,OnSelectedArtistListener selectedArtistListener) {
        this.artists = artists;
        this.selectedArtistListener = selectedArtistListener;

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

                for (Image url: artist.getImage()) {

                   if(!url.getText().isEmpty())
                    Picasso.get()
                            .load(url.getText())
                            .fit()
                            .into(thumbnail);
                  itemView.setOnClickListener(v -> {
                      if (artist.getName()!=null){
                          selectedArtistListener.getSelectedArtrist(artist.getName());
                      }

                  });
                }


            }



        }
    }
}
