package com.test.starwarsmovies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prachi on 04/10/16.
 */
public class FilmList {


    @SerializedName("results")
    private ArrayList<Film> films;

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }
}
