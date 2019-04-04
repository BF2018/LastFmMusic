package com.example.lastfmmusic.data.artist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryResults {
    @SerializedName("opensearch:Query")
    @Expose
    private OpenSearchQuery opensearchQuery;
    @SerializedName("opensearch:totalResults")
    @Expose
    private String opensearchTotalResults;
    @SerializedName("opensearch:startIndex")
    @Expose
    private String opensearchStartIndex;
    @SerializedName("opensearch:itemsPerPage")
    @Expose
    private String opensearchItemsPerPage;
    @SerializedName("artistmatches")
    @Expose
    private ArtistMatches artistmatches;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    public OpenSearchQuery getOpensearchQuery() {
        return opensearchQuery;
    }

    public void setOpensearchQuery(OpenSearchQuery opensearchQuery) {
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

    public ArtistMatches getArtistmatches() {
        return artistmatches;
    }



    public void setArtistmatches(ArtistMatches artistmatches) {
        this.artistmatches = artistmatches;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }
}
