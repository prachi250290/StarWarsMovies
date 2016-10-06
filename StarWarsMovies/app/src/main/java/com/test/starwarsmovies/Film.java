package com.test.starwarsmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by prachi on 04/10/16.
 */
public class Film implements Serializable {
    public String title;
    @SerializedName("opening_crawl")
    public String openingCrawl;
    public String director;
    public String producer;
    public String release_date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
