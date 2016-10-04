package com.test.starwarsmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by prachi on 04/10/16.
 */
public interface ApiInterface {

    @GET("films/")
    Call<FilmList> getAllFilms(@Query("page") int page);

}
