package com.example.lastfmmusic.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artists {
    @SerializedName("results")
    @Expose
    private QueryResults results;

    public QueryResults getResults() {
        return results;
    }

    public void setResults(QueryResults results) {
        this.results = results;
    }
}
