package com.example.nikhil.moviereviews.apis;

import com.example.nikhil.moviereviews.CastPOJO.RICast;
import com.example.nikhil.moviereviews.PopularMovies.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by NIKHIL on 22-06-2018.
 */

public interface API {


    //Base url

    String base_url = "https://api.themoviedb.org/3/movie/";

    //Enter end point and method
    @GET("popular?api_key=Your_API_Key")
    //Call<List<Result>> getMovies();

    Call<PopularMovies> getMovies();

    @GET("{Movie_Id}?api_key=Your_API_KEY&append_to_response=credits")

    Call<RICast> castishere(@Path("Movie_Id") int id);
}
