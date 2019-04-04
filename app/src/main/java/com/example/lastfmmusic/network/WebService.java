package com.example.lastfmmusic.network;


import com.example.lastfmmusic.data.Artists;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    // to indicate that the url is same as base url
    @GET(".")
    Observable <Artists> getArtist(@Query("method") String method,
                              @Query("artist") String artist,
                              @Query("api_key") String apiKey,
                              @Query("format") String format);

}
