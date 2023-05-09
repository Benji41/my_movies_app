package com.example.mymovies.data.network

import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/6/2023.
 */
interface MoviesApi {

    @GET("3/movie/top_rated")
    suspend fun getAllTopRatedMovies() : Response<ResponseMoviesAPI>

    @GET("3/movie/now_playing")
    suspend fun getAllPlayingNowMovies() : Response<ResponseMoviesAPI>

}
