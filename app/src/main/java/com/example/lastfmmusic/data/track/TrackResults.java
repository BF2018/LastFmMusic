package com.example.lastfmmusic.data.track;

import com.example.lastfmmusic.data.artist.Attr;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackResults {

    @SerializedName("opensearch:Query")
    @Expose
    private TrackSearchQuery opensearchQuery;
    @SerializedName("opensearch:totalResults")
    @Expose
    private String opensearchTotalResults;
    @SerializedName("opensearch:startIndex")
    @Expose
    private String opensearchStartIndex;
    @SerializedName("opensearch:itemsPerPage")
    @Expose
    private String opensearchItemsPerPage;
    @SerializedName("trackmatches")
    @Expose
    private TrackMatches trackmatches;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public TrackSearchQuery getOpensearchQuery() {
        return opensearchQuery;
    }

    public void setOpensearchQuery(TrackSearchQuery opensearchQuery) {
        this.opensearchQuery = opensearchQuery;
    }

    public String getOpensearchTotalResults() {
        return opensearchTotalResults;
    }

    public void setOpensearchTotalResults(String opensearchTotalResults) {
        this.opensearchTotalResults = opensearchTotalResults;
    }

    public String getOpensearchStartIndex() {
        return opensearchStartIndex;
    }

    public void setOpensearchStartIndex(String opensearchStartIndex) {
        this.opensearchStartIndex = opensearchStartIndex;
    }

    public String getOpensearchItemsPerPage() {
        return opensearchItemsPerPage;
    }

    public void setOpensearchItemsPerPage(String opensearchItemsPerPage) {
        this.opensearchItemsPerPage = opensearchItemsPerPage;
    }

    public TrackMatches getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(TrackMatches trackmatches) {
        this.trackmatches = trackmatches;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

}
