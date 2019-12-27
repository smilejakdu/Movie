package com.example.movie.MovieWebService;

import com.example.movie.MovieModel.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"X-Naver-Client-Id: BAsdOM5e9NjGTPDzQ78Y", "X-Naver-Client-Secret: whu48NHTRF"})
    @GET("movie.json")
    Call<Movie> getMovies(@Query("query") String title,
                          @Query("display") int displaySize,
                          @Query("start") int startPosition);

}
