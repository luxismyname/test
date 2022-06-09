package com.example.thenewmoviedb.data.api

import com.example.thenewmoviedb.vo.MovieDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMovieDbInterface {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int) :Single<MovieDetails>

}