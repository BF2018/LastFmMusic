package com.example.lastfmmusic.data.track;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracks {

    @SerializedName("results")
    @Expose
    private TrackResults results;

    public TrackResults getResults() {
        return results;
    }

    public void setResults(TrackResults results) {
        this.results = results;
    }

}
