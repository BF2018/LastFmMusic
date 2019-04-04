package com.example.lastfmmusic.data.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr {
    @SerializedName("for")
    @Expose
    private String _for;

    public String getFor() {
        return _for;
    }
}
